package com.smart.om.dao.sys;

import java.util.ArrayList;
import java.util.List;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.AppVersionsInfo;
import com.smart.om.persist.SysMenu;
import com.smart.om.persist.SysRoleAuth;

/**
 * 菜单DAO
 * @author langyuk
 *
 */
public class SysMenuDAO extends BaseDao{
	/**
	 * 构造菜单树
	 */
	public List<SysMenu> queryMenuByPmenu(Integer pmenuId){
		List<SysMenu> menuList = new ArrayList<SysMenu>();
		String hql = "from SysMenu as model where model.isDel = 0 and model.menuType = 1 and model.menuPid = " + pmenuId;
		List<SysMenu> oneLeveMenuList = this.find(hql, null);
		for (SysMenu sysMenu : oneLeveMenuList) {
			StringBuffer towHql = new StringBuffer();
			towHql.append("from SysMenu as model1 where model1.isDel = 0 and model1.menuType = 1 and model1.menuPid = " + sysMenu.getMenuId());
			towHql.append(" order by model1.menuSort ");
			List<SysMenu> towLeveMenuList = this.find(towHql.toString(), null);
			List<SysMenu> oneMenuList = new ArrayList<SysMenu>();
			if(towLeveMenuList != null){
				for (SysMenu sysMenu2 : towLeveMenuList) {
					StringBuffer threeHql = new StringBuffer();
					threeHql.append("from SysMenu as model2 where model2.isDel = 0  and model2.menuType = 1 and model2.menuPid = " + sysMenu2.getMenuId());					
					List<SysMenu> threeLeveMenuList = this.find(threeHql.toString(), null);
					sysMenu2.setSysMenulist(threeLeveMenuList);
					oneMenuList.add(sysMenu2);
				}
			}
			sysMenu.setSysMenulist(oneMenuList);
			menuList.add(sysMenu);
		}
		return menuList;
	}
	
	/** 后台用户菜单 **/
	public List<SysMenu> queryMenuByUser(Integer userId,String servState){
		List<SysMenu> menuLst = null;
		if(userId != null){
			StringBuffer hql = new StringBuffer();
			hql.append("select distinct model.menuId,model.menuPid,model.menuName,model.menuUrl,model.menuSort,model.menuCss,model.menuIcon,model.menuLevel ")
			.append(" from SysRoleAuth a,SysMenu model,SysUserRole r ")
			.append(" where model.isDel = 0 and model.menuType = 1 and model.menuId != 1 ")
			.append(" and a.authId = model.menuId and a.servState= ? and a.roleId = r.roleId and r.userId= ? ");
			
			List<Object[]> objArrList = this.find(hql.toString(), new Object[]{servState, userId});
			if(objArrList != null) {
				menuLst = new ArrayList<SysMenu>();
				for(Object[] objArr : objArrList) {
					Integer menuId = (Integer) objArr[0];
					Integer menuPid = (Integer) objArr[1];
					String menuName = (String) objArr[2];
					String menuSrc = (String) objArr[3];
					Integer sort = (Integer) objArr[4];
					String cssPic = (String) objArr[5];
					String cssId = (String) objArr[6];
					Integer menuLevel = (Integer) objArr[7];
					
					SysMenu menu = new SysMenu();
					menu.setMenuId(menuId);
					menu.setMenuPid(menuPid);
					menu.setMenuName(menuName);
					menu.setMenuUrl(menuSrc);
					menu.setMenuSort(sort);
					menu.setMenuCss(cssPic);
					menu.setMenuIcon(cssId);
					menu.setMenuLevel(menuLevel);
					
					menuLst.add(menu);
				}
			}
		}
		return menuLst;
	}
	
	/**
	 * 构造后台菜单树
	 */
	public List<SysMenu> queryMenuByPmenu(){
		List<SysMenu> menuList = new ArrayList<SysMenu>();
		String hql = "from SysMenu as model where model.isDel = 0 and model.menuType = 1 ";
		menuList = this.find(hql, null);		
		return menuList;
	}
	
	/** 查询用户所有的菜单 **/
	public List<SysRoleAuth> queryAppMenuByUser(Integer userId){
		List<SysRoleAuth> sysRoleAuthList = new ArrayList<SysRoleAuth>();
		String hql = "from SysRoleAuth where roleId in (select roleId from SysUserRole where userId ="+userId+") and servState ='APP' order by roleAuthId desc";
		sysRoleAuthList = this.find(hql, null);		
		return sysRoleAuthList;
	}
	
	/** 根据用户查询菜单名称 **/
	public List<String> queryMenuNameByUserId(Integer userId){
		StringBuffer hql = new StringBuffer();
		hql.append("select sm.menuName from SysRoleAuth sra,SysMenu sm,SysUserRole sur where 1 = 1 ");
		hql.append(" and sm.menuId = sra.authId and sur.roleId = sra.roleId and sm.menuLevel > 1 and sm.menuType = 1");
		hql.append(" and sur.userId = ?");
		List<String> list = this.find(hql.toString(), new Object[]{userId});
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}
}
