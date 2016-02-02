jQuery(function($) {
	$("#submenu-menu-advert-user").addClass("active");
	$("#menu-advert").addClass("active");
	$("#menu-advert").addClass("open");
	initOrgTree();
	initUserTree();
	//要求完成时间
	/*$("#dp_expEndTime").datetimepicker({
	    language: 'zh-CN',
		format: 'yyyy-mm-dd',
		weekStart: 1,
	    todayBtn:  1,
		autoclose: true,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	});*/
	
	$("#dp_expEndTime").datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
	    language: 'zh-CN',
		weekStart: 1,
	    todayBtn:  1,
		autoclose: true,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0
	});
	
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

});

//获得选择设备对象
function getChoiseDeviceInfo(ele_goodsRow){
	var $choiseDevice = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseDevice.deviceId = $ele_td.find("input[type=hidden]").eq(0).val();
	//$choiseDevice.deviceId = $ele_td.find("label[name='deviceId']").eq(0).html();
	$choiseDevice.deviceName = $ele_td.find("label[name='deviceName']").eq(0).html();
	return $choiseDevice
}
//获得选择用户对象
function getChoiseUserInfo(ele_goodsRow){
	var $choiseUser = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseUser.userId = $ele_td.find("input[type=hidden]").eq(0).val();
	//$choiseUser.userId = $ele_td.find("label[name='userId']").eq(0).html();
	$choiseUser.realName = $ele_td.find("label[name='realName']").eq(0).html();
	return $choiseUser
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
	var deviceStatus = 1;
	if(oTable == null) {
		oTable = $("#deviceInfo_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bInfo": true,
			"bProcessing": false,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/device/deviceInfo/queryDeviceInfo.action?deviceStatus="+deviceStatus,  //异步请求地址
			"aoColumns" : [
				{"mDataProp": "deviceNo", "sClass": "center"},
				{
					"mDataProp": "deviceName", "sClass": "center", "mRender": function (data, type, full) {
					return '<label name="deviceName">' + data + '</label><input type="hidden" value="' + full.deviceId + '"/>';
				}
				},
				{"mDataProp": "deviceAddress", "sClass": "center"}
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
			                 {"bSortable": false, "aTargets": [0, 1, 2]},  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
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
			"sAjaxSource": ROOT_PATH + "/view/sys/sysUser/querySysUserPage.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"userName","sClass":"center"},
			               {"mDataProp":"realName","sClass":"center","mRender": function(data, type, full) { 
			            	   return '<label name="realName">'+ data + '</label><input type="hidden" value="'+full.userId+'"/>';
			               }},
			               {"mDataProp":"tel","sClass":"center"},
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
			                 {"bSortable": false, "aTargets": [0, 1, 2]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
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
function retrieveData(sSource, aoData, fnCallback) { 
	//商品名称或编号
	var keyword = $("#keyword").val();
	var param = {"keyword":keyword};
	
    var selectNodes = $.fn.zTree.getZTreeObj("device_tree");
    if(selectNodes!=null){
    	if(selectNodes.getSelectedNodes() != ""){
    		var id=selectNodes.getSelectedNodes()[0].id;       
            var pid=selectNodes.getSelectedNodes()[0].pid;  
            var level = selectNodes.getSelectedNodes()[0].level;
            var deviceStatus = "1";
            param.id = id;
            param.pid = pid;
            param.level = level;
            param.deviceStatus = deviceStatus;
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

/**
* sSource   查询链接
* aoData    参数
* fnCallback 返回数据填充方法
*/
function retrieveDataUser(sSource, aoData, fnCallback) { 
	//商品名称或编号
	var keyword = "";
	var param = {"keyword":keyword};
	var regionId = $("#regionStr").val();
	var proviceId = $("#provinceStr").val();
	var orgId = $("#orgStr").val();
	param.orgId = orgId;
	param.regionId= regionId;
	param.proviceId = proviceId;
    var selectNodes = $.fn.zTree.getZTreeObj("user_tree");
    if(selectNodes!=null){
    	if(selectNodes.getSelectedNodes() != ""){
    		var id=selectNodes.getSelectedNodes()[0].id;       
            var pid=selectNodes.getSelectedNodes()[0].pid;  
            var level = selectNodes.getSelectedNodes()[0].level;
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

//搜索商品
function searchGoods() {
	if(oTable){
		oTable.fnDraw();
	}
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
//加载组织机构树
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
}
function searchDevice() {
	if(oTable){
		oTable.fnDraw();
	}
}

//初始化用户机构树
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
    initUserTable();
}
function searchUser() {
	if(uTable){
		uTable.fnDraw();
	}
}