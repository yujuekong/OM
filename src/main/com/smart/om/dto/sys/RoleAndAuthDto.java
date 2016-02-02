package com.smart.om.dto.sys;

import java.io.Serializable;

public class RoleAndAuthDto implements Serializable{
	 private String functionId;
     private String moduleId;
     private Integer roldId;
	public Integer getRoldId() {
		return roldId;
	}
	public void setRoldId(Integer roldId) {
		this.roldId = roldId;
	}
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
}
