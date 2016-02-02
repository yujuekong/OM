package com.smart.om.web.sale;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import com.smart.om.biz.sale.SaleHandler;
import com.smart.om.persist.OrderPay;
import com.smart.om.util.DTablePageModel;
import com.smart.om.web.base.BaseAction;

/**
 * 订单支付管理
 * Created by hxt on 2015/11/28.
 */
@Namespace("/view/sale/orderPay")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class OrderPayAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(OrderPayAction.class);

    @Resource
    private SaleHandler saleHandler;

    /**
     * 订单支付分页
     */
    @Action(value = "queryOrderPayPage")
    public void queryOrderPayPage() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String startDate = this.getRequestParm().getParameter("startDate");//查询开始日期
            String endDate = this.getRequestParm().getParameter("endDate");//查询结束日期
            String filter = this.getRequestParm().getParameter("filter");//查询结束日期
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("startDate", startDate);
            params.put("endDate", endDate);
            params.put("keyword", keyword);
            params.put("filter", filter);
            DTablePageModel dtPageModel = saleHandler.queryOrderPayPage(params, super.getPageData());
            List<OrderPay> list =dtPageModel.getAaData();
            if(list!=null){
            	for (int i = 0; i < list.size(); i++) {
            		OrderPay orderPay = list.get(i);
            		orderPay.setUserAddress(orderPay.getCity()+" "+orderPay.getRegion()+" "+orderPay.getTown()+"  "+orderPay.getUserAddress());
				}
            }
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 统计已售出计量
     */
    @Action(value = "sellTotalNumber")
    public void sellTotalNumber() {
        String sellToal = saleHandler.sellTotalNumber();
        try {
            PrintWriter pw = this.getResponse().getWriter();
            pw.print(sellToal);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
