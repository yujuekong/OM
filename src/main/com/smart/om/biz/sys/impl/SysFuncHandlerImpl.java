package com.smart.om.biz.sys.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.dao.sys.SysContractDAO;
import com.smart.om.dao.sys.SysDeptDAO;
import com.smart.om.dao.sys.SysLogDAO;
import com.smart.om.dao.sys.SysMemberDAO;
import com.smart.om.dao.sys.SysMenuDAO;
import com.smart.om.dao.sys.SysOrgDAO;
import com.smart.om.dao.sys.SysRoleAuthDAO;
import com.smart.om.dao.sys.SysRoleDAO;
import com.smart.om.dao.sys.SysUserDAO;
import com.smart.om.dao.sys.SysUserOpDAO;
import com.smart.om.persist.AppConsigneeAddress;
import com.smart.om.persist.AppConsumeRecord;
import com.smart.om.persist.AppLiveness;
import com.smart.om.persist.AppMemberAddress;
import com.smart.om.persist.AppOp;
import com.smart.om.persist.AppVersionsInfo;
import com.smart.om.persist.GoodsInfo;
import com.smart.om.persist.MemberCdkey;
import com.smart.om.persist.OrderPay;
import com.smart.om.persist.SysContract;
import com.smart.om.persist.SysDept;
import com.smart.om.persist.SysLog;
import com.smart.om.persist.SysMember;
import com.smart.om.persist.SysMenu;
import com.smart.om.persist.SysOrg;
import com.smart.om.persist.SysRole;
import com.smart.om.persist.SysRoleAuth;
import com.smart.om.persist.SysUser;
import com.smart.om.persist.SysUserOp;
import com.smart.om.persist.SysUserRole;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

/**
 * 系统功能管理业务逻辑类
 * @author langyuk
 *
 */
@Component("sysFuncHandler")
public class SysFuncHandlerImpl implements SysFuncHandler {
	private static final Logger logger = Logger.getLogger(SysFuncHandlerImpl.class);
	@Autowired
	private SysMenuDAO sysMenuDAO;//菜单管理DAO
	@Autowired
	private SysUserDAO sysUserDAO;//用户管理DAO
	@Autowired
	private SysOrgDAO sysOrgDAO;//机构管理DAO
	@Autowired
	private SysRoleDAO sysRoleDAO;//系统用户管理DAO
	@Autowired
	private SysDeptDAO sysDeptDAO;//部门管理DAO
	@Autowired
	private SysContractDAO sysContractDAO;//合同管理DAO
	@Autowired
	private SysUserOpDAO sysUserOpDAO;//系统用户操作
	@Autowired
	private SysLogDAO sysLogDAO;//系统日志操作
	@Autowired
	private SysMemberDAO sysMemberDAO;//系统会员
	@Autowired
	private SysRoleAuthDAO sysRoleAuthDAO;//系统角色权限分配DAO
	
	/** 登陆验证 **/
	@SuppressWarnings("unchecked")
	public List<SysUser> userLogin(SysUser sysAccount){
		StringBuffer hqlQuery = new StringBuffer();
		hqlQuery.append(" from SysUser as model where ");
		hqlQuery.append(" (model.userName=? or model.tel = ?) and model.password=? and model.isDel = '0' ");
		Object[] params={sysAccount.getUserName(),sysAccount.getUserName(),sysAccount.getPassword()};
		return sysUserDAO.find(hqlQuery.toString(), params);
	}
	
	/** 查询用户 **/
	@SuppressWarnings("unchecked")
	public List<SysUser> querySysUser(Map<String, Object> params, PageData pageData){
		return sysUserDAO.getObjects(SysUser.class);
	}
	
	/** 添加用户 **/
	public SysUser saveOrUpdateUser(SysUser sysUser){
		return (SysUser)sysUserDAO.saveOrUpdate(sysUser);
	}
	
	public SysUser userSetting(SysUser sysUser){
		return (SysUser)sysUserDAO.save(sysUser);
	}
	
	/** 添加机构 **/
	public SysOrg saveOrUpdateOrg(SysOrg sysOrg){
		return (SysOrg)sysOrgDAO.save(sysOrg);
	}	
	
	/** 添加部门 **/
	public SysDept saveOrUpdateDept(SysDept sysDept){
		return (SysDept)sysDeptDAO.saveOrUpdate(sysDept);
	}
	
