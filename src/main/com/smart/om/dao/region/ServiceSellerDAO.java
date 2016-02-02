package com.smart.om.dao.region;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.MotionDistrict;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * lc
 */
public class ServiceSellerDAO extends BaseDao{
    /**
     * 分页查询商圈列表
     **/
    public DTablePageModel queryServiceSeller(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from MotionDistrict md where 1 = 1 and isDel = '" + Const.IS_DEL_FALSE + "' ");
        hqlCount.append("select count(*) from MotionDistrict md where 1 = 1 and isDel = '" + Const.IS_DEL_FALSE + "' ");
		if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and ( md.districtNo like '%").append(keyword).append("%'")
                            .append(" or md.districtName like '%").append(keyword).append("%' )");

                    hqlCount.append(" and (md.districtNo like '%").append(keyword).append("%'")
                            .append(" or md.districtName like '%").append(keyword).append("%' )");
                }
            }

			if (params.containsKey("dictRegionId")) {
                String dictRegionId = (String) params.get("dictRegionId");
                if (StringUtils.isNotBlank(dictRegionId)) {
                	hql.append(" and md.dictRegionId =").append(Integer.valueOf(dictRegionId));
                	hqlCount.append(" and md.dictRegionId =").append(Integer.valueOf(dictRegionId));
                }
            }
            if (params.containsKey("dictProviceId")) {
                String dictProviceId = (String) params.get("dictProviceId");
                if (StringUtils.isNotBlank(dictProviceId)) {
                	hql.append(" and md.dictProviceId =").append(Integer.valueOf(dictProviceId));
                	hqlCount.append(" and md.dictProviceId =").append(Integer.valueOf(dictProviceId));
                }
            }
            if (params.containsKey("dictOrgId")) {
                String dictOrgId = (String) params.get("dictOrgId");
                if (StringUtils.isNotBlank(dictOrgId)) {
                	hql.append(" and md.dictOrgId =").append(Integer.valueOf(dictOrgId));
                	hqlCount.append(" and md.dictOrgId =").append(Integer.valueOf(dictOrgId));
                }
            }
            if (params.containsKey("orgId")) {
            	Integer orgId = (Integer) params.get("orgId");
                if (orgId != null) {
                	hql.append(" and md.dictOrgId =").append(Integer.valueOf(orgId));
                	hqlCount.append(" and md.dictOrgId =").append(Integer.valueOf(orgId));
                }
            }
		}
        hql.append("order by md.districtId");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }
	/**
	 * 查询所有商圈信息
	 * @author lC
	 *
	 */
	public List<MotionDistrict> queryAllServiceSeller(Class cla){
		return super.getObjects(cla);
	}
    
	/**
	 * 查询当前用户所在分公司的所有商圈信息
	 * @author lC
	 *
	 */
	public List<MotionDistrict> queryAllMDForMap(Integer orgId){
			List<MotionDistrict> dTList = new ArrayList<MotionDistrict>();
			String hql = " from MotionDistrict md where  md.dictOrgId ='" + orgId + "'  ";
			dTList = this.find(hql, null);		
			return dTList;
	}
	

}
