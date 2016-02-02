package com.smart.om.web.sys;

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

import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.SysDept;
import com.smart.om.persist.SysDict;
import com.smart.om.persist.SysUser;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.web.base.BaseAction;

/**
 * 系统部门管理Action
 * @author CA
 *
 */
@Namespace("/view/sys/sysDept")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class SysDeptAction  extends BaseAction{
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(SysUserAction.class);
	@Resource
	private SysFuncHandler sysFuncHandler;
	@Resource
    private SysAssistHandler sysAssistHandler;
	
	private SysDept sysDept;
	
	public SysDept getSysDept() {
		return sysDept;
	}

	public void setSysDept(SysDept sysDept) {
		this.sysDept = sysDept;
	}

	/**
	 * 查询部门
	 */
	@Action(value = "querySysDeptDt", results = {
			@Result(name = SUCCESS, location = "/view/sys/deptListDetil.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String querySysDeptDt(){
		String result = SUCCESS;
		try {
			String sysDeptId = this.getRequestParm().getParameter("deptId");
			if(StringUtils.isNotBlank(sysDeptId)){
				Integer deptId = Integer.valueOf(sysDeptId);
				SysDept sysDept = sysFuncHandler.querySysDeptById(deptId);
				this.getRequest().put("sysDept", sysDept);
			}
			else{
				this.getRequest().put("error", "系统异常，请重试！");
				return ERROR;
			}
			result = SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
			result = ERROR;
		}
		return result;
	}
	
	/**
	 * 查询部门分页信息
	 */
	@Action(value="querySysDeptPage")
	public void querySysDeptPage(){
		SysUser sysUser = (SysUser) this.getSession().get("account");
		try{
			//搜索关键字
			String keyword = this.getRequestParm().getParameter("keyword");
            String nodeId = this.getRequestParm().getParameter("id");
            String nodePid = this.getRequestParm().getParameter("pid");
            String level = this.getRequestParm().getParameter("level");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			if(sysUser.getOrgId() != null){
				params.put("orgId", sysUser.getOrgId());//所属分公司
				params.put("regionId", sysUser.getDictRegionId());
				params.put("proviceId", sysUser.getDictProviceId());
			}else{
				params.put("orgName2", "2");
			}
			
//			if(StringUtils.isNotBlank(level) && "0".equals(level) ){
//				params.put("orgName", level);
//			}else if(StringUtils.isNotBlank(level) && !"0".equals(level) && StringUtils.isNotBlank(nodeId)){
//				if(Const.IS_REGION.equals(level)){//点击区域
//            		params.put("dictRegionId", nodeId);
//            	}else if(Const.IS_PROVICE.equals(level)){//点击省份
//            		params.put("dictProviceId", nodeId);
//            	}else if(Const.IS_ORG.equals(level)) {//点击服务站
//            		params.put("dictOrgId", nodeId);
//            	}
//			}
			
			DTablePageModel dtPageModel = sysFuncHandler.querySysDeptPage(params, super.getPageData());
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
			SysUser sysUser = (SysUser) this.getSession().get("account");
			// 搜索关键字
			String userStatus = this.getRequestParm().getParameter("userStatus");
			String searchAdUser = this.getRequestParm().getParameter("keyword");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("searchAdUser", searchAdUser);
			if(StringUtils.isNotBlank(userStatus)){
				params.put("userStatus", userStatus);
			}
			if(sysUser.getOrgId() != null){
				params.put("orgId", sysUser.getOrgId());
			}
			DTablePageModel dtPageModel = sysFuncHandler.querySysDeptUserPage(
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
	 * 增加部门
	 */
	@Action(value="sysDeptAdd",results={
			@Result(name=SUCCESS,location="/view/sys/deptList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String sysDeptAdd(){
		try{
			SysUser sessionUser = (SysUser) this.getSession().get("account");
			String dictId = this.getRequestParm().getParameter("dictId");
			String dictPid = this.getRequestParm().getParameter("dictPid");
			String regionId = this.getRequestParm().getParameter("regionId");
			String treeNode = this.getRequestParm().getParameter("treeNode");
			if(sysDept.getDeptId() == null){
					if(StringUtils.isNotBlank(treeNode)){
						if(Const.IS_REGION.equals(treeNode)){
							sysDept.setDictRegionId(Integer.valueOf(dictId));
						}
						else if(Const.IS_PROVICE.equals(treeNode)){
							sysDept.setDictProviceId(Integer.valueOf(dictId));
							sysDept.setDictRegionId(Integer.valueOf(dictPid));
						}
						else if(Const.IS_ORG.equals(treeNode)){
							sysDept.setOrgId(Integer.valueOf(dictId));
							sysDept.setDictProviceId(Integer.valueOf(dictPid));
							sysDept.setDictRegionId(Integer.valueOf(regionId));
						}
						else{
							this.getRequest().put("errors", "系统异常，请联系管理员...");
							return ERROR;
						}
					}
					else if(StringUtils.isBlank(treeNode)){
						sysDept.setOrgId(sessionUser.getOrgId());
						sysDept.setDictProviceId(sessionUser.getDictProviceId());
						sysDept.setDictRegionId(sessionUser.getDictRegionId());
					}
				sysFuncHandler.saveOrUpdateDept(sysDept);
			}
			else if(sysDept.getDeptId() != null){
				if(StringUtils.isNotBlank(treeNode)){
					if(Const.IS_REGION.equals(treeNode)){
						sysDept.setDictRegionId(Integer.valueOf(dictId));
					}
					else if(Const.IS_PROVICE.equals(treeNode)){
						sysDept.setDictProviceId(Integer.valueOf(dictId));
						sysDept.setDictRegionId(Integer.valueOf(dictPid));
					}
					else if(Const.IS_ORG.equals(treeNode)){
						sysDept.setOrgId(Integer.valueOf(dictId));
						sysDept.setDictProviceId(Integer.valueOf(dictPid));
						sysDept.setDictRegionId(Integer.valueOf(regionId));
					}
					else{
						this.getRequest().put("errors", "系统异常，请联系管理员...");
						return ERROR;
					}
				}
				sysFuncHandler.saveOrUpdateDept(sysDept);
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试！");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 新增部门页面跳转
	 */
	@Action(value="sysDeptAddChange",results={
			@Result(name=SUCCESS,location="/view/sys/deptAdd.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String sysDeptAddChange(){
		try{
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
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请联系管理员...");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 新增或修改部门信息页面跳转
	 */
	@Action(value="addOrModifySysDept",results={
			@Result(name=SUCCESS,location="/view/sys/deptAdd.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String addOrModifySysDept(){
		try{
			String deptId = this.getRequestParm().getParameter("deptId");
			SysUser user = (SysUser) this.getSession().get("account");
			if(user != null){
				if(user.getOrgId() != null){
					this.getRequest().put("beforeShow","1");
				}
			}
			if(StringUtils.isNotBlank(deptId)){
				Integer sysDeptId = Integer.valueOf(deptId);
				SysDept sysDept = sysFuncHandler.querySysDeptById(sysDeptId);
				Integer sysUserId = sysDept.getDeptChief();
				SysUser sysUser = sysFuncHandler.querySysUserById(sysUserId);
				if(sysDept.getOrgId() != null){
					SysDict sysDict = sysAssistHandler.queryDictById(sysDept.getOrgId());
					this.getRequest().put("sysDict", sysDict);
				}
				else{
					if(sysDept.getDictProviceId() != null){
						SysDict sysDict = sysAssistHandler.queryDictById(sysDept.getDictProviceId());
						this.getRequest().put("sysDict", sysDict);
					}
					else{
						SysDict sysDict = sysAssistHandler.queryDictById(sysDept.getDictRegionId());
						this.getRequest().put("sysDict", sysDict);
					}
				}
				getRequest().put("sysUser", sysUser);
				getRequest().put("sysDept", sysDept);
			}
			else{
				this.getRequest().put("errors","系统异常，请重试！");
				return ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors","系统异常，请重试！");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除部门信息
	 */
	@Action(value="deleteSysDept",results={
			@Result(name=SUCCESS,location="/view/sys/deptList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String deleteSysDept(){
		try{
			String sysDeptId = this.getRequestParm().getParameter("deptId");
			if(StringUtils.isNotBlank(sysDeptId)){
				Integer deptId = Integer.valueOf(sysDeptId);
				sysFuncHandler.deleteSysDeptById(deptId);
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
}
