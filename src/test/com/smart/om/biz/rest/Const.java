package com.smart.om.biz.rest;

import com.smart.om.util.Tools;

public class Const {
	//设备后台接口地址
	public static final String DEVICE_URL = "http://120.25.71.171/";
	public static final String DEVICE_LOGIN = "PFSYSWebAPI/api/Login?UserCode=ycb&Password=123456";
	
	//GPS接口地址
	public static final String GPS_URL = "http://api.gpsoo.net/1/";
	public static final String GPS_LOGIN = "auth/access_token?account=18374881060&time="+ System.currentTimeMillis()/1000L +"&signature="+ Tools.md5(Tools.md5("123456") + System.currentTimeMillis()/1000L) +"";
	//是否有效
	public static final String REQUEST_METHOD_GET = "GET";//GET请求
	public static final String REQUEST_METHOD_POST = "POST";//POST请求
	
	//是否有效
	public static final String IS_VALID_TRUE = "0";//有效或已完成
	public static final String IS_VALID_FALSE = "1";//无效或未完成
	
	//数据是否被删除
	public static final String IS_DEL_FALSE = "0";//正常
	public static final String IS_DEL_TRUE = "1";//删除
	
	//交易类型
	public static final String WEI_XING = "Weixin";//微信支付
	public static final String ALI = "Ali";//支付宝支付
	public static final String CASH = "Cash";//现金支付
	public static final String MEMBER_CAED = "MemberCard";//会员卡
	
	//错误标志
	public static final Integer ERROR_101 = 101;//没有权限
	public static final Integer ERROR_102 = 102;//用户已禁用
	public static final Integer ERROR_103 = 103;//服务器繁忙
	public static final Integer ERROR_404 = 404;//资源未找到
	public static final Integer ERROR_500 = 500;//用户已禁用
	
	//是否有效
	public static final String IS_STATUS_INIT = "0";//未处理
	public static final String IS_STATUS_END = "1";//处理完成
	public static final String IS_STATUS_APPROVE = "2";//审核
	public static final String IS_STATUS_AGENT = "3";//处理中
	
	//图片存放地址
	public static final String DEVICE_CLEAN_IMG_PATH = "/upload/biz/img/clean";//设备清洗图片上传路径
	public static final String DEVICE_MAINTENANCE_IMG_PATH = "/upload/biz/img/maintenance";//设备维修图片上传路径
	public static final String SYS_GOODS_IMG_PATH = "/upload/biz/img/goods";//商品图片上传路径
	
	public static final String IS_AREA = "0";//组织机构
	public static final String IS_REGION = "1";//区域
	public static final String IS_PROVICE = "2";//省份
	public static final String IS_ORG = "3";//服务站
	
	//合同类型
	public static final String CONTRACT_SELLER = "0";//供应商合同
	public static final String CONTRACT_DISTRICT = "1";//商圈合同
	public static final String CONTRACT_ADVERT = "2";//广告合同
	
	//用户操作类型
	public static final String OP_TYPE_CLEAN = "1";//用户设备清洗
	public static final String OP_TYPE_MAINTENANCE = "2";//用户设备维修
	public static final String OP_TYPE_IN = "3";//用户商品入库
	public static final String OP_TYPE_OUTAGENT = "4";//用户商品出库前处理
	public static final String OP_TYPE_OUT = "5";//用户商品出库
	public static final String OP_TYPE_DISPATCHING = "6";//用户配送
}
