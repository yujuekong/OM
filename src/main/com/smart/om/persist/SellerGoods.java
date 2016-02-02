package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * SellerGoods entity. @author MyEclipse Persistence Tools
 */

public class SellerGoods extends BasePo {

	// Fields

	private Integer sellerGoodsId;
	private Integer goodsId;
	private Integer sellerId;
	private String goodsArea;
	private Double goodsPrice;

	private GoodsInfo goodsInfo;
	private SellerInfo sellerInfo;

	// Constructors

	/** default constructor */
	public SellerGoods() {
	}

	/** full constructor */
	public SellerGoods(Integer goodsId, Integer sellerId) {
		this.goodsId = goodsId;
		this.sellerId = sellerId;
	}

	// Property accessors

	public Integer getSellerGoodsId() {
		return this.sellerGoodsId;
	}

	public void setSellerGoodsId(Integer sellerGoodsId) {
		this.sellerGoodsId = sellerGoodsId;
	}

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getGoodsArea() {
		return goodsArea;
	}

	public void setGoodsArea(String goodsArea) {
		this.goodsArea = goodsArea;
	}

	public Double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public GoodsInfo getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(GoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public SellerInfo getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(SellerInfo sellerInfo) {
		this.sellerInfo = sellerInfo;
	}
}