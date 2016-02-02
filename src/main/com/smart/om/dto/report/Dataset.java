package com.smart.om.dto.report;

import java.util.List;

/**
 * Created by hxt on 2016/1/22.
 * 图表值类
 */
public class Dataset {
    //分类名
    private String seriesname;
    //图表显示数据键值对("value":"1000")
    private List<Data> data;

    public String getSeriesname() {
        return seriesname;
    }

    public void setSeriesname(String seriesname) {
        this.seriesname = seriesname;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
