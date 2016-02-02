package com.smart.om.dao.maintain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.dto.advert.AdvertInfoDto;
import com.smart.om.dto.device.DeviceMaintainDto;
import com.smart.om.persist.DeviceMaintain;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.PageData;
import com.smart.om.util.PageModel;

public class DeviceMaintainDao extends BaseDao {
	 /**
     * 查询巡检任务
     **/
    public DTablePageModel queryDeviceMaintain(Map<String, Object> params, PageData pageData) {
    	if (pageData == null) {
            return null;
        }
    	
    	StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from DeviceMaintain dw where 1 = 1 ");
        hqlCount.append("select count(*) from DeviceMaintain dw where 1 = 1");
        
        if (params != null) {
            if (params.containsKey("maintainIsFinish")) {
            	 String maintainIsFinish = (String) params.get("maintainIsFinish");
            	 String maintainDate =(String) params.get("maintainDate");
            	 String maintainDateNext = DateUtil.getAfterDayOfSpecified(maintainDate);
            	 
            	 if(StringUtils.isNotBlank(maintainIsFinish)){
            		 if("0".equals(maintainIsFinish)){
            			 hql.append(" and dw.maintainIsFinish='"+maintainIsFinish+"' ");
                		 hqlCount.append(" and dw.maintainIsFinish='"+maintainIsFinish+"' ");
                		 hql.append(" and dw.lastTime<'"+maintainDateNext+"'");
                		 hqlCount.append(" and dw.lastTime<'"+maintainDateNext+"' ");
                		 
            		 }else if("1".equals(maintainIsFinish)){
            			 hql.append(" and dw.maintainIsFinish='"+maintainIsFinish+"' and dw.thisTime>='"+maintainDate+"' and dw.thisTime<'"+maintainDateNext+"' ");
                		 hqlCount.append("  and dw.maintainIsFinish='"+maintainIsFinish+"' and dw.thisTime>='"+maintainDate+"' and dw.thisTime<'"+maintainDateNext+"'  ");
            		 }
            	 }else{
            		 hql.append(" and (dw.maintainIsFinish='0' and dw.lastTime<'"+maintainDateNext+"' "
            		 		+ "or  dw.maintainIsFinish='1' and dw.thisTime>='"+maintainDate+"' and dw.thisTime<'"+maintainDateNext+"') ");
            		 hqlCount.append(" and (dw.maintainIsFinish='0' and dw.lastTime<'"+maintainDateNext+"' "
            		 		+ "or  dw.maintainIsFinish='1' and dw.thisTime>='"+maintainDate+"' and dw.thisTime<'"+maintainDateNext+"') ");
        		 }
        		 
            }
            if (params.containsKey("orgId")) {
            	Integer orgId = Integer.parseInt(params.get("orgId").toString());
            	if(null!=orgId && orgId!=0){
            		hql.append(" and  dw.deviceId in (select deviceId from DeviceInfo where dictOrgId="+orgId+")");
            		 hqlCount.append(" and  dw.deviceId in (select deviceId from DeviceInfo where dictOrgId="+orgId+")");
            	}
            }
        }
        hql.append(" order by dw.maintainIsFinish,dw.lastTime");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }
    /**
     * 查询巡检分页信息
     */
	public DTablePageModel queryInspectionPage(Map<String, Object> params,
			PageData pageData){
		if(pageData == null) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append(" select dm.maintainId,di.deviceNo,di.deviceName,dm.lastTime,dm.thisTime,dm.maintainUser,dm.remark,dm.maintainIsFinish ");
		hql.append(" from DeviceInfo di,DeviceMaintain dm ");
		hql.append(" where di.deviceId = dm.deviceId ");
		hqlCount.append("select count(*) from DeviceInfo di,DeviceMaintain dm  ");
		hqlCount.append(" where di.deviceId = dm.deviceId ");
		if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and ( di.deviceNo like '%").append(keyword).append("%'")
                            .append(" or di.deviceName like '%").append(keyword).append("%')");
                    hqlCount.append(" and (di.deviceNo like '%").append(keyword).append("%'")
                            .append(" or di.deviceName like '%").append(keyword).append("%')");
                }
            }
            if (params.containsKey("inspectionStatus")) {
                String inspectionStatus = (String) params.get("inspectionStatus");
                if (StringUtils.isNotBlank(inspectionStatus)) {
                    hql.append(" and  dm.maintainIsFinish = '").append(inspectionStatus).append("'");
                    hqlCount.append(" and dm.maintainIsFinish = '").append(inspectionStatus).append("'");
                }
            }
            if(params.containsKey("orgId")){
            	Integer orgId = (Integer) params.get("orgId");
            	if(orgId > 0){
            		hql.append(" and di.dictOrgId = ").append(orgId);
            		hqlCount.append(" and di.dictOrgId = ").append(orgId);
            	}
            }
		}
		hql.append(" order by dm.maintainId desc ");
		
		DTablePageModel pageModel = new DTablePageModel();
		pageModel.setsEcho(pageData.getsEcho());
		PageModel pm = super.queryPageByHqlMySQl(hql.toString(), hqlCount.toString(), null, pageData.getCurrentPage(), pageData.getPageSize());
		if(pm != null) {
			pageModel.setiTotalDisplayRecords(pm.getTotal());
			pageModel.setiTotalRecords(pm.getTotal());
			@SuppressWarnings("unchecked")
			List<Object[]> rowLst = pm.getDatas();
			if(rowLst != null) {
				List<DeviceMaintainDto> aidLst = new ArrayList<DeviceMaintainDto>();
				for(Object[] row : rowLst) {
					DeviceMaintainDto aid = new DeviceMaintainDto();
					aid.setMaintainId((Integer)row[0]);
					aid.setDeviceNo((String)row[1]);
					aid.setDeviceName((String)row[2]);
					aid.setLastTime((String)row[3]);
					aid.setThisTime((String)row[4]);
					aid.setMaintainUser((Integer)row[5]);
					aid.setRemark((String)row[6]);
					aid.setMaintainIsFinish((String)row[7]);
					aidLst.add(aid);
				}
				pageModel.setAaData(aidLst);
			}
		}
		return pageModel;
	}
    
    /**
     * 根据巡检周期查询应该巡检的设备
     */
    public List<DeviceMaintain> queryDeviceMaintainByPeriod(Integer maintainId,String date){
    	 List<DeviceMaintain> list = new ArrayList<DeviceMaintain>();	
		 String hql = " from DeviceMaintain dm  where dm.maintainId="+maintainId +" and dm.thisTime>='"+date+"' and dm.maintainIsFinish='1'";
		 return this.find(hql, null);
    }
    
    /**
     * 根据巡检周期查询应该巡检的设备
     */
    public List<DeviceMaintain> queryDeviceMaintainByDeviceId(Integer deviceId){
		 String hql = " from DeviceMaintain dm  where dm.deviceId="+deviceId;
		 return this.find(hql, null);
    }
    
    /**
     * 根据设备ID查询设备最近一次保养
     */
    public DeviceMaintain deviceMaintainByDeviceId(Integer deviceId){
    	 List<DeviceMaintain> list = new ArrayList<DeviceMaintain>();	
		 String hql = " from DeviceMaintain dm  where dm.deviceId="+deviceId+" order by dm.lastTime desc";
		 list = this.find(hql, null);
		 return list.get(0);
	}
    
    /**
     *根据用户ID查询巡检任务 
     */
    public List<DeviceMaintain> queryDeviceMaintainList(Integer userId){
    	String hql = "from DeviceMaintain where maintainIsFinish ='0' and  deviceId in "
    			+ "(select deviceId from DeviceInfo where dictOrgId =(select orgId from SysUser where userId="+userId+"))";
    	return this.find(hql, null);
    }
}
