package com.smart.om.web.advert;

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

import com.smart.om.biz.advert.AdvertHandler;
import com.smart.om.persist.AdvertUser;
import com.smart.om.persist.SysUser;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.web.base.BaseAction;

/**
 * 广告主管理Action
 * 
 * @author CA
 */
@Namespace("/view/advert/advertUser")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class AdvertUserAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(AdvertUserAction.class);
	
	@Resource
	private AdvertHandler advertHandler;

	private AdvertUser advertUser;

	/**
	 * 新增广告主管理
	 */
	@Action(value = "saveOrUpdateAdvertUser", results = {
			@Result(name = SUCCESS, location = "/view/advert/adUserList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String saveOrUpdateAdvertUser() {
		try {
			SysUser sysUser = (SysUser) this.getSession().get("account");
			if(advertUser.getAdvertUserId() == null){
				if(sysUser.getOrgId() != null){
					advertUser.setOrgId(sysUser.getOrgId());
					advertUser.setProviceId(sysUser.getDictProviceId());
					advertUser.setRegionId(sysUser.getDictRegionId());
				}
				advertUser.setCreateDate(DateUtil.getDateY_M_DH_M_S());
			}
			if(!Const.IS_VALID_TRUE.equals(advertUser.getAuStatus()) && !Const.IS_VALID_FALSE.equals(advertUser.getAuStatus())){
				advertUser.setAuStatus(Const.IS_VALID_FALSE);
			}
			advertHandler.saveOrUpdateAdvertUser(advertUser);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("errors", "数据录入错误，请重新录入！");
			return ERROR;
		}
	}

	/**
	 * 新增和修改页面跳转
	 */
	@Action(value = "saveOrModifyAdvertUser", results = {
			@Result(name = SUCCESS, location = "/view/advert/adUserAdd.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String saveOrModifyAdvertUser() {
		try {
			String advertUserId = this.getRequestParm().getParameter(
					"advertUserId");
			if (StringUtils.isNotBlank(advertUserId)) {
				int userId = Integer.valueOf(advertUserId);
				AdvertUser advertUser = (AdvertUser) advertHandler.queryAdvertUserById(userId);
				this.getRequest().put("advertUser", advertUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("errors", "系统正忙，请重试...");
			return ERROR;
		}
		return SUCCESS;

	}

	/**
	 * 查询广告主分页信息
	 */
	@Action(value = "queryAdvertUserPage")
	public void queryAdvertUserPage() {
		try {
			// 搜索关键字
			String adUserStatus = this.getRequestParm().getParameter("advertUserStatus");
			String keyword = this.getRequestParm().getParameter("keyword");
			String orgId = this.getRequestParm().getParameter("orgId");
			String proviceId = this.getRequestParm().getParameter("proviceId");
			String regionId = this.getRequestParm().getParameter("regionId");	
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			params.put("orgId", orgId);
			params.put("proviceId", proviceId);
			params.put("regionId", regionId);
			if(StringUtils.isNotBlank(adUserStatus)){
				params.put("adUserStatus",adUserStatus);
			}
			DTablePageModel dtPageModel = advertHandler.queryAdvertUserPage(
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
	 * 搜索广告主
	 */
	@Action(value="searchAdvertUser",results={
			@Result(name=SUCCESS,location="/view/advert/adUserListSearch.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String searchAdvertUser(){
		try{
			String advertUserName = new String(this.getRequestParm().getParameter("auName").getBytes("ISO-8859-1"),"utf-8") ;  
			this.getRequest().put("advertUserName", advertUserName);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试！");
			return ERROR;
		}
		
	}
	
	/**
	 * 删除广告主
	 */
	@Action(value = "deleteAdvertUser", results = {
			@Result(name = SUCCESS, location = "/view/advert/adUserList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String deleteAdvertUser() {
		try {
			String advertUserId = this.getRequestParm().getParameter(
					"advertUserId");
			if (StringUtils.isNotBlank(advertUserId)) {
				int adUserId = Integer.valueOf(advertUserId);
				advertHandler.deleteAdvertUser(adUserId);
				return SUCCESS;
			} else {
				this.getRequest().put("errors", "删除失败，请重试!");
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("errors", "删除失败，请重试！");
			return ERROR;
		}
	}
	
	/**
	 * 查询广告主详细信息
	 */
	@Action(value = "queryAdvertUserDt", results = {
			@Result(name = SUCCESS, location = "/view/advert/adUserDetail.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String queryAdvertUserDt() {
		try {
			String advertUserId = this.getRequestParm().getParameter(
					"advertUserId");
			if (StringUtils.isNotBlank(advertUserId)) {
				int adUserId = Integer.valueOf(advertUserId);
				AdvertUser advertUser = (AdvertUser) advertHandler
						.queryAdvertUserById(adUserId);
				this.getRequest().put("advertUser", advertUser);
				return SUCCESS;
			} else {
				this.getRequest().put("errors", "查询失败，请重试！");
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("errors", "查询失败，请重试！");
			return ERROR;
		}
	}
	
	/**
	 * 修改广告状态
	 */
	@Action(value="changeAdvertStatus",results={
			@Result(name=SUCCESS,location="/view/advert/adUserList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String changeAdvertStatus(){
		try{
			String advertUserId = this.getRequestParm().getParameter("advertUserId");
			Integer adUserId = Integer.valueOf(advertUserId);
			advertUser = (AdvertUser) advertHandler.queryAdvertUserById(adUserId);
			if(Const.IS_VALID_TRUE.equals(advertUser.getAuStatus())){
				advertUser.setAuStatus(Const.IS_VALID_FALSE);
			}
			else if(Const.IS_VALID_FALSE.equals(advertUser.getAuStatus())){
				advertUser.setAuStatus(Const.IS_VALID_TRUE);
			}
			else{
				this.getRequest().put("errors", "系统异常，请重试！");
				return ERROR;
			}
			advertHandler.saveOrUpdateAdvertUser(advertUser);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试！");
			return ERROR;
		}
	}
	
	public AdvertUser getAdvertUser() {
		return advertUser;
	}

	public void setAdvertUser(AdvertUser advertUser) {
		this.advertUser = advertUser;
	}
	
}
