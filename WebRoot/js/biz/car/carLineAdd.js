jQuery(function($) {
	$("#submenu-op-list0").addClass("active");
	$("#menu-op").addClass("active");
	$("#menu-op").addClass("open");
	initUserTable();
	initOrgTree();
	initTree();
	
	//日历插件
	$("#dp_expEndTime1").datetimepicker({
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
	$("#dp_expEndTime2").datetimepicker({
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
	
	//单个追加用户到text
	$(document).on("dblclick", "#user_list tbody tr", function() {
		var $choiseUser = getChoiseUserInfo($(this));
		$("#userIdStr").val($choiseUser.userId);
		$("#userNameStr").val($choiseUser.realName);
		$("#user_choise_modal").modal('hide');
		//focusNumbox();
	});
	//批量选择时，单个checkbox选中改变[添加按钮]背景颜色
	$(document).on("click", "#search_goods_table td input:checkbox", function() {
		var checkedCount = $("#search_goods_table input:checkbox:checked").length;
		if(checkedCount > 0) {
			$("#choise_submit").removeClass("disabled");
		} else {
			$("#choise_submit").addClass("disabled");
		}
	});
		
	//站点选择列表checkbox全选，全不选
	$(document).on('click','th input:checkbox',function() {
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
	
	 $('#carLine').bootstrapValidator({
	        message: 'This value is not valid',
	        fields: {
	            'carLine.lineName': {
	                validators: {
	                    notEmpty: {
	                    	
	                    },
	                    stringLength: {
	                        min: 2,
	                        max: 40,
	                    },
	                }
	            }, 'carLine.lineLength': {
	                validators: {
	                    notEmpty: {
	                    	
	                    },
	                    digits:{
	                    },
	                }
	            }, 'carLine.ctlHour': {
	                validators: {
	                    notEmpty: {
	                    	
	                    },
	                    digits:{
	                    },
	                }
	            }, 'carLine.lineType': {
	                validators: {
	                    notEmpty: {
	                    }
	                }
	            }
	        }
	    });
	
});


oTable = null;
//初始化站点列表
function initTable() {
	var isNull = 0;
	if(oTable == null) {
		oTable = $("#search_goods_table").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH +"/view/car/carLineNode/querycarLineNode.action?isNull="+isNull, //异步请求地址 ：查询站点信息
			"aoColumns" : [ 
			               {"sDefaultContent": "","sClass": "center","bSortable": false,"mRender": function(data, type, full) { 
			            	   return '<label class="position-relative">'+
			            	   '<input type="checkbox" class="ace" value="'+full.lineNodeId+'"/><span class="lbl"></span></label>';
			               }},
			               {"mDataProp":"nodeNo","mRender": function(data, type, full) { 
			            	   return '<label name="nodeNo">'+ data + '</label><input type="hidden" value="'+full.lineNodeId+'"/>';
			               }},
			               {"mDataProp":"districtNo","mRender": function(data, type, full) { 
			            	   return '<label name="districtNo">'+ data + '</label><input type="hidden" value="'+full.districtId+'"/>';
			               }},
			               {"mDataProp":"districtName","mRender": function(data, type, full) { 
			            	   return '<label name="districtName">'+ data + '</label>';
			               }},
			               {"mDataProp":"districtAddress","mRender": function(data, type, full) { 
			            	   return '<label name="districtAddress">'+ data + '</label>';
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
* 备注：方法的3个参数名称不能改变
*/
function retrieveData(sSource, aoData, fnCallback) { 
	//商品名称或编号
	var keyword = $("#keyword").val();
	//商品规格
	var carLineId =$("#carLineId").val();
	var param = {"keyword":keyword,"carLineId":carLineId};
	
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
        }    
    });    
}


//搜索商品
function searchCarNodes() {
    if (oTable) {
        oTable.fnDraw();
    }
}

//批量选择站点
function batchChoiseGoods() {
	resetSelectGoodsForm();
	$("#choise_goods_action").show();
	$('#goods_choise_modal').modal('show');
}

//批量追加站点到表单
function batchAppendGoods() {
	var $purchase_goods_list = $("#purchase_goods_list");
//	var $pgl_fr = $purchase_goods_list.find("tr").eq(0);  //找到第一行
//	var fr_goodsName = $pgl_fr.find("input[name='goodsName']").eq(0).val(); //取第一列单元格goodsname的值
	var rowCount = $purchase_goods_list.find("tr").length; //得到行数
//	if(!fr_goodsName) {
//		rowCount = rowCount - 1;
//		$pgl_fr.remove();
//	}
	//遍历查询站点列表，添加选择的站点到表单显示页面
	$("#search_goods_table td input:checkbox:checked").each(function(i) {
		var nodeId = $(this).val();
		if(!existPurchaseGoods(nodeId)) {
			var $choise_row = $(this).closest('tr');
			rowCount = rowCount + 1;
			var append_row_no = rowCount;
			var append_row = createPurchaseGoodsRow(append_row_no);
			var $choiseGoods = getChoiseGoods($choise_row);
			$purchase_goods_list.append(append_row);
			var append_row = append_row_no;
			fillGoodsInfo(append_row,$choiseGoods);
			
		}else{
			return;
		}

		
	});
	$("#search_goods_table input:checkbox").removeAttr("checked");
	$("#choise_submit").addClass("disabled");
	$("#goods_choise_modal").modal('hide');
	alert("添加成功！");
	focusNumbox();

}
//批量添加追加行数
function createPurchaseGoodsRow(append_row_no) {
	return '<tr>'
	+'<td class="center" >' +( append_row_no) + '</td>'
	+'<td class="center" ><input type="hidden" id="lNodeId" name="carLineNodeList[' + (append_row_no - 1) + '].lineNodeId"  onclick="choiseGoods(this)"/><input type="text" class="center" /></td>'
	+'<td class="center" ></td>'
	+'<td class="center" ></td>'
	+'<td class="center" ></td>'
    +'<td class="center" ><a href="javascript:void(0)" onclick="removeGoodsItem(this)">移除</a></td>'
	+'</tr>';
}

//获得选择站点各项值
function getChoiseGoods(ele_goodsRow){
	var $choiseGoods = {};
	var $ele_td = ele_goodsRow.find("td");
	$choiseGoods.lineNodeId = $ele_td.find("input[type='hidden']").eq(0).val();
	$choiseGoods.nodeNo = $ele_td.find("label[name='nodeNo']").eq(0).html();
	$choiseGoods.districtNo = $ele_td.find("label[name='districtNo']").eq(0).html();
	$choiseGoods.districtId = $ele_td.find("label[name='districtId']").eq(0).html();
	$choiseGoods.districtName = $ele_td.find("label[name='districtName']").eq(0).html();
	$choiseGoods.districtAddress = $ele_td.find("label[name='districtAddress']").eq(0).html();
	return $choiseGoods;
}

//是否已存在 已选中的站点
function existPurchaseGoods(nodeId) {
	
	var flag = false;
	$("#purchase_goods_list").find("tr").each(function(i){
		var lNodeId = $(this).find("td input[id ='lNodeId']").eq(0).val();
		if (nodeId == lNodeId) {
			flag = true;
		}
	});
	return flag;
}

//填充站点信息
function fillGoodsInfo(purchaseGoodsRowNo,$choiseGoods) {
	var $ele_tr = $("#purchase_goods_list").find("tr").eq(purchaseGoodsRowNo - 1);
	$ele_tr.find("td").eq(1).find("input[type=hidden]").val($choiseGoods.lineNodeId);
	$ele_tr.find("td").eq(1).find("input[type=text]").val($choiseGoods.nodeNo);
	//$ele_tr.find("td").eq(2).html($choiseGoods.districtId);
	$ele_tr.find("td").eq(2).html($choiseGoods.districtNo);
	$ele_tr.find("td").eq(3).html($choiseGoods.districtName);
	$ele_tr.find("td").eq(4).html($choiseGoods.districtAddress);
}

//移除选择的站点信息
function removeGoodsItem(tdObj) {
	var itemCount = $("#purchase_goods_list").find("tr").length;
	var $ele_tr = $(tdObj).parents("tr:eq(0)");
	$(tdObj).parents("tr").remove();
	$("#purchase_goods_list").find("tr").each(function(i) {
		$(this).find("td").eq(0).html(i+1);
        $(this).find("input").eq(0).attr("name", "carLineNodeList[" + i + "].lineNodeId");
	});	
}

//重置站点选择窗口
function resetSelectGoodsForm() {
	initTable();
	$("#search_goods_table input:checkbox").removeAttr("checked");
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
            	searchCarNodes();
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
function choiseUser(input_goodsName) {
	resetSelectUser();
	$('#user_choise_modal').modal('show');
}  
function resetSelectUser(){
	initUserTable();
}

//获得选择用户对象
function getChoiseUserInfo(ele_goodsRow){
	var $choiseUser = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseUser.userId = $ele_td.find("input[type=hidden]").eq(0).val();
	$choiseUser.realName = $ele_td.find("label[name='realName']").eq(0).html();
	return $choiseUser;
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
			//"sAjaxSource": ROOT_PATH + "/view/device/deviceClean/queryUser.action",  //异步请求地址
			"sAjaxSource": ROOT_PATH + "/view/sys/sysUser/querySysUserPage.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"userName","sClass":"center"},
			               {"mDataProp":"realName","mRender": function(data, type, full) { 
			            	   return '<label name="realName">'+ data + '</label><input type="hidden" value="'+full.userId+'"/>';
			               }},
			               {"mDataProp":"tel","sClass":"center"},
			               {"mDataProp":"email","sClass":"center"},
			               {"mDataProp":"createDate","sClass":"center"}
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveDataUser,
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
function retrieveDataUser(sSource, aoData, fnCallback) { 
	//商品名称或编号
	var keyword = $("#keyword").val();
	var orgId = $("#orgId").val();
	var regionId = $("#regionId").val();
	var proviceId = $("#provinceId").val();
	
	
	var param = {"keyword":keyword};
	param.orgId = orgId;
	param.regionId= regionId;
	param.proviceId = proviceId;
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
//加载区域组织机构树
function initTree() {
    var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick,
                onClick : function(event, treeId, treeNode){
                    $("#orgId").val(treeNode.id);
                    $("#provinceId").val(treeNode.pid);
                    $("#regionId").val(treeNode.getParentNode().pid);
                    $("#userPro").val(treeNode.name);
                    hideMenu();
                }
            }
        };
        var treeObj = $("#org_tree1");
        var zNodes;
        $.ajax({
            url: ROOT_PATH + '/view/inventory/warehouse/getMulSubDictDataByPcode.action',
            type:'POST',
            data:{"dictPcode" : "AL_POSITION" ,"dictLevel":"5"},
            dataType:'json',
            success:function(data){
                zNodes = data;
                $.fn.zTree.init($("#org_tree1"), setting, zNodes);
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
//显示组织机构树
function showMenu() {
    var gtObj = $("#userPro");
    if($("#gt_combobox").css("display") == "none"){
        var gtOffset = $("#userPro").offset();
        $("#gt_combobox").css({width:gtObj.css("width"),left:gtOffset.left + "px", top:gtOffset.top + gtObj.outerHeight() + "px"}).slideDown("fast");
    }

    $("body").bind("mousedown", onBodyDown);
}

function hideMenu() {
    $("#gt_combobox").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}

function beforeClick(treeId, treeNode) {
    var check = (treeNode.level == "3");
    if (!check) alert("只能选择服务站...");
    return check;
}

