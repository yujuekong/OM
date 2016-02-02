package com.smart.om.dao.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.dto.advert.AdvertInfoDto;
import com.smart.om.dto.sys.SysUserDto;
import com.smart.om.persist.SysUser;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import com.smart.om.util.PageModel;

/**
 * 系统用户DAO
 * @author CA
 *
 */
public class SysUserDAO extends BaseDao {
	/** 查询系统用户分页信息 **/
	public DTablePageModel querySysUserPage(Map<String,Object> params,PageData pageData){
		if(pageData == null) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
//		hql.append(" from SysUser sg where 1 = 1 ").append(" and sg.isDel = ").append(Const.IS_DEL_FALSE);	
//		hqlCount.append("select count(*) from SysUser sg where 1 = 1").append(" and sg.isDel = ").append(Const.IS_DEL_FALSE);
		hql.append(" select su.userId,su.userName,su.realName,su.tel,su.email,su.userStatus,su.createDate,sd.dictId,sd.dictName ");
		hql.append(" from SysUser su left join su.sysDict sd where 1 = 1").append(" and su.isDel = ").append(Const.IS_DEL_FALSE);
		hqlCount.append("select count(*) from SysUser su left join su.sysDict sd where 1 = 1").append(" and su.isDel = ").append(Const.IS_DEL_FALSE);
		if(params != null) {
			if(params.containsKey("orgId")){
				String orgId = (String) params.get("orgId");
				if(StringUtils.isNotBlank(orgId)){
					hql.append(" and su.orgId = '").append(orgId).append("'");
					hqlCount.append(" and su.orgId = '").append(orgId).append("'");
				}
			}
			if(params.containsKey("searchAdUser")) {
				String searchAdUser = (String) params.get("searchAdUser");
				if("1".equals(searchAdUser)){
					hql.append(" and su.orgId = null");
					hqlCount.append(" and su.orgId = null");
				}else{
					hql.append(" and (su.userName like '%").append(searchAdUser).append("%' ")
					.append(" or sd.dictName like '%").append(searchAdUser).append("%' ")
					.append(" or su.realName like '%").append(searchAdUser).append("%') ");
					hqlCount.append(" and (su.userName like '%").append(searchAdUser).append("%'")
					.append(" or sd.dictName like '%").append(searchAdUser).append("%' ")
					.append(" or su.realName like '%").append(searchAdUser).append("%') ");
				}
				
			}
			 if (params.containsKey("regionId")) {
	                String regionId = (String) params.get("regionId");
	                if (StringUtils.isNotBlank(regionId)) {
	                	hql.append(" and su.dictRegionId =").append(Integer.valueOf(regionId));
	                	hqlCount.append(" and su.dictRegionId =").append(Integer.valueOf(regionId));
	                }
	            }
	            if (params.containsKey("proviceId")) {
	                String proviceId = (String) params.get("proviceId");
	                if (StringUtils.isNotBlank(proviceId)) {
	                	hql.append(" and su.dictProviceId =").append(Integer.valueOf(proviceId));
	                	hqlCount.append(" and su.dictProviceId =").append(Integer.valueOf(proviceId));
	                }
	            }
	            if(params.containsKey("userStatus")){
	            	String userStatus = (String) params.get("userStatus");
	            	if(StringUtils.isNotBlank(userStatus)){
	            		hql.append(" and su.userStatus = '").append(userStatus).append("'");
	            		hqlCount.append(" and su.userStatus = '").append(userStatus).append("'");
	            	}
	            }
		}
		hql.append(" order by su.userId desc ");
		
		DTablePageModel pageModel = new DTablePageModel();
		pageModel.setsEcho(pageData.getsEcho());
		PageModel pm = super.queryPageByHqlMySQl(hql.toString(), hqlCount.toString(), null, pageData.getCurrentPage(), pageData.getPageSize());
		if(pm != null) {
			pageModel.setiTotalDisplayRecords(pm.getTotal());
			pageModel.setiTotalRecords(pm.getTotal());
			@SuppressWarnings("unchecked")
			List<Object[]> rowLst = pm.getDatas();
			if(rowLst != null) {
				List<SysUserDto> aidLst = new ArrayList<SysUserDto>();
				for(Object[] row : rowLst) {
					SysUserDto aid = new SysUserDto();
					aid.setUserId((Integer)row[0]);
					aid.setUserName((String)row[1]);
					aid.setRealName((String)row[2]);
					aid.setTel((String)row[3]);
					aid.setEmail((String)row[4]);
					aid.setUserStatus((String)row[5]);
					aid.setCreateDate((String)row[6]);
					aid.setDictId((Integer)row[7]);
					aid.setDictName((String)row[8]);
					aidLst.add(aid);
				}
				pageModel.setAaData(aidLst);
			}
		}
		return pageModel;
	}
	
