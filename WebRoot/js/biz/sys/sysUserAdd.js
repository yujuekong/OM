jQuery(function($) {
	$("#submenu-menu-advert-user").addClass("active");
	$("#menu-advert").addClass("active");
	$("#menu-advert").addClass("open");
	
	selectInitOrgTree();
	initTable();
	initDeptTable();
	
    LazyLoad.css([ROOT_PATH + "/css/cityStyle.css"], function () {
        LazyLoad.js([ROOT_PATH + "/js/cityScript.js"], function () {
            var test = new citySelector.cityInit("advertUserCity");
        });
    });
    
    var beforeShow = $("#beforeShow").val();
    if(beforeShow){
    	 $("#orgNameStr").attr("disabled",true);
    }
    
    $("#searchDeptBtn").click(function () {
        searchDept();
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
	
	//批量选择时，单个checkbox选中改变背景颜色
    $(document).on("click", "#search_goods_table td input:checkbox", function () {
        if ($(this).prop("checked")) {
            $(this).closest("tr").addClass("row-click");
            $(this).closest("tr").find("td").addClass("row-click");
        } else {
            $(this).closest("tr").removeClass("row-click");
            $(this).closest("tr").find("td").removeClass("row-click");
        }
        var checkedCount = $("#search_goods_table input:checkbox:checked").length;
        if (checkedCount > 0) {
            $("#choise_submit").removeClass("disabled");
        } else {
            $("#choise_submit").addClass("disabled");
        }
    });

});


//单个追加部门
$(document).on("dblclick", "#dept_list tbody tr", function() {
	var $choiseDept = getChoiseDept($(this));
	var a = $("#sysDeptId").val($choiseDept.deptId);
	$("#sysDeptName").val($choiseDept.deptName);
	$("#dept_choise_modal").modal('hide');
	//focusNumbox();
});


//获得选择部门对象
function getChoiseDept(ele_goodsRow){
	var $choiseDept = {}; 
	var $ele_td = ele_goodsRow.find("td");
	$choiseDept.deptId = $ele_td.find("input[type=hidden]").eq(0).val();
	$choiseDept.deptName = $ele_td.find("label[name='deptName']").eq(0).html();
	return $choiseDept;
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
function initTable() {
	if(uTable == null) {
		uTable = $("#search_goods_table").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/sys/sysUser/querySysRolePage.action",  //异步请求地址
			"aoColumns" : [ 
							{"sDefaultContent": "","sClass": "center","bSortable": false,"mRender": function(data, type, full) { 
								   return '<label class="position-relative">'+
								   '<input type="checkbox" class="ace" value="'+full.roleId+'"/><span class="lbl"></span></label>';
							}},
			               {"mDataProp":"roleName","sClass":"center","mRender": function(data, type, full) { 
			            	   return '<label name="roleName">'+ data + '</label>';
			               }},
			               {"mDataProp":"roleDesc","sClass":"center","mRender":function(data,type,full){
			            	   return '<label name="roleDesc">'+ data + '</label>';
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
			                 {"bSortable": false, "aTargets": [0, 1, 2 ]},  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 {
			                     "sDefaultContent": '', "aTargets": ['_all'],
			                 }
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
	var orgNameStr = $("#orgNameStr").val();
	if(orgNameStr){
		param.isOrg = "1";
	}
	if(orgId){
		param.orgId = orgId;
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

oTable = null;
function initDeptTable() {
	if(oTable == null) {
		oTable = $("#dept_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,//是否显示一个每页长度的选择条（需要分页器支持）
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/sys/sysDept/querySysDeptPage.action",  //异步请求地址
			"aoColumns" : [ 
							{"mDataProp":"deptName","sClass":"center","mRender": function(data, type, full) { 
								   return '<label name="deptName">'+ data + '</label><input type="hidden" value="'+full.deptId+'"/>';
							}},
//			               {"mDataProp":"sysOrgDict.dictName","sClass":"center"},
			               {"mDataProp":"realName","sClass":"center"},			               
			               {"mDataProp":"deptTel","sClass":"center"}
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
			                 {"bSortable": false, "aTargets": [0, 1, 2]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveDeptData,
			                                
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
function retrieveDeptData(sSource, aoData, fnCallback) { 
	//商品名称或编号
	var searchDeptBtn = $("#keyword").val();
	var param = {"keyword":searchDeptBtn};

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

function searchDept(){
	if(oTable){
		oTable.fnDraw();
	}
}

//单个选择用户
function choiseRole(input_Name) {
	resetSelectRole();
	$('#role_choise_modal').modal('show');
}  
//加载用户数据
function resetSelectRole(){
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
//                beforeClick: beforeClick,
                onClick : function(event, treeId, treeNode){
                    $("#dictId").val(treeNode.id);
                    $("#dictPid").val(treeNode.pid);
                    $("#dictPPid").val(treeNode.getParentNode().pid);
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


function searchDevice() {
	if(oTable){
		oTable.fnDraw();
	}
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

//选择部门
function choiseDept(){
	initDeptTable();
//	initOrgTree();
	$("#dept_choise_modal").modal("show");
}

//非空判断
function checkBlank(){
	var result = true;
	var sysUserName = $("#sysUserName").val();  //账号
	var telnumber = $("#telnumber").val();   //电话号码
	var advertUserCity = $("#advertUserCity").val();  //所属城市
	var realUserName = $("#realUserName").val();  //用户真实姓名
	if(!sysUserName){
		$("#nameError").html("<div></div><font color='red'>账号不能为空!</font>");
		result = false;
	}else if(!realUserName){
		$("#realNameError").html("<div></div><font color='red'>用户真实姓名不能为空!</font>");
		result = false;
	}else if(realUserName.length > 4){
		$("#realNameError").html("<div></div><font color='red'>用户真实姓名最大长度为4!</font>");
		result = false;
	}else if(!telnumber){
		$("#telError").html("<div></div><font color='red'>电话号码不能为空!</font>");
		result = false;
	}else if(!advertUserCity){
		alert("所属城市不能为空！");
		result = false;
	}
	return result;
}

function checkRealName(){
	var realUserName = $("#realUserName").val();  //用户真实姓名
	if(realUserName){
		$("#realNameError").html("<div></div>");
	}
	else{
		$("#realNameError").html("<div></div><font color='red'>用户真实姓名不能为空!</font>");
	}
}

//账户唯一性判断
function checkUserOnly(){
	var username = $("#sysUserName").val();
	if(username && username.length < 20){
		$.ajax({
			url: ROOT_PATH + '/view/sys/sysUser/checkUserName.action',
			type:'POST',
			data: {
					"username":username
	        },
			dataType:'json',
			success:function(data){					
				if(data){
					$("#nameError").html("<div></div><font color='#00AA00'>账号可用!</font>");
				}
				else{
					$("#nameError").html("<div></div><font color='red'>账号已被注册，请重新输入!</font>");
					$("#sysUserName").val("");
				}
			}
		});
	}
	else if(username && username.length >= 20){
		$("#nameError").html("<div></div><font color='red'>账号超过最大长度!</font>");
		$("#sysUserName").val("");
	}
	else{
		$("#nameError").html("<div></div><font color='red'>用户账号不能为空!</font>");
		$("#sysUserName").val("");
	}
}

//联系电话唯一性判断
function checkTelOnly(){
	var telnumber = $("#telnumber").val();
	if(telnumber){
		if(!isNaN(telnumber)){
			if(telnumber && telnumber.length < 20){
				$.ajax({
					url: ROOT_PATH + '/view/sys/sysUser/checkTelOnly.action',
					type:'POST',
					data: {
							"telnumber":telnumber
			        },
					dataType:'json',
					success:function(data){					
						if(data){
							$("#telError").html("<div></div><font color='#00AA00'>电话号码可用!</font>");
						}
						else{
							$("#telError").html("<div></div><font color='red'>号码已被注册，请重新输入!</font>");
							$("#telnumber").val("");
						}
					}
				});
			}
			else{
				$("#telError").html("<div></div><font color='red'>电话号码超过最大长度!</font>");
				$("#telnumber").val("");
			}
		}
		else{
			$("#telError").html("<div></div><font color='red'>电话号码必须为数字!</font>");
			$("#telnumber").val("");
		}
	}
	else{
		$("#telError").html("<div></div><font color='red'>电话号码不能为空!</font>");
		$("#telnumber").val("");
	}

}

//批量选择角色
function choiseRoleBatch() {
  resetSelectGoodsForm();
  $("#choise_type").val('1');
  //显示列表第一列
  uTable.fnSetColumnVis(0, true);
  $("#choise_goods_action").show();
  $('#role_choise_modal').modal('show');
}

//批量追加站点到表单
function batchAppendRole() {
  var $purchase_goods_list = $("#purchase_goods_list");
  var $pgl_fr = $purchase_goods_list.find("tr").eq(0);  //找到第一行
  var fr_goodsName = $pgl_fr.find("td").eq(1).find("input[type='text']").val(); //取第一列单元格goodsname的值
  var rowCount = $purchase_goods_list.find("tr").length; //得到行数
  if (!fr_goodsName) {
      rowCount = rowCount - 1;
      $pgl_fr.remove();
  }
  //遍历查询站点列表，添加选择的站点到表单显示页面
  $("#search_goods_table td input:checkbox:checked").each(function () {
      var goodsId = $(this).val();
      if (!existPurchaseGoods(goodsId)) {
          var $choise_row = $(this).closest('tr');
          rowCount = rowCount + 1;
          var append_row_no = rowCount;
          var append_row = createPurchaseGoodsRow(append_row_no);
          var $choiseGoods = getChoiseGoods($choise_row);
          $purchase_goods_list.append(append_row);
          fillGoodsInfo(append_row_no, $choiseGoods);
      }
  });
//  $("#choise_submit").addClass("disabled");
  $("#role_choise_modal").modal("hide");
  focusNumbox();
  alert("添加成功!");

}
//批量添加追加行数
function createPurchaseGoodsRow(append_row_no) {
  return '<tr>'
      + '<td class="center">' + append_row_no + '</td>'
      + '<td class="center"><input type="text" id="roleRealName" value="${pg.roleName }" style="border-left: 0;border-right: 0;border-top: 0;border-bottom: 0px;color:#222222;font-size:13px;"></td>'
      + '<td class="center"></td>'
      + '<td class="center"><input type="hidden" id="roleId" class="center" value="${pg.roleId }" name="roleInfoList[' + (append_row_no - 1) + '].roleId"/>'
      + '<a href="javascript:void(0)" onclick="removeGoodsItem(this)">移除</a></td>'
      + '</tr>';
}

//获得选择角色各项值
function getChoiseGoods(ele_goodsRow) {
  var $choiseGoods = {};
  var $ele_td = ele_goodsRow.find("td");
  $choiseGoods.roleId = $ele_td.find("input[type=checkbox]").eq(0).val();
  $choiseGoods.roleName = $ele_td.find("label[name='roleName']").eq(0).html();
  $choiseGoods.roleDesc = $ele_td.find("label[name='roleDesc']").eq(0).html();
  return $choiseGoods;
}

//是否已存在 已选中的站点
function existPurchaseGoods(goodsId) {
  var flag = false;
  $("#purchase_goods_list").find("tr").each(function () {
      var purchase_goodsId = $(this).find("td").eq(3).find("input[type=hidden]").val();
      if (goodsId == purchase_goodsId) {
          flag = true;
      }
  });
  return flag;
}

//填充站点信息
function fillGoodsInfo(purchaseGoodsRowNo, $choiseGoods) {
  var $ele_tr = $("#purchase_goods_list").find("tr").eq(purchaseGoodsRowNo - 1);
//  $ele_tr.find("td").eq(1).html($choiseGoods.roleName);
  $ele_tr.find("td").eq(1).find("input[type=text]").val($choiseGoods.roleName);
  $ele_tr.find("td").eq(2).html( $choiseGoods.roleDesc);
  $ele_tr.find("td").eq(3).find("input[type=hidden]").val($choiseGoods.roleId);
}

//移除选择的站点信息
function removeGoodsItem(tdObj) {
  var itemCount = $("#purchase_goods_list").find("tr").length;
  var $ele_tr = $(tdObj).parents("tr:eq(0)");
  if (itemCount == 1) {
      $ele_tr.find("td").eq(1).html("");
      $ele_tr.find("td").eq(2).html("");
      $ele_tr.find("td").eq(3).find("input").val('');
  } else {
      $(tdObj).parents("tr").remove();
  }
  $("#purchase_goods_list").find("tr").each(function(i){
	  var s = "roleInfoList[ " + i + " ].roleId";
	  $(this).find("input[type=hidden]").eq(0).attr("name",s);
  });
  $("#purchase_goods_list").find("tr").each(function (i) {
      $(this).find("td").eq(0).html(i + 1);
  });

}

//重置站点选择窗口
function resetSelectGoodsForm() {
  initTable();
  $("#search-form input").val("");
  $("#search_goods_table input:checkbox").removeAttr("checked");

}

