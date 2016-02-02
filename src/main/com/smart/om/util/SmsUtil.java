package com.smart.om.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class SmsUtil {
	/**
	 * 短信商  http://www.ihuyi.com/ 
	 */
	private static String Url = "http://106.ihuyi.com/webservice/sms.php?method=Submit";
	
	/**
	 * 给一个人发送单条短信
	 * @param mobile 手机号
	 * @param code  短信内容
	 */
	public static String sendSms(Map dataMap){
		String mobile = dataMap.get("mobile").toString();
		String code = dataMap.get("code").toString();
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod(Url); 			
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");
	    String content = new String(code);  	    
	    String account = "", password = "";
	    account = Const.SMS_USER_NAME;
		password = Const.SMS_PASSWORD;	    
		NameValuePair[] data = {//提交短信
		    new NameValuePair("account", account), 
		    new NameValuePair("password", password), 			//密码可以使用明文密码或使用32位MD5加密
		    new NameValuePair("mobile", mobile), 
		    new NameValuePair("content", content),
		};		
		method.setRequestBody(data);		
		try {
			client.executeMethod(method);			
			String SubmitResult =method.getResponseBodyAsString();					
			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();
			code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");		
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}	
		return code;
	}
	
	public static void main(String[] args) {
		Map<String, String> smsMap = new HashMap<String,String>();
		smsMap.put("mobile", "13971188375");
		smsMap.put("code", "您的验证码是：1111。请不要把验证码泄露给其他人。");
		String code = sendSms(smsMap);
		System.out.println("return:"+code);
	}

}
