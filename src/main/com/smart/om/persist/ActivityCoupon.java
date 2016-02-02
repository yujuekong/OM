package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * 优惠券
 * 后台添加优惠券，包括起止日期
 */

public class ActivityCoupon extends BasePo {

    // Fields

    private Integer couponId;//优惠券ID
    private String couponName;//优惠券名称
    private String startDate;//有效开始日期
    private String endDate;//有效结束日期
    private Double couponAmount;//优惠券面值
    private String couponType;//优惠券类型
    private Integer dictOrgId;//所属分公司
    private Integer couponNumber;//数量
    private String getCouponEndDate;//领取结束时间
    private String couponDistrict;//优惠券地区说明

    private SysDict sysDict;

    // Constructors

    /**
     * default constructor
     */
    public ActivityCoupon() {
    }

    /**
     * full constructor
     */
    public ActivityCoupon(String couponName, String startDate, String endDate,
                          Integer couponAmount, String couponType, Integer dictOrgId) {
        this.couponName = couponName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.couponType = couponType;
        this.dictOrgId = dictOrgId;
    }

    // Property accessors

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Double couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public Integer getDictOrgId() {
        return dictOrgId;
    }

    public void setDictOrgId(Integer dictOrgId) {
        this.dictOrgId = dictOrgId;
    }

    public Integer getCouponNumber() {
        return couponNumber;
    }

    public void setCouponNumber(Integer couponNumber) {
        this.couponNumber = couponNumber;
    }

    public String getGetCouponEndDate() {
        return getCouponEndDate;
    }

    public void setGetCouponEndDate(String getCouponEndDate) {
        this.getCouponEndDate = getCouponEndDate;
    }

    public String getCouponDistrict() {
        return couponDistrict;
    }

    public void setCouponDistrict(String couponDistrict) {
        this.couponDistrict = couponDistrict;
    }

    public SysDict getSysDict() {
        return sysDict;
    }

    public void setSysDict(SysDict sysDict) {
        this.sysDict = sysDict;
    }
}