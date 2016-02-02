package com.smart.om.rest.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.smart.om.biz.device.DeviceHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.DeviceInfo;
import com.smart.om.persist.DeviceMaintenance;
import com.smart.om.persist.SysUser;
import com.smart.om.persist.SysUserOp;
import com.smart.om.rest.base.BaseResource;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.PageData;
import com.smart.om.util.Tools;

/**
 * 设备维修管理Rest接口
 * @author langyuk
 *
 */
@Component("DeviceMaintenanceResource")
public class DeviceMaintenanceResource extends BaseResource{
	private static final Logger logger = Logger.getLogger(DeviceMaintenanceResource.class);
	@Resource
	private DeviceHandler deviceHandler;
	@Resource
	private SysFuncHandler sysFuncHandler;
	
	//查询登录用户所有清洗订单
	public String queryDeviceMaintenances(Map dataMap){
		String result = "";
		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
		String userId = Tools.trim(dataMap.get("userId"));//登录用户	
		String orderDate = Tools.trim(dataMap.get("orderDate"));//维修日期
		String deviceId = Tools.trim(dataMap.get("deviceId"));//设备ID
		String deviceNo =Tools.trim(dataMap.get("deviceNo"));//设备编号
		if(StringUtils.isBlank(deviceNo)){
			if(StringUtils.isBlank(orderDate)) {//如果参数没有维修日期，则查询当前日期
				orderDate = DateUtil.getDateY_M_D();
			}
		}
		String maintenanceStatus = Tools.trim(dataMap.get("maintenanceStatus"));//维修状态
		String pageSize = Tools.trim(dataMap.get("pageSize"));//每页显示条数
		String currentPage = Tools.trim(dataMap.get("currentPage"));//当前页	
		try {
			PageData pageData = this.getPageData(pageSize, currentPage);
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("app", "app");
			params.put("userId", userId);
			params.put("orderDate", orderDate);
			params.put("maintenanceStatus", maintenanceStatus);
			params.put("deviceId", deviceId);
			DTablePageModel pageModel = deviceHandler.queryDeviceMain(params, pageData);
			List<DeviceMaintenance> deviceMaintenanceList = pageModel.getAaData();			
			if(deviceMaintenanceList != null){
				for (DeviceMaintenance deviceMaintenance : deviceMaintenanceList) {
					Map<String, Object> dmMap = new LinkedHashMap<String, Object>();					
					dmMap.put("maintenanceId", deviceMaintenance.getMaintenanceId());//维修ID
					dmMap.put("deviceId", deviceMaintenance.getDeviceId());//维修ID
					dmMap.put("maintenanceNo", deviceMaintenance.getMaintenanceNo());//维修编号					
					dmMap.put("deviceAddress", deviceMaintenance.getDeviceInfo().getDeviceAddress());//设备地址
					dmMap.put("orderTime", deviceMaintenance.getMaintenanceDate());//下单时间
					dmMap.put("requestTime", deviceMaintenance.getExpEndTime());//要求完成时间
					dmMap.put("finishTime", deviceMaintenance.getFinishTime());//实际完成时间
					dmMap.put("maintenanceStatus", deviceMaintenance.getMaintenanceStatus());//维修状态
					dmMap.put("maintenanceReason", deviceMaintenance.getMaintenanceReason());//维修说明
					
					
					if(StringUtils.isNotBlank(deviceMaintenance.getFinishTime())){
						if(DateUtil.getTimeDiff(deviceMaintenance.getFinishTime(), deviceMaintenance.getExpEndTime())){
							dmMap.put("isOverTime", Const.IS_DEL_TRUE);//超时
						}
						else{
							dmMap.put("isOverTime", Const.IS_DEL_FALSE);//未超时
						}
					}
					else{
						if("1".equals(deviceMaintenance.getMaintenanceStatus())){
							if(DateUtil.getTimeDiff(DateUtil.getDateY_M_DH_M_S(),deviceMaintenance.getExpEndTime())){
								dmMap.put("isOverTime", "2");//未完成超时
							}else{
								dmMap.put("isOverTime", null);//未完成
							}
						}
						
					}
					lstMap.add(dmMap);
				}
			}
			result = this.toResultJsonSuccess(lstMap);
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		return result;
	}
	
	//查询维修订单详情
	public String queryDeviceMaintenanceById(Map dataMap){
		String result = "";
		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
		String maintenanceId = Tools.trim(dataMap.get("maintenanceId"));//维修ID	
		try {
			DeviceMaintenance deviceMaintenance = deviceHandler.queryDeviceMainById(Integer.valueOf(maintenanceId));
			Map<String, Object> dmMap = new HashMap<String, Object>();	
			if(deviceMaintenance != null){
				dmMap.put("maintenanceId", deviceMaintenance.getMaintenanceId());//维修ID
				dmMap.put("deviceId", deviceMaintenance.getDeviceId());//维修ID
				dmMap.put("maintenanceNo", deviceMaintenance.getMaintenanceNo());//维修编号					
				dmMap.put("deviceAddress", deviceMaintenance.getDeviceInfo().getDeviceAddress());//设备地址
				dmMap.put("orderTime", deviceMaintenance.getMaintenanceDate());//下单时间
				dmMap.put("requestTime", deviceMaintenance.getExpEndTime());//要求完成时间
				dmMap.put("finishTime", deviceMaintenance.getFinishTime());//实际完成时间
				dmMap.put("maintenanceStatus", deviceMaintenance.getMaintenanceStatus());//维修状态
				dmMap.put("remark", deviceMaintenance.getRemark());//维修状态
				dmMap.put("maintenancePic1", deviceMaintenance.getMaintenancePic1());//维修图片1
				dmMap.put("maintenancePic2", deviceMaintenance.getMaintenancePic2());//维修图片2
				dmMap.put("maintenancePic3", deviceMaintenance.getMaintenancePic3());//维修图片3
				dmMap.put("maintenancePic4", deviceMaintenance.getMaintenancePic4());//维修图片4
				dmMap.put("maintenancePic5", deviceMaintenance.getMaintenancePic5());//维修图片5
				dmMap.put("maintenanceType", deviceMaintenance.getMaintenanceType());//故障类型
				dmMap.put("maintenanceReason", deviceMaintenance.getMaintenanceReason());//维修说明
			}			
			result = this.toResultJsonSuccess(dmMap);
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		return result;
	}
	
	/**
	 * 完成设备维修
	 * @param dataMap
	 * @return
	 */
	public String submitDeviceMaintenance(String data){
		try {
			JSONObject dataObject=JSONObject.fromObject(data);
			String maintenanceId = Tools.trim(dataObject.getString("maintenanceId"));//维修的订单ID
			String remark = Tools.trim(dataObject.getString("remark"));//维修备注
			String maintenancePics = dataObject.containsKey("maintenancePics")?dataObject.getString("maintenancePics"):null;	//上传的图片
			
			DeviceMaintenance deviceMaintenance = deviceHandler.queryDeviceMainById(Integer.valueOf(maintenanceId));			
			//获得图片地址
			String rootPath = this.getClass().getResource("/").getPath();
			rootPath = rootPath.substring(0, rootPath.indexOf("WEB-INF")-1);
			if(deviceMaintenance != null) {
				if(StringUtils.isNotBlank(remark)) {
					deviceMaintenance.setRemark(remark);
				}
				if(StringUtils.isNotEmpty(maintenancePics)) {
					//解析出库信息的JSON串，循环修改订单明细的实际出库数量
					JSONArray picList = JSONArray.fromObject(maintenancePics);
					int i = 0;
					for(Object obj:picList){
						i++;
						String fileName = "";//图片文件地址
						JSONObject jsonObject=JSONObject.fromObject(obj);
						String maintenancePic = jsonObject.getString("maintenancePic");
						if(StringUtils.isNotBlank(maintenancePic)) {
							fileName = Const.DEVICE_MAINTENANCE_IMG_PATH + "/" + deviceMaintenance.getMaintenanceNo() + i + ".png";
							Tools.base64StringToImage(maintenancePic,"png",rootPath + fileName);							
						}
						//设置图片文件地址
						if(i == 1){
							deviceMaintenance.setMaintenancePic1(fileName);
						}
						else if(i == 2){
							deviceMaintenance.setMaintenancePic2(fileName);
						}
						else if(i == 3){
							deviceMaintenance.setMaintenancePic3(fileName);
						}
						else if(i == 4){
							deviceMaintenance.setMaintenancePic4(fileName);
						}
						else if(i == 5){
							deviceMaintenance.setMaintenancePic5(fileName);
						}
					}
				}				
				
				deviceMaintenance.setNewer(false);
				if(deviceMaintenance.getFinishTime()==null){
					deviceMaintenance.setFinishTime(DateUtil.getDateY_M_DH_M_S());
				}
				deviceMaintenance.setMaintenanceStatus(Const.IS_VALID_TRUE);
				deviceMaintenance = deviceHandler.saveOrUpdateDeviceMainDAO(deviceMaintenance);
				
				//添加用户操作
				SysUser sysUser = sysFuncHandler.querySysUserById(deviceMaintenance.getMaintenanceUser());
				SysUserOp sysUserOp = new SysUserOp(sysUser.getUserId(), sysUser.getRealName(), Const.OP_TYPE_MAINTENANCE, deviceMaintenance.getMaintenanceId(), deviceMaintenance.getMaintenanceNo(), DateUtil.getDateY_M_D(), deviceMaintenance.getFinishTime(),deviceMaintenance.getMaintenanceStatus(), 
						 deviceMaintenance.getRemark(), deviceMaintenance.getMaintenancePic1(), deviceMaintenance.getMaintenancePic2(), deviceMaintenance.getMaintenancePic3(), deviceMaintenance.getMaintenancePic4(), deviceMaintenance.getMaintenancePic5(), deviceMaintenance.getMaintenanceReason());
				sysUserOp.setNewer(true);
				sysFuncHandler.saveOrUpdateOp(sysUserOp);
				
				return this.toSuccessTipJson("保存成功！");
			} else {
				return this.toFailTipJson("该清洗订单未找到！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
	}
	/** 根据设备标号查询设备信息 **/
	public List<DeviceInfo> queryDeviceByNo(String deviceNo){
		return deviceHandler.queryDeviceByNo(deviceNo);
	}
}
