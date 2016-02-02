package com.smart.om.web.inventory;

import com.smart.om.biz.inventory.InventoryHandler;
import com.smart.om.persist.StorageDeliveryDtl;
import com.smart.om.util.DTablePageModel;
import com.smart.om.web.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/28.
 */
@Namespace("/view/inventory/deliveryDetail")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class DeliveryDetailAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(DeliveryDetailAction.class);
    @Resource
    private InventoryHandler inventoryHandler;
    private StorageDeliveryDtl storageDeliveryDtl;

    public StorageDeliveryDtl getStorageDeliveryDtl() {
        return storageDeliveryDtl;
    }

    public void setStorageDeliveryDtl(StorageDeliveryDtl storageDeliveryDtl) {
        this.storageDeliveryDtl = storageDeliveryDtl;
    }
    /**
     * 出库单明细列表分页
     */
    @Action(value = "queryDeliveryDetailPage")
    public void queryDeliveryDetailPage() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String deliveryOrderId = this.getRequestParm().getParameter("deliveryOrderId");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            params.put("deliveryOrderId", deliveryOrderId);
            DTablePageModel dtPageModel = inventoryHandler.queryDeliveryDetailPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 出库单明细详情
     */
    @Action(value = "queryDeliveryDetailById", results = {
            @Result(name = SUCCESS, location = "/view/inventory/outDetailListDtl.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String queryDeliveryDetailById(){
        String result = SUCCESS;
        try {
            Integer deliveryDtlId = Integer.valueOf(this.getRequestParm().getParameter("deliveryDtlId"));
            if (deliveryDtlId != null) {
                StorageDeliveryDtl storageDeliveryDtl = (StorageDeliveryDtl) inventoryHandler.queryDeliveryDetailById(deliveryDtlId);
                getRequest().put("storageDeliveryDtl", storageDeliveryDtl);
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
     * 新增或修改出库单详情跳转
     */
    @Action(value = "preAddOrModifyDeliveryDetail", results = {
            @Result(name = SUCCESS, location = "/view/inventory/inDetailAddOrUpdata.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String preAddOrModifyDeliveryDetail(){
        String result = SUCCESS;
        try {
            String deliveryDtlIdStr = this.getRequestParm().getParameter("deliveryDtlId");
            if (StringUtils.isNotBlank(deliveryDtlIdStr)) {
                Integer deliveryDtlId = Integer.valueOf(deliveryDtlIdStr);
                StorageDeliveryDtl storageDeliveryDtl = (StorageDeliveryDtl) inventoryHandler.queryDeliveryDetailById(deliveryDtlId);
                getRequest().put("storageDeliveryDtl", storageDeliveryDtl);
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
     * 新增和修改出库单信息
     */
    @Action(value = "saveOrUpdateEntryDetail", results = {
            @Result(name = SUCCESS, location = "/view/inventory/inDetailList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String saveOrUpdateEntryDetail() {
        String result = SUCCESS;
        try {
            Integer deliveryDtlId = storageDeliveryDtl.getDeliveryDtlId();
            if (deliveryDtlId == null) {
                storageDeliveryDtl.setNewer(true);
            }
            inventoryHandler.saveOrUpdateDeliveryDetail(storageDeliveryDtl);
        } catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 删除出库单详情
     */
    @Action(value = "deleteEntryDetail", results = {
            @Result(name = SUCCESS, location = "/view/inventory/inDetailList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String deleteEntryDetail(){
        String result = SUCCESS;
        try {
            Integer deliveryDtlId = Integer.valueOf(this.getRequestParm().getParameter("deliveryDtlId"));
            inventoryHandler.deleteDeliveryDetail(deliveryDtlId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }
}
