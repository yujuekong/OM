package com.smart.om.dao.inventory;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.smart.om.dao.sys.SysMenuDAOTest;
import com.smart.om.persist.StorageWarehouse;
import com.smart.om.persist.StorageWarehousingEntry;
import com.smart.om.persist.SysDict;

/**
 * Created by Administrator on 2015/9/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"classpath:spring/applicationContext_persist.xml"}
)
public class WarehouseDAOTest extends TestCase {
    private static final Logger logger = Logger.getLogger(SysMenuDAOTest.class);
    @Autowired
    private WarehouseDAO warehouseDAO;
    @Autowired
    private WarehousingEntryDAO warehousingEntryDAO;
    @Autowired

    @Test
    public void testName() throws Exception {
        StorageWarehouse storageWarehouse = (StorageWarehouse) warehouseDAO.find(144);
        Integer warehouseId = storageWarehouse.getDictProviceId();
        logger.info(warehouseId);

//        SysDict dictProviceId = storageWarehouse.getDictProvice();
//        logger.info(dictProviceId.getDictName());
//        SysDict sysDictPid = dictProviceId.getSysDictPid();
//        logger.info(sysDictPid.getDictName());
    }

//    @Test
//    public void test1() throws Exception {
//        StorageWarehousingEntry storageWarehousingEntry = (StorageWarehousingEntry) warehousingEntryDAO.find(122);
//        SellerInfo SellerInfo = storageWarehousingEntry.getSellerInfo();
//        String sellerName = SellerInfo.getSellerName();
//
//    }
}