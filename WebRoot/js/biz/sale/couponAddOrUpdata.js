jQuery(function ($) {

    initTree();
    $("#activityCoupon_startDate").datetimepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
    });
    $("#activityCoupon_endDate").datetimepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
    });
    $("#activityCoupon_getCouponEndDate").datetimepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: true,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
    });
});

//区域下拉框
function showMenu() {
    var gtObj = $("#dictOrg");
    if ($("#gt_combobox").css("display") == "none") {
        var gtOffset = $("#dictOrg").offset();
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

//初始化机构树
function initTree() {
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
            beforeClick: beforeClick,
            onClick: function (event, treeId, treeNode) {
                $("#regionStrId").val(treeNode.id);
                $("#dictOrg").val(treeNode.name);
                hideMenu();
            }
        }
    };

    var treeObj = $("#gt_tree");
    var zNodes;

    $.ajax({
        url: ROOT_PATH + '/view/sys/sysDict/getMulSubDictDataByPcode.action',
        type: 'POST',
        data: {
            "dictPcode": "AL_POSITION",
            "dictLevel": "5"
        },
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

function beforeClick(treeId, treeNode) {
    var check = (treeNode.level == 3);
    if (!check) alert("只能选择分公司...");
    return check;
}