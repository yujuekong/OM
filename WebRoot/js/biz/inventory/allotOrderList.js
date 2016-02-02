jQuery(function ($) {
    $("#submenu-menu-advert-user").addClass("active");
    $("#menu-advert").addClass("active");
    $("#menu-advert").addClass("open");
    initTable();

    $("#searchGoodsBtn").click(function () {
        searchAdvertStatus();
    });
    $("#allot_keyword").keyup(function () {
        initTable();
    });
    
    $("#dp_quoteStartDate").datetimepicker({
		minView: "month",
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
    
    $("#dp_quoteEndDate").datetimepicker({
		minView: "month",
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
});

var uTable = null;
function initGoodsTable(allotId) {
    if (uTable == null) {
        uTable = $("#search_goods_table").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/inventory/allotOrder/queryGoodsInfoPage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "goodsName", "sClass": "center"},
                {"mDataProp": "goodsTypeName", "sClass": "center"},
                {"mDataProp": "goodsBarCode", "sClass": "center"},
                {"mDataProp": "expectNumber", "sClass": "center"},
                {"mDataProp": "deliveryCount", "sClass": "center"},
                {
                    "mDataProp": "goodsStatus", "sClass": "center", "mRender": function (data, type, full) {
                    var goodsStatus = '';
                    if (data == '0') {
                        goodsStatus = '<span style="cursor:pointer;"  class="label label-sm label-success">正常</span>';
                    }
                    else if (data == '1') {
                        goodsStatus = '<span style="cursor:pointer;"  class="label label-sm label-error">禁用</span>';
                    }

                    return goodsStatus;
                }
                }
            ],
            "language": {
                "sProcessing": "正在加载中......",
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "没有数据！",
                "sEmptyTable": "表中无数据存在！",
                "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
                "sInfoEmpty": "显示0到0条记录",
                "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast": "末页"
                }
            },
            "aoColumnDefs": [
                {
                    "bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5]
                },  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
                {
                    "sDefaultContent": '', "aTargets": [0, 1, 2, 3, 4, 5]
                }
            ],
            "fnServerData": retrieveGoodsData,
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
function retrieveGoodsData(sSource, aoData, fnCallback) {
    //商品名称或编号
    var keyword = $("#type_keyword").val();
    var allotStatus = $("#allotStatus").val();
    var allotId = $("#allotId").val();
    var param = {
        "keyword": keyword,
        "allotStatus":allotStatus
    };
    param.allotId = allotId;
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
        }
    });
}

oTable = null;
function initTable() {
    if (oTable == null) {
        oTable = $("#allot_order_list").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/inventory/allotOrder/queryTransferOrderPage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "allotNo"},
                {"mDataProp": "startStorateWarehouse.warehouseName"},
                {"mDataProp": "endStorateWarehouse.warehouseName"},
                {"mDataProp": "createDate"},
                {
                    "mDataProp": "allotStatus", "mRender": function (data, type, full) {
                    var allotStatus = '';
                    if (data == '0') {
                        allotStatus = '<span style="cursor:pointer;"  class="label label-sm label-success" >已审核</span>';
                    }
                    else if (data == '1') {
                        allotStatus = '<span style="cursor:pointer;"  class="label label-sm label-error" onclick="changeStatus(\'' + full.allotId + '\')">未审核</span>';
                    }
                    else if (data == '2') {
                        allotStatus = '<span style="cursor:pointer;"  class="label label-sm label-success">已通过</span>';
                    }
                    return allotStatus;
                }
                },
                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="allotOrderDet(\'' + full.allotId + '\',\'' + full.allotStatus + '\')" class="btn btn-xs btn-success" style="text-align: center"><i style="text-align: center" class="ace-icon fa fa-search-plus bigger-120"></i>调拨明细</button>';
                    actionDiv += '</div>';
                    return actionDiv;
                }
                },
                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="queryAllotDt(\'' + full.allotId + '\')" class="btn btn-xs btn-success" title="查询"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
                        '<button onclick="addOrModifyAllotOrder(\'' + full.allotId + '\',\'' + full.allotStatus + '\')" class="btn btn-xs btn-info" title="修改"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
                        '<button onclick="deleteAllotOrder(\'' + full.allotId + '\',\'' + full.allotStatus + '\')" class="btn btn-xs btn-danger" title="删除"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
                {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
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
    var allot_keyword = $("#allot_keyword").val();
    var param = {"keyword": allot_keyword};
    //有效开始日期
    var startDate = $("#dp_quoteStartDate").val();
    //有效终止日期
    var endDate = $("#dp_quoteEndDate").val();
    var allotOrderStatus = $("#orderStatus").val();
    param.allotOrderStatus = allotOrderStatus;
    param.startDate = startDate;
    param.endDate = endDate;
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
        }
    });
}

