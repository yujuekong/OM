package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * 积分交易
 */

public class ActivityIntegrlBill  extends BasePo{

	// Fields

	private Integer integrlBillId;//积分交易ID
	private Integer memberId;//积分交易会员
	private String billType;//积分交易类型（0消费，1兑换，2 期初录入）
	private Integer activityId;//积分交易的活动
	private Integer integrlNumber;//交易数量
	private String exchangeTime;//交易时间
	private String remark;//交易备注

	// Constructors

	/** default constructor */
	public ActivityIntegrlBill() {
	}

	/** full constructor */
	public ActivityIntegrlBill(Integer memberId, String billType,
			Integer activityId, Integer integrlNumber, String exchangeTime,
			String remark) {
		this.memberId = memberId;
		this.billType = billType;
		this.activityId = activityId;
		this.integrlNumber = integrlNumber;
		this.exchangeTime = exchangeTime;
		this.remark = remark;
	}

	// Property accessors

	public Integer getIntegrlBillId() {
		return this.integrlBillId;
	}

	public void setIntegrlBillId(Integer integrlBillId) {
		this.integrlBillId = integrlBillId;
	}

	public Integer getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getBillType() {
		return this.billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public Integer getActivityId() {
		return this.activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getIntegrlNumber() {
		return this.integrlNumber;
	}

	public void setIntegrlNumber(Integer integrlNumber) {
		this.integrlNumber = integrlNumber;
	}

	public String getExchangeTime() {
		return this.exchangeTime;
	}

	public void setExchangeTime(String exchangeTime) {
		this.exchangeTime = exchangeTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}