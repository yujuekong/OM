package com.smart.om.dao.inventory;

import com.smart.om.biz.goods.GoodsHandler;
import com.smart.om.biz.inventory.InventoryHandler;
import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.GoodsInfo;
import com.smart.om.persist.StorageInventory;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 库存DAO
 *
 * @author langyuk
 */
public class InventoryDAO extends BaseDao {
    @Autowired
    private GoodsHandler goodsHandler;
    @Autowired
    private InventoryHandler inventoryHandler;

    /**
     * 查询库存列表
     **/
    public DTablePageModel queryStorageInventoryPage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from StorageInventory sg where 1 = 1 ");
        hqlCount.append("select count(*) from StorageInventory sg where 1 = 1");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (sg.inventoryId like '%").append(keyword).append("%'")
                            .append(" or sg.warehouseId like '%").append(keyword).append("%') ")
                            .append(" or sg.goodsId like '%").append(keyword).append("%') ")
                            .append(" or sg.inventoryNumber like '%").append(keyword).append("%') ")
                            .append(" or sg.goodsUnit like '%").append(keyword).append("%') ");
                    hql.append(" and (sg.inventoryId like '%").append(keyword).append("%'")
                            .append(" or sg.warehouseId like '%").append(keyword).append("%') ")
                            .append(" or sg.goodsId like '%").append(keyword).append("%') ")
                            .append(" or sg.inventoryNumber like '%").append(keyword).append("%') ")
                            .append(" or sg.goodsUnit like '%").append(keyword).append("%') ");
                }
            }
            if (params.containsKey("warehouseId")) {
                String warehouseId = (String) params.get("warehouseId");
                if (StringUtils.isNotBlank(warehouseId)) {
                    hql.append(" and sg.warehouseId = ").append(warehouseId);
                    hqlCount.append(" and sg.warehouseId = ").append(warehouseId);
                }

            }
        }
        hql.append("order by sg.inventoryId");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    /**
     * 根据仓库ID，查询仓库库存
     *
     * @param warehouseId
     * @return
     */
    public List<StorageInventory> queryStorageInventoryById(Integer warehouseId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from StorageInventory as model where 1=1 ");
        hql.append(" and model.warehouseId = " + warehouseId);
        List<StorageInventory> storageInventoryList = (List<StorageInventory>) this.find(hql.toString(), null);
        return storageInventoryList;
    }

    /**
     * 根据仓库ID,删除仓库库存明细
     *
     * @param warehouseId
     * @return
     */
    public int deleteStorageInventoryById(Integer warehouseId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" delete StorageInventory as model where 1=1 ");
        hql.append(" and model.warehouseId = " + warehouseId);
        int deleteSize = this.updateHql(hql.toString(), null);
        return deleteSize;
    }

    /**
     * 根据仓库ID，商品ID查询库存
     */
    public StorageInventory queryStorageInventoryDt(Integer goodsId, Integer wareHouseId) {
        StringBuffer hql = new StringBuffer();
        hql.append("from StorageInventory si where 1=1 ");
        hql.append(" and si.goodsId = ").append(goodsId);
        hql.append(" and si.warehouseId = ").append(wareHouseId);
//        Session session = this.getSession();
//        Query query = session.createQuery(hql.toString());
//        List list = query.list();
        List list = this.find(hql.toString(), null);
        if (list.size() > 0) {
            StorageInventory storageInventory = (StorageInventory) list.get(0);
            return storageInventory;
        } else {
            return null;
        }
    }

    /**
     * 根据仓库ID和商品ID 查询仓库库存
     **/
    public StorageInventory queryStorageInventory(Integer warehouseId, Integer goodsId) {
        String hql = "from StorageInventory where warehouseId=" + warehouseId + " and goodsId = " + goodsId;
//        Session session = this.getSession();
//        Query query = session.createQuery(hql);
//        List list = query.list();
        List list = this.find(hql.toString(), null);
        if (list!=null) {
            StorageInventory storageInventory = (StorageInventory) list.get(0);
            return storageInventory;
        } else {
            return null;
        }
    }

    /**
     * 根据商品ID和数量修改仓库库存
     *
     * @param warehouseId      仓库ID
     * @param goodsId          商品ID
     * @param warehousingCount 商品入库数量
     */
    public void modifyStorageInventory(Integer warehouseId, Integer goodsId, Double warehousingCount) {
        StringBuffer findHql = new StringBuffer();
        findHql.append("from StorageInventory where warehouseId=" + warehouseId + " and goodsId=" + goodsId);
        List inventoryList = this.find(findHql.toString(), null);
        if (inventoryList != null) {
            StorageInventory storageInventory = (StorageInventory) inventoryList.get(0);
            Double currentNumber = storageInventory.getInventoryNumber() + warehousingCount;
            storageInventory.setInventoryNumber(currentNumber);
            inventoryHandler.saveOrUpdateStorageInventory(storageInventory);
        } else {
            StorageInventory storageInventory = new StorageInventory();
            storageInventory.setNewer(true);
            storageInventory.setWarehouseId(warehouseId);
            storageInventory.setGoodsId(goodsId);
            storageInventory.setInventoryNumber(warehousingCount);
            GoodsInfo goodsInfo = (GoodsInfo) goodsHandler.queryGoodsInfoById(goodsId);
            storageInventory.setGoodsUnit(goodsInfo.getMeasurementUnit());
            inventoryHandler.saveOrUpdateStorageInventory(storageInventory);
        }

    }
}
