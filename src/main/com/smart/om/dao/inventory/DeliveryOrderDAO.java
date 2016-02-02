package com.smart.om.dao.inventory;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.DispatchingPlan;
import com.smart.om.persist.StorageDeliveryOrder;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.PageData;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/23.
 */
public class DeliveryOrderDAO extends BaseDao {
    /**
     * 查询出库分页列表
     **/
    public DTablePageModel queryDeliveryOrderPage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
//        String orgId = (String) params.get("orgId");
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from StorageDeliveryOrder sd where 1 = 1 ");
        hqlCount.append("select count(*) from StorageDeliveryOrder sd where 1 = 1");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (sd.deliveryNo like '%").append(keyword).append("%'")
                            .append(" or sd.createDate like '%").append(keyword).append("%')");
                    hqlCount.append(" and (sd.deliveryNo like '%").append(keyword).append("%'")
                            .append(" or sd.createDate like '%").append(keyword).append("%')");
                }
            }
            if (params.containsKey("userId")) {//查询条件有维修用户
                String userId = (String) params.get("userId");
                if (StringUtils.isNotBlank(userId)) {
                    hql.append(" and sd.userId = ").append(userId);
                    hqlCount.append(" and sd.userId = ").append(userId);
                }
            }
            if (params.containsKey("cleanUser")) {//查询条件有维修用户
                String cleanUser = (String) params.get("cleanUser");
                if (StringUtils.isNotBlank(cleanUser)) {
                    hql.append(" and sd.cleanUser = ").append(cleanUser);
                    hqlCount.append(" and sd.cleanUser = ").append(cleanUser);
                }
            }
            if (params.containsKey("cleanStatus")) {//查询条件有清洗状态
                String cleanStatus = (String) params.get("cleanStatus");
                if (StringUtils.isNotBlank(cleanStatus)) {
                    String orderDate = (String) params.get("orderDate");
                    String orderDateNext = DateUtil.getAfterDayOfSpecified(orderDate);
                    if ("1".equals(cleanStatus)) {
                        hql.append(" and sd.cleanStatus = '").append(cleanStatus).append("' ");
                        hqlCount.append(" and sd.cleanStatus = '").append(cleanStatus).append("' ");
                        hql.append(" and sd.cleanTime < '").append(orderDateNext).append("' ");
                        hql.append(" and sd.cleanTime >= '").append(orderDate).append("' ");
                        hqlCount.append(" and sd.cleanTime < '").append(orderDateNext).append("' ");
                        hqlCount.append(" and sd.cleanTime >= '").append(orderDate).append("' ");
                    } else {
                        hql.append(" and sd.cleanStatus = '").append(cleanStatus).append("' ");
                        hqlCount.append(" and sd.cleanStatus = '").append(cleanStatus).append("' ");
                        hql.append(" and sd.createDate <  '").append(orderDateNext).append("' ");
                        hqlCount.append(" and sd.createDate <  '").append(orderDateNext).append("' ");

                    }
                } else {
                    if (params.containsKey("orderDate")) {//查询条件有清洗下单日期
                        String orderDate = (String) params.get("orderDate");
                        if (StringUtils.isNotBlank(orderDate)) {
                            String orderDateNext = DateUtil.getAfterDayOfSpecified(orderDate);
                            hql.append(" and (sd.cleanStatus='0' and sd.createDate <'" + orderDateNext + "'  or  sd.cleanStatus='1' and sd.cleanTime<'" + orderDateNext + "' and sd.cleanTime>='" + orderDate + "')");
                            hqlCount.append(" and (sd.cleanStatus='0' and sd.createDate <'" + orderDateNext + "'  or  sd.cleanStatus='1' and sd.cleanTime<'" + orderDateNext + "' and sd.cleanTime>='" + orderDate + "')");
                        }
                    }
                }
            }
            if (params.containsKey("deliveryStatus")) {//查询条件有维修状态
                String deliveryStatus = (String) params.get("deliveryStatus");
                if (StringUtils.isNotBlank(deliveryStatus)) {
                    String orderDate = (String) params.get("orderDate");
                    String orderDateNext = DateUtil.getAfterDayOfSpecified(orderDate);
                    if ("1".equals(deliveryStatus)) {
                        hql.append(" and sd.deliveryStatus = '").append(deliveryStatus).append("' ");
                        hqlCount.append(" and sd.deliveryStatus = '").append(deliveryStatus).append("' ");
                        hql.append(" and sd.deliveryDate < '").append(orderDateNext).append("' ");
                        hql.append(" and sd.deliveryDate >= '").append(orderDate).append("' ");
                        hqlCount.append(" and sd.deliveryDate < '").append(orderDateNext).append("' ");
                        hqlCount.append(" and sd.deliveryDate >= '").append(orderDate).append("' ");
                    } else {
                        hql.append(" and sd.deliveryStatus = '").append(deliveryStatus).append("' ");
                        hqlCount.append(" and sd.deliveryStatus = '").append(deliveryStatus).append("' ");
                        hql.append(" and sd.createDate <  '").append(orderDateNext).append("' ");
                        hqlCount.append(" and sd.createDate <  '").append(orderDateNext).append("' ");
                    }
                } else {
                    if (params.containsKey("orderDate")) {//查询条件有清洗下单日期
                        String orderDate = (String) params.get("orderDate");
                        if (StringUtils.isNotBlank(orderDate)) {
                            String orderDateNext = DateUtil.getAfterDayOfSpecified(orderDate);
                            hql.append(" and (sd.deliveryStatus='0' and sd.createDate <'" + orderDateNext + "' or  sd.deliveryStatus='1' and sd.deliveryDate<'" + orderDateNext + "' and sd.deliveryDate>='" + orderDate + "')");
                            hqlCount.append(" and (sd.deliveryStatus='0' and sd.createDate <'" + orderDateNext + "' or  sd.deliveryStatus='1' and sd.deliveryDate<'" + orderDateNext + "' and sd.deliveryDate>='" + orderDate + "')");
                        }
                    }
                }
            }
            if (params.containsKey("startDate")) {//查询条件有维修状态
                String startDate = (String) params.get("startDate");
                if (StringUtils.isNotBlank(startDate)) {
                    hql.append(" and sd.createDate >= '").append(startDate).append("' ");
                    hqlCount.append(" and sd.createDate >= '").append(startDate).append("' ");
                }
            }
            if (params.containsKey("endDate")) {//查询条件有维修状态
                String endDate = (String) params.get("endDate");
                if (StringUtils.isNotBlank(endDate)) {
                    hql.append(" and sd.createDate < '").append(endDate).append("' ");
                    hqlCount.append(" and sd.createDate < '").append(endDate).append("' ");
                }
            }
            if (params.containsKey("dictRid")) {
                String dictRid = params.get("dictRid").toString();
                if (StringUtils.isNotBlank(dictRid)) {
                    hql.append(" and sd.warehouseId in ( select warehouseId  from StorageWarehouse where dictRegionId ='").append(dictRid).append("') ");
                    hqlCount.append(" and sd.warehouseId in ( select warehouseId  from StorageWarehouse where dictRegionId ='").append(dictRid).append("') ");
                }
            }
        }
        if (params.containsKey("orgId")) {
            String orgId = params.get("orgId").toString();
            if (StringUtils.isNotBlank(orgId)) {
                StringBuffer findHql = new StringBuffer();
                findHql.append("select warehouseId from StorageWarehouse where dictOrgId=" + orgId);
                List list = this.find(findHql.toString(), null);
                if (list != null) {
                    String listStr = list.toString().replaceAll("[\\[\\]]", "");
                    hql.append(" and sd.warehouseId in (" + listStr + ")");
                    hqlCount.append(" and sd.warehouseId in (" + listStr + ")");
                } else {
                    hql.append(" and sd.warehouseId = null");
                    hqlCount.append(" and sd.warehouseId = null");
                }
            }
        }

        if ("app".equals(params.get("app"))) {
            if (params.containsKey("deliveryStatus")) {
                hql.append(" order by  sd.deliveryStatus  , case when sd.requestDate>sd.cleanTime then 1 else 0 end,sd.createDate ");
            } else {
                hql.append(" order by sd.cleanStatus  , case when sd.requestDate>sd.cleanTime then 1 else 0 end,sd.createDate");
            }
        } else {
            if (params.containsKey("agent")) {
                hql.append(" and (sd.isClean=1)");
                hqlCount.append(" and (sd.isClean=1)");
            }
            hql.append("order by sd.createDate desc");
        }

        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    /**
     * 查询配送是否存在出库单
     **/
    public List<DispatchingPlan> queryDispatchingPlanList(Integer outOrderId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from DispatchingPlan as model where 1=1 ");
        hql.append(" and model.deliveryOrderId = " + outOrderId);
        List<DispatchingPlan> dispatchingPlanList = (List<DispatchingPlan>) this.find(hql.toString(), null);
        return dispatchingPlanList;
    }

    /**
     * 查询出库及清洗个人任务
     */
    public List<StorageDeliveryOrder> queryStorageDeliveryOrderList(Integer userId, Integer num) {
        StringBuffer hql = new StringBuffer();
        if (num == 1) {
            hql.append("from StorageDeliveryOrder where deliveryStatus='0' and  warehouseId in "
                    + "(select warehouseId from StorageWarehouse where dictOrgId =(select orgId from  SysUser where userId =" + userId + ")))");
        } else if (num == 2) {
            hql.append("from StorageDeliveryOrder where cleanStatus='0' and  warehouseId in "
                    + "(select warehouseId from StorageWarehouse where dictOrgId =(select orgId from  SysUser where userId =" + userId + ")))");
        }
        return this.find(hql.toString(), null);
    }

    /**
     * 获取登录用户角色名
     */
    public List getUserRoleName(Integer id) {
        StringBuffer hql = new StringBuffer();
        hql.append("select sr.roleName from SysUser su,SysRole sr,SysUserRole sur where su.userId=sur.userId "
                + "and sr.roleId=sur.roleId and su.userId=" + id);
        List list = this.find(hql.toString(), null);
        if (list != null) {
            return list;
        }
        return null;
    }
}
