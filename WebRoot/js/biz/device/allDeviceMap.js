jQuery(function($) {
	$("#submenu-menu-device-info").addClass("active");
	$("#menu-device").addClass("active");
	$("#menu-device").addClass("open");
	Bmap();
});

function Bmap(){
	var userCity = $("#userId").val();
	var objList = JSON.parse(jsonData);
	var map = new BMap.Map("allmap");//初始化地图
	map.centerAndZoom(userCity, 15);//将光谷点设置为中心点并设置地图级别
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	var myIcon = new BMap.Icon(om+"/images/map/red.png", new BMap.Size(19,26));
	
	var deviceName = "";
	var deviceAddress="";
	var finishTime="";
	var maintenanceUser="";
	var maintenanceReason="";
	var remark="";
	
	// 编写自定义函数,创建标注
	for (var i = 0; i < objList.length; i ++) {
		var point = new BMap.Point(objList[i].deviceLng,objList[i].deviceLat);
		var label = new BMap.Label("设备编号:"+objList[i].deviceNo +"<br/>"+"设备地址:"+objList[i].deviceAddress,{offset:new BMap.Size(20,-10)});
		var myIcongree = new BMap.Icon(om+"/images/map/gree.png", new BMap.Size(19,24));
		if(objList[i].deviceStatus == 1){
			addMarker(point,label,myIcon,i);
		}else{
			addMarker(point,label,myIcongree,i);
		}
	}
	
	var opts ={
			title  : "设备信息 : ",      //标题
			width  : 290,             //宽度
			height : 130,              //高度
			panel  : "panel",         //检索结果面板
			enableAutoPan : true    //自动平移

	};
	
	function addMarker(point,label,myIcon,i){
		var marker = new BMap.Marker(point,{icon:myIcon});
		map.addOverlay(marker);
		marker.setLabel(label);
		
		if(objList[i].deviceName != null){
		  	deviceName = objList[i].deviceName;
		}
		if(objList[i].deviceAddress != null){
		  	deviceAddress = objList[i].deviceAddress;
		}
		if(objList[i].finishTime != null){
		  	finishTime = objList[i].finishTime;
		}
		if(objList[i].maintenanceUser != null){
		  	maintenanceUser = objList[i].maintenanceUser;
		}
		if(objList[i].maintenanceReason != null){
		  	maintenanceReason = objList[i].maintenanceReason;
		}
		if(objList[i].remark != null){
			remark = objList[i].remark;
		}
		
		var content = '<div style="margin:0;line-height:20px;padding:2px;">' +
		'设备名称:'+deviceName +'<br/>'+
		'设备地址:'+deviceAddress +'<br/>'+
		'最近维修时间:'+finishTime +'<br/>' +
		'维修人:'+maintenanceUser + '<br/>' +
		'维修原因:'+maintenanceReason + '<br/>' +
		'维修备注:'+remark + '<br/>' +
		'</div>';
		
		addClickHandler(content,marker);
	  
	}
	
	function addClickHandler(content,marker){
		marker.addEventListener("click",function(e){
			openInfo(content,e,marker)});
	}
	function openInfo(content,e,marker){
		var p = e.target;
		var pt = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
		var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
		map.openInfoWindow(infoWindow,pt); //开启信息窗口
	}
	
}