	/** 查询所有菜单 **/
	public List<SysMenu> querySysMenu(Integer userId ,String servState){
		List<SysMenu> treeList = new ArrayList<SysMenu>();	
		if("1".equals(String.valueOf(userId))){
			treeList = sysMenuDAO.queryMenuByPmenu();
		}
		else{
			treeList = sysMenuDAO.queryMenuByUser(userId, servState);
		}
//		treeList = sysMenuDAO.queryMenuByPmenu(1);	
//		treeList = sysMenuDAO.queryMenuByPmenu();	
		
		if(treeList != null){
			treeList = this.conver2TreeMenu(treeList);
		}		
		return treeList;
	}
	
	/** 查询用户所有的菜单 **/
	public List<SysRoleAuth> queryAppMenuByUser(Integer userId){
		return sysMenuDAO.queryAppMenuByUser(userId);
	}

	/**
	 * 根据HQL查询菜单
	 */
	@SuppressWarnings("unchecked")
	public List<SysMenu> queryMenu(String hqlQuery ,Object[] params){
		return sysMenuDAO.find(hqlQuery, params);
	}
	
	private List<SysMenu> conver2TreeMenu(List<SysMenu> menuList) {
		List<SysMenu> menuLv1 = null;
		if(menuList != null) {
			menuLv1 = new ArrayList<SysMenu>();
			List<SysMenu> menuLv2 = new ArrayList<SysMenu>();
			List<SysMenu> menuLv3 = new ArrayList<SysMenu>();
			for(SysMenu menu : menuList) {
				if(menu.getMenuLevel() == 1) {
					menuLv1.add(menu);
				} else if(menu.getMenuLevel() == 2) {
					menuLv2.add(menu);
				} else if(menu.getMenuLevel() == 3) {
					menuLv3.add(menu);
				}
			}
			Collections.sort(menuLv1);
			Collections.sort(menuLv2);
			Collections.sort(menuLv3);
			for(SysMenu mLv1 : menuLv1) {
				for(SysMenu mLv2 : menuLv2){
					if(mLv2.getMenuPid().intValue() == mLv1.getMenuId().intValue()){
						for(SysMenu mLv3 : menuLv3){
							if(mLv3.getMenuPid().intValue() == mLv2.getMenuId().intValue()) {
								mLv2.getSysMenulist().add(mLv3);
							}
						}
						mLv1.getSysMenulist().add(mLv2);
					}
				}
			}
		}
		return menuLv1;
	}
	
	/**
	 * 查询系统部门分页信息
	 */
	public DTablePageModel querySysDeptPage(Map<String, Object> params,
			PageData pageData) {
		DTablePageModel pageModel = new DTablePageModel();
		if (pageData != null) {
			pageModel = sysDeptDAO.querysysDeptPage(params, pageData);
		}
		return pageModel;
	}
	
	/**
	 * 根据id查询部门
	 */
	public SysDept querySysDeptById(Integer id){
		return (SysDept) sysDeptDAO.find(id);
	}
	
	/**
	 * 根据id删除部门
	 */
	public SysDept deleteSysDeptById(Integer id){
		return (SysDept) sysDeptDAO.delete(id);
	}

	/**
	 * 查询系统用户分页信息
	 */
	public DTablePageModel querySysUserPage(Map<String, Object> params,
			PageData pageData) {
		DTablePageModel pageModel = new DTablePageModel();
		if (pageData != null) {
			pageModel = sysUserDAO.querySysUserPage(params, pageData);
		}
		return pageModel;
	}
	
	/**
	 * 添加或修改合同
	 */
	public SysContract saveOrUpdateContract(SysContract sysContract){
		return (SysContract)sysContractDAO.save(sysContract);
	}
	
	/**
	 * 分页查询系统合同
	 * @param params
	 * @param pageData
	 * @return
	 */
	public DTablePageModel queryContractPage(Map<String, Object> params,PageData pageData){
		DTablePageModel pageModel = new DTablePageModel();
		if (pageData != null) {
			pageModel = sysContractDAO.queryContractPage(params, pageData);
		}
		return pageModel;
	}
	
	/**
	 * 根据ID查询合同
	 * @param contractId
	 * @return
	 */
	public SysContract queryContractById(Integer contractId){
		return (SysContract)sysContractDAO.find(contractId);
	}
	
	/**
	 * 根据id删除合同
	 */
	public SysContract deleteContractById(Integer contractId){
		return (SysContract) sysContractDAO.delete(contractId);
	}
	
