package com.smart.om.biz.goods.impl;

import com.smart.om.biz.goods.GoodsHandler;
import com.smart.om.dao.base.BasePo;
import com.smart.om.dao.goods.GoodsInfoDAO;
import com.smart.om.dao.goods.GoodsTypeDAO;
import com.smart.om.persist.GoodsInfo;
import com.smart.om.persist.GoodsType;
import com.smart.om.persist.SysDict;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 商品功能业务逻辑类
 * Created by Administrator on 2015/9/8.
 */
@Component("AdvertHandler")
public class GoodsHandlerImpl implements GoodsHandler {
    private static final Logger logger = Logger.getLogger(GoodsHandlerImpl.class);
    @Autowired
    private GoodsTypeDAO goodsTypeDAO;
    @Autowired
    private GoodsInfoDAO goodsInfoDAO;

    /**
     * 查询商品类型
     **/
    public DTablePageModel queryGoodsTypePage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = goodsTypeDAO.queryGoodsTypePage(params, pageData);
        }
        return pageModel;
    }

    @Override
    public BasePo queryGoodsTypeById(Integer id) {
        return goodsTypeDAO.find(id);
    }

    @Override
    public BasePo saveOrUpdateGoodsType(BasePo basePo) {
        return goodsTypeDAO.saveOrUpdate(basePo);
    }

    @Override
    public BasePo deleteGoodsType(Integer id) {
        return goodsTypeDAO.delete(id);
    }

    @Override
    public Integer findMaxSort() {
        return goodsInfoDAO.findMaxSort();
    }

    @Override
    public boolean isExistGoods(Integer gtId) {
        return goodsTypeDAO.isExistGoods(gtId);
    }

    @Override
    public DTablePageModel queryGoodsInfoPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = goodsInfoDAO.queryGoodsInfoPage(params, pageData);
        }
        return pageModel;
    }

    /**
     * 获得商品类型编号
     *
     * @param goodsTypeNo
     * @return
     */
    public String createGoodsLsNo(String goodsTypeNo) {
        return goodsInfoDAO.createGoodsLsNo(goodsTypeNo);
    }

    @Override
    public BasePo queryGoodsInfoById(Integer id) {
        return goodsInfoDAO.find(id);
    }

    @Override
    public BasePo saveOrUpdateGoodsInfo(BasePo basePo) {
        return goodsInfoDAO.save(basePo);
    }

    @Override
    public BasePo deleteGoodsInfo(Integer id) {
        return goodsInfoDAO.delete(id);
    }

    public List<GoodsType> queryAllGoodsType(Class clazz) {
        return goodsTypeDAO.getObjects(clazz);
    }

    @Override
    public List<GoodsType> getMulSubGoodsTypeDataByPcode() {
        return goodsInfoDAO.getMulSubGoodsTypeDataByPcode();
    }

    @Override
    public List<SysDict> getAllGoodsAgent(String str) {
        return goodsInfoDAO.getAllGoodsAgent(str);
    }

	//查询app首页展示商品信息
	public DTablePageModel queryPageGoodsInfos(Map<String, Object> params,
			PageData pageData) {
		  DTablePageModel pageModel = new DTablePageModel();
	        if (pageData != null) {
	            pageModel = goodsInfoDAO.queryPageGoodsInfos(params,pageData);
	        }
	        return pageModel;
	}

	@Override
	public List<GoodsInfo> queryGoodInfoBySort(Integer sort) {
		return goodsInfoDAO.queryGoodInfoBySort(sort);
	}

	//查询大于当前商品排序的商品信息
	public List<GoodsInfo> queryGoodInfoBySort2(Integer sort) {
		return goodsInfoDAO.queryGoodInfoBySort2(sort);
	}

	@Override
	public DTablePageModel queryAppGoodsInfoPage(Map<String, Object> params,
			PageData pageData) {
		 DTablePageModel pageModel = new DTablePageModel();
	        if (pageData != null) {
	            pageModel = goodsInfoDAO.queryAppPageGoodsInfos(params,pageData);
	        }
	        return pageModel;
	}
	
}
