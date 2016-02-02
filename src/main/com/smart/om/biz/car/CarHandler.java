package com.smart.om.biz.car;

import java.util.List;
import java.util.Map;

import com.smart.om.persist.CarInfo;
import com.smart.om.persist.CarLine;
import com.smart.om.persist.CarLineNode;
import com.smart.om.persist.CarTrack;
import com.smart.om.persist.GpsDevice;
import com.smart.om.persist.SysDict;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

public interface CarHandler {

	/** 分页查询车辆信息 **/
	public DTablePageModel queryCarInfo(Map<String, Object> params, PageData pageData);
	
	/** 查询所有车辆信息 **/
	public List<CarInfo> queryAllCar();
	
	/** 添加或修改车辆信息 **/
	public CarInfo saveOrUpdateCarInfo(CarInfo carInfo);

	/** 删除车辆信息 **/
	public CarInfo delCarById(Integer carId);
	
	/** 根据车辆ID查询车辆信息 **/
	public CarInfo queryCarById(Integer id);
	
	public List<SysDict> queryDictList(String str);
	
	/** 查询所有车辆FOR MAP **/
	public List<CarInfo> queryAllCarForMap(Integer orgId);
	/***************************************************/
	
	/** 分页查询车辆线路信息 **/
	public DTablePageModel queryCarLine(Map<String, Object> params, PageData pageData);
	
	/** 根据车辆线路ID查询车辆线路信息 **/
	public CarLine queryCarLineById(Integer id);
	
	/** 添加或修改车辆线路信息 **/
	public CarLine saveOrUpdateCarLine(CarLine carLine);

	/** 删除车辆线路信息 **/
	public CarLine delCarLineById(Integer id);
	
	/***************************************************/
	/** 分页查询车辆线路节点信息 **/
	public DTablePageModel querycarLineNode(Map<String, Object> params,
			PageData pageData);
	/** 根据车辆线路节点ID查询车辆线路节点信息 **/
	public CarLineNode queryCarlineNodeById(Integer id);
	/** 添加或修改车辆线路节点信息 */
	public CarLineNode saveOrUpdateCarlineNode(CarLineNode carLineNode);
	/** 删除车辆线路节点信息 **/
	public CarLineNode deleteCarlineNode(Integer id);
	/**根据线路ID查询所有的节点**/		
	public List<CarLineNode> queryNodeByCarLineId(Integer carLineId);
	/**根据线路ID更新数据**/		
	public int updateLineId(Integer carLineId);
	/**根据商圈ID查询所有的节点**/		
	public List<CarLineNode> queryNodeByDistrictId(Integer nodePostionId);
	
	/*************************车载GPS***********************/
	/**保存GPS设备位置信息**/
	public CarTrack saveOrUpdateCarTrack(CarTrack carTrack);
	/**根据IMEI号查找车辆信息**/
	public GpsDevice queryGpsDeviceByImei(String imei);
	/** 根据时间和车辆查找相同的位置信息 **/
	public List<CarTrack> queryCarTrackByTime(Integer carId,String time,String carLng,String carLat); 
	
	public CarTrack queryCarTrackByCarId(Integer carId);
	/** 根据时间、车辆ID获取车辆轨迹集合 **/
	public List<CarTrack> queryCarTrack(Integer carId,String startTime,String endTime); 
	
}