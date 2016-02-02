package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * 配送子订单
 */

public class DispatchingSubOrder extends BasePo {

    // Fields

    private Integer subOrderId;
    private Integer orderId;
    private Integer deviceId;
    private String deviceNo;
    private Integer districtId;
    private String districtName;
    private Integer goodsId;
    private String goodsName;
    private Double goodsNumber;
    private Integer dispatchingTeamId;
    private String dispatchingTime;
    private Double currencyAmount;
    private String subOrderStatus;
    private String orderPic1;
    private String orderPic2;
    private String orderPic3;
    private String orderPic4;
    private String orderPic5;
    private String orderAllocation;
    private Integer dictRegionId;
    private Integer dictProviceId;
    private Integer dictOrgId;
    private String bladeChange;
    private Double goodsCount;
    private String pomace;
    private Integer cupNumber;
    private Double realiGoodsNumber;

    private DeviceInfo deviceInfo;
    private GoodsInfo goodsInfo;
    private DispatchingTeam dispatchTeam;

    // Constructors

    /**
     * default constructor
     */
    public DispatchingSubOrder() {
    }

    /**
     * full constructor
     */
    public DispatchingSubOrder(Integer orderId, Integer deviceId,
                               String deviceNo, Integer districtId, String districtName,
                               Integer goodsId, String goodsName, Double goodsNumber,
                               Integer dispatchingTeamId, String dispatchingTime,
                               Double currencyAmount, String subOrderStatus, String orderPic1,
                               String orderPic2, String orderPic3, String orderPic4,
                               String orderPic5) {
        this.orderId = orderId;
        this.deviceId = deviceId;
        this.deviceNo = deviceNo;
        this.districtId = districtId;
        this.districtName = districtName;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsNumber = goodsNumber;
        this.dispatchingTeamId = dispatchingTeamId;
        this.dispatchingTime = dispatchingTime;
        this.currencyAmount = currencyAmount;
        this.subOrderStatus = subOrderStatus;
        this.orderPic1 = orderPic1;
        this.orderPic2 = orderPic2;
        this.orderPic3 = orderPic3;
        this.orderPic4 = orderPic4;
        this.orderPic5 = orderPic5;
    }

    // Property accessors

    public Integer getSubOrderId() {
        return this.subOrderId;
    }

    public void setSubOrderId(Integer subOrderId) {
        this.subOrderId = subOrderId;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getDistrictId() {
        return this.districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return this.districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Integer getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getDispatchingTeamId() {
        return this.dispatchingTeamId;
    }

    public void setDispatchingTeamId(Integer dispatchingTeamId) {
        this.dispatchingTeamId = dispatchingTeamId;
    }

    public String getDispatchingTime() {
        return this.dispatchingTime;
    }

    public void setDispatchingTime(String dispatchingTime) {
        this.dispatchingTime = dispatchingTime;
    }

    public String getSubOrderStatus() {
        return this.subOrderStatus;
    }

    public void setSubOrderStatus(String subOrderStatus) {
        this.subOrderStatus = subOrderStatus;
    }

    public String getOrderPic1() {
        return this.orderPic1;
    }

    public void setOrderPic1(String orderPic1) {
        this.orderPic1 = orderPic1;
    }

    public String getOrderPic2() {
        return this.orderPic2;
    }

    public void setOrderPic2(String orderPic2) {
        this.orderPic2 = orderPic2;
    }

    public String getOrderPic3() {
        return this.orderPic3;
    }

    public void setOrderPic3(String orderPic3) {
        this.orderPic3 = orderPic3;
    }

    public String getOrderPic4() {
        return this.orderPic4;
    }

    public void setOrderPic4(String orderPic4) {
        this.orderPic4 = orderPic4;
    }

    public String getOrderPic5() {
        return this.orderPic5;
    }

    public void setOrderPic5(String orderPic5) {
        this.orderPic5 = orderPic5;
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

    public String getOrderAllocation() {
        return orderAllocation;
    }

    public void setOrderAllocation(String orderAllocation) {
        this.orderAllocation = orderAllocation;
    }

    public DispatchingTeam getDispatchTeam() {
        return dispatchTeam;
    }

    public void setDispatchTeam(DispatchingTeam dispatchTeam) {
        this.dispatchTeam = dispatchTeam;
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

    public Double getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Double goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public Double getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(Double currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public void setDictProviceId(Integer dictProviceId) {
        this.dictProviceId = dictProviceId;
    }

	public Integer getCupNumber() {
		return cupNumber;
	}

	public void setCupNumber(Integer cupNumber) {
		this.cupNumber = cupNumber;
	}

	public String getPomace() {
		return pomace;
	}

	public void setPomace(String pomace) {
		this.pomace = pomace;
	}

	public Double getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Double goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getBladeChange() {
		return bladeChange;
	}

	public void setBladeChange(String bladeChange) {
		this.bladeChange = bladeChange;
	}

	public Double getRealiGoodsNumber() {
		return realiGoodsNumber;
	}

	public void setRealiGoodsNumber(Double realiGoodsNumber) {
		this.realiGoodsNumber = realiGoodsNumber;
	}
}