package com.smart.om.web.sale;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import com.smart.om.biz.sale.SaleHandler;
import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.persist.ActivityGameInfo;
import com.smart.om.persist.ActivityGamePrize;
import com.smart.om.persist.SysDict;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.web.base.BaseAction;
/**
 * 抽奖活动Action
 * @author CA
 *
 */
@Namespace("/view/sale/gameInfo")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class GameInfoAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(GameInfoAction.class);
	
	@Resource
	private SaleHandler saleHandler;
	@Resource
	private SysAssistHandler sysAssistHandler;
	
	private ActivityGameInfo activityGameInfo;
	
	private ActivityGamePrize activityGamePrize;
	
	@Action(value="queryGameInfoPage")
	public void queryGameInfoPage(){
        try {
            //搜索关键字
        	logger.info("queryGameInfoPage");
//            String keyword = this.getRequestParm().getParameter("keyword");
            String gameStatus = this.getRequestParm().getParameter("gameStatus");
            String keyword2 = this.getRequestParm().getParameter("type_keyword");
            Map<String, Object> params = new HashMap<String, Object>();
//            if(StringUtils.isNotBlank(keyword)){
//            	params.put("keyword",keyword);
//            }
            if(StringUtils.isNotBlank(gameStatus)){
            	params.put("gameStatus",gameStatus);
            }
            if(StringUtils.isNotBlank(keyword2)){
            	params.put("keyword2",keyword2);
            }
//            params.put("keyword", keyword);
            DTablePageModel dtPageModel = saleHandler.queryGameInfoPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * 查询活动结果页面
	 */
	@Action(value="querymemberGamePage")
	public void querymemberGamePage(){
        try {
            //搜索关键字
        	logger.info("querymemberGamePage");
            String keyword = this.getRequestParm().getParameter("keyword");
            Map<String, Object> params = new HashMap<String, Object>();
            if(StringUtils.isNotBlank(keyword)){
            	params.put("keyword", keyword);
            }
            params.put("keyword", keyword);
            DTablePageModel dtPageModel = saleHandler.querymemberGamePage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * 保存活动信息
	 * @return
	 */
	@Action(value="saveOrUpdateGameInfo",results={
			@Result(name=SUCCESS,location="/view/sale/gameInfoList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String saveOrUpdateGameInfo(){
		try{
			if(activityGameInfo.getGameId() == null){
				activityGameInfo.setGameStatus(Const.IS_VALID_FALSE);
				ActivityGameInfo activityGame = saleHandler.saveOrUpdateGameInfo(activityGameInfo);
			}
			else{
				saleHandler.saveOrUpdateGameInfo(activityGameInfo);
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "保存失败，请重试...");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询活动信息
	 * @return
	 */
	@Action(value="queryGameInfoDt",results={
			@Result(name=SUCCESS,location="/view/sale/gameInfoDetail.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String queryGameInfoDt(){
		try{
			String gameId = this.getRequestParm().getParameter("gameId");
			if(StringUtils.isNotBlank(gameId)){
				ActivityGameInfo activityGameInfo = saleHandler.queryGameInfoById(Integer.valueOf(gameId));
				if(activityGameInfo != null){
					this.getRequest().put("activityGameInfo", activityGameInfo);
				}
				if(activityGamePrize != null){
					this.getRequest().put("activityGamePrize", activityGamePrize);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "查询失败，请联系管理员....");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 增加或修改活动信息页面跳转
	 * @return
	 */
	@Action(value="addOrModifyGameInfo",results={
			@Result(name=SUCCESS,location="/view/sale/gameInfoAdd.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String addOrModifyGameInfo(){
		try{
			String gameId = this.getRequestParm().getParameter("gameId");
			if(StringUtils.isNotBlank(gameId)){
				ActivityGameInfo activityGameInfo = saleHandler.queryGameInfoById(Integer.valueOf(gameId));
				if(activityGameInfo != null){
					this.getRequest().put("activityGameInfo", activityGameInfo);
				}
				if(activityGameInfo.getPayType() != null){
					this.getRequest().put("payType", activityGameInfo.getPayType());
				}
				List<SysDict> sysDictList = sysAssistHandler.querySubDictByPcode(Const.ACTIVITY_PAY);
				if(sysDictList != null && sysDictList.size() > 0 ){
					this.getRequest().put("sysDictList", sysDictList);
				}
			}
			else{
				List<SysDict> sysDictList = sysAssistHandler.querySubDictByPcode(Const.ACTIVITY_PAY);
				if(sysDictList != null && sysDictList.size() > 0 ){
					this.getRequest().put("sysDictList", sysDictList);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请联系管理员....");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 增加或修改奖励页面
	 */
	@Action(value="addOrModifyGamePrize", results={
			@Result(name=SUCCESS,location="/view/sale/gamePrizeAdd.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String addOrModifyGamePrize(){
		try{
			String prizeId = this.getRequestParm().getParameter("prizeId");
			if(StringUtils.isNotBlank(prizeId)){
				
			}
			else{
				String gameId = this.getRequestParm().getParameter("gameId");
				List<SysDict> sysDictList = sysAssistHandler.querySubDictByPcode(Const.ACTIVITY_PRIZE);
				if(sysDictList != null && sysDictList.size() > 0 ){
					this.getRequest().put("sysDictList", sysDictList);
				}
				if(StringUtils.isNotBlank(gameId)){
					this.getRequest().put("gameId", gameId);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统正忙，请联系管理员...");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除活动信息
	 * @return
	 */
	@Action(value="deleteGameInfo",results={
			@Result(name=SUCCESS,location="/view/sale/gameInfoList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String deleteGameInfo(){
		try{
			String gameId = this.getRequestParm().getParameter("gameId");
			if(StringUtils.isNotBlank(gameId)){
				List<ActivityGamePrize> prizeList = saleHandler.queryActivityPrizeByGameId(Integer.valueOf(gameId));
				saleHandler.deleteGameInfoById(Integer.valueOf(gameId));
				if(prizeList != null){
					for(ActivityGamePrize activityGamePrize : prizeList){
						saleHandler.deleteGamePrize(activityGamePrize);
					}
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "删除失败，请重试....");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改活动状态
	 * @return
	 */
	@Action(value="changeGameStatus",results={
			@Result(name=SUCCESS,location="/view/sale/gameInfoList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String changeGameStatus(){
		try{
			String gameId = this.getRequestParm().getParameter("gameId");
			if(StringUtils.isNotBlank(gameId)){
				ActivityGameInfo activityGameInfo = saleHandler.queryGameInfoById(Integer.valueOf(gameId));
				if(Const.IS_VALID_FALSE.equals(activityGameInfo.getGameStatus())){
					activityGameInfo.setGameStatus(Const.IS_VALID_TRUE);
				}
				else{
					activityGameInfo.setGameStatus(Const.IS_VALID_FALSE);
				}
				saleHandler.saveOrUpdateGameInfo(activityGameInfo);
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "状态修改失败，请重试...");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 进入活动奖励管理页面
	 * @return
	 */
	@Action(value="enterGamePrizePage",results={
			@Result(name=SUCCESS,location="/view/sale/gamePrizeList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String enterGamePrizePage(){
		try{
			String gameId = this.getRequestParm().getParameter("gameId");
			logger.info("gameId:" + gameId);
			if(StringUtils.isNotBlank(gameId)){
				this.getRequest().put("gameId",Integer.valueOf(gameId));
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "进入失败，请重试......");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询活动奖励分页信息
	 * @return
	 */
	@Action("queryGamePrizePage")
	public void queryGamePrizePage(){
		 try {
	            //搜索关键字
	        	logger.info("queryGamePrizePage");
	            String gameId = this.getRequestParm().getParameter("gameId");
	            String keyword = this.getRequestParm().getParameter("keyword");
	            Map<String, Object> params = new HashMap<String, Object>();
	            if(StringUtils.isNotBlank(gameId)){
	            	params.put("gameId", gameId);
	            }
	            if(StringUtils.isNotBlank(keyword)){
	            	params.put("keyword", keyword);
	            }
	            DTablePageModel dtPageModel = saleHandler.queryGamePrizePage(params, super.getPageData());
	            String jsonData = JSONUtil.serialize(dtPageModel);
	            PrintWriter pw = super.getResponse().getWriter();
	            pw.write(jsonData);
	            pw.flush();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	/**
	 * 保存游戏奖励
	 * @return 
	 */
	@Action(value="addGamePrize",results={
			@Result(name=SUCCESS,location="/view/sale/gamePrizeList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String addGamePrize(){
		try{
			String gameId = this.getRequestParm().getParameter("gameId");
			if(StringUtils.isNotBlank(gameId)){
				activityGamePrize.setGameId(Integer.valueOf(gameId));
				saleHandler.saveOrupdateGamePrize(activityGamePrize);
				this.getRequest().put("gameId", gameId);
			}
			else{
				this.getRequest().put("errors", "保存失败，请重试....");
				return ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "保存失败，请重试....");
			return ERROR;
		}
		return SUCCESS;
	}
	/**
	 * 保存游戏奖励页面跳转
	 */
	@Action(value="returnGamePrizePage",results={
			@Result(name=SUCCESS,location="/view/sale/gamePrizeList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String returnGamePrizePage(){
		try{
			String gameId = this.getRequestParm().getParameter("gameId");
			if(StringUtils.isNotBlank(gameId)){
				this.getRequest().put("gameId", gameId);
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统正忙，请重试...");
			return ERROR;
		}
		return SUCCESS;
	}
	/**
	 * 删除游戏奖励
	 * @return
	 */
	@Action(value="deleteGamePrize",results={
			@Result(name=SUCCESS,location="/view/sale/gamePrizeList.jsp"),
			@Result(name=ERROR,location="error.jsp")
	})
	public String deleteGamePrize(){
		try{
			String gamePrizeId = this.getRequestParm().getParameter("gamePrizeId");
			if(StringUtils.isNotBlank(gamePrizeId)){
				ActivityGamePrize gamePrize = saleHandler.queryGamePrizeById(Integer.valueOf(gamePrizeId));
				this.getRequest().put("gameId", gamePrize.getGameId());
				saleHandler.deleteGamePrize(gamePrize);
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "删除失败，请重试....");
			return ERROR;
		}
		return SUCCESS;
		
	}
	
	public ActivityGameInfo getActivityGameInfo() {
		return activityGameInfo;
	}

	public void setActivityGameInfo(ActivityGameInfo activityGameInfo) {
		this.activityGameInfo = activityGameInfo;
	}

	public ActivityGamePrize getActivityGamePrize() {
		return activityGamePrize;
	}

	public void setActivityGamePrize(ActivityGamePrize activityGamePrize) {
		this.activityGamePrize = activityGamePrize;
	}
	
	
	
}
