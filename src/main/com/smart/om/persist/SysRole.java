package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * SysRole entity. @author MyEclipse Persistence Tools
 */

public class SysRole extends BasePo{

	// Fields

	private Integer roleId;
	private String roleName;
	private String roleDesc;
	private String roleGroup;
	private String isDel;
	private String isOrgId;
	// Constructors

	/** default constructor */
	public SysRole() {
	}

	/** full constructor */
	public SysRole(String roleName, String roleDesc, String roleGroup) {
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.roleGroup = roleGroup;
	}

	// Property accessors

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleGroup() {
		return this.roleGroup;
	}

	public void setRoleGroup(String roleGroup) {
		this.roleGroup = roleGroup;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getIsOrgId() {
		return isOrgId;
	}

	public void setIsOrgId(String isOrgId) {
		this.isOrgId = isOrgId;
	}
}