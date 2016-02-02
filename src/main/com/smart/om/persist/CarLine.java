package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * CarLine entity. @author MyEclipse Persistence Tools
 */

public class CarLine extends BasePo {

	// Fields

	private Integer carLineId;
	private Double lineLength;
	private Integer lineType;
	private String startDate;
	private String endDate;
	private Double ctlHour;
	private Integer agentUser;
	private String agentDate;
	private String lineName;
	private String lineNo;
	private Integer dictRegionId;
	private Integer dictProviceId;
	private Integer dictOrgId;
	private String isDel;
	
	private SysDict Region;
	private SysDict Provice;
	private SysDict org;
	private SysUser user;
	// Constructors

	/** default constructor */
	public CarLine() {
	}

	/** full constructor */
	public CarLine(Double lineLength, Integer lineType, String startDate,
			String endDate, Double ctlHour, Integer agentUser,
			String agentDate, String lineName, String lineNo) {
		this.lineLength = lineLength;
		this.lineType = lineType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.ctlHour = ctlHour;
		this.agentUser = agentUser;
		this.agentDate = agentDate;
		this.lineName = lineName;
		this.lineNo = lineNo;
	}

	// Property accessors

	public Integer getCarLineId() {
		return this.carLineId;
	}

	public Integer getDictRegionId() {
		return dictRegionId;
	}

	public void setDictRegionId(Integer dictRegionId) {
		this.dictRegionId = dictRegionId;
	}

	public Integer getDictProviceId() {
		return dictProviceId;
	}

	public void setDictProviceId(Integer dictProviceId) {
		this.dictProviceId = dictProviceId;
	}

	public Integer getDictOrgId() {
		return dictOrgId;
	}

	public void setDictOrgId(Integer dictOrgId) {
		this.dictOrgId = dictOrgId;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public void setCarLineId(Integer carLineId) {
		this.carLineId = carLineId;
	}

	public Double getLineLength() {
		return this.lineLength;
	}

	public void setLineLength(Double lineLength) {
		this.lineLength = lineLength;
	}

	public Integer getLineType() {
		return this.lineType;
	}

	public void setLineType(Integer lineType) {
		this.lineType = lineType;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Double getCtlHour() {
		return this.ctlHour;
	}

	public void setCtlHour(Double ctlHour) {
		this.ctlHour = ctlHour;
	}

	public Integer getAgentUser() {
		return this.agentUser;
	}

	public void setAgentUser(Integer agentUser) {
		this.agentUser = agentUser;
	}

	public String getAgentDate() {
		return this.agentDate;
	}

	public void setAgentDate(String agentDate) {
		this.agentDate = agentDate;
	}

	public String getLineName() {
		return this.lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getLineNo() {
		return this.lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public SysDict getRegion() {
		return Region;
	}

	public void setRegion(SysDict region) {
		Region = region;
	}

	public SysDict getProvice() {
		return Provice;
	}

	public void setProvice(SysDict provice) {
		Provice = provice;
	}

	public SysDict getOrg() {
		return org;
	}

	public void setOrg(SysDict org) {
		this.org = org;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

}