package com.smart.om.biz.sys.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.dao.sys.SysDictDAO;
import com.smart.om.dao.sys.SysLogDAO;
import com.smart.om.dao.sys.SysMenuDAO;
import com.smart.om.persist.SysDict;
import com.smart.om.persist.SysLog;
import com.smart.om.persist.SysMenu;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

/**
 * 系统辅助功能管理业务逻辑类
 * @author langyuk
 *
 */
@Component("sysAssistHandler")
public class SysAssistHandlerImpl implements SysAssistHandler {
	private static final Logger logger = Logger.getLogger(SysAssistHandlerImpl.class);
	@Autowired
	private SysDictDAO sysDictDAO;//系统字典
	@Autowired
	private SysLogDAO sysLogDAO;//系统日志
	@Autowired
	private SysMenuDAO sysMenuDAO;//系统菜单
	
	/** 查询顶层数据字典 **/
	public List<SysDict> queryDictOneLevel(){
		return sysDictDAO.queryDictOneLevel();
	}
	
	/** 根据数据字典ID查询数据字典 **/
	public SysDict queryDictById(Integer dictId){
		return (SysDict)sysDictDAO.find(dictId);
	}
	
	/** 根据父数据字典ID查询下面所有子数据字典 **/
	public List<SysDict> querySubDictByPid(Integer dictId){
		return sysDictDAO.querySubDictByPid(dictId);
	}
	
	/**
	 * 根据数据字典code查询数据字典
	 */
	public SysDict queryDictByPcode(String dictPcode){
		return sysDictDAO.queryDictByPcode(dictPcode);
	}
	
	/**
	 * 根据上级字典代码查询数据字典
	 */
	public List<SysDict> querySubDictByPcode(String dictPcode){
		return sysDictDAO.querySubDictByPcode(dictPcode);
	}	
	
	/**
	 * 根据上级字典代码查询多级数据字典
	 */
	public List<SysDict> queryMulSubDictByPcode(String dictPcode,String dictLevel){
		return sysDictDAO.queryMulSubDictByPcode(dictPcode,dictLevel);
	}
	
	/** 根据数据字典ID删除数据字典 **/
	public SysDict delDictById(Integer dictId){
		return (SysDict)sysDictDAO.delete(dictId);
	}
	
	/** 添加系统日志 **/
	public SysDict saveOrUpdateSysDict(SysDict sysDict){
		return (SysDict)sysDictDAO.save(sysDict);
	}
	
	/** 分页查询系统日志 **/
	public DTablePageModel querySysLogPage(Map<String, Object> params, PageData pageData){
		DTablePageModel pageModel = new DTablePageModel();
		if(pageData != null) {
			pageModel = sysLogDAO.querySysLogPage(params, pageData);
		}
		return pageModel;
	}
	
	/** 添加系统日志 **/
	public SysLog saveOrUpdateSysLog(SysLog sysLog){
		return (SysLog)sysLogDAO.save(sysLog);
	}
	
	/** 查询系统菜单 **/
	public List<SysMenu> querySysMenu(){
		return sysMenuDAO.queryMenuByPmenu();
	}
	
	/** 根据主键ID查询菜单 **/
	public SysMenu queryMenuById(Integer menuId){
		return (SysMenu)sysMenuDAO.find(menuId);
	}
	
	/** 添加或修改菜单 **/
	public SysMenu saveOrUpdateMenu(SysMenu sysMenu){
		return (SysMenu)sysMenuDAO.save(sysMenu);
	}
	
	/** 删除系统菜单 **/
	public SysMenu delMenuById(Integer menuId){
		SysMenu sysMenu = (SysMenu)sysMenuDAO.find(menuId);
		sysMenu.setNewer(false);
		sysMenu.setIsDel(Const.IS_DEL_TRUE);
		return (SysMenu)sysMenuDAO.save(sysMenu);
	}

	@Override
	public SysDict deleteDict(SysDict sysDict) {
		return (SysDict) sysDictDAO.delete(sysDict);
	}

	/** 查询所有分公司 **/
	public List<SysDict> queryAllChildCompany() {
		return sysDictDAO.queryAllChildCompany();
	}

}
