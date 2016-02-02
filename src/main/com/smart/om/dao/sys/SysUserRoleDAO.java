package com.smart.om.dao.sys;

import java.util.List;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.SysUser;
import com.smart.om.persist.SysUserRole;
import com.smart.om.util.Const;
/**
 * 系统用户角色DAO
 * @author langyuk
 *
 */
public class SysUserRoleDAO extends BaseDao{
	//根据用户查询所有用户角色
	public List<SysUserRole> queryUserRoleByUser(SysUser sysUser){
		StringBuffer hql = new StringBuffer();
		hql.append("from SysUserRole as model where model.userId = ?");
		Object[] params = {sysUser.getUserId()};
		return this.find(hql.toString(), params);
	}
	
	//根据用户查询所有用户角色
	public List<SysUserRole> queryUserRoleByUser(Integer userId){
		StringBuffer hql = new StringBuffer();
		hql.append("from SysUserRole as model where model.userId = ?");
		Object[] params = {userId};
		return this.find(hql.toString(), params);
	}
	
	/**
	 * 根据角色编号查询该角色下的所有员工对象
	 */
	public List<SysUser> queryAllEmpInTheRole(String roleId) {
		StringBuffer hql=new StringBuffer();
		hql.append(" select su from SysUser as su,SysUserRole as ur where su.isDel = '0' and  ur.userId = su.userId ");
		hql.append(" and ur.roleId="+roleId+" ");
		hql.append(" and su.userStatus = '").append(Const.IS_VALID_TRUE).append("'");
		List<SysUser> empList = this.find(hql.toString(), null, null, null);
		return empList;
	}
	
	/**检查数据库中是否存在具备该角色的用户 */
	public List<SysUserRole> queryEmpByEmpAndRole(String roleId,String userId){
		StringBuffer hql=new StringBuffer();
		hql.append(" select ur from SysUser as su,SysUserRole as ur ");
		hql.append(" where ur.userId = su.userId and  su.userId = "+ userId + " and ur.roleId = " + roleId);
		List<SysUserRole> urList = this.find(hql.toString(), null, null, null);
		return urList;
	}
	
	/** 根据所属分公司查询用户 **/
	public List<SysUser> queryAllUserByOrg(Integer orgId){
		StringBuffer hql = new StringBuffer();
		hql.append(" from SysUser su where 1=1 and su.orgId = ").append(orgId);
		hql.append(" and su.isDel = '").append(Const.IS_DEL_FALSE).append("'");
		hql.append(" and su.userStatus = '").append(Const.IS_VALID_TRUE).append("'");
		hql.append(" and su.userId != ").append(1);
		List<SysUser> list = this.find(hql.toString(), null);
		return list;
	}
	
	/** 查询所有用户 **/
	public List<SysUser> queryAllUser(){
		StringBuffer hql = new StringBuffer();
		hql.append(" from SysUser su where 1=1 and su.isDel = '").append(Const.IS_DEL_FALSE).append("'");
		hql.append(" and su.userStatus = '").append(Const.IS_VALID_TRUE).append("'");
		hql.append(" and su.userId != ").append(1);
		List<SysUser> list = this.find(hql.toString(), null);
		return list;
	}
}
