jQuery(function($) {
	$("#submenu-menu-yy-plan").addClass("active");
	$("#menu-yy").addClass("active");
	$("#menu-yy").addClass("open");
	//日历插件
	$("#startTime").datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
	    language: 'zh-CN',
		weekStart: 1,
	    todayBtn:  1,
		autoclose: true,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0
	});
	$("#endTime").datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
	    language: 'zh-CN',
		weekStart: 1,
	    todayBtn:  1,
		autoclose: true,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0
	});

	//单个追加出库单到text
	$(document).on("dblclick", "#order_list tbody tr", function() {
		var $choiseOrder = getChoiseOrderInfo($(this));
		var a = $("#orderIdStr").val($choiseOrder.orderId);
		$("#orderNameStr").val($choiseOrder.orderNo);
		$("#order_choise_modal").modal('hide');
		//focusNumbox();
	});
	//单个追加线路到text
	$(document).on("dblclick", "#carLine_list tbody tr", function() {
		var $choiseCarLine = getChoiseDeviceInfo($(this));
		var carLine = $("#lineIdStr").val($choiseCarLine.carLineId);
		$("#lineNameStr").val($choiseCarLine.lineName);
		$("#carLine_choise_modal").modal('hide');
		//getDevice(carLine);
	});
	//单个追加车辆到text
	$(document).on("dblclick", "#car_list tbody tr", function() {
		var $choiseCar = getCarInfo($(this));
		var a = $("#carStr").val($choiseCar.carId);
		$("#carNameStr").val($choiseCar.carNo);
		$("#car_choise_modal").modal('hide');
		//focusNumbox();
	});
	//单个追加司机到text
	$(document).on("dblclick", "#driver_list tbody tr", function() {
		var $choiseDriver = getDerverInfo($(this));
		var a = $("#driverStr").val($choiseDriver.driverId);
		$("#driverNameStr").val($choiseDriver.driverName);
		$("#driver_choise_modal").modal('hide');
		//focusNumbox();
	});
	
	//单个追加商品到text
	$(document).on("dblclick", "#out_dtl_table tbody tr", function() {
		var $choiseGoods = getGoodsInfo($(this));
		var count = $("#tb tbody tr").length;
		var sid = $("#sId").val();
	//	var a = $("#goodsIdStr").val($choiseGoods.goodsId);
		$("#"+sid+"").val($choiseGoods.goodsName);
		$("#goodsId"+sid+"").val($choiseGoods.goodsId);
		$("#out_dtl_modal").modal('hide');
	});
	
	//批量选择时，单个checkbox选中改变[添加按钮]背景颜色
	$(document).on("click", "#device_list td input:checkbox", function() {
		var checkedCount = $("#device_list input:checkbox:checked").length;
		if(checkedCount > 0) {
			$("#choise_device").removeClass("disabled");
		} else {
			$("#choise_device").addClass("disabled");
		}
	});
	
	//站点选择列表checkbox全选，全不选
	$(document).on('click','th input:checkbox',function() {
		var that = this;
		if(that.checked) {
			$("#choise_device").removeClass("disabled");
		} else {
			$("#choise_device").addClass("disabled");
		}
		$(this).closest('table').find('tr > td:first-child input:checkbox').each(function() {
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
		});
	});
	
});

//获得选择出库单对象
function getChoiseOrderInfo(ele_goodsRow){
	var $choiseOrder = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseOrder.orderId = $ele_td.find("label[name='orderId']").eq(0).html();
	$choiseOrder.orderNo = $ele_td.find("label[name='orderNo']").eq(0).html();
	return $choiseOrder;
}
//获得选择线路对象
function getChoiseDeviceInfo(ele_goodsRow){
	var $choiseCarLine = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseCarLine.carLineId = $ele_td.find("label[name='carLineId']").eq(0).html();
	$choiseCarLine.lineName = $ele_td.find("label[name='lineName']").eq(0).html();
	return $choiseCarLine;
}


//获得选择车辆对象
function getCarInfo(ele_goodsRow){
	var $choiseCar = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseCar.carId = $ele_td.find("label[name='carId']").eq(0).html();
	$choiseCar.carNo = $ele_td.find("label[name='carNo']").eq(0).html();
	return $choiseCar;
}

//获得选择司机对象
function getDerverInfo(ele_goodsRow){
	var $choiseDriver = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseDriver.driverId = $ele_td.find("label[name='driverId']").eq(0).html();
	$choiseDriver.driverName = $ele_td.find("label[name='driverName']").eq(0).html();
	return $choiseDriver;
}

