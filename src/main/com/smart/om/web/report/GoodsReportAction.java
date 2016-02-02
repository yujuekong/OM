package com.smart.om.web.report;

import com.google.gson.Gson;
import com.smart.om.biz.report.ReportHandler;
import com.smart.om.dto.report.Chart;
import com.smart.om.dto.report.Data;
import com.smart.om.dto.report.DataSource;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.ReportUtil;
import com.smart.om.web.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hxt on 2016/1/19.
 * 商品销售统计Action
 */
@Namespace("/view/report/goodsReport")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class GoodsReportAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(GoodsReportAction.class);

    @Resource
    private ReportHandler reportHandler;

    /**
     * 查询所有订单
     */
    @Action(value = "queryGoodsReportPage")
    public void queryGoodsReportPage() {
        try {
            //搜索关键字
            String year = this.getRequestParm().getParameter("year");
            String month = this.getRequestParm().getParameter("month");
            String dictOrgId = this.getRequestParm().getParameter("dictOrgId");

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("year", year);
//            params.put("orgId", sysUser.getOrgId());//用户所属服务站
            params.put("month", month);
            params.put("dictOrgId", dictOrgId);


            DTablePageModel dtPageModel = reportHandler.queryGoodsReportPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Action(value = "initSelect")
    public void initSelect() {
        try {
            Map map = reportHandler.initSelect();
            String jsonData = JSONUtil.serialize(map);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 进入报表页面
     */
    @Action(value = "preGoodsReportCharts", results = {
            @Result(name = SUCCESS, location = "/view/report/goodsReportCharts.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public String preGoodsReportCharts() {
        String result = SUCCESS;
        String year = this.getRequestParm().getParameter("year");
        String month = this.getRequestParm().getParameter("month");
        String orgId = this.getRequestParm().getParameter("orgId");
        getRequest().put("year", year);
        getRequest().put("month", month);
        getRequest().put("orgId", orgId);
//        try{
//            String districtId = this.getRequestParm().getParameter("districtId");
//            if(StringUtils.isNotBlank(districtId)){
//                Integer dId =Integer.valueOf(districtId);
//                MotionDistrict mDistrict = serviceSellerHandler.queryServiceSellerById(dId);
//                getRequest().put("motionDistrict", mDistrict);
//            }
//        }catch (Exception e) {
//            this.getRequest().put("where", " 查询所有数据字典");
//            this.getRequest().put("errors", "系统正忙，请联系管理员");
//            result = ERROR;
//        }
        return result;
    }

    @Action(value = "goodsReportCharts")
    public void goodsReportCharts() {
        String year = this.getRequestParm().getParameter("year");
        String month = this.getRequestParm().getParameter("month");
        String orgId = this.getRequestParm().getParameter("orgId");
        try {
            //创建报表实例
            DataSource dataSource = new DataSource();
            Chart chart = new Chart();
            chart.setCaption("商品销售");
            chart.setPaletteColors("#0075c2,#1aaf5d,#f2c500");
            chart.setyAxisName("销售金额(元)");
            chart.setFormatNumberScale("0");
            //设置报表头和基本属性
            dataSource.setChart(chart);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("year", year);
            params.put("month", month);
            params.put("orgId", orgId);
            //已知年份和月份
            List goodsReportChart = reportHandler.getGoodsReportChart(params);
            List<Data> dataList = new ArrayList<>();
            if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
                chart.setxAxisName("分公司");
                chart.setSubCaption(year + "." + month + "月");
                for (int i = 0; i < goodsReportChart.size(); i++) {
                    Data data = new Data();
                    Map map = (Map) goodsReportChart.get(i);
                    String dictName = map.get("dictName").toString();
                    data.setLabel(dictName);
                    String sumMoney = map.get("sumMoney").toString();
                    data.setValue(sumMoney);
                    dataList.add(data);
                }
            }
            if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(orgId)) {
                chart.setxAxisName("月份");
                String dictName = null;
                for (int i = 1; i < 13; i++) {
                    Data data = new Data();
                    for (Object row : goodsReportChart) {
                        Map map = (Map) row;
                        dictName = map.get("dictName").toString();
                        String sumMoney = map.get("sumMoney").toString();
                        String monthStr = map.get("month").toString();
                        String monthFormat = String.format("%02d", i);
                        if (monthFormat.equals(monthStr)) {
                            data.setValue(sumMoney);
                            break;
                        } else {
                            data.setValue("0");
                        }
                    }
                    data.setLabel(ReportUtil.ArabicNumberToChinese(i) + "月");
                    dataList.add(data);
                }
                chart.setSubCaption(year + "年" + dictName);
            }
            dataSource.setData(dataList);
            Gson gson = new Gson();
            String goodsReport = gson.toJson(dataSource);
            PrintWriter pw = getResponse().getWriter();
            pw.write(goodsReport);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
        }
    }

}
