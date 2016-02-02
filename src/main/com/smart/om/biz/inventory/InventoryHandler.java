package com.smart.om.biz.inventory;

import com.smart.om.dao.base.BasePo;
import com.smart.om.dto.inventory.GoodsInfoDto;
import com.smart.om.persist.*;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/16.
 */
public interface InventoryHandler {

    /**
     * 查询仓库列表
     */
    DTablePageModel queryWarehousePage(Map<String, Object> params, PageData pageData);

    /**
     * 查询仓库详情
     */
    BasePo queryWarehouseById(Integer id);

    /**
     * 新增或修改仓库
     */
    BasePo saveOrUpdateWarehouse(StorageWarehouse storageWarehouse);

    /**
     * 删除仓库
     */
    BasePo deleteWarehouse(Integer id);

    /**
     * 查询仓库是否有库存
     */
    boolean isInventory(Integer id);

    /**
     * 查询库存列表
     */
    DTablePageModel queryStorageInventoryPage(Map<String, Object> params, PageData pageData);


    /**
     * 根据仓库ID，查询仓库库存
     *
     * @param warehouseId
     * @return
     */
    List<StorageInventory> queryStorageInventoryById(Integer warehouseId);

    /**
     * 根据仓库ID，删除仓库库存明细
     *
     * @param warehouseId
     * @return
     */
    int deleteStorageInventoryById(Integer warehouseId);

    /**
     * 根据仓库ID和商品ID 查询仓库库存
     **/
    StorageInventory queryStorageInventory(Integer warehouseId, Integer goodsId);

    /**
     * 新增或修改库存明细
     *
     * @param basePo
     * @return
     */
    BasePo saveOrUpdateStorageInventory(BasePo basePo);


    /**
     * 查询入库单列表
     */
    DTablePageModel queryWarehousingPage(Map<String, Object> params, PageData pageData);

    /**
     * 查询个人入库任务
     **/
    List<StorageWarehousingEntry> queryWarehousingList(Integer userId);

    /**
     * 查询入库单详情
     */
    BasePo queryWarehousingById(Integer id);

    /**
     * 根据入库单ID，查询入库单明细
     *
     * @param warehousingEntryId
     * @return
     */
    List<StorageWarehousingDtl> queryWarehousingDtlByEntryId(Integer warehousingEntryId);

    /**
     * 根据入库单ID，删除对应入库单明细
     *
     * @param warehousingEntryId
     * @return
     */
    int deleteWarehousingDtlByEntryId(Integer warehousingEntryId);

    /**
     * 修改入库商品实际数量
     *
     * @param warehousingDtlId 入库单明细ID
     * @param warehousingCount 入库商品数量Double
     * @param goodsId          商品ID
     * @param warehouseId      仓库ID
     * @return int
     */
    int modifyWarehousingCount(Integer warehousingDtlId, Double warehousingCount, Integer goodsId, Integer warehouseId);

    /**
     * 根据ID查询入库单明细
     */
    BasePo queryWarehousingDtlById(Integer id);

    /**
     * 新增或修改入库单
     */
    BasePo saveOrUpdateWarehousing(BasePo basePo);

    /**
     * 根据调拨单ID查询出库单是否出库
     */
    StorageDeliveryOrder querySDOrderByAllotId(Integer allotId);

    /**
     * 查询入库业务类别
     */
    List<SysDict> getAllBizType(String string);

    /**
     * 删除入库单
     */
    BasePo deleteWarehousingById(Integer id);


    /**
     * 查询出库单列表
     */
    DTablePageModel queryDeliveryOrderPage(Map<String, Object> params, PageData pageData);

    /**
     * 查询出库及清洗个人任务
     */
    List<StorageDeliveryOrder> queryStorageDeliveryOrderList(Integer userId, Integer num);

    /**
     * 查询出库详情
     */
    BasePo queryDeliveryOrderById(Integer id);

    /**
     *获取登录用户角色名
     */
    List getUserRoleName(Integer id);

    /**
     * 根据出库单明细ID查询出库单明细
     */
    StorageDeliveryDtl queryDeliveryDtlById(Integer id);

    /**
     * 根据出库单ID，查询出库单明细
     *
     * @param deliveryOrderId
     * @return
     */
    List<StorageDeliveryDtl> queryDeliveryDtlByDeliveryId(Integer deliveryOrderId);

    /**
     * 根据出库单ID，删除对应入库单明细
     *
     * @param deliveryOrderId
     * @return
     */
    int deleteDeliveryDtlByDeliveryId(Integer deliveryOrderId);

    /**
     * 新增或修改出库单
     */
    BasePo saveOrUpdateDeliveryOrder(BasePo basePo);

    /**
     * 删除出库单
     */
    BasePo deleteDeliveryOrder(Integer id);


    /**
     * 查询入库单详情列表
     */
    DTablePageModel queryEntryDetailPage(Map<String, Object> params, PageData pageData);

    /**
     * 查询入库单详情
     */
    BasePo queryEntryDetailById(Integer id);

    /**
     * 入库单是否全部入库
     */
    boolean isAllwarehousing(Integer id);

    /**
     * 新增或修改入库单
     */
    BasePo saveOrUpdateEntryDetail(BasePo basePo);

    /**
     * 删除入库单
     */
    BasePo deleteEntryDetail(Integer id);

    /**
     * 仓库中是否已存在此ID的商品
     */
    boolean goodsIsExistHouse(Integer goodsId, Integer warehouseId);

    /**
     * 查询出库单详情列表
     */
    DTablePageModel queryDeliveryDetailPage(Map<String, Object> params, PageData pageData);

