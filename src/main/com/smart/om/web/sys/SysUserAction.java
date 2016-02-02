package com.smart.om.web.sys;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.SysDept;
import com.smart.om.persist.SysDict;
import com.smart.om.persist.SysRole;
import com.smart.om.persist.SysUser;
import com.smart.om.persist.SysUserRole;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.Tools;
import com.smart.om.web.base.BaseAction;

/**
 * 系统用户管理Action
 * @author ienovo
 *
 */
@Namespace("/view/sys/sysUser")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class SysUserAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(SysUserAction.class);
	@Resource
	private SysFuncHandler sysFuncHandler;
	@Resource
	private SysAssistHandler sysAssistHandler;
	
	private SysUser sysUser;
	
	private List<SysUserRole> roleInfoList;
	
    private File userFile; //上传的文件
	
	/**
	 * 进入修改密码
	 * @return
	 */
	@Action(value="preModifyPass",results = { @Result(name = SUCCESS, location = "/view/sys/modifyPass.jsp"),
			   @Result(name = ERROR, location = "/view/error.jsp") })
	public String preModifyPass() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", " 进入修改用户密码");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");	
		}
		return SUCCESS;
	}
	/**
	 * 判断旧密码
	 * @throws IOException 
	 */
	@Action(value="checkOldPassword")
	public void checkOldPassword() throws IOException{
		SysUser userPass = (SysUser) this.getSession().get("account");
		String oldPassword = this.getRequestParm().getParameter("password");
		if(StringUtils.isNotBlank(oldPassword) && userPass != null){
			if(!userPass.getPassword().equals(Tools.md5(oldPassword))){
				this.getResponse().getWriter().print(false);
			}
			else{
				this.getResponse().getWriter().print(true);
			}
		}
		else{
			this.getResponse().getWriter().print(false);
		}
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@Action(value="modifyPass",results = { @Result(name = SUCCESS, location = "/view/success.jsp" ),
			   @Result(name = ERROR, location = "/view/error.jsp") })
	public String modifyPass() {
		String result = SUCCESS;
		SysUser user = (SysUser)this.getSession().get("account");//获得登陆用户
		String newPass = this.getRequestParm().getParameter("newPass");		
		try {
			user.setPassword(Tools.md5(newPass));
			user.setNewer(false);
			sysFuncHandler.saveOrUpdateUser(user);
			this.getRequest().put("desc", "修改密码成功！");
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", "修改用户密码");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
			return ERROR;
		}
		return result;
	}
	
	/**
	 * 查询用户
	 */
	@Action(value = "querySysUser", results = {
			@Result(name = SUCCESS, location = "/view/sys/sysUserDetail.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String querySysUser() {
		String result = SUCCESS;
		try {
			String sysUserId = this.getRequestParm().getParameter("userId");
			if(StringUtils.isNotBlank(sysUserId)){
				Integer userId = Integer.valueOf(sysUserId);
				SysUser sysUser = sysFuncHandler.querySysUserById(userId);
				Integer roleId = sysUser.getRoleId();
				List<SysUserRole> sysRoleUserList = sysFuncHandler.queryRoleByUserId(userId);
				List<SysRole> sysRoleList = new ArrayList<SysRole>();
				if(sysRoleUserList != null && sysRoleUserList.size() > 0){
					for(SysUserRole sysUserRole : sysRoleUserList){
						SysRole sysRole = sysFuncHandler.querySysRoleById(sysUserRole.getRoleId());
						sysRoleList.add(sysRole);
					}
				}
				if(sysUser.getOrgId() != null){
					SysDict orgDict = sysAssistHandler.queryDictById(sysUser.getOrgId());
					this.getRequest().put("orgDict", orgDict);
				}
				if(sysUser.getDictRegionId() != null){
					SysDict regionDict = sysAssistHandler.queryDictById(sysUser.getDictRegionId());
					this.getRequest().put("regionDict", regionDict);
				}
				if(sysUser.getDictProviceId() != null){
					SysDict provinceDict = sysAssistHandler.queryDictById(sysUser.getDictProviceId());
					this.getRequest().put("provinceDict", provinceDict);
				}
				Integer sysDeptId = sysUser.getDeptId();
				SysDept sysDept = sysFuncHandler.querySysDeptById(sysDeptId);
				this.getRequest().put("sysUser", sysUser);
				this.getRequest().put("sysRoleList", sysRoleList);
				this.getRequest().put("sysDept", sysDept);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", " 查询用户");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
		}
		return result;
	}
	
	/**
	 * 进入添加用户
	 * @return
	 */
	@Action(value="preAddOrModifyUser",results = { @Result(name = SUCCESS, location = "/view/sys/userAdd.jsp"),
			   @Result(name = ERROR, location = "/view/error.jsp") })
	public String preAddOrModifyUser() {
		try {
			SysUser sysUser = (SysUser) this.getSession().get("account");
			if(sysUser != null){
				if(sysUser.getOrgId() != null){
					SysDict sysDict = sysAssistHandler.queryDictById(sysUser.getOrgId());
					this.getRequest().put("sysDict", sysDict);
					SysDict sysDictRegion = sysAssistHandler.queryDictById(sysUser.getDictRegionId());
					this.getRequest().put("sysDictRegion", sysDictRegion);
					this.getRequest().put("beforeShow","1");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", " 进入添加用户");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");	
		}
		return SUCCESS;
	}
	
	/**
	 * 添加用户
	 * @return
	 */
	@Action(value="saveOrUpdateUser",results = {
			@Result(name = SUCCESS, type = "redirect", location = "/view/sys/userList.jsp" ),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String saveOrUpdateUser() {
		try {
			SysUser sessionUser = (SysUser) this.getSession().get("account");
			String dictId = this.getRequestParm().getParameter("dictId");
			String dictPid = this.getRequestParm().getParameter("dictPid");
			String dictPPid = this.getRequestParm().getParameter("dictPPid");
			String treeNode = this.getRequestParm().getParameter("treeNode");
			if(sysUser.getUserId() == null){
				if(StringUtils.isNotBlank(treeNode)){
					if(Const.IS_REGION.equals(treeNode)){
						sysUser.setDictRegionId(Integer.valueOf(dictId));
					}
					else if(Const.IS_PROVICE.equals(treeNode)){
						sysUser.setDictProviceId(Integer.valueOf(dictId));
						sysUser.setDictRegionId(Integer.valueOf(dictPid));
					}
					else if(Const.IS_ORG.equals(treeNode)){
						sysUser.setOrgId(Integer.valueOf(dictId));
						sysUser.setDictProviceId(Integer.valueOf(dictPid));
						sysUser.setDictRegionId(Integer.valueOf(dictPPid));
					}
					else{
						this.getRequest().put("errors", "系统异常，请联系管理员...");
						return ERROR;
					}
				}
				else{
					sysUser.setOrgId(sessionUser.getOrgId());
					sysUser.setDictProviceId(sessionUser.getDictProviceId());
					sysUser.setDictRegionId(sessionUser.getDictRegionId());
				}
				sysUser.setPassword(Tools.md5(Const.USER_INIT_PASSWORD));
				String sysUserStatus = sysUser.getUserStatus();
				if(StringUtils.isNotBlank(sysUserStatus)){
					sysUser.setUserStatus(Const.IS_VALID_FALSE);
				}
				sysUser.setIsDel(Const.IS_DEL_FALSE);
				sysUser.setNewer(true);
				sysUser.setCreateDate(DateUtil.getDateY_M_D());
				SysUser newSysUser = sysFuncHandler.saveOrUpdateUser(sysUser);
				if(roleInfoList != null){
					if(roleInfoList.size() > 0){
						for(SysUserRole sysUserRole:roleInfoList){
							sysUserRole.setNewer(true);
							sysUserRole.setUserId(newSysUser.getUserId());
							sysFuncHandler.saveOrUpdateSysRoleUser(sysUserRole);
						}
					}
				}
			}
			else{
				if(StringUtils.isNotBlank(treeNode)){
					if(Const.IS_REGION.equals(treeNode)){
						sysUser.setDictRegionId(Integer.valueOf(dictId));
					}
					else if(Const.IS_PROVICE.equals(treeNode)){
						sysUser.setDictProviceId(Integer.valueOf(dictId));
						sysUser.setDictRegionId(Integer.valueOf(dictPid));
					}
					else if(Const.IS_ORG.equals(treeNode)){
						sysUser.setOrgId(Integer.valueOf(dictId));
						sysUser.setDictProviceId(Integer.valueOf(dictPid));
						sysUser.setDictRegionId(Integer.valueOf(dictPPid));
					}
					else{
						this.getRequest().put("errors", "系统异常，请联系管理员...");
						return ERROR;
					}
				}
				SysUser newSysUser = sysFuncHandler.saveOrUpdateUser(sysUser);
				List<SysUserRole> list = sysFuncHandler.queryRoleByUserId(newSysUser.getUserId());
				if(list != null){
					for(SysUserRole sysUserRole:list){
						sysFuncHandler.deleteSysUserRole(sysUserRole);
					}
				}
				for(SysUserRole sysUserRole:roleInfoList){
					if(sysUserRole != null){
						sysUserRole.setNewer(true);
						sysUserRole.setUserId(newSysUser.getUserId());
						sysFuncHandler.saveOrUpdateSysRoleUser(sysUserRole);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", " 添加用户");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");	
		}
		return SUCCESS;
	}

	/**
	 * 删除用户
	 */
	@Action(value="deleteSysUser",results={
			@Result(name=SUCCESS,location="/view/sys/userList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String deleteSysUser(){
		try{
			String sysUserId = this.getRequestParm().getParameter("userId");
			if(StringUtils.isNotBlank(sysUserId)){
				Integer userId = Integer.valueOf(sysUserId);
				SysUser sysUser = sysFuncHandler.querySysUserById(userId);
				List<SysUserRole> list = sysFuncHandler.queryRoleByUserId(userId);
				if(list != null){
					for(SysUserRole sysUserRole:list){
						sysFuncHandler.deleteSysUserRole(sysUserRole);
					}
				}
				sysUser.setNewer(false);
				sysUser.setIsDel(Const.IS_DEL_TRUE);
				sysFuncHandler.saveOrUpdateUser(sysUser);
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
		return SUCCESS;
	}
	
	/**
	 * 增加和修改用户页面跳转
	 */
	@Action(value="addOrModifySysUser",results={
			@Result(name=SUCCESS,location="/view/sys/userAdd.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String addOrModifySysUser(){
		try{
			String sysUserId = this.getRequestParm().getParameter("userId");
			if(StringUtils.isNotBlank(sysUserId)){
				Integer userId = Integer.valueOf(sysUserId);
				SysUser sysUser = sysFuncHandler.querySysUserById(userId);
				Integer roleId = sysUser.getRoleId();
				if(sysUser.getOrgId() != null){
					SysDict sysDict = sysAssistHandler.queryDictById(sysUser.getOrgId());
					this.getRequest().put("sysDict", sysDict);
				}
				else{
					if(sysUser.getDictProviceId() != null){
						SysDict sysDict = sysAssistHandler.queryDictById(sysUser.getDictProviceId());
						this.getRequest().put("sysDict", sysDict);
					}
					else{
						SysDict sysDict = sysAssistHandler.queryDictById(sysUser.getDictRegionId());
						this.getRequest().put("sysDict", sysDict);
					}
				}
				Integer sysDeptId = sysUser.getDeptId();
				SysDept sysDept = sysFuncHandler.querySysDeptById(sysDeptId);
				roleInfoList = sysFuncHandler.queryRoleByUserId(userId);
				if(roleInfoList != null){
					if(roleInfoList.size() > 0){
						List<SysRole> sysRoleList = new ArrayList<SysRole>();
						for(SysUserRole sysUserRole:roleInfoList){
							SysRole sysRole = sysFuncHandler.querySysRoleById(sysUserRole.getRoleId());
							sysRoleList.add(sysRole);
						}
						this.getRequest().put("sysRoleList", sysRoleList);
						this.getRequest().put("pgLst", "pgLst");
					}
				}
				this.getRequest().put("sysUser", sysUser);
				this.getRequest().put("sysDept", sysDept);
				SysUser user = (SysUser) this.getSession().get("account");
				if(user != null){
					if(user.getOrgId() != null){
						this.getRequest().put("beforeShow","1");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试！");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改用户状态
	 */
	@Action(value="modifySysUserStatus",results={
			@Result(name=SUCCESS,location="/view/sys/userList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String modifySysUserStatus(){
		try{
			String sysUserId = this.getRequestParm().getParameter("userId");
			if(StringUtils.isNotBlank(sysUserId)){
				Integer userId = Integer.valueOf(sysUserId);
				SysUser sysUser = sysFuncHandler.querySysUserById(userId);
				if(sysUser.getUserStatus().equals(Const.IS_VALID_FALSE)){
					sysUser.setUserStatus(Const.IS_VALID_TRUE);
				}else{
					sysUser.setUserStatus(Const.IS_VALID_FALSE);
				}
				sysFuncHandler.saveOrUpdateUser(sysUser);
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试！");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 重置用户密码
	 */
	@Action(value="resetPassword",results={
			@Result(name=SUCCESS,location="/view/sys/userList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String resetPassword(){
		try{
			String userId = this.getRequestParm().getParameter("userId");
			if(StringUtils.isNotBlank(userId)){
				SysUser user = sysFuncHandler.querySysUserById(Integer.valueOf(userId));
				user.setPassword(Tools.md5(Const.USER_INIT_PASSWORD));
				sysFuncHandler.saveOrUpdateUser(user);
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "重置密码失败，请重试...");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询角色分页信息
	 */
	@Action(value = "querySysRolePage")
	public void querySysRolePage() {
		try {
			// 搜索关键字
			String isOrg = this.getRequestParm().getParameter("isOrg");
			String orgId = this.getRequestParm().getParameter("orgId");
			String searchAdUser = this.getRequestParm().getParameter("searchAdUser");
			Map<String, Object> params = new HashMap<String, Object>();
			if(StringUtils.isNotBlank(isOrg)){
				params.put("isOrgId", isOrg);
			}
			if(StringUtils.isNotBlank(orgId)){
				params.put("isOrgId", Const.IS_CHILD);
			}
			params.put("searchAdUser", searchAdUser);
			DTablePageModel dtPageModel = sysFuncHandler.querySysRolePage(
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
	 * 查询用户分页信息
	 */
	@Action(value = "querySysUserPage")
	public void querySysUserPage() {
		SysDict sysDict = sysAssistHandler.queryDictByPcode("AL_POSITION");
		try {
			// 搜索关键字
			String userStatus = this.getRequestParm().getParameter("userStatus");
			String searchAdUser = this.getRequestParm().getParameter("keyword");
			String regionId = this.getRequestParm().getParameter("regionId");
            String proviceId = this.getRequestParm().getParameter("proviceId");
            String orgId = this.getRequestParm().getParameter("orgId");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("searchAdUser", searchAdUser);
			if(StringUtils.isNotBlank(orgId)){
				params.put("orgId", orgId);
			}
			if(StringUtils.isNotBlank(userStatus)){
				params.put("userStatus", userStatus);
			}
			params.put("regionId", regionId);
			params.put("proviceId", proviceId);
			DTablePageModel dtPageModel = sysFuncHandler.querySysUserPage(
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
	 * 进入用户分页信息页面
	 */
	@Action(value="entrySysUserPage",results={
			@Result(name=SUCCESS,location="/view/sys/userList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String entrySysUserPage(){
		try{
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请联系管理员...");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 检查用户名的唯一性
	 */
	@Action(value="checkUserName")
	public String checkUserName() throws Exception{
		try{
			String username = this.getRequestParm().getParameter("username");
			if(StringUtils.isNotBlank(username)){
				List<SysUser> sysUserList = sysFuncHandler.querySysUserByName(username);
				if(sysUserList != null && sysUserList.size() > 0 ){
					this.getResponse().getWriter().print(false);
				}
				else{
					this.getResponse().getWriter().print(true);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			this.getResponse().getWriter().print(false);
		}
		return null;
	}
	
	/**
	 * 检查电话号码的唯一性
	 */
	@Action(value="checkTelOnly")
	public String checkTelOnly() throws Exception{
		try{
			String telnumber = this.getRequestParm().getParameter("telnumber");
			if(StringUtils.isNotBlank(telnumber)){
				List<SysUser> sysUserList = sysFuncHandler.querySysUserByTel(telnumber);
				if(sysUserList != null && sysUserList.size() > 0 ){
					this.getResponse().getWriter().print(false);
				}
				else{
					this.getResponse().getWriter().print(true);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getResponse().getWriter().print(false);
		}
		return null;
	}
	
	/**
	 * 设备--点击设置事件 
	 * @return
	 */
	@Action(value="userSet",results={
			@Result(name=SUCCESS,location="/view/sys/userSetting.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String userSet(){
		try{
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请联系管理员...");
			return ERROR;
		}
		return SUCCESS;
	}
	/**
	 * 用户个人资料更新/完善
	 * @return
	 */
	@Action(value="userSetting",results={
			@Result(name=SUCCESS,location="/view/error.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String userSetting(){
		try {
			if(sysUser.getUserId() != null){
				SysUser user= sysFuncHandler.querySysUserById(sysUser.getUserId());
				
				InputStream fis = null;
	            if (userFile != null) {
	                // 建立文件上传流
	                fis = new FileInputStream(userFile);
	                String uploadImg;
	                //保存图片和地址
	                if (StringUtils.isNotBlank(sysUser.getUserImage())) {
	                    uploadImg = Tools.uploadImg(fis, "userInfo", getRequestParm(), sysUser.getUserImage());
	                } else {
	                    uploadImg = Tools.uploadImg(fis, "userInfo", getRequestParm(), null);
	                }
	                user.setUserImage(uploadImg);
	            }
				
				if(sysUser.getUserName() != null){
					user.setUserName(sysUser.getUserName());
				}
				if(sysUser.getRealName() != null){
					user.setRealName(sysUser.getRealName());
				}
				if(sysUser.getSex() != null){
					user.setSex(sysUser.getSex());
				}
				if(sysUser.getEmail() != null){
					user.setEmail(sysUser.getEmail());
				}
				if(sysUser.getTel() != null){
					user.setTel(sysUser.getTel());
				}
				sysFuncHandler.userSetting(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", " 添加用户");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");	
		}
		this.getRequest().put("errors",	"保存成功！");
		return SUCCESS;
	}
	
	/**
	 * 定时刷新用户所在坐标
	 */
	List<Map> list = new ArrayList<Map>();
	public void updateUserCoord() {
		//List list = new ArrayList();
		if(null==list || list.isEmpty()){
			Map<String,String> p = new HashMap<String,String>();
			p.put("userTel", "13971188622");
			p.put("userLng", "114.069666");
			p.put("userLat", "22.545627");
			list.add(p);
			Map<String,String> p1 = new HashMap<String,String>();
			p1.put("userTel", "13971188615");
			p1.put("userLng", "114.056182");
			p1.put("userLat", "22.540295");
			list.add(p1);
			
			Map<String,String> p2 = new HashMap<String,String>();
			p2.put("userTel", "13971188614");
			p2.put("userLng", "114.279384");
			p2.put("userLat", "30.564477");
			list.add(p2);
			
			Map<String,String> p3 = new HashMap<String,String>();
			p3.put("userTel", "13971188613");
			p3.put("userLng", "114.056236");
			p3.put("userLat", "22.524948");
			list.add(p3);
			
			Map<String,String> p4 = new HashMap<String,String>();
			p4.put("userTel", "13971188612");
			p4.put("userLng", "114.056344");
			p4.put("userLat", "22.528595");
			list.add(p4);
			
			Map<String,String> p5 = new HashMap<String,String>();
			p5.put("userTel", "13971188600");
			p5.put("userLng", "114.058167");
			p5.put("userLat", "22.524948");
			list.add(p5);
		}
		
		List<Map> list1 = new ArrayList<Map>();
		for (int i = 0; i < list.size(); i++) {
			
			Map userMap = new HashMap();
			userMap = list.get(i);
			String userTel = userMap.get("userTel").toString();
			String userLng = userMap.get("userLng").toString();
			String userLat = userMap.get("userLat").toString();
			
			int num = sysFuncHandler.updateUserCoord(userTel,userLng,userLat);
			if(num>0){
				Map userMapAdd = new HashMap();
				Double userLngD = Double.parseDouble(userLng.trim());
				Double userLatD = Double.parseDouble(userLat.trim());
				
				userMapAdd.put("userTel", userTel);
				userMapAdd.put("userLng", ((userLngD*1000000)+10)/1000000);
				userMapAdd.put("userLat", ((userLatD*1000000)+10)/1000000);
				list1.add(userMapAdd);
			}
		}
		
		list.clear();
		for (int i = 0; i < list1.size(); i++) {
			Map userNewMap = new HashMap();
			userNewMap = list1.get(i);
			list.add(userNewMap);
		}
    }
	
	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public List<SysUserRole> getRoleInfoList() {
		return roleInfoList;
	}

	public void setRoleInfoList(List<SysUserRole> roleInfoList) {
		this.roleInfoList = roleInfoList;
	}

	public File getUserFile() {
		return userFile;
	}

	public void setUserFile(File userFile) {
		this.userFile = userFile;
	}
	
	
}
