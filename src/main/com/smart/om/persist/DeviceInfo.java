package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * DeviceInfo entity. @author MyEclipse Persistence Tools
 */

public class DeviceInfo extends BasePo {

	// Fields

	private Integer deviceId;
	private Integer deviceTypeId;
	private String deviceNo;
	private String deviceName;
	private String deviceEara;
	private String powerRating;
	private String eleMothod;
	private String deviceWeight;
	private String deviceLed;
	private String deviceTouchScreen;
	private String deviceSlot;
	private String deviceTemperature;
	private String environTemperature;
	private String isNetwork;
	private String isPerson;
	private String createDate;
	private String isDel;
	private String deviceAddress;
	private String deviceLng;
	private String deviceLat;
	private Integer dictRegionId;
	private Integer dictProviceId;
	private Integer dictOrgId;
	private String deviceStatus;
	private String deviceTel3g;
	private String deviceIp;
	private String devicePort;
	private Integer districtId;
	private String bladeChangeTime;
	private Integer cupCount;
	private MotionDistrict mDistrict;
	private DeviceType deviceType;
	
	private SysDict region;
	private SysDict province;
	private SysDict org;
	
	// Constructors

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}


	public SysDict getOrg() {
		return org;
	}

	public void setOrg(SysDict org) {
		this.org = org;
	}

	/** default constructor */
	public DeviceInfo() {
	}

	/** full constructor */
	public DeviceInfo(Integer deviceTypeId, String deviceNo, String deviceName,
			String deviceEara, String powerRating, String eleMothod,
			String deviceWeight, String deviceLed, String deviceTouchScreen,
			String deviceSlot, String deviceTemperature,
			String environTemperature, String isNetwork, String isPerson,
			String createDate, String isDel, String deviceAddress,
			String deviceLng, String deviceLat, Integer dictRegionId,
			Integer dictProviceId, Integer dictOrgId) {
		this.deviceTypeId = deviceTypeId;
		this.deviceNo = deviceNo;
		this.deviceName = deviceName;
		this.deviceEara = deviceEara;
		this.powerRating = powerRating;
		this.eleMothod = eleMothod;
		this.deviceWeight = deviceWeight;
		this.deviceLed = deviceLed;
		this.deviceTouchScreen = deviceTouchScreen;
		this.deviceSlot = deviceSlot;
		this.deviceTemperature = deviceTemperature;
		this.environTemperature = environTemperature;
		this.isNetwork = isNetwork;
		this.isPerson = isPerson;
		this.createDate = createDate;
		this.isDel = isDel;
		this.deviceAddress = deviceAddress;
		this.deviceLng = deviceLng;
		this.deviceLat = deviceLat;
		this.dictRegionId = dictRegionId;
		this.dictProviceId = dictProviceId;
		this.dictOrgId = dictOrgId;
	}

	// Property accessors

	public Integer getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getDeviceTypeId() {
		return this.deviceTypeId;
	}

	public void setDeviceTypeId(Integer deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getDeviceNo() {
		return this.deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceEara() {
		return this.deviceEara;
	}

	public void setDeviceEara(String deviceEara) {
		this.deviceEara = deviceEara;
	}

	public String getPowerRating() {
		return this.powerRating;
	}

	public void setPowerRating(String powerRating) {
		this.powerRating = powerRating;
	}

	public String getEleMothod() {
		return this.eleMothod;
	}

	public void setEleMothod(String eleMothod) {
		this.eleMothod = eleMothod;
	}

	public String getDeviceWeight() {
		return this.deviceWeight;
	}

	public void setDeviceWeight(String deviceWeight) {
		this.deviceWeight = deviceWeight;
	}

	public String getDeviceLed() {
		return this.deviceLed;
	}

	public void setDeviceLed(String deviceLed) {
		this.deviceLed = deviceLed;
	}

	public String getDeviceTouchScreen() {
		return this.deviceTouchScreen;
	}

	public void setDeviceTouchScreen(String deviceTouchScreen) {
		this.deviceTouchScreen = deviceTouchScreen;
	}

	public String getDeviceSlot() {
		return this.deviceSlot;
	}

	public void setDeviceSlot(String deviceSlot) {
		this.deviceSlot = deviceSlot;
	}

	public String getDeviceTemperature() {
		return this.deviceTemperature;
	}

	public void setDeviceTemperature(String deviceTemperature) {
		this.deviceTemperature = deviceTemperature;
	}

	public String getEnvironTemperature() {
		return this.environTemperature;
	}

	public void setEnvironTemperature(String environTemperature) {
		this.environTemperature = environTemperature;
	}

	public String getIsNetwork() {
		return this.isNetwork;
	}

	public void setIsNetwork(String isNetwork) {
		this.isNetwork = isNetwork;
	}

	public String getIsPerson() {
		return this.isPerson;
	}

	public void setIsPerson(String isPerson) {
		this.isPerson = isPerson;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getIsDel() {
		return this.isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getDeviceAddress() {
		return this.deviceAddress;
	}

	public void setDeviceAddress(String deviceAddress) {
		this.deviceAddress = deviceAddress;
	}

	public String getDeviceLng() {
		return this.deviceLng;
	}

	public void setDeviceLng(String deviceLng) {
		this.deviceLng = deviceLng;
	}

	public String getDeviceLat() {
		return this.deviceLat;
	}

	public void setDeviceLat(String deviceLat) {
		this.deviceLat = deviceLat;
	}

	public Integer getDictRegionId() {
		return this.dictRegionId;
	}

	public void setDictRegionId(Integer dictRegionId) {
		this.dictRegionId = dictRegionId;
	}

	public Integer getDictProviceId() {
		return this.dictProviceId;
	}

	public void setDictProviceId(Integer dictProviceId) {
		this.dictProviceId = dictProviceId;
	}

	public Integer getDictOrgId() {
		return this.dictOrgId;
	}

	public void setDictOrgId(Integer dictOrgId) {
		this.dictOrgId = dictOrgId;
	}

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public String getDeviceTel3g() {
		return deviceTel3g;
	}

	public void setDeviceTel3g(String deviceTel3g) {
		this.deviceTel3g = deviceTel3g;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getDevicePort() {
		return devicePort;
	}

	public void setDevicePort(String devicePort) {
		this.devicePort = devicePort;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public MotionDistrict getmDistrict() {
		return mDistrict;
	}

	public void setmDistrict(MotionDistrict mDistrict) {
		this.mDistrict = mDistrict;
	}

	public String getBladeChangeTime() {
		return bladeChangeTime;
	}

	public void setBladeChangeTime(String bladeChangeTime) {
		this.bladeChangeTime = bladeChangeTime;
	}

	public Integer getCupCount() {
		return cupCount;
	}

	public void setCupCount(Integer cupCount) {
		this.cupCount = cupCount;
	}

	public SysDict getRegion() {
		return region;
	}

	public void setRegion(SysDict region) {
		this.region = region;
	}

	public SysDict getProvince() {
		return province;
	}

	public void setProvince(SysDict province) {
		this.province = province;
	}

}