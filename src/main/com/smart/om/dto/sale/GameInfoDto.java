package com.smart.om.dto.sale;

import java.io.Serializable;

public class GameInfoDto implements Serializable{
	
	private Integer gameId;//游戏活动ID
	private String gameName;//游戏名称
	private String startDate;//游戏开始日期
	private String endDate;//游戏结束其他
	private Integer payAmount;//玩游戏消耗数量
	private String payType;//玩游戏消耗类型
	private String gameStatus;//游戏状态
	private Integer dictOrgId;//所属分公司
	private Integer gamePrizeId;//奖励ID
	private String prizeName;//奖励名称
	private Integer prizeAmount;//奖励金额
	private String prizeType;//奖励类型
	
	
	public Integer getGameId() {
		return gameId;
	}
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(Integer payAmount) {
		this.payAmount = payAmount;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getGameStatus() {
		return gameStatus;
	}
	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}
	public Integer getDictOrgId() {
		return dictOrgId;
	}
	public void setDictOrgId(Integer dictOrgId) {
		this.dictOrgId = dictOrgId;
	}
	public Integer getGamePrizeId() {
		return gamePrizeId;
	}
	public void setGamePrizeId(Integer gamePrizeId) {
		this.gamePrizeId = gamePrizeId;
	}
	public String getPrizeName() {
		return prizeName;
	}
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	public Integer getPrizeAmount() {
		return prizeAmount;
	}
	public void setPrizeAmount(Integer prizeAmount) {
		this.prizeAmount = prizeAmount;
	}
	public String getPrizeType() {
		return prizeType;
	}
	public void setPrizeType(String prizeType) {
		this.prizeType = prizeType;
	}
	
	
	
}
