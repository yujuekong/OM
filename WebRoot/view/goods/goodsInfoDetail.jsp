\
<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../commons/head.jsp"/>

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

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">首页</a>
                </li>
                <li><a href="#">商品管理</a></li>
                <li><a href="${pageContext.request.contextPath}/view/goods/goodsTypeList.jsp">商品类型管理</a></li>
                <li class="active">商品类型详情</li>
            </ul>
            <!-- /.breadcrumb -->

            <div class="nav-search" id="nav-search">
                <a href="javascript:history.back()">
                    <i class="ace-icon fa fa-arrow-left"></i>返回
                </a>
            </div>
            <!-- /.nav-search -->
        </div>

        <div class="page-content">

            <!--<div class="page-header">
                <h1>设备列表
                </h1>
            </div> /.page-header -->

            <div class="row">
                <div class="col-xs-12">
                    <div class="form-group">
                        <div class="col-xs-12">
                            <div class="widget-box">
                                <div class="widget-header widget-header-small">
                                    <h5>商品类型详情</h5>
                                </div>
                                <div class="widget-body">
                                    <div class="widget-body" style="float: left;width: 45%">
                                        <div class="widget-main">
                                            <div class="form-group" hidden="hidden">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    商品ID:
                                                </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   id="gt__type_num"
                                                   value="${goodsInfo.goodsId}" id="goods_Info_id"
                                                   class="required" style="width:450px;"
                                                   name="goodsInfo.goodsId"
                                                   disabled="disabled"/>
                                        </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    商品类别：
                                                </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.goodsTypeName}" id="goods__type_gtId"
                                                   class="required"
                                                   style="width:450px;" name="goodsInfo.gtId"
                                                   disabled="disabled"/>
                                        </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    商品分类编号：
                                                </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.goodsTypeNo}"
                                                   id="goods__type_TypeNo" class="required"
                                                   style="width:450px;" name="goodsInfo.goodsTypeNo"
                                                   disabled="disabled"/>
                                        </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    商品流水编号：
                                                </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.goodsLsNo}" id="goods__type_LsNo"
                                                   class="required"
                                                   style="width:450px;" name="goodsInfo.goodsLsNo"
                                                   disabled="disabled"/>
                                        </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    商品条码：
                                                </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.goodsBarCode}" id="goods__type_BarCode"
                                                   class="required"
                                                   style="width:450px;" name="goodsInfo.goodsBarCode"
                                                   disabled="disabled"/>
                                        </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    商品名称：
                                                </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.goodsName}" id="goods__type_Name"
                                                   class="required"
                                                   style="width:450px;" name="goodsInfo.goodsName"
                                                   disabled="disabled"/>
                                        </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    商品品牌：
                                                </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.goodsBrand}" id="goods__type_Brand"
                                                   class="required"
                                                   style="width:450px;" name="goodsInfo.goodsBrand"
                                                   disabled="disabled"/>
                                        </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    计量单位：
                                                </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.unitName}" id="goods__type_measurementUnit"
                                                   class="required"
                                                   style="width:450px;" name="goodsInfo.measurementUnit"
                                                   disabled="disabled"/>
                                        </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    商品规格：
                                                </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.goodsSpec}" id="goods__type_Spec"
                                                   class="required"
                                                   style="width:450px;" name="goodsInfo.goodsSpec"
                                                   disabled="disabled"/>
                                        </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    商品拼音：
                                                </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.goodsSpell}" id="goods__type_Spell"
                                                   class="required"
                                                   style="width:450px;" name="goodsInfo.goodsSpell"
                                                   disabled="disabled"/>
                                        </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    保鲜期：
                                                </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.antistaling}" id="goods__type_antistaling"
                                                   class="required"
                                                   style="width:450px;" name="goodsInfo.antistaling"
                                                   disabled="disabled"/>
                                        </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    保质期：
                                                </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.qualityPeriod}" id="goods__type_qualityPeriod"
                                                   class="required"
                                                   style="width:450px;" name="goodsInfo.qualityPeriod"
                                                   disabled="disabled"/>
                                        </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    清洗类别：
                                                </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.agentTypeName}" id="goods__agentTypeName"
                                                   class="required"
                                                   style="width:450px;" name="goodsInfo.agentTypeName"
                                                   disabled="disabled"/>
                                        </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    商品状态：
                                                </label>
                                                  <span class="input-icon input-icon-right">
                                                     <input type="text"
                                                            value="${goodsInfo.goodsStatus==0?"正常":"禁用"}"
                                                            id="goods__type_Status"
                                                            class="required"
                                                            style="width:450px;" name="goodsInfo.goodsStatus"
                                                            disabled="disabled"/>
                                                  </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    冷藏状态：
                                                </label>
                                                  <span class="input-icon input-icon-right">
                                                     <input type="text"
                                                            value="${goodsInfo.isFrozen==1?"冷藏":"非冷藏"}"
                                                            id="goods__is_isFrozen"
                                                            class="required"
                                                            style="width:450px;" name="goodsInfo.isFrozen"
                                                            disabled="disabled"/>
                                                  </span>
                                            </div>
                                            <div class="form-group">
                                                <span class="input-icon input-icon-right">
                                                    <label class="control-label">&nbsp;&nbsp;&nbsp;智慧格图片</label>
                                                    <span id="preview1">
                                                        <img width="200px" height="200px"
                                                             src="${pageContext.request.contextPath}${goodsInfo.goodsPic1}"/>
                                                    </span>
                                                    <label>图&nbsp;&nbsp;&nbsp;片：</label>
                                                    <span id="preview2">
                                                        <img width="200px" height="200px"
                                                             src="${pageContext.request.contextPath}${goodsInfo.goodsPic2}"/>
                                                    </span>
                                                </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    商品描述：
                                                </label>
                                        <span class="input-icon input-icon-right">
                                            <textarea name="goodsInfo.goodsDesc" disabled="disabled"
                                                      class="col-xs-10 required"
                                                      style="width:450px;height:300px">${goodsInfo.goodsDesc }</textarea>
                                        </span>
                                            </div>
                                            <div class="form-group" hidden="hidden">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    删除标志：
                                                </label>
                                                <span class="input-icon input-icon-right">
                                                    <input type="text"
                                                           value="${goodsInfo.isDel}" id="goods__type_isDel"
                                                           class="required"
                                                           style="width:450px;" name="goodsInfo.isDel"
                                                           disabled="disabled"/>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <%--<div style="float: right;width: 55%">--%>
                                        <%--<img src="${pageContext.request.contextPath}/${goodsInfo.goodsPic1}"--%>
                                             <%--width="700px" height="500px"/>--%>
                                    <%--</div>--%>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.page-content -->
        </div>
        <!-- /.main-content -->
    </div>
    <!-- /.main-container -->
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
    <script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.dataTables.bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
    <script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>
    <script type="text/javascript">
        $("#submenu-menu-device-info").addClass("active");
        $("#menu-device").addClass("active");
        $("#menu-device").addClass("open");
    </script>
    <script type="text/javascript">
        var url = location.search;
        var Request = {};
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            var strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                Request[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
            }
        }
        var typeNumber =${goodsType.gtName};
        var typeId = decodeURI(request("gtNo"));
        var typeName = decodeURI(request("typeName"));
        $("#dp__type_num").attr("value", typeNumber);
        $("#dp__type_id").attr("value", typeId);
        $("#dp__type_name").attr("value", typeName);

        function request(paras) {
            var url = location.href;
            var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
            var paraObj = {};
            for (var i = 0; j = paraString[i]; i++) {
                paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length);
            }
            var returnValue = paraObj[paras.toLowerCase()];
            if (typeof(returnValue) == "undefined") {
                return "";
            } else {
                return returnValue;
            }
        }


    </script>
</div>
</body>
</html>


