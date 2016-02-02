package com.smart.om.web.inventory;

import com.smart.om.biz.inventory.InventoryHandler;
import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.dao.goods.GoodsInfoDAO;
import com.smart.om.dto.inventory.GoodsInfoDto;
import com.smart.om.persist.*;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.web.base.BaseAction;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调拨单Action
 *
 * @author CA
 */
@Namespace("/view/inventory/allotOrder")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class AllotOrderAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(AllotOrderAction.class);
    @Autowired
    private InventoryHandler inventoryHandler;
    @Autowired
    private SysFuncHandler sysFuncHandler;
    @Resource
    private SysAssistHandler sysAssistHandler;
    @Autowired
    private GoodsInfoDAO goodsInfoDAO;

    private StorageAllot storageAllot;

    private StorageAllotDtl storageAllotDtl;

    private List<StorageAllotDtl> storageAllotDt;

    /**
     * 查询调拨单分页信息
     */
    @Action(value = "queryTransferOrderPage")
    public void queryTransferOrderPage() {
        SysDict sysDict = sysAssistHandler.queryDictByPcode("AL_POSITION");
        SysUser user = (SysUser) this.getSession().get("account");
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");//查询关键字
            String startDate = this.getRequestParm().getParameter("startDate");//查询开始日期
            String endDate = this.getRequestParm().getParameter("endDate");//查询结束日期
            String allotOrderStatus = this.getRequestParm().getParameter("allotOrderStatus");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            if (StringUtils.isNotBlank(allotOrderStatus)) {
                params.put("allotOrderStatus", allotOrderStatus);
            }
            if (user.getOrgId() != null) {
                params.put("orgId", user.getOrgId());
            }
            params.put("keyword", keyword);
            params.put("startDate", startDate);
            params.put("endDate", endDate);
            DTablePageModel dtPageModel = inventoryHandler.queryTransferOrderPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询调拨单明细分页信息
     */
    @Action(value = "queryGoodsInfoPage")
    public void queryGoodsInfoPage() {
        try {
            String allotId = this.getRequestParm().getParameter("allotId");
            String allotStatus = this.getRequestParm().getParameter("allotStatus");
            Map<String, Object> params = new HashMap<String, Object>();
            if (StringUtils.isNotBlank(allotId)) {
                params.put("allotId", allotId);
            }
            if (StringUtils.isNotBlank(allotId)) {
                params.put("allotStatus", allotStatus);
            }
            DTablePageModel dtPageModel = inventoryHandler.queryGoodsInfoPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据仓库ID查询商品分页信息
     */
    @Action(value = "queryStorageGoodsPage")
    public void queryStorageGoodsPage() {
        try {
            String warehouseId = this.getRequestParm().getParameter("warehouseId");
            String keyword = this.getRequestParm().getParameter("keyword");
            Map<String, Object> params = new HashMap<String, Object>();
            if (StringUtils.isNotBlank(warehouseId)) {
                params.put("warehouseId", warehouseId);
            }
            if (StringUtils.isNotBlank(keyword)) {
                params.put("keyword", keyword);
            }
            DTablePageModel dtPageModel = inventoryHandler.queryStorageGoodsInfo(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增调拨单
     *
     * @return
     */
    @Action(value = "saveOrUpdateAllotOrder", results = {
            @Result(name = SUCCESS, location = "/view/inventory/allotOrderList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String saveOrUpdateAllotOrder() {
        try {
            if (storageAllot.getAllotId() == null) {
                SysUser sysUser = (SysUser) this.getHttpSession().getAttribute("account");
                storageAllot.setAllotStatus(Const.IS_VALID_FALSE);
                storageAllot.setCreateUser(sysUser.getUserId());
                storageAllot.setCreateDate(DateUtil.getDateY_M_D());
                storageAllot.setAllotNo(DateUtil.getNo("D"));
                storageAllot = inventoryHandler.saveOrUpdateAllotOrder(storageAllot);
                if (storageAllotDt.size() > 0) {
                    for (StorageAllotDtl storageAllotDtl : storageAllotDt) {
                        storageAllotDtl.setNewer(true);
                        storageAllotDtl.setAllotId(storageAllot.getAllotId());
                        inventoryHandler.saveOrUpdateAllotDtl(storageAllotDtl);
                    }
                }

            } else {
                StorageAllot storageAllotNew = inventoryHandler.saveOrUpdateAllotOrder(storageAllot);
                if (storageAllotDt.size() > 0) {
                    List<StorageAllotDtl> list = inventoryHandler.queryStorageAllotDtlById(storageAllot.getAllotId());
                    for (StorageAllotDtl storageAllotDtl : list) {
                        inventoryHandler.deleteStorageAllotDtl(storageAllotDtl);
                    }
                    for (StorageAllotDtl storageAllotDtl : storageAllotDt) {
                        storageAllotDtl.setNewer(true);
                        storageAllotDtl.setAllotId(storageAllotNew.getAllotId());
                        inventoryHandler.saveOrUpdateAllotDtl(storageAllotDtl);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("errors", "系统异常，请联系管理员...");
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 修改和新增调拨单页面跳转
     */
    @Action(value = "updateOrModifyAllotOrder", results = {
            @Result(name = SUCCESS, location = "/view/inventory/allotOrderAdd.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String updateOrModifyAllotOrder() {
        try {
            String allotId = this.getRequestParm().getParameter("allotId");
            if (StringUtils.isNotBlank(allotId)) {
                Integer allotOrderId = Integer.valueOf(allotId);
                StorageAllot storageAllot = inventoryHandler.queryAllotOrderById(allotOrderId);
                StorageWarehouse startWarehouse = (StorageWarehouse) inventoryHandler.queryWarehouseById(storageAllot.getStartWarehouse());
                StorageWarehouse endWarehouse = (StorageWarehouse) inventoryHandler.queryWarehouseById(storageAllot.getEndWarehouse());
                List<GoodsInfoDto> storageAllotDt = inventoryHandler.queryStorageAllotDtById(allotOrderId);
                this.getRequest().put("storageAllot", storageAllot);
                this.getRequest().put("startWarehouse", startWarehouse);
                this.getRequest().put("endWarehouse", endWarehouse);
                this.getRequest().put("storageAllotDt", storageAllotDt);
                if (storageAllotDt.size() > 0) {
                    this.getRequest().put("storageWarehousingDtls", "0");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("errors", "系统异常，请联系管理员...");
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 查询调拨单详情
     *
     * @return
     */
    @Action(value = "queryAllotOrderDt", results = {
            @Result(name = SUCCESS, location = "/view/inventory/allotOrderDetail.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String queryAllotOrderDt() {
        try {
            String allotOrderId = this.getRequestParm().getParameter("allotId");
            if (StringUtils.isNotBlank(allotOrderId)) {
                Integer allotId = Integer.valueOf(allotOrderId);
                StorageAllot storageAllot = inventoryHandler.queryAllotOrderById(allotId);
                StorageWarehouse startWarehouse = (StorageWarehouse) inventoryHandler.queryWarehouseById(storageAllot.getStartWarehouse());
                StorageWarehouse endWarehouse = (StorageWarehouse) inventoryHandler.queryWarehouseById(storageAllot.getEndWarehouse());
                if (storageAllot.getAuditingUser() != null) {
                    SysUser auditingUser = sysFuncHandler.querySysUserById(storageAllot.getAuditingUser());
                    this.getRequest().put("auditingUser", auditingUser);
                }
                if (storageAllot.getCheckUser() != null) {
                    SysUser checkUser = sysFuncHandler.querySysUserById(storageAllot.getCheckUser());
                    this.getRequest().put("checkUser", checkUser);
                }
                SysUser createUser = sysFuncHandler.querySysUserById(storageAllot.getCreateUser());
                this.getRequest().put("storageAllot", storageAllot);
                this.getRequest().put("startWarehouse", startWarehouse);
                this.getRequest().put("endWarehouse", endWarehouse);
                this.getRequest().put("createUser", createUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("errors", "系统异常，请联系管理员...");
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 删除调拨单
     *
     * @return
     */
    @Action(value = "deleteAllotOrder", results = {
            @Result(name = SUCCESS, location = "/view/inventory/allotOrderList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String deleteAllotOrder() {
        try {
            String allotOrderId = this.getRequestParm().getParameter("allotId");
            if (StringUtils.isNotBlank(allotOrderId)) {
                Integer allotId = Integer.valueOf(allotOrderId);
                StorageAllot storageAllot = inventoryHandler.queryAllotOrderById(allotId);
                List<StorageAllotDtl> storageAllotDt = inventoryHandler.queryStorageAllotDtlById(allotId);
                inventoryHandler.deleteStorageAllot(storageAllot);
                for (StorageAllotDtl storageAllotDtl : storageAllotDt) {
                    inventoryHandler.deleteStorageAllotDtl(storageAllotDtl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("errors", "系统异常，请联系管理员...");
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 审核调拨单
     *
     * @return
     */
    @Action(value = "changeAllotOrderStatus", results = {
            @Result(name = SUCCESS, location = "/view/inventory/allotOrderList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String changeAllotOrderStatus() {
        try {
            String allotOrderId = this.getRequestParm().getParameter("allotId");
            if (StringUtils.isNotBlank(allotOrderId)) {
                Integer allotId = Integer.valueOf(allotOrderId);
                StorageAllot storageAllot = inventoryHandler.queryAllotOrderById(allotId);
                if (storageAllot.getAllotStatus().equals(Const.IS_VALID_FALSE)) {
                    storageAllot.setAllotStatus(Const.IS_VALID_TRUE);
                    List<StorageAllotDtl> storageDtl = inventoryHandler.queryStorageAllotDtlById(allotId);
                    for (StorageAllotDtl storageAllotDtl : storageDtl) {
                        StorageInventory storageInventory = inventoryHandler.queryStorageInventoryDt(storageAllotDtl.getGoodsId(), storageAllot.getStartWarehouse());
                        if (storageInventory != null) {
                            if (storageInventory.getInventoryNumber() != null) {
                                if (storageInventory.getInventoryNumber() < storageAllotDtl.getExpectNumber()) {
                                    this.getRequest().put("errors", "库存不够，请检查库存...");
                                    return ERROR;
                                }
                            } else {
                                this.getRequest().put("errors", "库存不够，请检查库存...");
                                return ERROR;
                            }
                        } else {
                            this.getRequest().put("errors", "仓库不存在此商品，请核查...");
                            return ERROR;
                        }
                        //storageAllotDtl.setActualNumber(storageAllotDtl.getExpectNumber());
                        //inventoryHandler.saveOrUpdateAllotDtl(storageAllotDtl);
                    }
                }
                SysUser sysUser = (SysUser) this.getSession().get("account");
                storageAllot.setAuditingUser(sysUser.getUserId());
                storageAllot.setAuditingDate(DateUtil.getDateY_M_DH_M_S());

                //返回审核通过的调拨单
                StorageAllot sAllot = inventoryHandler.saveOrUpdateAllotOrder(storageAllot);
                //生成出库单
                StorageDeliveryOrder storageDeliveryOrder = new StorageDeliveryOrder();
                storageDeliveryOrder.setWarehouseId(sAllot.getStartWarehouse());
                storageDeliveryOrder.setDeliveryNo(DateUtil.getNo("C"));
                storageDeliveryOrder.setBizType(Const.OUT_BIZ_TYPE_ALLOT);
                storageDeliveryOrder.setUserId(sAllot.getCreateUser());
                storageDeliveryOrder.setCreateDate(DateUtil.getDateY_M_DH_M_S());
                storageDeliveryOrder.setDeliveryStatus("0");
                storageDeliveryOrder.setRequestDate(DateUtil.getDateY_M_DH_M_S());
                storageDeliveryOrder.setDeliveryHandover("0");
                storageDeliveryOrder.setAgentHandover("0");
                storageDeliveryOrder.setCleanStatus("2");
                storageDeliveryOrder.setRemark(sAllot.getAllotReason());
                storageDeliveryOrder.setAllotId(sAllot.getAllotId());
                storageDeliveryOrder.setNewer(true);

                //新增出库单反回对象
                StorageDeliveryOrder sOrder = (StorageDeliveryOrder) inventoryHandler.saveOrUpdateDeliveryOrder(storageDeliveryOrder);

                //生成出库单明细
                List<StorageAllotDtl> storageAllotDtlList = inventoryHandler.queryStorageAllotDtlById(sAllot.getAllotId());
                if (storageAllotDtlList != null) {
                    for (int i = 0; i < storageAllotDtlList.size(); i++) {
                        StorageAllotDtl sAllotDtl = storageAllotDtlList.get(i);
                        StorageDeliveryDtl sDeliveryDtl = new StorageDeliveryDtl();//新建出库单明细
                        sDeliveryDtl.setDeliveryOrderId(sOrder.getDeliveryOrderId());
                        sDeliveryDtl.setGoodsId(Integer.parseInt(sAllotDtl.getGoodsId().toString()));
                        sDeliveryDtl.setRequestCount(Double.parseDouble(sAllotDtl.getExpectNumber().toString()));
                        sDeliveryDtl.setNewer(true);
                        inventoryHandler.saveOrUpdateDebtDtl(sDeliveryDtl);
                    }
                }
                //根据刀片名称查询刀片ID
                GoodsInfo goodsInfo = goodsInfoDAO.getGoodsInfoByName("刀片");
                //生成入库单
                StorageWarehousingEntry storageWarehousingEntry = new StorageWarehousingEntry();
                //storageWarehousingEntry.setSellerId("");
                storageWarehousingEntry.setWarehouseId(sAllot.getEndWarehouse());
                storageWarehousingEntry.setWarehousingNo(DateUtil.getNo("R"));
                storageWarehousingEntry.setBizType(301);
                storageWarehousingEntry.setRemark(sAllot.getAllotReason());
                storageWarehousingEntry.setUserId(sAllot.getCreateUser());
                storageWarehousingEntry.setCreateDate(DateUtil.getDateY_M_D());
                storageWarehousingEntry.setWarehousingStatus("0");
                storageWarehousingEntry.setRequestDate(DateUtil.getDateY_M_D());
                storageWarehousingEntry.setAllotId(sAllot.getAllotId());
                storageWarehousingEntry.setNewer(true);
                StorageWarehousingEntry sWarehousingEntry = (StorageWarehousingEntry) inventoryHandler.saveOrUpdateWarehousing(storageWarehousingEntry);

                //生成入库单明细
                if (storageAllotDtlList != null) {
                    for (int i = 0; i < storageAllotDtlList.size(); i++) {
                        StorageAllotDtl sAllotDtl = storageAllotDtlList.get(i);
                        StorageWarehousingDtl storageWarehousingDtl = new StorageWarehousingDtl();//新建入库单明细
                        storageWarehousingDtl.setGoodsId(sAllotDtl.getGoodsId());
                        if (sAllotDtl.getGoodsId() == goodsInfo.getGoodsId()) {
                            storageWarehousingDtl.setRequestCount(Double.parseDouble(sAllotDtl.getExpectNumber().toString()) + Const.BLADE_NUMBER);
                        } else {
                            storageWarehousingDtl.setRequestCount(Double.parseDouble(sAllotDtl.getExpectNumber().toString()));
                        }
                        storageWarehousingDtl.setWarehousingEntryId(sWarehousingEntry.getWarehousingEntryId());
                        storageWarehousingDtl.setGoodsId(Integer.parseInt(sAllotDtl.getGoodsId().toString()));
                        storageWarehousingDtl.setNewer(true);
                        inventoryHandler.saveOrUpdateEntryDetail(storageWarehousingDtl);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("errors", "审核失败，请联系管理员...");
            return ERROR;
        }
        return SUCCESS;
    }

    public StorageAllot getStorageAllot() {
        return storageAllot;
    }

    public void setStorageAllot(StorageAllot storageAllot) {
        this.storageAllot = storageAllot;
    }

    public StorageAllotDtl getStorageAllotDtl() {
        return storageAllotDtl;
    }

    public void setStorageAllotDtl(StorageAllotDtl storageAllotDtl) {
        this.storageAllotDtl = storageAllotDtl;
    }

    public List<StorageAllotDtl> getStorageAllotDt() {
        return storageAllotDt;
    }

    public void setStorageAllotDt(List<StorageAllotDtl> storageAllotDt) {
        this.storageAllotDt = storageAllotDt;
    }

}
