jQuery(function($) {
	$("#submenu-menu-advert-user").addClass("active");
	$("#menu-advert").addClass("active");
	$("#menu-advert").addClass("open");

	initMainUserTable();
	initOtherUserTable();
	initCarTable();
	initCarLineTable();
	
});

//单个追加商品到申购单
$(document).on("dblclick", "#deviceInfo_list tbody tr", function() {
	var $choiseDevice = getChoiseDeviceInfo($(this));
	$("#deviceIdStr").val($choiseDevice.deviceId);
	/*$("#deviceNameStr").val($choiseDevice.deviceName);*/
	$("#devices_choise_modal").modal('hide');
	//focusNumbox();
});

//单个追加用户到申购单
$(document).on("dblclick", "#mainUser_list tbody tr", function() {
	var $choiseUser = getChoiseUserInfo($(this));
	var userIdStr2 = $("#userIdStr2").val();
	if(userIdStr2 == $choiseUser.userId){
		alert("该用户已被选为组员，请选择其他用户");
	}
	else{
		var a = $("#userIdStr").val($choiseUser.userId);
		$("#userNameStr").val($choiseUser.realName);
		$("#mainUser_choise_modal").modal('hide');
	}
});

//单个追加用户到申购单
$(document).on("dblclick", "#otherUser_list tbody tr", function() {
	var $choiseUser = getChoiseUserInfo($(this));
	var userIdStr = $("#userIdStr").val();
	if(userIdStr == $choiseUser.userId){
		alert("该用户已选为负责人，请选择其他用户！");
	}else{
		var a = $("#userIdStr2").val($choiseUser.userId);
		$("#userNameStr2").val($choiseUser.realName);
		$("#otherUser_choise_modal").modal('hide');
	}
});

//单个追加车辆到申购单
$(document).on("dblclick", "#car_list tbody tr", function() {
	var $choiseCarInfo = getChoiseCarInfo($(this));
	var a = $("#carIdStr").val($choiseCarInfo.carId);
	$("#carNameStr").val($choiseCarInfo.carNo);
	$("#car_choise_modal").modal('hide');
	
});

//单个追加线路到申购单
$(document).on("dblclick", "#carLine_list tbody tr", function() {
	var $choiseCarLine = getChoiseCarLine($(this));
	var a = $("#carLineIdStr").val($choiseCarLine.carLineId);
	$("#carLineNameStr").val($choiseCarLine.carLineName);
	$("#carLine_choise_modal").modal('hide');
	
});

//获得选择车辆对象
function getChoiseCarInfo(ele_goodsRow){
	var $choiseCarInfo = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseCarInfo.carId = $ele_td.find("input[type=hidden]").eq(0).val();
	$choiseCarInfo.carNo = $ele_td.find("label[name='carNo']").eq(0).html();
	return $choiseCarInfo;
}

//获得选择用户对象
function getChoiseUserInfo(ele_goodsRow){
	var $choiseUser = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseUser.userId = $ele_td.find("input[type=hidden]").eq(0).val();
	$choiseUser.realName = $ele_td.find("label[name='realName']").eq(0).html();
	return $choiseUser;
}
//获得选择线路对象
function getChoiseCarLine(ele_goodsRow){
	var $choiseCarLine = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseCarLine.carLineId = $ele_td.find("input[type=hidden]").eq(0).val();
	$choiseCarLine.carLineName = $ele_td.find("label[name='lineName']").eq(0).html();
	return $choiseCarLine;
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

uTable = null;
function initMainUserTable() {
	if(uTable == null) {
		uTable = $("#mainUser_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/region/dispatchTeam/queryTeamUser.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"realName","sClass":"center","mRender": function(data, type, full) { 
			            	   return '<label name="realName">'+ data + '</label><input type="hidden" value="'+full.userId+'"/>';
			               }},
			               {"mDataProp":"tel","sClass":"center"},
			               {"mDataProp":"email","sClass":"center"},
			               {"mDataProp":"userStatus","sClass":"center","mRender": function(data, type, full) { 
			            	   var goodsStatus = '';
			            	   if(data == '0'){
			            		   goodsStatus = '<span class="label label-sm label-success">正常</span>';
			            	   }
			            	   else if(data == '1') {
			            		   goodsStatus = '<span class="label label-sm label-error">禁用</span>';
			            	   } 
			            	   return goodsStatus;
			               }},
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveMainData,
		});
	} else {
		uTable.fnDraw(); //重新加载数据
	}
}

