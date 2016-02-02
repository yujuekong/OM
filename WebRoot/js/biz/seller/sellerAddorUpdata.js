jQuery(function ($) {
    $("#submenu-menu-device-clean").addClass("active");
    $("#menu-device").addClass("active");
    $("#menu-device").addClass("open");
    $("#keyword").keyup(function () {
        initGoodsTable();
    });
    //$('#seller').bootstrapValidator({
    //    message: 'This value is not valid',
    //    submitButtons: null,
    //    feedbackIcons: {
    //        valid: 'glyphicon glyphicon-ok',
    //        invalid: 'glyphicon glyphicon-remove',
    //        validating: 'glyphicon glyphicon-refresh'
    //    },
    //    fields: {
    //        'sellerInfo.sellerName': {
    //            validators: {
    //                notEmpty: {
    //                    message: "请输入长度为2-10的名称",
    //                },
    //                stringLength: {
    //                    min: 2,
    //                    max: 10,
    //                },
    //            }
    //        },
    //        'sellerInfo.linkMan': {
    //            validators: {
    //                notEmpty: {},
    //                stringLength: {
    //                    min: 2,
    //                    max: 4,
    //                },
    //            }
    //        },
    //        'sellerInfo.linkTel': {
    //            validators: {
    //                notEmpty: {},
    //                //regexp: {
    //                //    regexp: /^0?(13[0-9]|15[0-9]|17[0678]|18[0-9]|14[57])[0-9]{8}$/,
    //                //    message: '请输入正确的手机号'
    //                //}
    //            }
    //        },
    //        'sellerInfo.sellerAddress': {
    //            validators: {
    //                notEmpty: {},
    //            }
    //        }
    //    }
    //});


    initTree();
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
});

var gTable = null;
function initGoodsTable() {
    if (gTable == null) {
        gTable = $("#search_goods_table").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,
//			"bProcessing": false,
            "bFilter": false,// 搜索栏
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
    var param = {
        "keyword": keyword,
        //"orgFilter":"0"
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
    $("#choise_submit").addClass("disabled");
    focusNumbox();

}
//批量添加追加行数
function createPurchaseGoodsRow(append_row_no) {
    return '<tr>'
        + '<td class="center">' + append_row_no + '</td>'
        + '<td ><input type="hidden" class="center" name="sellerGoodsList[' + (append_row_no - 1) + '].goodsId"/><input type="text" class="center"></td>'
        + '<td ><input type="text" class="center" name="sellerGoodsList[' + (append_row_no - 1) + '].goodsArea" style="width: 400px" maxlength="30"/></td>'
        + '<td ><input type="text" class="center" style="text-align: center" name="sellerGoodsList[' + (append_row_no - 1) + '].goodsPrice" min="0" max="9999"/></td>'
        + '<td ><input type="hidden" class="center"/>'
        + '<a href="javascript:void(0)" onclick="removeGoodsItem(this)">移除</a></td>'
        + '</tr>';
}
function invalidPrice(inputelement) {
    if (inputelement.validity.patternMismatch) {
        inputelement.setCustomValidity('单价不符合');
    } else {
        inputelement.setCustomValidity(''); //输入内容符合验证条件
    }
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
    $("#purchase_goods_list").find("tr").each(function (i) {
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
    } else {
        $(tdObj).parents("tr").remove();
    }
    $("#purchase_goods_list").find("tr").each(function (i) {
        $(this).find("td").eq(0).html(i + 1);
        $(this).find("input").eq(0).attr("name", "sellerGoodsList[" + i + "].goodsId");
        $(this).find("input").eq(2).attr("name", "sellerGoodsList[" + i + "].goodsArea");
        $(this).find("input").eq(3).attr("name", "sellerGoodsList[" + i + "].goodsPrice");

    });

}

//重置站点选择窗口
function resetSelectGoodsForm() {
    initGoodsTable();
    $("#search-form input").val("");
    $("#search_goods_table input:checkbox").removeAttr("checked");
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


//初始化机构树
function initTree() {
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
                $("#orgStrId").val(treeNode.id);
                $("#regionStrId").val(treeNode.pid);
                $("#userPro").val(treeNode.name);
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
            "dictLevel": "4"
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
    var check = (treeNode && !treeNode.isParent);
    if (!check) alert("只能选择省份...");
    return check;
}


function searchGoods() {

}

function showMenu() {
    var gtObj = $("#userPro");
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

$("#seller").submit(function () {
    var check = true;
    var tr = $("#purchase_goods_list").find("tr");
    $.each(tr,function(idx,obj){
        var goods = $(obj).children().eq(1).find(":input").val();
        var address = $(obj).children().eq(2).find(":input").val();
        var price = $(obj).children().eq(3).find(":input").val();
        if(goods==''){
            alert("商品不能为空");
            check = false;
            return false;
        }
        if(address==''){
            alert("商品产地不能为空");
            check = false;
            return false;
        }
        var reg = new RegExp("^[0-9]+(.[0-9]{2})?$", "g");
        if (!reg.test(price)) {
            alert("请输入单价，最多只能有两位小数！");
            check = false;
            return false;
        }
        //console.log(idx);
    });
    return check;
});
