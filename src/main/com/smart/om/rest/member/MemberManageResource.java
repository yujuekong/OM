package com.smart.om.rest.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.smart.om.biz.device.DeviceHandler;
import com.smart.om.biz.goods.GoodsHandler;
import com.smart.om.biz.sale.SaleHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.ActivityCoupon;
import com.smart.om.persist.AppConsigneeAddress;
import com.smart.om.persist.AppConsumeRecord;
import com.smart.om.persist.AppLiveness;
import com.smart.om.persist.AppMemberAddress;
import com.smart.om.persist.AppOp;
import com.smart.om.persist.AppVersionsInfo;
import com.smart.om.persist.DeviceGoods;
import com.smart.om.persist.GoodsInfo;
import com.smart.om.persist.GoodsIntegral;
import com.smart.om.persist.MemberCdkey;
import com.smart.om.persist.SysMember;
import com.smart.om.rest.base.BaseController;
import com.smart.om.rest.base.BaseResource;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.PageData;
import com.smart.om.util.RandomNumberGenerator;
import com.smart.om.util.Tools;
/**
 * 会员管理Resource 接口
 * @author liuz
 *
 */
@Component("MemberManageResource")
public class MemberManageResource extends BaseResource{
	private static final Logger logger = Logger.getLogger(MemberManageResource.class);
	@Resource
	private SysFuncHandler sysFuncHandler;
	@Resource
    private SaleHandler saleHandler;
	@Resource
    private DeviceHandler deviceHandler;
	@Resource
    private GoodsHandler goodsHandler;
	
	/**
	 * 会员验证
	 */
	public String memberVerify(Map dataMap){
		String result = "";
		String memberTel = dataMap.get("memberTel").toString().trim();
		SysMember sysMember = sysFuncHandler.memberVerify(memberTel);
		if(null!=sysMember){
			return this.toResultJsonSuccess("1");
		}else{
			return this.toResultJsonSuccess("0");
		}
	}

	/**
	 * 会员注册
	 * @param dataMap
	 * @return
	 */
	public String memberRegister(Map dataMap){
		String result = "";
		String memberTel = dataMap.get("memberTel").toString();
		String memberPassword = dataMap.get("memberPassword").toString();
		String isRegister = dataMap.get("isRegister").toString();
		try {
			SysMember sysMember = new SysMember();
			if("0".equals(isRegister)){
				sysMember.setNewer(true);
			}else{
				sysMember = sysFuncHandler.queryContractByMemberTel(memberTel);
				
				//保存用户操作信息
				AppOp appOp = new AppOp();
				appOp.setMemberId(sysMember.getMemberId());
				appOp.setAppOpDate(DateUtil.getDateY_M_D());
				appOp.setAppOpDesc("修改密码");
				appOp.setNewer(true);
				sysFuncHandler.saveOrUpdateAppOp(appOp);
			}
			sysMember.setMemberPassword(Tools.md5(memberPassword));
			sysMember.setMemberTel(memberTel);
			sysFuncHandler.saveOrUpdateMemberInfoDAO(sysMember);
			if("0".equals(isRegister)){
				return this.toResultJsonSuccess("注册成功");
			}else{
				return this.toResultJsonSuccess("修改成功");
			}
			
		} catch (Exception e) {
			return this.toFailTipJson("注册失败");
		}
	}
	
	/**
	 * 第三方登录
	 */
	public String memberThirdRegister(Map dataMap){
		SysMember sysMemberBak = new SysMember();
		Map<String,Object> map = new HashMap<String,Object>();
		String thirdRegister = dataMap.get("thirdRegister").toString().trim();
		String logonType = dataMap.get("logonType").toString().trim();
		SysMember sysMember = sysFuncHandler.queryContractByMemberNo(thirdRegister);
		if(null==sysMember || "".equals(sysMember)){
			sysMember = new SysMember();
			sysMember.setMemberNo(thirdRegister);
			sysMember.setNewer(true);
			sysMember.setLogonType(logonType);
			sysMemberBak = sysFuncHandler.saveOrUpdateMemberInfoDAO(sysMember);
			map.put("memberId", sysMemberBak.getMemberId());//登陆用户ID
			map.put("logonType", sysMemberBak.getLogonType());//登陆用户类型
			return this.toResultJsonSuccess(map);
		}else{
			map.put("memberId", sysMember.getMemberId());//登陆用户ID
			map.put("logonType", sysMemberBak.getLogonType());//登陆用户类型
			return this.toResultJsonSuccess(map);
		}
	}
	
