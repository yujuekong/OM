package com.smart.om.dao.sys;

import java.util.List;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.SysRole;
import com.smart.om.persist.SysUser;
import com.smart.om.util.Const;

/**
 * 系统角色DAO
 * @author ienovo
 *
 */
public class SysRoleDAO extends BaseDao{
	/** 根据角色类型查询角色 */
	public List<SysRole> queryRoleByType(String roleType){
		StringBuffer hql = new StringBuffer();
		hql.append(" from SysRole as model where model.isDel = 0  ");
		if(roleType != null && !"".equals(roleType)){
			hql.append(" and model.roleType = '" + roleType + "' ");
		}		
		return this.find(hql.toString(), null);
	}	
	
	/** 根据用户查询用户角色  */
	public List<SysRole> queryRoleByUser(SysUser sysUser,String isOrg){
		StringBuffer hql = new StringBuffer();
		hql.append(" from SysRole as model where model.isDel = '" + Const.IS_DEL_FALSE + "'");
		if(Const.IS_CHILD.equals(isOrg)){
			hql.append(" and model.isOrgId = '").append(isOrg).append("'");
		}
		hql.append(" order by model.roleName");
		return this.find(hql.toString(), null);
	}
	
	/**根据用户查询角色*/
	public List<SysRole> queryRoleByUser(Integer userId){
		StringBuffer hql = new StringBuffer();
		hql.append("select model from SysRole as model,SysUserRole as model1 where model.isDel = 0 and model.roleId = model1.roleId ");
		if(userId != null && !"".equals(userId)){
			hql.append(" and model1.userId = " + userId);
		}		
		return this.find(hql.toString(), null);
	}
}
