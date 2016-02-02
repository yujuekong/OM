package com.smart.om.dao.dispatching;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.DispatchingPlan;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

/**
 * 配送计划
 * @author langyuk
 *
 */
public class DispatchingPlanDAO extends BaseDao{
	/**
     * 分页查询配送计划
     **/
    public DTablePageModel queryDispatchingPlanPage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        
        if (params != null) {
	        if (params.containsKey("deviceNo")) {
	    		String deviceId = params.get("deviceId").toString();//设备ID
	    		String deviceNo = params.get("deviceNo").toString();//设备编号
	    		if(StringUtils.isNotBlank(deviceNo)){
	    			StringBuffer sql = new StringBuffer();
	    	        StringBuffer sqlCount = new StringBuffer();
	    			sql.append("SELECT DP.PLAN_ID,DP.DELIVERY_ORDER_ID,DP.DRIVER_USER,DP.DISPATCHING_CAR,"
	    					+ "DP.CAR_LINE_ID,DP.DISPATCHING_USER,DP.PLAN_DATE,DP.START_TIME,DP.END_TIME,"
	    					+ "DP.PLAN_STATUS,DP.PLAN_NO,DP.FINISH_TIME FROM STORAGE_DELIVERY_DTL SDD "
	    					+ "LEFT JOIN DISPATCHING_PLAN DP  ON SDD.DELIVERY_ORDER_ID = DP.DELIVERY_ORDER_ID"
	    			 		+ " WHERE SDD.DEVICE_ID="+deviceId);	
	    			 if(params.containsKey("dispatchingStatus")){
	    				 String dispatchingStatus = (String) params.get("dispatchingStatus");
    					if(StringUtils.isNotBlank(dispatchingStatus)) {
    						sql.append(" and  dp.plan_status ='").append(dispatchingStatus).append("' ");
    					}
	    			 }
	    			sql.append(" order by  case when dp.end_time>dp.plan_date then 1 else 0 end,dp.plan_status  asc ");
	    			Session session = null;
	    			List<Object[]> list = new ArrayList();
    				try{
    					session = this.getSession();
        				Query query = session.createSQLQuery(sql.toString());
        				list = query.list();
    				}catch(Exception e){
    					session.close();
    					e.printStackTrace();
    				}
    				finally{
    					session.close();
    				}
    				DTablePageModel pageModel = new DTablePageModel();
    				if(list.size()>0){
    					List<DispatchingPlan> dispatchingPlanLst = new ArrayList<DispatchingPlan>();
    					for(Object[] row : list) {
    						DispatchingPlan dispatchingPlan = new DispatchingPlan();
    						dispatchingPlan.setPlanId(Integer.parseInt(row[0].toString())); 
    						dispatchingPlan.setDeliveryOrderId(Integer.parseInt(row[1].toString()));
    						dispatchingPlan.setDriverUser(Integer.parseInt(row[2].toString()));
    						dispatchingPlan.setDispatchingCar(Integer.parseInt(row[3].toString()));
    						dispatchingPlan.setCarLineId(Integer.parseInt(row[4].toString()));
    						if(row[5]!=null && row[5]!=""){
    							dispatchingPlan.setDispatchingUser(row[5].toString());
    						}
    						if(row[6]!=null && row[6]!=""){
    							dispatchingPlan.setPlanDate(row[6].toString());
    						}
    						if(row[7]!=null && row[7]!=""){
    							dispatchingPlan.setStartTime(row[7].toString());
    						}
    						if(row[8]!=null && row[8]!=""){
    							dispatchingPlan.setEndTime(row[8].toString());
    						}
    						if(row[9]!=null && row[9]!=""){
    							dispatchingPlan.setPlanStatus(row[9].toString());
    						}
    						if(row[10]!=null && row[10]!=""){
    							dispatchingPlan.setPlanNo(row[10].toString());
    						}
    						if(row[11]!=null && row[11]!=""){
    							dispatchingPlan.setFinishTime(row[11].toString());
    						}
    						dispatchingPlanLst.add(dispatchingPlan);
						}
    					pageModel.setAaData(dispatchingPlanLst);
    				}
    					
	    			 return pageModel;
	    		}
	    	}
        }
        hql.append(" from DispatchingOrder do where 1=1 ");
        hqlCount.append("select count(*) from DispatchingOrder do where 1=1 ");
        if (params != null) {
            if(params.containsKey("orderStatus")) {
				String orderStatus = (String) params.get("orderStatus");
				if(StringUtils.isNotBlank(orderStatus)) {
					hql.append(" and do.orderStatus = '").append(orderStatus).append("' ");
					hqlCount.append(" and do.orderStatusf = '").append(orderStatus).append("' ");
				}
			}
        }
        
        hql.append(" order by  case when do.orderEndTime>do.orderFinishTime then 1 else 0 end,do.orderStatus  asc   ");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }
   
}