	/**
	 * 会员注册
	 * @param dataMap
	 * @return
	 */
	public String memberLogin(Map dataMap){
		Map<String,Object> map = new HashMap<String,Object>();
		String memberTel = Tools.trim(dataMap.get("memberTel"));//用户名称
		String memberPassword = Tools.trim(dataMap.get("memberPassword"));//用户名称
		memberPassword = Tools.md5(memberPassword);
		
		SysMember sysMember = sysFuncHandler.memberLogin(memberTel,memberPassword);
		if(!"".equals(sysMember) && null!=sysMember){
			map.put("memberNo", sysMember.getMemberNo());//登陆用户编号
			map.put("memberTel", sysMember.getMemberTel());//登陆用户账号
			map.put("memberId", sysMember.getMemberId());//登陆用户ID
			map.put("memberIntegrl", sysMember.getMemberIntegrl());//会员积分
			return this.toResultJsonSuccess(map);
		}else{
			return this.toFailTipJson("用户名称和密码错误！");
		}
	}
	
	
	/**
	 * 会员兑换码
	 */
	public String memberCdkeyPage(Map dataMap){
		List<Map<String, Object>> memberCdkeyListMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> params = new HashMap<String, Object>();
		String memberId = Tools.trim(dataMap.get("memberId"));//会员ID
		String pageSize = Tools.trim(dataMap.get("pageSize"));//每页显示条数
		String currentPage = Tools.trim(dataMap.get("currentPage"));//当前页	
		params.put("app", "app");
		params.put("memberId", memberId);
		PageData pageData = this.getPageData(pageSize, currentPage);
		
		DTablePageModel mcPageModel = sysFuncHandler.memberCdkeyPage(params, pageData);
		List<MemberCdkey> mcdkeyList = mcPageModel.getAaData();
		if(mcdkeyList!=null){
			for (int i = 0; i < mcdkeyList.size(); i++) {
				Map<String,Object> map = new HashMap<String,Object>();
				MemberCdkey memberCdkey = mcdkeyList.get(i);
				map.put("cdkeyId", memberCdkey.getCdkeyId());
				map.put("memberId", memberCdkey.getMemberId());
				map.put("cdkey", memberCdkey.getCdkey());
				map.put("cdkeyStatus", memberCdkey.getCdkeyStatus());
				map.put("createDate", memberCdkey.getCreateDate());
				map.put("limitedDate", memberCdkey.getLimitedDate());
				if("0".equals(memberCdkey.getCdkeyStatus())){
					BaseController baseController = new BaseController();
					String accessKoken = baseController.getAccessToken();
					StringBuffer param = new StringBuffer();
					param.append("/PFPROWebAPI/api/JuicerApi/GetBunchCodes?");
					param.append("pageIndex=1&pageSize=10&BunchCode="+memberCdkey.getCdkey()+"&");
					param.append("access_token=" + accessKoken);
					String bunchCode = baseController.extInf(param.toString(), Const.REQUEST_METHOD_GET);
					JSONObject  jsonObject =JSONObject.fromObject(bunchCode);
					JSONArray picList = JSONArray.fromObject(jsonObject.get("BunchCodes"));
					if(!picList.isEmpty()){
						Object obj = picList.get(0);
						if(""!=obj && null!=obj){
							JSONObject jsonObject1=JSONObject.fromObject(obj);
							String Status =jsonObject1.getString("Status");
							map.put("cdkeyStatus", Status);
							if("2".equals(Status)){
								memberCdkey.setCdkeyStatus(Status);
								sysFuncHandler.memberCdkeyAdd(memberCdkey);
							}
						}
					}
					
				}
				memberCdkeyListMap.add(map);
			}
		}
		return this.toResultJsonSuccess(memberCdkeyListMap);
	}
	/**会员兑换码新增*/
	public String memberCdkeyAdd(Map dataMap){
		String memberId = Tools.trim(dataMap.get("memberId"));//会员ID
		String cdkey =RandomNumberGenerator.generateNumber();//会员兑换码
		String cdkeyStatus = Tools.trim(dataMap.get("cdkeyStatus"));//兑换码状态
		String limitedDate = Tools.trim(dataMap.get("limitedDate"));//兑换码有效期
		
		Map<String,Object> map = new HashMap<String,Object>();
		MemberCdkey MemberCdkey = new MemberCdkey();
		MemberCdkey.setMemberId(Integer.parseInt(memberId));
		MemberCdkey.setCdkey(cdkey);
		MemberCdkey.setCreateDate(DateUtil.getDateY_M_D());
		MemberCdkey.setLimitedDate(limitedDate);
		MemberCdkey.setNewer(true);
		if("1".equals(cdkeyStatus)){
			MemberCdkey.setCdkeyStatus("1");
		}else{
			MemberCdkey.setCdkeyStatus("0");
		}
		
		MemberCdkey MemberCdkeyNew =sysFuncHandler.memberCdkeyAdd(MemberCdkey);
		if(MemberCdkeyNew!=null){
			BaseController baseController = new BaseController();
			String accessKoken = baseController.getAccessToken();
			StringBuffer param = new StringBuffer();
			param.append("access_token=" + accessKoken);
			String url ="PFPROWebAPI/api/JuicerApi/AddBunchCodes";
			param.append("&Status=0&BunchCode="+cdkey);
			String msg = baseController.extInf(url, param.toString(),null);
			if(msg.indexOf("1000")<-1){
				return this.toFailTipJson("兑换码生成错误错");
			}
			map.put("SUCCESS_MESSAGE", "成功");
			map.put("SUCCESS_CODE", "0");
			return this.toResultJsonSuccess(map);
		}else{
			return this.toFailTipJson("失败");
		}
	}
	
