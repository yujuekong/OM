jQuery(function ($) {
    $("#submenu-menu-device-info").addClass("active");
    $("#menu-device").addClass("active");
    $("#menu-device").addClass("open");
    var deviceId = $("#deviceId").val();
    if (deviceId != null && deviceId != "") {
        btn();
    }

    //单个追加设备到申购单
    $(document).on("dblclick", "#district_list tbody tr", function () {
        var $choiseDistrict = getChoiseDistrict($(this));
        var a = $("#districtIdStr").val($choiseDistrict.districtId);
        $("#districtNameStr").val($choiseDistrict.districtName);
        $("#dictOrgId").val($choiseDistrict.dictOrgId);
        $("#dictProviceId").val($choiseDistrict.dictProviceId);
        $("#dictRegionId").val($choiseDistrict.dictRegionId);
        $("#district_choise_modal").modal('hide');
    });
    //单个用户添加到表单
    $(document).on("dblclick", "#person_list tbody tr", function () {
        var $choisePerson = getChoisePerson($(this));
        $("#personId").val($choisePerson.userId);
        $("#realName").val($choisePerson.realName);
        $("#choose_person").modal('hide');
    });

    //根据登陆用户类型判断是否显示树形搜索
    if ($("#orgId").val() != '') {
        $("#personTree").hide();
        $("#personTable").attr("class", "col-xs-12");
    }

    //$('#device').bootstrapValidator({
    //    message: 'This value is not valid',
    //    fields: {
    //        'deviceInfo.deviceName': {
    //            validators: {
    //                notEmpty: {},
    //                stringLength: {
    //                    min: 2,
    //                    max: 40,
    //                },
    //            }
    //        },
    //        'deviceInfo.deviceTypeId': {
    //            validators: {
    //                notEmpty: {
    //                    message: "请选择设备类型",
    //                }
    //            }
    //        },
    //        'deviceInfo.deviceAddress': {
    //            validators: {
    //                notEmpty: {}
    //            }
    //        },
    //        'deviceInfo.deviceLng': {
    //            validators: {
    //                notEmpty: {}
    //            }
    //        },
    //        'deviceInfo.deviceLat': {
    //            validators: {
    //                notEmpty: {}
    //            }
    //        }
    //    }
    //});
});

//获得选择设备对象
function getChoiseDistrict(ele_goodsRow) {
    var $choiseDistrict = {};
    var $ele_td = ele_goodsRow.find("td");
    $choiseDistrict.districtId = $ele_td.find("input[name='districtId']").eq(0).val();
    $choiseDistrict.districtName = $ele_td.find("label[name='districtName']").eq(0).html();
    $choiseDistrict.dictOrgId = $ele_td.find("input[name='dictOrgId']").eq(0).val();
    $choiseDistrict.dictProviceId = $ele_td.find("input[name='dictProviceId']").eq(0).val();
    $choiseDistrict.dictRegionId = $ele_td.find("input[name='dictRegionId']").eq(0).val();
    return $choiseDistrict;
}
//获得选择用户对象
function getChoisePerson(ele_goodsRow) {
    var $choisePerson = {};
    var $ele_td = ele_goodsRow.find("td");
    $choisePerson.userId = $ele_td.find("input[name='userId']").eq(0).val();
    $choisePerson.realName = $ele_td.find("label[name='realName']").eq(0).html();
    return $choisePerson;
}

function btn() {
    var userCity = $("#userId").val();
    var deviceLng = $("#deviceLng").val();
    var deviceLat = $("#deviceLat").val();
    var deviceName = $("#deviceName").val();
    var deviceAddress = $("#deviceAddress").val();

    var map = new BMap.Map("allmap");//初始化地图
    var point = new BMap.Point(deviceLng, deviceLat);//设置一个点为光谷
    map.centerAndZoom(userCity, 15);//将光谷点设置为中心点并设置地图级别
    map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
    var label = new BMap.Label("设备名称:" + deviceName + ",</br>设备地址:" + deviceAddress, {offset: new BMap.Size(20, -10)});
    var po = new BMap.Point(deviceLng, deviceLat);//设置一个点为光谷
    addMarker(po, label);

    map.addEventListener("click", function (e) {
        var a = e.point.lng;
        var b = e.point.lat;
        var point1 = new BMap.Point(a, b);//将点击的点生成新的坐标
        var label = new BMap.Label("你选择的位置是:" + a + "," + b, {offset: new BMap.Size(20, -10)});
        addMarker(point1, label);
        $("#deviceLng").val(a);
        $("#deviceLat").val(b);
    });
    /*
     *
     * 往地图上添加标注
     *
     **/
    function addMarker(point, label) {
        var marker = new BMap.Marker(point);
        map.addOverlay(marker);
        marker.setLabel(label);
    }
}

