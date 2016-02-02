jQuery(function($) {
	$("#submenu-menu-device-clean").addClass("active");
	$("#menu-device").addClass("active");
	$("#menu-device").addClass("open");
	
	initTable();
    $("#keyword").keyup(function () {
        initTable();
    });
    
    $("#dp_quoteStartDate").datetimepicker({
		minView: "month",
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
    
    $("#dp_quoteEndDate").datetimepicker({
		minView: "month",
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
oTable = null;
function initTable() {
	var deviceId = $("#deviceId").val();
	if(oTable == null) {
		oTable = $("#deviceClean_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": false,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource":  ROOT_PATH +"/view/device/deviceClean/queryDeviceClean.action?deviceId="+deviceId,  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"cleanNo","sClass":"center"},
			               {"mDataProp":"deviceInfo.deviceNo","sClass":"center"},
			               {"mDataProp":"deviceInfo.deviceName","sClass":"center"},
			               {"mDataProp":"deviceInfo.deviceType.deviceTypeName","sClass":"center"},
			               {"mDataProp":"cleanEndTiem","sClass":"center"},
			               {"mDataProp":"finishTime","sClass":"center"},
			               {"mDataProp":"cleanDate","sClass":"center"},
			               {"mDataProp":"sysUser.realName","sClass":"center"},
			               {"mDataProp":"cleanStatus","sClass":"center" ,"mRender": function (data, type, full) {
			                    var cleanStatus = '';
			                    if (data == '0') {
			                    	cleanStatus = '<span class="lbl">完成</span>';
			                    	
			                    }
			                    else if (data == '1') {
			                    	cleanStatus = '<span class="lbl">未完成</span>';
			                    }
			                    return cleanStatus;
			                }},
//			               {"mDataProp":"cleanStatus","sClass":"center" ,"mRender": function (data, type, full) {
//			                    var cleanStatus = '';
//			                    if (data == '0') {
//			                    	cleanStatus = '<input title="完成" class="ace ace-switch ace-switch-6" type="checkbox" onclick="changeStatus(this,'+full.cleanId+')" checked /><span class="lbl"></span>';
//			                    	
//			                    }
//			                    else if (data == '1') {
//			                    	cleanStatus = '<input title="未完成" class="ace ace-switch ace-switch-6" type="checkbox" onclick="changeStatus(this,'+full.cleanId+')"  /><span class="lbl"></span>';
//			                    }
//			                    return cleanStatus;
//			                }},
			               {"sDefaultContent": "","sClass":"center","mRender": function(data, type, full) { 
			            	   var actionDiv = '';
			            	   actionDiv = '<div class="hidden-sm hidden-xs btn-group">'+
			            	   '<button class="btn btn-xs btn-success" onclick="deviceCleanDtl(\'' + full.cleanId + '\')"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
								'<button class="btn btn-xs btn-danger" onclick="deleteType(\'' + full.cleanId + '\')"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
								actionDiv += '</div>';
								return actionDiv;
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6 ,7 ,8 ,9]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
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
    
    //有效起始日期
    var startDate = $("#dp_quoteStartDate").val();
    //有效终止日期
    var endDate = $("#dp_quoteEndDate").val();
    var deviceId = $("#deviceId").val();	
	var param = {
			"keyword":keyword,
			"deviceId" : deviceId,
			"startDate":startDate,
			"endDate":endDate
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
//搜索商品
function searchGoods() {
	if(oTable){
		oTable.fnDraw();
	}
}

/**
 * 设备清洗是否完成
 * @param checkbox
 * @param deviceId
 */
function changeStatus(checkbox,cleanId) {
	if(cleanId){
		var isDisable = null;
		if($(checkbox).prop("checked")) {
			isDisable = '0';
		} else {
			isDisable = '1';
		}
		$.ajax({
			type : "POST",
			url : ROOT_PATH + "/view/device/deviceClean/changeCleanStatus.action",
			data : { "cleanId" : cleanId,"isDisable":isDisable}, 
			success : function(data) {
				if(!data) {
					alert("系统错误，请联系管理员!");
				}
				initTable();
			}
		});
	}
}

function deleteType(id){
	if (confirm("确定删除此条设备清洗信息吗?")) {
		window.location.href = ROOT_PATH +"/view/device/deviceClean/delDeviceCleanById.action?cleanId="+id;
	}
}

function deviceCleanDtl(cleanId){
	window.location.href = ROOT_PATH +"/view/device/deviceClean/deviceCleanDtl.action?cleanId="+cleanId;
}

function add(){
	window.location.href = ROOT_PATH +"/view/device/deviceClean/addOrModifyDeviceClean.action?cleanId=add";
}

function dateFind(){
	if($("#dp_quoteStartDate").val() != "" && $("#dp_quoteEndDate").val() != ""){
		initTable();
	}
}

function quickSearch(type) {
    var startDate = "";
    var endDate = "";
    if (type == 1) { //本周
        startDate = DateUtil.dateToStr('yyyy-MM-dd', DateUtil.weekStartDate());
        endDate = DateUtil.dateToStr('yyyy-MM-dd', DateUtil.weekEndDate());
    } else if (type == 2) { //上周
        startDate = DateUtil.dateToStr('yyyy-MM-dd', DateUtil.lastWeekStartDate());
        endDate = DateUtil.dateToStr('yyyy-MM-dd', DateUtil.lastWeekEndDate());
    } else if (type == 3) { //本月
        startDate = DateUtil.dateToStr('yyyy-MM-dd', DateUtil.monthStartDate());
        endDate = DateUtil.dateToStr('yyyy-MM-dd', DateUtil.monthEndDate());
    } else if (type == 4) { //上月
        startDate = DateUtil.dateToStr('yyyy-MM-dd', DateUtil.lastMonthStartDate());
        endDate = DateUtil.dateToStr('yyyy-MM-dd', DateUtil.lastMonthEndDate());
    }
    $("#dp_quoteStartDate").val(startDate);
    $("#dp_quoteEndDate").val(endDate);
    initTable();
}