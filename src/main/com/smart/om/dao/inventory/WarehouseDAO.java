package com.smart.om.dao.inventory;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.dao.base.BasePo;
import com.smart.om.persist.StorageWarehouse;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/16.
 */
public class WarehouseDAO extends BaseDao {
    /**
     * 查询仓库分页列表
     **/
    public DTablePageModel queryWarehousePage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from StorageWarehouse sw where 1 = 1 ");
        hqlCount.append("select count(*) from StorageWarehouse sw where 1 = 1");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (sw.warehouseNo like '%").append(keyword).append("%' ")
                            .append(" or sw.warehouseName like '%").append(keyword).append("%' ")
                            .append(" or sw.warehouseAddress like '%").append(keyword).append("%')");
                    hqlCount.append(" and (sw.warehouseNo like '%").append(keyword).append("%' ")
                            .append(" or sw.warehouseName like '%").append(keyword).append("%' ")
                            .append(" or sw.warehouseAddress like '%").append(keyword).append("%')");
                }
            }
            if (params.containsKey("dictRegionId")) {
                String dictRegionId = (String) params.get("dictRegionId");
                if (StringUtils.isNotBlank(dictRegionId)) {
                    hql.append(" and sw.dictRegionId =").append(Integer.valueOf(dictRegionId));
                    hqlCount.append(" and sw.dictRegionId =").append(Integer.valueOf(dictRegionId));
                }
            }
            if (params.containsKey("dictProviceId")) {
                String dictProviceId = (String) params.get("dictProviceId");
                if (StringUtils.isNotBlank(dictProviceId)) {
                    hql.append(" and sw.dictProviceId =").append(Integer.valueOf(dictProviceId));
                    hqlCount.append(" and sw.dictProviceId =").append(Integer.valueOf(dictProviceId));
                }
            }
            if (params.containsKey("dictOrgId")) {
                String dictOrgId = (String) params.get("dictOrgId");
                hql.append(" and sw.dictOrgId=").append(Integer.valueOf(dictOrgId));
                hqlCount.append(" and sw.dictOrgId=").append(Integer.valueOf(dictOrgId));

            }
            if (params.containsKey("orgId")) {
                String orgId = (String) params.get("orgId");
                if(StringUtils.isNotBlank(orgId)){
                    hql.append(" and sw.dictOrgId=").append(Integer.valueOf(orgId));
                    hqlCount.append(" and sw.dictOrgId=").append(Integer.valueOf(orgId));
                }

            }
        }
        hql.append(" order by sw.warehouseNo");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    /**
     * 根据所属区域查询仓库ID
     */
    public List<StorageWarehouse> queryStorageWarehouseById(Integer orgId) {
        String hql = "";
        if (!"".equals(orgId) && orgId != null) {
            hql = "from StorageWarehouse where dictOrgId=" + orgId;
            return (List<StorageWarehouse>) this.find(hql, null);
        } else {
            return null;
        }
    }

    public BasePo saveOrUpdateWarehouse(StorageWarehouse storageWarehouse) {
        StringBuffer hqlOrg = new StringBuffer();
        hqlOrg.append("select dictName from SysDict where dictId=" + storageWarehouse.getDictOrgId());
        List orgList = this.find(hqlOrg.toString(), null);
        if (orgList != null) {
            String orgName = (String) orgList.get(0);
            storageWarehouse.setDictOrgName(orgName);
        }
        StringBuffer hqlReg = new StringBuffer();
        hqlReg.append("select dictName from SysDict where dictId=" + storageWarehouse.getDictRegionId());
        List RegList = this.find(hqlReg.toString(), null);
        if (RegList != null) {
            String regName = (String) RegList.get(0);
            storageWarehouse.setDictRegionName(regName);
        }
        StringBuffer hqlPro = new StringBuffer();
        hqlPro.append("select dictName from SysDict where dictId=" + storageWarehouse.getDictProviceId());
        List proList = this.find(hqlPro.toString(), null);
        if (proList != null) {
            String proName = (String) proList.get(0);
            storageWarehouse.setDictProviceName(proName);
        }
        storageWarehouse = (StorageWarehouse) this.save(storageWarehouse);
        return storageWarehouse;
    }

    public boolean isInventory(Integer id) {
        boolean isInventory = true;
        StringBuffer hql = new StringBuffer();
        hql.append("from StorageInventory where warehouseId=" + id);
        List list = this.find(hql.toString(), null);
        if (list == null) {
            isInventory = false;
        }
        return isInventory;
    }
}
