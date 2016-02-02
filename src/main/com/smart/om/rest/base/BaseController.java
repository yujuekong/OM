package com.smart.om.rest.base;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.googlecode.jsonplugin.JSONException;
import com.googlecode.jsonplugin.JSONUtil;
import com.smart.om.util.Const;

/**
 * 封装调研设备接口
 * @author langyuk
 *
 */
public class BaseController {
	private static final Logger logger = Logger.getLogger(BaseController.class);
	/**
	 * 返回预期结果
	 */
	private static final int RESULT_CODE_SUCCESS = 0;
	/**
	 * 返回非预期结果
	 */
	private static final int RESULT_CODE_ERROR = 1;
	/**
	 * 服务端返回json的键名：结果代码
	 */	
	private static final String KEY_RESULT_CODE = "RESULT_CODE";
	/**
	 * 服务端返回json的键名：结果数据
	 */
	private static final String KEY_RESULT_DATA = "RESULT_DATA";
	
	/**
	 * 调用外部接口
	 * @param param
	 * @return
	 */
	public String extInf(String param,String requestMethod){
		String result = "";
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		String rTime="";
		String str = "";		
		try{
			long startTime = System.currentTimeMillis(); 					//请求起始时间_毫秒
			URL url = new URL(Const.DEVICE_URL + param);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("contentType", "utf-8");  
			connection.setRequestMethod(requestMethod);		//请求类型  POST or GET	
	        InputStream inStream = connection.getInputStream();  
	        BufferedReader in = new BufferedReader(new InputStreamReader(inStream, "utf-8"));  
			//BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			long endTime = System.currentTimeMillis(); 						//请求结束时间_毫秒
			String temp = "";
			while((temp = in.readLine()) != null){ 
				str = str + temp;
			}
			rTime = String.valueOf(endTime - startTime); 
		}
		catch(Exception e){
			errInfo = "error";
			e.printStackTrace();
		}
		result = str;
		return result;
	}
	
	/**
	 * 调用外部接口POST
	 * @param param
	 * @return
	 */
	public String extInf(String urlStr,String param,String requestMethod){
		String result = "";
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		try{
			long startTime = System.currentTimeMillis(); 					//请求起始时间_毫秒
			URL url = new URL(Const.DEVICE_URL+urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");//请求类型  POST or GET
			connection.setDoInput(true);  
			connection.setDoOutput(true);//如果通过post提交数据，必须设置允许对外输出数据  
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
			connection.connect();
			OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(), "utf-8");  
			osw.write(param);
			osw.flush();  
		    osw.close();
            if(connection.getResponseCode() ==200){
            	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            	String inputLine;  
                while ((inputLine = in.readLine()) != null) {  
                    result += inputLine;  
                }  
                in.close();  
            }
		}
		catch(Exception e){
			errInfo = "error";
			e.printStackTrace();
		}
//		result = str;
		return result;
	}
	
	/** 返回成功时，把Map转换成String **/
	protected String toResultJsonSuccess(Object resultData) {
		return this.toResultJson(RESULT_CODE_SUCCESS, resultData);
	}
	/** 把Map转换成String **/
	protected String toResultJson(int resultCode, Object resultData) {
		try {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put(KEY_RESULT_CODE, resultCode);
			map.put(KEY_RESULT_DATA, resultData);
			return JSONUtil.serialize(map);
		} catch (JSONException ex) {
			return "{\"" + KEY_RESULT_CODE + "\":" + RESULT_CODE_ERROR + ",\""
					+ KEY_RESULT_DATA + "\":\"转换为Json异常\"}";
		}
	}
	
	/** 获得服务验证令牌 **/
	public String getAccessToken(){
		BaseController baseController = new BaseController();
		String loginStr = baseController.extInf(Const.DEVICE_LOGIN,Const.REQUEST_METHOD_GET);
		JSONObject jsonObject=JSONObject.fromObject(loginStr);
		String accessKoken = jsonObject.getString("access_token");
		return accessKoken;
	}
	
	/** 物料信息查询 **/
	public String getFileName(String name,String msg){
		String result = "";
		StringBuffer param = new StringBuffer();
		param.append("/PFPROWebAPI/api/JuicerApi/GetMediaInfo?");
		param.append("pageIndex=1&pageSize=32767&");
		param.append("access_token=" + this.getAccessToken());
		param.append("&name=").append(name);
		String mediaInfo = this.extInf(param.toString(), Const.REQUEST_METHOD_GET);
		if(StringUtils.isNotBlank(mediaInfo)){
			JSONObject jsonObject = JSONObject.fromObject(mediaInfo);
			String mediaInfos = jsonObject.getString("MediaInfos");
			System.out.println(mediaInfos);
			if(StringUtils.isNotBlank(mediaInfos)){
				JSONArray jsonArray = JSONArray.fromObject(mediaInfos);
				if(jsonArray.size() > 0){
					JSONObject json = jsonArray.getJSONObject(0);
					result = json.getString(msg);
				}
			}
		}
		return result;
	}
	
