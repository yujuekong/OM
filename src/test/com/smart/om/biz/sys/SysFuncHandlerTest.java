package com.smart.om.biz.sys;

import com.smart.om.dao.sys.SysDictDAO;
import com.smart.om.persist.SysDict;
import com.smart.om.persist.SysMenu;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "classpath:spring/applicationContext_persist.xml"}
	)
public class SysFuncHandlerTest {
	private static final Logger logger = Logger.getLogger(SysFuncHandlerTest.class);
	
	@Autowired
	private SysFuncHandler sysFuncHandler;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testQuerySysMenu() {
//		List<SysMenu> menuList = sysFuncHandler.querySysMenu();
//		if(menuList != null){
//			for (SysMenu sysMenu : menuList) {
//				logger.info("菜单名称："+sysMenu.getMenuName());
//			}
//		}
	}

}
