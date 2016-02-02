package com.smart.om.web.car;

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

import com.smart.om.biz.car.CarHandler;
import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.persist.CarLine;
import com.smart.om.persist.CarLineNode;
import com.smart.om.persist.SysDict;
import com.smart.om.persist.SysUser;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.web.base.BaseAction;

/**
 * 车辆线路管理Action
 * @author lc
 *
 */   
@Namespace("/view/car/carLine")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class CarLineAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(CarLineAction.class);
	@Resource
	private CarHandler carHandler;
	
	private CarLine carLine;
	
	@Resource
    private SysAssistHandler sysAssistHandler;
	
	private List<CarLineNode> carLineNodeList;
	/**
	 * 查询所有线路信息
	 */
	@Action(value = "queryCarLine")
	public void queryCarLine() {
		SysUser sysUser = (SysUser) this.getSession().get("account");
		SysDict sysDict = sysAssistHandler.queryDictByPcode("AL_POSITION");
		try{
			//搜索关键字
			String keyword = this.getRequestParm().getParameter("keyword");
			String nodeId = this.getRequestParm().getParameter("id");
            String nodePid = this.getRequestParm().getParameter("pid");
            String level = this.getRequestParm().getParameter("level");
            String orgId = this.getRequestParm().getParameter("orgId");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			params.put("orgId", sysUser.getOrgId());
			 if(StringUtils.isNotBlank(orgId)){
	            	params.put("orgId", orgId);
	            }
			if (StringUtils.isNotBlank(nodePid)) {
            	if(sysDict != null){
                	if(Const.IS_REGION.equals(level)){//点击区域
                		params.put("dictRegionId", nodeId);
                	}else if(Const.IS_PROVICE.equals(level)){//点击省份
                		params.put("dictProviceId", nodeId);
                	}else if(Const.IS_ORG.equals(level)) {//点击服务站
                		params.put("dictOrgId", nodeId);
                	}
                }
            } 
			
			DTablePageModel dtPageModel = carHandler.queryCarLine(params, super.getPageData());
			String jsonData = JSONUtil.serialize(dtPageModel);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 /**
     * 线路详情
     */
    @Action(value = "queryCarLineById", results = {
            @Result(name = SUCCESS, location = "/view/car/carLineDetail.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String queryCarLineById() {
		String result = SUCCESS;
		try{
			String LineId = this.getRequestParm().getParameter("carLineId");
			 if (StringUtils.isNotBlank(LineId)) {
		            Integer carLineId = Integer.valueOf(LineId);
		            CarLine carLine = (CarLine) carHandler.queryCarLineById(carLineId);
		            List<CarLineNode> carLineNode = carHandler.queryNodeByCarLineId(carLineId);
		            getRequest().put("carLine", carLine);
		            getRequest().put("carLineNode", carLineNode);
		        }
		}catch (Exception e) {
			e.printStackTrace();
	        this.getRequest().put("where", " 查询所有数据字典");
	        this.getRequest().put("errors", "系统正忙，请联系管理员");
	        result = ERROR;
	    }
		return result;
    }
	

  
	/**
	 * 进入添加或编辑车辆线路信息页面
	 */
	@Action(value = "addOrModifyCarLine", results = {
			@Result(name = SUCCESS, location = "/view/car/carLineAdd.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String addOrModifyCarLine() {
		String result = SUCCESS;
		try{
			String LineId = this.getRequestParm().getParameter("carLineId");
			 if (StringUtils.isNotBlank(LineId)) {
		            Integer carLineId = Integer.valueOf(LineId);
		            CarLine carLine = (CarLine) carHandler.queryCarLineById(carLineId);
		            List<CarLineNode> carLineNode = carHandler.queryNodeByCarLineId(carLineId);
		            getRequest().put("carLine", carLine);
		            getRequest().put("carLineNode", carLineNode);
		        }
		}catch (Exception e) {
			e.printStackTrace();
	        this.getRequest().put("where", " 查询所有数据字典");
	        this.getRequest().put("errors", "系统正忙，请联系管理员");
	        result = ERROR;
	    }
		return result;
	}
	
	/**
	 * 保存或更新 线路与站点 信息
	 */
	@Action(value = "saveOrUpdateCarLine", results = {
			@Result(name = SUCCESS, location = "/view/car/carLineList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String saveOrUpdate() {
		String result = SUCCESS;
		try{
			Integer carLineId = carLine.getCarLineId();			
			if(carLineId == null) {
				carLine.setNewer(true);
			} 
			carLine.setAgentDate(DateUtil.getDateY_M_DH_M_S());
			carLine.setLineNo(DateUtil.getNo("C"));
			carLine.setIsDel(Const.IS_DEL_FALSE);
			CarLine cLine = (CarLine)carHandler.saveOrUpdateCarLine(carLine);
			Integer cId = cLine.getCarLineId();
			carHandler.updateLineId(cId);
			int index = 0;
			if(carLineNodeList.size() > 0){
				for (CarLineNode carLineNode : carLineNodeList) {
					if(carLineNode != null){
						index++;
						CarLineNode carLineNode1 = (CarLineNode)carHandler.queryCarlineNodeById(carLineNode.getLineNodeId());
						carLineNode1.setCarLineId(cId);
						carLineNode1.setNodeSort(index);
						carLineNode1.setNewer(false);
	                    carHandler.saveOrUpdateCarlineNode(carLineNode1);
					}					
                }				
			}
			result = SUCCESS;
		}catch (Exception e) {
			e.printStackTrace();
	        this.getRequest().put("where", " 查询所有数据字典");
	        this.getRequest().put("errors", "系统正忙，请联系管理员");
	        result = ERROR;
	    }
		return result;
	}	
	
	
	/**
	 * 删除车辆线路信息
	 */
	@Action(value = "delCarLineById", results = {
			@Result(name = SUCCESS, location = "/view/car/carLineList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String delCarLineById(){
		String result = SUCCESS;
		try{
			Integer id =Integer.valueOf(this.getRequestParm().getParameter("carLineId"));
			carHandler.updateLineId(id);
			carHandler.delCarLineById(id);
		}catch (Exception e) {
	        this.getRequest().put("where", " 查询所有数据字典");
	        this.getRequest().put("errors", "系统正忙，请联系管理员");
	        result = ERROR;
	    }
		return result;	
	}
	
	/**
	 * 查询譔线路下有多少个站点
	 */
	@Action(value = "queryCarlineNodeList", results = {
			@Result(name = SUCCESS, location = "/view/car/carMap.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String queryCarlineNodeList(){
		String result = SUCCESS;
		try{
			Integer id =Integer.valueOf(this.getRequestParm().getParameter("carLineId"));
			List<CarLineNode> nList = carHandler.queryNodeByCarLineId(id);
			
			String jsonData = JSONUtil.serialize(nList);
			getRequest().put("jsonData", jsonData);
		}catch (Exception e) {
			e.printStackTrace();
	        this.getRequest().put("where", " 查询所有数据字典");
	        this.getRequest().put("errors", "系统正忙，请联系管理员");
	        result = ERROR;
	    }
		return result;	
	}
	
	public CarLine getCarLine() {
		return carLine;
	}

	public void setCarLine(CarLine carLine) {
		this.carLine = carLine;
	}

	public List<CarLineNode> getCarLineNodeList() {
		return carLineNodeList;
	}

	public void setCarLineNodeList(List<CarLineNode> carLineNodeList) {
		this.carLineNodeList = carLineNodeList;
	}
	
}
