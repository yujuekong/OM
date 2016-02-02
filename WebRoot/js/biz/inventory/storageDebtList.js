jQuery(function ($) {
    $("#submenu-menu-advert-user").addClass("active");
    $("#menu-advert").addClass("active");
    $("#menu-advert").addClass("open");

    initTable();
    $("#searchGoodsBtn").click(function () {
        searchGoods();
    });
    $("#debt_keyword").keyup(function () {
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
        oTable = $("#debt_list").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            "bFilter": false,//过滤功能
            "bInfo": true,//页脚信息
            //"sPaginationType": "two_button",
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/inventory/storageDebt/queryStorageDebtPage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "debtNo"},
                {"mDataProp": "debtDate"},
                {"mDataProp": "sysUser.userName"},
                {"mDataProp": "debtStatus"},
                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="debtDtl(\'' + full.debtId + '\')" class="btn btn-xs btn-success" style="text-align: center"><i style="text-align: center" class="ace-icon fa fa-search-plus bigger-120">明细</i></button>'
                    actionDiv += '</div>';
                    return actionDiv;
                }
                },
                //{
                //  "mDataProp": "warehousingStatus", "sClass": "center", "mRender": function (data, type, full) {
                //  var warehousingStatus = '';
                //  if (data == '1') {
                //    warehousingStatus = '<input title="" class="ace ace-switch ace-switch-6" type="checkbox"  onclick="modifygoodsStatus(this,' + full.goodsId + ')" /><span class="lbl"></span>';
                //  }
                //  else if (data == '0') {
                //    warehousingStatus = '<input title="" class="ace ace-switch ace-switch-6" type="checkbox" checked onclick="modifygoodsStatus(this,' + full.goodsId + ')"  /><span class="lbl"></span>';
                //  }
                //  return warehousingStatus;
                //}
                //},
                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="queryStorageDebtById(\'' + full.debtId + '\')" class="btn btn-xs btn-success"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
                        '<button onclick="preAddOrModifyStorageDebt(\'' + full.debtId + '\')" class="btn btn-xs btn-info"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
                        '<button onclick="deleteStorageDebt(\'' + full.debtId + '\')" class="btn btn-xs btn-danger"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
            //和aoColums类似，但他可以给指定列附近爱属性
            "aoColumnDefs": [
                {
                    "bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5]
                },
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
    var keyword = $("#debt_keyword").val();
    //报损单ID
    var debtId = $("#debtId").val();
    //有效起始日期
    var startDate = $("#dp_quoteStartDate").val();
    //有效终止日期
    var endDate = $("#dp_quoteEndDate").val();
    //用户所属公司
    var orgId = $("#orgId").val();
    var param = {
        "keyword": keyword,
        "debtId": debtId,
        "startDate": startDate,
        "endDate": endDate,
        "orgId": orgId,
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
function queryStorageDebtById(id) {
    window.location.href = ROOT_PATH + "/view/inventory/storageDebt/queryStorageDebtById.action?debtId=" + id;
}
//pre增加或修改
function preAddOrModifyStorageDebt(id) {
    window.location.href = ROOT_PATH + "/view/inventory/storageDebt/preAddOrModifyStorageDebt.action?debtId=" + id;
}
//删除
function deleteStorageDebt(id) {
    if (confirm("确定删除?")) {
        window.location.href = ROOT_PATH + "/view/inventory/storageDebt/deleteStorageDebt.action?debtId=" + id;
    }
}
function modifygoodsStatus(obj, id) {
    if (obj.checked == true) {
        alert(1);
    } else {
        alert("无法修改已入库单状态")
        obj.attr("checked", false);
    }
}


//供应商品明细
function debtDtl(id) {
    $("#debtId").val(id);
    resetDebtDtl();
    $('#debtDtl_show_modal').modal('show');
}
//加载供应商数据
function resetDebtDtl() {
    initDebtDtlTable();
}

//初始化报损详细列表
var dTable = null;
function initDebtDtlTable() {
    if (dTable == null) {
        dTable = $("#search_dtl_table").dataTable({
            "bDestory": true,
            "bAutoWidth": false,
            "bLengthChange": false,
            "bProcessing": true,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/inventory/storageDebtDtl/queryStorageDebtDtlPage.action", //异步请求地址 ：查询站点信息
            "bStateSave": true,
            "aoColumns": [
                {"mDataProp": "goodsInfo.goodsName"},
                {"mDataProp": "debtGoodsCount"},
                {"mDataProp": "debtDesc"},
                {"mDataProp": "sysDict.dictName"},
            ],
            "aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
                {"bSortable": false, "aTargets": [0, 1, 2, 3]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
            ],
            "fnServerData": retrieveData,

        });
    } else {
        dTable.fnDraw(); //重新加载数据
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