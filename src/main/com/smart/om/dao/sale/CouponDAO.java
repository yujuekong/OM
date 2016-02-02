package com.smart.om.dao.sale;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.ActivityCoupon;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.PageData;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by hxt on 2015/11/23.
 */
public class CouponDAO extends BaseDao {
    /**
     * 分页优惠劵
     **/
    public DTablePageModel queryCouponPage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from ActivityCoupon model where 1=1 ");
        hqlCount.append("select count(*) from ActivityCoupon model where 1=1 ");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and model.couponName = '").append(keyword).append("' ");
                    hqlCount.append(" and model.couponName = '").append(keyword).append("' ");
                }

            }
            if (params.containsKey("startDate")) {//开始时间
                String startDate = (String) params.get("startDate");
                if (StringUtils.isNotBlank(startDate)) {
                    hql.append(" and model.startDate >= '").append(startDate).append("' ");
                    hqlCount.append(" and model.startDate >= '").append(startDate).append("' ");
                }
            }
            if (params.containsKey("endDate")) {//结束时间
                String endDate = (String) params.get("endDate");
                if (StringUtils.isNotBlank(endDate)) {
                    hql.append(" and model.endDate <= '").append(endDate).append("' ");
                    hqlCount.append(" and model.endDate <= '").append(endDate).append("' ");
                }
            }
            
            if (params.containsKey("app")) {
            	String app = params.get("app").toString();
                if(StringUtils.isNotBlank(app)){
                	hql.append(" and model.getCouponEndDate >='").append(DateUtil.getDateY_M_D()).append("' ");
                    hqlCount.append(" and model.getCouponEndDate >='").append(DateUtil.getDateY_M_D()).append("' ");
                    String isAll = params.get("isAll").toString();
                    if("0".equals(isAll)){
                    	hql.append(" and model.couponNumber !=0 ");
                        hqlCount.append(" and model.couponNumber !=0");
                    }else if("1".equals(isAll)){
                    	String memberId = params.get("memberId").toString();
                        if(StringUtils.isNotBlank(memberId)){
                        	hql.append(" and model.couponId in (select couponId from ActivityMemberCoupon where memberId ="+memberId+" and isUse <> '1') ");
                            hqlCount.append(" and model.couponId in (select couponId from ActivityMemberCoupon where memberId ="+memberId+"  and isUse <> '1')");
                        }
                    }
                }
            }
        }
        hql.append(" order by model.couponAmount desc  ");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }
    
    /**
     * 已领取优惠劵
     **/
    public List<ActivityCoupon> queryIsCouponPage(Map<String, Object> params) {
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from ActivityCoupon model where 1=1 ");
        if (params != null) {
           
            if (params.containsKey("app")) {
            	String app = params.get("app").toString();
                if(StringUtils.isNotBlank(app)){
                	hql.append(" and model.getCouponEndDate >='").append(DateUtil.getDateY_M_D()).append("' ");
                    hqlCount.append(" and model.getCouponEndDate >='").append(DateUtil.getDateY_M_D()).append("' ");
                    String isAll = params.get("isAll").toString();
                    if("1".equals(isAll)){
                    	String memberId = params.get("memberId").toString();
                        if(StringUtils.isNotBlank(memberId)){
                        	hql.append(" and model.couponId in (select couponId from ActivityMemberCoupon where memberId ="+memberId+") ");
                            hqlCount.append(" and model.couponId in (select couponId from ActivityMemberCoupon where memberId ="+memberId+")");
                        }
                    }
                }
            }
        }
        hql.append(" and model.couponNumber !=0 ");
        List<ActivityCoupon> list = this.find(hql.toString(), null);
        return list;
    }
    
    
    /**
     * 更新优惠券数量
     */
    public int updateCouponNumber(String couponId){
    	String hql = "update ActivityCoupon set couponNumber = couponNumber-1  where couponId =  " + couponId;
		return this.updateHql(hql, new Object[]{});
    }
    
    /**
     * 修改优惠券状态
     * @param activityMemberCoupon
     * @return
     */
    public int updateMemberCouponIsUser(String memberId,String couponId){
    	String hql = "update ActivityMemberCoupon set isUse = '1' where couponId =" + couponId +" and memberId ="+memberId;
    	return this.updateHql(hql, new Object[]{});
    }
}
