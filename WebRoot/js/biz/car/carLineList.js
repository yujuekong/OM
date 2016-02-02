jQuery(function($) {
	$("#submenu-menu-car-line").addClass("active");
	$("#menu-car").addClass("active");
	$("#menu-car").addClass("open");
	initOrgTree();
	$("#house_keyword").keyup(function () {
        initTable();
    });
});

oTable = null;
function initTable() {
	if(oTable == null) {
		oTable = $("#carLine_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": false,
			"bInfo": true,//页脚信息
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置 
			"sAjaxSource": ROOT_PATH +"/view/car/carLine/queryCarLine.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"lineNo","sClass":"center"},
			               {"mDataProp":"lineName","sClass":"center"},
			               {"mDataProp": "lineType", "sClass":"center","mRender": function (data, type, full) {
			                    var lineType = '';
			                    if (data == '0') {
			                    	lineType = '<label  class="label label-sm label-info">时间最短</label>';
			                    }
			                    else if (data == '1') {
			                    	lineType = '<label class="label label-sm label-warning">线路最短</label>';
			                    }
			                    else if (data == '2') {
			                    	lineType = '<label  class="label label-sm label-success">花费最少</label>';
			                    }
			                    return lineType;
			                }
			                },
			               {"mDataProp":"lineLength","sClass":"center"},
			               {"mDataProp":"startDate","sClass":"center"},
			               {"mDataProp":"endDate","sClass":"center"},
			               {"mDataProp":"ctlHour","sClass":"center"},
			               {"mDataProp":"user.realName","sClass":"center"},
			               {"mDataProp":"agentDate","sClass":"center"},
			              
			               {
			                    "sDefaultContent": "", "mRender": function (data, type, full) {
			                    var actionDiv = '';
			                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
			                        '<button onclick="queryCarLineById(\'' + full.carLineId + '\')" class="btn btn-xs btn-success"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
			                        '<button onclick="modifyCarLine(\'' + full.carLineId + '\')" class="btn btn-xs btn-info"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
			                        '<button onclick="delCarLineById(\'' + full.carLineId + '\')" class="btn btn-xs btn-danger"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
			                    actionDiv += '</div>';
			                    return actionDiv;
			                },"sClass":"center"
			                }
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]},  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 {"aTargets":[0],"mRender":function(data,type,full){
			                     return "<a   onclick='carLineMap(\"" + full.carLineId + "\")'>" + data + "</a>"}
			                 }
			    
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
	var keyword = $("#house_keyword").val();
	var param = {"keyword":keyword};
	
    var selectNodes = $.fn.zTree.getZTreeObj("org_tree");
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
//            removeMask($('.goods-container'));
        }    
    });    
}
function searchCarLine() {
	if(oTable){
		oTable.fnDraw();
	}
}

//查询详情
function queryCarLineById(id) {
    window.location.href = ROOT_PATH + "/view/car/carLine/queryCarLineById.action?carLineId=" + id;
}

function add(id){
	window.location.href = ROOT_PATH +"/view/car/carLine/addOrModifyCarLine.action?carLineId="+id;
}
function modifyCarLine(id){
	window.location.href = ROOT_PATH +"/view/car/carLine/addOrModifyCarLine.action?carLineId="+id;
}
function delCarLineById(id){
	window.location.href = ROOT_PATH +"/view/car/carLine/delCarLineById.action?carLineId="+id;
}
function testMap(){
	window.location.href = ROOT_PATH +"/view/device/testMap.jsp";
}
function carLineMap(id){
	window.location.href = ROOT_PATH +"/view/car/carLine/queryCarlineNodeList.action?carLineId="+id;
}

//修改车辆线路类型
function modifylineType(id) {
    window.location.href = ROOT_PATH +"/view/car/carLine/modifylineType.action?carLineId=" + id;
}
//初始化机构树
function initOrgTree() {
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: function(){
            	searchCarLine();
            }
        }
    };

    var treeObj = $("#org_tree");
    var zNodes;
    $.ajax({
        url: ROOT_PATH + '/view/inventory/warehouse/getMulSubDictDataByPcode.action',
        type:'POST',
        data:{"dictPcode" : "AL_POSITION" ,"dictLevel":"5"},
        dataType:'json',
        success:function(data){
            zNodes = data;
            $.fn.zTree.init($("#org_tree"), setting, zNodes);
        }
    });
    initTable();
}
