package com.smart.om.dao.sale;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.dao.base.BasePo;
import com.smart.om.persist.GoodsIntegral;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by hxt on 2016/1/12.
 */
public class GoodsIntegralDAO extends BaseDao {
    /**
     * 查询供应商列表
     **/
    public DTablePageModel queryGoodsIntegralPage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append("from GoodsIntegral gi where 1=1");
        hqlCount.append("select count(*) from GoodsIntegral where 1=1 ");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (gi.goodsIntegralId like '%").append(keyword).append("%'")
                            .append(" or go.goodsName like '%").append(keyword).append("%' ")
                            .append(" or gi.integralNumber like '%").append(keyword).append("%' ");
                    hqlCount.append(" and (gi.goodsIntegralId like '%").append(keyword).append("%'")
                            .append(" or go.goodsName like '%").append(keyword).append("%' ")
                            .append(" or gi.integralNumber like '%").append(keyword).append("%' ");

                }
            }
        }
//        if (params.containsKey("dictRegionId")) {
//            String dictRegionId = (String) params.get("dictRegionId");
//            if (StringUtils.isNotBlank(dictRegionId)) {
//                hql.append(" and gi.dictRegionId =").append(dictRegionId);
//                hqlCount.append(" and gi.dictRegionId =").append(dictRegionId);
//            }
//        }
//        if (params.containsKey("dictProviceId")) {
//            String dictProviceId = (String) params.get("dictProviceId");
//            if (StringUtils.isNotBlank(dictProviceId)) {
//                hql.append(" and gi.dictProviceId =").append(dictProviceId);
//                hqlCount.append(" and gi.dictProviceId =").append(dictProviceId);
//            }
//        }
//        if (params.containsKey("orgId")) {
//            String orgId = (String) params.get("orgId");
//            if (StringUtils.isNotBlank(orgId)) {
//                hql.append(" and gi.dictOrgId =").append(orgId);
//                hqlCount.append(" and gi.dictOrgId =").append(orgId);
//            }
//        }
        hql.append(" order by gi.goodsIntegralId");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    public BasePo queryGoodsIntegralDt(Integer id) {
       return this.find(id);
    }

    /**
     * 商品ID查询积分
     */
    public GoodsIntegral getGoodsIntegralByGoodsId(String goodsId) {
        StringBuffer hql = new StringBuffer();
        hql.append("select * from GoodsIntegral where goodsId=" + goodsId + " and isUser='1'");

        List list = this.find(hql.toString(), null);
        GoodsIntegral goodsIntegral = null;
        if (list!=null) {
            goodsIntegral = (GoodsIntegral) list.get(0);
        }
        return goodsIntegral;
    }
}
