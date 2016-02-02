package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * 设备放置商品
 */

public class DeviceGoods extends BasePo {

	// Fields

	private Integer deviceGoodsId;
	private Integer deviceId;
	private Integer goodsId;
	private String deviceNo;
	private String goodsName;
	private Integer goodsUnit;
	private String startTime;
	private String endTime;
	private Integer gridId;
	private String gridNo;
	private String gridBar;
	
	private DeviceInfo deviceInfo;
	private GoodsInfo goodsInfo;
	private SysDict sysDict;
	
	// Constructors

	/** default constructor */
	public DeviceGoods() {
	}

	/** full constructor */
	public DeviceGoods(Integer deviceId, Integer goodsId, String deviceNo,
			String goodsName, Integer goodsUnit) {
		this.deviceId = deviceId;
		this.goodsId = goodsId;
		this.deviceNo = deviceNo;
		this.goodsName = goodsName;
		this.goodsUnit = goodsUnit;
	}

	// Property accessors

	public Integer getDeviceGoodsId() {
		return this.deviceGoodsId;
	}

	public void setDeviceGoodsId(Integer deviceGoodsId) {
		this.deviceGoodsId = deviceGoodsId;
	}

	public Integer getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getDeviceNo() {
		return this.deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getGoodsUnit() {
		return this.goodsUnit;
	}

	public void setGoodsUnit(Integer goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public DeviceInfo getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public GoodsInfo getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public SysDict getSysDict() {
		return sysDict;
	}

	public void setSysDict(SysDict sysDict) {
		this.sysDict = sysDict;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getGridId() {
		return gridId;
	}

	public void setGridId(Integer gridId) {
		this.gridId = gridId;
	}

	public String getGridNo() {
		return gridNo;
	}

	public void setGridNo(String gridNo) {
		this.gridNo = gridNo;
	}

	public String getGridBar() {
		return gridBar;
	}

	public void setGridBar(String gridBar) {
		this.gridBar = gridBar;
	}

}