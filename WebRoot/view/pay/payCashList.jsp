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
                <li><a href="#">支付管理</a></li>
                <li class="active">设备现金管理</li>
                <%--<p>--%>
                <%--<button class="btn btn-minier " type="button">默认</button>--%>
                <%--<button class="btn btn-minier btn-primary" type="button">主要</button>--%>
                <%--<button class="btn btn-minier btn-info" type="button">信息</button>--%>
                <%--<button class="btn btn-minier btn-success" type="button">成功</button>--%>
                <%--<button class="btn btn-minier btn-warning" type="button">警告</button>--%>
                <%--<button class="btn btn-minier btn-danger" type="button">危险</button>--%>
                <%--<button class="btn btn-minier btn-inverse" type="button">反向</button>--%>
                <%--<button class="btn btn-minier btn-link" type="button">链接</button>--%>
                <%--</p>--%>
            </ul>
            <!-- /.breadcrumb -->

            <!-- /.nav-search -->
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="searchbar">
                        <ul class="list-inline">
                            <li>
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
                            </li>
                            <li>
                                <label>开始日期： </label>
										<span class="input-icon input-icon-right">
											<input type="text" id="dp_quoteStartDate" name="quoteStartDate"
                                                   class="input-sm" style="width:110px;" onchange="dateFind()"/>
											<i class="ace-icon fa fa-calendar blue"></i>
										</span>
                                <label>至</label>
										<span class="input-icon input-icon-right">
											<input type="text" id="dp_quoteEndDate" name="quoteEndDate" class="input-sm"
                                                   style="width:110px;" onchange="dateFind()"/>
											<i class="ace-icon fa fa-calendar blue"></i>
										</span>
                                    <span class="input-icon">
								        <input type="text" placeholder="Search ..." class="nav-search-input"
                                               id="in_keyword" autocomplete="off" style="height: 30px"/>
								     <i class="ace-icon fa fa-search nav-search-icon"></i>
							        </span>
                            </li>
                            <%--<a style="float: right" class="tooltip-info" data-rel="tooltip" title="新增">--%>
                            <%--<button onclick="preAddOrModifyOrderIn('')" class="btn btn-xs btn-success">新增--%>
                            <%--</button>--%>
                            <%--</a>--%>
                        </ul>
                    </div>
                </div>
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <input id="warehousingId" type="hidden">
                            <table id="order_in_list" class="table table-striped table-bordered table-hover"
                                   STYLE="text-align: center">
                                <thead>
                                <tr>
                                    <th class="center">设备编号</th>
                                    <th class="center">收取现金金额</th>
                                    <th class="center">上次收取现金时间</th>
                                    <th class="center">本次收取现金时间</th>
                                    <th class="center">入账接收人</th>
                                    <th class="center">入账时间</th>
                                    <th class="center">现金状态</th>
                                    <th class="center">关联订单</th>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                        <!-- /.span -->
                    </div>
                    <!-- /.row -->
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
<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/ztree/js/jquery.ztree.core-3.5.min.js"></script>

<!-- ace scripts -->
<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>

<script src="${pageContext.request.contextPath}/js/biz/pay/payCash.js"></script>

<script type="text/javascript">

</script>
</body>
</html>



