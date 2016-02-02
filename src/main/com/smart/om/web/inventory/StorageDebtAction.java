package com.smart.om.web.inventory;

import com.smart.om.biz.inventory.InventoryHandler;
import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.persist.StorageDebt;
import com.smart.om.persist.StorageDebtDtl;
import com.smart.om.persist.SysDict;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.ZTreeNode;
import com.smart.om.util.ZTreeUtil;
import com.smart.om.web.base.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/21.
 */
@Namespace("/view/inventory/storageDebt")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class StorageDebtAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(WarehouseAction.class);
    @Resource
    private InventoryHandler inventoryHandler;
    @Resource
    private SysAssistHandler sysAssistHandler;
    private StorageDebt storageDebt;
    private List<StorageDebtDtl> storageDebtDtlList;

    /**
     * 报损单列表分页
     */
    @Action(value = "queryStorageDebtPage")
    public void queryStorageDebtPage() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String startDate = this.getRequestParm().getParameter("startDate");//查询开始日期
            String endDate = this.getRequestParm().getParameter("endDate");//查询结束日期
            String orgId = this.getRequestParm().getParameter("orgId");//查询公司ID
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            params.put("startDate", startDate);
            params.put("endDate", endDate);
           if(StringUtils.isNotBlank(orgId)){
               params.put("orgId", orgId);
           }
            DTablePageModel dtPageModel = inventoryHandler.queryStorageDebtPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定报损单详情
     */
    @Action(value = "queryStorageDebtById", results = {
            @Result(name = SUCCESS, location = "/view/inventory/storageDebtDtl.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String queryStorageDebtById() {
        String result = SUCCESS;
        try {
            Integer debtId = Integer.valueOf(this.getRequestParm().getParameter("debtId"));
            if (debtId != null) {
                StorageDebt storageDebt = (StorageDebt) inventoryHandler.queryStorageDebtById(debtId);
                getRequest().put("storageDebt", storageDebt);
                result = SUCCESS;
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
     * 新增或修改供应商跳转
     */
    @Action(value = "preAddOrModifyStorageDebt", results = {
            @Result(name = SUCCESS, location = "/view/inventory/storageDebtAddOrUpdata.jsp"),
    })
    public String preAddOrModifyStorageDebt() {
        String result = SUCCESS;
        try {
            String debtIdStr = this.getRequestParm().getParameter("debtId");
            if (StringUtils.isNotBlank(debtIdStr)) {
                Integer debtId = Integer.valueOf(this.getRequestParm().getParameter("debtId"));
                StorageDebt storageDebt = (StorageDebt) inventoryHandler.queryStorageDebtById(debtId);
                List<StorageDebtDtl> storageDebtDtlList = inventoryHandler.queryStorageDebtDtlById(debtId);
                getRequest().put("storageDebt", storageDebt);
                getRequest().put("storageDebtDtlList", storageDebtDtlList);
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
     * 新增和修改报损单信息
     */
    @Action(value = "saveOrUpdateStorageDebt", results = {
            @Result(name = SUCCESS, location = "/view/inventory/storageDebtList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String saveOrUpdateStorageDebt() {
        String result = SUCCESS;
        try {
            Integer orderInId = storageDebt.getDebtId();
            if (orderInId == null) {
                storageDebt.setNewer(true);
                String debtNo = DateUtil.getNo("S");
                storageDebt.setDebtNo(debtNo);
            }
            storageDebt = (StorageDebt) inventoryHandler.saveOrUpdateStorageDebt(storageDebt);
            Integer debtId = storageDebt.getDebtId();
            if (storageDebtDtlList.size() > 0) {
                inventoryHandler.deleteStorageDebtById(debtId);
                for (StorageDebtDtl storageDebtDtl : storageDebtDtlList) {
                    storageDebtDtl.setNewer(true);
                    storageDebtDtl.setDebtId(debtId);
                    inventoryHandler.saveOrUpdateDebtDtl(storageDebtDtl);
                }
            }
        } catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 报损类型树
     */
    @Action(value = "getMulSubGoodsDebtDataByPcode")
    public void getMulSubGoodsDebtDataByPcode() {
        try {
            try {
                String dictPcode = this.getRequestParm().getParameter("dictPcode");
                String dictLevel = this.getRequestParm().getParameter("dictLevel");
                SysDict sysDict = sysAssistHandler.queryDictByPcode(dictPcode);
                List<SysDict> dictLst = sysAssistHandler.queryMulSubDictByPcode(dictPcode, dictLevel);
                ZTreeNode root = new ZTreeNode();
                root.setName("处理");
                root.setOpen(true);
                root.setId(String.valueOf(sysDict.getDictId()));
                String jsonData = ZTreeUtil.toJson(root, dictLst, "dictId", "dictPid", "dictCode", "dictName");
                PrintWriter pw = super.getResponse().getWriter();
                pw.write(jsonData);
                pw.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除报损单详情
     */
    @Action(value = "deleteStorageDebt", results = {
            @Result(name = SUCCESS, location = "/view/inventory/storageDebtList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String deleteStorageDebt() {
        String result = SUCCESS;
        try {
            Integer debtId = Integer.valueOf(this.getRequestParm().getParameter("debtId"));
            inventoryHandler.deleteStorageDebt(debtId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 报损类型
     */
    @Action(value = "getAllDebtType")
    public void getAllDebtType() {
        String dictCode = this.getRequestParm().getParameter("dictCode");
        List<SysDict> sdList = inventoryHandler.getAllDebtType(dictCode);
        try {
            PrintWriter out = getResponse().getWriter();
            JsonConfig config = new JsonConfig();
            config.setJsonPropertyFilter(new PropertyFilter() {
                public boolean apply(Object arg0, String arg1, Object arg2) {
                    if (arg1.equals("sysDictPid")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            out.print(JSONArray.fromObject(sdList, config));
            out.flush();
            out.close();
        } catch (IOException e) {

        }
    }


    public List<StorageDebtDtl> getStorageDebtDtlList() {
        return storageDebtDtlList;
    }

    public void setStorageDebtDtlList(List<StorageDebtDtl> storageDebtDtlList) {
        this.storageDebtDtlList = storageDebtDtlList;
    }

    public StorageDebt getStorageDebt() {

        return storageDebt;
    }

    public void setStorageDebt(StorageDebt storageDebt) {
        this.storageDebt = storageDebt;
    }
}
