package com.smart.om.dao.pay;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * 支付信息
 * @author langyuk
 *
 */
public class PayInfoDAO extends BaseDao{
	/**
     * 分页查询支付信息
     **/
    public DTablePageModel queryPayInfoPage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from PayInfo model where 1=1 ");
        hqlCount.append("select count(*) from PayInfo model where 1=1 ");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if(StringUtils.isNotBlank(keyword)){
                	hql.append(" and model.payTradeNo= '").append(keyword).append("' ");
                	hqlCount.append(" and model.payTradeNo= '").append(keyword).append("' ");
                }
            }
            if(params.containsKey("startDate")) {//开始时间
    			String startDate = (String) params.get("startDate");
    			if(StringUtils.isNotBlank(startDate)) {
    				hql.append(" and model.payDate >= '").append(startDate).append("' ");
    				hqlCount.append(" and model.payDate >= '").append(startDate).append("' ");
    			}
    		}
    		if(params.containsKey("endDate")) {//结束时间
    			String endDate = (String) params.get("endDate");
    			if(StringUtils.isNotBlank(endDate)) {
    				hql.append(" and model.payDate <= '").append(endDate).append("' ");
    				hqlCount.append(" and model.payDate <= '").append(endDate).append("' ");
    			}
    		}
        }
        
        hql.append(" order by model.saleDate desc  ");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }
}
