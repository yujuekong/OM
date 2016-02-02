package com.smart.om.biz.goods.impl;

import com.smart.om.biz.advert.impl.AdvertHandlerImplTest;
import com.smart.om.biz.goods.GoodsHandler;
import com.smart.om.persist.GoodsInfo;
import com.smart.om.persist.GoodsType;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"classpath:spring/applicationContext_persist.xml"}
)
public class GoodsHandlerImplTest extends TestCase {
    private static final Logger logger = Logger.getLogger(AdvertHandlerImplTest.class);
    @Autowired
    private GoodsHandler goodsHandler;

    @Test
    public void testQueryGoodsTypePage() throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("keyword", "温带");
        PageData pageData = new PageData();
        pageData.setCurrentPage(1);
        pageData.setPageSize(3);
        DTablePageModel dTablePageModel = goodsHandler.queryGoodsTypePage(params, pageData);
        List<GoodsType> goodsTypeList = dTablePageModel.getAaData();
        for (GoodsType goodsType : goodsTypeList) {
            logger.info("name" + goodsType.getGtName());
        }

    }

    @Test
    public void testAddGoodsType() throws Exception {
        GoodsType goodsType = new GoodsType();
        goodsType.setGtId(963);
        goodsType.setNewer(true);
        goodsHandler.saveOrUpdateGoodsInfo(goodsType);
    }

    @Test
    public void testUpdateGoodsType() throws Exception {
        GoodsType goodsType = (GoodsType) goodsHandler.queryGoodsTypeById(110);
        goodsType.setGtName("测试");
        goodsHandler.saveOrUpdateGoodsInfo(goodsType);
    }

    @Test
    public void testQueryAllGoodsType() throws Exception {
        System.out.println(1);

        List list = goodsHandler.queryAllGoodsType(GoodsType.class);

        for (int i = 0; i < list.size(); i++) {
            GoodsType o = (GoodsType) list.get(i);
            logger.info(o.getGtName());
        }
    }
    @Test
    public void testSaveOrUpdateGoodsInfo() throws Exception {
        GoodsInfo goodsType = new GoodsInfo();
        goodsType.setNewer(true);
        goodsHandler.saveOrUpdateGoodsInfo(goodsType);
    }
}