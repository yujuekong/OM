package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * StorageWarehousingEntry entity. @author MyEclipse Persistence Tools
 */

public class StorageWarehousingEntry extends BasePo{

	// Fields

	private Integer warehousingEntryId;
	private Integer sellerId;
	private Integer warehouseId;
	private String warehousingNo;
	private Integer bizType;
	private String remark;
	private String warehousingDate;
	private Integer userId;
	private String createDate;
	private String warehousingStatus;
	private String checkUserId;
	private String requestDate;
	private Integer allotId;
	
	private SysDict sysDict;
	private SellerInfo sellerInfo;
	private StorageWarehouse storageWarehouse;
	private SysUser sysUser;
//	private Set<StorageWarehousingDtl> storageWarehousingDtlList;

	// Constructors

	/** default constructor */
	public StorageWarehousingEntry() {
	}

	/** full constructor */
	public StorageWarehousingEntry(Integer sellerId, Integer warehouseId,
			String warehousingNo, Integer bizType, String remark,
			String warehousingDate, Integer userId, String createDate) {
		this.sellerId = sellerId;
		this.warehouseId = warehouseId;
		this.warehousingNo = warehousingNo;
		this.bizType = bizType;
		this.remark = remark;
		this.warehousingDate = warehousingDate;
		this.userId = userId;
		this.createDate = createDate;
	}

	// Property accessors

	public Integer getWarehousingEntryId() {
		return this.warehousingEntryId;
	}

	public void setWarehousingEntryId(Integer warehousingEntryId) {
		this.warehousingEntryId = warehousingEntryId;
	}

	public Integer getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehousingNo() {
		return this.warehousingNo;
	}

	public void setWarehousingNo(String warehousingNo) {
		this.warehousingNo = warehousingNo;
	}

	public Integer getBizType() {
		return bizType;
	}

	public void setBizType(Integer bizType) {
		this.bizType = bizType;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWarehousingDate() {
		return this.warehousingDate;
	}

	public void setWarehousingDate(String warehousingDate) {
		this.warehousingDate = warehousingDate;
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

	public SellerInfo getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(SellerInfo sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	public StorageWarehouse getStorageWarehouse() {
		return storageWarehouse;
	}

	public void setStorageWarehouse(StorageWarehouse storageWarehouse) {
		this.storageWarehouse = storageWarehouse;
	}

//	public Set<StorageWarehousingDtl> getStorageWarehousingDtlList() {
//		return storageWarehousingDtlList;
//	}
//
//	public void setStorageWarehousingDtlList(Set<StorageWarehousingDtl> storageWarehousingDtlList) {
//		this.storageWarehousingDtlList = storageWarehousingDtlList;
//	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getWarehousingStatus() {
		return warehousingStatus;
	}

	public void setWarehousingStatus(String warehousingStatus) {
		this.warehousingStatus = warehousingStatus;
	}

	public String getCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public SysDict getSysDict() {
		return sysDict;
	}

	public void setSysDict(SysDict sysDict) {
		this.sysDict = sysDict;
	}

	public Integer getAllotId() {
		return allotId;
	}

	public void setAllotId(Integer allotId) {
		this.allotId = allotId;
	}
}