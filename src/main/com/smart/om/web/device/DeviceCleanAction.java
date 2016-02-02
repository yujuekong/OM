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
import com.smart.om.persist.DeviceClean;
import com.smart.om.persist.SysUser;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.web.base.BaseAction;

/**
 * 设备清洗管理Action
 * @author lc
 *
 */
@Namespace("/view/device/deviceClean")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class DeviceCleanAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(DeviceCleanAction.class);
	@Resource
	private DeviceHandler deviceHandler;
	
	private DeviceClean deviceClean;
	
	/**
	 * 查询清洗信息
	 * @return
	 */
	@Action(value="queryDeviceCleanList",results = { 
			@Result(name = SUCCESS, location = "/view/device/deviceCleanList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String queryDeviceCleanList() {
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
	 * 分页查询查询所有清洗设备信息
	 */
	@Action(value = "queryDeviceClean", results = {
			@Result(name = SUCCESS, location = "/view/device/deviceCleanList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public void queryDeviceClean() {
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
			DTablePageModel dtPageModel = deviceHandler.queryDeviceClean(params, super.getPageData());
			String jsonData = JSONUtil.serialize(dtPageModel);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 进入添加或编辑清洗设备信息页面
	 */
	@Action(value = "addOrModifyDeviceClean", results = {
			@Result(name = SUCCESS, location = "/view/device/deviceCleanAdd.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String addOrModifyDeviceClean() {
		String str = this.getRequestParm().getParameter("cleanId");
		if(!str.equals("add")){
			Integer id =Integer.valueOf(str);
			DeviceClean dClean = (DeviceClean) deviceHandler.queryDeviceCleanById(id);
			getRequest().put("deviceClean", dClean);
		}
		return SUCCESS;
	}
	
	/**
	 * 添加或修改清洗设备信息
	 */
	@Action(value = "saveOrUpdateDeviceClean", results = {
			@Result(name = SUCCESS, location = "/view/device/deviceCleanList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String saveOrUpdateDeviceClean(){
		Integer str =  deviceClean.getCleanId();
		try {			
			if(str == null ){
				deviceClean.setNewer(true);		
				deviceClean.setCleanNo(DateUtil.getNo("Q"));
				deviceClean.setCleanStatus(Const.IS_VALID_FALSE);
			}
			deviceClean.setCleanDate(DateUtil.getDateY_M_DH_M_S());
			deviceHandler.saveOrUpdateDeviceCleanDAO(deviceClean);
		} catch (Exception e) {
			this.getRequest().put("where", "添加或修改清洗设备信息");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
		}
		
		return SUCCESS;
	}
	
	/**
	 * 删除清洗设备信息
	 */
	@Action(value = "delDeviceCleanById", results = {
			@Result(name = SUCCESS, location = "/view/device/deviceCleanList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String delDeviceCleanById(){
		try {
			Integer id =Integer.valueOf(this.getRequestParm().getParameter("cleanId"));
			deviceHandler.delDeviceCleanById(id);
		} catch (Exception e) {
			this.getRequest().put("where", "删除清洗设备信息");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
		}
		
		return SUCCESS;	
	}
	/**
	 * 分页查询所有用户信息
	 */
	@Action(value = "queryUser")
	public void queryUser() {
		try{
			//搜索关键字
			String keyword = this.getRequestParm().getParameter("keyword");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			DTablePageModel dtPageModel = deviceHandler.queryUser(params, super.getPageData());
			String jsonData = JSONUtil.serialize(dtPageModel);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			this.getRequest().put("where", "删除清洗设备信息");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
		}
	}
	
	/**
	 * 修改清洗状态
	 * @return
	 * @throws IOException
	 */
	@Action(value="changeCleanStatus")
	public String changeCleanStatus() throws IOException {
		try {
			String cleanId = this.getRequestParm().getParameter("cleanId");
			String isDisable = this.getRequestParm().getParameter("isDisable");
			DeviceClean deviceClean = (DeviceClean)deviceHandler.queryDeviceCleanById(Integer.valueOf(cleanId));
			deviceClean.setCleanStatus(isDisable);
			deviceClean.setFinishTime(DateUtil.getDateY_M_DH_M_S());
			deviceClean.setNewer(false);
			deviceHandler.saveOrUpdateDeviceCleanDAO(deviceClean);
			this.getResponse().getWriter().print(true);
		} catch (Exception e) {
			e.printStackTrace();
			this.getResponse().getWriter().print(false);
		}
		return null;
	}
	
	/**
	 * 进入设备清洗详情页面
	 */
	@Action(value = "deviceCleanDtl", results = {
			@Result(name = SUCCESS, location = "/view/device/deviceCleanDetail.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String deviceCleanDtl() {
		String result = SUCCESS;
		try{
			String cleanId = this.getRequestParm().getParameter("cleanId");
			if(StringUtils.isNotBlank(cleanId)){
				Integer cId =Integer.valueOf(cleanId);
				DeviceClean dClean = (DeviceClean) deviceHandler.queryDeviceCleanById(cId);
				getRequest().put("deviceClean", dClean);
			}
		}catch (Exception e) {
	        this.getRequest().put("where", " 查询所有数据字典");
	        this.getRequest().put("errors", "系统正忙，请联系管理员");
	        result = ERROR;
	    }
		return result;
	}
	public DeviceClean getDeviceClean() {
		return deviceClean;
	}
	public void setDeviceClean(DeviceClean deviceClean) {
		this.deviceClean = deviceClean;
	}
}
