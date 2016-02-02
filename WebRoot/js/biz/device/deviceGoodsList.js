jQuery(function ($) {
    $("#submenu-menu-device-info").addClass("active");
    $("#menu-device").addClass("active");
    $("#menu-device").addClass("open");
    initTable();
    initOrgTree();
    //$("#searchGoodsBtn").click(function () {
    //    searchDevice();
    //});
    $("#keyword").keyup(function () {
        oTable.fnDraw();
    });

//	 $('#deviceInfo_list tbody').on('click','tr td:nth-child(3)', function (e) {
//		   var name = $(this).text();
//		    alert(name);
//		} );
});

oTable = null;
function initTable() {
    if (oTable == null) {
        oTable = $("#deviceInfo_list").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,
            "bProcessing": false,
            "bInfo": true,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/device/deviceGoods/queryDeviceGoodsByDevice.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "deviceNo", "sClass": "center"},
                {"mDataProp": "deviceName", "sClass": "center"},
                {"mDataProp": "deviceAddress", "sClass": "center"},
                {
                    "sDefaultContent": "", "sClass": "center", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="DDtl(\'' + full.deviceId + '\')" class="btn btn-xs btn-success" style="text-align: center"><i style="text-align: center" class="ace-icon fa fa-search-plus bigger-120">格子明细</i></button>';
                    actionDiv += '</div>';
                    return actionDiv;
                }
                },
                {
                    "sDefaultContent": "", "sClass": "center", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button class="btn btn-xs btn-info" onclick="modifyDeviceInfo(\'' + full.deviceId + '\')"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
                        '<button class="btn btn-xs btn-danger" onclick="delDeviceGoods(\'' + full.deviceId + '\')"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
                "zeroRecords": "没有内容",
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
                    sDefaultContent: '',
                    aTargets: ['_all']
                },
                {"bSortable": false, "aTargets": [0, 1, 2, 3, 4]},  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
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
    var keyword = $("#keyword").val();
    //商品状态
    var deviceStatus = $("#status_sele").val();
    var param = {
        "keyword": keyword
    };
    param.deviceStatus = deviceStatus;
    var selectNodes = $.fn.zTree.getZTreeObj("org_tree");
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
//            removeMask($('.goods-container'));
        }
    });
}
//搜索商品
function searchDevice() {
    if (oTable) {
        oTable.fnDraw();
    }
}

function modifyDeviceInfo(id) {
    window.location.href = ROOT_PATH + "/view/device/deviceGoods/addOrModifydeviceGoods.action?deviceId=" + id;
}
function delDeviceGoods(id) {
    if (confirm("确定删除此条设备信息吗?")) {
        window.location.href = ROOT_PATH + "/view/device/deviceGoods/delDeviceGoodsById.action?deviceId=" + id;
    }
}

function add(id) {
    window.location.href = ROOT_PATH + "/view/device/deviceGoods/addOrModifydeviceGoods.action?deviceId=" + id;
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
                searchDevice();
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

function show() {
    var selectNodes = $.fn.zTree.getZTreeObj("org_tree").getSelectedNodes();
}

function DDtl(deviceId) {
    gezi(deviceId);

}
function gezi(deviceId) {
    var devNo = "";
    if (deviceId) {
        $.ajax({
            type: "POST",
            url: ROOT_PATH + "/view/device/deviceGoods/geZiDtl.action",
            data: {"deviceId": deviceId},
            success: function (data) {
                var json = eval("(" + data + ")");
                for (var i = 0; i < json.length; i++) {
                    devNo = "-" + i;
                    $("#lab" + i).text(json[0].deviceNo + devNo);
                    $("#goodsName" + i).val(json[i].goodsName);

                }
                $('#goods_modal').modal('show');
            }
        });
    }


}
gTable = null;
function initGoodsTable() {
    if (gTable == null) {
        gTable = $("#goods_list").dataTable({
            "bAutoWidth": false,
            "bLengthChange": true,
            "bInfo": true,
            "bProcessing": false,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/device/deviceGoods/queryDeviceGoods.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "goodsName", "sClass": "center"},
                {"mDataProp": "sysDict.dictName", "sClass": "center"},
            ],
            "oLanguage": {
                "sProcessing": "正在加载中......",
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "没有数据！",
                "sEmptyTable": "表中无数据存在！",
                "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
                "sInfoEmpty": "显示0到0条记录",
                "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
                "zeroRecords": "没有内容",
                "sSearch": "搜索",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast": "末页"
                }
            },
            "aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
                {"bSortable": false, "aTargets": [0, 1]},  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
            ],
            "fnServerData": retrieveGoodsData,
        });
    } else {
        gTable.fnDraw(); //重新加载数据
    }
}

/**
 * sSource   查询链接
 * aoData    参数
 * fnCallback 返回数据填充方法
 */
function retrieveGoodsData(sSource, aoData, fnCallback) {
    //商品名称或编号
    var keyword = $("#keyword").val();
    var deviceId = $("#deviceId").val();
    var param = {"keyword": keyword, "deviceId": deviceId};
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