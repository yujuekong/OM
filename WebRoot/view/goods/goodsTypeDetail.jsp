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
                                    <div class="widget-main">
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                商品类别ID：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text" value="${goodsType.gtId}" id="gt__type_num"
                                                               class="required" style="width:450px;"
                                                               name="purchase.deliveryTime"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                上级商品类别：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text" value="${goodsType.dgtId==null?"无上级商品类别":goodsType.goodsTypeName}" id="gt__super_type" class="required"
                                                               style="width:450px;" name="purchase.deliveryTime"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                商品类别编号：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text" value="${goodsType.gtNo}" id="gt__type_number" class="required"
                                                               style="width:450px;" name="purchase.deliveryTime"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                商品类别名称：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text" value="${goodsType.gtName}" id="gt__type_name" class="required"
                                                               style="width:450px;" name="purchase.deliveryTime" onfocus="showMenu()"/>
													</span>
                                        </div>
                                        <div hidden="hidden" class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                删除标志：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text" value="${goodsType.isDel==0?"启用":"删除"}" id="dp__type_isDel" class="required"
                                                               style="width:450px;" name="purchase.deliveryTime"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                        </div>
                                    </div>
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
    <%--<script type="text/javascript">--%>
        <%--var url = location.search;--%>
        <%--var Request = new Object();--%>
        <%--if (url.indexOf("?") != -1) {--%>
            <%--var str = url.substr(1);--%>
            <%--var strs = str.split("&");--%>
            <%--for (var i = 0; i < strs.length; i++) {--%>
                <%--Request[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);--%>
            <%--}--%>
        <%--}--%>
        <%--var typeNumber =${goodsType.gtName};--%>
        <%--var typeId = decodeURI(request("gtNo"));--%>
        <%--var typeName = decodeURI(request("typeName"));--%>
        <%--$("#dp__type_num").attr("value", typeNumber);--%>
        <%--$("#dp__type_id").attr("value", typeId);--%>
        <%--$("#dp__type_name").attr("value", typeName);--%>

        <%--function request(paras) {--%>
            <%--var url = location.href;--%>
            <%--var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");--%>
            <%--var paraObj = {};--%>
            <%--for (var i = 0; j = paraString[i]; i++) {--%>
                <%--paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length);--%>
            <%--}--%>
            <%--var returnValue = paraObj[paras.toLowerCase()];--%>
            <%--if (typeof(returnValue) == "undefined") {--%>
                <%--return "";--%>
            <%--} else {--%>
                <%--return returnValue;--%>
            <%--}--%>
        <%--}--%>

    <%--</script>--%>
</div>
</body>
</html>



