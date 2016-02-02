package com.smart.om.dao.sys;

import java.util.List;

import com.smart.om.persist.SysDict;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.smart.om.persist.SysMenu;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "classpath:spring/applicationContext_persist.xml"}
	)
	
public class SysMenuDAOTest {
	private static final Logger logger = Logger.getLogger(SysMenuDAOTest.class);
	@Autowired
	private SysMenuDAO sysMenuDAO;
	@Autowired
	private SysDictDAO sysDictDAO;
	@Before
	public void setUp() throws Exception {
		logger.info("单元测试setup方法！");
	}

	@Test
	public void testQuerySysMenu() {
		List<SysMenu> menuList = sysMenuDAO.getObjects(SysMenu.class);
		if(menuList != null){
			for (SysMenu sysMenu : menuList) {
				logger.info("菜单名称："+sysMenu.getMenuName());
			}
		}
	}

}
