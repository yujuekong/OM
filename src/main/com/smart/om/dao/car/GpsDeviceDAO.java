package com.smart.om.dao.car;

import java.util.List;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.GpsDevice;
/**
 * gps设备DAO
 * @author Administrator
 *
 */
public class GpsDeviceDAO extends BaseDao{
	/**根据imei查找车辆信息**/
	public GpsDevice queryGpsDeviceByImei(String imei){
		GpsDevice gpsDevice = null;
		StringBuffer hql = new StringBuffer();
		hql.append(" from GpsDevice gd where 1 = 1");
		hql.append(" and gd.imei = '").append(imei).append("' ");
		List<GpsDevice> list = this.find(hql.toString(), null);
		if(list != null && list.size() > 0){
			gpsDevice = list.get(0);
		}
		return gpsDevice;
	}
}
