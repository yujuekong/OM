package com.smart.om.web.sale;

import java.io.PrintWriter;
import java.util.HashMap;
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
import com.smart.om.persist.ActivityIntegrlBill;
import com.smart.om.persist.ActivityMemberGame;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.web.base.BaseAction;

/**
 * 积分活动管理Action
 * @author CA
 *
 */
@Namespace("/view/sale/integral")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class IntegralAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(IntegralAction.class);
	
	@Resource
	private SaleHandler saleHandler;
	
	private ActivityIntegrlBill activityIntegrlBill;
	
	private ActivityMemberGame activityMemberGame;
	
	@Action(value="queryIntegralPage")
	public void queryIntegralPage(){
        try {
            //搜索关键字
        	logger.info("queryIntegralPage");
            String keyword = this.getRequestParm().getParameter("keyword");
            Map<String, Object> params = new HashMap<String, Object>();
            if(StringUtils.isNotBlank(keyword)){
            	params.put("keyword", keyword);
            }
            params.put("keyword", keyword);
            DTablePageModel dtPageModel = saleHandler.queryIntegralPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	/**
	 * 积分活动保存
	 * @return
	 */
	@Action(value="saveOrUpdateIntegral",results={
			@Result(name=SUCCESS,location="/view/sale/integralList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String saveOrUpdateIntegral(){
		try{
			if(activityIntegrlBill.getIntegrlBillId() == null){
				activityIntegrlBill.setExchangeTime(DateUtil.getDateY_M_DH_M_S());
				saleHandler.saveOrUpdateIntegral(activityIntegrlBill);
			}
			else{
				saleHandler.saveOrUpdateIntegral(activityIntegrlBill);
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "保存失败，请重试....");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 积分活动修改和保存页面跳转
	 * @return
	 */
	@Action(value="addOrModifyIntegral",results={
			@Result(name=SUCCESS,location="/view/sale/integralAdd.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String addOrModifyIntegral(){
		try{
			String integrlBillId = this.getRequestParm().getParameter("integrlBillId");
			if(StringUtils.isNotBlank(integrlBillId)){
				 ActivityIntegrlBill activityIntegrlBill = saleHandler.queryIntegralBillById(Integer.valueOf(integrlBillId));
				 this.getRequest().put("activityIntegrlBill", activityIntegrlBill);
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试...");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除积分活动
	 * @return
	 */
	@Action(value="deleteIntegral",results={
			@Result(name=SUCCESS,location="/view/sale/integralList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String deleteIntegral(){
		try{
			String integrlBillId = this.getRequestParm().getParameter("integrlBillId"); 
			if(StringUtils.isNotBlank(integrlBillId)){
				saleHandler.deleteIntegralById(Integer.valueOf(integrlBillId));
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "删除失败，请联系管理员...");
			return ERROR;
		}
		return SUCCESS;
	}
	
	public ActivityIntegrlBill getActivityIntegrlBill() {
		return activityIntegrlBill;
	}

	public void setActivityIntegrlBill(ActivityIntegrlBill activityIntegrlBill) {
		this.activityIntegrlBill = activityIntegrlBill;
	}

	public ActivityMemberGame getActivityMemberGame() {
		return activityMemberGame;
	}

	public void setActivityMemberGame(ActivityMemberGame activityMemberGame) {
		this.activityMemberGame = activityMemberGame;
	}
	
	
	
}
