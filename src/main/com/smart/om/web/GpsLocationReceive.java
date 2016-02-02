package com.smart.om.web;

import com.smart.om.biz.car.CarHandler;
import com.smart.om.persist.CarInfo;
import com.smart.om.persist.CarTrack;
import com.smart.om.persist.GpsDevice;
import com.smart.om.util.Const;
import com.smart.om.util.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 车载GPS位置获取接口
 * @author CA
 *
 */
public class GpsLocationReceive {
	private String lng;  //经度
	private String lat;  //纬度
	private String imei;  //设备imei编号
	private static Logger logger = Logger.getLogger(GpsLocationReceive.class);
	@Autowired
	private CarHandler carHandler;
	 
	/**
	 * 每隔1分钟获取一次车辆位置
	 * @throws UnsupportedEncodingException 
	 * @throws JSONException 
	 */
	public void gpsLocationInteface() throws UnsupportedEncodingException{
		CarTrack carTrack = new CarTrack();
		String access_token = this.getAccessToken();
		String param = ""; //接口地址参数
		String result = ""; //调用接口返回的数据
		String ret = "";  //数据返回的状态
		String msg = "";  //数据返回的状态信息
		String device_info = "";  //设备状态
		if(StringUtils.isNotBlank(access_token)){
			param = "account/monitor?map_type=BAIDU&target=18374881060&account=18374881060&time=" + System.currentTimeMillis()/1000L + "&access_token=" + access_token;
			result = this.extInf(param, "GET");
			JSONObject jsonObject = JSONObject.fromObject(result);
			if(jsonObject != null){
				ret = jsonObject.containsKey("ret")?jsonObject.getString("ret"):"";
				msg = jsonObject.containsKey("msg")?jsonObject.getString("msg"):"";
				if("0".equals(ret)){           //获取数据成功
					String locations = jsonObject.getString("data");
					if(locations == null || "".equals(locations)){
						logger.info("数据为空");
					}else{
						JSONArray jsonArray = JSONArray.fromObject(locations);
						for(int i = 0 ;i<jsonArray.size();i++){
							JSONObject locationObject = jsonArray.getJSONObject(i);
							device_info = locationObject.getString("device_info");
							if("0".equals(device_info)){   //获取设备信息成功
								lng = locationObject.getString("lng");  //经度
								lat = locationObject.getString("lat");  //纬度
								imei = locationObject.getString("imei");  //设备ID
								if(StringUtils.isNotBlank(imei)){
									GpsDevice gpsDevice = carHandler.queryGpsDeviceByImei(imei);
									if(gpsDevice != null){
										CarInfo carInfo = carHandler.queryCarById(gpsDevice.getCarId());
										CarTrack track = carHandler.queryCarTrackByCarId(gpsDevice.getCarId());
										if(track != null){
											if(lng.equals(track.getCarLng()) && lat.equals(track.getCarLat())){
												logger.info("位置信息重复....");
											}
											else{
												carTrack.setNewer(true);
												carTrack.setCarId(gpsDevice.getCarId());
												carTrack.setCarLat(lat);
												carTrack.setCarLng(lng);
												carTrack.setCreateDate(DateUtil.getDateY_M_DH_M_SS());
												carInfo.setCarId(gpsDevice.getCarId());
												carInfo.setCarLng(lng);
												carInfo.setCarLat(lat);
												carInfo.setNewer(false);
												carHandler.saveOrUpdateCarInfo(carInfo);
												carHandler.saveOrUpdateCarTrack(carTrack);
											}
										}
										else{
											carTrack.setNewer(true);
											carTrack.setCarId(gpsDevice.getCarId());
											carTrack.setCarLat(lat);
											carTrack.setCarLng(lng);
											carTrack.setCreateDate(DateUtil.getDateY_M_DH_M_SS());
											carInfo.setCarId(gpsDevice.getCarId());
											carInfo.setCarLng(lng);
											carInfo.setCarLat(lat);
											carInfo.setNewer(false);
											carHandler.saveOrUpdateCarInfo(carInfo);
											carHandler.saveOrUpdateCarTrack(carTrack);
										}
									}
									else{
										logger.info("车辆不存在...");
									}
								}
								logger.info("经度：" + lng + "纬度：" + lat + "设备ID:" + imei);
							}
							else{
								logger.info("设备离线或设备已过期，设备状态ID:" + device_info);
							}
						}
					}
				}
				else{
					logger.info("获取数据失败，错误码：" + ret + ",错误信息：" + msg);
				}
			}
			else{
				logger.info("获取车辆位置失败！");
			}
		}
		else{
			logger.info("获取令牌失败！");
		}
	}
	
	/**
	 * 创建URL连接
	 * @param param
	 * @param requestMethod
	 * @return
	 */
	public String extInf(String param,String requestMethod){
		String result = "";
		String str = "";		
		try{
			URL url = new URL(Const.GPS_URL + param);
			System.out.println("url:"+url.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(requestMethod);		//请求类型  POST or GET	
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
			String temp = "";
			while((temp = in.readLine()) != null){ 
				str = str + temp;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		result = str;
		return result;
	}
	
	/**
	 * 获取服务令牌
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws JSONException 
	 */
	public String getAccessToken() throws UnsupportedEncodingException{
		String loginStr = this.extInf(Const.GPS_LOGIN,Const.REQUEST_METHOD_GET);
		JSONObject jsonObject=JSONObject.fromObject(loginStr);
		if(jsonObject.containsKey("access_token")){
			String accessKoken = jsonObject.getString("access_token");
			return accessKoken;
		}
		return "";
	}
	
	/**
	 * 获取当前时间的前几分钟的时间
	 */
	public String getTime(int minute){
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.MINUTE, date.get(Calendar.MINUTE) - minute);
		Date endDate = new Date();
		try {
			endDate = dft.parse(dft.format(date.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String time = dft.format(endDate);
		return time;
	}
	
}
