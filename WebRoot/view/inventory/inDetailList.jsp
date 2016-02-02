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
                <li><a href="#">仓库管理</a></li>
                <li class="active">商品出入库管理</li>
                <li class="active">商品入库</li>
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
            <div class="row">
                <div class="col-xs-12">
                    <div class="searchbar">
                        <div>
                            <label>快捷搜索： </label>

                            <div class="btn-group">
                                <p>
                                    <a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"
                                       onclick="quickSearch(0)">全部 </a>
                                    <a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"
                                       onclick="quickSearch(1)">本周</a>
                                    <a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"
                                       onclick="quickSearch(2)">上周</a>
                                    <a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"
                                       onclick="quickSearch(3)">本月</a>
                                    <a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"
                                       onclick="quickSearch(4)">上月</a>
                                </p>
                            </div>
                        </div>
                        <ul class="list-inline">

                            <li>
                                <label>开始日期： </label>
										<span class="input-icon input-icon-right">
											<input type="text" id="dp_quoteStartDate" name="quoteStartDate"
                                                   class="input-sm" style="width:110px;"/>
											<i class="ace-icon fa fa-calendar blue"></i>
										</span>
                                <label>至</label>
										<span class="input-icon input-icon-right">
											<input type="text" id="dp_quoteEndDate" name="quoteEndDate" class="input-sm"
                                                   style="width:110px;"/>
											<i class="ace-icon fa fa-calendar blue"></i>
										</span>
                            </li>
                            <li>
                                <label>入库单号： </label>
                                <input type="text" id="quotationNo" name="quotationNo" class="input-sm"
                                       style="width:100px;"/>
                                <a id="searchQuoteBillBtn" class="btn btn-sm btn-success no-border">
                                    <i class="ace-icon fa fa-search nav-search-icon"></i>
                                    搜索</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <input type="hidden" id="warehousingId" value="${warehousingId }">
                            <table id="order_in_list" class="table table-striped table-bordered table-hover"
                                   STYLE="text-align: center">
                                <thead>
                                <tr>
                                    <%--<th class="center">--%>
                                    <%--&lt;%&ndash;<label class="position-relative">&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<input type="checkbox" class="ace" />&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<span class="lbl"></span>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;</label>&ndash;%&gt;--%>
                                    <%--</th>--%>
                                    <th class="center">入库单编号</th>
                                    <th class="center">商品名称</th>
                                    <th class="center">计划入库数量</th>
                                    <th class="center">入库数量</th>
                                    <th class="center">入库单价</th>
                                    <th class="center">入库金额</th>
                                    <th class="center">明细备注</th>
                                    <th class="center">操作</th>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                        <!-- /.span -->
                    </div>
                    <!-- /.row -->
                    <div class="col-xs-6">
                        <tr>
                            <td>
                                <a class="tooltip-info" data-rel="tooltip" title="维修">
                                    <button onclick="preAddOrModifyEntryDetail('')" class="btn btn-xs btn-success">新增
                                    </button>
                                </a>
                                <button class="btn btn-xs btn-danger">批量删除</button>
                            </td>
                            <td style="vertical-align:top;">

                            </td>
                        </tr>
                    </div>
                    <div class="hr hr-18 dotted hr-double"></div>


                </div>
                <!-- /.col -->
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
<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/ztree/js/jquery.ztree.core-3.5.min.js"></script>

<!-- ace scripts -->
<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>

<script src="${pageContext.request.contextPath}/js/biz/inventory/inDetailList.js"></script>
</body>
</html>

