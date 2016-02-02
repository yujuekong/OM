package com.smart.om.dao.sys;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.SysDict;

/**
 * 系统数据字典DAO
 * @author langyuk
 *
 */
public class SysDictDAO extends BaseDao{
	/**
	 * 查询顶层数据字典
	 */
	public List<SysDict> queryDictOneLevel(){
		List<SysDict> dictList = new ArrayList<SysDict>();
		String hql = "from SysDict as model1 where model1.dictLevel = 1 order by model1.dictSort";
		dictList = this.find(hql, null);		
		return dictList;
	}
	
	/**
	 * 查询顶层数据字典
	 */
	public List<SysDict> querySubDictByPid(Integer dictId){
		List<SysDict> dictList = new ArrayList<SysDict>();
//		String hql = "from SysDict as model1 where model1.dictPid = " + dictId + " and order by model1.dictSort";
		StringBuffer hql = new StringBuffer();
		hql.append("from SysDict as model1 where model1.dictPid = ").append(dictId).append(" order by model1.dictSort");
		dictList = this.find(hql.toString(), null);		
		return dictList;
	}
	
	/**
	 * 根据数据字典code查询数据字典
	 */
	public SysDict queryDictByPcode(String dictPcode){
		SysDict sysDict = new SysDict();
		List<SysDict> dictList = new ArrayList<SysDict>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from SysDict as model1 where 1 = 1 ");	
		if(StringUtils.isNotBlank(dictPcode)) {
			hql.append(" and model1.dictCode = '" + dictPcode + "' ");	
		}
		dictList = this.find(hql.toString(), null);		
		if(dictList != null){
			if(dictList.size() > 0){
				sysDict = dictList.get(0);
			}
		}
		return sysDict;
	}
	
	/**
	 * 根据上级字典代码查询下级数据字典
	 */
	public List<SysDict> querySubDictByPcode(String dictPcode){
		List<SysDict> dictList = new ArrayList<SysDict>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from SysDict as model1 where 1 = 1 ");	
		if(StringUtils.isNotBlank(dictPcode)) {	
			hql.append(" and model1.dictPcode = '" + dictPcode + "' ");
		}
		dictList = this.find(hql.toString(), null);		
		return dictList;
	}
	
	/**
	 * 根据上级字典代码查询多级数据字典
	 */
	public List<SysDict> queryMulSubDictByPcode(String dictPcode,String dictLevel){
		List<SysDict> dictList = new ArrayList<SysDict>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from SysDict as model1 where 1 = 1 ");	
		if(StringUtils.isNotBlank(dictPcode)) {	
			hql.append(" and model1.dictTopCode = '" + dictPcode + "' ");
		}
		if(StringUtils.isNotBlank(dictLevel)) {	
			hql.append(" and model1.dictLevel < " + dictLevel);
		}
		dictList = this.find(hql.toString(), null);		
		return dictList;
	}
	
	/**
	 * 查询所有分公司
	 */
	public List<SysDict> queryAllChildCompany(){
		StringBuffer hql = new StringBuffer();
		hql.append(" from SysDict sd where 1=1 and sd.dictTopCode= '").append("AL_POSITION").append("'");
		hql.append(" and sd.dictLevel = 4");
		List<SysDict> list = this.find(hql.toString(), null);
		return list;
	}
}
