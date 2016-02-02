jQuery(function($) {
	if($("#purchaseId").val()) {
		$("#submenu-op-list0").addClass("active");
	} else {
		$("#submenu-op-add").addClass("active");
	}
	$("#menu-op").addClass("active");
	$("#menu-op").addClass("open");
		
	$('textarea[class*=autosize]').autosize({append: "\n"});
	$("#appendix").ace_file_input({
		no_file:'',
		btn_choose:'浏 览',
		btn_change:'更 改',
		droppable:false,
		onchange:null,
		thumbnail:false //| true | large
		//whitelist:'gif|png|jpg|jpeg'
		//blacklist:'exe|php'
	});
	
    $(document).on("blur",".numberbox",sumGoodsRprice);
    
    $("body").bind("mousedown", function(event) {
    	if (!(event.target.id == "purchase_goods_list")) {
    		sumGoodsRprice();
    	}
    });
    
    if($("#purchaseId").val()) {
    	sumGoodsRprice();
    	var userMobile = $("#purchaseUser").find("option:selected").attr("id");
    	$("#purchaseUserMobile").val(userMobile);
    }
	
	$("#purchaseUser").on("change",function() {
		var userMobile = $(this).find("option:selected").attr("id");
		var userName = $(this).find("option:selected").text();
		$("#purchaseUserName").val(userName);
		$("#purchaseUserMobile").val(userMobile);
	});
	
	$("#searchGoodsBtn").click(function() {
		searchGoods();
	});
	
	//申购日期选择框
	$("#dp_purchase_date").datetimepicker({
	    language: 'zh-CN',
		format: 'yyyy-mm-dd',
		weekStart: 1,
	    todayBtn:  1,
		autoclose: true,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	});
	
	//交货日期选择框
	$("#dp_delive_time").datetimepicker({
//		format: 'yyyy-mm-dd hh:ii:ss',
		format: 'yyyy-mm-dd hh:ii',
	    language: 'zh-CN',
		weekStart: 1,
	    todayBtn:  1,
		autoclose: true,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0
	});
	
	//批量选择时，单个checkbox选中改变背景颜色
	$(document).on("click", "#search_goods_table td input:checkbox", function() {
//		if($(this).prop("checked")){
//			$(this).closest("tr").addClass("row-click");
//			$(this).closest("tr").find("td").addClass("row-click");
//		} else {
//			$(this).closest("tr").removeClass("row-click");
//			$(this).closest("tr").find("td").removeClass("row-click");
//		}
		var checkedCount = $("#search_goods_table input:checkbox:checked").length;
		if(checkedCount > 0) {
			$("#choise_submit").removeClass("disabled");
		} else {
			$("#choise_submit").addClass("disabled");
		}
	});

	//单击查询商品列表行，改变背景颜色
	$(document).on("click", "#search_goods_table tbody tr", function() {
		var $choise_row = $(this);
		var choiseType = $("#choise_type").val();
		//单个选择
		if(choiseType == '0') {
			$("#search_goods_table tbody tr").removeClass("row-click");
			$("#search_goods_table tbody tr td").removeClass("row-click");
			$choise_row.addClass("row-click");
			$choise_row.find("td").addClass("row-click");
		}
		//批量选择
		if(choiseType == '1') {
//			var $checkbox = $choise_row.find("input:checkbox").eq(0);
//			if($checkbox.prop("checked")) {
//				$checkbox.prop("checked",false);
//			} else {
//				$checkbox.prop("checked",true);
//			}
//			console.info($checkbox.prop("checked"));
		}
	});

	//单个追加商品到申购单
	$(document).on("dblclick", "#search_goods_table tbody tr", function() {
		var choiseType = $("#choise_type").val();
		if(choiseType == '0') {
			var purchaseGoodsRowNo = $("#purchase_goods_rowno").val();
			var $choiseGoods = getChoiseGoods($(this));
			fillGoodsInfo(purchaseGoodsRowNo, $choiseGoods);
			$("#goods_choise_modal").modal('hide');
			focusNumbox();
		}
	});
		
	//商品选择列表checkbox全选，全不选
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
	
	$("#purchaseDept").on("change",function() {
		loadDeptUsers($(this).val());
	});
	
});

oTable = null;
	
