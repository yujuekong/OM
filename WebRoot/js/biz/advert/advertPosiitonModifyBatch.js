jQuery(function($) {
	$("#submenu-menu-advert-user").addClass("active");
	$("#menu-advert").addClass("active");
	$("#menu-advert").addClass("open");
	//	initTableBatch();
	
	//站点选择列表checkbox全选，全不选
	$(document).on('click','th input:checkbox',function() {
		var that = this;
		if(that.checked) {
			$("#choise_submit").removeClass("disabled");
		} else {
			$("#choise_submit").addClass("disabled");
		}
		$(this).closest('table').find('tr > td:first-child input:checkbox').each(function() {
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
		});
	});
	
	//批量选择时，单个checkbox选中改变背景颜色
    $(document).on("click", "#deviceInfo_list_batch td input:checkbox", function () {
        if ($(this).prop("checked")) {
            $(this).closest("tr").addClass("row-click");
            $(this).closest("tr").find("td").addClass("row-click");
        } else {
            $(this).closest("tr").removeClass("row-click");
            $(this).closest("tr").find("td").removeClass("row-click");
        }
        var checkedCount = $("#deviceInfo_list_batch input:checkbox:checked").length;
        if (checkedCount > 0) {
            $("#choise_submit").removeClass("disabled");
        } else {
            $("#choise_submit").addClass("disabled");
        }
    });
	
});

//单个追加商品到申购单
$(document).on("dblclick", "#deviceInfo_list_batch tbody tr", function() {
	var choiseType = $("#choise_type").val();
	if(choiseType == '0') {
	var $choiseDevice = getChoiseDeviceInfo($(this));
	var purchaseGoodsRowNo = $("#purchase_goods_rowno").val();
	fillGoodsInfo(purchaseGoodsRowNo, $choiseDevice);
	$("#devices_choise_modal_batch").modal('hide');
	//focusNumbox();
	}
});

