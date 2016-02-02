jQuery(function ($) {
    initTable();
    $("#coupon_keyword").keyup(function () {
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
        oTable = $("#couponList").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            "bFilter": false,//过滤功能
            "bInfo": true,//页脚信息
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/sale/MemberCoupon/queryMemberPage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "memberName"},
                {
                    "mDataProp": "memberSex", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    if (data == '1') {
                        actionDiv = '<label>男</label>'
                    } else {
                        actionDiv = '<label>女</label>';
                    }
                    return actionDiv;
                }
                },
                {"mDataProp": "memberTel"},
                {"mDataProp": "memberMail"},

                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="couponDtl(\'' + full.memberId + '\')" class="btn btn-xs btn-success" style="text-align: center"><i style="text-align: center" class="ace-icon fa fa-search-plus bigger-120">明细</i></button>';
                    actionDiv += '</div>';
                    return actionDiv;
                }
                },
                //{
                //    "sDefaultContent": "", "mRender": function (data, type, full) {
                //    var actionDiv = '';
                //    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                //        //'<button id="test" onclick="queryCouponById(\'' + full.couponId + '\')" class="btn btn-xs btn-success"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
                //        '<button onclick="preAddOrModifyCoupon(\'' + full.memberId + '\')" class="btn btn-xs btn-info"><i class="ace-icon fa fa-pencil bigger-120"></i></button>';
                //        //'<button onclick="deleteCoupon(\'' + full.couponId + '\')" class="btn btn-xs btn-danger"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
                //    actionDiv += '</div>';
                //    return actionDiv;
                //}
                //}
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
                    "bSortable": false, "aTargets": [0, 1, 2, 3, 4]
                },
                {
                    "sDefaultContent": '', "aTargets": [0, 1, 2, 3, 4]
                }
            ],
            "fnServerData": retrieveMember,
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
function retrieveMember(sSource, aoData, fnCallback) {
    //有效起始日期
    var startDate = $("#dp_quoteStartDate").val();
    //有效终止日期
    var endDate = $("#dp_quoteEndDate").val();
    var keyword = $("#coupon_keyword").val();
    var param = {
        "startDate": startDate,
        "endDate": endDate,
        "keyword": keyword,
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

var mTable = null;
function initMTable() {
    if (mTable == null) {
        mTable = $("#order_inDtl_list").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            "bFilter": false,//过滤功能
            "bInfo": true,//页脚信息
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/sale/MemberCoupon/queryMemberCouponPage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "activityCoupon.couponName"},
                {"mDataProp": "couponTime"},
                {"mDataProp": "activityCoupon.couponAmount"},
                {
                    "mDataProp": "activityCoupon.couponType", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    if (data == '0') {
                        actionDiv = '<label>现金</label>';
                    } else if (data == '1') {
                        actionDiv = '<label>折扣</label>';
                    }
                    return actionDiv;
                }
                },
                {
                    "mDataProp": "isUse", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    var actionDiv = '';
                    if (data == '0') {
                        actionDiv = '<label>未使用</label>';
                    } else if (data == '1') {
                        actionDiv = '<label>使用</label>';
                    }
                    return actionDiv;
                    return actionDiv;
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
            "aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
                {
                    "bSortable": false, "aTargets": [0, 1, 2, 3, 4]
                },
                {
                    "sDefaultContent": '', "aTargets": [0, 1, 2, 3, 4]
                }
            ],
            "fnServerData": retrieveData,
        });


    } else {
        mTable.fnDraw(); //重新加载数据
    }

}


/**
 * sSource   查询链接
 * aoData    参数
 * fnCallback 返回数据填充方法
 */
function retrieveData(sSource, aoData, fnCallback) {
    //有效起始日期
    var startDate = $("#dp_quoteStartDate").val();
    //有效终止日期
    var endDate = $("#dp_quoteEndDate").val();
    var keyword = $("#coupon_keyword").val();
    var memberId = $("#memberId").val();
    var param = {
        "startDate": startDate,
        "endDate": endDate,
        "keyword": keyword,
        "memberId": memberId
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


function couponDtl(id) {
    $("#memberId").val(id);
    initMTable();
    $('#inOrder_show_modal').modal('show');
}
//function queryCouponById(id) {
//    window.location.href = ROOT_PATH + "/view/sale/coupon/queryCouponById.action?couponId=" + id;
//}
function preAddOrModifyCoupon(id) {
    window.location.href = ROOT_PATH + "/view/sale/MemberCoupon/preMemberCouponUpdate.action?memberId=" + id;
}
//function deleteCoupon(id) {
//    if (confirm("确定删除?")) {
//        window.location.href = ROOT_PATH + "/view/sale/coupon/deleteCoupon.action?couponId=" + id;
//    }
//
//}
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