function searchGoods() {
    if (uTable) {
        uTable.fnDraw();
    }
}

//初始化商品树
function initOrgTree() {
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        view: {
            dblClickExpand: false
        },
        callback: {
            onClick: function () {
                searchGoods();
            }
        }
    };

    var treeObj = $("#gt_tree");
    var zNodes;

    $.ajax({
        url: ROOT_PATH + '/view/goods/goodsInfo/getMulSubGoodsTypeDataByPcode.action',
        type: 'POST',
        data: {"dictPcode": "AL_POSITION", "dictLevel": 4},
        dataType: 'json',
        success: function (data) {
            zNodes = data;
            $.fn.zTree.init($("#gt_tree"), setting, zNodes);
        }
    });

    treeObj.hover(function () {
        if (!treeObj.hasClass("showIcon")) {
            treeObj.addClass("showIcon");
        }
    }, function () {
        treeObj.removeClass("showIcon");
    });
}

//搜索商品
function searchAllotStatus() {
    if (oTable) {
        oTable.fnDraw();
    }
}

function preAddOrModifyOrderIn() {

    window.location.href = ROOT_PATH + "/view/inventory/allotOrder/updateOrModifyAllotOrder.action";
}

//查询调拨内容详情
function queryAllotDt(id) {

    window.location.href = ROOT_PATH + "/view/inventory/allotOrder/queryAllotOrderDt.action?allotId=" + id;
}

//删除调拨单
function deleteAllotOrder(id, status) {
    if (status == '0') {
        alert("订单已审核，不能删除！");
        return;
    }
    if (confirm("确定删除？")) {
        window.location.href = ROOT_PATH + "/view/inventory/allotOrder/deleteAllotOrder.action?allotId=" + id;
    }
}

//增加和修改调拨单
function addOrModifyAllotOrder(id, status) {
    if (status == "0") {
        alert("订单已审核，不能修改！");
        return;
    }
    window.location.href = ROOT_PATH + "/view/inventory/allotOrder/updateOrModifyAllotOrder.action?allotId=" + id;
}

//查询调拨单明细
function allotOrderDet(id,allotStatus) {
    $("#allotId").val(id);
    $("#allotStatus").val(allotStatus);
    resectAllotOrder();
    $("#goods_choise_modal").modal("show");
}

function resectAllotOrder() {
    initGoodsTable();
    initOrgTree();
}
//根据状态搜索调拨单
function searchOrderStatus() {
    if (oTable) {
        oTable.fnDraw();
    }
}
//改变调拨单状态
function changeStatus(id) {
    if (confirm("确定提交审核？")) {
        window.location.href = ROOT_PATH + "/view/inventory/allotOrder/changeAllotOrderStatus.action?allotId=" + id;
    }
}

function dateFind(){
	if($("#dp_quoteStartDate").val() != "" && $("#dp_quoteEndDate").val() != ""){
		initTable();
	}
}

/**
 * 快捷搜索
 * @param type 搜索类型
 */
function quickSearch(type) {
    var startDate = "";
    var endDate = "";
    if (type == 1) { //本周
        startDate = DateUtil.dateToStr('yyyy-MM-dd', DateUtil.weekStartDate());
        endDate = DateUtil.dateToStr('yyyy-MM-dd', DateUtil.weekEndDate());
    } else if (type == 2) { //上周
        startDate = DateUtil.dateToStr('yyyy-MM-dd', DateUtil.lastWeekStartDate());
        endDate = DateUtil.dateToStr('yyyy-MM-dd', DateUtil.lastWeekEndDate());
    } else if (type == 3) { //本月
        startDate = DateUtil.dateToStr('yyyy-MM-dd', DateUtil.monthStartDate());
        endDate = DateUtil.dateToStr('yyyy-MM-dd', DateUtil.monthEndDate());
    } else if (type == 4) { //上月
        startDate = DateUtil.dateToStr('yyyy-MM-dd', DateUtil.lastMonthStartDate());
        endDate = DateUtil.dateToStr('yyyy-MM-dd', DateUtil.lastMonthEndDate());
    }
    $("#dp_quoteStartDate").val(startDate);
    $("#dp_quoteEndDate").val(endDate);
    initTable();
}


