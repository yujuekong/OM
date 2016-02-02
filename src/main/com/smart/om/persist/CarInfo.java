package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * CarInfo entity. @author MyEclipse Persistence Tools
 */

public class CarInfo  extends BasePo{

	// Fields

	private Integer carId;
	private String carNo;
	private Integer carBrand;
	private Integer carType;
	private String motorNo;
	private String vehicleCode;
	private String carWeight;
	private String carSize;
	private String regDate;
	private String checkDate;
	private String remark;
	private String createDate;
	private String carLng;
	private String carLat;
	private String carStatus;
	private Integer dictRegionId;
	private Integer dictProviceId;
	private Integer dictOrgId;
	private String isDel;
	private SysDict sysDictBrand;
	private SysDict sysDictType;
	private SysDict Region;
	private SysDict Provice;
	private SysDict org;
	// Constructors

	/** default constructor */
	public CarInfo() {
	}

	/** full constructor */
	public CarInfo(String carNo, Integer carBrand, Integer carType,
			String motorNo, String vehicleCode, String carWeight,
			String carSize, String regDate, String checkDate, String remark,
			String createDate, String carLng, String carLat) {
		this.carNo = carNo;
		this.carBrand = carBrand;
		this.carType = carType;
		this.motorNo = motorNo;
		this.vehicleCode = vehicleCode;
		this.carWeight = carWeight;
		this.carSize = carSize;
		this.regDate = regDate;
		this.checkDate = checkDate;
		this.remark = remark;
		this.createDate = createDate;
		this.carLng = carLng;
		this.carLat = carLat;
	}

	// Property accessors
	public Integer getCarId() {
		return this.carId;
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

	public SysDict getSysDictBrand() {
		return sysDictBrand;
	}

	public void setSysDictBrand(SysDict sysDictBrand) {
		this.sysDictBrand = sysDictBrand;
	}

	public SysDict getSysDictType() {
		return sysDictType;
	}

	public void setSysDictType(SysDict sysDictType) {
		this.sysDictType = sysDictType;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getCarNo() {
		return this.carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public Integer getCarBrand() {
		return this.carBrand;
	}

	public void setCarBrand(Integer carBrand) {
		this.carBrand = carBrand;
	}

	public Integer getCarType() {
		return this.carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public String getMotorNo() {
		return this.motorNo;
	}

	public void setMotorNo(String motorNo) {
		this.motorNo = motorNo;
	}

	public String getVehicleCode() {
		return this.vehicleCode;
	}

	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}

	public String getCarWeight() {
		return this.carWeight;
	}

	public void setCarWeight(String carWeight) {
		this.carWeight = carWeight;
	}

	public String getCarSize() {
		return this.carSize;
	}

	public void setCarSize(String carSize) {
		this.carSize = carSize;
	}

	public String getRegDate() {
		return this.regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCarLng() {
		return this.carLng;
	}

	public void setCarLng(String carLng) {
		this.carLng = carLng;
	}

	public String getCarLat() {
		return this.carLat;
	}

	public void setCarLat(String carLat) {
		this.carLat = carLat;
	}

	public String getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}

	public SysDict getRegion() {
		return Region;
	}

	public void setRegion(SysDict region) {
		Region = region;
	}

	public SysDict getProvice() {
		return Provice;
	}

	public void setProvice(SysDict provice) {
		Provice = provice;
	}

	public SysDict getOrg() {
		return org;
	}

	public void setOrg(SysDict org) {
		this.org = org;
	}

}