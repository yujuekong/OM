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

import com.smart.om.biz.goods.GoodsHandler;
import com.smart.om.biz.inventory.InventoryHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.GoodsInfo;
import com.smart.om.persist.StorageDeliveryOrder;
import com.smart.om.persist.StorageInventory;
import com.smart.om.persist.StorageWarehousingDtl;
import com.smart.om.persist.StorageWarehousingEntry;
import com.smart.om.persist.SysUser;
import com.smart.om.persist.SysUserOp;
import com.smart.om.rest.base.BaseResource;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.PageData;
import com.smart.om.util.Tools;

/**
 * 入库管理Rest接口
 * @author langyuk
 *
 */
@Component("WarehousingResource")
public class WarehousingResource extends BaseResource {
	private static final Logger logger = Logger.getLogger(WarehousingResource.class);
	@Resource
	private InventoryHandler inventoryHandler;
	@Resource
    private GoodsHandler goodsHandler;
    @Resource
	private SysFuncHandler sysFuncHandler;
    //查询登录用户入库单
  	public String queryWarehousings(Map dataMap){
  		String result = "";
  		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
  		String userId = Tools.trim(dataMap.get("userId"));//登录用户	
  		String checkUserId = Tools.trim(dataMap.get("checkUserId"));//App用户
  		String orderDate = Tools.trim(dataMap.get("orderDate"));//入库日期
  		if(StringUtils.isBlank(orderDate)) {//如果参数没有入库日期，则查询当前日期
  			orderDate = DateUtil.getDateY_M_D();
  		}
  		String warehousingStatus = Tools.trim(dataMap.get("warehousingStatus"));//入库单状态
  		String pageSize = Tools.trim(dataMap.get("pageSize"));//每页显示条数
  		String currentPage = Tools.trim(dataMap.get("currentPage"));//当前页	
  		try {
  			PageData pageData = this.getPageData(pageSize, currentPage);
  			Map<String, Object> params = new HashMap<String, Object>();
  			params.put("app", "app");
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
  			params.put("orderDate", orderDate);
  			params.put("warehousingStatus", warehousingStatus);
  			DTablePageModel pageModel = inventoryHandler.queryWarehousingPage(params, pageData);
  			List<StorageWarehousingEntry> deviceCleanList = pageModel.getAaData();			
  			if(deviceCleanList != null){
  				for (StorageWarehousingEntry storageWarehousingEntry : deviceCleanList) {
  					Map<String, Object> dcMap = new HashMap<String, Object>();
  					dcMap.put("warehousingEntryId", storageWarehousingEntry.getWarehousingEntryId());//清洗ID
  					dcMap.put("warehousingNo", storageWarehousingEntry.getWarehousingNo());//入库单编号			
  					dcMap.put("requestDate", storageWarehousingEntry.getRequestDate());//要求入库日期
  					dcMap.put("warehousingDate", storageWarehousingEntry.getWarehousingDate());//实际入库日期
  					dcMap.put("warehousingStatus", storageWarehousingEntry.getWarehousingStatus());//入库单状态
  					if(StringUtils.isNotBlank(storageWarehousingEntry.getWarehousingDate())){
						if(DateUtil.getDayDiff(storageWarehousingEntry.getWarehousingDate(), storageWarehousingEntry.getRequestDate()) >0){
							dcMap.put("isOverTime", Const.IS_DEL_TRUE);//超时
						}
						else{
							dcMap.put("isOverTime", Const.IS_DEL_FALSE);//未超时
						}
					}
  					else{
  						if(!"1".equals(storageWarehousingEntry.getWarehousingStatus())){
  							if(DateUtil.getDayDiff(DateUtil.getDateY_M_D(), storageWarehousingEntry.getRequestDate())>0){
  								dcMap.put("isOverTime", "2");//未完成超时
  							}else{
  								dcMap.put("isOverTime", null);//未完成
  							}
  						}
  					}
  					//根据调拨单查询出库单是否出库
  					if(null!=storageWarehousingEntry.getAllotId() && storageWarehousingEntry.getAllotId()>0){
  						StorageDeliveryOrder storageDeliveryOrder =inventoryHandler.querySDOrderByAllotId(storageWarehousingEntry.getAllotId());
  						storageDeliveryOrder.getDeliveryStatus();
  						dcMap.put("deliveryStatus", storageWarehousingEntry.getWarehousingStatus());//出库单状态
  					}else{
  						dcMap.put("deliveryStatus", "");//出库单状态
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
  	
  	//查询入库单详情
  	public String queryWarehousingById(Map dataMap){
  		String result = "";
  		Map<String, Object> weMap = new HashMap<String, Object>();		
  		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
  		String warehousingEntryId = Tools.trim(dataMap.get("warehousingEntryId"));//入库单ID	
  		try {
  			//查询入库基本信息
  			StorageWarehousingEntry storageWarehousingEntry = (StorageWarehousingEntry)inventoryHandler.queryWarehousingById(Integer.valueOf(warehousingEntryId));
  			weMap.put("warehousingEntryId", storageWarehousingEntry.getWarehousingEntryId());//清洗ID
  			weMap.put("warehousingNo", storageWarehousingEntry.getWarehousingNo());//入库单编号			
  			weMap.put("requestDate", storageWarehousingEntry.getRequestDate());//要求入库日期
  			weMap.put("warehousingDate", storageWarehousingEntry.getWarehousingDate());//实际入库日期
			//查询入库单明细
			List<StorageWarehousingDtl> warehousingDtlList = inventoryHandler.queryWarehousingDtlByEntryId(Integer.valueOf(warehousingEntryId));
			if(warehousingDtlList != null){
				for (StorageWarehousingDtl storageWarehousingDtl : warehousingDtlList) {
					Map<String, Object> wdMap = new HashMap<String, Object>();					
					wdMap.put("warehousingDtlId", storageWarehousingDtl.getWarehousingDtlId());//明细ID
					wdMap.put("goodsId", storageWarehousingDtl.getGoodsInfo().getGoodsId());//入库商品ID
					wdMap.put("goodsName", storageWarehousingDtl.getGoodsInfo().getGoodsName());//入库商品
					wdMap.put("requestCount", storageWarehousingDtl.getRequestCount());//订单数量
					wdMap.put("warehousingCount", storageWarehousingDtl.getWarehousingCount());//入库商品数量
  					lstMap.add(wdMap);
				}
			}
			weMap.put("warehousingDtlList", lstMap);//入库单明细
  			result = this.toResultJsonSuccess(weMap);
  		} catch (Exception e) {
  			e.printStackTrace();
  			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
  		}
  		return result;
  	}
  	
  	/**
	 * 提交入库单
	 * 1、修改入库订单状态和实际入库日期
	 * 2、修改商品明细的实际入库数量
	 */
	public String submitWarehousing(String data){
		try {
			JSONObject dataObject=JSONObject.fromObject(data);
			String checkUserId = dataObject.getString("checkUserId");//App用户
			String warehousingEntryId = dataObject.getString("warehousingEntryId");
			String warehousingDtls = dataObject.containsKey("warehousingDtls")?dataObject.getString("warehousingDtls"):null;	
			StorageWarehousingEntry storageWarehousingEntry = (StorageWarehousingEntry)inventoryHandler.queryWarehousingById(Integer.valueOf(warehousingEntryId));
			if(storageWarehousingEntry == null) {
				return this.toFailTipJson("该订单未找到！");
			}
			if(Const.IS_STATUS_INIT.equals(storageWarehousingEntry.getWarehousingStatus())) {				
				if(StringUtils.isNotEmpty(warehousingDtls)) {
					//解析备货信息的JSON串，循环修改订单明细的备货数量
					JSONArray orderList = JSONArray.fromObject(warehousingDtls);
					for(Object obj:orderList){
						JSONObject jsonObject=JSONObject.fromObject(obj);
						String warehousingDtlId = jsonObject.getString("warehousingDtlId");
						String goodsNumber = jsonObject.getString("goodsNumber");
						StorageWarehousingDtl storageWarehousingDtl = (StorageWarehousingDtl)inventoryHandler.queryEntryDetailById(Integer.valueOf(warehousingDtlId));
						//根据出库ID查询出库单信息
						StorageInventory storageInventory =inventoryHandler.queryStorageInventory(storageWarehousingEntry.getWarehouseId(),storageWarehousingDtl.getGoodsId());
						if(storageInventory!=null){
							Double inventoryNumber = storageInventory.getInventoryNumber();//获取库存数量
							inventoryNumber = inventoryNumber+ Integer.valueOf(goodsNumber);
							storageInventory.setInventoryNumber(inventoryNumber);
							inventoryHandler.saveOrUpdateStorageInventory(storageInventory);
						}else{
							GoodsInfo goodsInfo =(GoodsInfo) goodsHandler.queryGoodsInfoById(storageWarehousingDtl.getGoodsId());
							StorageInventory sInventory = new StorageInventory();
							sInventory.setGoodsId(storageWarehousingDtl.getGoodsId());
							sInventory.setWarehouseId(storageWarehousingEntry.getWarehouseId());
							sInventory.setInventoryNumber(Double.parseDouble(goodsNumber));
							sInventory.setGoodsUnit(goodsInfo.getMeasurementUnit());
							sInventory.setNewer(true);
							inventoryHandler.saveOrUpdateStorageInventory(sInventory);
						}

						storageWarehousingDtl.setNewer(false);
						storageWarehousingDtl.setWarehousingCount(Double.valueOf(goodsNumber));
						inventoryHandler.saveOrUpdateDeliveryDetail(storageWarehousingDtl);
					}
				}
				//修改入库单状态和日期
				storageWarehousingEntry.setWarehousingStatus(Const.IS_STATUS_END);
				storageWarehousingEntry.setWarehousingDate(DateUtil.getDateY_M_DH_M_S());//实际入库日期
				storageWarehousingEntry.setNewer(false);
				inventoryHandler.saveOrUpdateDeliveryOrder(storageWarehousingEntry);
				
				//添加用户操作
				SysUser sysUser = sysFuncHandler.querySysUserById(Integer.parseInt(checkUserId));
				SysUserOp sysUserOp = new SysUserOp(sysUser.getUserId(), sysUser.getRealName(), Const.OP_TYPE_IN, storageWarehousingEntry.getWarehouseId(), storageWarehousingEntry.getWarehousingNo(), DateUtil.getDateY_M_D(), storageWarehousingEntry.getWarehousingDate(),storageWarehousingEntry.getWarehousingStatus(), 
						storageWarehousingEntry.getRemark(), "", "", "", "", "", "");
				sysUserOp.setNewer(true);
				sysFuncHandler.saveOrUpdateOp(sysUserOp);
				
				return this.toSuccessTipJson("入库成功！");
			} else {
				return this.toFailTipJson("该订单已入库过！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
	}
}
