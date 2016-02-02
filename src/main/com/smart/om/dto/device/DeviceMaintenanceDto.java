package com.smart.om.dto.device;

import java.io.Serializable;

public class DeviceMaintenanceDto implements Serializable{
	private static final long serialVersionUID = -2878094627666035249L;
	
	private Integer maintenanceId;
	private Integer deviceId;
	private Integer maintenanceUser;
	private Integer deviceTypeId;
	
	private String maintenanceDate;
	private String deviceNo;
	private String deviceName;
	private String realName;
	private String deviceWeight;
	private String deviceTypeName;
	public Integer getMaintenanceId() {
		return maintenanceId;
	}
	public void setMaintenanceId(Integer maintenanceId) {
		this.maintenanceId = maintenanceId;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public Integer getMaintenanceUser() {
		return maintenanceUser;
	}
	public void setMaintenanceUser(Integer maintenanceUser) {
		this.maintenanceUser = maintenanceUser;
	}
	public Integer getDeviceTypeId() {
		return deviceTypeId;
	}
	public void setDeviceTypeId(Integer deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}
	public String getMaintenanceDate() {
		return maintenanceDate;
	}
	public void setMaintenanceDate(String maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getDeviceWeight() {
		return deviceWeight;
	}
	public void setDeviceWeight(String deviceWeight) {
		this.deviceWeight = deviceWeight;
	}
	public String getDeviceTypeName() {
		return deviceTypeName;
	}
	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}
}
