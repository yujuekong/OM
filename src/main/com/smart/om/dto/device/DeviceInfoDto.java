package com.smart.om.dto.device;

import java.io.Serializable;

public class DeviceInfoDto implements Serializable{
	private static final long serialVersionUID = -2878094627666035249L;
	
	private Integer deviceId;
	private Integer deviceTypeId;
	private String deviceNo;
	private String deviceName;
	private String deviceEara;
	private String deviceWeight;
	private String createDate;
	private String deviceTypeName;
	private String deviceAddress;
	private String deviceStatus;
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public Integer getDeviceTypeId() {
		return deviceTypeId;
	}
	public void setDeviceTypeId(Integer deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
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
	public String getDeviceEara() {
		return deviceEara;
	}
	public void setDeviceEara(String deviceEara) {
		this.deviceEara = deviceEara;
	}
	public String getDeviceWeight() {
		return deviceWeight;
	}
	public void setDeviceWeight(String deviceWeight) {
		this.deviceWeight = deviceWeight;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getDeviceTypeName() {
		return deviceTypeName;
	}
	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}
	public String getDeviceAddress() {
		return deviceAddress;
	}
	public void setDeviceAddress(String deviceAddress) {
		this.deviceAddress = deviceAddress;
	}
	public String getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}	
}
