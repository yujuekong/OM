jQuery(function($) {
	initTable();
	initOrgTree();
    $("#searchGoodsBtn").click(function () {
        searchDevice();
    });
    $("#type_keyword").keyup(function () {
        initTable();
    });
});

oTable = null;
function initTable() {
	if(oTable == null) {
		oTable = $("#deviceInfo_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": false,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH +"/view/sys/contract/queryContractPage.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"contractNo","sClass":"center"},
			               {"mDataProp":"contractName","sClass":"center"},				               
			               {"mDataProp":"startDate","sClass":"center"}, 
			               {"mDataProp":"endDate","sClass":"center"},  
			               {"mDataProp": "contractType","sClass":"center", "mRender": function (data, type, full) {
			                    var contractType = '';
			                    if (data == '0') {
			                    	contractType = '<span class="label label-sm label-warning">供应商合同</span>';
			                    }
			                    else if (data == '1') {
			                    	contractType = '<span class="label label-sm label-success">商圈合同</span>';
			                    }
			                    else if (data == '2') {
			                    	contractType = '<span class="label label-sm label-info">广告合同</span>';
			                    }
			                    return contractType;
			                }},				               
			               {"mDataProp":"contractFee","sClass":"center"},  
			               {"mDataProp": "isPay","sClass":"center", "mRender": function (data, type, full) {
			                    var isPay = '';
			                    if (data == '0') {
			                    	isPay = '<span class="label label-sm label-success">已支付</span>';
			                    }
			                    else if (data == '1') {
			                    	isPay = '<span class="label label-sm label-success">部分支付</span>';
			                    }
			                    else if(data == '2'){
			                    	isPay = '<span class="label label-sm label-success">未支付</span>';
			                    }
			                    return isPay;
			                }},
			               {"mDataProp": "contractStatus","sClass":"center", "mRender": function (data, type, full) {
			                    var contractStatus = '';
			                    if (data == '0') {
			                    	contractStatus = '<input title="启用" class="ace ace-switch ace-switch-6"  checked="checked" type="checkbox" onclick="changeContractStatus('+full.contractId+')" /><span class="lbl"></span>';
			                    }
			                    else if (data == '1') {
			                    	contractStatus = '<input title="禁用" class="ace ace-switch ace-switch-6" type="checkbox" onclick="changeContractStatus('+full.contractId+')" /><span class="lbl"></span>';
			                    }
			                    return contractStatus;
			                }},
			               
			               {"sDefaultContent": "","sClass":"center","mRender": function(data, type, full) { 
			            	   var actionDiv = '';
			            	   actionDiv = '<div class="hidden-sm hidden-xs btn-group">'+
			            	   '<button onclick="contractDt(\'' + full.contractId + '\')" class="btn btn-xs btn-success"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
		                       '<button class="btn btn-xs btn-info" onclick="modifyContractInfo(\'' + full.contractId + '\')"><i class="ace-icon fa fa-pencil bigger-120"></i></button>'+
			            	   '<button class="btn btn-xs btn-danger" onclick="deleteContractInfo(\'' + full.contractId + '\')"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
			            	   actionDiv += '</div>';
								return actionDiv;
			               }}
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
			                	 sDefaultContent: '',
			                	 aTargets: [ '_all' ]
			                	  },
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6 ,7 , 8]},  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 
			                 {"aTargets":[1],"mRender":function(data,type,full){
			                     return "<a   onclick='contractInfo(\"" + full.contractId + "\")'>" + data + "</a>"}
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
*/
function retrieveData(sSource, aoData, fnCallback) { 
	//商品名称或编号
	var keyword = $("#type_keyword").val();
    var param = {"keyword": keyword};
    var orgId = $("#orgId").val();
	var regionId = $("#regionId").val();
	var proviceId = $("#proviceId").val();
	param.regionId= regionId;
	param.proviceId = proviceId;
	param.orgId = orgId; 
//    var selectNodes = $.fn.zTree.getZTreeObj("org_tree");
//    if(selectNodes!=null){
//    	if(selectNodes.getSelectedNodes() != ""){
//    		var id=selectNodes.getSelectedNodes()[0].id;       
//            var pid=selectNodes.getSelectedNodes()[0].pid;  
//            var level = selectNodes.getSelectedNodes()[0].level;
//            param.id = id;
//            param.pid = pid;
//            param.level = level;
//    	}        
//    }	

	for(var i = 0;i < aoData.length;i++) {
		var _aoData = aoData[i];
		if(_aoData.name == "iDisplayStart") {
			/*开始页数*/
			param.iDisplayStart = _aoData.value;
		}else if(_aoData.name == "iDisplayLength"){
			/*记录条数*/
			param.iDisplayLength = _aoData.value;
		}else if(_aoData.name == "sEcho"){
			/*操作次数*/
			param.sEcho = _aoData.value;
		}
	}
	//提交访问
    $.ajax( {    
        type: "POST",     
        url: sSource,     
        dataType: "json",    
        data: param, // 以json格式传递  
        success: function(resp) {
            fnCallback(resp);
//            removeMask($('.goods-container'));
        }    
    });    
}
//搜索商品
function searchDevice() {
	if(oTable){
		oTable.fnDraw();
	}
}
//修改合同
function modifyContractInfo(id){
	window.location.href = ROOT_PATH +"/view/sys/contract/preAddOrModifyContract.action?contractId=" + id;
}
//删除合同
function deleteContractInfo(id){
	if (confirm("确定删除?")) {
		window.location.href = ROOT_PATH +"/view/sys/contract/deleteContract.action?contractId=" + id;
	}
}

//查看合同详情
function contractDt(id){
	window.location.href = ROOT_PATH +"/view/sys/contract/queryContract.action?contractId=" + id;
}

//查看合同
function contractInfo(id){
	window.location.href = ROOT_PATH + "/view/sys/contract/queryContractDt.action?contractId=" + id;
}
/**
 * 支付未支付切换
 * @param deviceId 设备ID
 */
function changePayStatus(id) {
	if(confirm("确定修改？")){
		window.location.href = ROOT_PATH + "/view/sys/contract/changePayStatus.action?contractId=" + id;
	}
}

/**
 * 合同状态切换
 * @param deviceId 设备ID
 */
function changeContractStatus(id) {
	if(confirm("确定修改？")){
		window.location.href = ROOT_PATH + "/view/sys/contract/changeContractStatus.action?contractId=" + id;
	}
	else{
		window.location.href = ROOT_PATH + "/view/sys/contractList.jsp";
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
            	searchDevice();
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

    treeObj.hover(function () {
        if (!treeObj.hasClass("showIcon")) {
            treeObj.addClass("showIcon");
        }
    }, function() {
        treeObj.removeClass("showIcon");
    });
    initTable();
}


function showMenu() {
    var gtObj = $("#dict_provice_id");
    //alert(gtObj);
    if($("#gt_combobox").css("display") == "none"){
        var gtOffset = $("#dict_provice_id").offset();
        $("#gt_combobox").css({width:gtObj.css("width"),left:gtOffset.left + "px", top:gtOffset.top + gtObj.outerHeight() + "px"}).slideDown("fast");
    }

    $("body").bind("mousedown", onBodyDown);
}

function hideMenu() {
    $("#gt_combobox").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "gt_combobox" || $(event.target).parents("#gt_combobox").length>0)) {
        hideMenu();
    }
}

function show(){
    var selectNodes = $.fn.zTree.getZTreeObj("org_tree").getSelectedNodes();
    alert(selectNodes[0].id);
}