	/**
	 * 添加或修改用户操作
	 */
	public SysUserOp saveOrUpdateOp(SysUserOp sysUserOp){
		return (SysUserOp)sysUserOpDAO.save(sysUserOp);
	}
	
	/**
	 * 分页查询用户操作
	 */
	public DTablePageModel queryUserOpPage(Map<String, Object> params,PageData pageData){
		DTablePageModel pageModel = new DTablePageModel();
		if (pageData != null) {
			pageModel = sysUserOpDAO.queryUserOpPage(params, pageData);
		}
		return pageModel;
	}

	/**
	 * 根据ID查询部门负责人
	 */
	public SysUser querySysUserById(Integer id) {
		return (SysUser) sysUserDAO.find(id);
	}
	
	/**
	 * 定时刷新用户所在坐标
	 */
	public int updateUserCoord(String userTel,String userLng,String userLat){
		return sysUserDAO.updateUserCoord(userTel,userLng,userLat);
	}

	/**
	 * 根据ID删除用户
	 */
	public SysUser deleteSysUserById(Integer id) {

		return (SysUser) sysUserDAO.delete(id);
	}

	/**
	 * 查询用户角色分页
	 */
	public DTablePageModel querySysRolePage(Map<String, Object> params,
			PageData pageData) {
		DTablePageModel pageModel = new DTablePageModel();
		if (pageData != null) {
			pageModel = sysUserDAO.querySysRolePage(params, pageData);
		}
		return pageModel;
	}

	/**
	 * 根据ID查询用户角色信息
	 */
	public SysRole querySysRoleById(Integer id) {
		
		return (SysRole) sysRoleDAO.find(id);
	}

	/**
	 * 查询系统日志
	 */
	public DTablePageModel querySysLogPage(Map<String, Object> params,
			PageData pageData) {

		return sysLogDAO.querySysLogPage(params, pageData);
	}

	/**
	 * 删除系统日志
	 */
	public SysLog deleteSysLogById(Integer id) {
		
		return (SysLog) sysLogDAO.delete(id);
	}

	/**
	 * 根据ID查询系统日志
	 */
	public SysLog querySysLogById(Integer id) {
		
		return (SysLog) sysLogDAO.find(id);
	}
	/**
	 * 查询用户操作详细信息
	 */
	public List<SysUserOp> querySysUserOpById(int id) {
		return sysUserOpDAO.querySysUserOpById(id);
	}

	/**
	 * 会员管理分页信息
	 */
	public DTablePageModel querySysMemberPage(Map<String, Object> params,
			PageData pageData) {
		return sysMemberDAO.querySysMemberPage(params,pageData);
	}

	/**
	 * 会员给人信息查看
	 */
	public SysMember editorSysMemberById(int memberId) {
		return (SysMember)sysMemberDAO.find(memberId);
	}
	/** 
	 * 会员验证
	 */
	public SysMember memberVerify(String memberTel){
		return (SysMember)sysMemberDAO.memberVerify(memberTel);
	}
	
	/**
	 * 取会员编号最大
	 */
	public List querySysMemberNo() {
		return sysMemberDAO.querySysMemberNo();
	}

	/**
	 * 添加或修改会员信息
	 */
	public SysMember saveOrUpdateMemberInfoDAO(SysMember sysMember) {
		return (SysMember)sysMemberDAO.save(sysMember);
	}
	
	/**根据会员手机号查询会员信息*/
	public SysMember queryContractByMemberTel(String memberTel){
		return (SysMember)sysMemberDAO.queryContractByMemberTel(memberTel);
	}
	
	
	/**根据会员编号查询会员信息*/
	public SysMember queryContractByMemberNo(String memberNo){
		return (SysMember)sysMemberDAO.queryContractByMemberNo(memberNo);
	}
	
	/** 
	 * 删除会员信息 
	 */
	public SysMember deleteSysMemberById(int memberId){
		return (SysMember)sysMemberDAO.delete(memberId);
	}

	/**
	 * App登录
	 */
	public SysMember memberLogin(String memberTel,String memberPassword){
		return (SysMember)sysMemberDAO.memberLogin(memberTel,memberPassword);
	}
	
	@Override
	public SysLog saveOrUpdateSysLog(SysLog sysLog) {
		return (SysLog) sysLogDAO.saveOrUpdate(sysLog);
	}

