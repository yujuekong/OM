jQuery(function($) {
	$("#submenu-menu-device-clean").addClass("active");
	$("#menu-device").addClass("active");
	$("#menu-device").addClass("open");
	initTable();
	initUserTable();
	initCarTable();
	initCarLineTree();
	initCarTree();
	//单个追加设备到申购单
	$(document).on("dblclick", "#deviceInfo_list tbody tr", function() {
		var $choiseDevice = getChoiseDeviceInfo($(this));
		var a = $("#deviceIdStr").val($choiseDevice.deviceId);
		$("#deviceNameStr").val($choiseDevice.deviceName);
		$("#goods_choise_modal").modal('hide');
		//focusNumbox();
	});
	//单个追加用户到申购单
	$(document).on("dblclick", "#user_list tbody tr", function() {
		var $choiseUser = getChoiseUserInfo($(this));
		var a = $("#userIdStr").val($choiseUser.userId);
		$("#userNameStr").val($choiseUser.realName);
		$("#user_choise_modal").modal('hide');
		//focusNumbox();
	});
	//单个追加车辆到申购单
	$(document).on("dblclick", "#car_list tbody tr", function() {
		var $choiseCar = getChoiseCarInfo($(this));
		var a = $("#carIdStr").val($choiseCar.carId);
		$("#carNameStr").val($choiseCar.carNo);
		$("#car_choise_modal").modal('hide');
		//focusNumbox();
	});

});

//获得选择设备对象
function getChoiseDeviceInfo(ele_goodsRow){
	var $choiseDevice = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseDevice.deviceId = $ele_td.find("label[name='deviceId']").eq(0).html();
	$choiseDevice.deviceName = $ele_td.find("label[name='deviceName']").eq(0).html();
	return $choiseDevice;
}
//获得选择用户对象
function getChoiseUserInfo(ele_goodsRow){
	var $choiseUser = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseUser.userId = $ele_td.find("label[name='userId']").eq(0).html();
	$choiseUser.realName = $ele_td.find("label[name='realName']").eq(0).html();
	return $choiseUser;
}
//获得选择车辆对象
function getChoiseCarInfo(ele_goodsRow){
	var $choiseCar = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseCar.carId = $ele_td.find("label[name='carId']").eq(0).html();
	$choiseCar.carNo = $ele_td.find("label[name='carNo']").eq(0).html();
	return $choiseCar;
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
function initTable() {
	if(oTable == null) {
		oTable = $("#deviceInfo_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH +"/view/car/carLine/queryCarLine.action",  //异步请求地址
			"aoColumns" : [ 
							{"mDataProp":"carLineId","mRender": function(data, type, full) { 
			            	   return '<label name="deviceId">'+ data + '</label>';
			               }},
			               {"mDataProp":"lineLength","sClass":"center"},
			               {
			                    "mDataProp": "lineType", "sClass":"center","mRender": function (data, type, full) {
			                    var lineType = '';
			                    if (data == '0') {
			                    	lineType = '<button  class="label label-sm label-info">时间最短</button>';
			                    }
			                    else if (data == '1') {
			                    	lineType = '<button class="label label-sm label-warning">线路最短</button>';
			                    }
			                    else if (data == '2') {
			                    	lineType = '<button  class="label label-sm label-success">花费最小</button>';
			                    }
			                    return lineType;
			                }
			                },
			               {"mDataProp":"lineName","mRender": function(data, type, full) { 
			            	   return '<label name="deviceName">'+ data + '</label>';
			               }},
			               {"mDataProp":"lineNo","sClass":"center"},
			               {"mDataProp":"startDate","sClass":"center"},
			               {"mDataProp":"endDate","sClass":"center"},
			               {"mDataProp":"ctlHour","sClass":"center"},
			               {"mDataProp":"agentUser","sClass":"center"},
			               {"mDataProp":"agentDate","sClass":"center"},
			               ],
           "oLanguage": {
               "sProcessing": "正在加载中......",
               "sLengthMenu": "每页显示 _MENU_ 条记录",
               "sZeroRecords": "没有数据！",
               "sEmptyTable": "表中无数据存在！",
               "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
               "sInfoEmpty": "显示0到0条记录",
               "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
               "zeroRecords": "没有内容",
               "sSearch": "搜索",
               "oPaginate": {
                   "sFirst": "首页",
                   "sPrevious": "上一页",
                   "sNext": "下一页",
                   "sLast": "末页"
               }
           },
			"aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6, 7]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveDataCarLine,
		});
	} else {
		oTable.fnDraw(); //重新加载数据
	}
}
//加载用户列表
uTable = null;
function initUserTable() {
	if(uTable == null) {
		uTable = $("#user_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/device/deviceClean/queryUser.action",  //异步请求地址
			"aoColumns" : [ 
							{"mDataProp":"userId","mRender": function(data, type, full) { 
			            	   return '<label name="userId">'+ data + '</label>';
			               }},
			               {"mDataProp":"userName","sClass":"center"},
			               {"mDataProp":"realName","mRender": function(data, type, full) { 
			            	   return '<label name="realName">'+ data + '</label>';
			               }},
			               {"mDataProp":"sex","sClass":"center"},
			               {"mDataProp":"tel","sClass":"center"},
			               {"mDataProp":"userStatus","sClass":"center"},
			               {"mDataProp":"createDate","sClass":"center"}
			               ],
           "oLanguage": {
               "sProcessing": "正在加载中......",
               "sLengthMenu": "每页显示 _MENU_ 条记录",
               "sZeroRecords": "没有数据！",
               "sEmptyTable": "表中无数据存在！",
               "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
               "sInfoEmpty": "显示0到0条记录",
               "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
               "zeroRecords": "没有内容",
               "sSearch": "搜索",
               "oPaginate": {
                   "sFirst": "首页",
                   "sPrevious": "上一页",
                   "sNext": "下一页",
                   "sLast": "末页"
               }
           },
			"aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveDataUser,
		});
	} else {
		uTable.fnDraw(); //重新加载数据
	}
}

