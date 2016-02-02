package com.smart.om.dao.inventory;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

/**
 * 调拨DAO
 * @author langyuk
 *
 */
public class AllotDAO extends BaseDao{
	/**
     * 查询调拨单DAO
     **/
    public DTablePageModel queryTransferOrderPage(Map<String, Object> params, PageData pageData) {
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append("from StorageAllot sa where 1=1 ");
        hqlCount.append("select count(*) from StorageAllot sa where 1 = 1 ");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (sa.allotNo like '%").append(keyword).append("%'")
                            .append(" or sa.startStorateWarehouse.warehouseName like '%").append(keyword).append("%'")
                            .append(" or sa.endStorateWarehouse.warehouseName like '%").append(keyword).append("%')");
                    hqlCount.append(" and (sa.allotNo like '%").append(keyword).append("%'")
                    .append(" or sa.startStorateWarehouse.warehouseName like '%").append(keyword).append("%'")
                    .append(" or sa.endStorateWarehouse.warehouseName like '%").append(keyword).append("%')");
                }
            }
            if (params.containsKey("allotOrderStatus")) {//查询条件状态
                String allotOrderStatus = (String) params.get("allotOrderStatus");
                if (StringUtils.isNotBlank(allotOrderStatus)) {
                    hql.append(" and sa.allotStatus= '").append(allotOrderStatus).append("' ");
                    hqlCount.append(" and sa.allotStatus= '").append(allotOrderStatus).append("' ");
                }
            }
            if(params.containsKey("startDate")){//查询开始日期
            	String startDate = (String) params.get("startDate");
            	if(StringUtils.isNotBlank(startDate)){
            		hql.append(" and sa.createDate >= '").append(startDate).append("' ");
            		hqlCount.append(" and sa.createDate >= '").append(startDate).append("' ");
            	}
            }
            if(params.containsKey("endDate")){//查询结束日期
            	String endDate = (String) params.get("endDate");
            	if(StringUtils.isNotBlank(endDate)){
            		hql.append(" and sa.createDate <= '").append(endDate).append("' ");
            		hqlCount.append(" and sa.createDate <= '").append(endDate).append("' ");
            	}
            }
            if(params.containsKey("orgId")){//查询所属分公司
            	Integer orgId = (Integer) params.get("orgId");
            	if(orgId > 0){
            		hql.append(" and sa.startStorateWarehouse.dictOrgId = ").append(orgId);
            		hqlCount.append(" and sa.startStorateWarehouse.dictOrgId = ").append(orgId);
            	}
            }
            
        }
        hql.append(" order by sa.allotStatus desc,sa.createDate desc,sa.allotId");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }
}
