package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */

public class SysUser extends BasePo {

	// Fields
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private Integer deptId;
	private Integer orgId;
	private String userName;
	private String password;
	private String realName;
	private String sex;
	private String tel;
	private String email;
	private Integer roleId;
	private String userStatus;
	private String lastLoginTime;
	private String createDate;
	private String isDel;
	private Integer dictRegionId;
	private Integer dictProviceId;
	private Integer dictServStation;
	private String dictName;
	private String userCity;
	private String userImage;
	private String userLng;
	private String userLat;
	
	private SysDict sysDict;
	// Constructors

	/** default constructor */
	public SysUser() {
	}

	/** full constructor */
	public SysUser(Integer deptId, Integer orgId, String userName,
			String password, String realName, String sex, String tel,
			String email, Integer roleId, String userStatus,
			String lastLoginTime, String createDate,String userCity) {
		this.deptId = deptId;
		this.orgId = orgId;
		this.userName = userName;
		this.password = password;
		this.realName = realName;
		this.sex = sex;
		this.tel = tel;
		this.email = email;
		this.roleId = roleId;
		this.userStatus = userStatus;
		this.lastLoginTime = lastLoginTime;
		this.createDate = createDate;
		this.userCity = userCity; 
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public Integer getDictRegionId() {
		return dictRegionId;
	}

	public void setDictRegionId(Integer dictRegionId) {
		this.dictRegionId = dictRegionId;
	}

	public Integer getDictProviceId() {
		return dictProviceId;
	}

	public void setDictProviceId(Integer dictProviceId) {
		this.dictProviceId = dictProviceId;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public Integer getDictServStation() {
		return dictServStation;
	}

	public void setDictServStation(Integer dictServStation) {
		this.dictServStation = dictServStation;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public SysDict getSysDict() {
		return sysDict;
	}

	public void setSysDict(SysDict sysDict) {
		this.sysDict = sysDict;
	}

	public String getUserLng() {
		return userLng;
	}

	public void setUserLng(String userLng) {
		this.userLng = userLng;
	}

	public String getUserLat() {
		return userLat;
	}

	public void setUserLat(String userLat) {
		this.userLat = userLat;
	}
	
	
}