package com.smart.om.dao.region;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.DispatchingTeam;
import com.smart.om.persist.SysUser;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

public class DispatchingTeamDAO extends BaseDao{
	/**
     * 查询配送小组DAO
     **/
    public DTablePageModel queryDispatchTeamPage(Map<String, Object> params, PageData pageData) {
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append("from DispatchingTeam dt where 1=1 ");
        hqlCount.append("select count(*) from DispatchingTeam dt where 1 = 1 ");
        if (params != null) {
            if (params.containsKey("teamKeyword")) {
                String teamKeyword = (String) params.get("teamKeyword");
                if (StringUtils.isNotBlank(teamKeyword)) {
                    hql.append(" and (dt.teamNo like '%").append(teamKeyword).append("%'")
                            .append(" or dt.teamName like '%").append(teamKeyword).append("%'")
                            .append(" or dt.mainSysUser.realName like '%").append(teamKeyword).append("%')");
                    hqlCount.append(" and (dt.teamNo like '%").append(teamKeyword).append("%'")
                    .append(" or dt.teamName like '%").append(teamKeyword).append("%'")
                    .append(" or dt.mainSysUser.realName like '%").append(teamKeyword).append("%')");
                }
            }
            if (params.containsKey("orgId")) {
                Integer orgId =  (Integer) params.get("orgId");
                if (orgId != null) {
                	hql.append(" and dt.dictOrgId =").append(orgId);
                	hqlCount.append(" and dt.dictOrgId =").append(orgId);
                }
            }
        }
        hql.append(" order by dt.teamNo desc");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }
    
    
    public List dispatchingTeamIdList(String subOrderIds){
    	String hql ="";
		if(!"".equals(subOrderIds) && subOrderIds!=null){
			hql ="select dt.teamId from DispatchingTeam dt where dt.carLineId in "
					+ "(select cn.carLineId from CarLineNode cn where cn.nodePositionId in "
					+ "(select ds.districtId from DispatchingSubOrder ds where ds.subOrderId in ("+subOrderIds+") group by ds.districtId) "
							+ "group by cn.carLineId) group by dt.teamId";
	    	return this.find(hql, null);
		}else{
			return null;
		}
    }
    
    public List<DispatchingTeam> queryDispatchingTeamByUserId(Integer id){
    	String hql ="";
    	if(id!=0 && id!=null){
    		hql = "from DispatchingTeam dt where dt.mainUser ="+id+" or dt.otherUser='"+id+"'";
    	}
    	return this.find(hql, null);
    }
    
    /**
     * 查询配送小组成员信息
     */
    public DTablePageModel queryTeamUser(Map<String,Object> params,PageData pageData){
		if(pageData == null) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append(" from SysUser sg where 1 = 1 ").append(" and sg.isDel = ").append(Const.IS_DEL_FALSE);	
		hql.append(" and sg.userStatus = '").append(Const.IS_VALID_TRUE).append("'");
		hql.append(" and sg.userId != 1");
		hqlCount.append("select count(*) from SysUser sg where 1 = 1").append(" and sg.isDel = ").append(Const.IS_DEL_FALSE);
		hqlCount.append(" and sg.userId != 1");
		if (params != null) {
	            if (params.containsKey("keyword")) {
	                String orgId = (String) params.get("keyword");
	                if (orgId != null) {
	                	hql.append(" and sg.orgId =").append(Integer.valueOf(orgId));
	                	hqlCount.append(" and sg.orgId =").append(Integer.valueOf(orgId));
	                }
	            }
	        }
		hql.append(" order by sg.userId desc ");
		return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
	}
    /**
     * 查询配送小组成员信息
     */
    public SysUser userMap(Integer userId){
    	String hql ="";
    	if(userId!=0 && userId!=null){
    		hql = "from SysUser su where su.userId ="+userId;
    	}
		return (SysUser) this.find(hql, null).get(0);
	}
	
    /**
     * 根据未配送单查询配送小组
     */
    public List queryTeamBySubOrderId(Integer id){
    	String hql ="";
    	if(id!=0 && id!=null){
    		hql = "select dt.teamId from DispatchingTeam dt,CarLineNode cln,DispatchingSubOrder dso"
    				+ " where dso.districtId = cln.nodePositionId and cln.carLineId = dt.carLineId and dso.subOrderId  ="+id;
    	}
		return this.find(hql, null);
	}
	
    
}
