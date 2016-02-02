jQuery(function ($) {
    initTable();
    $("#searchGoodsBtn").click(function () {
        searchGoods();
    });
    $("#in_keyword").keyup(function () {
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
var oTable = null;
function initTable() {
    if (oTable == null) {
        oTable = $("#order_in_list").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            "bFilter": false,//过滤功能
            "bInfo": true,//页脚信息
            //"sPaginationType": "two_button",
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/pay/payCash/queryPayCashPage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "1"},
                {"mDataProp": "2"},
                {"mDataProp": "3"},
                {"mDataProp": "4"},
                {"mDataProp": "5"},
                {"mDataProp": "6"},
                {
                    "mDataProp": "7", "sClass": "center", "mRender": function (data, type, full) {
                    var cashStatus = '';
                    if (data == '1') {
                        cashStatus = '<button class="btn btn-minier btn-success" type="button" disabled>暂定</button>';
                    }
                    else if (data == '0') {
                        cashStatus = '<button class="btn btn-minier btn-info " type="button" >暂定</button>';
                    }
                    return cashStatus;
                }
                },
                {"mDataProp": "8"},
//                {
//                    "sDefaultContent": "", "mRender": function (data, type, full) {
//                    var actionDiv = '';
//                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
//                        //'<button onclick="queryPayCashById(\'' + full[0] + '\')" class="btn btn-xs btn-success"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
//                            //'<button onclick="preAddOrModifyOrderIn(\'' + full.warehousingEntryId + '\')" class="btn btn-xs btn-info"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
//                        //'<button onclick="deletePayCash(\'' + full[0] + '\')" class="btn btn-xs btn-danger"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
//                    actionDiv += '</div>';
//                    return actionDiv;
//                }
//                },
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
                {
                    "bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6 ,7]
                },
                {
                    "sDefaultContent": '', "aTargets": [0, 1, 2, 3, 4, 5, 6, 7]
                }
            ],
            "fnServerData": retrieveData,
        });


    } else {
        oTable.fnDraw(); //重新加载数据
    }

}
function queryPayCashById(id) {
    window.location.href = ROOT_PATH + "/view/pay/payInfo/queryPayCashById.action?cashId=" + id;
}

function deletePayCash(id) {
    if (confirm("确定删除?")) {
        var param = {
            "cashId": id
        };
        $.ajax({
            type: "post",
            data: param,
            url: ROOT_PATH + "/view/pay/payInfo/deletePayCash.action",
            success: function (data) {
                if (!data) {
                    alert("系统错误，请联系管理员!");
                }
                initTable();
            }


        });
    }
}

/**
 * sSource   查询链接
 * aoData    参数
 * fnCallback 返回数据填充方法
 */
function retrieveData(sSource, aoData, fnCallback) {
    //商品名称或编号
    var keyword = $("#in_keyword").val();
    //入库单ID
    var warehousingId = $("#warehousingId").val();
    //有效起始日期
    var startDate = $("#dp_quoteStartDate").val();
    //有效终止日期
    var endDate = $("#dp_quoteEndDate").val();
    var param = {
        "keyword": keyword,
        "warehousingId": warehousingId,
        "startDate": startDate,
        "endDate": endDate
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
