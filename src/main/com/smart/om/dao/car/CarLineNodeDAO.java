package com.smart.om.dao.car;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.dto.car.CarNodeLineDto;
import com.smart.om.persist.CarLineNode;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import com.smart.om.util.PageModel;

/**
 * 车辆信息DAO
 * @author lc
 *
 */
public class CarLineNodeDAO extends BaseDao{
	
	/** 分页查询车辆线路节点信息 **/
	public DTablePageModel querycarLineNode(Map<String, Object> params, PageData pageData){
		if(pageData == null) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append(" select cln.lineNodeId,cln.nodeNo,md.districtId,md.districtNo,md.districtName,md.districtAddress,sd.dictName");
		hql.append(" from CarLineNode cln ,MotionDistrict md,SysDict sd");
		hql.append(" where 1 = 1 and cln.isDel = '" + Const.IS_DEL_FALSE + "' ");
		hql.append(" and md.isDel = '" + Const.IS_DEL_FALSE + "' ");
		hql.append(" and cln.nodePositionId = md.districtId ");
		hql.append(" and md.dictOrgId = sd.dictId ");
		
		hqlCount.append(" select count(*) from CarLineNode cln ,MotionDistrict md,SysDict sd");
		hqlCount.append(" where 1 = 1 and cln.isDel = '" + Const.IS_DEL_FALSE + "' ");
		hqlCount.append(" and md.isDel = '" + Const.IS_DEL_FALSE + "' ");
		hqlCount.append(" and cln.nodePositionId = md.districtId ");
		hqlCount.append(" and md.dictOrgId = sd.dictId ");
		
		if (params != null) {
			
			if (params.containsKey("keyword")) {
				String keyword = (String) params.get("keyword");
                if(StringUtils.isNotBlank(keyword)){
                	hql.append(" and cln.nodeNo like '%").append(keyword).append("%' ");
                	hqlCount.append(" and cln.nodeNo like '%").append(keyword).append("%' ");
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
            
            if (params.containsKey("isNull")) {
                String isNull = (String) params.get("isNull");
                if (StringUtils.isNotBlank(isNull)) {
            	   if (params.containsKey("carLineId")) {
            		   	String carLineId = (String) params.get("carLineId");   	
	           	    	if (StringUtils.isNotBlank(carLineId)) {
	           	        	hql.append(" and (cln.carLineId is null or cln.carLineId =").append(Integer.valueOf(carLineId));
	           	        	hql.append(" )");
	           	        	hqlCount.append(" and (cln.carLineId is null or cln.carLineId =").append(Integer.valueOf(carLineId));
	           	        	hqlCount.append(" )");
	           	        }
	                }else{
	    	        	hql.append(" and cln.carLineId is null ");
	    	        	hqlCount.append(" and cln.carLineId is null");
	    	        }
                }
            }
            
		}
		
		
		
		DTablePageModel pageModel = new DTablePageModel();
		pageModel.setsEcho(pageData.getsEcho());
		PageModel pm = super.queryPageByHqlMySQl(hql.toString(), hqlCount.toString(), null, pageData.getCurrentPage(), pageData.getPageSize());
		if(pm != null) {
			pageModel.setiTotalDisplayRecords(pm.getTotal());
			pageModel.setiTotalRecords(pm.getTotal());
			List<Object[]> rowLst = pm.getDatas();
			if(rowLst != null) {
				List<CarNodeLineDto> didLst = new ArrayList<CarNodeLineDto>();
				for(Object[] row : rowLst) {
					CarNodeLineDto cld = new CarNodeLineDto();
					cld.setLineNodeId((Integer)row[0]);
					cld.setNodeNo((String)row[1]);
					cld.setDistrictId((Integer)row[2]);
					cld.setDistrictNo((String)row[3]);
					cld.setDistrictName((String)row[4]);
					cld.setDistrictAddress((String)row[5]);
					cld.setDictName((String)row[6]);
					didLst.add(cld);
				}
				pageModel.setAaData(didLst);
			}
		}
		hql.append(" order by cln.lineNodeId  ");
		return pageModel;
	}
	
	/** 分页查询车辆线路下的所有节点信息 **/
	public List<CarLineNode> queryNodeByCarLineId(Integer carLineId){
		List<CarLineNode> dictList = new ArrayList<CarLineNode>();
		String hql = "from CarLineNode as model1 where model1.carLineId = " + carLineId + " order by model1.nodeSort";
		dictList = this.find(hql, null);		
		return dictList;
	}
	
	/** 根据车辆线路ID更新的所有节点信息 **/
	public int updateLineId(Integer carLineId) {
		String hql = "update from CarLineNode set carLineId = null,nodeSort=null where carLineId =  " + carLineId;
		return this.updateHql(hql, new Object[]{});
	}
	
	/** 根据商圈 id车辆线路下的所有节点信息 **/
	public List<CarLineNode> queryNodeByDistrictId(Integer nodePostionId){
		List<CarLineNode> dictList = new ArrayList<CarLineNode>();
		String hql = "from CarLineNode as model1 where model1.nodePositionId = " + nodePostionId ;
		dictList = this.find(hql, null);		
		return dictList;
	}
}
