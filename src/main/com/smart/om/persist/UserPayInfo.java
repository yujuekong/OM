package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;
import org.apache.poi.openxml4j.opc.PackageRelationship;

/**
 * GoodsType entity. @author MyEclipse Persistence Tools
 */

public class UserPayInfo extends BasePo{
	private Integer payId;
	private String userRealName;
	private String tel;
	private String userAddress;
	private String userPostcode;
	public Integer getPayId() {
		return payId;
	}
	public void setPayId(Integer payId) {
		this.payId = payId;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserPostcode() {
		return userPostcode;
	}
	public void setUserPostcode(String userPostcode) {
		this.userPostcode = userPostcode;
	}
}