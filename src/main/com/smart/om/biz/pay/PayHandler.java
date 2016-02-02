package com.smart.om.biz.pay;

import java.util.List;
import java.util.Map;

import com.smart.om.dao.base.BasePo;
import com.smart.om.persist.PayDeviceCash;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

/**
 * 支付业务逻辑
 *
 * @author langyuk
 */
public interface PayHandler {

    /**
     * 分页查询支付信息
     */
    DTablePageModel queryPayInfoPage(Map<String, Object> params, PageData pageData);

    /**
     * 现金管理分页
     */
    DTablePageModel queryPayCashPage(Map<String, Object> params, PageData pageData);

    /**
     * 现金管理详情
     */
    List queryPayCashById(Integer id);

    /**
     * 删除现金管理条目
     */
    BasePo deletePayCash(Integer id);

    /**
     * 根据设备ID和订单ID查询收取金额
     */
    PayDeviceCash queryPayCashByDoId(Integer orderId,Integer deviceId);
    
    /**
     * 根据设备ID查询收取金额
     */
    PayDeviceCash queryPayCashByDeviceId(Integer deviceId);
    
    /**
     * 修改或新增
     */
    PayDeviceCash saveOrUpdatePayDeviceCash(PayDeviceCash payDeviceCash);

}
