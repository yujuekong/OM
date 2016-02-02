package com.smart.om.biz.sys.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smart.om.biz.sys.RoleHandler;
import com.smart.om.dao.sys.SysRoleAuthDAO;
import com.smart.om.dao.sys.SysRoleDAO;
import com.smart.om.dao.sys.SysUserDAO;
import com.smart.om.dao.sys.SysUserRoleDAO;
import com.smart.om.persist.SysRole;
import com.smart.om.persist.SysRoleAuth;
import com.smart.om.persist.SysUser;
import com.smart.om.persist.SysUserRole;
import com.smart.om.util.PropertyData;
import com.smart.om.util.QueryData;

/**
 * 角色业务逻辑类
 * @author langyuk
 */
@Component("roleHandler")
public class RoleHandlerImpl implements RoleHandler{
private static final Logger logger = Logger.getLogger(RoleHandlerImpl.class);
	
	@Autowired
	private SysUserDAO sysUserDAO;
	@Autowired
	private SysRoleDAO sysRoleDAO;
	@Autowired
	private SysUserRoleDAO sysUserRoleDAO;
	@Autowired
	private SysRoleAuthDAO sysRoleAuthDAO;

	//-------------业务方法开始--------------
	/** 查询角色 **/
	public List<SysRole> getRoleList(SysUser sysUser,String isOrg){
		return sysRoleDAO.queryRoleByUser(sysUser,isOrg);
	}
		
	public List getSysRoleAuth(Integer roleId,String servState){
    	List list=sysRoleAuthDAO.getRoleAuth(roleId,servState);
    	return list;
    }
	
	
	public SysRoleAuth delSysRoleAuth(Integer roleId){
    	return (SysRoleAuth)sysRoleAuthDAO.delete(roleId);
    }
	
	
	public SysRole queryRoleById(Integer roleId){
		return (SysRole)sysRoleDAO.find(roleId);
	}
	
	
	public SysRole addRoles(SysRole role){
		return (SysRole)sysRoleDAO.save(role);
	}
	

	/**根据用户查询角色*/
	public String queryRoleByUser(Integer userId){
		String result = "";
		List<SysRole> roleList = sysRoleDAO.queryRoleByUser(userId);
		if(roleList != null) {
			for (SysRole sysRole : roleList) {
				result = result + sysRole.getRoleName() + "," ;
			}
			if(result.length() > 1){
				result = result.substring(0, result.length()-1);
			}
		}
		return result;
	}
	
	public SysUser addSysUser(SysUser sysUser){
		return (SysUser)sysUserDAO.save(sysUser);
	}
	
	
	public SysRole delRoles(Integer roleId){
		SysRole sys=(SysRole)sysRoleDAO.delete(roleId);
		return sys;
	}
	
	/** 根据角色类型查询角色 */
	public List<SysRole> queryRoleByType(String roleType){
		return sysRoleDAO.queryRoleByType(roleType);
	}
	
//	public List ModAndFunc(String servState){
//		List getList=sysModuleDAO.getModAndFunc(servState);
//		return getList;
//	}
	
	
//	public List RoleAndAuth(String roleId,String servState){
//		List getRoleAuth=sysRoleAuthDAO.getRoleAndAuth(roleId,servState);
//		return getRoleAuth;
//	}
	
	/** 根据菜单ID，角色ID查询是否在菜单权限中 */
	public boolean getMenuRoleAuth(Integer roleId,Integer menuId, String servState){
		return sysRoleAuthDAO.getMenuRoleAuth(roleId,menuId, servState);
	}
	
	
//	public List<SysModule> getSysModule(Class clazz){
//		return sysModuleDAO.getObjects(clazz);
//	}
	
	
//	public SysAuth getSysAuthList(SysAuth sysAuth){
//		QueryData query=new QueryData();
//    	query.clear();
//    	String moduleId=sysAuth.getModuleId();
//    	String functionId=sysAuth.getFunctionId();
//    	if(moduleId != null && !"".equals(moduleId))
//    	{
//    	  query.add(new PropertyData("moduleId",moduleId,null));
//    	}
//        query.add(new PropertyData("functionId",functionId,null));
//        SysAuth sysAuths=(SysAuth)sysAuthDAO.find(query);
//    	return sysAuths;
//	}
//	
	
	public SysRoleAuth getSaveAuth(SysRoleAuth sysAuth){
		return (SysRoleAuth)sysRoleAuthDAO.save(sysAuth);
	}
	
	
	public SysUserRole saveSysUserRole(SysUserRole userRole){
		return (SysUserRole)sysUserRoleDAO.save(userRole);
	}
	
	
//	public List getFunction(Integer userId,String servState){
//		return sysUserDAO.getFunction(userId,servState);
//	}
//	
//	
//	public List getMenuByUser(Integer userId,String servState){
//		return sysUserDAO.getMenuByUser(userId, servState);
//	}
	
