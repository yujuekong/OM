package com.smart.om.biz.advert.impl;

import java.security.Provider;
import java.security.Security;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.smart.om.util.DateUtil;

public class Test {
	public static void main(String[] args) throws ParseException{
//		Provider[] provider = Security.getProviders(); 
//		for(Provider provid:provider){
//			System.out.println(provid.toString());
//		}
//		//unix时间戳转换为正常格式时间（参数为毫秒）
//		String date = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date(1450057053*1000L));
//		//当前系统时间转换为时间戳
//		long time = System.currentTimeMillis() / 1000;
//		//时间转换为unix时间戳
//		long epoch = new java.text.SimpleDateFormat ("dd/MM/yyyy HH:mm:ss").parse("09/22/2008 16:33:00").getTime();
//		System.out.println(date);


//		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
//		Date beginDate = new Date();
//		Calendar date = Calendar.getInstance();
//		date.setTime(beginDate);
//		System.out.println(date.get(Calendar.MINUTE));
//		date.set(Calendar.MINUTE, date.get(Calendar.MINUTE) - 1);
//		Date endDate = dft.parse(dft.format(date.getTime()));
//		String s = dft.format(endDate);
//		String b = s.substring(0, s.length() - 3);
//		System.out.println(b);
		System.out.println(DateUtil.getDateY_M_DH_M_SS());
	}
}
