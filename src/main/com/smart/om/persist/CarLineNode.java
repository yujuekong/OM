package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * CarLineNode entity. @author MyEclipse Persistence Tools
 */

public class CarLineNode extends BasePo {

	// Fields

	private Integer lineNodeId;
	private Integer carLineId;
	private Integer nodePositionId;
	private String nodePositionName;
	private String nodeLng;
	private String nodeLat;
	private String remark;
	private String nodeNo;
	private Integer nodeSort;
	private String isDel;
	
	private MotionDistrict motionDistrict;
	private CarLine carLine;
	// Constructors

	/** default constructor */
	public CarLineNode() {
	}

	/** full constructor */
	public CarLineNode(Integer carLineId, Integer nodePositionId,
			String nodePositionName, String nodeLng, String nodeLat,
			String remark, String nodeNo, Integer nodeSort) {
		this.carLineId = carLineId;
		this.nodePositionId = nodePositionId;
		this.nodePositionName = nodePositionName;
		this.nodeLng = nodeLng;
		this.nodeLat = nodeLat;
		this.remark = remark;
		this.nodeNo = nodeNo;
		this.nodeSort = nodeSort;
	}

	// Property accessors

	public Integer getLineNodeId() {
		return this.lineNodeId;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public void setLineNodeId(Integer lineNodeId) {
		this.lineNodeId = lineNodeId;
	}

	public Integer getCarLineId() {
		return this.carLineId;
	}

	public void setCarLineId(Integer carLineId) {
		this.carLineId = carLineId;
	}

	public Integer getNodePositionId() {
		return this.nodePositionId;
	}

	public void setNodePositionId(Integer nodePositionId) {
		this.nodePositionId = nodePositionId;
	}

	public String getNodePositionName() {
		return this.nodePositionName;
	}

	public void setNodePositionName(String nodePositionName) {
		this.nodePositionName = nodePositionName;
	}

	public String getNodeLng() {
		return this.nodeLng;
	}

	public void setNodeLng(String nodeLng) {
		this.nodeLng = nodeLng;
	}

	public String getNodeLat() {
		return this.nodeLat;
	}

	public void setNodeLat(String nodeLat) {
		this.nodeLat = nodeLat;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getNodeNo() {
		return this.nodeNo;
	}

	public void setNodeNo(String nodeNo) {
		this.nodeNo = nodeNo;
	}

	public Integer getNodeSort() {
		return this.nodeSort;
	}

	public void setNodeSort(Integer nodeSort) {
		this.nodeSort = nodeSort;
	}

	public MotionDistrict getMotionDistrict() {
		return motionDistrict;
	}

	public void setMotionDistrict(MotionDistrict motionDistrict) {
		this.motionDistrict = motionDistrict;
	}

	public CarLine getCarLine() {
		return carLine;
	}

	public void setCarLine(CarLine carLine) {
		this.carLine = carLine;
	}

	
}