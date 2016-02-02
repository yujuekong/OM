package com.smart.om.dao.advert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.dao.base.BasePo;
import com.smart.om.dto.advert.AdvertInfoDto;
import com.smart.om.dto.device.DeviceInfoDto;
import com.smart.om.persist.AdvertDevice;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import com.smart.om.util.PageModel;

/**
 * 广告内容DAO
 * @author CA
 *
 */
public class AdvertInfoDAO extends BaseDao{
	
	/** 查询广告内容 **/
	public DTablePageModel queryAdvertInfoPage(Map<String,Object> params,PageData pageData){
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();		
		hql.append(" select model1.advertInfoId,model1.advertUserId,model3.auName,model1.advertTitle,model1.advertContent,model1.startDate,"
				+ "model1.endDate,model1.advertStatus,model1.createDate,model3.auLinktel,"
				+ "model1.advertTotalFee,model1.payStatus,model1.payFee,model1.advertFileType,model1.advertFile ");
		hql.append(" from AdvertInfo model1,AdvertUser model3  where 1 = 1 ");
		hql.append(" and model1.advertUserId = model3.advertUserId ");
		hqlCount.append("select count(model1.advertInfoId)  ");		
		hqlCount.append("from AdvertInfo model1,AdvertUser model3 where 1 = 1 ");
		hqlCount.append(" and model1.advertUserId = model3.advertUserId ");
		if(params != null) {
			if(params.containsKey("keyword")){
				String keyword = (String) params.get("keyword");
				if(StringUtils.isNotBlank(keyword)) {
					hql.append(" and (model1.advertTitle like '%").append(keyword).append("%'")
					.append(" or model3.auName like '%").append(keyword).append("%') ");
					hqlCount.append(" and (model1.advertTitle like '%").append(keyword).append("%'")
					.append(" or model3.auName like '%").append(keyword).append("%') ");
				}
			}
			if(params.containsKey("advertInfoStatus")) {
				String advertInfoStatus = (String) params.get("advertInfoStatus");
				if(StringUtils.isNotBlank(advertInfoStatus)) {
					hql.append(" and model1.advertStatus = ").append(advertInfoStatus);
					hqlCount.append(" and model1.advertStatus = ").append(advertInfoStatus);
				}
			}
			if(params.containsKey("advertFeeStatus")) {
				String advertFeeStatus = (String) params.get("advertFeeStatus");
				if(StringUtils.isNotBlank(advertFeeStatus)) {
					hql.append(" and model1.payStatus = ").append(advertFeeStatus);
					hqlCount.append(" and model1.payStatus = ").append(advertFeeStatus);
				}
			}
			 if (params.containsKey("orgId")) {
	                String orgId = (String) params.get("orgId");
	                if (StringUtils.isNotBlank(orgId)) {
	                	hql.append(" and model3.orgId =").append(Integer.valueOf(orgId));
	                	hqlCount.append(" and model3.orgId =").append(Integer.valueOf(orgId));
	                }
	            }
			 if (params.containsKey("regionId")) {
	                String regionId = (String) params.get("regionId");
	                if (StringUtils.isNotBlank(regionId)) {
	                	hql.append(" and model3.regionId =").append(Integer.valueOf(regionId));
	                	hqlCount.append(" and model3.regionId =").append(Integer.valueOf(regionId));
	                }
	            }
	         if (params.containsKey("proviceId")) {
	               String proviceId = (String) params.get("proviceId");
	               if (StringUtils.isNotBlank(proviceId)) {
	                	hql.append(" and model3.proviceId =").append(Integer.valueOf(proviceId));
	                	hqlCount.append(" and model3.proviceId =").append(Integer.valueOf(proviceId));
	                }
	            }
		}
		hql.append(" order by model1.advertInfoId desc  ");

		DTablePageModel pageModel = new DTablePageModel();
		pageModel.setsEcho(pageData.getsEcho());
		PageModel pm = super.queryPageByHqlMySQl(hql.toString(), hqlCount.toString(), null, pageData.getCurrentPage(), pageData.getPageSize());
		if(pm != null) {
			pageModel.setiTotalDisplayRecords(pm.getTotal());
			pageModel.setiTotalRecords(pm.getTotal());
			@SuppressWarnings("unchecked")
			List<Object[]> rowLst = pm.getDatas();
			if(rowLst != null) {
				List<AdvertInfoDto> aidLst = new ArrayList<AdvertInfoDto>();
				for(Object[] row : rowLst) {
					AdvertInfoDto aid = new AdvertInfoDto();
					aid.setAdvertInfoId((Integer)row[0]);
					aid.setAdvertUserId((Integer)row[1]);
					aid.setAuName((String)row[2]);
					aid.setAdvertTitle((String)row[3]);
					aid.setAdvertContent((String)row[4]);
					aid.setStartDate((String)row[5]);
					aid.setEndDate((String)row[6]);
					aid.setAdvertStatus((String)row[7]);
					aid.setCreateDate((String)row[8]);
					aid.setAuLinktel((String)row[9]);
					aid.setAdvertTotalFee((Double)row[10]);
					aid.setPayStatus((String)row[11]);
					aid.setPayFee((Double)row[12]);
					aid.setAdvertFileType((String)row[13]);
					aid.setAdvertFile((String)row[14]);
					aidLst.add(aid);
				}
				pageModel.setAaData(aidLst);
			}
		}
		return pageModel;
	}
	
