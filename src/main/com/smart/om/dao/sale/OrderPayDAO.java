package com.smart.om.dao.sale;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 网页订单Dao
 * Created by hxt on 2015/11/28.
 */
public class OrderPayDAO extends BaseDao {
    /**
     * 分页查询销售信息
     **/
    public DTablePageModel queryOrderPayPage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from OrderPay model where 1=1 ");
        hqlCount.append("select count(*) from OrderPay model where 1=1 ");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (model.userRealName like '%").append(keyword).append("%'")
                            .append(" or model.tel like '%").append(keyword).append("%') ")
                            .append(" or model.payId like '%").append(keyword).append("%') ")
                            .append(" or model.userAddress like '%").append(keyword).append("%') ")
                            .append(" or model.inventoryNumber like '%").append(keyword).append("%') ")
                            .append(" or model.goodsUnit like '%").append(keyword).append("%') ");
                    hqlCount.append(" and (model.userRealName like '%").append(keyword).append("%'")
                            .append(" or model.tel like '%").append(keyword).append("%') ")
                            .append(" or model.payId like '%").append(keyword).append("%') ")
                            .append(" or model.userAddress like '%").append(keyword).append("%') ")
                            .append(" or model.inventoryNumber like '%").append(keyword).append("%') ")
                            .append(" or model.goodsUnit like '%").append(keyword).append("%') ");
                }

            }
            if (params.containsKey("startDate")) {//开始时间
                String startDate = (String) params.get("startDate");
                if (StringUtils.isNotBlank(startDate)) {
                    hql.append(" and model.orderTime >= '").append(startDate).append("' ");
                    hqlCount.append(" and model.orderTime >= '").append(startDate).append("' ");
                }
            }
            if (params.containsKey("endDate")) {//结束时间
                String endDate = (String) params.get("endDate");
                if (StringUtils.isNotBlank(endDate)) {
                    hql.append(" and model.orderTime <= '").append(endDate).append("' ");
                    hqlCount.append(" and model.orderTime <= '").append(endDate).append("' ");
                }
            }
            if (params.containsKey("filter")) {//开始时间
                String filter = (String) params.get("filter");
                if (StringUtils.isNotBlank(filter)) {
                    hql.append(" and model.orderStatus=" + filter);
                    hqlCount.append(" and model.orderStatus=" + filter);
                }
            }
        }
        hql.append(" and model.orderStatus!=0");
        hqlCount.append(" and model.orderStatus!=0");
        hql.append(" order by model.orderTime desc ");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }


    public String sellTotalNumber() {
        StringBuffer hql = new StringBuffer();
        hql.append("select sum(goodsNumber) from OrderPay where orderStatus=1");
        List list = this.find(hql.toString(), null);
        String sumSell = list.get(0).toString();
        return sumSell;
    }
}
