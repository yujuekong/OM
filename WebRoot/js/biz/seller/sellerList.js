jQuery(function ($) {
    $("#submenu-menu-inventory-info").addClass("active");
    $("#menu-advert").addClass("active");
    $("#menu-advert").addClass("open");


    initTable();
    $("#searchGoodsBtn").click(function () {
        searchGoods();
    });
    $("#seller_keyWord").keyup(function () {
        initTable();
    });
    initOrgTree();
});

var oTable = null;

function initTable() {
    if (oTable == null) {
        oTable = $("#warehouse_list").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            "bFilter": false,//过滤功能
            "bInfo": true,//页脚信息
            //"sPaginationType": "two_button",
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/seller/sellerInfo/querySellerPage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "sellerName"},
                {"mDataProp": "sellerNo"},
                {"mDataProp": "linkMan"},
                {"mDataProp": "linkTel"},
                {"mDataProp": "createDate"},
                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="sellerGoodsDet(\'' + full.sellerId + '\')" class="btn btn-xs btn-success"><i class="ace-icon fa fa-search-plus bigger-120">明细</i></button>';
                    actionDiv += '</div>';
                    return actionDiv;
                }
                },
                {
                    "mDataProp": "sellerStatus", "sClass": "center", "mRender": function (data, type, full) {
                    var warehousingStatus = '';
                    if (data == '1') {
                        warehousingStatus = '<input title="启用" class="ace ace-switch ace-switch-6" type="checkbox" onclick="modifySellerStatus(\'' + full.sellerId + '\')" /><span class="lbl"></span>';
                    }
                    else if (data == '0') {
                        warehousingStatus = '<input title="禁用" class="ace ace-switch ace-switch-6" type="checkbox" onclick="modifySellerStatus(\'' + full.sellerId + '\')" checked /><span class="lbl"></span>';
                    }
                    return warehousingStatus;
                }
                },
                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="querySellerInfoById(\'' + full.sellerId + '\')" class="btn btn-xs btn-success"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
                        '<button onclick="preAddOrModifySellerInfo(\'' + full.sellerId + '\')" class="btn btn-xs btn-info"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
                        '<button onclick="deleteSellerInfo(\'' + full.sellerId + '\')" class="btn btn-xs btn-danger"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
                "bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6, 7],//这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
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
    var keyword = $("#seller_keyWord").val();
    var sellerId = $("#sellerId").val();
    var orgId = $("#orgId").val();
    var zTree = $.fn.zTree.getZTreeObj("org_tree");
    var param = {
        "keyword": keyword,
        "sellerId": sellerId,
        "orgId": orgId,
    };
    if (zTree != null) {
        if (zTree.getSelectedNodes() != "") {
            var id = zTree.getSelectedNodes()[0].id;
            var pid = zTree.getSelectedNodes()[0].pid;
            var level = zTree.getSelectedNodes()[0].level;
            param.id = id;
            param.pid = pid;
            param.level = level;
        }
    }
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
function searchGoods() {
    if (oTable) {
        oTable.fnDraw();
    }
}
//查询详情
function querySellerInfoById(id) {
    window.location.href = ROOT_PATH + "/view/seller/sellerInfo/querySellerInfoById.action?sellerId=" + id;
}
//增加或修改
function preAddOrModifySellerInfo(id) {
    window.location.href = ROOT_PATH + "/view/seller/sellerInfo/preAddOrModifySellerInfo.action?sellerId=" + id;
}
//删除
function deleteSellerInfo(id) {
    if (confirm("确定删除?")) {
        window.location.href = ROOT_PATH + "/view/seller/sellerInfo/deleteSellerInfo.action?sellerId=" + id;
    }
}
function modifySellerStatus(id) {
    var param = {
        "sellerId": id,
    };
    $.ajax({
        type: "post",
        data: param,
        url: ROOT_PATH + "/view/seller/sellerInfo/modifySellerStatus.action",
        success: function (data) {
            if (!data) {
                alert("系统错误，请联系管理员!");
            }
            initTable();
        }
    })
}


//供应商品明细
function sellerGoodsDet(id) {
    $("#sellerId").val(id);
    resetSelectHouse();
    $('#house_choise_modal').modal('show');
}

//加载供应商数据
function resetSelectHouse() {
    initSellerGoodsTable();
}

//初始化站点列表
var gTable = null;
function initSellerGoodsTable() {
    if (gTable == null) {
        gTable = $("#search_dtl_table").dataTable({
            "bDestory": true,
            "bAutoWidth": false,
            "bLengthChange": false,
            "bProcessing": true,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/seller/sellerGoods/querySellerGoodsPage.action", //异步请求地址 ：查询站点信息
            "bStateSave": true,
            "aoColumns": [
                {"mDataProp": "goodsInfo.goodsName"},
                {"mDataProp": "sellerInfo.sellerName"},
                {"mDataProp": "goodsArea"},
                {"mDataProp": "goodsPrice"},
            ],
            "aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
                {"bSortable": false, "aTargets": [0, 1, 2, 3]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
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
            "fnServerData": retrieveData,

        });
    } else {
        gTable.fnDraw(); //重新加载数据
    }
}

function initOrgTree() {
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: function (event, treeId, treeNode) {
                if (treeNode.level == '3') {
                    alert("只能选择区域和省份");
                } else {
                    searchGoods();
                }
            }
        }
    };

    var treeObj = $("#org_tree");
    var zNodes;

    $.ajax({
        url: ROOT_PATH + '/view/inventory/warehouse/getMulSubDictDataByPcode.action',
        type: 'POST',
        data: {"dictPcode": "AL_POSITION", "dictLevel": 5},
        dataType: 'json',
        success: function (data) {
            zNodes = data;
            $.fn.zTree.init($("#org_tree"), setting, zNodes);
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