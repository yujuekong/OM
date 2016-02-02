package com.smart.om.web.region;

import com.smart.om.biz.region.RegionHandler;
import com.smart.om.persist.DispatchingTeam;
import com.smart.om.util.DTablePageModel;
import com.smart.om.web.base.BaseAction;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.om.biz.car.CarHandler;
import com.smart.om.biz.inventory.InventoryHandler;
import com.smart.om.biz.region.RegionHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.CarInfo;
import com.smart.om.persist.CarLine;
import com.smart.om.persist.DispatchingTeam;
import com.smart.om.persist.SysUser;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.web.base.BaseAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * 配送小组Action
 * @author CA
 *
 */
@Namespace("/view/region/dispatchTeam")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class DispatchTeamAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private RegionHandler regionHandler;
	@Autowired
	private SysFuncHandler sysFuncHandler;
	@Autowired
	private CarHandler carHandler;
	
	private DispatchingTeam dispatchingTeam;
	
	private static final Logger logger = Logger.getLogger(DispatchTeamAction.class);
	
	/**
	 * 查询配送小组分页信息
	 */
	@Action(value="queryDispatchTeamPage")
	public void queryDispatchTeamPage(){
		SysUser sysUser = (SysUser) this.getSession().get("account");
		try{
			//搜索关键字
			String keyword = this.getRequestParm().getParameter("keyword");
			String teamKeyword = this.getRequestParm().getParameter("teamKeyword");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			params.put("orgId", sysUser.getOrgId());
			if(StringUtils.isNotBlank(teamKeyword)){
				params.put("teamKeyword", teamKeyword);
			}
			DTablePageModel dtPageModel = regionHandler.queryDipatchTeamPage(params, super.getPageData());
			String jsonData = JSONUtil.serialize(dtPageModel);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询配送小组成员分页信息
	 * @throws IOException 
	 * @throws JSONException 
	 */
	@Action(value="queryTeamUser")
	public void queryTeamUser() throws IOException, JSONException{
		//搜索关键字
		String keyword = this.getRequestParm().getParameter("keyword");
		Map<String, Object> params = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(keyword)){
			params.put("keyword", keyword);
		}
		DTablePageModel dtPageModel = regionHandler.queryTeamUser(params, super.getPageData());
		String jsonData = JSONUtil.serialize(dtPageModel);
		PrintWriter pw = super.getResponse().getWriter();
		pw.write(jsonData);
		pw.flush();
	}
	
	/**
	 * 新增配送小组页面跳转
	 * @return
	 */

	@Action(value="updateDispatchTeam",results={
			@Result(name=SUCCESS,location="/view/region/dispatchTeamAdd.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String updateDispatchTeam(){
		String result = ERROR;
		try{
			SysUser sysUser = (SysUser) this.getSession().get("account");
			if(sysUser.getOrgId() != null){
				this.getRequest().put("orgId", sysUser.getOrgId());
			}
			result = SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请联系管理员...");
			return ERROR;
		}
		return result;
	}

	/**
	 *	新增或修改配送小组
	 * @return
	 */
	@Action(value="saveOrUpdateDispatchTeam",results={
			@Result(name=SUCCESS,location="/view/region/dispatchTeamList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String saveOrUpdateDispatchTeam(){
		try{
			if(dispatchingTeam.getTeamId() == null){
				dispatchingTeam.setTeamNo(DateUtil.getNo("P"));
				regionHandler.saveOrUpdateDispatchingTeam(dispatchingTeam);
			}
			else{
				regionHandler.saveOrUpdateDispatchingTeam(dispatchingTeam);
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "保存失败，请联系管理员...");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改配送小组页面跳转
	 * @return
	 */
	@Action(value="addOrModifyDispatchTeam",results={
			@Result(name=SUCCESS,location="/view/region/dispatchTeamAdd.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String addOrModifyDispatchTeam(){
		try{
			String dispatchTeamId = this.getRequestParm().getParameter("teamId");
			if(StringUtils.isNotBlank(dispatchTeamId)){
				Integer teamId = Integer.valueOf(dispatchTeamId);
				DispatchingTeam dispatchingTeam = regionHandler.queryDispatchingTeamById(teamId);
				this.getRequest().put("dispatchingTeam", dispatchingTeam);
				if(dispatchingTeam.getMainUser() != null){
					SysUser mainUser = sysFuncHandler.querySysUserById(dispatchingTeam.getMainUser());
					this.getRequest().put("mainUser", mainUser);
				}
				if(dispatchingTeam.getOtherUser() != null){
					SysUser otherUser = sysFuncHandler.querySysUserById(dispatchingTeam.getOtherUser());
					this.getRequest().put("otherUser", otherUser);
				}
				if(dispatchingTeam.getCarId() != null){
					CarInfo carInfo = carHandler.queryCarById(dispatchingTeam.getCarId());
					this.getRequest().put("carInfo", carInfo);
				}
				if(dispatchingTeam.getCarLineId() != null){
					CarLine carLine = carHandler.queryCarLineById(dispatchingTeam.getCarLineId());
					this.getRequest().put("carLine", carLine);
				}
				SysUser sysUser = (SysUser) this.getSession().get("account");
				if(sysUser.getOrgId() != null){
					this.getRequest().put("orgId", sysUser.getOrgId());
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请联系管理员...");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询配送小组详情
	 * @return
	 */
	@Action(value="queryDispatchTeamDt",results={
			@Result(name=SUCCESS,location="/view/region/dispatchTeamDetail.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String queryDispatchTeamDt(){
		try{
			String dispatchTeamId = this.getRequestParm().getParameter("teamId");
			if(StringUtils.isNotBlank(dispatchTeamId)){
				Integer teamId = Integer.valueOf(dispatchTeamId);
				DispatchingTeam dispatchingTeam = regionHandler.queryDispatchingTeamById(teamId);
				this.getRequest().put("dispatchingTeam", dispatchingTeam);
				if(dispatchingTeam.getMainUser() != null){
					SysUser mainUser = sysFuncHandler.querySysUserById(dispatchingTeam.getMainUser());
					this.getRequest().put("mainUser", mainUser);
				}
				if(dispatchingTeam.getOtherUser() != null){
					SysUser otherUser = sysFuncHandler.querySysUserById(dispatchingTeam.getOtherUser());
					this.getRequest().put("otherUser", otherUser);
				}
				if(dispatchingTeam.getCarId() != null){
					CarInfo carInfo = carHandler.queryCarById(dispatchingTeam.getCarId());
					this.getRequest().put("carInfo", carInfo);
				}
				if(dispatchingTeam.getCarLineId() != null){
					CarLine carLine = carHandler.queryCarLineById(dispatchingTeam.getCarLineId());
					this.getRequest().put("carLine", carLine);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "查询失败，请联系管理员...");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除配送小组
	 * @return
	 */
	@Action(value="deleteDispatchTeam",results={
			@Result(name=SUCCESS,location="/view/region/dispatchTeamList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String deleteDispatchTeam(){
		try{
			String dispatchTeamId = this.getRequestParm().getParameter("teamId");
			if(StringUtils.isNotBlank(dispatchTeamId)){
				Integer teamId = Integer.valueOf(dispatchTeamId);
				DispatchingTeam dispatchingTeam = regionHandler.queryDispatchingTeamById(teamId);
				regionHandler.deleteDispatchingTeam(dispatchingTeam);
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "删除失败，请联系管理员...");
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 根据用户ID查询用户信息
	 * @return
	 */
	
	@Action(value="userMap",results={
			@Result(name=SUCCESS,location="/view/region/dispatchTeamMap.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String userMap(){
		try{
			String userId = this.getRequestParm().getParameter("userId");
			if(StringUtils.isNotBlank(userId)){
				Integer id = Integer.valueOf(userId);
				SysUser user  = regionHandler.userMap(id);
				String jsonData = JSONUtil.serialize(user);
				
				getRequest().put("jsonData", jsonData);
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "删除失败，请联系管理员...");
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	public DispatchingTeam getDispatchingTeam() {
		return dispatchingTeam;
	}

	public void setDispatchingTeam(DispatchingTeam dispatchingTeam) {
		this.dispatchingTeam = dispatchingTeam;
	}
	
}
