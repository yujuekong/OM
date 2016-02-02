package com.smart.om.web.sale;

import com.smart.om.biz.sale.SaleHandler;
import com.smart.om.persist.ActivityCoupon;
import com.smart.om.util.DTablePageModel;
import com.smart.om.web.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 优惠劵管理
 * Created by hxt on 2015/11/23.
 */
@Namespace("/view/sale/coupon")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class CouponAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(CouponAction.class);

    @Resource
    private SaleHandler saleHandler;


    /**
     * 优惠劵列表分页
     */
    @Action(value = "queryCouponPage")
    public void queryCouponPage() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String startDate = this.getRequestParm().getParameter("startDate");//查询开始日期
            String endDate = this.getRequestParm().getParameter("endDate");//查询结束日期
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("startDate", startDate);
            params.put("endDate", endDate);
            params.put("keyword", keyword);
            params.put("app", "");
            DTablePageModel dtPageModel = saleHandler.queryCouponPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定优惠劵详情
     */
    @Action(value = "queryCouponById", results = {
            @Result(name = SUCCESS, location = "/view/sale/couponDtl.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String queryCouponById() {
        String result = SUCCESS;
        try {
            Integer activityCouponId = Integer.valueOf(this.getRequestParm().getParameter("couponId"));
            if (activityCouponId != null) {
                ActivityCoupon activityCoupon = (ActivityCoupon) saleHandler.queryCouponById(activityCouponId);
                getRequest().put("activityCoupon", activityCoupon);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 新增和修改优惠劵页面跳转
     */
    @Action(value = "preAddOrModifyCoupon", results = {
            @Result(name = SUCCESS, location = "/view/sale/couponAddOrUpdate.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")

    })
    public String preAddOrModifyCoupon() {
        String result = SUCCESS;
        try {
            String activityCouponId = this.getRequestParm().getParameter("couponId");
            if (StringUtils.isNotBlank(activityCouponId)) {
                Integer couponId = Integer.valueOf(this.getRequestParm().getParameter("couponId"));
                ActivityCoupon activityCoupon = (ActivityCoupon) saleHandler.queryCouponById(couponId);
                getRequest().put("activityCoupon", activityCoupon);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 新增和修改优惠劵信息
     */
    @Action(value = "saveOrUpdateCoupon", results = {
            @Result(name = SUCCESS, location = "/view/sale/couponList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String saveOrUpdateCoupon() {
        String result = SUCCESS;
        try {
            Integer couponId = activityCoupon.getCouponId();
            if (couponId == null) {
                activityCoupon.setNewer(true);
            }
            saleHandler.saveOrUpdateCoupon(activityCoupon);
        } catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 删除优惠劵
     */
    @Action(value = "deleteCoupon", results = {
            @Result(name = SUCCESS, location = "/view/sale/couponList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String deleteCoupon() {
        String result = SUCCESS;
        try {
            Integer typeId = Integer.valueOf(this.getRequestParm().getParameter("couponId"));
            saleHandler.deleteCoupon(typeId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * set and get
     */
    private ActivityCoupon activityCoupon;

    public ActivityCoupon getActivityCoupon() {
        return activityCoupon;
    }

    public void setActivityCoupon(ActivityCoupon activityCoupon) {
        this.activityCoupon = activityCoupon;
    }
}
