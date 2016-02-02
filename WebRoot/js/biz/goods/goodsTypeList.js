jQuery(function ($) {
    $("#submenu-menu-advert-user").addClass("active");
    $("#menu-advert").addClass("active");
    $("#menu-advert").addClass("open");

    initTable();
    initOrgTree();
    $("#searchGoodsBtn").click(function () {
        searchGoods();
    });
    $("#type_keyword").keyup(function () {
        initTable();
    });
});

oTable = null;

function initTable() {
    if (oTable == null) {
        oTable = $("#goodsType_list").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            "bFilter": false,//过滤功能
            "bInfo": true,//页脚信息
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/goods/goodsType/queryGoodsTypePage.action",  //异步请求地址
            "aoColumns": [
                {
                    "mDataProp": "dgtId", "mRender": function (data, type, full) {
                    if (data == null) {
                        return "无上级商品类别";
                    } else {
                        return full.goodsType.gtName
                    }
                }
                },
                {"mDataProp": "gtNo"},
                {"mDataProp": "gtName"},

                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="queryGoodsTypeDt(\'' + full.gtId + '\')" class="btn btn-xs btn-success"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
                        '<button onclick="addOrModifyGoodsType(\'' + full.gtId + '\')" class="btn btn-xs btn-info"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
                        '<button onclick="deleteGoodsType(\'' + full.gtId + '\')" class="btn btn-xs btn-danger"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
                "sSearch": "搜索",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast": "末页"
                }
            },
            "aoColumnDefs": [
                {
                    "bSortable": false, "aTargets": [0, 1, 2, 3]
                },
                //{
                //    "aTargets": [0], "mRender": function (data, type, full) {
                //    if (data == null) {
                //        return "无上级商品类别";
                //    } else {
                //        return full.goodsTypeName
                //    }
                //}
                //}
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
    var keyword = $("#type_keyword").val();
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
function queryGoodsTypeDt(id) {
    window.location.href = ROOT_PATH + "/view/goods/goodsType/queryGoodsTypeDt.action?goodsTypeId=" + id;
}
function addOrModifyGoodsType(id) {
    var url = ROOT_PATH + "/view/goods/goodsType/addOrModifyGoodsType.action?goodsTypeId=" + id;
    window.location.href = url;
}
function deleteGoodsType(id) {
    if (confirm("确定删除?")) {
        window.location.href = ROOT_PATH + "/view/goods/goodsType/deleteGoodsType.action?goodsTypeId=" + id;
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