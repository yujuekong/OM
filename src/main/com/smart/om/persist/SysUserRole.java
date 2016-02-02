package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * SysUserRole entity. @author MyEclipse Persistence Tools
 */

public class SysUserRole  extends BasePo {

	// Fields

	private Integer userRoleId;
	private Integer roleId;
	private Integer userId;
	private Integer orderNo;

	// Constructors

	/** default constructor */
	public SysUserRole() {
	}

	/** full constructor */
	public SysUserRole(Integer roleId, Integer userId, Integer orderNo) {
		this.roleId = roleId;
		this.userId = userId;
		this.orderNo = orderNo;
	}

	// Property accessors

	public Integer getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

}