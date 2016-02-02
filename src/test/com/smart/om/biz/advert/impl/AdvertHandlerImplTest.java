package com.smart.om.biz.advert.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.smart.om.biz.advert.AdvertHandler;
import com.smart.om.persist.AdvertDevice;
import com.smart.om.persist.AdvertUser;
import com.smart.om.util.PageModel;

/**
 * 广告管理业务的单元测试
 * @author langyuk
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "classpath:spring/applicationContext_persist.xml"}
	)
public class AdvertHandlerImplTest {
	private static final Logger logger = Logger.getLogger(AdvertHandlerImplTest.class);
	
	@Autowired
	private AdvertHandler advertHandler;
	@Before
	public void setUp() throws Exception {
	}

	/** 分页查询广告主 **/
	@Test
	public void testQueryAdvertUserPage() {
		Integer id = 1242;
		List<AdvertDevice> list = advertHandler.queryAdvertDevice(id);
		for(AdvertDevice advertDevice:list){
			advertHandler.deleteAdvertDevice(advertDevice);
		}
	}
	
	@Test
	public void testQueryAdvertDeviceSum(){
		Integer id = 1;
		Long sum = advertHandler.totalDevice(id);
		logger.info("sum:" +sum);
	}

}
