package com.smart.om.web.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import com.smart.om.biz.sys.RoleHandler;
import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.SysDict;
import com.smart.om.persist.SysMenu;
import com.smart.om.persist.SysRole;
import com.smart.om.persist.SysRoleAuth;
import com.smart.om.persist.SysUser;
import com.smart.om.util.Const;
import com.smart.om.util.Tools;
import com.smart.om.util.ZTreeNode;
import com.smart.om.web.base.BaseAction;

/**
 * 角色管理Action
 * @author langyuk
 *
 */
@Namespace("/view/sys/sysRole") 
@Results( {  @Result(name = "error", location = "/view/error.jsp") })
public class SysRoleAction  extends BaseAction{
	private static final Logger logger = Logger.getLogger(SysRoleAction.class);	
	@Resource
	private RoleHandler roleHandler;
	@Resource
	private SysFuncHandler sysFuncHandler;
	@Resource
	private SysAssistHandler sysAssistHandler;
	
	private SysRole sysRole;
	/**
	 * 查询角色
	 */
	@Action(value="queryRole",results = { @Result(name = SUCCESS, location = "/view/sys/roleList.jsp" ),
			   @Result(name = ERROR, location = "/view/failure.jsp") })
	public String queryRole() {
		SysUser user = (SysUser) this.getSession().get("account");
		List<SysRole> roleList = new ArrayList<SysRole>();
		try {
			if(user.getOrgId() != null){
				roleList = roleHandler.getRoleList(user,Const.IS_CHILD);
			}
			else{
				roleList = roleHandler.getRoleList(user,Const.IS_PARENT);
			}
			this.getRequest().put("roleList", roleList);
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", " 查询角色");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
		}
		return SUCCESS;
	}
	
	/**
	 * 添加角色
	 */
	@Action(value="addRole",results = { @Result(name = SUCCESS, location = "/view/sys/sysRole/queryRole.action" ,type = "redirect"),
			   @Result(name = ERROR, location = "/view/failure.jsp") })
	public String addRole(){
		try {
			sysRole.setNewer(true);		
			sysRole.setIsDel(Const.IS_DEL_FALSE);
			SysRole role = roleHandler.addRoles(sysRole);
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", "添加角色");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
		}
		return SUCCESS;
	}
	
	/**
	 * 刪除角色
	 * @return
	 * @throws IOException
	 */
	@Action(value="delRole")
	public String delRole() throws IOException {
		try {
			SysUser user = (SysUser) this.getSession().get("account");
			String roleId = this.getRequestParm().getParameter("roleId");
			SysRole sysRole = roleHandler.queryRoleById(Integer.valueOf(roleId));
			sysRole.setIsDel(Const.IS_DEL_TRUE);
			sysRole.setNewer(false);
			SysRole role = roleHandler.addRoles(sysRole);
			this.getResponse().getWriter().print(true);
		} catch (Exception e) {
			e.printStackTrace();
			this.getResponse().getWriter().print(false);
		}
		return null;
	}
	
	/**
	 * 更新角色对应的权限
	 * @return
	 * @throws IOException
	 */
	@Action(value="updateAuth")
	public String updateAuth()  throws IOException {
		try {
//			SysUser user = (SysUser) this.getSession().get("account");
			String roleId = this.getRequestParm().getParameter("roleId");
			String serv = this.getRequestParm().getParameter("servState");
			String ids = this.getRequestParm().getParameter("id");
//			String menutype = "";
			String servState = "";
			List<String> menuList = Tools.strToList(ids, ",");
			if(StringUtils.isNotBlank(serv) && "1".equals(serv)){
				servState = "TREE";
			}
			else if(StringUtils.isNotBlank(serv) && "2".equals(serv)){
				servState = "APP";
			}
//			servState = "TREE";		
			SysRole role = roleHandler.queryRoleById(Integer.valueOf(roleId));
			this.getRequest().put("role", role);
			/** 先删除权限菜单列表 */
			List alist = roleHandler.getSysRoleAuth(Integer.valueOf(roleId), servState);
			if (alist != null) {
				for (int i = 0; i < alist.size(); i++) {
					SysRoleAuth sysRA = (SysRoleAuth) alist.get(i);
					Integer id = sysRA.getRoleAuthId();
					roleHandler.delSysRoleAuth(id);
				}
			}	
			if(ids != null ){
				for(String menuId : menuList){
					if(StringUtils.isNotBlank(menuId)){
						SysRoleAuth sys = new SysRoleAuth();
						
						SysMenu sysMenu = sysFuncHandler.querySysMenu(Integer.valueOf(menuId));
						if(sysMenu.getMenuType() == 2){
							sys.setServState(Const.AUTH_SERV_APP);
						}
						else if(sysMenu.getMenuType() == 1){
							sys.setServState(Const.AUTH_SERV_TREE);
						}
						sys.setNewer(true);
						sys.setAuthId(Integer.valueOf(menuId));
						sys.setRoleId(Integer.valueOf(roleId));
						roleHandler.getSaveAuth(sys);
					}
				}
			}
			this.getResponse().getWriter().print(true);
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", "更新角色对应的权限");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
			this.getResponse().getWriter().print(false);
		}
		return null;
	}
	
