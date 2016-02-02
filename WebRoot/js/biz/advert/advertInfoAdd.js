jQuery(function($) {
	$("#submenu-menu-advert-user").addClass("active");
	$("#menu-advert").addClass("active");
	$("#menu-advert").addClass("open");
	initUserTable();
	
	$("#advertInfo_startDate").datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
	    language: 'zh-CN',
		weekStart: 1,
	    todayBtn:  1,
		autoclose: true,
		todayHighlight: 1,
//		minView: "hour",
		startView: 2,
//		minView: 2,
		forceParse: 0
	});
	
	$("#advertInfo_endDate").datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
	    language: 'zh-CN',
		weekStart: 1,
	    todayBtn:  1,
		autoclose: true,
		todayHighlight: 1,
//		minView: "hour",
		startView: 2,
//		minView: 2,
		forceParse: 0
	});
	
	//单个追加商品到申购单
	$(document).on("dblclick", "#deviceInfo_list tbody tr", function() {
		var $choiseDevice = getChoiseDeviceInfo($(this));
		$("#deviceIdStr").val($choiseDevice.deviceId);
		/*$("#deviceNameStr").val($choiseDevice.deviceName);*/
		$("#devices_choise_modal").modal('hide');
		//focusNumbox();
	});
	
});

//单个追加用户到申购单
$(document).on("dblclick", "#user_list tbody tr", function() {
	var $choiseUser = getChoiseUserInfo($(this));
	var a = $("#userIdStr").val($choiseUser.userId);
	$("#userNameStr").val($choiseUser.realName);
	$("#user_choise_modal").modal('hide');
	//focusNumbox();
});

//获得选择商品对象
function getChoiseDeviceInfo(ele_goodsRow){
	var $choiseDevice = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseDevice.deviceId = $ele_td.find("input[type=hidden]").eq(0).val();
	$choiseDevice.deviceName = $ele_td.find("label[name='deviceName']").eq(0).html();
	return $choiseDevice;
}

//获得选择用户对象
function getChoiseUserInfo(ele_goodsRow){
	var $choiseUser = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseUser.userId = $ele_td.find("input[type=hidden]").eq(0).val();
	$choiseUser.realName = $ele_td.find("label[name='realName']").eq(0).html();
	return $choiseUser;
}

function focusNumbox() {
	$(".fnumbox").each(function(i){
		var goodsNumVal = $(this).val();
		if(!goodsNumVal) {
			$(this).focus();
			return false;
		}
	});
}

oTable = null;
function initTable(advertInfoId) {
	if(oTable == null) {
		oTable = $("#deviceInfo_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/advert/advertInfo/queryAdvertDevicePage.action?advertInfoId=" + advertInfoId,  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"deviceNo","sClass":"center"},
			               {"mDataProp":"deviceName","mRender": function(data, type, full) { 
			            	   return '<label name="deviceName">'+ data + '</label><input type="hidden" value="'+full.deviceId+'"/>';
			               }},
			               {"mDataProp":"deviceAddress","sClass":"center"},
			               {"mDataProp":"deviceTypeId","sClass":"center"},
			               {"mDataProp":"deviceEara","sClass":"center"},
			               {"mDataProp":"deviceStatus","sClass":"center","mRender": function(data, type, full) { 
			            	   var goodsStatus = '';
			            	   if(data == '0'){
			            		   goodsStatus = '<span class="label label-sm label-error">维修中</span>';
			            	   }
			            	   else if(data == '1') {
			            		   goodsStatus ='<span class="label label-sm label-success">运行中</span>';
			            	   } 
			            	   else if(data == '2') {
			            		   goodsStatus ='<span class="label label-sm label-error">停用</span>';
			            	   }
			            	   else if(data == '3') {
			            		   goodsStatus ='<span class="label label-sm label-error">报废</span>';
			            	   }
			            	   return goodsStatus;
			               }}
			               ],
           "oLanguage": {
               "sProcessing": "正在加载中......",
               "sLengthMenu": "每页显示 _MENU_ 条记录",
               "sZeroRecords": "没有数据！",
               "sEmptyTable": "表中无数据存在！",
               "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
               "sInfoEmpty": "显示0到0条记录",
               "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
               zeroRecords: "没有内容",
               "sSearch": "搜索",
               "oPaginate": {
                   "sFirst": "首页",
                   "sPrevious": "上一页",
                   "sNext": "下一页",
                   "sLast": "末页"
               }
           },
			"aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveData,
		});
	} else {
		oTable.fnDraw(); //重新加载数据
	}
}

