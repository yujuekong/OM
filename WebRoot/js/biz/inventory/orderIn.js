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
            "sAjaxSource": ROOT_PATH + "/view/inventory/warehousing/queryWarehousingPage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "warehousingNo"},
                {"mDataProp": "storageWarehouse.warehouseName"},
                {"mDataProp": "sysDict.dictName"},
                {"mDataProp": "sellerInfo.sellerName"},
                {"mDataProp": "createDate"},
                {"mDataProp": "requestDate"},
                {"mDataProp": "warehousingDate"},
                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="orderInDet(\'' + full.warehousingEntryId + '\')" class="btn btn-xs btn-success" style="text-align: center"><i style="text-align: center" class="ace-icon fa fa-search-plus bigger-120">明细</i></button>';
                    actionDiv += '</div>';
                    return actionDiv;
                }
                },
                {
                    "mDataProp": "warehousingStatus", "sClass": "center", "mRender": function (data, type, full) {
                    var warehousingStatus = '';
                    if (data == '1') {
                        warehousingStatus = '<button class="btn btn-minier btn-success" type="button" disabled>已入库</button>';
                    }
                    else if (data == '0') {
                        warehousingStatus = '<button onclick="modifyInDet(\'' + full.warehousingEntryId + '\',\'' + full.warehouseId + '\')" class="btn btn-minier btn-info " type="button" >未入库</button>';
                    }
                    return warehousingStatus;
                }
                },
                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button id="test" onclick="queryWarehousingById(\'' + full.warehousingEntryId + '\')" class="btn btn-xs btn-success"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
                            //'<button onclick="preAddOrModifyOrderIn(\'' + full.warehousingEntryId + '\')" class="btn btn-xs btn-info"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
                        '<button onclick="deleteOrderIn(\'' + full.warehousingEntryId + '\',\'' + full.warehousingStatus + '\')" class="btn btn-xs btn-danger"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
                {
                    "bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 7, 6, 8, 9]
                },
                {
                    "aTargets": [1], "mRender": function (data, type, full) {
                    return "<a>" + data + "</a>"

                }
                },
                {
                    "sDefaultContent": '', "aTargets": [0, 1, 2, 3, 4, 5, 7, 6, 8, 9]
                }
            ],
            "fnServerData": retrieveData,
        });


    } else {
        oTable.fnDraw(); //重新加载数据
    }

}


var gTable = null;

function initInDtlTable() {
    if (gTable == null) {
        gTable = $("#order_inDtl_list").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": true,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            iDisplayLength: "20",
            //"bFilter": true,//过滤功能
            "bInfo": true,//页脚信息
            "bFilter": false,
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/inventory/InListDetail/queryEntryDetailPage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "storageWarehousingEntry.warehousingNo"},
                {"mDataProp": "goodsInfo.goodsName"},
                {"mDataProp": "requestCount"},
                {
                    "sDefaultContent": "", "mDataProp": "warehousingCount", "mRender": function (data, type, full) {
                    var actionDiv;
                    actionDiv =
                        "<input name='warehousingCount' style='text-align: center' type='text' value='" + full.warehousingCount + "'/>" +
                        "<input name='warehousingDtlId' style='text-align: center' type='hidden' value='" + full.warehousingDtlId + "'/>" +
                        "<input name='goodsId' style='text-align: center' type='hidden' value='" + full.goodsInfo.goodsId + "'/>";
                    return actionDiv;
                }
                },
                {"mDataProp": "warehousingPrice"},
                {"mDataProp": "warehousingAmount"},
                {"mDataProp": "remark"},
                {
                    "sDefaultContent": "", "sClass": "center", "mRender": function (data, type, full) {
                    var warehousingStatus = '<button onclick="sendCountSingle($(this))" class="btn btn-minier btn-info " type="button">未入库</button>';
                    return warehousingStatus;
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
            "aoColumnDefs": [
                {//和aoColums类似，但他可以给指定列附近爱属性
                    "bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6, 7]
                },//这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
            ],
            "fnServerData": retrieveData,
        });


    } else {
        gTable.fnDraw(); //重新加载数据
    }

}

var sTable = null;

