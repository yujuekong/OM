jQuery(function ($) {
    $("#submenu-submenu-menu-sys-org-user").addClass("active");
    $("#submenu-menu-sys-org").addClass("active");
    $("#submenu-menu-sys-org").addClass("open");
    $("#menu-sys").addClass("active");
    $("#menu-sys").addClass("open");

    initOrgTree();

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

    //商品选择列表checkbox全选，全不选
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


//初始化机构树
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
            beforeClick: beforeClick,
            onClick: function (event, treeId, treeNode) {
                var dictProvice = treeNode.getParentNode();
                var dictRegion = dictProvice.getParentNode();
                $("#dictOrgId").val(treeNode.id);
                $("#dictOrgName").val(treeNode.name);
                $("#dictRegionId").val(dictRegion.id);
                $("#dictRegionName").val(dictRegion.name);
                $("#dictProviceId").val(dictProvice.id);
                $("#dictProviceName").val(dictProvice.name);
                hideMenu();
            }
        }
    };

    var treeObj = $("#gt1_tree");
    var zNodes;

    $.ajax({
        url: ROOT_PATH + '/view/sys/sysDict/getMulSubDictDataByPcode.action',
        type: 'POST',
        data: {
            "dictPcode": "AL_POSITION",
            "dictLevel": "5"
        },
        dataType: 'json',
        success: function (data) {
            zNodes = data;
            $.fn.zTree.init($("#gt1_tree"), setting, zNodes);
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

function beforeClick(treeId, treeNode) {
    var check = (treeNode && treeNode.level == 3);
    if (!check) {
        alert("只能选择分公司...");
    }
    return check;
}


//区域下拉框
function showMenu() {
    var gtObj = $("#dictOrgName");
    if ($("#gt_combobox").css("display") == "none") {
        var gtOffset = $("#dictOrgName").offset();
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
//单位下拉框
function showUnitMenu(obj) {
    var objId = obj.prev();
    alert(objId);
    if ($("#gt_combobox").css("display") == "none") {
        var gtOffset = $("#userPro").offset();
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

var gTable = null;
function initGoodsTable() {
    if (gTable == null) {
        gTable = $("#search_goods_table").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,
//			"bProcessing": false,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/goods/goodsInfo/queryGoodsInfoPage.action", //异步请求地址 ：查询商品信息
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
                    "mDataProp": "goodsTypeName", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="goodsTypeName">' + data + '</label>';
                }
                },
                {
                    "mDataProp": "unitName", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="unitName">' + data + '</label><div hidden="hidden"><label name="unitId">' + full.measurementUnit + '</label></div>';
                }
                },
                {
                    "mDataProp": "goodsSpell", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="goodsSpell">' + data + '</label>';
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
                {"bSortable": false, "aTargets": [0, 1, 2, 3, 4]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
            ],
            "fnServerData": retrieveData,

        });
    } else {
//		oTable.fnClearTable(0); //清空数据
        gTable.fnDraw(); //重新加载数据
    }

}
/**
 * sSource   查询链接
 * aoData    参数
 * fnCallback 返回数据填充方法
 */
function retrieveData(sSource, aoData, fnCallback) {
    //商品名称或编号
    var keyword = $("#keyword").val();
    var param = {"keyword": keyword};


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

//单个选择商品
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

//批量选择商品
function batchChoiseGoods() {
    initGoodsTree();
    resetSelectGoodsForm();
    $("#choise_type").val('1');
    //显示列表第一列
    gTable.fnSetColumnVis(0, true);
    $("#choise_goods_action").show();
    $('#goods_choise_modal').modal('show');
}

//初始化商品树
function initGoodsTree() {
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

//批量追加商品到表单
function batchAppendGoods() {
    var $purchase_goods_list = $("#purchase_goods_list");
    var $pgl_fr = $purchase_goods_list.find("tr").eq(0);  //找到第一行
    var fr_goodsName = $pgl_fr.find("td").eq(1).find("input[type='hidden']").val(); //取第一列单元格goodsname的值
    var rowCount = $purchase_goods_list.find("tr").length; //得到行数
    if (!fr_goodsName) {
        rowCount = rowCount - 1;
        $pgl_fr.remove();
    }
    //遍历查询商品列表，添加选择的商品到表单显示页面
    $("#search_goods_table td input:checkbox:checked").each(function (i) {
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
    //$("#search_goods_table input:checkbox").removeAttr("checked");
    //$("#choise_submit").addClass("disabled");
    //$("#goods_choise_modal").modal('hide');
    focusNumbox();
    alert("添加成功！");

}
//批量添加追加行数
function createPurchaseGoodsRow(append_row_no) {
    return '<tr>'
        + '<td class="center">' + append_row_no + '</td>'
        + '<td ><input type="hidden" class="center" name="storageInventoryList[' + (append_row_no - 1) + '].goodsId"/><input type="text" class="center" disabled></td>'
        + '<td ><input type="text" class="center" name="storageInventoryList[' + (append_row_no - 1) + '].inventoryNumber"/></td>'
        + '<td ><input type="hidden" name="storageInventoryList[' + (append_row_no - 1) + '].goodsUnit"/><input type="text" class="center" disabled></td>'
            //+ '<td ><input type="hidden" class="center"/><input type="hidden" class="center" name="storageInventoryList[' + (append_row_no - 1) + '].sellerGoodsId"></td>'
        + '<td ><input type="hidden" class="center"/>'
        + '<a href="javascript:void(0)" onclick="removeGoodsItem(this)">移除</a></td>'
        + '</tr>';
}

//获得选择商品各项值
function getChoiseGoods(ele_goodsRow) {
    var $choiseGoods = {};
    var $ele_td = ele_goodsRow.find("td");
    $choiseGoods.goodsId = $ele_td.find("input[type=checkbox]").eq(0).val();
    $choiseGoods.goodsName = $ele_td.find("label[name='goodsName']").eq(0).html();
    $choiseGoods.unitName = $ele_td.find("label[name='unitName']").eq(0).html();
    $choiseGoods.unitId = $ele_td.find("label[name='unitId']").eq(0).html();
    //$choiseGoods.goodsTypeNo = $ele_td.find("label[name='goodsTypeNo']").eq(0).html()
    //$choiseGoods.measurementUnit = $ele_td.find("label[name='measurementUnit']").eq(0).html()
    //$choiseGoods.goodsSpell = $ele_td.find("label[name='goodsSpell']").eq(0).html()
    //$choiseGoods.nodeSort = $ele_td.find("label[name='nodeSort']").eq(0).html()
    return $choiseGoods;
}

//是否已存在 已选中的商品
function existPurchaseGoods(goodsId) {
    var flag = false;
    $("#purchase_goods_list").find("tr").each(function (i) {
        var purchase_goodsId = $(this).find("input[type=hidden]").eq(0).val();
        if (goodsId == purchase_goodsId) {
            flag = true;
        }
    });
    return flag;
}

//填充商品信息
function fillGoodsInfo(purchaseGoodsRowNo, $choiseGoods) {
    var $ele_tr = $("#purchase_goods_list").find("tr").eq(purchaseGoodsRowNo - 1);
    $ele_tr.find("td").eq(1).find("input[type=hidden]").val($choiseGoods.goodsId);
    $ele_tr.find("td").eq(1).find("input[type=text]").val($choiseGoods.goodsName);
    $ele_tr.find("td").eq(3).find("input[type=text]").val($choiseGoods.unitName);
    $ele_tr.find("td").eq(3).find("input[type=hidden]").val($choiseGoods.unitId);
}

//移除选择的商品信息
function removeGoodsItem(tdObj) {
    var itemCount = $("#purchase_goods_list").find("tr").length;
    var $ele_tr = $(tdObj).parents("tr:eq(0)");
    if (itemCount == 1) {
        $ele_tr.find("td").eq(1).find("input").val('');
        $ele_tr.find("td").eq(2).find("input").val('');
        $ele_tr.find("td").eq(3).find("input").val('');
        $ele_tr.find("td").eq(4).find("input").val('');
        $ele_tr.find("td").eq(5).find("input").val('');

    } else {
        $(tdObj).parents("tr").remove();
    }
    $("#purchase_goods_list").find("tr").each(function (i) {
        $(this).find("td").eq(0).html(i + 1);
    });

}

//重置商品选择窗口
function resetSelectGoodsForm() {
    initGoodsTable();
    $("#search-form input").val("");
    $("#search_goods_table input:checkbox").removeAttr("checked");
}

function check(obj) {
    if (obj.val() != '') {
        obj.attr('disabled ', 'disabled ');

    }
}

//function submitForm() {
//    //var check = checkTable($("#storage_inventory"));
//    //if(check){
//    //    $("#house").submit();
//    //}
//    $("#house").submit();
//}


//$("#house").submit(function () {
//    var flag = true;
//    $("input.required").each(function vailRequired() {
//        var obj = $(this);
//        if (obj.val() == null || obj.val() == '') {
//            $(obj.next().html("*不能为空"));
//            $(obj.parent().parent()).removeClass("has-success");
//            $(obj.parent().parent()).addClass("has-error");
//            flag = false;
//        } else {
//            $(obj.next().html(""));
//            $(obj.parent().parent()).removeClass("has-error");
//            $(obj.parent().parent()).addClass("has-success");
//        }
//    })
//    return flag;
//})

function showError(obj, msg) {
    obj.next().addClass("glyphicon glyphicon-remove").removeClass("glyphicon-ok").removeAttr("style");
    obj.parent().next().text(msg);
    obj.parent().parent().removeClass("has-success");
    obj.parent().parent().addClass("has-error");
}
function showOK(obj) {
    obj.next().addClass("glyphicon glyphicon-ok").removeClass("glyphicon-remove").removeAttr("style");
    obj.parent().next().text('');
    obj.parent().parent().removeClass("has-error");
    obj.parent().parent().addClass("has-success");
}


function vailForm(obj) {
    var flag = true;
    var obj = $(obj);
    var name = obj.attr("name");
    if (name == 'storageWarehouse.warehouseName') {
        if (obj.val() == null || obj.val() == '') {
            showError(obj, "*仓库名称不能为空");
            flag = false;
        } else {
            showOK(obj);
        }
    }
    if (name == 'storageWarehouse.warehouseSize') {
        if (obj.val() == null || obj.val() == '') {
            showError(obj, "*仓库大小不能为空");
            flag = false;
        } else {
            showOK(obj);
        }
    }
    if (name == 'storageWarehouse.warehouseAddress') {
        if (obj.val() == null || obj.val() == '') {
            showError(obj, "*仓库地址不能为空");
            flag = false;
        } else {
            showOK(obj);
        }
    }
    return flag;
}

$("#house").submit(function () {
    var flag = true;
    $("input.required").each(function () {
        if (!vailForm(this)) {
            flag = false;
        }
    });
    return flag;
});
//function send() {
//    var flag = true
//    $("input.required").each(function () {
//        if (!vailForm(this)) {
//            flag = false;
//        }
//    });
//    if (flag) {
//        $("#house").submit();
//    }
//}

//表单检查
function checkTable($table) {
    if ($("tbody tr", $table).html()) {
        //var tableArray = [], trString = '', tableString = '';
        var check = true;
        $("tbody tr", $table).each(function (index, element) {
            //trString = '{';
            $(":text", $(this)).each(function (indexTd, element) {
                if ($(this).val()) {
                    //trString += '"' + this.name + '":"' + $(this).val() + '",';
                } else {
                    alert('第' + (index + 1) + '行，第' + (indexTd + 1) + '列的值为空，请输入！');
                    //trString = '';
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