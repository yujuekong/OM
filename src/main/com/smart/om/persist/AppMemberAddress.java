package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * CarInfo entity. @author MyEclipse Persistence Tools
 */

public class AppMemberAddress  extends BasePo{

	private Integer memberAddressId;
	private Integer memberId;
	private String address;
	private String isMr;
	private String userName;
	private String userTel;
	private String city;
	private String region;
	private String province;
	
	public Integer getMemberAddressId() {
		return memberAddressId;
	}
	public void setMemberAddressId(Integer memberAddressId) {
		this.memberAddressId = memberAddressId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIsMr() {
		return isMr;
	}
	public void setIsMr(String isMr) {
		this.isMr = isMr;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
}