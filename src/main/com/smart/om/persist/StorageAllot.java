package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * 调拨
 */

public class StorageAllot extends BasePo {

	// Fields

	private Integer allotId;
	private Integer startWarehouse;
	private Integer endWarehouse;
	private Integer createUser;
	private String createDate;
	private Integer auditingUser;
	private String auditingDate;
	private Integer checkUser;
	private String allotReason;
	private String allotStatus;
	private String allotNo;
	
	private StorageWarehouse startStorateWarehouse;
	private StorageWarehouse endStorateWarehouse;

	// Constructors

	/** default constructor */
	public StorageAllot() {
	}

	/** full constructor */
	public StorageAllot(Integer startWarehouse, Integer endWarehouse,
			Integer createUser, String createDate, Integer auditingUser,
			String auditingDate, Integer checkUser) {
		this.startWarehouse = startWarehouse;
		this.endWarehouse = endWarehouse;
		this.createUser = createUser;
		this.createDate = createDate;
		this.auditingUser = auditingUser;
		this.auditingDate = auditingDate;
		this.checkUser = checkUser;
	}

	// Property accessors

	public Integer getAllotId() {
		return this.allotId;
	}

	public void setAllotId(Integer allotId) {
		this.allotId = allotId;
	}

	public Integer getStartWarehouse() {
		return this.startWarehouse;
	}

	public void setStartWarehouse(Integer startWarehouse) {
		this.startWarehouse = startWarehouse;
	}

	public Integer getEndWarehouse() {
		return this.endWarehouse;
	}

	public void setEndWarehouse(Integer endWarehouse) {
		this.endWarehouse = endWarehouse;
	}

	public Integer getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Integer getAuditingUser() {
		return this.auditingUser;
	}

	public void setAuditingUser(Integer auditingUser) {
		this.auditingUser = auditingUser;
	}

	public String getAuditingDate() {
		return this.auditingDate;
	}

	public void setAuditingDate(String auditingDate) {
		this.auditingDate = auditingDate;
	}

	public Integer getCheckUser() {
		return this.checkUser;
	}

	public void setCheckUser(Integer checkUser) {
		this.checkUser = checkUser;
	}

	public String getAllotReason() {
		return allotReason;
	}

	public void setAllotReason(String allotReason) {
		this.allotReason = allotReason;
	}

	public String getAllotStatus() {
		return allotStatus;
	}

	public void setAllotStatus(String allotStatus) {
		this.allotStatus = allotStatus;
	}

	public String getAllotNo() {
		return allotNo;
	}

	public void setAllotNo(String allotNo) {
		this.allotNo = allotNo;
	}

	public StorageWarehouse getStartStorateWarehouse() {
		return startStorateWarehouse;
	}

	public void setStartStorateWarehouse(StorageWarehouse startStorateWarehouse) {
		this.startStorateWarehouse = startStorateWarehouse;
	}

	public StorageWarehouse getEndStorateWarehouse() {
		return endStorateWarehouse;
	}

	public void setEndStorateWarehouse(StorageWarehouse endStorateWarehouse) {
		this.endStorateWarehouse = endStorateWarehouse;
	}
	
	

}