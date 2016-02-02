package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

public class CarTrack  extends BasePo {

	private Integer trackId;
	private Integer carId;
	private String createDate;
	private String carLng;
	private String carLat;
	
	public Integer getTrackId() {
		return trackId;
	}
	public void setTrackId(Integer trackId) {
		this.trackId = trackId;
	}
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCarLng() {
		return carLng;
	}
	public void setCarLng(String carLng) {
		this.carLng = carLng;
	}
	public String getCarLat() {
		return carLat;
	}
	public void setCarLat(String carLat) {
		this.carLat = carLat;
	}
	
}
