package com.smart.om.biz.device.impl;

import com.smart.om.biz.device.DeviceHandler;
import com.smart.om.biz.sys.impl.SysFuncHandlerImpl;
import com.smart.om.dao.device.*;
import com.smart.om.dao.dispatching.DispatchingSubOrderDAO;
import com.smart.om.dao.goods.GoodsInfoDAO;
import com.smart.om.dao.region.ServiceSellerDAO;
import com.smart.om.persist.*;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.PageData;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 设备功能管理业务逻辑类
 * @author lc
 *
 */
@Component("deviceHandler")
public class DeviceHandlerImpl implements DeviceHandler {
	private static final Logger logger = Logger.getLogger(SysFuncHandlerImpl.class);
	
	@Autowired
	private DeviceTypeDAO deviceTypeDAO;
	@Autowired
	private DeviceInfoDAO deviceInfoDAO;
	@Autowired
	private DeviceMaintenanceDAO deviceMaintenanceDAO;
	@Autowired
	private DeviceCleanDAO deviceCleanDAO;
	@Autowired
	private DeviceGoodsDAO deviceGoodsDAO;
	@Autowired
	private DeviceGridDAO deviceGridDAO;
	@Autowired
	private GoodsInfoDAO goodsInfoDAO;
	@Autowired
	private DispatchingSubOrderDAO dSubOrderDAO;
	@Autowired
	private ServiceSellerDAO serviceSellerDAO;
	
	/** 查询设备类型 **/
	public DTablePageModel queryDeviceType(Map<String, Object> params, PageData pageData){
		DTablePageModel pageModel = new DTablePageModel();
		if(pageData != null) {
			pageModel = deviceTypeDAO.queryDeviceType(params, pageData);
		}
		return pageModel;
	}
	/** 查询所有设备类型 **/
	public List<DeviceType> queryAllDeviceType(){
		return deviceTypeDAO.queryAllDeviceType();
	}
	/** 添加或修改设备类型 **/
	public DeviceType saveOrUpdateDeviceTypeDAO(DeviceType deviceType){
		return (DeviceType)deviceTypeDAO.save(deviceType);
	}
	
	/** 删除设备类型 **/
	public DeviceType delDeviceTypeById(Integer deviceTypeId){
		deviceTypeDAO.delDeviceByTypeId(deviceTypeId);
		return (DeviceType)deviceTypeDAO.delete(deviceTypeId);
	}
	/**根据设备类型ID查询设备**/
	public DeviceType queryDTypeById(Integer id) {
	    return (DeviceType)deviceTypeDAO.find(id);
	} 
	
	/*****************************************************************************************/
	
	/** 分页查询设备信息**/
	public DTablePageModel queryDeviceInfo(Map<String, Object> params, PageData pageData){
		DTablePageModel pageModel = new DTablePageModel();
		if(pageData != null) {
			pageModel = deviceInfoDAO.queryDeviceInfo(params, pageData);
		}
		return pageModel;
	}

	@Override
	public DTablePageModel choosePerson(Map<String, Object> params, PageData pageData) {
		DTablePageModel pageModel = new DTablePageModel();
		if(pageData != null) {
			pageModel = deviceInfoDAO.choosePerson(params, pageData);
		}
		return pageModel;
	}

	/**根据设备ID查询设备**/
	public DeviceInfo queryDeviceById(Integer id) {
	    return (DeviceInfo)deviceInfoDAO.find(id);
	} 
	/**根据设备ID删除设备**/
	public DeviceInfo delDeviceById(Integer deviceId) {
	    return (DeviceInfo)deviceInfoDAO.delete(deviceId);
	} 	
	/** 添加或修改设备信息 **/
	public DeviceInfo saveOrUpdateDeviceInfoDAO(DeviceInfo deviceInfo){
		return (DeviceInfo)deviceInfoDAO.save(deviceInfo);
	}
	
	/** 查询所有设备**/
	public List<DeviceInfo> queryAllDevice(){
		return deviceInfoDAO.queryAllDevice();
	}
	/** 查询所有设备**/
	public List<DeviceInfo> queryAllDeviceForMap(Integer orgId){
		return deviceInfoDAO.queryAllDeviceForMap(orgId);
	}
	
	/** 批量删除设备信息 **/
	public int delDeviceInfoByType(Integer deviceTypeId) {
		return deviceInfoDAO.delDeviceInfoByType(deviceTypeId);
	}
	/** 根据设备所在商圈查询 设备所在站点 **/
	public List<CarLineNode> queryDistrict(Integer districtId){
		return deviceInfoDAO.queryDistrict(districtId);
	}
	
	/** 根据设备标号查询设备信息 **/
	public List<DeviceInfo> queryDeviceByNo(String deviceNo) {
		return deviceInfoDAO.queryDeviceByNo(deviceNo);
	}
	/*****************************************************************************************/
	
