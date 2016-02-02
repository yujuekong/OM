package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * DispatchingPlan entity. @author MyEclipse Persistence Tools
 */

public class DispatchingPlan extends BasePo {

	// Fields

	private Integer planId;
	private Integer deliveryOrderId;
	private Integer driverUser;
	private Integer dispatchingCar;
	private Integer carLineId;
	private String dispatchingUser;
	private String planDate;
	private String startTime;
	private String endTime;
	private String planStatus;
	private String planNo;
	private String finishTime;
	
	private StorageDeliveryOrder sdo;
	private CarInfo carInfo;
	private SysUser sysUser;
	private CarLine carLine;
 
	public StorageDeliveryOrder getSdo() {
		return sdo;
	}

	public void setSdo(StorageDeliveryOrder sdo) {
		this.sdo = sdo;
	}

	public CarInfo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public CarLine getCarLine() {
		return carLine;
	}

	public void setCarLine(CarLine carLine) {
		this.carLine = carLine;
	}

	/** default constructor */
	public DispatchingPlan() {
	}

	/** full constructor */
	public DispatchingPlan(Integer deliveryOrderId, Integer driverUser,
			Integer dispatchingCar, Integer carLineId, String dispatchingUser,
			String planDate, String startTime, String endTime, String planStatus) {
		this.deliveryOrderId = deliveryOrderId;
		this.driverUser = driverUser;
		this.dispatchingCar = dispatchingCar;
		this.carLineId = carLineId;
		this.dispatchingUser = dispatchingUser;
		this.planDate = planDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.planStatus = planStatus;
	}

	// Property accessors

	public Integer getPlanId() {
		return this.planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getDeliveryOrderId() {
		return this.deliveryOrderId;
	}

	public void setDeliveryOrderId(Integer deliveryOrderId) {
		this.deliveryOrderId = deliveryOrderId;
	}

	public Integer getDriverUser() {
		return this.driverUser;
	}

	public void setDriverUser(Integer driverUser) {
		this.driverUser = driverUser;
	}

	public Integer getDispatchingCar() {
		return this.dispatchingCar;
	}

	public void setDispatchingCar(Integer dispatchingCar) {
		this.dispatchingCar = dispatchingCar;
	}

	public Integer getCarLineId() {
		return this.carLineId;
	}

	public void setCarLineId(Integer carLineId) {
		this.carLineId = carLineId;
	}

	public String getDispatchingUser() {
		return this.dispatchingUser;
	}

	public void setDispatchingUser(String dispatchingUser) {
		this.dispatchingUser = dispatchingUser;
	}

	public String getPlanDate() {
		return this.planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPlanStatus() {
		return this.planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

}