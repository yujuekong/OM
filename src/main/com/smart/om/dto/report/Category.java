package com.smart.om.dto.report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hxt on 2016/1/22.
 * X轴种类
 */
public class Category {
    //标签
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
//
//    public void addLabel(List category) {
//        List list = new ArrayList();
//        for (int i = 0; i < category.size(); i++) {
//            Map map = new HashMap();
//            map.put("label", category.get(i).toString());
//            list.add(map);
//        }
//        this.category = list;
//    }

    public List<Categories> addCategory(List<String> categoryList) {
        List<Categories> categoriesList = new ArrayList<Categories>();
        Categories categories = new Categories();
        List<Category> list = new ArrayList<Category>();
        for (int i = 0; i < categoryList.size(); i++) {
            Category category = new Category();
            category.setLabel(categoryList.get(i).toString());
            list.add(category);
        }
        categories.setCategory(list);
        categoriesList.add(categories);
        return categoriesList;
    }
}