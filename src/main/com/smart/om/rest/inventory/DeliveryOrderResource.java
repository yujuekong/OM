package com.smart.om.rest.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.smart.om.biz.inventory.InventoryHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.StorageDeliveryDtl;
import com.smart.om.persist.StorageDeliveryOrder;
import com.smart.om.persist.StorageInventory;
import com.smart.om.persist.SysUser;
import com.smart.om.persist.SysUserOp;
import com.smart.om.rest.base.BaseResource;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.PageData;
import com.smart.om.util.Tools;

/**
 * 出库管理Rest接口
 * @author langyuk
 *
 */
@Component("DeliveryOrderResource")
public class DeliveryOrderResource extends BaseResource{
	private static final Logger logger = Logger.getLogger(DeliveryOrderResource.class);
	@Resource
	private InventoryHandler inventoryHandler;
	@Resource
	private SysFuncHandler sysFuncHandler;
    
    //查询登录用户出库单
  	public String queryDeliveryOrders(Map dataMap){
  		String result = "";
  		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
  		String userId = Tools.trim(dataMap.get("userId"));//登录用户	
  		String checkUserId = Tools.trim(dataMap.get("checkUserId"));//App用户
  		String orderDate = Tools.trim(dataMap.get("orderDate"));//出库日期
  		if(StringUtils.isBlank(orderDate)) {//如果参数没有出库日期，则查询当前日期
  			orderDate = DateUtil.getDateY_M_D();
  		}
  		String deliveryStatus = Tools.trim(dataMap.get("deliveryStatus"));//出库单状态
  		String pageSize = Tools.trim(dataMap.get("pageSize"));//每页显示条数
  		String currentPage = Tools.trim(dataMap.get("currentPage"));//当前页	
  		
  		try {
  			PageData pageData = this.getPageData(pageSize, currentPage);
  			Map<String, Object> params = new HashMap<String, Object>();
  			params.put("userId", userId);
  			if(!"".equals(checkUserId)  && null!=checkUserId){
  	  			SysUser user = sysFuncHandler.querySysUserById(Integer.parseInt(checkUserId));
  	  			if(user!=null){
	  	  			Integer orgId = user.getOrgId();
	  				if(null!=orgId && orgId!=0){
	  					params.put("orgId", orgId);		
	  				}
  	  			}
  	  		}
  			params.put("app", "app");
  			params.put("orderDate", orderDate);
  			params.put("deliveryStatus", deliveryStatus);
  			params.put("isClean", "0");//区分是否清洗查询排序
  			DTablePageModel pageModel = inventoryHandler.queryDeliveryOrderPage(params, pageData);
  			List<StorageDeliveryOrder> deliveryList = pageModel.getAaData();			
  			if(deliveryList != null){
  				for (StorageDeliveryOrder storageDeliveryOrder : deliveryList) {
  					Map<String, Object> dcMap = new HashMap<String, Object>();					
  					dcMap.put("deliveryOrderId", storageDeliveryOrder.getDeliveryOrderId());//清洗ID
  					dcMap.put("deliveryNo", storageDeliveryOrder.getDeliveryNo());//出库单编号			
  					dcMap.put("deliveryDate", storageDeliveryOrder.getDeliveryDate());//实际出库时间
  					dcMap.put("requestDate", storageDeliveryOrder.getRequestDate());//要求出库时间
  					dcMap.put("deliveryStatus", storageDeliveryOrder.getDeliveryStatus());//出库单状态
  					if(StringUtils.isNotBlank(storageDeliveryOrder.getDeliveryDate())){
						if(DateUtil.getTimeDiff(storageDeliveryOrder.getDeliveryDate(), storageDeliveryOrder.getRequestDate())){
							dcMap.put("isOverTime", Const.IS_DEL_TRUE);//超时
						}
						else{
							dcMap.put("isOverTime", Const.IS_DEL_FALSE);//未超时
						}
					}	
  					else{
  						if(!"1".equals(storageDeliveryOrder.getDeliveryStatus())){
  							if(DateUtil.getTimeDiff(DateUtil.getDateY_M_DH_M_S(),storageDeliveryOrder.getRequestDate())){
								dcMap.put("isOverTime", "2");//未完成超时
							}else{
								dcMap.put("isOverTime", null);//未完成
							}
  						}
  						
  					}
  					lstMap.add(dcMap);
				}		
  			}
  			result = this.toResultJsonSuccess(lstMap);
  		} catch (Exception e) {
  			e.printStackTrace();
  			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
  		}
  		return result;
  	}
  	
