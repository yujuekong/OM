package com.smart.om.biz.sys;

import java.util.List;

import com.smart.om.persist.SysRole;
import com.smart.om.persist.SysRoleAuth;
import com.smart.om.persist.SysUser;
import com.smart.om.persist.SysUserRole;

public interface RoleHandler {

	//-------------业务方法开始--------------
	/** 查询角色 **/
	public List<SysRole> getRoleList(SysUser sysUser,String isOrg);
	
	public List getSysRoleAuth(Integer roleId,String servState);
	
	public SysRoleAuth delSysRoleAuth(Integer roleId);
	
	public SysRole queryRoleById(Integer roleId);
	
	public SysRole addRoles(SysRole role);

	/**根据用户查询角色*/
	public String queryRoleByUser(Integer userId);
	public SysUser addSysUser(SysUser sysUser);
	
	public SysRole delRoles(Integer roleId);
	
	/** 根据角色类型查询角色 */
	public List<SysRole> queryRoleByType(String roleType);
	
	/** 根据菜单ID，角色ID查询是否在菜单权限中 */
	public boolean getMenuRoleAuth(Integer roleId,Integer menuId, String servState);
	
	public SysRoleAuth getSaveAuth(SysRoleAuth sysAuth);
	
	public SysUserRole saveSysUserRole(SysUserRole userRole);
	
	public SysUserRole getSysUserRole(SysUserRole userRole);
	
	/**查询用户的用户角色*/
	public List<SysUserRole> queryUserRoleByUser(SysUser sysUser);
	
	
	/**删除用户角色*/
	public SysUserRole delUserRole(Integer userRoleId);
	
	/**根据商家查询所有员工对象 */
	public List<SysUser> queryAllUserBySeller(Integer sellerId);
	
	/** 根据所属分公司查询用户 **/
	
	public List<SysUser> queryAllUserByOrg(Integer orgId);
	
	/** 查询所有用户 **/
	public List<SysUser> queryAllUser();
	
	/**根据角色编号查询该角色下的所有员工对象 */
	public List<SysUser> queryAllEmpInTheRole(String roleId);
	
	/**
	 * 根据角色编号和员工数组，将一组员工添加到选定的角色中
	 */
	public boolean addEmpToRole(String roleId, String[] userIds) ;
	
	/**
	 * 根据员工ID和角色数组，将一组角色添加到用户中
	 */
	public boolean addRoleToUser(String userId, String[] rolesId);
	
	/**
	 * 根据角色ID，用户ID，添加用户角色数据
	 */
	public boolean addUserRole(int roleId, int userId);
	
	/**
	 * 从选定的角色中移除某些用户(批量删除选定角色下的用户)
	 */
	public boolean delEmpFromRole(String roleId, String[] userIds);
	
	/**
	 * 查询总公司的用户
	 */
	public List<SysUser> queryAllUserByRegion();
	
}