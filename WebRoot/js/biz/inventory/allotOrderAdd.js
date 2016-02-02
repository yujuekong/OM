jQuery(function ($) {
    $("#submenu-menu-device-clean").addClass("active");
    $("#menu-device").addClass("active");
    $("#menu-device").addClass("open");
    initHouseTree2();
    initHouseTree();
    initDictTable();
    $("#goodsKey").keyup(function () {
        initTable();
    });
    //单个追加仓库到申购单
    $(document).on("dblclick", "#outhouse_list tbody tr", function () {
        var $choiseUser = getChoiseHouseInfo($(this));
        var a = $("#inventoryId").val($choiseUser.warehouseId);
        $("#inventoryName").val($choiseUser.warehouseName);
        $("#outhouse_choise_modal").modal('hide');
        //focusNumbox();
    });

    //单个追加仓库到申购单
    $(document).on("dblclick", "#inhouse_list tbody tr", function () {
        var $choiseUser = getChoiseHouseInfo($(this));
        var a = $("#inventoryId2").val($choiseUser.warehouseId);
        $("#inventoryName2").val($choiseUser.warehouseName);
        $("#inhouse_choise_modal").modal('hide');
        //focusNumbox();
    });

    //批量选择时，单个checkbox选中改变背景颜色
    $(document).on("click", "#search_goods_table td input:checkbox", function () {
        if ($(this).prop("checked")) {
            $(this).closest("tr").addClass("row-click");
            $(this).closest("tr").find("td").addClass("row-click");
        } else {
            $(this).closest("tr").removeClass("row-click");
            $(this).closest("tr").find("td").removeClass("row-click");
        }
        var checkedCount = $("#search_goods_table input:checkbox:checked").length;
        if (checkedCount > 0) {
            $("#choise_submit").removeClass("disabled");
        } else {
            $("#choise_submit").addClass("disabled");
        }
    });

    //单击查询商品列表行，改变背景颜色
    $(document).on("click", "#search_goods_table tbody tr", function () {
        var $choise_row = $(this);
        var choiseType = $("#choise_type").val();
        //单个选择
        if (choiseType == '0') {
            $("#search_goods_table tbody tr").removeClass("row-click");
            $("#search_goods_table tbody tr td").removeClass("row-click");
            $choise_row.addClass("row-click");
            $choise_row.find("td").addClass("row-click");
        }
        //批量选择
        if (choiseType == '1') {
//			var $checkbox = $choise_row.find("input:checkbox").eq(0);
//			if($checkbox.prop("checked")) {
//				$checkbox.prop("checked",false);
//			} else {
//				$checkbox.prop("checked",true);
//			}
//			console.info($checkbox.prop("checked"));
        }
    });

    //单个追加商品到 表单
    $(document).on("dblclick", "#search_goods_table tbody tr", function () {
        var choiseType = $("#choise_type").val();
        if (choiseType == '0') {
            var purchaseGoodsRowNo = $("#purchase_goods_rowno").val();
            var $choiseGoods = getChoiseGoods($(this));
            fillGoodsInfo(purchaseGoodsRowNo, $choiseGoods);
            $("#goods_choise_modal").modal('hide');
            focusNumbox();
        }
    });

    //站点选择列表checkbox全选，全不选
    $(document).on('click', 'th input:checkbox', function () {
        var that = this;
        if (that.checked) {
            $("#choise_submit").removeClass("disabled");
        } else {
            $("#choise_submit").addClass("disabled");
        }
        $(this).closest('table').find('tr > td:first-child input:checkbox').each(function () {
            this.checked = that.checked;
            $(this).closest('tr').toggleClass('selected');
        });
    });
});

//获得选择仓库对象
function getChoiseHouseInfo(ele_goodsRow) {
    var $choiseUser = {};
    var $ele_td = ele_goodsRow.find("td");
    $choiseUser.warehouseId = $ele_td.find("input[type=hidden]").eq(0).val();
    $choiseUser.warehouseName = $ele_td.find("label[name='warehouseName']").eq(0).html();
    return $choiseUser;
}

