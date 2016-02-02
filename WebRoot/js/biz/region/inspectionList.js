jQuery(function($) {
	$("#submenu-menu-advert-user").addClass("active");
	$("#menu-advert").addClass("active");
	$("#menu-advert").addClass("open");
	
	initTable();
	
	$("#team_keyword").keyup(function () {
        initTable();
    });
	
});

oTable = null;
function initTable() {
	if(oTable == null) {
		oTable = $("#inspection_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,//是否显示一个每页长度的选择条（需要分页器支持）
			"bProcessing": false,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/region/inspection/queryInspectionPage.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"deviceNo","sClass":"center"},
			               {"mDataProp":"deviceName","sClass":"center"},
			               {"mDataProp":"realName","sClass":"center"},
			               {"mDataProp":"lastTime","sClass":"center"},
			               {"mDataProp":"thisTime","sClass":"center"},
			               {"mDataProp":"remark"},
			               {"mDataProp": "maintainIsFinish", "mRender": function (data, type, full) {
			                    var goodsStatus = '';
			                    if (data == '1') {
			                        goodsStatus = '<span style="cursor:pointer;"  class="label label-sm label-success">已完成</span>';
			                    }
			                    else if (data == '0') {
			                        goodsStatus = '<span style="cursor:pointer;"  class="label label-sm label-error">未完成</span>';
			                    }
			                    return goodsStatus;
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
			                 {
			                	 sDefaultContent: '',
			                	 aTargets: [ '_all' ]
			                	  },
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6],
			                	 "searchable": false,
			                     "orderable": false,
			                     "targets": 0
			                 }  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
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
	var keyword = $("#type_keyword").val();
    var param = {"keyword": keyword};
    var teamKeyword = $("#team_keyword").val();
    var inspectionStatus = $("#inspectionStatus").val();
    param.teamKeyword = teamKeyword;
    if(inspectionStatus){
    	param.inspectionStatus = inspectionStatus;
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
function search() {
	if(oTable){
		oTable.fnDraw();
	}
}

//改变状态
function searchStatus(){
	if(oTable){
		oTable.fnDraw();
	}
}

