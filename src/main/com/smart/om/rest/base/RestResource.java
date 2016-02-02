package com.smart.om.rest.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.restlet.representation.Representation;

import com.smart.om.persist.DeviceInfo;
import com.smart.om.rest.device.DeviceCleanResource;
import com.smart.om.rest.device.DeviceMaintenanceResource;
import com.smart.om.rest.dispatching.DispatchingPlanResource;
import com.smart.om.rest.inventory.DeliveryAgentResoure;
import com.smart.om.rest.inventory.DeliveryOrderResource;
import com.smart.om.rest.inventory.WarehousingResource;
import com.smart.om.rest.job.JobResource;
import com.smart.om.rest.maintain.DeviceMaintainResource;
import com.smart.om.rest.member.CouponInfoResoure;
import com.smart.om.rest.member.GoodsInfoResource;
import com.smart.om.rest.member.MemberManageResource;
import com.smart.om.rest.pay.PayResource;
import com.smart.om.rest.sale.SaleResource;
import com.smart.om.rest.user.UserResource;
import com.smart.om.util.Const;
import com.smart.om.util.SmsUtil;

/**
 * rest接口
 * @author langyuk
 */
public class RestResource  extends BaseResource{
	private static final Logger logger = Logger.getLogger(RestResource.class);
	@Resource
	private UserResource userResource;
	@Resource
	private DeviceMaintenanceResource deviceMaintenanceResource;//设备维修
	@Resource
	private DeviceCleanResource deviceCleanResource;//设备清洗
	@Resource
	private WarehousingResource warehousingResource;//入库
	@Resource
	private DeliveryOrderResource deliveryOrderResource;//出库
	@Resource
	private DeliveryAgentResoure deliveryAgentResoure;//出库清洗
	@Resource
	private DispatchingPlanResource dispatchingPlanResource;//配送
	@Resource
	private PayResource payResource;//支付信息
	@Resource
	private SaleResource saleResource;//销售信息
	@Resource
	private DeviceMaintainResource deviceMaintainResource;//巡检
	@Resource
	private JobResource jobResource;//任务
	@Resource
	private MemberManageResource memberManageResource;//会员
	@Resource
	private GoodsInfoResource goodsInfoResource;//商品
	@Resource
	private CouponInfoResoure couponInfoResoure;//优惠券
	
