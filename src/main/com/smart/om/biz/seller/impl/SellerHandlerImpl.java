package com.smart.om.biz.seller.impl;

import com.smart.om.biz.seller.SellerHandler;
import com.smart.om.dao.base.BasePo;
import com.smart.om.dao.seller.SellerGoodDAO;
import com.smart.om.dao.seller.SellerInfoDAO;
import com.smart.om.persist.SellerGoods;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/23.
 */
@Component("SellerHandler")
public class SellerHandlerImpl implements SellerHandler {
    @Autowired
    private SellerInfoDAO sellerInfoDAO;
    @Autowired
    private SellerGoodDAO sellerGoodDAO;
    @Override
    public DTablePageModel querySellerPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = sellerInfoDAO.querySellerPage(params, pageData);
        }
        return pageModel;
    }

    @Override
    public BasePo querySellerInfoById(Integer id) {
        return sellerInfoDAO.find(id);
    }

    @Override
    public BasePo saveOrUpdateSellerInfo(BasePo basePo) {
        return sellerInfoDAO.save(basePo);
    }

    @Override
    public BasePo deleteSellerInfo(Integer id) {
        return sellerInfoDAO.delete(id);
    }

    @Override
    public DTablePageModel querySellerGoodsPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = sellerGoodDAO.querySellerGoodsPage(params, pageData);
        }
        return pageModel;
    }

    @Override
    public List<SellerGoods> querySellerGoodsBySellerId(Integer sellerId) {
        return sellerInfoDAO.querySellerGoodsBySellerId(sellerId);
    }

    @Override
    public int deleteSellerGoodsBySellerId(Integer sellerId) {
        return sellerGoodDAO.deleteSellerGoodsBySellerId(sellerId);
    }

}
