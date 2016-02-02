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

import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.SysDict;
import com.smart.om.persist.SysLog;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.web.base.BaseAction;
/**
 * 系统日志Action类
 * @author CA
 *
 */
@Namespace("/view/sys/sysLog")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class SysLogAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(SysLogAction.class);
	@Resource
	private SysFuncHandler sysFuncHandler;
	
	/**
	 * 查询日志分页信息
	 */
	@Action(value="querySysLogPage")
	public void querySysLogPage(){
		try{
			//搜索关键字
			String keyword = this.getRequestParm().getParameter("keyword");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			DTablePageModel dtPageModel = sysFuncHandler.querySysLogPage(params, super.getPageData());
			String jsonData = JSONUtil.serialize(dtPageModel);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除日志分页信息
	 */
	@Action(value="deleteSysLogPage",results={
			@Result(name=SUCCESS,location="/view/sys/sysLogList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String deleteSysLogPage(){
		try{
			String sysLogId = this.getRequestParm().getParameter("logId");
			if(StringUtils.isNotBlank(sysLogId)){
				sysFuncHandler.deleteSysLogById(Integer.valueOf(sysLogId));
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请联系管理员！");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 根据ID查询日志信息
	 */
	@Action(value="querySysLogDt",results={
			@Result(name=SUCCESS,location="/view/sys/sysLogListDt.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String querySysLogDt(){
		try{
			String sysLogId = this.getRequestParm().getParameter("logId");
			if(StringUtils.isNotBlank(sysLogId)){
				SysLog sysLog = sysFuncHandler.querySysLogById(Integer.valueOf(sysLogId));
				this.getRequest().put("sysLog", sysLog);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请联系管理员！");
			return ERROR;
		}
		return SUCCESS;
	}
}
