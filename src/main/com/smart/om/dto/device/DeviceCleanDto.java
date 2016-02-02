package com.smart.om.dto.device;

import java.io.Serializable;

public class DeviceCleanDto implements Serializable{
	private static final long serialVersionUID = -2878094627666035249L;
	
	private Integer cleanId;
	private Integer deviceId;
	private Integer cleanUserId;
	private Integer deviceTypeId;
	
	private String cleanDate;
	private String deviceNo;
	private String deviceName;
	private String realName;
	private String deviceWeight;
	private String deviceTypeName;
	private String cleanEndTiem;
	private String finishTime;
	private String cleanStatus;
	private String deviceAddress;
	public Integer getCleanId() {
		return cleanId;
	}
	public void setCleanId(Integer cleanId) {
		this.cleanId = cleanId;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public Integer getCleanUserId() {
		return cleanUserId;
	}
	public void setCleanUserId(Integer cleanUserId) {
		this.cleanUserId = cleanUserId;
	}
	public Integer getDeviceTypeId() {
		return deviceTypeId;
	}
	public void setDeviceTypeId(Integer deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}
	public String getCleanDate() {
		return cleanDate;
	}
	public void setCleanDate(String cleanDate) {
		this.cleanDate = cleanDate;
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
	public String getCleanEndTiem() {
		return cleanEndTiem;
	}
	public void setCleanEndTiem(String cleanEndTiem) {
		this.cleanEndTiem = cleanEndTiem;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getCleanStatus() {
		return cleanStatus;
	}
	public void setCleanStatus(String cleanStatus) {
		this.cleanStatus = cleanStatus;
	}
	public String getDeviceAddress() {
		return deviceAddress;
	}
	public void setDeviceAddress(String deviceAddress) {
		this.deviceAddress = deviceAddress;
	}	
}