function focusNumbox() {
    $(".fnumbox").each(function (i) {
        var goodsNumVal = $(this).val();
        if (!goodsNumVal) {
            $(this).focus();
            return false;
        }
    });
}

uTable = null;
function initHouseTree() {
    if (uTable == null) {
        uTable = $("#outhouse_list").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,
            "bProcessing": true,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/inventory/warehouse/queryWarehousePage.action",  //异步请求地址
            "aoColumns": [
                {
                    "mDataProp": "warehouseName", "mRender": function (data, type, full) {
                    return '<label name="warehouseName">' + data + '</label><input type="hidden" value="' + full.warehouseId + '"/>';
                }
                },
                {"mDataProp": "warehouseNo", "sClass": "center"},
                {"mDataProp": "warehouseAddress", "sClass": "center"},
                {"mDataProp": "createDate", "sClass": "center"},
                {
                    "mDataProp": "warehouseStatus", "mRender": function (data, type, full) {
                    var goodsStatus = '';
                    if (data == '0') {
                        goodsStatus = '<span style="cursor:pointer;"  class="label label-sm label-success">正常</span>';
                    }
                    else if (data == '1') {
                        goodsStatus = '<span style="cursor:pointer;"  class="label label-sm label-error">禁用</span>';
                    }

                    return goodsStatus;
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
                    "aTargets": [0],
                    "bVisible": true,
                },//这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
                {
                    "sDefaultContent": '', "aTargets": ['_all'],
                }
            ],
            "fnServerData": retrieveHouseData,
        });
    } else {
        uTable.fnDraw(); //重新加载数据
    }
}

houseTable = null;
function initHouseTree2() {
    if (houseTable == null) {
        houseTable = $("#inhouse_list").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,
            "bProcessing": true,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/inventory/warehouse/queryWarehousePage.action",  //异步请求地址
            "aoColumns": [
                {
                    "mDataProp": "warehouseName", "mRender": function (data, type, full) {
                    return '<label name="warehouseName">' + data + '</label><input type="hidden" value="' + full.warehouseId + '"/>';
                }
                },
                {"mDataProp": "warehouseNo", "sClass": "center"},
                {"mDataProp": "warehouseAddress", "sClass": "center"},
                {"mDataProp": "createDate", "sClass": "center"},
                {
                    "mDataProp": "warehouseStatus", "mRender": function (data, type, full) {
                    var goodsStatus = '';
                    if (data == '0') {
                        goodsStatus = '<span style="cursor:pointer;"  class="label label-sm label-success">正常</span>';
                    }
                    else if (data == '1') {
                        goodsStatus = '<span style="cursor:pointer;"  class="label label-sm label-error">禁用</span>';
                    }

                    return goodsStatus;
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
                    "aTargets": [0],
                    "bVisible": true,
                },//这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
                {
                    "sDefaultContent": '', "aTargets": ['_all'],
                }
            ],
            "fnServerData": retrieveHouseData2,
        });
    } else {
        houseTable.fnDraw(); //重新加载数据
    }
}


/**
 * sSource   查询链接
 * aoData    参数
 * fnCallback 返回数据填充方法
 */
function retrieveHouseData(sSource, aoData, fnCallback) {
    //商品名称或编号
    var keyword = $("#keyword").val();
    var param = {"keyword": keyword};
    var orgId = $("#orgId").val();
    param.orgId = orgId;
//    var zTree = $.fn.zTree.getZTreeObj("org_tree");
//    if (zTree != null) {
//        if (zTree.getSelectedNodes() != "") {
//            var id = zTree.getSelectedNodes()[0].id;
//            var pid = zTree.getSelectedNodes()[0].pid;
//            param.id = id;
//            param.pid = pid;
//        }
//    }
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

/**
 * sSource   查询链接
 * aoData    参数
 * fnCallback 返回数据填充方法
 */
function retrieveHouseData2(sSource, aoData, fnCallback) {
    //商品名称或编号
    var keyword = $("#keyword").val();
    var param = {"keyword": keyword};
    var zTree2 = $.fn.zTree.getZTreeObj("org2_tree");
    if (zTree2 != null) {
        if (zTree2.getSelectedNodes() != "") {
            var id = zTree2.getSelectedNodes()[0].id;
            var pid = zTree2.getSelectedNodes()[0].pid;
            param.id = id;
            param.pid = pid;
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
//            removeMask($('.goods-container'));
        }
    });
}


