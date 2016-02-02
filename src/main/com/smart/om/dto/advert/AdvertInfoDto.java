package com.smart.om.dto.advert;

import java.io.Serializable;

public class AdvertInfoDto implements Serializable{
	private static final long serialVersionUID = -2878094627666035249L;
	private Integer advertInfoId;
	private Integer advertUserId;
	private String advertTitle;
	private String advertContent;
	private String startDate;
	private String endDate;
	private String advertStatus;
	private String createDate;
	private String auName;
	private String auLinktel;
	private String advertFileType;
	private String advertFile;
	private Double advertFee;
	private String payStatus;
	private Double payFee;
	private Integer adId;
	private Double advertTotalFee;
	
	public Integer getAdvertInfoId() {
		return advertInfoId;
	}
	public void setAdvertInfoId(Integer advertInfoId) {
		this.advertInfoId = advertInfoId;
	}
	public Integer getAdvertUserId() {
		return advertUserId;
	}
	public void setAdvertUserId(Integer advertUserId) {
		this.advertUserId = advertUserId;
	}
	public String getAdvertTitle() {
		return advertTitle;
	}
	public void setAdvertTitle(String advertTitle) {
		this.advertTitle = advertTitle;
	}
	public String getAdvertContent() {
		return advertContent;
	}
	public void setAdvertContent(String advertContent) {
		this.advertContent = advertContent;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getAdvertStatus() {
		return advertStatus;
	}
	public void setAdvertStatus(String advertStatus) {
		this.advertStatus = advertStatus;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getAuName() {
		return auName;
	}
	public void setAuName(String auName) {
		this.auName = auName;
	}
	public String getAuLinktel() {
		return auLinktel;
	}
	public void setAuLinktel(String auLinktel) {
		this.auLinktel = auLinktel;
	}
	public String getAdvertFileType() {
		return advertFileType;
	}
	public void setAdvertFileType(String advertFileType) {
		this.advertFileType = advertFileType;
	}
	public String getAdvertFile() {
		return advertFile;
	}
	public void setAdvertFile(String advertFile) {
		this.advertFile = advertFile;
	}
	public Double getAdvertFee() {
		return advertFee;
	}
	public void setAdvertFee(Double advertFee) {
		this.advertFee = advertFee;
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
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	public Double getAdvertTotalFee() {
		return advertTotalFee;
	}
	public void setAdvertTotalFee(Double advertTotalFee) {
		this.advertTotalFee = advertTotalFee;
	}
	
	
	
	
	
	
}
