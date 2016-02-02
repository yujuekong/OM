package com.smart.om.util;
/**
 * 待办消息提醒工具类
 * @author CA
 *
 */
public class MessageUtil {
	
	private String msgAddress;  //提醒消息的位置
	private String msgCount;  //消息的数量
	
	
	public String getMsgAddress() {
		return msgAddress;
	}
	public void setMsgAddress(String msgAddress) {
		this.msgAddress = msgAddress;
	}
	public String getMsgCount() {
		return msgCount;
	}
	public void setMsgCount(String msgCount) {
		this.msgCount = msgCount;
	}
	
	
}
