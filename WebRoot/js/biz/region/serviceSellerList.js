jQuery(function($) {
	$("#submenu-menu-area-supplier").addClass("active");
	$("#menu-area").addClass("active");
	$("#menu-area").addClass("open");
	initOrgTree();
	$("#keyword").keyup(function () {
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
			"sAjaxSource": ROOT_PATH +"/view/region/serviceInfo/queryService.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"districtNo","sClass":"center"},
			               {"mDataProp":"districtName","sClass":"center"},	
			               {"mDataProp":"districtAddress","sClass":"center"},			               
			               {"mDataProp":"sysDict.dictName","sClass":"center"},
			               {"mDataProp":"linkMan","sClass":"center"},   
			               {"mDataProp":"linkTel","sClass":"center"},
			               {"mDataProp":"linkMail","sClass":"center"},
			               {"mDataProp":"districtManager","sClass":"center"},
			               {"sDefaultContent": "","sClass":"center","mRender": function(data, type, full) { 
			            	   var actionDiv = '';
			            	   actionDiv = '<div class="hidden-sm hidden-xs btn-group">'+
			            	   '<button onclick="serviceSelllerDtl(\'' + full.districtId + '\')" class="btn btn-xs btn-success"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
		                       '<button class="btn btn-xs btn-info" onclick="modifyServiceSeller(\'' + full.districtId + '\')"><i class="ace-icon fa fa-pencil bigger-120"></i></button>'+
			            	   '<button class="btn btn-xs btn-danger" onclick="deleteServiceSeller(\'' + full.districtId + '\')"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6, 7, 8]}, //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
		                	 {"aTargets":[0],"mRender":function(data,type,full){
		                		 return "<a   onclick='districtMapInfo(\"" + full.districtId + "\")'>" + data + "</a>"}
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
	var keyword = $("#keyword").val();
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
function searchService() {
	if(oTable){
		oTable.fnDraw();
	}
}

function modifyServiceSeller(id){
	window.location.href = ROOT_PATH +"/view/region/serviceInfo/addOrModifyServiceSeller.action?districtId="+id;
}
function deleteServiceSeller(id){
	if (confirm("确定删除此条商圈信息吗?")) {
		window.location.href = ROOT_PATH +"/view/region/serviceInfo/delServiceSellerById.action?districtId="+id;
	}
}
function add(id){
	window.location.href = ROOT_PATH +"/view/region/serviceInfo/addOrModifyServiceSeller.action?districtId="+id;
}

function districtMapInfo(id){
	window.location.href = ROOT_PATH +"/view/region/serviceInfo/districtMap.action?districtId="+id;
}
function allDeviceMap(){
	window.location.href = ROOT_PATH +"/view/region/serviceInfo/allDistrictMap.action";
}
function serviceSelllerDtl(districtId){
	window.location.href = ROOT_PATH +"/view/region/serviceInfo/serviceSellerDtl.action?districtId="+districtId;
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
            	searchService();
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

