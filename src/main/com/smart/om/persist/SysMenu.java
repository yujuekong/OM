package com.smart.om.persist;

import java.util.ArrayList;
import java.util.List;

import com.smart.om.dao.base.BasePo;

/**
 * SysMenu entity. @author MyEclipse Persistence Tools
 */

public class SysMenu extends BasePo implements Comparable<SysMenu>{

	// Fields

	private Integer menuId;
	private Integer menuPid;
	private SysMenu sysPmenu;
	private String menuName;
	private String menuUrl;
	private Integer menuSort;
	private String menuIcon;
	private String menuCss;
	private Integer menuType;
	private Integer menuLevel;
	private String remark;
	private String isDel;
	private List<SysMenu> sysMenulist = new ArrayList<SysMenu>();
	// Constructors

	/** default constructor */
	public SysMenu() {
	}

	/** full constructor */
	public SysMenu(Integer menuPid, String menuName, String menuUrl,
			Integer menuSort, String menuIcon, String menuCss,
			Integer menuType, Integer menuLevel, String remark, String isDel) {
		this.menuPid = menuPid;
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.menuSort = menuSort;
		this.menuIcon = menuIcon;
		this.menuCss = menuCss;
		this.menuType = menuType;
		this.menuLevel = menuLevel;
		this.remark = remark;
		this.isDel = isDel;
	}

	// Property accessors

	public Integer getMenuId() {
		return this.menuId;
	}

	public SysMenu getSysPmenu() {
		return sysPmenu;
	}

	public void setSysPmenu(SysMenu sysPmenu) {
		this.sysPmenu = sysPmenu;
	}

	public List<SysMenu> getSysMenulist() {
		return sysMenulist;
	}

	public void setSysMenulist(List<SysMenu> sysMenulist) {
		this.sysMenulist = sysMenulist;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getMenuPid() {
		return this.menuPid;
	}

	public void setMenuPid(Integer menuPid) {
		this.menuPid = menuPid;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer getMenuSort() {
		return this.menuSort;
	}

	public void setMenuSort(Integer menuSort) {
		this.menuSort = menuSort;
	}

	public String getMenuIcon() {
		return this.menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuCss() {
		return this.menuCss;
	}

	public void setMenuCss(String menuCss) {
		this.menuCss = menuCss;
	}

	public Integer getMenuType() {
		return this.menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public Integer getMenuLevel() {
		return this.menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsDel() {
		return this.isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	@Override
	public int compareTo(SysMenu o) {
		int val = this.getMenuPid().compareTo(o.getMenuPid());
		if(val == 0){
			val = this.getMenuSort().compareTo(o.getMenuSort());
		}
		return val;
	}
}