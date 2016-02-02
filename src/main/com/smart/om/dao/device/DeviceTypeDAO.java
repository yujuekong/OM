package com.smart.om.dao.device;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.CarLineNode;
import com.smart.om.persist.DeviceType;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

/**
 * 设备类型维护DAO
 * @author lc
 *
 */
public class DeviceTypeDAO extends BaseDao{
	
	/** 查询广告主 **/
	public DTablePageModel queryDeviceType(Map<String, Object> params, PageData pageData){
		if(pageData == null) {
			return null;
		}
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append(" from DeviceType dt where 1 = 1 and dt.isDel = '" + Const.IS_DEL_FALSE + "' ");		
		hqlCount.append("select count(*) from DeviceType dt where 1 = 1 and dt.isDel = '" + Const.IS_DEL_FALSE + "' ");
		if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and dt.deviceTypeNo like '%").append(keyword).append("%'")
                            .append(" or dt.deviceTypeName like '%").append(keyword).append("%'");

                    hqlCount.append(" and dt.deviceTypeNo like '%").append(keyword).append("%'")
                            .append(" or dt.deviceTypeName like '%").append(keyword).append("%'");
                }
            }
		}
		hql.append(" order by dt.deviceTypeId  ");
		return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
	}
	/**
	 * 查询所有设备信息
	 * @author lC
	 *
	 */
	public List<DeviceType> queryAllDeviceType(){
		List<DeviceType> dTList = new ArrayList<DeviceType>();
		String hql = " from DeviceType dt where 1 = 1 and isDel = '" + Const.IS_DEL_FALSE + "'  ";
		dTList = this.find(hql, null);		
		return dTList;
	}
	/**
	 * 根据设备类型删除设备信息
	 * @author lC
	 *
	 */
	public void delDeviceByTypeId(Integer id){
		StringBuffer hql = new StringBuffer();
		hql.append(" delete from DeviceInfo di where di.deviceTypeId ="+id);		
		super.updateHql(hql.toString(),null);
	}
}
