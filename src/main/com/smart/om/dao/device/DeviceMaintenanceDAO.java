package com.smart.om.dao.device;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;








import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.DeviceMaintenance;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.PageData;
import com.smart.om.util.PageModel;

/**
 * 设备维修DAO
 * @author lc
 *
 */
public class DeviceMaintenanceDAO extends BaseDao{
	
	@Autowired
	private DeviceInfoDAO deviceInfoDAO;
	
	
	/** 查询维护设备信息 **/
	public DTablePageModel queryDeviceMain(Map<String, Object> params, PageData pageData){
		if(pageData == null) {
			return null;
		}
 		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append(" from DeviceMaintenance dm where 1 = 1 and dm.deviceInfo.isDel = '" + Const.IS_DEL_FALSE + "' ");		
		hqlCount.append("select count(*) from DeviceMaintenance dm where 1 = 1 and dm.deviceInfo.isDel = '" + Const.IS_DEL_FALSE + "' ");
		if(params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (dm.maintenanceNo like '%").append(keyword).append("%'")
                    .append(" or dm.deviceInfo.deviceNo like '%").append(keyword).append("%'")
                    .append(" or dm.deviceInfo.deviceName like '%").append(keyword).append("%')");
                    hqlCount.append(" and (dm.maintenanceNo like '%").append(keyword).append("%'")
                    .append(" or dm.deviceInfo.deviceNo like '%").append(keyword).append("%'")
                    .append(" or dm.deviceInfo.deviceName like '%").append(keyword).append("%')");
                }
            }
			if(params.containsKey("deviceId")) {
				String deviceId = (String) params.get("deviceId");
				if(StringUtils.isNotBlank(deviceId)) {
					hql.append(" and dm.deviceId =").append(deviceId);
					hqlCount.append(" and dm.deviceId =").append(deviceId);
				}
			}
			if(params.containsKey("userId")) {//查询条件有维修用户
				String userId = (String) params.get("userId");
				if(StringUtils.isNotBlank(userId)) {
					hql.append(" and dm.maintenanceUser = ").append(userId);
					hqlCount.append(" and dm.maintenanceUser = ").append(userId);
				}
			}
            if (params.containsKey("startDate")) {//查询条件有维修状态
                String startDate = (String) params.get("startDate");
                if (StringUtils.isNotBlank(startDate)) {
                    hql.append(" and dm.maintenanceDate >= '").append(startDate).append("' ");
                    hqlCount.append(" and dm.maintenanceDate >= '").append(startDate).append("' ");
                }
            }
            if (params.containsKey("endDate")) {//查询条件有维修状态
                String endDate = (String) params.get("endDate");
                if (StringUtils.isNotBlank(endDate)) {
                    hql.append(" and dm.maintenanceDate < '").append(endDate).append("' ");
                    hqlCount.append(" and dm.maintenanceDate < '").append(endDate).append("' ");
                }
            }
			
			if(params.containsKey("maintenanceStatus")) {//查询条件有维修状态
				String maintenanceStatus = (String) params.get("maintenanceStatus");
				if(StringUtils.isNotBlank(maintenanceStatus)) {
					String orderDate = (String) params.get("orderDate");
					String orderDateNext = DateUtil.getAfterDayOfSpecified(orderDate);
					if("1".equals(maintenanceStatus)){
						hql.append(" and dm.maintenanceStatus = '").append(maintenanceStatus).append("' ");
						hqlCount.append(" and dm.maintenanceStatus = '").append(maintenanceStatus).append("' ");
						hql.append(" and dm.maintenanceDate < '").append(orderDateNext).append("' ");
						//hql.append(" and dm.maintenanceDate >= '").append(orderDate).append("' ");
						hqlCount.append(" and dm.maintenanceDate < '").append(orderDateNext).append("' ");
						//hqlCount.append(" and dm.maintenanceDate >= '").append(orderDate).append("' ");
					}else{
						hql.append(" and dm.maintenanceStatus = '").append(maintenanceStatus).append("' ");
						hqlCount.append(" and dm.maintenanceStatus = '").append(maintenanceStatus).append("' ");
						hql.append(" and dm.finishTime >= '").append(orderDate).append("' ");
						hql.append(" and dm.finishTime < '").append(orderDateNext).append("' ");
						hqlCount.append(" and dm.finishTime >= '").append(orderDate).append("' ");
						hqlCount.append(" and dm.finishTime < '").append(orderDateNext).append("' ");
					}
				}else{
					if(params.containsKey("orderDate")) {//查询条件有维修下单日期
						String orderDate = (String) params.get("orderDate");				
						if(StringUtils.isNotBlank(orderDate)) {
							String orderDateNext = DateUtil.getAfterDayOfSpecified(orderDate);
							hql.append(" and (dm.maintenanceStatus ='1' and dm.maintenanceDate <'"+orderDateNext +"' or dm.maintenanceStatus ='0' and dm.finishTime<'"+orderDateNext +"' and dm.finishTime>='"+orderDate+"')" );
							hqlCount.append(" and (dm.maintenanceStatus ='1' and dm.maintenanceDate <'"+orderDateNext +"'  or dm.maintenanceStatus ='0' and dm.finishTime<'"+orderDateNext +"' and dm.finishTime>='"+orderDate+"')" );
						}
					}
				}
			}
			if (params.containsKey("orgId")) {
            	Integer orgId = (Integer) params.get("orgId");
                if (orgId != null) {
                	hql.append(" and dm.deviceInfo.dictOrgId =").append(Integer.valueOf(orgId));
                	hqlCount.append(" and dm.deviceInfo.dictOrgId =").append(Integer.valueOf(orgId));
                }
            }
		}
		if("app".equals(params.get("app"))){
			hql.append("  order by  dm.maintenanceStatus  desc,case when dm.expEndTime>dm.finishTime then 1 else 0 end,dm.maintenanceDate asc ");
		}else{
			hql.append("  order by  dm.maintenanceStatus  desc,case when dm.expEndTime>dm.finishTime then 1 else 0 end,dm.maintenanceId  desc ");
		}
		//
		return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
	}
	/**
	 * 
	 * @param 根据设备ID查询设备维护信息
	 * @return
	 */
	public List<DeviceMaintenance> queryDMainByDeviceId(Integer deviceId) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from DeviceMaintenance  where deviceId='"+deviceId+"'");
		hql.append(" order by finishTime desc ");
		return this.find(hql.toString(), null);
	}
	
	
	/**
	 * 查询个人维护任务
	 */
	public List<DeviceMaintenance> queryDeviceMaintenanceList(Integer userId){
		StringBuffer hql = new StringBuffer();
		hql.append("from DeviceMaintenance where maintenanceStatus='1' and maintenanceUser="+userId);
		return this.find(hql.toString(), null);
	}
}
