package com.smart.om.dao.advert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import com.smart.om.dao.base.BaseDao;
import com.smart.om.dto.advert.AdvertPositionDto;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import com.smart.om.util.PageModel;

/**
 * 广告位置DAO
 * @author CA
 *
 */
public class AdvertPositionDAO extends BaseDao{
	/** 查询广告位置 **/
	public DTablePageModel queryAdvertPositionPage(Map<String,Object> params,PageData pageData){
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();		
		hql.append(" select model1.advertInfoId,model2.adId,model3.deviceId,model1.advertTitle,model2.advertFee,model3.deviceNo,model3.deviceName,model3.deviceAddress,model3.deviceStatus,model2.adStatus ");
		hql.append(" from AdvertInfo model1,AdvertDevice model2,DeviceInfo model3 where 1 = 1 ");
		hql.append(" and model1.advertInfoId = model2.advertInfoId and model2.deviceId = model3.deviceId ");
		hqlCount.append("select count(model2.adId)  ");		
		hqlCount.append("from AdvertInfo model1,AdvertDevice model2,DeviceInfo model3 where 1 = 1 ");
		hqlCount.append(" and model1.advertInfoId = model2.advertInfoId and model2.deviceId = model3.deviceId ");
		if(params != null) {
			if(params.containsKey("keyword")) {
				String keyword = (String) params.get("keyword");
				if(StringUtils.isNotBlank(keyword)) {
					hql.append(" and (model3.deviceName like '%").append(keyword).append("%'")
					.append(" or model1.advertTitle like '%").append(keyword).append("%'")
					.append(" or model3.deviceAddress like '%").append(keyword).append("%') ");
					hqlCount.append(" and (model3.deviceName like '%").append(keyword).append("%'")
					.append(" or model1.advertTitle like '%").append(keyword).append("%'")
					.append(" or model3.deviceAddress like '%").append(keyword).append("%') ");
				}
			}
			if(params.containsKey("positionStatus")) {
				String positionStatus = (String) params.get("positionStatus");
				if(StringUtils.isNotBlank(positionStatus)) {
					hql.append(" and model2.adStatus =  ").append(positionStatus);
					hqlCount.append(" and model2.adStatus =  ").append(positionStatus);
				}
			}
			if(params.containsKey("deviceStatus")) {
				String deviceStatus = (String) params.get("deviceStatus");
				if(StringUtils.isNotBlank(deviceStatus)) {
					hql.append(" and model3.deviceStatus = ").append(deviceStatus);
					hqlCount.append(" and model3.deviceStatus = ").append(deviceStatus);
				}
			}
			 if (params.containsKey("orgId")) {
	                Integer orgId = (Integer) params.get("orgId");
	                if (orgId>0) {
	                	hql.append(" and model3.dictOrgId =").append(orgId);
	                	hqlCount.append(" and model3.dictOrgId =").append(orgId);
	                }
	            }
			 if (params.containsKey("regionId")) {
	                Integer regionId = (Integer) params.get("regionId");
	                if (regionId>0) {
	                	hql.append(" and model3.dictRegionId =").append(regionId);
	                	hqlCount.append(" and model3.dictRegionId =").append(regionId);
	                }
	            }
	         if (params.containsKey("proviceId")) {
	               Integer proviceId = (Integer) params.get("proviceId");
	               if (proviceId>0) {
	                	hql.append(" and model3.dictProviceId =").append(proviceId);
	                	hqlCount.append(" and model3.dictProviceId =").append(proviceId);
	                }
	            }
			
		}
		hql.append(" order by model2.adId desc  ");
		DTablePageModel pageModel = new DTablePageModel();
		pageModel.setsEcho(pageData.getsEcho());
		PageModel pm = super.queryPageByHqlMySQl(hql.toString(), hqlCount.toString(), null, pageData.getCurrentPage(), pageData.getPageSize());
		if(pm != null) {
			pageModel.setiTotalDisplayRecords(pm.getTotal());
			pageModel.setiTotalRecords(pm.getTotal());
			@SuppressWarnings("unchecked")
			List<Object[]> rowLst = pm.getDatas();
			if(rowLst != null) {
				List<AdvertPositionDto> aidLst = new ArrayList<AdvertPositionDto>();
				for(Object[] row : rowLst) {
					AdvertPositionDto aid = new AdvertPositionDto();
					aid.setAdvertInfoId((Integer)row[0]);
					aid.setAdId((Integer)row[1]);
					aid.setDeviceId((Integer)row[2]);
					aid.setAdvertTitle((String)row[3]);
					aid.setAdvertFee((Double)row[4]);
					aid.setDeviceNo((String)row[5]);
					aid.setDeviceName((String)row[6]);
					aid.setDeviceAddress((String)row[7]);
					aid.setDeviceStatus((String)row[8]);
					aid.setAdStatus((String)row[9]);
					aidLst.add(aid);
				}
				pageModel.setAaData(aidLst);
			}
		}
		return pageModel;
	}
	
	/** 根据广告内容ID查询广告投放分页信息 **/
	public List<AdvertPositionDto> queryAdvertDeviceByAdId(Integer id){
		Session session = null;
		List<AdvertPositionDto> list = new ArrayList<AdvertPositionDto>();
		try{
			String hql = "select di.deviceId,di.deviceName,di.deviceNo,di.deviceAddress,ad.advertFee,ad.adId from AdvertDevice ad , DeviceInfo di where ad.deviceId = di.deviceId and ad.advertInfoId = " + id;
			session = this.getSession();
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Object[]> rowList = query.list();
			for(Object[] row : rowList) {
				AdvertPositionDto aid = new AdvertPositionDto();
				aid.setDeviceId((Integer)row[0]);
				aid.setDeviceName((String)row[1]);
				aid.setDeviceNo((String)row[2]);
				aid.setDeviceAddress((String)row[3]);
				aid.setAdvertFee((Double)row[4]);
				aid.setAdId((Integer)row[5]);
				list.add(aid);
			}
		}catch(Exception e){
			session.close();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	
}
