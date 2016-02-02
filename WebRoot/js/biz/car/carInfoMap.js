jQuery(function($) {
	$("#submenu-menu-car-info").addClass("active");
	$("#menu-car").addClass("active");
	$("#menu-car").addClass("open");
	Bmap();
});

function Bmap(){
	var userCity = $("#userId").val();
	var obj = JSON.parse(jsonData);
	var objList =JSON.parse(jsonList);
	
	var carLng = obj.carLng;
	var carLat = obj.carLat;
	var carNowPoint = new BMap.Point(carLng,carLat);
	var carNo = "";
	var carBrand = "";
	var carType = "";
	var checkDate = "";
	var dictOrg = "";
	
	var map = new BMap.Map("allmap");//初始化地图
	if(carLng != "" &&　carLng != null){
		map.centerAndZoom(carNowPoint, 15);//将光谷点设置为中心点并设置地图级别
	}else{
		alert("此车辆未配备GPS设备或未开启GPS功能!");
		map.centerAndZoom(userCity, 15);//将用户所在城市设置为中心点并设置地图级别
	}
	for (var i = 0; i < objList.length; i ++) {
		if(obj.carId == objList[i].carId){
			var point = new BMap.Point(objList[i].carLng,objList[i].carLat);
			var label = new BMap.Label("车牌号:"+objList[i].carNo,{offset:new BMap.Size(20,-10)});
			var myIcon = new BMap.Icon(om+"/images/map/car.png", new BMap.Size(52,26));
			addMarker(point,label,myIcon,i);
		}else{
			var point = new BMap.Point(objList[i].carLng,objList[i].carLat);
			var label = new BMap.Label("车牌号:"+objList[i].carNo,{offset:new BMap.Size(20,-10)});
			var myIcon = new BMap.Icon(om+"/images/map/car.gif", new BMap.Size(52,26));
			addMarker(point,label,myIcon,i);
		}
	}
	/*********************************/
	//创建检索信息窗口对象
	var opts ={
			title  : "车辆实时信息 : ",      //标题
			width  : 290,             //宽度
			height : 110,              //高度
			panel  : "panel",         //检索结果面板
			enableAutoPan : true    //自动平移

	};
	
	
	// 编写自定义函数,创建标注
	function addMarker(point,label,myIcon,i){
		
		var marker = new BMap.Marker(point,{icon:myIcon});
	  	map.addOverlay(marker);
	  	marker.setLabel(label);
	  
		if(objList[i].carNo != null){
			carNo = objList[i].carNo;
		}
		if(objList[i].carBrand != null){
			carBrand = objList[i].sysDictBrand.dictName;
		}
		if(objList[i].carType != null){
			carType = objList[i].sysDictType.dictName;
		}
		if(objList[i].checkDate != null){
			checkDate = objList[i].checkDate;
		}
		if(objList[i].org.dictName != null){
			dictOrg = objList[i].org.dictName;
		}
		var content = '<div style="margin:0;line-height:20px;padding:2px;">' +
		'所属分公司:'+dictOrg +'<br/>'+
		'车辆编号:'+carNo +'<br/>'+
		'车辆品牌:'+carBrand +'<br/>'+
		'车辆类型:'+carType +'<br/>'+
		'注册日期:'+checkDate +'<br/>'+
		'</div>';
		addClickHandler(content,marker);
	}
	function addClickHandler(content,marker){
		marker.addEventListener("click",function(e){
			openInfo(content,e,marker)}
		);
	}
	function openInfo(content,e,marker){
		var p = e.target;
		var pt = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
		var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
		//var searchInfoWindow = new BMapLib.SearchInfoWindow(map, content,opts);
		map.openInfoWindow(infoWindow,pt); //开启信息窗口
		//searchInfoWindow.open(pt); //开启信息窗口
	}
}