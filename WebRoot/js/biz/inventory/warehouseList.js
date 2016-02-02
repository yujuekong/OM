//jQuery(function($) {
//    $("#submenu-submenu-menu-sys-org-user").addClass("active");
//    $("#submenu-menu-sys-org").addClass("active");
//    $("#submenu-menu-sys-org").addClass("open");
//    $("#menu-sys").addClass("active");
//    $("#menu-sys").addClass("open");
//
//    initOrgTree();
//});
//
//function zTreeOnClick(event, treeId, treeNode) {
//    initTable(treeNode.id);
//    alert(treeNode ? treeNode.tId + ", " + treeNode.id : "isRoot");
//}
////初始化机构树
//function initOrgTree() {
//    var setting = {
//        data: {
//            simpleData: {
//                enable: true
//            }
//        },
//        callback: {
//            onClick: function(){
//                searchGoods();
//            }
//        }
//    };
//
//    var treeObj = $("#org_tree");
//    var zNodes;
//
//    $.ajax({
//        url: ROOT_PATH + '/view/inventory/warehouse/getMulSubDictDataByPcode.action',
//        type:'POST',
//        data: {"dictPcode": "AL_POSITION", "dictLevel": 5},
//        dataType:'json',
//        success:function(data){
//            zNodes = data;
//            $.fn.zTree.init($("#org_tree"), setting, zNodes);
//        }
//    });
//
//    treeObj.hover(function () {
//        if (!treeObj.hasClass("showIcon")) {
//            treeObj.addClass("showIcon");
//        }
//    }, function() {
//        treeObj.removeClass("showIcon");
//    });
//}
//
//
//function searchGoods(){
//    if (oTable) {
//        oTable.fnDraw();
//    }
//}
//function showMenu() {
//    var gtObj = $("#dict_provice_id");
//    //alert(gtObj);
//    if($("#gt_combobox").css("display") == "none"){
//        var gtOffset = $("#dict_provice_id").offset();
//        $("#gt_combobox").css({width:gtObj.css("width"),left:gtOffset.left + "px", top:gtOffset.top + gtObj.outerHeight() + "px"}).slideDown("fast");
//    }
//
//    $("body").bind("mousedown", onBodyDown);
//}
//
//function hideMenu() {
//    $("#gt_combobox").fadeOut("fast");
//    $("body").unbind("mousedown", onBodyDown);
//}
//function onBodyDown(event) {
//    if (!(event.target.id == "menuBtn" || event.target.id == "gt_combobox" || $(event.target).parents("#gt_combobox").length>0)) {
//        hideMenu();
//    }
//}
