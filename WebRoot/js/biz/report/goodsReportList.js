jQuery(function ($) {
    $("#submenu-menu-inventory-info").addClass("active");
    $("#menu-advert").addClass("active");
    $("#menu-advert").addClass("open");
    initTable();
    initSelect();
    $("#seller_keyWord").keyup(function () {
        initTable();
    });
    $("#reportChart_year").datetimepicker({
        format: 'yyyy',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: true,
        todayHighlight: 1,
        startView: 4,
        minView: 4,
        forceParse: 4,
    });
    $("#reportChart_month").datetimepicker({
        format: 'mm',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: true,
        todayHighlight: 1,
        startView: 3,
        minView: 3,
        pickerPosition: "bottom-left"
    });
});

var oTable = null;

function initTable() {
    if (oTable == null) {
        oTable = $("#goodsReport_list").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            "bFilter": false,//过滤功能
            "bInfo": true,//页脚信息
            //"sPaginationType": "two_button",
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/report/goodsReport/queryGoodsReportPage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "year"},
                {"mDataProp": "month"},
                {"mDataProp": "dictName"},
                {"mDataProp": "saleMoney"},
                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="sellerGoodsDet(\'' + full.sellerId + '\')" class="btn btn-xs btn-success"><i class="ace-icon fa fa-search-plus bigger-120">明细</i></button>';
                    actionDiv += '</div>';
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
            "aoColumnDefs": [{//和aoColums类似，但他可以给指定列附近爱属性
                "bSortable": false, "aTargets": [0, 1, 2, 3, 4],//这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
            }],
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
    //var keyword = $("#keyword").val();
    var year = $("#year_report").val();
    var month = $("#month_report").val();
    var dictOrgId = $("#orgId_report").val();
    //var zTree = $.fn.zTree.getZTreeObj("org_tree");
    var param = {
        //"keyword": keyword,
        "year": year,
        "month": month,
        "dictOrgId": dictOrgId,
    };
    //if (zTree != null) {
    //    if (zTree.getSelectedNodes() != "") {
    //        var id = zTree.getSelectedNodes()[0].id;
    //        var pid = zTree.getSelectedNodes()[0].pid;
    //        var level = zTree.getSelectedNodes()[0].level;
    //        param.id = id;
    //        param.pid = pid;
    //        param.level = level;
    //    }
    //}
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

$("#reportChart").click(function () {
    var year = $("#year_report").val();
    var month = $("#month_report").val();
    var orgId = $("#orgId_report").val();
    if (year == '') {
        alert("请选择年份");
        return false;
    }
    if ((month == '' && orgId == '')) {
        alert("月份和公司必须选择其中一个");
        return false;
    }
    if ((month != '' && orgId != '')) {
        alert("月份和公司只能选择其中一个");
        return false;
    }
    window.location.href = ROOT_PATH + "/view/report/goodsReport/preGoodsReportCharts.action?year=" + year + "&month=" + month + "&orgId=" + orgId;
})

function selectOrg(obj) {
    var ul = $(obj);
    var orgId = ul.children().eq(0).val();
    var orgName = ul.children().eq(1).text();
    $("#orgName").html("<span class='ace-icon fa fa-caret-down'>" + orgName + "</span>");
    $("#orgId_report").val(orgId);
    initTable();
}

function selectYears(obj) {
    var ul = $(obj);
    var year = ul.children().eq(0).val();
    var yearStr = ul.children().eq(1).text();
    $("#year").html("<span class='ace-icon fa fa-caret-down'>" + yearStr + "</span>");
    $("#year_report").val(year);
    initTable();
}

function selectMonth(obj) {
    var ul = $(obj);
    var month = ul.children().eq(0).val();
    var monthStr = ul.children().eq(1).text();
    $("#month").html("<span class='ace-icon fa fa-caret-down'>" + monthStr + "</span>");
    $("#month_report").val(month);
    initTable();
}

function initSelect() {
    $.ajax({
        url: ROOT_PATH + "/view/report/goodsReport/initSelect.action",
        //data:"",
        type: "post",
        dataType: "json",
        success: function (data) {
            $.each(data.years, function (idx, obj) {
                $("#year_ul").append("<li onclick='selectYears(this)'><input value='" + obj + "' hidden/><a>" + obj + "年</a></li>");
            });
            $.each(data.org, function (idx, obj) {
                $("#org").append("<li onclick='selectOrg(this)'><input value='" + obj.dictOrgId + "' hidden/><a>" + obj.dictName + "</a></li>");
            });
        }
    });
}
