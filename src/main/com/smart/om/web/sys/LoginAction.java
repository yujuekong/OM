package com.smart.om.web.sys;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.smart.om.biz.device.DeviceHandler;
import com.smart.om.biz.inventory.InventoryHandler;
import com.smart.om.biz.region.RegionHandler;
import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.DeviceClean;
import com.smart.om.persist.DeviceMaintenance;
import com.smart.om.persist.DispatchingOrder;
import com.smart.om.persist.StorageDeliveryOrder;
import com.smart.om.persist.StorageWarehousingEntry;
import com.smart.om.persist.SysDict;
import com.smart.om.persist.SysMenu;
import com.smart.om.persist.SysUser;
import com.smart.om.util.Const;
import com.smart.om.util.DateUtil;
import com.smart.om.util.MessageUtil;
import com.smart.om.util.Tools;
import com.smart.om.web.base.BaseAction;

@Namespace("/sys/user")
@Results({ @Result(name = "error", location = "/console/error.jsp") })
public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoginAction.class);
	@Resource
	private SysFuncHandler sysFuncHandler;
	private SysUser sysUser;
	@Resource
    private SysAssistHandler sysAssistHandler;
	@Resource
	private DeviceHandler deviceHandler;//设备
	@Resource
	private InventoryHandler inventoryHandler;//出库入库
	@Resource
	private RegionHandler regionHandler;//配送

	// 后台账号登陆
	@Action(value = "userLogin", results = {
			@Result(name = SUCCESS, location = "/view/commons/main.jsp"),
			@Result(name = ERROR, location = "/index.jsp") })
	public String login() {
		String result = SUCCESS;
		try {
			String randCode = super.getRequestParm().getParameter("randCode");//输入的验证码
			if(StringUtils.isEmpty(randCode)) {
				this.getRequest().put("userName", sysUser.getUserName());
				this.getRequest().put("errors", "验证码不能为空！");
				return ERROR;
			}
			randCode = randCode.toLowerCase();
			String rand = (String)this.getSession().get("rand");//系统验证码
			if(StringUtils.isNotBlank(rand)){
				rand = rand.toLowerCase();
				if(!rand.equals(randCode)){
//					this.getRequest().put("userName", sysUser.getUserName());
					this.getRequest().put("errors", "验证码输入错误，请重新输入！");
					return ERROR;
				}
			}
			SysUser user = new SysUser();
			if(StringUtils.isNotBlank(sysUser.getPassword())){
				sysUser.setPassword(Tools.md5(sysUser.getPassword()));
			}
			List<SysUser> userList = sysFuncHandler.userLogin(sysUser);
			if (userList != null) {
				if (userList.size() > 0 && Const.IS_VALID_TRUE.equals(userList.get(0).getUserStatus())) {
					user = userList.get(0);	
					if(!"".equals(user.getOrgId()) && null!=user.getOrgId()){
						SysDict sysDict = sysAssistHandler.queryDictById(user.getOrgId());
						user.setDictName(sysDict.getDictName());
					}
					this.getRequest().put("listCount", this.queryMsg(user.getUserId()));
					this.getRequest().put("resultList", this.queryMessage(user.getUserId()));
					this.getHttpSession().setAttribute("account", user);
					
					List<SysMenu> menuList = sysFuncHandler.querySysMenu(user.getUserId(),"TREE");
					this.getHttpSession().setAttribute("menuList", menuList);
					if(user != null){
						user.setLastLoginTime(DateUtil.getDateY_M_DH_M_S());
						sysFuncHandler.saveOrUpdateUser(user);
					}
				}
				else if(userList.size() > 0 && Const.IS_VALID_FALSE.equals(userList.get(0).getUserStatus())){
					this.getRequest().put("errors", "用户已被禁用，请更换账号登陆！");
					result = ERROR;
				}
				else{
					this.getRequest().put("errors", "用户名称或者密码错误，请重新再试！");
					result = ERROR;
				}
			}
			else{
				this.getRequest().put("errors", "用户名称或者密码错误，请重新再试！");
				result = ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", " 用户登录");
			this.getRequest().put("errors", "系统正忙，请联系管理员");
			result = ERROR;
		}
		return result;
	}
	
	// 回到首页
	@Action(value = "userIndex", results = {
			@Result(name = SUCCESS, location = "/view/commons/main.jsp"),
			@Result(name = ERROR, location = "/index.jsp") })
	public String userIndex() {
		String result = SUCCESS;
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", " 用户首页");
			this.getRequest().put("errors", "系统正忙，请联系管理员");
		}
		return result;
	}
	
	// 后台账号退出
	@Action(value = "exit", results = {
			@Result(name = SUCCESS, location = "/index.jsp"),
			@Result(name = ERROR, location = "/index.jsp") })
	public String exit() {
		String result = SUCCESS;
		try {
			this.getHttpSession().removeAttribute("menuList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//消息提醒总数量
	public Integer queryMsg(Integer userId){
		List<String> menuList = sysFuncHandler.queryMenuNameByUserId(userId);
		Integer result = 0;
		List<DeviceMaintenance> deviceMaintenanceList = deviceHandler.queryDeviceMaintenanceList(userId); //设备维修
		List<DeviceClean> deviceCleanList = deviceHandler.queryDeviceCleanList(userId);//设备清洗
		List<StorageWarehousingEntry> storageWarehousingEntryList =inventoryHandler.queryWarehousingList(userId);//商品入库
		List<StorageDeliveryOrder> storageDeliveryOrderList = inventoryHandler.queryStorageDeliveryOrderList(userId,1); //商品出库
		List<StorageDeliveryOrder> storageDeliveryOrderList2 = inventoryHandler.queryStorageDeliveryOrderList(userId,2); //出库处理
		List<DispatchingOrder> dispatchingOrderList = regionHandler.queryDispatchingOrderList(userId);//商品配送
		if(deviceMaintenanceList != null && deviceMaintenanceList.size() > 0){
			for(String menuName:menuList){
				if(Const.WEI_XIU.equals(menuName)){
					result += deviceMaintenanceList.size();
					break;
				}
			}
		}
		if(deviceCleanList != null && deviceCleanList.size() > 0){
			for(String menuName:menuList){
				if(Const.QING_XI.equals(menuName)){
					result += deviceCleanList.size();
					break;
				}
			}
		}
		if(storageWarehousingEntryList != null && storageWarehousingEntryList.size() > 0){
			for(String menuName:menuList){
				if(Const.RU_KU.equals(menuName)){
					result += storageWarehousingEntryList.size();
					break;
				}
			}
		}
		if(storageDeliveryOrderList != null && storageDeliveryOrderList.size() > 0){
			for(String menuName:menuList){
				if(Const.CHU_KU.equals(menuName)){
					result += storageDeliveryOrderList.size();
					break;
				}
			}
		}
		if(storageDeliveryOrderList2 != null && storageDeliveryOrderList2.size() > 0){
			for(String menuName:menuList){
				if(Const.CHU_LI.equals(menuName)){
					result += storageDeliveryOrderList2.size();
					break;
				}
			}
		}
		if(dispatchingOrderList != null && dispatchingOrderList.size() > 0){
			for(String menuName:menuList){
				if(Const.PEI_SONG.equals(menuName)){
					result += dispatchingOrderList.size();
					break;
				}
			}
		}
		return result;
	}
	
	//消息提醒具体数量
	public List<MessageUtil> queryMessage(Integer userId){
		List<String> menuList = sysFuncHandler.queryMenuNameByUserId(userId);
		List<MessageUtil> list = new ArrayList<MessageUtil>();
		List<DeviceMaintenance> deviceMaintenanceList = deviceHandler.queryDeviceMaintenanceList(userId); //设备维修
		List<DeviceClean> deviceCleanList = deviceHandler.queryDeviceCleanList(userId);//设备清洗
		List<StorageWarehousingEntry> storageWarehousingEntryList =inventoryHandler.queryWarehousingList(userId);//商品入库
		List<StorageDeliveryOrder> storageDeliveryOrderList = inventoryHandler.queryStorageDeliveryOrderList(userId,1); //商品出库
		List<StorageDeliveryOrder> storageDeliveryOrderList2 = inventoryHandler.queryStorageDeliveryOrderList(userId,2); //出库处理
		List<DispatchingOrder> dispatchingOrderList = regionHandler.queryDispatchingOrderList(userId);//商品配送
		if(deviceMaintenanceList != null && deviceMaintenanceList.size() > 0){
			MessageUtil msgDeviceM = new MessageUtil();
			msgDeviceM.setMsgAddress("view/device/deviceMaintenanceList.jsp");
			msgDeviceM.setMsgCount("设备维修未完成：" + deviceMaintenanceList.size() + "条");
			for(String menuName:menuList){
				if(Const.WEI_XIU.equals(menuName)){
					list.add(msgDeviceM);
					break;
				}
			}
		}
		if(deviceCleanList != null && deviceCleanList.size() > 0){
			MessageUtil msgDeviceC = new MessageUtil();
			msgDeviceC.setMsgAddress("view/device/deviceCleanList.jsp");
			msgDeviceC.setMsgCount("设备清洗未完成：" + deviceCleanList.size() + "条");
			for(String menuName:menuList){
				if(Const.QING_XI.equals(menuName)){
					list.add(msgDeviceC);
					break;
				}
			}
		}
		if(storageWarehousingEntryList != null && storageWarehousingEntryList.size() > 0){
			MessageUtil msgEntry = new MessageUtil();
			msgEntry.setMsgAddress("view/inventory/inList.jsp");
			msgEntry.setMsgCount("入库未完成：" + storageWarehousingEntryList.size() + "条");
			for(String menuName:menuList){
				if(Const.RU_KU.equals(menuName)){
					list.add(msgEntry);
					break;
				}
			}
		}
		if(storageDeliveryOrderList != null && storageDeliveryOrderList.size() > 0){
			MessageUtil msgOut = new MessageUtil();
			msgOut.setMsgAddress("view/inventory/outList.jsp");
			msgOut.setMsgCount("出库未完成：" + storageDeliveryOrderList.size() + "条");
			for(String menuName:menuList){
				if(Const.CHU_KU.equals(menuName)){
					list.add(msgOut);
					break;
				}
			}
		}
		if(storageDeliveryOrderList2 != null && storageDeliveryOrderList2.size() > 0){
			MessageUtil msgOrder = new MessageUtil();
			msgOrder.setMsgAddress("view/inventory/deliveryAgentList.jsp");
			msgOrder.setMsgCount("出库处理未完成：" + storageDeliveryOrderList2.size() + "条");
			for(String menuName:menuList){
				if(Const.CHU_LI.equals(menuName)){
					list.add(msgOrder);
					break;
				}
			}
		}
		if(dispatchingOrderList != null && dispatchingOrderList.size() > 0){
			MessageUtil msgDispatch = new MessageUtil();
			msgDispatch.setMsgAddress("view/region/dispatchOrderList.jsp");
			msgDispatch.setMsgCount("配送单未完成：" + dispatchingOrderList.size() + "条");
			for(String menuName:menuList){
				if(Const.PEI_SONG.equals(menuName)){
					list.add(msgDispatch);
					break;
				}
			}
		}
		return list;
	}

	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	
	
	
}