	/** 新增广告信息 **/
	public BasePo saveOrUpdateAdvertInfo(BasePo basePo) {
		return super.saveOrUpdate(basePo);
	}
	
	/** 删除广告信息 **/
	public BasePo delete(Integer id){
		return super.delete(id);
	}
	
	/** 查询单个广告信息 **/
	public BasePo queryAdvertInfoByid(Integer id){
		return super.find(id);
	}
	
	/** 查询所有广告主 **/
	public DTablePageModel queryAdvertUser(Map<String, Object> params, PageData pageData){
		if(pageData == null) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append(" from AdvertUser ad where 1 = 1 ");		
		hqlCount.append("select count(*) from AdvertUser au where 1 = 1");
		hql.append(" order by au.advertUserId  ");
		return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
	}
	
	/** 查询所有投放的设备信息 **/
	public DTablePageModel queryDeviceInfo(Map<String, Object> params, PageData pageData){
		if(pageData == null) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append("select di.deviceId,di.deviceTypeId,di.deviceNo,di.deviceName,di.deviceEara,di.deviceWeight, ");
		hql.append(" di.createDate,dt.deviceTypeName,di.deviceAddress,di.deviceStatus ");	
		hql.append(" from DeviceInfo di, DeviceType dt ,AdvertDevice ad  where  ad.deviceId = di.deviceId and di.deviceTypeId = dt.deviceTypeId");
		hqlCount.append("select count(*) from DeviceInfo di, DeviceType dt ,AdvertDevice ad where ad.deviceId = di.deviceId and di.deviceTypeId = dt.deviceTypeId ");
		if (params != null) {
			if (params.containsKey("advertInfoId")) {
                String advertInfoId = (String) params.get("advertInfoId");
                if (StringUtils.isNotBlank(advertInfoId)) {
                	hql.append(" and ad.advertInfoId =").append(Integer.valueOf(advertInfoId));
                	hqlCount.append(" and ad.advertInfoId =").append(Integer.valueOf(advertInfoId));
                }
            }
			if (params.containsKey("dictRegionId")) {
                String dictRegionId = (String) params.get("dictRegionId");
                if (StringUtils.isNotBlank(dictRegionId)) {
                	hql.append(" and di.dictRegionId =").append(Integer.valueOf(dictRegionId));
                	hqlCount.append(" and di.dictRegionId =").append(Integer.valueOf(dictRegionId));
                }
            }
            if (params.containsKey("dictProviceId")) {
                String dictProviceId = (String) params.get("dictProviceId");
                if (StringUtils.isNotBlank(dictProviceId)) {
                	hql.append(" and di.dictProviceId =").append(Integer.valueOf(dictProviceId));
                	hqlCount.append(" and di.dictProviceId =").append(Integer.valueOf(dictProviceId));
                }
            }
            if (params.containsKey("dictOrgId")) {
                String dictOrgId = (String) params.get("dictOrgId");
                if (StringUtils.isNotBlank(dictOrgId)) {
                	hql.append(" and di.dictOrgId =").append(Integer.valueOf(dictOrgId));
                	hqlCount.append(" and di.dictOrgId =").append(Integer.valueOf(dictOrgId));
                }
            }
            if (params.containsKey("deviceStatus")) {
                String deviceStatus = (String) params.get("deviceStatus");
                if (StringUtils.isNotBlank(deviceStatus)) {
                	hql.append(" and di.deviceStatus =").append(Integer.valueOf(deviceStatus));
                	hqlCount.append(" and di.deviceStatus =").append(Integer.valueOf(deviceStatus));
                }
            }
		}
		
		DTablePageModel pageModel = new DTablePageModel();
		pageModel.setsEcho(pageData.getsEcho());
		PageModel pm = super.queryPageByHqlMySQl(hql.toString(), hqlCount.toString(), null, pageData.getCurrentPage(), pageData.getPageSize());
		if(pm != null) {
			pageModel.setiTotalDisplayRecords(pm.getTotal());
			pageModel.setiTotalRecords(pm.getTotal());
			@SuppressWarnings("unchecked")
			List<Object[]> rowLst = pm.getDatas();
			if(rowLst != null) {
				List<DeviceInfoDto> didLst = new ArrayList<DeviceInfoDto>();
				for(Object[] row : rowLst) {
					DeviceInfoDto did = new DeviceInfoDto();
					did.setDeviceId((Integer)row[0]);
					did.setDeviceTypeId((Integer)row[1]);
					did.setDeviceNo((String)row[2]);
					did.setDeviceName((String)row[3]);
					did.setDeviceEara((String)row[4]);
					did.setDeviceWeight((String)row[5]);
					did.setCreateDate((String)row[6]);
					did.setDeviceTypeName((String)row[7]);
					did.setDeviceAddress((String)row[8]);
					did.setDeviceStatus((String)row[9]);
					didLst.add(did);
				}
				pageModel.setAaData(didLst);
			}
		}
		hql.append(" order by di.deviceId  ");
		return pageModel;
	}
	
