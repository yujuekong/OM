package com.smart.om.biz.device;

import static org.junit.Assert.fail;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.DeviceType;

/**
 *  设备管理业务的单元测试
 * @author ienovo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = { "classpath:spring/applicationContext_persist.xml"}
	)
public class DeviceHandlerTest {
	private static final Logger logger = Logger.getLogger(DeviceHandlerTest.class);
	
	@Autowired
	private DeviceHandler deviceHandler;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testQueryDeviceType() {
//		List<DeviceType> deviceTypeList = deviceHandler.queryDeviceType();
//		if(deviceTypeList != null){
//			for (DeviceType deviceType : deviceTypeList) {
//				logger.info("name:"+deviceType.getDeviceTypeName());
//			}
//		}
	}

	@Test
	public void testSaveOrUpdateDeviceTypeDAO() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelDeviceTypeById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeviceMaintain(){
		StringBuffer hql = new StringBuffer();
		hql.append(" select aa.maintainId,aa.deviceNo , aa.lastTime,AA.thisTime,sysu.realName,aa.remark,aa.maintainIsFinish from  ");
		hql.append(" (SELECT dm.maintainId,dm.maintainUser,di.deviceNo,di.deviceName,dm.lastTime,dm.thisTime,sysu.realName,dm.remark,dm.maintainIsFinish from DeviceMaintain dm,DeviceInfo di where dm.deviceId = di.deviceId) aa ");
		hql.append(" left join SysUser su on aa.maintainUser = sysu.userId ");
		
		BaseDao basedao = new BaseDao();
		List list = basedao.find(hql.toString(), null);
		System.out.println(list.size());
		
	}
	
}
