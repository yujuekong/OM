package com.smart.om.dao.dispatching;

import java.util.List;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.DispatchingNode;

/**
 * 配送站点
 * @author langyuk
 *
 */
public class DispatchingNodeDAO extends BaseDao{
	/**
     * 根据配送计划ID，查询配送站点
     * @param planId
     * @return
     */
    public List<DispatchingNode> queryNodeByPlans(Integer planId){
    	StringBuffer hql = new StringBuffer();
		hql.append(" from DispatchingSubOrder as dso where 1=1 ");
		hql.append(" and dso.planId = " + planId);
		hql.append(" order by model.noSort ");
		List<DispatchingNode> nodeList = (List<DispatchingNode>)this.find(hql.toString(), null);
    	return nodeList;
    }
}
