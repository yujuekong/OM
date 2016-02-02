jQuery(function ($) {
    $("#submenu-menu-advert-user").addClass("active");
    $("#menu-advert").addClass("active");
    $("#menu-advert").addClass("open");
    initOrgTree();
    $("#house_keyword").keyup(function () {
        initTable();
    });
});

// datatable 数据/
var oTable = null;
function initTable() {
    if (oTable == null) {
        oTable = $("#carStation_list").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            "bFilter": false,//过滤功能
            "bInfo": true,//页脚信息
            //"sPaginationType": "two_button",
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH +"/view/car/carLineNode/querycarLineNode.action",  //异步请求地址
            "aoColumns": [
             	{"mDataProp": "dictName","sClass":"center"},
             	{"mDataProp": "nodeNo","sClass":"center"},
                {"mDataProp": "districtNo","sClass":"center"},
                {"mDataProp": "districtName","sClass":"center"},
                {"mDataProp": "districtAddress","sClass":"center"},
                {"sDefaultContent": "", "mRender": function (data, type, full) {
                    var actionDiv = '';
                    actionDiv = '<div class="hidden-sm hidden-xs btn-group">' +
                        '<button onclick="queryCarlineNodeById(\'' + full.lineNodeId + '\')" class="btn btn-xs btn-success"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
                        '<button onclick="modifyCarlineNode(\'' + full.lineNodeId + '\')" class="btn btn-xs btn-info"><i class="ace-icon fa fa-pencil bigger-120"></i></button>' +
                        '<button onclick="deleteCarlineNode(\'' + full.lineNodeId + '\')" class="btn btn-xs btn-danger"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
                    actionDiv += '</div>';
                    return actionDiv;
                },"sClass":"center"
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
            "aoColumnDefs": [{//和aoColums类似，但他可以给指定列附近爱属性
                "bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5],//这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
                "searchable": false,
                "orderable": false,
                "targets": 0
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
    var keyword = $("#house_keyword").val();
    var param = {"keyword": keyword};

    var selectNodes = $.fn.zTree.getZTreeObj("org_tree");
    if(selectNodes!=null){
    	if(selectNodes.getSelectedNodes() != ""){
    		var id=selectNodes.getSelectedNodes()[0].id;       
            var pid=selectNodes.getSelectedNodes()[0].pid;  
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
function searchCarNodes() {
    if (oTable) {
        oTable.fnDraw();
    }
}
//查询详情
function queryCarlineNodeById(id) {
    window.location.href = ROOT_PATH +"/view/car/carLineNode/queryCarlineNodeById.action?lineNodeId=" + id;
}
//增加
function add(id) {
    window.location.href = ROOT_PATH +"/view/car/carLineNode/addOrModifyCarlineNode.action?lineNodeId=" + id;
}

function modifyCarlineNode(id) {
    window.location.href = ROOT_PATH +"/view/car/carLineNode/addOrModifyCarlineNode.action?lineNodeId=" + id;
}
//删除
function deleteCarlineNode(id) {
    if (confirm("确定删除?")) {
        window.location.href = ROOT_PATH +"/view/car/carLineNode/deleteCarlineNode.action?lineNodeId=" + id;
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
        callback: {
            onClick: function(){
            	searchCarNodes();
            }
        }
    };

    var treeObj = $("#org_tree");
    var zNodes;

    $.ajax({
        url: ROOT_PATH + '/view/inventory/warehouse/getMulSubDictDataByPcode.action',
        type:'POST',
        data:{"dictPcode" : "AL_POSITION" ,"dictLevel":"5"},
        dataType:'json',
        success:function(data){
            zNodes = data;
            $.fn.zTree.init($("#org_tree"), setting, zNodes);
        }
    });
    initTable();
}
