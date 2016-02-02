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
import com.smart.om.persist.StorageDeliveryAgent;
import com.smart.om.persist.StorageDeliveryDtl;
import com.smart.om.persist.StorageDeliveryOrder;
import com.smart.om.persist.SysUser;
import com.smart.om.persist.SysUserOp;
import com.smart.om.rest.base.BaseResource;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.PageData;
import com.smart.om.util.Tools;

/**
 * 出库清洗管理Rest接口
 * @author langyuk
 *
 */
@Component("DeliveryAgentResoure")
public class DeliveryAgentResoure  extends BaseResource{
	private static final Logger logger = Logger.getLogger(DeliveryOrderResource.class);
	@Resource
	private InventoryHandler inventoryHandler;
	@Resource
	private SysFuncHandler sysFuncHandler;
	
	//查询登录用户出库单清洗
  	public String queryDeliveryAgents(Map dataMap){
  		String result = "";
  		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
  		String cleanUserId = Tools.trim(dataMap.get("userId"));//登录用户	
  		String checkUserId = Tools.trim(dataMap.get("checkUserId"));//App用户	
  		String orderDate = Tools.trim(dataMap.get("orderDate"));//出库日期
  		if(StringUtils.isBlank(orderDate)) {//如果参数没有出库日期，则查询当前日期
  			orderDate = DateUtil.getDateY_M_D();
  		}
  		String cleanStatus = Tools.trim(dataMap.get("cleanStatus"));//出库单状态
  		String pageSize = Tools.trim(dataMap.get("pageSize"));//每页显示条数
  		String currentPage = Tools.trim(dataMap.get("currentPage"));//当前页	
  		try {
  			PageData pageData = this.getPageData(pageSize, currentPage);
  			Map<String, Object> params = new HashMap<String, Object>();
  			params.put("cleanUser", cleanUserId);
  			params.put("app", "app");
  			params.put("orderDate", orderDate);
  			params.put("cleanStatus", cleanStatus);//处理类型
  			//params.put("isClean", "1");//区分是否清洗查询排序
  			SysUser user = sysFuncHandler.querySysUserById(Integer.parseInt(checkUserId));//根据用户ID 查询出用户所在地区
  			Integer orgId = null;
  			if(user!=null){
  				orgId= user.getOrgId();//查询出用户所在地区
  			}
  			params.put("orgId", orgId);//查询出用户所在地区
  			DTablePageModel pageModel = inventoryHandler.queryDeliveryOrderPage(params, pageData);
  			List<StorageDeliveryOrder> deviceCleanList = pageModel.getAaData();			
  			if(deviceCleanList != null){
  				for (StorageDeliveryOrder storageDeliveryOrder : deviceCleanList) {
  					Map<String, Object> dcMap = new HashMap<String, Object>();					
  					dcMap.put("deliveryOrderId", storageDeliveryOrder.getDeliveryOrderId());//清洗ID
  					dcMap.put("warehousingNo", storageDeliveryOrder.getDeliveryNo());//出库单编号		
  					dcMap.put("requestDate", storageDeliveryOrder.getRequestDate());//要求出库时间
  					dcMap.put("agentTime", storageDeliveryOrder.getCleanTime());//出库清洗时间
  					dcMap.put("cleanStatus", storageDeliveryOrder.getCleanStatus());//出库单清洗状态
  					dcMap.put("deliveryStatus", storageDeliveryOrder.getDeliveryStatus());//出库单状态
  					dcMap.put("isSubmit", storageDeliveryOrder.getIsSubmit());//配送状态
  					
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
  	
  	//查询出库清洗详情
  	/**
  	 * @param dataMap
  	 * @return
  	 */
  	public String queryDeliveryAgentByDeliveryId(Map dataMap){
  		String result = "";
  		Map<String, Object> weMap = new HashMap<String, Object>();		
  		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
  		String deliveryOrderId = Tools.trim(dataMap.get("deliveryOrderId"));//出库单ID	
  		try {
  			//查询入库基本信息
  			StorageDeliveryOrder deliveryOrder = (StorageDeliveryOrder)inventoryHandler.queryDeliveryOrderById(Integer.valueOf(deliveryOrderId));
  			weMap.put("deliveryOrderId", deliveryOrder.getDeliveryOrderId());//出库单ID
  			weMap.put("deliveryNo", deliveryOrder.getDeliveryNo());//出库单编号			
  			weMap.put("warehousingDate", deliveryOrder.getDeliveryDate());//实际出库时间
  			weMap.put("requestDate", deliveryOrder.getRequestDate());//要求出库时间
  			weMap.put("deliveryStatus", deliveryOrder.getDeliveryStatus());//出库状态
  			weMap.put("bizType", deliveryOrder.getBizType());//出库单状态
			//查询出库单明细
			//List<StorageDeliveryAgent> deliveryCleanList = inventoryHandler.queryDeliveryAgentByDeliveryId(Integer.valueOf(deliveryOrderId));
			List<StorageDeliveryDtl> StorageDeliveryDtlList =inventoryHandler.queryDeliveryDtlByDeliveryId(Integer.valueOf(deliveryOrderId));
			
  			if(StorageDeliveryDtlList != null){
				for (StorageDeliveryDtl storageDeliveryClean : StorageDeliveryDtlList) {
					Map<String, Object> dcMap = new HashMap<String, Object>();					
					dcMap.put("deliveryAgentId", storageDeliveryClean.getDeliveryDtlId());//出库清洗明细ID
					dcMap.put("goodsId", storageDeliveryClean.getGoodsId());//出库清洗商品ID
					dcMap.put("goodsName", storageDeliveryClean.getGoodsInfo().getGoodsName());//出库清洗商品
					dcMap.put("agentNumber", storageDeliveryClean.getDeliveryCount());//清洗商品数量
					dcMap.put("agentPic", storageDeliveryClean.getDevicePic1());//清洗图片
					dcMap.put("agentPic2", storageDeliveryClean.getDevicePic2());//清洗图片
  					lstMap.add(dcMap);
				}
			}
			weMap.put("deliveryCleanList", lstMap);//出库单清洗明细
			weMap.put("deliveryHandover", deliveryOrder.getDeliveryHandover()); //出库单交接状态
  			result = this.toResultJsonSuccess(weMap);
  		} catch (Exception e) {
  			e.printStackTrace();
  			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
  		}
  		return result;
  	}
  	
  	/**
	 * 提交出库清洗
	 * 1、修改出库订单清洗状态和清洗时间
	 */
	public String submitDeliveryAgent(String data){
		try {
			JSONObject dataObject=JSONObject.fromObject(data);
			String deliveryOrderId = Tools.trim(dataObject.getString("deliveryOrderId"));//出库单ID
			String checkUserId = Tools.trim(dataObject.getString("checkUserId"));//操作用户
			String bizType = Tools.trim(dataObject.getString("bizType"));//出库单状态
			
			String agents = dataObject.containsKey("agents")?dataObject.getString("agents"):null;
			
			StorageDeliveryOrder deliveryOrder = (StorageDeliveryOrder)inventoryHandler.queryDeliveryOrderById(Integer.valueOf(deliveryOrderId));
			if(deliveryOrder == null) {
				return this.toFailTipJson("该订单未找到！");
			}
			if(Const.IS_STATUS_INIT.equals(deliveryOrder.getCleanStatus())) {
				//修改出库单状态和日期
				deliveryOrder.setIsClean(Const.IS_STATUS_END);
				deliveryOrder.setNewer(false);
				deliveryOrder.setIsSubmit("1");
				if(!"1".equals(bizType)){
					if("".equals(deliveryOrder.getCleanTime()) || null==deliveryOrder.getCleanTime()){
						deliveryOrder.setCleanTime(DateUtil.getDateY_M_DH_M_S());
					}
					deliveryOrder.setCleanStatus("1");
				}
				//deliveryOrder.setDeliveryStatus("1");
				inventoryHandler.saveOrUpdateDeliveryOrder(deliveryOrder);
				//修改清洗明细
				//获得图片地址
				String rootPath = this.getClass().getResource("/").getPath();
				rootPath = rootPath.substring(0, rootPath.indexOf("WEB-INF")-1);
				if(StringUtils.isNotEmpty(agents)) {
					//解析出库信息的JSON串，循环修改订单明细的实际出库数量
					JSONArray picList = JSONArray.fromObject(agents);
					for(Object obj:picList){
						String fileName = "";//图片文件地址
						JSONObject jsonObject=JSONObject.fromObject(obj);
						String deliveryAgentId = jsonObject.getString("deliveryAgentId");
						String agentPic = jsonObject.getString("agentPic");
						StorageDeliveryDtl storageDeliveryDtl = inventoryHandler.queryDeliveryDtlById(Integer.valueOf(deliveryAgentId));
						if(StringUtils.isNotBlank(agentPic)) {
							fileName = Const.DEVICE_CLEAN_IMG_PATH + "/" + storageDeliveryDtl.getDeliveryOrderId() + storageDeliveryDtl.getDeliveryDtlId() + ".png";
							Tools.base64StringToImage(agentPic,"png",rootPath + fileName);	
							storageDeliveryDtl.setDevicePic1(fileName);
						}
						storageDeliveryDtl.setAgentType(1);
						storageDeliveryDtl.setNewer(false);
						inventoryHandler.saveOrUpdateDeliveryOrder(storageDeliveryDtl);
					}
				}
				//添加用户操作
				SysUser sysUser = sysFuncHandler.querySysUserById(Integer.parseInt(checkUserId));
				SysUserOp sysUserOp = new SysUserOp(sysUser.getUserId(), 
						sysUser.getRealName(), Const.OP_TYPE_OUT, 
						deliveryOrder.getDeliveryOrderId(), deliveryOrder.getDeliveryNo(), 
						DateUtil.getDateY_M_D(), DateUtil.getDateY_M_DH_M_S(),
						deliveryOrder.getIsClean(), 
						deliveryOrder.getRemark(), "", "", "", "", "", "");
				sysUserOp.setNewer(true);
				sysFuncHandler.saveOrUpdateOp(sysUserOp);
				
				return this.toSuccessTipJson("出库处理成功！");
			} else {
				return this.toFailTipJson("该订单已处理过！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
	}
	
	/**
	 * 修改接收订单状态
	 * 
	 */
	public String submitDeliveryReceive(Map dataMap){
		String result = "";
  		Map<String, Object> weMap = new HashMap<String, Object>();		
  		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
  		String deliveryOrderId = Tools.trim(dataMap.get("deliveryOrderId"));//出库单ID	
  		try {
  			StorageDeliveryOrder StorageDeliveryOrder= (StorageDeliveryOrder)inventoryHandler.queryDeliveryOrderById(Integer.parseInt(deliveryOrderId));
  			StorageDeliveryOrder.setDeliveryHandover("1");//接收订单状态修改为已接受
  			inventoryHandler.saveOrUpdateDeliveryOrder(StorageDeliveryOrder);
			weMap.put("deliveryCleanList", lstMap);//出库单清洗明细
			return this.toSuccessTipJson("订单接收完成！");
  		} catch (Exception e) {
  			e.printStackTrace();
  			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
  		}
	}
}
