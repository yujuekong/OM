package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * 游戏设置的奖励
 */

public class ActivityGamePrize  extends BasePo {

	// Fields

	private Integer gamePrizeId;//奖励ID
	private Integer gameId;//游戏ID、
	private String prizeName;//奖励名称
	private Integer prizeAmount;//奖励金额
	private Integer prizeType;//奖励类型
	private String prizePro; //中奖概率
	private Integer prizeNum; //奖品总数量
	

	// Constructors

	/** default constructor */
	public ActivityGamePrize() {
	}

	/** full constructor */
	public ActivityGamePrize(Integer gameId, String prizeName,
			Integer prizeAmount, Integer prizeType) {
		this.gameId = gameId;
		this.prizeName = prizeName;
		this.prizeAmount = prizeAmount;
		this.prizeType = prizeType;
	}

	// Property accessors

	public Integer getGamePrizeId() {
		return this.gamePrizeId;
	}

	public void setGamePrizeId(Integer gamePrizeId) {
		this.gamePrizeId = gamePrizeId;
	}

	public Integer getGameId() {
		return this.gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public String getPrizeName() {
		return this.prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public Integer getPrizeAmount() {
		return this.prizeAmount;
	}

	public void setPrizeAmount(Integer prizeAmount) {
		this.prizeAmount = prizeAmount;
	}

	public Integer getPrizeType() {
		return prizeType;
	}

	public void setPrizeType(Integer prizeType) {
		this.prizeType = prizeType;
	}

	public Integer getPrizeNum() {
		return prizeNum;
	}

	public void setPrizeNum(Integer prizeNum) {
		this.prizeNum = prizeNum;
	}

	public String getPrizePro() {
		return prizePro;
	}

	public void setPrizePro(String prizePro) {
		this.prizePro = prizePro;
	}
	
	

}