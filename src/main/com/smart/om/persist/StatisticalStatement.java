package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

public class StatisticalStatement extends BasePo {
    private Integer statisticalStatementId;
    private String year;
    private String month;
    private Integer dictOrgId;
    private Integer goodsId;
    private Double saleMoney;
    
	public Integer getStatisticalStatementId() {
		return statisticalStatementId;
	}
	public void setStatisticalStatementId(Integer statisticalStatementId) {
		this.statisticalStatementId = statisticalStatementId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getDictOrgId() {
		return dictOrgId;
	}
	public void setDictOrgId(Integer dictOrgId) {
		this.dictOrgId = dictOrgId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Double getSaleMoney() {
		return saleMoney;
	}
	public void setSaleMoney(Double saleMoney) {
		this.saleMoney = saleMoney;
	}
}