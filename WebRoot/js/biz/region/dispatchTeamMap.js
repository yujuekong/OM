jQuery(function($) {
	$("#submenu-menu-device-info").addClass("active");
	$("#menu-device").addClass("active");
	$("#menu-device").addClass("open");
	Bmap();
});

function Bmap(){
	var userCity = $("#userId").val();
	var obj = JSON.parse(jsonData);
	if(obj != null){
		var userLng = obj.userLng;
		var userLat = obj.userLat;
		if(userLng != null && userLat!=null ){
			var map = new BMap.Map("allmap");//初始化地图
			var point = new BMap.Point(userLng, userLat);//设置一个点为光谷
			map.centerAndZoom(point, 15);//将光谷点设置为中心点并设置地图级别
			var label = new BMap.Label("配送员名称:"+obj.realName +"<br/>",{offset:new BMap.Size(20,-10)});
		  	addMarker(point,label);
		}
	}else{
		alert("此用户未没有配置GPS设备");
	}
	// 编写自定义函数,创建标注
	function addMarker(point,label){
	  var marker = new BMap.Marker(point);
	  map.addOverlay(marker);
	  marker.setLabel(label);
	}


}