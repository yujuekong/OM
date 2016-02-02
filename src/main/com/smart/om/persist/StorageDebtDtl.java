package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * StorageDebtDtl entity. @author MyEclipse Persistence Tools
 */

public class StorageDebtDtl extends BasePo {

	// Fields

	private Integer debtDtlId;
	private Integer debtId;
	private Integer goodsId;
	private Integer debtType;
	private Double debtGoodsCount;
	private String debtDesc;

	private GoodsInfo goodsInfo;
	private SysDict sysDict;
	// Constructors

	/** default constructor */
	public StorageDebtDtl() {
	}

	/** full constructor */
	public StorageDebtDtl(Integer debtId, Integer goodsId,
						  Double debtGoodsCount, String debtDesc) {
		this.debtId = debtId;
		this.goodsId = goodsId;
		this.debtGoodsCount = debtGoodsCount;
		this.debtDesc = debtDesc;
	}

	// Property accessors

	public Integer getDebtDtlId() {
		return this.debtDtlId;
	}

	public void setDebtDtlId(Integer debtDtlId) {
		this.debtDtlId = debtDtlId;
	}

	public Integer getDebtId() {
		return this.debtId;
	}

	public void setDebtId(Integer debtId) {
		this.debtId = debtId;
	}

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Double getDebtGoodsCount() {
		return this.debtGoodsCount;
	}

	public void setDebtGoodsCount(Double debtGoodsCount) {
		this.debtGoodsCount = debtGoodsCount;
	}

	public String getDebtDesc() {
		return this.debtDesc;
	}

	public void setDebtDesc(String debtDesc) {
		this.debtDesc = debtDesc;
	}

	public GoodsInfo getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public Integer getDebtType() {
		return debtType;
	}

	public void setDebtType(Integer debtType) {
		this.debtType = debtType;
	}

	public SysDict getSysDict() {
		return sysDict;
	}

	public void setSysDict(SysDict sysDict) {
		this.sysDict = sysDict;
	}
}