	/**
	 * 根据ID查询系统菜单
	 */
	public SysMenu querySysMenu(Integer id) {
		return (SysMenu) sysMenuDAO.find(id);
	}

	/** 根据姓名查询用户 **/
	public List<SysUser> querySysUserByName(String username) {
		return sysUserDAO.querySysUserByName(username);
	}

	/** 根据电话查询用户 **/
	public List<SysUser> querySysUserByTel(String tel) {
		return sysUserDAO.querySysUserByTel(tel);
	}

	/** 添加用户角色 **/
	public SysUserRole saveOrUpdateSysRoleUser(SysUserRole sysUserRole) {
		return (SysUserRole) sysRoleAuthDAO.save(sysUserRole);
	}

	/** 根据用户ID查询用户角色 **/
	public List<SysUserRole> queryRoleByUserId(Integer id) {
		return sysRoleAuthDAO.queryRoleByUserId(id);
	}

	@Override
	public SysUserRole deleteSysUserRole(SysUserRole sysUserRole) {
		return (SysUserRole) sysRoleAuthDAO.delete(sysUserRole);
	}

	@Override
	public DTablePageModel querySysDeptUserPage(Map<String, Object> params,
			PageData pageData) {
		return sysDeptDAO.querySysUserPage(params, pageData);
	}
	
	/**会员兑换码*/
	public DTablePageModel memberCdkeyPage(Map<String, Object> params, PageData pageData){
		DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = sysMemberDAO.memberCdkeyPage(params, pageData);
        }
        return pageModel;
	}
	
	/**会员兑换码新增*/
	public MemberCdkey memberCdkeyAdd(MemberCdkey memberCdkey){
		return (MemberCdkey)sysMemberDAO.save(memberCdkey);
	}
	
	
	/**APP操作信息*/
	public AppOp saveOrUpdateAppOp(AppOp appOp){
		return (AppOp)sysMemberDAO.save(appOp);
	}
	/**APP操作信息List*/
	public List<AppOp> AppOpList(int memberId){
		return sysMemberDAO.AppOpList(memberId);
	}
	
	/**
	 * APP会员地址
	 */
	public List<AppMemberAddress> AppMemberAddressList(int memberId){
		return sysMemberDAO.AppMemberAddressList(memberId);
	}
	
	/**APP会员地址新增或者修改*/
	public AppMemberAddress saveOrUpdateAppMemberAddress(AppMemberAddress appMemberAddress){
		return (AppMemberAddress)sysMemberDAO.save(appMemberAddress);
	}
	
	/**APP会员地址删除*/
	public AppMemberAddress AppMemberAddressDelete(AppMemberAddress appMemberAddress){
		return (AppMemberAddress)sysMemberDAO.delete(appMemberAddress);
	}
	
	/**APP会员地址默认*/
	public int updateAddressTolerant(Map<String, Object> params){
		return sysMemberDAO.updateAddressTolerant(params);
	}
	/**APP下订单*/
	public OrderPay saveOrUpdateOrderPay(OrderPay orderPay){
		return (OrderPay)sysMemberDAO.save(orderPay);
	}

	@Override
	public List<String> queryMenuNameByUserId(Integer userId) {
		return sysMenuDAO.queryMenuNameByUserId(userId);
	}
	
	/**APP消费记录新增*/
	public AppConsumeRecord AppConsumeRecordAdd(AppConsumeRecord appConsumeRecord){
		return (AppConsumeRecord)sysMemberDAO.save(appConsumeRecord);
	}
	
	/**APP生成收货地址*/
	public AppConsigneeAddress AppConsigneeAddressAdd(AppConsigneeAddress appConsigneeAddress){
		return (AppConsigneeAddress)sysMemberDAO.save(appConsigneeAddress);
	}
	
	/**APP版本信息*/
	public AppVersionsInfo AppVersionsInfoSelType(String versionstype){
		return sysMemberDAO.AppVersionsInfoSelType(versionstype);
	}
	/**APP商品查询库存*/
	public GoodsInfo AppGoodsInventory(Integer goodsId,Integer goodsNumber){
		return sysMemberDAO.AppGoodsInventory(goodsId,goodsNumber);
	}
	
	/**APP活跃度Add*/
	public AppLiveness addAppLiveness(AppLiveness appLiveness){
		return (AppLiveness)sysMemberDAO.save(appLiveness);
	}
}
