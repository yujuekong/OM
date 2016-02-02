package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * 配送特勤小组
 */

public class DispatchingTeam extends BasePo {

	private static final long serialVersionUID = 1L;
	
	private Integer teamId;
	private Integer mainUser;
	private Integer otherUser;
	private Integer carId;
	private Integer carLineId;
	private String teamNo;
	private String teamName;
	private Integer dictRegionId;
	private Integer dictProviceId;
	private Integer dictOrgId;
	
	private CarInfo carinfo;
	private CarLine carLine;
	private SysUser mainSysUser;
	private SysUser otherSysUser;

	// Constructors

	/** default constructor */
	public DispatchingTeam() {
	}

	/** full constructor */
	public DispatchingTeam(Integer mainUser, Integer otherUser, Integer carId,
			Integer carLineId) {
		this.mainUser = mainUser;
		this.otherUser = otherUser;
		this.carId = carId;
		this.carLineId = carLineId;
	}

	// Property accessors

	public Integer getTeamId() {
		return this.teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getMainUser() {
		return this.mainUser;
	}

	public void setMainUser(Integer mainUser) {
		this.mainUser = mainUser;
	}

	public Integer getOtherUser() {
		return otherUser;
	}

	public void setOtherUser(Integer otherUser) {
		this.otherUser = otherUser;
	}

	public Integer getCarId() {
		return this.carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getCarLineId() {
		return this.carLineId;
	}

	public void setCarLineId(Integer carLineId) {
		this.carLineId = carLineId;
	}

	public String getTeamNo() {
		return teamNo;
	}

	public void setTeamNo(String teamNo) {
		this.teamNo = teamNo;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public CarInfo getCarinfo() {
		return carinfo;
	}

	public void setCarinfo(CarInfo carinfo) {
		this.carinfo = carinfo;
	}

	public CarLine getCarLine() {
		return carLine;
	}

	public void setCarLine(CarLine carLine) {
		this.carLine = carLine;
	}

	public SysUser getMainSysUser() {
		return mainSysUser;
	}

	public void setMainSysUser(SysUser mainSysUser) {
		this.mainSysUser = mainSysUser;
	}

	public SysUser getOtherSysUser() {
		return otherSysUser;
	}

	public void setOtherSysUser(SysUser otherSysUser) {
		this.otherSysUser = otherSysUser;
	}

	public Integer getDictOrgId() {
		return dictOrgId;
	}

	public void setDictOrgId(Integer dictOrgId) {
		this.dictOrgId = dictOrgId;
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