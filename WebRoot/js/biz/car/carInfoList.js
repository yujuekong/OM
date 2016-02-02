jQuery(function($) {
	$("#submenu-menu-car-info").addClass("active");
	$("#menu-car").addClass("active");
	$("#menu-car").addClass("open");
	initOrgTree();
	$("#house_keyword").keyup(function () {
        initTable();
    });
});

oTable = null;
function initTable() {
	if(oTable == null) {
		oTable = $("#carInfo_list").dataTable({
			"bAutoWidth":false,
			"bLengthChange" :false,
			"bProcessing": false,
			"bFilter" : false,// 搜索栏
			"bServerSide" : true,//异步请求必须设置  
			"sAjaxSource": ROOT_PATH +"/view/car/carInfo/queryCarInfo.action",  //异步请求地址
			"aoColumns" : [ 
			               {"mDataProp":"carNo","sClass":"center"},
			               {"mDataProp":"sysDictBrand.dictDesc","sClass":"center"},	
			               {"mDataProp":"sysDictType.dictDesc","sClass":"center"},
			               {"mDataProp":"org.dictName","sClass":"center"},
			               {"mDataProp":"checkDate","sClass":"center"},
			               {"mDataProp":"createDate","sClass":"center"},
			               {"mDataProp":"","sClass":"center", "mRender": function (data, type, full) {
			            	   var carTrack ="";
			            	   carTrack = '<div class="hidden-sm hidden-xs btn-group">'+
								'<button class="btn btn-xs btn-success" onclick="carTrackMap(\'' + full.carId + '\')"><i class="ace-icon fa fa-car bigger-120"></i></button>';
			            	   carTrack += '</div>';
								return carTrack;
			            	   
			                }},
			               {"mDataProp": "carStatus","sClass":"center", "mRender": function (data, type, full) {
			                    var carStatus = '';
			                    if (data == '0') {
			                    	carStatus = '<input title="启用" class="ace ace-switch ace-switch-6" type="checkbox" onclick="changeStatus(this,'+full.carId+')" checked/><span class="lbl"></span>';
			                    }
			                    else if (data == '1') {
			                    	carStatus = '<input title="禁用" class="ace ace-switch ace-switch-6" type="checkbox" onclick="changeStatus(this,'+full.carId+')"  /><span class="lbl"></span>';
			                    }
			                    return carStatus;
			                }},
			               {"sDefaultContent": "","sClass":"center","mRender": function(data, type, full) { 
			            	   var actionDiv = '';
			            	   actionDiv = '<div class="hidden-sm hidden-xs btn-group">'+
			            	    '<button class="btn btn-xs btn-success" onclick="detailsCarInfo(\'' + full.carId + '\')"><i class="ace-icon fa fa-search-plus bigger-120"></i></button>' +
								'<button class="btn btn-xs btn-info" onclick="modifyCarInfo(\'' + full.carId + '\')"><i class="ace-icon fa fa-pencil bigger-120"></i></button>'+
								'<button class="btn btn-xs btn-danger" onclick="deleteCarInfo(\'' + full.carId + '\')"><i class="ace-icon fa fa-trash-o bigger-120"></i></button>';
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
			                   "zeroRecords": "没有内容",
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
				                {"bSortable": false, "aTargets": [0, 1, 2, 3, 4, 5, 6, 7, 8]},  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
								{"aTargets":[0],"mRender":function(data,type,full){
									return "<a   onclick='carMapInfo(\"" + full.carId + "\")'>" + data + " </a>" }
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
	var keyword = $("#house_keyword").val();
	var param = {"keyword":keyword};
	
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
function searchCar() {
	if(oTable){
		oTable.fnDraw();
	}
}

/**
 * 车辆启用禁止
 * @param carId 车辆ID
 */
function changeStatus(checkbox,carId) {
	if(carId){
		var isDisable = null;
		if($(checkbox).prop("checked")) {
			isDisable = '0';
		} else {
			isDisable = '1';
		}
		$.ajax({
			type : "POST",
			url : ROOT_PATH + "/view/car/carInfo/changeCarStatus.action",
			data : { "carId" : carId,"isDisable":isDisable}, 
			success : function(data) {
				if(!data) {
					alert("系统错误，请联系管理员!");
				}
				initTable();
			}
		});
	}
}
function carMapInfo(id){
	window.location.href = ROOT_PATH +"/view/car/carInfo/carInfoMap.action?carId="+id;
}
function allCarMap(){
	window.location.href = ROOT_PATH +"/view/car/carInfo/carAllMap.action";
}
function modifyCarInfo(id){
	window.location.href = ROOT_PATH +"/view/car/carInfo/addOrModifyCarInfo.action?carId="+id;
}
function deleteCarInfo(id){
	if (confirm("确定删除此条设备维护信息吗?")) {
		window.location.href = ROOT_PATH +"/view/car/carInfo/delCarById.action?carId="+id;
	}
}

function add(id){
	window.location.href = ROOT_PATH +"/view/car/carInfo/addOrModifyCarInfo.action?carId="+id;
}

function detailsCarInfo(id){
	window.location.href = ROOT_PATH +"/view/car/carInfo/queryCarById.action?carId="+id;
}

function carTrackMap(id){
	window.location.href = ROOT_PATH +"/view/car/carInfo/carTrackMap.action?carId="+id;
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
            	searchCar();
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