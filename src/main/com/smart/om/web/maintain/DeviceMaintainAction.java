package com.smart.om.web.maintain;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.smart.om.biz.maintain.DeviceMaintainHandler;
import com.smart.om.web.base.BaseAction;

public class DeviceMaintainAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(DeviceMaintainAction.class);
    
    @Resource
    private DeviceMaintainHandler deviceMaintainHandler;
    
    /**
     * 生成保养任务
     */
    public void queryDeviceMaintainByPeriod(){
    	deviceMaintainHandler.queryDeviceMaintainByPeriod();
    }
}
