jQuery(function($) {
	$("#submenu-menu-area-supplier").addClass("active");
	$("#menu-area").addClass("active");
	$("#menu-area").addClass("open");
	Bmap();
});

function Bmap(){
	var obj = JSON.parse(jsonData);
	var objList =JSON.parse(jsonList);
	
	var districtLng = obj.districtLng; 
	var districtLat = obj.districtLat;
	var districtNo = "";
	var districtName = "";
	var districtAddress = "";
	var districtManager = "";
	var linkMan = "";
	var linkTel = "";
	
	var map = new BMap.Map("allmap");//初始化地图
	var point = new BMap.Point(districtLng, districtLat);//设置一个点为光谷
	map.centerAndZoom(point, 15);//将光谷点设置为中心点并设置地图级别

	// 编写自定义函数,创建标注

	for (var i = 0; i < objList.length; i ++) {
		if(obj.districtId == objList[i].districtId){
			var point1 = new BMap.Point(objList[i].districtLng,objList[i].districtLat);
			var label1 = new BMap.Label("商圈名称:"+objList[i].districtName +"<br/>"+"商圈地址:"+objList[i].districtAddress,{offset:new BMap.Size(20,-10)});
			var myIcon = new BMap.Icon(om+"/images/map/blue.png", new BMap.Size(19,24));
			addMarker(point1,label1,myIcon,i);
			
		}else{
			var point2 = new BMap.Point(objList[i].districtLng,objList[i].districtLat);
			var label2 = new BMap.Label("商圈名称:"+objList[i].districtName +"<br/>"+"商圈地址:"+objList[i].districtAddress,{offset:new BMap.Size(20,-10)});
			addMarker(point2,label2,null,i);
		}
	}
	var opts ={
			title  : "商圈信息 : ",      //标题
			width  : 290,             //宽度
			height : 130,              //高度
			panel  : "panel",         //检索结果面板
			enableAutoPan : true    //自动平移

	};
	function addMarker(point,label,myIcon,i){
		var marker = new BMap.Marker(point,{icon:myIcon});
		map.addOverlay(marker);
		marker.setLabel(label);
		
		if(objList[i].districtNo != null){
			districtNo = objList[i].districtNo;
		}
		if(objList[i].districtName != null){
			districtName = objList[i].districtName;
		}
		if(objList[i].districtAddress != null){
			districtAddress = objList[i].districtAddress;
		}
		if(objList[i].districtManager != null){
			districtManager = objList[i].districtManager;
		}
		if(objList[i].linkMan != null){
			linkMan = objList[i].linkMan;
		}
		if(objList[i].linkTel != null){
			linkTel = objList[i].linkTel;
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