	/**
	 * APP操作信息
	 */
	public String AppOpList(Map dataMap){
		List<Map<String, Object>> appOpListMap = new ArrayList<Map<String, Object>>();
		String memberId = Tools.trim(dataMap.get("memberId"));//会员ID
		
		List<AppOp> appOplist = sysFuncHandler.AppOpList(Integer.parseInt(memberId));
		if(appOplist!=null){
			for (int i = 0; i < appOplist.size(); i++) {
				Map<String,Object> map = new HashMap<String,Object>();
				AppOp appOp = appOplist.get(i);
				map.put("appOpId", appOp.getAppOpId());
				map.put("memberId", appOp.getMemberId());
				map.put("appOpDate", appOp.getAppOpDate());
				map.put("appOpDesc", appOp.getAppOpDesc());
				appOpListMap.add(map);
			}
		}
		return this.toResultJsonSuccess(appOpListMap);
	}
	
	/**
	 * APP会员地址
	 */
	public String AppMemberAddressList(Map dataMap){
		List<Map<String, Object>> appMemberAddressListMap = new ArrayList<Map<String, Object>>();
		String memberId = Tools.trim(dataMap.get("memberId"));//会员ID
		List<AppMemberAddress> appMemberAddressList = sysFuncHandler.AppMemberAddressList(Integer.parseInt(memberId));
		if(appMemberAddressList!=null){
			for (int i = 0; i < appMemberAddressList.size(); i++) {
				Map<String,Object> map = new HashMap<String,Object>();
				AppMemberAddress appMemberAddress = appMemberAddressList.get(i);
				map.put("memberAddressId", appMemberAddress.getMemberAddressId());
				map.put("memberId", appMemberAddress.getMemberId());
				map.put("address", appMemberAddress.getAddress());
				map.put("userName", appMemberAddress.getUserName());
				map.put("userTel", appMemberAddress.getUserTel());
				map.put("province", appMemberAddress.getProvince());
				map.put("city", appMemberAddress.getCity());
				map.put("region", appMemberAddress.getRegion());
				map.put("isMr", appMemberAddress.getIsMr());
				
				appMemberAddressListMap.add(map);
			}
		}
		return this.toResultJsonSuccess(appMemberAddressListMap);
	}
	
