package com.smart.om.dto.sale;

import java.io.Serializable;

public class MemberGameDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer memberGameId;//会员游戏ID
	private Integer gameId;//游戏ID
	private Integer memberId;//会员ID
	private Integer gamePrizeId;//游戏奖励ID
	private String gameTime;//游戏时间
	private String memberName;
	private String gameName;//游戏名称
	private Integer prizeAmount;//奖励金额
	
	
	public Integer getMemberGameId() {
		return memberGameId;
	}
	public void setMemberGameId(Integer memberGameId) {
		this.memberGameId = memberGameId;
	}
	public Integer getGameId() {
		return gameId;
	}
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getGamePrizeId() {
		return gamePrizeId;
	}
	public void setGamePrizeId(Integer gamePrizeId) {
		this.gamePrizeId = gamePrizeId;
	}
	public String getGameTime() {
		return gameTime;
	}
	public void setGameTime(String gameTime) {
		this.gameTime = gameTime;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public Integer getPrizeAmount() {
		return prizeAmount;
	}
	public void setPrizeAmount(Integer prizeAmount) {
		this.prizeAmount = prizeAmount;
	}
	
	
}
