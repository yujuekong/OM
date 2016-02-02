package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * StorageWarehouse entity. @author MyEclipse Persistence Tools
 */

public class StorageWarehouse extends BasePo{

	// Fields

	private Integer warehouseId;
	private String warehouseNo;
	private String warehouseName;
	private String warehouseAddress;
	private String warehouseSize;
	private String warehouseStatus;
	private String createDate;
	private String warehouseLng;
	private String warehouseLat;
	private Integer dictRegionId;//区域ID
	private String dictRegionName;//区域名称
	private Integer dictProviceId;//省ID
	private String dictProviceName;//省名称
	private Integer dictOrgId;//分公司ID
	private String dictOrgName;//分公司名称
	private String isRefrigeratory;
	// Constructors

	/** default constructor */
	public StorageWarehouse() {
	}

	/** full constructor */
//	public StorageWarehouse(String warehouseNo, String warehouseName,
//			String warehouseAddress, String warehouseSize,
//			String warehouseStatus, String createDate, String warehouseLng,
//			String warehouseLat, Integer dictRegionId, Integer dictProviceId) {
//		this.warehouseNo = warehouseNo;
//		this.warehouseName = warehouseName;
//		this.warehouseAddress = warehouseAddress;
//		this.warehouseSize = warehouseSize;
//		this.warehouseStatus = warehouseStatus;
//		this.createDate = createDate;
//		this.warehouseLng = warehouseLng;
//		this.warehouseLat = warehouseLat;
//		this.dictRegionId = dictRegionId;
//		this.dictProviceId = dictProviceId;
//	}

	// Property accessors

	public Integer getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	public String getWarehouseName() {
		return this.warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getWarehouseAddress() {
		return this.warehouseAddress;
	}

	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}

	public String getWarehouseSize() {
		return this.warehouseSize;
	}

	public void setWarehouseSize(String warehouseSize) {
		this.warehouseSize = warehouseSize;
	}

	public String getWarehouseStatus() {
		return this.warehouseStatus;
	}

	public void setWarehouseStatus(String warehouseStatus) {
		this.warehouseStatus = warehouseStatus;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getWarehouseLng() {
		return this.warehouseLng;
	}

	public void setWarehouseLng(String warehouseLng) {
		this.warehouseLng = warehouseLng;
	}

	public String getWarehouseLat() {
		return this.warehouseLat;
	}

	public void setWarehouseLat(String warehouseLat) {
		this.warehouseLat = warehouseLat;
	}

	public Integer getDictRegionId() {
		return dictRegionId;
	}

	public void setDictRegionId(Integer dictRegionId) {
		this.dictRegionId = dictRegionId;
	}

	public Integer getDictProviceId() {
		return dictProviceId;
	}

	public void setDictProviceId(Integer dictProviceId) {
		this.dictProviceId = dictProviceId;
	}

	public String getDictRegionName() {
		return dictRegionName;
	}

	public void setDictRegionName(String dictRegionName) {
		this.dictRegionName = dictRegionName;
	}

	public String getDictProviceName() {
		return dictProviceName;
	}

	public void setDictProviceName(String dictProviceName) {
		this.dictProviceName = dictProviceName;
	}

	public Integer getDictOrgId() {
		return dictOrgId;
	}

	public void setDictOrgId(Integer dictOrgId) {
		this.dictOrgId = dictOrgId;
	}

	public String getDictOrgName() {
		return dictOrgName;
	}

	public void setDictOrgName(String dictOrgName) {
		this.dictOrgName = dictOrgName;
	}

	public String getIsRefrigeratory() {
		return isRefrigeratory;
	}

	public void setIsRefrigeratory(String isRefrigeratory) {
		this.isRefrigeratory = isRefrigeratory;
	}
}