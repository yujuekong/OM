package com.smart.om.dao.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.dto.advert.AdvertInfoDto;
import com.smart.om.dto.sys.SysDeptDto;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import com.smart.om.util.PageModel;

/**
 * 系统部门DAO
 * @author CA
 *
 */
public class SysDeptDAO extends BaseDao{
	/**
	 * 查询系统部门信息
	 */
	public DTablePageModel querysysDeptPage(Map<String, Object> params, PageData pageData){
		if(pageData == null) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append(" select sd.deptId,sd.deptName,sd.deptTel,su.realName ");	
		hql.append(" from SysDept sd ,SysUser su where 1 = 1 ");
		hql.append(" and sd.deptChief = su.userId ");
		hqlCount.append("select count(*) from SysDept sd ,SysUser su  where 1 = 1");
		hqlCount.append(" and sd.deptChief = su.userId ");
		if(params != null) {
			if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (sd.deptName like '%").append(keyword).append("%'")
                    		.append(" or su.realName like '%").append(keyword).append("%')");
                            
                    hqlCount.append(" and (sd.deptName like '%").append(keyword).append("%'")
                    		.append(" or su.realName like '%").append(keyword).append("%')");
                }
            }
			if(params.containsKey("orgId")){
				Integer orgId = (Integer) params.get("orgId");
				if(orgId > 0 ){
					hql.append(" and sd.orgId =").append(orgId);
                	hqlCount.append(" and sd.orgId =").append(orgId);
				}
			}
            if (params.containsKey("regionId")) {
               Integer regionId = (Integer) params.get("regionId");
                if (regionId > 0) {
                	hql.append(" and sd.dictRegionId =").append(regionId);
                	hqlCount.append(" and sd.dictRegionId =").append(regionId);
                }
            }
            if (params.containsKey("proviceId")) {
                Integer proviceId = (Integer) params.get("proviceId");
                if (proviceId > 0) {
                	hql.append(" and sd.dictProviceId =").append(proviceId);
                	hqlCount.append(" and sd.dictProviceId =").append(proviceId);
                }
            }
            if (params.containsKey("orgName2")) {
                hql.append(" and sd.orgId = null");
                hqlCount.append(" and sd.orgId = null");
            }
		}
		hql.append(" order by sd.deptId desc ");
		
		DTablePageModel pageModel = new DTablePageModel();
		pageModel.setsEcho(pageData.getsEcho());
		PageModel pm = super.queryPageByHqlMySQl(hql.toString(), hqlCount.toString(), null, pageData.getCurrentPage(), pageData.getPageSize());
		if(pm != null) {
			pageModel.setiTotalDisplayRecords(pm.getTotal());
			pageModel.setiTotalRecords(pm.getTotal());
			@SuppressWarnings("unchecked")
			List<Object[]> rowLst = pm.getDatas();
			if(rowLst != null) {
				List<SysDeptDto> aidLst = new ArrayList<SysDeptDto>();
				for(Object[] row : rowLst) {
					SysDeptDto aid = new SysDeptDto();
					aid.setDeptId((Integer)row[0]);
					aid.setDeptName((String)row[1]);
					aid.setDeptTel((String)row[2]);
					aid.setRealName((String)row[3]);
					aidLst.add(aid);
				}
				pageModel.setAaData(aidLst);
			}
		}
		return pageModel;
	}
	
	/** 查询系统用户 **/
	public DTablePageModel querySysUserPage(Map<String,Object> params,PageData pageData){
		if(pageData == null) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append(" from SysUser sg where 1 = 1 ").append(" and sg.isDel = ").append(Const.IS_DEL_FALSE);	
		hql.append(" and sg.userStatus = '").append(Const.IS_VALID_TRUE).append("'");
		hqlCount.append("select count(*) from SysUser sg where 1 = 1").append(" and sg.isDel = ").append(Const.IS_DEL_FALSE);
		hqlCount.append(" and sg.userStatus = '").append(Const.IS_VALID_TRUE).append("'");
		if(params != null) {
			if(params.containsKey("orgId")){
				Integer orgId = (Integer) params.get("orgId");
				if(orgId>0){
					hql.append(" and sg.orgId = ").append(orgId);
					hqlCount.append(" and sg.orgId = ").append(orgId);
				}
			}else{
				hql.append(" and sg.orgId = null");
				hqlCount.append(" and sg.orgId = null");
			}
	            if(params.containsKey("userStatus")){
	            	String userStatus = (String) params.get("userStatus");
	            	if(StringUtils.isNotBlank(userStatus)){
	            		hql.append(" and sg.userStatus = '").append(userStatus).append("'");
	            		hqlCount.append(" and sg.userStatus = '").append(userStatus).append("'");
	            	}
	            }
		}
		hql.append(" order by sg.userId desc ");
		return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
	}
	
}
