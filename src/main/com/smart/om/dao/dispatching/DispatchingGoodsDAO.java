package com.smart.om.dao.dispatching;

import java.util.List;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.DispatchingGoods;

/**
 * 配送商品
 * @author langyuk
 *
 */
public class DispatchingGoodsDAO extends BaseDao{
	/**
     * 根据配送计划ID，查询配送站点
     * @param planId
     * @return
     */
    public List<DispatchingGoods> queryGoodsByNode(Integer nodeId){
    	StringBuffer hql = new StringBuffer();
		hql.append(" from DispatchingGoods as model where 1=1 ");
		hql.append(" and model.nodeId = " + nodeId);
		List<DispatchingGoods> dispatchingGoodList = (List<DispatchingGoods>)this.find(hql.toString(), null);
    	return dispatchingGoodList;
    }
}
