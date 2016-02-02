jQuery(function($) {
	$("#submenu-menu-inventory-plan").addClass("active");
	$("#menu-inventory").addClass("active");
	$("#menu-inventory").addClass("open");
	initTable();
	//单选 按钮可用与不可用
	$(document).on("click", "#yyPlan_list td input:checkbox", function() {
		var checkedCount = $("#yyPlan_list input:checkbox:checked").length;
		if(checkedCount > 0) {
			$("#choise_submit").removeClass("disabled");
		} else {
			$("#choise_submit").addClass("disabled");
		}
	});
		
	//站点选择列表checkbox全选，全不选
	$(document).on("click","th input:checkbox",function() {
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
});

oTable = null;
function initTable() {
	
	if(oTable == null) {
		oTable = $("#yyPlan_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"iDisplayLength": 100,
			"bProcessing": false,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/region/dispatchSubOrder/queryDSubOrder.action",  //异步请求地址
			"aoColumns" : [ 
			               {"sDefaultContent": "","sClass": "center","bSortable": false,"mRender": function(data, type, full) { 
			            	   return '<label class="position-relative">'+
			            	   '<input type="checkbox" class="ace" onclick="checkeds()"  id="checkid" name="multiple" value="'+full.subOrderId+'"/><span class="lbl"></span></label>';
			               }},
			               {"mDataProp":"deviceNo","sClass":"center"},
			               {"mDataProp":"districtName","sClass":"center"},
			               {"mDataProp":"goodsName","sClass":"center"},
			               {"mDataProp":"goodsNumber","sClass":"center"},
			               {"mDataProp":"dispatchingTime","sClass":"center"},
			               {"mDataProp": "orderAllocation","sClass":"center", "mRender": function (data, type, full) {
			                    var orderAllocation = '';
			                    if (data == '0') {
			                    	orderAllocation = '<span class="lbl">未完成</span>';
			                    }
			                    else if (data == '1') {
			                    	orderAllocation = '<span class="lbl">已完成</span>';
			                    }
			                    return orderAllocation;
			                }},
//			               {"mDataProp": "orderAllocation","sClass":"center", "mRender": function (data, type, full) {
//			                    var orderAllocation = '';
//			                    if (data == '0') {
//			                    	orderAllocation = '<input title="未分配" class="ace ace-switch ace-switch-6" type="checkbox" onclick="changeStatus(this,'+full.subOrderId+')" /><span class="lbl"></span>';
//			                    }
//			                    else if (data == '1') {
//			                    	orderAllocation = '<input title="已分配" class="ace ace-switch ace-switch-6" type="checkbox" onclick="changeStatus(this,'+full.subOrderId+')"  checked/><span class="lbl"></span>';
//			                    }
//			                    return orderAllocation;
//			                }},
			               {"sDefaultContent": "","sClass":"center","mRender": function(data, type, full) { 
			            	   var actionDiv = '';
			            	   actionDiv = '<div class="hidden-sm hidden-xs btn-group">'+
			            	    '<button class="btn btn-xs btn-info" onclick="modifyDispatchSubOrder(\'' + full.subOrderId + '\')"><i class="ace-icon fa fa-pencil bigger-120"></i></button>'+
			            	    '<button class="btn btn-xs btn-danger" onclick="delDispatchPlan(\'' + full.subOrderId + '\')"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6, 7]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
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
 * @param subOrderId 子订单ID
 */
function changeStatus(checkbox,subOrderId) {
	if(subOrderId){
		var isDisable = null;
		if($(checkbox).prop("checked")) {
			isDisable = '1';
		} else {
			isDisable = '0';
		}
		$.ajax({
			type : "POST",
			url : ROOT_PATH + "/view/region/dispatchSubOrder/changeStatus.action",
			data : { "subOrderId" : subOrderId,"isDisable":isDisable}, 
			success : function(data) {
				if(!data) {
					alert("系统错误，请联系管理员!");
				}
				initTable();
			}
		});
	}
}

function add(id){
	window.location.href = ROOT_PATH +"/view/region/dispatchSubOrder/addDSubOrder.action?subOrderId="+id;
}

function delDispatchPlan(id){
	if (confirm("确定删除此条待处理订单信息吗?")) {
		window.location.href = ROOT_PATH +"/view/region/dispatchSubOrder/delDSubOrderById.action?subOrderId="+id;
	}
}

function modifyDispatchSubOrder(id){
	window.location.href = ROOT_PATH +"/view/region/dispatchSubOrder/modifyDSubOrder.action?subOrderId="+id;
}
function addOrder(){
	$("#choise_submit").addClass("disabled");
	var idsList = "";
	$("#yyPlan_list td input:checkbox:checked").each(function(i) {
		var deviceGoodsId = $(this).val();
		if(idsList==""){
			idsList = deviceGoodsId;
		}else{
			idsList=idsList +","+deviceGoodsId;
		}
		
	});
	if(idsList != null){
		$.ajax({
			type : "POST",
			async:false,
			url : ROOT_PATH + "/view/region/dispatchSubOrder/combineDSubOrder.action",
			data : { "idsList" : idsList}, 
			success : function(data) {
				json=eval("("+data+")");
				if(json == "storageWarehouseException"){
					alert("未配置仓库信息!");
				}else if(json == "teamException"){
					alert("未配置配送小组!");
				}else{
					initTable();
				}
				$("#choise_submit").removeAttr("disabled");
			}
		});
	} 
	
}
