package com.smart.om.biz.goods;

import com.smart.om.dao.base.BasePo;
import com.smart.om.persist.GoodsInfo;
import com.smart.om.persist.GoodsType;
import com.smart.om.persist.SysDict;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/8.
 */
public interface GoodsHandler {
    /**
     * 查询商品类型
     */
    DTablePageModel queryGoodsTypePage(Map<String, Object> params, PageData pageData);

    /**
     * 查询单个商品类型详情
     */
    BasePo queryGoodsTypeById(Integer id);

    /**
     * 新增或修改商品类别
     */
    BasePo saveOrUpdateGoodsType(BasePo basePo);

    /**
     * 删除商品类别
     */
    BasePo deleteGoodsType(Integer id);

    /**
     * 查找最大商品排序编号
     */
    Integer findMaxSort();

    /**
     * 商品类别下是否存在商品
     */
    boolean isExistGoods(Integer gtId);

    /**
     * 查询商品信息
     */
    DTablePageModel queryGoodsInfoPage(Map<String, Object> params, PageData pageData);

    /**
     * 获得商品类型编号
     *
     * @param goodsTypeNo
     * @return
     */
    String createGoodsLsNo(String goodsTypeNo);

    /**
     * 查询单个商品信息
     */
    BasePo queryGoodsInfoById(Integer id);

    /**
     * 新增或修改商品信息
     */
    BasePo saveOrUpdateGoodsInfo(BasePo basePo);

    /**
     * 删除商品信息
     */
    BasePo deleteGoodsInfo(Integer id);

    /**
     * 查询所有商品类型
     */
    List queryAllGoodsType(Class clazz);

    /**
     * 商品类型树状图
     *
     * @return
     */
    List<GoodsType> getMulSubGoodsTypeDataByPcode();

    /**
     * 查询商品单位集合
     */
    List<SysDict> getAllGoodsAgent(String str);
    
    /**
     * 查询app首页商品展示信息
     */
    DTablePageModel queryPageGoodsInfos(Map<String, Object> params, PageData pageData);
    /**
     * 查询小于当前商品排序的商品信息
     */
    public List<GoodsInfo> queryGoodInfoBySort(Integer sort);
    /**
     * 查询大于当前商品排序的商品信息
     */
    public List<GoodsInfo> queryGoodInfoBySort2(Integer sort);
    /**
     * 查询App商品列表
     */
    DTablePageModel queryAppGoodsInfoPage(Map<String,Object> params,PageData pageData);
}
