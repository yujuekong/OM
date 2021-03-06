jQuery(function($) {
	$("#submenu-menu-advert-user").addClass("active");
	$("#menu-advert").addClass("active");
	$("#menu-advert").addClass("open");
	//initTable();
	initUserTable();
	
	//单个追加用户到申购单
	$(document).on("dblclick", "#user_list tbody tr", function() {
		var $choiseUser = getChoiseUserInfo($(this));
		var a = $("#userIdStr").val($choiseUser.userId);
		$("#userNameStr").val($choiseUser.realName);
		$("#user_choise_modal").modal('hide');
		//focusNumbox();
	});

	//单个追加商品到申购单
	$(document).on("dblclick", "#deviceInfo_list tbody tr", function() {
		var $choiseDevice = getChoiseDeviceInfo($(this));
		var a = $("#deviceIdStr").val($choiseDevice.deviceId);
		$("#deviceNameStr").val($choiseDevice.deviceName);
		$("#devices_choise_modal").modal('hide');
		//focusNumbox();
	});
	
});



//获得选择商品对象
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
			"sAjaxSource": ROOT_PATH + "/view/device/deviceInfo/queryDeviceInfo.action",  //异步请求地址
			"aoColumns" : [ 
							{"mDataProp":"deviceId","mRender": function(data, type, full) { 
			            	   return '<label name="deviceId">'+ data + '</label>';
			               }},
			               {"mDataProp":"deviceNo","sClass":"center"},
			               {"mDataProp":"deviceName","mRender": function(data, type, full) { 
			            	   return '<label name="deviceName">'+ data + '</label>';
			               }},
			               {"mDataProp":"deviceTypeId","sClass":"center"},
			               {"mDataProp":"deviceEara","sClass":"center"},
			               {"mDataProp":"deviceWeight","sClass":"center"},
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveDataDevice,
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
			"sAjaxSource": ROOT_PATH + "/view/advert/advertInfo/queryAdvertInfoPage.action",  //异步请求地址
			"aoColumns" : [ 
							{"mDataProp":"advertInfoId","mRender": function(data, type, full) { 
			            	   return '<label name="userId">'+ data + '</label>';
			               }},
			               {"mDataProp":"advertTitle","mRender": function(data, type, full) { 
			            	   return '<label name="realName">'+ data + '</label>';
			               }},
			               {"mDataProp":"auName","sClass":"center"},
			               {"mDataProp":"auLinktel","sClass":"center"},
			               {"mDataProp":"startDate","sClass":"center"},
			               {"mDataProp":"endDate","sClass":"center"},
			               {"mDataProp":"createDate","sClass":"center"},
			               {"mDataProp":"advertStatus","mRender": function(data, type, full) { 
			            	   var goodsStatus = '';
			            	   if(data == '0'){
			            		   goodsStatus = '<span class="label label-sm label-success">正常</span>';
			            	   }
			            	   else if(data == '1') {
			            		   goodsStatus ='<span class="label label-sm label-error">禁用</span>';
			            	   } 
//			            	   '<span style="cursor:pointer;" onclick="changeAdvertStatus(\'' + full.advertInfoId + '\')" class="label label-sm label-error">禁用</span>'
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6, 7]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveDataUser,
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
function retrieveDataDevice(sSource, aoData, fnCallback) { 
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

/**
* sSource   查询链接
* aoData    参数
* fnCallback 返回数据填充方法
*/
function retrieveDataUser(sSource, aoData, fnCallback) { 
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

//单个选择设备
function choiseDevice(input_deviceName){
	resetSelectDevice();
	$("#devices_choise_modal").modal("show");
}

//加载设备数据
function resetSelectDevice(){
	initOrgTree();
	initTable();
}

//单个选择广告
function choiseAdvertInfo(input_goodsName) {
	resetSelectUser();
	$('#user_choise_modal').modal('show');
}  
//加载用户数据
function resetSelectUser(){
	initGoodsTypeTree();
	initUserTable();
}

//搜索商品
function searchDevice() {
	if(oTable){
		oTable.fnDraw();
	}
} 
//初始化机构树
function initOrgTree() {
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: function(){
            	searchDevice();
            }
        }
    };

    var treeObj = $("#device_tree");
    var zNodes;

    $.ajax({
        url: ROOT_PATH + '/view/inventory/warehouse/getMulSubDictDataByPcode.action',
        type:'POST',
        data:{"dictPcode" : "AL_POSITION" ,"dictLevel":"5"},
        dataType:'json',
        success:function(data){
            zNodes = data;
            $.fn.zTree.init($("#device_tree"), setting, zNodes);
        }
    });
    
    treeObj.hover(function () {
        if (!treeObj.hasClass("showIcon")) {
            treeObj.addClass("showIcon");
        }
    }, function() {
        treeObj.removeClass("showIcon");
    });
    initTable();
}

function initGoodsTypeTree(){
	var setting = {
		data: {
			simpleData: {
				enable: true
			}
		}
	};	
	var zNodes =[
		{ id:1, pId:0, name:"慧惠宝", open:true},
		{ id:11, pId:1, name:"东北"},
		{ id:12, pId:1, name:"华北"},
		{ id:13, pId:1, name:"华东"},
		{ id:14, pId:1, name:"华中"},
		{ id:15, pId:1, name:"华南"},
		{ id:16, pId:1, name:"西北"},
		{ id:17, pId:1, name:"西南"},
		
		{ id:111, pId:11, name:"辽宁省"},
		{ id:1111, pId:111, name:"沈阳服务站"},
		
		{ id:141, pId:14, name:"湖北"},
		{ id:1411, pId:141, name:"武汉服务站"},
		{ id:1412, pId:141, name:"宜昌服务站"},
		
		{ id:151, pId:15, name:"广东"},
		{ id:1511, pId:151, name:"深圳服务站"}
	];

	$(document).ready(function(){
		$.fn.zTree.init($("#device_tree"), setting, zNodes);
	});
}
