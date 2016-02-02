package com.smart.om.biz.seller;

import com.smart.om.dao.base.BasePo;
import com.smart.om.persist.SellerGoods;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/23.
 */
public interface SellerHandler {
    /**
     * 查询供应商列表
     */
    DTablePageModel querySellerPage(Map<String, Object> params, PageData pageData);

    /**
     * 查询供应商详情
     */
    BasePo querySellerInfoById(Integer id);

    /**
     * 新增或修改供应商
     */
    BasePo saveOrUpdateSellerInfo(BasePo basePo);

    /**
     * 删除供应商
     */
    BasePo deleteSellerInfo(Integer id);


    /**
     * 查询供应商品列表
     */
    DTablePageModel querySellerGoodsPage(Map<String, Object> params, PageData pageData);


    /**
     * 根据供应商ID，查询供应商商品
     *
     * @param sellerId
     * @return
     */
    List<SellerGoods> querySellerGoodsBySellerId(Integer sellerId);

    /**
     * 根据供应商ID，删除供应商商品明细
     *
     * @param sellerId
     * @return
     */
    int deleteSellerGoodsBySellerId(Integer sellerId);
}
