package com.smart.om.dao.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.SysMenu;
import com.smart.om.persist.SysUser;
import com.smart.om.persist.SysUserOp;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

/**
 * 用户操作DAO
 * @author langyuk
 *
 */
public class SysUserOpDAO extends BaseDao{
	/**
	 * 分页查询用户操作
	 * @param params 查询条件
	 * @param pageData 分页信息
	 * @return
	 */
	public DTablePageModel queryUserOpPage(Map<String, Object> params,PageData pageData) {
		// 构造查询的HQL
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append(" from SysUserOp as model where 1=1  ");
		hqlCount.append(" select count(model.userOpId) from SysUserOp as model where 1=1  ");
		if (params != null) {
			if (params.containsKey("keyword")) {
				String keyword = (String) params.get("keyword");
                if(StringUtils.isNotBlank(keyword)){
                	hql.append(" and model.userId = '").append(keyword).append("' ");
                }
			}
			if(params.containsKey("startDate")) {//开始时间
    			String startDate = (String) params.get("startDate");
    			if(StringUtils.isNotBlank(startDate)) {
    				hql.append(" and model.opDate >= '").append(startDate).append("' ");
    				hqlCount.append(" and model.opDate >= '").append(startDate).append("' ");
    			}
    		}
    		if(params.containsKey("endDate")) {//结束时间
    			String endDate = (String) params.get("endDate");
    			if(StringUtils.isNotBlank(endDate)) {
    				hql.append(" and model.opDate < '")	.append(endDate).append("' ");
    				hqlCount.append(" and model.opDate < '").append(endDate).append("' ");
    			}
    		}
			
		}
		if(params.containsKey("orgId")) {
			if(!"".equals(params.get("orgId")) && null!=params.get("orgId")){
				String orgId = params.get("orgId").toString();
				if(StringUtils.isNotBlank(orgId)) {
					hql.append(" and userId in (select userId from SysUser where orgId ="+orgId+") ");
					hqlCount.append(" and userId in (select userId from SysUser where orgId ="+orgId+")  ");
				}
			}
		}
		
		hql.append(" order by model.opTime desc  ");
		return this.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
	}
	
	/**查询用户操作详细信息**/
	public List<SysUserOp> querySysUserOpById(int id){
		List<SysUserOp> menuList = new ArrayList<SysUserOp>();
		String hql = "from SysUserOp as model where model.userId ="+id+" order by model.opTime desc";
		menuList = this.find(hql, null);		
		return menuList;
	}
}
