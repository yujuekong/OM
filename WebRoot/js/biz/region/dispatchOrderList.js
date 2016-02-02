jQuery(function($) {
	$("#submenu-menu-inventory-plan").addClass("active");
	$("#menu-inventory").addClass("active");
	$("#menu-inventory").addClass("open");
	initTable();
    $("#out_keyword").keyup(function () {
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
	if(oTable == null) {
		oTable = $("#yyPlan_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": false,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/region/dispatchOrder/queryDOrder.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"orderNo","sClass":"center"},
			               {"mDataProp":"user.realName","sClass":"center"},
			               {"mDataProp":"team.teamName","sClass":"center"},
			               {"mDataProp": "orderStatus","sClass":"center", "mRender": function (data, type, full) {
			                    var orderStatus = '';
			                    if (data == '0') {
			                    	orderStatus = '<label class="center" />未完成</label><span class="lbl"></span>';
			                    }
			                    else if (data == '1') {
			                    	orderStatus = '<label class="center" />配送中</label><span class="lbl"></span>';
			                    }
			                    else if (data == '2') {
			                    	orderStatus = '<label class="center" />完成</label><span class="lbl"></span>';
			                    }
			                    return orderStatus;
			                }},
			               {"sDefaultContent": "","sClass":"center","mRender": function(data, type, full) { 
			            	   var actionDiv = '';
			            	   actionDiv = '<div class="hidden-sm hidden-xs btn-group">'+
			            	    '<button class="btn btn-xs btn-success" onclick="detailsDispatchPlan(\'' + full.orderId + '\')"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
			            	    '<button class="btn btn-xs btn-info" onclick="modifyDispatchPlan(\'' + full.orderId + '\')"><i class="ace-icon fa fa-pencil bigger-120"></i></button>';
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
    var keyword = $("#out_keyword").val();
    //有效起始日期
    var startDate = $("#dp_quoteStartDate").val();
    //有效终止日期
    var endDate = $("#dp_quoteEndDate").val();
    var deviceId = $("#deviceId").val();	
	var param = {
		"keyword":keyword,
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
 * 启用禁止
 * @param orderId 子订单ID
 */
function changeStatus(checkbox,orderId) {
	if(orderId){
		var isDisable = null;
		if($(checkbox).prop("checked")) {
			isDisable = '1';
		} else {
			isDisable = '0';
		}
		$.ajax({
			type : "POST",
			url : ROOT_PATH + "/view/region/dispatchOrder/changeStatus.action",
			data : { "orderId" : orderId,"isDisable":isDisable}, 
			success : function(data) {
				if(!data) {
					alert("系统错误，请联系管理员!");
				}
				initTable();
			}
		});
	}
}



function detailsDispatchPlan(id){
	window.location.href = ROOT_PATH +"/view/region/dispatchOrder/dispatchOrderDtl.action?orderId="+id;
}

function delDispatchPlan(id){
	if (confirm("确定删除此条待处理订单信息吗?")) {
		window.location.href = ROOT_PATH +"/view/region/dispatchOrder/delDOrderById.action?orderId="+id;
	}	
}

function modifyDispatchPlan(id){
	alert("后续讨论开发");
	//window.location.href = ROOT_PATH +"/view/region/dispatchSubOrder/addOrModifyDSubOrder.action?orderId="+id;
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