jQuery(function($) {
	$("#submenu-menu-advert-user").addClass("active");
	$("#menu-advert").addClass("active");
	$("#menu-advert").addClass("open");
	
});

function focusNumbox() {
	$(".fnumbox").each(function(i){
		var goodsNumVal = $(this).val();
		if(!goodsNumVal) {
			$(this).focus();
			return false;
		}
	});
}

oTable = null;
function initTable(advertInfoId) {
	if(oTable == null) {
		oTable = $("#deviceInfo_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH + "/view/advert/advertInfo/queryAdvertDevicePage.action?advertInfoId=" + advertInfoId,  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"deviceNo","sClass":"center"},
			               {"mDataProp":"deviceName","sClass":"center","mRender": function(data, type, full) { 
			            	   return '<label name="deviceName">'+ data + '</label>';
			               }},
			               {"mDataProp":"deviceTypeName","sClass":"center"},
			               {"mDataProp":"deviceAddress","sClass":"center"},
			               {"mDataProp":"deviceStatus","sClass":"center","mRender": function(data, type, full) { 
			            	   var goodsStatus = '';
			            	   if(data == '0'){
			            		   goodsStatus = '<span class="label label-sm label-error">维修中</span>';
			            	   }
			            	   else if(data == '1') {
			            		   goodsStatus ='<span class="label label-sm label-success">运行中</span>';
			            	   } 
			            	   else if(data == '2') {
			            		   goodsStatus ='<span class="label label-sm label-error">停用</span>';
			            	   }
			            	   else if(data == '3') {
			            		   goodsStatus ='<span class="label label-sm label-error">报废</span>';
			            	   }
			            	   return goodsStatus;
			               }},
			               {"mDataProp":"createDate","sClass":"center"}
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5]}  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
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
	var param = {"keyword":keyword};
	var selectNodes = $.fn.zTree.getZTreeObj("device_tree");
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

//加载设备数据
function resetSelectDevice(advertInfoId){
	initOrgTree(advertInfoId);
	initTable(advertInfoId);
}

//查看已投放广告的设备
function showDevice(){
	var advertInfoId = $("#advertInfoId").val(); 
	resetSelectDevice(advertInfoId);
	$('#devices_choise_modal').modal('show');
	
}

//搜索商品
function searchDevice() {
	if(oTable){
		oTable.fnDraw();
	}
}
//初始化机构树
function initOrgTree(advertInfoId) {
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

    var treeObj = $("#device_tree");
    var zNodes;

    $.ajax({
        url: ROOT_PATH + '/view/inventory/warehouse/getMulSubDictDataByPcode.action',
        type:'POST',
        data:{"dictPcode" : "AL_POSITION" ,"dictLevel":"5"},
        dataType:'json',
        success:function(data){
            zNodes = data;
            $.fn.zTree.init($("#device_tree"), setting, zNodes);
        }
    });
    
    treeObj.hover(function () {
        if (!treeObj.hasClass("showIcon")) {
            treeObj.addClass("showIcon");
        }
    }, function() {
        treeObj.removeClass("showIcon");
    });
    initTable(advertInfoId);
}

//下载附件
function downLoad(){
	var result = true;
	var inputPath = $("#advertFile").val();
	alert("");
	if(!inputPath){
		alert("文件不存在，无法下载！");
		result = false;
	}
	else{
		result = true;
	}
	return result;
}
