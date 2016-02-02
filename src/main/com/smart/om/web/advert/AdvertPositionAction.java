package com.smart.om.web.advert;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import com.smart.om.biz.advert.AdvertHandler;
import com.smart.om.biz.device.DeviceHandler;
import com.smart.om.dto.advert.AdvertPositionDto;
import com.smart.om.persist.AdvertDevice;
import com.smart.om.persist.AdvertInfo;
import com.smart.om.persist.DeviceInfo;
import com.smart.om.persist.SysUser;
import com.smart.om.rest.base.BaseController;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.web.base.BaseAction;

/**
 * 广告位置管理Action
 * @author CA
 *
 */
@Namespace("/view/advert/advertPosition")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class AdvertPositionAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(AdvertPositionAction.class);
	
	private static final BaseController baseController = new BaseController(); 
	@Resource
	private AdvertHandler advertHandler;
	@Resource
	private DeviceHandler deviceHandler; 
	
	private AdvertDevice advertDevice;
	
	private List<AdvertDevice> advertDeviceDt;
	
	/**
	 * 查询广告设备分页信息
	 */
	@Action(value = "queryAdvertPositionPage")
	public void queryAdvertPositionPage() {
		try {
			SysUser sysUser = (SysUser) this.getSession().get("account");
			// 搜索关键字
			String positionStatus = this.getRequestParm().getParameter("positionStatus");
			String deviceStatus = this.getRequestParm().getParameter("deviceStatus");
			String keyword = this.getRequestParm().getParameter("keyword");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			if(sysUser.getOrgId() != null){
				params.put("proviceId", sysUser.getDictProviceId());
				params.put("regionId", sysUser.getDictRegionId());
				params.put("orgId", sysUser.getOrgId());
			}
			if(StringUtils.isNotBlank(positionStatus)){
				params.put("positionStatus", positionStatus);
			}
			if(StringUtils.isNotBlank(deviceStatus)){
				params.put("deviceStatus", deviceStatus);
			}
			DTablePageModel dtPageModel = advertHandler.queryAdvertPositionPage(
					params, super.getPageData());
			String jsonData = JSONUtil.serialize(dtPageModel);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据设备名称搜索广告投放信息
	 */
	@Action(value="searchAdvertDevice",results={
			@Result(name=SUCCESS,location="/view/advert/adPositionListSearch.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String searchAdvertDevice(){
		try{
			String advertDeviceName = new String(this.getRequestParm().getParameter("advertDeviceName").getBytes("ISO-8859-1"),"utf-8");
			if(StringUtils.isNotBlank(advertDeviceName)){
				this.getRequest().put("advertDeviceName", advertDeviceName);
				return SUCCESS;
			}
			else{
				this.getRequest().put("errors", "系统异常，请重试！");
				return ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试！");
			return ERROR;
		}
	}
	
	/**
	 * 批量修改广告投放信息保存
	 */
	@Action(value="updateAdvertDevice",results={
			@Result(name=SUCCESS,location="/view/advert/adInfoList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String updateAdvertDevice(){
		try{
			String advertInfoId = this.getRequestParm().getParameter("advertInfoId");
			Integer adInfoId = Integer.valueOf(advertInfoId);
			AdvertInfo advertInfo = (AdvertInfo) advertHandler.queryAdvertInfoById(adInfoId);
			double totalFee = 0.0;//修改后广告总价
			double changeFee = 0.0;//变化的价格
			for(AdvertDevice advertDevice:advertDeviceDt){
				totalFee += advertDevice.getAdvertFee();//计算添加的设备总费用
			}
			if(advertInfo.getAdvertTotalFee() != null){
				changeFee = totalFee -  advertInfo.getAdvertTotalFee();
				advertInfo.setAdvertTotalFee(advertInfo.getAdvertTotalFee() + changeFee);
				if(advertInfo.getPayFee() != null){
					if((advertInfo.getAdvertTotalFee() + changeFee) <= advertInfo.getPayFee()){
						advertInfo.setPayStatus(Const.IS_VALID_TRUE);
					}
					else{
						advertInfo.setPayStatus(Const.IS_VALID_FALSE);
					}
				}
				else{
					advertInfo.setPayStatus(Const.IS_VALID_FALSE);
				}
				
				advertHandler.saveOrUpdateAdvertInfo(advertInfo);
			}else{
				advertInfo.setAdvertTotalFee(totalFee);
				if(advertInfo.getPayFee() != null){
					if(totalFee <= advertInfo.getPayFee()){
						advertInfo.setPayStatus(Const.IS_VALID_TRUE);
					}
					else{
						advertInfo.setPayStatus(Const.IS_VALID_FALSE);
					}
				}else{
					advertInfo.setPayStatus(Const.IS_VALID_FALSE);
				}
				advertHandler.saveOrUpdateAdvertInfo(advertInfo);
			}
		/*List<AdvertDevice> deviceList = advertHandler.queryAdvertDevice(adInfoId);
			for(AdvertDevice advertDevice:deviceList){
				advertHandler.deleteAdvertDevice(advertDevice);
			}*/
			for(AdvertDevice advertDevice:advertDeviceDt){
				advertDevice.setAdvertInfoId(adInfoId);
				advertDevice.setAdStatus(Const.IS_VALID_TRUE);
				advertHandler.saveOrUpdateAdvertDevice(advertDevice);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试！");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 批量修改广告投放设备页面跳转
	 */
	@Action(value="queryDeviceInfoByAd",results={
			@Result(name=ERROR,location="/view/error.jsp"),
			@Result(name=SUCCESS,location="/view/advert/adPositionModifyBatch.jsp"),
	})
	public String queryDeviceInfoByAd(){
		try{
			String advertInfoId = this.getRequestParm().getParameter("advertInfoId");
			if(advertInfoId != null){
				Integer adInfoId = Integer.valueOf(advertInfoId);
				AdvertInfo advertInfo = (AdvertInfo) advertHandler.queryAdvertInfoById(adInfoId);
				List<AdvertPositionDto> deviceInfoList =advertHandler.queryAdvertDeviceByAdId(adInfoId);
				this.getRequest().put("advertInfo", advertInfo);
				this.getRequest().put("deviceInfoList", deviceInfoList);
				this.getRequest().put("pgLst", "pgLst");
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试！");
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 改变广告状态
	 */
	@Action(value="changeAdvertStatus",results={
			@Result(name=SUCCESS,location="/view/advert/adPositionList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String changeAdvertStatus(){
		try{
			String advertDeviceId = this.getRequestParm().getParameter("adId");
			if(StringUtils.isNotBlank(advertDeviceId)){
				Integer adId = Integer.valueOf(advertDeviceId);
				AdvertDevice advertDevice = (AdvertDevice) advertHandler.queryAdvertDeviceById(adId);
				if(advertDevice.getAdStatus().equals(Const.IS_VALID_TRUE)){
					advertDevice.setAdStatus(Const.IS_VALID_FALSE);
				}
				else{
					advertDevice.setAdStatus(Const.IS_VALID_TRUE);
				}
				advertHandler.saveOrUpdateAdvertDevice(advertDevice);
			}
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试！");
			return ERROR;
		}
		
	}
	
	/**
	 * 修改广告投放信息
	 */
	@Action(value="addOrModifyAdvertDevice",results={
			@Result(name=SUCCESS,location="/view/advert/adPositionModify.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String addOrModifyAdvertDevice(){
		try{
			String advertDeviceId = this.getRequestParm().getParameter("adId");
			if(StringUtils.isNotBlank(advertDeviceId)){
				Integer adId = Integer.valueOf(advertDeviceId);
				AdvertDevice advertDevice = (AdvertDevice) advertHandler.queryAdvertDeviceById(adId);
				AdvertInfo advertInfo = (AdvertInfo) advertHandler.queryAdvertInfoById(advertDevice.getAdvertInfoId());
				DeviceInfo deviceInfo = deviceHandler.queryDeviceById(advertDevice.getDeviceId());
				this.getRequest().put("deviceInfo", deviceInfo);
				this.getRequest().put("advertInfo", advertInfo);
				this.getRequest().put("advertDevice", advertDevice);
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试！");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 保存修改后的广告投放信息
	 */
	@Action(value="saveModifyAdvertDevice",results={
			@Result(name=SUCCESS,location="/view/advert/adPositionList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String saveModifyAdvertDevice(){
		try{
			String advertFee = this.getRequestParm().getParameter("advertFee");
			//更新广告内容费用和支付状态
			AdvertInfo advertInfo = (AdvertInfo) advertHandler.queryAdvertInfoById(advertDevice.getAdvertInfoId());
			Double adFee = Double.valueOf(advertFee);//更改前的价格
			double changeFee = advertDevice.getAdvertFee() - adFee;
			double totalFee = advertInfo.getAdvertTotalFee() + changeFee;
			advertInfo.setAdvertTotalFee(totalFee);
			if(advertInfo.getAdvertTotalFee() == advertInfo.getPayFee()){
				advertInfo.setPayStatus(Const.IS_VALID_TRUE);
			}
			else{
				advertInfo.setPayStatus(Const.IS_VALID_FALSE);
			}
			
			advertHandler.saveOrUpdateAdvertInfo(advertInfo);
			advertHandler.saveOrUpdateAdvertDevice(advertDevice);
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试！");
			return ERROR;
		}
		return SUCCESS; 
	}
	
	/**
	 * 删除广告投放设备
	 */
	@Action(value="deleteAdvertDevice",results={
			@Result(name=SUCCESS,location="/view/advert/adPositionList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String deleteAdvertDevice(){
		try{
			String advertDeviceId = this.getRequestParm().getParameter("adId");
			double totalFee = 0.0;
			if(StringUtils.isNotBlank(advertDeviceId)){
				Integer adId = Integer.valueOf(advertDeviceId);
				AdvertDevice advertDevice = (AdvertDevice) advertHandler.queryAdvertDeviceById(adId);
				//更新广告内容费用和支付状态
				AdvertInfo advertInfo = (AdvertInfo) advertHandler.queryAdvertInfoById(advertDevice.getAdvertInfoId());
				totalFee = advertInfo.getAdvertTotalFee() - advertDevice.getAdvertFee();
				advertInfo.setAdvertTotalFee(totalFee);
				if(advertInfo.getPayFee() != null && totalFee <= advertInfo.getPayFee()){
					advertInfo.setPayStatus(Const.IS_VALID_TRUE);
				}
				else{
					advertInfo.setPayStatus(Const.IS_VALID_FALSE);
				}
				//删除设备投放广告
				DeviceInfo deviceInfo = deviceHandler.queryDeviceById(advertDevice.getDeviceId());
				String code = baseController.getAdvertMsg(deviceInfo.getDeviceNo(), "Code");
				if(StringUtils.isNotBlank(code)){
					baseController.delAdvert(Integer.valueOf(code));
				}
				else{
					this.getRequest().put("errors", "删除设备投放广告失败，请重试！");
					return ERROR;
				}
				advertHandler.saveOrUpdateAdvertInfo(advertInfo);
				advertHandler.deleteAdvertDeviceById(adId);
			}
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试!");
			return ERROR;
		}
	}
	
	public List<AdvertDevice> getAdvertDeviceDt() {
		return advertDeviceDt;
	}

	public void setAdvertDeviceDt(List<AdvertDevice> advertDeviceDt) {
		this.advertDeviceDt = advertDeviceDt;
	}

	public AdvertDevice getAdvertDevice() {
		return advertDevice;
	}

	public void setAdvertDevice(AdvertDevice advertDevice) {
		this.advertDevice = advertDevice;
	}
}
