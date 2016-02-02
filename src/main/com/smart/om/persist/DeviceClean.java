package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * DeviceClean entity. @author MyEclipse Persistence Tools
 */

public class DeviceClean extends BasePo {

	// Fields

	private Integer cleanId;
	private Integer deviceId;
	private Integer cleanUserId;
	private String cleanDate;
	private String cleanEndTiem;
	private String finishTime;
	private String cleanStatus;
	private String cleanReason;
	private String remark;
	private String cleanNo;
	private String cleanPic1;
	private String cleanPic2;
	private String cleanPic3;
	private String cleanPic4;
	private String cleanPic5;
	private String cleanType;
	private DeviceInfo deviceInfo;
	private SysUser sysUser;

	// Constructors

	/** default constructor */
	public DeviceClean() {
	}

	/** full constructor */
	public DeviceClean(Integer deviceId, Integer cleanUserId, String cleanDate,
			String cleanEndTiem, String finishTime, String cleanStatus,
			String cleanReason, String remark) {
		this.deviceId = deviceId;
		this.cleanUserId = cleanUserId;
		this.cleanDate = cleanDate;
		this.cleanEndTiem = cleanEndTiem;
		this.finishTime = finishTime;
		this.cleanStatus = cleanStatus;
		this.cleanReason = cleanReason;
		this.remark = remark;
	}

	// Property accessors

	public Integer getCleanId() {
		return this.cleanId;
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

	public void setCleanId(Integer cleanId) {
		this.cleanId = cleanId;
	}

	public Integer getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getCleanUserId() {
		return this.cleanUserId;
	}

	public void setCleanUserId(Integer cleanUserId) {
		this.cleanUserId = cleanUserId;
	}

	public String getCleanDate() {
		return this.cleanDate;
	}

	public void setCleanDate(String cleanDate) {
		this.cleanDate = cleanDate;
	}

	public String getCleanEndTiem() {
		return this.cleanEndTiem;
	}

	public void setCleanEndTiem(String cleanEndTiem) {
		this.cleanEndTiem = cleanEndTiem;
	}

	public String getFinishTime() {
		return this.finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getCleanStatus() {
		return this.cleanStatus;
	}

	public void setCleanStatus(String cleanStatus) {
		this.cleanStatus = cleanStatus;
	}

	public String getCleanReason() {
		return this.cleanReason;
	}

	public void setCleanReason(String cleanReason) {
		this.cleanReason = cleanReason;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCleanNo() {
		return cleanNo;
	}

	public void setCleanNo(String cleanNo) {
		this.cleanNo = cleanNo;
	}

	public String getCleanPic1() {
		return cleanPic1;
	}

	public void setCleanPic1(String cleanPic1) {
		this.cleanPic1 = cleanPic1;
	}

	public String getCleanPic2() {
		return cleanPic2;
	}

	public void setCleanPic2(String cleanPic2) {
		this.cleanPic2 = cleanPic2;
	}

	public String getCleanPic3() {
		return cleanPic3;
	}

	public void setCleanPic3(String cleanPic3) {
		this.cleanPic3 = cleanPic3;
	}

	public String getCleanPic4() {
		return cleanPic4;
	}

	public void setCleanPic4(String cleanPic4) {
		this.cleanPic4 = cleanPic4;
	}

	public String getCleanPic5() {
		return cleanPic5;
	}

	public void setCleanPic5(String cleanPic5) {
		this.cleanPic5 = cleanPic5;
	}

	public String getCleanType() {
		return cleanType;
	}

	public void setCleanType(String cleanType) {
		this.cleanType = cleanType;
	}
}