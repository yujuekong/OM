package com.smart.om.web.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.persist.SysDict;
import com.smart.om.util.ZTreeNode;
import com.smart.om.util.ZTreeUtil;
import com.smart.om.web.base.BaseAction;

/**
 * 数据字典管理Action
 * @author ienovo
 *
 */
@Namespace("/view/sys/sysDict")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class SysDictAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(SysDictAction.class);
	@Resource
	private SysAssistHandler sysAssistHandler;
	
	private SysDict sysDict;
	/**
	 * 查询顶层数据字典
	 */
	@Action(value = "querySysDict", results = {
			@Result(name = SUCCESS, location = "/view/sys/dictList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String querySysDict() {
		String result = SUCCESS;
		try {
			List<SysDict> sysDictList = sysAssistHandler.queryDictOneLevel();
			this.getRequest().put("sysDictList", sysDictList);
			this.getRequest().put("dictPid", "0");
			this.getRequest().put("dictLevel", "1");
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", " 查询所有数据字典");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
			return ERROR;
		}
		return result;
	}
	
	/**
	 * 查询顶层数据字典
	 */
	@Action(value = "querySysDictByPid", results = {
			@Result(name = SUCCESS, location = "/view/sys/dictList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String querySysDictByPid() {
		String result = SUCCESS;
		try {
			String dictId = this.getRequestParm().getParameter("dictId");
			if(StringUtils.isNotBlank(dictId)){
				SysDict sysDict = sysAssistHandler.queryDictById(Integer.valueOf(dictId));
				List<SysDict> sysDictList = sysAssistHandler.querySubDictByPid(Integer.valueOf(dictId));
				this.getRequest().put("sysDictList", sysDictList);
				this.getRequest().put("dictId", dictId);
				this.getRequest().put("dictLevel", sysDict.getDictLevel()+1);
				if(sysDict.getDictLevel() == 1){
					this.getRequest().put("dictTopCode", sysDict.getDictCode());
				}
				else{
					this.getRequest().put("dictTopCode", sysDict.getDictTopCode());
				}
				this.getRequest().put("dictPcode", sysDict.getDictCode());
			}
			else{
				List<SysDict> sysDictList = sysAssistHandler.queryDictOneLevel();
				this.getRequest().put("sysDictList", sysDictList);
				this.getRequest().put("dictPid", "0");
				this.getRequest().put("dictLevel", "1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", " 查询所有数据字典");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
		}
		return result;
	}
	
	/**
	 * 查询顶层数据字典
	 */
	@Action(value = "saveOrUpdateDict", results = {
			@Result(name = SUCCESS,type = "redirect", location = "/view/sys/sysDict/querySysDict.action"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String saveOrUpdateDict() {
		String result = SUCCESS;
		try {
			sysDict.setNewer(true);
			sysAssistHandler.saveOrUpdateSysDict(sysDict);
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", " 查询所有数据字典");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
		}
		return result;
	}
	
	//删除系统数据字典
	@Action(value="delDict")
	public String delDict() throws IOException {
		try {
			String dictId = this.getRequestParm().getParameter("dictId");
//			SysDict dict = sysAssistHandler.delDictById(Integer.valueOf(dictId));
			SysDict dict = sysAssistHandler.queryDictById(Integer.valueOf(dictId));
			sysAssistHandler.deleteDict(dict);
			this.getResponse().getWriter().print(true);
		} catch (Exception e) {
			e.printStackTrace();
			this.getResponse().getWriter().print(false);
		}
		return null;
	}
	
	/**
	 * 根据上级字典Code获得数据字典数据
	 */
	@Action(value="getMulSubDictDataByPcode")
	public void getMulSubDictDataByPcode() {		
		try{
			String dictPcode = this.getRequestParm().getParameter("dictPcode");
			String dictLevel = this.getRequestParm().getParameter("dictLevel");
			SysDict sysDict = sysAssistHandler.queryDictByPcode(dictPcode);
			List<SysDict> dictLst = sysAssistHandler.queryMulSubDictByPcode(dictPcode,dictLevel);			
			ZTreeNode root = new ZTreeNode();
			root.setName("所有分类");
			if("AL_POSITION".equals(dictPcode)){
				root.setName("智慧生活网络科技有限公司");
			}
			root.setOpen(true);
			root.setId(String.valueOf(sysDict.getDictId()));
			String jsonData = ZTreeUtil.toJson(root, dictLst, "dictId","dictPid","dictCode","dictName");
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据上级字典Code获得数据字典数据
	 */
	@Action(value="getSubDictDataByPcode")
	public void getSubDictDataByPcode() {		
		try{
			String dictPcode = this.getRequestParm().getParameter("dictPcode");
			SysDict sysDict = sysAssistHandler.queryDictByPcode(dictPcode);
			List<SysDict> dictLst = sysAssistHandler.querySubDictByPcode(dictPcode);			
			ZTreeNode root = new ZTreeNode();
			root.setName("所有分类");
			root.setOpen(true);
			root.setId(String.valueOf(sysDict.getDictId()));
			String jsonData = ZTreeUtil.toJson(root, dictLst, "dictId","dictPid","dictCode","dictName");
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SysDict getSysDict() {
		return sysDict;
	}
	public void setSysDict(SysDict sysDict) {
		this.sysDict = sysDict;
	}
}
