package com.smart.om.rest.member;

import com.smart.om.biz.sale.SaleHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.ActivityCoupon;
import com.smart.om.persist.ActivityMemberCoupon;
import com.smart.om.rest.base.BaseResource;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.PageData;
import com.smart.om.util.Tools;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 优惠券Resource 接口
 * @author liuz
 *
 */
@Component("CouponInfoResoure")
public class CouponInfoResoure extends BaseResource{
	private static final Logger logger = Logger.getLogger(CouponInfoResoure.class);
	
	@Resource
	private SysFuncHandler sysFuncHandler;
	@Resource
    private SaleHandler saleHandler;
	/**
	 * 查询所有优惠券
	 * @param dataMap
	 * @return
	 */
	public String couponList(Map dataMap){
		List<Map<String, Object>> couponInfoListMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> params = new HashMap<String, Object>();
		String pageSize = Tools.trim(dataMap.get("pageSize"));//每页显示条数
		String currentPage = Tools.trim(dataMap.get("currentPage"));//当前页	
		params.put("app", "app");
		String memberId = Tools.trim(dataMap.get("memberId"));//会员ID
		String isAll = Tools.trim(dataMap.get("isAll"));//0：全部  1：自己
		params.put("memberId", memberId);
		params.put("isAll", isAll);

		PageData pageData = this.getPageData(pageSize, currentPage);
        DTablePageModel dtPageModel = saleHandler.queryCouponPage(params, pageData);
        List<ActivityCoupon> couponInfoList = dtPageModel.getAaData();
        
		if("0".equals(isAll)){
			params.put("isAll", "1");
			List<ActivityCoupon> couponInfoList1= saleHandler.queryIsCouponPage(params);
	        if(couponInfoList!=null){
	        	if(couponInfoList1!=null && couponInfoList1.size()>0){
		        	for (int i = 0; i < couponInfoList.size(); i++) {
		        		ActivityCoupon activityCoupon =couponInfoList.get(i);
        				Map<String,Object> map = new HashMap<String,Object>();
		        		map.put("couponId", activityCoupon.getCouponId());
		        		map.put("couponNumber",activityCoupon.getCouponNumber());
		        		map.put("couponName",activityCoupon.getCouponName());
		        		map.put("endDate",activityCoupon.getEndDate());
		        		map.put("getCouponEndDate",activityCoupon.getGetCouponEndDate());
		        		map.put("couponAmount",activityCoupon.getCouponAmount());
		        		map.put("couponType",activityCoupon.getCouponType());
		        		for (int j = 0; j < couponInfoList1.size(); j++) {
		        			ActivityCoupon activityCoupon1 =couponInfoList1.get(j);
			        		if(activityCoupon.getCouponId().equals(activityCoupon1.getCouponId())){
			        			map.put("isExist","1");
			        			break;
			        		}else{
			        			map.put("isExist","0");
			        		}
			        	}
		        		couponInfoListMap.add(map);
					}
		        }else{
		        	if(couponInfoList!=null){
			        	for (int i = 0; i < couponInfoList.size(); i++) {
			        		Map<String,Object> map = new HashMap<String,Object>();
			        		ActivityCoupon activityCoupon = couponInfoList.get(i);
			        		map.put("couponId", activityCoupon.getCouponId());
			        		map.put("couponNumber",activityCoupon.getCouponNumber());
			        		map.put("couponName",activityCoupon.getCouponName());
			        		map.put("endDate",activityCoupon.getEndDate());
			        		map.put("getCouponEndDate",activityCoupon.getGetCouponEndDate());
			        		map.put("couponAmount",activityCoupon.getCouponAmount());
			        		map.put("couponType",activityCoupon.getCouponType());
			        		map.put("isExist","0");
			        		couponInfoListMap.add(map);
						}
			        }
		        }
	        }
		}else{
			if(couponInfoList!=null){
	        	for (int i = 0; i < couponInfoList.size(); i++) {
	        		Map<String,Object> map = new HashMap<String,Object>();
	        		ActivityCoupon activityCoupon = couponInfoList.get(i);
	        		map.put("couponId", activityCoupon.getCouponId());
	        		map.put("couponNumber",activityCoupon.getCouponNumber());
	        		map.put("couponName",activityCoupon.getCouponName());
	        		map.put("endDate",activityCoupon.getEndDate());
	        		map.put("getCouponEndDate",activityCoupon.getGetCouponEndDate());
	        		map.put("couponAmount",activityCoupon.getCouponAmount());
	        		map.put("couponType",activityCoupon.getCouponType());
	        		map.put("isExist","0");
	        		couponInfoListMap.add(map);
				}
	        }
		}	
        
		return this.toResultJsonSuccess(couponInfoListMap);
	}
	
	/**
	 * 会员领取优惠券
	 * @param dataMap
	 * @return
	 */
	public String getCoupon(Map dataMap){
		String memberId = Tools.trim(dataMap.get("memberId"));//会员ID
		String couponId = Tools.trim(dataMap.get("couponId"));//优惠券ID
		Map<String,Object> map = new HashMap<String,Object>();
		
		ActivityCoupon activityMemberCouponSel = (ActivityCoupon)saleHandler.queryCouponById(Integer.parseInt(couponId));
		try {
			if(activityMemberCouponSel!=null && activityMemberCouponSel.getCouponAmount()>0){
				int num = saleHandler.updateCouponNumber(couponId);
				if(num<1){
					return this.toFailTipJson("更新优惠券数量有误");
				}
				ActivityMemberCoupon activityMemberCoupon = new ActivityMemberCoupon();
				activityMemberCoupon.setNewer(true);
				activityMemberCoupon.setCouponId(Integer.parseInt(couponId));
				activityMemberCoupon.setMemberId(Integer.parseInt(memberId));
				activityMemberCoupon.setCouponTime(DateUtil.getDateY_M_DH_M_SS());
				activityMemberCoupon.setIsUse("0");
				saleHandler.saveOrUpdateMemberCoupon(activityMemberCoupon);
			}else{
				map.put("SUCCESS_MESSAGE", "优惠券数量不足");
				map.put("SUCCESS_CODE", "1");
				return this.toResultJsonSuccess(map);
			}
		} catch (Exception e) {
			return this.toFailTipJson("领取优惠券失败");
		}
		map.put("SUCCESS_MESSAGE", "领取优惠券成功");
		map.put("SUCCESS_CODE", "0");
		return this.toResultJsonSuccess(map);
	}
	
	/**
	 *使用优惠券
	 */
	public String useCoupon(Map dataMap){
		String memberId = Tools.trim(dataMap.get("memberId"));//会员ID
		String couponId = Tools.trim(dataMap.get("couponId"));//优惠券ID
		Map<String,Object> map = new HashMap<String,Object>();
		
		int number = saleHandler.updateMemberCouponIsUser(memberId,couponId);
		if(number>0){
			map.put("SUCCESS_MESSAGE", "优惠券使用成功");
			map.put("SUCCESS_CODE", "0");
			return this.toResultJsonSuccess(map);
		}else{
			return this.toFailTipJson("优惠券使用失败");
		}
	}
}
