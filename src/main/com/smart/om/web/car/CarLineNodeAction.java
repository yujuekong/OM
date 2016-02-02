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
import com.smart.om.persist.CarLineNode;
import com.smart.om.persist.SysDict;
import com.smart.om.persist.SysUser;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.web.base.BaseAction;

/**
 * 车辆站点管理Action
 * @author lc
 *
 */
@Namespace("/view/car/carLineNode")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class CarLineNodeAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(CarLineNodeAction.class);
	@Resource
	private CarHandler carHandler;
	@Resource
    private SysAssistHandler sysAssistHandler;
	
	private CarLineNode carLineNode;
	
	public CarLineNode getCarLineNode() {
		return carLineNode;
	}
	public void setCarLineNode(CarLineNode carLineNode) {
		this.carLineNode = carLineNode;
	}



	/**
	 * 查询所有线路节点信息
	 */
	@Action(value = "querycarLineNode")
	public void querycarLineNode() {
		SysUser sysUser = (SysUser) this.getSession().get("account");
		SysDict sysDict = sysAssistHandler.queryDictByPcode("AL_POSITION");
		try{
			//搜索关键字
			String keyword = this.getRequestParm().getParameter("keyword");
			String nodeId = this.getRequestParm().getParameter("id");
            String nodePid = this.getRequestParm().getParameter("pid");
            String level = this.getRequestParm().getParameter("level");
            String carLineId = this.getRequestParm().getParameter("carLineId");
            String isNull = this.getRequestParm().getParameter("isNull");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			params.put("orgId", sysUser.getOrgId());
			
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
			if (StringUtils.isNotBlank(isNull)) {
                params.put("isNull", isNull);
            } 
			if (StringUtils.isNotBlank(carLineId)) {
                params.put("carLineId", carLineId);
            } 
			DTablePageModel dtPageModel = carHandler.querycarLineNode(params, super.getPageData());
			String jsonData = JSONUtil.serialize(dtPageModel);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 进入添加或编辑车辆线路节点信息页面
	 */
	@Action(value = "addOrModifyCarlineNode", results = {
	            @Result(name = SUCCESS, location = "/view/car/carLineNodeAdd.jsp"),
	    })
	public String addOrModifyCarlineNode() {

		String result = SUCCESS;
		try{
			String lineNodeId = this.getRequestParm().getParameter("lineNodeId");
			if(StringUtils.isNotBlank(lineNodeId)){
			    Integer id = Integer.valueOf(lineNodeId);
			    CarLineNode carLineNode = (CarLineNode) carHandler.queryCarlineNodeById(id);
			    getRequest().put("carLineNode", carLineNode);
			}
		}catch (Exception e) {
	        this.getRequest().put("where", " 查询所有数据字典");
	        this.getRequest().put("errors", "系统正忙，请联系管理员");
	        result = ERROR;
	    }
		return result;
	}

	/**
	 * 添加或修改车辆线路节点信息
	 */
	 @Action(value = "saveOrUpdateCarlineNode", results = {
	            @Result(name = SUCCESS, location = "/view/car/carLineNodeList.jsp"),
	            @Result(name = ERROR, location = "/view/error.jsp")})
	 public String saveOrUpdateCarlineNode() {
		 String result = SUCCESS;
			try{
		        Integer lineNodeId = carLineNode.getLineNodeId();
		        if (lineNodeId == null) {
		        	carLineNode.setNewer(true);
		        	carLineNode.setNodeNo(DateUtil.getNo("X"));
		        }
		        carLineNode.setIsDel(Const.IS_DEL_FALSE);
		        carHandler.saveOrUpdateCarlineNode(carLineNode);
			}catch (Exception e) {
	            this.getRequest().put("where", " 查询所有数据字典");
	            this.getRequest().put("errors", "系统正忙，请联系管理员");
	            result = ERROR;
	        }
		return result;	
	 }
	
	/**
	 * 删除车辆线路线路节点信息
	 */
	@Action(value = "deleteCarlineNode", results = {
			@Result(name = SUCCESS, location = "/view/car/carLineNodeList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String deleteCarlineNode(){
		Integer NodeId =Integer.valueOf(this.getRequestParm().getParameter("lineNodeId"));
		carHandler.deleteCarlineNode(NodeId);
		return SUCCESS;	
	}
	
	 /**
     * 站点详情
     */
    @Action(value = "queryCarlineNodeById", results = {
            @Result(name = SUCCESS, location = "/view/car/carLineNodeDetail.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String queryCarlineNodeById() {
        String result = null;
        Integer lineNodeId = Integer.valueOf(this.getRequestParm().getParameter("lineNodeId"));
        if (lineNodeId != null) {
        	CarLineNode carLineNode = (CarLineNode) carHandler.queryCarlineNodeById(lineNodeId);
            getRequest().put("carLineNode", carLineNode);
            result = SUCCESS;
        }
        return result;
    }
	
	/**
	 * 查询线路下的的所有站点
	 */
	@Action(value = "queryNodeByCarLineId", results = {
			@Result(name = SUCCESS, location = "/view/car/carMap.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String queryNodeByCarLineId() {
		 String carLineId = this.getRequestParm().getParameter("carLineId");
		try {
			Integer lineId = Integer.valueOf(carLineId);
			List<CarLineNode> cLineNode = (List<CarLineNode>) carHandler.queryNodeByCarLineId(lineId);
			String jsonData = JSONUtil.serialize(cLineNode);
			getRequest().put("jsonData", jsonData);
		} catch (Exception e) {
		}
		return SUCCESS;
	}	
	/**
	 * 查询站点下是否有商圈
	 */
	@Action(value = "queryNodeByDistrictId")
	public void queryNodeByDistrictId() {
		String districtId = this.getRequestParm().getParameter("districtId");
		Integer nodePostionId = Integer.valueOf(districtId);
		try {
			List<CarLineNode> nodeList = (List<CarLineNode>) carHandler.queryNodeByDistrictId(nodePostionId);
			String jsonData = JSONUtil.serialize(nodeList);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
