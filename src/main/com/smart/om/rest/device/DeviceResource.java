package com.smart.om.rest.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.smart.om.biz.device.DeviceHandler;
import com.smart.om.persist.DeviceInfo;
import com.smart.om.persist.DeviceMaintenance;
import com.smart.om.rest.base.BaseResource;
import com.smart.om.rest.user.UserResource;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import com.smart.om.util.Tools;

/**
 * 设备管理Rest接口
 * @author langyuk
 *
 */
@Component("DeviceResource")
public class DeviceResource  extends BaseResource{
	private static final Logger logger = Logger.getLogger(UserResource.class);
	@Resource
	private DeviceHandler deviceHandler;
	
	//根据ID查询设备信息
	public String queryDeviceInfoById(Map dataMap){
		String result = "";
		String deviceId = Tools.trim(dataMap.get("deviceId"));//设备ID		
		try {
			DeviceInfo deviceInfo = deviceHandler.queryDeviceById(Integer.valueOf(deviceId));
			Map<String, Object> diMap = new HashMap<String, Object>();	
			diMap.put("deviceId", deviceInfo.getDeviceId());//设备ID
			diMap.put("deviceNo", deviceInfo.getDeviceNo());//设备编号
			diMap.put("deviceName", deviceInfo.getDeviceName());//设备名称					
			diMap.put("deviceLng", deviceInfo.getDeviceLng());//设备经度
			diMap.put("deviceLat", deviceInfo.getDeviceLat());//设备纬度
			diMap.put("deviceLng", deviceInfo.getDeviceAddress());//设备地址
			diMap.put("orgId", deviceInfo.getDictOrgId());//设备所属机构
			result = this.toResultJsonSuccess(diMap);
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		return result;
	}
	
	//根据条件查询设备信息
	public String queryDeviceInfos(Map dataMap){
		String result = "";
		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
		String orderId = Tools.trim(dataMap.get("orderId"));//参数	
		String pageSize = Tools.trim(dataMap.get("pageSize"));//每页显示条数
		String currentPage = Tools.trim(dataMap.get("currentPage"));//当前页	
		try {
			PageData pageData = this.getPageData(pageSize, currentPage);
			DTablePageModel pageModel = deviceHandler.queryDeviceInfo(null, pageData);
			List<DeviceInfo> deviceInfoList = pageModel.getAaData();
			if(deviceInfoList != null){
				for (DeviceInfo deviceInfo : deviceInfoList) {
					Map<String, Object> diMap = new HashMap<String, Object>();					
					diMap.put("deviceId", deviceInfo.getDeviceId());//设备ID
					diMap.put("deviceNo", deviceInfo.getDeviceNo());//设备编号
					diMap.put("deviceName", deviceInfo.getDeviceName());//设备名称					
					diMap.put("deviceLng", deviceInfo.getDeviceLng());//设备经度
					diMap.put("deviceLat", deviceInfo.getDeviceLat());//设备纬度
					diMap.put("deviceLng", deviceInfo.getDeviceAddress());//设备地址
					diMap.put("orgId", deviceInfo.getDictOrgId());//设备所属机构
					lstMap.add(diMap);
				}
			}
			result = this.toResultJsonSuccess(lstMap);
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		return result;
	}
	
	//根据查询设备维修信息
	public String queryDeviceMainById(Map dataMap){
		String result = "";
		String maintenanceId = Tools.trim(dataMap.get("maintenanceId"));//设备维修ID	
		try {
			DeviceMaintenance deviceMaintenance = deviceHandler.queryDeviceMainById(Integer.valueOf(maintenanceId));
			Map<String, Object> dmMap = new HashMap<String, Object>();	
			dmMap.put("maintenanceId", deviceMaintenance.getDeviceId());//设备ID			
			
			result = this.toResultJsonSuccess(dmMap);
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		return result;
	}
}
