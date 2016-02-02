package com.smart.om.dao.dispatching;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.DeviceOrder;
import com.smart.om.persist.DispatchingSubOrder;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 配送子订单
 * @author langyuk
 *
 */
public class DispatchingSubOrderDAO extends BaseDao{

	 public DTablePageModel queryDSubOrder(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from DispatchingSubOrder dso where 1 = 1 and dso.orderAllocation = '" + Const.IS_DEL_FALSE + "' ");
        hqlCount.append("select count(*) from DispatchingSubOrder dso where 1 = 1 and dso.orderAllocation = '" + Const.IS_DEL_FALSE + "' ");
        if (params != null) {
	        if (params.containsKey("orgId")) {
	        	Integer orgId = (Integer) params.get("orgId");
	            if (orgId != null) {
	            	hql.append(" and dso.dictOrgId =").append(Integer.valueOf(orgId));
	            	hqlCount.append(" and dso.dictOrgId =").append(Integer.valueOf(orgId));
	            }
	        }
        }
        hql.append(" order by dso.subOrderId");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

	 /** 根据多个子订单ID查询子订单 **/
	 public List queryDSubOrderByIdS(String subOrderIds){
		 String hql ="";
			if(!"".equals(subOrderIds) && subOrderIds!=null){
				hql ="select goodsId,SUM(goodsNumber) from DispatchingSubOrder where goodsId in ( select goodsId from GoodsInfo where goodsId in ("
						+ "select goodsId from DispatchingSubOrder where subOrderId in ("+subOrderIds+")"
						+ ") and isFrozen ='1') and subOrderId in ("+subOrderIds+") group by goodsId";
		    	return this.find(hql, null);
			}else{
				return null;
			}
	 }


	 /** 根据订单ID查询子订单 商品数量**/
	 public List queryDSubOrderByOrderId(String orderId,String districtId){
		 String hql ="";
			if(!"".equals(orderId) && orderId!=null){
				hql ="select goodsId,SUM(goodsNumber) from DispatchingSubOrder where goodsId in "
						+ "( select goodsId from GoodsInfo where goodsId in (select goodsId from DispatchingSubOrder where orderId="+orderId+") and isFrozen ='0') "
								+ "and orderId = "+orderId+" and districtId = "+districtId+"  group by goodsId";
		    	return this.find(hql, null);
			}else{
				return null;
			}
	 }
	 /** 根据多个子订单ID查询设备ID **/
	public List queryDeviceIdDSubOrderByIdS(String subOrderIds){
		 String hql ="";
			if(!"".equals(subOrderIds) && subOrderIds!=null){
				hql ="select deviceId  from DispatchingSubOrder where subOrderId in ("+subOrderIds+") group by deviceId";
		    	return this.find(hql, null);
			}else{
				return null;
			}
	}


	 /** 根据多个子订单ID查询子订单 **/
	 public List<DispatchingSubOrder> queryDSubOrderByOrderId(Integer orderId){
		 List<DispatchingSubOrder> list = new ArrayList<DispatchingSubOrder>();
		 String hql = " from DispatchingSubOrder dso  where 1=1 and dso.orderId='"+orderId+"'";
		 return this.find(hql, null);
	 }
	 /** 根据订单ID删除子订单 **/
	 public int delDSubOrderByDOrderIdId(Integer DOrderId){
			String hql = "update DispatchingSubOrder set orderId= null,orderAllocation = '"+Const.IS_DEL_FALSE+"' where orderId =" + DOrderId;
			return this.updateHql(hql, new Object[]{});
	 }

	 /** 根据商品ID和设备ID查询子订单信息 **/

	 public  List<DispatchingSubOrder> querydSubOrder(Integer goodsId,Integer deviceId) {
			String hql = " from DispatchingSubOrder  where deviceId="+deviceId+" and goodsId="+goodsId+" and subOrderStatus='0'";
			return this.find(hql, null);
	 }

	 /**根据设备ID和订单ID查询设备订单**/
	public List<DeviceOrder> queryDeviceOrderByOrIdDeId(Integer orderId,Integer deviceId){
		String hql = " from DeviceOrder  where deviceId="+deviceId+" and orderId="+orderId;
		return this.find(hql, null);
	}

	/** 根据订单ID  设备ID  商品ID查询 子订单*/
	public DispatchingSubOrder queryBladeDcO(Integer orderId,Integer deviceId,Integer goodsId){
		String hql = " from DispatchingSubOrder  where deviceId="+deviceId+" and orderId="+orderId+" and goodsId="+goodsId;
		List list =this.find(hql, null);
		if(list!=null){
			DispatchingSubOrder dispatchingSubOrder = (DispatchingSubOrder)list.get(0);
			return dispatchingSubOrder;
		}else{
			return null;
		}
	}
}
