jQuery(function ($) {
    $("#submenu-menu-device-clean").addClass("active");
    $("#menu-device").addClass("active");
    $("#menu-device").addClass("open");

    $("#form").submit(function () {
        var submit = true;
        var required = $(":input[class=required]");
        $.each(required, function (idx, obj) {
            $(obj).parent().children().remove("span");
            if ($(obj).val()) {

            } else {
                $(obj).parent().append("<span style='color: red'>不能为空！</span>");
                submit = false;
                return false;

            }
        });
        var tr = $("#purchase_goods_list").find("tr");
        $.each(tr, function (idx, obj) {
            if ($(obj).children().eq(2).find(":input").eq(0).val()) {

            } else {
                alert("出库数量不能为空");
                submit = false;
                return false;
            }
        });
        return submit;
    })

    //单个追加用户到交接用户
    $(document).on("dblclick", "#checkUser_list tbody tr", function () {
        var $choiseSeller = getChoiseUserInfo($(this));
        var a = $("#checkUserId").val($choiseSeller.userId);
        $("#checkUser").val($choiseSeller.realName);
        $("#checkUser_choise_modal").modal('hide');
        //focusNumbox();
    });
    //单个追加用户到清洗用户
    $(document).on("dblclick", "#cleanUser_list tbody tr", function () {
        var $choiseSeller = getChoiseUserInfo($(this));
        var a = $("#cleanUserId").val($choiseSeller.userId);
        $("#cleanUser").val($choiseSeller.realName);
        $("#cleanUser_choise_modal").modal('hide');
        //focusNumbox();
    });

    //单个追加仓库到申购单
    $(document).on("dblclick", "#house_list tbody tr", function () {
        var $choiseUser = getChoiseHouseInfo($(this));
        var a = $("#houseId").val($choiseUser.warehouseId);
        $("#houseName").val($choiseUser.warehouseName);
        $("#house_choise_modal").modal('hide');
        //focusNumbox();
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


    //单个追加设备到表单
    $(document).on("dblclick", "#search_device_table tbody tr", function () {
        var $choiseDevice = getChoiseDeviceInfo($(this));
        var sid = $("#deviceName").val();
        $("#" + sid + "").val($choiseDevice.deviceName);
        $("#" + "deviceId" + sid + "").val($choiseDevice.deviceId);
        $("#device_choise_modal").modal('hide');
        focusNumbox();
    });


    $("#storageDeliveryOrder_deliveryDate").datetimepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,

    });

    $("#storageDeliveryOrder_requestDate").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        minView: 0,
        forceParse: 0,

    });


    //批量选择时，单个checkbox选中改变背景颜色
    $(document).on("click", "#search_goods_table td input:checkbox", function () {
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

//获得选择用户对象
function getChoiseUserInfo(ele_goodsRow) {
    var $choiseSeller = {};
    var $ele_td = ele_goodsRow.find("td");
    $choiseSeller.userId = $ele_td.find("label[name='userId']").eq(0).html();
    $choiseSeller.realName = $ele_td.find("label[name='realName']").eq(0).html();
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

//获得选择设备对象
function getChoiseDeviceInfo(ele_goodsRow) {
    var $choiseDevice = {};
    var $ele_td = ele_goodsRow.find("td");
    $choiseDevice.deviceId = $ele_td.find("label[name='deviceId']").eq(0).html();
    $choiseDevice.deviceName = $ele_td.find("label[name='deviceName']").eq(0).html();
    return $choiseDevice
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
        uTable = $("#house_list").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,
            "bProcessing": true,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/inventory/warehouse/queryWarehousePage.action",  //异步请求地址
            "aoColumns": [
                {
                    "mDataProp": "warehouseName", "mRender": function (data, type, full) {
                    return '<label name="warehouseName">' + data + '</label><div hidden><label name="warehouseId">' + full.warehouseId + '</label></div>';
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
                }  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
            ],
            "fnServerData": retrieveData,
        });
    } else {
        uTable.fnDraw(); //重新加载数据
    }
}

oTable = null;
function initCheckUserTree() {
    if (oTable == null) {
        oTable = $("#checkUser_list").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,
            "bProcessing": true,
            "sClass": "center",
            "bFilter": false,// 搜索栏
            //"sPaginationType": "two_button",
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/sys/sysUser/querySysUserPage.action",  //异步请求地址
            "aoColumns": [
                {
                    "mDataProp": "userName", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="userName">' + data + '</label><div hidden><label hidden="hidden" name="userId">' + full.userId + '</label></div>';
                }
                },
                {
                    "mDataProp": "realName", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="realName">' + data + '</label>';
                }
                },
                {"mDataProp": "tel", "sClass": "center"},
                {"mDataProp": "email", "sClass": "center"},
                //{
                //    "mDataProp": "userStatus", "mRender": function (data, type, full) {
                //    var goodsStatus = '';
                //    if (data == '0') {
                //        goodsStatus = '<button onclick="modifygoodsStatus(\'' + full.userId + '\')" class="label label-sm label-success">正常</button>';
                //    }
                //    else if (data == '1') {
                //        goodsStatus = '<button onclick="modifygoodsStatus(\'' + full.userId + '\')" class="label label-sm label-error">禁用</button>';
                //    }
                //    return goodsStatus;
                //}
                //},
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
                "bSortable": false, "aTargets": [0, 1, 2, 3],//1-7列不排序
            }],
            "fnServerData": retrieveData,
        });


    } else {
        oTable.fnDraw(); //重新加载数据
    }

}

