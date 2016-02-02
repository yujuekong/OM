package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * SysDept entity. @author MyEclipse Persistence Tools
 */

public class SysDept extends BasePo{

	// Fields

	private Integer deptId;
	private Integer orgId;
	private Integer deptPid;
	private String deptName;
	private Integer deptChief;
	private String deptTel;
	private Integer dictRegionId;
	private Integer dictProviceId;
	// Constructors
	
	/** default constructor */
	public SysDept() {
	}

	/** full constructor */
	public SysDept(Integer orgId, Integer deptPid, String deptName,
			Integer deptChief, String deptTel) {
		this.orgId = orgId;
		this.deptPid = deptPid;
		this.deptName = deptName;
		this.deptChief = deptChief;
		this.deptTel = deptTel;
	}

	// Property accessors

	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getDeptPid() {
		return this.deptPid;
	}

	public void setDeptPid(Integer deptPid) {
		this.deptPid = deptPid;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getDeptChief() {
		return this.deptChief;
	}

	public void setDeptChief(Integer deptChief) {
		this.deptChief = deptChief;
	}

	public String getDeptTel() {
		return this.deptTel;
	}

	public void setDeptTel(String deptTel) {
		this.deptTel = deptTel;
	}

	public Integer getDictRegionId() {
		return dictRegionId;
	}

	public void setDictRegionId(Integer dictRegionId) {
		this.dictRegionId = dictRegionId;
	}

	public Integer getDictProviceId() {
		return dictProviceId;
	}

	public void setDictProviceId(Integer dictProviceId) {
		this.dictProviceId = dictProviceId;
	}

}