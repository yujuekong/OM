package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * DeviceMaintenance entity. @author MyEclipse Persistence Tools
 */

public class DeviceMaintenance extends BasePo{

	// Fields

	private Integer maintenanceId;
	private Integer deviceId;
	private Integer maintenanceUser;
	private String maintenanceDate;
	private String expEndTime;
	private String finishTime;
	private String maintenanceStatus;
	private String maintenanceReason;
	private String remark;
	private String maintenanceNo;
	private String maintenancePic1;
	private String maintenancePic2;
	private String maintenancePic3;
	private String maintenancePic4;
	private String maintenancePic5;
	private String maintenanceType;
	private DeviceInfo deviceInfo;
	private SysUser sysUser;

	// Constructors

	/** default constructor */
	public DeviceMaintenance() {
	}

	/** full constructor */
	public DeviceMaintenance(Integer deviceId, Integer maintenanceUser,
			String maintenanceDate, String expEndTime, String finishTime,
			String maintenanceStatus) {
		this.deviceId = deviceId;
		this.maintenanceUser = maintenanceUser;
		this.maintenanceDate = maintenanceDate;
		this.expEndTime = expEndTime;
		this.finishTime = finishTime;
		this.maintenanceStatus = maintenanceStatus;
	}

	// Property accessors

	public Integer getMaintenanceId() {
		return this.maintenanceId;
	}

	public DeviceInfo getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public void setMaintenanceId(Integer maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public Integer getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getMaintenanceUser() {
		return this.maintenanceUser;
	}

	public void setMaintenanceUser(Integer maintenanceUser) {
		this.maintenanceUser = maintenanceUser;
	}

	public String getMaintenanceDate() {
		return this.maintenanceDate;
	}

	public void setMaintenanceDate(String maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}

	public String getExpEndTime() {
		return this.expEndTime;
	}

	public void setExpEndTime(String expEndTime) {
		this.expEndTime = expEndTime;
	}

	public String getFinishTime() {
		return this.finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getMaintenanceStatus() {
		return this.maintenanceStatus;
	}

	public void setMaintenanceStatus(String maintenanceStatus) {
		this.maintenanceStatus = maintenanceStatus;
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

	public String getMaintenanceNo() {
		return maintenanceNo;
	}

	public void setMaintenanceNo(String maintenanceNo) {
		this.maintenanceNo = maintenanceNo;
	}

	public String getMaintenancePic1() {
		return maintenancePic1;
	}

	public void setMaintenancePic1(String maintenancePic1) {
		this.maintenancePic1 = maintenancePic1;
	}

	public String getMaintenancePic2() {
		return maintenancePic2;
	}

	public void setMaintenancePic2(String maintenancePic2) {
		this.maintenancePic2 = maintenancePic2;
	}

	public String getMaintenancePic3() {
		return maintenancePic3;
	}

	public void setMaintenancePic3(String maintenancePic3) {
		this.maintenancePic3 = maintenancePic3;
	}

	public String getMaintenancePic4() {
		return maintenancePic4;
	}

	public void setMaintenancePic4(String maintenancePic4) {
		this.maintenancePic4 = maintenancePic4;
	}

	public String getMaintenancePic5() {
		return maintenancePic5;
	}

	public void setMaintenancePic5(String maintenancePic5) {
		this.maintenancePic5 = maintenancePic5;
	}

	public String getMaintenanceType() {
		return maintenanceType;
	}

	public void setMaintenanceType(String maintenanceType) {
		this.maintenanceType = maintenanceType;
	}
}