	/** 广告信息查询 **/
	public String getAdvertMsg(String deviceNo,String msg){
		String result = "";
		StringBuffer param = new StringBuffer();
		param.append("/PFPROWebAPI/api/JuicerApi/GetAdvertisingInfos?");
		param.append("pageIndex=1&pageSize=10&StartTime=&EndTime=&");
		param.append("access_token=" + this.getAccessToken());
		param.append("&JuicerCode=").append(deviceNo);
		String advertInfo = this.extInf(param.toString(), Const.REQUEST_METHOD_GET);
		if(StringUtils.isNotBlank(advertInfo)){
			JSONObject jsonObject = JSONObject.fromObject(advertInfo);
			String mediaInfos = jsonObject.getString("Advertisings");
			if(StringUtils.isNotBlank(mediaInfos)){
				JSONArray jsonArray = JSONArray.fromObject(mediaInfos);
				if(jsonArray.size() > 0){
					JSONObject json = jsonArray.getJSONObject(0);
					result = json.getString(msg);
				}
			}
		}
		return result;
	}
	
	/** 创建广告 **/
	public void createAdvert(String startTime,String endTime,String juicerCode,String name){
		System.out.println("name:" + name);
		String url = this.getFileName(name, "Url");
		System.out.println("url:" + url);
		if(StringUtils.isNotBlank(url)){
			String param = Const.ACCESS_TOKEN + "=" + this.getAccessToken() + "&" + Const.START_TIME + "=" + startTime + 
					"&" + Const.END_TIME + "=" + endTime + "&" + Const.JUICER_CODE + "=" + juicerCode + "&" + Const.DOWN_LOAD_URL + "=" + url;
			this.extInf("PFPROWebAPI/api/JuicerApi/AddAdvertising", param, Const.REQUEST_METHOD_POST);
		}
	}
	
	/** 广告删除 **/
	public void delAdvert(int code){
		String param = Const.ACCESS_TOKEN + "=" + this.getAccessToken() + "&" + Const.CODE + "=" + code;
		this.extInf("PFPROWebAPI/api/JuicerApi/DelAdvertising", param, Const.REQUEST_METHOD_POST);
	}
	
	/** 删除物料 **/
	public void delFile(int code){
		String param = Const.ACCESS_TOKEN + "=" + this.getAccessToken() + "&" + Const.CODE + "=" + code;
		this.extInf("PFPROWebAPI/api/JuicerApi/DelMediaInfo", param, Const.REQUEST_METHOD_POST);
	}
	
	public static void main(String[] args) {
		BaseController baseController = new BaseController();
		String accessKoken = baseController.getAccessToken();
//		StringBuffer param = new StringBuffer();
//		String param = "Juicer?access_token="+accessKoken;
//		String deviceList = baseController.extInf(param, Const.REQUEST_METHOD_GET);
		
//		param.append("/PFPROWebAPI/api/JuicerApi/GetMediaInfo?");
//		param.append("pageIndex=1&pageSize=32767&name=测试接口&");
//		param.append("access_token=" + accessKoken);
//		String mediaInfo = baseController.extInf(param.toString(), Const.REQUEST_METHOD_GET);
//		JSONObject jsonObject = JSONObject.fromObject(mediaInfo);
//		String ss = jsonObject.getString("MediaInfos");
//		JSONArray jsonArray = JSONArray.fromObject(ss);
//		JSONObject jsonObject2 = jsonArray.getJSONObject(0);
//		String s = jsonObject2.getString("Url");
//		logger.info(s);
//		logger.info(mediaInfo);
//		String s = "接口测试";
//		String url = baseController.getFileName("接口测试2","Url");
//		System.out.println(url);
		
//		String code = baseController.getAdvertMsg("0000027", "Code");
//		System.out.println(code);
//		String param = Const.ACCESS_TOKEN + "=" + baseController.getAccessToken() + "&" + Const.CODE + "=" + 97;
//		baseController.extInf("PFPROWebAPI/api/JuicerApi/DelAdvertising", param, Const.REQUEST_METHOD_POST);
//		baseController.delAdvert(98);
//		baseController.delFile(62);
		baseController.createAdvert("2016-1-18 9:13", "2016-1-19 9:13", "0000027", "测试接口2");
		
	}
	
	
}
