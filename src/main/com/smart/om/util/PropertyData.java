package com.smart.om.util;

import java.util.List;

/**
 * 
 * <p>
 * <h2>PropertyData类实现单个动态查询条件的封装</h2>
 * </p>
 * 
 * <p>
 * PropertyData类与Query类配合，可是生成复杂的查询条件。
 * PropertyData类包括：PO的属性名、参数值、关系运算符，通过getExp()函数得到
 * 形式如下的查询条件HQL子句：<br>
 * <pre>
 * o.id &gt;= ?
 * </pre>
 * 其中：
 * <li>o.id：表示别名为o的PO类的属性id，可以使用任何有效的PO属性</li>
 * <li>?：表示对应的参数占位符，在执行查询时，将实现动态参数绑定，被实际的参数值代替</li>
 * <li>&gt;=：表示查询条件的关系运算符，可以使用大部分常用的关系运算符，暂不支持
 * between/in等复杂关系运算符</li>
 * QueryData类通过getValue()函数能得到参数值，参数值的数据类型应该与对应属性的
 * 数据类型一致。
 * </p>
 *
 */
public final class PropertyData {

	//PO属性名称，包含PO的别名
	private String name;
	
	//关系运算符，如：">="
	private String operator;
	
	//PO属性参数值
	private Object value;
	
	//PO属性参数值
	private List values;
	
	//生成的HQL子句，如：o.id >= ?
	private String hql;
	
	private static final String isnull = "is null";
	private static final String equal = "=";
	private static final String param = "?";
	private static final String alias = "o" + ".";
	
	/**构建PropertyData实例，各属性为：Null，没有实际意义*/
	public PropertyData() {
	}
	
	/**用指定PO属性名称构建PropertyData实例，参数值为：Null，关系运算符为："is"。
	 * <p>
	 * 可生成形式如下的HQL子句：<pre>o.id is null</pre>，可在指定PO属性
	 * 名称前指定PO的别名，如果指定PO属性名称时，没有指定PO别名，则缺省别名
	 * 为："o"。
	 * </p>
	 */
	public PropertyData(String name) {
		this(name, null);
	}
	
	/**用指定PO属性名称和参数值构建PropertyData实例，关系运算符为："="。
	 * <p>
	 * 可生成形式如下的HQL子句：<pre>o.id = ?</pre>，可在指定PO属性名称
	 * 前指定PO的别名，如果指定PO属性名称时，没有指定PO别名，则缺省别名
	 * 为："o"。
	 * </p>
	 */
	public PropertyData(String name, Object value) {
		this(name, value, null);
	}
	
	/**用指定PO属性名称、参数值、关系运算符构建PropertyData实例。
	 * <p>
	 * 可生成形式如下的HQL子句：<pre>o.id &gt;= ?</pre>，可在指定PO属性名称
	 * 前指定PO的别名，如果指定PO属性名称时，没有指定PO别名，则缺省别名
	 * 为："o"。
	 * </p>
	 */
	public PropertyData(String name, Object value, String operator) {
		create(name, value, operator);
	}
	
	//用指定PO属性名称、参数值、关系运算符构建PropertyData实例
	private void create(String name, Object value, String operator) {
		if (name != null && name.length() > 0) {
			int p = name.indexOf(".");
			if (p <= 0) {
				this.name = alias + name;
			} else {
				this.name = name;
			}
			if (value == null) {
				this.value = null;
				this.operator = isnull;
			} else {
				this.value = value;
				if (operator != null && operator.length() > 0) {
					this.operator = operator.trim();
				} else {
					this.operator = equal;
				}
			}
		}
	}
	
	/**取得PO属性名称，如：o.id*/
	public String getName() {
		return name;
	}

	/**设置PO属性名称，如：o.id*/
	public void setName(String name) {
		this.name = name;
		hql = null;
	}

	/**取得关系运算符，如："&gt;="*/
	public String getOperator() {
		return operator;
	}

	/**设置关系运算符，如："&gt;="*/
	public void setOperator(String operator) {
		this.operator = operator;
		hql = null;
	}

	/**取得参数值，如：new Long(0)*/
	public Object getValue() {
		return value;
	}

	/**设置参数值，如：new Long(0)*/
	public void setValue(Object value) {
		this.value = value;
		hql = null;
	}

	/**取得参数值，多个值，用于In表达式*/
	public List getValues() {
		return values;
	}

	/**设置参数值，多个值，用于In表达式*/
	public void setValues(List values) {
		this.values = values;
	}

	/**将PropertyData转换成字符串，如：o.id=0*/
	public String toString() {
		if (value == null) {
			return name + operator;
		}
		return name + operator + value.toString();
	}
	
	/**取得带有占位符的查询条件HQL子句，如：<pre>o.id &gt;= ?</pre>*/
	public String getExp() {
		if (hql == null) {
			StringBuffer sb = null;
			if (name != null && name.length() > 0) {
				if (operator != null && operator.length() > 0) {
					sb = new StringBuffer();
					sb.append(name);
					sb.append(" ");
					sb.append(operator);
					if (value != null) {
						sb.append(" ");
						sb.append(param);
					}
				}
			}
			if (sb != null && sb.length() > 0) {
				hql = sb.toString();
			}
		}
		return hql;
	}
}


