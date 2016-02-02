package com.smart.om.web.inventory;

import com.smart.om.biz.inventory.InventoryHandler;
import com.smart.om.persist.StorageWarehousingDtl;
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
@Namespace("/view/inventory/InListDetail")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class InListDetailAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(InListDetailAction.class);
    @Resource
    private InventoryHandler inventoryHandler;

    //入库单
    private StorageWarehousingDtl storageWarehousingDtl;


    /**
     * 入库单明细列表分页
     */
    @Action(value = "queryEntryDetailPage")
    public void queryEntryDetailPage() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String warehousingId = this.getRequestParm().getParameter("warehousingId");
            String showAll = this.getRequestParm().getParameter("showAll");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            params.put("warehousingId", warehousingId);
            params.put("showAll", showAll);

            DTablePageModel dtPageModel = inventoryHandler.queryEntryDetailPage(params, super.getPageData());
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
    @Action(value = "queryEntryDetailById", results = {
            @Result(name = SUCCESS, location = "/view/inventory/inDetailListDtl.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String queryEntryDetailById() {
        String result = SUCCESS;
        try {
            Integer entryDetailId = Integer.valueOf(this.getRequestParm().getParameter("entryDetailId"));
            if (entryDetailId != null) {
                StorageWarehousingDtl storageWarehousingDtl = (StorageWarehousingDtl) inventoryHandler.queryEntryDetailById(entryDetailId);
                getRequest().put("storageWarehousingDtl", storageWarehousingDtl);
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
     * 新增或修改入库单详情跳转
     */
    @Action(value = "preAddOrModifyEntryDetail", results = {
            @Result(name = SUCCESS, location = "/view/inventory/inDetailAddOrUpdata.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String preAddOrModifyEntryDetail() {
        String result = SUCCESS;
        try {
            String entryDetailIdStr = this.getRequestParm().getParameter("entryDetailId");
            if (StringUtils.isNotBlank(entryDetailIdStr)) {
                Integer entryDetailId = Integer.valueOf(this.getRequestParm().getParameter("entryDetailId"));
                StorageWarehousingDtl storageWarehousingDtl = (StorageWarehousingDtl) inventoryHandler.queryEntryDetailById(entryDetailId);
                getRequest().put("storageWarehousingDtl", storageWarehousingDtl);
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
     * 新增和修改入库单详情信息
     */
    @Action(value = "saveOrUpdateEntryDetail", results = {
            @Result(name = SUCCESS, location = "/view/inventory/inDetailList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String saveOrUpdateEntryDetail() {
        String result = SUCCESS;
        try {
            Integer entryDetailId = storageWarehousingDtl.getWarehousingDtlId();
            Integer warehousingEntryId = storageWarehousingDtl.getWarehousingEntryId();
            getRequest().put("warehousingId", warehousingEntryId);

            if (entryDetailId == null) {
                storageWarehousingDtl.setNewer(true);
            }
            inventoryHandler.saveOrUpdateWarehousing(storageWarehousingDtl);
        } catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }


    /**
     * 删除入库单详情
     */
    @Action(value = "deleteEntryDetail", results = {
            @Result(name = SUCCESS, location = "/view/inventory/inDetailList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String deleteEntryDetail() {
        String result = SUCCESS;
        try {
            Integer entryDetailId = Integer.valueOf(this.getRequestParm().getParameter("entryDetailId"));
            inventoryHandler.deleteEntryDetail(entryDetailId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }


    public StorageWarehousingDtl getStorageWarehousingDtl() {
        return storageWarehousingDtl;
    }

    public void setStorageWarehousingDtl(StorageWarehousingDtl storageWarehousingDtl) {
        this.storageWarehousingDtl = storageWarehousingDtl;
    }
}