function getChoiseDeviceInfo(ele_goodsRow){
	var $choiseDevice= {};
	var $ele_td = ele_goodsRow.find("td");
	$choiseDevice.deviceId = $ele_td.find("input[type=hidden]").eq(0).val();
	$choiseDevice.deviceName = $ele_td.find("label[name='deviceName']").eq(0).html();
	$choiseDevice.deviceNo = $ele_td.find("label[name='deviceNo']").eq(0).html();
	$choiseDevice.deviceAddress = $ele_td.find("label[name='deviceAddress']").eq(0).html();
	return $choiseDevice;
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
function initTableBatch() {
	if(oTable == null) {
		oTable = $("#deviceInfo_list_batch").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/device/deviceInfo/queryDeviceInfo.action",  //异步请求地址
			"aoColumns" : [ 
							{"sDefaultContent": "","sClass": "center","bSortable": false,"mRender": function(data, type, full) { 
								   return '<label class="position-relative">'+
								   '<input type="checkbox" class="ace" value="'+full.deviceId+'"/><span class="lbl"></span></label>';
							}},
			               {"mDataProp":"deviceNo","sClass":"center","mRender": function(data,type,full){ 
			            	   return '<label name="deviceNo">' + data + '</label><input type="hidden" value="'+full.deviceId+'"/>';
			               		}},
			               {"mDataProp":"deviceName","sClass":"center","mRender": function(data, type, full) { 
			            	   return '<label name="deviceName">'+ data + '</label>';
			               }},
			               {"mDataProp":"deviceAddress","sClass":"center","mRender": function(data,type,full){
			            	   return '<label name="deviceAddress">' + data + '</label>';
			               }},
			               {"mDataProp":"deviceStatus","sClass":"center","mRender": function(data, type, full) { 
			            	   var deviceStatus = '';
			                    if (data == '1') {
			                    	deviceStatus ='<span class="label label-sm label-success">运行中</span>';
			                    }
			                    else if (data == '0' || data == '2' || data == '3') {
			                    	deviceStatus ='<span class="label label-sm label-error">停用</span>';
			                    }
			                    return deviceStatus;
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
			"fnServerData": retrieveData,
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
function retrieveData(sSource, aoData, fnCallback) { 
	//商品名称或编号
	var keyword = $("#keyword").val();
	var param = {"keyword":keyword};
//	var orgId = $("#orgId").val();
//    var proviceId = $("#proviceId").val();
//    var regionId = $("#regionId").val();
//    param.orgId = orgId;
//    param.proviceId = proviceId;
//    param.regionId = regionId;
	var selectNodes = $.fn.zTree.getZTreeObj("device_tree_batch");
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
        }    
    });    
}

//搜索商品
function searchGoods() {
	if(oTable){
		oTable.fnDraw();
	}
}

//移除选择的设备信息
function removeDevicesItem(tdObj) {
	var itemCount = $("#purchase_goods_list").find("tr").length;
	var $ele_tr = $(tdObj).parents("tr:eq(0)");
	if(itemCount == 1) {
		$ele_tr.find("td").eq(1).html('');
		$ele_tr.find("td").eq(2).html('');
		$ele_tr.find("td").eq(3).html('');
		$ele_tr.find("td").eq(4).html('');
	} else {
		$(tdObj).parents("tr").remove();
	}
	$("#purchase_goods_list").find("tr").each(function(i) {
		$(this).find("td").eq(0).html(i+1);
	});
	
}

//是否已存在 已选中的站点
function existPurchaseGoods(goodsId) {
	var flag = false;
	$("#purchase_goods_list").find("tr").each(function(i){
		var purchase_goodsId = $(this).find("td input[id='deviceId']").eq(0).val();
		if (goodsId == purchase_goodsId) {
			flag = true;
		}
	});
	return flag;
}

//批量追加站点到表单
function batchAppendDevices() {
	var $purchase_goods_list = $("#purchase_goods_list");
	var $pgl_fr = $purchase_goods_list.find("tr").eq(0);  //找到第一行
	var rowCount = $purchase_goods_list.find("tr").length; //得到行数
	//遍历查询站点列表，添加选择的站点到表单显示页面
	$("#deviceInfo_list_batch td input:checkbox:checked").each(function(i) {
		var goodsId = $(this).val();
		if(!existPurchaseGoods(goodsId)) {
			var $choise_row = $(this).closest('tr');
			rowCount = rowCount + 1;
			var append_row_no = rowCount;
			var append_row = createPurchaseGoodsRow(append_row_no);
			
			var $choiseGoods = getChoiseGoods($choise_row);
			$purchase_goods_list.append(append_row);
			fillGoodsInfo(append_row_no,$choiseGoods);
		}
	});
	$("#deviceInfo_list input:checkbox").removeAttr("checked");
	$("#devices_choise_modal_batch").modal('hide');
	alert("添加成功！");
}

//批量添加追加行数
function createPurchaseGoodsRow(append_row_no) {
	return '<tr>'
	+'<td class="center">' + append_row_no + '</td>'
	+'<td class="center"><input type="text" name="deviceNo" onclick="choiseDevice(this);"/><input type="hidden" id="deviceId"  name="advertDeviceDt[' + (append_row_no - 1) +'].deviceId"/></td>'
	+'<td class="center"></td>'
	+'<td class="center"></td>'
	+'<td class="center"><input type="text" id="advertFee"  name="advertDeviceDt[' + (append_row_no - 1) +'].advertFee"/></td>'
	+'<td class="center"><input type="hidden"  value="${deviceInfo.deviceId}"/>'
	+'<a href="javascript:void(0)" onclick="removeGoodsItem(this)">移除</a></td>'
	+'</tr>';
}

//获得选择设备各项值
function getChoiseGoods(ele_goodsRow){
	var $choiseGoods = {};
	var $ele_td = ele_goodsRow.find("td");
	$choiseGoods.deviceId = $ele_td.find("input[type=hidden]").eq(0).val();
	$choiseGoods.deviceName = $ele_td.find("label[name='deviceName']").eq(0).html();
	$choiseGoods.deviceNo = $ele_td.find("label[name='deviceNo']").eq(0).html();
	$choiseGoods.deviceAddress = $ele_td.find("label[name='deviceAddress']").eq(0).html();
	return $choiseGoods;
}

//填充设备信息
function fillGoodsInfo(purchaseGoodsRowNo,$choiseDevice) {
	var $ele_tr = $("#purchase_goods_list").find("tr").eq(purchaseGoodsRowNo - 1);
	$ele_tr.find("td").eq(1).find("input[name='deviceNo']").val($choiseDevice.deviceNo);
	$ele_tr.find("td").eq(1).find("input[type=hidden]").val($choiseDevice.deviceId);
	$ele_tr.find("td").eq(2).html($choiseDevice.deviceName);
	$ele_tr.find("td").eq(3).html($choiseDevice.deviceAddress);
}

//移除选择的设备信息
function removeGoodsItem(tdObj) {
	var itemCount = $("#purchase_goods_list").find("tr").length;
	var $ele_tr = $(tdObj).parents("tr:eq(0)");
	if(itemCount == 1) {
		$ele_tr.find("td").eq(1).html('');
		$ele_tr.find("td").eq(2).html('');
		$ele_tr.find("td").eq(3).html('');
		$ele_tr.find("td").eq(4).html('');
	} else {
		$(tdObj).parents("tr").remove();
	}
	
	 $("#purchase_goods_list").find("tr").each(function(i){
		  var deviceId = "advertDeviceDt[" + i + "].deviceId";
		  var advertFee = "advertDeviceDt[" + i + "].advertFee";
		  $(this).find("input[id='deviceId']").eq(0).attr("name",deviceId);
		  $(this).find("input[id='advertFee']").eq(0).attr("name",advertFee);
	  });
	 
	$("#purchase_goods_list").find("tr").each(function(i) {
		$(this).find("td").eq(0).html(i+1);
	});
	
}

//单个选择设备
function choiseDevice(input_device) {
	resetSelectDevice();
	var $ele_tr = $(input_device).parents("tr:eq(0)"); //得到选择的行数
	$("#purchase_goods_rowno").val($ele_tr.find("td").eq(0).html());
	$("#choise_type").val('0');
	//隐藏列表第一列
	oTable.fnSetColumnVis(0,false);
	//隐藏底部批量操作的按钮
	$("#choise_goods_action").hide();
	$('#devices_choise_modal_batch').modal('show');
}
//批量选择设备
function choiseDeviceBatch(){
	resetSelectDevice();
	$("#choise_type").val('1');
	//显示列表第一列
	oTable.fnSetColumnVis(0,true);
	$("#choise_goods_action").show();
	$('#devices_choise_modal_batch').modal('show');
}

//加载设备信息
function resetSelectDevice(){
	initTableBatch();
	$("#search_goods_table input:checkbox").removeAttr("checked");
	initOrgTreeBatch();
}

//搜索商品
function searchDevice() {
	if(oTable){
		oTable.fnDraw();
	}
}

//初始化机构树
function initOrgTreeBatch() {
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

    var treeObj = $("#device_tree_batch");
    var zNodes;

    $.ajax({
        url: ROOT_PATH + '/view/inventory/warehouse/getMulSubDictDataByPcode.action',
        type:'POST',
        data:{"dictPcode" : "AL_POSITION" ,"dictLevel":"5"},
        dataType:'json',
        success:function(data){
            zNodes = data;
            $.fn.zTree.init($("#device_tree_batch"), setting, zNodes);
        }
    });
    
    treeObj.hover(function () {
        if (!treeObj.hasClass("showIcon")) {
            treeObj.addClass("showIcon");
        }
    }, function() {
        treeObj.removeClass("showIcon");
    });
    initTableBatch();
}

//费用非空判断
function checkFee(){
	var result = true;
	$("#purchase_goods_list").find("tr").each(function(i){
		var advertFee = $(this).find("td input[id='advertFee']").eq(0).val();
		if (!advertFee) {
			alert("费用不能为空");
			result = false;
			return false;
		}else if(isNaN(advertFee)){
			alert("费用必须为数字");
			result = false;
			return false;
		}
	});
	return result;
}

