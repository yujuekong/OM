package com.smart.om.dao.region;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.dto.device.DeviceCarLineNodeDto;
import com.smart.om.persist.DeviceInfo;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * lc
 */
public class DispatchPlanDAO extends BaseDao{
    /**
     * 分页查询配送计划列表
     **/
    public DTablePageModel queryDispatchPlan(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from DispatchingPlan dp where 1 = 1 ");
        hqlCount.append("select count(*) from DispatchingPlan dp where 1 = 1 ");
        hql.append("order by dp.planId");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }
    
	/**
	 * 查询所有设备信息
	 * @author lC
	 *
	 */
	public List<DeviceCarLineNodeDto> queryDeviceByCarLine(Integer carLineId){
		StringBuffer hql = new StringBuffer();
        
		hql.append( " select di.deviceId,di.deviceName,cld.lineNodeId,cld.nodeNo,  ");
		hql.append( " cld.nodePositionId,cld.nodePositionName,cld.carLineId  ");
     	hql.append( " from DeviceInfo di,CarLineNode cld  ");
     	hql.append( " where 1 = 1 and di.isDel = '" + Const.IS_DEL_FALSE + "'  ");
     	hql.append( " and di.deviceStatus = '" + Const.IS_VALID_TRUE + "'  ");
     	hql.append( " and di.districtId = cld.nodePositionId");
     	hql.append( " and cld.carLineId = "+carLineId);
     	
     	List<Object[]> objArrList = this.find(hql.toString(), null);	
		
		List<DeviceCarLineNodeDto> getList = new ArrayList<DeviceCarLineNodeDto>();
		if (objArrList != null) {
			for(Object[] objArr : objArrList) {
				DeviceCarLineNodeDto dcld = new DeviceCarLineNodeDto();
				dcld.setDeviceId((Integer) objArr[0]);
				dcld.setDeviceName((String) objArr[1]);
				dcld.setLineNodeId((Integer) objArr[2]);
				dcld.setNodeNo((String) objArr[3]);
				dcld.setNodePositionId((Integer) objArr[4]);
				dcld.setNodePositionName((String) objArr[5]);
				dcld.setNodePositionName((String) objArr[5]);
				getList.add(dcld);
			}
		}
		return getList;
	}
}