	public SysUserRole getSysUserRole(SysUserRole userRole){
		QueryData query=new QueryData();
    	query.clear();
    	Integer roleId=userRole.getRoleId();
    	Integer userId=userRole.getUserId();
    	query.add(new PropertyData("roleId",roleId,null));
        query.add(new PropertyData("userId",userId,null));
        SysUserRole sysUserRole=(SysUserRole)sysUserRoleDAO.find(query);
    	return sysUserRole;
	}
	
	/**查询用户的用户角色*/
	public List<SysUserRole> queryUserRoleByUser(SysUser sysUser){
		return sysUserRoleDAO.queryUserRoleByUser(sysUser);
	}
	
	
	/**删除用户角色*/
	public SysUserRole delUserRole(Integer userRoleId){
		return (SysUserRole)sysUserRoleDAO.delete(userRoleId);
	}
	
	/**根据商家查询所有员工对象 */
	public List<SysUser> queryAllUserBySeller(Integer userId){
		List<SysUser> userList = sysUserDAO.getObjects(SysUser.class);
		return userList;
	} 
	
	/**根据角色编号查询该角色下的所有员工对象 */
	public List<SysUser> queryAllEmpInTheRole(String roleId) {
		return sysUserRoleDAO.queryAllEmpInTheRole(roleId);
	}
	
	/**
	 * 根据角色编号和员工数组，将一组员工添加到选定的角色中
	 */
	public boolean addEmpToRole(String roleId, String[] userIds) {
		boolean flag = false;
		SysUserRole userRole = new SysUserRole();
		// 得到员工编号数组，先检查数据库中是否存在具备该角色的用户，如果存在就先删除，然后再插入
		if(userIds != null){
			for (int i = 0; i < userIds.length; i++) {
				SysUser sysUser = (SysUser)sysUserDAO.find(Integer.valueOf(userIds[i]));
				List<SysUserRole> urList = sysUserRoleDAO.queryEmpByEmpAndRole(roleId, userIds[i]);
				if(urList != null){
					if(urList.size()>0){
						for(SysUserRole ur:urList){
							sysUserRoleDAO.delete(ur);
						}
					}
				}
				userRole.setNewer(true);
				userRole.setRoleId(Integer.valueOf(roleId));		
				userRole.setUserId(sysUser.getUserId());
				sysUserRoleDAO.save(userRole);
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 根据员工ID和角色数组，将一组角色添加到用户中
	 */
	public boolean addRoleToUser(String userId, String[] rolesId) {
		boolean flag = false;
		//删除用户的所有角色
		List<SysUserRole> rolelList = sysUserRoleDAO.queryUserRoleByUser(Integer.valueOf(userId));
		if(rolelList != null){
			if(rolelList.size()>0){
				for(SysUserRole ur:rolelList){
					sysUserRoleDAO.delete(ur);
				}
			}
		}
		
		SysUserRole userRole = new SysUserRole();
		// 得到员工编号数组，先检查数据库中是否存在具备该角色的用户，如果存在就先删除，然后再插入
		if(rolesId != null){
			for (int i = 0; i < rolesId.length; i++) {
				userRole.setNewer(true);
				userRole.setRoleId(Integer.valueOf(rolesId[i]));		
				userRole.setUserId(Integer.valueOf(userId));
				sysUserRoleDAO.save(userRole);
				flag = true;
			}
		}		
		return flag;
	}
	
	/**
	 * 根据角色ID，用户ID，添加用户角色数据
	 */
	public boolean addUserRole(int roleId, int userId) {
		boolean flag = false;
		SysUserRole userRole = new SysUserRole();
		userRole.setNewer(true);
		userRole.setRoleId(Integer.valueOf(roleId));		
		userRole.setUserId(userId);
		sysUserRoleDAO.save(userRole);
		return flag;
	}
	
	/**
	 * 从选定的角色中移除某些用户(批量删除选定角色下的用户)
	 */
	public boolean delEmpFromRole(String roleId, String[] userIds) {
		boolean flag = false;
		// 得到员工编号数组，先检查数据库中是否存在具备该角色的用户，如果存在就先删除，然后再插入
		for (int i = 0; i < userIds.length; i++) {
			List<SysUserRole> urList = sysUserRoleDAO.queryEmpByEmpAndRole(roleId, userIds[i]);
			if(urList != null){
				if(urList.size()>0){
					for(SysUserRole ur:urList){
						sysUserRoleDAO.delete(ur);
					}
				}
			}
			flag = true;
		}
		return flag;
	}

	/**
	 * 根据所属分公司查询用户
	 */
	public List<SysUser> queryAllUserByOrg(Integer orgId) {
		return sysUserRoleDAO.queryAllUserByOrg(orgId);
	}

	/**
	 * 查询所有用户
	 */
	public List<SysUser> queryAllUser() {
		return sysUserRoleDAO.queryAllUser();
	}

	/**
	 * 查询总公司的用户
	 */
	public List<SysUser> queryAllUserByRegion() {
		return sysUserDAO.queryAllUserByRegion();
	}

	
}