	/**
	 * APP会员地址新增或修改
	 */
	public String AppMemberAddressAddOrUp(Map dataMap){
		Map<String,Object> map = new HashMap<String,Object>();
		String memberAddressId ="";
		if(!"".equals(dataMap.get("memberAddressId")) && null!=dataMap.get("memberAddressId")){
			memberAddressId = Tools.trim(dataMap.get("memberAddressId"));//会员地址ID
		}
		String memberId = Tools.trim(dataMap.get("memberId"));//会员ID
		String address = Tools.trim(dataMap.get("address"));//会员地址
		String userName = Tools.trim(dataMap.get("userName"));//会员ID
		String userTel = Tools.trim(dataMap.get("userTel"));//会员地址
		String province = Tools.trim(dataMap.get("province"));//省
		String city = Tools.trim(dataMap.get("city"));//城市
		String region = Tools.trim(dataMap.get("region"));//区域
		String isNew = Tools.trim(dataMap.get("isNew"));//新增或者修改
		
		AppMemberAddress appMemberAddress = new AppMemberAddress();
		appMemberAddress.setMemberId(Integer.parseInt(memberId));
		appMemberAddress.setAddress(address);
		appMemberAddress.setUserName(userName);
		appMemberAddress.setUserTel(userTel);
		if(!"".equals(province) && null!=province){
			appMemberAddress.setProvince(province);
		}
		if(!"".equals(city) && null!=city){
			appMemberAddress.setCity(city);
		}
		if(!"".equals(region) && null!=region){
			appMemberAddress.setRegion(region);
		}
		
		if(!"".equals(memberAddressId) && null!=memberAddressId){
			appMemberAddress.setMemberAddressId(Integer.parseInt(memberAddressId));
		}
		
		if("0".equals(isNew)){
			appMemberAddress.setNewer(true);
		}
		AppMemberAddress appMemberAddressNew =sysFuncHandler.saveOrUpdateAppMemberAddress(appMemberAddress);
			if(appMemberAddressNew!=null){
				map.put("SUCCESS_MESSAGE", "成功");
				map.put("SUCCESS_CODE", "0");
				return this.toResultJsonSuccess(map);
			}else{
			return this.toFailTipJson("失败");
		}
	}
	
	/**
	 * APP会员地址删除
	 */
	public String AppMemberAddressDelete(Map dataMap){
		Map<String,Object> map = new HashMap<String,Object>();
		String memberAddressId = Tools.trim(dataMap.get("memberAddressId"));//会员ID
		AppMemberAddress appMemberAddress = new AppMemberAddress();
		appMemberAddress.setMemberAddressId(Integer.parseInt(memberAddressId));
		sysFuncHandler.AppMemberAddressDelete(appMemberAddress);
		
		map.put("SUCCESS_MESSAGE", "成功");
		map.put("SUCCESS_CODE", "0");
		return this.toResultJsonSuccess(map);
		
	}
	
	/**
	 * APP会员地址默认
	 */
	public String AppMemberAddressTolerant(Map dataMap){
		
		String memberId = Tools.trim(dataMap.get("memberId"));//会员ID
		String memberAddressId = Tools.trim(dataMap.get("memberAddressId"));//会员ID
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("memberId", memberId);
		params.put("memberAddressId", memberAddressId);
		sysFuncHandler.updateAddressTolerant(params);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("SUCCESS_MESSAGE", "成功");
		map.put("SUCCESS_CODE", "0");
		return this.toResultJsonSuccess(map);
	}
	
