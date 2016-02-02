package com.smart.om.biz.region;

import java.util.List;
import java.util.Map;

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
public interface RegionHandler {

	/** 分页查询商家商圈 **/
	public DTablePageModel queryServiceSeller(Map<String, Object> params, PageData pageData);
	
	/** 添加或修改商家商圈 **/
	public MotionDistrict saveOrUpdateServiceSeller(MotionDistrict motionDistrict);

	/** 删除商家商圈 **/
	public MotionDistrict delServiceSellerById(Integer districtId);
	
	/** 根据商家商圈ID查询商家商圈 **/
	public MotionDistrict queryServiceSellerById(Integer districtId);
	
	/** 查询所有商圈 **/
	public List<MotionDistrict> queryAllServiceSeller();
	
	/** 查询所有商圈FOR MAP **/
	public List<MotionDistrict> queryAllMDForMap(Integer orgId);
	/*******************************************************************/
	
	/** 分页查询配送计划 **/
	public DTablePageModel queryDispatchPlan(Map<String, Object> params, PageData pageData);
	
	/** 根据配送计划ID查询配送计划 **/
	public DispatchingPlan queryDispatchPlanById(Integer planId);
	
	/** 添加或修改配送计划**/
	public DispatchingPlan saveOrUpdateDispatchPlan(DispatchingPlan dispatchingPlan);
	
	/** 删除配送计划 **/
	public DispatchingPlan delDispatchPlanById(Integer planId);
	
	/** 查询某些配送线路下的所有设备  **/
	public List<DeviceCarLineNodeDto> queryDeviceByCarLine(Integer carLineId);
	
	/*******************************************************************/
	/** 添加或修改配送商品**/
	public DispatchingGoods saveOrUpdateDispatchGoods(DispatchingGoods dispatchingGoods);
	
	/*******************************************************************/
	/** 添加或修改配送商品**/
	public DispatchingNode saveOrUpdateDispatchNode(DispatchingNode dispatchingNode);
	
	/*******************************************************************/
	
	/** 分页查询子订单数据 **/
	public DTablePageModel queryDSubOrder(Map<String, Object> params, PageData pageData);
	
	/** 根据子订单ID查询子订单 **/
	public DispatchingSubOrder queryDSubOrderById(Integer subOrderId);
	/** 根据多个子订单ID查询子订单 **/
	public List queryDSubOrderByIdS(String subOrderId);
	/** 根据订单ID查询子订单 商品数量**/
	public List queryDSubOrderByOrderId(String orderId,String districtId);
	/** 根据多个子订单ID查询设备ID **/
	public List queryDeviceIdDSubOrderByIdS(String subOrderIds);
	
	/** 添加或修改子订单**/
	public DispatchingSubOrder saveOrUpdateDSubOrder(DispatchingSubOrder dSubOrder);
	
	/** 删除子订单**/
	public DispatchingSubOrder delDSubOrderById(Integer subOrderId);
	/** 根据订单ID删除子订单**/
	public int delDSubOrderByDOrderIdId(Integer DOrderId);
	
	/** 根据订单ID查询 子订单 **/
	public List<DispatchingSubOrder> queryDSubOrderByOrderId(Integer orderId);
	
	/** 添加或修改设备订单**/
	public DeviceOrder saveOrUpdateDeviceOrder(DeviceOrder deviceOrder);
	
	/**根据设备ID和订单ID查询设备订单**/
	public List<DeviceOrder> queryDeviceOrderByOrIdDeId(Integer orderId,Integer deviceId);
	/*******************************************************************/
	
	/** 分页查询订单数据 **/
	public DTablePageModel queryDOrder(Map<String, Object> params, PageData pageData);
	
	/**配送订单个人任务**/
	public List<DispatchingOrder> queryDispatchingOrderList(Integer userId);
	
	/** 根据订单ID查询订单 **/
	public DispatchingOrder queryDOrderById(Integer subOrderId);
	
	/** 根据订单ID  设备ID  商品ID查询 子订单*/
	DispatchingSubOrder queryBladeDcO(Integer orderId,Integer deviceId,Integer goodsId);
	/** 添加或修改订单**/
	public DispatchingOrder saveOrUpdateDOrder(DispatchingOrder dSubOrder);
	
	/** 删除订单**/
	public DispatchingOrder delDOrderById(Integer subOrderId);
	
	/**根据订单ID查询刀片数量**/
	public List bladeCountByOrderId(Integer orderId);
	/**根据订单ID查询商圈**/
	public List dispatchingSubOrderByOrderId(Integer orderId);
	public List subOrderByOrderIdAll(Integer orderId);
	public List subOrderByDeliveryOrderIdAll(Integer deliveryOrderId);
	/**根据订单ID查询子订单明细状态**/
	public List<DispatchingSubOrder> subOrderByOrderId(Integer orderId);
	/**根据商圈ID查询配送物品**/
	public List goodsByDistrictId(Map<String, Object> params);
	/**根据设备ID查询子订单**/
    public List<DispatchingSubOrder> subOrderByDeviceId(Integer deviceId,Integer orderId);
	
	/************************************ 配送小组 ******************************/
	
	/** 查询配送小组分页信息 **/
	public DTablePageModel queryDipatchTeamPage(Map<String, Object> params, PageData pageData);
	
	/** 新增或更新配送小组 **/
	public DispatchingTeam saveOrUpdateDispatchingTeam(DispatchingTeam dispatchingTeam);
	
	/** 根据ID查询配送小组 **/
	public DispatchingTeam queryDispatchingTeamById(Integer id);
	/** 根据ID查询配送小组 **/
	public SysUser userMap(Integer userId);
	
	/** 根据ID删除配送小组 **/
	public DispatchingTeam deleteDispatchingTeam(DispatchingTeam dispatchingTeam);
	
	/** 根据待配送订单ID查询所在配送小组 **/
	public List queryTeamBySubOrderId(Integer id);

	/**根据商圈ID查询特勤小组ID**/
	public List dispatchingTeamIdList(String subOrderIds);
	
	/**根据用户ID查询所在的特勤小组**/
	public List<DispatchingTeam> queryDispatchingTeamByUserId(Integer id);
	
	/** 查询小组成员分页信息 **/
	public DTablePageModel queryTeamUser(Map<String, Object> params, PageData pageData);
	
	/********************************** 巡检管理 ************************************/
	public DTablePageModel queryInspectionPage(Map<String, Object> params, PageData pageData);

}