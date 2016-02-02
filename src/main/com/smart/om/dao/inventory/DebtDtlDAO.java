package com.smart.om.dao.inventory;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.StorageDebtDtl;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 报损明细DAO
 *
 * @author langyuk
 */
public class DebtDtlDAO extends BaseDao {
    /**
     * 查询报损明细
     **/
    public DTablePageModel queryStorageDebtDtlPage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from StorageDebtDtl sd where 1 = 1 ");
        hqlCount.append("select count(*) from StorageDebtDtl sd where 1 = 1");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (sd.debtDtlId like '%").append(keyword).append("%'")
                            .append(" or sd.debtId like '%").append(keyword).append("%') ")
                            .append(" or sd.goodsId like '%").append(keyword).append("%') ")
                            .append(" or sd.debtGoodsCount like '%").append(keyword).append("%') ")
                            .append(" or sd.debtDesc like '%").append(keyword).append("%') ");
                    hql.append(" and (sd.debtDtlId like '%").append(keyword).append("%'")
                            .append(" or sd.debtId like '%").append(keyword).append("%') ")
                            .append(" or sd.goodsId like '%").append(keyword).append("%') ")
                            .append(" or sd.debtGoodsCount like '%").append(keyword).append("%') ")
                            .append(" or sd.debtDesc like '%").append(keyword).append("%') ");

                }
            }
            if (params.containsKey("debtId")) {
                String debtId = (String) params.get("debtId");
                if (StringUtils.isNotBlank(debtId)) {
                    hql.append(" and sd.debtId = ").append(debtId);
                    hqlCount.append(" and sd.debtId = ").append(debtId);
                }

            }
        }
        hql.append("order by sd.debtId");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    /**
     * 根据报损单ID,查询报损明细
     *
     * @param debtId
     * @return
     */
    public List<StorageDebtDtl> queryStorageDebtDtlById(Integer debtId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from StorageDebtDtl as model where 1=1 ");
        hql.append(" and model.debtId = " + debtId);
        List<StorageDebtDtl> storageDebtDtlList = (List<StorageDebtDtl>) this.find(hql.toString(), null);
        return storageDebtDtlList;
    }

    /**
     * 根据仓库ID,删除仓库库存明细
     *
     * @param debtId
     * @return
     */
    public int deleteStorageDebtDtlById(Integer debtId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" delete StorageDebtDtl as model where 1=1 ");
        hql.append(" and model.debtId = " + debtId);
        int deleteSize = this.updateHql(hql.toString(), null);
        return deleteSize;
    }
}