	/**
	 * APP下订单
	 */
	public String AppOrderPay(Map dataMap){
		JSONObject dataObject=JSONObject.fromObject(dataMap);
		String goods = dataObject.containsKey("goodsList")?dataObject.getString("goodsList"):null;
		String memberId = dataObject.getString("memberId");//会员ID
		String payNumber = dataObject.getString("payNumber");//支付编码
		String deviceId = dataObject.getString("deviceId");//设备ID
		AppConsumeRecord bakAppConsumeRecord =null;
		String isRegister = dataObject.getString("isRegister");//是否生成兑换码
		if(StringUtils.isNotEmpty(goods)) {
			//解析出库信息的JSON串
			JSONArray goodsList = JSONArray.fromObject(goods);
			for(Object obj:goodsList){
				AppConsumeRecord appConsumeRecord = new AppConsumeRecord();
				JSONObject jsonObject=JSONObject.fromObject(obj);
				String goodsId = jsonObject.getString("goodsId");//商品ID
				String goodsAmount = jsonObject.getString("goodsNumber");//商品数量
				
				appConsumeRecord.setMemberId(Integer.parseInt(memberId));
				appConsumeRecord.setConsumeType(Const.ZHI_FU);
				appConsumeRecord.setGoodsId(Integer.parseInt(goodsId));
				appConsumeRecord.setConsumeTime(DateUtil.getDateY_M_DH_M_SS());
				appConsumeRecord.setConsumeMoney(Double.parseDouble(jsonObject.getString("goodsPrice")));
				appConsumeRecord.setIsRefund("0");
				if(!"".equals(deviceId)){
					appConsumeRecord.setDeviceId(Integer.parseInt(deviceId));
				}
				
				appConsumeRecord.setPayNumber(payNumber);
				if(!"".equals(goodsAmount)){
					appConsumeRecord.setGoodsAmount(Integer.parseInt(goodsAmount.trim()));
				}
				appConsumeRecord.setNewer(true);
				try {
					bakAppConsumeRecord = sysFuncHandler.AppConsumeRecordAdd(appConsumeRecord);
				} catch (Exception e) {
					return this.toFailTipJson("订单生成失败");
				}
			}
		}else{
			return this.toFailTipJson("传入参数出错");
		}
		//生成收货地址
		if(bakAppConsumeRecord!=null){
			AppConsigneeAddress appConsigneeAddress = new AppConsigneeAddress();
			appConsigneeAddress.setConsumeRecordId(bakAppConsumeRecord.getConsumeRecordId());
			appConsigneeAddress.setTel(dataObject.getString("tel"));
			appConsigneeAddress.setUserRealName(dataObject.getString("userRealName"));
			appConsigneeAddress.setUserAddress(dataObject.getString("userAddress"));
			appConsigneeAddress.setCity(dataObject.getString("city"));
			appConsigneeAddress.setRegion(dataObject.getString("region"));
			appConsigneeAddress.setTown(dataObject.getString("town"));
			appConsigneeAddress.setNewer(true);
			
			sysFuncHandler.AppConsigneeAddressAdd(appConsigneeAddress);
		}else{
			return this.toFailTipJson("生成收货地址失败");
		}
		
		//积分和金额修改
		JSONArray goodsList = JSONArray.fromObject(goods);
		for(Object obj:goodsList){
			JSONObject jsonObject=JSONObject.fromObject(obj);
			String goodsId = jsonObject.getString("goodsId");//商品ID
			Integer goodsNumber = Integer.parseInt(jsonObject.getString("goodsNumber").trim());//商品数量
			GoodsIntegral goodsIntegral = saleHandler.getGoodsIntegralByGoodsId(goodsId);
			SysMember sysMember = sysFuncHandler.editorSysMemberById(Integer.parseInt(memberId));
			if(goodsIntegral!=null){
				sysMember.setMemberIntegrl(sysMember.getMemberIntegrl()+goodsIntegral.getIntegralNumber()*goodsNumber);
				if("5".equals(dataMap.get("payNumber").toString())){
					sysMember.setMemberMoney(sysMember.getMemberMoney()-Double.parseDouble(jsonObject.getString("price")));
				}
				try {
					sysFuncHandler.saveOrUpdateMemberInfoDAO(sysMember);
				} catch (Exception e) {
					return this.toFailTipJson("积分和金额修改失败");
				}
			}
			
			if("1".equals(isRegister)){
				//兑换码生成
				String cdkey =RandomNumberGenerator.generateNumber();//会员兑换码
				String limitedDate = Tools.trim(dataObject.getString("limitedDate"));//兑换码有效期
				
				MemberCdkey MemberCdkey = new MemberCdkey();
				MemberCdkey.setMemberId(Integer.parseInt(memberId));
				MemberCdkey.setCdkey(cdkey);
				MemberCdkey.setCreateDate(DateUtil.getDateY_M_D());
				MemberCdkey.setLimitedDate(limitedDate);
				MemberCdkey.setNewer(true);
				MemberCdkey.setCdkeyStatus("0");
				try {
					MemberCdkey MemberCdkeyNew =sysFuncHandler.memberCdkeyAdd(MemberCdkey);
					if(MemberCdkeyNew!=null){
						BaseController baseController = new BaseController();
						String accessKoken = baseController.getAccessToken();
						StringBuffer param = new StringBuffer();
						param.append("access_token=" + accessKoken);
						String url ="PFPROWebAPI/api/JuicerApi/AddBunchCodes";
						param.append("&Status=0&BunchCode="+cdkey);
						String msg = baseController.extInf(url, param.toString(),null);
						if(msg.indexOf("1000")<-1){
							return this.toFailTipJson("服务器兑换码生成错误");
						}
					}else{
						return this.toFailTipJson("兑换码生成失败");
					}
				} catch (Exception e) {
					return this.toFailTipJson("兑换码生成失败");
				}
			}
		}
		
		//保存用户操作信息
		AppOp appOp = new AppOp();
		appOp.setMemberId(Integer.parseInt(memberId));
		appOp.setAppOpDate(DateUtil.getDateY_M_D());
		appOp.setAppOpDesc("成功购买商品");
		appOp.setNewer(true);
		sysFuncHandler.saveOrUpdateAppOp(appOp);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("SUCCESS_MESSAGE", "成功");
		map.put("SUCCESS_CODE", "0");
		return this.toResultJsonSuccess(map);
	}
	
