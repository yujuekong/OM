jQuery(function ($) {
    $("#submenu-menu-device-clean").addClass("active");
    $("#menu-device").addClass("active");
    $("#menu-device").addClass("open");
    //单个追加设备到申购单
    $(document).on("dblclick", "#goods_list tbody tr", function () {
        var $choiseSeller = getChoiseSellerInfo($(this));
        $("#goodsName").val($choiseSeller.goodsName);
        $("#goodsId").val($choiseSeller.goodsId);
        $("#goods_choise_modal").modal('hide');
    });

});

function getChoiseSellerInfo(ele_goodsRow) {
    var $choiseSeller = {};
    var $ele_td = ele_goodsRow.find("td");
    $choiseSeller.goodsName = $ele_td.find("label[name='goodsName']").eq(0).html();
    $choiseSeller.goodsId = $ele_td.find("input[name='goodsId']").eq(0).val();
    return $choiseSeller;
}


oTable = null;
function initTable() {
    if (oTable == null) {
        oTable = $("#goods_list").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,
            "bProcessing": true,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/goods/goodsInfo/queryGoodsInfoPage.action",  //异步请求地址
            "aoColumns": [
                {
                    "mDataProp": "goodsName", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="goodsName">' + data + '</label><input name="goodsId" type="hidden" value="' + full.goodsId + '"/>';
                }
                },
                {"mDataProp": "goodsTypeNo", "sClass": "center"},
                {"mDataProp": "unitName", "sClass": "center"},
                {"mDataProp": "goodsSpell", "sClass": "center"},
                //{
                //    "mDataProp": "goodsStatus", "mRender": function (data, type, full) {
                //    var goodsStatus = '';
                //    if (data == '0') {
                //        goodsStatus = '<button onclick="modifyGoodsStatus(\'' + full.sellerId + '\')" class="label label-sm label-success">正常</button>';
                //    }
                //    else if (data == '1') {
                //        goodsStatus = '<button onclick="modifyGoodsStatus(\'' + full.sellerId + '\')" class="label label-sm label-error">禁用</button>';
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
/**
 * sSource   查询链接
 * aoData    参数
 * fnCallback 返回数据填充方法
 */
function retrieveData(sSource, aoData, fnCallback) {
    //商品名称或编号
    var keyword = $("#keyword").val();
    var param = {
        "keyword": keyword
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
//搜索商品
function searchGoods() {
    if (oTable) {
        oTable.fnDraw();
    }
}

//单个选择商品
function chooseGoods(input_goodsName) {
    resetSelectGoods();
    $('#goods_choise_modal').modal('show');
}


//加载设备数据
function resetSelectGoods() {
    initGoodsTypeTree();
    initTable();
}

//初始化商品类别树
function initGoodsTypeTree() {
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
}