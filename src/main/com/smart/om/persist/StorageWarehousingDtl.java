package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * StorageWarehousingDtl entity. @author MyEclipse Persistence Tools
 */

public class StorageWarehousingDtl extends BasePo{

	// Fields

	private Integer warehousingDtlId;
	private Integer warehousingEntryId;
	private Integer goodsId;
	private Double requestCount;
	private Double warehousingCount;
	private Double warehousingPrice;
	private Double warehousingAmount;
	private String remark;
	private String warehousingStatus;

	private StorageWarehousingEntry storageWarehousingEntry;
	private GoodsInfo goodsInfo;

	public Double getRequestCount() {
		return requestCount;
	}

	public void setRequestCount(Double requestCount) {
		this.requestCount = requestCount;
	}

	public Double getWarehousingCount() {
		return warehousingCount;
	}

	public void setWarehousingCount(Double warehousingCount) {
		this.warehousingCount = warehousingCount;
	}
	// Constructors

	/** default constructor */
	public StorageWarehousingDtl() {
	}

	/** full constructor */
	public StorageWarehousingDtl(Integer warehousingEntryId, Integer goodsId,
			Integer warehousingCount, Double warehousingPrice,
			Double warehousingAmount, String remark) {
		this.warehousingEntryId = warehousingEntryId;
		this.goodsId = goodsId;
		this.warehousingPrice = warehousingPrice;
		this.warehousingAmount = warehousingAmount;
		this.remark = remark;
	}

	// Property accessors

	public Integer getWarehousingDtlId() {
		return this.warehousingDtlId;
	}

	public void setWarehousingDtlId(Integer warehousingDtlId) {
		this.warehousingDtlId = warehousingDtlId;
	}

	public Integer getWarehousingEntryId() {
		return this.warehousingEntryId;
	}

	public void setWarehousingEntryId(Integer warehousingEntryId) {
		this.warehousingEntryId = warehousingEntryId;
	}

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Double getWarehousingPrice() {
		return this.warehousingPrice;
	}

	public void setWarehousingPrice(Double warehousingPrice) {
		this.warehousingPrice = warehousingPrice;
	}

	public Double getWarehousingAmount() {
		return this.warehousingAmount;
	}

	public void setWarehousingAmount(Double warehousingAmount) {
		this.warehousingAmount = warehousingAmount;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public com.smart.om.persist.StorageWarehousingEntry getStorageWarehousingEntry() {
		return storageWarehousingEntry;
	}

	public void setStorageWarehousingEntry(com.smart.om.persist.StorageWarehousingEntry storageWarehousingEntry) {
		this.storageWarehousingEntry = storageWarehousingEntry;
	}

	public GoodsInfo getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public String getWarehousingStatus() {
		return warehousingStatus;
	}

	public void setWarehousingStatus(String warehousingStatus) {
		this.warehousingStatus = warehousingStatus;
	}
}