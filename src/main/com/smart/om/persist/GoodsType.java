package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;
import org.apache.poi.openxml4j.opc.PackageRelationship;

/**
 * GoodsType entity. @author MyEclipse Persistence Tools
 */

public class GoodsType extends BasePo{

	// Fields

	private Integer gtId;
	private Integer dgtId;
	private String gtNo;
	private String gtName;
	private String isDel;

	private GoodsType goodsType;
	// Constructors

	/** default constructor */
	public GoodsType() {
	}

	/** full constructor */
	public GoodsType(Integer dgtId, String gtNo, String gtName, String isDel) {
		this.dgtId = dgtId;
		this.gtNo = gtNo;
		this.gtName = gtName;
		this.isDel = isDel;
	}

	// Property accessors

	public Integer getGtId() {
		return this.gtId;
	}

	public void setGtId(Integer gtId) {
		this.gtId = gtId;
	}

	public Integer getDgtId() {
		return this.dgtId;
	}

	public void setDgtId(Integer dgtId) {
		this.dgtId = dgtId;
	}

	public String getGtNo() {
		return this.gtNo;
	}

	public void setGtNo(String gtNo) {
		this.gtNo = gtNo;
	}

	public String getGtName() {
		return this.gtName;
	}

	public void setGtName(String gtName) {
		this.gtName = gtName;
	}

	public String getIsDel() {
		return this.isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public GoodsType getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}
}