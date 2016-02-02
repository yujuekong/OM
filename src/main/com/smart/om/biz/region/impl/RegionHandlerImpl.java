package com.smart.om.biz.region.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smart.om.biz.region.RegionHandler;
import com.smart.om.biz.sys.impl.SysFuncHandlerImpl;
import com.smart.om.dao.dispatching.DispatchingGoodsDAO;
import com.smart.om.dao.dispatching.DispatchingOrderDAO;
import com.smart.om.dao.dispatching.DispatchingSubOrderDAO;
import com.smart.om.dao.maintain.DeviceMaintainDao;
import com.smart.om.dao.region.DispatchPlanDAO;
import com.smart.om.dao.region.DispatchingTeamDAO;
import com.smart.om.dao.region.ServiceSellerDAO;
import com.smart.om.dto.device.DeviceCarLineNodeDto;
import com.smart.om.persist.DeviceOrder;
import com.smart.om.persist.DispatchingGoods;
import com.smart.om.persist.DispatchingNode;
import com.smart.om.persist.DispatchingOrder;
import com.smart.om.persist.DispatchingPlan;
import com.smart.om.persist.DispatchingSubOrder;
import com.smart.om.persist.DispatchingTeam;
import com.smart.om.persist.MotionDistrict;
import com.smart.om.persist.SysUser;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

/**
 * 配送计划业务逻辑类
 * @author lc
 *
 */
@Component("RegionHandler")
public class RegionHandlerImpl implements RegionHandler {
	private static final Logger logger = Logger.getLogger(SysFuncHandlerImpl.class);
	
	@Autowired
	private ServiceSellerDAO serviceSellerDAO;
	@Autowired
	private DispatchPlanDAO dispatchPlanDAO;
	@Autowired
	private DispatchingSubOrderDAO dSubOrderDAO;
	@Autowired
	private DispatchingOrderDAO dOrderDAO;
	@Autowired
	private DispatchingTeamDAO dispatchTeamDAO;
	@Autowired
	private DispatchingGoodsDAO dispatchingGoodsDAO;
	@Autowired
	private DeviceMaintainDao deviceMaintainDao;
	
	/** 查询商家商圈 **/
	public DTablePageModel queryServiceSeller(Map<String, Object> params, PageData pageData){
		DTablePageModel pageModel = new DTablePageModel();
		if(pageData != null) {
			pageModel = serviceSellerDAO.queryServiceSeller(params, pageData);
		}
		return pageModel;
	}
	/**根据商圈ID查询商圈**/
	public MotionDistrict queryServiceSellerById(Integer districtId) {
	    return (MotionDistrict)serviceSellerDAO.find(districtId);
	} 
	/**根据商圈ID删除商圈**/
	public MotionDistrict delServiceSellerById(Integer districtId) {
	    return (MotionDistrict)serviceSellerDAO.delete(districtId);
	} 	
	/** 添加或修改商圈信息 **/
	public MotionDistrict saveOrUpdateServiceSeller(MotionDistrict motionDistrict){
		return (MotionDistrict)serviceSellerDAO.save(motionDistrict);
	}
	
	/** 查询所有商圈**/
	public List<MotionDistrict> queryAllServiceSeller(){
		return (List<MotionDistrict>)serviceSellerDAO.queryAllServiceSeller(MotionDistrict.class);
	}
	
	/** 查询所有商圈**/
	public List<MotionDistrict> queryAllMDForMap(Integer orgId){
		return (List<MotionDistrict>)serviceSellerDAO.queryAllMDForMap(orgId);
	}
/*******************************************************************/
	
	/**分页查询配送计划 **/
	public DTablePageModel queryDispatchPlan(Map<String, Object> params, PageData pageData){
		DTablePageModel pageModel = new DTablePageModel();
		if(pageData != null) {
			pageModel = dispatchPlanDAO.queryDispatchPlan(params, pageData);
		}
		return pageModel;
	}
	
	/**根据配送计划ID查询配送计划**/
	public DispatchingPlan queryDispatchPlanById(Integer planId) {
		
	    return (DispatchingPlan)dispatchPlanDAO.find(planId);
	} 
	
	/** 添加或修改配送计划 **/
	public DispatchingPlan saveOrUpdateDispatchPlan(DispatchingPlan dispatchingPlan){
		
		return (DispatchingPlan)dispatchPlanDAO.save(dispatchingPlan);
	}
	
