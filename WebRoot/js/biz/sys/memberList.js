jQuery(function ($) {
    $("#submenu-menu-advert-user").addClass("active");
    $("#menu-advert").addClass("active");
    $("#menu-advert").addClass("open");
    initTable();
    $("#house_keyword").keyup(function () {
        initTable();
    });
});

var oTable = null;
function initTable() {
    if (oTable == null) {
        oTable = $("#member_list").dataTable({
            "bAutoWidth": false,//自动宽度
            "bLengthChange": false,//是否显示一个每页长度的选择条（需要分页器支持）
            "bProcessing": false,//以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息
            "bFilter": false,//过滤功能
            "bInfo": true,//页脚信息
            //"sPaginationType": "two_button",
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/sys/sysMemberInfo/querySysMemberPage.action",  //异步请求地址
            "aoColumns": [{"mDataProp": "memberId"},
                          {"mDataProp": "memberNo"},
                          {"mDataProp": "memberName"},
                          {"mDataProp": "memberLevel"},
                          {"mDataProp": "memberTel"},
                          {"mDataProp": "memberMail"},
                          {"mDataProp": "wxNo"},
                          {"mDataProp": "payNo"},
			              {"sDefaultContent": "","sClass":"center","mRender": function(data, type, full) { 
			            	   var actionDiv = '';
			            	   actionDiv = '<div class="hidden-sm hidden-xs btn-group">'+
			            	   '<button onclick="sysMemberS(\'' + full.memberId + '\')" class="btn btn-xs btn-success"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
		                       '<button class="btn btn-xs btn-info" onclick="sysMemberE(\'' + full.memberId + '\')"><i class="ace-icon fa fa-pencil bigger-120"></i></button>'+
			            	   '<button class="btn btn-xs btn-danger" onclick="sysMemberD(\'' + full.memberId + '\')"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
                "sSearch": "会员账号搜索",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast": "末页"
                }
            },
            "aoColumnDefs": [{//和aoColums类似，但他可以给指定列附近爱属性
                "bSortable": false, "aTargets": [0, 1, 2, 3,4,5,6,7,8],//这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
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
	var sSearch = $("#house_keyword").val();
    //有效起始日期
	var startDate = $("#dp_quoteStartDate").val();
	//有效终止日期
	var endDate = $("#dp_quoteEndDate").val();
	var param = {
			"sSearch":sSearch,
			"startDate":startDate,
			"endDate":endDate
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
   // window.location.href = ROOT_PATH + "/view/goods/goodsInfo/queryGoodsInfoDt.action?goodsInfoId=" + id;
}

//查看会员个人信息
function sysMemberS(id){
	window.location.href = ROOT_PATH +"/view/sys/sysMemberInfo/editorSysMemberById.action?memberId="+id+"&view=view";
}
//编辑会员个人信息
function sysMemberE(id){
	window.location.href = ROOT_PATH +"/view/sys/sysMemberInfo/editorSysMemberById.action?memberId="+id+"&view=editor";
}
//删除
function sysMemberD(id){
	if (confirm("确定删除此会员信息吗?")) {
		window.location.href = ROOT_PATH +"/view/sys/sysMemberInfo/deleteSysMemberById.action?memberId="+id;
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

    var treeObj = $("#org_tree");
    var zNodes;

    $.ajax({
        url: ROOT_PATH + '/view/sale/saleInfo/getMulSubDictDataByPcode.action',
        type: 'POST',
        data: {"dictPcode": "AL_POSITION"},
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

/**
 * 快捷搜索
 * @param type 搜索类型
 */
function quickSearch(type) {
	var startDate = "";
	var endDate = "";
	if(type == 1) { //本周
		startDate = DateUtil.dateToStr('yyyy-MM-dd',DateUtil.weekStartDate());
		endDate = DateUtil.dateToStr('yyyy-MM-dd',DateUtil.weekEndDate());
	} else if(type == 2){ //上周
		startDate = DateUtil.dateToStr('yyyy-MM-dd',DateUtil.lastWeekStartDate());
		endDate = DateUtil.dateToStr('yyyy-MM-dd',DateUtil.lastWeekEndDate());
	} else if(type == 3){ //本月
		startDate = DateUtil.dateToStr('yyyy-MM-dd',DateUtil.monthStartDate());
		endDate = DateUtil.dateToStr('yyyy-MM-dd',DateUtil.monthEndDate());
	} else if(type == 4){ //上月
		startDate = DateUtil.dateToStr('yyyy-MM-dd',DateUtil.lastMonthStartDate());
		endDate = DateUtil.dateToStr('yyyy-MM-dd',DateUtil.lastMonthEndDate());
	}
	$("#dp_quoteStartDate").val(startDate);
	$("#dp_quoteEndDate").val(endDate);
	initTable();
}