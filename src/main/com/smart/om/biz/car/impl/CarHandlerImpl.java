package com.smart.om.biz.car.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smart.om.biz.car.CarHandler;
import com.smart.om.dao.car.CarInfoDAO;
import com.smart.om.dao.car.CarLineDAO;
import com.smart.om.dao.car.CarLineNodeDAO;
import com.smart.om.dao.car.CarTrackDAO;
import com.smart.om.dao.car.GpsDeviceDAO;
import com.smart.om.persist.CarInfo;
import com.smart.om.persist.CarLine;
import com.smart.om.persist.CarLineNode;
import com.smart.om.persist.CarTrack;
import com.smart.om.persist.GpsDevice;
import com.smart.om.persist.SysDict;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

/**
 * 设备功能管理业务逻辑类
 * @author lc
 *
 */
@Component("carHandler")
public class CarHandlerImpl implements CarHandler {
	private static final Logger logger = Logger.getLogger(CarHandlerImpl.class);
	@Autowired
	private CarInfoDAO carInfoDAO;
	@Autowired
	private CarLineDAO carLineDAO;
	@Autowired
	private CarLineNodeDAO carLineNodeDAO;
	@Autowired
	private GpsDeviceDAO gpsDeviceDAO;
	@Autowired
	private CarTrackDAO carTrackDAO;
	
	/** 查询车辆信息 **/
	public DTablePageModel queryCarInfo(Map<String, Object> params, PageData pageData){
		DTablePageModel pageModel = new DTablePageModel();
		if(pageData != null) {
			pageModel = carInfoDAO.queryCarInfo(params, pageData);
		}
		return pageModel;
	}
	/** 添加或修改车辆信息 **/
	public CarInfo saveOrUpdateCarInfo(CarInfo carInfo){
		return (CarInfo)carInfoDAO.save(carInfo);
	}
	/** 删除车辆信息 **/
	public CarInfo delCarById(Integer id){
		return (CarInfo)carInfoDAO.delete(id);
	}
	/** 根据ID查询车辆信息 **/
	public CarInfo queryCarById(Integer id){
		return (CarInfo)carInfoDAO.find(id);
	}
	/**
	 * 查询车辆品牌或类型
	 */
	public List<SysDict> queryDictList(String str){
		List<SysDict> sd = new ArrayList<SysDict>();
		sd = carInfoDAO.queryDictList(str);
		return sd;
	}
	/** 查询所有车辆**/
	public List<CarInfo> queryAllCar(){
		return (List<CarInfo>)carInfoDAO.queryAllCar(CarInfo.class);
	}
	/** 根据用户所在分公司查询所有车辆**/
	public List<CarInfo> queryAllCarForMap(Integer orgId){
		return (List<CarInfo>)carInfoDAO.queryAllCarForMap(orgId);
	}
	
	/************************************************/
	/** 查询车辆线路信息 **/
	public DTablePageModel queryCarLine(Map<String, Object> params, PageData pageData){
		DTablePageModel pageModel = new DTablePageModel();
		if(pageData != null) {
			pageModel = carLineDAO.queryCarLine(params, pageData);
		}
		return pageModel;
	}
	
	/** 根据车辆线路ID查询车辆线路信息 **/
	public CarLine queryCarLineById(Integer id){ 
		return (CarLine)carLineDAO.find(id);
	}
	
	/** 添加或修改车辆线路信息 **/
	public CarLine saveOrUpdateCarLine(CarLine carLine){
		return (CarLine)carLineDAO.save(carLine);
	}
	
	/** 删除车辆线路信息 **/
	public CarLine delCarLineById(Integer id){
		return (CarLine)carLineDAO.delete(id);
	}
	/************************************************/	
	/** 分页查询车辆线路节点信息 **/
	@Override
	public DTablePageModel querycarLineNode(Map<String, Object> params,
			PageData pageData) {
		DTablePageModel pageModel = new DTablePageModel();
		if(pageData != null) {
			pageModel = carLineNodeDAO.querycarLineNode(params, pageData);
		}
		return pageModel;
	}
	/** 根据车辆线路节点ID查询车辆线路节点信息 **/
	@Override
	public CarLineNode queryCarlineNodeById(Integer id) {
		return (CarLineNode)carLineNodeDAO.find(id);
	}
	/** 添加或修改车辆线路节点信息 **/
	@Override
	public CarLineNode saveOrUpdateCarlineNode(CarLineNode carLineNode) {
		return (CarLineNode) carLineNodeDAO.save(carLineNode);
	}
	/** 删除车辆线路节点信息 **/
	@Override
	public CarLineNode deleteCarlineNode(Integer id) {
		return (CarLineNode)carLineNodeDAO.delete(id);
	}
	/**根据线路ID查询所有的节点**/	
	public List<CarLineNode> queryNodeByCarLineId(Integer carLineId){
		return (List<CarLineNode>)carLineNodeDAO.queryNodeByCarLineId(carLineId);
	}
	/** 删除车辆线路节点信息 **/
	public int updateLineId(Integer id) {
		return carLineNodeDAO.updateLineId(id);
	}
	/**根据商圈ID查询所有的节点**/	
	public List<CarLineNode> queryNodeByDistrictId(Integer nodePostionId){
		return (List<CarLineNode>)carLineNodeDAO.queryNodeByDistrictId(nodePostionId);
	}
	/**保存Gps位置信息 **/
	public CarTrack saveOrUpdateCarTrack(CarTrack carTrack) {
		return (CarTrack) gpsDeviceDAO.save(carTrack);
	}
	@Override
	public GpsDevice queryGpsDeviceByImei(String imei) {
		return gpsDeviceDAO.queryGpsDeviceByImei(imei);
	}
	@Override
	public List<CarTrack> queryCarTrackByTime(Integer carId, String time,String carLng,String carLat) {
		return carTrackDAO.queryCarTrackByTime(carId,time,carLng,carLat);
	}
	@Override
	public CarTrack queryCarTrackByCarId(Integer carId) {
		return carTrackDAO.queryCarTrackByCarId(carId);
	}
	/** 根据时间、车辆ID获取车辆轨迹集合 **/
	public List<CarTrack> queryCarTrack(Integer carId,String startTime,String endTime) {
		return carTrackDAO.queryCarTrack(carId,startTime,endTime);
	}
}