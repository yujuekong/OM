package com.smart.om.web.device;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import com.smart.om.biz.device.DeviceHandler;
import com.smart.om.persist.DeviceInfo;
import com.smart.om.persist.DeviceType;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.web.base.BaseAction;

/**
 * 设备类型管理Action
 * @author lc
 *
 */
@Namespace("/view/device/deviceType")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class DeviceTypeAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(DeviceTypeAction.class);
	@Resource
	private DeviceHandler deviceHandler;
	
	private DeviceType deviceType;	
	private DeviceInfo deviceInfo;	
	
	/**
	 * 分页查询所有设备类型
	 */
	@Action(value = "queryDeviceType", results = {
			@Result(name = SUCCESS, location = "/view/device/deviceTypeList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public void queryDeviceType() {
		try{
			//搜索关键字
			String keyword = this.getRequestParm().getParameter("keyword");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			DTablePageModel dtPageModel = deviceHandler.queryDeviceType(params, super.getPageData());
			String jsonData = JSONUtil.serialize(dtPageModel);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询所有设备类型信息页面
	 */
	@Action(value = "queryAllDeviceType")
	public void queryAllDeviceType() {
		try {
			List<DeviceType> dType = (List<DeviceType>) deviceHandler.queryAllDeviceType();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(JSONArray.fromObject(dType));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 进入添加或编辑设备类型信息页面
	 */
	@Action(value = "addOrModifyDeviceType", results = {
			@Result(name = SUCCESS, location = "/view/device/deviceTypeAdd.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String addOrModifyDeviceType() {
		String result = SUCCESS;
		try{
			String typeId = this.getRequestParm().getParameter("deviceTypeId");
			if(StringUtils.isNotBlank(typeId)){
				Integer id =Integer.valueOf(typeId);
				DeviceType dType = (DeviceType) deviceHandler.queryDTypeById(id);
				getRequest().put("deviceType", dType);
			}
		}catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}
	/**
	 * 添加或修改设备类型信息
	 */
	@Action(value = "saveOrUpdateDeviceType", results = {
			@Result(name = SUCCESS, location = "/view/device/deviceTypeList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String saveOrUpdateDeviceType(){
		String result = SUCCESS;
		try{
			Integer typeId =  deviceType.getDeviceTypeId();
			deviceType.setIsDel(Const.IS_DEL_FALSE);
			if(typeId == null ){
				deviceType.setNewer(true);
				deviceType.setDeviceTypeNo(DateUtil.getNo("L"));
			}
			deviceHandler.saveOrUpdateDeviceTypeDAO(deviceType);
		}catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}
	/**
	 * 删除设备类型信息
	 */
	@Action(value = "delDeviceTypeById", results = {
			@Result(name = SUCCESS, location = "/view/device/deviceTypeList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String delDeviceTypeById(){
		String result = SUCCESS;
		try{
			Integer typeId =Integer.valueOf(this.getRequestParm().getParameter("deviceTypeId"));
			DeviceType dType = (DeviceType) deviceHandler.queryDTypeById(typeId);
			dType.setIsDel(Const.IS_DEL_TRUE);			
			deviceHandler.saveOrUpdateDeviceTypeDAO(dType);
			//删除设备类型下的所有设备
			deviceHandler.delDeviceInfoByType(typeId);
		}catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;	
	}
	
	public DeviceInfo getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	public DeviceType getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}
}
