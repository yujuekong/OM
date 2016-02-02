package com.smart.om.biz.maintain;

import java.util.List;
import java.util.Map;

import com.smart.om.persist.DeviceMaintain;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

public interface DeviceMaintainHandler {
	/**
     * 查询巡检任务
     */
    DTablePageModel queryDeviceMaintain(Map<String, Object> params, PageData pageData);
    
    /**
     * 根据保养ID查询明细
     * @param maintainId
     * @return
     */
    DeviceMaintain queryDeviceMaintainById(Integer maintainId);
    
    /**
     *根据用户ID查询巡检任务 
     */
    public List<DeviceMaintain> queryDeviceMaintainList(Integer userId);
    /**
     *添加或修改保养记录
     */
    public DeviceMaintain saveOrUpdateDeviceMaintain(DeviceMaintain deviceMaintain);
    
    /**
     * 生成保养任务
     */
    public void queryDeviceMaintainByPeriod();
    
    /**
     * 根据设备ID查询巡检信息
     */
    public List<DeviceMaintain> queryDeviceMaintainByDeviceId(Integer deviceId);
}
