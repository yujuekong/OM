package com.smart.om.dao.sale;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.ActivityMemberCoupon;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by hxt on 2015/11/23.
 */
public class MemberCouponDAO extends BaseDao {
    /**
     * 会员优惠分页
     **/
    public DTablePageModel queryMemberCouponPage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from ActivityMemberCoupon model where 1=1 ");
        hqlCount.append("select count(*) from ActivityMemberCoupon model where 1=1 ");
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
            if (params.containsKey("memberId")) {
                String memberId = (String) params.get("memberId");
                if (StringUtils.isNotBlank(memberId)) {
                    hql.append(" and model.memberId = '").append(memberId).append("' ");
                    hqlCount.append(" and model.memberId = '").append(memberId).append("' ");
                }
            }
        }
        hql.append(" order by model.couponTime desc  ");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    public List<ActivityMemberCoupon> queryMemberCouponById(Integer memberId) {
        if (memberId != null) {
            String hql = "from ActivityMemberCoupon where memberId=" + memberId;
            return (List<ActivityMemberCoupon>) this.find(hql, null);
        } else {
            return null;
        }
    }

    public int deleteMemberCouponById(Integer memberId) {
        StringBuffer hql = new StringBuffer();
        hql.append("delete from ActivityMemberCoupon where memberId=" + memberId);
        int deleteSize = this.updateHql(hql.toString(), null);
        return deleteSize;
    }
}
