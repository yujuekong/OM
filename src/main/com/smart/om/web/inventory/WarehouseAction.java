package com.smart.om.web.inventory;

import com.smart.om.biz.inventory.InventoryHandler;
import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.persist.StorageInventory;
import com.smart.om.persist.StorageWarehouse;
import com.smart.om.persist.SysDict;
import com.smart.om.util.*;
import com.smart.om.web.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/16.
 */
@Namespace("/view/inventory/warehouse")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class WarehouseAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(WarehouseAction.class);
    @Resource
    private InventoryHandler inventoryHandler;
    @Resource
    private SysAssistHandler sysAssistHandler;

    private StorageWarehouse storageWarehouse;
    private List<StorageInventory> storageInventoryList;

    /**
     * 仓库列表分页
     */
    @Action(value = "queryWarehousePage")
    public void queryWarehousePage() {
        try {
            SysDict sysDict = sysAssistHandler.queryDictByPcode("AL_POSITION");
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String nodeId = this.getRequestParm().getParameter("id");
            String level = this.getRequestParm().getParameter("level");
            String orgId = this.getRequestParm().getParameter("orgId");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            params.put("orgId", orgId);
            if (StringUtils.isNotBlank(level)) {
                if (level.equals("0")) {//点击的是根节点

                } else if (level.equals("1")) {//点击的是区域
                    params.put("dictRegionId", nodeId);
                } else if (level.equals("2")) {//点击的是省份
                    params.put("dictProviceId", nodeId);
                } else if (level.equals("3")) {//点击的是分公司
                    params.put("dictOrgId", nodeId);
                }
            }
            DTablePageModel dtPageModel = inventoryHandler.queryWarehousePage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定仓库详情
     */
    @Action(value = "queryWarehouseById", results = {
            @Result(name = SUCCESS, location = "/view/inventory/warehouseDetail.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String queryWarehouseById() {
        String result = SUCCESS;
        try {
            Integer storageWarehouseId = Integer.valueOf(this.getRequestParm().getParameter("warehouseId"));
            if (storageWarehouseId != null) {
                StorageWarehouse storageWarehouse = (StorageWarehouse) inventoryHandler.queryWarehouseById(storageWarehouseId);
                getRequest().put("storageWarehouse", storageWarehouse);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 新增和修改仓库页面跳转
     */
    @Action(value = "preAddOrModifystorageWarehouse", results = {
            @Result(name = SUCCESS, location = "/view/inventory/warehouseAddOrUpdata.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")

    })
    public String preAddOrModifystorageWarehouse() {
        String result = SUCCESS;
        try {
            String storageWarehouseId = this.getRequestParm().getParameter("warehouseId");
            if (StringUtils.isNotBlank(storageWarehouseId)) {
                Integer warehouseId = Integer.valueOf(this.getRequestParm().getParameter("warehouseId"));
                StorageWarehouse storageWarehouse = (StorageWarehouse) inventoryHandler.queryWarehouseById(warehouseId);
                List<StorageInventory> storageInventoryList = inventoryHandler.queryStorageInventoryById(warehouseId);
                getRequest().put("storageInventoryList", storageInventoryList);
//                logger.info(storageInventoryList.get(0).getGoodsInfo().getSysDict().getDictName());

                getRequest().put("storageWarehouse", storageWarehouse);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 新增和修改仓库信息
     */
    @Action(value = "saveOrUpdateWarehouse", results = {
            @Result(name = SUCCESS, location = "/view/inventory/warehouseList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String saveOrUpdateWarehouse() {
        String result = SUCCESS;
        try {
            Integer warehouseId = storageWarehouse.getWarehouseId();
            if (warehouseId == null) {
                storageWarehouse.setNewer(true);
                storageWarehouse.setWarehouseStatus(Const.IS_VALID_TRUE);
                storageWarehouse.setWarehouseNo(DateUtil.getNo("C"));
            }
            storageWarehouse.setCreateDate(DateUtil.getDateY_M_D());
            StorageWarehouse warehouse = (StorageWarehouse) inventoryHandler.saveOrUpdateWarehouse(storageWarehouse);
            warehouseId = warehouse.getWarehouseId();
            if (storageInventoryList != null) {
                inventoryHandler.deleteStorageInventoryById(warehouseId);
                for (StorageInventory storageInventory : storageInventoryList) {
                    storageInventory.setNewer(true);
                    storageInventory.setWarehouseId(warehouseId);
                    inventoryHandler.saveOrUpdateStorageInventory(storageInventory);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 删除仓库
     */
    @Action(value = "deleteWarehouse", results = {
            @Result(name = SUCCESS, location = "/view/inventory/warehouseList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String deleteWarehouse() {
        String result = SUCCESS;
        try {
            Integer typeId = Integer.valueOf(this.getRequestParm().getParameter("warehouseId"));
            inventoryHandler.deleteWarehouse(typeId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }


    /**
     * 根据上级字典Code获得数据字典数据
     */
    @Action(value = "getMulSubDictDataByPcode")
    public void getMulSubDictDataByPcode() {
        try {
            String dictPcode = this.getRequestParm().getParameter("dictPcode");
            String dictLevel = this.getRequestParm().getParameter("dictLevel");
            SysDict sysDict = sysAssistHandler.queryDictByPcode(dictPcode);
            List<SysDict> dictLst = sysAssistHandler.queryMulSubDictByPcode(dictPcode, dictLevel);
            ZTreeNode root = new ZTreeNode();
            root.setIcon("../../images/diy/3.png");
            root.setName("地区");
            root.setOpen(true);
            root.setId(String.valueOf(sysDict.getDictId()));
            String jsonData = ZTreeUtil.toJson(root, dictLst, "dictId", "dictPid", "dictCode", "dictName");
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改仓库状态
     */
    @Action(value = "modifyHouseStatus", results = {
            @Result(name = SUCCESS, location = "/view/inventory/warehouseList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String modifyHouseStatus() {
        String result = SUCCESS;
        try {
            Integer warehouseId = Integer.valueOf(this.getRequestParm().getParameter("warehouseId"));
            StorageWarehouse storageWarehouse = (StorageWarehouse) inventoryHandler.queryWarehouseById(warehouseId);
            if ("0".equals(storageWarehouse.getWarehouseStatus())) {
                storageWarehouse.setWarehouseStatus("1");
            } else {
                storageWarehouse.setWarehouseStatus("0");
            }
            inventoryHandler.saveOrUpdateWarehouse(storageWarehouse);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 判断仓库状态
     */
    @Action(value = "isInventory", results = {
            @Result(name = SUCCESS, location = "/view/inventory/warehouseList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public void isInventory() {
        Integer warehouseId = Integer.valueOf(this.getRequestParm().getParameter("warehouseId"));
        boolean isInventory = inventoryHandler.isInventory(warehouseId);
        try {
            String jsonData = JSONUtil.serialize(isInventory);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * set and get
     */
    public List<StorageInventory> getStorageInventoryList() {
        return storageInventoryList;
    }

    public void setStorageInventoryList(List<StorageInventory> storageInventoryList) {
        this.storageInventoryList = storageInventoryList;
    }

    public StorageWarehouse getStorageWarehouse() {
        return storageWarehouse;
    }

    public void setStorageWarehouse(StorageWarehouse storageWarehouse) {
        this.storageWarehouse = storageWarehouse;
    }
}
