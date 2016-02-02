package com.smart.om.dao.inventory;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.StorageDeliveryDtl;
import com.smart.om.persist.StorageDeliveryOrder;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

/**
 * Created by Administrator on 2015/9/28.
 */
public class DeliveryDetailDAO extends BaseDao {
    /**
     * 入库单明细
     **/
    public DTablePageModel queryDeliveryDetailPage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from StorageDeliveryDtl sd where 1 = 1 ");
        hqlCount.append("select count(*) from StorageDeliveryDtl sd where 1 = 1");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (sd.deliveryDtlId like '%").append(keyword).append("%'")
                            .append(" or sd.deliveryOrderId like '%").append(keyword).append("%') ")
                            .append(" or sd.goodsId like '%").append(keyword).append("%') ")
                            .append(" or sd.DeviceInfoId like '%").append(keyword).append("%') ")
                            .append(" or sd.deliveryCount like '%").append(keyword).append("%') ")
                            .append(" or sd.remark like '%").append(keyword).append("%') ");
                    hqlCount.append(" and (sd.deliveryDtlId like '%").append(keyword).append("%'")
                            .append(" or sd.deliveryOrderId like '%").append(keyword).append("%') ")
                            .append(" or sd.goodsId like '%").append(keyword).append("%') ")
                            .append(" or sd.DeviceInfoId like '%").append(keyword).append("%') ")
                            .append(" or sd.deliveryCount like '%").append(keyword).append("%') ")
                            .append(" or sd.remark like '%").append(keyword).append("%') ");
                }
            }
            if (params.containsKey("deliveryOrderId")) {
                String deliveryOrderId = (String) params.get("deliveryOrderId");
                if (StringUtils.isNotBlank(deliveryOrderId)) {
                    hql.append(" and sd.deliveryOrderId =").append(deliveryOrderId);
                    hqlCount.append(" and sd.deliveryOrderId =").append(deliveryOrderId);
                }
            }
        }
        hql.append("order by sd.deliveryDtlId");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }
    
    /**
     * 根据出库单ID，查询出库单明细
     * @param deliveryOrderId
     * @return
     */
    public List<StorageDeliveryDtl> queryDeliveryDtlByDeliveryId(Integer deliveryOrderId){
    	StringBuffer hql = new StringBuffer();
		hql.append(" from StorageDeliveryDtl as model where 1=1 ");
		hql.append(" and model.deliveryOrderId = " + deliveryOrderId);
		List<StorageDeliveryDtl> warehousingDtlList = (List<StorageDeliveryDtl>)this.find(hql.toString(), null);
    	return warehousingDtlList;
    }

    /**
     * 根据出库单ID，删除对应出库单明细
     *
     * @param deliveryOrderId
     * @return
     */
    public int deleteWarehousingDtlByEntryId(Integer deliveryOrderId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" delete StorageDeliveryDtl as model where 1=1 ");
        hql.append(" and model.deliveryOrderId = " + deliveryOrderId);
        int deleteSize = this.updateHql(hql.toString(), null);
        return deleteSize;
    }
    
    /**
     * 根据调拨单ID查询出库单是否出库
     */
    public StorageDeliveryOrder querySDOrderByAllotId(Integer allotId){
    	StringBuffer hql = new StringBuffer();
		hql.append(" from StorageDeliveryOrder where allotId ="+allotId);
		List<StorageDeliveryOrder> storageDeliveryOrderList = (List<StorageDeliveryOrder>)this.find(hql.toString(), null);
		if(storageDeliveryOrderList.size()>0){
			StorageDeliveryOrder storageDeliveryOrder =storageDeliveryOrderList.get(0);
			return storageDeliveryOrder;
		}
    	return null;
    }
}
