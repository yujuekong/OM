package com.smart.om.biz.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;
import com.googlecode.jsonplugin.JSONException;
import com.googlecode.jsonplugin.JSONUtil;
import com.smart.om.biz.rest.Const;
import com.smart.om.persist.SysOrg;

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
			System.out.println("url:"+url.toString());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(requestMethod);		//请求类型  POST or GET	
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
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
		System.out.println("loginStr:" + loginStr);
		JSONObject jsonObject=JSONObject.fromObject(loginStr);
		String accessKoken = jsonObject.getString("access_token");
		return accessKoken;
	}
	
	public static void main(String[] args) {
		BaseController baseController = new BaseController();
		String accessKoken = baseController.getAccessToken();
		StringBuffer param = new StringBuffer();
//		logger.info("accessKoken:"+accessKoken);
//		//测试榨汁机列表
//		param.append("/PFPROWebAPI/api/JuicerApi/GetDeviceInfo?pageIndex=1&pageSize=2&JuicerCode=");
//		param.append("&access_token="+accessKoken);
//		String params = "/PFPROWebAPI/api/JuicerApi/GetDeviceInfo?access_token="+accessKoken+"&pageIndex=1&pageSize=100&JuicerCode=0001692";
//		String deviceList = baseController.extInf(param.toString(), Const.REQUEST_METHOD_GET);
//		logger.info("deviceList:"+deviceList);
		
		//销售列表
//		param.append("/PFPROWebAPI/api/JuicerApi/GetSaleDetailInfo?pageIndex=1&pageSize=50");
//		param.append("&juicerCode=&startTime=2015-09-08T16:30:31.493&endTime=2015-10-1T15:20:31&weixinOutTradeNo=&aliOutTradeNo=");
//		param.append("&access_token="+accessKoken);
//		String saleList = baseController.extInf(param.toString(), Const.REQUEST_METHOD_GET);
//		logger.info("saleList:"+saleList);
		
		//销售列表（无）
//		param.append("/PFPROWebAPI/api/JuicerApi/GetSaleStatistic?");
//		param.append("juicerCodes=&startTime=&endTime=&exchangeTypeCodes=&groupByJuicer=true&groupByExchangeType=true");
//		param.append("&access_token="+accessKoken);
//		String saleList = baseController.extInf(param.toString(), Const.REQUEST_METHOD_GET);
//		logger.info("saleList:"+saleList);
		/**
		 * 消息提醒查询接口（单独根据电话或是机器码无法查询）
		 */
//		param.append("/PFPROWebAPI/api/JuicerApi/GetRemingSettingsInfo?");
//		param.append("access_token=" + accessKoken);
//		param.append("&pageIndex=1&pageSize=10&machineCode=&phone=");
//		String msg = baseController.extInf(param.toString(), Const.REQUEST_METHOD_GET);
//		logger.info(msg);
		/**
		 * 获取串码
		 */
//		param.append("/PFPROWebAPI/api/JuicerApi/GetBunchCodes?");
//		param.append("pageIndex=1&pageSize=10&BunchCode=&");
//		param.append("access_token=" + accessKoken);
//		String bunchCode = baseController.extInf(param.toString(), Const.REQUEST_METHOD_GET);
//		logger.info(bunchCode);
		/**
		 * 查询退款记录
		 */
//		param.append("/PFPROWebAPI/api/JuicerApi/GetRefundInfo?");
//		param.append("pageIndex=1&pageSize=10&exchangeType=" + Const.ALI).append("&trade_no=&out_trade_no=&strOperator=&");
//		param.append("access_token=" + accessKoken);
//		String refund = baseController.extInf(param.toString(), Const.REQUEST_METHOD_GET);
//		logger.info(refund);
		/**
		 * 物料信息查询
		 */
//		param.append("/PFPROWebAPI/api/JuicerApi/GetMediaInfo?");
//		param.append("pageIndex=1&pageSize=10&name=xiaozhang&");
//		param.append("access_token=" + accessKoken);
//		String mediaInfo = baseController.extInf(param.toString(), Const.REQUEST_METHOD_GET);
//		JSONObject jsonObject = JSONObject.fromObject(mediaInfo);
//		String ss = jsonObject.getString("MediaInfos");
//		JSONArray jsonArray = JSONArray.fromObject(ss);
//		JSONObject jsonObject2 = jsonArray.getJSONObject(0);
//		String s = jsonObject2.getString("Name");
//		String s = jsonObject2.getString("Url");
//		logger.info(s);
//		logger.info(mediaInfo);
		/**
		 * 广告信息查询
		 */
		param.append("/PFPROWebAPI/api/JuicerApi/GetAdvertisingInfos?");
		param.append("pageIndex=1&pageSize=10&StartTime=&EndTime=&JuicerCode=&");
		param.append("access_token=" + accessKoken);
		String advertInfo = baseController.extInf(param.toString(), Const.REQUEST_METHOD_GET);
		logger.info(advertInfo);
		/**
		 * 价格信息查询
		 */
//		param.append("/PFPROWebAPI/api/JuicerApi/GetPriceInfos?");
//		param.append("pageIndex=1&pageSize=2147483647&StartTime=&EndTime=&JuicerCode=&Status=&");
//		param.append("access_token=" + accessKoken);
//		String advertInfo = baseController.extInf(param.toString(), Const.REQUEST_METHOD_GET);
//		logger.info(advertInfo);
		/**
		 * 折扣信息查询
		 */
//		param.append("/PFPROWebAPI/api/JuicerApi/GetDiscountInfos?");
//		param.append("pageIndex=1&pageSize=10&StartTime=&EndTime=&JuicerCode=&");
//		param.append("access_token=" + accessKoken);
//		String advertInfo = baseController.extInf(param.toString(), Const.REQUEST_METHOD_GET);
//		logger.info(advertInfo);
		/**
		 * 故障类型查询
		 */
//		param.append("/PFPROWebAPI/api/JuicerApi/GetFaultTypes?");
//		param.append("pageIndex=1&pageSize=32767&FaultTypeCode=&FaultTypeName=&");
//		param.append("access_token=" + accessKoken);
//		String errorType = baseController.extInf(param.toString(), Const.REQUEST_METHOD_GET);
//		JSONObject jsonObject = JSONObject.fromObject(errorType);
//		String result = jsonObject.getString("FaultTypes");
//		JSONArray jsonArray = JSONArray.fromObject(result);
//		for(int i = 0 ; i<jsonArray.size();i++){
//			JSONObject jsonObject2 = jsonArray.getJSONObject(i);
//			String FaultTypeCode = jsonObject2.getString("FaultTypeCode");
//			logger.info(FaultTypeCode);
//		}
//		logger.info(result);
		/**
		 * 故障查询
		 */
//		param.append("/PFPROWebAPI/api/JuicerApi/GetFaults?");
//		param.append("pageIndex=1&pageSize=32767&JuicerCode=&StartTime=&EndTime=&FaultTypeCode=1&");
//		param.append("access_token=" + accessKoken);
//		String error = baseController.extInf(param.toString(), Const.REQUEST_METHOD_GET);
//		logger.info(error);
		
		//会员列表
//		StringBuffer param = new StringBuffer();
//		param.append("/PFPROWebAPI/api/JuicerApi/GetMemberInfo?pageIndex=1&pageSize=10");
//		param.append("&access_token="+accessKoken);
//		String userList = baseController.extInf(param.toString(), Const.REQUEST_METHOD_GET);
//		logger.info("userList:"+userList);
		
		//员工列表
//		StringBuffer param = new StringBuffer();
//		param.append("/PFPROWebAPI/api/JuicerApi/GetEmployeeInfo?pageIndex=1&pageSize=10");
//		param.append("&access_token="+accessKoken);
//		String userList = baseController.extInf(param.toString(), Const.REQUEST_METHOD_GET);
//		logger.info("userList:"+userList);
	}
}


