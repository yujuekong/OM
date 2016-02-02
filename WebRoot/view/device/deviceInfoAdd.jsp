<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../commons/head.jsp"/>
<style type="text/css">
    #allmap {
        width: 100%;
        height: 1000px;
        overflow: hidden;
        margin: 0;
        font-family: "微软雅黑";
    }
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=wYELzyLpaRxWSOgD0gS9wGM3"></script>
<body class="no-skin">
<div class="main-container" id="main-container">
    <div>
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul id="uiId" class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">首页</a>
                </li>
                <li><a href="#">设备管理</a></li>
                <li><a href="${pageContext.request.contextPath}/view/device/deviceInfoList.jsp">设备信息管理</a></li>
                <li class="active">新增设备信息</li>
            </ul><!-- /.breadcrumb -->
            <input type="hidden" id='userId' value="${account.userCity}"/>
            <div class="nav-search" id="nav-search">
                <a href="javascript:history.back()">
                    <i class="ace-icon fa fa-arrow-left"></i>返回
                </a>
            </div><!-- /.nav-search -->
        </div>

        <div class="page-content">
            <div class="row">
                <form id="device" action="../deviceInfo/saveOrUpdateDeviceInfo.action" method="post"
                      class="form-horizontal" onsubmit="return check();">
                    <div class="col-xs-12">
                        <div>
                            <div class="widget-header widget-header-small">
                                <h5 id="h5Id">新增设备信息</h5>
                            </div>
                            <div class="widget-body" style=" width:100%;height:100% ">
                                <div class="widget-main" style="float:left;width:50%">
                                    <div class="form-group" style="display:none">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            设备ID：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input readonly type="text"
                                                       value="${deviceInfo.deviceId}"
                                                       id="deviceId" class="required"
                                                       style="width:300px" name="deviceInfo.deviceId"/>
                                                <input readonly type="text"
                                                       value="${deviceInfo.cupCount}"
                                                       id="deviceId" class="required"
                                                       style="width:300px" name="deviceInfo.cupCount"/>
                                                <input readonly type="text"
                                                       value="${deviceInfo.bladeChangeTime}"
                                                       id="deviceId" class="required"
                                                       style="width:300px"
                                                       name="deviceInfo.bladeChangeTime"/>
                                            </span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            设备编号：
                                        </label>
                                                <span class="input-icon input-icon-right">
                                                    <input type="text" value="${deviceInfo.deviceNo}"
                                                           id="deviceNo" class="required"
                                                           style="width:300px" name="deviceInfo.deviceNo"/>
                                                </span>
                                        <span id="deviceNoError" class="input-icon input-icon-right"></span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            所属商圈：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input id="districtIdStr" type="hidden"
                                                       name="deviceInfo.districtId"
                                                       value="${deviceInfo.districtId}"/>
                                                <input id="districtNameStr" type="text"
                                                       value="${deviceInfo.mDistrict.districtName}"
                                                       class="required" style="width:300px;"
                                                       onclick="choiseDistrict(this)"/>
                                                <input type="hidden" id="dictOrgId"
                                                       name="deviceInfo.dictOrgId"
                                                       value="${deviceInfo.dictOrgId}"/>
                                                <input type="hidden" id="dictProviceId"
                                                       name="deviceInfo.dictProviceId"
                                                       value="${deviceInfo.dictProviceId}"/>
                                                <input type="hidden" id="dictRegionId"
                                                       name="deviceInfo.dictRegionId"
                                                       value="${deviceInfo.dictRegionId}"/>
                                            </span>
                                            <span id="districtError" class="input-icon input-icon-right">
                                            </span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right"><i
                                                class="red">*</i>
                                            设备名称：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text" id="deviceName" class="required"
                                                       style="width:300px;" maxlength="50"
                                                       name="deviceInfo.deviceName"
                                                       value="${deviceInfo.deviceName}"/>
                                            </span>
                                            <span id="nameError" class="input-icon input-icon-right">
                                            </span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right"><i
                                                class="red">*</i>
                                            设备类型：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <select id="deviceTypeSelect" class="required"
                                                        style="width: 150px" name="deviceInfo.deviceTypeId">
                                                    <option value="">请选择</option>
                                                </select>
                                            </span>
                                            <span id="deviceTypeSelectError"
                                                  class="input-icon input-icon-right">
                                            </span>
                                    </div>

                                    <div class="form-group" style="display:none">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right"><i
                                                class="red">*</i>
                                            设备状态 ：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input id="deviceStatuSelect" class="required"
                                                       style="width: 150px" name="deviceInfo.deviceStatus"
                                                       value="${deviceInfo.deviceStatus}"/>
                                            </span>
                                    </div>

                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            占地面积：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text" id="deviceInfo.deviceEara"
                                                       class="required" maxlength="10" style="width:300px;"
                                                       name="deviceInfo.deviceEara"
                                                       value="${deviceInfo.deviceEara}"/>
                                            </span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            额定功耗：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text" id="deviceInfo.powerRating"
                                                       class="required" style="width:300px;" maxlength="10"
                                                       name="deviceInfo.powerRating"
                                                       value="${deviceInfo.powerRating}"/>
                                            </span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            供电方式：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text" id="deviceInfo.eleMothod" maxlength="10"
                                                       class="required" style="width:300px;"
                                                       name="deviceInfo.eleMothod"
                                                       value="${deviceInfo.eleMothod}"/>
                                            </span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            设备自重：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text" id="deviceInfo.deviceWeight"
                                                       maxlength="10" class="required" style="width:300px;"
                                                       name="deviceInfo.deviceWeight"
                                                       value="${deviceInfo.deviceWeight}"/>
                                            </span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            显示屏：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text" id="deviceInfo.deviceLed" maxlength="10"
                                                       class="required" style="width:300px;"
                                                       name="deviceInfo.deviceLed"
                                                       value="${deviceInfo.deviceLed}"/>
                                            </span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            触摸屏：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text" id="deviceInfo.deviceTouchScreen"
                                                       maxlength="10" class="required" style="width:300px;"
                                                       name="deviceInfo.deviceTouchScreen"
                                                       value="${deviceInfo.deviceTouchScreen}"/>
                                            </span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            投币机：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text" id="deviceInfo.deviceSlot" maxlength="10"
                                                       class="required" style="width:300px;"
                                                       name="deviceInfo.deviceSlot"
                                                       value="${deviceInfo.deviceSlot}"/>
                                            </span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            设备内温度：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text" id="deviceInfo.deviceTemperature"
                                                       maxlength="10" class="required" style="width:300px;"
                                                       name="deviceInfo.deviceTemperature"
                                                       value="${deviceInfo.deviceTemperature}"/>
                                            </span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            环境温度：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text" id="deviceInfo.environTemperature"
                                                       maxlength="10" class="required" style="width:300px;"
                                                       name="deviceInfo.environTemperature"
                                                       value="${deviceInfo.environTemperature}"/>
                                            </span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            联网通讯功能：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text" id="deviceInfo.isNetwork"
                                                       class="required" maxlength="10" style="width:300px;"
                                                       name="deviceInfo.isNetwork"
                                                       value="${deviceInfo.isNetwork}"/>
                                            </span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            值守人：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input id="realName"
                                                       value="${deviceInfo.isPerson}"
                                                       type="text" class="required" style="width:300px;"
                                                       readonly
                                                       onclick="choiseHouse(this)"/>
                                                <input id="personId" name="deviceInfo.isPerson"
                                                       value="${deviceInfo.isPerson}"
                                                       type="hidden" class="required" style="width:300px;"/>
                                            </span>
                                    </div>
                                    <div class="form-group" style="display:none">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            创建日期：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text" id="deviceInfo.createDate"
                                                       name="deviceInfo.createDate" class="required"
                                                       style="width:150px;" value="${deviceInfo.createDate}"
                                                       data-link-field="delive_time"/>
                                                <i class="ace-icon fa fa-clock-o blue"></i>
                                            </span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            存放地址：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text" id="deviceAddress" class="required"
                                                       maxlength="100" style="width:300px;"
                                                       name="deviceInfo.deviceAddress"
                                                       value="${deviceInfo.deviceAddress}"/>
                                            </span>
                                            <span id="addressError" class="input-icon input-icon-right">
                                            </span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            地址经度：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text" id="deviceLng" class="required"
                                                       style="width:300px;" maxlength="20"
                                                       name="deviceInfo.deviceLng"
                                                       value="${deviceInfo.deviceLng}"/>
                                                <input type="button" value="加载地图" onclick="btn()"/>

                                            </span>
                                            <span id="lngError" class="input-icon input-icon-right">
                                            </span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            地址纬度：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text" id="deviceLat" class="required"
                                                       style="width:300px;" name="deviceInfo.deviceLat"
                                                       maxlength="20" value="${deviceInfo.deviceLat}"/>
                                            </span>
                                            <span id="latError" class="input-icon input-icon-right">
                                            </span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            3G号码：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text" id="deviceTel3g" class="required"
                                                       style="width:300px;" maxlength="20"
                                                       name="deviceInfo.deviceTel3g"
                                                       value="${deviceInfo.deviceTel3g}"/>
                                            </span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            设备IP：
                                        </label>
														<span class="input-icon input-icon-right">
															<input type="text" id="deviceIp" class="required"
                                                                   style="width:300px;" maxlength="20"
                                                                   name="deviceInfo.deviceIp"
                                                                   value="${deviceInfo.deviceIp}"/>
														</span>
                                    </div>
                                    <div class="form-group">
                                        <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
                                            设备端口：
                                        </label>
														<span class="input-icon input-icon-right">
															<input type="text" id="devicePort" class="required"
                                                                   maxlength="10" style="width:300px;"
                                                                   name="deviceInfo.devicePort"
                                                                   value="${deviceInfo.devicePort}"/>
														</span>
                                    </div>
                                </div>
                                <br/>
                                <div style="float:right;width:50%;top:-200px;">
                                    <div id="allmap"></div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <!-- 表单操作按钮开始 -->
                    <div class="row">
                        <div id="form_action_btns" class="col-xs-12" style="text-align: center">
                            <button type="submit" id="save" class="btn btn-sm no-border btn-success">
                                <i class="ace-icon fa fa-floppy-o"></i>保存
                            </button>
                            &nbsp;&nbsp;&nbsp;
                            <a class="btn btn-sm no-border btn-default" onclick="history.back()">
                                <i class="ace-icon fa fa-times red2"></i>取消</a>
                        </div>
                    </div>
                </form>
            </div><!-- /.row -->
        </div><!-- /.page-content -->
    </div><!-- /.main-content -->