	@Action(value="getMenuTreeData")
	public void getMenuTreeData() {
		String roleId = this.getRequestParm().getParameter("roleId");
		String menutype = this.getRequestParm().getParameter("menutype");
		String serv = "";
		logger.info("menutype:" + menutype);
		try{
			SysUser user = (SysUser) this.getSession().get("account");
			StringBuffer hql = new StringBuffer();
			hql.append(" from SysMenu as model where 1=1 and model.isDel = 0  and model.menuType = ").append(Integer.valueOf(menutype));			
			hql.append(" order by model.menuLevel,model.menuSort  ");
			List<SysMenu> sysTreeList = sysFuncHandler.queryMenu(hql.toString(), null);
			if(menutype != null && "1".equals(menutype)){
				serv = "TREE";
			}
			else if(menutype != null && "2".equals(menutype)){
				serv = "APP";
			}
			logger.info("serv:" + serv);
			String orgName = "后台所有菜单";
			ZTreeNode root = new ZTreeNode();
			root.setName(orgName);
			root.setOpen(true);
			this.setMenuChildNodes(root,sysTreeList,"menuId","menuPid","menuId","menuName",roleId,serv);
			String jsonData = JSONUtil.serialize(new ZTreeNode[]{root});	
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	private void setMenuChildNodes(ZTreeNode node,List<SysMenu> list,String idFiled,String pidField,String key,String name,String roleId,String servState){
		try {
			if(list != null){
				for (SysMenu sysMenu : list) {
					//根据菜单ID，角色ID查询是否在菜单权限中
					Integer menuId = sysMenu.getMenuId();
					Integer menuPid = sysMenu.getMenuPid();
					String menuName = sysMenu.getMenuName();
					String pid = "";
					if(menuPid != null){
						pid = menuPid.toString();
					}		
					if(node.getId() == null) {
						node.setId("");
					}
					if(node.getId().equals(pid)) {						
						ZTreeNode childNode = new ZTreeNode();
						childNode.setId(menuId.toString());
						childNode.setPid(node.getId());
						childNode.setKey(menuId.toString());
						childNode.setName(menuName.toString());
						childNode.setOpen(true);
						childNode.setLevel(node.getLevel() + 1);
						boolean flag = roleHandler.getMenuRoleAuth(Integer.valueOf(roleId), menuId, servState);
						if(flag){
							childNode.setChecked(flag);
						}
						setMenuChildNodes(childNode,list,idFiled,pidField,key,name,roleId,servState);
						node.getChildren().add(childNode);
					}
				}
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 进入向角色中添加员工
	 * @return
	 */
	@Action(value="preAddEmpToRole",results = { @Result(name = SUCCESS, location = "/view/sys/deptTreeIndex.jsp" ),
			   @Result(name = ERROR, location = "/view/failure.jsp") })
	public String preAddEmpToRole() {
		try {
			String dictId = this.getRequestParm().getParameter("dictId");
			String roleId = this.getRequestParm().getParameter("roleId");
			logger.info("roleId:" + roleId);
			SysUser user = (SysUser) this.getSession().get("account");
			List<SysUser> userList = new ArrayList<SysUser>();
			if(user.getOrgId() == null){
				if(roleId == null){
					roleId = (String) this.getSession().get("roleId");
				}
				SysRole sysRole = roleHandler.queryRoleById(Integer.valueOf(roleId));
				if(Const.IS_CHILD.equals(sysRole.getIsOrgId())){
					if(StringUtils.isNotBlank(dictId)){
						userList = roleHandler.queryAllUserByOrg(Integer.valueOf(dictId));
						SysDict sysDict = sysAssistHandler.queryDictById(Integer.valueOf(dictId)); 
						List<SysDict> dictList = sysAssistHandler.queryAllChildCompany();
						//获得角色名称
						String roleName = roleHandler.queryRoleById(Integer.valueOf(roleId)).getRoleName();
						//查询角色的所有用户
						List<SysUser> roleUserList = roleHandler.queryAllEmpInTheRole(roleId);
						this.getRequest().put("userList", userList);
						this.getRequest().put("dictList", dictList);
						this.getRequest().put("roleUserList", roleUserList);
						this.getSession().put("roleId", roleId);
						this.getRequest().put("roleName", roleName);
						this.getRequest().put("sysDict", sysDict);
					}
					else{
						userList =roleHandler.queryAllUser();
						List<SysDict> dictList = sysAssistHandler.queryAllChildCompany();
						//获得角色名称
						String roleName = roleHandler.queryRoleById(Integer.valueOf(roleId)).getRoleName();
						//查询角色的所有用户
						List<SysUser> roleUserList = roleHandler.queryAllEmpInTheRole(roleId);
						this.getRequest().put("dictList", dictList);
						this.getRequest().put("userList", userList);
						this.getRequest().put("roleUserList", roleUserList);
						this.getSession().put("roleId", roleId);
						this.getRequest().put("roleName", roleName);
					}
				}else{
					userList = roleHandler.queryAllUserByRegion();
					//获得角色名称
					String roleName = roleHandler.queryRoleById(Integer.valueOf(roleId)).getRoleName();
					//查询角色的所有用户
					List<SysUser> roleUserList = roleHandler.queryAllEmpInTheRole(roleId);
					this.getRequest().put("userList", userList);
					this.getRequest().put("roleUserList", roleUserList);
					this.getSession().put("roleId", roleId);
					this.getRequest().put("roleName", roleName);
					this.getRequest().put("str", "1");
				}
				
			}
			else{
				userList = roleHandler.queryAllUserByOrg(user.getOrgId());
				SysDict sysDict = sysAssistHandler.queryDictById(user.getOrgId());
				if(roleId == null){
					roleId = (String) this.getSession().get("roleId");
				}
				String roleName = roleHandler.queryRoleById(Integer.valueOf(roleId)).getRoleName();
				List<SysUser> roleUserList = roleHandler.queryAllEmpInTheRole(roleId); 
				
				this.getRequest().put("userList", userList);
				this.getRequest().put("roleUserList", roleUserList);
				this.getSession().put("roleId", roleId);
				this.getRequest().put("roleName", roleName);
				this.getRequest().put("sysDict", sysDict);
				this.getRequest().put("str", "0");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", "显示所有模块以及菜单的树型菜单显示");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 向选中的角色中添加员工对象
	 * @return
	 */
	@Action(value="addEmpToRole",results = { @Result(name = SUCCESS, location = "/view/sys/sysRole/preAddEmpToRole.action" ,type = "redirect" ),
			   @Result(name = ERROR, location = "/view/error.jsp") })
	public String addEmpToRole() {
		String result = ERROR;
		try {
			SysUser user = (SysUser) this.getSession().get("account");
			String roleId = (String)this.getSession().get("roleId");
			String[] empIds = this.getRequestParm().getParameterValues("empIds");
			boolean flag = roleHandler.addEmpToRole(roleId, empIds);
			if (flag) {
				result = SUCCESS;
			} else {
				this.getRequest().put("errors",	"很抱歉，向该角色中添加用户失败！");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
			return result;
		}
		return result;
	}
	
	/**
	 * 从该角色中移除某个用户
	 * @return
	 */
	@Action(value="delEmpFromRole",results = { @Result(name = SUCCESS, location = "/view/sys/sysRole/preAddEmpToRole.action" ,type = "redirect"  ),
			   @Result(name = ERROR, location = "/view/failure.jsp") })
	public String delEmpFromRole() {
		String result = "ERROR";
		try {
			SysUser user = (SysUser) this.getSession().get("account");
			String roleId = (String) this.getSession().get("roleId");
			String empId = this.getRequestParm().getParameter("empId");
			boolean flag = roleHandler.delEmpFromRole(roleId,new String[] { empId });
			if (flag) {
				result = SUCCESS;
			} else {
				this.getRequest().put("where", "从该角色中移除某个用户");
				this.getRequest().put("errors",	"很抱歉，从该角色中移除用户失败！");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", "从该角色中移除某个用户");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	public SysRole getSysRole() {
		return sysRole;
	}
	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}	
}
