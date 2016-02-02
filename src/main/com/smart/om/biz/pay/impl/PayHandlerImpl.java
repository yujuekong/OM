package com.smart.om.biz.pay.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smart.om.biz.pay.PayHandler;
import com.smart.om.dao.base.BasePo;
import com.smart.om.dao.pay.PayCashDAO;
import com.smart.om.dao.pay.PayInfoDAO;
import com.smart.om.persist.PayDeviceCash;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

/**
 * 支付业务逻辑
 *
 * @author langyuk
 */
@Component("PayHandler")
public class PayHandlerImpl implements PayHandler {
    private static final Logger logger = Logger.getLogger(PayHandlerImpl.class);
    @Autowired
    private PayInfoDAO payInfoDAO;//支付DAO
    @Autowired
    private PayCashDAO payCashDAO;//支付DAO

    /**
     * 分页查询支付信息
     */
    public DTablePageModel queryPayInfoPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = payInfoDAO.queryPayInfoPage(params, pageData);
        }
        return pageModel;
    }

    @Override
    public DTablePageModel queryPayCashPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = payCashDAO.queryPayCashPage(params, pageData);
        }
        return pageModel;
    }

    /**
     * 现金管理详情
     */
    @Override
    public List queryPayCashById(Integer id) {
        return payCashDAO.queryPayCashById(id);
    }

    /**
     * 删除现金管理条目
     */
    @Override
    public BasePo deletePayCash(Integer id) {
        return payCashDAO.deletePayCash(id);
    }
    
    /**
     * 根据设备ID和订单ID查询收取金额
     */
    @Override
    public PayDeviceCash queryPayCashByDoId(Integer orderId,Integer deviceId){
    	return payCashDAO.queryPayCashByDoId(orderId,deviceId);
    }
    
    /**
     * 根据设备ID查询收取金额
     */
    @Override
    public PayDeviceCash queryPayCashByDeviceId(Integer deviceId){
    	return payCashDAO.queryPayCashByDeviceId(deviceId);
    }
    
    /**
     * 修改或新增
     */
    @Override
    public PayDeviceCash saveOrUpdatePayDeviceCash(PayDeviceCash payDeviceCash){
    	return (PayDeviceCash)payCashDAO.save(payDeviceCash);
    }
}
