package com.smart.om.dao.advert;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.dao.base.BasePo;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

/**
 * 广告主DAO
 * @author CA
 *
 */
public class AdvertUserDAO extends BaseDao {
	
	/** 查询广告主 **/
	public DTablePageModel queryAdvertUserPage(Map<String, Object> params, PageData pageData){
		if(pageData == null) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append(" from AdvertUser sg where 1 = 1 ");		
		hqlCount.append("select count(*) from AdvertUser sg where 1 = 1");
		if(params != null) {
			if(params.containsKey("keyword")) {
				String keyword = (String) params.get("keyword");
				if(StringUtils.isNotBlank(keyword)) {
					hql.append(" and (sg.auName like '%").append(keyword).append("%'")
					.append(" or sg.auLinkman like '%").append(keyword).append("%' ")
					.append(" or sg.auLinkaddress like '%").append(keyword).append("%') ");
					hqlCount.append(" and (sg.auName like '%").append(keyword).append("%'")
					.append(" or sg.auLinkman like '%").append(keyword).append("%'")
					.append(" or sg.auLinkaddress like '%").append(keyword).append("%') ");
				}
			}
			if(params.containsKey("adUserStatus")){
				String adUserStatus = (String) params.get("adUserStatus");
				if(StringUtils.isNotBlank(adUserStatus)){
					hql.append(" and sg.auStatus = ").append(adUserStatus);
					hqlCount.append(" and sg.auStatus = ").append(adUserStatus);
				}
			}
			 if (params.containsKey("orgId")) {
	                String orgId = (String) params.get("orgId");
	                if (StringUtils.isNotBlank(orgId)) {
	                	hql.append(" and sg.orgId =").append(Integer.valueOf(orgId));
	                	hqlCount.append(" and sg.orgId =").append(Integer.valueOf(orgId));
	                }
	            }
			 if (params.containsKey("regionId")) {
	                String regionId = (String) params.get("regionId");
	                if (StringUtils.isNotBlank(regionId)) {
	                	hql.append(" and sg.regionId =").append(Integer.valueOf(regionId));
	                	hqlCount.append(" and sg.regionId =").append(Integer.valueOf(regionId));
	                }
	            }
	         if (params.containsKey("proviceId")) {
	               String proviceId = (String) params.get("proviceId");
	               if (StringUtils.isNotBlank(proviceId)) {
	                	hql.append(" and sg.proviceId =").append(Integer.valueOf(proviceId));
	                	hqlCount.append(" and sg.proviceId =").append(Integer.valueOf(proviceId));
	                }
	            }
		}
		hql.append(" order by sg.advertUserId desc ");
		return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
	}
	
	/** 新增广告主 **/
	public BasePo saveOrUpdate(BasePo po) {
		return super.saveOrUpdate(po);
	}
	
	/** 查询单个广告主 **/
	
	public BasePo findById(Integer id){
		return super.find(id);
	}
	
	/** 删除单个广告主 **/
	public BasePo delete(Integer id){
		return super.delete(id);
	}
	
}
