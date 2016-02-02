package com.smart.om.rest.sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.restlet.engine.adapter.HttpRequest;
import org.springframework.stereotype.Component;

import com.smart.om.biz.sale.SaleHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.SaleInfo;
import com.smart.om.rest.base.BaseController;
import com.smart.om.rest.base.BaseResource;
import com.smart.om.util.Const;
import com.smart.om.util.Tools;

/**
 * 配送管理Rest接口
 * @author langyuk
 *
 */
@Component("SaleResource")
public class SaleResource  extends BaseResource{
	private static final Logger logger = Logger.getLogger(SaleResource.class);
	@Resource
	private SysFuncHandler sysFuncHandler;
	@Resource
	private SaleHandler saleHandler;
	
	//销售信息添加
	public String saleInfoAdd(Map dataMap){
		String result = "";
		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
		SaleInfo sale = new SaleInfo();
		sale.setSaleNo(dataMap.get("bsinessOrderNo").toString());//交易订单号
		
		
		
		sale.setPayResultUrl(dataMap.get("payResultUrl").toString());//支付结果回调的URL
		sale.setBsinessOrderNo(dataMap.get("bsinessOrderNo").toString());//商户订单号
		sale.setBsinessNum(dataMap.get("bsinessNum").toString());//商户号
		sale.setDeviceId(dataMap.get("deviceId").toString());//设备号
		sale.setSaleMoney(Double.parseDouble(dataMap.get("saleMoney").toString()));//总金额
		sale.setGoodsDetails(dataMap.get("goodsDetails").toString());//商品详情
		sale.setGoodsDescribe(dataMap.get("goodsDescribe").toString());//商品描述
		sale.setSaleDate(dataMap.get("saleDate").toString());//交易起始时间和结束时间  创建时间
		sale.setAdditionalData(dataMap.get("additionalData").toString());//附加数据
		sale.setPayType(dataMap.get("payType").toString());//货币类型
		sale.setGoodsId(dataMap.get("goodsId").toString());//商品ID
		sale.setPayStatus("0");//支付状态
		
		//sale.setPayDate(dataMap.get("payDate").toString());//交易起始时间和结束时间  支付时间
		sale.setNewer(true);
		try {
			SaleInfo saleInfo =saleHandler.saveOrUpdateSaleInfoDAO(sale);
			Map<String, Object> saleMap = new HashMap<String, Object>();
			saleMap.put("bsinessOrderNo",saleInfo.getBsinessOrderNo());//商户订单号
			lstMap.add(saleMap);
			result = this.toResultJsonSuccess(lstMap);
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		return result;
	}
	
	//销售信息查询
	public String querySaleInfo(Map dataMap){
		String result = "";
		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
		String bsinessOrderNo = Tools.trim(dataMap.get("bsinessOrderNo"));//商户订单号
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("bsinessOrderNo", bsinessOrderNo);
			List<SaleInfo> saleInfoList =saleHandler.querySaleInfo(bsinessOrderNo);
			Map<String, Object> saleMap = new HashMap<String, Object>();
			if(!saleInfoList.isEmpty() && saleInfoList.size()>0){
				SaleInfo saleInfo = saleInfoList.get(0);
				saleMap.put("bsinessOrderNo", saleInfo.getBsinessOrderNo());
				saleMap.put("bsinessNum", saleInfo.getBsinessNum());
				saleMap.put("saleMoney", saleInfo.getSaleMoney());
				saleMap.put("goodsDetails", saleInfo.getGoodsDetails());
				saleMap.put("goodsDescribe", saleInfo.getGoodsDescribe());
				saleMap.put("goodsId", saleInfo.getGoodsId());
			}
			lstMap.add(saleMap);
			result = this.toResultJsonSuccess(lstMap);
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		return result;
	}
	
	//销售完成返回祈飞服务器
	public String saleFinish(Map dataMap) {
		String bsinessOrderNo = dataMap.get("bsinessOrderNo").toString();
		String payStatus = dataMap.get("payStatus").toString();
		String payDate = dataMap.get("payDate").toString();
		String wrongWord = "";
		String wrongMessage = "";
		if(!"".equals(dataMap.get("wrongWord")) && null!=dataMap.get("wrongWord")){
			wrongWord = dataMap.get("wrongWord").toString();
		}
		if(!"".equals(dataMap.get("wrongMessage")) && null!=dataMap.get("wrongMessage")){
			wrongMessage = dataMap.get("wrongMessage").toString();
		}
		List<SaleInfo> saleInfoList =saleHandler.querySaleInfo(bsinessOrderNo);
		if(!saleInfoList.isEmpty() && saleInfoList.size()>0){
			SaleInfo sale = saleInfoList.get(0);//保存对象
			sale.setBsinessOrderNo(bsinessOrderNo);
			sale.setPayDate(payDate);//交易起始时间和结束时间  支付时间
			sale.setWrongWord(wrongWord);
			sale.setWrongMessage(wrongMessage);
			
			if("1".equals(payStatus)){
				sale.setPayStatus(payStatus);
			}else{
				sale.setPayStatus("0");
			}
			
			SaleInfo saleInfo =saleHandler.saveOrUpdateSaleInfoDAO(sale);//更新数据库
			String canshu ="";
			if(StringUtils.isNotBlank(saleInfo.getSaleNo())){
//				Map<String, Object> saleMap = new HashMap<String, Object>();
				if("1".equals(payStatus)){
//					saleMap.put("status", "SUCCESS");
//					saleMap.put("mag", "交易成功");
//					saleMap.put("bsinessNum",saleInfo.getBsinessNum());
//					saleMap.put("deviceId",saleInfo.getDeviceId());
//					saleMap.put("payStatus", "1");
					
					canshu ="&data={\"status\":\"SUCCESS\",\"mag\":\"交易成功\",\"bsinessNum\":\""+saleInfo.getBsinessNum()+"\",\"deviceId\":\""+saleInfo.getDeviceId()+"\","
							+ "\"payStatus\":\"1\"}";
				}else{
//					saleMap.put("status", "FAIL");
//					saleMap.put("mag", "交易失败");
//					saleMap.put("wrongWord",wrongWord);
//					saleMap.put("wrongMessage", wrongMessage);
//					saleMap.put("saleMoney", saleInfo.getSaleMoney());
//					saleMap.put("bsinessOrderNo", saleInfo.getBsinessOrderNo());
//					saleMap.put("additionalData", saleInfo.getAdditionalData());
//					saleMap.put("payDate", payDate);
					
					canshu ="&data={\"status\":\"FAIL\",\"mag\":\"交易失败\",\"wrongWord\":\""+wrongWord+"\",\"wrongMessage\":\""+wrongMessage+"\","
							+ "\"saleMoney\":\""+saleInfo.getSaleMoney()+"\",\"bsinessOrderNo\":\""+saleInfo.getBsinessOrderNo()+"\","
									+ "\"additionalData\":\""+saleInfo.getAdditionalData()+"\",\"payDate\":\"2015-11-0418:02\"}";
				}
				String url =saleInfo.getPayResultUrl();
				BaseController baseController = new BaseController();
				//调用接口 返回请求
				baseController.extInf(url,canshu,"POST");
				
			}
		}
		return "";
	}
}
