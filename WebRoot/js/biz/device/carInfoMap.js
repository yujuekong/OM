jQuery(function($) {
	$("#submenu-menu-car-info").addClass("active");
	$("#menu-car").addClass("active");
	$("#menu-car").addClass("open");
	alert("3  :");
	alert("4  :"+jsonList);
	//Bmap();
});

function Bmap(){
	var userCity = $("#userId").val();
	var obj = JSON.parse(jsonData);
	var objList =JSON.parse(jsonList);
	var carLng = obj.carLng; 
	var carLat = obj.carLat;
	var carName = obj.carName;
	var carSize = obj.carSize; 
	var map = new BMap.Map("allmap");//初始化地图
	var point = new BMap.Point(carLng, carLat);//设置一个点为光谷
	map.centerAndZoom(userCity, 15);//将光谷点设置为中心点并设置地图级别
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	var label = new BMap.Label("设备名称:"+carName +"<br/>"+"设备地址:"+carSize,{offset:new BMap.Size(20,-10)});
	var myIcon = new BMap.Icon(om+"/images/map/blue.png", new BMap.Size(300,157));
	addMarker(point,label,myIcon);
	// 编写自定义函数,创建标注
	function addMarker(point,label,myIcon){
	  var marker = new BMap.Marker(point,{icon:myIcon});
	  map.addOverlay(marker);
	  marker.setLabel(label);
	}
	for (var i = 0; i < objList.length; i ++) {
		if(obj.carId != objList[i].carId){
			var point = new BMap.Point(objList[i].carLng,objList[i].carLat);
			var label = new BMap.Label("设备名称:"+objList[i].carName +"<br/>"+"设备地址:"+objList[i].carSize,{offset:new BMap.Size(20,-10)});
			//var myIcon = new BMap.Icon(om+"/images/map/blue.png", new BMap.Size(300,157));
			addMarker(point,label);
		}

	}
}