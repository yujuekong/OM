package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * 会员优惠券
 * 1、会员领取的优惠券
 */

public class ActivityMemberCoupon extends BasePo {

    // Fields

    private Integer memberCouponId;//会员领取优惠券ID
    private Integer couponId;//领取的优惠券ID
    private Integer memberId;//会员ID
    private String couponTime;//领取优惠券的时间
    private String expiringTime;//优惠劵到期时间
    private String couponName;//优惠券名称
    private Integer couponAmount;//优惠券面值
    private String couponType;//优惠券类型
    private String isUse;//是否使用

    private ActivityCoupon activityCoupon;
    // Constructors

    /**
     * default constructor
     */
    public ActivityMemberCoupon() {
    }

    /**
     * full constructor
     */
    public ActivityMemberCoupon(Integer couponId, Integer memberId,
                                String couponTime, String couponName, Integer couponAmount,
                                String couponType) {
        this.couponId = couponId;
        this.memberId = memberId;
        this.couponTime = couponTime;
        this.couponName = couponName;
        this.couponAmount = couponAmount;
        this.couponType = couponType;
    }

    // Property accessors

    public Integer getMemberCouponId() {
        return this.memberCouponId;
    }

    public void setMemberCouponId(Integer memberCouponId) {
        this.memberCouponId = memberCouponId;
    }

    public Integer getCouponId() {
        return this.couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Integer getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getCouponTime() {
        return this.couponTime;
    }

    public void setCouponTime(String couponTime) {
        this.couponTime = couponTime;
    }

    public String getCouponName() {
        return this.couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Integer getCouponAmount() {
        return this.couponAmount;
    }

    public void setCouponAmount(Integer couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getCouponType() {
        return this.couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getExpiringTime() {
        return expiringTime;
    }

    public void setExpiringTime(String expiringTime) {
        this.expiringTime = expiringTime;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public ActivityCoupon getActivityCoupon() {
        return activityCoupon;
    }

    public void setActivityCoupon(ActivityCoupon activityCoupon) {
        this.activityCoupon = activityCoupon;
    }
}