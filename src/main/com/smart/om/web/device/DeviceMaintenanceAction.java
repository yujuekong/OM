package com.smart.om.web.device;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import com.smart.om.biz.device.DeviceHandler;
import com.smart.om.persist.DeviceMaintenance;
import com.smart.om.persist.SysUser;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.web.base.BaseAction;

/**
 * 设备类型管理Action
 * @author lc
 *
 */
@Namespace("/view/device/deviceMain")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class DeviceMaintenanceAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(DeviceMaintenanceAction.class);
	@Resource
	private DeviceHandler deviceHandler;
	
	private DeviceMaintenance deviceMain;	
	
	/**
	 * 查询维护信息
	 * @return
	 */
	@Action(value="queryDeviceMaintenance",results = { @Result(name = SUCCESS, location = "/view/device/deviceMaintenanceList.jsp"),
			   @Result(name = ERROR, location = "/view/error.jsp") })
	public String queryDeviceMaintenance() {
		try {
			String deviceId = this.getRequestParm().getParameter("deviceId");
			getRequest().put("deviceId", deviceId);
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", "查询维护信息");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");	
		}
		return SUCCESS;
	}
	
	/**
	 * 查询所有设备信息
	 */
	@Action(value = "queryDeviceMain")
	public void queryDeviceMain() {
		try{
			SysUser sysUser = (SysUser) this.getSession().get("account");
			//搜索关键字
			String deviceId = super.getRequestParm().getParameter("deviceId");
			String keyword = this.getRequestParm().getParameter("keyword");
			String startDate = this.getRequestParm().getParameter("startDate");
			String endDate = this.getRequestParm().getParameter("endDate");
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			params.put("deviceId", deviceId);
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			
			params.put("orgId", sysUser.getOrgId());//用户所属服务站
			DTablePageModel dtPageModel = deviceHandler.queryDeviceMain(params, super.getPageData());
			String jsonData = JSONUtil.serialize(dtPageModel);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 进入添加或编辑设备维护页面
	 */
	@Action(value = "addOrModifyDeviceMain", results = {
			@Result(name = SUCCESS, location = "/view/device/deviceMaintenanceAdd.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String addOrModifyDeviceMain() {
		String result = SUCCESS;
		try{
			String maintenanceId = this.getRequestParm().getParameter("maintenanceId");
			if(StringUtils.isNotBlank(maintenanceId)){
				Integer mId =Integer.valueOf(maintenanceId);
				DeviceMaintenance dInfo = (DeviceMaintenance) deviceHandler.queryDeviceMainById(mId);
				getRequest().put("deviceMain", dInfo);
			}
		}catch (Exception e) {
	        this.getRequest().put("where", " 查询所有数据字典");
	        this.getRequest().put("errors", "系统正忙，请联系管理员");
	        result = ERROR;
	    }
		return result;
	}
	
	/**
	 * 添加或修改设备维护信息
	 */
	@Action(value = "saveOrUpdateDeviceMain", results = {
			@Result(name = SUCCESS, location = "/view/device/deviceMaintenanceList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String saveOrUpdateDeviceMain(){
		String result = SUCCESS;
		try{
			Integer str =  deviceMain.getMaintenanceId();
			if(str == null ){
				deviceMain.setNewer(true);
				deviceMain.setMaintenanceNo(DateUtil.getNo("W"));
				deviceMain.setMaintenanceStatus(Const.IS_VALID_FALSE);
			}
			deviceMain.setMaintenanceDate(DateUtil.getDateY_M_DH_M_S());
			deviceHandler.saveOrUpdateDeviceMainDAO(deviceMain);
		}catch (Exception e) {
	        this.getRequest().put("where", " 查询所有数据字典");
	        this.getRequest().put("errors", "系统正忙，请联系管理员");
	        result = ERROR;
	    }
		return result;
	}
	/**
	 * 删除设备信息
	 */
	@Action(value = "delDeviceMainById", results = {
			@Result(name = SUCCESS, location = "/view/device/deviceMaintenanceList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String delDeviceMainById(){
		String result = SUCCESS;
		try{
			Integer id =Integer.valueOf(this.getRequestParm().getParameter("maintenanceId"));
			deviceHandler.delDeviceMainById(id);
		}catch (Exception e) {
	        this.getRequest().put("where", " 查询所有数据字典");
	        this.getRequest().put("errors", "系统正忙，请联系管理员");
	        result = ERROR;
	    }
		return result;
	}
	/**
	 * 修改维护状态
	 * @return
	 * @throws IOException
	 */
	@Action(value="changeMainStatus")
	public String changeMainStatus() throws IOException {
		try {
			String maintenanceId = this.getRequestParm().getParameter("maintenanceId");
			String isDisable = this.getRequestParm().getParameter("isDisable");
			DeviceMaintenance deviceMain = (DeviceMaintenance)deviceHandler.queryDeviceMainById(Integer.valueOf(maintenanceId));
			deviceMain.setMaintenanceStatus(isDisable);
			deviceMain.setFinishTime(DateUtil.getDateY_M_DH_M_S());
			deviceMain.setNewer(false);
			deviceHandler.saveOrUpdateDeviceMainDAO(deviceMain);
			this.getResponse().getWriter().print(true);
		} catch (Exception e) {
			e.printStackTrace();
			this.getResponse().getWriter().print(false);
		}
		return null;
	}
	/**
	 * 进入设备维护详情页面
	 */
	@Action(value = "deviceMainDtl", results = {
			@Result(name = SUCCESS, location = "/view/device/deviceMaintenanceDetail.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String deviceMainDtl() {
		String result = SUCCESS;
		try{
			String maintenanceId = this.getRequestParm().getParameter("maintenanceId");
			if(StringUtils.isNotBlank(maintenanceId)){
				Integer mId =Integer.valueOf(maintenanceId);
				DeviceMaintenance dInfo = (DeviceMaintenance) deviceHandler.queryDeviceMainById(mId);
				getRequest().put("deviceMain", dInfo);
			}
		}catch (Exception e) {
	        this.getRequest().put("where", " 查询所有数据字典");
	        this.getRequest().put("errors", "系统正忙，请联系管理员");
	        result = ERROR;
	    }
		return result;
	}
	
	public DeviceMaintenance getDeviceMain() {
		return deviceMain;
	}
	public void setDeviceMain(DeviceMaintenance deviceMain) {
		this.deviceMain = deviceMain;
	}
}
