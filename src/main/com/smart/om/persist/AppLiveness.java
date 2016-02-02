package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

public class AppLiveness extends BasePo {

    private Integer livenessId;
    private String livenessName;
    
	public Integer getLivenessId() {
		return livenessId;
	}
	public void setLivenessId(Integer livenessId) {
		this.livenessId = livenessId;
	}
	public String getLivenessName() {
		return livenessName;
	}
	public void setLivenessName(String livenessName) {
		this.livenessName = livenessName;
	}
}