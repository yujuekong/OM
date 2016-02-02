jQuery(function($) {
	$("#submenu-menu-car-info").addClass("active");
	$("#menu-car").addClass("active");
	$("#menu-car").addClass("open");
	Bmap();
});

function Bmap(){
	var userCity = $("#userId").val();
	var objList = JSON.parse(jsonData);
	var map = new BMap.Map("allmap");//初始化地图
	map.centerAndZoom(userCity, 15);//将光谷点设置为中心点并设置地图级别
	var myIcon = new BMap.Icon(om+"/images/map/carGree.jpg", new BMap.Size(52,26));
	

	
	// 编写自定义函数,创建标注
	for (var i = 0; i < objList.length; i ++) {
		var point = new BMap.Point(objList[i].carLng,objList[i].carLat);
		var label = new BMap.Label("车牌号:"+objList[i].carNo,{offset:new BMap.Size(20,-10)});
		var myIcongree = new BMap.Icon(om+"/images/map/car.gif", new BMap.Size(52,26));
		if(objList[i].carStatus == 1){
			addMarker(point,label,myIcon,i);
		}else{
			addMarker(point,label,myIcongree,i);
		}
	}
	
	function addMarker(point,label,myIcon,i){
		var marker = new BMap.Marker(point,{icon:myIcon});
		map.addOverlay(marker);
		marker.setLabel(label);
	}
		
}