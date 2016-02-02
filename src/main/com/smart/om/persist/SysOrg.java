package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * SysOrg entity. @author MyEclipse Persistence Tools
 */

public class SysOrg extends BasePo{

	// Fields

	private Integer orgId;
	private Integer orgPid;
	private String orgName;
	private Integer orgChief;
	private String orgTel;
	private Integer dictRegionId;
	private Integer dictProvinceId;
	private String dictRegionName;
	private String dictProvinceName;

	// Constructors

	/** default constructor */
	public SysOrg() {
	}

	/** full constructor */
	public SysOrg(Integer orgPid, String orgName, Integer orgChief,
			String orgTel) {
		this.orgPid = orgPid;
		this.orgName = orgName;
		this.orgChief = orgChief;
		this.orgTel = orgTel;
	}

	// Property accessors

	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getOrgPid() {
		return this.orgPid;
	}

	public void setOrgPid(Integer orgPid) {
		this.orgPid = orgPid;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getOrgChief() {
		return this.orgChief;
	}

	public void setOrgChief(Integer orgChief) {
		this.orgChief = orgChief;
	}

	public String getOrgTel() {
		return this.orgTel;
	}

	public void setOrgTel(String orgTel) {
		this.orgTel = orgTel;
	}

	public Integer getDictRegionId() {
		return dictRegionId;
	}

	public void setDictRegionId(Integer dictRegionId) {
		this.dictRegionId = dictRegionId;
	}

	public Integer getDictProvinceId() {
		return dictProvinceId;
	}

	public void setDictProvinceId(Integer dictProvinceId) {
		this.dictProvinceId = dictProvinceId;
	}

	public String getDictRegionName() {
		return dictRegionName;
	}

	public void setDictRegionName(String dictRegionName) {
		this.dictRegionName = dictRegionName;
	}

	public String getDictProvinceName() {
		return dictProvinceName;
	}

	public void setDictProvinceName(String dictProvinceName) {
		this.dictProvinceName = dictProvinceName;
	}

}