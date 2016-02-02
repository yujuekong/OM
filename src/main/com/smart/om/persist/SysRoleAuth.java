package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * SysRoleAuth entity. @author MyEclipse Persistence Tools
 */

public class SysRoleAuth extends BasePo {

	// Fields

	private Integer roleAuthId;
	private Integer roleId;
	private Integer authId;
	private String servState;

	// Constructors

	/** default constructor */
	public SysRoleAuth() {
	}

	/** full constructor */
	public SysRoleAuth(Integer roleId, Integer authId, String servState) {
		this.roleId = roleId;
		this.authId = authId;
		this.servState = servState;
	}

	// Property accessors

	public Integer getRoleAuthId() {
		return this.roleAuthId;
	}

	public void setRoleAuthId(Integer roleAuthId) {
		this.roleAuthId = roleAuthId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getAuthId() {
		return this.authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public String getServState() {
		return this.servState;
	}

	public void setServState(String servState) {
		this.servState = servState;
	}

}