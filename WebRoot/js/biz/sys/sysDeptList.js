jQuery(function($) {
	$("#submenu-submenu-menu-sys-org-dept").addClass("active");
	$("#submenu-menu-sys-org").addClass("active");
	$("#submenu-menu-sys-org").addClass("open");
	initTable();
	$("#keyword").keyup(function() {
		initTable();
	});
	
});

oTable = null;

function initTable() {
	if(oTable == null) {
		oTable = $("#sysDept_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,//是否显示一个每页长度的选择条（需要分页器支持）
			"bProcessing": false,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/sys/sysDept/querySysDeptPage.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"deptName","sClass":"center"},
			               {"mDataProp":"realName","sClass":"center"},			               
			               {"mDataProp":"deptTel","sClass":"center"},
			               {"sDefaultContent": "","mRender": function(data, type, full) { 
			            	   var actionDiv = '';
			            	   actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
		                        '<button onclick="addOrModifySysDept(\'' + full.deptId + '\')" class="btn btn-xs btn-info"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
		                        '<button onclick="deleteSysDept(\'' + full.deptId + '\')" class="btn btn-xs btn-danger"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
			"aoColumnDefs": [//和aoColums类似，但他可以给指定列附近的属性
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
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
//	var selectNodes = $.fn.zTree.getZTreeObj("device_tree");
//    if(selectNodes!=null){
//    	if(selectNodes.getSelectedNodes() != ""){
//    		var id=selectNodes.getSelectedNodes()[0].id;
//            var pid=selectNodes.getSelectedNodes()[0].pid;  
//            var level = selectNodes.getSelectedNodes()[0].level;
//            param.id = id;
//            param.pid = pid;
//            param.level = level;
//    	}        
//    }

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
function searchDept() {
	if(oTable){
		oTable.fnDraw();
	}
}

function search(){
	if(oTable){
		oTable.fnDraw();
	}
}

//查询系统部门信息
function querySysDeptDt(id){
	window.location.href= ROOT_PATH +"/view/sys/sysDept/querySysDeptDt.action?deptId=" + id;
}

//删除系统部门
function deleteSysDept(id){
	if(confirm("确定删除？")){
		window.location.href= ROOT_PATH +"/view/sys/sysDept/deleteSysDept.action?deptId=" + id;
	}
}

//增加和修改系统部门信息
function addOrModifySysDept(id){
	if(id==''){
		window.location.href= ROOT_PATH +"/view/sys/sysDept/sysDeptAddChange.action";
	}else{
		window.location.href= ROOT_PATH +"/view/sys/sysDept/addOrModifySysDept.action?deptId=" + id;
	}
}

////初始化机构树
//function initOrgTree() {
//    var setting = {
//        data: {
//            simpleData: {
//                enable: true
//            }
//        },
//        callback: {
//            onClick: function(){
//            	search();
//            }
//        }
//    };
//
//    var treeObj = $("#device_tree");
//    var zNodes;
//
//    $.ajax({
//        url: ROOT_PATH + '/view/inventory/warehouse/getMulSubDictDataByPcode.action',
//        type:'POST',
//        data:{"dictPcode" : "AL_POSITION" ,"dictLevel":"5"},
//        dataType:'json',
//        success:function(data){
//            zNodes = data;
//            $.fn.zTree.init($("#device_tree"), setting, zNodes);
//        }
//    });
//    
//    treeObj.hover(function () {
//        if (!treeObj.hasClass("showIcon")) {
//            treeObj.addClass("showIcon");
//        }
//    }, function() {
//        treeObj.removeClass("showIcon");
//    });
//    initTable();
//}
