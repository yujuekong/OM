jQuery(function ($) {
    $("#submenu-menu-advert-user").addClass("active");
    $("#menu-advert").addClass("active");
    $("#menu-advert").addClass("open");
    initTable();
    $("#type_keyword").keyup(function () {
        initTable();
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
	
	//批量选择时，单个checkbox选中改变背景颜色
    $(document).on("click", "#deviceInfo_list_batch td input:checkbox", function () {
        if ($(this).prop("checked")) {
            $(this).closest("tr").addClass("row-click");
            $(this).closest("tr").find("td").addClass("row-click");
        } else {
            $(this).closest("tr").removeClass("row-click");
            $(this).closest("tr").find("td").removeClass("row-click");
        }
        var checkedCount = $("#deviceInfo_list_batch input:checkbox:checked").length;
        if (checkedCount > 0) {
            $("#choise_submit").removeClass("disabled");
        } else {
            $("#choise_submit").addClass("disabled");
        }
    });
});

oTable = null;

function initTable() {
    if (oTable == null) {
        oTable = $("#pageGoods_list").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/goods/goodsInfo/queryPageGoodsInfos.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "goodsSort"},
                {"mDataProp": "goodsName"},
                {"mDataProp": "goodsBarCode"},
                {"mDataProp": "goodsPrice"},
                {
                    "mDataProp": "goodsStatus", "mRender": function (data, type, full) {
                    var goodsStatus = '';
                    if (data == '0') {
                        goodsStatus = '<span style="cursor:pointer;"  class="label label-sm label-success">正常</span>';
                    }
                    else if (data == '1') {
                        goodsStatus = '<span style="cursor:pointer;"  class="label label-sm label-error">禁用</span>';
                    }

                    return goodsStatus;
                }
                },
                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="modifyGoodsInfoSort(\'' + full.goodsId + '\')" class="btn btn-xs btn-info" title="修改排序"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
                        '<button onclick="deleteGoodsInfoSort(\'' + full.goodsId + '\')" class="btn btn-xs btn-danger" title="删除"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
                    actionDiv += '</div>';
                    return actionDiv;
                }
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
    var keyword = $("#type_keyword").val();
    var param = {"keyword": keyword};
    for (var i = 0; i < aoData.length; i++) {
        var _aoData = aoData[i];
        if (_aoData.name == "iDisplayStart") {
            /*开始页数*/
            param.iDisplayStart = _aoData.value;
        } else if (_aoData.name == "iDisplayLength") {
            /*记录条数*/
            param.iDisplayLength = _aoData.value;
        } else if (_aoData.name == "sEcho") {
            /*操作次数*/
            param.sEcho = _aoData.value;
        }
    }
    //提交访问
    $.ajax({
        type: "POST",
        url: sSource,
        dataType: "json",
        data: param, // 以json格式传递  
        success: function (resp) {
            fnCallback(resp);
//            removeMask($('.goods-container'));
        }
    });
}

aTable = null;
function initTableBatch() {
	if(aTable == null) {
		aTable = $("#deviceInfo_list_batch").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/goods/goodsInfo/queryAppGoodsPage.action",  //异步请求地址
			"aoColumns" : [ 
							{"sDefaultContent": "","sClass": "center","bSortable": false,"mRender": function(data, type, full) { 
								   return '<label class="position-relative">'+
								   '<input type="checkbox" class="ace" name="goodsId" value="'+full.goodsId+'"/><span class="lbl"></span></label>';
							}},
			               {"mDataProp":"goodsName","sClass":"center"},
			               {"mDataProp":"goodsBarCode","sClass":"center"},
			               {"mDataProp":"goodsPrice","sClass":"center"},
			               {"mDataProp":"goodsStatus","sClass":"center","mRender": function(data, type, full) { 
			            	   var deviceStatus = '';
			                    if (data == '0') {
			                    	deviceStatus ='<span class="label label-sm label-success">正常</span>';
			                    }
			                    else if (data == '1') {
			                    	deviceStatus ='<span class="label label-sm label-error">禁用</span>';
			                    }
			                    return deviceStatus;
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 ],
			"fnServerData": retrieveData2,
		});
	} else {
		aTable.fnDraw(); //重新加载数据
	}
}

/**
* sSource   查询链接
* aoData    参数
* fnCallback 返回数据填充方法
*/
function retrieveData2(sSource, aoData, fnCallback) { 
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
        }    
    });    
}

//搜索商品
function searchAdvertStatus() {
    if (oTable) {
        oTable.fnDraw();
    }
}

//删除商品
function deleteGoodsInfoSort(id) {
    if (confirm("确定删除？")) {
        window.location.href = ROOT_PATH + "/view/goods/goodsInfo/deleteGoodsInfoSort.action?goodsInfoId=" + id;
    }
}

function modifyGoodsInfoSort(id){
	if(confirm("确定将该商品显示在第一个？")){
		window.location.href = ROOT_PATH + "/view/goods/goodsInfo/modifyGoodsInfoSort.action?goodsInfoId=" + id;
	}
}

//搜索广告内容
function searchAdvertInfo() {
    var advertInfoTitle = $("#advertInfoDt").val();
    window.location.href = ROOT_PATH + "/view/goods/goodsInfo/searchAdvertInfo.action?advertInfoTitle=" + advertInfoTitle;
}

//添加需要显示在app上的商品
function addGoodsInfo(){
	initTableBatch();
	$("#devices_choise_modal_batch").modal("show");
	$("#choise_submit").addClass("disabled");
}

//添加已选择的商品
function batchAppendGoods(){
	var goodsIdList = getTable($('#deviceInfo_list_batch'));
    if (goodsIdList) {
        if (confirm("确定添加?")) {
            var params = {
                "goodsIdList": goodsIdList,
            };
            $.ajax({
                data: params,
                type: "post",
                url: ROOT_PATH + "/view/goods/goodsInfo/addAppGoods.action",
                success: function (data) {
                    $("#devices_choise_modal_batch").modal("hide");
                    initTable();
                }
            });
        }
    }
}

//获取列表JSON
function getTable($table) {
    if ($("#deviceInfo_list_batch td").html()) {
        var tableArray = [], trString = '{', tableString = '';
        $("#deviceInfo_list_batch td input:checkbox:checked").each(function (i) {
        	tableArray.push('{"' + this.name + '":"' + $(this).val() + '"}');
            });
        if (trString) {
            tableString = '[' + tableArray.join(',') + ']';
            return tableString;
        } else {
            return '';
        }
    } else {
        alert('请选择商品');
    }
}
