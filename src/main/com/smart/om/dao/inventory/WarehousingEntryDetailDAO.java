package com.smart.om.dao.inventory;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.StorageWarehousingDtl;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/28.
 */
public class WarehousingEntryDetailDAO extends BaseDao {
    @Autowired
    private InventoryDAO inventoryDAO;

    /**
     * 入库单明细
     **/
    public DTablePageModel queryEntryDetailPage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from StorageWarehousingDtl sh where 1 = 1 ");
        hqlCount.append("select count(*) from StorageWarehousingDtl sh where 1 = 1");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (sh.warehousingDtlId like '%").append(keyword).append("%'")
                            .append(" or sh.warehousingEntryId like '%").append(keyword).append("%') ")
                            .append(" or sh.goodsId like '%").append(keyword).append("%') ")
                            .append(" or sh.warehousingCount like '%").append(keyword).append("%') ")
                            .append(" or sh.warehousingPrice like '%").append(keyword).append("%') ")
                            .append(" or sh.warehousingAmount like '%").append(keyword).append("%') ")
                            .append(" or sh.remark like '%").append(keyword).append("%') ");
                    hqlCount.append(" and (sh.warehousingDtlId like '%").append(keyword).append("%'")
                            .append(" or sh.warehousingEntryId like '%").append(keyword).append("%') ")
                            .append(" or sh.goodsId like '%").append(keyword).append("%') ")
                            .append(" or sh.warehousingCount like '%").append(keyword).append("%') ")
                            .append(" or sh.warehousingPrice like '%").append(keyword).append("%') ")
                            .append(" or sh.warehousingAmount like '%").append(keyword).append("%') ")
                            .append(" or sh.remark like '%").append(keyword).append("%') ");
                }
            }
            if (params.containsKey("warehousingId")) {
                String warehousingId = (String) params.get("warehousingId");
                if (StringUtils.isNotBlank(warehousingId)) {
                    hql.append(" and sh.warehousingEntryId =").append(warehousingId);
                    hqlCount.append(" and sh.warehousingEntryId =").append(warehousingId);
                }
            }

            if (params.containsKey("showAll")) {
                String showAll = (String) params.get("showAll");
                if ("1".equals(showAll)) {
                    hql.append(" and (warehousingStatus=0 or warehousingStatus=null)");
                    hqlCount.append(" and (warehousingStatus=0 or warehousingStatus=null)");
                }
            }
        }
        hql.append(" order by sh.warehousingDtlId");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    /**
     * 根据入库单ID，查询入库单明细
     *
     * @param warehousingEntryId
     * @return
     */
    public List<StorageWarehousingDtl> queryWarehousingDtlByEntryId(Integer warehousingEntryId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from StorageWarehousingDtl as model where 1=1 ");
        hql.append(" and model.warehousingEntryId = " + warehousingEntryId);
        List<StorageWarehousingDtl> warehousingDtlList = (List<StorageWarehousingDtl>) this.find(hql.toString(), null);
        return warehousingDtlList;
    }

    /**
     * 根据入库单ID，删除对应入库单明细
     *
     * @param warehousingEntryId
     * @return
     */
    public int deleteWarehousingDtlByEntryId(Integer warehousingEntryId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" delete StorageWarehousingDtl as model where 1=1 ");
        hql.append(" and model.warehousingEntryId = " + warehousingEntryId);
        int deleteSize = this.updateHql(hql.toString(), null);
        return deleteSize;
    }

    /**
     * 仓库中是否已存在此ID的商品
     */
    public boolean goodsIsExistHouse(Integer goodsId, Integer warehouseId) {
        StringBuffer hql = new StringBuffer();
        hql.append("from StorageInventory as model where 1=1");
        hql.append(" and model.goodsId = " + goodsId + "and model.warehouseId =" + warehouseId);
        List list = this.find(hql.toString(), null);
        return list != null;
    }

    /**
     * 修改入库商品实际数量
     */
    public int modifyWarehousingCount(Integer warehousingDtlId, Double warehousingCount, Integer goodsId, Integer warehouseId) {
        StringBuffer hql = new StringBuffer();
        hql.append("update StorageWarehousingDtl set warehousingCount=" + warehousingCount + ",warehousingStatus=1 where warehousingDtlId=" + warehousingDtlId);
        inventoryDAO.modifyStorageInventory(warehouseId, goodsId, warehousingCount);
        return this.updateHql(hql.toString(), null);
    }

    /**
     * 入库单是否全部入库
     */
    public boolean isAllwarehousing(Integer id) {
        boolean isAllwarehousing = false;
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from StorageWarehousingDtl where warehousingStatus='0' and warehousingEntryId=" + id);
        List list = this.find(hql.toString(), null);
        String count = list.get(0).toString();
        System.out.println(list.get(0));
        if (count.equals("0")) {
            isAllwarehousing = true;
        }
        return isAllwarehousing;
    }

}
