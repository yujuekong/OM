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
                <li><a href="#">统计分析</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/view/report/goodsReport.jsp">商品销售分析</a></li>
                <li class="active">商品销售分析报表</li>
            </ul><!-- /.breadcrumb -->

            <div class="nav-search" id="nav-search">
                <%--<form class="form-search">--%
							<%--<span class="input-icon">--%>
								<%--<input type="text" placeholder="Search ..." class="nav-search-input"--%>
                                       <%--id="nav-search-input" autocomplete="off"/>--%>
								<%--<i class="ace-icon fa fa-search nav-search-icon"></i>--%>
							<%--</span>--%>
                <%--</form>--%>
            </div><!-- /.nav-search -->
        </div>

        <div class="page-content">
            <div id="chart-container">报表加载中</div>
            <%--<button id="set_chart_data"></button>--%>
                <%--<span class="input-icon input-icon-right">--%>
                    <%--<input value="" type="text"--%>
                           <%--id="dataChart" class="required"--%>
                           <%--style="width:200px;"/>--%>
                <%--</span>--%>
            <input id="year_report"  value="${year}" type="hidden"/>
            <input id="month_report" value="${month}" type="hidden"/>
            <input id="orgId_report" value="${orgId}" type="hidden"/>
            <div id='controllers'>
                <%--<label><input name='chart-type' id='line' type='radio' value='line'/> Line chart</label>--%>
                <label><input name='chart-type' id='bar' type='radio' value='bar2d'/> Bar chart</label>
                <label><input name='chart-type' id='column' type='radio' value='column3d' checked='true'/> Column
                    chart</label>

            </div>
        </div><!-- /.page-content -->
    </div><!-- /.main-content -->

</div><!-- /.main-container -->

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

<!-- inline scripts related to this page -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fusionCharts3.1.0/fusioncharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/biz/report/goodsReportCharts.js"></script>
<script type="text/javascript">
    $("#submenu-menu-report-sale").addClass("active");
    $("#menu-report").addClass("active");
    $("#menu-report").addClass("open");
</script>
</body>
</html>