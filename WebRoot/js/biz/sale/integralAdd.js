jQuery(function($) {
	$("#submenu-menu-advert-user").addClass("active");
	$("#menu-advert").addClass("active");
	$("#menu-advert").addClass("open");

	initUserTable();
	
});

//单个追加商品到申购单
$(document).on("dblclick", "#deviceInfo_list tbody tr", function() {
	var $choiseDevice = getChoiseDeviceInfo($(this));
	$("#deviceIdStr").val($choiseDevice.deviceId);
	/*$("#deviceNameStr").val($choiseDevice.deviceName);*/
	$("#devices_choise_modal").modal('hide');
	//focusNumbox();
});

//单个追加商品到申购单
$(document).on("dblclick", "#deviceInfo_list tbody tr", function() {
	var $choiseDevice = getChoiseDeviceInfo($(this));
	$("#deviceIdStr").val($choiseDevice.deviceId);
	/*$("#deviceNameStr").val($choiseDevice.deviceName);*/
	$("#devices_choise_modal").modal('hide');
	//focusNumbox();
});

