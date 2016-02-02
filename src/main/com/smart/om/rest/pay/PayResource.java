package com.smart.om.rest.pay;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.PayInfo;
import com.smart.om.persist.SaleInfo;
import com.smart.om.rest.base.BaseResource;
import com.smart.om.util.Const;

/**
 * 配送管理Rest接口
 * @author langyuk
 *
 */
@Component("PayResource")
public class PayResource  extends BaseResource{
	private static final Logger logger = Logger.getLogger(PayResource.class);
	@Resource
	private SysFuncHandler sysFuncHandler;
	
	//销售信息
	public String payInfoAdd(Map dataMap){
		String result = "";
		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
		PayInfo payInfo = new PayInfo();
		
		SaleInfo sale = new SaleInfo();
		payInfo.setBsinessOrderNo(dataMap.get("BSINESS_ORDER_NO").toString());//商户订单号
		payInfo.setDeviceNo(dataMap.get("DEVICE_NO").toString());//设备号
		payInfo.setSaleDate(dataMap.get("SALE_DATE").toString());//交易起始时间和结束时间              交易创建时间    
		payInfo.setPayDate(dataMap.get("PAY_DATE").toString());//交易起始时间和结束时间             付款时间
		payInfo.setPayType(dataMap.get("PAY_TYPE").toString());//货币类型
		//商品ID
		

		sale.setBsinessNum(dataMap.get("BSINESS_NUM").toString());//商户号
		sale.setDeviceId(dataMap.get("DEVICE_ID").toString());//设备号
		sale.setSaleMoney(Double.parseDouble(dataMap.get("SALE_MONEY").toString()));//总金额


		try {
			result = this.toResultJsonSuccess(lstMap);
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		return result;
	}
	
}
