jQuery(function ($) {
    $("#submenu-menu-advert-user").addClass("active");
    $("#menu-advert").addClass("active");
    $("#menu-advert").addClass("open");
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
    
    initTable();
    getToal();
    $("#searchOrderPay").keyup(function () {
        searchOrder();
    });
    
    
});

var oTable = null;

function initTable() {
    if (oTable == null) {
        oTable = $("#orderPay_list").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            "bFilter": false,//过滤功能
            "bInfo": true,//页脚信息
            //"sPaginationType": "two_button",
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/sale/orderPay/queryOrderPayPage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "payId"},
                {"mDataProp": "goodsName"},
                {"mDataProp": "goodsNumber"},
                {"mDataProp": "goosUnit"},
                {"mDataProp": "price"},
                {"mDataProp": "userRealName"},
                {"mDataProp": "tel"},
                {"mDataProp": "userAddress"},
                //{"mDataProp": "payNumber"},
                {"mDataProp": "orderTime"},
                {
                    "mDataProp": "isGet", "sClass": "center", "mRender": function (data, type, full) {
                    var isGet = '';
                    if (data == '0' || data == null) {
                        isGet = '<button class="btn btn-minier btn-info" type="button" disabled>未领取</button>';
                    }
                    else if (data == '1') {
                        isGet = '<button  class="btn btn-minier btn-danger " type="button" disabled>已领取</button>';
                    }
                    return isGet;
                }
                },
                {
                    "mDataProp": "orderStatus", "sClass": "center", "mRender": function (data, type, full) {
                    var orderStatus = '';
                    if (data == '0') {
                        //if (full.isRefund == '0' || full.isRefund == null) {
                        //    orderStatus = '<button class="btn btn-minier btn-info" type="button" disabled>未付款</button>';
                        //} else if (full.isRefund == '1') {
                        //    orderStatus = '<button  class="btn btn-minier btn-danger " type="button" disabled>已退款</button>';
                        //}
                        orderStatus = '<button  class="btn btn-minier btn-danger " type="button" disabled>未付款</button>';

                    } else if (data == "1") {
                        orderStatus = '<button class="btn btn-minier btn-success" type="button" disabled>已付款</button>';

                    }
                    else if (data == "2") {
                        orderStatus = '<button class="btn btn-minier btn-danger" type="button" disabled>已退款</button>';
                    }
                    return orderStatus;
                }
                },
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
                "bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10],//这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
                "searchable": false,
                "orderable": false,
                "targets": 0
            }],
            "fnServerData": retrieveData,
        });


    } else {
        oTable.fnDraw(); //重新加载数据
    }

}

function getToal() {
    $.ajax({
        url: ROOT_PATH + "/view/sale/orderPay/sellTotalNumber.action",
        type: "post",
        success: function (data) {
            $("#sumSell").text(data);
        }

    })
}
/**
 * sSource   查询链接
 * aoData    参数
 * fnCallback 返回数据填充方法
 */
function retrieveData(sSource, aoData, fnCallback) {
    //关键字
    var keyword = $("#searchOrderPay").val();
    //有效起始日期
    var startDate = $("#dp_quoteStartDate").val();
    //有效终止日期
    var endDate = $("#dp_quoteEndDate").val();
    var filter = $("#filter").val();
    var param = {
        "keyword": keyword,
        "startDate": startDate,
        "endDate": endDate,
        "filter": filter,
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
        }
    });
}

//搜索商品
function searchOrder() {
    if (oTable) {
        oTable.fnDraw();
    }
}

//修改订单为已出库
function modifyOrderPay(id) {
    var params = {
        "payId": id
    };
    $.ajax({
        type: "post",
        data: params,
        url: ROOT_PATH + "/view/sale/orderPay/modifyOrderPay.action",
        success: function (data) {
            if (!data) {
                alert("系统错误，请联系管理员!");
            }
            searchOrder();
        }
    })
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

function chaxun(){
	if($("#dp_quoteStartDate").val()!='' && $("#dp_quoteEndDate").val()!=''){
		initTable();
	}
	
}
/**
 * 全部订单
 */
function allOrder() {
    $("#orderStatus").html("全部订单<span class='ace-icon fa fa-caret-down'></span>");
    $("#filter").val('');
    initTable();
}
/**
 * 已付款过滤
 */
function paid() {
    $("#orderStatus").html("已付款<span class='ace-icon fa fa-caret-down'></span>");
    $("#filter").val(1);
    initTable();
}
///**
// * 未付款过滤
// */
//function unpaid() {
//    $("#orderStatus").html("未付款<span class='ace-icon fa fa-caret-down'></span>");
//    $("#filter").val(0);
//    initTable();
//}

/**
 * 已退款过滤
 */
function refund() {
    $("#orderStatus").html("已退款<span class='ace-icon fa fa-caret-down'></span>");
    $("#filter").val(2);
    initTable();
}