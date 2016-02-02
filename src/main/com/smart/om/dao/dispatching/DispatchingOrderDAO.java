package com.smart.om.dao.dispatching;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.DispatchingOrder;
import com.smart.om.persist.DispatchingSubOrder;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.PageData;

/**
 * 配送订单
 * @author lc
 *
 */
public class DispatchingOrderDAO extends BaseDao{
	
	public DTablePageModel queryDOrder(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        
        hql.append(" from DispatchingOrder do where 1=1 ");
        hqlCount.append("select count(*) from DispatchingOrder do where 1=1 ");
        if (params != null) {
        	
            if(params.containsKey("orderStatus")) {
            	
				String orderStatus = (String) params.get("orderStatus");
				if(StringUtils.isNotBlank(orderStatus)) {
					
					String orderDate = (String) params.get("orderDate");
					String orderDateNext = DateUtil.getAfterDayOfSpecified(orderDate);
					if(!"2".equals(orderStatus)){
						hql.append(" and ( do.orderStatus = '0' and do.orderTime  <'").append(orderDateNext).append("' or do.orderStatus = '1'and do.orderTime  <'").append(orderDateNext).append("') ");
						hqlCount.append(" and  (do.orderStatus = '0' and do.orderTime < '").append(orderDateNext).append("' or do.orderStatus = '1' and do.orderTime < '").append(orderDateNext).append("' )");
					}else{
						hql.append(" and do.orderStatus = '").append(orderStatus).append("' ");
						hqlCount.append(" and do.orderStatus = '").append(orderStatus).append("' ");
						hql.append(" and do.orderFinishTime  >= '").append(orderDate).append("' ");
						hql.append(" and do.orderFinishTime < '").append(orderDateNext).append("' ");
						hqlCount.append(" and do.orderFinishTime >= '").append(orderDate).append("' ");
						hqlCount.append(" and do.orderFinishTime < '").append(orderDateNext).append("' ");
					}
				}
			}
            if("".equals(params.get("orderStatus"))){
            	if(params.containsKey("orderDate")) {
                	String orderDate = (String) params.get("orderDate");
                	String orderDateNext = DateUtil.getAfterDayOfSpecified(orderDate);
    				if(StringUtils.isNotBlank(orderDate)) {
    					hql.append(" and (do.orderStatus='0' and do.orderTime <'"+orderDateNext+"' or  do.orderStatus='2' and  do.orderFinishTime <'"+orderDateNext+"' and  do.orderFinishTime>='"+orderDate+"' or do.orderStatus='1' and do.orderTime <'"+orderDateNext+"' ) ");
    					hqlCount.append(" and (do.orderStatus='0' and do.orderTime <'"+orderDateNext+"'  or  do.orderStatus='2' and  do.orderFinishTime  <'"+orderDateNext+"' and  do.orderFinishTime>='"+orderDate+"'  or do.orderStatus='1' and  do.orderTime <'"+orderDateNext+"' ) ");
    				}
    			}
			}
            
            //获取当前特勤小组ID
            if(params.containsKey("teamId")){
            	String teamId =params.get("teamId").toString();
            	if(StringUtils.isNotBlank(teamId)){
            		hql.append(" and do.teamId =").append(teamId).append(" ");
            		hqlCount.append(" and do.teamId =").append(teamId).append(" ");
            	}
            }
            //是否是分配
            if(params.containsKey("deliveryOrderId")){
            	String deliveryOrderId =params.get("deliveryOrderId").toString();
            	if(StringUtils.isNotBlank(deliveryOrderId)){
            		hql.append(" and do.deliveryOrderId =").append(deliveryOrderId).append(") ");
            		hqlCount.append(" and do.deliveryOrderId=").append(deliveryOrderId).append(") ");
            	}
            }
	        if (params.containsKey("orgId")) {
	        	Integer orgId = (Integer) params.get("orgId");
	            if (orgId != null) {
	            	hql.append(" and do.dictOrgId =").append(Integer.valueOf(orgId));
	            	hqlCount.append(" and do.dictOrgId =").append(Integer.valueOf(orgId));
	            }
	        }
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and do.orderNo like '%").append(keyword).append("%'");
                    hqlCount.append(" and do.orderNo like '%").append(keyword).append("%'");
                }
            }
            if (params.containsKey("startDate")) {//查询条件有维修状态
                String startDate = (String) params.get("startDate");
                if (StringUtils.isNotBlank(startDate)) {
                    hql.append(" and do.orderTime >= '").append(startDate).append("' ");
                    hqlCount.append(" and do.orderTime >= '").append(startDate).append("' ");
                }
            }
            if (params.containsKey("endDate")) {//查询条件有维修状态
                String endDate = (String) params.get("endDate");
                if (StringUtils.isNotBlank(endDate)) {
                    hql.append(" and do.orderTime < '").append(endDate).append("' ");
                    hqlCount.append(" and do.orderTime < '").append(endDate).append("' ");
                }
            }
        }
        if("app".equals(params.get("app"))){
        	hql.append(" order by case when do.orderEndTime>do.orderFinishTime then 1 else 0 end asc, do.orderStatus asc, do.orderTime desc ");
        }else{
        	hql.append(" order by  do.orderStatus  ,do.orderId desc ");
        }
        
        //hql.append(" order by  case when do.orderEndTime>do.orderFinishTime then 1 else 0 end,do.orderStatus  asc   ");
        
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }
 
	/**根据订单ID查询刀片数量**/
	public List bladeCountByOrderId(Integer orderId){
		 String hql ="";
		hql ="SELECT count(*) from DispatchingSubOrder where orderId = "+orderId+" and goodsId in (select goodsId from GoodsInfo where goodsName='刀片' )";
    	return this.find(hql, null);
	}
 
 	/**
     * 根据订单ID，查询配送商圈
     * @param orderId
     * @return
     */
    public List dispatchingSubOrderByOrderId(Integer orderId){
    	StringBuffer hql = new StringBuffer();
		hql.append("select dso.districtId,dso.districtName from DispatchingSubOrder dso where 1=1 ");
		if(orderId!=0 && orderId!=null){
			hql.append(" and dso.orderId = " + orderId );
		}
		hql.append("  group by dso.districtId,dso.districtName  ");
    	return this.find(hql.toString(), null);
    }
    
    /**
     * 根据商圈ID，配送商品
     * @param planId
     * @return
     */
    public List goodsByDistrictId(Map<String, Object> params){
    	StringBuffer hql = new StringBuffer();
    	hql.append("select dso.deviceId  from DispatchingSubOrder dso where 1=1 ");
		if (params != null) {
			hql.append(" and dso.districtId = " + params.get("districtId") +"and dso.orderId="+params.get("orderId") +"  group by dso.deviceId,dso.subOrderStatus order by dso.subOrderStatus");
        }
    	return this.find(hql.toString(), null);
    }
    
    public List<DispatchingSubOrder> subOrderByOrderId(Integer orderId){
    	String hql ="from DispatchingSubOrder dso where dso.subOrderStatus ='0'  and goodsId not in (select goodsId from GoodsInfo where goodsName like '%刀片%') and orderId="+orderId;
    	return (List<DispatchingSubOrder>)this.find(hql.toString(), null);
    }
    
    public List<DispatchingSubOrder> subOrderByOrderIdAll(Integer orderId){
    	String hql ="from DispatchingSubOrder dso where (dso.orderAllocation ='1' or dso.orderAllocation ='0' or dso.orderAllocation  is null)  and orderId="+orderId;
    	return (List<DispatchingSubOrder>)this.find(hql.toString(), null);
    }
    
    public List<DispatchingSubOrder> subOrderByDeliveryOrderIdAll(Integer deliveryOrderId){
    	String hql ="from DispatchingSubOrder dso where (dso.orderAllocation ='1' or dso.orderAllocation ='0' or dso.orderAllocation  is null)  "
    			+ "and orderId in (select orderId from DispatchingOrder where deliveryOrderId ="+deliveryOrderId+")";
    	return (List<DispatchingSubOrder>)this.find(hql.toString(), null);
    }
    
    /**根据设备ID查询子订单**/
    public List<DispatchingSubOrder> subOrderByDeviceId(Integer deviceId,Integer orderId){
    	String hql ="from DispatchingSubOrder dso where dso.orderId ="+orderId+"  and deviceId="+ deviceId;
    	return (List<DispatchingSubOrder>)this.find(hql.toString(), null);
    }
    
    /**配送订单个人任务**/
	public List<DispatchingOrder> queryDispatchingOrderList(Integer userId){
		String hql ="from DispatchingOrder where  orderStatus!='2' and teamId in "
				+ "(select teamId from DispatchingTeam where otherUser="+userId+" or mainUser="+userId+")";
    	return this.find(hql.toString(), null);
	}

}
