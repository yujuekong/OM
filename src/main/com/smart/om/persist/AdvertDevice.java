package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * AdvertDevice entity. @author MyEclipse Persistence Tools
 */

public class AdvertDevice extends BasePo {

	// Fields

	private Integer adId;
	private Integer deviceId;
	private Integer advertInfoId;
	private Double advertFee;
	private String adStatus;

	// Constructors

	/** default constructor */
	public AdvertDevice() {
	}

	/** full constructor */
	public AdvertDevice(Integer deviceId, Integer advertInfoId, Double advertFee) {
		this.deviceId = deviceId;
		this.advertInfoId = advertInfoId;
		this.advertFee = advertFee;
	}

	// Property accessors

	public Integer getAdId() {
		return this.adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	public Integer getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getAdvertInfoId() {
		return this.advertInfoId;
	}

	public void setAdvertInfoId(Integer advertInfoId) {
		this.advertInfoId = advertInfoId;
	}

	public Double getAdvertFee() {
		return this.advertFee;
	}

	public void setAdvertFee(Double advertFee) {
		this.advertFee = advertFee;
	}

	public String getAdStatus() {
		return adStatus;
	}

	public void setAdStatus(String adStatus) {
		this.adStatus = adStatus;
	}

}