package com.smart.om.dao.seller;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.SellerGoods;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/23.
 */
public class SellerInfoDAO extends BaseDao {
    /**
     * 查询供应商列表
     **/
    public DTablePageModel querySellerPage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from SellerInfo rs where 1 = 1 ");
        hqlCount.append("select count(*) from SellerInfo rs where 1 = 1");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (rs.sellerId like '%").append(keyword).append("%'")
                            .append(" or rs.sellerName like '%").append(keyword).append("%' ")
                            .append(" or rs.sellerNo like '%").append(keyword).append("%' ")
                            .append(" or rs.linkMan like '%").append(keyword).append("%' ")
                            .append(" or rs.linkTel like '%").append(keyword).append("%' ")
                            .append(" or rs.sellerType like '%").append(keyword).append("%' ")
                            .append(" or rs.sellerGoodsType like '%").append(keyword).append("%' ")
                            .append(" or rs.sellerStatus like '%").append(keyword).append("%' ")
                            .append(" or rs.createDate like '%").append(keyword).append("%' )");
                    hqlCount.append(" and (rs.sellerId like '%").append(keyword).append("%'")
                            .append(" or rs.sellerName like '%").append(keyword).append("%' ")
                            .append(" or rs.sellerNo like '%").append(keyword).append("%' ")
                            .append(" or rs.linkMan like '%").append(keyword).append("%' ")
                            .append(" or rs.linkTel like '%").append(keyword).append("%' ")
                            .append(" or rs.sellerType like '%").append(keyword).append("%' ")
                            .append(" or rs.sellerGoodsType like '%").append(keyword).append("%' ")
                            .append(" or rs.sellerStatus like '%").append(keyword).append("%' ")
                            .append(" or rs.createDate like '%").append(keyword).append("%') ");
                }
            }
        }
        if (params.containsKey("dictRegionId")) {
            String dictRegionId = (String) params.get("dictRegionId");
            if (StringUtils.isNotBlank(dictRegionId)) {
                hql.append(" and rs.dictRegionId =").append(dictRegionId);
                hqlCount.append(" and rs.dictRegionId =").append(dictRegionId);
            }
        }
        if (params.containsKey("dictProviceId")) {
            String dictProviceId = (String) params.get("dictProviceId");
            if (StringUtils.isNotBlank(dictProviceId)) {
                hql.append(" and rs.dictProviceId =").append(dictProviceId);
                hqlCount.append(" and rs.dictProviceId =").append(dictProviceId);
            }
        }
        if (params.containsKey("orgId")) {
            String orgId = (String) params.get("orgId");
            if (StringUtils.isNotBlank(orgId)) {
                hql.append(" and rs.dictOrgId =").append(orgId);
                hqlCount.append(" and rs.dictOrgId =").append(orgId);
            }
        }
        hql.append(" order by rs.sellerId");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    public List<SellerGoods> querySellerGoodsBySellerId(Integer sellerId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from SellerGoods as model where 1=1 ");
        hql.append(" and model.sellerId = " + sellerId);
        List<SellerGoods> sellerGoodsList = (List<SellerGoods>) this.find(hql.toString(), null);
        return sellerGoodsList;
    }
}
