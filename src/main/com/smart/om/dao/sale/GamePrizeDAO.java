package com.smart.om.dao.sale;

import java.util.List;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.ActivityGamePrize;

public class GamePrizeDAO extends BaseDao{
	/**
	 * 根据ID查询活动奖励信息
	 */
	public List<ActivityGamePrize> queryActivityPrizeByGameId(Integer id){
		StringBuffer hql = new StringBuffer();
		hql.append(" from ActivityGamePrize agp where 1=1 ");
		hql.append(" and agp.gameId = ").append(id);
		List<ActivityGamePrize> list = this.find(hql.toString(), null);
		return list;
	}
}