function initShowTable() {
    if (sTable == null) {
        sTable = $("#in_inDtl_list").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": true,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            //"bFilter": true,//过滤功能
            "bInfo": true,//页脚信息
            "bFilter": false,
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/inventory/InListDetail/queryEntryDetailPage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "storageWarehousingEntry.warehousingNo"},
                {"mDataProp": "goodsInfo.goodsName"},
                {"mDataProp": "requestCount"},
                {"mDataProp": "warehousingCount"},
                {"mDataProp": "warehousingPrice"},
                {"mDataProp": "warehousingAmount"},
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
            "aoColumnDefs": [
                {//和aoColums类似，但他可以给指定列附近爱属性
                    "bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6]
                },//这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
            ],
            "fnServerData": retrieveData,
        });


    } else {
        sTable.fnDraw(); //重新加载数据
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
    //用户所属公司
    var orgId = $("#orgId").val();
    //是否显示全部入库单明细
    var showAll = $("#showAll").val();
    //有效起始日期
    var startDate = $("#dp_quoteStartDate").val();
    //有效终止日期
    var endDate = $("#dp_quoteEndDate").val();
    var param = {
        "keyword": keyword,
        "warehousingId": warehousingId,
        "startDate": startDate,
        "endDate": endDate,
        "orgId": orgId,
        "showAll": showAll,
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
//查询详情
function queryWarehousingById(id) {
    window.location.href = ROOT_PATH + "/view/inventory/warehousing/queryWarehouingById.action?warehousingId=" + id;
}
//pre增加或修改
function preAddOrModifyOrderIn(id) {
    window.location.href = ROOT_PATH + "/view/inventory/warehousing/preAddOrModifyOrderIn.action?warehousingId=" + id;
}
//删除
function deleteOrderIn(id, warehousingStatus) {
    if (warehousingStatus == '1') {
        alert("已入库订单无法删除");
    } else {
        if (confirm("确定删除?")) {
            window.location.href = ROOT_PATH + "/view/inventory/warehousing/deleteOrderIn.action?warehousingId=" + id;
        }
    }
}
function modifyWarehousingStatus(id) {
    if (window.confirm("确定修改为已入库?")) {
        var param = {
            "warehousingId": id
        };
        $.ajax({
            type: "post",
            data: param,
            url: ROOT_PATH + "/view/inventory/warehousing/modifyWarehousingStatus.action",
            success: function (data) {
                if (!data) {
                    alert("系统错误，请联系管理员!");
                }
                initTable();
            }
        })
    }
}


//入库单明细
function orderInDet(id) {
    $("#warehousingId").val(id);
    $("#showAll").val("0");
    initShowTable();
    $('#in_show_modal').modal('show');
}

//修改入库数量
function modifyInDet(warehousingId, warehouseId) {
    $("#warehousingId").val(warehousingId);
    $("#warehouseId").val(warehouseId);
    $("#showAll").val("1");
    initInDtlTable();
    $('#inOrder_show_modal').modal('show');
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


// 获取列表JSON
function getTable($table) {
    var reg = /,$/gi;
    if ($("tbody tr", $table).html()) {
        var tableArray = [], trString = '', tableString = '';
        $("tbody tr", $table).each(function (index, element) {
            trString = '{';
            $("input", $(this)).each(function (indexTd, element) {
                //if ($(this).val()) {
                trString += '"' + this.name + '":"' + $(this).val() + '",';
                //} else {
                //    alert('第' + (index + 1) + '行有数据为空，请输入！');
                //    trString = '';
                //    return false;
                //}
            });
            if (trString) {
                tableArray.push(trString.replace(reg, "}"));
            } else {
                return false;
            }
        });
        if (trString) {
            tableString = '[' + tableArray.join(',') + ']';
            return tableString;
        } else {
            return '';
        }
    } else {
        alert('设备列表不能为空，请添加设备（点击添加设备按钮）！');
    }
}

function sendCount() {
    var count = getTable($('#order_inDtl_list'));
    var warehousingId = $("#warehousingId").val();
    var warehouseId = $("#warehouseId").val();
    if (count) {
        if (confirm("确定入库?")) {
            var params = {
                "count": count,
                "warehousingId": warehousingId,
                "warehouseId": warehouseId,
            };
            $.ajax({
                data: params,
                type: "post",
                url: ROOT_PATH + "/view/inventory/warehousing/modifyWarehousingCount.action",
                success: function (data) {
                    if (!data) {
                        alert("系统错误，请联系管理员!");
                    }
                    hiddenModel();
                    initTable();
                }
            });
        }
    }

}


function modifyInDetSingle(full) {
    var warehousingCount = full.parent().parent().find("td").eq(3).find("[name=warehousingCount]").val();
    var warehousingDtlId = (full.parent().parent().find("td").eq(3).find("[name=warehousingDtlId]").val());
    var goodsId = (full.parent().parent().find("td").eq(3).find("[name=goodsId]").val());
    if (warehousingCount == '' || warehousingCount == 0) {
        alert("实际入库数量不能为空");
        return false;
    } else {
        var single = "[{'warehousingCount':" + warehousingCount + ", 'warehousingDtlId': " + warehousingDtlId + ", 'goodsId':" + goodsId + "}]";
    }
    return single;
}

function sendCountSingle(full) {
    var count = modifyInDetSingle(full);
    var warehousingId = $("#warehousingId").val();
    var warehouseId = $("#warehouseId").val();
    if (count) {
        if (confirm("确定入库?")) {
            var params = {
                "count": count,
                "warehousingId": warehousingId,
                "warehouseId": warehouseId,
            };
            $.ajax({
                data: params,
                type: "post",
                url: ROOT_PATH + "/view/inventory/warehousing/modifyWarehousingCount.action",
                success: function (data) {
                    if (!data) {
                        alert("系统错误，请联系管理员!");
                    }
                    initInDtlTable();
                    initTable();
                }
            });
        }
    }

}
function dateFind(){
	if($("#dp_quoteStartDate").val() != "" && $("#dp_quoteEndDate").val() != ""){
		initTable();
	}
}
function hiddenModel() {
    $('#inOrder_show_modal').modal('hide');
}


