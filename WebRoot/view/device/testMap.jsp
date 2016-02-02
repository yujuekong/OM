<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<jsp:include page="../commons/head.jsp" />
	<meta charset="utf-8" /> 
	<title>路书</title> 
	<style type="text/css">
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#map_canvas{width:100%;height:90%;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=wYELzyLpaRxWSOgD0gS9wGM3"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/LuShu/1.2/src/LuShu_min.js"></script>
</head> 
<body> 
	<div id="map_canvas"></div> 
	<button id="run">开始</button> 
	<script> 
	var map = new BMap.Map('map_canvas');
	map.enableScrollWheelZoom();
	map.centerAndZoom(new BMap.Point(114.405855, 30.513091), 15);
	var lushu;
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
                defaultContent:"从起点到终点",
                autoView:true,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
                icon  : new BMap.Icon('${pageContext.request.contextPath}/images/map/car.png', new BMap.Size(52,26),{anchor : new BMap.Size(27, 13)}),
                speed: 500,
                enableRotation:true//是否设置marker随着道路的走向进行旋转
               });          
            }
        }
    });
	
    var p1 = new BMap.Point(114.402783,30.497363);
    var p2 = new BMap.Point(114.42878,30.521242);
	
	drv.search(p1, p2,{waypoints:['光谷时代广场']});
	//绑定事件
	$("run").onclick = function(){
		lushu.start();
	};
	function $(element){
		return document.getElementById(element);
	}
</script> 
</body> 
</html> 