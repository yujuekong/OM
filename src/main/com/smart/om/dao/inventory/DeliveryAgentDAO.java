package com.smart.om.dao.inventory;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.StorageDeliveryDtl;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import com.smart.om.util.PageModel;
import com.smart.om.util.QueryData;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 出库处理DAO
 *
 * @author langyuk
 */
public class DeliveryAgentDAO extends BaseDao {
    /**
     * 查询出库处理单
     **/
    public DTablePageModel queryDeliveryAgentPage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }

        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append("select new Map(ds.goodsId as goodsId,gi.goodsName as goodsName,gi.isFrozen as isFrozen,sd.deliveryNo as deliveryNo,sd.remark as remark,sum(ds.goodsNumber) as requestCount,sum(ds.realiGoodsNumber) as realiGoodsNumber)"
                + "from DispatchingOrder do, DispatchingSubOrder ds, GoodsInfo gi, StorageDeliveryOrder sd "
                + "where gi.isFrozen='0' AND do.orderId = ds.orderId AND ds.goodsId = gi.goodsId AND do.deliveryOrderId = sd.deliveryOrderId");
        hqlCount.append("select count(distinct goodsId) from DispatchingSubOrder ds where 1 = 1");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
//                    hql.append(" and (sd.deliveryDtlId like '%").append(keyword).append("%'")
//                            .append(" or sd.deliveryOrderId like '%").append(keyword).append("%') ")
//                            .append(" or sd.goodsId like '%").append(keyword).append("%') ")
//                            .append(" or sd.deviceInfoId like '%").append(keyword).append("%') ")
//                            .append(" or sd.requestCount like '%").append(keyword).append("%') ")
//                            .append(" or sd.remark like '%").append(keyword).append("%') ")
//                            .append(" or sd.agentType like '%").append(keyword).append("%') ");
//                    hqlCount.append(" and (sd.deliveryDtlId like '%").append(keyword).append("%'")
//                            .append(" or sd.deliveryOrderId like '%").append(keyword).append("%') ")
//                            .append(" or sd.goodsId like '%").append(keyword).append("%') ")
//                            .append(" or sd.deviceInfoId like '%").append(keyword).append("%') ")
//                            .append(" or sd.requestCount like '%").append(keyword).append("%') ")
//                            .append(" or sd.remark like '%").append(keyword).append("%') ")
//                            .append(" or sd.agentType like '%").append(keyword).append("%') ");
                }
            }
            if (params.containsKey("deliveryOrderId")) {
                String deliveryOrderId = (String) params.get("deliveryOrderId");
                if (StringUtils.isNotBlank(deliveryOrderId)) {
                    hql.append(" AND ds.orderId IN (SELECT orderId FROM DispatchingOrder WHERE deliveryOrderId =" + deliveryOrderId + ")");
                    hqlCount.append(" and ds.orderId IN (select orderId from DispatchingOrder do where do.deliveryOrderId=" + deliveryOrderId + ")");
                }

            }
            hql.append(" GROUP BY ds.goodsId, gi.goodsName, sd.deliveryNo, sd.remark,gi.isFrozen");
        }
        DTablePageModel pageModel = new DTablePageModel();
        pageModel.setsEcho(pageData.getsEcho());
        PageModel pm = super.queryPageByHqlMySQl(hql.toString(), hqlCount.toString(), null, pageData.getCurrentPage(), pageData.getPageSize());
        if (params.containsKey("deliveryOrderId")) {
            String deliveryOrderId = (String) params.get("deliveryOrderId");
            if (StringUtils.isNotBlank(deliveryOrderId)) {
                List list = super.find("select so.deliveryNo,gi.goodsName,sd.requestCount,sd.deliveryCount,so.remark from StorageDeliveryDtl sd,GoodsInfo gi,StorageDeliveryOrder so where sd.goodsId=gi.goodsId and sd.deliveryOrderId=so.deliveryOrderId and so.deliveryOrderId="+deliveryOrderId, null);
                List datas = pm.getDatas();
                if (datas!=null) {
                    for (int i = 0; i < list.size(); i++) {
                        Map map = new HashMap();
                        Object[] objects = (Object[]) list.get(i);
                        map.put("deliveryNo", objects[0]);
                        map.put("goodsName", objects[1]);
                        map.put("requestCount", objects[2]);
                        map.put("realiGoodsNumber", objects[3]);
                        map.put("remark", objects[4]);
                        datas.add(map);
                    }
                }
                pageModel.setAaData(datas);
                pageModel.setiTotalDisplayRecords(pm.getTotal());
                pageModel.setiTotalRecords(pm.getTotal());
            }
        }
        return pageModel;
    }

    /**
     * 根据出库单ID，查询出库单处理商品
     *
     * @param deliveryOrderId
     * @return
     */
    public List<StorageDeliveryDtl> queryDeliveryAgentByDeliveryId(Integer deliveryOrderId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from StorageDeliveryDtl as model where 1=1 ");
        hql.append(" and model.deliveryOrderId = " + deliveryOrderId);
        QueryData queryData = new QueryData();
        List<StorageDeliveryDtl> DeliveryAgentList = (List<StorageDeliveryDtl>) this.find(hql.toString(), null);
        return DeliveryAgentList;
    }

    /**
     * 查询出库处理单
     **/
    public DTablePageModel queryDispatchingListById(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from DispatchingOrder do where 1 = 1 ");
        hqlCount.append("select count(*) from DispatchingOrder do where 1 = 1");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (do.orderId like '%").append(keyword).append("%'")
                            .append(" or do.orderNo like '%").append(keyword).append("%') ")
                            .append(" or do.orderTime like '%").append(keyword).append("%') ")
                            .append(" or do.createUser like '%").append(keyword).append("%') ")
                            .append(" or do.warehouseId like '%").append(keyword).append("%') ")
                            .append(" or do.orderStatus like '%").append(keyword).append("%') ")
                            .append(" or do.orderFinishTime like '%").append(keyword).append("%') ")
                            .append(" or do.orderEndTime like '%").append(keyword).append("%') ")
                            .append(" or do.teamId like '%").append(keyword).append("%') ")
                            .append(" or do.deliveryOrderId like '%").append(keyword).append("%') ")
                            .append(" or do.orderAllocation like '%").append(keyword).append("%') ");
                    hqlCount.append(" and (do.orderId like '%").append(keyword).append("%'")
                            .append(" or do.orderNo like '%").append(keyword).append("%') ")
                            .append(" or do.orderTime like '%").append(keyword).append("%') ")
                            .append(" or do.createUser like '%").append(keyword).append("%') ")
                            .append(" or do.warehouseId like '%").append(keyword).append("%') ")
                            .append(" or do.orderStatus like '%").append(keyword).append("%') ")
                            .append(" or do.orderFinishTime like '%").append(keyword).append("%') ")
                            .append(" or do.orderEndTime like '%").append(keyword).append("%') ")
                            .append(" or do.teamId like '%").append(keyword).append("%') ")
                            .append(" or do.deliveryOrderId like '%").append(keyword).append("%') ")
                            .append(" or do.orderAllocation like '%").append(keyword).append("%') ");
                }
            }
            if (params.containsKey("deliveryOrderId")) {
                String deliveryOrderId = (String) params.get("deliveryOrderId");
                if (StringUtils.isNotBlank(deliveryOrderId)) {
                    hql.append(" and do.deliveryOrderId = ").append(deliveryOrderId);
                    hqlCount.append(" and do.deliveryOrderId = ").append(deliveryOrderId);
                }

            }
        }
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }


    /**
     * 根据订单ID查询对应子订单
     **/
    public DTablePageModel querySubOrderListById(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from MotionDistrict md where 1 = 1 ");
        hqlCount.append("select count(*) from  MotionDistrict where 1 = 1");
        if (params != null) {
            hql.append("and districtId in (select districtId from DispatchingSubOrder ds");
            hqlCount.append(" and districtId in (select districtId from DispatchingSubOrder ds");
            if (params.containsKey("orderId")) {
                String orderId = (String) params.get("orderId");
                if (StringUtils.isNotBlank(orderId)) {
                    hql.append(" where ds.orderId = ").append(orderId);
                    hqlCount.append(" where ds.orderId = ").append(orderId);
                }

            }

            hql.append(" group by districtId)");
            hqlCount.append(" group by districtId)");
        }
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }


    /**
     * 查询每个商圈的商品数量统计
     **/
    public DTablePageModel queryOrderAgentDet(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append("select gi.goodsName,sum(ds.goodsNumber),gi.goodsTypeName from DispatchingSubOrder ds,GoodsInfo gi where 1=1");
        hqlCount.append("select count(distinct goodsId) from DispatchingSubOrder ds where 1 = 1 ");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {

                }
            }
        }
        if (params.containsKey("orderId")) {
            String orderId = (String) params.get("orderId");
            if (StringUtils.isNotBlank(orderId)) {
                hql.append(" and ds.orderId=" + orderId);
                hqlCount.append(" and ds.orderId=" + orderId);
            }
        }
        hql.append(" and ds.goodsId = gi.goodsId group by ds.goodsId, gi.goodsName, gi.goodsTypeName");

        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }


    /**
     * 查询设备分组
     **/
    public DTablePageModel queryDeviceGroup(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from DeviceInfo md where 1 = 1 ");
        hqlCount.append("select count(*) from  DeviceInfo di where 1 = 1");
        if (params != null) {
//			if (params.containsKey("keyword")) {
//				String keyword = (String) params.get("keyword");
//				if (StringUtils.isNotBlank(keyword)) {
//					hql.append(" and (t.orderId like '%").append(keyword).append("%'")
//							.append(" or t.orderNo like '%").append(keyword).append("%') ")
//							.append(" or t.orderTime like '%").append(keyword).append("%') ")
//							.append(" or t.createUser like '%").append(keyword).append("%') ")
//							.append(" or t.warehouseId like '%").append(keyword).append("%') ")
//							.append(" or t.orderStatus like '%").append(keyword).append("%') ")
//							.append(" or t.orderFinishTime like '%").append(keyword).append("%') ")
//							.append(" or t.orderEndTime like '%").append(keyword).append("%') ")
//							.append(" or t.teamId like '%").append(keyword).append("%') ")
//							.append(" or t.deliveryOrderId like '%").append(keyword).append("%') ")
//							.append(" or t.orderAllocation like '%").append(keyword).append("%') ");
//					hqlCount.append(" and (t.orderId like '%").append(keyword).append("%'")
//							.append(" or t.orderNo like '%").append(keyword).append("%') ")
//							.append(" or t.orderTime like '%").append(keyword).append("%') ")
//							.append(" or t.createUser like '%").append(keyword).append("%') ")
//							.append(" or t.warehouseId like '%").append(keyword).append("%') ")
//							.append(" or t.orderStatus like '%").append(keyword).append("%') ")
//							.append(" or t.orderFinishTime like '%").append(keyword).append("%') ")
//							.append(" or t.orderEndTime like '%").append(keyword).append("%') ")
//							.append(" or t.teamId like '%").append(keyword).append("%') ")
//							.append(" or t.deliveryOrderId like '%").append(keyword).append("%') ")
//							.append(" or t.orderAllocation like '%").append(keyword).append("%') ");
//				}
//			}
            hql.append("and deviceId in (select t.deviceId from DispatchingSubOrder t");
            hqlCount.append(" and di.deviceId in (select t.deviceId from DispatchingSubOrder t");
            if (params.containsKey("districtId")) {
                String districtId = (String) params.get("districtId");
                if (StringUtils.isNotBlank(districtId)) {
                    hql.append(" where t.districtId = ").append(districtId);
                    hqlCount.append(" where t.districtId = ").append(districtId);
                }

            }
            if (params.containsKey("orderId")) {
                String orderId = (String) params.get("orderId");
                if (StringUtils.isNotBlank(orderId)) {
                    hql.append(" and t.orderId = ").append(orderId);
                    hqlCount.append(" and t.orderId = ").append(orderId);
                }

            }

            hql.append(" group by t.deviceId)");
            hqlCount.append(" group by t.deviceId)");
        }
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }


    /**
     * 查询每个设备的商品数量统计
     **/
    public DTablePageModel queryDeviceSumGoodsById(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append("select new Map(gi.goodsName as goodsName,sum(ds.goodsNumber) as goodsNumber,gi.agentTypeName as agentTypeName) from DispatchingSubOrder ds,GoodsInfo gi where 1=1");
        hqlCount.append("select count(distinct goodsId) from DispatchingSubOrder ds where 1 = 1");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {

                }
            }
        }
        if (params.containsKey("deviceId")) {
            String deviceId = (String) params.get("deviceId");
            if (StringUtils.isNotBlank(deviceId)) {
                hql.append(" and ds.deviceId=" + deviceId);
                hqlCount.append(" and ds.deviceId=" + deviceId);
            }
        }
        if (params.containsKey("orderId")) {
            String orderId = (String) params.get("orderId");
            if (StringUtils.isNotBlank(orderId)) {
                hql.append(" and ds.orderId=" + orderId);
                hqlCount.append(" and ds.orderId=" + orderId);
            }
        }
        hql.append(" and ds.goodsId = gi.goodsId group by ds.goodsId, gi.goodsName, gi.agentTypeName");

        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    /**
     * 统计每个商圈的商品数量
     **/
    public DTablePageModel querySumGoodsMotion(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append("select new Map(gi.goodsName as goodsName,sum(ds.goodsNumber) as goodsNumber,gi.agentTypeName as agentTypeName) from DispatchingSubOrder ds,GoodsInfo gi where 1=1");
        hqlCount.append("select count(distinct ds.goodsId) from DispatchingSubOrder ds where 1=1");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {

                }
            }
        }
        if (params.containsKey("districtId")) {
            String districtId = (String) params.get("districtId");
            if (StringUtils.isNotBlank(districtId)) {
                hql.append(" and ds.districtId=" + districtId);
                hqlCount.append(" and ds.districtId=" + districtId);
            }
        }
        if (params.containsKey("orderId")) {
            String orderId = (String) params.get("orderId");
            if (StringUtils.isNotBlank(orderId)) {
                hql.append(" and ds.orderId=" + orderId);
                hqlCount.append(" and ds.orderId=" + orderId);
            }
        }
        hql.append(" and ds.goodsId = gi.goodsId group by ds.goodsId, gi.goodsName, gi.agentTypeName");

        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }
}