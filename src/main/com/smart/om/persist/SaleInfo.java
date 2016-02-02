package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * SaleInfo entity. @author MyEclipse Persistence Tools
 */

public class SaleInfo extends BasePo {

	// Fields

	private Integer saleId;
	private String saleNo;
	private String saleDate;
	private Double saleAmount;
	private Double saleMoney;
	private String deviceId;
	private String deviceName;
	private String payType;
	private String indentNum;
	private String bsinessNum;
	private String bsinessOrderNo;
	private String payResultUrl;
	private String goodsDetails;
	private String goodsDescribe;
	private String additionalData;
	private String payDate;
	private String goodsId;
	private String payStatus;
	private String wrongWord;
	private String wrongMessage;
	// Constructors

	/** default constructor */
	public SaleInfo() {
	}

	/** minimal constructor */
	public SaleInfo(String saleNo) {
		this.saleNo = saleNo;
	}

	/** full constructor */
	public SaleInfo(String saleNo, String saleDate, Double saleAmount,
			Double saleMoney, String deviceId, String deviceName,
			String payType, String indentNum, String bsinessNum) {
		this.saleNo = saleNo;
		this.saleDate = saleDate;
		this.saleAmount = saleAmount;
		this.saleMoney = saleMoney;
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.payType = payType;
		this.indentNum = indentNum;
		this.bsinessNum = bsinessNum;
	}

	// Property accessors

	public Integer getSaleId() {
		return this.saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	public String getSaleNo() {
		return this.saleNo;
	}

	public void setSaleNo(String saleNo) {
		this.saleNo = saleNo;
	}

	public String getSaleDate() {
		return this.saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public Double getSaleMoney() {
		return this.saleMoney;
	}

	public void setSaleMoney(Double saleMoney) {
		this.saleMoney = saleMoney;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getPayType() {
		return this.payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getIndentNum() {
		return this.indentNum;
	}

	public void setIndentNum(String indentNum) {
		this.indentNum = indentNum;
	}

	public String getBsinessNum() {
		return this.bsinessNum;
	}

	public void setBsinessNum(String bsinessNum) {
		this.bsinessNum = bsinessNum;
	}

	public String getBsinessOrderNo() {
		return bsinessOrderNo;
	}

	public void setBsinessOrderNo(String bsinessOrderNo) {
		this.bsinessOrderNo = bsinessOrderNo;
	}

	public String getPayResultUrl() {
		return payResultUrl;
	}

	public void setPayResultUrl(String payResultUrl) {
		this.payResultUrl = payResultUrl;
	}

	public String getGoodsDetails() {
		return goodsDetails;
	}

	public void setGoodsDetails(String goodsDetails) {
		this.goodsDetails = goodsDetails;
	}

	public String getGoodsDescribe() {
		return goodsDescribe;
	}

	public void setGoodsDescribe(String goodsDescribe) {
		this.goodsDescribe = goodsDescribe;
	}

	public String getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getWrongWord() {
		return wrongWord;
	}

	public void setWrongWord(String wrongWord) {
		this.wrongWord = wrongWord;
	}

	public String getWrongMessage() {
		return wrongMessage;
	}

	public void setWrongMessage(String wrongMessage) {
		this.wrongMessage = wrongMessage;
	}

	public Double getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(Double saleAmount) {
		this.saleAmount = saleAmount;
	}
}