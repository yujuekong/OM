package com.smart.om.dao.car;

import java.util.List;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.CarTrack;
/**
 * 车辆位置DAO
 * @author Administrator
 *
 */
public class CarTrackDAO extends BaseDao{
	/**
	 * 根据时间、车辆获取相同的车辆轨迹
	 * @return
	 */
	public List<CarTrack> queryCarTrackByTime(Integer carId,String time,String carLng,String carLat){
		StringBuffer hql = new StringBuffer();
		System.out.println("daoCarid:" + carId  + ",time:" + time.substring(0, time.length() - 3) + "carLng:" + carLng + "carLat:" + carLat);
		hql.append(" from CarTrack ct where 1 = 1");
		hql.append(" and ct.carId = ").append(carId);
		hql.append(" and ct.carLng = '").append(carLng).append("'");
		hql.append(" and ct.carLat = '").append(carLat).append("'");
		hql.append(" and ct.createDate like '%").append(time.substring(0, time.length() - 3)).append("%'");
		List<CarTrack> list = this.find(hql.toString(), null);
		return list;
	}
	
	/**
	 * 根据车辆ID查询最近时间的车辆轨迹
	 */
	public CarTrack queryCarTrackByCarId(Integer carId){
		StringBuffer hql = new StringBuffer();
		hql.append(" from CarTrack ct where ct.carId = ").append(carId);
		hql.append(" and ct.createDate = ").append(" (select max(c.createDate) from CarTrack c where carId = ").append(carId).append(")");
		List<CarTrack> list = this.find(hql.toString(), null);
		if(list != null && list.size() > 0 ){
			return list.get(0);
		}
		return null;
	};
	
	/**
	 * 根据时间、车辆ID获取车辆轨迹集合
	 * @return
	 */
	public List<CarTrack> queryCarTrack(Integer carId,String startTime,String endTime){
		StringBuffer hql = new StringBuffer();
		hql.append(" from CarTrack ct where 1 = 1");
		hql.append(" and ct.carId = ").append(carId);
		hql.append(" and ct.createDate >= '").append(startTime).append("'");
		hql.append(" and ct.createDate < '").append(endTime).append("'");
		hql.append(" order by createDate");
		List<CarTrack> list = this.find(hql.toString(), null);
		return list;
	}
	
	
}
