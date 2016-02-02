jQuery(function ($) {
    $("#submenu-menu-advert-user").addClass("active");
    $("#menu-advert").addClass("active");
    $("#menu-advert").addClass("open");
    initTable();
    //initOrgTree();
    $("#type_keyword").keyup(function () {
        initTable();
    });
    
});

var oTable = null;

function initTable() {
    if (oTable == null) {
        oTable = $("#gameInfo_list").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            "bFilter": false,//过滤功能
            "bInfo": true,//页脚信息
            //"sPaginationType": "two_button",
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/sale/gameInfo/queryGameInfoPage.action",  //异步请求地址
            "aoColumns": [{"mDataProp": "1","sClass":"center"},
                          {"mDataProp": "7","sClass":"center"},
                          {"mDataProp": "4","sClass":"center"},
                          {"mDataProp": "2","sClass":"center"},
                          {"mDataProp": "3","sClass":"center"},
                          {
                              "mDataProp": "6","sClass":"center", "mRender": function (data, type, full) {
                              var goodsStatus = '';
                              if (data == '0') {
                                  goodsStatus = '<input title="启用" class="ace ace-switch ace-switch-6" type="checkbox" checked="checked"  onclick="changeStatus(\'' + full[0] + '\')" /><span class="lbl"></span>';
                              }
                              else if (data == '1') {
                                  goodsStatus = '<input title="禁用" class="ace ace-switch ace-switch-6" type="checkbox" onclick="changeStatus(\'' + full[0] + '\')" /><span class="lbl"></span>';
                              }
                              return goodsStatus;
                          }
                          },
                          {
                              "sDefaultContent": "", "mRender": function (data, type, full) {
                              var actionDiv = '';
                              actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                                  '<button onclick="querygamePrizeDt(\'' + full[0] + '\')" class="btn btn-xs btn-success">奖励管理</button>'
                              ;
                              actionDiv += '</div>';
                              return actionDiv;
                          }
                          },
                          {
                              "sDefaultContent": "","sClass":"center", "mRender": function (data, type, full) {
                              var actionDiv = '';
                              actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                                  /*'<button onclick="queryGameInfoDt(\'' + full.gameId + '\')" class="btn btn-xs btn-success" title="查询"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +*/
                                  '<button onclick="addOrModifyGameInfo(\'' + full[0] + '\')" class="btn btn-xs btn-info" title="修改"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
                                  '<button onclick="deleteGameInfo(\'' + full[0] + '\')" class="btn btn-xs btn-danger" title="删除"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
            "aoColumnDefs": [{//和aoColums类似，但他可以给指定列附近爱属性
                "bSortable": false, "aTargets": [0, 1, 2, 3,4,5,6,7],//这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
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
    var keyword = $("#orgId").val();
    var gameStatus = $("#gameStatus").val();
    var type_keyword = $("#type_keyword").val();
	var param = {"keyword":keyword};
	param.gameStatus = gameStatus;
	param.type_keyword = type_keyword;
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
function searchGameStatus() {
    if (oTable) {
        oTable.fnDraw();
    }
}

//查询活动信息
function queryGameInfoDt(id) {
    window.location.href = ROOT_PATH + "/view/sale/gameInfo/queryGameInfoDt.action?gameId=" + id;
}

//删除活动
function deleteGameInfo(id) {
    if (confirm("确定删除？")) {
        window.location.href = ROOT_PATH + "/view/sale/gameInfo/deleteGameInfo.action?gameId=" + id;
    }
}

//增加和修改活动
function addOrModifyGameInfo(id) {
    if (id == '' || id == undefined || id == null) {
        window.location.href = ROOT_PATH + "/view/sale/gameInfo/addOrModifyGameInfo.action";
    } else {
        window.location.href = ROOT_PATH + "/view/sale/gameInfo/addOrModifyGameInfo.action?gameId=" + id;
    }
}

//改变状态
function changeStatus(id) {
    if (confirm("确定修改？")) {
        window.location.href = ROOT_PATH + "/view/sale/gameInfo/changeGameStatus.action?gameId=" + id;
    }
    else {
        window.location.href = ROOT_PATH + "/view/sale/gameInfoList.jsp";
    }
}

//抽奖活动管理
function querygamePrizeDt(id){
	window.location.href = ROOT_PATH + "/view/sale/gameInfo/enterGamePrizePage.action?gameId=" + id;
}