//初始化机构树

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

function beforeClick(treeId, treeNode) {
    var check = (treeNode.level == "3");
    if (!check) alert("只能选择服务站...");
    return check;
}

//单个选择商品
function choiseDistrict(input_goodsName) {
    initOrgTree();
    $('#district_choise_modal').modal('show');
}
dTable = null;
function initDTable() {
    if (dTable == null) {
        dTable = $("#district_list").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,
            "bProcessing": true,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/region/serviceInfo/queryService.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "districtNo", "sClass": "center"},
                {
                    "mDataProp": "districtName", "mRender": function (data, type, full) {
                    return '<label name="districtName">' + data + '</label><input name="districtId" type="hidden" value="' + full.districtId + '"/>';
                }
                },
                {"mDataProp": "districtAddress", "sClass": "center"},
                {
                    "mDataProp": "sysDict.dictName", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="orgName">' + data + '</label><input name="dictOrgId" type="hidden" value="' + full.dictOrgId + '"/><input name="dictProviceId" type="hidden" value="' + full.dictProviceId + '"/><input name="dictRegionId" type="hidden" value="' + full.dictRegionId + '"/>';
                }
                },
            ],
            "oLanguage": {
                "sProcessing": "正在加载中......",
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "没有数据！",
                "sEmptyTable": "表中无数据存在！",
                "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
                "sInfoEmpty": "显示0到0条记录",
                "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
                zeroRecords: "没有内容",
                "sSearch": "搜索",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast": "末页"
                }
            },
            "aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
                {"bSortable": false, "aTargets": [0, 1, 2, 3]},  //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
            ],
            "fnServerData": retrieveDataDT,
        });
    } else {
        dTable.fnDraw(); //重新加载数据
    }
}

/**
 * sSource   查询链接
 * aoData    参数
 * fnCallback 返回数据填充方法
 */
function retrieveDataDT(sSource, aoData, fnCallback) {
    //商品名称或编号
    var keyword = $("#keyword").val();
    var param = {"keyword": keyword};
    var selectNodes = $.fn.zTree.getZTreeObj("district_tree");
    if (selectNodes != null) {
        if (selectNodes.getSelectedNodes() != "") {
            var id = selectNodes.getSelectedNodes()[0].id;
            var pid = selectNodes.getSelectedNodes()[0].pid;
            var level = selectNodes.getSelectedNodes()[0].level;
            param.id = id;
            param.pid = pid;
            param.level = level;
        }
    }
    for (var i = 0; i < aoData.length; i++) {
        var _aoData = aoData[i];
        if (_aoData.name == "iDisplayStart") {
            /*开始页数*/
            param.iDisplayStart = _aoData.value;
        } else if (_aoData.name == "iDisplayLength") {
            /*记录条数*/
            param.iDisplayLength = _aoData.value;
        } else if (_aoData.name == "sEcho") {
            /*操作次数*/
            param.sEcho = _aoData.value;
        }
    }
    //提交访问
    $.ajax({
        type: "POST",
        url: sSource,
        dataType: "json",
        data: param, // 以json格式传递  
        success: function (resp) {
            fnCallback(resp);
//            removeMask($('.goods-container'));
        }
    });
}
//初始化商圈机构树
function initOrgTree() {
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: function () {
                searchService();
            }
        }
    };
    var treeObj = $("#district_tree");
    var zNodes;
    $.ajax({
        url: ROOT_PATH + '/view/inventory/warehouse/getMulSubDictDataByPcode.action',
        type: 'POST',
        data: {"dictPcode": "AL_POSITION", "dictLevel": "5"},
        dataType: 'json',
        success: function (data) {
            zNodes = data;
            $.fn.zTree.init($("#district_tree"), setting, zNodes);
        }
    });
    treeObj.hover(function () {
        if (!treeObj.hasClass("showIcon")) {
            treeObj.addClass("showIcon");
        }
    }, function () {
        treeObj.removeClass("showIcon");
    });
    initDTable();
}
//搜索商圈
function searchService() {
    if (dTable) {
        dTable.fnDraw();
    }
}

