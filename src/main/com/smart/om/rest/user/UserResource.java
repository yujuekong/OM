package com.smart.om.rest.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.SysRoleAuth;
import com.smart.om.persist.SysUser;
import com.smart.om.rest.base.BaseResource;
import com.smart.om.util.Const;
import com.smart.om.util.Tools;

@Component("UserResource")
public class UserResource  extends BaseResource{
	private static final Logger logger = Logger.getLogger(UserResource.class);
	@Resource
	private SysFuncHandler sysFuncHandler; 
	
	/**
	 * 用户登陆
	 * @param dataMap
	 * @return
	 */
	public String login(Map dataMap){
		String userName = Tools.trim(dataMap.get("userName"));//用户名称
		String password = Tools.trim(dataMap.get("password"));//用户名称
		SysUser sysUser = new SysUser();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			sysUser.setUserName(userName);
			sysUser.setPassword(Tools.md5(password));
			List<SysUser> userList = sysFuncHandler.userLogin(sysUser);
			if (userList != null) {
				if (userList.size() > 0) {
					sysUser = userList.get(0);					
					map.put("userId", sysUser.getUserId());//登陆用户ID
					map.put("userName", sysUser.getUserName());//登陆用户账号
					map.put("realName", sysUser.getRealName());//登陆用户名称
					map.put("mobile", sysUser.getTel());//登陆用户手机电话
					map.put("userStatus", sysUser.getUserStatus());//用户状态
					
					
					List<SysRoleAuth> sysRoleAuthList = sysFuncHandler.queryAppMenuByUser(sysUser.getUserId());
					if(sysRoleAuthList!=null){
						 List<Map<Object, Object>> list  = new ArrayList();
						for (int i = 0; i < sysRoleAuthList.size(); i++){
							    for  ( int j=sysRoleAuthList.size()-1;j>i;j -- ){
							    	SysRoleAuth sysRoleAuthI = sysRoleAuthList.get(i);
							    	SysRoleAuth sysRoleAuthJ = sysRoleAuthList.get(j);
							    	if  (sysRoleAuthJ.getAuthId().equals(sysRoleAuthI.getAuthId()))   { 
							    		sysRoleAuthList.remove(j); 
								      } 
							  } 
							Map<Object, Object> authIdMap = new HashMap<Object, Object>();
							SysRoleAuth sysRoleAuth = sysRoleAuthList.get(i);
							authIdMap.put("authId", sysRoleAuth.getAuthId());
							list.add(authIdMap);
						}
						map.put("authIdList", list);
					}
					
//					//获得用户权限
//					List<SysMenu> treeList = sysFuncHandler.queryAppMenuByUser(63, sysUser.getUserId());
//					List<Map> lstMap = new ArrayList<Map>();
//					int sort = 1;
//					if(treeList != null){
//						for (SysMenu sysMenu : treeList) {
//							Map<Object, Object> userMap = new HashMap<Object, Object>();
//							userMap.put("menuCode", sysMenu.getCssId());//菜单编码
//							userMap.put("menuName", sysMenu.getMenuName());//菜单名称
//							userMap.put("sort", sort);//菜单名称
//							lstMap.add(userMap);
//							sort++;
//						}
//					}
//					map.put("menuList", lstMap);
				}
			}
			else{
				return this.toFailTipJson("用户名称和密码错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}		
		return this.toResultJsonSuccess(map);
	}
}