//获得选择商品对象
function getGoodsInfo(ele_goodsRow){
	var $choiseGoodS = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseGoodS.goodsId = $ele_td.find("label[name='goodsId']").eq(0).html();
	$choiseGoodS.goodsName = $ele_td.find("label[name='goodsName']").eq(0).html();
	return $choiseGoodS;
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

//选择配送线路
oTable = null;
function initTable() {
	if(oTable == null) {
		oTable = $("#carLine_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH +"/view/car/carLine/queryCarLine.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"carLineId","mRender": function(data, type, full) { 
			            	   return '<label name="carLineId" >'+ data + '</label>';
			               }},
			               {"mDataProp":"lineName","mRender": function(data, type, full) { 
			            	   return '<label name="lineName">'+ data + '</label>';
			               }},
			               {"mDataProp":"lineNo","sClass":"center"},
			               {"mDataProp":"lineLength","sClass":"center"},
			               {"mDataProp":"startDate","sClass":"center"},
			               {"mDataProp":"endDate","sClass":"center"},
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveData,
		});
	} else {
		oTable.fnDraw(); //重新加载数据
	}
}
//选择配送出库单
uTable = null;
function initUserTable() {
	if(uTable == null) {
		uTable = $("#order_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/inventory/deliveryOrder/queryDeliveryOrderPage.action",  //异步请求地址
			"aoColumns" : [ 
							{"mDataProp":"deliveryOrderId","mRender": function(data, type, full) { 
			            	   return '<label name="orderId">'+ data + '</label>';
			               }},
			               {"mDataProp":"warehouseId","sClass":"center"},
			               {"mDataProp":"deliveryNo","mRender": function(data, type, full) { 
			            	   return '<label name="orderNo">'+ data + '</label>';
			               }},
			               {"mDataProp":"bizType","sClass":"center"},
			               {"mDataProp":"remark","sClass":"center"},
			               {"mDataProp":"deliveryDate","sClass":"center"},
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
			"fnServerData": retrieveData,
		});
	} else {
		uTable.fnDraw(); //重新加载数据
	}
}
//选择配送车辆 
cTable = null;
function initCarTable() {
	if(cTable == null) {
		cTable = $("#car_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/car/carInfo/queryCarInfo.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"org.dictName","sClass":"center"},
			               {"mDataProp":"carId","mRender": function(data, type, full) { 
			            	   return '<label name="carId">'+ data + '</label>';
			               }},
			               {"mDataProp":"carNo","mRender": function(data, type, full) { 
			            	   return '<label name="carNo">'+ data + '</label>';
			               }},
			               {"mDataProp":"sysDictBrand.dictDesc","sClass":"center"},
			               {"mDataProp":"sysDictType.dictDesc","sClass":"center"},
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveData,
		});
	} else {
		cTable.fnDraw(); //重新加载数据
	}
}

