package com.smart.om.util;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public final class SessionQuery implements HibernateCallback {
	private String hqlQuery;
	// 查询用的参数
	private Object[] params;

	// 查询总行数的HQL
	private String hqlCount;

	// 分页查询信息
	private PageData page;

	public SessionQuery(String hqlQuery, Object[] params) {
		this.hqlQuery = hqlQuery;
		this.params = params;
	}

	public SessionQuery(String hqlQuery, String hqlCount, PageData page,
			Object[] params) {
		 if (page != null) {
//			 System.out.println("cupage12:"+page.getCurrentPage());
//			 System.out.println("pageSize:"+page.getPageSize());
		 }
		this.hqlQuery = hqlQuery;
		this.hqlCount = hqlCount;
		this.page = page;
		this.params = params;

	}

	/***/
	public Object doInHibernate(Session session) throws HibernateException,
			SQLException {
		if (page != null && page.isFirstPage()) {
			// page.setRowCount(this.queryCount(session));
		}
		return this.queryData(session);
	}

	private List queryData(Session session) throws HibernateException {
		if (hqlQuery != null && hqlQuery.length() > 0) {
			Query query = session.createQuery(hqlQuery);
			if (page != null) {
//				 System.out.println("cupage:"+page.getCurrentPage());
//				 System.out.println("pageSize:"+page.getPageSize());
				int firstRow = (page.getCurrentPage() - 1) * page.getPageSize();
//				int firstRow = page.getCurrentPage()-1;
//				 System.out.println("firstRow:"+firstRow);
				int maxRow = page.getPageSize();
				query.setFirstResult(firstRow);
				query.setMaxResults(maxRow);
			}
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			List list = query.list();
			if (list == null || list.size() <= 0) {
				return null;
			} else {
				return list;
			}
		}
		return null;
	}

	// 根据hqlCount查询总行数
	private int queryCount(Session session) throws HibernateException,
			SQLException {
		if (hqlCount != null && hqlCount.length() > 0) {
			Query query = session.createQuery(hqlCount);
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			Long cnt = (Long) query.uniqueResult();
			if (cnt != null) {
				return cnt.intValue();
			}
		}
		return 0;
	}
}
