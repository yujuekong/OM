jQuery(function ($) {
    //$("#submenu-menu-advert-user").addClass("active");
    //$("#menu-advert").addClass("active");
    //$("#menu-advert").addClass("open");
    initTable();
    initOrgTree();
    $("#searchGoodsBtn").click(function () {
        searchGoods();
    });
    $("#keyword").keyup(function () {
        oTable.fnDraw()();
    });
    $('#goodsInfo_list').on('click', 'tr', function () {
        var data = oTable.row($(this).closest('tr')).data();
        alert(data.goodsName);
    });
});

var oTable = null;

function initTable() {
    if (oTable == null) {
        oTable = $("#goodsInfo_list").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            "searching": false,//过滤功能
            "ordering": false,
            "bInfo": true,//页脚信息
            //"sPaginationType": "two_button",
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/goods/goodsInfo/queryGoodsInfoPage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "goodsTypeNo"},
                {"mDataProp": "goodsName"},
                {"mDataProp": "unitName"},
                {"mDataProp": "goodsSpell"},
                {
                    "mDataProp": "goodsStatus", "mRender": function (data, type, full) {
                    var goodsStatus = '';
                    if (data == '1') {
                        goodsStatus = '<input title="启用" class="ace ace-switch ace-switch-6" type="checkbox" onclick="modifyGoodsStatus(this,' + full.goodsId + ')" /><span class="lbl"></span>';


                    }
                    else if (data == '0') {
                        goodsStatus = '<input title="禁用" class="ace ace-switch ace-switch-6" type="checkbox" onclick="modifyGoodsStatus(this,' + full.goodsId + ')" checked /><span class="lbl"></span>';


                    }
                    return goodsStatus;
                }
                },
                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="queryGoodsInfoDt(\'' + full.goodsId + '\')" class="btn btn-xs btn-success"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
                        '<button onclick="addOrModifyGoodsInfo(\'' + full.goodsId + '\')" class="btn btn-xs btn-info"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
                        '<button onclick="deleteGoodsInfo(\'' + full.goodsId + '\')" class="btn btn-xs btn-danger"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
                    actionDiv += '</div>';
                    return actionDiv;
                }
                }
            ],
            "language": {
                "sProcessing": "正在加载中......",
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "没有数据！",
                "sEmptyTable": "表中无数据存在！",
                "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
                "sInfoEmpty": "显示0到0条记录",
                "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast": "末页"
                }
            },
            "aoColumnDefs": [
                {
                    orderable: false, targets: [0, 1, 2, 3, 4, 5]
                }
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
 * 备注：方法的3个参数名称不能改变
 */
function retrieveData(sSource, aoData, fnCallback) {
    //商品名称或编号
    var keyword = $("#keyword").val();
    var param = {"keyword": keyword};
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
    if (oTable) {
        oTable.fnDraw();
    }
}
//查询详情
function queryGoodsInfoDt(id) {
    window.location.href = ROOT_PATH + "/view/goods/goodsInfo/queryGoodsInfoDt.action?goodsInfoId=" + id;
}
//增加或修改
function addOrModifyGoodsInfo(id) {
    window.location.href = ROOT_PATH + "/view/goods/goodsInfo/addOrModifyGoodsInfo.action?goodsInfoId=" + id;
}
//删除
function deleteGoodsInfo(id) {
    if (confirm("确定删除?")) {
        window.location.href = ROOT_PATH + "/view/goods/goodsInfo/deleteGoodsInfo.action?goodsInfoId=" + id;
    }
}

function modifyGoodsStatus(checkbox, goodsInfoId) {
    if (goodsInfoId) {
        var isDisable = null;
        if ($(checkbox).prop("checked")) {
            isDisable = '0';
        } else {
            isDisable = '1';
        }
        $.ajax({
            type: "POST",
            url: ROOT_PATH + "/view/goods/goodsInfo/modifyGoodsStatus.action",
            data: {"goodsInfoId": goodsInfoId, "isDisable": isDisable},
            success: function (data) {
                if (!data) {
                    alert("系统错误，请联系管理员!");
                }
                initTable();
            }
        });
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
        data: {"dictPcode": "AL_POSITION"},
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

    initTable();
}