sTable = null;
function initCleanUserTree() {
    if (sTable == null) {
        sTable = $("#cleanUser_list").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,
            "bProcessing": true,
            "bFilter": false,// 搜索栏
            //"sPaginationType": "two_button",
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/sys/sysUser/querySysUserPage.action",  //异步请求地址
            "aoColumns": [
                {
                    "mDataProp": "userName", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="userName">' + data + '</label><label hidden="hidden" name="userId">' + full.userId + '</label>';
                }
                },
                {
                    "mDataProp": "realName", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="realName">' + data + '</label>';
                }
                },
                {"mDataProp": "tel", "sClass": "center",},
                {"mDataProp": "email", "sClass": "center",},
                //{
                //    "mDataProp": "userStatus", "mRender": function (data, type, full) {
                //    var goodsStatus = '';
                //    if (data == '0') {
                //        goodsStatus = '<button onclick="modifygoodsStatus(\'' + full.userId + '\')" class="label label-sm label-success">正常</button>';
                //    }
                //    else if (data == '1') {
                //        goodsStatus = '<button onclick="modifygoodsStatus(\'' + full.userId + '\')" class="label label-sm label-error">禁用</button>';
                //    }
                //    return goodsStatus;
                //}
                //},
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
                "bSortable": false, "aTargets": [0, 1, 2, 3],//1-7列不排序
            }],
            "fnServerData": retrieveData,
        });


    } else {
        oTable.fnDraw(); //重新加载数据
    }

}