	public String executeHandler(String method, Map<Object,Object> dataMap,Representation entity,String data) throws Exception {
		if (method == null) {
			return this.toErrorResultJson("非法服务请求！",Const.ERROR_404);
		}
		if ("user.login".equals(method)){//用户登录
			return userResource.login(dataMap);
		}
		else if ("device.queryDeviceInfo".equals(method)){//查询设备信息
			
		}
		else if ("device.queryDeviceMaintenances".equals(method)){//查询维修订单
			if(!"".equals(dataMap.get("deviceNo")) && dataMap.get("deviceNo")!=null){
				List<DeviceInfo> deviceInfoList = new ArrayList<DeviceInfo>();
				deviceInfoList =deviceMaintenanceResource.queryDeviceByNo(dataMap.get("deviceNo").toString());
				if(deviceInfoList!=null && !deviceInfoList.isEmpty()){
					DeviceInfo deviceInfo =deviceInfoList.get(0);
					dataMap.put("deviceId", deviceInfo.getDeviceId());
					dataMap.put("deviceNo", dataMap.get("deviceNo"));
					dataMap.put("userId", dataMap.get("checkUserId"));
					return deviceMaintenanceResource.queryDeviceMaintenances(dataMap);
				}else{
					return this.toErrorResultJson("没有该设备！",Const.ERROR_500);
				}
			}else{
				return deviceMaintenanceResource.queryDeviceMaintenances(dataMap);
			}
		}
		else if ("device.queryDeviceMaintenanceById".equals(method)){//查询维修订单ID,查询详情
			return deviceMaintenanceResource.queryDeviceMaintenanceById(dataMap);
		}
		else if ("device.submitDeviceMaintenance".equals(method)){//提交维修订单
			return deviceMaintenanceResource.submitDeviceMaintenance(data);
		}
		else if ("device.queryDeviceCleans".equals(method)){//查询清洗订单
			if(!"".equals(dataMap.get("deviceNo")) && dataMap.get("deviceNo")!=null){
				List<DeviceInfo> deviceInfoList =deviceMaintenanceResource.queryDeviceByNo(dataMap.get("deviceNo").toString());
				if(deviceInfoList!=null && !deviceInfoList.isEmpty()){
					DeviceInfo deviceInfo =deviceInfoList.get(0);
					dataMap.put("deviceId", deviceInfo.getDeviceId());
					dataMap.put("deviceNo", dataMap.get("deviceNo"));
					dataMap.put("userId", dataMap.get("checkUserId"));
					
					return deviceCleanResource.queryDeviceCleans(dataMap);
				}else{
					return this.toErrorResultJson("没有该设备！",Const.ERROR_500);
				}
			}else{
				return deviceCleanResource.queryDeviceCleans(dataMap);
			}
		}
		else if ("device.queryDeviceCleanById".equals(method)){//查询清洗订单ID，查询详情
			return deviceCleanResource.queryDeviceCleanById(dataMap);
		}
		else if ("device.submitDeviceClean".equals(method)){//提交清洗订单
			return deviceCleanResource.submitDeviceClean(data);
		}
		else if ("inventory.queryInOrder".equals(method)){//查询入库订单
			return warehousingResource.queryWarehousings(dataMap);
		}
		else if("inventory.inOrderDtl".equals(method)){//查询入库订单详情
			return warehousingResource.queryWarehousingById(dataMap);
		}
		else if("inventory.submitWarehousing".equals(method)){//提交入库单
			return warehousingResource.submitWarehousing(data);
		}
		else if("inventory.queryOutOrder".equals(method)){//查询出库订单
			return deliveryOrderResource.queryDeliveryOrders(dataMap);
		}
		else if("inventory.outOrderDtl".equals(method)){//查询出库订单详情
			return deliveryOrderResource.queryDeliveryDtlByDeliveryId(dataMap);
		}
		else if("inventory.submitDelivery".equals(method)){//提交出库单
			return deliveryOrderResource.submitDelivery(data);
		}
		else if("inventory.queryDeliveryAgents".equals(method)){//查询出库清洗
			return deliveryAgentResoure.queryDeliveryAgents(dataMap);
		}
		else if("inventory.queryDeliveryAgentByDeliveryId".equals(method)){//查询出库清洗详情
			return deliveryAgentResoure.queryDeliveryAgentByDeliveryId(dataMap);
		}
		else if("inventory.submitDeliveryReceive".equals(method)){//确认接收订单
			return deliveryAgentResoure.submitDeliveryReceive(dataMap);
		}
		else if("inventory.submitDeliveryAgent".equals(method)){//提交出库清洗
			return deliveryAgentResoure.submitDeliveryAgent(data);
		}
		else if ("dispatching.queryDispatchingPlans".equals(method)){//查询配送订单
			if(!"".equals(dataMap.get("deviceNo")) && dataMap.get("deviceNo")!=null){
				List<DeviceInfo> deviceInfoList =deviceMaintenanceResource.queryDeviceByNo(dataMap.get("deviceNo").toString());
				if(deviceInfoList!=null && !deviceInfoList.isEmpty()){
					DeviceInfo deviceInfo =deviceInfoList.get(0);
					dataMap.put("deviceId", deviceInfo.getDeviceId());
					dataMap.put("deviceNo", dataMap.get("deviceNo"));
					dataMap.put("checkUserId", dataMap.get("checkUserId"));
					return dispatchingPlanResource.queryDispatchingPlans(dataMap);
				}else{
					return this.toErrorResultJson("没有该设备！",Const.ERROR_500);
				}
			}else{
				return dispatchingPlanResource.queryDispatchingPlans(dataMap);
			}
		}
		else if("dispatching.submitOrderStatusReceive".equals(method)){//确认接收订单
			return dispatchingPlanResource.submitOrderStatusReceive(dataMap);
		}
		else if("dispatching.submitAgentReceive".equals(method)){//确认接收订单
			return dispatchingPlanResource.submitAgentReceive(dataMap);
		}
		else if ("dispatching.queryNodesByPlan".equals(method)){//查询配送计划的站点
			return dispatchingPlanResource.queryNodesByPlan(dataMap);
		}
		else if ("dispatching.queryGoodsByNode".equals(method)){//查询配送商品
			return dispatchingPlanResource.queryGoodsByNode(dataMap);
		}
		else if ("dispatching.submitDispatchingGoods".equals(method)){//提交站点的配送商品
			return dispatchingPlanResource.submitDispatchingGoods(dataMap);
		}
		else if ("dispatching.submitDispatchingPlan".equals(method)){//提交配送计划
			return dispatchingPlanResource.submitDispatchingPlan(dataMap);
		}
		else if ("dispatching.submitGoodsNumber".equals(method)){//提交配送计划 实际配送数量
			return dispatchingPlanResource.submitGoodsNumber(dataMap);
		}
		else if ("sale.saleResource".equals(method)){//销售信息管理
			return saleResource.saleInfoAdd(dataMap);
		}
		else if ("sale.querySaleResource".equals(method)){//销售信息管理
			return saleResource.querySaleInfo(dataMap);
		}
		else if ("sale.payResource".equals(method)){//支付完成回调
			return saleResource.saleFinish(dataMap);
		}
		else if("maintain.queryDeviceMaintain".equals(method)){//巡检查询
			return deviceMaintainResource.queryDeviceMaintain(dataMap);
		}
		else if("maintain.queryDeviceMaintainById".equals(method)){//巡检查询明细
			return deviceMaintainResource.queryDeviceMaintainById(dataMap);
		}
		else if("maintain.submitDeviceMaintain".equals(method)){//巡检查询明细
			return deviceMaintainResource.submitDeviceMaintain(dataMap);
		}
		else if("job.queryUserJob".equals(method)){//查询个人任务
			return jobResource.queryUserJob(dataMap);
		}
		else if("sms.sendSms".equals(method)){//根据手机端生成的验证码，通过短信平台发给手机
			return SmsUtil.sendSms(dataMap);
		}
		else if("member.memberVerify".equals(method)){//APP用户验证
			return memberManageResource.memberVerify(dataMap);
		}
		else if("member.memberCdkeyList".equals(method)){//APP用户查询兑换码
			return memberManageResource.memberCdkeyPage(dataMap);
		}
		else if("member.memberCdkeyAdd".equals(method)){//APP用户添加兑换码
			return memberManageResource.memberCdkeyAdd(dataMap);
		}
		else if("member.memberRegister".equals(method)){//APP注册
			return memberManageResource.memberRegister(dataMap);
		}
		else if("member.memberThirdRegister".equals(method)){//APP第三方注册
			return memberManageResource.memberThirdRegister(dataMap);
		}
		else if("member.memberLogin".equals(method)){//对外APP登录
			return memberManageResource.memberLogin(dataMap);
		}
		else if("member.goodsList".equals(method)){//APP查询商品列表
			return goodsInfoResource.goodsList(dataMap);
		}
		else if("member.goodsInfoById".equals(method)){//APP查询商品列表
			return goodsInfoResource.goodsInfoById(dataMap);
		}
		else if("member.couponList".equals(method)){//APP查询优惠券
			return couponInfoResoure.couponList(dataMap);
		}
		else if("member.getCoupon".equals(method)){//APP领取优惠券
			return couponInfoResoure.getCoupon(dataMap);
		}
		else if("member.useCoupon".equals(method)){//APP优惠券使用
			return couponInfoResoure.useCoupon(dataMap);
		}
		else if("member.getDeviceInfo".equals(method)){//APP查询附近设备
			return goodsInfoResource.getDeviceInfo(dataMap);
		}
		else if("member.getDeviceInfo".equals(method)){//APP查询附近设备
			return goodsInfoResource.getDeviceInfo(dataMap);
		}
		else if("member.AppOpList".equals(method)){//APP操作信息
			return memberManageResource.AppOpList(dataMap);
		}
		else if("member.AppMemberAddressList".equals(method)){//APP会员地址
			return memberManageResource.AppMemberAddressList(dataMap);
		}
		else if("member.AppMemberAddressAddOrUp".equals(method)){//APP会员地址新增或修改
			return memberManageResource.AppMemberAddressAddOrUp(dataMap);
		}
		else if("member.AppMemberAddressDelete".equals(method)){//APP会员地址删除
			return memberManageResource.AppMemberAddressDelete(dataMap);
		}
		else if("member.AppMemberAddressTolerant".equals(method)){//APP会员地址默认
			return memberManageResource.AppMemberAddressTolerant(dataMap);
		}
		else if("member.AppOrderPay".equals(method)){//APP下订单
			return memberManageResource.AppOrderPay(dataMap);
		}
		else if("member.AppPersonageSel".equals(method)){//APP个人中心
			return memberManageResource.AppPersonageSel(dataMap);
		}
		else if("member.AppRecharge".equals(method)){//APP充值
			return memberManageResource.AppRecharge(dataMap);
		}
		else if("member.AppSelDeviceGoods".equals(method)){//APP查询设备格子商品
			return memberManageResource.AppSelDeviceGoods(dataMap);
		}
		else if("member.AppVersionsInfoSelType".equals(method)){//APK版本
			return memberManageResource.AppVersionsInfoSelType(dataMap);
		}
		else if("member.AppGoodsInventory".equals(method)){//APP商品查询库存
			return memberManageResource.AppGoodsInventory(dataMap);
		}
		return this.toErrorResultJson("非法服务请求！",Const.ERROR_500);
	}
}
