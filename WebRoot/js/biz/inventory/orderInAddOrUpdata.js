jQuery(function ($) {
    $('#orderIn').bootstrapValidator({
        message: 'This value is not valid',
        submitButtons: null,
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'warehousingEntry.bizType': {
                validators: {
                    notEmpty: {}
                }
            },
            sellerName: {
                validators: {
                    notEmpty: {}
                }
            },
            houseName: {
                validators: {
                    notEmpty: {}
                }
            },
            'warehousingEntry.requestDate': {
                validators: {
                    notEmpty: {}
                }
            },
        }
    });
    $("#keyword").keyup(function () {
        initGoodsTable();
    });

    //单个追加供应商到申购单
    $(document).on("dblclick", "#seller_list tbody tr", function () {
        var $choiseSeller = getChoiseSellerInfo($(this));
        var a = $("#sellerId").val($choiseSeller.sellerId);
        $("#sellerName").val($choiseSeller.sellerName);
        $("#orderIn")
            .data('bootstrapValidator')
            .updateStatus('sellerName', 'NOT_VALIDATED')
            .validateField('sellerName');
        $("#seller_choise_modal").modal('hide');
        //focusNumbox();
    });
    //单个追加仓库到申购单
    $(document).on("dblclick", "#house_list tbody tr", function () {
        var $choiseUser = getChoiseHouseInfo($(this));
        var a = $("#houseId").val($choiseUser.warehouseId);
        $("#houseName").val($choiseUser.warehouseName);
        //主动验证
        $("#orderIn")
            .data('bootstrapValidator')
            .updateStatus('houseName', 'NOT_VALIDATED')
            .validateField('houseName');
        $("#house_choise_modal").modal('hide');
        //focusNumbox();
    });
    $("#warehousingEntry_warehousingDate").datetimepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
    });

    //$("#orderIn")
    //    .data('bootstrapValidator')
    //    .updateStatus('houseName', 'NOT_VALIDATED')
    //    .validateField('houseName');
    $("#warehousingEntry_warehousingDate").change(function () {
        $("#orderIn")
            .data('bootstrapValidator')
            .updateStatus('warehousingEntry.requestDate', 'NOT_VALIDATED')
            .validateField('warehousingEntry.requestDate');
    }),
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

    //单个追加站点到 表单
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
    $("#search").keyup(function () {
        $("#warehouseId").val($(this).val());
        initHouseTree();
    })
});
//获得选择供应商对象
function getChoiseSellerInfo(ele_goodsRow) {
    var $choiseSeller = {};
    var $ele_td = ele_goodsRow.find("td");
    $choiseSeller.sellerId = $ele_td.find("label[name='sellerId']").eq(0).html();
    $choiseSeller.sellerName = $ele_td.find("label[name='sellerName']").eq(0).html();
    return $choiseSeller
}
//获得选择仓库对象
function getChoiseHouseInfo(ele_goodsRow) {
    var $choiseUser = {};
    var $ele_td = ele_goodsRow.find("td");
    $choiseUser.warehouseId = $ele_td.find("label[name='warehouseId']").eq(0).html();
    $choiseUser.warehouseName = $ele_td.find("label[name='warehouseName']").eq(0).html();
    return $choiseUser
}
////获得选择商品对象
//function getChoiseGoodsInfo(ele_goodsRow) {
//    var $choiseUser = {};
//    var $ele_td = ele_goodsRow.find("td");
//    $choiseUser.goodsId = $ele_td.find("label[name='goodsId']").eq(0).html();
//    $choiseUser.goodsName = $ele_td.find("label[name='goodsName']").eq(0).html();
//    return $choiseUser
//}

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
        uTable = $("#house_list").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,
            "bProcessing": false,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/inventory/warehouse/queryWarehousePage.action",  //异步请求地址
            "aoColumns": [
                {
                    "mDataProp": "warehouseName", "mRender": function (data, type, full) {
                    return '<label name="warehouseName">' + data + '</label><div hidden="hidden"><label name="warehouseId">' + full.warehouseId + '</label></div>';
                }
                },
                {"mDataProp": "warehouseAddress", "sClass": "center"},
                {"mDataProp": "warehouseSize", "sClass": "center"},
                {"mDataProp": "createDate", "sClass": "center"},
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
                    "bSortable": false, "aTargets": [0, 1, 2, 3]
                },
                {
                    "aTargets": [0],
                    "bVisible": true,
                }//这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
            ],
            "fnServerData": retrieveDataHouse,
        });
    } else {
        uTable.fnDraw(); //重新加载数据
    }
}
/**
 * sSource   查询链接
 * aoData    参数
 * fnCallback 返回数据填充方法
 * 备注：方法的3个参数名称不能改变
 */
