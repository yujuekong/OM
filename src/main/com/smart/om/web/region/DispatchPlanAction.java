package com.smart.om.web.region;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import com.smart.om.biz.region.RegionHandler;
import com.smart.om.dto.device.DeviceCarLineNodeDto;
import com.smart.om.persist.DispatchingGoods;
import com.smart.om.persist.DispatchingNode;
import com.smart.om.persist.DispatchingPlan;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.web.base.BaseAction;

/**
 * 设备类型管理Action
 * @author lc
 *
 */
@Namespace("/view/region/dispatch")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class DispatchPlanAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(DispatchPlanAction.class);
	@Resource
	private RegionHandler regionHandler;
	
	private DispatchingPlan dispatchingPlan;
	
	private List<DispatchingGoods> goodsList;
	
	public DispatchingPlan getDispatchingPlan() {
		return dispatchingPlan;
	}

	public void setDispatchingPlan(DispatchingPlan dispatchingPlan) {
		this.dispatchingPlan = dispatchingPlan;
	}

	/**
	 * 查询所有配送计划
	 */
	@Action(value = "queryDispatchPlan")
	public void queryDispatchPlan() {
		try{
			//搜索关键字
			String keyword = this.getRequestParm().getParameter("keyword");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			DTablePageModel dtPageModel = regionHandler.queryDispatchPlan(params, super.getPageData());
			String jsonData = JSONUtil.serialize(dtPageModel);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 修改配送计划状态
	 * @return
	 * @throws IOException
	 */
	@Action(value="changeStatus", results = {
			@Result(name = SUCCESS, location = "/view/region/dispatchPlanList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String changeStatus() throws IOException {
		String result = SUCCESS;
		try {
			String planId = this.getRequestParm().getParameter("planId");
			String isDisable = this.getRequestParm().getParameter("isDisable");
			DispatchingPlan dPlan = (DispatchingPlan)regionHandler.queryDispatchPlanById(Integer.valueOf(planId));
			dPlan.setPlanStatus(isDisable);
			dPlan.setNewer(false);
			regionHandler.saveOrUpdateDispatchPlan(dPlan);
			this.getResponse().getWriter().print(true);
		} catch (Exception e) {
			e.printStackTrace();
			this.getResponse().getWriter().print(false);
			result = ERROR;
		}
		return result;
	}
	
	
	
	/**
	 * 进入添加或编辑配送计划页面
	 */
	@Action(value = "addOrModifydispatchPlan", results = {
			@Result(name = SUCCESS, location = "/view/region/dispatchPlanAdd.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String addOrModifyServiceSeller() {
		String result = SUCCESS;
		try{
			String planId = this.getRequestParm().getParameter("planId");
			if(StringUtils.isNotBlank(planId)){
				Integer dId =Integer.valueOf(planId);
				DispatchingPlan dPlan = (DispatchingPlan) regionHandler.queryDispatchPlanById(dId);
				//getRequest().put("dispatchingPlan", dPlan);
			}
		}catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}
	
	/**
	 * 添加或修改配送计划信息
	 */
	@Action(value = "saveOrUpdateDispatchPlan", results = {
			@Result(name = SUCCESS, location = "/view/region/dispatchPlanList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String saveOrUpdateDispatchPlan(){
		String result = SUCCESS;
		try{
			Integer planId =  dispatchingPlan.getPlanId();
			if(planId == null ){
				dispatchingPlan.setNewer(true);
				dispatchingPlan.setPlanNo(DateUtil.getNo("DP"));
				dispatchingPlan.setPlanDate(DateUtil.getDateY_M_DH_M_S());
				dispatchingPlan.setPlanStatus(Const.IS_VALID_TRUE);
			}
			DispatchingPlan dPlan =  regionHandler.saveOrUpdateDispatchPlan(dispatchingPlan);
			Integer dPlanId = dPlan.getPlanId();
			List<DispatchingGoods> gList = new ArrayList<DispatchingGoods>();
			gList = goodsList;
			if(goodsList.size() > 0){
				for (DispatchingGoods dispatchingGoods : goodsList) {
					if(dispatchingGoods != null){
						dispatchingGoods.setNewer(true);
						regionHandler.saveOrUpdateDispatchGoods(dispatchingGoods);
					}					
                }
			   for(int i = 0;i<gList.size()-1;i++){ 
				    for(int j = gList.size()-1;j>i;j--){ 
				    	if(gList.get(j).getNodeId().equals(gList.get(i).getNodeId())){
				    		gList.remove(gList.get(j));
				    	} 
				    } 
			   }
			   if(gList.size() > 0){
				   for (DispatchingGoods dispatchingGoods : gList) {
						if(dispatchingGoods != null){
							DispatchingNode dispatchingNode = new DispatchingNode();
							dispatchingNode.setPlanId(dPlanId);
							dispatchingNode.setNewer(true);
							dispatchingNode.setIsFinish(Const.IS_STATUS_INIT);
							dispatchingNode.setLineNodeId(dispatchingGoods.getNodeId());
							regionHandler.saveOrUpdateDispatchNode(dispatchingNode);
						}					
	                }
			   }

			}
		}catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}
	/**
	 * 删除配送计划
	 */
	@Action(value = "delDispatchPlanById", results = {
			@Result(name = SUCCESS, location = "/view/region/dispatchPlanList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String delDispatchPlanById(){
		String result = SUCCESS;
		try{
			Integer planId =Integer.valueOf(this.getRequestParm().getParameter("planId"));
			//DispatchingPlan mDistrict = (DispatchingPlan) regionHandler.queryDispatchPlanById(districtId);
			//mDistrict.setIsDel(Const.IS_DEL_TRUE);
			//mDistrict.setNewer(false);
			regionHandler.delDispatchPlanById(planId);
		}catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}
	
	/**
	 * 根据配送计划ID进入配送计划页面
	 */
	@Action(value = "dispatchPlanDtl", results = {
			@Result(name = SUCCESS, location = "/view/region/dispatchPlanDtl.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String dispatchPlanDtl() {
		String result = SUCCESS;
		try{
			Integer planId =Integer.valueOf(this.getRequestParm().getParameter("planId"));
			DispatchingPlan dispatchingPlan = (DispatchingPlan) regionHandler.queryDispatchPlanById(planId);
			getRequest().put("dispatchingPlan", dispatchingPlan);
		}catch (Exception e) {
			e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}

	/**
	 * 查询某些配送线路下的所有设备
	 * 
	 * 实现方法:先查到该线路下有多少个站点,再根据站点获取站点所在的商圈,在设备列表根据商圈ID查询设备
	 */
	@Action(value = "queryDeviceByCarLine")
	public void queryDeviceByCarLine() {
		try {
			String cId = this.getRequestParm().getParameter("carLineId");
			Integer carLineId =Integer.valueOf(cId);
			List<DeviceCarLineNodeDto> dInfoList = (List<DeviceCarLineNodeDto>) regionHandler.queryDeviceByCarLine(carLineId);
			String jsonData = JSONUtil.serialize(dInfoList);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public List<DispatchingGoods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<DispatchingGoods> goodsList) {
		this.goodsList = goodsList;
	}
}
