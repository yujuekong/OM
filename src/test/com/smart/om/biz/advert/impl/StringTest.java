package com.smart.om.biz.advert.impl;

import java.util.List;

import org.junit.Test;

import com.smart.om.dao.advert.AdvertInfoDAO;
import com.smart.om.persist.AdvertDevice;

public class StringTest {
	public void queryTest(){
		AdvertInfoDAO advertInfoDAO = new AdvertInfoDAO();
		Integer advertInfoId = 1241;
		List<AdvertDevice> list = advertInfoDAO.queryAdvertDevice(advertInfoId);
		for(AdvertDevice advertDevice:list){
			System.out.println(advertDevice.getAdvertInfoId());
		}
	}
	public static void main(String[] args) {
		String s = "['9ab2c86d3c1e496db669983dfae64b58.swf']";
		String ss = s.substring(1, s.length()-1);
		System.out.println(ss);
	}
		
		
		
	
}
