package com.smart.om.rest.dispatching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smart.om.biz.car.CarHandler;
import com.smart.om.biz.device.DeviceHandler;
import com.smart.om.biz.dispatching.DispatchingHandler;
import com.smart.om.biz.goods.GoodsHandler;
import com.smart.om.biz.inventory.InventoryHandler;
import com.smart.om.biz.pay.PayHandler;
import com.smart.om.biz.region.RegionHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.dao.goods.GoodsInfoDAO;
import com.smart.om.persist.DeviceInfo;
import com.smart.om.persist.DeviceOrder;
import com.smart.om.persist.DispatchingOrder;
import com.smart.om.persist.DispatchingSubOrder;
import com.smart.om.persist.DispatchingTeam;
import com.smart.om.persist.GoodsInfo;
import com.smart.om.persist.PayDeviceCash;
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
 * 配送管理Rest接口
 * @author langyuk
 *
 */
@Component("DispatchingPlanResource")
public class DispatchingPlanResource  extends BaseResource{
	private static final Logger logger = Logger.getLogger(DispatchingPlanResource.class);
	@Resource
	private DispatchingHandler dispatchingHandler;
	@Resource
	private CarHandler carHandler;
	@Resource
	private GoodsHandler goodsHandler;
	@Resource
	private DeviceHandler deviceHandler;
	@Resource
	private SysFuncHandler sysFuncHandler;
	@Resource
	private RegionHandler regionHandler;
	@Resource
    private InventoryHandler inventoryHandler;
	@Resource
	private PayHandler payHandler;
	@Autowired
	private GoodsInfoDAO goodsInfoDAO;
	
