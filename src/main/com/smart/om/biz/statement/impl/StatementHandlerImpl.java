package com.smart.om.biz.statement.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smart.om.biz.statement.StatementHandler;
import com.smart.om.dao.goods.GoodsInfoDAO;
import com.smart.om.dao.statement.StatementDAO;
import com.smart.om.persist.GoodsInfo;
import com.smart.om.persist.StatisticalStatement;

@Component("StatementHandler")
public class StatementHandlerImpl implements StatementHandler{
	@Autowired
    private StatementDAO statementDAO;
	@Autowired
    private GoodsInfoDAO goodsInfoDAO;
	/**
	 * 统计报表
	 * @param fristDay
	 * @param lastDay
	 */
	public void statisticalStatement(String fristDay, String lastDay) {
		List list = statementDAO.statisticalStatement(fristDay,lastDay);
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				Object[] obj  = (Object[])list.get(i);
				StatisticalStatement statisticalStatement = new StatisticalStatement();
				String [] strs = fristDay.split("-");
				statisticalStatement.setYear(strs[0]);
				statisticalStatement.setMonth(strs[1]);
				if(!"".equals(obj[0]) && null !=obj[0]){
					statisticalStatement.setDictOrgId(Integer.parseInt(obj[0].toString()));
				}
				if(!"".equals(obj[1]) && null !=obj[1]){
					statisticalStatement.setGoodsId(Integer.parseInt(obj[1].toString()));
				}else{
					//根据刀片名称查询刀片ID
	                GoodsInfo goodsInfo = goodsInfoDAO.getGoodsInfoByName("橙子");
					statisticalStatement.setGoodsId(goodsInfo.getGoodsId());
				}
				if(!"".equals(obj[2]) && null !=obj[2]){
					statisticalStatement.setSaleMoney(Double.parseDouble(obj[2].toString()));
				}
				statisticalStatement.setNewer(true);
				statementDAO.save(statisticalStatement);
			}
		}
	}
}
