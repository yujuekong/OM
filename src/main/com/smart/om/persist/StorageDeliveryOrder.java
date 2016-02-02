package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * StorageDeliveryOrder entity. @author MyEclipse Persistence Tools
 */

public class StorageDeliveryOrder extends BasePo {

	// Fields

	private Integer deliveryOrderId;
	private Integer warehouseId;
	private String deliveryNo;
	private String bizType;
	private String remark;
	private String deliveryDate;
	private Integer userId;
	private String createDate;
	private String deliveryStatus;
	private Integer checkUserId;
	private String requestDate;
	private String isClean;
	private String cleanTime;
	private Integer cleanUserId;
	private String deliveryHandover;
	private String agentHandover;
	private String cleanStatus;
	private Integer allotId;
	private String isSubmit;

	private StorageWarehouse storageWarehouse;
	private SysUser user;
	private SysUser checkUser;
	private SysUser cleanUser;
	// Constructors

	/** default constructor */
	public StorageDeliveryOrder() {
	}

	/** full constructor */
	public StorageDeliveryOrder( Integer warehouseId,
			String deliveryNo, String bizType, String remark,
			String deliveryDate, Integer userId, String createDate) {

		this.warehouseId = warehouseId;
		this.deliveryNo = deliveryNo;
		this.bizType = bizType;
		this.remark = remark;
		this.deliveryDate = deliveryDate;
		this.userId = userId;
		this.createDate = createDate;
	}

	// Property accessors

	public Integer getDeliveryOrderId() {
		return this.deliveryOrderId;
	}

	public void setDeliveryOrderId(Integer deliveryOrderId) {
		this.deliveryOrderId = deliveryOrderId;
	}



	public Integer getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getDeliveryNo() {
		return this.deliveryNo;
	}

	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	public String getBizType() {
		return this.bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	public StorageWarehouse getStorageWarehouse() {
		return storageWarehouse;
	}

	public void setStorageWarehouse(StorageWarehouse storageWarehouse) {
		this.storageWarehouse = storageWarehouse;
	}

	public SysUser getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(SysUser checkUser) {
		this.checkUser = checkUser;
	}

	public SysUser getCleanUser() {
		return cleanUser;
	}

	public void setCleanUser(SysUser cleanUser) {
		this.cleanUser = cleanUser;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Integer getCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(Integer checkUserId) {
		this.checkUserId = checkUserId;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getIsClean() {
		return isClean;
	}

	public void setIsClean(String isClean) {
		this.isClean = isClean;
	}

	public String getCleanTime() {
		return cleanTime;
	}

	public void setCleanTime(String cleanTime) {
		this.cleanTime = cleanTime;
	}

	public Integer getCleanUserId() {
		return cleanUserId;
	}

	public void setCleanUserId(Integer cleanUser) {
		this.cleanUserId = cleanUser;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public String getDeliveryHandover() {
		return deliveryHandover;
	}

	public void setDeliveryHandover(String deliveryHandover) {
		this.deliveryHandover = deliveryHandover;
	}

	public String getAgentHandover() {
		return agentHandover;
	}

	public void setAgentHandover(String agentHandover) {
		this.agentHandover = agentHandover;
	}

	public String getCleanStatus() {
		return cleanStatus;
	}

	public void setCleanStatus(String cleanStatus) {
		this.cleanStatus = cleanStatus;
	}

	public Integer getAllotId() {
		return allotId;
	}

	public void setAllotId(Integer allotId) {
		this.allotId = allotId;
	}

	public String getIsSubmit() {
		return isSubmit;
	}

	public void setIsSubmit(String isSubmit) {
		this.isSubmit = isSubmit;
	}
}