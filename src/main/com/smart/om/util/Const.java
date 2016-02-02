package com.smart.om.util;

public class Const {
	//设备后台接口地址
	public static final String DEVICE_URL = "http://120.25.71.171/";
	public static final String DEVICE_LOGIN = "PFSYSWebAPI/api/Login?UserCode=JuicerTest&Password=123456";
	public static final String ADVERT_FILE = "PFPROWebAPI/api/JuicerApi/AddMediaInfo"; //新增物料信息
	public static final String ADVERT_UPLOAD = "PFPROWebAPI/api/JuicerApi/PostFile"; //物料上传
	public static final String Name = "Name"; //新增物料Name参数
	public static final String ACCESS_TOKEN = "access_token"; 
	public static final String FileName = "FileName";  //新增物料FileName参数
	public static final String MEDIA_INFOS = "MediaInfos"; //查询物料返回的参数
	public static final String URL = "Url"; //查询物料文件返回的Url地址
	public static final String START_TIME = "StartTime" ;  //广告开始日期
	public static final String END_TIME = "EndTime";  //广告结束日期
	public static final String JUICER_CODE = "JuicerCode";  //机器码
	public static final String DOWN_LOAD_URL = "DownLoadUrl"; //物料文件下载地址
	public static final String ADVERTISINGS = "Advertisings"; //查询广告返回的参数
	public static final String CODE = "Code"; //广告查询返回的参数 
	public static final String DEL_ADVERT = "PFPROWebAPI/api/JuicerApi/DelAdvertising"; //删除广告
	public static final String ADVERT_ADD = "PFPROWebAPI/api/JuicerApi/AddAdvertising"; //新增广告
	public static final String DEL_FILE = "PFPROWebAPI/api/JuicerApi/DelMediaInfo" ;  //删除物料
	//GPS接口
	public static final String GPS_URL = "http://api.gpsoo.net/1/";
	public static final String GPS_LOGIN = "auth/access_token?account=18374881060&time="+ System.currentTimeMillis()/1000L +"&signature="+ Tools.md5(Tools.md5("123456") + System.currentTimeMillis()/1000L) +"";
	
	//是否有效
	public static final String REQUEST_METHOD_GET = "GET";//GET请求
	public static final String REQUEST_METHOD_POST = "POST";//POST请求
	
	//微信支付
	public static final  String APPID =  "wx055139769b08ab1d";
	public static final String MCHID = "1282424201";
	public static final String AppSecret="5feb68381693161044afc79b71cfc004";
	public static final String API="Huihuibao88812345678912345678912";
	public static final String WEIXINPAYURL="http://yg.i-lavie.cn/rest/rest";//微信返回接口
	public static final String CREATEORDERURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";//生成预支付订单
	public static final String WEI = "http://127.0.0.1:8080/OM/rest/rest";//生成预支付订单
	
	//短信平台信息
	public static final String SMS_USER_NAME = "cf_langyuk";//短信平台账号
	public static final String SMS_PASSWORD = "zhshHHB401";//短信平台密码
	
	//是否有效
	public static final String IS_VALID_TRUE = "0";//有效或已完成
	public static final String IS_VALID_FALSE = "1";//无效或未完成
	
	//数据是否被删除
	public static final String IS_DEL_FALSE = "0";//正常
	public static final String IS_DEL_TRUE = "1";//删除
	
	//错误标志
	public static final Integer ERROR_101 = 101;//没有权限
	public static final Integer ERROR_102 = 102;//用户已禁用
	public static final Integer ERROR_103 = 103;//服务器繁忙
	public static final Integer ERROR_404 = 404;//资源未找到
	public static final Integer ERROR_500 = 500;//用户已禁用
	
	//是否有效
	public static final String IS_STATUS_INIT = "0";//未处理
	public static final String IS_STATUS_END = "1";//处理完成
	public static final String IS_STATUS_APPROVE = "2";//待审核
	public static final String IS_STATUS_AGENT = "3";//处理中