function choiseHouse(input_goodsName) {
    resetSelectHouse();
    initPersonOrgTree();
    $('#choose_person').modal('show');
}
function resetSelectHouse() {
    initHouseTree();
}

pTable = null;
function initHouseTree() {
    if (pTable == null) {
        pTable = $("#person_list").dataTable({
            "bAutoWidth": false,
            "bLengthChange": false,
            "bProcessing": true,
            "bFilter": false,// 搜索栏
            "bServerSide": true,//异步请求必须设置
            "sAjaxSource": ROOT_PATH + "/view/device/deviceInfo/choosePerson.action",  //异步请求地址
            "aoColumns": [
                {"mDataProp": "userName", "sClass": "center"},
                {
                    "mDataProp": "realName", "sClass": "center", "mRender": function (data, type, full) {
                    return '<label name="realName">' + data + '</label><input name="userId" type="hidden" value="' + full.userId + '"/>';
                }
                },
                {"mDataProp": "tel", "sClass": "center"},
                {"mDataProp": "sysDict.dictName", "sClass": "center"},
            ],
            "oLanguage": {
                "sProcessing": "正在加载中......",
                "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "没有数据！",
                "sEmptyTable": "表中无数据存在！",
                "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
                "sInfoEmpty": "显示0到0条记录",
                "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
                zeroRecords: "没有内容",
                "sSearch": "搜索",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上一页",
                    "sNext": "下一页",
                    "sLast": "末页"
                }
            },
            "aoColumnDefs": [//和aoColums类似，但他可以给指定列附近爱属性
                {
                    "bSortable": false, "aTargets": [0, 1, 2, 3]
                },
                {
                    "aTargets": [0],
                    "bVisible": true,
                },//这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
                {
                    "sDefaultContent": '', "aTargets": ['_all'],
                }
            ],
            "fnServerData": retrieveDataPerson,
        });
    } else {
        pTable.fnDraw(); //重新加载数据
    }
}

/**
 * sSource   查询链接
 * aoData    参数
 * fnCallback 返回数据填充方法
 */
function retrieveDataPerson(sSource, aoData, fnCallback) {
    //商品名称或编号
    var keywordPerson = $("#keyword").val();
    var param = {
        "keyword": keywordPerson
    };
    var selectNodes = $.fn.zTree.getZTreeObj("person_tree");
    if (selectNodes != null) {
        if (selectNodes.getSelectedNodes() != "") {
            var id = selectNodes.getSelectedNodes()[0].id;
            var pid = selectNodes.getSelectedNodes()[0].pid;
            var level = selectNodes.getSelectedNodes()[0].level;
            param.id = id;
            param.pid = pid;
            param.level = level;
        }
    }
    for (var i = 0; i < aoData.length; i++) {
        var _aoData = aoData[i];
        if (_aoData.name == "iDisplayStart") {
            /*开始页数*/
            param.iDisplayStart = _aoData.value;
        } else if (_aoData.name == "iDisplayLength") {
            /*记录条数*/
            param.iDisplayLength = _aoData.value;
        } else if (_aoData.name == "sEcho") {
            /*操作次数*/
            param.sEcho = _aoData.value;
        }
    }
    //提交访问
    $.ajax({
        type: "POST",
        url: sSource,
        dataType: "json",
        data: param, // 以json格式传递
        success: function (resp) {
            fnCallback(resp);
        }
    });
}

function initPersonOrgTree() {
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: function () {
                searchPerson();
            }
        }
    };
    var treeObj = $("#person_tree");
    var zNodes;
    $.ajax({
        url: ROOT_PATH + '/view/inventory/warehouse/getMulSubDictDataByPcode.action',
        type: 'POST',
        data: {"dictPcode": "AL_POSITION", "dictLevel": "5"},
        dataType: 'json',
        success: function (data) {
            zNodes = data;
            $.fn.zTree.init($("#person_tree"), setting, zNodes);
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
function searchPerson() {
    if (pTable) {
        pTable.fnDraw();
    }
}