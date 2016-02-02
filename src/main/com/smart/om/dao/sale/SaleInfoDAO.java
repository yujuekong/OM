package com.smart.om.dao.sale;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.SaleInfo;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

/**
 * 销售
 * @author langyuk
 *
 */
public class SaleInfoDAO extends BaseDao{
	/**
     * 分页查询销售信息
     **/
    public DTablePageModel querySaleInfoPage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from SaleInfo model where 1=1 ");
        hqlCount.append("select count(*) from SaleInfo model where 1=1 ");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if(StringUtils.isNotBlank(keyword)){
                	hql.append(" and model.saleNo = '").append(keyword).append("' ");
                	hqlCount.append(" and model.saleNo = '").append(keyword).append("' ");
                }
                
            }
            if(params.containsKey("startDate")) {//开始时间
    			String startDate = (String) params.get("startDate");
    			if(StringUtils.isNotBlank(startDate)) {
    				hql.append(" and model.saleDate >= '").append(startDate).append("' ");
    				hqlCount.append(" and model.saleDate >= '").append(startDate).append("' ");
    			}
    		}
    		if(params.containsKey("endDate")) {//结束时间
    			String endDate = (String) params.get("endDate");
    			if(StringUtils.isNotBlank(endDate)) {
    				hql.append(" and model.saleDate < '").append(endDate).append("' ");
    				hqlCount.append(" and model.saleDate < '").append(endDate).append("' ");
    			}
    		}
        }
        hql.append(" order by model.saleDate desc  ");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }
    
    /**
     * public
     */
    public List<SaleInfo> querySaleInfo(String bsinessOrderNo){
    	// 构造查询的HQL
		String hql = "from SaleInfo si where si.bsinessOrderNo='"+bsinessOrderNo+"'";
		return this.find(hql,null);
    	
    }
    
    /**
     * 删除当天数据
     */
    public void deleteSaleInfoIntraday(String startDate ,String overDate){
    	String sql = "delete from SaleInfo where saleDate >='"+startDate+"' and saleDate<'"+overDate+"'";
    	this.updateHql(sql, null);
    }
}