	/**根据商圈ID删除商圈**/
	public DispatchingPlan delDispatchPlanById(Integer planId) {
	    return (DispatchingPlan)dispatchPlanDAO.delete(planId);
	} 	
	
	/** 查询所有站点**/
	public List<DeviceCarLineNodeDto> queryDeviceByCarLine(Integer carLineId){
		return (List<DeviceCarLineNodeDto>)dispatchPlanDAO.queryDeviceByCarLine(carLineId);
	}
	
/*******************************************************************/
	/** 添加或修改配送计划 **/
	public DispatchingGoods saveOrUpdateDispatchGoods(DispatchingGoods dispatchingGoods){
		
		return (DispatchingGoods)dispatchingGoodsDAO.save(dispatchingGoods);
	}
/*******************************************************************/
	/** 添加或修改配送站点**/
	public DispatchingNode saveOrUpdateDispatchNode(DispatchingNode dispatchingNode){
		
		return (DispatchingNode)dispatchingGoodsDAO.save(dispatchingNode);
	}
	

/*******************************************************************/
	/** 分页查询子订单数据 **/
	public DTablePageModel queryDSubOrder(Map<String, Object> params, PageData pageData){
		DTablePageModel pageModel = new DTablePageModel();
		if(pageData != null) {
			pageModel = dSubOrderDAO.queryDSubOrder(params, pageData);
		}
		return pageModel;
	}
	/** 根据子订单ID查询子订单 **/
	public DispatchingSubOrder queryDSubOrderById(Integer subOrderId) {
		
	    return (DispatchingSubOrder)dSubOrderDAO.find(subOrderId);
	} 
	/** 根据多个子订单ID查询子订单 **/
	public List queryDSubOrderByIdS(String subOrderIds){
		return dSubOrderDAO.queryDSubOrderByIdS(subOrderIds);
	}
	
	/** 根据订单ID查询子订单 商品数量**/
	public List queryDSubOrderByOrderId(String orderId,String districtId){
		return dSubOrderDAO.queryDSubOrderByOrderId(orderId,districtId);
	}
	
	/** 根据多个子订单ID查询设备ID **/
	public List queryDeviceIdDSubOrderByIdS(String subOrderIds){
		return dSubOrderDAO.queryDeviceIdDSubOrderByIdS(subOrderIds);
	}
	
	/** 添加或修改子订单**/
	public DispatchingSubOrder saveOrUpdateDSubOrder(DispatchingSubOrder dSubOrder){
		
		return (DispatchingSubOrder)dSubOrderDAO.save(dSubOrder);
	}
	
	/** 删除子订单 **/
	public DispatchingSubOrder delDSubOrderById(Integer subOrderId) {
	    return (DispatchingSubOrder)dSubOrderDAO.delete(subOrderId);
	}
	/**根据订单ID删除子订单**/
	public int delDSubOrderByDOrderIdId(Integer DOrderId) {
	    return dSubOrderDAO.delDSubOrderByDOrderIdId(DOrderId);
	}
	
	/**根据订单ID查询子订单 **/
	public List<DispatchingSubOrder> queryDSubOrderByOrderId(Integer orderId){
		return (List<DispatchingSubOrder>)dSubOrderDAO.queryDSubOrderByOrderId(orderId);
	}
	
	/** 添加或修改设备订单**/
	public DeviceOrder saveOrUpdateDeviceOrder(DeviceOrder deviceOrder){
		return (DeviceOrder)dSubOrderDAO.save(deviceOrder);
	}
	
	/**根据设备ID和订单ID查询设备订单**/
	public List<DeviceOrder> queryDeviceOrderByOrIdDeId(Integer orderId,Integer deviceId){
		
		return dSubOrderDAO.queryDeviceOrderByOrIdDeId(orderId,deviceId);
	}
	
	/*******************************************************************/
	/** 分页查询订单数据 **/
	public DTablePageModel queryDOrder(Map<String, Object> params, PageData pageData){
		DTablePageModel pageModel = new DTablePageModel();
		if(pageData != null) {
			pageModel = dOrderDAO.queryDOrder(params, pageData);
		}
		return pageModel;
	}
	
	/**配送订单个人任务**/
	public List<DispatchingOrder> queryDispatchingOrderList(Integer userId){
		return dOrderDAO.queryDispatchingOrderList(userId);
	}
	
