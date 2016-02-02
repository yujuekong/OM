package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * 设备订单
 * @author liuz
 *
 */
public class DeviceOrder extends BasePo {
	private Integer deviceOrderId;
	private Integer deviceId;
	private Integer orderId; 
	private String orderPic1;
    private String orderPic2;
    private String orderPic3;
    private String orderPic4;
    private String orderPic5;
    private Integer cupNumber;
    private Integer strawNumber;
    private String isFinish;
    private String isBladeCheck;
    
    
	public Integer getDeviceOrderId() {
		return deviceOrderId;
	}
	public void setDeviceOrderId(Integer deviceOrderId) {
		this.deviceOrderId = deviceOrderId;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderPic1() {
		return orderPic1;
	}
	public void setOrderPic1(String orderPic1) {
		this.orderPic1 = orderPic1;
	}
	public String getOrderPic2() {
		return orderPic2;
	}
	public void setOrderPic2(String orderPic2) {
		this.orderPic2 = orderPic2;
	}
	public String getOrderPic3() {
		return orderPic3;
	}
	public void setOrderPic3(String orderPic3) {
		this.orderPic3 = orderPic3;
	}
	public String getOrderPic4() {
		return orderPic4;
	}
	public void setOrderPic4(String orderPic4) {
		this.orderPic4 = orderPic4;
	}
	public String getOrderPic5() {
		return orderPic5;
	}
	public void setOrderPic5(String orderPic5) {
		this.orderPic5 = orderPic5;
	}
	public Integer getCupNumber() {
		return cupNumber;
	}
	public void setCupNumber(Integer cupNumber) {
		this.cupNumber = cupNumber;
	}
	public Integer getStrawNumber() {
		return strawNumber;
	}
	public void setStrawNumber(Integer strawNumber) {
		this.strawNumber = strawNumber;
	}
	public String getIsFinish() {
		return isFinish;
	}
	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}
	public String getIsBladeCheck() {
		return isBladeCheck;
	}
	public void setIsBladeCheck(String isBladeCheck) {
		this.isBladeCheck = isBladeCheck;
	}
}