tTable = null;
function initOtherUserTable() {
	if(tTable == null) {
		tTable = $("#otherUser_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/region/dispatchTeam/queryTeamUser.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"realName","sClass":"center","mRender": function(data, type, full) { 
			            	   return '<label name="realName">'+ data + '</label><input type="hidden" value="'+full.userId+'"/>';
			               }},
			               {"mDataProp":"tel","sClass":"center"},
			               {"mDataProp":"email","sClass":"center"},
			               {"mDataProp":"userStatus","sClass":"center","mRender": function(data, type, full) { 
			            	   var goodsStatus = '';
			            	   if(data == '0'){
			            		   goodsStatus = '<span class="label label-sm label-success">正常</span>';
			            	   }
			            	   else if(data == '1') {
			            		   goodsStatus = '<span class="label label-sm label-error">禁用</span>';
			            	   } 
			            	   return goodsStatus;
			               }},
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveOtherData,
		});
	} else {
		tTable.fnDraw(); //重新加载数据
	}
}

cTable = null;
function initCarTable() {
	if(cTable == null) {
		cTable = $("#car_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/car/carInfo/queryCarInfo.action?carStatus=0",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"carNo","sClass":"center","mRender": function(data, type, full) { 
			            	   return '<label name="carNo">'+ data + '</label><input type="hidden" value="'+full.carId+'"/>';
			               }},
			               {"mDataProp":"sysDictBrand.dictDesc","sClass":"center"},
			               {"mDataProp":"sysDictType.dictDesc","sClass":"center"},
			               {"mDataProp":"carWeight","sClass":"center"},
			               {"mDataProp":"carStatus","sClass":"center","mRender": function(data, type, full) { 
			            	   var goodsStatus = '';
			            	   if(data == '0'){
			            		   goodsStatus = '<span class="label label-sm label-success">正常</span>';
			            	   }
			            	   else if(data == '1') {
			            		   goodsStatus = '<span class="label label-sm label-error">禁用</span>';
			            	   } 
			            	   return goodsStatus;
			               }},
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
			"fnServerData": retrieveCarData,
		});
	} else {
		cTable.fnDraw(); //重新加载数据
	}
}

lTable = null;
function initCarLineTable() {
	if(lTable == null) {
		lTable = $("#carLine_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/car/carLine/queryCarLine.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"lineNo","sClass":"center"},
			               {"mDataProp":"lineName","sClass":"center","mRender": function(data, type, full) { 
			            	   return '<label name="lineName">'+ data + '</label><input type="hidden" value="'+full.carLineId+'"/>';
			               }},
			               {"mDataProp": "lineType", "sClass":"center","mRender": function (data, type, full) {
			                    var lineType = '';
			                    if (data == '0') {
			                    	lineType = '<label  class="label label-sm label-info">时间最短</label>';
			                    }
			                    else if (data == '1') {
			                    	lineType = '<label class="label label-sm label-warning">线路最短</label>';
			                    }
			                    else if (data == '2') {
			                    	lineType = '<label  class="label label-sm label-success">花费最少</label>';
			                    }
			                    return lineType;
			                }
			                },
			               
			               {"mDataProp":"lineLength","sClass":"center"},
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3 ]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveCarData,
		});
	} else {
		lTable.fnDraw(); //重新加载数据
	}
}

/**
* sSource   查询链接
* aoData    参数
* fnCallback 返回数据填充方法
*/
function retrieveMainData(sSource, aoData, fnCallback) { 
	//商品名称或编号
	var keyword = $("#orgId").val();
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
        }    
    });    
}

/**
* sSource   查询链接
* aoData    参数
* fnCallback 返回数据填充方法
*/
function retrieveOtherData(sSource, aoData, fnCallback) { 
	//商品名称或编号
	var keyword = $("#orgId").val();
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
        }    
    });    
}

/**
* sSource   查询链接
* aoData    参数
* fnCallback 返回数据填充方法
*/
function retrieveCarData(sSource, aoData, fnCallback) { 
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
        }    
    });    
}
//搜索商品
function searchGoods() {
	if(oTable){
		oTable.fnDraw();
	}
}

//选择负责人
function choiseMainUser(input_goodsName) {
	initMainUserTable();
	$('#mainUser_choise_modal').modal('show');
}  
//单个组员
function choiseOtherUser(input_goodsName) {
	initOtherUserTable();
	$('#otherUser_choise_modal').modal('show');
} 
//选择配送车辆
function choiseCar(input_goodsName){
	initCarTable();
	$('#car_choise_modal').modal('show');
}
//选择配送路线
function choiseCarLine(input_goodsName){
	initCarLineTable();
	$('#carLine_choise_modal').modal('show');
}