uTable = null;
function initUserTable() {
	if(uTable == null) {
		uTable = $("#user_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/advert/advertInfo/queryUserPage.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"auName","sClass":"center","mRender": function(data, type, full) { 
			            	   return '<label name="realName">'+ data + '</label><input type="hidden" value="'+full.advertUserId+'"/>';
			               }},
			               {"mDataProp":"auLinkman","sClass":"center"},
			               {"mDataProp":"auLinktel","sClass":"center"},
			               {"mDataProp":"auLinkaddress","sClass":"center"},
			               {"mDataProp":"auLevel","sClass":"center"},
			               ],
           "oLanguage": {
               "sProcessing": "正在加载中......",
               "sLengthMenu": "每页显示 _MENU_ 条记录",
               "sZeroRecords": "没有数据！",
               "sEmptyTable": "表中无数据存在！",
               "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
               "sInfoEmpty": "显示0到0条记录",
               "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
               zeroRecords: "没有内容",
               "sSearch": "搜索",
               "oPaginate": {
                   "sFirst": "首页",
                   "sPrevious": "上一页",
                   "sNext": "下一页",
                   "sLast": "末页"
               }
           },
			"aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveData,
		});
	} else {
		uTable.fnDraw(); //重新加载数据
	}
}

/**
* sSource   查询链接
* aoData    参数
* fnCallback 返回数据填充方法
*/
function retrieveData(sSource, aoData, fnCallback) { 
	//商品名称或编号
	var keyword = $("#keyword").val();
	var param = {"keyword":keyword};
	

	for(var i = 0;i < aoData.length;i++) {
		var _aoData = aoData[i];
		if(_aoData.name == "iDisplayStart") {
			/*开始页数*/
			param.iDisplayStart = _aoData.value;
		}else if(_aoData.name == "iDisplayLength"){
			/*记录条数*/
			param.iDisplayLength = _aoData.value;
		}else if(_aoData.name == "sEcho"){
			/*操作次数*/
			param.sEcho = _aoData.value;
		}
	}
	//提交访问
    $.ajax( {    
        type: "POST",     
        url: sSource,     
        dataType: "json",    
        data: param, // 以json格式传递  
        success: function(resp) {
            fnCallback(resp);
//            removeMask($('.goods-container'));
        }    
    });    
}
//搜索商品
function searchGoods() {
	if(oTable){
		oTable.fnDraw();
	}
}


//加载设备数据
function resetSelectDevice(advertInfoId){
//	initGoodsTypeTree();
	initTable(advertInfoId);
}

//单个选择用户
function choiseUser(input_goodsName) {
	resetSelectUser();
	$('#user_choise_modal').modal('show');
}  
//加载用户数据
function resetSelectUser(){
	initUserTable();
}

//查看已投放广告的设备
function showDevice(){
	var advertInfoId = $("#advertInfoId").val() + " "; 
	resetSelectDevice(advertInfoId);
	$('#devices_choise_modal').modal('show');
	
}

//非空验证
function checkBlank(){
	var result = true;
	var advertTitle = $("#advertTitle").val();
	var userNameStr = $("#userNameStr").val();
	var file = $("#file").val();
	var advertInfo_startDate = $("#advertInfo_startDate").val();
	var advertInfo_endDate = $("#advertInfo_endDate").val();
	var adFileName = $("#adFileName").val();
	if(!advertTitle){
		$("#titleError").html("<div></div><font color='red'>广告标题不能为空!</font>");
		result=false;
	}
	else if(!file){
		$("#upLoadError").html("<div></div><font color='red'>上传文件不能为空!</font>");
		result=false;
	}
	else if(!adFileName){
		$("#upLoadError").html("<div></div><font color='red'>上传文件名不能为空!</font>");
		result=false;
	}
	else if(!userNameStr){
		$("#nameError").html("<div></div><font color='red'>广告主不能为空!</font>");
		result=false;
	}
	else if(!advertInfo_startDate){
		$("#startError").html("<div></div><font color='red'>广告开始日期不能为空!</font>");
		result=false;
	}
	else if(!advertInfo_endDate){
		$("#endError").html("<div></div><font color='red'>广告结束日期不能为空!</font>");
		result=false;
	}
	return result;
}

function titleError(){
	var advertTitle = $("#advertTitle").val();
	if(advertTitle){
		$("#titleError").html("");
	}
	else{
		$("#titleError").html("<div></div><font color='red'>广告标题不能为空!</font>");
	}
}

function nameError(){
	var userNameStr = $("#userNameStr").val();
	if(userNameStr){
		$("#nameError").html("");
	}
}

function checkAdvertFee(){
	var payFee = $("#payFee").val();
	if(payFee){
		if(isNaN(payFee)){
			$("#feeError").html("<div></div><font color='red'>广告费用必须为数字!</font>");
			$("#payFee").val("");
		}
		else if(payFee.length > 8){
			$("#feeError").html("<div></div><font color='red'>广告费用超过最大值!</font>");
			$("#payFee").val("");
		}
		else{
			$("#feeError").html("");
		}
	}
}

function fileChange(target) {
    var fileSize = 0;         
    fileSize = target.files[0].size;     
     var size = fileSize / 1024;    
     if(size>1100){  
      alert("附件不能大于1M");
      target.value="";
      return
     }
     var name=target.value;
     var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();
     if(fileName !="jpg" && fileName !="png" && fileName !="swf"){
       alert("请选择正确格式文件上传(jpg,png,swf)！");
         target.value="";
         return
     }
   }