    /**
     * 查询出库单详情
     */
    BasePo queryDeliveryDetailById(Integer id);

    /**
     * 新增或修改出库单
     */
    BasePo saveOrUpdateDeliveryDetail(BasePo basePo);

    /**
     * 删除出库单
     */
    BasePo deleteDeliveryDetail(Integer id);

    /**
     * 出库单明细分页查询
     *
     * @param params
     * @param pageData
     * @return
     */
    DTablePageModel queryDeliveryAgentPage(Map<String, Object> params, PageData pageData);

    /**
     * 根据出库单ID，查询出库单处理商品
     *
     * @param deliveryOrderId
     * @return
     */
    List<StorageDeliveryDtl> queryDeliveryAgentByDeliveryId(Integer deliveryOrderId);

    /**
     * 根据出库处理ID，查询出库处理对象
     *
     * @param deliveryAgentId
     * @return
     */
    StorageDeliveryAgent queryDeliveryAgentByAgentId(Integer deliveryAgentId);

    /**
     * 添加或修改处理明细
     *
     * @param storageDeliveryAgent
     * @return
     */
    StorageDeliveryAgent saveOrUpdateDeliveryAgent(StorageDeliveryAgent storageDeliveryAgent);


    /**
     * 出库单ID查询对应配送订单
     *
     * @param params
     * @param pageData
     * @return
     */
    DTablePageModel queryDispatchingListById(Map<String, Object> params, PageData pageData);


    /**
     * 根据订单ID查询对应子订单
     *
     * @param params
     * @param pageData
     * @return
     */
    DTablePageModel querySubOrderListById(Map<String, Object> params, PageData pageData);


    /**
     * 查询单个订单下的商品总数分组信息
     */
    DTablePageModel queryOrderAgentDet(Map<String, Object> params, PageData pageData);

    /**
     * 查询每个设备的商品数量统计
     *
     * @param params
     * @param pageData
     * @return
     */
    DTablePageModel queryDeviceSumGoodsById(Map<String, Object> params, PageData pageData);

    /**
     * 查询商圈商品统计
     *
     * @param params
     * @param pageData
     * @return
     */
    DTablePageModel querySumGoodsMotion(Map<String, Object> params, PageData pageData);


    /**
     * 报损单分页查询
     *
     * @param params
     * @param pageData
     * @return
     */
    DTablePageModel queryStorageDebtPage(Map<String, Object> params, PageData pageData);

    /**
     * 查询设备分组
     *
     * @param params
     * @param pageData
     * @return
     */
    DTablePageModel queryDeviceGroup(Map<String, Object> params, PageData pageData);

    /**
     * 根据出库单ID，查询出库单报损商品
     *
     * @param debtId
     * @return
     */
    List<StorageDebtDtl> queryStorageDebtDtlById(Integer debtId);

    /**
     * 查询报损单详情
     */
    BasePo queryStorageDebtById(Integer id);

    /**
     * 报损单明细分页查询
     *
     * @param params
     * @param pageData
     * @return
     */
    DTablePageModel queryStorageDebtDtlPage(Map<String, Object> params, PageData pageData);

    /**
     * 添加或修改处理明细
     *
     * @param basePo
     * @return
     */
    BasePo saveOrUpdateDebtDtl(BasePo basePo);

    /**
     * 根据报损单ID，删除对应报损单明细
     *
     * @param debtId
     * @return
     */
    int deleteStorageDebtById(Integer debtId);

    /**
     * 删除报损单
     */
    BasePo deleteStorageDebt(Integer id);

    /**
     * 查询报损类型
     */
    List<SysDict> getAllDebtType(String str);

    /**
     * 新增或修改出库单
     */
    BasePo saveOrUpdateStorageDebt(BasePo basePo);

    /**
     * 查询配送是否存在出库单
     **/
    List queryDispatchingPlanList(Integer outOrderId);

    /*****************************************
     * 调拨单
     ******************************************/
    DTablePageModel queryTransferOrderPage(Map<String, Object> params, PageData pageData);

    /**
     * 新增调拨单
     **/
    StorageAllot saveOrUpdateAllotOrder(StorageAllot storageAllot);

    /**
     * 新增调拨单明细
     **/
    StorageAllotDtl saveOrUpdateAllotDtl(StorageAllotDtl list);

    /**
     * 根据ID查询调拨单
     **/
    StorageAllot queryAllotOrderById(Integer id);

    /**
     * 根据ID查询调拨单明细
     **/
    List<GoodsInfoDto> queryStorageAllotDtById(Integer id);

    /**
     * 删除调拨单
     **/
    StorageAllot deleteStorageAllot(StorageAllot storageAllot);

    /**
     * 根据ID查询调拨单明细
     **/
    List<StorageAllotDtl> queryStorageAllotDtlById(Integer id);

    /**
     * 删除调拨单明细
     **/
    StorageAllotDtl deleteStorageAllotDtl(StorageAllotDtl storageAllotDtl);

    /**
     * 查询调拨单分页明细
     **/
    DTablePageModel queryGoodsInfoPage(Map<String, Object> params, PageData pageData);


    /**
     * 根据商品ID查询库存
     **/
    StorageInventory queryStorageInventory(Integer id);


    /**
     * 根据所属区域查询仓库ID
     */
    List<StorageWarehouse> queryStorageWarehouseById(Integer orgId);

    /**
     * 根据商品ID和所属仓库查询库存
     **/
    StorageInventory queryStorageInventoryDt(Integer goodsId, Integer wareHouseId);

    /**
     * 根据ID查询仓库商品分页信息
     **/
    DTablePageModel queryStorageGoodsInfo(Map<String, Object> params, PageData pageData);

}