	public DTablePageModel querySysRolePage(Map<String,Object> params,PageData pageData){
		if(pageData == null) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append(" from SysRole sg where 1 = 1 and sg.isDel = '").append(Const.IS_DEL_FALSE).append("'");		
		hqlCount.append("select count(*) from SysRole sg where 1 = 1 and sg.isDel = '").append(Const.IS_DEL_FALSE).append("'");
		if(params != null) {
			if(params.containsKey("searchAdUser")) {
				String searchAdUser = (String) params.get("searchAdUser");
				if(StringUtils.isNotBlank(searchAdUser)) {
					hql.append(" and (sg.auName like '%").append(searchAdUser).append("%'")
					.append(" or sg.auLinkman like '%").append(searchAdUser).append("%') ");
					hqlCount.append(" and (sg.auName like '%").append(searchAdUser).append("%'")
					.append(" or sg.auLinkman like '%").append(searchAdUser).append("%') ");
				}
			}
			 if(params.containsKey("isOrgId")){
	            	String isOrgId = (String) params.get("isOrgId");
	            	if(Const.IS_CHILD.equals(isOrgId)){
		            		hql.append(" and sg.isOrgId = '").append(isOrgId).append("'");
		            		hqlCount.append(" and sg.isOrgId = '").append(isOrgId).append("'");
	            	}
	            }
		}
		hql.append(" order by sg.roleId desc ");
		return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
	}
	
	/** 根据用户名查询用户 **/
	public List<SysUser> querySysUserByName(String username){
		StringBuffer hql = new StringBuffer();
		hql.append("from SysUser su where 1 = 1 and su.isDel = '").append(Const.IS_DEL_FALSE).append("'");
		hql.append(" and su.userName = '").append(username).append("'");
		List<SysUser> list = this.find(hql.toString(), null);
		return list;
	}
	
	/** 根据电话查询用户 **/
	public List<SysUser> querySysUserByTel(String tel){
		StringBuffer hql = new StringBuffer();
		hql.append(" from SysUser su where 1=1 and su.isDel= '").append(Const.IS_DEL_FALSE).append("'");
		hql.append(" and su.tel = '").append(tel).append("'");
		List<SysUser> list = this.find(hql.toString(), null);
		return list;
	}
	
	/** 查询总公司的用户 **/
	public List<SysUser> queryAllUserByRegion(){
		StringBuffer hql = new StringBuffer();
		hql.append(" from SysUser su where 1=1 and su.orgId =" + null);
		hql.append(" and su.isDel = '").append(Const.IS_DEL_FALSE).append("'");
		hql.append("and su.userStatus = '").append(Const.IS_VALID_TRUE).append("'");
		List<SysUser> list = this.find(hql.toString(), null);
		return list;
	}
	
	/**
	 * 定时刷新用户所在坐标
	 */
	public int updateUserCoord(String userTel,String userLng,String userLat){
		String hql = "update from SysUser set userLng = '"+userLng+"',userLat='"+userLat+"' where tel ='"+userTel+"'";
		return this.updateHql(hql, new Object[]{});
	}
	
}
