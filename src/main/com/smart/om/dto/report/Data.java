package com.smart.om.dto.report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hxt on 2016/1/22.
 */
public class Data {
    private String label;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Dataset addData(String seriesname, List<String> datas) {
        Dataset dataset = new Dataset();
        List<Data> dataList = new ArrayList<Data>();
        for (int i = 0; i < datas.size(); i++) {
            Data data = new Data();
            System.out.println(datas.get(i).toString());
            data.setValue(datas.get(i).toString());
            dataList.add(data);
        }
        dataset.setSeriesname(seriesname);
        dataset.setData(dataList);
        return dataset;
    }
}
