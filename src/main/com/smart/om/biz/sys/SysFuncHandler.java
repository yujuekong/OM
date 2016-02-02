package com.smart.om.biz.sys;

import java.util.List;
import java.util.Map;

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
import com.smart.om.persist.SysRole;
import com.smart.om.persist.SysRoleAuth;
import com.smart.om.persist.SysUser;
import com.smart.om.persist.SysUserOp;
import com.smart.om.persist.SysUserRole;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

public interface SysFuncHandler {

	/** 登陆验证 **/
	public List<SysUser> userLogin(SysUser sysAccount);
	
	/** 查询用户 **/
	public List<SysUser> querySysUser(Map<String, Object> params, PageData pageData);
	
	/** 查询角色分页 **/
	public DTablePageModel querySysRolePage(Map<String, Object> params,
			PageData pageData);  
	
	/**查询用户分页信息**/
	public DTablePageModel querySysUserPage(Map<String, Object> params,
			PageData pageData); 
	
	/**查询用户角色信息**/
	public SysRole querySysRoleById(Integer id);
	
	/** 添加用户 **/
	public SysUser saveOrUpdateUser(SysUser sysUser);
	
	/** 添加用户 **/
	public SysUser userSetting(SysUser sysUser);
	
	/** 删除用户 **/
	public SysUser deleteSysUserById(Integer id);
	
	/** 查询所有菜单 **/
	public List<SysMenu> querySysMenu(Integer userId ,String servState);
	
	/** 查询用户所有的菜单 **/
	public List<SysRoleAuth> queryAppMenuByUser(Integer userId);
	
	/** 根据ID查询菜单 **/
	public SysMenu querySysMenu(Integer id);
	
	/**
	 * 根据HQL查询菜单
	 */
	public List<SysMenu> queryMenu(String hqlQuery ,Object[] params);
	
	/** 根据用户名查询用户 **/
	public List<SysUser> querySysUserByName(String username);
	
	/** 根据电话查询用户 **/
	public List<SysUser> querySysUserByTel(String tel);
	
	/** 添加系统用户角色 **/
	public SysUserRole saveOrUpdateSysRoleUser(SysUserRole sysUserRole);
	
	/** 根据用户ID查询所属角色 **/
	public List<SysUserRole> queryRoleByUserId(Integer id);
	
	/** 删除用户所属角色 **/
	public SysUserRole deleteSysUserRole(SysUserRole sysUserRole);
	
	/** 根据用户ID查询菜单名称 **/
	public List<String> queryMenuNameByUserId(Integer userId);
	
	/*********************************************部门管理**********************************************/
	
	/**查询系统部门分页信息**/
	public DTablePageModel querySysDeptPage(Map<String, Object> params,
			PageData pageData);
	
	/**新增系统部门**/
	public SysDept saveOrUpdateDept(SysDept sysDept);
	
	/**查询系统部门**/
	public SysDept querySysDeptById(Integer id);
	
	/**删除系统部门**/
	public SysDept deleteSysDeptById(Integer id);
	
	/**根据ID查询系统负责人**/
	public SysUser querySysUserById(Integer id);
	
	/**定时刷新用户所在坐标*/
	public int updateUserCoord(String userTel,String userLng,String userLat);
	
	/** 查询用户分页 **/
	public DTablePageModel querySysDeptUserPage(Map<String, Object> params,
			PageData pageData);
	
	/*********************************************合同管理**********************************************/
	/** 添加或修改合同 */
	public SysContract saveOrUpdateContract(SysContract sysContract);
	
	/** 分页查询系统合同 */
	public DTablePageModel queryContractPage(Map<String, Object> params,PageData pageData);
	
	/** 根据ID查询合同 */
	public SysContract queryContractById(Integer contractId);
	
	/** 根据id删除合同  */
	public SysContract deleteContractById(Integer contractId);
	
	/*********************************************日志管理**********************************************/
	/** 查询系统日志 **/
	public DTablePageModel querySysLogPage(Map<String, Object> params,PageData pageData); 
	
	/** 删除系统日志 **/
	public SysLog deleteSysLogById(Integer id);
	
	/** 根据ID查询系统日志 **/
	public SysLog querySysLogById(Integer id);
	
	public SysLog saveOrUpdateSysLog(SysLog sysLog);
	
	/*********************************************用户操作**********************************************/
	
	/** 添加或修改用户操作 */
	public SysUserOp saveOrUpdateOp(SysUserOp sysUserOp);
	
	/**用户操作管理分页信息**/
	public DTablePageModel queryUserOpPage(Map<String, Object> params, PageData pageData);
	
	/**查询用户操作详细信息**/
	public List<SysUserOp> querySysUserOpById(int id);
	
	/*********************************************会员管理**********************************************/
	
	/**会员管理分页信息**/
	public DTablePageModel querySysMemberPage(Map<String, Object> params, PageData pageData);
	
	/**会员个人信息查看**/
	public SysMember editorSysMemberById(int memberId);
	
	/**取会员编号最大**/
	public List querySysMemberNo();
	/** 会员验证**/
	public SysMember memberVerify(String memberTel);
	
	/** 添加或修改会员信息 **/
	public SysMember saveOrUpdateMemberInfoDAO(SysMember sysMember);
	
	/**根据会员手机号查询会员信息*/
	public SysMember queryContractByMemberTel(String memberTel);
	
	/**根据会员编号查询会员信息*/
	public SysMember queryContractByMemberNo(String memberNo);
	
	/** 删除会员信息 **/
	public SysMember deleteSysMemberById(int memberId);
	
	/**App登录**/
	public SysMember memberLogin(String memberTel,String memberPassword);
	
	/**会员兑换码*/
	public DTablePageModel memberCdkeyPage(Map<String, Object> params, PageData pageData);
	
	/**会员兑换码新增*/
	MemberCdkey memberCdkeyAdd(MemberCdkey memberCdkey);
	
	/**APP操作信息*/
	public AppOp saveOrUpdateAppOp(AppOp appOp);
	
	/**APP操作信息List*/
	public List<AppOp> AppOpList(int memberId);
	
	/**APP会员地址*/
	public List<AppMemberAddress> AppMemberAddressList(int memberId);
	
	/**APP会员地址新增或者修改*/
	AppMemberAddress saveOrUpdateAppMemberAddress(AppMemberAddress appMemberAddress);
	
	/**APP会员地址删除*/
	public AppMemberAddress AppMemberAddressDelete(AppMemberAddress appMemberAddress);
	/**APP会员地址默认*/
	public int updateAddressTolerant(Map<String, Object> params);
	/**APP下订单*/
	public OrderPay saveOrUpdateOrderPay(OrderPay orderPay);
	/**APP消费记录新增*/
	AppConsumeRecord AppConsumeRecordAdd(AppConsumeRecord appConsumeRecord);
	/**APP生成收货地址*/
	AppConsigneeAddress AppConsigneeAddressAdd(AppConsigneeAddress appConsigneeAddress);
	/**APP版本信息*/
	AppVersionsInfo AppVersionsInfoSelType(String versionstype);
	/**APP商品查询库存*/
	GoodsInfo AppGoodsInventory(Integer goodsId,Integer goodsNumber);
	/**APP活跃度Add*/
	AppLiveness addAppLiveness(AppLiveness appLiveness);
}