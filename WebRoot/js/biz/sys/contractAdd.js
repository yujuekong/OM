jQuery(function($) {
	$("#submenu-menu-advert-user").addClass("active");
	$("#menu-advert").addClass("active");
	$("#menu-advert").addClass("open");
	
//	initAdvertTable();
//	initSellerTable();
//	initServiceTable();
	
	$("#contract_startDate").datetimepicker({
		language: 'zh-CN',
		format: 'yyyy-mm-dd',
		weekStart: 1,
	    todayBtn:  1,
		autoclose: true,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	});
	$("#contract_endDate").datetimepicker({
		format: 'yyyy-mm-dd',
	    language: 'zh-CN',
		weekStart: 1,
	    todayBtn:  1,
		autoclose: true,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	});
	
});

//单个选择广告内容
$(document).on("dblclick", "#advert_list tbody tr", function() {
	var $ChoiseAdvert = getChoiseAdvert($(this));
	var a = $("#idStr").val($ChoiseAdvert.advertId);
	$("#nameStr").val($ChoiseAdvert.advertName);
	$("#advert_choise_modal").modal('hide');
});

//单个选择供应商
$(document).on("dblclick", "#seller_list tbody tr", function() {
	var $choiseSeller = getchoiseSeller($(this));
	var a = $("#idStr").val($choiseSeller.sellerId);
	$("#nameStr").val($choiseSeller.sellerName);
	$("#seller_choise_modal").modal('hide');
});

//单个选择商圈
$(document).on("dblclick", "#service_list tbody tr", function() {
	var $ChoiseService = getChoiseService($(this));
	var a = $("#idStr").val($ChoiseService.serviceId);
	$("#nameStr").val($ChoiseService.serviceName);
	$("#service_choise_modal").modal('hide');
});

//获得选择广告对象
function getChoiseAdvert(ele_goodsRow){
	var $ChoiseAdvert = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$ChoiseAdvert.advertId = $ele_td.find("input[type=hidden]").eq(0).val();
	$ChoiseAdvert.advertName = $ele_td.find("label[name='advertTitle']").eq(0).html();
	return $ChoiseAdvert;
}

//获得选择供应商对象
function getchoiseSeller(ele_goodsRow){
	var $choiseSeller = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseSeller.sellerId = $ele_td.find("input[type=hidden]").eq(0).val();
	$choiseSeller.sellerName = $ele_td.find("label[name='sellerName']").eq(0).html();
	return $choiseSeller;
}

//获得选择商圈对象
function getChoiseService(ele_goodsRow){
	var $ChoiseService = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$ChoiseService.serviceId = $ele_td.find("input[type=hidden]").eq(0).val();
	$ChoiseService.serviceName = $ele_td.find("label[name='serviceName']").eq(0).html();
	return $ChoiseService;
}

