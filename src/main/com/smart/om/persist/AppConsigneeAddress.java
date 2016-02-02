package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

public class AppConsigneeAddress extends BasePo {
	private Integer consigneeAddressId;
	private Integer consumeRecordId;
	private String tel;
	private String userRealName;
	private String userAddress;
	private String city;
	private String region;
	private String town;
	
	public Integer getConsigneeAddressId() {
		return consigneeAddressId;
	}
	public void setConsigneeAddressId(Integer consigneeAddressId) {
		this.consigneeAddressId = consigneeAddressId;
	}
	public Integer getConsumeRecordId() {
		return consumeRecordId;
	}
	public void setConsumeRecordId(Integer consumeRecordId) {
		this.consumeRecordId = consumeRecordId;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
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
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
}