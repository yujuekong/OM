package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * 调拨明细
 */

public class StorageAllotDtl extends BasePo {

    // Fields

    private Integer allotDtlId;
    private Integer allotId;
    private Integer goodsId;
    private Double expectNumber;
    private Double actualNumber;

    // Constructors

    /**
     * default constructor
     */
    public StorageAllotDtl() {
    }

    /**
     * full constructor
     */
    public StorageAllotDtl(Integer allotId, Integer goodsId,
                           Double expectNumber, Double actualNumber) {
        this.allotId = allotId;
        this.goodsId = goodsId;
        this.expectNumber = expectNumber;
        this.actualNumber = actualNumber;
    }

    // Property accessors

    public Integer getAllotDtlId() {
        return this.allotDtlId;
    }

    public void setAllotDtlId(Integer allotDtlId) {
        this.allotDtlId = allotDtlId;
    }

    public Integer getAllotId() {
        return this.allotId;
    }

    public void setAllotId(Integer allotId) {
        this.allotId = allotId;
    }

    public Integer getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Double getExpectNumber() {
        return expectNumber;
    }

    public void setExpectNumber(Double expectNumber) {
        this.expectNumber = expectNumber;
    }

    public Double getActualNumber() {
        return actualNumber;
    }

    public void setActualNumber(Double actualNumber) {
        this.actualNumber = actualNumber;
    }
}