  	//查询出库单详情
  	public String queryDeliveryDtlByDeliveryId(Map dataMap){
  		String result = "";
  		Map<String, Object> weMap = new HashMap<String, Object>();		
  		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
  		String deliveryOrderId = Tools.trim(dataMap.get("deliveryOrderId"));//出库单ID	
  		try {
  			//查询出库基本信息
  			StorageDeliveryOrder deliveryOrder = (StorageDeliveryOrder)inventoryHandler.queryDeliveryOrderById(Integer.valueOf(deliveryOrderId));
  			weMap.put("deliveryOrderId", deliveryOrder.getDeliveryOrderId());//出库单ID
  			weMap.put("deliveryNo", deliveryOrder.getDeliveryNo());//出库单编号			
  			weMap.put("deliveryDate", deliveryOrder.getDeliveryDate());//实际出库时间
  			weMap.put("requestDate", deliveryOrder.getRequestDate());//要求出库时间
			//查询出库单明细
			List<StorageDeliveryDtl> deliveryDtlList = inventoryHandler.queryDeliveryDtlByDeliveryId(Integer.valueOf(deliveryOrderId));
			if(deliveryDtlList != null){
				for (StorageDeliveryDtl storageDeliveryDtl : deliveryDtlList) {
					Map<String, Object> wdMap = new HashMap<String, Object>();					
					wdMap.put("deliveryDtlId", storageDeliveryDtl.getDeliveryDtlId());//明细ID
					wdMap.put("goodsId", storageDeliveryDtl.getGoodsInfo().getGoodsId());//出库商品ID
					wdMap.put("goodsName", storageDeliveryDtl.getGoodsInfo().getGoodsName());//出库商品
					wdMap.put("requestCount", storageDeliveryDtl.getRequestCount());//订单数量
					wdMap.put("deliveryCount", storageDeliveryDtl.getDeliveryCount());//出库商品数量
  					lstMap.add(wdMap);
				}
			}
			weMap.put("deliveryDtlList", lstMap);//入库单明细
  			result = this.toResultJsonSuccess(weMap);
  		} catch (Exception e) {
  			e.printStackTrace();
  			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
  		}
  		return result;
  	}
  	
