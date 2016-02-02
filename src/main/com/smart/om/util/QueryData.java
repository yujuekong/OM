package com.smart.om.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 
 * <p>
 * <h2>QueryData类实现动态查询条件的封装</h2>
 * </p>
 * 
 * <p>
 * QueryData类通过聚合PropertyData类，将多个查询条件封装起来， 通过getHql()函数得到形式如下的查询条件HQL子句：<br>
 * 
 * <pre>
 * o.id = ? and o.name like ? and o.birthday &gt;= ?
 * </pre>
 * 
 * 其中：
 * <li>o.id/o.name/o.birthday：表示别名为o的PO类的属性id，可以使用任何有效的PO属性</li>
 * <li>?：表示对应的参数占位符，在执行查询时，将实现动态参数绑定，被实际的参数值代替</li>
 * <li>=/like/&gt;=：表示查询条件的关系运算符，可以使用大部分常用的关系运算符，暂不 支持between/in等复杂关系运算符</li>
 * <li>and：表示逻辑运算符，暂不支持or/not等逻辑运算符</li>
 * QueryData类通过getParams()函数能得到与查询条件中的参数一一对应的参数值数组，如：<br>
 * 
 * <pre>
 * Object[] {new Long(1), "X%", SysDate.getSysDate()}
 * </pre>
 * 
 * 其中：各个参数值的数据类型应该与对应属性的数据类型一致。
 * </p>
 * 
 */
public class QueryData {

	// 生成的查询条件
	private String hqls;

	// 生成的查询参数值
	private Object[] vals;

	// 封装的多个查询条件，如：o.id = ?
	private List query;

	// 封装的查询参数
	private List values;

	private int count = 0;

	// 查询条件中的逻辑运算符
	private static final String and = " and ";

	/** 构造QueryData实例 */
	public QueryData() {
	}

	/** 取得查询条件的个数 */
	public int size() {
		return count;
	}

	/** 清除所有的查询条件 */
	public void clear() {
		hqls = null;
		vals = null;
		count = 0;
		if (query != null) {
			query.clear();
		}
		if (values != null) {
			values.clear();
		}
	}

	/** 增加一个PropertyData封装的查询条件，包括：属性名，参数值，关系运算符，使用AND进行关联 */
	public void add(PropertyData property) {
		if (property == null) {
			return;
		}
		Object val = property.getValue();
		String con = property.getExp();
		if (con != null && con.length() > 0) {
			if (query == null) {
				query = new ArrayList();
			}
			if (values == null) {
				values = new ArrayList();
			}
			if (val != null) {
				values.add(val);
			}
			query.add(con);
			hqls = null;
			vals = null;
			count++;
		}
	}

	/** 取得查询条件HQL，其中包含参数占位符："?"，如：o.id = ? */
	public String getHql() {
		if (hqls == null) {
			if (query != null && query.size() > 0) {
				StringBuffer sb = new StringBuffer();
				Iterator it = query.iterator();
				while (it.hasNext()) {
					String con = (String) it.next();
					if (con != null && con.length() > 0) {
						sb.append(con + and);
					}
				}
				if (sb.length() > 0) {
					sb.delete(sb.length() - and.length(), sb.length());
				}
				hqls = sb.toString();
			}
		}
		return hqls;
	}

	/** 取得查询参数值，如：Object[] {new Long(1)} */
	public Object[] getParams() {
		if (vals == null) {
			if (values != null && values.size() > 0) {
				vals = values.toArray();
			}
		}
		return vals;
	}

}