	//查询登录用户所有配送订单
	public String queryDispatchingPlans(Map dataMap){
		String result = "";
		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
		String userId = Tools.trim(dataMap.get("userId"));//登录用户	
  		String checkUserId = Tools.trim(dataMap.get("checkUserId"));//App用户
  		String deliveryOrderId = Tools.trim(dataMap.get("deliveryOrderId"));//分配
		String orderDate = Tools.trim(dataMap.get("orderDate"));//配送日期
		String deviceId = Tools.trim(dataMap.get("deviceId"));//设备ID
		String deviceNo = Tools.trim(dataMap.get("deviceNo"));//设备编号
		if(StringUtils.isBlank(orderDate)) {//如果参数没有配送日期，则查询当前日期
			orderDate = DateUtil.getDateY_M_D();
		}
		String orderStatus = Tools.trim(dataMap.get("orderStatus"));//订单状态
		String pageSize = Tools.trim(dataMap.get("pageSize"));//每页显示条数
		String currentPage = Tools.trim(dataMap.get("currentPage"));//当前页	
		String teamId ="";
		try {
			if(null== deliveryOrderId || "".equals(deliveryOrderId)){
				if(checkUserId!="" && !"".equals(checkUserId)){
					List<DispatchingTeam> dispatchingTeamList= regionHandler.queryDispatchingTeamByUserId(Integer.parseInt(checkUserId));//根据用户ID 查询用户所在的特勤小组
					if(dispatchingTeamList !=null){
						DispatchingTeam dispatchingTeam = dispatchingTeamList.get(0);
						teamId = dispatchingTeam.getTeamId().toString();//获取当前特勤小组ID
					}else{
						return this.toResultJsonSuccess(lstMap);
					}
				}
			}
			
			PageData pageData = this.getPageData(pageSize, currentPage);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("deviceId", deviceId);
			params.put("deviceNo", deviceNo);
			params.put("userId", userId);
			params.put("teamId", teamId);//获取当前特勤小组ID
			params.put("orderDate", orderDate);
			params.put("orderStatus", orderStatus);
			params.put("deliveryOrderId", deliveryOrderId);
			params.put("app", "app");
			
			DTablePageModel pageModel = regionHandler.queryDOrder(params, pageData);
			List<DispatchingOrder> dispatchingOrderList = pageModel.getAaData();
			if(dispatchingOrderList != null){
				for (DispatchingOrder dispatchingOrder : dispatchingOrderList) {
					Map<String, Object> dpMap = new HashMap<String, Object>();
					dpMap.put("orderId", dispatchingOrder.getOrderId());//配送计划ID
					dpMap.put("orderNo", dispatchingOrder.getOrderNo());//配送计划编号
					dpMap.put("orderStartTime", dispatchingOrder.getOrderTime());//计划开始时间
					dpMap.put("orderEndTime", dispatchingOrder.getOrderEndTime());//计划完成时间
					dpMap.put("orderFinishTime", dispatchingOrder.getOrderFinishTime());//实际完成时间
					dpMap.put("orderStatus", dispatchingOrder.getOrderStatus());//配送状态
					dpMap.put("orderAllocation", dispatchingOrder.getOrderAllocation());//订单分配是否完成
					StorageDeliveryOrder deliveryOrder = (StorageDeliveryOrder)inventoryHandler.queryDeliveryOrderById(dispatchingOrder.getDeliveryOrderId());
					dpMap.put("agentHandover", deliveryOrder.getAgentHandover());//清洗交接
					if(StringUtils.isNotBlank(dispatchingOrder.getOrderFinishTime())){
						if(DateUtil.getTimeDiff(dispatchingOrder.getOrderFinishTime(), dispatchingOrder.getOrderEndTime())){
							dpMap.put("isOverTime", Const.IS_DEL_TRUE);//超时
						}
						else{
							dpMap.put("isOverTime", Const.IS_DEL_FALSE);//未超时
						}
					}
					else{
						if(!"1".equals(dispatchingOrder.getOrderStatus())){
							if(DateUtil.getTimeDiff(DateUtil.getDateY_M_DH_M_S(), dispatchingOrder.getOrderEndTime())){
								dpMap.put("isOverTime", "2");//未完成超时
							}else{
								dpMap.put("isOverTime", null);//未完成
							}
						}
						
					}
					lstMap.add(dpMap);
				}				
			}
			result = this.toResultJsonSuccess(lstMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		return result;
	}
	//根据配送计划查询配送计划的站点
	public String queryNodesByPlan(Map dataMap){
		String result = "";
		String subOrderStatus ="";
		Map<String, Object> subMap = new HashMap<String, Object>();		
		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
		String orderId = Tools.trim(dataMap.get("orderId"));//配送计划ID	
		try {			
			List<Object[]> dispatchingSubOrderList = regionHandler.dispatchingSubOrderByOrderId(Integer.valueOf(orderId));	
			List<DispatchingSubOrder> dsoList = regionHandler.subOrderByOrderId(Integer.valueOf(orderId));
			if(dsoList==null){
				subOrderStatus = "1";
			}else{
				subOrderStatus = "0";
			}
			//DispatchingOrder dispatchingOrder = regionHandler.queryDOrderById(Integer.valueOf(orderId));
			//subMap.put("orderAllocation", dispatchingOrder.getOrderAllocation());//订单是否被分配
			if(dispatchingSubOrderList != null){
				for (int i = 0; i < dispatchingSubOrderList.size(); i++) {
					//Object obj=new Object();
					Object[] obj  = dispatchingSubOrderList.get(i);
					Map<String, Object> dnMap = new HashMap<String, Object>();		
					dnMap.put("districtId", obj[0]);//商圈ID
					dnMap.put("districtName", obj[1]);//商圈名称
					//dnMap.put("subOrderId", dispatchingSubOrder.getSubOrderId());//子订单ID
					//dnMap.put("orderId", dispatchingSubOrder.getOrderId());//订单ID
					lstMap.add(dnMap);
				}	
			}
			List bladeCountList = regionHandler.bladeCountByOrderId(Integer.valueOf(orderId));
			Integer bladeCount = Integer.parseInt(bladeCountList.get(0).toString());
			subMap.put("BLADECount", bladeCount+Const.BLADE_NUMBER);//刀片数量
			subMap.put("goodsList", lstMap);//商圈
			subMap.put("subOrderStatus", subOrderStatus); //订单下面子订单状态
			result = this.toResultJsonSuccess(subMap);
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		return result;
	}
	
	//根据配送计划查询配送计划的站点
	public String queryGoodsByNode(Map dataMap){
		String result = "";
		String bladeStatus = "0";
		String districtId = Tools.trim(dataMap.get("districtId"));//商圈ID
		String orderId = Tools.trim(dataMap.get("orderId"));//订单ID
		List<Map<String, Object>> dsoListMap = new ArrayList<Map<String, Object>>();
		try {			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("districtId", districtId);
			params.put("orderId", orderId);
			List list = new ArrayList();
			list = regionHandler.goodsByDistrictId(params);	
			if(list != null){
				for (int i = 0; i < list.size(); i++) {
					Integer deviceId = (Integer)list.get(i);
					//Integer goodsId = dispatchingSubOrder.getGoodsId();
					DeviceInfo deviceInfo = deviceHandler.queryDeviceById(deviceId);
					Map<String, Object> diMap = new HashMap<String, Object>();
					
					diMap.put("deviceId", deviceId);//设备ID
					diMap.put("deviceName", deviceInfo.getDeviceName());//设备名称
					diMap.put("deviceAdress", deviceInfo.getDeviceAddress());//设备地址
					diMap.put("bladeChangeTime", deviceInfo.getBladeChangeTime());
					//DateUtil.beforNowDay(Const.BLADE);
					if(DateUtil.getDayDiff(DateUtil.beforNowDay(Const.BLADE),deviceInfo.getBladeChangeTime())>0){
						diMap.put("bladeChange", "1");//刀片需要更换
					}else{
						diMap.put("bladeChange", "0");
					}
					List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
					List<DispatchingSubOrder> dispatchingSubOrderList =regionHandler.subOrderByDeviceId(deviceId,Integer.parseInt(orderId));
					for (int j = 0; j < dispatchingSubOrderList.size(); j++) {
						Map<String, Object> dsoMap = new HashMap<String, Object>();
						DispatchingSubOrder dispatchingSubOrder =dispatchingSubOrderList.get(j);
						if("刀片".equals(dispatchingSubOrder.getGoodsName())){
							bladeStatus = dispatchingSubOrder.getSubOrderStatus();
						}else{
							dsoMap.put("orderId", dispatchingSubOrder.getOrderId());//配送订单ID
							dsoMap.put("subOrderId", dispatchingSubOrder.getSubOrderId());//配送子订单ID
							dsoMap.put("goodsId", dispatchingSubOrder.getGoodsId());//商品ID
							dsoMap.put("goodsName", dispatchingSubOrder.getGoodsName());//商品名称
							dsoMap.put("goodsNumber", dispatchingSubOrder.getGoodsNumber());//出库商品数量
							dsoMap.put("goodsCount", dispatchingSubOrder.getGoodsCount());//清洗商品出库数量
							dsoMap.put("realiGoodsNumber", dispatchingSubOrder.getRealiGoodsNumber());//实际商品数量
							dsoMap.put("subOrderStatus", dispatchingSubOrder.getSubOrderStatus());//完成状态
//							dsoMap.put("bladeChange", dispatchingSubOrder.getBladeChange());//刀片是否更换
							dsoMap.put("orderAllocation", dispatchingSubOrder.getOrderAllocation());//当时2的时候是清洗状态
							lstMap.add(dsoMap);
						}
					}
					PayDeviceCash payDeviceCash =payHandler.queryPayCashByDoId(Integer.parseInt(orderId), deviceId);
					if(payDeviceCash!=null){
						diMap.put("cashNumber", payDeviceCash.getCashNumber());//收取金额
					}else{
						diMap.put("cashNumber", "");//收取金额
					}
					List<DeviceOrder> deviceOrderList =regionHandler.queryDeviceOrderByOrIdDeId(Integer.parseInt(orderId),deviceId);
					if(deviceOrderList!=null){
						for (int j = 0; j < deviceOrderList.size(); j++) {
							DeviceOrder deviceOrder = deviceOrderList.get(j);
							diMap.put("orderPic1", deviceOrder.getOrderPic1());
							diMap.put("orderPic2", deviceOrder.getOrderPic2());
							diMap.put("orderPic3", deviceOrder.getOrderPic3());
							diMap.put("cupNumber", deviceOrder.getCupNumber());//杯子
							diMap.put("strawNumber", deviceOrder.getStrawNumber());//吸管
							diMap.put("isFinish", deviceOrder.getIsFinish());
							diMap.put("isBladeCheck", deviceOrder.getIsBladeCheck());
						}
					}else{
						diMap.put("orderPic1", "");
						diMap.put("orderPic2", "");
						diMap.put("orderPic3", "");
						diMap.put("cupNumber", "");//杯子
						diMap.put("strawNumber", "");//吸管
						diMap.put("isFinish", "0");
						diMap.put("isBladeCheck", "0");
					}
					diMap.put("bladeStatus", bladeStatus);//刀片状态
					diMap.put("pomace", "0");//果渣
					diMap.put("goodsList", lstMap);
					dsoListMap.add(diMap);
				}
			}
			result = this.toResultJsonSuccess(dsoListMap);
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		return result;
	}
	
	//配送页面 订单详情 提交子订单信息
	public String submitDispatchingGoods(Map dataMap){
		String result = "";
		JSONObject dataObject=JSONObject.fromObject(dataMap);
		Integer orderId = Integer.parseInt(Tools.trim(dataObject.getString("orderId")));//订单ID
		Integer deviceId = Integer.parseInt(Tools.trim(dataObject.getString("deviceId")));//设备ID
		Integer cupNumber =0;
		if(!"".equals(dataObject.getString("cupNumber"))){
			cupNumber =Integer.parseInt(Tools.trim(dataObject.getString("cupNumber")));//杯子
		}
		Integer strawNumber=0;
		if(!"".equals(dataObject.getString("strawNumber"))){
			strawNumber=Integer.parseInt(Tools.trim(dataObject.getString("strawNumber")));//吸管
		}
		double cashNumber = 0.0;
		if(!"".equals(dataObject.getString("cashNumber"))){
			cashNumber =Double.parseDouble(Tools.trim(dataObject.getString("cashNumber")));//收取金额
		}
		String checkUserId = Tools.trim(dataMap.get("checkUserId"));//App用户
		String pomace = null;
		if(!"".equals(dataObject.getString("pomace"))){
			pomace =Tools.trim(dataObject.getString("pomace"));//果渣
		}
		
		String bladeCheck=null;
		if(!"".equals(dataObject.getString("bladeCheck"))){
			bladeCheck =Tools.trim(dataObject.getString("bladeCheck"));//是否更换刀片
		}
		if("1".equals(bladeCheck)){
			DeviceInfo deviceInfo = deviceHandler.queryDeviceById(deviceId);
			deviceInfo.setBladeChangeTime(DateUtil.getDateY_M_D());
			deviceHandler.saveOrUpdateDeviceInfoDAO(deviceInfo);
		}
		 
		String orderPics = dataObject.containsKey("orderPics")?dataObject.getString("orderPics"):null;
		String goods = dataObject.containsKey("goodsList")?dataObject.getString("goodsList"):null;
		
		//获得图片地址
		String rootPath = this.getClass().getResource("/").getPath();
		rootPath = rootPath.substring(0, rootPath.indexOf("WEB-INF")-1);
		try {			
			DeviceOrder deviceOrder = new DeviceOrder();
			if(StringUtils.isNotEmpty(orderPics)) {
				//解析出库信息的JSON串，循环修改订单明细的实际出库数量
				JSONArray picList = JSONArray.fromObject(orderPics);
				int i = 0;
				for(Object obj:picList){
					i++;
					String fileName = "";//图片文件地址
					JSONObject jsonObject=JSONObject.fromObject(obj);
					String orderPic = jsonObject.getString("orderPic");
					if(StringUtils.isNotBlank(orderPic)) {
						fileName = Const.DEVICE_ORDER_IMG_PATH + "/" + orderId+deviceId + i + ".png";
						Tools.base64StringToImage(orderPic,"png",rootPath + fileName);							
					}
					//设置图片文件地址
					if(i == 1){
						deviceOrder.setOrderPic1(fileName);
					}
					else if(i == 2){
						deviceOrder.setOrderPic2(fileName);
					}
					else if(i == 3){
						deviceOrder.setOrderPic3(fileName);
					}
				}
			}
			deviceOrder.setIsBladeCheck(bladeCheck);
			deviceOrder.setIsFinish("1");
			deviceOrder.setOrderId(orderId);
			deviceOrder.setDeviceId(deviceId);
			deviceOrder.setCupNumber(cupNumber);
			deviceOrder.setStrawNumber(strawNumber);
			deviceOrder.setNewer(true);
			regionHandler.saveOrUpdateDeviceOrder(deviceOrder);//保存设备订单
			
			if(StringUtils.isNotEmpty(goods)) {
				//解析出库信息的JSON串
				JSONArray goodsList = JSONArray.fromObject(goods);
				for(Object obj:goodsList){
					JSONObject jsonObject=JSONObject.fromObject(obj);
					String subOrderId = jsonObject.getString("subOrderId");//子订单ID
					String realiGoodsNumber= Tools.trim(jsonObject.getString("realiGoodsNumber"));//商品数量
					DispatchingSubOrder dispatchingSubOrder = regionHandler.queryDSubOrderById(Integer.valueOf(subOrderId));
					dispatchingSubOrder.setRealiGoodsNumber(Double.parseDouble(realiGoodsNumber));
					if(dispatchingSubOrder.getDispatchingTime()==null){
						dispatchingSubOrder.setDispatchingTime(DateUtil.getDateY_M_DH_M_S());
					}
					dispatchingSubOrder.setSubOrderStatus(Const.IS_STATUS_END);
					regionHandler.saveOrUpdateDSubOrder(dispatchingSubOrder);//保存子订单
				}
			}
			PayDeviceCash payDeviceCash =payHandler.queryPayCashByDeviceId(deviceId);
			if(payDeviceCash==null){
				payDeviceCash.setDeviceId(deviceId);
				DeviceInfo deviceInfo = deviceHandler.queryDeviceById(deviceId);
				payDeviceCash.setDeviceNo(deviceInfo.getDeviceNo());
				payDeviceCash.setCashNumber(cashNumber);
				payDeviceCash.setLastCashTime(DateUtil.getDateY_M_DH_M_S());
				payDeviceCash.setThisCashTime(DateUtil.getDateY_M_DH_M_S());
				payDeviceCash.setPostingUser(Integer.parseInt(checkUserId));
				payDeviceCash.setCashStatus("0");
				payDeviceCash.setOrderId(orderId);
				payDeviceCash.setNewer(true);
				payHandler.saveOrUpdatePayDeviceCash(payDeviceCash);
			}else{
				payDeviceCash.setDeviceId(deviceId);
				DeviceInfo deviceInfo = deviceHandler.queryDeviceById(deviceId);
				payDeviceCash.setDeviceNo(deviceInfo.getDeviceNo());
				payDeviceCash.setCashNumber(cashNumber);
				payDeviceCash.setLastCashTime(payDeviceCash.getLastCashTime());
				payDeviceCash.setThisCashTime(DateUtil.getDateY_M_DH_M_S());
				payDeviceCash.setPostingUser(Integer.parseInt(checkUserId));
				payDeviceCash.setCashStatus("0");
				payDeviceCash.setOrderId(orderId);
				payDeviceCash.setNewer(true);
				payHandler.saveOrUpdatePayDeviceCash(payDeviceCash);
			}
			
			result = this.toSuccessTipJson("保存成功！");
			
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		DispatchingOrder dispatchingOrder = regionHandler.queryDOrderById(Integer.valueOf(orderId));
		return result;
	}
	
	//配送页面  商圈 提交 配送单
	public String submitDispatchingPlan(Map dataMap){
		String result = "";
		String orderId = Tools.trim(dataMap.get("orderId"));//配送订单ID
		String deliveryOrderId = Tools.trim(dataMap.get("deliveryOrderId"));//订单ID	
		String checkUserId = Tools.trim(dataMap.get("checkUserId"));//App用户
		try {			
			DispatchingOrder dispatchingOrder = regionHandler.queryDOrderById(Integer.valueOf(orderId));
			dispatchingOrder.setNewer(false);
			if("".equals(deliveryOrderId)){
				dispatchingOrder.setOrderStatus("2");//配送结束
			}else{
				dispatchingOrder.setOrderAllocation("1");
			}
			String dateTime =  DateUtil.getDateY_M_DH_M_S();
			dispatchingOrder.setOrderFinishTime(dateTime);//配送结束时间
			regionHandler.saveOrUpdateDOrder(dispatchingOrder);
			
			//添加用户操作
			//String opUsers = dispatchingPlan.getDispatchingUser();
			String opUsers =dispatchingOrder.getCreateUser().toString();
			List<String> userList = Tools.strToList(opUsers, ",");
			if(userList != null){
				for (String userIdStr : userList) {
					SysUser sysUser = sysFuncHandler.querySysUserById(Integer.valueOf(checkUserId));
					SysUserOp sysUserOp = new SysUserOp(sysUser.getUserId(), sysUser.getRealName(), Const.OP_TYPE_DISPATCHING, 
							dispatchingOrder.getOrderId(), dispatchingOrder.getOrderNo(), DateUtil.getDateY_M_D(), dateTime,dispatchingOrder.getOrderStatus(), 
							 "", "", "", "", "", "", "");
					sysUserOp.setNewer(true);
					sysFuncHandler.saveOrUpdateOp(sysUserOp);
				}
			}			
			result = this.toSuccessTipJson("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		return result;
	}
	
	//配送页面  点击订单  完成清洗交接  订单变成配送中
	public String submitAgentReceive(Map dataMap){
		String result = "";
		String orderId = Tools.trim(dataMap.get("orderId"));//订单ID	
		try {
			DispatchingOrder dispatchingOrder = regionHandler.queryDOrderById(Integer.parseInt(orderId));
			StorageDeliveryOrder storageDeliveryOrder = (StorageDeliveryOrder)inventoryHandler.queryDeliveryOrderById(dispatchingOrder.getDeliveryOrderId());
			storageDeliveryOrder.setDeliveryOrderId(dispatchingOrder.getDeliveryOrderId());
			storageDeliveryOrder.setAgentHandover("1");
			inventoryHandler.saveOrUpdateDeliveryOrder(storageDeliveryOrder);
			
			dispatchingOrder.setOrderStatus("1");
			regionHandler.saveOrUpdateDOrder(dispatchingOrder);
			
			result = this.toSuccessTipJson("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		return result;
	}
	
	/**提交订单状态
	 * @param dataMap
	 * @return
	 */
	public String submitOrderStatusReceive(Map dataMap){
		String result = "";
		String orderId = Tools.trim(dataMap.get("orderId"));//订单ID	
		try {
			DispatchingOrder dispatchingOrder = new DispatchingOrder();
			
			
			result = this.toSuccessTipJson("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		return result;
	}
	
	/**提交配送计划 实际配送数量
	 * @param dataMap
	 * @return
	 */
	public String submitGoodsNumber(Map dataMap){
		String result = "";
		JSONObject dataObject=JSONObject.fromObject(dataMap);
		String bladeNumber = Tools.trim(dataObject.getString("bladeNumber"));//刀片数量
		String deliveryOrderId = Tools.trim(dataObject.getString("deliveryOrderId"));//出库单ID 
		String orderId = Tools.trim(dataObject.getString("orderId"));//订单ID
		String districtId = Tools.trim(dataObject.getString("districtId"));//商圈ID
		String checkUserId = Tools.trim(dataObject.getString("checkUserId"));//APPID 
		
		
		StorageDeliveryOrder deliveryOrder = (StorageDeliveryOrder)inventoryHandler.queryDeliveryOrderById(Integer.valueOf(deliveryOrderId));
		String orderDtls = dataObject.containsKey("orderDtls")?dataObject.getString("orderDtls"):null;
		String deviceDtls = dataObject.containsKey("deviceDtls")?dataObject.getString("deviceDtls"):null;
		
		StorageInventory storageInventoryD =null;
		if(!"".equals(bladeNumber) && null!=bladeNumber ){
			GoodsInfo goodsInfo = goodsInfoDAO.getGoodsInfoByName("刀片");
			storageInventoryD =inventoryHandler.queryStorageInventory(deliveryOrder.getWarehouseId(),goodsInfo.getGoodsId());
			if(null!=storageInventoryD.getInventoryNumber() && storageInventoryD.getInventoryNumber()>0 ){
				Double inventoryNumber = storageInventoryD.getInventoryNumber();//获取库存数量
				if(inventoryNumber>=Double.parseDouble(bladeNumber)){
				}else{
					return this.toFailTipJson(goodsInfo.getGoodsName()+"库存量不足，不能分配！");
				}
			}else{
				return this.toFailTipJson(goodsInfo.getGoodsName()+"库存量不足，不能分配！");
			}
			
			if(Integer.parseInt(bladeNumber)>5){
				if(StringUtils.isNotEmpty(deviceDtls)){
					JSONArray deviceDtlsList = JSONArray.fromObject(deviceDtls);
					for(Object obj:deviceDtlsList){
						JSONObject jsonObject=JSONObject.fromObject(obj);
						String deviceId = jsonObject.getString("deviceId");
						DispatchingSubOrder dispatchingSubOrderBlade = regionHandler.queryBladeDcO(Integer.parseInt(orderId),Integer.parseInt(deviceId),goodsInfo.getGoodsId());
						if(dispatchingSubOrderBlade!=null){
							dispatchingSubOrderBlade.setOrderAllocation("2");
							dispatchingSubOrderBlade.setSubOrderStatus("1");
							regionHandler.saveOrUpdateDSubOrder(dispatchingSubOrderBlade);
						}
					}
				}
			}
		}
		
		try {
			if(orderDtls!=null && orderDtls.length()>0) {
				//解析出库信息的JSON串，循环修改订单明细的实际出库数量
				JSONArray orderDtlsList = JSONArray.fromObject(orderDtls);
				List<Object[]> dispatchingSubOrderList = regionHandler.queryDSubOrderByOrderId(orderId,districtId);
				if(dispatchingSubOrderList != null){
					for (int i = 0; i < dispatchingSubOrderList.size(); i++) {
						Object[] objList  = dispatchingSubOrderList.get(i);
						Double goodsNumber =0.0;
						for(Object obj:orderDtlsList){
							JSONObject jsonObject=JSONObject.fromObject(obj);
							String subOrderId = jsonObject.getString("subOrderId");
							DispatchingSubOrder dispatchingSubOrder = regionHandler.queryDSubOrderById(Integer.valueOf(subOrderId));
							Integer goodsId = dispatchingSubOrder.getGoodsId();
							if(goodsId==Integer.parseInt(objList[0].toString())){
								String goodsCount = jsonObject.getString("goodsCount");
								goodsNumber = goodsNumber + Double.parseDouble(goodsCount);
							}
						}
						if(goodsNumber!=0.0){
							objList[1]=goodsNumber;
						}
						//goodsNumber= goodsNumber+Double.parseDouble(goodsCount);
					}
					
					for (int i = 0; i < dispatchingSubOrderList.size(); i++) {
						Object[] obj  = dispatchingSubOrderList.get(i);
						StorageInventory storageInventory =inventoryHandler.queryStorageInventory(deliveryOrder.getWarehouseId(),Integer.parseInt(obj[0].toString()));
						GoodsInfo goodsInfo = (GoodsInfo) goodsHandler.queryGoodsInfoById(Integer.parseInt(obj[0].toString()));
						if(null!=storageInventory.getInventoryNumber() && storageInventory.getInventoryNumber()>0 ){
							Double inventoryNumber = storageInventory.getInventoryNumber();//获取库存数量
							if(inventoryNumber>=Double.parseDouble(obj[1].toString())){
							}else{
								return this.toFailTipJson(goodsInfo.getGoodsName()+"库存量不足，不能分配！");
							}
						}else{
							return this.toFailTipJson(goodsInfo.getGoodsName()+"库存量不足，不能分配！");
						}
					}
				}
				
				for(Object obj:orderDtlsList){
					JSONObject jsonObject=JSONObject.fromObject(obj);
					String subOrderId = jsonObject.getString("subOrderId");
					String goodsCount = jsonObject.getString("goodsCount");
					
					if(StringUtils.isNotBlank(subOrderId)) {
						DispatchingSubOrder dispatchingSubOrder = regionHandler.queryDSubOrderById(Integer.valueOf(subOrderId));
						Integer goodsId = dispatchingSubOrder.getGoodsId();
						Integer orgId = dispatchingSubOrder.getDictOrgId();
						GoodsInfo goodsInfo = (GoodsInfo) goodsHandler.queryGoodsInfoById(goodsId);
						StorageInventory storageInventory =inventoryHandler.queryStorageInventory(deliveryOrder.getWarehouseId(),goodsId);
						if(0==goodsInfo.getIsFrozen()){
							Double inventoryNumber = storageInventory.getInventoryNumber();//获取库存数量
							inventoryNumber = inventoryNumber-Double.parseDouble(goodsCount);
							storageInventory.setInventoryNumber(inventoryNumber);
							inventoryHandler.saveOrUpdateStorageInventory(storageInventory);
						}
						dispatchingSubOrder.setOrderAllocation("2");
						dispatchingSubOrder.setGoodsCount(Double.parseDouble(goodsCount));
						regionHandler.saveOrUpdateDSubOrder(dispatchingSubOrder);
						
						DispatchingOrder dispatchingOrder = regionHandler.queryDOrderById(dispatchingSubOrder.getOrderId());
						
						List<DispatchingSubOrder> dsoList = regionHandler.subOrderByOrderIdAll(dispatchingOrder.getOrderId());
						if(dsoList==null){
							dispatchingOrder.setOrderAllocation("1");
							regionHandler.saveOrUpdateDOrder(dispatchingOrder);
							if(null!=storageInventoryD){
								Double inventoryNumber = storageInventoryD.getInventoryNumber();//获取库存数量
								inventoryNumber = inventoryNumber-Double.parseDouble(bladeNumber);
								storageInventoryD.setInventoryNumber(inventoryNumber);
								inventoryHandler.saveOrUpdateStorageInventory(storageInventoryD);
							}
						}
					}
					result = this.toSuccessTipJson("保存成功！");
				}
				if(null == orderDtlsList || orderDtlsList.size()==0){
					List<DispatchingSubOrder> dsoList = regionHandler.subOrderByOrderIdAll(Integer.parseInt(orderId));
					if(dsoList==null){
						DispatchingOrder dispatchingOrder = regionHandler.queryDOrderById(Integer.parseInt(orderId));
						dispatchingOrder.setOrderAllocation("1");
						regionHandler.saveOrUpdateDOrder(dispatchingOrder);
						if(null!=storageInventoryD){
							Double inventoryNumber = storageInventoryD.getInventoryNumber();//获取库存数量
							inventoryNumber = inventoryNumber-Double.parseDouble(bladeNumber);
							storageInventoryD.setInventoryNumber(inventoryNumber);
							inventoryHandler.saveOrUpdateStorageInventory(storageInventoryD);
						}
					}
					result = this.toSuccessTipJson("保存成功！");
				}
			}else{
				List<DispatchingSubOrder> dsoList = regionHandler.subOrderByOrderIdAll(Integer.parseInt(orderId));
				if(dsoList==null){
					DispatchingOrder dispatchingOrder = regionHandler.queryDOrderById(Integer.parseInt(orderId));
					dispatchingOrder.setOrderAllocation("1");
					regionHandler.saveOrUpdateDOrder(dispatchingOrder);
					if(null!=storageInventoryD){
						Double inventoryNumber = storageInventoryD.getInventoryNumber();//获取库存数量
						inventoryNumber = inventoryNumber-Double.parseDouble(bladeNumber);
						storageInventoryD.setInventoryNumber(inventoryNumber);
						inventoryHandler.saveOrUpdateStorageInventory(storageInventoryD);
					}
				}
				result = this.toSuccessTipJson("保存成功！");
			}
			List<DispatchingSubOrder> orderAllList = regionHandler.subOrderByDeliveryOrderIdAll(Integer.parseInt(deliveryOrderId));
			if(orderAllList==null){
				deliveryOrder.setCleanTime(DateUtil.getDateY_M_DH_M_S());
				deliveryOrder.setCleanStatus("1");
				inventoryHandler.saveOrUpdateDeliveryOrder(deliveryOrder);
			}
			
			//添加用户操作
			SysUser sysUser = sysFuncHandler.querySysUserById(Integer.parseInt(checkUserId));
			SysUserOp sysUserOp = new SysUserOp(sysUser.getUserId(), 
					sysUser.getRealName(), Const.OP_TYPE_DISTRIBUTION, 
					deliveryOrder.getDeliveryOrderId(), deliveryOrder.getDeliveryNo(), 
					DateUtil.getDateY_M_D(), DateUtil.getDateY_M_DH_M_S(),
					deliveryOrder.getIsClean(), 
					deliveryOrder.getRemark(), "", "", "", "", "", "");
			sysUserOp.setNewer(true);
			sysFuncHandler.saveOrUpdateOp(sysUserOp);
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		return result;
	}
}
