package com.smart.om.persist;

/**
 * Created by hxt on 2016/1/12.
 */

import com.smart.om.dao.base.BasePo;

/**
 * 商品积分
 */
public class GoodsIntegral extends BasePo {
    private Integer goodsIntegralId;//主键
    private Integer goodsId;//商品ID
    private Integer integralNumber;//商品对应积分值
    private String isUser;//是否使用
    private String goodsName;

    public GoodsIntegral() {
    }

    public GoodsIntegral(Integer goodsIntegralId, Integer goodsId, Integer integralNumber, String isUser) {
        this.goodsIntegralId = goodsIntegralId;
        this.goodsId = goodsId;
        this.integralNumber = integralNumber;
        this.isUser = isUser;
    }

    public Integer getGoodsIntegralId() {
        return goodsIntegralId;
    }

    public void setGoodsIntegralId(Integer goodsIntegralId) {
        this.goodsIntegralId = goodsIntegralId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getIntegralNumber() {
        return integralNumber;
    }

    public void setIntegralNumber(Integer integralNumber) {
        this.integralNumber = integralNumber;
    }

    public String getIsUser() {
        return isUser;
    }

    public void setIsUser(String isUser) {
        this.isUser = isUser;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
