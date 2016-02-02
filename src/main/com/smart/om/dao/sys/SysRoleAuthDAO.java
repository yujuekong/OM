package com.smart.om.dao.sys;

import java.util.ArrayList;
import java.util.List;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.SysRoleAuth;
import com.smart.om.persist.SysUserRole;

/**
 * 系统角色权限DAO
 * 
 * @author langyuk
 * 
 */
public class SysRoleAuthDAO extends BaseDao {
//	public List getRoleAndAuth(String roleId, String servState) {
//		StringBuffer sbf = new StringBuffer();
//		sbf.append(" select ra.roleId,a.moduleId,a.functionId from SysRoleAuth ra,SysAuth a where ");
//		sbf.append(" a.servState='" + servState + "' and ra.authId=a.authId and ra.roleId='" + roleId + "' ");
//		sbf.append("  ");
//		List list = this.find(sbf.toString(), null);
//		List getList = new ArrayList();
//		if (list != null) {
//			for (int i = 0; i < list.size(); i++) {
//				RoleAndAuthDto mfc = new RoleAndAuthDto();
//				Object[] item = (Object[]) list.get(i);
//				mfc.setRoldId((Integer) item[0]);
//				mfc.setModuleId((String) item[1]);
//				mfc.setFunctionId((String) item[2]);
//				getList.add(mfc);
//			}
//		}
//		return getList;
//	}

	/** 查询角色模块功能信息 */
	public List getRoleAuth(Integer roleId, String servState) {
		String hql = "select ra.roleAuthId,ra.authId from SysRoleAuth as ra where ra.roleId='" + roleId + "' and ra.servState='" + servState + "' ";
		List list = this.find(hql, null);
		List getList = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				SysRoleAuth stys = new SysRoleAuth();
				Object[] item = (Object[]) list.get(i);
				stys.setRoleAuthId((Integer) item[0]);
				stys.setAuthId((Integer) item[1]);
				getList.add(stys);
			}
		}
		return getList;
	}
	
	/** 根据菜单ID，角色ID查询是否在菜单权限中 */
	public boolean getMenuRoleAuth(Integer roleId,Integer menuId, String servState) {
		boolean flag = false;
		String hql = "select ra.roleAuthId,ra.authId from SysRoleAuth as ra where ra.roleId='" + roleId + "' and ra.authId='" + menuId + "'  and ra.servState='" + servState + "' ";
		List list = this.find(hql, null);
		List getList = new ArrayList();
		if (list != null) {
			flag = true;
		}
		return flag;
	}
	
	/** 根据用户ID查询所属角色 **/
	public List<SysUserRole> queryRoleByUserId(Integer id){
		StringBuffer hql = new StringBuffer();
		hql.append(" from SysUserRole sur where 1=1 ");
		hql.append(" and sur.userId = ").append(id);
		List<SysUserRole> list = this.find(hql.toString(), null);
		return list;
	}
}
