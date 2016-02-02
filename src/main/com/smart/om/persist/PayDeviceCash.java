package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * 收取现金
 */

public class PayDeviceCash extends BasePo {

    // Fields

    private Integer cashId;
    private Integer deviceId;
    private String deviceNo;
    private Double cashNumber;
    private String lastCashTime;
    private String thisCashTime;
    private Integer postingUser;
    private String postingTime;
    private String cashStatus;
    private Integer orderId;
    private Double shouldCashNumber;


    // Constructors

    /**
     * default constructor
     */
    public PayDeviceCash() {
    }

    /**
     * full constructor
     */
    public PayDeviceCash(Integer deviceId, String deviceNo, Double cashNumber,
                         String lastCashTime, String thisCashTime, Integer postingUser,
                         String postingTime, String cashStatus,
                         Integer orderId) {
        this.deviceId = deviceId;
        this.deviceNo = deviceNo;
        this.cashNumber = cashNumber;
        this.lastCashTime = lastCashTime;
        this.thisCashTime = thisCashTime;
        this.postingUser = postingUser;
        this.postingTime = postingTime;
        this.cashStatus = cashStatus;
        this.orderId = orderId;
    }

    // Property accessors

    public Integer getCashId() {
        return this.cashId;
    }

    public void setCashId(Integer cashId) {
        this.cashId = cashId;
    }

    public Integer getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceNo() {
        return this.deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Double getCashNumber() {
        return this.cashNumber;
    }

    public void setCashNumber(Double cashNumber) {
        this.cashNumber = cashNumber;
    }

    public String getLastCashTime() {
        return this.lastCashTime;
    }

    public void setLastCashTime(String lastCashTime) {
        this.lastCashTime = lastCashTime;
    }

    public String getThisCashTime() {
        return this.thisCashTime;
    }

    public void setThisCashTime(String thisCashTime) {
        this.thisCashTime = thisCashTime;
    }

    public Integer getPostingUser() {
        return this.postingUser;
    }

    public void setPostingUser(Integer postingUser) {
        this.postingUser = postingUser;
    }

    public String getPostingTime() {
        return this.postingTime;
    }

    public void setPostingTime(String postingTime) {
        this.postingTime = postingTime;
    }

    public String getCashStatus() {
        return this.cashStatus;
    }

    public void setCashStatus(String cashStatus) {
        this.cashStatus = cashStatus;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getShouldCashNumber() {
        return shouldCashNumber;
    }

    public void setShouldCashNumber(Double shouldCashNumber) {
        this.shouldCashNumber = shouldCashNumber;
    }

}