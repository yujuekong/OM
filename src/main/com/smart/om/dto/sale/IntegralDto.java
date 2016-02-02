package com.smart.om.dto.sale;

import java.io.Serializable;

public class IntegralDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer integrlBillId;//积分交易ID
	private Integer memberId;//积分交易会员
	private String billType;//积分交易类型（0消费，1兑换，2 期初录入）
	private Integer activityId;//积分交易的活动
	private Integer integrlNumber;//交易数量
	private String exchangeTime;//交易时间
	private String remark;//交易备注
	private String memberName;
	private String gameName;//游戏名称
	public Integer getIntegrlBillId() {
		return integrlBillId;
	}
	public void setIntegrlBillId(Integer integrlBillId) {
		this.integrlBillId = integrlBillId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getIntegrlNumber() {
		return integrlNumber;
	}
	public void setIntegrlNumber(Integer integrlNumber) {
		this.integrlNumber = integrlNumber;
	}
	public String getExchangeTime() {
		return exchangeTime;
	}
	public void setExchangeTime(String exchangeTime) {
		this.exchangeTime = exchangeTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	
}
