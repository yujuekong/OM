package com.smart.om.dao.device;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.DeviceClean;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.PageData;

/**
 * 设备清洗DAO
 * @author lc
 *
 */
public class DeviceCleanDAO extends BaseDao{
	
	@Autowired
	private DeviceInfoDAO deviceInfoDAO;
	/** 查询清洗设备信息 **/
	public DTablePageModel queryDeviceClean(Map<String, Object> params, PageData pageData){
		if(pageData == null) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append(" from DeviceClean dc where 1 = 1 and dc.deviceInfo.isDel ='").append(Const.IS_DEL_FALSE).append("'");		
		hqlCount.append("select count(*) from DeviceClean dc where 1 = 1 and dc.deviceInfo.isDel ='").append(Const.IS_DEL_FALSE).append("'");	
		if(params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (dc.cleanNo like '%").append(keyword).append("%'")
                    .append(" or dc.deviceInfo.deviceNo like '%").append(keyword).append("%'")
                    .append(" or dc.deviceInfo.deviceName like '%").append(keyword).append("%')");
                    hqlCount.append(" and (dc.cleanNo like '%").append(keyword).append("%'")
                    .append(" or dc.deviceInfo.deviceNo like '%").append(keyword).append("%'")
                    .append(" or dc.deviceInfo.deviceName like '%").append(keyword).append("%')");
                }
            }
			if(params.containsKey("deviceId")) {
				String deviceId = (String) params.get("deviceId");
				if(StringUtils.isNotBlank(deviceId)) {
					hql.append(" and (dc.deviceId like '%").append(deviceId).append("%') ");
					hqlCount.append(" and (dc.deviceId like '%").append(deviceId).append("%') ");
				}
			}
			if(params.containsKey("userId")) {//查询条件有清洗用户
				String userId = (String) params.get("userId");
				if(StringUtils.isNotBlank(userId)) {
					hql.append(" and dc.cleanUserId = ").append(userId);
					hqlCount.append(" and dc.cleanUserId = ").append(userId);
				}
			}
            if (params.containsKey("startDate")) {//查询条件有维修状态
                String startDate = (String) params.get("startDate");
                if (StringUtils.isNotBlank(startDate)) {
                    hql.append(" and dc.cleanDate >= '").append(startDate).append("' ");
                    hqlCount.append(" and dc.cleanDate >= '").append(startDate).append("' ");
                }
            }
            if (params.containsKey("endDate")) {//查询条件有维修状态
                String endDate = (String) params.get("endDate");
                if (StringUtils.isNotBlank(endDate)) {
                    hql.append(" and dc.cleanDate < '").append(endDate).append("' ");
                    hqlCount.append(" and dc.cleanDate < '").append(endDate).append("' ");
                }
            }
						
			if(params.containsKey("cleanStatus")) {//查询条件有清洗状态
				String cleanStatus = (String) params.get("cleanStatus");
				if(StringUtils.isNotBlank(cleanStatus)) {
					String orderDate = (String) params.get("orderDate");		
					//hql.append(" and dc.cleanStatus = '").append(cleanStatus).append("' ");
					//hqlCount.append(" and dc.cleanStatus = '").append(cleanStatus).append("' ");
					String orderDateNext = DateUtil.getAfterDayOfSpecified(orderDate);
					if("1".equals(cleanStatus)){
						hql.append(" and dc.cleanStatus = '").append(cleanStatus).append("' ");
						hqlCount.append(" and dc.cleanStatus = '").append(cleanStatus).append("' ");
						hql.append(" and dc.cleanDate < '").append(orderDateNext).append("' ");
						//hql.append(" and dc.cleanDate >= '").append(orderDate).append("' ");
						hqlCount.append(" and dc.cleanDate < '").append(orderDateNext).append("' ");
						//hqlCount.append(" and dc.cleanDate >= '").append(orderDate).append("' ");
					}else{
						hql.append(" and dc.cleanStatus = '").append(cleanStatus).append("' ");
						hqlCount.append(" and dc.cleanStatus = '").append(cleanStatus).append("' ");
						hql.append(" and dc.finishTime >= '").append(orderDate).append("' ");
						hql.append(" and dc.finishTime < '").append(orderDateNext).append("' ");
						hqlCount.append(" and dc.finishTime >= '").append(orderDate).append("' ");
						hqlCount.append(" and dc.finishTime < '").append(orderDateNext).append("' ");
					}
				}else{
					if(params.containsKey("orderDate")) {//查询条件有清洗下单日期
						String orderDate = (String) params.get("orderDate");	
						if(StringUtils.isNotBlank(orderDate)) {
							String orderDateNext = DateUtil.getAfterDayOfSpecified(orderDate);
							hql.append(" and (dc.cleanStatus='1' and dc.cleanDate <'"+orderDateNext +"' or  dc.cleanStatus='0' and dc.finishTime<'"+orderDateNext +"' and dc.finishTime>='"+orderDate+"')" );
							hqlCount.append(" and (dc.cleanStatus='1' and dc.cleanDate <'"+orderDateNext +"' or  dc.cleanStatus='0' and dc.finishTime<'"+orderDateNext +"' and dc.finishTime>='"+orderDate+"')" );
						}
					}
				}
			}
			if (params.containsKey("orgId")) {
            	Integer orgId = (Integer) params.get("orgId");
                if (orgId != null) {
                	hql.append(" and dc.deviceInfo.dictOrgId =").append(Integer.valueOf(orgId));
                	hqlCount.append(" and dc.deviceInfo.dictOrgId =").append(Integer.valueOf(orgId));
                }
            }
		}
		if("app".equals(params.get("app"))){
			hql.append("  order by  dc.cleanStatus desc,case when dc.cleanEndTiem>dc.finishTime then 1 else 0 end,dc.cleanDate asc   ");
		}else{
			hql.append("  order by  case when dc.cleanEndTiem>dc.finishTime then 1 else 0 end,dc.cleanStatus desc,dc.cleanId  desc   ");
		}
		
		return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
	}
	/*查询所有用户*/
	public DTablePageModel queryUser(Map<String, Object> params, PageData pageData){
		if(pageData == null) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append(" from SysUser su where 1 = 1 ");		
		hqlCount.append("select count(*) from SysUser sg where 1 = 1");
		hql.append(" order by su.userId  ");
		return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
	}
	
	/**
	 * 查询个人清洗任务
	 */
	public List<DeviceClean> queryDeviceCleanList(Integer userId){
		StringBuffer hql = new StringBuffer();
		hql.append("from DeviceClean where cleanStatus='1' and cleanUserId="+userId);
		return this.find(hql.toString(), null);
	}
	
}
