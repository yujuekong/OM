package com.smart.om.dto.device;

import java.io.Serializable;

public class DeviceGoodsDto implements Serializable{
	private static final long serialVersionUID = -2878094627666035249L;
	
	private Integer deviceGoodsId;
	private Integer deviceId;
	private String deviceNo;
	private String deviceName;
	private String deviceAddress;
	public Integer getDeviceGoodsId() {
		return deviceGoodsId;
	}
	public void setDeviceGoodsId(Integer deviceGoodsId) {
		this.deviceGoodsId = deviceGoodsId;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
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
	public String getDeviceAddress() {
		return deviceAddress;
	}
	public void setDeviceAddress(String deviceAddress) {
		this.deviceAddress = deviceAddress;
	}

}
