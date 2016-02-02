jQuery(function($) {
	$("#submenu-submenu-menu-sys-org-user").addClass("active");
	$("#submenu-menu-sys-org").addClass("active");
	$("#submenu-menu-sys-org").addClass("open");
	$("#menu-sys").addClass("active");
	$("#menu-sys").addClass("open");
	initTable();
	initOrgTree();
	
	 $("#searchGoodsBtn").click(function () {
	        searchAdvertStatus();
	    });
	$("#type_keyword").keyup(function () {
        initTable();
    });
});


oTable = null;

function initTable() {
	if(oTable == null) {
		oTable = $("#sysUserList").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,//是否显示一个每页长度的选择条（需要分页器支持）
			"bProcessing": false,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/sys/sysUser/querySysUserPage.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"userName"},
			               {"mDataProp":"realName"},
			               {"mDataProp":"tel"},			               
			               {"mDataProp":"email"},
			               {"mDataProp":"dictName"},
			               {"mDataProp":"userStatus","mRender": function(data, type, full) { 
			            	   var goodsStatus = '';
			            	   if(data == '0'){
			            		   goodsStatus = '<input title="启用" class="ace ace-switch ace-switch-6" type="checkbox" checked="checked" onclick="changeUserStatus(\'' + full.userId + '\')" /><span class="lbl"></span>';
			            	   }
			            	   else if(data == '1') {
			            		   goodsStatus ='<input title="禁用" class="ace ace-switch ace-switch-6" type="checkbox" onclick="changeUserStatus(\'' + full.userId + '\')"/><span class="lbl"></span>' ;
			            	   } 
			            	   return goodsStatus;
			               }},
			               {"mDataProp":"createDate"},
			               {"sDefaultContent": "","mRender": function(data, type, full) { 
			            	   var actionDiv = '';
			            	   actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
		                        '<button onclick="queryUserDt(\'' + full.userId + '\')" class="btn btn-xs btn-success" title="查询"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
		                        '<button onclick="addOrModifySysUser(\'' + full.userId + '\')" class="btn btn-xs btn-info" title="修改"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
		                        '<button onclick="resetPassword(\'' + full.userId + '\')" class="btn btn-xs btn-info" title="重置密码"><i class="ace-icon fa fa-refresh bigger-120"></i></button>' +
		                        '<button onclick="deleteSysUser(\'' + full.userId + '\')" class="btn btn-xs btn-danger" title="删除"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
	var keyword = $("#type_keyword").val();
	var userStatus = $("#userStatus").val();
	var param = {"keyword":keyword};
	var orgId = $("#orgId").val();
	var regionId = $("#regionId").val();
	var proviceId = $("#proviceId").val();
	var accountId = $("#accountId").val();
//	var selectNodes = $.fn.zTree.getZTreeObj("org_tree");
	param.orgId = orgId;
	param.regionId= regionId;
	param.proviceId = proviceId;
	param.accountId = accountId;
	if(userStatus){
		param.userStatus = userStatus;
	}
    /*if(selectNodes!=null){
    	if(selectNodes.getSelectedNodes() != ""){
    		var id=selectNodes.getSelectedNodes()[0].id;       
            var pid=selectNodes.getSelectedNodes()[0].pid;  
            var level = selectNodes.getSelectedNodes()[0].level;
            param.id = id;
            param.pid = pid;
            param.level = level;
    	}        
    }*/

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

//查询用户详情
function queryUserDt(id){
	window.location.href=ROOT_PATH + "/view/sys/sysUser/querySysUser.action?userId=" + id;
}

//删除用户
function deleteSysUser(id){
	if(confirm("确定删除？")){
		window.location.href=ROOT_PATH + "/view/sys/sysUser/deleteSysUser.action?userId=" + id;
	}
	
}

//增加和修改用户
function addOrModifySysUser(id){
	if(id==''){
		window.location.href=ROOT_PATH + "/view/sys/sysUser/preAddOrModifyUser.action";
	}else{
		window.location.href=ROOT_PATH + "/view/sys/sysUser/addOrModifySysUser.action?userId=" + id;
	}
}

//重置密码
function resetPassword(id){
	if(confirm("确定重置该用户的密码?")){
		window.location.href=ROOT_PATH + "/view/sys/sysUser/resetPassword.action?userId=" + id;
	}
}

//修改用户状态
function changeUserStatus(id){
	if(confirm("确定修改？")){
	window.location.href=ROOT_PATH + "/view/sys/sysUser/modifySysUserStatus.action?userId=" + id;
	}
	else{
		window.location.href=ROOT_PATH + "/view/sys/userList.jsp";
	}
}

//搜索用户
function searchUser() {
	if(oTable){
		oTable.fnDraw();
	}
}
//搜索用户状态
function searchUserStatus() {
    if (oTable) {
        oTable.fnDraw();
    }
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
            	searchUser();
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
    
    treeObj.hover(function () {
        if (!treeObj.hasClass("showIcon")) {
            treeObj.addClass("showIcon");
        }
    }, function() {
        treeObj.removeClass("showIcon");
    });
    initTable();
}


