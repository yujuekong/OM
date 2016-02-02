package com.smart.om.dao.car;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.dao.sys.SysDictDAO;
import com.smart.om.persist.CarInfo;
import com.smart.om.persist.SysDict;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

/**
 * 车辆信息DAO
 * @author lc
 *
 */
public class CarInfoDAO extends BaseDao{
	
	@Autowired
	private SysDictDAO sysDictDAO;
	
	/** 分页查询所有车辆信息 **/
	public DTablePageModel queryCarInfo(Map<String, Object> params, PageData pageData){
		if(pageData == null) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append(" from CarInfo ci where 1 = 1 and ci.isDel = '" + Const.IS_DEL_FALSE + "' ");		
		hqlCount.append("select count(*) from CarInfo ci  where 1 = 1 and ci.isDel = '" + Const.IS_DEL_FALSE + "' ");
		
		if (params != null) {
			
			if (params.containsKey("keyword")) {
				String keyword = (String) params.get("keyword");
                if(StringUtils.isNotBlank(keyword)){
                	hql.append(" and ci.carNo like '%").append(keyword).append("%' ");
                	hqlCount.append(" and ci.carNo like '%").append(keyword).append("%' ");
                }
			}
			if (params.containsKey("dictRegionId")) {
                String dictRegionId = (String) params.get("dictRegionId");
                if (StringUtils.isNotBlank(dictRegionId)) {
                	hql.append(" and ci.dictRegionId =").append(Integer.valueOf(dictRegionId));
                	hqlCount.append(" and ci.dictRegionId =").append(Integer.valueOf(dictRegionId));
                }
            }
            if (params.containsKey("dictProviceId")) {
                String dictProviceId = (String) params.get("dictProviceId");
                if (StringUtils.isNotBlank(dictProviceId)) {
                	hql.append(" and ci.dictProviceId =").append(Integer.valueOf(dictProviceId));
                	hqlCount.append(" and ci.dictProviceId =").append(Integer.valueOf(dictProviceId));
                }
            }
            if (params.containsKey("dictOrgId")) {
                String dictOrgId = (String) params.get("dictOrgId");
                if (StringUtils.isNotBlank(dictOrgId)) {
                	hql.append(" and ci.dictOrgId =").append(Integer.valueOf(dictOrgId));
                	hqlCount.append(" and ci.dictOrgId =").append(Integer.valueOf(dictOrgId));
                }
            }
            if (params.containsKey("carStatus")) {
                String deviceStatus = (String) params.get("carStatus");
                if (StringUtils.isNotBlank(deviceStatus)) {
                	hql.append(" and ci.carStatus =").append(Integer.valueOf(deviceStatus));
                	hqlCount.append(" and ci.carStatus =").append(Integer.valueOf(deviceStatus));
                }
            }
            if (params.containsKey("orgId")) {
                Integer orgId = (Integer) params.get("orgId");
                if (orgId != null) {
                	hql.append(" and ci.dictOrgId =").append(Integer.valueOf(orgId));
                	hqlCount.append(" and ci.dictOrgId =").append(Integer.valueOf(orgId));
                }
            }
		}
		
		hql.append(" order by ci.carId  ");
		return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
	}
	/***********查询所有的车辆品牌信息************/
	public List<SysDict> queryDictList(String str){
		SysDict sDict= new SysDict();
		List<SysDict> dictList = new ArrayList<SysDict>();
		sDict = sysDictDAO.queryDictByPcode(str);
		dictList = sysDictDAO.querySubDictByPcode(sDict.getDictCode());
		return dictList;
	}
	
	/**
	 * 查询所有车辆信息
	 * @author lC
	 *
	 */
	public List<CarInfo> queryAllCar(Class cla){
		return super.getObjects(cla);
	}
	
	/**
	 * 根据用户所在分公司查询所有车辆信息
	 * @author lC
	 *
	 */
	public List<CarInfo> queryAllCarForMap(Integer orgId){
		List<CarInfo> dTList = new ArrayList<CarInfo>();
		String hql = " from CarInfo ci where  ci.dictOrgId ='" + orgId + "' and ci.isDel = '" + Const.IS_DEL_FALSE + "'  ";
		dTList = this.find(hql, null);		
		return dTList;
	}
}
