package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * AdvertInfo entity. @author MyEclipse Persistence Tools
 */

public class AdvertInfo extends BasePo{

	// Fields

	private Integer advertInfoId;
	private Integer advertUserId;
	private String advertTitle;
	private String advertContent;
	private String startDate;
	private String endDate;
	private String advertStatus;
	private String createDate;
	private String advertFileType;
	private String advertFile;
	private Double advertTotalFee;
	private String payStatus;
	private Double payFee;
	private String advertFileName;

	// Constructors

	/** default constructor */
	public AdvertInfo() {
	}

	/** full constructor */
	public AdvertInfo(Integer advertUserId, String advertTitle,
			String advertContent, String startDate, String endDate,
			String advertStatus, String createDate, String advertFileType,
			String advertFile, Double advertFee) {
		this.advertUserId = advertUserId;
		this.advertTitle = advertTitle;
		this.advertContent = advertContent;
		this.startDate = startDate;
		this.endDate = endDate;
		this.advertStatus = advertStatus;
		this.createDate = createDate;
		this.advertFileType = advertFileType;
		this.advertFile = advertFile;
		this.advertTotalFee = advertFee;
	}

	// Property accessors

	public Integer getAdvertInfoId() {
		return this.advertInfoId;
	}

	public void setAdvertInfoId(Integer advertInfoId) {
		this.advertInfoId = advertInfoId;
	}

	public Integer getAdvertUserId() {
		return this.advertUserId;
	}

	public void setAdvertUserId(Integer advertUserId) {
		this.advertUserId = advertUserId;
	}

	public String getAdvertTitle() {
		return this.advertTitle;
	}

	public void setAdvertTitle(String advertTitle) {
		this.advertTitle = advertTitle;
	}

	public String getAdvertContent() {
		return this.advertContent;
	}

	public void setAdvertContent(String advertContent) {
		this.advertContent = advertContent;
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

	public String getAdvertStatus() {
		return this.advertStatus;
	}

	public void setAdvertStatus(String advertStatus) {
		this.advertStatus = advertStatus;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getAdvertFileType() {
		return this.advertFileType;
	}

	public void setAdvertFileType(String advertFileType) {
		this.advertFileType = advertFileType;
	}

	public String getAdvertFile() {
		return this.advertFile;
	}

	public void setAdvertFile(String advertFile) {
		this.advertFile = advertFile;
	}

	public Double getAdvertTotalFee() {
		return this.advertTotalFee;
	}

	public void setAdvertTotalFee(Double advertTotalFee) {
		this.advertTotalFee = advertTotalFee;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public Double getPayFee() {
		return payFee;
	}

	public void setPayFee(Double payFee) {
		this.payFee = payFee;
	}

	public String getAdvertFileName() {
		return advertFileName;
	}

	public void setAdvertFileName(String advertFileName) {
		this.advertFileName = advertFileName;
	}
	
	
}