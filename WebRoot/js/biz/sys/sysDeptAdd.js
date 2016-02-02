jQuery(function($) {
	$("#submenu-menu-advert-user").addClass("active");
	$("#menu-advert").addClass("active");
	$("#menu-advert").addClass("open");

	initUserTable();
	selectInitOrgTree();
	
	var beforeShow = $("#beforeShow").val();
    if(beforeShow){
    	 $("#orgNameStr").attr("disabled",true);
    }
});

//单个追加用户到申购单
$(document).on("dblclick", "#user_list tbody tr", function() {
	var $choiseDeptChief = getChoiseUserInfo($(this));
	var a = $("#userIdStr").val($choiseDeptChief.userId);
	$("#sysDeptChief").val($choiseDeptChief.realName );
	$("#user_choise_modal").modal('hide');
	//focusNumbox();
});


//获得选择用户对象
function getChoiseUserInfo(ele_goodsRow){
	var $choiseDeptChief = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseDeptChief.userId = $ele_td.find("input[type=hidden]").eq(0).val();
	$choiseDeptChief.realName = $ele_td.find("label[name='realName']").eq(0).html();
	return $choiseDeptChief;
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


uTable = null;
function initUserTable() {
	if(uTable == null) {
		uTable = $("#user_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/sys/sysDept/querySysUserPage.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"realName","sClass":"center","mRender": function(data, type, full) { 
			            	   return '<label name="realName">'+ data + '</label><input type="hidden" value="'+full.userId+'"/>';
			               }},
			               {"mDataProp":"tel","sClass":"center"},
			               {"mDataProp":"email","sClass":"center"},
			               {"mDataProp":"userStatus","sClass":"center","mRender": function(data, type, full) { 
			            	   var goodsStatus = '';
			            	   if(data == '0'){
			            		   goodsStatus = '<span class="label label-sm label-success">正常</span>';
			            	   }
			            	   else if(data == '1') {
			            		   goodsStatus = '<span class="label label-sm label-error">禁用</span>';
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveData,
		});
	} else {
		uTable.fnDraw(); //重新加载数据
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
	var orgId = $("#orgId").val();
	var regionId = $("#regionId").val();
	var proviceId = $("#proviceId").val();
//	var accountId = $("#accountId").val();
//	param.accountId = accountId;
	param.regionId= regionId;
	param.proviceId = proviceId;
	param.orgId = orgId;
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
function searchGoods() {
	if(oTable){
		oTable.fnDraw();
	}
}

//单个选择设备
function choiseDevice(input_goodsName) {
	resetSelectDevice();
	$('#devices_choise_modal').modal('show');
}   

//加载设备数据
function resetSelectDevice(){
	initOrgTree();
	initTable();
}

//单个选择用户
function choiseDeptChief(input_goodsName) {
	resetSelectUser();
	$('#user_choise_modal').modal('show');
}  
//加载用户数据
function resetSelectUser(){
	initUserTable();
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
    initTable();
}

//初始化新增机构树
function selectInitOrgTree() {
    var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick,
                onClick : function(event, treeId, treeNode){
                	$("#dictId").val(treeNode.id);
                    $("#dictPid").val(treeNode.pid);
                    $("#regionPid").val(treeNode.getParentNode().pid);
                    $("#orgNameStr").val(treeNode.name);
                    $("#treeNode").val(treeNode.level);
                    hideMenu();
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
}

function showMenu() {
    var gtObj = $("#orgNameStr");
    if($("#gt_combobox").css("display") == "none"){
        var gtOffset = $("#orgNameStr").offset();
        $("#gt_combobox").css({width:gtObj.css("width"),left:gtOffset.left + "px", top:gtOffset.top + gtObj.outerHeight() + "px"}).slideDown("fast");
    }

    $("body").bind("mousedown", onBodyDown);
}

function hideMenu() {
    $("#gt_combobox").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "gt_combobox" || $(event.target).parents("#gt_combobox").length>0)) {
        hideMenu();
    }
}

function beforeClick(treeId, treeNode) {
    var check = (treeNode.level == "3");
    if (!check) alert("只能选择分公司...");
    return check;
}

//非空判断
function checkBlank(){
	var result = true;
	var sysDeptName = $("#sysDeptName").val();
	var userIdStr = $("#userIdStr").val();
	if(!sysDeptName){
		alert("部门名称不能为空！");
		result = false;
	}else if(!userIdStr){
		alert("部门负责人不能为空");
		result = false;
	}
	return result;
}

function checkTel(){
	var sysDeptTel = $("#sysDeptTel").val();
	if(sysDeptTel){
		if(isNaN(sysDeptTel)){
			$("#telError").html("<div></div><font color='red'>部门电话必须为数字!</font>");
			$("#sysDeptTel").val("");
		}
		else if(sysDeptTel.length > 20){
			$("#telError").html("<div></div><font color='red'>部门电话超过最大长度!</font>");
			$("#sysDeptTel").val("");
		}
	}
}

function checkDeptName(){
	var sysDeptName = $("#sysDeptName").val();
	if(sysDeptName){
		if(sysDeptName.length > 40){
			$("#nameError").html("<div></div><font color='red'>部门名称字数超过最大长度!</font>");
			$("#sysDeptName").val("");
		}
	}
}
