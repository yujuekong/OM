jQuery(function($) {
	$("#submenu-menu-yy-goods").addClass("active");
	$("#menu-yy").addClass("active");
	$("#menu-yy").addClass("open");

	initTable();
});

oTable = null;
function initTable() {
	if(oTable == null) {
		oTable = $("#carYyLine_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH +"/view/car/carYyLine/queryCarYyLine.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"carInfo.carNo","sClass":"center"},
			               {"mDataProp":"sysUser.realName","sClass":"center"},
			               {"mDataProp":"carLine.lineName","sClass":"center"},
			               {"mDataProp":"carInfo.carWeight","sClass":"center"},
			               {"mDataProp":"carLine.lineLength","sClass":"center"},
			               {"sDefaultContent": "","sClass":"center","mRender": function(data, type, full) { 
			            	   var actionDiv = '';
			            	   actionDiv = '<div class="hidden-sm hidden-xs btn-group">'+
			            	    '<button class="btn btn-xs btn-success" onclick="detailsCarYyLine(\'' + full.yyLineId + '\')"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
								'<button class="btn btn-xs btn-danger" onclick="delCarYyLine(\'' + full.yyLineId + '\')"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
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
function delCarYyLine(id){
	window.location.href = ROOT_PATH +"/view/car/carYyLine/delCarYyLineById.action?carYyLineId="+id;
}

function add(id){
	window.location.href = ROOT_PATH +"/view/car/carYyLine/addOrModifyCarYyLine.action?carYyLineId="+id;
}

function detailsCarYyLine(id){
	window.location.href = ROOT_PATH +"/view/car/carYyLine/queryCarYyLineById.action?carYyLineId="+id;
}