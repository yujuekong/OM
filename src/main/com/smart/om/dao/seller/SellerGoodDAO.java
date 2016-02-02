package com.smart.om.dao.seller;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * Created by Administrator on 2015/10/16.
 */
public class SellerGoodDAO extends BaseDao {
    /**
     * 查询供应商列表
     **/
    public DTablePageModel querySellerGoodsPage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from SellerGoods sg where 1 = 1 ");
        hqlCount.append("select count(*) from SellerGoods sg where 1 = 1");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (sg.sellerGoodsId like '%").append(keyword).append("%'")
                            .append(" or sg.goodsId like '%").append(keyword).append("%') ")
                            .append(" or sg.sellerId like '%").append(keyword).append("%') ")
                            .append(" or sg.goodsArea like '%").append(keyword).append("%') ")
                            .append(" or sg.goodsPrice like '%").append(keyword).append("%') ");
                    hql.append(" and (sg.sellerGoodsId like '%").append(keyword).append("%'")
                            .append(" or sg.goodsId like '%").append(keyword).append("%') ")
                            .append(" or sg.sellerId like '%").append(keyword).append("%') ")
                            .append(" or sg.goodsArea like '%").append(keyword).append("%') ")
                            .append(" or sg.goodsPrice like '%").append(keyword).append("%') ");
                }
            }
            if (params.containsKey("sellerId")) {
                String sellerId = (String) params.get("sellerId");
                if (StringUtils.isNotBlank(sellerId)) {
                    hql.append(" and sg.sellerId = ").append(sellerId);
                    hqlCount.append(" and sg.sellerId = ").append(sellerId);
                }

            }
        }
        hql.append(" order by sg.sellerGoodsId");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    public int deleteSellerGoodsBySellerId(Integer sellerId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" delete SellerGoods as model where 1=1 ");
        hql.append(" and model.sellerId = " + sellerId);
        int deleteSize = this.updateHql(hql.toString(), null);
        return deleteSize;
    }
}
