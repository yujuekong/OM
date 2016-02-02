package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * CarInfo entity. @author MyEclipse Persistence Tools
 */

public class AppOp  extends BasePo{

	private Integer appOpId;
	private Integer memberId;
	private String appOpDate;
	private String appOpDesc;
	
	public Integer getAppOpId() {
		return appOpId;
	}
	public void setAppOpId(Integer appOpId) {
		this.appOpId = appOpId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getAppOpDate() {
		return appOpDate;
	}
	public void setAppOpDate(String appOpDate) {
		this.appOpDate = appOpDate;
	}
	public String getAppOpDesc() {
		return appOpDesc;
	}
	public void setAppOpDesc(String appOpDesc) {
		this.appOpDesc = appOpDesc;
	}
}