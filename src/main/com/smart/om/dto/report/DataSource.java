package com.smart.om.dto.report;

import java.util.List;

/**
 * Created by hxt on 2016/1/22.
 * 图例数据源类
 */
public class DataSource {
    private Chart chart;
    private List<Categories> categories;
    private List<Dataset> dataset;
    private List<Data> data;

    public Chart getChart() {
        return chart;
    }

    public void setChart(Chart chart) {
        this.chart = chart;
    }


    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public List<Dataset> getDataset() {
        return dataset;
    }

    public void setDataset(List<Dataset> dataset) {
        this.dataset = dataset;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