</div><!-- /.main-container -->

<div id="gt_combobox"
     style=" display:none; position: absolute;border: 1px solid #ddd;background-color:#F0F0F0;overflow:auto">
    <ul id="org_tree" class="ztree" style="margin-top:0; "></ul>
</div>

<!-- 设备选择弹出窗开始 -->
<div id="district_choise_modal" class="modal hiden fade"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:860px;">
        <div class="modal-content">
            <div class="modal-header thicktitle">
                <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                <span class="modal-title" id="myModalLabel">选择商圈</span>
            </div>
            <div class="modal-body thickcon"
                 style="width:858px;height: 650px; padding-left: 10px; padding-right: 10px;">
                <input type="hidden" id="purchase_goods_rowno"/>
                <input type="hidden" id="choise_type" value="0"/>
                <div class="row">
                    <div class="col-xs-3">
                        <div style="height: 410px;overflow-y: scroll; border: 1px solid #ddd;">
                            <ul id="district_tree" class="ztree"></ul>
                        </div>
                    </div>
                    <div class="col-xs-9">
                        <table id="district_list" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">商圈编号</th>
                                <th class="center">商圈名称</th>
                                <th class="center">商圈地址</th>
                                <th class="center">商圈服务站</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="choose_person" class="modal hiden fade"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:920px;">
        <div class="modal-content">
            <div class="modal-header thicktitle">
                <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                <span class="modal-title" id="myModalLabel">选择值守人</span>
            </div>
            <div class="modal-body thickcon"
                 style="width:910px;height: 650px; padding-left: 10px; padding-right: 10px;">
                <div class="row">
                    <div id="personTree" class="col-xs-3">
                        <div style="height: 410px;overflow-y: scroll; border: 1px solid #ddd;">
                            <ul id="person_tree" class="ztree"></ul>
                        </div>
                    </div>
                    <div id="personTable" class="col-xs-9">
                        <input id="orgId" type="hidden" value="${account.orgId}">
                        <table id="person_list" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">用户账号</th>
                                <th class="center">姓名</th>
                                <th class="center">电话</th>
                                <th class="center">所属分公司</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- basic scripts -->

