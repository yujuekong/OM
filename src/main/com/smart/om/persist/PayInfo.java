package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * PayInfo entity. @author MyEclipse Persistence Tools
 */

public class PayInfo extends BasePo {

	// Fields

	private Integer payId;
	private String deviceNo;
	private String deviceName;
	private String payTradeNo;
	private String buyerPayUserno;
	private String buyerPayAccount;
	private String bsinessOrderNo;
	private Double indentMoney;
	private Double realMoney;
	private String tradeState;
	private String saleDate;
	private String payDate;
	private String isRefund;
	private Double refundMoney;
	private String refundDate;
	private String payType;

	// Constructors

	/** default constructor */
	public PayInfo() {
	}

	/** minimal constructor */
	public PayInfo(String payTradeNo) {
		this.payTradeNo = payTradeNo;
	}

	/** full constructor */
	public PayInfo(String deviceNo, String deviceName, String payTradeNo,
			String buyerPayUserno, String buyerPayAccount,
			String bsinessOrderNo, Double indentMoney, Double realMoney,
			String tradeState, String saleDate, String payDate,
			String isRefund, Double refundMoney, String refundDate,
			String payType) {
		this.deviceNo = deviceNo;
		this.deviceName = deviceName;
		this.payTradeNo = payTradeNo;
		this.buyerPayUserno = buyerPayUserno;
		this.buyerPayAccount = buyerPayAccount;
		this.bsinessOrderNo = bsinessOrderNo;
		this.indentMoney = indentMoney;
		this.realMoney = realMoney;
		this.tradeState = tradeState;
		this.saleDate = saleDate;
		this.payDate = payDate;
		this.isRefund = isRefund;
		this.refundMoney = refundMoney;
		this.refundDate = refundDate;
		this.payType = payType;
	}

	// Property accessors

	public Integer getPayId() {
		return this.payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	public String getDeviceNo() {
		return this.deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getPayTradeNo() {
		return this.payTradeNo;
	}

	public void setPayTradeNo(String payTradeNo) {
		this.payTradeNo = payTradeNo;
	}

	public String getBuyerPayUserno() {
		return this.buyerPayUserno;
	}

	public void setBuyerPayUserno(String buyerPayUserno) {
		this.buyerPayUserno = buyerPayUserno;
	}

	public String getBuyerPayAccount() {
		return this.buyerPayAccount;
	}

	public void setBuyerPayAccount(String buyerPayAccount) {
		this.buyerPayAccount = buyerPayAccount;
	}

	public String getBsinessOrderNo() {
		return this.bsinessOrderNo;
	}

	public void setBsinessOrderNo(String bsinessOrderNo) {
		this.bsinessOrderNo = bsinessOrderNo;
	}

	public Double getIndentMoney() {
		return this.indentMoney;
	}

	public void setIndentMoney(Double indentMoney) {
		this.indentMoney = indentMoney;
	}

	public Double getRealMoney() {
		return this.realMoney;
	}

	public void setRealMoney(Double realMoney) {
		this.realMoney = realMoney;
	}

	public String getTradeState() {
		return this.tradeState;
	}

	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}

	public String getSaleDate() {
		return this.saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public String getPayDate() {
		return this.payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getIsRefund() {
		return this.isRefund;
	}

	public void setIsRefund(String isRefund) {
		this.isRefund = isRefund;
	}

	public Double getRefundMoney() {
		return this.refundMoney;
	}

	public void setRefundMoney(Double refundMoney) {
		this.refundMoney = refundMoney;
	}

	public String getRefundDate() {
		return this.refundDate;
	}

	public void setRefundDate(String refundDate) {
		this.refundDate = refundDate;
	}

	public String getPayType() {
		return this.payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

}