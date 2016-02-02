package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * 配送订单
 */

public class DispatchingOrder extends BasePo {

	// Fields

	private Integer orderId;
	private String orderNo;
	private String orderTime;
	private Integer createUser;
	private Integer warehouseId;
	private String orderStatus;
	private String orderFinishTime;
	private String orderEndTime;
	private Integer teamId;
	private StorageWarehouse warehouse; 
	private SysUser user;
	private DispatchingTeam team;
	private Integer deliveryOrderId;
	private String orderAllocation;
	private Integer dictRegionId;
	private Integer dictProviceId;
	private Integer dictOrgId;
	
	// Constructors

	/** default constructor */
	public DispatchingOrder() {
	}

	/** full constructor */
	public DispatchingOrder(String orderNo, String orderTime, String orderDate,
			Integer createUser, Integer warehouseId) {
		this.orderNo = orderNo;
		this.orderTime = orderTime;
		this.createUser = createUser;
		this.warehouseId = warehouseId;
	}

	// Property accessors

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderFinishTime() {
		return orderFinishTime;
	}

	public void setOrderFinishTime(String orderFinishTime) {
		this.orderFinishTime = orderFinishTime;
	}

	public String getOrderEndTime() {
		return orderEndTime;
	}

	public void setOrderEndTime(String orderEndTime) {
		this.orderEndTime = orderEndTime;
	}

	public StorageWarehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(StorageWarehouse warehouse) {
		this.warehouse = warehouse;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public DispatchingTeam getTeam() {
		return team;
	}

	public void setTeam(DispatchingTeam team) {
		this.team = team;
	}

	public Integer getDeliveryOrderId() {
		return deliveryOrderId;
	}

	public void setDeliveryOrderId(Integer deliveryOrderId) {
		this.deliveryOrderId = deliveryOrderId;
	}

	public String getOrderAllocation() {
		return orderAllocation;
	}

	public void setOrderAllocation(String orderAllocation) {
		this.orderAllocation = orderAllocation;
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