<!--[if !IE]> -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- <![endif]-->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='${pageContext.request.contextPath}/js/jquery.min.js'>" + "<" + "/script>");
</script>

<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<%--<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>--%>
<script type="text/javascript"
        src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css"/>


<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/js/biz/device/deviceInfoAdd.js"></script>
<script type="text/javascript">
    function check() {
        if (checkDeviceNo() && checkDistrict() && checkName() && checkDeviceType() && checkAddress() && checkLng() && checkLat()) {
            return true;
        } else {
            return false;
        }
    }
    //验证设备编号不为空
    function checkDeviceNo() {
        var district = $("#deviceNo").val();

        if (district == "") {
            $("#deviceNoError").html("<div></div><font color='red'>请填写设备编号!</font>");
            return false;
        } else {
            $("#deviceNoError").html("<div></div>");
            return true;
        }
    }
    //验证商圈不为空
    function checkDistrict() {
        var district = $("#districtNameStr").val();

        if (district == "") {
            $("#districtError").html("<div></div><font color='red'>请选择商圈!</font>");
            return false;
        } else {
            $("#districtError").html("<div></div>");
            return true;
        }
    }
    //验证设备名称不为空
    function checkName() {
        var district = $("#deviceName").val();
        if (district == "") {
            $("#nameError").html("<div></div><font color='red'>设备名称不能为空!</font>");
            return false;
        } else {
            $("#nameError").html("<div></div>");
            return true;
        }
    }
    //验证设备类型不为空
    function checkDeviceType() {
        var deviceType = $("#deviceTypeSelect").val();
        if (deviceType == "") {
            $("#deviceTypeSelectError").html("<div></div><font color='red'>请选择正确的设备类型!</font>");
            return false;
        } else {
            $("#deviceTypeSelectError").html("<div></div>");
            return true;
        }
    }
    //验证设备地址不为空LngError
    function checkAddress() {
        var district = $("#deviceAddress").val();
        if (district == "") {
            $("#addressError").html("<div></div><font color='red'>设备地址不能为空!</font>");
            return false;
        } else {
            $("#addressError").html("<div></div>");
            return true;
        }
    }
    //验证设备经度不为空
    function checkLng() {
        var district = $("#deviceLng").val();
        if (district == "") {
            $("#lngError").html("<div></div><font color='red'>设备经度不能为空!</font>");
            return false;
        } else {
            $("#lngError").html("<div></div>");
            return true;
        }
    }
    //验证设备纬度不为空
    function checkLat() {
        var district = $("#deviceLat").val();
        if (district == "") {
            $("#latError").html("<div></div><font color='red'>设备纬度不能为空!</font>");
            return false;
        } else {
            $("#latError").html("<div></div>");
            return true;
        }
    }


    var selectId = '${deviceInfo.deviceTypeId}';
    $.ajax({
        type: "post",
        url: ROOT_PATH + "/view/device/deviceType/queryAllDeviceType.action",
        data: {"deviceTypeId": selectId},
        success: function (msg) {
            var str = "";
            var json = eval("(" + msg + ")");
            for (var i = 0; i < json.length; i++) {
                var str = "";
                if (json[i].deviceTypeId == selectId) {
                    str = "<option value='" + json[i].deviceTypeId + "' selected='selected'>" + json[i].deviceTypeName + "</option>";
                } else {
                    str = "<option value='" + json[i].deviceTypeId + "'>" + json[i].deviceTypeName + "</option>";
                }
                $("#deviceTypeSelect").append(str);
            }
        },
        error: function (e) {
            console.error("e: ", e);
        }
    });

</script>


</body>
</html>



