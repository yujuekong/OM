package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * DeviceType entity. @author MyEclipse Persistence Tools
 */

public class DeviceType extends BasePo {

	// Fields

	private Integer deviceTypeId;
	private String deviceTypeNo;
	private String deviceTypeName;
	private String isDel;

	// Constructors

	/** default constructor */
	public DeviceType() {
	}

	/** full constructor */
	public DeviceType(String deviceTypeNo, String deviceTypeName, String isDel) {
		this.deviceTypeNo = deviceTypeNo;
		this.deviceTypeName = deviceTypeName;
		this.isDel = isDel;
	}

	// Property accessors

	public Integer getDeviceTypeId() {
		return this.deviceTypeId;
	}

	public void setDeviceTypeId(Integer deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getDeviceTypeNo() {
		return this.deviceTypeNo;
	}

	public void setDeviceTypeNo(String deviceTypeNo) {
		this.deviceTypeNo = deviceTypeNo;
	}

	public String getDeviceTypeName() {
		return this.deviceTypeName;
	}

	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}

	public String getIsDel() {
		return this.isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

}