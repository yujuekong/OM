jQuery(function ($) {
    $("#submenu-menu-advert-user").addClass("active");
    $("#menu-advert").addClass("active");
    $("#menu-advert").addClass("open");

    initTable();

    $("#searchGoodsBtn").click(function () {
        searchAdvertStatus();
    });
    $("#type_keyword").keyup(function () {
        initTable();
    });
});

oTable = null;

function initTable() {
    if (oTable == null) {
        oTable = $("#advertInfo_list").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/advert/advertInfo/queryAdvertInfoPage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "advertTitle"},
                {"mDataProp": "auName"},
                {"mDataProp": "auLinktel"},
                {"mDataProp": "startDate"},
                {"mDataProp": "endDate"},
                {
                    "mDataProp": "payStatus", "mRender": function (data, type, full) {
                    var goodsStatus = '';
                    if (data == '0') {
                        goodsStatus = '<span style="cursor:pointer;"  class="label label-sm label-success">已结清</span>';
                    }
                    else if (data == '1') {
                        goodsStatus = '<span style="cursor:pointer;"  class="label label-sm label-error">未结清</span>';
                    }

                    return goodsStatus;
                }
                },
                {
                    "mDataProp": "advertStatus", "mRender": function (data, type, full) {
                    var goodsStatus = '';
                    if (data == '0') {
                        goodsStatus = '<input title="启用" class="ace ace-switch ace-switch-6" type="checkbox" checked="checked" onclick="changeAdvertStatus(\'' + full.advertInfoId + '\')" /><span class="lbl"></span>';
                    }
                    else if (data == '1') {
                        goodsStatus = '<input title="禁用" class="ace ace-switch ace-switch-6" type="checkbox" onclick="changeAdvertStatus(\'' + full.advertInfoId + '\')"/><span class="lbl"></span>';
                    }
                    return goodsStatus;
                }
                },
                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="queryAdvertDeviceDt(\'' + full.advertInfoId + '\')" class="btn btn-xs btn-success">投放设备</button>'
                    ;
                    actionDiv += '</div>';
                    return actionDiv;
                }
                },
                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="queryAdvertInfoDt(\'' + full.advertInfoId + '\')" class="btn btn-xs btn-success" title="查询"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
                        '<button onclick="addOrModifyAdvertInfo(\'' + full.advertInfoId + '\')" class="btn btn-xs btn-info" title="修改"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
                        '<button onclick="deleteAdvertInfo(\'' + full.advertInfoId + '\')" class="btn btn-xs btn-danger" title="删除"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
            "aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
                {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6, 7, 8]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
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
    var advertInfoStatus = $("#advertInfoStatus").val();
    var advertFeeStatus = $("#advertFeeStatus").val();
    var orgId = $("#orgId").val();
    var proviceId = $("#proviceId").val();
    var regionId = $("#regionId").val();
    param.advertInfoStatus = advertInfoStatus;
    param.advertFeeStatus = advertFeeStatus;
    param.orgId = orgId;
    param.proviceId = proviceId;
    param.regionId = regionId;
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
function searchAdvertStatus() {
    if (oTable) {
        oTable.fnDraw();
    }
}

//查询广告内容详情
function queryAdvertInfoDt(id) {

    window.location.href = ROOT_PATH + "/view/advert/advertInfo/queryAdvertInfoDt.action?advertInfoId=" + id;
}

//删除广告内容
function deleteAdvertInfo(id) {

    if (confirm("确定删除？")) {
        window.location.href = ROOT_PATH + "/view/advert/advertInfo/deleteAdvertInfo.action?advertInfoId=" + id;
    }
}

//增加和修改广告
function addOrModifyAdvertInfo(id) {
    if (id == '') {
        window.location.href = ROOT_PATH + "/view/advert/advertInfo/addOrModifyAdvertInfo.action";
    } else {
        window.location.href = ROOT_PATH + "/view/advert/advertInfo/addOrModifyAdvertInfo.action?advertInfoId=" + id;
    }
}

//搜索广告内容
function searchAdvertInfo() {
    var advertInfoTitle = $("#advertInfoDt").val();
    window.location.href = ROOT_PATH + "/view/advert/advertInfo/searchAdvertInfo.action?advertInfoTitle=" + advertInfoTitle;
}

//改变广告状态
function changeAdvertStatus(id) {
    if (confirm("确定修改？")) {
        window.location.href = ROOT_PATH + "/view/advert/advertInfo/changeAdvertStatus.action?advertInfoId=" + id;
    }
    else {
        window.location.href = ROOT_PATH + "/view/advert/adInfoList.jsp";
    }
}
//广告投放设备管理
function queryAdvertDeviceDt(id) {

    window.location.href = ROOT_PATH + "/view/advert/advertPosition/queryDeviceInfoByAd.action?advertInfoId=" + id;
}
