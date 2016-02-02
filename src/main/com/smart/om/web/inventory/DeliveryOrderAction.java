package com.smart.om.web.inventory;

import com.smart.om.biz.inventory.InventoryHandler;
import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.persist.*;
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
 * Created by Administrator on 2015/9/23.
 */
@Namespace("/view/inventory/deliveryOrder")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class DeliveryOrderAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(DeliveryOrderAction.class);
    @Resource
    private InventoryHandler inventoryHandler;
    @Resource
    private SysAssistHandler sysAssistHandler;

    private StorageDeliveryOrder storageDeliveryOrder;
    private List<StorageDeliveryDtl> storageDeliveryDtls;
    private List<StorageDeliveryAgent> StorageDeliveryAgents;

    /**
     * 出库单列表分页
     */
    @Action(value = "queryDeliveryOrderPage")
    public void queryDeliveryOrderPage() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String startDate = this.getRequestParm().getParameter("startDate");//查询开始日期
            String endDate = this.getRequestParm().getParameter("endDate");//查询结束日期
            String orgId = this.getRequestParm().getParameter("orgId");//查询公司ID
            String agent = this.getRequestParm().getParameter("agent");//是否是出库处理页查询
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            params.put("startDate", startDate);
            params.put("endDate", endDate);
            params.put("isClean", "0");//区分是否清洗查询排序
            params.put("orgId", orgId);
            if(agent!=null){
                params.put("agent", agent);
            }
            DTablePageModel dtPageModel = inventoryHandler.queryDeliveryOrderPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 出库单详情
     */
    @Action(value = "queryDeliveryOrderById", results = {
            @Result(name = SUCCESS, location = "/view/inventory/outDetail.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String queryDeliveryOrderById() {
        String result = SUCCESS;
        try {
            Integer deliveryOrderId = Integer.valueOf(this.getRequestParm().getParameter("deliveryOrderId"));
            if (deliveryOrderId != null) {
                StorageDeliveryOrder storageDeliveryOrder = (StorageDeliveryOrder) inventoryHandler.queryDeliveryOrderById(deliveryOrderId);
                getRequest().put("storageDeliveryOrder", storageDeliveryOrder);
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
     * 新增或修改出库跳转
     */
    @Action(value = "preAddOrModifyDeliveryOrder", results = {
            @Result(name = SUCCESS, location = "/view/inventory/outAddOrUpdata.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String preAddOrModifyDeliveryOrder() {
        String result = SUCCESS;
        try {
            String deliveryOrderIdStr = this.getRequestParm().getParameter("deliveryOrderId");
            if (StringUtils.isNotBlank(deliveryOrderIdStr)) {
                Integer deliveryOrderId = Integer.valueOf(deliveryOrderIdStr);
                StorageDeliveryOrder storageDeliveryOrder = (StorageDeliveryOrder) inventoryHandler.queryDeliveryOrderById(deliveryOrderId);
                List<StorageDeliveryDtl> StorageDeliveryAgents = inventoryHandler.queryDeliveryAgentByDeliveryId(deliveryOrderId);
                List<StorageDeliveryDtl> storageDeliveryDtls = inventoryHandler.queryDeliveryDtlByDeliveryId(deliveryOrderId);
                getRequest().put("storageDeliveryOrder", storageDeliveryOrder);
                getRequest().put("StorageDeliveryAgents", StorageDeliveryAgents);
                getRequest().put("storageDeliveryDtls", storageDeliveryDtls);
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
     * 新增或修改出库单
     */
    @Action(value = "saveOrUpdateDeliveryOrder", results = {
            @Result(name = SUCCESS, location = "/view/inventory/outList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String saveOrUpdateDeliveryOrder() {
        String result = SUCCESS;
        try {
            Integer deliveryOrderId = storageDeliveryOrder.getDeliveryOrderId();
            StorageDeliveryOrder deliveryOrder = (StorageDeliveryOrder) inventoryHandler.queryDeliveryOrderById(deliveryOrderId);
            if (deliveryOrderId == null) {
                storageDeliveryOrder.setNewer(true);
                storageDeliveryOrder.setDeliveryNo(DateUtil.getNo("C"));
                storageDeliveryOrder.setDeliveryStatus(Const.IS_STATUS_APPROVE);
                storageDeliveryOrder.setCreateDate(DateUtil.getDateY_M_DH_M_S());
                storageDeliveryOrder.setIsClean(Const.IS_CLEAN_NONEED);
                storageDeliveryOrder.setBizType(Const.OUT_BIZ_TYPE_ACTIVITY);
            } else {
                storageDeliveryOrder.setDeliveryStatus(deliveryOrder.getDeliveryStatus());
                storageDeliveryOrder.setCreateDate(deliveryOrder.getCreateDate());
                storageDeliveryOrder.setIsClean(deliveryOrder.getIsClean());
                storageDeliveryOrder.setCleanTime(deliveryOrder.getCleanTime());
            }
            deliveryOrder = (StorageDeliveryOrder) inventoryHandler.saveOrUpdateDeliveryOrder(storageDeliveryOrder);
            deliveryOrderId = deliveryOrder.getDeliveryOrderId();
            if (storageDeliveryDtls.size() > 0) {
                inventoryHandler.deleteDeliveryDtlByDeliveryId(deliveryOrderId);
                for (StorageDeliveryDtl storageDeliveryDtl : storageDeliveryDtls) {
                    if (storageDeliveryDtl instanceof StorageDeliveryDtl) {
                        storageDeliveryDtl.setNewer(true);
                        storageDeliveryDtl.setDeliveryOrderId(deliveryOrderId);
                        inventoryHandler.saveOrUpdateDeliveryDetail(storageDeliveryDtl);
//                        StorageDeliveryAgent storageDeliveryAgent = new StorageDeliveryAgent();
//                        storageDeliveryAgent.setDeliveryOrderId(storageDeliveryOrder.getDeliveryOrderId());
//                        storageDeliveryAgent.setGoodsId(storageDeliveryDtl.getGoodsId());
//                        storageDeliveryAgent.setAgentNumber(storageDeliveryDtl.getRequestCount());
//                        storageDeliveryAgent.setNewer(true);
//                        inventoryHandler.saveOrUpdateDeliveryAgent(storageDeliveryAgent);
                    }
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
     * 删除出库单
     */
    @Action(value = "deleteDeliveryOrder", results = {
            @Result(name = SUCCESS, location = "/view/inventory/outList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String deleteDeliveryOrder() {
        String result = SUCCESS;
        try {
            Integer deliveryOrderId = Integer.valueOf(this.getRequestParm().getParameter("deliveryOrderId"));
//            List list = inventoryHandler.queryDispatchingPlanList(deliveryOrderId);
//            logger.info(list.size());
//            if (list.size() > 0) {
//                result = SUCCESS;
//                this.getRequest().put("msg", "配送单中已存在此出库单，无法删除");
//            } else {
            inventoryHandler.deleteDeliveryOrder(deliveryOrderId);
//                inventoryHandler.deleteDeliveryDtlByDeliveryId(deliveryOrderId);
//            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 出库单明细
     */
    @Action(value = "deliveryOrderDet", results = {
            @Result(name = SUCCESS, location = "/view/inventory/outDetailList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String orderInDet() {
        try {
            String deliveryOrderId = this.getRequestParm().getParameter("deliveryOrderId");
            getRequest().put("deliveryOrderId", deliveryOrderId);
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("where", "查询维护信息");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
        }
        return SUCCESS;
    }

    /**
     * 修改出库单状态
     * @throws JSONException 
     * @throws IOException 
     */
    @Action(value = "modifyDeliveryStatus", results = {
            @Result(name = SUCCESS, location = "/view/inventory/outDetailList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public void modifyDeliveryStatus() throws JSONException, IOException {
        String result = SUCCESS;
        try {
            Integer deliveryOrderId = Integer.valueOf(this.getRequestParm().getParameter("deliveryOrderId"));
            StorageDeliveryOrder storageDeliveryOrder = (StorageDeliveryOrder) inventoryHandler.queryDeliveryOrderById(deliveryOrderId);
            
            List<StorageDeliveryDtl> list = inventoryHandler.queryDeliveryDtlByDeliveryId(deliveryOrderId);
            if(list!=null){
            	for (int i = 0; i < list.size(); i++) {
            		StorageDeliveryDtl storageDeliveryDtl = list.get(i);
            		StorageInventory storageInventory =inventoryHandler.queryStorageInventory(storageDeliveryOrder.getWarehouseId(),storageDeliveryDtl.getGoodsId());
        			if(null!=storageInventory.getInventoryNumber() && storageInventory.getInventoryNumber()!=0 ){
        				Double inventoryNumber = storageInventory.getInventoryNumber();//获取库存数量
        				if(inventoryNumber>=storageDeliveryDtl.getRequestCount()){
        				}else{
        					result=storageDeliveryDtl.getGoodsInfo().getGoodsName();
        					String jsonData = JSONUtil.serialize(result);
        					PrintWriter pw = super.getResponse().getWriter();
        					pw.write(jsonData);
        					pw.flush();
        					return;
        				}
        			}else{
        				result=storageDeliveryDtl.getGoodsInfo().getGoodsName();
    					String jsonData = JSONUtil.serialize(result);
    					PrintWriter pw = super.getResponse().getWriter();
    					pw.write(jsonData);
    					pw.flush();
    					return;
        			}
				}
            	
            	for (int i = 0; i < list.size(); i++) {
            		StorageDeliveryDtl storageDeliveryDtl = list.get(i);
            		StorageInventory storageInventory =inventoryHandler.queryStorageInventory(storageDeliveryOrder.getWarehouseId(),storageDeliveryDtl.getGoodsId());
					Double inventoryNumber = storageInventory.getInventoryNumber();//获取库存数量
					inventoryNumber = inventoryNumber-storageDeliveryDtl.getRequestCount();
					storageInventory.setInventoryNumber(inventoryNumber);
					inventoryHandler.saveOrUpdateStorageInventory(storageInventory);
            	}
            }
            
            if (Const.IS_STATUS_INIT.equals(storageDeliveryOrder.getDeliveryStatus())) {
                storageDeliveryOrder.setDeliveryStatus(Const.IS_STATUS_END);
            }
            if (Const.IS_STATUS_APPROVE.equals(storageDeliveryOrder.getDeliveryStatus())) {
                storageDeliveryOrder.setDeliveryStatus(Const.IS_STATUS_END);
            }
            inventoryHandler.saveOrUpdateDeliveryOrder(storageDeliveryOrder);
            
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
    }


    /**
     * 出库处理树
     */
    @Action(value = "getMulSubGoodsAgentDataByPcode")
    public void getMulSubGoodsAgentDataByPcode() {
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

    @Action(value = "getUserRoleName")
    public void getUserRoleName() {
        String userIdStr = this.getRequestParm().getParameter("userId");
        try {
            if (StringUtils.isNotBlank(userIdStr)) {
                Integer userId = Integer.parseInt(userIdStr);
                List userRoleName = inventoryHandler.getUserRoleName(userId);
                PrintWriter pw = super.getResponse().getWriter();
                if (userRoleName != null) {
                    String jsonData = JSONUtil.serialize(userRoleName);
                    pw.write(jsonData);
                } else {
                    pw.write("null");
                }
                pw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * set and get
     */
    public StorageDeliveryOrder getStorageDeliveryOrder() {
        return storageDeliveryOrder;
    }

    public void setStorageDeliveryOrder(StorageDeliveryOrder storageDeliveryOrder) {
        this.storageDeliveryOrder = storageDeliveryOrder;
    }

    public List<StorageDeliveryDtl> getStorageDeliveryDtls() {
        return storageDeliveryDtls;
    }

    public void setStorageDeliveryDtls(List<StorageDeliveryDtl> storageDeliveryDtls) {
        this.storageDeliveryDtls = storageDeliveryDtls;
    }

    public List<StorageDeliveryAgent> getStorageDeliveryAgents() {
        return StorageDeliveryAgents;
    }

    public void setStorageDeliveryAgents(List<StorageDeliveryAgent> StorageDeliveryAgents) {
        this.StorageDeliveryAgents = StorageDeliveryAgents;
    }
}