function searchTree2() {
    if (houseTable) {
        houseTable.fnDraw();
    }
}

function searchTree() {
    if (uTable) {
        uTable.fnDraw();
    }
}
//初始化地区树
function initDictTable() {
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: function () {
                searchTree();
                searchTree2();
            }
        }
    };
    var treeObj = $("#org_tree");
    var zNodes;
    $.ajax({
        url: ROOT_PATH + '/view/inventory/warehouse/getMulSubDictDataByPcode.action',
        type: 'POST',
        data: {"dictPcode": "AL_POSITION", "dictLevel": "5"},
        dataType: 'json',
        success: function (data) {
            zNodes = data;
            $.fn.zTree.init($("#org_tree"), setting, zNodes);
            $.fn.zTree.init($("#org2_tree"), setting, zNodes);
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


//初始化商品树
function initOrgTree() {
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        view: {
            dblClickExpand: false
        },
        callback: {
            onClick: function () {
                searchGoods();
            }
        }
    };

    var treeObj = $("#gt_tree");
    var zNodes;

    $.ajax({
        url: ROOT_PATH + '/view/goods/goodsInfo/getMulSubGoodsTypeDataByPcode.action',
        type: 'POST',
        data: {"dictPcode": "AL_POSITION", "dictLevel": "4"},
        dataType: 'json',
        success: function (data) {
            zNodes = data;
            $.fn.zTree.init($("#gt_tree"), setting, zNodes);
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


function choiseInventory(input_goodsName) {
    resetSelectHouse();
    $('#outhouse_choise_modal').modal('show');
}

function choiseInventory2(input_goodsName) {
    resetSelectHouse2();
    $('#inhouse_choise_modal').modal('show');
}
//加载入库仓库数据
function resetSelectHouse() {
    initHouseTree();
    initDictTable();
}
//加载入库仓库数据
function resetSelectHouse2() {
    initHouseTree2();
    initDictTable();
}

//加载商品数据
function resetSelectGoods() {
    initTable();
    initOrgTree();
}

//初始化商品列表
gTable = null;
function initTable() {
    if (gTable == null) {
        gTable = $("#search_goods_table").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,
//			"bProcessing": false,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/inventory/allotOrder/queryStorageGoodsPage.action", //异步请求地址 ：查询站点信息
            "aoColumns": [
                {
                    "sDefaultContent": "",

                    "sClass": "center",
                    "bSortable": false,
                    "mRender": function (data, type, full) {
                        return '<label class="position-relative">' +
                            '<input type="checkbox" class="ace" value="' + full.goodsInfo.goodsId + '"/><span class="lbl"></span></label>';
                    }
                },
                {
                    "mDataProp": "goodsInfo.goodsName", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="goodsName">' + data + '</label>';
                }
                },
                {
                    "mDataProp": "goodsInfo.goodsTypeName", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="goodsTypeName" style="text-align: center">' + data + '</label>';
                }
                },
                {
                    "mDataProp": "goodsInfo.goodsBarCode", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="goodsBarCode">' + data + '</label>';
                }
                },
                {
                    "mDataProp": "goodsInfo.goodsStatus", "sClass": "center", "mRender": function (data, type, full) {
                    var goodsStatus = '';
                    if (data == '0') {
                        goodsStatus = '<span style="cursor:pointer;"  class="label label-sm label-success">正常</span>';
                    }
                    else if (data == '1') {
                        goodsStatus = '<span style="cursor:pointer;"  class="label label-sm label-error">禁用</span>';
                    }

                    return goodsStatus;
                }
                },

            ],
            "aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
                {"bSortable": false, "aTargets": [0, 1, 2, 3, 4]},
                {
                    "sDefaultContent": '', "aTargets": ['_all'],
                }//这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
            ],

            "fnServerData": retrieveData,

        });
    } else {
        gTable.fnDraw(); //重新加载数据
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
    var keyword = $("#goodsKey").val();
    var param = {
        "keyword": keyword
    };
    var inventoryId = $("#inventoryId").val();
    param.warehouseId = inventoryId;
    //商品规格
    var selectNodes = $.fn.zTree.getZTreeObj("gt_tree");
    if (selectNodes != null) {
        if (selectNodes.getSelectedNodes() != "") {
            var id = selectNodes.getSelectedNodes()[0].id;
            var pid = selectNodes.getSelectedNodes()[0].pid;
            var level = selectNodes.getSelectedNodes()[0].level;
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
    if (gTable) {
        gTable.fnDraw();
    }
}


//表单提交验证
function check() {
    var flag = true;
    $("form :input.required").each(function () {
        var val = $(this).val();
        if (!val) {
            $(this).addClass("required-tip");
            flag = false;
        }
    });
    if (!flag) {
        showErrorTips("信息填写错误，请核对修改", 3000);
    }
    var delivery_address = $("#province").find("option:selected").text()
        + $("#city").find("option:selected").text()
        + $("#detail_address").val();
    $("#delivery_address").val(delivery_address);
    if (flag) {
        $("#form_action_btns").find("button").addClass("disabled");
        $("#form_action_btns").find("a").addClass("disabled");
    }
    return flag;
}

function showErrorTips(msg, time) {
    if ($('#top_alert span').text().length > 0) {
        $('#top_alert').empty().append('<span>' + msg + '</span>');
        $('#top_alert').css('display', 'block');
    } else {
        $('.main-content').prepend('<div id="top_alert"><span>' + msg + '</span></div>');
    }
    $('#top_alert').fadeOut(time);
}

//单个选择站点
function choiseGoods(input_goodsName) {
    initOrgTree();
    resetSelectGoodsForm();
    var $ele_tr = $(input_goodsName).parents("tr:eq(0)"); //得到选择的行数
    $("#purchase_goods_rowno").val($ele_tr.find("td").eq(0).html());
    $("#choise_type").val('0');
    //隐藏列表第一列
    gTable.fnSetColumnVis(0, false);
    //隐藏底部批量操作的按钮
    $("#choise_goods_action").hide();
    $('#goods_choise_modal').modal('show');
}

//批量选择站点
function batchChoiseGoods() {
    if ($("#inventoryId").val() == '') {
        alert("请先选择出库仓库");
        return;
    }
    initOrgTree();
    resetSelectGoodsForm();
    $("#choise_type").val('1');
    //显示列表第一列
    gTable.fnSetColumnVis(0, true);
    $("#choise_goods_action").show();
    $('#goods_choise_modal').modal('show');
}


//批量追加站点到表单
function batchAppendGoods() {
    var $purchase_goods_list = $("#purchase_goods_list");
    var $pgl_fr = $purchase_goods_list.find("tr").eq(0);  //找到第一行
    var fr_goodsName = $pgl_fr.find("td").eq(1).find("input[type='hidden']").val(); //取第一列单元格goodsname的值
    var rowCount = $purchase_goods_list.find("tr").length; //得到行数
    if (!fr_goodsName) {
        rowCount = rowCount - 1;
        $pgl_fr.remove();
    }
    //遍历查询站点列表，添加选择的站点到表单显示页面
    $("#search_goods_table td input:checkbox:checked").each(function () {
        var goodsId = $(this).val();
        if (!existPurchaseGoods(goodsId)) {
            var $choise_row = $(this).closest('tr');
            rowCount = rowCount + 1;
            var append_row_no = rowCount;
            var append_row = createPurchaseGoodsRow(append_row_no);
            var $choiseGoods = getChoiseGoods($choise_row);
            $purchase_goods_list.append(append_row);
            fillGoodsInfo(append_row_no, $choiseGoods);
        }
    });
    $("#choise_submit").addClass("disabled");
    $("#goods_choise_modal").modal("hide");
    focusNumbox();
    alert("添加成功!");

}
//批量添加追加行数
function createPurchaseGoodsRow(append_row_no) {
    return '<tr>'
        + '<td class="center">' + append_row_no + '</td>'
        + '<td ><input type="hidden" id="goodsId" class="center" name="storageAllotDt[' + (append_row_no - 1) + '].goodsId"/><input type="text" id="goodsName" class="center" style="border-left: 0;border-right: 0;border-top: 0;border-bottom: 0px;color:#222222;font-size:13px;"></td>'
        + '<td class="center"></td>'
        + '<td ><input type="text" class="center" id="expectNum" name="storageAllotDt[' + (append_row_no - 1) + '].expectNumber"/></td>'
        + '<td ><input type="hidden" class="center"/>'
        + '<a href="javascript:void(0)" onclick="removeGoodsItem(this)">移除</a></td>'
        + '</tr>';
}

//获得选择站点各项值
function getChoiseGoods(ele_goodsRow) {
    var $choiseGoods = {};
    var $ele_td = ele_goodsRow.find("td");
    $choiseGoods.goodsId = $ele_td.find("input[type=checkbox]").eq(0).val();
    $choiseGoods.goodsName = $ele_td.find("label[name='goodsName']").eq(0).html();
    $choiseGoods.goodsBarCode = $ele_td.find("label[name='goodsBarCode']").eq(0).html();
    return $choiseGoods;
}

//是否已存在 已选中的站点
function existPurchaseGoods(goodsId) {
    var flag = false;
    $("#purchase_goods_list").find("tr").each(function () {
        var purchase_goodsId = $(this).find("input[type=hidden]").eq(0).val();
        if (goodsId == purchase_goodsId) {
            flag = true;
        }
    });
    return flag;
}

//填充站点信息
function fillGoodsInfo(purchaseGoodsRowNo, $choiseGoods) {
    var $ele_tr = $("#purchase_goods_list").find("tr").eq(purchaseGoodsRowNo - 1);
    $ele_tr.find("td").eq(1).find("input[type=hidden]").val($choiseGoods.goodsId);
    $ele_tr.find("td").eq(1).find("input[type=text]").val($choiseGoods.goodsName);
    $ele_tr.find("td").eq(2).html($choiseGoods.goodsBarCode);

}

//移除选择的站点信息
function removeGoodsItem(tdObj) {
    var itemCount = $("#purchase_goods_list").find("tr").length;
    var $ele_tr = $(tdObj).parents("tr:eq(0)");
    if (itemCount == 1) {
        $ele_tr.find("td").eq(1).find("input").val('');
        $ele_tr.find("td").eq(2).html("");
        $ele_tr.find("td").eq(3).find("input").val('');
    } else {
        $(tdObj).parents("tr").remove();
    }

    $("#purchase_goods_list").find("tr").each(function (i) {
        var goodsId = "storageAllotDt[" + i + "].goodsId";
        var num = "storageAllotDt[" + i + "].expectNumber";
        $(this).find("input[id='goodsId']").eq(0).attr("name", goodsId);
        $(this).find("input[id='expectNum']").eq(0).attr("name", num);
    });

    $("#purchase_goods_list").find("tr").each(function (i) {
        $(this).find("td").eq(0).html(i + 1);
    });

}

//重置站点选择窗口
function resetSelectGoodsForm() {
    initTable();
    $("#search-form input").val("");
    $("#search_goods_table input:checkbox").removeAttr("checked");

}

//提交非空验证
function checkForm() {
    var result = true;
    var inventoryName = $("#inventoryName").val();
    var inventoryName2 = $("#inventoryName2").val();
    var checkEmpty = $("#checkEmpty").val();
    if (!inventoryName) {
        alert("出库仓库不能为空！");
        return false;
    }
    else if (!inventoryName2) {
        alert("入库仓库不能为空！");
        return false;
    }
    else if (typeof(checkEmpty) != 'undefined') {
        if (checkEmpty) {
            alert("商品不能为空！");
            return false;
        }
    }

    $("#purchase_goods_list").find("tr").each(function (i) {
        var expectNum = $(this).find("td input[id='expectNum']").eq(0).val();
        if (!expectNum) {
            alert("预计数量不能为空");
            result = false;
            return false;
        }
    });

    return result;
}

function cancelOp() {
    window.location.href = ROOT_PATH + "/view/inventory/allotOrderList.jsp";
}

