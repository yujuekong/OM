package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * StorageInventory entity. @author MyEclipse Persistence Tools
 */

public class StorageInventory extends BasePo{

	// Fields

	private Integer inventoryId;
	private Integer warehouseId;
	private Integer goodsId;
	private Double inventoryNumber;
	private Integer goodsUnit;

	private StorageWarehouse storageWarehouse;
	private GoodsInfo goodsInfo;
	// Constructors

	/** default constructor */
	public StorageInventory() {
	}

	/** full constructor */
	public StorageInventory(Integer warehouseId, Integer goodsId,
			Integer inventoryNumber, Integer goodsUnit) {
		this.warehouseId = warehouseId;
		this.goodsId = goodsId;
		this.goodsUnit = goodsUnit;
	}

	// Property accessors

	public Integer getInventoryId() {
		return this.inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Integer getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getGoodsUnit() {
		return this.goodsUnit;
	}

	public void setGoodsUnit(Integer goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public StorageWarehouse getStorageWarehouse() {
		return storageWarehouse;
	}

	public void setStorageWarehouse(StorageWarehouse storageWarehouse) {
		this.storageWarehouse = storageWarehouse;
	}

	public GoodsInfo getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public Double getInventoryNumber() {
		return inventoryNumber;
	}

	public void setInventoryNumber(Double inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}
}