	/** 查询广告主分页信息 **/
	public DTablePageModel queryUserPage(Map<String, Object> params, PageData pageData){
		if(pageData == null) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append(" from AdvertUser sg where 1 = 1 ");	
		hql.append(" and sg.auStatus = '").append(Const.IS_VALID_TRUE).append("'");
		hqlCount.append("select count(*) from AdvertUser sg where 1 = 1");
		hqlCount.append(" and sg.auStatus = '").append(Const.IS_VALID_TRUE).append("'");
		if(params != null) {
			 if (params.containsKey("orgId")) {
	                Integer orgId = (Integer) params.get("orgId");
	                if (orgId>0) {
	                	hql.append(" and sg.orgId =").append(orgId);
	                	hqlCount.append(" and sg.orgId =").append(orgId);
	                }
	            }
			 if (params.containsKey("regionId")) {
	                Integer regionId = (Integer) params.get("regionId");
	                if (regionId>0) {
	                	hql.append(" and sg.regionId =").append(regionId);
	                	hqlCount.append(" and sg.regionId =").append(regionId);
	                }
	            }
	         if (params.containsKey("proviceId")) {
	               Integer proviceId = (Integer) params.get("proviceId");
	               if (proviceId>0) {
	                	hql.append(" and sg.proviceId =").append(proviceId);
	                	hqlCount.append(" and sg.proviceId =").append(proviceId);
	                }
	            }
           
		}
		hql.append(" order by sg.advertUserId desc ");
		return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
	}
	
	/** 查询广告投放设备的数量 **/
	public Long totalDevice(Integer advertInfoId){
		Session session = null;
		long sum = 0;
		try{
			String hqlCount = "select count(*) from AdvertDevice ad where ad.advertInfoId =" + advertInfoId;
			System.out.println(hqlCount.toString());
			session = this.getSession();
			Query query = session.createQuery(hqlCount);
			@SuppressWarnings("rawtypes")
			List list = query.list();
			sum = (Long) list.get(0);
		}catch(Exception e){
			session.close();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return sum;
	}
	
	/** 查询广告投放设备 **/
	public List<AdvertDevice> queryAdvertDevice(Integer advertInfoId){
		String hql = "from AdvertDevice ad where ad.advertInfoId = " + advertInfoId;
		List<AdvertDevice> list = this.find(hql, null);
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
}
