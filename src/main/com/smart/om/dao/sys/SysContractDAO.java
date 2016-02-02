package com.smart.om.dao.sys;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.SysContract;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

public class SysContractDAO extends BaseDao{
	/**
	 * 分页查询合同
	 * @param params 查询条件
	 * @param pageData 分页信息
	 * @return
	 */
	public DTablePageModel queryContractPage(Map<String, Object> params,PageData pageData) {
		// 构造查询的HQL
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append(" from SysContract as model where 1=1 and model.isDel = 0 ");
		hqlCount.append(" select count(model.contractId) from SysContract as model where 1=1 and model.isDel = 0 ");
		if (params != null) {
			if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (model.contractNo like '%").append(keyword).append("%'")
                            .append(" or model.contractName like '%").append(keyword).append("%') ");
                    hqlCount.append(" and (model.contractNo like '%").append(keyword).append("%'")
                            .append(" or model.contractName like '%").append(keyword).append("%') ");
                }
            }
            if (params.containsKey("contractType")) {//根据合同类型查询
            	String contractType = (String) params.get("contractType");
				if(StringUtils.isNotBlank(contractType)) {
					hql.append(" and model.contractType = '").append(contractType).append("' ");
					hqlCount.append(" and model.contractType = '").append(contractType).append("' ");
				}
            }
            if (params.containsKey("contractStatus")) {//根据合同状态查询
            	String contractStatus = (String) params.get("contractStatus");
				if(StringUtils.isNotBlank(contractStatus)) {
					hql.append(" and model.contractStatus = '").append(contractStatus).append("' ");
					hqlCount.append(" and model.contractStatus = '").append(contractStatus).append("' ");
				}
            }
            if (params.containsKey("dictOrgId")) {
                String dictOrgId = (String) params.get("dictOrgId");
                if (StringUtils.isNotBlank(dictOrgId)) {
                	hql.append(" and model.orgId =").append(Integer.valueOf(dictOrgId));
                	hqlCount.append(" and model.orgId =").append(Integer.valueOf(dictOrgId));
                }
            }
            if (params.containsKey("dictRegionId")) {
                String dictRegionId = (String) params.get("dictRegionId");
                if (StringUtils.isNotBlank(dictRegionId)) {
                	hql.append(" and model.dictRegionId =").append(Integer.valueOf(dictRegionId));
                	hqlCount.append(" and model.dictRegionId =").append(Integer.valueOf(dictRegionId));
                }
            }
            if (params.containsKey("dictProviceId")) {
                String dictProviceId = (String) params.get("dictProviceId");
                if (StringUtils.isNotBlank(dictProviceId)) {
                	hql.append(" and model.dictProviceId =").append(Integer.valueOf(dictProviceId));
                	hqlCount.append(" and model.dictProviceId =").append(Integer.valueOf(dictProviceId));
                }
            }
		}
		hql	.append(" order by model.contractId desc");		
		return this.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
	}
	
	/**
	 * 查询服务合同
	 * @return
	 */
	public List<SysContract> querySysContract() {
		// 构造查询的HQL
		StringBuffer hql = new StringBuffer();
		hql.append(" from SysContract as model where 1=1 and model.isDel = 0 ");	
		hql	.append(" order by model.createDate desc");		
		return this.find(hql.toString(), null);
	}
	
}
