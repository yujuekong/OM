package com.smart.om.biz.report;

import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

import java.util.List;
import java.util.Map;

/**
 * 统计分析业务逻辑类
 * Created by hxt on 2016/1/19.
 */
public interface ReportHandler {
    /**
     * 查询商品销售统计
     */
    DTablePageModel queryGoodsReportPage(Map<String, Object> params, PageData pageData);

    /**
     * 初始化年份和公司选择下拉框
     */
    Map initSelect();

    /**
     * 报表查询
     */
    List getGoodsReportChart(Map map);
}
