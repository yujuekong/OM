package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * SysLog entity. @author MyEclipse Persistence Tools
 */

public class SysLog extends BasePo {

	// Fields

	private Integer logId;
	private String logTime;
	private String logUsername;
	private String logRelname;
	private String logType;
	private String logContent;
	private String logIp;

	// Constructors

	/** default constructor */
	public SysLog() {
	}

	/** full constructor */
	public SysLog(String logTime, String logUsername, String logRelname,
			String logType, String logContent) {
		this.logTime = logTime;
		this.logUsername = logUsername;
		this.logRelname = logRelname;
		this.logType = logType;
		this.logContent = logContent;
	}

	// Property accessors

	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getLogTime() {
		return this.logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public String getLogUsername() {
		return this.logUsername;
	}

	public void setLogUsername(String logUsername) {
		this.logUsername = logUsername;
	}

	public String getLogRelname() {
		return this.logRelname;
	}

	public void setLogRelname(String logRelname) {
		this.logRelname = logRelname;
	}

	public String getLogType() {
		return this.logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLogContent() {
		return this.logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public String getLogIp() {
		return logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

}