/*********************/
//加载车辆列表
cTable = null;
function initCarTable() {
	if(cTable == null) {
		cTable = $("#car_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH +"/view/car/carInfo/queryCarInfo.action",  //异步请求地址
			"aoColumns" : [ 
							{"mDataProp":"carId","mRender": function(data, type, full) { 
								   return '<label name="carId">'+ data + '</label>';
							}},
							{"mDataProp":"carNo","mRender": function(data, type, full) { 
								   return '<label name="carNo">'+ data + '</label>';
							}},
			               {"mDataProp":"sysDictBrand.dictDesc","sClass":"center"},	
			               {"mDataProp":"sysDictType.dictDesc","sClass":"center"},
			               {"mDataProp":"createDate","sClass":"center"},
			               {"mDataProp": "carStatus","sClass":"center", "mRender": function (data, type, full) {
			                    var carStatus = '';
			                    if (data == '1') {
			                    	carStatus = '<input title="启用" class="ace ace-switch ace-switch-6" type="checkbox" onclick="changeStatus(this,'+full.carId+')" /><span class="lbl"></span>';
			                    }
			                    else if (data == '0') {
			                    	carStatus = '<input title="禁用" class="ace ace-switch ace-switch-6" type="checkbox" onclick="changeStatus(this,'+full.carId+')" checked /><span class="lbl"></span>';
			                    }
			                    return carStatus;
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
			                   "zeroRecords": "没有内容",
			                   "sSearch": "搜索",
			                   "oPaginate": {
			                       "sFirst": "首页",
			                       "sPrevious": "上一页",
			                       "sNext": "下一页",
			                       "sLast": "末页"
			                   }
			               },
			"aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
			                 {
			                	 sDefaultContent: '',
			                	 aTargets: [ '_all' ]
			                	  },
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveDataCar,
			                                
		});
	} else {
		cTable.fnDraw(); //重新加载数据
	}
}
/************************/

/**
* sSource   查询链接
* aoData    参数
* fnCallback 返回数据填充方法
*/
function retrieveDataCarLine(sSource, aoData, fnCallback) { 
	//商品名称或编号
	var keyword = $("#keyword").val();
	var param = {"keyword":keyword};
    var selectCarLineNodes = $.fn.zTree.getZTreeObj("carLine_tree");
    if(selectCarLineNodes!=null){
    	if(selectCarLineNodes.getSelectedNodes() != ""){
    		var id=selectCarLineNodes.getSelectedNodes()[0].id;       
            var pid=selectCarLineNodes.getSelectedNodes()[0].pid;  
            var level = selectCarLineNodes.getSelectedNodes()[0].level;
            param.id = id;
            param.pid = pid;
            param.level = level;
    	}        
    }
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
/************************/

/**
* sSource   查询链接
* aoData    参数
* fnCallback 返回数据填充方法
*/
function retrieveDataCar(sSource, aoData, fnCallback) { 
	//商品名称或编号
	var keyword = $("#keyword").val();
	var param = {"keyword":keyword};
    var selectCarNodes = $.fn.zTree.getZTreeObj("car_tree");
    if(selectCarNodes!=null){
    	if(selectCarNodes.getSelectedNodes() != ""){
    		var id=selectCarNodes.getSelectedNodes()[0].id;       
            var pid=selectCarNodes.getSelectedNodes()[0].pid;  
            var level = selectCarNodes.getSelectedNodes()[0].level;
            param.id = id;
            param.pid = pid;
            param.level = level;
    	}        
    }
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

/************************/

/**
* sSource   查询链接
* aoData    参数
* fnCallback 返回数据填充方法
*/
function retrieveDataUser(sSource, aoData, fnCallback) { 
	//商品名称或编号
	var keyword = $("#keyword").val();
	var param = {"keyword":keyword};
    var selectCarNodes = $.fn.zTree.getZTreeObj("user_tree");
    if(selectCarNodes!=null){
    	if(selectCarNodes.getSelectedNodes() != ""){
    		var id=selectCarNodes.getSelectedNodes()[0].id;       
            var pid=selectCarNodes.getSelectedNodes()[0].pid;  
            var level = selectCarNodes.getSelectedNodes()[0].level;
            param.id = id;
            param.pid = pid;
            param.level = level;
    	}        
    }
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
//单个选择商品
function choiseDevice(input_goodsName) {
	resetSelectDevice();
	$('#goods_choise_modal').modal('show');
}   

//加载设备数据
function resetSelectDevice(){
	initTable();
}
function choiseUser(input_goodsName) {
	resetSelectUser();
	$('#user_choise_modal').modal('show');
}  
//加载设备数据
function resetSelectUser(){
	initUserTable();
}
function choiseCar(input_goodsName) {
	resetSelectCar();
	$('#car_choise_modal').modal('show');
}
function resetSelectCar(){
	initCarTable();
}
//加载线路DLG组织机构树
function initCarLineTree() {
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: function(){
            	searchCarLine();
            }
        }
    };

    var treeObj = $("#carLine_tree");
    var zNodes;

    $.ajax({
        url: ROOT_PATH + '/view/inventory/warehouse/getMulSubDictDataByPcode.action',
        type:'POST',
        data:{"dictPcode" : "AL_POSITION" ,"dictLevel":"5"},
        dataType:'json',
        success:function(data){
            zNodes = data;
            $.fn.zTree.init($("#carLine_tree"), setting, zNodes);
        }
    });

    treeObj.hover(function () {
        if (!treeObj.hasClass("showIcon")) {
            treeObj.addClass("showIcon");
        }
    }, function() {
        treeObj.removeClass("showIcon");
    });
}
function searchCarLine() {
	if(oTable){
		oTable.fnDraw();
	}
}
//加载车辆DLG组织机构树
function initCarTree() {
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: function(){
            	searchCar();
            }
        }
    };

    var treeObj = $("#car_tree");
    var zNodes;

    $.ajax({
        url: ROOT_PATH + '/view/inventory/warehouse/getMulSubDictDataByPcode.action',
        type:'POST',
        data:{"dictPcode" : "AL_POSITION" ,"dictLevel":"5"},
        dataType:'json',
        success:function(data){
            zNodes = data;
            $.fn.zTree.init($("#car_tree"), setting, zNodes);
        }
    });

    treeObj.hover(function () {
        if (!treeObj.hasClass("showIcon")) {
            treeObj.addClass("showIcon");
        }
    }, function() {
        treeObj.removeClass("showIcon");
    });
}
function searchCar() {
	if(cTable){
		cTable.fnDraw();
	}
}
//加载用户DLG组织机构树
function initUserTree() {
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: function(){
            	searchUser();
            }
        }
    };

    var treeObj = $("#user_tree");
    var zNodes;

    $.ajax({
        url: ROOT_PATH + '/view/inventory/warehouse/getMulSubDictDataByPcode.action',
        type:'POST',
        data:{"dictPcode" : "AL_POSITION" ,"dictLevel":"5"},
        dataType:'json',
        success:function(data){
            zNodes = data;
            $.fn.zTree.init($("#user_tree"), setting, zNodes);
        }
    });

    treeObj.hover(function () {
        if (!treeObj.hasClass("showIcon")) {
            treeObj.addClass("showIcon");
        }
    }, function() {
        treeObj.removeClass("showIcon");
    });
}
function searchUser() {
	if(cTable){
		cTable.fnDraw();
	}
}
