package com.smart.om.dao.goods;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.dao.base.BasePo;
import com.smart.om.persist.GoodsType;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import com.sun.faces.taglib.html_basic.DataTableTag;

import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/8.
 */
public class GoodsTypeDAO extends BaseDao {

    /**
     * 查询商品类型
     **/
    public DTablePageModel queryGoodsTypePage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from GoodsType gt where gt.isDel=0 ");
        hqlCount.append("select count(*) from GoodsType gt where gt.isDel=0");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (gt.gtNo like '%").append(keyword).append("%'")
                            .append(" or gt.gtName like '%").append(keyword).append("%') ");
                    hqlCount.append(" and (gt.gtNo  like '%").append(keyword).append("%'")
                            .append(" or gt.gtName like '%").append(keyword).append("%') ");
                }
            }
            if (params.containsKey("dictRegionId")) {
                String dictRegionId = (String) params.get("dictRegionId");
                if (StringUtils.isNotBlank(dictRegionId)) {
                	hql.append(" and gt.dgtId =").append(dictRegionId);
                	hqlCount.append(" and gt.dgtId =").append(dictRegionId);
                }
            }else{
            	hql.append(" and gt.dgtId is null");
            	hqlCount.append(" and gt.dgtId is null");
            	
            }
        }
        hql.append(" order by gt.gtId  ");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    /**
     * 查询指定商品类型
     */
    @Override
    public BasePo find(Integer id) {
        return super.find(id);
    }

    /**
     * 新增或修改商品类别
     */
    @Override
    public BasePo saveOrUpdate(BasePo po) {
        return super.save(po);
    }

    /**
     * 删除商品类别
     */
    @Override
    public BasePo delete(Integer id) {
        return super.delete(id);
    }
    /**
     * 查询所有商品类别
     */
    public List<GoodsType> queryAllGoodsType(Class clazz) {
        return getObjects(clazz);
    }

    public boolean isExistGoods(Integer gtId){
        StringBuffer hql = new StringBuffer();
        hql.append("from GoodsInfo where gtId="+gtId);
        List list = this.find(hql.toString(), null);
        return list!=null;
    }
}

