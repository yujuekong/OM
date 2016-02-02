package com.smart.om.persist;

public class GpsDevice {
	private Integer gpsDeviceId;
	private String imei; //gps设备imei标识码
	private Integer carId;
	
	public Integer getGpsDeviceId() {
		return gpsDeviceId;
	}
	public void setGpsDeviceId(Integer gpsDeviceId) {
		this.gpsDeviceId = gpsDeviceId;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	
	
}
