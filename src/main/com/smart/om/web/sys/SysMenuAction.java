package com.smart.om.web.sys;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.persist.SysMenu;
import com.smart.om.web.base.BaseAction;

/**
 * 菜单管理Action
 * @author ienovo
 *
 */
@Namespace("/view/sys/sysMenu")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class SysMenuAction  extends BaseAction{
	private static final Logger logger = Logger.getLogger(SysMenuAction.class);
	@Resource
	private SysAssistHandler sysAssistHandler;

	/**
	 * 查询所有菜单
	 */
	@Action(value = "querySysMenu", results = {
			@Result(name = SUCCESS, location = "/view/sys/menuList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String querySysMenu() {
		String result = SUCCESS;
		try {
			List<SysMenu> sysMenuList = sysAssistHandler.querySysMenu();
			this.getRequest().put("sysMenuList", sysMenuList);
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", " 查询所有菜单");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
		}
		return result;
	}
	
	//删除系统菜单
	@Action(value="delMenu")
	public String delMenu() throws IOException {
		try {
			String menuId = this.getRequestParm().getParameter("menuId");
			SysMenu notice = sysAssistHandler.delMenuById(Integer.valueOf(menuId));
			this.getResponse().getWriter().print(true);
		} catch (Exception e) {
			e.printStackTrace();
			this.getResponse().getWriter().print(false);
		}
		return null;
	}
}