	/**
	 * APP个人中心 
	 */
	public String AppPersonageSel(Map dataMap){
		Map<String,Object> map = new HashMap<String,Object>();
		String memberId = Tools.trim(dataMap.get("memberId"));//会员ID
		SysMember sysMember = sysFuncHandler.editorSysMemberById(Integer.parseInt(memberId));
		Map<String, Object> params = new HashMap<String, Object>();
		String pageSize = "10000";//每页显示条数
		String currentPage = "1";//当前页	
		params.put("app", "app");
		//String isAll = "1";//0：全部  1：自己
		params.put("memberId", memberId);
		params.put("isAll","1");
		PageData pageData = this.getPageData(pageSize, currentPage);
		DTablePageModel dtPageModel = saleHandler.queryCouponPage(params, pageData);
        List<ActivityCoupon> couponInfoList = dtPageModel.getAaData();
		
		map.put("couponSize", couponInfoList.size());//会员优惠券数量
		map.put("memberMoney", sysMember.getMemberMoney());//会员金额
		map.put("memberIntegrl", sysMember.getMemberIntegrl());//会员积分
		return this.toResultJsonSuccess(map);
	}
	
	/**
	 * APP充值
	 * @param dataMap
	 * @return
	 */
	public String AppRecharge(Map dataMap){
		String memberId = dataMap.get("memberId").toString();//会员ID
		SysMember sysMember = sysFuncHandler.editorSysMemberById(Integer.parseInt(memberId));
		if("".equals(sysMember.getMemberMoney()) || null==sysMember.getMemberMoney()){
			sysMember.setMemberMoney(0.00);
		}
		sysMember.setMemberMoney(sysMember.getMemberMoney() + Double.parseDouble(dataMap.get("price").toString()));
		try {
			sysFuncHandler.saveOrUpdateMemberInfoDAO(sysMember);
		} catch (Exception e) {
			return this.toFailTipJson("APP充值失败");
		}
		
		AppConsumeRecord appConsumeRecord = new AppConsumeRecord();
		appConsumeRecord.setMemberId(Integer.parseInt(memberId));
		appConsumeRecord.setConsumeType(Const.CHONG_ZHI);
		appConsumeRecord.setConsumeTime(DateUtil.getDateY_M_DH_M_SS());
		appConsumeRecord.setConsumeMoney(Double.parseDouble(dataMap.get("price").toString()));
		appConsumeRecord.setPayNumber(Const.PAY_CHONGZHI);
		appConsumeRecord.setNewer(true);
		sysFuncHandler.AppConsumeRecordAdd(appConsumeRecord);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("SUCCESS_MESSAGE", "成功");
		map.put("SUCCESS_CODE", "0");
		return this.toResultJsonSuccess(map);
	}
	/**
	 * APP查询设备格子商品
	 * @param dataMap
	 * @return
	 */
	public String AppSelDeviceGoods(Map dataMap){
		List<Map<String, Object>> appGoodsInfoListMap = new ArrayList<Map<String, Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		String deviceId = Tools.trim(dataMap.get("deviceId"));//设备ID
		String grNo = Tools.trim(dataMap.get("grNo"));//格子序号
		if("".equals(grNo) || null==grNo){
			List<DeviceGoods> deviceGoodsList = deviceHandler.queryDeviceGoodsBydeviceId(Integer.parseInt(deviceId));
			if(deviceGoodsList!=null){
				for (int i = 0; i < deviceGoodsList.size(); i++) {
					DeviceGoods deviceGoods = deviceGoodsList.get(i);
					GoodsInfo goodsInfo = (GoodsInfo) goodsHandler.queryGoodsInfoById(deviceGoods.getGoodsId());
					if(goodsInfo!=null){
						map.put("goodsId", goodsInfo.getGoodsId());
						map.put("goodsName",goodsInfo.getGoodsName());
						map.put("goodsPic1",goodsInfo.getGoodsPic1());
						map.put("goodsPic2",goodsInfo.getGoodsPic2());
						map.put("goodsPrice",goodsInfo.getGoodsPrice());
						map.put("goodsDesc",goodsInfo.getGoodsDesc());
						map.put("gridNo", deviceGoods.getGridNo());
						appGoodsInfoListMap.add(map);
					}
				}
			}
		}else{
			GoodsInfo goodsInfo = deviceHandler.AppSelDeviceGoods(Integer.parseInt(deviceId),grNo);
			if(goodsInfo!=null){
				map.put("goodsId", goodsInfo.getGoodsId());
				map.put("goodsName",goodsInfo.getGoodsName());
				map.put("goodsPic1",goodsInfo.getGoodsPic1());
				map.put("goodsPic2",goodsInfo.getGoodsPic2());
				map.put("goodsPrice",goodsInfo.getGoodsPrice());
				map.put("goodsDesc",goodsInfo.getGoodsDesc());
				map.put("gridNo", "");
				appGoodsInfoListMap.add(map);
			}
		}
		return this.toResultJsonSuccess(appGoodsInfoListMap);
	}
	
