jQuery(function ($) {
    $("#submenu-menu-advert-user").addClass("active");
    $("#menu-advert").addClass("active");
    $("#menu-advert").addClass("open");
    
    $("#searchGoodsBtn").keyup(function () {
        searchGoods();
    });
    
    $("#gameInfo_startDate").datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
	    language: 'zh-CN',
		weekStart: 1,
	    todayBtn:  1,
		autoclose: true,
		todayHighlight: 1,
//		minView: "hour",
		startView: 2,
//		minView: 2,
		forceParse: 0
	});
	
	$("#gameInfo_endDate").datetimepicker({
		format: 'yyyy-mm-dd hh:ii',
	    language: 'zh-CN',
		weekStart: 1,
	    todayBtn:  1,
		autoclose: true,
		todayHighlight: 1,
//		minView: "hour",
		startView: 2,
//		minView: 2,
		forceParse: 0
	});
});