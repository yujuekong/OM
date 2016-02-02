package com.smart.om.web.pay;

import com.smart.om.biz.pay.PayHandler;
import com.smart.om.persist.PayDeviceCash;
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
 * Created by hxt on 2015/11/17.
 * 现金管理
 */
@Namespace("/view/pay/payCash")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class PayCashAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(PayCashAction.class);

    @Resource
    private PayHandler payHandler;


    /**
     * 现金管理分页
     */
    @Action(value = "queryPayCashPage")
    public void queryPayCashPage() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String startDate = this.getRequestParm().getParameter("startDate");//查询开始日期
            String endDate = this.getRequestParm().getParameter("endDate");//查询结束日期
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("startDate", startDate);
            params.put("endDate", endDate);
            params.put("keyword", keyword);
            DTablePageModel dtPageModel = payHandler.queryPayCashPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定现金管理条目详情
     */
    @Action(value = "queryPayCashById", results = {
            @Result(name = SUCCESS, location = "/view/pay/payCashDtl.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String queryPayCashById() {
        String result = SUCCESS;
        try {
            Integer cashId = Integer.valueOf(this.getRequestParm().getParameter("cashId"));
            if (cashId != null) {
                List payDeviceCash = payHandler.queryPayCashById(cashId);
                getRequest().put("payDeviceCash", payDeviceCash);
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
     * 删除现金管理条目
     */
    @Action(value = "deletePayCash", results = {
            @Result(name = SUCCESS, location = "/view/pay/payCashList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String deletePayCash() {
        String result = SUCCESS;
        try {
            Integer cashId = Integer.valueOf(this.getRequestParm().getParameter("cashId"));
            payHandler.deletePayCash(cashId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }
}
