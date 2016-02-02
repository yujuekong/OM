package com.smart.om.rest.maintain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.smart.om.biz.maintain.DeviceMaintainHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.DeviceMaintain;
import com.smart.om.persist.SysUser;
import com.smart.om.rest.base.BaseResource;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.PageData;
import com.smart.om.util.Tools;


@Component("DeviceMaintainResource")
public class DeviceMaintainResource extends BaseResource{
	private static final Logger logger = Logger.getLogger(DeviceMaintainResource.class);
	@Resource
	private DeviceMaintainHandler deviceMaintainHandler;//巡检
	@Resource
	private SysFuncHandler sysFuncHandler;//用户
	
	//查询巡检任务
  	public String queryDeviceMaintain(Map dataMap){
  		String result = "";
  		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
  		String checkUserId = Tools.trim(dataMap.get("checkUserId"));//App用户
  		String maintainDate = Tools.trim(dataMap.get("maintainDate"));//巡检日期
  		if(StringUtils.isBlank(maintainDate)) {//如果参数没有巡检日期，则查询当前日期
  			maintainDate = DateUtil.getDateY_M_D();
  		}
  		String maintainIsFinish = Tools.trim(dataMap.get("maintainIsFinish"));//巡检状态
  		String pageSize = Tools.trim(dataMap.get("pageSize"));//每页显示条数
  		String currentPage = Tools.trim(dataMap.get("currentPage"));//当前页	
  		PageData pageData = this.getPageData(pageSize, currentPage);
  		Map<String, Object> params = new HashMap<String, Object>();
  		params.put("maintainDate", maintainDate);
  		params.put("maintainIsFinish", maintainIsFinish);
  		SysUser user = sysFuncHandler.querySysUserById(Integer.parseInt(checkUserId));//根据用户ID 查询出用户所在地区
		Integer orgId = null;
		if(user!=null){
			orgId= user.getOrgId();//查询出用户所在地区
		}
		params.put("orgId", orgId);//查询出用户所在地区
  		try {
  			DTablePageModel pageModel = deviceMaintainHandler.queryDeviceMaintain(params, pageData);
  	  		List<DeviceMaintain> deviceMaintainList = pageModel.getAaData();
	  	  	if(deviceMaintainList != null){
		  	  	for (DeviceMaintain deviceMaintain : deviceMaintainList) {
		  	  		Map<String, Object> dmMap = new HashMap<String, Object>();
		  	  		dmMap.put("maintainId", deviceMaintain.getMaintainId());//设备保养ID
		  	  		dmMap.put("deviceId", deviceMaintain.getDeviceId());//保养设备ID
					dmMap.put("deviceNo", deviceMaintain.getDeviceNo());//保养设备编号
					dmMap.put("lastTime", deviceMaintain.getLastTime());//上次保养日期
					dmMap.put("maintainIsFinish", deviceMaintain.getMaintainIsFinish());//巡检是否完成
					dmMap.put("thisTime", deviceMaintain.getThisTime());//本次保养日期
					
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
  	//查询巡检任务
  	public String queryDeviceMaintainById(Map dataMap){
  		String result = "";
		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
		String maintainId = Tools.trim(dataMap.get("maintainId"));//设备保养ID
		try {
			DeviceMaintain deviceMaintain =deviceMaintainHandler.queryDeviceMaintainById(Integer.parseInt(maintainId));
			Map<String, Object> dmMap = new HashMap<String, Object>();
  	  		dmMap.put("maintainId", deviceMaintain.getMaintainId());//设备保养ID
  	  		dmMap.put("deviceId", deviceMaintain.getDeviceId());//保养设备ID
			dmMap.put("deviceNo", deviceMaintain.getDeviceNo());//保养设备编号
			dmMap.put("lastTime", deviceMaintain.getLastTime());//上次保养日期
			dmMap.put("maintainIsFinish", deviceMaintain.getMaintainIsFinish());//巡检是否完成
			dmMap.put("thisTime", deviceMaintain.getThisTime());//本次保养日期
			dmMap.put("remark", deviceMaintain.getRemark());//保养备注
			
			lstMap.add(dmMap);
			
			result = this.toResultJsonSuccess(lstMap);
		} catch (Exception e) {
			e.printStackTrace();
  			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		return result;
  	}
  	
  	//提交巡检任务
  	public String submitDeviceMaintain(Map dataMap){
  		String result = "";
  		String maintainId = Tools.trim(dataMap.get("maintainId"));//设备保养ID
  		String remark = Tools.trim(dataMap.get("remark"));//保养备注
  		String checkUserId = Tools.trim(dataMap.get("checkUserId"));//App用户
  		try {
  			DeviceMaintain deviceMaintain =deviceMaintainHandler.queryDeviceMaintainById(Integer.parseInt(maintainId));
  			deviceMaintain.setRemark(remark);
  			if(deviceMaintain.getThisTime()==null){
  				deviceMaintain.setThisTime(DateUtil.getDateY_M_D());
			}
  			deviceMaintain.setMaintainIsFinish("1");
  			deviceMaintain.setMaintainUser(Integer.parseInt(checkUserId));
  			deviceMaintainHandler.saveOrUpdateDeviceMaintain(deviceMaintain);
  			
  			result = this.toSuccessTipJson("保存成功！");
  		} catch (Exception e) {
			e.printStackTrace();
  			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
  		return result;
  	}
}
