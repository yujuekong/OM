package com.smart.om.biz.maintain.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smart.om.biz.maintain.DeviceMaintainHandler;
import com.smart.om.dao.device.DeviceInfoDAO;
import com.smart.om.dao.maintain.DeviceMaintainDao;
import com.smart.om.persist.DeviceInfo;
import com.smart.om.persist.DeviceMaintain;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.PageData;

@Component("DeviceMaintainHandler")
public class DeviceMaintainHandlerImpl implements DeviceMaintainHandler {
	@Autowired
    private DeviceMaintainDao deviceMaintainDao;
	@Autowired
	private DeviceInfoDAO deviceInfoDAO;
	/**
     * 查询巡检任务
     */
	@Override
	public DTablePageModel queryDeviceMaintain(Map<String, Object> params,
			PageData pageData) {
		DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
        	pageModel = deviceMaintainDao.queryDeviceMaintain(params, pageData);
        }
		return pageModel;
	}
	
	/**
     * 根据保养ID查询明细
     * @param maintainId
     * @return
     */
	@Override
	public DeviceMaintain queryDeviceMaintainById(Integer maintainId) {
		return (DeviceMaintain) deviceMaintainDao.find(maintainId);
	}
	/**
     *根据用户ID查询巡检任务 
     */
    public List<DeviceMaintain> queryDeviceMaintainList(Integer userId){
    	return deviceMaintainDao.queryDeviceMaintainList(userId);
    }
    
	/**
     *添加或修改保养记录
     */
	@Override
	public DeviceMaintain saveOrUpdateDeviceMaintain(
			DeviceMaintain deviceMaintain) {
		return (DeviceMaintain)deviceMaintainDao.save(deviceMaintain);
	}
	
	/**
     * 生成保养任务
     */
    public void queryDeviceMaintainByPeriod(){
    	//查询所有设备
    	List<DeviceInfo> deviceInfoList=deviceInfoDAO.queryAllDevice();
    	if(deviceInfoList!=null && deviceInfoList.size()>0){
    		for (int i = 0; i < deviceInfoList.size(); i++) {
    			DeviceInfo deviceInfo = deviceInfoList.get(i);
    			//查询设备是否存在巡检表
    			List<DeviceMaintain> dMaintainList=deviceMaintainDao.queryDeviceMaintainByDeviceId(deviceInfo.getDeviceId());
    			if(dMaintainList==null){
    				DeviceMaintain deviceMaintain = new DeviceMaintain();//新建保存对象
    				deviceMaintain.setDeviceId(deviceInfo.getDeviceId());//设备ID
    				deviceMaintain.setDeviceNo(deviceInfo.getDeviceNo());//设备编号
    				deviceMaintain.setLastTime(DateUtil.getDateY_M_D());//上次保养日期
    				deviceMaintain.setMaintainIsFinish("0");
    				deviceMaintain.setNewer(true);
    				deviceMaintainDao.save(deviceMaintain);
    			}else{
    				//查询设备最近一次保养
    				DeviceMaintain dMaintain=deviceMaintainDao.deviceMaintainByDeviceId(deviceInfo.getDeviceId());
    				List<DeviceMaintain> dmList = deviceMaintainDao.queryDeviceMaintainByPeriod(dMaintain.getMaintainId(),DateUtil.beforNowDay(Const.POLLING));
    				if(dmList!=null){
    					DeviceMaintain dm = dmList.get(0);
    					DeviceMaintain deviceMaintain = new DeviceMaintain();//新建保存对象
    					deviceMaintain.setDeviceId(dm.getDeviceId());//设备ID
        				deviceMaintain.setDeviceNo(dm.getDeviceNo());//设备编号
        				deviceMaintain.setLastTime(dm.getThisTime());//上次保养日期
        				deviceMaintain.setMaintainIsFinish("0");
        				deviceMaintain.setNewer(true);
        				deviceMaintainDao.save(deviceMaintain);
    				}
    			}
    		}
    	}
    }
    
    /**
     * 根据设备ID查询巡检信息
     */
    public List<DeviceMaintain> queryDeviceMaintainByDeviceId(Integer deviceId){
    	return deviceMaintainDao.queryDeviceMaintainByDeviceId(deviceId);
    }
    
    /**
     * 根据设备ID查询设备最近一次保养
     */
    public DeviceMaintain deviceMaintainByDeviceId(Integer deviceId){
    	return deviceMaintainDao.deviceMaintainByDeviceId(deviceId);
    }
}
