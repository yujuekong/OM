package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * 抽奖游戏活动
 */

public class ActivityGameInfo  extends BasePo {

	// Fields

	private Integer gameId;//游戏活动ID
	private String gameName;//游戏名称
	private String startDate;//游戏开始日期
	private String endDate;//游戏结束日期
	private Integer payAmount;//玩游戏消耗数量
	private Integer payType;//玩游戏消耗类型
	private String gameStatus;//游戏状态
	private Integer dictOrgId;//所属分公司

	// Constructors

	/** default constructor */
	public ActivityGameInfo() {
	}

	/** full constructor */
	public ActivityGameInfo(String gameName, String startDate, String endDate,
			Integer payAmount, Integer payType, String gameStatus,
			Integer dictOrgId) {
		this.gameName = gameName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.payAmount = payAmount;
		this.payType = payType;
		this.gameStatus = gameStatus;
		this.dictOrgId = dictOrgId;
	}

	// Property accessors

	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return this.gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getPayAmount() {
		return this.payAmount;
	}

	public void setPayAmount(Integer payAmount) {
		this.payAmount = payAmount;
	}

	public String getGameStatus() {
		return this.gameStatus;
	}

	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}

	public Integer getDictOrgId() {
		return this.dictOrgId;
	}

	public void setDictOrgId(Integer dictOrgId) {
		this.dictOrgId = dictOrgId;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

}