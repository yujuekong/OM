jQuery(function($) {
	$("#submenu-menu-car-info").addClass("active");
	$("#menu-car").addClass("active");
	$("#menu-car").addClass("open");
	initTable();
	initOrgTree();
	//单个追加设备到申购单
	$(document).on("dblclick", "#deviceInfo_list tbody tr", function() {
		var $choiseDevice = getChoiseDeviceInfo($(this));
//		checkCarLineNode($choiseDevice.districtId);
//		var rowCount = $("#rowCount").val();
//		if(rowCount > 0){
//			alert("该商圈已存在站点,请选择其它商圈!");
//			return ;
//		}
		var a = $("#districtIdStr").val($choiseDevice.districtId);
		$("#districtNameStr").val($choiseDevice.districtName);
		$("#districtLngStr").val($choiseDevice.districtLng);
		$("#districtLatStr").val($choiseDevice.districtLat);
		$("#goods_choise_modal").modal('hide');
	});


});
oTable = null;
//获得选择设备对象
function getChoiseDeviceInfo(ele_goodsRow){	
	var $choiseDevice = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseDevice.districtId = $ele_td.find("input[type=hidden]").eq(0).val();
	$choiseDevice.districtName = $ele_td.find("label[name='districtName']").eq(0).html();
	$choiseDevice.districtLng = $ele_td.find("label[name='districtLng']").eq(0).html();
	$choiseDevice.districtLat = $ele_td.find("label[name='districtLat']").eq(0).html();
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



function initTable() {
	if(oTable == null) {
		oTable = $("#deviceInfo_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/region/serviceInfo/queryService.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"districtNo","sClass":"center"},
			               {"mDataProp":"districtName","mRender": function(data, type, full) { 
			            	   return '<label name="districtName">'+ data + '</label><input type="hidden" value="'+full.districtId+'"/>';
			               }},
			               {"mDataProp":"districtAddress","sClass":"center"},
			               {"mDataProp":"districtLng","mRender": function(data, type, full) { 
			            	   return '<label name="districtLng">'+ data + '</label>';
			               }},
			               {"mDataProp":"districtLat","mRender": function(data, type, full) { 
			            	   return '<label name="districtLat">'+ data + '</label>';
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4]},  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                // {"bVisible": false, "aTargets": [0] },
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

function checkCarLineNode(districtId){
	if(districtId != "" && districtId != null){
		$.ajax({
			type : "POST",
			url : ROOT_PATH + "/view/car/carLineNode/queryNodeByDistrictId.action",
			sync: true,
			data : { "districtId" : districtId}, 
			success : function(data) {
				if(!data) {
					alert("系统错误，请联系管理员!");
				}
				ajaxobj=eval("("+data+")");
				var rowCount = ajaxobj.length;
				$("#rowCount").val(rowCount);
			}
		});
	}
}