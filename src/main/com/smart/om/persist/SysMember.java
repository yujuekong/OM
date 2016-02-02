package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * SysMember entity. @author MyEclipse Persistence Tools
 */

public class SysMember extends BasePo {

	// Fields

	private Integer memberId;
	private String memberNo;
	private String memberName;
	private String memberSex;
	private Integer memberAge;
	private String memberTel;
	private String memberMail;
	private Integer memberLevel;
	private String regDate;
	private String wxNo;
	private String payNo;
	private String memberPassword;
	private String memberImage;
	private String consigneesAddress;
	private String logonType;
	private Integer memberIntegrl;
	private Double memberMoney;
	// Constructors

	/** default constructor */
	public SysMember() {
	}

	/** full constructor */
	public SysMember(String memberNo, String memberName, String memberSex,
			Integer memberAge, String memberTel, String memberMail,
			Integer memberLevel, String regDate, String wxNo, String payNo) {
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.memberSex = memberSex;
		this.memberAge = memberAge;
		this.memberTel = memberTel;
		this.memberMail = memberMail;
		this.memberLevel = memberLevel;
		this.regDate = regDate;
		this.wxNo = wxNo;
		this.payNo = payNo;
	}

	// Property accessors

	public Integer getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberNo() {
		return this.memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return this.memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberSex() {
		return this.memberSex;
	}

	public void setMemberSex(String memberSex) {
		this.memberSex = memberSex;
	}

	public Integer getMemberAge() {
		return this.memberAge;
	}

	public void setMemberAge(Integer memberAge) {
		this.memberAge = memberAge;
	}

	public String getMemberTel() {
		return this.memberTel;
	}

	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}

	public String getMemberMail() {
		return this.memberMail;
	}

	public void setMemberMail(String memberMail) {
		this.memberMail = memberMail;
	}

	public Integer getMemberLevel() {
		return this.memberLevel;
	}

	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}

	public String getRegDate() {
		return this.regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getWxNo() {
		return this.wxNo;
	}

	public void setWxNo(String wxNo) {
		this.wxNo = wxNo;
	}

	public String getPayNo() {
		return this.payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}

	public String getMemberImage() {
		return memberImage;
	}

	public void setMemberImage(String memberImage) {
		this.memberImage = memberImage;
	}

	public String getConsigneesAddress() {
		return consigneesAddress;
	}

	public void setConsigneesAddress(String consigneesAddress) {
		this.consigneesAddress = consigneesAddress;
	}

	public String getLogonType() {
		return logonType;
	}

	public void setLogonType(String logonType) {
		this.logonType = logonType;
	}

	public Integer getMemberIntegrl() {
		return memberIntegrl;
	}

	public void setMemberIntegrl(Integer memberIntegrl) {
		this.memberIntegrl = memberIntegrl;
	}

	public Double getMemberMoney() {
		return memberMoney;
	}

	public void setMemberMoney(Double memberMoney) {
		this.memberMoney = memberMoney;
	}

}