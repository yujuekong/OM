package com.smart.om.biz.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class BaseGpsRest {
	
	/**
	 * 调用外部接口
	 * @param param
	 * @return
	 */
	public static String extInf(String param,String requestMethod){
		String result = "";
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "success";
		String str = "";		
		try{
			URL url = new URL(Const.GPS_URL + param);
			System.out.println("url:"+url.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(requestMethod);		//请求类型  POST or GET	
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String temp = "";
			while((temp = in.readLine()) != null){ 
				str = str + temp;
			}
		}
		catch(Exception e){
			errInfo = "error";
			e.printStackTrace();
		}
		result = str;
		return result;
	}
	
	/** 获得服务验证令牌 **/
	public static String getAccessToken(){
		String loginStr = extInf(Const.GPS_LOGIN,Const.REQUEST_METHOD_GET);
		System.out.println(loginStr);
		JSONObject jsonObject=JSONObject.fromObject(loginStr);
		String s = jsonObject.getString("ret");
		System.out.println("s:" + s);
		String msg = jsonObject.getString("msg");
		System.out.println("msg:" + msg);
		String accessKoken = jsonObject.getString("access_token");
		return accessKoken;
	}
	
	public static void main(String[] args) {
		//获取一个账户名下所有设备最新位置信息(target为要监控的账户(在汽车在线平台的账号))
		String access_token =  getAccessToken();
		System.out.println(access_token);
//		String loginStr = extInf(Const.GPS_URL,"account/monitor?map_type=BAIDU&target=mycar&account=''&time=" + System.currentTimeMillis()/1000L + "&access_token=" + access_token);
//		System.out.println(access_token);
	}
	
}


