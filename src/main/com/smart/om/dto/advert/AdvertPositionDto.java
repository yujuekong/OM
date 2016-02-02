package com.smart.om.dto.advert;

import java.io.Serializable;

public class AdvertPositionDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer advertInfoId;
	private String advertTitle;
	private String deviceNo;
	private String deviceName;
	private String deviceStatus;
	private String deviceAddress;
	private Integer deviceId;
	private Integer adId;
	private Double advertFee;
	private String adStatus;
	
	public Integer getAdvertInfoId() {
		return advertInfoId;
	}
	public void setAdvertInfoId(Integer advertInfoId) {
		this.advertInfoId = advertInfoId;
	}
	public String getAdvertTitle() {
		return advertTitle;
	}
	public void setAdvertTitle(String advertTitle) {
		this.advertTitle = advertTitle;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	public String getDeviceAddress() {
		return deviceAddress;
	}
	public void setDeviceAddress(String deviceAddress) {
		this.deviceAddress = deviceAddress;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public Double getAdvertFee() {
		return advertFee;
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
