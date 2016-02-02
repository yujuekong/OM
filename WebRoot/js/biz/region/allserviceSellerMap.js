jQuery(function($) {
	$("#submenu-menu-device-info").addClass("active");
	$("#menu-device").addClass("active");
	$("#menu-device").addClass("open");
	Bmap();
});

function Bmap(){
	var userCity = $("#userId").val();
	var obj = JSON.parse(jsonList);
	var map = new BMap.Map("allmap");//初始化地图
	map.centerAndZoom(userCity, 15);//将光谷点设置为中心点并设置地图级别
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用

	var districtNo = "";
	var districtName = "";
	var districtAddress = "";
	var districtManager = "";
	var linkMan = "";
	var linkTel = "";
	
	for (var i = 0; i < obj.length; i ++) {
		var point = new BMap.Point(obj[i].districtLng,obj[i].districtLat);
		var label = new BMap.Label("商圈名称:"+obj[i].districtName +"<br/>"+"商圈地址:"+obj[i].districtAddress,{offset:new BMap.Size(20,-10)});
		addMarker(point,label,i);
	}
	var opts ={
			title  : "商圈信息 : ",      //标题
			width  : 290,             //宽度
			height : 130,              //高度
			panel  : "panel",         //检索结果面板
			enableAutoPan : true    //自动平移

	};
	// 编写自定义函数,创建标注
	function addMarker(point,label,i){
		var marker = new BMap.Marker(point);
		map.addOverlay(marker);
		marker.setLabel(label);
		
		if(obj[i].districtNo != null){
			districtNo = obj[i].districtNo;
		}
		if(obj[i].districtName != null){
			districtName = obj[i].districtName;
		}
		if(obj[i].districtAddress != null){
			districtAddress = obj[i].districtAddress;
		}
		if(obj[i].districtManager != null){
			districtManager = obj[i].districtManager;
		}
		if(obj[i].linkMan != null){
			linkMan = obj[i].linkMan;
		}
		if(obj[i].linkTel != null){
			linkTel = obj[i].linkTel;
		}
		
		var content = '<div style="margin:0;line-height:20px;padding:2px;">' +
		'商圈编号:'+districtNo +'<br/>'+
		'商圈名称:'+districtName +'<br/>'+
		'商圈地址:'+districtAddress +'<br/>' +
		'商圈联系人:'+linkMan + '<br/>' +
		'商圈联系电话:'+linkTel + '<br/>' +
		'商圈管理区域:'+districtManager + '<br/>' +
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
		map.openInfoWindow(infoWindow,pt); //开启信息窗口
	}
	
	
}