//初始化商品类别树
function initGoodsTypeTree() {
	var setting = {
			callback: {
				onClick: function(){
					searchGoods();
				}
			}
	};

	var treeObj = $("#goodstype_tree");
	var zNodes;
	$.ajax({
        type: "POST",
        url: ROOT_PATH + "/console/sys/getGoodsTypeData.action",
        async : false,
        data: {
        		
        },
        dataType: "json",
        success: function(data) {
        	zNodes = data;
        }
	});
	
	$.fn.zTree.init(treeObj, setting, zNodes);
	
	treeObj.hover(function () {
		if (!treeObj.hasClass("showIcon")) {
			treeObj.addClass("showIcon");
		}
	}, function() {
		treeObj.removeClass("showIcon");
	});
}

//初始化商品列表
function initTable() {
	if(oTable == null) {
		oTable = $("#search_goods_table").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
//			"bProcessing": false,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/console/sys/searchGoods.action",  //异步请求地址
			"aoColumns" : [ 
			               {"sDefaultContent": "","sClass": "center","bSortable": false,"mRender": function(data, type, full) { 
			            	   return '<label class="position-relative">'+
			            	   '<input type="checkbox" class="ace" value="'+full.goodsId+'"/><span class="lbl"></span></label>';
			               }},
			               {"mDataProp":"goodsLsNo","mRender": function(data, type, full) { 
			            	   return '<label>'+ data + '</label><input type="hidden" value="'+full.goodsId+'"/>';
			               }},
			               {"mDataProp":"goodsName","mRender": function(data, type, full) { 
			            	   return '<label name="goodsName">'+ data + '</label>';
			               }},
			               {"mDataProp":"goodsType","mRender": function(data, type, full) { 
			            	   return '<label name="goodsType">'+ data + '</label>';
			               }},
			               {"mDataProp":"measurementUnitName","mRender": function(data, type, full) { 
			            	   return '<label name="measurementUnit">'+ data + '</label>';
			               }},
			               {"mDataProp":"referencePrice","mRender": function(data, type, full) { 
			            	   return '<label name="referencePrice">'+ data + '</label>';
			               }},
			               {"mDataProp":"goodsDesc"}
			               ],
			"aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveData,
			                                
		});
	} else {
//		oTable.fnClearTable(0); //清空数据
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
	var goodsSpell = $("#goodsSpell").val();
	
	var param = {"keyword":keyword,"goodsSpell":goodsSpell};
	var selectNodes = $.fn.zTree.getZTreeObj("goodstype_tree").getSelectedNodes();
	if(selectNodes.length > 0) {
		param.goodsTypeNo = selectNodes[0].key;
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
function searchGoods() {
	if(oTable){
		oTable.fnDraw();
	}
}

//计算商品估价总金额
function sumGoodsRprice() {
	var expectTotalPrice = 0;
	$("#purchase_goods_list tr").each(function(i){
		var $goodsItem = $(this);
		var goodsNumVal = $goodsItem.find("td").eq(4).find("input").eq(0).val();
		var goodsRpriceVal = $goodsItem.find("td").eq(5).html();
		if(goodsNumVal && goodsRpriceVal) {
			var goodsNum = parseFloat(goodsNumVal);
			var goodsRprice = parseFloat($goodsItem.find("td").eq(5).html());
			expectTotalPrice += goodsNum * goodsRprice;
		}
	});
	$("#expect_total_price").html(expectTotalPrice.toFixed(2));
}

//表单提交验证
function check() {
	var flag = true;
	$("form :input.required").each(function(){
       var val = $(this).val();
       if(!val) {
    	   $(this).addClass("required-tip");
    	   flag = false;
       }
    });
	if(!flag) {
		showErrorTips("信息填写错误，请核对修改",3000);
	}
	var delivery_address = $("#province").find("option:selected").text() 
							+ $("#city").find("option:selected").text() 
							+ $("#detail_address").val(); 
	$("#delivery_address").val(delivery_address);
	if(flag) {
		$("#form_action_btns").find("button").addClass("disabled");
		$("#form_action_btns").find("a").addClass("disabled");
	}
	return flag;
}

function showErrorTips(msg,time) {
	if($('#top_alert span').text().length>0){
        $('#top_alert').empty().append('<span>'+msg+'</span>');
        $('#top_alert').css('display','block');
    }else{
        $('.main-content').prepend('<div id="top_alert"><span>'+msg+'</span></div>');
    }
	$('#top_alert').fadeOut(time);
}

//单个选择商品
function choiseGoods(input_goodsName) {
	resetSelectGoodsForm();
	var $ele_tr = $(input_goodsName).parents("tr:eq(0)");
	$("#purchase_goods_rowno").val($ele_tr.find("td").eq(0).html());
	$("#choise_type").val('0');
	//隐藏列表第一列
	oTable.fnSetColumnVis(0,false);
	//隐藏底部批量操作的按钮
	$("#choise_goods_action").hide();
	$('#goods_choise_modal').modal('show');
}

//批量选择商品
function batchChoiseGoods() {
	resetSelectGoodsForm();
	$("#choise_type").val('1');
	//显示列表第一列
	oTable.fnSetColumnVis(0,true);
	$("#choise_goods_action").show();
	$('#goods_choise_modal').modal('show');
}

function impLastPurchaseGoods() {
	$.ajax({
        type: "GET",
        url: "getLastPurchaseGoods.action",
        dataType: "json",
        success: function(data) {
        	if(data && data.length > 0){
        		var $purchase_goods_list = $("#purchase_goods_list");
        		var $pgl_fr = $purchase_goods_list.find("tr").eq(0);
        		var fr_goodsName = $pgl_fr.find("input[name='goodsName']").eq(0).val();
        		var rowCount = $purchase_goods_list.find("tr").length;
        		if(!fr_goodsName) {
        			rowCount = rowCount - 1;
        			$pgl_fr.remove();
        		}
        		$.each(data,function(i,g) {
        			if(!existPurchaseGoods(g.goodsId)) {
        				var append_row_no = rowCount + (i + 1);
        				var append_row = createPurchaseGoodsRow(append_row_no);
        				$purchase_goods_list.append(append_row);
        				
        				var $choiseGoods = {};
        				$choiseGoods.id= g.goodsId;
        				$choiseGoods.name= g.goodsName;
        				$choiseGoods.type= g.goodsType;
        				$choiseGoods.unit= g.measurementUnit;
        				$choiseGoods.rprice= g.referencePrice;
        				$choiseGoods.num= g.purchaseNumber;
        				fillGoodsInfo(append_row_no,$choiseGoods);
        			}
        		});
        	} else {
        		alert("您还没有录入过申购单！");
        	}
        }
	});
	
	
}


//批量追加商品到申购单
function batchAppendGoods() {
	var $purchase_goods_list = $("#purchase_goods_list");
	var $pgl_fr = $purchase_goods_list.find("tr").eq(0);
	var fr_goodsName = $pgl_fr.find("input[name='goodsName']").eq(0).val();
	var rowCount = $purchase_goods_list.find("tr").length;
	if(!fr_goodsName) {
		rowCount = rowCount - 1;
		$pgl_fr.remove();
	}
	//遍历查询商品列表，添加选择的商品到申购商品列表
	$("#search_goods_table td input:checkbox:checked").each(function(i) {
		var goodsId = $(this).val();
		
		if(!existPurchaseGoods(goodsId)) {
			var $choise_row = $(this).closest('tr');
			rowCount = rowCount + 1;
			var append_row_no = rowCount;
			var append_row = createPurchaseGoodsRow(append_row_no);
			
			var $choiseGoods = getChoiseGoods($choise_row);
			$purchase_goods_list.append(append_row);
			fillGoodsInfo(append_row_no,$choiseGoods);
		}
	});
	$("#search_goods_table input:checkbox").removeAttr("checked");
	$("#choise_submit").addClass("disabled");
//	$("#goods_choise_modal").modal('hide');
	focusNumbox();
	alert("添加成功！");
}

function createPurchaseGoodsRow(append_row_no) {
	return '<tr>'
	+'<td class="center">' + append_row_no + '</td>'
	+'<td><input type="text" name="goodsName" class="input-sm required" onclick="choiseGoods(this)"/></td>'
	+'<td></td>'
	+'<td></td>'
	+'<td><input type="text" class="input-sm col-xs-3 fnumbox limited required" maxlength="4" name="purchaseDtl[' + (append_row_no - 1) +'].purchaseNumber"/></td>'
	+'<td></td>'
	+'<td><input type="text" class="input-sm limited" maxlength="500" name="purchaseDtl[' + (append_row_no - 1) +'].remark"/></td>'
	+'<td><input type="hidden" name="purchaseDtl[' + (append_row_no - 1) +'].goodsId"/>'
	+'<a href="javascript:void(0)" onclick="removeGoodsItem(this)">移除</a></td>'
	+'</tr>';
}

//获得选择商品对象
function getChoiseGoods(ele_goodsRow){
	var $choiseGoods = {};
	var $ele_td = ele_goodsRow.find("td");
	$choiseGoods.id = $ele_td.find("input[type=hidden]").eq(0).val();
	$choiseGoods.name = $ele_td.find("label[name='goodsName']").eq(0).html()
	$choiseGoods.type = $ele_td.find("label[name='goodsType']").eq(0).html()
	$choiseGoods.unit = $ele_td.find("label[name='measurementUnit']").eq(0).html()
	$choiseGoods.rprice = $ele_td.find("label[name='referencePrice']").eq(0).html()
	return $choiseGoods;
}

//是否已存在申购的商品
function existPurchaseGoods(goodsId) {
	var flag = false;
	$("#purchase_goods_list").find("tr").each(function(i){
		var purchase_goodsId = $(this).find("td input[type='hidden']").eq(0).val();
		if (goodsId == purchase_goodsId) {
			flag = true;
		}
	});
	return flag;
}

//填充商品信息
function fillGoodsInfo(purchaseGoodsRowNo,$choiseGoods) {
	var $ele_tr = $("#purchase_goods_list").find("tr").eq(purchaseGoodsRowNo - 1);
	$ele_tr.find("td").eq(1).find("input").val($choiseGoods.name);
	$ele_tr.find("td").eq(2).html($choiseGoods.type);
	$ele_tr.find("td").eq(3).html($choiseGoods.unit);
	$ele_tr.find("td").eq(4).find(":input").val($choiseGoods.num);
	$ele_tr.find("td").eq(5).html($choiseGoods.rprice);
	$ele_tr.find("td").eq(7).find("input").val($choiseGoods.id);
	
	$ele_tr.find("td").eq(1).find("input").removeClass("required-tip");
	$ele_tr.find("td").eq(4).find("input").removeClass("required-tip");
}

//移除申购单中的商品
function removeGoodsItem(tdObj) {
	var itemCount = $("#purchase_goods_list").find("tr").length;
	var $ele_tr = $(tdObj).parents("tr:eq(0)");
	if(itemCount == 1) {
		$ele_tr.find("td").eq(1).find("input").val('');
		$ele_tr.find("td").eq(2).html('');
		$ele_tr.find("td").eq(3).html('');
		$ele_tr.find("td").eq(4).find("input").val('');
		$ele_tr.find("td").eq(5).html('');
		$ele_tr.find("td").eq(6).find("input").val('');
		$ele_tr.find("td").eq(7).find("input").val('');
	} else {
		$(tdObj).parents("tr").remove();
	}
	$("#purchase_goods_list").find("tr").each(function(i) {
		$(this).find("td").eq(0).html(i+1);
	});
	sumGoodsRprice();
}

//重置商品选择窗口
function resetSelectGoodsForm() {
	initGoodsTypeTree();
	initTable();
	
	$("#search-form input").val("");
	$("#search_goods_table input:checkbox").removeAttr("checked");
//	$("#search_goods_list tr").removeClass("row-click");
	
	$.fn.zTree.getZTreeObj("goodstype_tree").cancelSelectedNode();
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

//加载部门员工
function loadDeptUsers(deptId) {
	if(deptId != "") {
		$.ajax({
	        type: "GET",
	        url: "getDeptUsers.action",
	        data: {
	        	"deptId" : deptId
	        },
	        dataType: "json",
	        success: function(data) {
	        	$("#purchaseUser").empty();
	        	$("#purchaseUser").append('<option value="">-请选择-</option>');
	        	$.each(data,function(i,u) {
	        		$("#purchaseUser").append('<option id="' + u.mobile +'" value="' + u.userId + '">' + u.realName + '</option>')
	        	});
	        }
		});
	}
}

/**
 * 保存或提交
 * @param status
 */
function saveOp(status) {
	$("#purchase_status").val(status);
}

/**
 * 取消
 */
function cancelOp() {
	if($("#purchaseId").val()) {
		window.location.href = ROOT_PATH + "/console/purchase/uncommitList.action";
	} else {
		window.location.href = ROOT_PATH + "/console/purchase/list.action";
	}
}