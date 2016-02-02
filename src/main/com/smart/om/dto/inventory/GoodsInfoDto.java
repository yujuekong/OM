package com.smart.om.dto.inventory;

import java.io.Serializable;

public class GoodsInfoDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer goodsId;
    private String goodsBarCode;
    private String goodsName;
    private String goodsStatus;
    private String goodsTypeName;
    private Integer allotDtlId;
	private Integer allotId;
	private Double expectNumber;
	private Double actualNumber;
    
	public GoodsInfoDto() {
		super();
	}
	
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsBarCode() {
		return goodsBarCode;
	}
	public void setGoodsBarCode(String goodsBarCode) {
		this.goodsBarCode = goodsBarCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsStatus() {
		return goodsStatus;
	}
	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}
	public String getGoodsTypeName() {
		return goodsTypeName;
	}
	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	public Integer getAllotDtlId() {
		return allotDtlId;
	}

	public void setAllotDtlId(Integer allotDtlId) {
		this.allotDtlId = allotDtlId;
	}

	public Integer getAllotId() {
		return allotId;
	}

	public void setAllotId(Integer allotId) {
		this.allotId = allotId;
	}

	public Double getExpectNumber() {
		return expectNumber;
	}

	public void setExpectNumber(Double expectNumber) {
		this.expectNumber = expectNumber;
	}

	public Double getActualNumber() {
		return actualNumber;
	}

	public void setActualNumber(Double actualNumber) {
		this.actualNumber = actualNumber;
	}


    
    
}
