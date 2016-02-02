jQuery(function ($) {
    $("#submenu-menu-inventory-info").addClass("active");
    $("#menu-advert").addClass("active");
    $("#menu-advert").addClass("open");
    $("#submenu-submenu-menu-sys-org-user").addClass("active");
    $("#submenu-menu-sys-org").addClass("active");
    $("#submenu-menu-sys-org").addClass("open");
    $("#menu-sys").addClass("active");
    $("#menu-sys").addClass("open");
    initTable();
    initOrgTree();
    $("#searchGoodsBtn").click(function () {
        searchGoods();
    });
    $("#house_keyword").keyup(function () {
        initTable();
    });
    if ($("#orgId").val() != '') {
        $("#searchTree").hide();
        $("#houseTable").attr("class", "col-xs-12");
    }
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
            "sAjaxSource": ROOT_PATH + "/view/inventory/warehouse/queryWarehousePage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "warehouseNo"},
                {"mDataProp": "warehouseName"},
                {"mDataProp": "warehouseAddress"},
                {"mDataProp": "warehouseSize"},
                {"mDataProp": "createDate"},
                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="inventoryDet(\'' + full.warehouseId + '\')" class="btn btn-xs btn-success" style="text-align: center"><i style="text-align: center" class="ace-icon fa fa-search-plus bigger-120">明细</i></button>';
                    actionDiv += '</div>';
                    return actionDiv;
                }
                },
                {
                    "mDataProp": "warehouseStatus", "sClass": "center", "mRender": function (data, type, full) {
                    var warehousingStatus = '';
                    if (data == '1') {
                        warehousingStatus = '<input title="启用" class="ace ace-switch ace-switch-6" type="checkbox" onclick="modifyHouseStatus(\'' + full.warehouseId + '\')" /><span class="lbl"></span>';
                    }
                    else if (data == '0') {
                        warehousingStatus = '<input title="禁用" class="ace ace-switch ace-switch-6" type="checkbox" onclick="modifyHouseStatus(\'' + full.warehouseId + '\')" checked /><span class="lbl"></span>';
                    }
                    return warehousingStatus;
                }
                },
                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="queryWarehouseById(\'' + full.warehouseId + '\')" class="btn btn-xs btn-success"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
                        '<button onclick="preAddOrModifystorageWarehouse(\'' + full.warehouseId + '\')" class="btn btn-xs btn-info"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
                        '<button onclick="deleteWarehouse(\'' + full.warehouseId + '\')" class="btn btn-xs btn-danger"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
            "fnServerData": retrieveData
        });


    } else {
        oTable.fnDraw(); //重新加载数据
    }

}

/**
 * sSource   查询链接
 * aoData    参数
 * fnCallback 返回数据填充方法
 * 备注：方法的3个参数名称不能改变
 */
function retrieveData(sSource, aoData, fnCallback) {
    //商品名称或编号
    var keyword = $("#house_keyword").val();
    var warehouseId = $("#warehouseId").val();
    var orgId = $("#orgId").val();
    var param = {
        "keyword": keyword,
        "warehouseId": warehouseId,
        "orgId": orgId,
    };
    var zTree = $.fn.zTree.getZTreeObj("org_tree");
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


//查询详情
function queryWarehouseById(id) {
    window.location.href = ROOT_PATH + "/view/inventory/warehouse/queryWarehouseById.action?warehouseId=" + id;
}
//增加或修改
function preAddOrModifystorageWarehouse(id) {
    window.location.href = ROOT_PATH + "/view/inventory/warehouse/preAddOrModifystorageWarehouse.action?warehouseId=" + id;
}
//删除
function deleteWarehouse(id) {
    var params = {
        "warehouseId": id,
    };
    $.ajax({
        url: ROOT_PATH + "/view/inventory/warehouse/isInventory.action",
        type: "post",
        "data": params,
        success: function (data) {
            if (data == 'true') {
                alert("此仓库仍有库存，无法删除");
            } else {
                if (confirm("确定删除?")) {
                    window.location.href = ROOT_PATH + "/view/inventory/warehouse/deleteWarehouse.action?warehouseId=" + id;
                }
            }
        }

    });
}
//修改仓库状态
function modifyHouseStatus(id) {
    var param = {
        "warehouseId": id,
    };
    $.ajax({
        type: "post",
        url: ROOT_PATH + "/view/inventory/warehouse/modifyHouseStatus.action",
        data: param,
        success: function (data) {
            if (!data) {
                alert("系统错误，请联系管理员!");
            }
            initTable();
        }

    });

}

//库存明细
function inventoryDet(id) {
    $("#warehouseId").val(id);
    resetSelectHouse();
    $('#house_choise_modal').modal('show');
}

//加载库存数据
function resetSelectHouse() {
    //initSellerTree();
    initSellerGoodsTable();
}

var gTable = null;
function initSellerGoodsTable() {
    if (gTable == null) {
        gTable = $("#search_goods_table").dataTable({
            "bDestory": true,
            "bAutoWidth": false,
            "bLengthChange": false,
            "bProcessing": true,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/inventory/storageInventory/queryStorageInventoryPage.action", //异步请求地址 ：查询站点信息
            "bStateSave": true,
            "aoColumns": [
                {"mDataProp": "goodsInfo.goodsName"},
                {"mDataProp": "inventoryNumber"},
                {"mDataProp": "goodsInfo.unitName"},
            ],
            "aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
                {"bSortable": false, "aTargets": [0, 1, 2]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
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


//初始化机构树
function initOrgTree() {
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: function () {
                zTreeOnClick();
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

function zTreeOnClick(event, treeId, treeNode) {
    if (oTable) {
        oTable.fnDraw();
    }
}

function searchGoods() {
    if (oTable) {
        oTable.fnDraw();
    }
}
function showMenu() {
    var gtObj = $("#dict_provice_id");
    //alert(gtObj);
    if ($("#gt_combobox").css("display") == "none") {
        var gtOffset = $("#dict_provice_id").offset();
        $("#gt_combobox").css({
            width: gtObj.css("width"),
            left: gtOffset.left + "px",
            top: gtOffset.top + gtObj.outerHeight() + "px"
        }).slideDown("fast");
    }

    $("body").bind("mousedown", onBodyDown);
}

function hideMenu() {
    $("#gt_combobox").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "gt_combobox" || $(event.target).parents("#gt_combobox").length > 0)) {
        hideMenu();
    }
}

function show() {
    var selectNodes = $.fn.zTree.getZTreeObj("org_tree").getSelectedNodes();
    alert(selectNodes[0].id);
}

