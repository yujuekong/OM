package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * AdvertUser entity. @author MyEclipse Persistence Tools
 */

public class AdvertUser extends BasePo{

	// Fields

	private Integer advertUserId;
	private String auName;
	private String auLinkman;
	private String auLinktel;
	private String auLinkaddress;
	private String auType;
	private String auLevel;
	private String auStatus;
	private String createDate;
	private String auMail;
	private Integer orgId;
	private Integer regionId;
	private Integer proviceId;

	// Constructors

	/** default constructor */
	public AdvertUser() {
	}

	/** full constructor */
	public AdvertUser(String auName, String auLinkman, String auLinktel,
			String auLinkaddress, String auType, String auLevel,
			String auStatus, String createDate) {
		this.auName = auName;
		this.auLinkman = auLinkman;
		this.auLinktel = auLinktel;
		this.auLinkaddress = auLinkaddress;
		this.auType = auType;
		this.auLevel = auLevel;
		this.auStatus = auStatus;
		this.createDate = createDate;
	}

	// Property accessors

	public Integer getAdvertUserId() {
		return this.advertUserId;
	}

	public void setAdvertUserId(Integer advertUserId) {
		this.advertUserId = advertUserId;
	}

	public String getAuName() {
		return this.auName;
	}

	public void setAuName(String auName) {
		this.auName = auName;
	}

	public String getAuLinkman() {
		return this.auLinkman;
	}

	public void setAuLinkman(String auLinkman) {
		this.auLinkman = auLinkman;
	}

	public String getAuLinktel() {
		return this.auLinktel;
	}

	public void setAuLinktel(String auLinktel) {
		this.auLinktel = auLinktel;
	}

	public String getAuLinkaddress() {
		return this.auLinkaddress;
	}

	public void setAuLinkaddress(String auLinkaddress) {
		this.auLinkaddress = auLinkaddress;
	}

	public String getAuType() {
		return this.auType;
	}

	public void setAuType(String auType) {
		this.auType = auType;
	}

	public String getAuLevel() {
		return this.auLevel;
	}

	public void setAuLevel(String auLevel) {
		this.auLevel = auLevel;
	}

	public String getAuStatus() {
		return this.auStatus;
	}

	public void setAuStatus(String auStatus) {
		this.auStatus = auStatus;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getAuMail() {
		return auMail;
	}

	public void setAuMail(String auMail) {
		this.auMail = auMail;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getProviceId() {
		return proviceId;
	}

	public void setProviceId(Integer proviceId) {
		this.proviceId = proviceId;
	}

}