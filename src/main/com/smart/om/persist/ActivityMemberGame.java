package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * 会员游戏
 */

public class ActivityMemberGame extends BasePo{

	// Fields

	private Integer memberGameId;//会员游戏ID
	private Integer gameId;//游戏ID
	private Integer memberId;//会员ID
	private Integer gamePrizeId;//游戏奖励ID
	private String gameTime;//游戏时间

	// Constructors

	/** default constructor */
	public ActivityMemberGame() {
	}

	/** full constructor */
	public ActivityMemberGame(Integer gameId, Integer memberId,
			Integer gamePrizeId, String gameTime) {
		this.gameId = gameId;
		this.memberId = memberId;
		this.gamePrizeId = gamePrizeId;
		this.gameTime = gameTime;
	}

	// Property accessors

	public Integer getMemberGameId() {
		return this.memberGameId;
	}

	public void setMemberGameId(Integer memberGameId) {
		this.memberGameId = memberGameId;
	}

	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public Integer getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getGamePrizeId() {
		return this.gamePrizeId;
	}

	public void setGamePrizeId(Integer gamePrizeId) {
		this.gamePrizeId = gamePrizeId;
	}

	public String getGameTime() {
		return this.gameTime;
	}

	public void setGameTime(String gameTime) {
		this.gameTime = gameTime;
	}

}