package com.smart.om.biz.inventory.impl;

import com.smart.om.biz.inventory.InventoryHandler;
import com.smart.om.dao.base.BasePo;
import com.smart.om.dao.inventory.*;
import com.smart.om.dto.inventory.GoodsInfoDto;
import com.smart.om.persist.*;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/16.
 */
@Component("InventoryHandler")
public class InventoryHandlerImpl implements InventoryHandler {
    private static final Logger logger = Logger.getLogger(InventoryHandlerImpl.class);
    @Autowired
    private WarehouseDAO warehouseDAO;//仓库DAO
    @Autowired
    private InventoryDAO inventoryDAO;//库存DAO
    @Autowired
    private WarehousingEntryDAO warehousingEntryDAO;//入库单DAO
    @Autowired
    private WarehousingEntryDetailDAO warehousingEntryDetailDAO;//入库单明细DAO
    @Autowired
    private DeliveryOrderDAO deliveryOrderDAO;//出库单DAO
    @Autowired
    private DeliveryDetailDAO deliveryDetailDAO;//出库单明细DAO
    @Autowired
    private DeliveryAgentDAO deliveryAgentDAO;//出库处理DAO
    @Autowired
    private DebtDAO debtDAO;//报损DAO
    @Autowired
    private DebtDtlDAO debtDtlDAO;//报损明细DAO
    @Autowired
    private AllotDAO allotDAO;//调拨单DAO
    @Autowired
    private AllotDtlDAO allotDtlDAO;//调拨单明细DAO

