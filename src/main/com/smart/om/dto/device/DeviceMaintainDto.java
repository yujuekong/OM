package com.smart.om.dto.device;

public class DeviceMaintainDto {
	private Integer maintainId;
	private Integer deviceId;
	private String deviceNo;
	private String lastTime;
	private String thisTime;
	private Integer maintainUser;
	private String remark;
	private String maintainIsFinish;
	private String realName;
	private String deviceName;
	
	
	public Integer getMaintainId() {
		return maintainId;
	}
	public void setMaintainId(Integer maintainId) {
		this.maintainId = maintainId;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public String getThisTime() {
		return thisTime;
	}
	public void setThisTime(String thisTime) {
		this.thisTime = thisTime;
	}
	public Integer getMaintainUser() {
		return maintainUser;
	}
	public void setMaintainUser(Integer maintainUser) {
		this.maintainUser = maintainUser;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMaintainIsFinish() {
		return maintainIsFinish;
	}
	public void setMaintainIsFinish(String maintainIsFinish) {
		this.maintainIsFinish = maintainIsFinish;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	
	
	
}
