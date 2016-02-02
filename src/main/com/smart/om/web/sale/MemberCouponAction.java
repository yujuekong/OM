package com.smart.om.web.sale;

import com.smart.om.biz.sale.SaleHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.ActivityMemberCoupon;
import com.smart.om.persist.SysMember;
import com.smart.om.util.DTablePageModel;
import com.smart.om.web.base.BaseAction;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员优惠劵管理
 * Created by hxt on 2015/11/23.
 */
@Namespace("/view/sale/MemberCoupon")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class MemberCouponAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(MemberCouponAction.class);

    @Resource
    private SaleHandler saleHandler;
    @Resource
    private SysFuncHandler sysFuncHandler;
    private SysMember sysMember;
    List<ActivityMemberCoupon> memberCouponList;
    /**
     * 会员列表
     */
    @Action(value = "queryMemberPage")
    public void queryMemberPage() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String startDate = this.getRequestParm().getParameter("startDate");//查询开始日期
            String endDate = this.getRequestParm().getParameter("endDate");//查询结束日期
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("startDate", startDate);
            params.put("endDate", endDate);
            params.put("keyword", keyword);
            DTablePageModel dtPageModel = sysFuncHandler.querySysMemberPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 会员优惠劵管理
     */
    @Action(value = "queryMemberCouponPage")
    public void queryMemberCouponPage() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String startDate = this.getRequestParm().getParameter("startDate");//查询开始日期
            String endDate = this.getRequestParm().getParameter("endDate");//查询结束日期
            String memberId = this.getRequestParm().getParameter("memberId");//会员ID
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("startDate", startDate);
            params.put("endDate", endDate);
            params.put("keyword", keyword);
            params.put("memberId", memberId);
            DTablePageModel dtPageModel = saleHandler.queryMemberCouponPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 新增和修改会员优惠劵跳转
//     */
//    @Action(value = "preMemberCouponUpdate", results = {
//            @Result(name = SUCCESS, location = "/view/sale/memberCouponUpdate.jsp"),
//            @Result(name = ERROR, location = "/view/error.jsp")
//
//    })
//    public String preMemberCouponUpdate() {
//        String result = SUCCESS;
//        try {
//            String memberIdStr = this.getRequestParm().getParameter("memberId");
//            if (StringUtils.isNotBlank(memberIdStr)) {
//                Integer memberId = Integer.valueOf(this.getRequestParm().getParameter("memberId"));
//                SysMember sysMember = sysFuncHandler.editorSysMemberById(memberId);
//                List<ActivityMemberCoupon> memberCouponList = saleHandler.queryMemberCouponById(memberId);
//                getRequest().put("memberCouponList", memberCouponList);
//
//                getRequest().put("sysMember", sysMember);
//            }
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            this.getRequest().put("where", " 查询所有数据字典");
//            this.getRequest().put("errors", "系统正忙，请联系管理员");
//            result = ERROR;
//        }
//        return result;
//    }
//
//    /**
//     * 新增和修改会员优惠劵
//     */
//    @Action(value = "memberCouponUpdate", results = {
//            @Result(name = SUCCESS, location = "/view/sale/memberCouponList.jsp"),
//            @Result(name = ERROR, location = "/view/error.jsp")
//
//    })
//    public String memberCouponUpdate() {
//        String result = SUCCESS;
//        try {
//            if (memberCouponList!=null) {
//                Integer memberId = sysMember.getMemberId();
//                saleHandler.deleteMemberCouponById(memberId);
//                for (ActivityMemberCoupon activityMemberCoupon : memberCouponList) {
//                    activityMemberCoupon.setNewer(true);
//                    activityMemberCoupon.setMemberId(memberId);
//                    saleHandler.saveOrUpdateMemberCoupon(activityMemberCoupon);
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            this.getRequest().put("where", " 查询所有数据字典");
//            this.getRequest().put("errors", "系统正忙，请联系管理员");
//            result = ERROR;
//        }
//        return result;
//    }


    public List<ActivityMemberCoupon> getMemberCouponList() {
        return memberCouponList;
    }

    public void setMemberCouponList(List<ActivityMemberCoupon> memberCouponList) {
        this.memberCouponList = memberCouponList;
    }

    public SysMember getSysMember() {
        return sysMember;
    }

    public void setSysMember(SysMember sysMember) {
        this.sysMember = sysMember;
    }
}