	/** 分页查询维护设备信息 **/
	public DTablePageModel queryDeviceMain(Map<String, Object> params, PageData pageData){
		DTablePageModel pageModel = new DTablePageModel();
		if(pageData != null) {
			pageModel = deviceMaintenanceDAO.queryDeviceMain(params, pageData);
		}
		return pageModel;
	}
	
	/**查询个人维护任务**/
	public List<DeviceMaintenance> queryDeviceMaintenanceList(Integer userId){
		return deviceMaintenanceDAO.queryDeviceMaintenanceList(userId);
	}
	/**根据设备ID删除维护设备**/
	public DeviceMaintenance delDeviceMainById(Integer deviceMainId) {
	    return (DeviceMaintenance)deviceMaintenanceDAO.delete(deviceMainId);
	} 	
	/** 添加或修改维护设备维护信息 **/
	public DeviceMaintenance saveOrUpdateDeviceMainDAO(DeviceMaintenance deviceMaintenance){
		return (DeviceMaintenance)deviceMaintenanceDAO.save(deviceMaintenance);
	}
	/** 根据维护ID查询设备维护信息 **/
	public DeviceMaintenance queryDeviceMainById(Integer deviceMainId){
		return (DeviceMaintenance)deviceMaintenanceDAO.find(deviceMainId);
	}
	/** 根据设备ID查询设备维护信息 **/
	public List<DeviceMaintenance> queryDMainByDeviceId(Integer deviceId){
		return deviceMaintenanceDAO.queryDMainByDeviceId(deviceId);
	}
	
	/*****************************************************************************************/
	
	/** 分页查询设备清洗信息 **/
	public DTablePageModel queryDeviceClean(Map<String, Object> params, PageData pageData){
		DTablePageModel pageModel = new DTablePageModel();
		if(pageData != null) {
			pageModel = deviceCleanDAO.queryDeviceClean(params, pageData);
		}
		return pageModel;
	}
	/**查询个人清洗任务**/
	public List<DeviceClean> queryDeviceCleanList(Integer userId){
		return deviceCleanDAO.queryDeviceCleanList(userId);
	}
	
	/**根据清洗设备ID删除清洗信息**/
	public DeviceClean delDeviceCleanById(Integer deviceCleanId) {
	    return (DeviceClean)deviceCleanDAO.delete(deviceCleanId);
	} 	
	/** 添加或修改设备清洗信息信息 **/
	public DeviceClean saveOrUpdateDeviceCleanDAO(DeviceClean deviceClean){
		return (DeviceClean)deviceCleanDAO.save(deviceClean);
	}
	
	/** 分页查询所有用户信息 **/
	public DTablePageModel queryUser(Map<String, Object> params, PageData pageData){
		DTablePageModel pageModel = new DTablePageModel();
		if(pageData != null) {
			pageModel = deviceCleanDAO.queryUser(params, pageData);
		}
		return pageModel;
	}
	/** 根据设备ID查询设备信息 **/
	public DeviceClean queryDeviceCleanById(Integer deviceId){
		return (DeviceClean)deviceCleanDAO.find(deviceId);
	}
	
	
	/***********************************************************/
	/** 查询设备商品**/
	public DTablePageModel queryDeviceGoods(Map<String, Object> params, PageData pageData){
		DTablePageModel pageModel = new DTablePageModel();
		if(pageData != null) {
			pageModel = deviceGoodsDAO.queryDeviceGoods(params, pageData);
		}
		return pageModel;
	}
	/** 查询设备商品**/
	public DTablePageModel queryDeviceGoodsByDevice(Map<String, Object> params, PageData pageData){
		DTablePageModel pageModel = new DTablePageModel();
		if(pageData != null) {
			pageModel = deviceGoodsDAO.queryDeviceGoodsByDevice(params, pageData);
		}
		return pageModel;
	}
	/** 根据设备id查询所有设备商品 **/
	public List<DeviceGoods> queryDeviceGoodsBydeviceId(Integer deviceId){
		return deviceGoodsDAO.queryDeviceGoodsBydeviceId(deviceId);
	}
	
	/**
     * APP查询设备格子商品
     **/
	public GoodsInfo AppSelDeviceGoods(Integer deviceId,String grNo){
    	return deviceGoodsDAO.AppSelDeviceGoods(deviceId,grNo);
    }
	/** 添加或修改设备商品 **/
	public DeviceGoods saveOrUpdateDeviceGoods(DeviceGoods deviceGoods){
		return (DeviceGoods)deviceGoodsDAO.save(deviceGoods);
	}
	
	/** 根据设备ID删除设备商品**/
	public int delDeviceGoodsById(Integer deviceId){
		return deviceGoodsDAO.delDeviceGoodsById(deviceId);
	}
	/** 根据设备商品ID删除设备商品**/
	public int delById(Integer deviceGoodsId){
		return  deviceGoodsDAO.delById(deviceGoodsId);
	}
	/**根据设备商品ID查询设备商品**/
	public DeviceGoods queryDeviceGoodsById(Integer id) {
	    return (DeviceGoods)deviceGoodsDAO.find(id);
	} 
	/**************************************************************/
	/** 添加或修改设备格子**/
	public DeviceGrid saveDeviceGrid(DeviceGrid deviceGrid){
		return (DeviceGrid)deviceGridDAO.save(deviceGrid);
	}
	
