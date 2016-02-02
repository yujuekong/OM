jQuery(function($) {
	$("#submenu-menu-car-info").addClass("active");
	$("#menu-car").addClass("active");
	$("#menu-car").addClass("open");
	Bmap();
});
var lushu;
function Bmap(){

	var obj = JSON.parse(jsonData);
	var map = new BMap.Map("allmap");//初始化地图
	var point = new BMap.Point(obj[0].nodeLng,obj[0].nodeLat);
	map.centerAndZoom(point, 15);//将光谷点设置为中心点并设置地图级别
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	// 编写自定义函数,创建标注
	function addMarker(point,label){
	  var marker = new BMap.Marker(point);
	  map.addOverlay(marker);
	  marker.setLabel(label);
	}
	var pointList =new Array();
	for (var i = 0; i < obj.length; i ++) {
		var point = new BMap.Point(obj[i].nodeLng,obj[i].nodeLat);
		var label = new BMap.Label("站点名称:"+obj[i].nodePositionName +"<br/>",{offset:new BMap.Size(20,-10)});
	  	addMarker(point,label);
	  	pointList.push(point);
	}
	// 实例化一个驾车导航用来生成路线
    var drv = new BMap.DrivingRoute('武汉', {
        onSearchComplete: function(res) {
            if (drv.getStatus() == BMAP_STATUS_SUCCESS) {
                var plan = res.getPlan(0);
                var arrPois =[];
                for(var j=0;j<plan.getNumRoutes();j++){
                    var route = plan.getRoute(j);
                    arrPois= arrPois.concat(route.getPath());
                }
                map.addOverlay(new BMap.Polyline(arrPois, {strokeColor: '#ED2D2D'}));
                map.setViewport(arrPois);
                
                lushu = new BMapLib.LuShu(map,arrPois,{
                defaultContent:"",
                autoView:true,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
                icon  : new BMap.Icon(om+"/images/map/car.png", new BMap.Size(52,26),{anchor : new BMap.Size(27, 13)}),
                speed: 2000,
                enableRotation:true//是否设置marker随着道路的走向进行旋转
               });          
            }
        }
    });
    var pList =new Array();
    for(var i = 0; i < pointList.length; i ++){
    	var po = pointList[i];
    	if(pointList.length > 2){
    		pList.push(po);
    	}else{
    		pList.push(po);
    	}
    }
    if(pointList.length > 2){
    	pList.pop();
    	pList.splice(0,1)
    }
    
    
    if(pointList.length >2){
    	drv.search(pointList[0], pointList[pointList.length-1],{waypoints:pList});
    }else{
    	drv.search(pointList[0],pointList[1]);
    }
	//绑定事件
	function $(element){
		return document.getElementById(element);
	}
	$("run").onclick = function(){
		lushu.start();
	};
	
}
