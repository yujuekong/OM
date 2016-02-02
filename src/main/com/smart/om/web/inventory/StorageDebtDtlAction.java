package com.smart.om.web.inventory;

import com.smart.om.biz.inventory.InventoryHandler;
import com.smart.om.persist.StorageDebtDtl;
import com.smart.om.util.DTablePageModel;
import com.smart.om.web.base.BaseAction;
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
 * Created by Administrator on 2015/10/21.
 */
@Namespace("/view/inventory/storageDebtDtl")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class StorageDebtDtlAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(StorageDebtDtlAction.class);
    @Resource
    private InventoryHandler inventoryHandler;
    private StorageDebtDtl storageDebtDtl;

    /**
     * 供应商品列表分页
     */
    @Action(value = "queryStorageDebtDtlPage")
    public void queryStorageDebtDtlPage() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String debtId = this.getRequestParm().getParameter("debtId");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            params.put("debtId", debtId);
            DTablePageModel dtPageModel = inventoryHandler.queryStorageDebtDtlPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StorageDebtDtl getStorageDebtDtl() {
        return storageDebtDtl;
    }

    public void setStorageDebtDtl(StorageDebtDtl storageDebtDtl) {
        this.storageDebtDtl = storageDebtDtl;
    }
}