    /**
     * 仓库列表分页查询
     *
     * @param params
     * @param pageData
     * @return
     */
    @Override
    public DTablePageModel queryWarehousePage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = warehouseDAO.queryWarehousePage(params, pageData);
        }
        return pageModel;
    }

    /**
     * 根据ID查询仓库详情
     *
     * @param warehouseId
     * @return
     */
    @Override
    public BasePo queryWarehouseById(Integer warehouseId) {
        return warehouseDAO.find(warehouseId);
    }

    /**
     * 保存或修改仓库
     *
     * @param storageWarehouse
     * @return
     */
    @Override
    public BasePo saveOrUpdateWarehouse(StorageWarehouse storageWarehouse) {
        return warehouseDAO.saveOrUpdateWarehouse(storageWarehouse);
    }

    /**
     * 根据ID删除仓库
     *
     * @param warehouseId
     * @return
     */
    @Override
    public BasePo deleteWarehouse(Integer warehouseId) {
        return warehouseDAO.delete(warehouseId);
    }

    @Override
    public boolean isInventory(Integer id) {
        return warehouseDAO.isInventory(id);
    }

    /**
     * 仓库库存分页查询
     *
     * @param params
     * @param pageData
     * @return
     */
    @Override
    public DTablePageModel queryStorageInventoryPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = inventoryDAO.queryStorageInventoryPage(params, pageData);
        }
        return pageModel;
    }

    /**
     * 根据仓库ID查询库存详情
     *
     * @param warehouseId
     * @return
     */
    @Override
    public List<StorageInventory> queryStorageInventoryById(Integer warehouseId) {
        return inventoryDAO.queryStorageInventoryById(warehouseId);
    }

    /**
     * 根据仓库ID批量删除库存信息
     *
     * @param warehouseId
     * @return
     */
    @Override
    public int deleteStorageInventoryById(Integer warehouseId) {
        return inventoryDAO.deleteStorageInventoryById(warehouseId);
    }

    /**
     * 根据仓库ID和商品ID 查询仓库库存
     **/
    public StorageInventory queryStorageInventory(Integer warehouseId, Integer goodsId) {
        return inventoryDAO.queryStorageInventory(warehouseId, goodsId);
    }


    /**
     * 保存或修改库存明细
     *
     * @param basePo
     * @return
     */
    @Override
    public BasePo saveOrUpdateStorageInventory(BasePo basePo) {
        return inventoryDAO.save(basePo);
    }

    /**
     * 入库单分页查询
     *
     * @param params
     * @param pageData
     * @return
     */
    @Override
    public DTablePageModel queryWarehousingPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = warehousingEntryDAO.queryWarehousePage(params, pageData);
        }
        return pageModel;
    }

    /**
     * 查询个人入库任务
     **/
    public List<StorageWarehousingEntry> queryWarehousingList(Integer userId) {
        return warehousingEntryDAO.queryWarehousingList(userId);
    }

    /**
     * 根据入库单ID,查询入库单详情
     *
     * @param id
     * @return
     */
    @Override
    public BasePo queryWarehousingById(Integer id) {
        return warehousingEntryDAO.find(id);
    }

    /**
     * 根据入库单ID,查询入库单明细
     *
     * @param warehousingEntryId
     * @return
     */
    public List<StorageWarehousingDtl> queryWarehousingDtlByEntryId(Integer warehousingEntryId) {
        return warehousingEntryDetailDAO.queryWarehousingDtlByEntryId(warehousingEntryId);
    }

    /**
     * 根据入库单ID批量删除入库单明细
     *
     * @param warehousingEntryId
     * @return
     */
    @Override
    public int deleteWarehousingDtlByEntryId(Integer warehousingEntryId) {
        return warehousingEntryDetailDAO.deleteWarehousingDtlByEntryId(warehousingEntryId);
    }

    @Override
    public int modifyWarehousingCount(Integer warehousingDtlId, Double warehousingCount, Integer goodsId, Integer warehouseId) {
        return warehousingEntryDetailDAO.modifyWarehousingCount(warehousingDtlId, warehousingCount, goodsId, warehouseId);
    }

    @Override
    public BasePo queryWarehousingDtlById(Integer id) {
        return warehousingEntryDetailDAO.find(id);
    }

    /**
     * 保存或修改入库单
     *
     * @param basePo
     * @return
     */
    @Override
    public BasePo saveOrUpdateWarehousing(BasePo basePo) {
        return warehousingEntryDAO.save(basePo);
    }

    /**
     * 根据调拨单ID查询出库单是否出库
     */
    public StorageDeliveryOrder querySDOrderByAllotId(Integer allotId) {
        return deliveryDetailDAO.querySDOrderByAllotId(allotId);
    }

    /**
     * 查询入库业务类别
     */
    @Override
    public List<SysDict> getAllBizType(String string) {
        return warehousingEntryDAO.getAllBizType(string);
    }

    /**
     * 根据入库单ID删除入库单
     *
     * @param id
     * @return
     */
    @Override
    public BasePo deleteWarehousingById(Integer id) {
        return warehousingEntryDAO.delete(id);
    }

    /**
     * 出库单分页查询
     *
     * @param params
     * @param pageData
     * @return
     */
    @Override
    public DTablePageModel queryDeliveryOrderPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = deliveryOrderDAO.queryDeliveryOrderPage(params, pageData);
        }
        return pageModel;
    }

    /**
     * 查询出库及清洗个人任务
     */
    public List<StorageDeliveryOrder> queryStorageDeliveryOrderList(Integer userId, Integer num) {
        return deliveryOrderDAO.queryStorageDeliveryOrderList(userId, num);
    }

    /**
     * 根据出库单ID,查询出库单详情
     *
     * @param id
     * @return
     */
    @Override
    public BasePo queryDeliveryOrderById(Integer id) {
        return deliveryOrderDAO.find(id);
    }

    @Override
    public List getUserRoleName(Integer id) {
        return deliveryOrderDAO.getUserRoleName(id);
    }

    /**
     * 根据出库单ID，查询出库单明细
     *
     * @param deliveryOrderId
     * @return
     */
    public List<StorageDeliveryDtl> queryDeliveryDtlByDeliveryId(Integer deliveryOrderId) {
        return deliveryDetailDAO.queryDeliveryDtlByDeliveryId(deliveryOrderId);
    }

    /**
     * 根据出库单ID,批量删除出库单明细
     *
     * @param deliveryOrderId
     * @return
     */
    @Override
    public int deleteDeliveryDtlByDeliveryId(Integer deliveryOrderId) {
        return deliveryDetailDAO.deleteWarehousingDtlByEntryId(deliveryOrderId);
    }

    /**
     * 根据出库单明细ID查询出库单明细
     *
     * @param id
     * @return
     */
    public StorageDeliveryDtl queryDeliveryDtlById(Integer id) {
        return (StorageDeliveryDtl) deliveryDetailDAO.find(id);
    }

    /**
     * 保存或修改出库单
     *
     * @param basePo
     * @return
     */
    @Override
    public BasePo saveOrUpdateDeliveryOrder(BasePo basePo) {
        return deliveryOrderDAO.save(basePo);
    }

    /**
     * 根据ID删除出库单
     *
     * @param id
     * @return
     */
    @Override
    public BasePo deleteDeliveryOrder(Integer id) {
        return deliveryOrderDAO.delete(id);
    }

    /**
     * 入库单明细分页查询
     *
     * @param params
     * @param pageData
     * @return
     */
    @Override
    public DTablePageModel queryEntryDetailPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = warehousingEntryDetailDAO.queryEntryDetailPage(params, pageData);
        }
        return pageModel;
    }

    /**
     * 根据ID查询入库单明细详情
     *
     * @param id
     * @return
     */
    @Override
    public BasePo queryEntryDetailById(Integer id) {
        return warehousingEntryDetailDAO.find(id);
    }

    @Override
    public boolean isAllwarehousing(Integer id) {
        return warehousingEntryDetailDAO.isAllwarehousing(id);
    }

    /**
     * 保存或修改入库单明细
     *
     * @param basePo
     * @return
     */
    @Override
    public BasePo saveOrUpdateEntryDetail(BasePo basePo) {
        return warehousingEntryDetailDAO.save(basePo);
    }

    /**
     * 根据ID删除入库单明细
     *
     * @param id
     * @return
     */
    @Override
    public BasePo deleteEntryDetail(Integer id) {
        return warehousingEntryDetailDAO.delete(id);
    }

    /**
     * 仓库中是否已存在此ID的商品
     */
    @Override
    public boolean goodsIsExistHouse(Integer goodsId, Integer warehouseId) {
        return warehousingEntryDetailDAO.goodsIsExistHouse(goodsId, warehouseId);
    }

    /**
     * 出库单明细分页查询
     *
     * @param params
     * @param pageData
     * @return
     */
    @Override
    public DTablePageModel queryDeliveryDetailPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = deliveryDetailDAO.queryDeliveryDetailPage(params, pageData);
        }
        return pageModel;
    }

    /**
     * 根据ID查询出库单明细详情
     *
     * @param id
     * @return
     */
    @Override
    public BasePo queryDeliveryDetailById(Integer id) {
        return deliveryDetailDAO.find(id);
    }

    /**
     * 保存或修改出库单明细
     *
     * @param basePo
     * @return
     */
    @Override
    public BasePo saveOrUpdateDeliveryDetail(BasePo basePo) {
        return deliveryDetailDAO.save(basePo);
    }

    /**
     * 根据ID删除出库单明细
     *
     * @param id
     * @return
     */
    @Override
    public BasePo deleteDeliveryDetail(Integer id) {
        return deliveryOrderDAO.delete(id);
    }

    /**
     * 出库单明细分页查询
     *
     * @param params
     * @param pageData
     * @return
     */
    @Override
    public DTablePageModel queryDeliveryAgentPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = deliveryAgentDAO.queryDeliveryAgentPage(params, pageData);
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
        return deliveryAgentDAO.queryDeliveryAgentByDeliveryId(deliveryOrderId);
    }

    /**
     * 根据出库处理ID，查询出库处理对象
     *
     * @param DeliveryAgentId
     * @return
     */
    public StorageDeliveryAgent queryDeliveryAgentByAgentId(Integer DeliveryAgentId) {
        return (StorageDeliveryAgent) deliveryAgentDAO.find(DeliveryAgentId);
    }

    /**
     * 添加或修改处理明细
     *
     * @param StorageDeliveryAgent
     * @return
     */
    public StorageDeliveryAgent saveOrUpdateDeliveryAgent(StorageDeliveryAgent StorageDeliveryAgent) {
        return (StorageDeliveryAgent) deliveryAgentDAO.save(StorageDeliveryAgent);
    }

    /**
     * 出库单ID查询对应配送订单
     *
     * @param params
     * @param pageData
     * @return
     */
    @Override
    public DTablePageModel queryDispatchingListById(Map<String, Object> params, PageData pageData) {
        return deliveryAgentDAO.queryDispatchingListById(params, pageData);
    }

    /**
     * 根据订单ID查询对应子订单
     *
     * @param params
     * @param pageData
     * @return
     */
    @Override
    public DTablePageModel querySubOrderListById(Map<String, Object> params, PageData pageData) {
        return deliveryAgentDAO.querySubOrderListById(params, pageData);
    }

    /**
     * 查询单个订单下的商品总数分组信息
     */
    @Override
    public DTablePageModel queryOrderAgentDet(Map<String, Object> params, PageData pageData) {
        return deliveryAgentDAO.queryOrderAgentDet(params, pageData);
    }

    /**
     * 查询每个设备的商品数量统计
     */
    @Override
    public DTablePageModel queryDeviceSumGoodsById(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = deliveryAgentDAO.queryDeviceSumGoodsById(params, pageData);
        }
        return pageModel;
    }

    @Override
    public DTablePageModel querySumGoodsMotion(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = deliveryAgentDAO.querySumGoodsMotion(params, pageData);
        }
        return pageModel;
    }

    /**
     * 查询设备分组信息
     */
    @Override
    public DTablePageModel queryDeviceGroup(Map<String, Object> params, PageData pageData) {
        return deliveryAgentDAO.queryDeviceGroup(params, pageData);
    }

    /**
     * 报损单分页查询
     *
     * @param params
     * @param pageData
     * @return
     */
    @Override
    public DTablePageModel queryStorageDebtPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = debtDAO.queryStorageDebtPage(params, pageData);
        }
        return pageModel;
    }


    @Override
    public List<StorageDebtDtl> queryStorageDebtDtlById(Integer debtId) {
        return debtDtlDAO.queryStorageDebtDtlById(debtId);
    }

    /**
     * 查询报损单详情
     */
    @Override
    public BasePo queryStorageDebtById(Integer id) {
        return debtDAO.find(id);
    }

    /**
     * 报损单明细分页查询
     *
     * @param params
     * @param pageData
     * @return
     */
    @Override
    public DTablePageModel queryStorageDebtDtlPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = debtDtlDAO.queryStorageDebtDtlPage(params, pageData);
        }
        return pageModel;
    }

    @Override
    public BasePo saveOrUpdateDebtDtl(BasePo basePo) {
        return debtDtlDAO.save(basePo);
    }

    @Override
    public int deleteStorageDebtById(Integer debtId) {
        return debtDtlDAO.deleteStorageDebtDtlById(debtId);
    }

    @Override
    public BasePo deleteStorageDebt(Integer id) {
        return debtDAO.delete(id);
    }

    @Override
    public List<SysDict> getAllDebtType(String str) {
        return debtDAO.getAllDebtType(str);
    }

    @Override
    public BasePo saveOrUpdateStorageDebt(BasePo basePo) {
        return debtDAO.saveOrUpdate(basePo);
    }

    @Override
    public List queryDispatchingPlanList(Integer outOrderId) {
        return deliveryOrderDAO.queryDispatchingPlanList(outOrderId);
    }

    /**
     * 查询调拨单分页信息
     **/
    public DTablePageModel queryTransferOrderPage(Map<String, Object> params,
                                                  PageData pageData) {
        return allotDAO.queryTransferOrderPage(params, pageData);
    }

    /**
     * 新增调拨单
     **/
    public StorageAllot saveOrUpdateAllotOrder(StorageAllot storageAllot) {

        return (StorageAllot) allotDAO.saveOrUpdate(storageAllot);
    }

    /**
     * 新增调拨单明细
     **/
    public StorageAllotDtl saveOrUpdateAllotDtl(StorageAllotDtl list) {

        return (StorageAllotDtl) allotDtlDAO.save(list);
    }

    /**
     * 根据ID查询调拨单
     **/
    public StorageAllot queryAllotOrderById(Integer id) {

        return (StorageAllot) allotDAO.find(id);
    }

    /**
     * 根据ID查询调拨单明细
     **/
    public List<GoodsInfoDto> queryStorageAllotDtById(Integer id) {
        return allotDtlDAO.queryStorageAllotDtById(id);
    }

    /**
     * 删除调拨单
     **/
    public StorageAllot deleteStorageAllot(StorageAllot storageAllot) {

        return (StorageAllot) allotDAO.delete(storageAllot);
    }

    @Override
    public List<StorageAllotDtl> queryStorageAllotDtlById(Integer id) {

        return allotDtlDAO.queryStorageAllotDtlById(id);
    }

    @Override
    public StorageAllotDtl deleteStorageAllotDtl(StorageAllotDtl storageAllotDtl) {

        return (StorageAllotDtl) allotDtlDAO.delete(storageAllotDtl);
    }

    @Override
    public DTablePageModel queryGoodsInfoPage(Map<String, Object> params,
                                              PageData pageData) {

        return allotDtlDAO.queryGoodsInfo(params, pageData);
    }


    /**
     * 根据商品ID查询库存
     **/
    public StorageInventory queryStorageInventory(Integer id) {
        return (StorageInventory) inventoryDAO.find(id);
    }


    /**
     * 根据所属区域查询仓库ID
     */
    public List<StorageWarehouse> queryStorageWarehouseById(Integer orgId) {
        return warehouseDAO.queryStorageWarehouseById(orgId);
    }

    /**
     * 根据商品ID和所属仓库查询库存
     **/
    public StorageInventory queryStorageInventoryDt(Integer goodsId, Integer wareHouseId) {
        return inventoryDAO.queryStorageInventoryDt(goodsId, wareHouseId);
    }

    /**
     * 根据ID查询仓库商品分页信息
     **/
    public DTablePageModel queryStorageGoodsInfo(Map<String, Object> params,
                                                 PageData pageData) {

        return allotDtlDAO.queryStorageGoodsInfo(params, pageData);
    }

}
