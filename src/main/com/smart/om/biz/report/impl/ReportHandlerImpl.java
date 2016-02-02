package com.smart.om.biz.report.impl;

import com.smart.om.biz.report.ReportHandler;
import com.smart.om.dao.report.GoodsReportDao;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 统计分析业务逻辑实现类
 * Created by hxt on 2016/1/19.
 */
@Component("ReportHandler")
public class ReportHandlerImpl implements ReportHandler {
    @Autowired
    private GoodsReportDao goodsReportDao;//销售DAO

    @Override
    public DTablePageModel queryGoodsReportPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = goodsReportDao.queryGoodsReportPage(params, pageData);
        }
        return pageModel;
    }

    @Override
    public Map initSelect() {
        return goodsReportDao.initSelect();
    }

    @Override
    public List getGoodsReportChart(Map map) {
        return goodsReportDao.getGoodsReportChart(map);
    }
}