	/** 根据设备id查询所有设备商品 **/
	public List<DeviceGrid> queryDeviceGridBydeviceId(Integer deviceId){
		return deviceGridDAO.queryDeviceGridBydeviceId(deviceId);
	}
	
	/**生成刀片更新任务 **/
	public void queryDeviceByPeriod(){
		//根据刀片名称查询刀片ID
		GoodsInfo goodsInfo = goodsInfoDAO.getGoodsInfoByName("刀片");
		//根据杯子名称查询杯子ID
		//GoodsInfo goodsInfoCup = goodsInfoDAO.getGoodsInfoByName("杯子");
		
		//查询所有设备
    	List<DeviceInfo> deviceInfoList=deviceInfoDAO.queryDeviceByPeriod(DateUtil.beforNowDay(Const.BLADE));
    	if(deviceInfoList!=null){
    		for (int i = 0; i < deviceInfoList.size(); i++) {
    			DeviceInfo deviceInfo = deviceInfoList.get(i);
    			
    			List<DispatchingSubOrder> dispatchingSubOrderList =dSubOrderDAO.querydSubOrder(goodsInfo.getGoodsId(),deviceInfo.getDeviceId());
    			if(dispatchingSubOrderList==null){

        			DispatchingSubOrder dispatchingSubOrder = new DispatchingSubOrder();
        			dispatchingSubOrder.setDeviceId(deviceInfo.getDeviceId());
        			dispatchingSubOrder.setDeviceNo(deviceInfo.getDeviceNo());
        			dispatchingSubOrder.setDistrictId(deviceInfo.getDistrictId());
        			MotionDistrict motionDistrict =(MotionDistrict)serviceSellerDAO.find(deviceInfo.getDistrictId());
        			dispatchingSubOrder.setDistrictName(motionDistrict.getDistrictName());
        			dispatchingSubOrder.setGoodsId(goodsInfo.getGoodsId());
        			dispatchingSubOrder.setGoodsName(goodsInfo.getGoodsName());
        			dispatchingSubOrder.setGoodsNumber(1.0);
        			String orderDateNext = DateUtil.getAfterDayOfSpecified(DateUtil.getDateY_M_D());
        			dispatchingSubOrder.setDispatchingTime(orderDateNext);
        			dispatchingSubOrder.setSubOrderStatus("0");
        			dispatchingSubOrder.setRealiGoodsNumber(1.0);
        			dispatchingSubOrder.setDictOrgId(deviceInfo.getDictOrgId());
        			dispatchingSubOrder.setDictRegionId(deviceInfo.getDictRegionId());
        			dispatchingSubOrder.setDictProviceId(deviceInfo.getDictProviceId());
        			dispatchingSubOrder.setGoodsCount(1.0);
        			dispatchingSubOrder.setOrderAllocation("0");
        			dispatchingSubOrder.setNewer(true);
        			
        			dSubOrderDAO.save(dispatchingSubOrder);
        			
//        			if(null!=deviceInfo.getCupCount() && deviceInfo.getCupCount()>=0 && deviceInfo.getCupCount()<Const.CUP_MIX){
//        				
//        				dispatchingSubOrder.setDeviceId(deviceInfo.getDeviceId());
//            			dispatchingSubOrder.setDeviceNo(deviceInfo.getDeviceNo());
//            			dispatchingSubOrder.setDistrictId(deviceInfo.getDistrictId());
//            			dispatchingSubOrder.setDistrictName(motionDistrict.getDistrictName());
//            			dispatchingSubOrder.setGoodsId(goodsInfoCup.getGoodsId());
//            			dispatchingSubOrder.setGoodsName(goodsInfoCup.getGoodsName());
//            			Integer cupCount = Const.CUP_MAX - deviceInfo.getCupCount();
//            			dispatchingSubOrder.setGoodsNumber(0.0);
//            			dispatchingSubOrder.setDispatchingTime(orderDateNext);
//            			dispatchingSubOrder.setSubOrderStatus("0");
//            			dispatchingSubOrder.setRealiGoodsNumber((double)cupCount);
//            			dispatchingSubOrder.setDictOrgId(deviceInfo.getDictOrgId());
//            			dispatchingSubOrder.setDictRegionId(deviceInfo.getDictRegionId());
//            			dispatchingSubOrder.setDictProviceId(deviceInfo.getDictProviceId());
//            			dispatchingSubOrder.setGoodsCount((double)cupCount);
//            			dispatchingSubOrder.setOrderAllocation("0");
//            			dispatchingSubOrder.setNewer(true);
//            			
//            			dSubOrderDAO.save(dispatchingSubOrder);
//        			}
    			}
			}
    	}
	}
}
