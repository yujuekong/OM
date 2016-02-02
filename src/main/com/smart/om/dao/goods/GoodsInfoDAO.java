package com.smart.om.dao.goods;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.dao.base.BasePo;
import com.smart.om.dao.sys.SysDictDAO;
import com.smart.om.persist.GoodsInfo;
import com.smart.om.persist.GoodsType;
import com.smart.om.persist.SysDict;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/8.
 */
public class GoodsInfoDAO extends BaseDao {
    @Autowired
    private SysDictDAO sysDictDAO;

    /**
     * 查询商品信息
     **/
    public DTablePageModel queryGoodsInfoPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel dTablePageModel;
        if (pageData == null) {
            dTablePageModel = null;
        } else {
            StringBuffer hql = new StringBuffer();
            StringBuffer hqlCount = new StringBuffer();
            hql.append("from GoodsInfo gi where 1=1");
            hqlCount.append("select count(*) from GoodsInfo gi where 1=1");
            if (params != null) {
                if (params.containsKey("keyword")) {
                    String keyword = (String) params.get("keyword");
                    if (StringUtils.isNotBlank(keyword)) {
                        hql.append(" and (gi.goodsName like '%").append(keyword).append("%'")
                                .append(" or gi.goodsTypeName like '%").append(keyword).append("%'")
                                .append(" or gi.goodsSpell like '%").append(keyword).append("%')");

                        hqlCount.append(" and (gi.goodsName like '%").append(keyword).append("%'")
                                .append(" or gi.goodsTypeName like '%").append(keyword).append("%'")
                                .append(" or gi.goodsSpell like '%").append(keyword).append("%')");
                    }
                }
                if (params.containsKey("dictRegionId")) {
                    String dictRegionId = params.get("dictRegionId").toString();
                    if (StringUtils.isNotBlank(dictRegionId)) {
                        hql.append(" and gi.gtId =").append(dictRegionId);
                        hqlCount.append(" and gi.gtId =").append(dictRegionId);
                    }
                }
                if (params.containsKey("app")) {
                    String app = params.get("app").toString();
                    if (StringUtils.isNotBlank(app)) {
                        hql.append(" and gi.goodsName !='刀片'");
                        hqlCount.append(" and gi.goodsName !='刀片'");
                    }
                }
                if (params.containsKey("sellerId")) {
                    String sellerId = params.get("sellerId").toString();
                    StringBuffer findHql = new StringBuffer();
                    findHql.append("select goodsId from SellerGoods where sellerId=" + sellerId);
                    List list = this.find(findHql.toString(), null);
                    if (list != null) {
                        String listStr = list.toString().replaceAll("[\\[\\]]", "");
                        hql.append(" and gi.goodsId in (" + listStr + ")");
                        hqlCount.append(" and gi.goodsId in (" + listStr + ")");
                    }
                }
                if (params.containsKey("orgId")) {
                    String orgId = params.get("orgId").toString();
                    if (params.containsKey("orgFilter")) {
                        StringBuffer deviceHql = new StringBuffer();
                        deviceHql.append("SELECT si.goodsId FROM StorageInventory si, StorageWarehouse sw WHERE si.warehouseId = sw.warehouseId AND sw.dictOrgId =" + orgId + " GROUP BY si.goodsId");
                        List list = this.find(deviceHql.toString(), null);
                        if (list != null) {
                            String listStr = list.toString().replaceAll("[\\[\\]]", "");
                            hql.append(" and gi.goodsId in (" + listStr + ")");
                            hqlCount.append(" and gi.goodsId in (" + listStr + ")");
                        }
                    }
                }
            }
            hql.append(" and gi.isDel=0");
            hqlCount.append(" and gi.isDel=0");
            hql.append(" order by gi.goodsId ");
            dTablePageModel = super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
        }
        return dTablePageModel;
    }

    /**
     * 查询指定商品信息
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
        return super.saveOrUpdate(po);
    }

    /**
     * 删除商品类别
     */
    @Override
    public BasePo delete(Integer id) {
        return super.delete(id);
    }

    /**
     * 获得商品类型编号
     *
     * @param goodsTypeNo
     * @return
     */
    public String createGoodsLsNo(String goodsTypeNo) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from GoodsInfo sg where sg.goodsTypeNo = ?");
        List goodsLst = this.find(hql.toString(), new Object[]{goodsTypeNo});
        int count = 1;
        if (goodsLst != null) {
            count += goodsLst.size();
        }
        return StringUtils.rightPad(goodsTypeNo, 6, "0") + StringUtils.leftPad(String.valueOf(count), 7, "0");
    }

    /**
     * 商品树状图
     */
    public List<GoodsType> getMulSubGoodsTypeDataByPcode() {
        List<GoodsType> goodsTypeList;
        StringBuffer hql = new StringBuffer();
        hql.append(" from GoodsType as model1 where 1 = 1 and model1.isDel=0");
        goodsTypeList = this.find(hql.toString(), null);
        return goodsTypeList;
    }

    /**
     * 查询商品处理类型
     */
    public List<SysDict> getAllGoodsAgent(String str) {
        List<SysDict> sysDictList;
        SysDict sysDict = sysDictDAO.queryDictByPcode(str);
        sysDictList = sysDictDAO.querySubDictByPcode(sysDict.getDictCode());
        return sysDictList;
    }

    /**
     *
     */
    public GoodsInfo getGoodsInfoByName(String nameD) {
        StringBuffer hql = new StringBuffer();
        GoodsInfo goodsInfo = new GoodsInfo();
        hql.append(" from GoodsInfo gi where  gi.goodsName='" + nameD + "'");
        List<GoodsInfo> GoodsInfoList = this.find(hql.toString(), null);
        if (GoodsInfoList != null) {
            goodsInfo = GoodsInfoList.get(0);
        }
        return goodsInfo;

    }
    
    public DTablePageModel queryPageGoodsInfos(Map<String, Object> params, PageData pageData){
    	 DTablePageModel dTablePageModel;
         if (pageData == null) {
             dTablePageModel = null;
         } else {
             StringBuffer hql = new StringBuffer();
             StringBuffer hqlCount = new StringBuffer();
             hql.append("from GoodsInfo gi where 1=1 ");
             hql.append(" and gi.goodsSort > 0 ");
             hqlCount.append("select count(*) from GoodsInfo gi where 1=1")
             .append(" and gi.goodsSort > 0");
             if (params != null) {
                 if (params.containsKey("keyword")) {
                     String keyword = (String) params.get("keyword");
                     if (StringUtils.isNotBlank(keyword)) {
                         hql.append(" and (gi.goodsName like '%").append(keyword).append("%'")
                         .append(" or gi.goodsBarCode like '%").append(keyword).append("%')");

                         hqlCount.append(" and (gi.goodsName like '%").append(keyword).append("%'")
                         .append(" or gi.goodsBarCode like '%").append(keyword).append("%')");
                     }
                 }
             }
             hql.append(" order by gi.goodsSort");
             dTablePageModel = super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
         }
         return dTablePageModel;
    }

    public Integer findMaxSort() {
        Integer maxSort = 0;
        StringBuffer hql = new StringBuffer();
        hql.append("select max(goodsSort) from GoodsInfo");
        List list = this.find(hql.toString(), null);
        if (list != null && list.get(0) != null) {
            maxSort= (Integer) list.get(0);
        }
        return maxSort;
    }
    
    public List<GoodsInfo> queryGoodInfoBySort(Integer sort){
    	StringBuffer hql = new StringBuffer();
    	hql.append(" from GoodsInfo gi where 1 = 1 ");
    	hql.append(" and gi.goodsSort < ").append(sort);
    	hql.append(" and gi.goodsSort > 0");
    	List<GoodsInfo> list = this.find(hql.toString(), null);
    	return list;
    }
    
    public List<GoodsInfo> queryGoodInfoBySort2(Integer sort){
    	StringBuffer hql = new StringBuffer();
    	hql.append(" from GoodsInfo gi where 1 = 1 ");
    	hql.append(" and gi.goodsSort > ").append(sort);
    	List<GoodsInfo> list = this.find(hql.toString(), null);
    	return list;
    }
    
    public DTablePageModel queryAppPageGoodsInfos(Map<String, Object> params, PageData pageData) {
    	 DTablePageModel dTablePageModel;
         if (pageData == null) {
             dTablePageModel = null;
         } else {
             StringBuffer hql = new StringBuffer();
             StringBuffer hqlCount = new StringBuffer();
             hql.append("from GoodsInfo gi where 1=1");
             hqlCount.append("select count(*) from GoodsInfo gi where 1=1");
             if (params != null) {
                 if (params.containsKey("keyword")) {
                     String keyword = (String) params.get("keyword");
                     if (StringUtils.isNotBlank(keyword)) {
                         hql.append(" and (gi.goodsName like '%").append(keyword).append("%'")
                                 .append(" or gi.goodsTypeName like '%").append(keyword).append("%'")
                                 .append(" or gi.goodsSpell like '%").append(keyword).append("%')");

                         hqlCount.append(" and (gi.goodsName like '%").append(keyword).append("%'")
                                 .append(" or gi.goodsTypeName like '%").append(keyword).append("%'")
                                 .append(" or gi.goodsSpell like '%").append(keyword).append("%')");
                     }
                 }
             }
             hql.append(" and gi.isDel=0 and gi.goodsSort = null ");
             hqlCount.append(" and gi.isDel=0 and gi.goodsSort = null ");
             hql.append(" order by gi.goodsId ");
             dTablePageModel = super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
         }
         return dTablePageModel;
    }
}
