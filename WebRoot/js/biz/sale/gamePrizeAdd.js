jQuery(function ($) {
    $("#submenu-menu-advert-user").addClass("active");
    $("#menu-advert").addClass("active");
    $("#menu-advert").addClass("open");
   
});

function pageReturn(){
	var gameId = $("#gameId").val();
	window.location.href = ROOT_PATH + "/view/sale/gameInfo/returnGamePrizePage.action?gameId=" + gameId;
}