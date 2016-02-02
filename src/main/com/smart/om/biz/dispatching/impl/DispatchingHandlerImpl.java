package com.smart.om.biz.dispatching.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smart.om.biz.dispatching.DispatchingHandler;
import com.smart.om.dao.dispatching.DispatchingGoodsDAO;
import com.smart.om.dao.dispatching.DispatchingNodeDAO;
import com.smart.om.dao.dispatching.DispatchingPlanDAO;
import com.smart.om.persist.DispatchingGoods;
import com.smart.om.persist.DispatchingNode;
import com.smart.om.persist.DispatchingPlan;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

/**
 * 配送管理
 * langyuk
 */
@Component("DispatchingHandler")
public class DispatchingHandlerImpl implements DispatchingHandler {
	private static final Logger logger = Logger.getLogger(DispatchingHandlerImpl.class);
    @Autowired
    private DispatchingPlanDAO dispatchingPlanDAO;//配送计划
    @Autowired
    private DispatchingNodeDAO dispatchingNodeDAO;//配送站点
    @Autowired
    private DispatchingGoodsDAO dispatchingGoodsDAO;//配送商品    
    
    /**
	 * 分页查询配送计划
	 **/
    public DTablePageModel queryDispatchingPlanPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = dispatchingPlanDAO.queryDispatchingPlanPage(params, pageData);
        }
        return pageModel;
    }
    
    /**
     * 根据配送计划ID，查询配送计划
     */
    public DispatchingPlan queryPlanById(Integer planId){
    	return (DispatchingPlan)dispatchingPlanDAO.find(planId);
    }
    
    /**
     * 添加或修改配送计划
     */
    public DispatchingPlan saveOrUpdatePlan(DispatchingPlan dispatchingPlan){
    	return (DispatchingPlan)dispatchingPlanDAO.save(dispatchingPlan);
    }
    
    /**
     * 根据配送计划ID，查询配送站点
     */
    public List<DispatchingNode> queryNodeByPlans(Integer planId){
    	return dispatchingNodeDAO.queryNodeByPlans(planId);
    }
    
    /**
     * 根据站点ID，查询站点
     */
    public DispatchingNode queryNodeById(Integer dispatchingNodeId){
    	return (DispatchingNode)dispatchingNodeDAO.find(dispatchingNodeId);
    }
    
    /**
     * 添加或修改站点
     */
    public DispatchingNode saveOrUpdateNode(DispatchingNode dispatchingNode){
    	return (DispatchingNode)dispatchingNodeDAO.save(dispatchingNode);
    }
    
    /**
     * 根据配送计划ID，查询配送站点
     */
    public List<DispatchingGoods> queryGoodsByNode(Integer nodeId){
    	return dispatchingGoodsDAO.queryGoodsByNode(nodeId);
    }
}
