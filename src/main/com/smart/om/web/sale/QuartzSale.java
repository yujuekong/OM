package com.smart.om.web.sale;

import com.smart.om.biz.sale.SaleHandler;
import com.smart.om.persist.SaleInfo;
import com.smart.om.rest.base.BaseController;
import com.smart.om.util.Const;
import com.smart.om.util.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

public class QuartzSale {
	@Resource
    private SaleHandler saleHandler;
	/**
     * 销售记录  接口
	 * @throws UnsupportedEncodingException 
     */
    public void  saleInfoInterface() throws UnsupportedEncodingException{
    	//"2016-01-09";
		String date =DateUtil.getDateY_M_D();
		String date1 = date+"T00:00:00";//结束时间
		String date2 = DateUtil.getBeforeDayOfSpecified(date)+"T00:00:00";//开始时间
		
		//先删除当天的数据
		saleHandler.deleteSaleInfoIntraday(date2,date1);
		
		
		BaseController baseController = new BaseController();
		String accessKoken = baseController.getAccessToken();
		StringBuffer param = new StringBuffer();
		
		param.append("/PFPROWebAPI/api/JuicerApi/GetSaleDetailInfo?");
		param.append("access_token=" + accessKoken);
		param.append("&juicerCode=&startTime="+date2+"&endTime="+date1+"&weixinOutTradeNo=&aliOutTradeNo=&pageIndex=1&pageSize=2147483647");
		String refund = baseController.extInf(param.toString(), Const.REQUEST_METHOD_GET);
		JSONObject json = JSONObject.fromObject(refund);
		String jsonString = json.containsKey("SaleDetails")?json.getString("SaleDetails"):null;
		JSONArray jsonList = JSONArray.fromObject(jsonString);
		
		//新增当天数据
		if(jsonList!=null){
			for(Object obj:jsonList){
				SaleInfo saleInfo =new SaleInfo();
				JSONObject jsonObject=JSONObject.fromObject(obj);
				saleInfo.setSaleNo(jsonObject.getString("SaleCode"));//交易号
				saleInfo.setSaleDate(jsonObject.getString("CreateOn"));//交易时间
				saleInfo.setSaleAmount(Double.parseDouble(jsonObject.getString("CupNumber")));//杯数
				saleInfo.setSaleMoney(Double.parseDouble(jsonObject.getString("ExchangeMoney")));//交易金额,单位元
				if(!"".equals(jsonObject.getString("WeixinOutTradeNo"))){
					saleInfo.setBsinessOrderNo(jsonObject.getString("WeixinOutTradeNo"));//商户订单号
				}else{
					saleInfo.setBsinessOrderNo(jsonObject.getString("AliOutTradeNo"));//商户订单号
				}
				saleInfo.setIndentNum(jsonObject.getString("TransactionId"));
				saleInfo.setDeviceId(jsonObject.getString("JuicerCode"));//设备编号
				String juicerName = jsonObject.getString("JuicerName");
//				byte[] temp=juicerName.getBytes("UTF-8");//这里写原编码方式
//				byte[] newtemp=new String(temp,"UTF-8").getBytes("GBK");//这里写转换后的编码方式
//	            juicerName=new String(newtemp,"UTF-8");//这里写转换后的编码方式
//				System.out.println("juicerName----------------"+juicerName);
				saleInfo.setDeviceName(juicerName);//设备名称
				saleInfo.setPayType(jsonObject.getString("ExchangeTypeCode"));//交易类型
				saleInfo.setNewer(true);
				saleHandler.saveOrUpdateSaleInfoDAO(saleInfo);
			}
		}
    }
}
