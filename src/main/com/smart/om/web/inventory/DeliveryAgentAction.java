package com.smart.om.web.inventory;

import com.smart.om.biz.inventory.InventoryHandler;
import com.smart.om.persist.StorageDeliveryAgent;
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
@Namespace("/view/inventory/deliveryAgent")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class DeliveryAgentAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(DeliveryAgentAction.class);
    @Resource
    private InventoryHandler inventoryHandler;
    private StorageDeliveryAgent storageDeliveryAgent;

    /**
     * 出库单分页
     */
    @Action(value = "queryDeliveryAgentPage")
    public void queryDeliveryAgentPage() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String deliveryOrderId = this.getRequestParm().getParameter("deliveryOrderId");
            String dictServStation = this.getRequestParm().getParameter("dictServStation");//查询公司ID

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            params.put("deliveryOrderId", deliveryOrderId);
            params.put("dictServStation", dictServStation);
            DTablePageModel dtPageModel = inventoryHandler.queryDeliveryAgentPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 修改出库单状态
//     */
//    @Action(value = "modifyIsAgentStatus", results = {
//            @Result(name = SUCCESS, location = "/view/inventory/outDetailList.jsp"),
//            @Result(name = ERROR, location = "/view/error.jsp")
//    })
//    public String modifyIsAgentStatus() {
//        String result = SUCCESS;
//        logger.info("修改状态");
//        try {
//            Integer deliveryOrderId = Integer.valueOf(this.getRequestParm().getParameter("deliveryOrderId"));
//            logger.info(deliveryOrderId);
//            StorageDeliveryOrder storageDeliveryOrder = (StorageDeliveryOrder) inventoryHandler.queryDeliveryOrderById(deliveryOrderId);
//            if ("0".equals(storageDeliveryOrder.getIsClean())) {
//                storageDeliveryOrder.setIsClean("1");
//            } else if ("1".equals(storageDeliveryOrder.getIsClean())) {
//                storageDeliveryOrder.setIsClean("2");
//            }
//            logger.info(storageDeliveryOrder.isNewer());
//            inventoryHandler.saveOrUpdateDeliveryOrder(storageDeliveryOrder);
//        } catch (Exception e) {
//            e.printStackTrace();
//            this.getRequest().put("where", " 查询所有数据字典");
//            this.getRequest().put("errors", "系统正忙，请联系管理员");
//            result = ERROR;
//        }
//        return result;
//    }

    /**
     * 跳转至分配配送页
     */
    @Action(value = "queryDispatchingList", results = {
            @Result(name = SUCCESS, location = "/view/inventory/dispatchingList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String queryDispatchingList() {
        String result = SUCCESS;
        try {
            Integer deliveryOrderId = Integer.valueOf(this.getRequestParm().getParameter("deliveryOrderId"));
            getRequest().put("deliveryOrderId", deliveryOrderId);
        } catch (Exception e) {
            e.printStackTrace();
            result = ERROR;
        }
        return result;
    }

    /**
     * 出库单ID查询对应配送订单
     */
    @Action(value = "queryDispatchingListById")
    public void queryDispatchingListById() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String deliveryOrderId = this.getRequestParm().getParameter("deliveryOrderId");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            params.put("deliveryOrderId", deliveryOrderId);
            DTablePageModel dtPageModel = inventoryHandler.queryDispatchingListById(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据订单ID查询对应子订单
     */
    @Action(value = "querySubOrderListById")
    public void querySubOrderListById() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String orderId = this.getRequestParm().getParameter("orderId");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            params.put("orderId", orderId);
            DTablePageModel dtPageModel = inventoryHandler.querySubOrderListById(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转至分配配送页
     */
    @Action(value = "queryDispatchingSubList", results = {
            @Result(name = SUCCESS, location = "/view/inventory/dispatchingSubList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String queryDispatchingSubList() {
        String result = SUCCESS;
        try {
            Integer orderId = Integer.valueOf(this.getRequestParm().getParameter("orderId"));
            getRequest().put("orderId", orderId);
        } catch (Exception e) {
            e.printStackTrace();
            result = ERROR;
        }
        return result;
    }


    /**
     * 查询单个订单下的商品总数分组信息
     */
    @Action(value = "queryOrderAgentDet")
    public void queryOrderAgentDet() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String orderId = this.getRequestParm().getParameter("orderId");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            params.put("orderId", orderId);
            DTablePageModel dtPageModel = inventoryHandler.queryOrderAgentDet(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 跳转至设备分组页
     */
    @Action(value = "queryDeviceByDistrictId", results = {
            @Result(name = SUCCESS, location = "/view/inventory/deviceInfoGroup.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String queryDeviceByDistrictId() {
        String result = SUCCESS;
        try {
            Integer districtId = Integer.valueOf(this.getRequestParm().getParameter("districtId"));
            Integer orderId = Integer.valueOf(this.getRequestParm().getParameter("orderId"));
            getRequest().put("districtId", districtId);
            getRequest().put("orderId", orderId);
        } catch (Exception e) {
            e.printStackTrace();
            result = ERROR;
        }
        return result;
    }


    /**
     * 查询设备分组
     */
    @Action(value = "queryDeviceGroup")
    public void queryDeviceGroup() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String districtId = this.getRequestParm().getParameter("districtId");
            String orderId = this.getRequestParm().getParameter("orderId");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            params.put("districtId", districtId);
            params.put("orderId", orderId);
            DTablePageModel dtPageModel = inventoryHandler.queryDeviceGroup(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 查询单个订单下的商品总数分组信息
     */
    @Action(value = "queryDeviceSumGoodsById")
    public void queryDeviceSumGoodsById() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String deviceId = this.getRequestParm().getParameter("deviceId");
            String orderId = this.getRequestParm().getParameter("orderId");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            params.put("deviceId", deviceId);
            params.put("orderId", orderId);
            DTablePageModel dtPageModel = inventoryHandler.queryDeviceSumGoodsById(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 统计单个商圈的商品数量
     */
    @Action(value = "querySumGoodsMotion")
    public void querySumGoodsMotion() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String districtId = this.getRequestParm().getParameter("districtId");
            String orderId = this.getRequestParm().getParameter("orderId");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            params.put("districtId", districtId);
            params.put("orderId", orderId);
            DTablePageModel dtPageModel = inventoryHandler.querySumGoodsMotion(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public StorageDeliveryAgent getStorageDeliveryAgent() {
        return storageDeliveryAgent;
    }

    public void setStorageDeliveryAgent(StorageDeliveryAgent storageDeliveryAgent) {
        this.storageDeliveryAgent = storageDeliveryAgent;
    }
}
