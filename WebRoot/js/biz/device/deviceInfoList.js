jQuery(function($) {
	$("#submenu-menu-device-info").addClass("active");
	$("#menu-device").addClass("active");
	$("#menu-device").addClass("open");
	initOrgTree();
    $("#searchGoodsBtn").click(function () {
        searchDevice();
    });
    $("#keyword").keyup(function () {
        oTable.fnDraw();
    });
    
//	 $('#deviceInfo_list tbody').on('click','tr td:nth-child(3)', function (e) {
//		   var name = $(this).text();
//		    alert(name);
//		} );
});

oTable = null;
function initTable() {
	if(oTable == null) {
		oTable = $("#deviceInfo_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": false,
			"bInfo": true,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH +"/view/device/deviceInfo/queryDeviceInfo.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"deviceNo","sClass":"center"},
			               {"mDataProp":"deviceName","sClass":"center"},
			               {"mDataProp":"deviceTypeName","sClass":"center"},
			               {"mDataProp":"deviceAddress","sClass":"left"},
			               {"mDataProp":"createDate","sClass":"center"},
			               {"mDataProp":"deviceStatus","sClass":"center", "mRender": function (data, type, full) {
			                    var deviceStatus = '';
			                    if (data == '1') {
			                    	deviceStatus = '<input title="启用" class="ace ace-switch ace-switch-6" type="checkbox" onclick="changeStatus(this,'+full.deviceId+')" checked/><span class="lbl"></span>';
			                    }
			                    else if (data == '0' || data == '2' || data == '3') {
			                    	deviceStatus = '<input title="禁用" class="ace ace-switch ace-switch-6" type="checkbox" onclick="changeStatus(this,'+full.deviceId+')"  /><span class="lbl"></span>';
			                    }
			                    return deviceStatus;
			                }},
			               {"sDefaultContent": "","sClass":"center","mRender": function(data, type, full) { 
			            	   var actionDiv = '';
			            	   actionDiv = '<div class="hidden-sm hidden-xs btn-group">'+
								'<a  class="tooltip-info" data-rel="tooltip" title="维修">'+
								'<button class="btn btn-xs btn-info" onclick="deviceMaintenance(\'' + full.deviceId + '\')" >维修</button>'+
								'</a>&nbsp;'+
								'<a  class="tooltip-info" data-rel="tooltip" title="清洗">'+
								'<button class="btn btn-xs btn-info" onclick="deviceClean(\'' + full.deviceId + '\')" >清洗</button>'+
								'</a>&nbsp;'+
								'<a class="tooltip-info" data-rel="tooltip" title="销售" onclick="sell(\'' + full.deviceId + '\')">'+
								'<button class="btn btn-xs btn-info">销售</button>'+
								'</a>';
								
								actionDiv += '</div>';
								return actionDiv;
			               }},
			               {"sDefaultContent": "","sClass":"center","mRender": function(data, type, full) { 
			            	   var actionDiv = '';
			            	   actionDiv = '<div class="hidden-sm hidden-xs btn-group">'+
			            	   '<button onclick="deviceDtl(\'' + full.deviceId + '\')" class="btn btn-xs btn-success"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
		                       '<button class="btn btn-xs btn-info" onclick="modifyDeviceInfo(\'' + full.deviceId + '\')"><i class="ace-icon fa fa-pencil bigger-120"></i></button>'+
			            	   '<button class="btn btn-xs btn-danger" onclick="deleteDeviceInfo(\'' + full.deviceId + '\')"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
			                 {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6,7]},  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
			                 
			                 {"aTargets":[0],"mRender":function(data,type,full){
			                     return "<a   onclick='deviceMapInfo(\"" + full.deviceId + "\")'>" + data + "</a>"}
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
	//商品状态
	var deviceStatus = $("#status_sele").val();
    var param = {"keyword": keyword};
    param.deviceStatus = deviceStatus;
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
function searchDevice() {
	if(oTable){
		oTable.fnDraw();
	}
}

function modifyDeviceInfo(id){
	window.location.href = ROOT_PATH +"/view/device/deviceInfo/addOrModifyDeviceInfo.action?deviceId="+id;
}
function deleteDeviceInfo(id){
	var isAdmin = $("#isAdmin").val();
	alert(isAdmin);
	if(isAdmin != null && isAdmin == '1'){
		if (confirm("确定删除此条设备信息吗?")) {
			window.location.href = ROOT_PATH +"/view/device/deviceInfo/delDeviceById.action?deviceId="+id;
		}
	}
	else{
		alert("您没有权限删除该设备！");
	}
	
}

function add(id){
	window.location.href = ROOT_PATH +"/view/device/deviceInfo/addOrModifyDeviceInfo.action?deviceId="+id;
}

function deviceMaintenance(id){
	var url = ROOT_PATH +"/view/device/deviceMain/queryDeviceMaintenance.action?deviceId="+id;
	window.location.href = url;
}
function deviceClean(id){
	window.location.href = ROOT_PATH +"/view/device/deviceClean/queryDeviceCleanList.action?deviceId="+id;
}

function sell(id){
	//window.location.href = ROOT_PATH +"/view/device/deviceInfo/deviceMap.action?deviceId="+id;
}
function deviceMapInfo(id){
	window.location.href = ROOT_PATH +"/view/device/deviceInfo/deviceMap.action?deviceId="+id;
}
function allDeviceMap(){
	$.ajax({
		url: ROOT_PATH + '/view/device/deviceInfo/checkDevice.action',
		type:'POST',
		dataType:'json',
		success:function(data){					
			if(data){
				window.location.href = ROOT_PATH +"/view/device/deviceInfo/queryAllDevice.action";
			}
			else{
				alert("请添加设备！");
			}
		}
	});
}
function carMap(){
	window.location.href = ROOT_PATH +"/view/device/testMap.jsp";
}
//设备详情
function deviceDtl(id){
	window.location.href = ROOT_PATH +"/view/device/deviceInfo/queryDeviceById.action?deviceId="+id;
}
/**
 * 启用禁止
 * @param deviceId 设备ID
 */
function changeStatus(checkbox,deviceId) {
	if(deviceId){
		var isDisable = null;
		if($(checkbox).prop("checked")) {
			isDisable = '1';
		} else {
			isDisable = '0';
		}
		$.ajax({
			type : "POST",
			url : ROOT_PATH + "/view/device/deviceInfo/changeDeviceStatus.action",
			data : { "deviceId" : deviceId,"isDisable":isDisable}, 
			success : function(data) {
				if(!data) {
					alert("系统错误，请联系管理员!");
				}
				//initTable();
			}
		});
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
}

