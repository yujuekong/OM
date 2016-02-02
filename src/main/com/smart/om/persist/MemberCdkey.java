package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * SellerInfo entity. @author MyEclipse Persistence Tools
 */

public class MemberCdkey extends BasePo {

    private Integer cdkeyId;
    private Integer memberId;
    private String cdkey;
    private String cdkeyStatus;
    private String createDate;
    private String limitedDate;
    
	public Integer getCdkeyId() {
		return cdkeyId;
	}
	public void setCdkeyId(Integer cdkeyId) {
		this.cdkeyId = cdkeyId;
	}

	public String getCdkey() {
		return cdkey;
	}
	public void setCdkey(String cdkey) {
		this.cdkey = cdkey;
	}
	public String getCdkeyStatus() {
		return cdkeyStatus;
	}
	public void setCdkeyStatus(String cdkeyStatus) {
		this.cdkeyStatus = cdkeyStatus;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getLimitedDate() {
		return limitedDate;
	}
	public void setLimitedDate(String limitedDate) {
		this.limitedDate = limitedDate;
	}
}