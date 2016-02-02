package com.smart.om.web.inventory;

import com.smart.om.biz.goods.GoodsHandler;
import com.smart.om.biz.inventory.InventoryHandler;
import com.smart.om.dao.base.BasePo;
import com.smart.om.persist.StorageWarehousingDtl;
import com.smart.om.persist.StorageWarehousingEntry;
import com.smart.om.persist.SysDict;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
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
 * Created by Administrator on 2015/9/21.
 */
@Namespace("/view/inventory/warehousing")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class WarehousingAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(WarehousingAction.class);
    @Resource
    private InventoryHandler inventoryHandler;
    @Resource
    private GoodsHandler goodsHandler;
    //    @Resource
//    private SysAssistHandler sysAssistHandler;
    private StorageWarehousingEntry warehousingEntry;
    private List<StorageWarehousingDtl> warehousingDtls;


    /**
     * 入库单列表分页
     */
    @Action(value = "queryWarehousingPage")
    public void queryWarehousePage() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");//查询结束日期
            String startDate = this.getRequestParm().getParameter("startDate");//查询开始日期
            String endDate = this.getRequestParm().getParameter("endDate");//查询结束日期
            String orgId = this.getRequestParm().getParameter("orgId");//查询公司ID
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            params.put("startDate", startDate);
            params.put("endDate", endDate);
            if (StringUtils.isNotBlank(orgId)) {
                params.put("orgId", orgId);
            }
            DTablePageModel dtPageModel = inventoryHandler.queryWarehousingPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定入库单详情
     */
    @Action(value = "queryWarehouingById", results = {
            @Result(name = SUCCESS, location = "/view/inventory/inDetail.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String queryWarehouingById() {
        String result = SUCCESS;
        try {
            Integer storageWarehousingId = Integer.valueOf(this.getRequestParm().getParameter("warehousingId"));
            if (storageWarehousingId != null) {
                StorageWarehousingEntry warehousingEntry = (StorageWarehousingEntry) inventoryHandler.queryWarehousingById(storageWarehousingId);
                getRequest().put("warehousingEntry", warehousingEntry);
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
     * 入库单页面跳转
     */
    @Action(value = "preAddOrModifyOrderIn", results = {
            @Result(name = SUCCESS, location = "/view/inventory/inAddOrUpdata.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String preAddOrModifyOrderIn() {
        String result = SUCCESS;
        try {
            String storageWarehouseIdStr = this.getRequestParm().getParameter("warehousingId");
            String count = this.getRequestParm().getParameter("count");
            logger.info(count);
            if (StringUtils.isNotBlank(storageWarehouseIdStr)) {
                Integer warehousingId = Integer.valueOf(storageWarehouseIdStr);
                StorageWarehousingEntry warehousingEntry = (StorageWarehousingEntry) inventoryHandler.queryWarehousingById(warehousingId);
                List<StorageWarehousingDtl> storageWarehousingDtls = inventoryHandler.queryWarehousingDtlByEntryId(warehousingId);
                getRequest().put("storageWarehousingDtls", storageWarehousingDtls);
                getRequest().put("warehousingEntry", warehousingEntry);
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
     * 新增和修改入库单信息
     */
    @Action(value = "saveOrUpdateOrderIn", results = {
            @Result(name = SUCCESS, location = "/view/inventory/inList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String saveOrUpdateOrderIn() {
        String result = SUCCESS;
        try {
            Integer orderInId = warehousingEntry.getWarehousingEntryId();
            if (orderInId == null) {
                warehousingEntry.setNewer(true);
                warehousingEntry.setWarehousingStatus(Const.IS_STATUS_INIT);
                String warehousingNo = DateUtil.getNo("R");
                warehousingEntry.setWarehousingNo(warehousingNo);
            }
            warehousingEntry.setCreateDate(DateUtil.getDateY_M_D());
            StorageWarehousingEntry storageWarehousingEntry = (StorageWarehousingEntry) inventoryHandler.saveOrUpdateWarehousing(warehousingEntry);
            Integer warehousingEntryId = storageWarehousingEntry.getWarehousingEntryId();
            //入库仓库ID
            Integer warehouseId = warehousingEntry.getWarehouseId();
            if (warehousingDtls.size() > 0) {
                inventoryHandler.deleteWarehousingDtlByEntryId(warehousingEntryId);
                for (StorageWarehousingDtl warehousingDtl : warehousingDtls) {
                    warehousingDtl.setNewer(true);
                    warehousingDtl.setWarehousingStatus("0");
                    warehousingDtl.setWarehousingEntryId(warehousingEntryId);
                    inventoryHandler.saveOrUpdateEntryDetail(warehousingDtl);
                    //商品ID
                    Integer goodsId = warehousingDtl.getGoodsId();
                    boolean isGoodsExistHouse = inventoryHandler.goodsIsExistHouse(warehousingDtl.getGoodsId(), warehouseId);

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
     * 删除入库单
     */
    @Action(value = "deleteOrderIn", results = {
            @Result(name = SUCCESS, location = "/view/inventory/inList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String deleteOrderIn() {
        String result = SUCCESS;
        try {
            Integer warehousingId = Integer.valueOf(this.getRequestParm().getParameter("warehousingId"));
            StorageWarehousingEntry warehousingEntry = (StorageWarehousingEntry) inventoryHandler.queryWarehousingById(warehousingId);
            if ("1".equals(warehousingEntry.getWarehousingStatus())) {
                this.getRequest().put("msg", "已入库订单无法删除");
            } else {
                inventoryHandler.deleteWarehousingById(warehousingId);
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
     * 入库单明细
     */
    @Action(value = "orderInDet", results = {
            @Result(name = SUCCESS, location = "/view/inventory/inDetailList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String orderInDet() {
        try {
            String warehousingId = this.getRequestParm().getParameter("warehousingId");
            getRequest().put("warehousingId", warehousingId);
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("where", "查询维护信息");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
        }
        return SUCCESS;
    }

    /**
     * 修改入库单状态
     */
    @Action(value = "modifyWarehousingStatus", results = {
            @Result(name = SUCCESS, location = "/view/inventory/inList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String modifyWarehousingStatus() {
        String result = SUCCESS;
        try {
            Integer warehousingId = Integer.valueOf(this.getRequestParm().getParameter("warehousingId"));
            StorageWarehousingEntry warehousingEntry = (StorageWarehousingEntry) inventoryHandler.queryWarehousingById(warehousingId);
            if (warehousingEntry.getWarehousingStatus().equals("0")) {
                warehousingEntry.setWarehousingStatus("1");
            }
            inventoryHandler.saveOrUpdateWarehousing(warehousingEntry);
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }


    /**
     * 查询入库业务类别
     */
    @Action(value = "getAllBizType")
    public void getAllBizType() {
        String dictCode = this.getRequestParm().getParameter("dictCode");
        List<SysDict> sdList = inventoryHandler.getAllBizType(dictCode);
        try {
            PrintWriter out = getResponse().getWriter();
            JsonConfig config = new JsonConfig();
            config.setJsonPropertyFilter(new PropertyFilter() {
                public boolean apply(Object arg0, String arg1, Object arg2) {
                    return arg1.equals("sysDictPid");
                }
            });
            out.print(JSONArray.fromObject(sdList, config));
            out.flush();
            out.close();
        } catch (IOException e) {

        }
    }


    /**
     * 修改实际入库数量
     */
    @Action(value = "modifyWarehousingCount", results = {
            @Result(name = SUCCESS, location = "/view/inventory/inList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String modifyWarehousingCount() {
        String result = SUCCESS;
        try {
            String count = this.getRequestParm().getParameter("count");
            Integer warehousingId = Integer.parseInt(this.getRequestParm().getParameter("warehousingId"));
            Integer warehouseId = Integer.parseInt(this.getRequestParm().getParameter("warehouseId"));
            JSONArray jsonArray = JSONArray.fromObject(count);
            for (int i = 0; i < jsonArray.size(); i++) {
                String warehousingCount = jsonArray.getJSONObject(i).getString("warehousingCount");
                String warehousingDtlId = jsonArray.getJSONObject(i).getString("warehousingDtlId");
                String goodsId = jsonArray.getJSONObject(i).getString("goodsId");
                if (StringUtils.isNotBlank(warehousingDtlId) && StringUtils.isNotBlank(warehousingCount)) {
                    inventoryHandler.modifyWarehousingCount(Integer.parseInt(warehousingDtlId), Double.parseDouble(warehousingCount), Integer.parseInt(goodsId), warehouseId);
                    StorageWarehousingEntry warehousingEntry = (StorageWarehousingEntry) inventoryHandler.queryWarehousingById(warehousingId);
                    inventoryHandler.queryWarehousingDtlById(Integer.parseInt(warehousingDtlId));
                    boolean allwarehousing = inventoryHandler.isAllwarehousing(warehousingId);
                    if (allwarehousing) {
                        warehousingEntry.setWarehousingStatus("1");
                        warehousingEntry.setWarehousingDate(DateUtil.getDateY_M_DH_M_SS());
                    }
                    inventoryHandler.saveOrUpdateWarehousing(warehousingEntry);
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
    /*set and get*/

    public List<StorageWarehousingDtl> getWarehousingDtls() {
        return warehousingDtls;
    }

    public void setWarehousingDtls(List<StorageWarehousingDtl> warehousingDtls) {
        this.warehousingDtls = warehousingDtls;
    }

    public StorageWarehousingEntry getWarehousingEntry() {
        return warehousingEntry;
    }

    public void setWarehousingEntry(StorageWarehousingEntry warehousingEntry) {
        this.warehousingEntry = warehousingEntry;
    }
}