dTable = null;
function initDriverTable() {
	if(dTable == null) {
		dTable = $("#driver_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/device/deviceClean/queryUser.action",  //异步请求地址
			"aoColumns" : [ 
							{"mDataProp":"userId","sClass":"center","mRender": function(data, type, full) { 
			            	   return '<label name="driverId">'+ data + '</label>';
			               }},
			               {"mDataProp":"userName","sClass":"center"},
			               {"mDataProp":"realName","sClass":"center","mRender": function(data, type, full) { 
			            	   return '<label name="driverName">'+ data + '</label>';
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
			"fnServerData": retrieveData,
		});
	} else {
		dTable.fnDraw(); //重新加载数据
	}
}
/************************/

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

//单个选择车辆线路
function choiseDevice(input_goodsName) {
	resetSelectDevice();
	$('#carLine_choise_modal').modal('show');
}   
//单个选择车辆信息
function choiseCar(input_goodsName) {
	resetSelectCar();
	$('#car_choise_modal').modal('show');
}  
//加载车辆线路数据
function resetSelectDevice(){
	initTable();
}
//加载车辆信息数据
function resetSelectCar(){
	initCarTable();
}
//单个选择司机
function choiseDriver(input_goodsName) {
	resetSelectDriver();
	$('#driver_choise_modal').modal('show');
}  
//加载司机数据
function resetSelectDriver(){
	initDriverTable();
}
//单个选择出库单
function choiseUser(input_goodsName) {
	resetSelectUser();
	$('#order_choise_modal').modal('show');
}  
//加载出库单数据
function resetSelectUser(){
	initUserTable();
}
//批量选择设备
function batchChoiseDevice() {
	var orderId = $("#orderIdStr").val();
	if(orderId != "" ){
		resetSelectDeviceForm();
		$("#choise_device_action").show();
		$('#device_choise_modal').modal('show');
	}else{
		alert("请选择配送单号");
		return ;
	}

}
//重置设备选择窗口
function resetSelectDeviceForm() {
	initDeviceTable();
	$("#device_list input:checkbox").removeAttr("checked");
}
inTable = null;
//初始化站点列表
function initDeviceTable() {
	if(inTable == null) {
		inTable = $("#device_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH +"/view/device/deviceInfo/queryDeviceInfo.action", //异步请求地址 ：查询站点信息
			"aoColumns" : [ 
			               {"sDefaultContent": "","sClass": "center","bSortable": false,"mRender": function(data, type, full) { 
			            	   return '<label class="position-relative">'+
			            	   '<input type="checkbox" class="ace" value="'+full.deviceId+'"/><span class="lbl"></span></label>';
			               }},
			               {"mDataProp":"deviceNo","mRender": function(data, type, full) { 
			            	   return '<label name="deviceNo">'+ data + '</label><input name="deviceId" type="hidden" class="ace" value="'+full.deviceId+'"/>';
			               }},
			               {"mDataProp":"deviceName","mRender": function(data, type, full) { 
			            	   return '<label name="deviceName">'+ data + '</label>';
			               }},
			               {"mDataProp":"mDistrict.districtName","mRender": function(data, type, full) { 
			            	   return '<label name="districtName">'+ data + '</label><input name="districtId" type="hidden" value="'+full.districtId+'"/>';
			               }},
			               {"mDataProp":"deviceAddress","mRender": function(data, type, full) { 
			            	   return '<label name="deviceAddress">'+ data + '</label>';
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4 ]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveDataDevice,
			                                
		});
	} else {
		inTable.fnDraw(); //重新加载数据
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
	var orgId = $("#userId").val();
	var deviceStatus = 0;
	var level = 4;
	param.level = level;
	param.id = orgId;
	param.deviceStatus = deviceStatus;
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
function searchDevice() {
	if(inTable){
		inTable.fnDraw();
	}
}
//批量追加站点到表单
function batchAppendDevice() {
	var $purchase_device_list = $("#purchase_device_list");
	var rowCount = $purchase_device_list.find("tr").length; //得到行数
	//遍历查询站点列表，添加选择的站点到表单显示页面
	$("#device_list td input:checkbox:checked").each(function(i) {
		var nodeId = $(this).val();
		if(!existPurchaseDevice(nodeId)) {
			var $choise_row = $(this).closest('tr');
			rowCount = rowCount + 1;
			var append_row_no = rowCount ;
			var append_row = createPurchaseGoodsRow(append_row_no);
			var $choiseDevice = getChoiseDevice($choise_row);
			$purchase_device_list.append(append_row);
			var append_no = append_row_no;
			fillGoodsInfo(append_no,$choiseDevice);
		}else{
			return;
		}
	});
	
	$("#device_list input:checkbox").removeAttr("checked");
	$("#choise_device").addClass("disabled");
	$("#device_choise_modal").modal('hide');
	alert("添加成功！");
	focusNumbox();
}
//是否已存在已选中的设备
function existPurchaseDevice(nodeId) {
	var flag = false;
	$("#purchase_device_list").find("tr").each(function(i){
		var lNodeId = $(this).find("td input[id ='lNodeId']").eq(0).val();
		if (nodeId == lNodeId) {
			flag = true;
		}
	});
	
	return flag;
}
//批量添加追加行数
function createPurchaseGoodsRow(append_row_no) {
	return '<tr>'
	+'<td class="center" >' +( append_row_no ) + '</td>'
	+'<td class="center" ><input type="hidden" id="lNodeId" name="goodsList[' + (append_row_no - 1) + '].deviceId"  /><input type="text" class="center" /></td>'
	+'<td class="center" ><input type="hidden" id="nodeId" name="goodsList[' + (append_row_no - 1) + '].nodeId"  /><input type="text" class="center" /></td>'
	+'<td class="center" ></td>'
	+'<td class="center" ><input type="text" id="' + (append_row_no - 1) + '"   onclick="choiseGoods(this)"/>'
	+'<input type="hidden" id="goodsId' + (append_row_no - 1) + '" name="goodsList[' + (append_row_no - 1) + '].goodsId"  />'	
	+'</td>'
	+'<td class="center" ><input type="text" id="lgoodsNumber" name="goodsList[' + (append_row_no - 1) + '].goodsNumber" /></td>'
    +'<td class="center" ><a href="javascript:void(0)" onclick="removeGoodsItem(this)">移除</a></td>'
	+'</tr>';
}
//获得选择设备各项值
function getChoiseDevice(ele_goodsRow){
	var $choiseDevice = {};
	var $ele_td = ele_goodsRow.find("td");
	$choiseDevice.deviceId = $ele_td.find("input[name='deviceId']").eq(0).val();
	$choiseDevice.deviceNo = $ele_td.find("label[name='deviceNo']").eq(0).html();
	$choiseDevice.deviceName = $ele_td.find("label[name='deviceName']").eq(0).html();
	$choiseDevice.districtId = $ele_td.find("input[name='districtId']").eq(0).val();
	$choiseDevice.deviceAddress = $ele_td.find("label[name='deviceAddress']").eq(0).html();
	$choiseDevice.districtName = $ele_td.find("label[name='districtName']").eq(0).html();
	$choiseDevice.lineNodeId = "";
	$choiseDevice.nodeNo = "";
	/*****************************************/
	if($choiseDevice.districtId != null){
		var districtId = $choiseDevice.districtId;
		$.ajax({
			type : "POST",
			async:false,
			url : ROOT_PATH + "/view/device/deviceInfo/queryDistrict.action",
			data : { "districtId" : districtId}, 
			success : function(data) {
				if(!data) {
					alert("系统错误，请联系管理员!");
				}
				ajaxobj=eval("("+data+")");
				$choiseDevice.lineNodeId = ajaxobj.lineNodeId;
				$choiseDevice.nodeNo = ajaxobj.nodeNo;
			}
		});
	} 
	/****************************************/
	
	return $choiseDevice;
}
//填充设备信息
function fillGoodsInfo(purchaseGoodsRowNo,$choiseDevice) {
	var $ele_tr = $("#purchase_device_list").find("tr").eq(purchaseGoodsRowNo - 1);
	$ele_tr.find("td").eq(1).find("input[type=hidden]").val($choiseDevice.deviceId);
	$ele_tr.find("td").eq(1).find("input[type=text]").val($choiseDevice.deviceName);
	$ele_tr.find("td").eq(2).find("input[type=hidden]").val($choiseDevice.lineNodeId);
	$ele_tr.find("td").eq(2).find("input[type=text]").val($choiseDevice.nodeNo);
	$ele_tr.find("td").eq(3).html($choiseDevice.districtName);
	
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

/*
 * 选择出库商品
 */
function choiseGoods(e){
    resetDeliveryOrder(e.id);
    $('#out_dtl_modal').modal('show');
}
//加载供应商数据
function resetDeliveryOrder(id) {
    initDeliveryOrderTable(id);
}
//初始化站点列表
var gTable = null;
function initDeliveryOrderTable(id) {
	$("#sId").val(id);
    if (gTable == null) {
        gTable = $("#out_dtl_table").dataTable({
            "bDestory": true,
            "bAutoWidth": false,
            "bLengthChange": true,
            "bProcessing": true,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/inventory/deliveryDetail/queryDeliveryDetailPage.action?sId="+id, //异步请求地址 ：查询站点信息
            "bStateSave": true,
            "aoColumns": [
				{"mDataProp": "goodsId","mRender": function(data, type, full) { 
					return '<label name="goodsId">'+ data + '</label>';
				}},
				{"mDataProp": "storageDeliveryOrder.deliveryNo"},
                {"mDataProp": "goodsInfo.goodsName","mRender": function(data, type, full) { 
	            	   return '<label name="goodsName">'+ data + '</label>';
	               }},
                {"mDataProp": "deviceInfo.deviceName"},
                {"mDataProp": "deliveryCount"},
                {"mDataProp": "remark"}
            ],
            "aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
                {"bSortable": false, "aTargets": [0, 1, 2, 3]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
            ],
            "fnServerData": retrieveDataNote,

        });
    } else {
        gTable.fnDraw(); //重新加载数据
    }
}
function retrieveDataNote(sSource, aoData, fnCallback) { 
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

/****************************************************/
function getDevice(carLine){
	var carLineId =carLine.val();
	if(carLineId != null){
		$.ajax({
			type : "POST",
			async:false,
			url : ROOT_PATH + "/view/region/dispatch/queryDeviceByCarLine.action",
			data : { "carLineId" : carLineId}, 
			success : function(data) {
				if(!data) {
					alert("系统错误，请联系管理员!");
				}
				ajaxobj=eval("("+data+")");
				var $purchase_device_list = $("#purchase_device_list");
				var rowCount = ajaxobj.length;
				var $choiseDevice ={} ;
				if(rowCount > 0){
					for(var i = 0;i<rowCount;i++){
						var append_row = createPurchaseGoodsRow(i+1);
						$purchase_device_list.append(append_row);
						
						$choiseDevice.deviceId = ajaxobj[i].deviceId;
//						$choiseDevice.deviceName = ajaxobj[i].deviceName;
//						$choiseDevice.lineNodeId = ajaxobj[i].lineNodeId;
//						$choiseDevice.nodeNo = ajaxobj[i].nodeNo;
//						$choiseDevice.nodePositionId = ajaxobj[i].nodePositionId;
//						$choiseDevice.nodePositionName = ajaxobj[i].nodePositionName;
						//alert($choiseDevice.deviceId);
						fillGoodsInfo(append_row,$choiseDevice);
						$choiseDevice = {};					}
				}
			}
		});
	} 
}

/****************************************************/
