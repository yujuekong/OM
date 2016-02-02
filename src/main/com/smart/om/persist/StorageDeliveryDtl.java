package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * StorageDeliveryDtl entity. @author MyEclipse Persistence Tools
 */

public class StorageDeliveryDtl extends BasePo {

    // Fields

    private Integer deliveryDtlId;
    private Integer deliveryOrderId;
    private Integer goodsId;
    private Integer deviceInfoId;
    private Double requestCount;
    private Double deliveryCount;
    private String remark;
    private Integer agentType;
    private String devicePic1;
    private String devicePic2;
    private String devicePic3;
    private String devicePic4;
    private String devicePic5;

    private StorageDeliveryOrder storageDeliveryOrder;
    private GoodsInfo goodsInfo;
    private DeviceInfo deviceInfo;

    public Double getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Double requestCount) {
        this.requestCount = requestCount;
    }

    public Double getDeliveryCount() {
        return deliveryCount;
    }

    public void setDeliveryCount(Double deliveryCount) {
        this.deliveryCount = deliveryCount;
    }
    // Constructors

    /**
     * default constructor
     */
    public StorageDeliveryDtl() {

    }

    /**
     * full constructor
     */
    public StorageDeliveryDtl(Integer deliveryOrderId, Integer goodsId,
                              Double deliveryCount, String remark) {
        this.deliveryOrderId = deliveryOrderId;
        this.goodsId = goodsId;
        this.deliveryCount = deliveryCount;
        this.remark = remark;
    }

    // Property accessors

    public Integer getDeliveryDtlId() {
        return this.deliveryDtlId;
    }

    public void setDeliveryDtlId(Integer deliveryDtlId) {
        this.deliveryDtlId = deliveryDtlId;
    }

    public Integer getDeliveryOrderId() {
        return this.deliveryOrderId;
    }

    public void setDeliveryOrderId(Integer deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }

    public Integer getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }


    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDeviceInfoId() {
        return deviceInfoId;
    }

    public void setDeviceInfoId(Integer deviceInfoId) {
        this.deviceInfoId = deviceInfoId;
    }

    public GoodsInfo getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }


    public StorageDeliveryOrder getStorageDeliveryOrder() {
        return storageDeliveryOrder;
    }

    public void setStorageDeliveryOrder(StorageDeliveryOrder storageDeliveryOrder) {
        this.storageDeliveryOrder = storageDeliveryOrder;
    }

    public Integer getAgentType() {
        return agentType;
    }

    public void setAgentType(Integer agentType) {
        this.agentType = agentType;
    }

    public String getDevicePic1() {
        return devicePic1;
    }

    public void setDevicePic1(String devicePic1) {
        this.devicePic1 = devicePic1;
    }

    public String getDevicePic2() {
        return devicePic2;
    }

    public void setDevicePic2(String devicePic2) {
        this.devicePic2 = devicePic2;
    }

    public String getDevicePic3() {
        return devicePic3;
    }

    public void setDevicePic3(String devicePic3) {
        this.devicePic3 = devicePic3;
    }

    public String getDevicePic4() {
        return devicePic4;
    }

    public void setDevicePic4(String devicePic4) {
        this.devicePic4 = devicePic4;
    }

    public String getDevicePic5() {
        return devicePic5;
    }

    public void setDevicePic5(String devicePic5) {
        this.devicePic5 = devicePic5;
    }
}