  	/**
	 * 提交出库单
	 * 1、修改出库订单状态和实际出库日期
	 * 2、修改商品明细的实际出库数量
	 */
	public String submitDelivery(String data){
		try {
			JSONObject dataObject=JSONObject.fromObject(data);
			String deliveryOrderId = dataObject.getString("deliveryOrderId");
			String checkUserId = dataObject.getString("checkUserId");
			String deliveryDtls = dataObject.containsKey("deliveryDtls")?dataObject.getString("deliveryDtls"):null;	
			
			StorageDeliveryOrder deliveryOrder = (StorageDeliveryOrder)inventoryHandler.queryDeliveryOrderById(Integer.valueOf(deliveryOrderId));
			if(deliveryOrder == null) {
				return this.toFailTipJson("该订单未找到！");
			}
			if(Const.IS_STATUS_INIT.equals(deliveryOrder.getDeliveryStatus())) {
				if(StringUtils.isNotEmpty(deliveryDtls)) {
					//解析出库信息的JSON串，循环修改订单明细的实际出库数量
					JSONArray orderList = JSONArray.fromObject(deliveryDtls);
					for(Object obj:orderList){
						JSONObject jsonObject=JSONObject.fromObject(obj);
						String deliveryDtlId = jsonObject.getString("deliveryDtlId");
						String goodsNumber = jsonObject.getString("goodsNumber");
						StorageDeliveryDtl storageWarehousingDtl = (StorageDeliveryDtl)inventoryHandler.queryDeliveryDtlById(Integer.valueOf(deliveryDtlId));
						
						//根据出库ID查询出库单信息
						StorageInventory storageInventory =inventoryHandler.queryStorageInventory(deliveryOrder.getWarehouseId(),storageWarehousingDtl.getGoodsId());
						if(null!=storageInventory.getInventoryNumber() && storageInventory.getInventoryNumber()!=0 ){
							Double inventoryNumber = storageInventory.getInventoryNumber();//获取库存数量
							if(inventoryNumber>=Double.parseDouble(goodsNumber)){
							}else{
								return this.toFailTipJson(storageWarehousingDtl.getGoodsInfo().getGoodsName()+"库存量不足，不能出库！");
							}
						}else{
							return this.toFailTipJson(storageWarehousingDtl.getGoodsInfo().getGoodsName()+"库存量不足，不能出库！");
						}
					}
					
					for(Object obj:orderList){
						JSONObject jsonObject=JSONObject.fromObject(obj);
						String deliveryDtlId = jsonObject.getString("deliveryDtlId");
						String goodsNumber = jsonObject.getString("goodsNumber");
						StorageDeliveryDtl storageWarehousingDtl = (StorageDeliveryDtl)inventoryHandler.queryDeliveryDtlById(Integer.valueOf(deliveryDtlId));
						
						//根据出库ID查询出库单信息
						StorageInventory storageInventory =inventoryHandler.queryStorageInventory(deliveryOrder.getWarehouseId(),storageWarehousingDtl.getGoodsId());
						Double inventoryNumber = storageInventory.getInventoryNumber();//获取库存数量
						inventoryNumber = inventoryNumber-Double.parseDouble(goodsNumber);
						storageInventory.setInventoryNumber(inventoryNumber);
						inventoryHandler.saveOrUpdateStorageInventory(storageInventory);
						storageWarehousingDtl.setNewer(false);
						storageWarehousingDtl.setDeliveryCount(Double.valueOf(goodsNumber));
						inventoryHandler.saveOrUpdateDeliveryDetail(storageWarehousingDtl);
					}
				}
				//修改出库单状态和日期
				deliveryOrder.setDeliveryStatus(Const.IS_STATUS_END);
				deliveryOrder.setCheckUserId(Integer.parseInt(checkUserId));
				deliveryOrder.setDeliveryDate(DateUtil.getDateY_M_DH_M_S());//实际出库日期
				deliveryOrder.setNewer(false);
				inventoryHandler.saveOrUpdateDeliveryOrder(deliveryOrder);
				
				//添加用户操作
				SysUser sysUser = sysFuncHandler.querySysUserById(Integer.parseInt(checkUserId));
				SysUserOp sysUserOp = new SysUserOp(sysUser.getUserId(), sysUser.getRealName(), Const.OP_TYPE_OUTAGENT, 
						deliveryOrder.getDeliveryOrderId(), deliveryOrder.getDeliveryNo(), DateUtil.getDateY_M_D(), 
						deliveryOrder.getDeliveryDate(),deliveryOrder.getDeliveryStatus(), 
						deliveryOrder.getRemark(), "", "", "", "", "", "");
				sysUserOp.setNewer(true);
				sysFuncHandler.saveOrUpdateOp(sysUserOp);
				
				return this.toSuccessTipJson("出库成功！");
			} else {
				return this.toFailTipJson("该订单已出库过！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
	}
}
