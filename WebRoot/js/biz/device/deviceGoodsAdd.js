jQuery(function($) {
	$("#submenu-menu-inventory-plan").addClass("active");
	$("#menu-inventory").addClass("active");
	$("#menu-inventory").addClass("open");
	$(document).on("dblclick", "#device_list tbody tr", function() {
		var $choiseDevice = getChoiseDeviceInfo($(this));
		getDeviceGridList($choiseDevice.deviceId,$choiseDevice.deviceNo,$choiseDevice.deviceName);
	});
	var devNoStr = $("#deviceNoStr").val();
	setDeviceNo(devNoStr);
	$(document).on("dblclick", "#goods_list tbody tr", function() {
		var Tid = $("#tId").val();
		var $choiseGoods = getChoiseGoodInfo($(this));
		$("#goodsId"+Tid).val($choiseGoods.goodsId);
		$("#goodsName"+Tid).val($choiseGoods.goodsName);
		$("#goodsUnit"+Tid).val($choiseGoods.goodsUnit);
		$("#goods_choise_modal").modal('hide');
		//focusNumbox();
	});
});

//获得选择用户对象
function getChoiseGoodInfo(ele_goodsRow){
	var $choiseGoods = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseGoods.goodsId = $ele_td.find("input[id='goodsId']").eq(0).val();
	//$choiseGoods.goodsUnit = $ele_td.find("label[name='goodsUnit']").eq(0).html();
	$choiseGoods.goodsName = $ele_td.find("label[name='goodsName']").eq(0).html();
	$choiseGoods.goodsUnit = $ele_td.find("input[id='measurementUnit']").eq(0).val();
	return $choiseGoods;
}
//获得选择设备对象
function getChoiseDeviceInfo(ele_goodsRow){
	var $choiseDevice = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseDevice.deviceId = $ele_td.find("input[id='deviceId']").eq(0).val();
	$choiseDevice.deviceNo = $ele_td.find("label[name='deviceNo']").eq(0).html();
	$choiseDevice.deviceName = $ele_td.find("label[name='deviceName']").eq(0).html();
	return $choiseDevice;
}
function setDeviceNo(deviceNo){
	var devNo="";
	for(var i = 0;i < 13 ;i++){
		if(deviceNo != null && deviceNo != ""){
			devNo = "-"+i;
		}else{
			devNo = i;
		}
		$("#lab"+i).text(deviceNo +devNo);
	}
}
function getDeviceGridList(deviceId,deviceNo,deviceName){
	if(deviceId){
		$.ajax({
			type : "POST",
			url : ROOT_PATH + "/view/device/deviceGoods/geZi.action",
			data : { "deviceId" : deviceId}, 
			success : function(data) {
				var json = eval("(" + data + ")");
				var gridList = json[0];
				var goodsList = json[1];
				if(goodsList != null ){
					alert("该设备存在商品,选择其它设备!");
				}else{
					setDeviceNo(deviceNo);
					$("#deviceIdStr").val(deviceId);
					$("#deviceNoStr").val(deviceNo);
					$("#deviceNameStr").val(deviceName);
					$("#device_choise_modal").modal('hide');
					for(var i = 0;i<gridList.length;i++){
						$("#gridId"+i).val(gridList[i].gridId);
						$("#gridNo"+i).val(gridList[i].gridNo);
						$("#gridBar"+i).val(gridList[i].gridBar);
					}
				}
			}
		});
	}
}
function choiseDevice(input_deviceName) {
	initDeviceTable();
	$('#device_choise_modal').modal('show');
}   
oTable = null;
function initDeviceTable() {
	var deviceStatus = 1;
	if(oTable == null) {
		oTable = $("#device_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bInfo": true,
			"bProcessing": false,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/device/deviceInfo/queryDeviceInfo.action?deviceStatus="+deviceStatus,  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"deviceNo","sClass":"center","mRender": function(data, type, full) {
			            	   return '<label name="deviceNo">'+ data + '</label>';
			               }},
			               {"mDataProp":"deviceName","sClass":"center","mRender": function(data, type, full) {
			            	   return '<label name="deviceName">'+ data + '</label><input id="deviceId" type="hidden" value="'+full.deviceId+'"/>';
			               }},
			               {"mDataProp":"deviceAddress","sClass":"center","mRender": function(data, type, full) {
			            	   return '<label name="deviceAddress">'+ data + '</label>';
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
			                 {"bSortable": false, "aTargets": [0, 1, 2]},  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveDeviceData,
		});
	} else {
		oTable.fnDraw(); //重新加载数据
	}
}

