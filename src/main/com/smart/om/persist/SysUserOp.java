package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * SysUserOp entity. @author MyEclipse Persistence Tools
 */

public class SysUserOp extends BasePo{

	// Fields

	private Integer userOpId;
	private Integer userId;
	private String userName;
	private String bizType;
	private Integer bizId;
	private String bizNo;
	private String opDate;
	private String opTime;
	private String opStatus;
	private String opDesc;
	private String opPic1;
	private String opPic2;
	private String opPic3;
	private String opPic4;
	private String opPic5;
	private String opReason;

	// Constructors

	/** default constructor */
	public SysUserOp() {
	}

	/** full constructor */
	public SysUserOp(Integer userId, String userName, String bizType,
			Integer bizId, String bizNo, String opDate, String opTime,
			String opStatus, String opDesc, String opPic1, String opPic2,
			String opPic3, String opPic4, String opPic5, String opReason) {
		this.userId = userId;
		this.userName = userName;
		this.bizType = bizType;
		this.bizId = bizId;
		this.bizNo = bizNo;
		this.opDate = opDate;
		this.opTime = opTime;
		this.opStatus = opStatus;
		this.opDesc = opDesc;
		this.opPic1 = opPic1;
		this.opPic2 = opPic2;
		this.opPic3 = opPic3;
		this.opPic4 = opPic4;
		this.opPic5 = opPic5;
		this.opReason = opReason;
	}

	// Property accessors

	public Integer getUserOpId() {
		return this.userOpId;
	}

	public void setUserOpId(Integer userOpId) {
		this.userOpId = userOpId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBizType() {
		return this.bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public Integer getBizId() {
		return this.bizId;
	}

	public void setBizId(Integer bizId) {
		this.bizId = bizId;
	}

	public String getBizNo() {
		return this.bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public String getOpDate() {
		return this.opDate;
	}

	public void setOpDate(String opDate) {
		this.opDate = opDate;
	}

	public String getOpTime() {
		return this.opTime;
	}

	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}

	public String getOpStatus() {
		return this.opStatus;
	}

	public void setOpStatus(String opStatus) {
		this.opStatus = opStatus;
	}

	public String getOpDesc() {
		return this.opDesc;
	}

	public void setOpDesc(String opDesc) {
		this.opDesc = opDesc;
	}

	public String getOpPic1() {
		return this.opPic1;
	}

	public void setOpPic1(String opPic1) {
		this.opPic1 = opPic1;
	}

	public String getOpPic2() {
		return this.opPic2;
	}

	public void setOpPic2(String opPic2) {
		this.opPic2 = opPic2;
	}

	public String getOpPic3() {
		return this.opPic3;
	}

	public void setOpPic3(String opPic3) {
		this.opPic3 = opPic3;
	}

	public String getOpPic4() {
		return opPic4;
	}

	public void setOpPic4(String opPic4) {
		this.opPic4 = opPic4;
	}

	public String getOpPic5() {
		return opPic5;
	}

	public void setOpPic5(String opPic5) {
		this.opPic5 = opPic5;
	}

	public String getOpReason() {
		return opReason;
	}

	public void setOpReason(String opReason) {
		this.opReason = opReason;
	}
}