package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * 设备巡检保养
 */

public class DeviceMaintain extends BasePo{

	// Fields

	private Integer maintainId;
	private Integer deviceId;
	private String deviceNo;
	private String lastTime;
	private String thisTime;
	private Integer maintainUser;
	private String remark;
	private String maintainIsFinish;

	// Constructors

	/** default constructor */
	public DeviceMaintain() {
	}

	/** full constructor */
	public DeviceMaintain(Integer deviceId, String deviceNo, String lastTime,
			String thisTime, Integer maintainUser, String remark) {
		this.deviceId = deviceId;
		this.deviceNo = deviceNo;
		this.lastTime = lastTime;
		this.thisTime = thisTime;
		this.maintainUser = maintainUser;
		this.remark = remark;
	}

	// Property accessors

	public Integer getMaintainId() {
		return this.maintainId;
	}

	public void setMaintainId(Integer maintainId) {
		this.maintainId = maintainId;
	}

	public Integer getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceNo() {
		return this.deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public String getThisTime() {
		return this.thisTime;
	}

	public void setThisTime(String thisTime) {
		this.thisTime = thisTime;
	}

	public Integer getMaintainUser() {
		return this.maintainUser;
	}

	public void setMaintainUser(Integer maintainUser) {
		this.maintainUser = maintainUser;
	}

	public String getRemark() {
		return this.remark;
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

}