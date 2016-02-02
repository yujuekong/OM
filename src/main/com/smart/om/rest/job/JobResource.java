package com.smart.om.rest.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.smart.om.biz.device.DeviceHandler;
import com.smart.om.biz.inventory.InventoryHandler;
import com.smart.om.biz.maintain.DeviceMaintainHandler;
import com.smart.om.biz.region.RegionHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.DeviceClean;
import com.smart.om.persist.DeviceMaintain;
import com.smart.om.persist.DeviceMaintenance;
import com.smart.om.persist.DispatchingOrder;
import com.smart.om.persist.StorageDeliveryOrder;
import com.smart.om.persist.StorageWarehousingEntry;
import com.smart.om.persist.SysRoleAuth;
import com.smart.om.rest.base.BaseResource;
import com.smart.om.util.Const;
import com.smart.om.util.Tools;

@Component("JobResource")
public class JobResource  extends BaseResource{
	private static final Logger logger = Logger.getLogger(JobResource.class);
	@Resource
	private SysFuncHandler sysFuncHandler; 
	@Resource
	private DeviceHandler deviceHandler;//设备
	@Resource
	private InventoryHandler inventoryHandler;//出库入库
	@Resource
	private RegionHandler regionHandler;//配送
	@Resource
	private DeviceMaintainHandler deviceMaintainHandler;//巡检
	
	//查询个人任务
	public String queryUserJob(Map dataMap){
  		String result = "";
  		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
  		String checkUserId = Tools.trim(dataMap.get("checkUserId"));//App用户
  		if(!"".equals(checkUserId) && null!=checkUserId){
  			List<SysRoleAuth> sysRoleAuthList = sysFuncHandler.queryAppMenuByUser(Integer.parseInt(checkUserId));
  			
  			if(sysRoleAuthList!=null){
  				try {
  				 List<Map<Object, Object>> list  = new ArrayList();
	  				for (int i = 0; i < sysRoleAuthList.size(); i++){
	  					    for  ( int j=sysRoleAuthList.size()-1;j>i;j -- ){
	  					    	SysRoleAuth sysRoleAuthI = sysRoleAuthList.get(i);
	  					    	SysRoleAuth sysRoleAuthJ = sysRoleAuthList.get(j);
	  					    	if  (sysRoleAuthJ.getAuthId().equals(sysRoleAuthI.getAuthId()))   { 
	  					    		sysRoleAuthList.remove(j); 
	  						      } 
	  					    } 
	  					Map<String, Object> dmMap = new LinkedHashMap<String, Object>();		
	  					SysRoleAuth sysRoleAuth = sysRoleAuthList.get(i);
	  					
	  					Integer authId = sysRoleAuth.getAuthId();
	  					if(authId==72){//设备维修
	  						List<DeviceMaintenance> deviceMaintenanceList = deviceHandler.queryDeviceMaintenanceList(Integer.parseInt(checkUserId));
	  						if(null!=deviceMaintenanceList){
	  							dmMap.put("authId", authId);
	  							dmMap.put("job", "设备维修未完成有"+deviceMaintenanceList.size()+"条");
	  						}
	  					}else if(authId==73){//设备清洗
	  						List<DeviceClean> deviceCleanList = deviceHandler.queryDeviceCleanList(Integer.parseInt(checkUserId));
	  						if(null!=deviceCleanList){
	  							dmMap.put("authId", authId);
	  							dmMap.put("job", "设备清洗未完成有"+deviceCleanList.size()+"条");
	  						}
	  					}else if(authId==74){//商品入库
	  						List<StorageWarehousingEntry> storageWarehousingEntryList =inventoryHandler.queryWarehousingList(Integer.parseInt(checkUserId));
	  						if(null!=storageWarehousingEntryList){
	  							dmMap.put("authId", authId);
	  							dmMap.put("job", "入库未完成有"+storageWarehousingEntryList.size()+"条");
	  						}
	  					}else if(authId==75){//商品出库
	  						List<StorageDeliveryOrder> storageDeliveryOrderList = inventoryHandler.queryStorageDeliveryOrderList(Integer.parseInt(checkUserId),1);
	  						if(null!=storageDeliveryOrderList){
	  							dmMap.put("authId", authId);
	  							dmMap.put("job", "出库未完成有"+storageDeliveryOrderList.size()+"条");
	  						}
	  					}else if(authId==76){//出库处理
	  						List<StorageDeliveryOrder> storageDeliveryOrderList = inventoryHandler.queryStorageDeliveryOrderList(Integer.parseInt(checkUserId),2);
	  						if(null!=storageDeliveryOrderList){
	  							dmMap.put("authId", authId);
	  							dmMap.put("job", "出库处理未完成有"+storageDeliveryOrderList.size()+"条");
	  						}
	  					}else if(authId==77){//商品配送
	  						List<DispatchingOrder> dispatchingOrderList = regionHandler.queryDispatchingOrderList(Integer.parseInt(checkUserId));
	  						if(null!=dispatchingOrderList){
	  							dmMap.put("authId", authId);
	  							dmMap.put("job", "配送单未完成有"+dispatchingOrderList.size()+"条");
	  						}
	  					}else if(authId==79){//巡查保养
	  						List<DeviceMaintain> deviceMaintainList = deviceMaintainHandler.queryDeviceMaintainList(Integer.parseInt(checkUserId));
	  						if(null!=deviceMaintainList){
	  							dmMap.put("authId", authId);
	  							dmMap.put("job", "巡检未完成有"+deviceMaintainList.size()+"条");
	  						}
	  					}
	  					if(!dmMap.isEmpty() && null!=dmMap){
	  						lstMap.add(dmMap);
	  					}
	  					
	  				}
  				} catch (Exception e) {
  					e.printStackTrace();
  		  			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
				}
  			}
  		}
  		result = this.toResultJsonSuccess(lstMap);
  		return result;
  	}
  		
}
