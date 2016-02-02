package com.smart.om.dao.car;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

/**
 * 车辆信息DAO
 * @author lc
 *
 */
public class CarLineDAO extends BaseDao{
	
	/** 分页查询所有线路信息 **/
	public DTablePageModel queryCarLine(Map<String, Object> params, PageData pageData){
		if(pageData == null) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append(" from CarLine cl where 1 = 1  and isDel = '" + Const.IS_DEL_FALSE + "' ");		
		hqlCount.append("select count(*) from CarLine  cl where 1 = 1 and isDel = '" + Const.IS_DEL_FALSE + "' ");
		if (params != null) {
			if (params.containsKey("keyword")) {
				String keyword = (String) params.get("keyword");
                if(StringUtils.isNotBlank(keyword)){
                	hql.append(" and cl.lineNo like '%").append(keyword).append("%' ");
                	hqlCount.append(" and cl.lineNo like '%").append(keyword).append("%' ");
                }
			}
			
			if (params.containsKey("dictRegionId")) {
                String dictRegionId = (String) params.get("dictRegionId");
                if (StringUtils.isNotBlank(dictRegionId)) {
                	hql.append(" and cl.dictRegionId =").append(Integer.valueOf(dictRegionId));
                	hqlCount.append(" and cl.dictRegionId =").append(Integer.valueOf(dictRegionId));
                }
            }
            if (params.containsKey("dictProviceId")) {
                String dictProviceId = (String) params.get("dictProviceId");
                if (StringUtils.isNotBlank(dictProviceId)) {
                	hql.append(" and cl.dictProviceId =").append(Integer.valueOf(dictProviceId));
                	hqlCount.append(" and cl.dictProviceId =").append(Integer.valueOf(dictProviceId));
                }
            }
            if (params.containsKey("dictOrgId")) {
                String dictOrgId = (String) params.get("dictOrgId");
                if (StringUtils.isNotBlank(dictOrgId)) {
                	hql.append(" and cl.dictOrgId =").append(Integer.valueOf(dictOrgId));
                	hqlCount.append(" and cl.dictOrgId =").append(Integer.valueOf(dictOrgId));
                }
            }
            if (params.containsKey("carStatus")) {
                String deviceStatus = (String) params.get("carStatus");
                if (StringUtils.isNotBlank(deviceStatus)) {
                	hql.append(" and cl.carStatus =").append(Integer.valueOf(deviceStatus));
                	hqlCount.append(" and cl.carStatus =").append(Integer.valueOf(deviceStatus));
                }
            }
            if (params.containsKey("orgId")) {
                Integer orgId = (Integer) params.get("orgId");
                if (orgId != null) {
                	hql.append(" and cl.dictOrgId =").append(Integer.valueOf(orgId));
                	hqlCount.append(" and cl.dictOrgId =").append(Integer.valueOf(orgId));
                }
            }
		}
		hql.append(" order by cl.carLineId  ");
		return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
	}
}
