package com.smart.om.dao.sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.dto.advert.AdvertInfoDto;
import com.smart.om.dto.inventory.GoodsInfoDto;
import com.smart.om.dto.sale.GameInfoDto;
import com.smart.om.dto.sale.MemberGameDto;
import com.smart.om.persist.ActivityGamePrize;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import com.smart.om.util.PageModel;

public class GameInfoDAO extends BaseDao{
	/** 查询游戏内容 **/
	public DTablePageModel queryGameInfoPage(Map<String,Object> params,PageData pageData){
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();		
		hql.append("select agi.gameId,agi.gameName,agi.startDate,agi.endDate,agi.payAmount,agi.payType,agi.gameStatus,sd.dictName ");
		hql.append(" from ActivityGameInfo agi,SysDict sd where 1 = 1 ");
		hql.append(" and agi.payType = sd.dictId ");
		hqlCount.append("select count(*) ");		
		hqlCount.append("from ActivityGameInfo agi,SysDict sd where 1 = 1 and agi.payType = sd.dictId ");
		if(params != null) {
			if(params.containsKey("keyword2")){
				String keyword2 = (String) params.get("keyword2");
				if(StringUtils.isNotBlank(keyword2)) {
					hql.append(" and (agi.gameName like '%").append(keyword2).append("%') ");
					hqlCount.append(" and (agi.gameName like '%").append(keyword2).append("%') ");
				}
			}
			if(params.containsKey("gameStatus")){
				String gameStatus = (String) params.get("gameStatus");
				if(StringUtils.isNotBlank(gameStatus)){
					hql.append(" and agi.gameStatus = '").append(gameStatus).append("'");
					hqlCount.append(" and agi.gameStatus = '").append(gameStatus).append("'");
				}
			}
		}
		hql.append(" order by agi.gameId desc  ");
		
		return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
	}
	
	/**
	 * 根据活动id查询活动奖励信息
	 */
	public List<ActivityGamePrize> queryGamePrizeByGameId(Integer id){
		StringBuffer hql = new StringBuffer();
		hql.append(" from ActivityGamePrize agp where 1=1 ");
		hql.append(" and agp.gameId= ").append(id);
		List<ActivityGamePrize> list = this.find(hql.toString(), null);
		return list;
	}
	
	/**
	 * 查询活动结果页面
	 */
	public DTablePageModel querymemberGamePage(Map<String,Object> params,PageData pageData){
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();		
		hql.append(" select amg.memberGameId,amg.gameTime,sm.memberName,agp.prizeAmount,agi.gameName ");
		hql.append(" from SysMember sm,ActivityMemberGame amg,ActivityGamePrize agp,ActivityGameInfo agi where 1 = 1 ");
		hql.append(" and amg.gameId = agi.gameId and amg.memberId = sm.memberId and amg.gamePrizeId = agp.gamePrizeId ");
		hqlCount.append("select count(amg.memberGameId)  ");		
		hqlCount.append("from SysMember sm,ActivityMemberGame amg,ActivityGamePrize agp,ActivityGameInfo agi where 1 = 1 ");
		hqlCount.append(" and amg.gameId = agi.gameId and amg.memberId = sm.memberId and amg.gamePrizeId = agp.gamePrizeId ");
		if(params != null) {
			if(params.containsKey("keyword2")){
				String keyword2 = (String) params.get("keyword2");
				if(StringUtils.isNotBlank(keyword2)) {
					hql.append(" and (model1.advertTitle like '%").append(keyword2).append("%'")
					.append(" or model3.auName like '%").append(keyword2).append("%') ");
					hqlCount.append(" and (model1.advertTitle like '%").append(keyword2).append("%'")
					.append(" or model3.auName like '%").append(keyword2).append("%') ");
				}
			}
			if(params.containsKey("keyword")){
				String keyword = (String) params.get("keyword");
				if(StringUtils.isNotBlank(keyword)){
					hql.append(" and agi.dictOrgId = ").append(keyword);
					hqlCount.append(" and agi.dictOrgId = ").append(keyword);
				}
			}
		}
		hql.append(" order by amg.memberGameId desc  ");

		DTablePageModel pageModel = new DTablePageModel();
		pageModel.setsEcho(pageData.getsEcho());
		PageModel pm = super.queryPageByHqlMySQl(hql.toString(), hqlCount.toString(), null, pageData.getCurrentPage(), pageData.getPageSize());
		if(pm != null) {
			pageModel.setiTotalDisplayRecords(pm.getTotal());
			pageModel.setiTotalRecords(pm.getTotal());
			@SuppressWarnings("unchecked")
			List<Object[]> rowLst = pm.getDatas();
			if(rowLst != null) {
				List<MemberGameDto> didLst = new ArrayList<MemberGameDto>();
				for(Object[] row : rowLst) {
					MemberGameDto aid = new MemberGameDto();
					aid.setMemberGameId((Integer)row[0]);
					aid.setGameTime((String)row[1]);
					aid.setMemberName((String)row[2]);
					aid.setPrizeAmount((Integer)row[3]);
					aid.setGameName((String)row[4]);
					didLst.add(aid);
				}
				pageModel.setAaData(didLst);
			}
		}
		return pageModel;
	}
	
	/**
	 * 查询活动奖励分页信息
	 */
	public DTablePageModel queryGamePrizePage(Map<String,Object> params,PageData pageData){
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();		
		hql.append(" select agp.gamePrizeId,agp.prizeName,agi.gameName,agp.prizeAmount,agp.prizeNum,agp.prizePro,sd.dictName ");
		hql.append(" from ActivityGamePrize agp,ActivityGameInfo agi , SysDict sd where 1 = 1 ");
		hql.append(" and agp.gameId = agi.gameId and agp.prizeType = sd.dictId ");
		hqlCount.append("select count(agp.gamePrizeId)  ");		
		hqlCount.append("from ActivityGamePrize agp,ActivityGameInfo agi , SysDict sd where 1 = 1 ");
		hqlCount.append(" and agp.gameId = agi.gameId and agp.prizeType = sd.dictId ");
		if(params != null) {
			if(params.containsKey("gameId")){
				String gameId = (String) params.get("gameId");
				if(StringUtils.isNotBlank(gameId)){
					hql.append(" and agp.gameId = ").append(Integer.valueOf(gameId));
					hqlCount.append(" and agp.gameId = ").append(Integer.valueOf(gameId));
				}
			}
			if(params.containsKey("keyword")){
				String keyword = (String) params.get("keyword");
				if(StringUtils.isNotBlank(keyword)){
					hql.append(" and (agp.prizeName like '%").append(keyword).append("%'")
					.append(" or agi.gameName like '").append(keyword).append("%'")
					.append(" or sd.dictName like '").append(keyword).append("%')");
					hqlCount.append(" and (agp.prizeName like '%").append(keyword).append("%'")
					.append(" or agi.gameName like '").append(keyword).append("%'")
					.append(" or sd.dictName like '").append(keyword).append("%')");
				}
			}
		}
		hql.append(" order by agp.gamePrizeId desc  ");
		
		return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
	}
	
}
