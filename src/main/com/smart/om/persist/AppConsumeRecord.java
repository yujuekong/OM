package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * DeviceInfo entity. @author MyEclipse Persistence Tools
 */

public class AppConsumeRecord extends BasePo {
	private Integer consumeRecordId;
	private Integer memberId;
	private String consumeType;
	private Integer goodsId;
	private String consumeTime;
	private Double consumeMoney;
	private String isRefund;
	private Integer deviceId;
	private String payNumber;
	private Integer goodsAmount;
	
	public Integer getConsumeRecordId() {
		return consumeRecordId;
	}
	public void setConsumeRecordId(Integer consumeRecordId) {
		this.consumeRecordId = consumeRecordId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getConsumeType() {
		return consumeType;
	}
	public void setConsumeType(String consumeType) {
		this.consumeType = consumeType;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getConsumeTime() {
		return consumeTime;
	}
	public void setConsumeTime(String consumeTime) {
		this.consumeTime = consumeTime;
	}
	public Double getConsumeMoney() {
		return consumeMoney;
	}
	public void setConsumeMoney(Double consumeMoney) {
		this.consumeMoney = consumeMoney;
	}
	public String getIsRefund() {
		return isRefund;
	}
	public void setIsRefund(String isRefund) {
		this.isRefund = isRefund;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public String getPayNumber() {
		return payNumber;
	}
	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}
	public Integer getGoodsAmount() {
		return goodsAmount;
	}
	public void setGoodsAmount(Integer goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

}