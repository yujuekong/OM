package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * DeviceGrid entity. @author MyEclipse Persistence Tools
 */

public class DeviceGrid extends BasePo{

	// Fields

	private Integer gridId;
	private Integer deviceId;
	private String gridNo;
	private String gridBar;

	// Constructors

	/** default constructor */
	public DeviceGrid() {
	}

	/** full constructor */
	public DeviceGrid(Integer deviceId, String gridNo, String gridBar) {
		this.deviceId = deviceId;
		this.gridNo = gridNo;
		this.gridBar = gridBar;
	}

	// Property accessors

	public Integer getGridId() {
		return this.gridId;
	}

	public void setGridId(Integer gridId) {
		this.gridId = gridId;
	}

	public Integer getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getGridNo() {
		return this.gridNo;
	}

	public void setGridNo(String gridNo) {
		this.gridNo = gridNo;
	}

	public String getGridBar() {
		return this.gridBar;
	}

	public void setGridBar(String gridBar) {
		this.gridBar = gridBar;
	}

}