jQuery(function ($) {
    $("#submenu-menu-advert-user").addClass("active");
    $("#menu-advert").addClass("active");
    $("#menu-advert").addClass("open");

    initTable();
    $("#searchGoodsBtn").click(function () {
        searchGoods();
    });
    $("#out_keyword").keyup(function () {
        initTable();
    });
    $("#dp_quoteStartDate").datetimepicker({
        minView: "month",
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
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
        todayBtn: 1,
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
});

var oTable = null;

function initTable() {
    if (oTable == null) {
        oTable = $("#out_list").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            "bFilter": false,//过滤功能
            "bInfo": true,//页脚信息
            //"sPaginationType": "two_button",
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/inventory/deliveryOrder/queryDeliveryOrderPage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "deliveryNo"},
                {"mDataProp": "storageWarehouse.warehouseName"},
                {"mDataProp": "createDate"},
                {"mDataProp": "deliveryDate"},
                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv;
                    actionDiv = '<div>' +
                        '<button onclick="queryDispatchingList(\'' + full.deliveryOrderId + '\')" class="btn btn-xs btn-success bigger-110" style="text-align: center"><i style="text-align: center" class="ace-icon fa fa-search-plus bigger-100">分配配送</i></button>' +
                        '&nbsp;&nbsp;&nbsp;&nbsp;' +
                        '<button onclick="agentDet(\'' + full.deliveryOrderId + '\')" class="btn btn-xs btn-success bigger-110" style="text-align: center"><i style="text-align: center" class="ace-icon fa fa-search-plus bigger-100">商品明细</i></button>';
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
            "aoColumnDefs": [
                {
                    "bSortable": false, "aTargets": [0, 1, 2, 3, 4]
                },
                {
                    sDefaultContent: '',
                    aTargets: ['_all']
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
    var keyword = $("#out_keyword").val();
    //用户所属公司
    var orgId = $("#orgId").val();

    var deliveryOrderId = $("#deliveryOrderId").val();
    //有效起始日期
    var startDate = $("#dp_quoteStartDate").val();
    //有效终止日期
    var endDate = $("#dp_quoteEndDate").val();
    var param = {
        "keyword": keyword,
        "deliveryOrderId": deliveryOrderId,
        "startDate": startDate,
        "endDate": endDate,
        "orgId": orgId,
        "agent": "agent"
    };

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

//搜索商品
function searchGoods() {
    if (oTable) {
        oTable.fnDraw();
    }
}

//出库单明细
function orderInDet(id) {
    $("#deliveryOrderId").val(id);
    resetDeliveryOrder();
    $('#out_dtl_modal').modal('show');
}

//加载供应商数据
function resetDeliveryOrder() {
    initDeliveryOrderTable();
}

//初始化站点列表
var gTable = null;
function initDeliveryOrderTable() {
    if (gTable == null) {
        gTable = $("#out_dtl_table").dataTable({
            "bAutoWidth": false,
            "bLengthChange": true,
            "bProcessing": true,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/inventory/deliveryDetail/queryDeliveryDetailPage.action", //异步请求地址 ：查询站点信息
            "bStateSave": true,
            "aoColumns": [
                {"mDataProp": "storageDeliveryOrder.deliveryNo"},
                {"mDataProp": "goodsInfo.goodsName"},
                {"mDataProp": "deviceInfo.deviceName"},
                {"mDataProp": "deliveryCount"},
                {"mDataProp": "remark"},
            ],
            "aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
                {
                    "bSortable": false, "aTargets": [0, 1, 2, 3, 4]
                },  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
            ],
            "fnServerData": retrieveData,

        });
    } else {
        gTable.fnDraw(); //重新加载数据
    }
}

//加载供应商数据
function resetAgentOrder() {
    initAgentTable();
}

//初始化站点列表
var aTable = null;
function initAgentTable() {
    if (aTable == null) {
        aTable = $("#agent_dtl_table").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": true,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            "bFilter": false,//过滤功能
            "bInfo": true,//页脚信息
            //"sPaginationType": "two_button",
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/inventory/deliveryAgent/queryDeliveryAgentPage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "deliveryNo"},
                {"mDataProp": "goodsName"},
                {"mDataProp": "requestCount"},
                {"mDataProp": "realiGoodsNumber"},
                {"mDataProp": "remark"},
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
            "aoColumnDefs": [{//和aoColums类似，但他可以给指定列附近爱属性
                "bSortable": false, "aTargets": [0, 1, 2, 3, 4],//这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
            }],
            "fnServerData": retrieveData,
        });


    } else {
        aTable.fnDraw(); //重新加载数据
    }

}

//处理明细
function agentDet(id) {
    $("#deliveryOrderId").val(id);
    resetAgentOrder();
    $('#agent_dtl_modal').modal('show');
}


//分配配送
function queryDispatchingList(id) {
    window.location.href = ROOT_PATH + "/view/inventory/deliveryAgent/queryDispatchingList.action?deliveryOrderId=" + id;
}

//查询详情
function queryDeliveryOrderById(id) {
    window.location.href = ROOT_PATH + "/view/inventory/deliveryOrder/queryDeliveryOrderById.action?deliveryOrderId=" + id;
}
//增加或修改
function preAddOrModifyDeliveryOrder(id) {
    alert("暂时停用");
    return false;
    window.location.href = ROOT_PATH + "/view/inventory/deliveryOrder/preAddOrModifyDeliveryOrder.action?deliveryOrderId=" + id;
}
//删除
function deleteDeliveryOrder(id) {
    if (confirm("确定删除?")) {
        window.location.href = ROOT_PATH + "/view/inventory/deliveryOrder/deleteDeliveryOrder.action?deliveryOrderId=" + id;
    }
}
//修改出库单状态
function modifyDeliveryStatus(id) {
    var param = {
        "deliveryOrderId": id,
    };
    if (window.confirm("确定修改为已出库")) {
        $.ajax({
            type: "post",
            url: ROOT_PATH + "/view/inventory/deliveryOrder/modifyDeliveryStatus.action",
            data: param,
            success: function (data) {
                if (!data) {
                    alert("系统错误，请联系管理员!");
                }
                initTable();
            }

        });
    }
}
//修改处理状态
function modifyIsAgentStatus(state, id) {
    var param = {
        "deliveryOrderId": id,
    };
    var msg;
    if (state == '0') {
        msg = "确定修改为未完成?";
    } else if (state == '1') {
        msg = "确定修改为已完成?"
    }
    if (window.confirm(msg)) {
        $.ajax({
            type: "post",
            url: ROOT_PATH + "/view/inventory/deliveryAgent/modifyIsAgentStatus.action",
            data: param,
            success: function (data) {
                if (!data) {
                    alert("系统错误，请联系管理员!");
                }
                initTable();
            }

        });
    }
}
function dateFind() {
    if ($("#dp_quoteStartDate").val() != "" && $("#dp_quoteEndDate").val() != "") {
        initTable();
    }
}
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