	//是否清洗
	public static final String IS_CLEAN_UNDONE = "0";//未清洗
	public static final String IS_CLEAN_DONE = "1";//已清洗
	public static final String IS_CLEAN_NONEED = "2";//不需要清洗


	//图片存放地址
	public static final String DEVICE_CLEAN_IMG_PATH = "/upload/biz/img/clean";//设备清洗图片上传路径
	public static final String DEVICE_MAINTENANCE_IMG_PATH = "/upload/biz/img/maintenance";//设备维修图片上传路径
	public static final String SYS_GOODS_IMG_PATH = "/upload/biz/img/goods";//商品图片上传路径
	public static final String DEVICE_ORDER_IMG_PATH = "/upload/biz/img/order";//订单图片上传路径
	
	
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
	public static final String OP_TYPE_OUTAGENT = "4";//用户商品出库
	public static final String OP_TYPE_OUT = "5";//用户商品出库处理
	public static final String OP_TYPE_DISPATCHING = "6";//用户配送
	public static final String OP_TYPE_DISTRIBUTION = "7";//商品分配
	
	//用户初始密码
	public static final String USER_INIT_PASSWORD = "000000";
	
	//出库业务类型
	public static final String OUT_BIZ_TYPE_DISPATCHING = "1";//配送出库
	public static final String OUT_BIZ_TYPE_ALLOT = "2";//调拨出库
	public static final String OUT_BIZ_TYPE_ACTIVITY = "3";//活动出库

	//入库业务类型
	public static final String IN_BIZ_TYPE_SELLER = "1";//商家入库
	public static final String IN_BIZ_TYPE_ALLOT = "2";//调拨入库
	public static final String IN_BIZ_TYPE_DEVICE = "3";//设备过期商品反库
	public static final String IN_BIZ_TYPE_REMAINDER = "4";//配送剩余商品反库
	
	//报损类型
	public static final String DEBT_TYPE_STORAGE = "1";//仓库内商品报损
	public static final String DEBT_TYPE_DISPATCHING = "2";//配送过程的损耗
	public static final String DEBT_TYPE_DEVICE = "3";//设备上商品的报损
	
	//角色权限分类
	public static final String AUTH_SERV_APP = "APP";//app权限
	public static final String AUTH_SERV_TREE = "TREE";//后台权限
	
	//总公司和分公司判断
	public static final String IS_PARENT = "0";//总公司
	public static final String IS_CHILD = "1";//分公司
	
	//杯子最大数
	public static final Integer CUP_MAX=80;
	//杯子报警数
	public static final Integer CUP_MIX=20;
	
	//刀片更新周期
	public static final Integer BLADE=2;
	//刀片出库多带数量
	public static final Integer BLADE_NUMBER=5;
	//巡检周期
	public static final Integer POLLING=20;
	
	//榨汁机四个状态
	public static final String DEVICE_MAINTAIN="0";//0维修中、1运行中、2 停止、3 报废
	public static final String DEVICE_STATR="1";
	public static final String DEVICE_STOP="2";
	public static final String DEVICE_BED="3";
	 
	//抽奖活动数据字典code
	public static final String ACTIVITY_PAY = "ACTIVITY_PAY";//游戏消耗类型
	public static final String ACTIVITY_PRIZE = "ACTIVITY_PRIZE"; //奖励类型
	
	//支付方式
	public static final String PAY_XIANJIN="1";
	public static final String PAY_WEIXIN="2";
	public static final String PAY_ZHIFUBAO="3";
	public static final String PAY_YUE="4";
	public static final String PAY_HUIYUANKA="5";
	public static final String PAY_CHONGZHI="6";
	
	//消息提醒
	public static final String WEI_XIU = "设备维护管理";
	public static final String QING_XI = "设备清洗管理";
	public static final String RU_KU = "入库管理";
	public static final String CHU_KU = "出库管理";
	public static final String CHU_LI = "出库处理";
	public static final String PEI_SONG = "配送订单管理";
	
	//APP消费类型
	public static final String ZHI_FU="1";
	public static final String CHONG_ZHI="2";
	
}
