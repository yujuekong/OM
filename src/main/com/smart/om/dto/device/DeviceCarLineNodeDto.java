package com.smart.om.dto.device;

import java.io.Serializable;

public class DeviceCarLineNodeDto implements Serializable{
	private static final long serialVersionUID = -2878094627666035249L;
	
	private Integer deviceId;
	private String deviceName;
	private Integer lineNodeId;
	private String nodeNo;
	private Integer nodePositionId;
	private String nodePositionName;
	private Integer carLineId;
	
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public Integer getLineNodeId() {
		return lineNodeId;
	}
	public void setLineNodeId(Integer lineNodeId) {
		this.lineNodeId = lineNodeId;
	}
	public String getNodeNo() {
		return nodeNo;
	}
	public void setNodeNo(String nodeNo) {
		this.nodeNo = nodeNo;
	}
	public Integer getNodePositionId() {
		return nodePositionId;
	}
	public void setNodePositionId(Integer nodePositionId) {
		this.nodePositionId = nodePositionId;
	}
	public String getNodePositionName() {
		return nodePositionName;
	}
	public void setNodePositionName(String nodePositionName) {
		this.nodePositionName = nodePositionName;
	}
	public Integer getCarLineId() {
		return carLineId;
	}
	public void setCarLineId(Integer carLineId) {
		this.carLineId = carLineId;
	}
	
}
