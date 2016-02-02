package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * StorageDeliveryClean entity. @author MyEclipse Persistence Tools
 */

public class StorageDeliveryAgent extends BasePo {

	// Fields

	private Integer deliveryAgentId;
	private Integer deliveryOrderId;
	private Integer goodsId;
	private Double agentNumber;
	private String remark;
	private String agentPic;

	private StorageDeliveryOrder storageDeliveryOrder;
	private GoodsInfo goodsInfo;

	// Constructors

	/**
	 * default constructor
	 */
	public StorageDeliveryAgent() {
	}

	/**
	 * full constructor
	 */
	public StorageDeliveryAgent(Integer deliveryOrderId, Integer goodsId,
								Integer agentNumber, String remark) {
		this.deliveryOrderId = deliveryOrderId;
		this.goodsId = goodsId;
		this.remark = remark;
	}

	// Property accessors

	public Integer getDeliveryAgentId() {
		return deliveryAgentId;
	}

	public void setDeliveryAgentId(Integer deliveryAgentId) {
		this.deliveryAgentId = deliveryAgentId;
	}

	public Integer getDeliveryOrderId() {
		return deliveryOrderId;
	}

	public void setDeliveryOrderId(Integer deliveryOrderId) {
		this.deliveryOrderId = deliveryOrderId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAgentPic() {
		return agentPic;
	}

	public void setAgentPic(String agentPic) {
		this.agentPic = agentPic;
	}

	public StorageDeliveryOrder getStorageDeliveryOrder() {
		return storageDeliveryOrder;
	}

	public void setStorageDeliveryOrder(StorageDeliveryOrder storageDeliveryOrder) {
		this.storageDeliveryOrder = storageDeliveryOrder;
	}

	public GoodsInfo getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public Double getAgentNumber() {
		return agentNumber;
	}

	public void setAgentNumber(Double agentNumber) {
		this.agentNumber = agentNumber;
	}
}