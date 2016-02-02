jQuery(function ($) {
    //$("#submenu-submenu-menu-sys-org-user").addClass("active");
    //$("#submenu-menu-sys-org").addClass("active");
    //$("#submenu-menu-sys-org").addClass("open");
    //$("#menu-sys").addClass("active");
    //$("#menu-sys").addClass("open");
    initOrgTree();
    initUnitTree();

    $('#goods').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            'goodsInfo.goodsTypeName': {
                validators: {
                    notEmpty: {}
                }
            },
            'goodsInfo.goodsName': {
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
            'goodsInfo.measurementUnit': {
                validators: {
                    notEmpty: {}
                }
            },
            'goodsInfo.agentType': {
                validators: {
                    notEmpty: {}
                }
            },
            'goodsInfo.synopsis': {
                validators: {
                    notEmpty: {},
                    stringLength: {
                        min: 2,
                        max: 7,
                    },
                }
            },
            'goodsInfo.goodsBarCode': {
                validators: {
                    notEmpty: {},
                    stringLength: {
                        min: 5,
                        max: 50,
                    },
                }
            },
            'goodsInfo.goodsBrand': {
                validators: {
                    notEmpty: {},
                    stringLength: {
                        min: 2,
                        max: 5,
                    },
                }
            },
            'goodsInfo.goodsSpec': {
                validators: {
                    notEmpty: {},
                    stringLength: {
                        min: 2,
                        max: 5,
                    },
                }
            },
            'goodsInfo.antistaling': {
                validators: {
                    notEmpty: {},
                    stringLength: {
                        min: 2,
                        max: 5,
                    },
                }
            },
            'goodsInfo.qualityPeriod': {
                validators: {
                    notEmpty: {},
                    stringLength: {
                        min: 2,
                        max: 5,
                    },
                }
            }
        }
    });
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
            beforeClick: beforeClick,
            onClick: function (event, treeId, treeNode) {
                $("#orgStrId").val(treeNode.id);
                $("#regionStrId").val(treeNode.pid);
                $("#goodsType").val(treeNode.name);
                hideMenu();
                //$("#goods").bootstrapValidator('validateField', "goodsInfo.goodsTypeName");
                $("#goods").bootstrapValidator('updateStatus', 'goodsInfo.goodsTypeName', 'NOT_VALIDATED')
                    .bootstrapValidator('validateField', 'goodsInfo.goodsTypeName');
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


//初始化单位树
function initUnitTree() {
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
                $("#unitId").val(treeNode.id);
                $("#unitStr").val(treeNode.name);
                hideUnitMenu();
            }
        }
    };

    var treeObj = $("#gt_tree");
    var zNodes;

    $.ajax({
        url: ROOT_PATH + '/view/goods/goodsInfo/getMulSubGoodsUnitDataByPcode.action',
        type: 'POST',
        data: {"dictPcode": "UNIT", "dictLevel": 3},
        dataType: 'json',
        success: function (data) {
            zNodes = data;
            $.fn.zTree.init($("#unit_tree"), setting, zNodes);
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
    var check = (treeNode && !treeNode.isParent);
    if (!check) alert("只能选择具体类别...");
    return check;
}


function showMenu() {
    var gtObj = $("#goodsType");
    if ($("#gt_combobox").css("display") == "none") {
        var gtOffset = $("#goodsType").offset();
        $("#gt_combobox").css({
            width: gtObj.css("width"),
            left: gtOffset.left + "px",
            top: gtOffset.top + gtObj.outerHeight() + "px"
        }).slideDown("fast");
    }

    $("body").bind("mousedown", onBodyDown);
}

function showUnitMenu() {
    var gtObj = $("#unitStr");
    if ($("#unit_combobox").css("display") == "none") {
        var gtOffset = $("#unitStr").offset();
        $("#unit_combobox").css({
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
function hideUnitMenu() {
    $("#unit_combobox").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "gt_combobox" || $(event.target).parents("#gt_combobox").length > 0)) {
        hideMenu();
    }
}


var agentType = $("#agentType").val();
$.ajax({
    type: "post",
    url: ROOT_PATH + "/view/goods/goodsInfo/getAllGoodsAgent.action",
    data: {"dictCode": "OUT_AGENT_METHOD"},
    success: function (msg) {
        var str = "";
        var json = eval("(" + msg + ")");
        for (var i = 0; i < json.length; i++) {
            var str = "";
            if (json[i].dictId == agentType) {
                str = "<option value='" + json[i].dictId + "' selected='selected'>" + json[i].dictName + "</option>";
            } else {
                str = "<option value='" + json[i].dictId + "'>" + json[i].dictName + "</option>";
            }
            $("#goodsTypeSelect").append(str);
        }
        getTypeName();
    },
    error: function (e) {
        console.error("e: ", e);
    }
});

function getTypeName() {
    var typeName = $("#goodsTypeSelect").find("option:selected").text();
    $("#agentTypeName").val(typeName);
}


var unitId = $("#unitId").val();
$.ajax({
    type: "post",
    url: ROOT_PATH + "/view/goods/goodsInfo/getAllGoodsAgent.action",
    data: {"dictCode": "GOODS_UNIT"},
    success: function (msg) {
        var str = "";
        var json = eval("(" + msg + ")");
        for (var i = 0; i < json.length; i++) {
            var str = "";
            if (json[i].dictId == unitId) {
                str = "<option value='" + json[i].dictId + "' selected='selected'>" + json[i].dictName + "</option>";
            } else {
                str = "<option value='" + json[i].dictId + "'>" + json[i].dictName + "</option>";
            }
            $("#unitSelect").append(str);
        }
        getUnitName();
    },
    error: function (e) {
        console.error("e: ", e);
    }
});

function getUnitName() {
    var unitName = $("#unitSelect").find("option:selected").text();
    $("#unitName").val(unitName);
}

function validate_img(a) {
    var file = a.value;
    if (!/.(gif|jpg|jpeg|png|GIF|JPG|png)$/.test(file)) {
        alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
        return false;
    } else {
        var image = new Image();
        image.src = file;
        var height = image.height;
        var width = image.width;
        var filesize = image.filesize;
        //alert(height + "x.." + filesize);
        if (filesize < 102400) {
            alert('请上传小于1M的图片');
            return false;
        }
    }
    alert("图片通过");
}


function previewImage(file) {
    var MAXWIDTH = 200;
    var MAXHEIGHT = 200;
    var div = document.getElementById('preview1');
    if (file.files && file.files[0]) {
        div.innerHTML = '<img id=imghead>';
        var img = document.getElementById('imghead');
        img.onload = function () {
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            img.width = rect.width;
            img.height = rect.height;
            img.style.marginLeft = rect.left + 'px';
            img.style.marginTop = rect.top + 'px';
        };
        var reader = new FileReader();
        reader.onload = function (evt) {
            img.src = evt.target.result;
        };
        reader.readAsDataURL(file.files[0]);
    }
    else {
        var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text;
        div.innerHTML = '<img id=imghead>';
        var img = document.getElementById('imghead');
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
        div.innerHTML = "<div id=divhead style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;margin-left:" + rect.left + "px;" + sFilter + src + "\"'></div>";
    }
}
function previewImage2(file) {
    var MAXWIDTH = 200;
    var MAXHEIGHT = 200;
    var div = document.getElementById('preview2');
    if (file.files && file.files[0]) {
        div.innerHTML = '<img id=imghead2>';
        var img = document.getElementById('imghead2');
        img.onload = function () {
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            img.width = rect.width;
            img.height = rect.height;
            img.style.marginLeft = rect.left + 'px';
            img.style.marginTop = rect.top + 'px';
        };
        var reader = new FileReader();
        reader.onload = function (evt) {
            img.src = evt.target.result;
        };
        reader.readAsDataURL(file.files[0]);
    }
    else {
        var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text;
        div.innerHTML = '<img id=imghead>';
        var img = document.getElementById('imghead');
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
        div.innerHTML = "<div id=divhead style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;margin-left:" + rect.left + "px;" + sFilter + src + "\"'></div>";
    }
}
function clacImgZoomParam(maxWidth, maxHeight, width, height) {
    var param = {top: 0, left: 0, width: width, height: height};
    if (width > maxWidth || height > maxHeight) {
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;
        if (rateWidth > rateHeight) {
            param.width = maxWidth;
            param.height = Math.round(height / rateWidth);
        } else {
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
}