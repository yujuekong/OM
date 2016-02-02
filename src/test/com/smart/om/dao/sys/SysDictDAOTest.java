package com.smart.om.dao.sys;

import java.util.List;

import com.smart.om.biz.sys.SysFuncHandlerTest;
import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.SysDict;
import com.smart.om.persist.SysUser;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2015/9/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = { "classpath:spring/applicationContext_persist.xml"}
)
public class SysDictDAOTest extends TestCase {
    private static final Logger logger = Logger.getLogger(SysFuncHandlerTest.class);
    @Autowired
    private SysDictDAO sysDictDAO;
    @Autowired
    private BaseDao baseDao;

    @Test
    public void testSysDict() {
        SysDict position_sc = (SysDict) sysDictDAO.queryDictByPcode("HB_TJ");
        SysDict sysDictPid = position_sc.getSysDictPid();
        SysDict sysDictPid1 = sysDictPid.getSysDictPid();

    }
    
    @Test
    public void querySysUserByTel(){
		StringBuffer hql = new StringBuffer();
		hql.append(" from SysUser su where 1=1 ");
		hql.append(" and su.tel = ? ");
		List list = baseDao.find(hql.toString(), new Object[]{"15685254741"});
		System.out.println(list.size());
	}

}
