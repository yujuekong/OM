package com.smart.om.util;

import java.util.List;

/**
 * 
* 类功能说明：分页模型
* 类修改者	修改日期
* 修改说明
* <p>Title: PageModel.java</p>
* @author Asher
* @date 2013-2-26 上午09:17:04
* @version V2.2
 */
public class PageModel {
	private int total;//总记录数
	private List datas; //当前页的记录集
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}
}
