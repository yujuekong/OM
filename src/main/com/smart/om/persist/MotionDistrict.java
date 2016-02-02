package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * 商圈
 */

public class MotionDistrict extends BasePo{

	// Fields

	private Integer districtId;
	private String districtNo;
	private String districtName;
	private String districtLng;
	private String districtLat;
	private String districtAddress;
	private String districtManager;
	private String linkMan;
	private String linkTel;
	private String linkMail;
	private Integer dictRegionId;
	private Integer dictProviceId;
	private Integer dictOrgId;
	private String isDel;
	
	private SysDict sysDict;
	
	// Constructors

	public SysDict getSysDict() {
		return sysDict;
	}

	public void setSysDict(SysDict sysDict) {
		this.sysDict = sysDict;
	}

	/** default constructor */
	public MotionDistrict() {
	}

	/** full constructor */
	public MotionDistrict(String districtNo, String districtName,
			String districtLng, String districtLat, String districtAddress,
			String districtManager, String linkMan, String linkTel,
			String linkMail) {
		this.districtNo = districtNo;
		this.districtName = districtName;
		this.districtLng = districtLng;
		this.districtLat = districtLat;
		this.districtAddress = districtAddress;
		this.districtManager = districtManager;
		this.linkMan = linkMan;
		this.linkTel = linkTel;
		this.linkMail = linkMail;
	}

	// Property accessors

	public Integer getDistrictId() {
		return this.districtId;
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

	public Integer getDictOrgId() {
		return dictOrgId;
	}

	public void setDictOrgId(Integer dictOrgId) {
		this.dictOrgId = dictOrgId;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public String getDistrictNo() {
		return this.districtNo;
	}

	public void setDistrictNo(String districtNo) {
		this.districtNo = districtNo;
	}

	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictLng() {
		return this.districtLng;
	}

	public void setDistrictLng(String districtLng) {
		this.districtLng = districtLng;
	}

	public String getDistrictLat() {
		return this.districtLat;
	}

	public void setDistrictLat(String districtLat) {
		this.districtLat = districtLat;
	}

	public String getDistrictAddress() {
		return this.districtAddress;
	}

	public void setDistrictAddress(String districtAddress) {
		this.districtAddress = districtAddress;
	}

	public String getDistrictManager() {
		return this.districtManager;
	}

	public void setDistrictManager(String districtManager) {
		this.districtManager = districtManager;
	}

	public String getLinkMan() {
		return this.linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getLinkTel() {
		return this.linkTel;
	}

	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}

	public String getLinkMail() {
		return this.linkMail;
	}

	public void setLinkMail(String linkMail) {
		this.linkMail = linkMail;
	}

}