	/**
	 * APP版本信息
	 * @param dataMap
	 * @return
	 */
	public String AppVersionsInfoSelType(Map dataMap){
		Map<String,Object> map = new HashMap<String,Object>();
		String versionstype = Tools.trim(dataMap.get("versionstype"));//版本类型
		String versionsOn = Tools.trim(dataMap.get("versionsOn"));//版本号
		String livenessName = Tools.trim(dataMap.get("livenessName"));//版本号
		
		AppVersionsInfo appVersionsInfo = null;
		try {
			appVersionsInfo = sysFuncHandler.AppVersionsInfoSelType(versionstype);
		} catch (Exception e) {
			return this.toFailTipJson("查询失败");
		}
		if(appVersionsInfo!=null){
			if(appVersionsInfo.getVersionsOn().equals(versionsOn)){
				map.put("SUCCESS_MESSAGE", "没有需要更新的版本");
				map.put("SUCCESS_CODE", "0");
			}else{
				map.put("SUCCESS_MESSAGE", appVersionsInfo.getApkAddress());
				map.put("SUCCESS_CODE", "0");
			}
		}
		
		AppLiveness appLiveness = new AppLiveness();
		appLiveness.setLivenessName(livenessName);
		appLiveness.setNewer(true);
		sysFuncHandler.addAppLiveness(appLiveness);
		
		return this.toResultJsonSuccess(map);
	}
	
	/**
	 * APP商品查询库存
	 * @param dataMap
	 * @return
	 */
	public String AppGoodsInventory(Map dataMap){
		List<Map<String, Object>> goodsInventoryListMap = new ArrayList<Map<String, Object>>();
		JSONObject dataObject=JSONObject.fromObject(dataMap);
		String goodsInfoList = dataObject.containsKey("goodsInfoList")?dataObject.getString("goodsInfoList"):null;
		if(StringUtils.isNotEmpty(goodsInfoList)) {
			JSONArray goodsInfoArr = JSONArray.fromObject(goodsInfoList);
			for(Object obj:goodsInfoArr){
				JSONObject jsonObject=JSONObject.fromObject(obj);
				Integer goodsId = Integer.parseInt(jsonObject.getString("goodsId"));
				Integer goodsNumber = Integer.parseInt(jsonObject.getString("goodsNumber"));
				Map<String,Object> map = new HashMap<String,Object>();
				GoodsInfo goodsInfo = sysFuncHandler.AppGoodsInventory(goodsId,goodsNumber);
				if(goodsInfo!=null){
					map.put("goodsName",goodsInfo.getGoodsName());
					goodsInventoryListMap.add(map);
				}
			}
		}
		return this.toResultJsonSuccess(goodsInventoryListMap);
	}
}