dTable = null;
function initDeviceTable() {
    if (dTable == null) {
        dTable = $("#search_device_table").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,
            "bProcessing": false,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/device/deviceInfo/queryDeviceInfo.action",  //异步请求地址
            "aoColumns": [
                {
                    "mDataProp": "deviceNo", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="deviceNo">' + data + '</label><label hidden="hidden" name="deviceId">' + full.deviceId + '</label>';
                }
                },
                {
                    "mDataProp": "deviceName", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="deviceName">' + data + '</label>';
                }
                },
                {
                    "mDataProp": "deviceType.deviceTypeName",
                    "sClass": "center",
                    "mRender": function (data, type, full) {
                        return '<label name="deviceType">' + data + '</label>';
                    }
                },
                {
                    "mDataProp": "createDate", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="createDate">' + data + '</label>';
                }
                },
                //{
                //    "mDataProp": "userStatus", "mRender": function (data, type, full) {
                //    var goodsStatus = '';
                //    if (data == '0') {
                //        goodsStatus = '<button onclick="modifygoodsStatus(\'' + full.userId + '\')" class="label label-sm label-success">正常</button>';
                //    }
                //    else if (data == '1') {
                //        goodsStatus = '<button onclick="modifygoodsStatus(\'' + full.userId + '\')" class="label label-sm label-error">禁用</button>';
                //    }
                //    return goodsStatus;
                //}
                //},
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
                "bSortable": false, "aTargets": [0, 1, 2, 3],//1-7列不排序
            }],
            "fnServerData": retrieveData,
        });


    } else {
        dTable.fnDraw(); //重新加载数据
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
//搜索商品
function searchGoods() {
    if (oTable) {
        oTable.fnDraw();
    }
}

//单个选择商品
function choiseGoods(input_goodsName) {
    resetSelectDevice();
    $('#goods_choise_modal').modal('show');
}

//加载设备数据
function resetSelectGoods() {
    initGoodsTypeTree();
    initTable();
}

function showAgentMenu(e) {
    var menuId = e.id;
    $("#agent").val(e.id);
    initUnitTree();
    var gtObj = $("#" + menuId);
    if ($("#agent_combobox").css("display") == "none") {
        var gtOffset = $("#" + menuId).offset();
        $("#agent_combobox").css({
            width: gtObj.css("width"),
            left: gtOffset.left + "px",
            top: gtOffset.top + gtObj.outerHeight() + "px"
        }).slideDown("fast");
    }

    $("body").bind("mousedown", onBodyDown);
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
            $.fn.zTree.init($("#org2_tree"), setting, zNodes);
            $.fn.zTree.init($("#org3_tree"), setting, zNodes);
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

//初始化处理类型树
function initUnitTree() {
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
            onClick: function (event, treeId, treeNode) {
                var id = $("#agent").val();
                $("#" + id).val(treeNode.name);
                $("#agent" + id).val(treeNode.id);
                hideUnitMenu();
            }
        }
    };

    var treeObj = $("#agent_tree");
    var zNodes;

    $.ajax({
        url: ROOT_PATH + '/view/inventory/deliveryOrder/getMulSubGoodsAgentDataByPcode.action',
        type: 'POST',
        data: {"dictPcode": "OUT_AGENT_METHOD", "dictLevel": 3},
        dataType: 'json',
        success: function (data) {
            zNodes = data;
            $.fn.zTree.init($("#agent_tree"), setting, zNodes);
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

function hideUnitMenu() {
    $("#agent_combobox").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
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

function choiseCheckUser(input_goodsName) {
    resetSelectCheckUser();
    $('#checkUser_choise_modal').modal('show');
}

function choiseCleanUser(input_goodsName) {
    $('#cleanUser_choise_modal').modal('show');
    resetSelectCleanUser();
}

function choiseHouse(input_goodsName) {
    resetSelectHouse();
    $('#house_choise_modal').modal('show');
}

function choiseDevice(e) {
    $("#deviceName").val(e.id);
    resetSelectDevice();
    $('#device_choise_modal').modal('show');
}

//加载仓库数据
function resetSelectHouse() {
    initHouseTree();
    initDictTable();
}
//加载交接用户数据
function resetSelectCheckUser() {
    initCheckUserTree();
    initDictTable();
}
//加载清洗用户数据
function resetSelectCleanUser() {
    initCleanUserTree();
    initDictTable();
}
//加载商品数据
function resetSelectGoods() {
    initTable();
    initOrgTree();
}
//加载设备数据
function resetSelectDevice() {
    initDeviceTable();
    initDictTable();
}


gTable = null;

//初始化站点列表
function initTable() {
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
                /* {"mDataProp":"carLineId","mRender": function(data, type, full) {
                 return '<label name="carLineId">'+ data + '</label>';
                 }},*/
                {
                    "mDataProp": "goodsTypeNo", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label  name="gtName">' + data + '</label>';
                }
                },
                {
                    "mDataProp": "unitName", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="measurementUnit">' + data + '</label>';
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
    var keyword = $("#keyword").val();
    //商品规格
    var goodsSpell = $("#goodsSpell").val();
    var orgId = $("#orgId").val();

    var param = {
        "keyword": keyword,
        "goodsSpell": goodsSpell,
        "orgId": orgId
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
    $("#choise_submit").addClass("disabled");
    //$("#goods_choise_modal").modal('hide');
    focusNumbox();
}
//批量添加追加行数
function createPurchaseGoodsRow(append_row_no) {
    return '<tr>'
        + '<td class="center">' + append_row_no + '</td>'
        + '<td ><input type="hidden" class="center" name="storageDeliveryDtls[' + (append_row_no - 1) + '].goodsId"/><input type="text" class="center"></td>'
        + '<td ><input type="text" class="center" style="text-align: center; width: 200px" name="storageDeliveryDtls[' + (append_row_no - 1) + '].requestCount"/></td>'
        + '<td ><input type="text" class="center" style="text-align: center; width: 500px" name="storageDeliveryDtls[' + (append_row_no - 1) + '].remark"/></td>'
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
        //$ele_tr.find("td").eq(6).html('');
        //$ele_tr.find("td").eq(7).html('');
        //$ele_tr.find("td").eq(8).html('');
        //$ele_tr.find("td").eq(9).html('');

    } else {
        $(tdObj).parents("tr").remove();
    }
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