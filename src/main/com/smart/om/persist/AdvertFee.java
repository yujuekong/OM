package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * AdvertFee entity. @author MyEclipse Persistence Tools
 */

public class AdvertFee extends BasePo{

	// Fields

	private Integer advertFeeId;
	private Integer advertInfoId;
	private Integer advertUserId;
	private Double advertPrice;
	private Double totalPrice;
	private String feeStatus;

	// Constructors

	/** default constructor */
	public AdvertFee() {
	}

	/** full constructor */
	public AdvertFee(Integer advertInfoId, Integer advertUserId,
			Double advertPrice, Double totalPrice) {
		this.advertInfoId = advertInfoId;
		this.advertUserId = advertUserId;
		this.advertPrice = advertPrice;
		this.totalPrice = totalPrice;
	}

	// Property accessors

	public Integer getAdvertFeeId() {
		return this.advertFeeId;
	}

	public void setAdvertFeeId(Integer advertFeeId) {
		this.advertFeeId = advertFeeId;
	}

	public Integer getAdvertInfoId() {
		return this.advertInfoId;
	}

	public void setAdvertInfoId(Integer advertInfoId) {
		this.advertInfoId = advertInfoId;
	}

	public Integer getAdvertUserId() {
		return this.advertUserId;
	}

	public void setAdvertUserId(Integer advertUserId) {
		this.advertUserId = advertUserId;
	}

	public Double getAdvertPrice() {
		return this.advertPrice;
	}

	public void setAdvertPrice(Double advertPrice) {
		this.advertPrice = advertPrice;
	}

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getFeeStatus() {
		return feeStatus;
	}

	public void setFeeStatus(String feeStatus) {
		this.feeStatus = feeStatus;
	}

	
	

}