	/** 根据订单ID查询订单 **/
	public DispatchingOrder queryDOrderById(Integer orderId) {
		
	    return (DispatchingOrder)dOrderDAO.find(orderId);
	} 
	
	/** 根据订单ID  设备ID  商品ID查询 子订单*/
	public DispatchingSubOrder queryBladeDcO(Integer orderId,Integer deviceId,Integer goodsId){
		return dSubOrderDAO.queryBladeDcO(orderId,deviceId,goodsId);
	}
	
	/** 添加或修改订单**/
	public DispatchingOrder saveOrUpdateDOrder(DispatchingOrder dOrder){
		
		return (DispatchingOrder)dOrderDAO.save(dOrder);
	}
	
	/** 删除订单 **/
	public DispatchingOrder delDOrderById(Integer orderId) {
	    return (DispatchingOrder)dOrderDAO.delete(orderId);
	} 	
	
	/**根据订单ID查询刀片数量**/
	public List bladeCountByOrderId(Integer orderId){
		return dOrderDAO.bladeCountByOrderId(orderId);
	}
	
	/**根据订单ID查询商圈**/
	public List dispatchingSubOrderByOrderId(Integer orderId){
		return dOrderDAO.dispatchingSubOrderByOrderId(orderId);
	}
	
	/**根据商圈ID查询配送物品**/
	public List goodsByDistrictId(Map<String, Object> params) {
		return dOrderDAO.goodsByDistrictId(params);
	}
	/**根据设备ID查询子订单**/
    public List<DispatchingSubOrder> subOrderByDeviceId(Integer deviceId,Integer orderId){
    	return dOrderDAO.subOrderByDeviceId(deviceId,orderId);
    }
	/**根据订单ID查询子订单明细状态**/
	public List<DispatchingSubOrder> subOrderByOrderId(Integer orderId){
		return dOrderDAO.subOrderByOrderId(orderId);
	}
	/**根据订单ID查询子订单明细状态**/
	public List<DispatchingSubOrder> subOrderByOrderIdAll(Integer orderId){
		return dOrderDAO.subOrderByOrderIdAll(orderId);
	}
	
	public List subOrderByDeliveryOrderIdAll(Integer deliveryOrderId){
		return dOrderDAO.subOrderByDeliveryOrderIdAll(deliveryOrderId);
	}

	/** 配送小组分页信息 **/
	public DTablePageModel queryDipatchTeamPage(Map<String, Object> params,
			PageData pageData) {
		return dispatchTeamDAO.queryDispatchTeamPage(params,pageData);
	}
	/** 根据用户ID查询用户信息 **/
	public SysUser userMap(Integer userId) {
		return dispatchTeamDAO.userMap(userId);
	}

	/** 新增配送小组 **/
	public DispatchingTeam saveOrUpdateDispatchingTeam(
			DispatchingTeam dispatchingTeam) {
		return (DispatchingTeam) dispatchTeamDAO.saveOrUpdate(dispatchingTeam);
	}

	/** 查询配送小组 **/
	public DispatchingTeam queryDispatchingTeamById(Integer id) {
		return (DispatchingTeam) dispatchTeamDAO.find(id);
	}
	
	/** 删除配送小组 **/
	public DispatchingTeam deleteDispatchingTeam(DispatchingTeam dispatchingTeam) {
		return (DispatchingTeam) dispatchTeamDAO.delete(dispatchingTeam);
	}

	/** 查询配送小组 **/
	public List queryTeamBySubOrderId(Integer id) {
		return dispatchTeamDAO.queryTeamBySubOrderId(id);
	}
	
	/**根据商圈ID查询特勤小组ID**/
	public List dispatchingTeamIdList(String subOrderIds) {
		return dispatchTeamDAO.dispatchingTeamIdList(subOrderIds);
	}
	
	/**根据用户ID查询所在的特勤小组**/
	public List<DispatchingTeam> queryDispatchingTeamByUserId(Integer id){
		return dispatchTeamDAO.queryDispatchingTeamByUserId(id);
	}
	
	/** 查询巡检记录 **/
	public DTablePageModel queryInspectionPage(Map<String, Object> params,
			PageData pageData) {
		return deviceMaintainDao.queryInspectionPage(params,pageData);
	}
	@Override
	public DTablePageModel queryTeamUser(Map<String, Object> params,
			PageData pageData) {
		return dispatchTeamDAO.queryTeamUser(params,pageData);
	}

}
