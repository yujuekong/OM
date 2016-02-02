jQuery(function ($) {
    $("#submenu-menu-advert-user").addClass("active");
    $("#menu-advert").addClass("active");
    $("#menu-advert").addClass("open");
    initTable();
    //initOrgTree();
    $("#type_keyword").keyup(function () {
        initTable();
    });

});

var oTable = null;

function initTable() {
    if (oTable == null) {
        oTable = $("#gameInfo_list").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            "bFilter": false,//过滤功能
            "bInfo": true,//页脚信息
            //"sPaginationType": "two_button",
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/sale/goodsIntegral/queryGoodsIntegralPage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "goodsName", "sClass": "center"},
                {"mDataProp": "integralNumber", "sClass": "center"},
                {
                    "mDataProp": "isUser", "sClass": "center", "mRender": function (data, type, full) {
                    var goodsStatus = '';
                    if (data == '0') {
                        goodsStatus = '<input title="使用" class="ace ace-switch ace-switch-6" type="checkbox" checked="checked"  onclick="changeStatus(\'' + full.goodsIntegralId+ '\')" /><span class="lbl"></span>';
                    }
                    else if (data == '1') {
                        goodsStatus = '<input title="禁用" class="ace ace-switch ace-switch-6" type="checkbox" onclick="changeStatus(\'' + full.goodsIntegralId + '\')" /><span class="lbl"></span>';
                    }
                    return goodsStatus;
                }
                },
                {
                    "sDefaultContent": "", "sClass": "center", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="queryGoodsIntegralDt(\'' + full.goodsIntegralId + '\')" class="btn btn-xs btn-success" title="查询"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
                        '<button onclick="preAddOrModifyIntegral(\'' + full.goodsIntegralId + '\')" class="btn btn-xs btn-info" title="修改"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
                        '<button onclick="deleteGoodsIntegral(\'' + full.goodsIntegralId + '\')" class="btn btn-xs btn-danger" title="删除"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
            "aoColumnDefs": [{//和aoColums类似，但他可以给指定列附近爱属性
                "bSortable": false, "aTargets": [0, 1, 2, 3],//这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
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
    var keyword = $("#orgId").val();
    var gameStatus = $("#gameStatus").val();
    var type_keyword = $("#type_keyword").val();
    var param = {"keyword": keyword};
    param.gameStatus = gameStatus;
    param.type_keyword = type_keyword;
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
function searchGameStatus() {
    if (oTable) {
        oTable.fnDraw();
    }
}

//查询详情
function queryGoodsIntegralDt(id) {
    window.location.href = ROOT_PATH + "/view/sale/goodsIntegral/queryGoodsIntegralDt.action?goodsIntegralId=" + id;
}
//增加或修改
function preAddOrModifyIntegral(id) {
    window.location.href = ROOT_PATH + "/view/sale/goodsIntegral/preAddOrModifyIntegral.action?goodsIntegralId=" + id;
}
//删除
function deleteGoodsIntegral(id) {
    if (confirm("确定删除?")) {
        window.location.href = ROOT_PATH + "/view/sale/goodsIntegral/deleteGoodsIntegral.action?goodsIntegralId=" + id;
    }
}

function changeStatus(checkbox, goodsInfoId) {
    if (goodsInfoId) {
        var isDisable = null;
        if ($(checkbox).prop("checked")) {
            isDisable = '0';
        } else {
            isDisable = '1';
        }
        $.ajax({
            type: "POST",
            url: ROOT_PATH + "/view/goods/goodsInfo/modifyGoodsStatus.action",
            data: {"goodsInfoId": goodsInfoId, "isDisable": isDisable},
            success: function (data) {
                if (!data) {
                    alert("系统错误，请联系管理员!");
                }
                initTable();
            }
        });
    }
}