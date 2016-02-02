package com.smart.om.dao.statement;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.smart.om.dao.base.BaseDao;

public class StatementDAO extends BaseDao{
	/**
	 * 统计报表
	 * @param fristDay
	 * @param lastDay
	 */
	public List statisticalStatement(String fristDay, String lastDay) {
		StringBuffer hql = new StringBuffer();
		hql.append("select di.dict_org_id,si.GOODS_ID,sum(si.SALE_MONEY) from DEVICE_INFO di left join SALE_INFO si on  si.device_id = di.DEVICE_NO and  ");
		hql.append("EXISTS  ( select device_id from SALE_INFO where  sale_date >='"+fristDay+"' and sale_date<='"+lastDay+"' group by device_id )   group by di.dict_org_id,si.GOODS_ID ");
		hql.append("UNION ");
		hql.append("select di.dict_org_id,acr.goods_id,sum(acr.consume_money) from  DEVICE_INFO di join APP_CONSUME_RECORD acr on acr.DEVICE_ID = di.DEVICE_ID and acr.DEVICE_ID is not null ");
		hql.append("and acr.CONSUME_TIME>='"+fristDay+"' and acr.CONSUME_TIME<='"+lastDay+"' ");
		hql.append("group by di.dict_org_id,acr.goods_id ");
		Session session = null;
		List<Object[]> list = new ArrayList();
		try{
			session = this.getSession();
			Query query = session.createSQLQuery(hql.toString());
			list = query.list();
		}catch(Exception e){
			session.close();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return list;
	}
}
