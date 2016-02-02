package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * GoodsType entity. @author MyEclipse Persistence Tools
 */

public class OrderPay extends BasePo {
    private Integer payId;
    private String payNumber;
    private Integer goodsId;
    private String goodsName;
    private String goodsNumber;
    private String goosUnit;
    private String userRealName;
    private String tel;
    private String userAddress;
    private String orderNo;
    private String orderStatus;//是否付款：0-未付款；1-已付款
    private String orderTime;
    private String openId;
    private String isGet;//是否取货：0-未取货；1-已取货
    private Integer deviceId;

    private Double price;
    private String city;
    private String region;
    private String town;
    
    private String isRefund;//是否退款：0-未退款；1-已退款

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getGoosUnit() {
        return goosUnit;
    }

    public void setGoosUnit(String goosUnit) {
        this.goosUnit = goosUnit;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrderPay orderPay = (OrderPay) o;

        if (payId != null ? !payId.equals(orderPay.payId) : orderPay.payId != null) return false;
        if (payNumber != null ? !payNumber.equals(orderPay.payNumber) : orderPay.payNumber != null) return false;
        if (goodsId != null ? !goodsId.equals(orderPay.goodsId) : orderPay.goodsId != null) return false;
        if (goodsName != null ? !goodsName.equals(orderPay.goodsName) : orderPay.goodsName != null) return false;
        if (goodsNumber != null ? !goodsNumber.equals(orderPay.goodsNumber) : orderPay.goodsNumber != null)
            return false;
        if (goosUnit != null ? !goosUnit.equals(orderPay.goosUnit) : orderPay.goosUnit != null) return false;
        if (userRealName != null ? !userRealName.equals(orderPay.userRealName) : orderPay.userRealName != null)
            return false;
        if (tel != null ? !tel.equals(orderPay.tel) : orderPay.tel != null) return false;
        if (userAddress != null ? !userAddress.equals(orderPay.userAddress) : orderPay.userAddress != null)
            return false;
        if (orderNo != null ? !orderNo.equals(orderPay.orderNo) : orderPay.orderNo != null) return false;
        return !(orderStatus != null ? !orderStatus.equals(orderPay.orderStatus) : orderPay.orderStatus != null);

    }

    @Override
    public int hashCode() {
        int result = payId != null ? payId.hashCode() : 0;
        result = 31 * result + (payNumber != null ? payNumber.hashCode() : 0);
        result = 31 * result + (goodsId != null ? goodsId.hashCode() : 0);
        result = 31 * result + (goodsName != null ? goodsName.hashCode() : 0);
        result = 31 * result + (goodsNumber != null ? goodsNumber.hashCode() : 0);
        result = 31 * result + (goosUnit != null ? goosUnit.hashCode() : 0);
        result = 31 * result + (userRealName != null ? userRealName.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (userAddress != null ? userAddress.hashCode() : 0);
        result = 31 * result + (orderNo != null ? orderNo.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        return result;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getIsGet() {
		return isGet;
	}

	public void setIsGet(String isGet) {
		this.isGet = isGet;
	}

	public String getIsRefund() {
		return isRefund;
	}

	public void setIsRefund(String isRefund) {
		this.isRefund = isRefund;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
}