function retrieveDataHouse(sSource, aoData, fnCallback) {
    //商品名称或编号
    var keyword = $("#warehouseId").val();
    //商品规格
    var goodsSpell = $("#goodsSpell").val();
    //用户所属公司
    var orgId = $("#orgId").val();
    //alert(orgId);
    var param = {
        "keyword": keyword,
        "goodsSpell": goodsSpell,
        "orgId": orgId,
    };
    /*var selectNodes = $.fn.zTree.getZTreeObj("goodstype_tree").getSelectedNodes();
     if(selectNodes.length > 0) {
     param.goodsTypeNo = selectNodes[0].key;
     }*/
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


oTable = null;
function initSellerTree() {
    if (oTable == null) {
        oTable = $("#seller_list").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,
            "bProcessing": true,
            "bFilter": false,// 搜索栏
            //"sPaginationType": "two_button",
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/seller/sellerInfo/querySellerPage.action",  //异步请求地址
            "aoColumns": [
                {
                    "mDataProp": "sellerName", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="sellerName">' + data + '</label><div hidden="hidden"><label name="sellerId">' + full.sellerId + '</label></div>';
                }
                },
                {"mDataProp": "sellerNo", "sClass": "center"},
                {"mDataProp": "linkMan", "sClass": "center"},
                {"mDataProp": "linkTel", "sClass": "center"},
                {"mDataProp": "createDate", "sClass": "center"},
                {
                    "mDataProp": "sellerStatus", "sClass": "center", "mRender": function (data, type, full) {
                    var goodsStatus = '';
                    if (data == '0') {
                        goodsStatus = '<button onclick="modifygoodsStatus(\'' + full.sellerId + '\')" class="label label-sm label-success">正常</button>';
                    }
                    else if (data == '1') {
                        goodsStatus = '<button onclick="modifygoodsStatus(\'' + full.sellerId + '\')" class="label label-sm label-error">禁用</button>';
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
            //和aoColums类似，但他可以给指定列附近爱属性
            "aoColumnDefs": [{
                "bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5],//1-7列不排序
            }],
            "fnServerData": retrieveDataSeller,
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
function retrieveDataSeller(sSource, aoData, fnCallback) {
    //商品名称或编号
    var keyword = $("#keyword").val();
    //商品规格
    var goodsSpell = $("#goodsSpell").val();
    //用户所属公司
    var orgId = $("#orgId").val();
    //alert(orgId);
    var param = {
        "keyword": keyword,
        "goodsSpell": goodsSpell,
        "orgId": orgId,
    };
    /*var selectNodes = $.fn.zTree.getZTreeObj("goodstype_tree").getSelectedNodes();
     if(selectNodes.length > 0) {
     param.goodsTypeNo = selectNodes[0].key;
     }*/
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

//单个选择商品
function choiseDevice(input_goodsName) {
    resetSelectDevice();
    $('#goods_choise_modal').modal('show');
}

//加载设备数据
function resetSelectDevice() {
    initGoodsTypeTree();
    initTable();
}


//初始化地区树
function initDictTable() {
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        }
    };
    var treeObj = $("#org_tree");
    var zNodes;
    $.ajax({
        url: ROOT_PATH + '/view/inventory/warehouse/getMulSubDictDataByPcode.action',
        type: 'POST',
        data: {"dictPcode": "AL_POSITION", "dictLevel": 4},
        dataType: 'json',
        success: function (data) {
            zNodes = data;
            $.fn.zTree.init($("#org_tree"), setting, zNodes);
            $.fn.zTree.init($("#org1_tree"), setting, zNodes);
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
        data: {"dictPcode": "AL_POSITION", "dictLevel": 4},
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

function choiseSeller(input_goodsName) {
    resetSelectSeller();
    $('#seller_choise_modal').modal('show');
}

function choiseHouse(input_goodsName) {
    resetSelectHouse();
    $('#house_choise_modal').modal('show');
}

//加载仓库数据
function resetSelectHouse() {
    initHouseTree();
    initDictTable();
}
//加载供应商数据
function resetSelectSeller() {
    initSellerTree();
    initDictTable();
}
//加载商品数据
function resetSelectGoods() {
    initTable();
    initOrgTree();
}

//初始化商品列表
gTable = null;
function initGoodsTable() {
    if (gTable == null) {
        gTable = $("#search_goods_table").dataTable({
            "bAutoWidth": true,
            "bLengthChange": false,
//			"bProcessing": false,
            "bFilter": false,// 搜索栏
            "iDisplayLength": "10",
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/goods/goodsInfo/queryGoodsInfoPage.action", //异步请求地址 ：查询站点信息
            "aoColumns": [
                {
                    "sDefaultContent": "",

                    "sClass": "center",
                    "bSortable": false,
                    "mRender": function (data, type, full) {
                        return '<label class="position-relative">' +
                            '<input type="checkbox" class="ace" value="' + full.goodsId + '"/><span class="lbl"></span></label>';
                    }
                },
                {
                    "mDataProp": "goodsName", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="goodsName">' + data + '</label>';
                }
                },
                {
                    "mDataProp": "goodsTypeNo", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="goodsTypeNo" style="text-align: center">' + data + '</label>';
                }
                },
                {
                    "mDataProp": "unitName", "sClass": "center", "mRender": function (data, type, full) {
                    if (data != null) {
                        return '<label name="unitName">' + data + '</label>';
                    } else {
                        return '<label name="unitName">无</label>';

                    }
                }
                },
                {
                    "mDataProp": "goodsSpell", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="goodsSpell">' + data + '</label>';
                }
                },

            ],
            "aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
                {
                    "bSortable": false, "aTargets": [0, 1, 2, 3, 4]
                },
                {
                    "sDefaultContent": '', "aTargets": [0, 1, 2, 3, 4]
                }
                //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
            ],
            "fnServerData": retrieveDataGoods,

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
function retrieveDataGoods(sSource, aoData, fnCallback) {
    //商品名称或编号
    var keyword = $("#keyword").val();
    var sellerId = $("#sellerId").val();
    //商品规格
    var param = {
        "keyword": keyword,
        "sellerId": sellerId,
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
    if ($("#bizType").find("option:selected").text() == '请选择') {
        alert("请先选择业务类型");
        return;
    }
    var bizTypeText = $("#bizType").find("option:selected").text();
    if (bizTypeText == '供应商入库' && $("#sellerId").val() == '') {
        alert("请先选择供应商");
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
    //$("#goods_choise_modal").modal('hide');
    focusNumbox();
    alert("添加成功!");

}
//批量添加追加行数
function createPurchaseGoodsRow(append_row_no) {
    return '<tr>'
        + '<td class="center">' + append_row_no + '</td>'
        + '<td ><input type="hidden" class="center" name="warehousingDtls[' + (append_row_no - 1) + '].goodsId"/><input type="text" class="center" disabled></td>'
        + '<td ><input type="number" class="center" name="warehousingDtls[' + (append_row_no - 1) + '].requestCount" min="0"/></td>'
        + '<td ><input type="text" class="center" style="text-align: center; width: 500px" name="warehousingDtls[' + (append_row_no - 1) + '].remark"/></td>'
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
    //$choiseGoods.goodsName = $ele_td.find("label[name='goodsName']").eq(0).html()
    //$choiseGoods.goodsTypeNo = $ele_td.find("label[name='goodsTypeNo']").eq(0).html()
    //$choiseGoods.measurementUnit = $ele_td.find("label[name='measurementUnit']").eq(0).html()
    //$choiseGoods.goodsSpell = $ele_td.find("label[name='goodsSpell']").eq(0).html()
    //$choiseGoods.nodeSort = $ele_td.find("label[name='nodeSort']").eq(0).html()
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
    ///*$ele_tr.find("td").eq(2).html($choiseGoods.carLineId);*/
    //$ele_tr.find("td").eq(2).html($choiseGoods.goodsName);
    //$ele_tr.find("td").eq(3).html($choiseGoods.goodsLsNo);
    //$ele_tr.find("td").eq(4).html($choiseGoods.goodsTypeNo);
    //$ele_tr.find("td").eq(5).html($choiseGoods.measurementUnit);
    //$ele_tr.find("td").eq(6).html($choiseGoods.goodsSpell);

}

//移除选择的站点信息
function removeGoodsItem(tdObj) {
    var itemCount = $("#purchase_goods_list").find("tr").length;
    var $ele_tr = $(tdObj).parents("tr:eq(0)");
    if (itemCount == 1) {
        $ele_tr.find("td").eq(1).find("input").val('');
        $ele_tr.find("td").eq(2).find("input").val('');
        $ele_tr.find("td").eq(3).find("input").val('');
        $ele_tr.find("td").eq(4).find("input").val('');
        $ele_tr.find("td").eq(5).find("input").val('');
        //$ele_tr.find("td").eq(6).html('');
        //$ele_tr.find("td").eq(7).html('');
        //$ele_tr.find("td").eq(8).html('');
        //$ele_tr.find("td").eq(9).html('');

    } else {
        $(tdObj).parents("tr").remove();
    }
    $("#purchase_goods_list").find("tr").each(function (i) {
        $(this).find("td").eq(0).html(i + 1);
        $(this).find("input").eq(0).attr("name", "warehousingDtls[" + i + "].goodsId");
        $(this).find("input").eq(2).attr("name", "warehousingDtls[" + i + "].requestCount");
        $(this).find("input").eq(3).attr("name", "warehousingDtls[" + i + "].remark");
    });

}

//重置站点选择窗口
function resetSelectGoodsForm() {
    initGoodsTable();
    $("#search-form input").val("");
    $("#search_goods_table input:checkbox").removeAttr("checked");

}


var agentType = $("#agentType").val();
$.ajax({
    type: "post",
    url: ROOT_PATH + "/view/inventory/warehousing/getAllBizType.action",
    data: {"dictCode": "BIZ_TYPE"},
    success: function (msg) {
        var str = "";
        var json = eval("(" + msg + ")");
        for (var i = 0; i < json.length; i++) {
            var str = "";
            if (json[i].dictId == agentType) {
                str = "<option value='" + json[i].dictId + "' selected='selected'>" + json[i].dictName + "</option>";
            } else {
                str = "<option value='" + json[i].dictId + "'>" + json[i].dictName + "</option>";
            }
            $("#bizType").append(str);
        }
        getTypeName();
    },
    error: function (e) {
        console.error("e: ", e);
    }
});

function getTypeName() {
    var typeName = $("#goodsTypeSelect").find("option:selected").text();
    $("#agentTypeName").val(typeName);
}

$("#bizType").change(function () {
    var bizType = $(this).find("option:selected").text();
    if (bizType == "供应商入库") {
        $("#sellerName").removeAttr("disabled");
        $("#divSeller").show();
    } else {
        $("#divSeller").hide();
        $("#sellerId").val('');
        $("#sellerName").val('');
        $("#sellerName").attr("disabled", "disabled");
    }
});

//function checkFrom() {
//    var houseName = $("#houseName");
//    var warehousingDate = $("#warehousingEntry_warehousingDate");
//    var sellerName = $("#sellerName");
//    var sellerName = $("#sellerName");
//    var bizType = $("#bizType");
//    if (bizType.val() == '0') {
//        $("#typeValidate").replaceWith("<label id='typeValidate' style='color: red'>请选择业务类型</label>");
//        return false;
//    } else {
//        $("#typeValidate").replaceWith("<label id='typeValidate'></label>")
//    }
//    if (houseName.val() == '') {
//        return false;
//    }
//    if (warehousingDate.val() == '') {
//        return false;
//    }
//    if (sellerName.val() == '') {
//        return false;
//    }
//    return true;
//}
$("#submitBtn").click(function () {
    var check = checkTable($("#goods_list"));
    if (check) {
        //alert($("#orderIn").serialize());
        $("#orderIn").submit();
    } else {
        return false;
    }
});
//表单检查
function checkTable($table) {
    if ($("tbody tr", $table).html()) {
        var check = true;
        $("tbody tr", $table).each(function (index, element) {
            //trString = '{';
            $("input[type='number']", $(this)).each(function (indexTd, element) {
                var count = $(this).val();
                if (count == "0" || count == null || count == "") {
                    alert("入库商品数量格式有误");
                    check = false;
                    return false;
                }
            });
            if (!check) {
                return false;
            }
        });
        return check;
    }
}