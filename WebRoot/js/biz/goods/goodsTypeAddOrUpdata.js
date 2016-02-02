jQuery(function ($) {
    $("#submenu-submenu-menu-sys-org-user").addClass("active");
    $("#submenu-menu-sys-org").addClass("active");
    $("#submenu-menu-sys-org").addClass("open");
    $("#menu-sys").addClass("active");
    $("#menu-sys").addClass("open");

    initOrgTree();
    $('#goodsType').bootstrapValidator({
        message: 'This value is not valid',
        fields: {
            'goodsType.gtName': {
                validators: {
                    notEmpty: {
                        //message:"请输入长度为2-10的名称",
                    },
                    stringLength: {
                        min: 2,
                        max: 10,
                    },
                }
            },
        }
    })
});


//初始化机构树
function initOrgTree() {
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        view: {
            dblClickExpand: false
        },
        callback: {
            onClick: function (event, treeId, treeNode) {
                $("#orgStrId").val(treeNode.id);
                $("#regionStrId").val(treeNode.pid);
                $("#userPro").val(treeNode.name);
                hideMenu();
            }
        }
    };

    var treeObj = $("#gt_tree");
    var zNodes;

    $.ajax({
        url: ROOT_PATH + '/view/goods/goodsInfo/getMulSubGoodsTypeDataByPcode.action',
        type: 'POST',
        data: {"dictPcode": "AL_POSITION"},
        dataType: 'json',
        success: function (data) {
            zNodes = data;
            $.fn.zTree.init($("#gt_tree"), setting, zNodes);
        }
    });

    treeObj.hover(function () {
        if (!treeObj.hasClass("showIcon")) {
            treeObj.addClass("showIcon");
        }
    }, function () {
        treeObj.removeClass("showIcon");
    });
}

function searchGoods() {

}

function showMenu() {
    var gtObj = $("#userPro");
    if ($("#gt_combobox").css("display") == "none") {
        var gtOffset = $("#userPro").offset();
        $("#gt_combobox").css({
            width: gtObj.css("width"),
            left: gtOffset.left + "px",
            top: gtOffset.top + gtObj.outerHeight() + "px"
        }).slideDown("fast");
    }

    $("body").bind("mousedown", onBodyDown);
}

function hideMenu() {
    $("#gt_combobox").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
