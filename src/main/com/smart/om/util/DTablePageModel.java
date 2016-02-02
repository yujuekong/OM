package com.smart.om.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * JQuery DataTable 表格插件分页模型对象
 * @author huanglian
 *
 */
public class DTablePageModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3522503482604277069L;
	/**
	 *	表格列表内容对象
	 */
	private  List aaData;
	/**
	 * 总共多少条记录
	 */
	private int iTotalDisplayRecords;
	/**
	 * 总共多少条记录
	 */
	private int iTotalRecords;
	/**
	 * 页面请求次数
	 */
	private int sEcho = 1;
	
	public DTablePageModel() {
		
	}

	public DTablePageModel(List aaData,int iTotalRecords) {
		super();
		if(aaData == null) {
			aaData = new ArrayList();
		}
		this.aaData = aaData;
		this.iTotalDisplayRecords = iTotalRecords;
		this.iTotalRecords = iTotalRecords;
	}
	
	public DTablePageModel(List aaData,int iTotalRecords,int sEcho) {
		super();
		if(aaData == null) {
			aaData = new ArrayList();
		}
		this.aaData = aaData;
		this.iTotalDisplayRecords = iTotalRecords;
		this.iTotalRecords = iTotalRecords;
		this.sEcho = sEcho;
	}

	public List getAaData() {
		if(aaData == null) {
			aaData = new ArrayList();
		}
		return aaData;
	}

	public void setAaData(List aaData) {
		this.aaData = aaData;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getsEcho() {
		return sEcho;
	}

	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}
	
}