function initAdvertTable() {
	if(oTable == null) {
		uTable = $("#advert_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bRetrieve": true,
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/advert/advertInfo/queryAdvertInfoPage.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"advertTitle","sClass":"center","mRender": function(data, type, full) { 
			            	   return '<label name="advertTitle">'+ data + '</label><input type="hidden" value="'+full.advertInfoId+'"/>';
			               }},
			               {"mDataProp":"auName","sClass":"center"},
			               {"mDataProp":"auLinktel","sClass":"center"},
			               {"mDataProp":"startDate","sClass":"center"},
			               {"mDataProp":"endDate","sClass":"center"},
			               {"mDataProp":"createDate","sClass":"center"},
			               {"mDataProp":"advertStatus","sClass":"center","mRender": function(data, type, full) { 
			            	   var goodsStatus = '';
			            	   if(data == '0'){
			            		   goodsStatus = '<span class="label label-sm label-success">正常</span>';
			            	   }
			            	   else if(data == '1') {
			            		   goodsStatus ='<span class="label label-sm label-error">禁用</span>';
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveData,
		});
	} else {
		oTable.fnDraw(); //重新加载数据
	}
}

function initSellerTable() {
    if (oTable == null) {
        oTable = $("#seller_list").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": true,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": true,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            "bFilter": false,//过滤功能
            "bRetrieve": true,
            "bInfo": true,//页脚信息
            //"sPaginationType": "two_button",
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/seller/sellerInfo/querySellerPage.action",  //异步请求地址
            "aoColumns": [
				{"mDataProp":"sellerName","sClass":"center","mRender": function(data, type, full) { 
					   return '<label name="sellerName">'+ data + '</label><input type="hidden" value="'+full.sellerId+'"/>';
				}},
                {"mDataProp": "sellerNo","sClass":"center"},
                {"mDataProp": "linkMan","sClass":"center"},
                {"mDataProp": "linkTel","sClass":"center"},
                {"mDataProp": "createDate","sClass":"center"},
                {"mDataProp":"sellerStatus","sClass":"center","mRender": function(data, type, full) { 
	            	   var goodsStatus = '';
	            	   if(data == '0'){
	            		   goodsStatus = '<span class="label label-sm label-success">正常</span>';
	            	   }
	            	   else if(data == '1') {
	            		   goodsStatus ='<span class="label label-sm label-error">禁用</span>';
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
            "aoColumnDefs": [{//和aoColums类似，但他可以给指定列附近爱属性
                "bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5],//这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
            }],
            "fnServerData": retrieveData,
        });


    } else {
        oTable.fnDraw(); //重新加载数据
    }

}


function initServiceTable() {
    if (oTable == null) {
        oTable = $("#service_list").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": true,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": true,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            "bFilter": false,//过滤功能
            "bRetrieve": true,
            "bInfo": true,//页脚信息
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH +"/view/region/serviceInfo/queryService.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "districtNo","sClass":"center"},
                {"mDataProp":"districtName","sClass":"center","mRender": function(data, type, full) { 
					   return '<label name="serviceName">'+ data + '</label><input type="hidden" value="'+full.districtId+'"/>';
				}},
                {"mDataProp": "districtAddress","sClass":"center"},
                {"mDataProp": "sysDict.dictName","sClass":"center"},
                {"mDataProp": "districtManager","sClass":"center"},
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
            "aoColumnDefs": [{//和aoColums类似，但他可以给指定列附近爱属性
                "bSortable": false, "aTargets": [0, 1, 2, 3, 4],//这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
            }],
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
	var advertInfoDt = $("#advertInfoDt").val();
	var param = {"advertInfoDt":advertInfoDt};
	
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


function choiseSeller(input_seller){
	var contractType = $("#contractTypeSelect").val();
	if(contractType == "-1"){
		alert("合同类型不能为空！");
		$("#contractTypeSelect").focus();
	}
	else{
		if(contractType == "0"){
			resetSelectSeller();
			$('#seller_choise_modal').modal('show');
		}
		else if(contractType == "1"){
			resetSelectService();
			$('#service_choise_modal').modal('show');
		}
		else if(contractType == "2"){
			resetSelectAdvert();
			$('#advert_choise_modal').modal('show');
		}
	}
}

function resetSelectSeller(){
	oTable = null;
	initSellerTable();
}

function resetSelectService(){
	oTable = null;
	initServiceTable();
}

function resetSelectAdvert(){
	oTable = null;
	initAdvertTable();
}

//非空判断
function checkBlank(){
	var result = true;
	var sysContractName = $("#sysContractName").val();
	var contractTypeSelect = $("#contractTypeSelect").val();
	var nameStr = $("#nameStr").val();
	var contract_startDate = $("#contract_startDate").val();
	var contract_endDate = $("#contract_endDate").val();
	var contractFeeSelect = $("#contractFeeSelect").val();
	var payMethodSelect = $("#payMethodSelect").val();
	var payCycleSelect = $("#payCycleSelect").val();
	
	if(!sysContractName){
		alert("合同名称不能为空！");
		result = false;
	}
	else if(contractTypeSelect == "-1"){
		alert("合同类型不能为空！");
		result = false;
	}
	else if(!nameStr){
		alert("关联商家不能为空！");
		result = false;
	}
	else if(!contract_startDate){
		alert("合同开始日期不能为空！");
		result = false;
	}
	else if(!contract_endDate){
		alert("合同结束日期不能为空！");
		result = false;
	}
	else if(contractFeeSelect == "-1"){
		alert("费用支付状态不能为空！");
		result  = false;
	}
	else if(payMethodSelect == "-1"){
		alert("支付方式不能为空！");
		result = false;
	}
	else if(payCycleSelect == "-1"){
		alert("支付周期不能为空！");
		result = false;
	}
	
	return result;
}


