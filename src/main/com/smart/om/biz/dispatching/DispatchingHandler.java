package com.smart.om.biz.dispatching;

import java.util.List;
import java.util.Map;

import com.smart.om.persist.DispatchingGoods;
import com.smart.om.persist.DispatchingNode;
import com.smart.om.persist.DispatchingPlan;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

public interface DispatchingHandler {

	/**
	 * 分页查询配送计划
	 **/
	public DTablePageModel queryDispatchingPlanPage(Map<String, Object> params, PageData pageData);

	/**
     * 根据配送计划ID，查询配送计划
     */
    public DispatchingPlan queryPlanById(Integer planId);
    
    /**
     * 添加或修改配送计划
     */
    public DispatchingPlan saveOrUpdatePlan(DispatchingPlan dispatchingPlan);
    
	/**
     * 根据配送计划ID，查询配送站点
     */
    public List<DispatchingNode> queryNodeByPlans(Integer planId);
    
    /**
     * 根据站点ID，查询站点
     */
    public DispatchingNode queryNodeById(Integer dispatchingNodeId);
    
    /**
     * 添加或修改站点
     */
    public DispatchingNode saveOrUpdateNode(DispatchingNode dispatchingNode);
    
    /**
     * 根据配送计划ID，查询配送站点
     */
    public List<DispatchingGoods> queryGoodsByNode(Integer nodeId);
    
    
}