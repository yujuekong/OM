package com.smart.om.dao.sys;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

/**
 * 系统日志DAO
 * @author ienovo
 *
 */
public class SysLogDAO extends BaseDao {
	/** 分页查询系统日志 **/
	public DTablePageModel querySysLogPage(Map<String, Object> params, PageData pageData){
		if(pageData == null) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append(" from SysLog sg where 1 = 1 ");		
		hqlCount.append("select count(*) from SysLog sg where 1 = 1");
		if (params != null) {
			if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (sg.logUsername like '%").append(keyword).append("%'")
                            .append(" or sg.logRelname like '%").append(keyword).append("%') ");
                    hqlCount.append(" and (sg.logUsername like '%").append(keyword).append("%'")
                            .append(" or sg.logRelname like '%").append(keyword).append("%') ");
                }
            }
		}
		hql.append(" order by sg.logTime desc  ");
		return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
	}
}
