package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

public class AppVersionsInfo extends BasePo {

    private String versionsOn;
    private String versionsType;
    private String apkAddress;
    private Integer versionsId;
    
	public String getVersionsOn() {
		return versionsOn;
	}
	public void setVersionsOn(String versionsOn) {
		this.versionsOn = versionsOn;
	}
	public String getVersionsType() {
		return versionsType;
	}
	public void setVersionsType(String versionsType) {
		this.versionsType = versionsType;
	}
	public String getApkAddress() {
		return apkAddress;
	}
	public void setApkAddress(String apkAddress) {
		this.apkAddress = apkAddress;
	}
	public Integer getVersionsId() {
		return versionsId;
	}
	public void setVersionsId(Integer versionsId) {
		this.versionsId = versionsId;
	}
}