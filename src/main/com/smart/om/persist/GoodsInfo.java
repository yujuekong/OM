package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * GoodsInfo entity. @author MyEclipse Persistence Tools
 */

public class GoodsInfo extends BasePo {

    // Fields

    private Integer goodsId;
    private Integer gtId;
    private String goodsTypeNo;
    private String goodsLsNo;
    private String goodsBarCode;
    private String goodsName;
    private String goodsBrand;
    private Integer measurementUnit;
    private String goodsSpec;
    private String goodsSpell;
    private String antistaling;
    private String qualityPeriod;
    private String goodsStatus;
    private String isDel;
    private String goodsTypeName;
    private String goodsPic1;
    private String goodsPic2;
    private String goodsDesc;
    private Integer agentType;
    private String agentTypeName;
    private String unitName;
    private Integer isFrozen;
    private Integer dictOrgId;
    private Integer dictProviceId;
    private Integer dictRegionId;
    private Double  goodsPrice;
    private String synopsis;
    private Integer goodsSort;

    /**
     * default constructor
     */
    public GoodsInfo() {
    }

    /**
     * full constructor
     */
    public GoodsInfo(Integer gtId, String goodsTypeNo, String goodsLsNo,
                     String goodsBarCode, String goodsName, String goodsBrand,
                     Integer measurementUnit, String goodsSpec, String goodsSpell,
                     String antistaling, String qualityPeriod, String goodsStatus,
                     String isDel) {
        this.gtId = gtId;
        this.goodsTypeNo = goodsTypeNo;
        this.goodsLsNo = goodsLsNo;
        this.goodsBarCode = goodsBarCode;
        this.goodsName = goodsName;
        this.goodsBrand = goodsBrand;
        this.measurementUnit = measurementUnit;
        this.goodsSpec = goodsSpec;
        this.goodsSpell = goodsSpell;
        this.antistaling = antistaling;
        this.qualityPeriod = qualityPeriod;
        this.goodsStatus = goodsStatus;
        this.isDel = isDel;
    }

    // Property accessors
    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    public Integer getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGtId() {
        return this.gtId;
    }

    public void setGtId(Integer gtId) {
        this.gtId = gtId;
    }

    public String getGoodsTypeNo() {
        return this.goodsTypeNo;
    }

    public void setGoodsTypeNo(String goodsTypeNo) {
        this.goodsTypeNo = goodsTypeNo;
    }

    public String getGoodsLsNo() {
        return this.goodsLsNo;
    }

    public void setGoodsLsNo(String goodsLsNo) {
        this.goodsLsNo = goodsLsNo;
    }

    public String getGoodsBarCode() {
        return this.goodsBarCode;
    }

    public void setGoodsBarCode(String goodsBarCode) {
        this.goodsBarCode = goodsBarCode;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsBrand() {
        return this.goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public Integer getMeasurementUnit() {
        return this.measurementUnit;
    }

    public void setMeasurementUnit(Integer measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public String getGoodsSpec() {
        return this.goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public String getGoodsSpell() {
        return this.goodsSpell;
    }

    public void setGoodsSpell(String goodsSpell) {
        this.goodsSpell = goodsSpell;
    }

    public String getAntistaling() {
        return this.antistaling;
    }

    public void setAntistaling(String antistaling) {
        this.antistaling = antistaling;
    }

    public String getQualityPeriod() {
        return this.qualityPeriod;
    }

    public void setQualityPeriod(String qualityPeriod) {
        this.qualityPeriod = qualityPeriod;
    }

    public String getGoodsStatus() {
        return this.goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getIsDel() {
        return this.isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getGoodsPic1() {
        return goodsPic1;
    }

    public void setGoodsPic1(String goodsPic1) {
        this.goodsPic1 = goodsPic1;
    }

    public String getGoodsPic2() {
        return goodsPic2;
    }

    public void setGoodsPic2(String goodsPic2) {
        this.goodsPic2 = goodsPic2;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getAgentTypeName() {
        return agentTypeName;
    }

    public void setAgentTypeName(String agentTypeName) {
        this.agentTypeName = agentTypeName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getAgentType() {
        return agentType;
    }

    public void setAgentType(Integer agentType) {
        this.agentType = agentType;
    }

    public Integer getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(Integer isFrozen) {
        this.isFrozen = isFrozen;
    }

	public Integer getDictOrgId() {
		return dictOrgId;
	}

	public void setDictOrgId(Integer dictOrgId) {
		this.dictOrgId = dictOrgId;
	}

	public Integer getDictProviceId() {
		return dictProviceId;
	}

	public void setDictProviceId(Integer dictProviceId) {
		this.dictProviceId = dictProviceId;
	}

	public Integer getDictRegionId() {
		return dictRegionId;
	}

	public void setDictRegionId(Integer dictRegionId) {
		this.dictRegionId = dictRegionId;
	}

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getGoodsSort() {
        return goodsSort;
    }

    public void setGoodsSort(Integer goodsSort) {
        this.goodsSort = goodsSort;
    }
}