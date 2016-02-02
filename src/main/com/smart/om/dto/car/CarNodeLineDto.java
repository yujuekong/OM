package com.smart.om.dto.car;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/9/17.
 */
public class CarNodeLineDto implements Serializable{
	
	private Integer lineNodeId;
	private Integer carLineId;
	private String nodeNo;

	private Integer districtId;
	private String districtNo;
	private String districtName;
	private String districtAddress;
	private String dictName;
	public Integer getLineNodeId() {
		return lineNodeId;
	}
	public void setLineNodeId(Integer lineNodeId) {
		this.lineNodeId = lineNodeId;
	}
	public Integer getCarLineId() {
		return carLineId;
	}
	public void setCarLineId(Integer carLineId) {
		this.carLineId = carLineId;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public String getDistrictNo() {
		return districtNo;
	}
	public void setDistrictNo(String districtNo) {
		this.districtNo = districtNo;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getDistrictAddress() {
		return districtAddress;
	}
	public void setDistrictAddress(String districtAddress) {
		this.districtAddress = districtAddress;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public String getNodeNo() {
		return nodeNo;
	}
	public void setNodeNo(String nodeNo) {
		this.nodeNo = nodeNo;
	}
	
}
