package com.smart.om.biz.sys;

import java.util.List;
import java.util.Map;

import com.smart.om.persist.SysDict;
import com.smart.om.persist.SysLog;
import com.smart.om.persist.SysMenu;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

public interface SysAssistHandler {

	/** 查询顶层数据字典 **/
	public List<SysDict> queryDictOneLevel();
	
	/** 根据数据字典ID查询数据字典 **/
	public SysDict queryDictById(Integer dictId);

	/** 根据父数据字典ID查询下面所有子数据字典 **/
	public List<SysDict> querySubDictByPid(Integer dictId);
	/**
	 * 根据数据字典code查询数据字典
	 */
	public SysDict queryDictByPcode(String dictPcode);
	/**
	 * 根据上级字典代码查询数据字典
	 */
	public List<SysDict> querySubDictByPcode(String dictPcode);
	
	/**
	 * 根据上级字典代码查询多级数据字典
	 */
	public List<SysDict> queryMulSubDictByPcode(String dictPcode,String dictLevel);
	
	/** 根据数据字典ID删除数据字典 **/
	public SysDict delDictById(Integer dictId);
	public SysDict deleteDict(SysDict sysDict);
	
	
	/** 添加系统日志 **/
	public SysDict saveOrUpdateSysDict(SysDict sysDict);

	/** 分页查询系统日志 **/
	public DTablePageModel querySysLogPage(Map<String, Object> params,
			PageData pageData);

	/** 添加系统日志 **/
	public SysLog saveOrUpdateSysLog(SysLog sysLog);

	/** 查询系统菜单 **/
	public List<SysMenu> querySysMenu();
	
	/** 根据主键ID查询菜单 **/
	public SysMenu queryMenuById(Integer menuId);
	
	/** 添加或修改菜单 **/
	public SysMenu saveOrUpdateMenu(SysMenu sysMenu);
	
	/** 删除系统菜单 **/
	public SysMenu delMenuById(Integer menuId);
	
	/** 查询所有分公司 **/
	public List<SysDict> queryAllChildCompany();
}