/**
* sSource   查询链接
* aoData    参数
* fnCallback 返回数据填充方法
*/
function retrieveDeviceData(sSource, aoData, fnCallback) { 
	//商品名称或编号
	var keyword = $("#keyword").val();
	var param = {
		"keyword":keyword,
		"goodsFilter":"1"
	};

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

gTable = null;
function initGoodsTable() {
	if(gTable == null) {
		gTable = $("#goods_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bInfo": true,
			"bProcessing": false,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/goods/goodsInfo/queryGoodsInfoPage.action",  //异步请求地址
			"aoColumns" : [ 
//			               {"sDefaultContent": "","sClass": "center","bSortable": false,"mRender": function(data, type, full) { 
//			            	   return '<label class="position-relative">'+
//			            	   '<input type="checkbox" class="ace" value="'+full.goodsId+'"/><span class="lbl"></span></label>';
//			               }},
			               {"mDataProp":"goodsName","sClass":"center","mRender": function(data, type, full) { 
			            	   return '<label name="goodsName">'+ data + '</label><input id="goodsId" type="hidden" value="'+full.goodsId+'"/>';
			               }},
			               {"mDataProp":"unitName","sClass":"center","mRender": function(data, type, full) { 
			            	   return '<label name="goodsUnit">'+ data + '</label><input id="measurementUnit" type="hidden" value="'+full.measurementUnit+'"/>';
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
			                 {"bSortable": false, "aTargets": [0, 1]},  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveGoodsData,
		});
	} else {
		gTable.fnDraw(); //重新加载数据
	}
}

/**
* sSource   查询链接
* aoData    参数
* fnCallback 返回数据填充方法
*/
function retrieveGoodsData(sSource, aoData, fnCallback) { 
	//商品名称或编号
	var keyword = $("#keyword").val();
	var param = {
		"keyword":keyword,
		"orgFilter":"1"
	};

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
function btn(obj){
	var deviceId = $("#deviceIdStr").val();
	var deviceGoodsId1 = $("#deviceGoodsId1").val();
	$("#tId").val(obj.id);
	if(deviceId !=null && deviceId != ""){
		initGoodsTable();
		$("#choise_goods_action").show();
		$('#goods_choise_modal').modal('show');
	}else{
		alert("请先选择设备!");
	}
}
function delBtn(obj){
	var deviceGoodsId = $("#deviceGoodsId"+obj.id).val();
	$("#goodsName"+obj.id).val("");
	$("#deviceGoodsId"+obj.id).val("");
	$("#goodsId"+obj.id).val("");
	$("#goodsUnit"+obj.id).val("");
	$("#tId").val(obj.id);
//    $.ajax({
//        type: 'post',
//        url:  ROOT_PATH + "/view/device/deviceGoods/delById.action",
//        dataType: "json",
//        data: {"deviceGoodsId":deviceGoodsId},
//        success: function (msg) {
//        		alert("删除成功!");
//        		$("#goodsName"+obj.id).val("");
//        		$("#deviceGoodsId"+obj.id).val("");
//        		$("#goodsId"+obj.id).val("");
//        		$("#goodsUnit"+obj.id).val("");
//        		$("#gridId"+obj.id).val("");
//        		$("#gridNo"+obj.id).val("");
//        		$("#gridBar"+obj.id).val("");
//        }
//    });
}