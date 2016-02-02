package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * DispatchingGoods entity. @author MyEclipse Persistence Tools
 */

public class DispatchingGoods extends BasePo {

    // Fields

    private Integer dispatchingGoodsId;
    private Integer nodeId;
    private Integer deviceId;
    private Integer goodsId;
    private Double goodsNumber;

    private CarLineNode lineNode;
    private DeviceInfo dInfo;
    private GoodsInfo gInfo;

    // Constructors

    /**
     * default constructor
     */
    public DispatchingGoods() {
    }

    /**
     * full constructor
     */
    public DispatchingGoods(Integer nodeId, Integer deviceId, Integer goodsId,
                            Double goodsNumber) {
        this.nodeId = nodeId;
        this.deviceId = deviceId;
        this.goodsId = goodsId;
        this.goodsNumber = goodsNumber;
    }

    // Property accessors

    public Integer getDispatchingGoodsId() {
        return this.dispatchingGoodsId;
    }

    public void setDispatchingGoodsId(Integer dispatchingGoodsId) {
        this.dispatchingGoodsId = dispatchingGoodsId;
    }

    public Integer getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Double getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(Double goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public CarLineNode getLineNode() {
        return lineNode;
    }

    public void setLineNode(CarLineNode lineNode) {
        this.lineNode = lineNode;
    }

    public DeviceInfo getdInfo() {
        return dInfo;
    }

    public void setdInfo(DeviceInfo dInfo) {
        this.dInfo = dInfo;
    }

    public GoodsInfo getgInfo() {
        return gInfo;
    }

    public void setgInfo(GoodsInfo gInfo) {
        this.gInfo = gInfo;
    }

}