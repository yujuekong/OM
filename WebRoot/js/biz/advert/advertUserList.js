jQuery(function ($) {
    $("#submenu-menu-advert-user").addClass("active");
    $("#menu-advert").addClass("active");
    $("#menu-advert").addClass("open");

    initTable();

    $("#searchGoodsBtn").click(function () {
        searchAdvert();
    });
    $("#type_keyword").keyup(function () {
        initTable();
    });
});

oTable = null;

function initTable() {
    if (oTable == null) {
        oTable = $("#advertUser_list").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,
            "bFilter": false,// 搜索栏
//            "bInfo": true,//页脚信息
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/advert/advertUser/queryAdvertUserPage.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "auName"},
                {"mDataProp": "auLinkaddress"},
                {"mDataProp": "auLinkman"},
                {"mDataProp": "auLinktel"},
                {"mDataProp": "auLevel"},
                {
                    "mDataProp": "auStatus", "mRender": function (data, type, full) {
                    var goodsStatus = '';
                    if (data == '0') {
                        goodsStatus = '<input title="启用" class="ace ace-switch ace-switch-6" type="checkbox" checked="checked"  onclick="changeStatus(\'' + full.advertUserId + '\')" /><span class="lbl"></span>';
                    }
                    else if (data == '1') {
                        goodsStatus = '<input title="禁用" class="ace ace-switch ace-switch-6" type="checkbox" onclick="changeStatus(\'' + full.advertUserId + '\')" /><span class="lbl"></span>';
                    }
                    return goodsStatus;
                }
                },
                {
                    "sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="queryAdvertUserDt(\'' + full.advertUserId + '\')" class="btn btn-xs btn-success" title="查询"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
                        '<button onclick="addOrModifyAdvertUser(\'' + full.advertUserId + '\')" class="btn btn-xs btn-info" title="修改"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
                        '<button onclick="deleteAdvertUser(\'' + full.advertUserId + '\')" class="btn btn-xs btn-danger" title="删除"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
                {
                    "bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6],
                    "searchable": false,
                    "orderable": false,
                    "targets": 0
                }  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
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
    var advertUserStatus = $("#advertUserStatus").val();
    var keyword = $("#type_keyword").val();
    var param = {"keyword": keyword};
    var orgId = $("#orgId").val();
    var proviceId = $("#proviceId").val();
    var regionId = $("#regionId").val();
    param.advertUserStatus = advertUserStatus;
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
function searchAdvert() {
    if (oTable) {
        oTable.fnDraw();
    }
}

//查询广告主信息
function queryAdvertUserDt(id) {
    window.location.href = ROOT_PATH + "/view/advert/advertUser/queryAdvertUserDt.action?advertUserId=" + id;
}

//删除广告主
function deleteAdvertUser(id) {
    if (confirm("确定删除？")) {
        window.location.href = ROOT_PATH + "/view/advert/advertUser/deleteAdvertUser.action?advertUserId=" + id;
    }
}

//增加和修改广告主
function addOrModifyAdvertUser(id) {
    if (id == '' || id == undefined || id == null) {
        window.location.href = ROOT_PATH + "/view/advert/advertUser/saveOrModifyAdvertUser.action";
    } else {
        window.location.href = ROOT_PATH + "/view/advert/advertUser/saveOrModifyAdvertUser.action?advertUserId=" + id;
    }
}

//搜索广告主
function searchAdvertUser() {
    var adUserName = $("#searchAdUser").val();
    window.location.href = ROOT_PATH + "/view/advert/advertUser/searchAdvertUser.action?auName=" + adUserName;
}

//改变广告状态
function changeStatus(id) {
    if (confirm("确定修改？")) {
        window.location.href = ROOT_PATH + "/view/advert/advertUser/changeAdvertStatus.action?advertUserId=" + id;
    }
    else {
        window.location.href = ROOT_PATH + "/view/advert/adUserList.jsp";
    }

}
