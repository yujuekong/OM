package com.smart.om.dao.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import com.smart.om.dao.base.BaseDao;
import com.smart.om.dto.inventory.GoodsInfoDto;
import com.smart.om.persist.StorageAllotDtl;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import com.smart.om.util.PageModel;

/**
 * 调拨明细
 *
 * @author CA
 */
public class AllotDtlDAO extends BaseDao {
    public List<GoodsInfoDto> queryStorageAllotDtById(Integer id) {
        StringBuffer hql = new StringBuffer();
        hql.append("select gi.goodsId,gi.goodsBarCode,gi.goodsName,gi.goodsStatus,gi.goodsTypeName,sad.allotDtlId,sad.allotId,sad.expectNumber,sad.actualNumber from GoodsInfo gi,StorageAllotDtl sad where 1 = 1 ");
        hql.append(" and gi.goodsId = sad.goodsId and sad.allotId = ").append(id);
        List<Object[]> rowList = this.find(hql.toString(), null);
        List<GoodsInfoDto> list = new ArrayList<GoodsInfoDto>();
        for (Object[] row : rowList) {
            GoodsInfoDto aid = new GoodsInfoDto();
            aid.setGoodsId((Integer) row[0]);
            aid.setGoodsBarCode((String) row[1]);
            aid.setGoodsName((String) row[2]);
            aid.setGoodsStatus((String) row[3]);
            aid.setGoodsTypeName((String) row[4]);
            aid.setAllotDtlId((Integer) row[5]);
            aid.setAllotId((Integer) row[6]);
            aid.setExpectNumber((Double) row[7]);
            aid.setActualNumber((Double) row[8]);
            list.add(aid);
        }
        return list;
    }

    public List<StorageAllotDtl> queryStorageAllotDtlById(Integer id) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from StorageAllotDtl sad where 1 = 1 ");
        hql.append(" and sad.allotId = ").append(id);
        List<StorageAllotDtl> list = this.find(hql.toString(), null);
        return list;
    }

    /**
     * 查询调拨单明细信息
     **/
    public DTablePageModel queryGoodsInfo(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        if (params != null) {
            if (params.containsKey("allotStatus")) {
                String allotStatus = (String) params.get("allotStatus");
                if ("0".equals(allotStatus)) {
                    hql.append(" select new map(gi.goodsName as goodsName,gi.goodsTypeName as goodsTypeName,gi.goodsBarCode as goodsBarCode,sa.expectNumber as expectNumber,se.deliveryCount as deliveryCount,gi.goodsStatus as goodsStatus) " +
                            "from GoodsInfo gi,StorageAllotDtl sa,StorageDeliveryOrder sd,StorageDeliveryDtl se where gi.goodsId=sa.goodsId and sa.goodsId=se.goodsId and sa.allotId=sd.allotId and sd.deliveryOrderId=se.deliveryOrderId");
                    hqlCount.append("select count(*) from GoodsInfo gi,StorageAllotDtl sa,StorageDeliveryOrder sd,StorageDeliveryDtl se " +
                            " where gi.goodsId=sa.goodsId and sa.goodsId=se.goodsId and sa.allotId=sd.allotId and sd.deliveryOrderId=se.deliveryOrderId");
                } else if ("1".equals(allotStatus)) {
                    hql.append(" select new map(gi.goodsName as goodsName,gi.goodsTypeName as goodsTypeName,gi.goodsBarCode as goodsBarCode,sa.expectNumber as expectNumber,gi.goodsStatus as goodsStatus) " +
                            "from GoodsInfo gi,StorageAllotDtl sa where gi.goodsId=sa.goodsId");
                    hqlCount.append("select count(*) from GoodsInfo gi,StorageAllotDtl sa" +
                            " where gi.goodsId=sa.goodsId");
                }
            }
            String allotId = (String) params.get("allotId");
            if (StringUtils.isNotBlank(allotId)) {
                hql.append(" and sa.allotId =").append(Integer.valueOf(allotId));
                hqlCount.append(" and sa.allotId =").append(Integer.valueOf(allotId));
            }
        }
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);

    }

    /**
     * 根据ID查询仓库商品分页信息
     **/
    public DTablePageModel queryStorageGoodsInfo(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from StorageInventory si where 1 = 1 ");
        hqlCount.append("select count(*) from StorageInventory si where 1 = 1 ");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                hql.append(" and (si.goodsInfo.goodsName like '%").append(keyword).append("%'")
                        .append(" or si.goodsInfo.goodsTypeName like '%").append(keyword).append("%')");
                hqlCount.append(" and (si.goodsInfo.goodsName like '%").append(keyword).append("%'")
                        .append(" or si.goodsInfo.goodsTypeName like '%").append(keyword).append("%')");
            }
            if (params.containsKey("warehouseId")) {
                String warehouseId = (String) params.get("warehouseId");
                if (StringUtils.isNotBlank(warehouseId)) {
                    hql.append(" and si.warehouseId =").append(Integer.valueOf(warehouseId));
                    hqlCount.append(" and si.warehouseId =").append(Integer.valueOf(warehouseId));
                }
            }
        }
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }
}
