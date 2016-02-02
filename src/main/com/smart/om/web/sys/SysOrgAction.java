package com.smart.om.web.sys;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.SysDict;
import com.smart.om.web.base.BaseAction;

/**
 * 系统机构管理Action
 * @author ienovo
 *
 */
@Namespace("/view/sys/sysOrg")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class SysOrgAction  extends BaseAction{
	private static final Logger logger = Logger.getLogger(SysUserAction.class);
	@Resource
	private SysFuncHandler sysFuncHandler;

	/**
	 * 查询机构
	 */
	@Action(value = "querySysOrg", results = {
			@Result(name = SUCCESS, location = "/view/sys/orgList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String querySysOrg() {
		String result = SUCCESS;
		try {

		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", " 查询所有机构");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
		}
		return result;
	}
}
