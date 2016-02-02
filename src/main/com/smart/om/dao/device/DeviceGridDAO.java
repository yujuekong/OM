package com.smart.om.dao.device;

import java.util.ArrayList;
import java.util.List;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.DeviceGrid;

/**
 * 设备存放商品
 * @author langyuk
 *
 */
public class DeviceGridDAO extends BaseDao {
	/*根据设备ID查询设备格子*/
	public List<DeviceGrid> queryDeviceGridBydeviceId(Integer deviceId){
		List<DeviceGrid> deviceGridList = new ArrayList<DeviceGrid>();
		String hql = "from DeviceGrid as model1 where model1.deviceId = " + deviceId + " order by model1.gridId";
		deviceGridList = this.find(hql, null);		
		return deviceGridList;
	}

}
