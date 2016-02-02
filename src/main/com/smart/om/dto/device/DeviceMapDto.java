package com.smart.om.dto.device;

import java.io.Serializable;

public class DeviceMapDto implements Serializable{
	private static final long serialVersionUID = -2878094627666035249L;
	
	private Integer deviceId;
	private Integer deviceTypeId;
	private String deviceNo;
	private String deviceName;
	private String deviceTypeName;
	private String deviceAddress;
	private String deviceStatus;
	private String deviceLng;
	private String deviceLat;
	private String maintenanceUser;
	private String maintenanceDate;
	private String expEndTime;
	private String finishTime;
	private String maintenanceReason;
	private String remark;
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
	public String getMaintenanceUser() {
		return maintenanceUser;
	}
	public void setMaintenanceUser(String maintenanceUser) {
		this.maintenanceUser = maintenanceUser;
	}
	public String getMaintenanceDate() {
		return maintenanceDate;
	}
	public void setMaintenanceDate(String maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}
	public String getExpEndTime() {
		return expEndTime;
	}
	public void setExpEndTime(String expEndTime) {
		this.expEndTime = expEndTime;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getMaintenanceReason() {
		return maintenanceReason;
	}
	public void setMaintenanceReason(String maintenanceReason) {
		this.maintenanceReason = maintenanceReason;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDeviceLng() {
		return deviceLng;
	}
	public void setDeviceLng(String deviceLng) {
		this.deviceLng = deviceLng;
	}
	public String getDeviceLat() {
		return deviceLat;
	}
	public void setDeviceLat(String deviceLat) {
		this.deviceLat = deviceLat;
	}
	public String getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

}
