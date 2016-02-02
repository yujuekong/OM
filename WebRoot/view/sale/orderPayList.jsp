<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../commons/head.jsp"/>
<body class="no-skin">
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>
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
                <li><a href="#">销售管理</a></li>
                <li class="active">订单管理</li>
            </ul><!-- /.breadcrumb -->

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
											 <!--<input type="text" id="dp_quoteStartDate" name="quoteStartDate"
                                                   class="input-sm" style="width:110px;"/>
											<i class="ace-icon fa fa-calendar blue"></i> -->
											<input type="text" id="dp_quoteStartDate" name="quoteStartDate" style="width:110px;" onchange="chaxun()"/>
											<i class="ace-icon fa fa-clock-o blue"></i>
										</span>
                                <label>至</label>
										<span class="input-icon input-icon-right">
											<input type="text" id="dp_quoteEndDate" name="quoteStartDate" style="width:110px;" onchange="chaxun()"/>
											<i class="ace-icon fa fa-clock-o blue"></i>
											<!-- <input type="text" id="dp_quoteEndDate" name="quoteEndDate" class="input-sm"
                                                   style="width:110px;"/>
											<i class="ace-icon fa fa-calendar blue"></i> -->
										</span>
                                    <span class="input-icon">
								        <input type="text" placeholder="Search ..." class="nav-search-input"
                                               id="searchOrderPay" autocomplete="off" style="height: 30px"/>
								     <i class="ace-icon fa fa-search nav-search-icon"></i>
							        </span>
                            </li>
                            <%--<a style="float: right" class="tooltip-info" data-rel="tooltip" title="新增">--%>
                            <%--<button onclick="preAddOrModifyOrderIn('')" class="btn btn-xs btn-success">新增--%>
                            <%--</button>--%>
                            <%--</a>--%>
                        </ul>
                    </div>
                    <%--<div class="searchbar">--%>
                    <%--<div >--%>
                    <%--<label>快捷搜索： </label>--%>
                    <%--<div class="btn-group">--%>
                    <%--<p>--%>
                    <%--<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"--%>
                    <%--onclick="quickSearch(0)">全部 </a>--%>
                    <%--<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"--%>
                    <%--onclick="quickSearch(1)">本周</a>--%>
                    <%--<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"--%>
                    <%--onclick="quickSearch(2)">上周</a>--%>
                    <%--<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"--%>
                    <%--onclick="quickSearch(3)">本月</a>--%>
                    <%--<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"--%>
                    <%--onclick="quickSearch(4)">上月</a>--%>
                    <%--</p>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--<ul class="list-inline">--%>
                    <%----%>
                    <%--<li>--%>
                    <%--<label>开始日期： </label>--%>
                    <%--<span class="input-icon input-icon-right">--%>
                    <%--<input type="text" id="dp_quoteStartDate" name="quoteStartDate" class="input-sm" style="width:110px;" />--%>
                    <%--<i class="ace-icon fa fa-calendar blue"></i>--%>
                    <%--</span>--%>
                    <%--<label>至</label>--%>
                    <%--<span class="input-icon input-icon-right">--%>
                    <%--<input type="text" id="dp_quoteEndDate" name="quoteEndDate" class="input-sm" style="width:110px;"/>--%>
                    <%--<i class="ace-icon fa fa-calendar blue"></i>--%>
                    <%--</span>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                    <%--<label>交易号： </label>--%>
                    <%--<input type="text" id="quotationNo" name="quotationNo" class="input-sm" style="width:100px;" />--%>
                    <%--<a id="searchQuoteBillBtn" class="btn btn-sm btn-success no-border" onclick="initTable()">--%>
                    <%--<i class="ace-icon fa fa-search nav-search-icon"></i>--%>
                    <%--搜索</a>--%>
                    <%--</li>--%>
                    <%--</ul>--%>
                    <%--</div>--%>
                </div>
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <table id="orderPay_list" class="table table-striped table-bordered table-hover"
                                   style="text-align: center">
                                <thead>
                                <tr>
                                    <th class="center">取货码</th>
                                    <th class="center">商品名称</th>
                                    <th class="center">商品计量</th>
                                    <th class="center">商品单位</th>
                                    <th class="center">付款金额</th>
                                    <th class="center">用户真实姓名</th>
                                    <th class="center">手机号</th>
                                    <th class="center">家庭住址</th>
                                    <th class="center">下单时间</th>
                                    <th class="center">是否领取</th>
                                    <th class="center">
                                        <input id="filter" type="hidden">

                                        <div class="btn-group">
                                            <button id="orderStatus" data-toggle="dropdown"
                                                    class="btn btn-sm dropdown-75">
                                                订单状态
                                                <span class="ace-icon fa fa-caret-down"></span>
                                            </button>

                                            <ul class="dropdown-menu dropdown-75">
                                                <li>
                                                    <a onclick="allOrder()">全部订单</a>
                                                </li>
                                                <li>
                                                    <a onclick="paid()">已付款</a>
                                                </li>
                                                <li>
                                                    <a onclick="refund()">已退款</a>
                                                </li>

                                            </ul>
                                        </div>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                </div><!-- /.col -->
                <div>
                    <span>已售出数量统计：</span>
                    <span id="sumSell" style="color: red;font-size: large"></span>
                </div>
            </div><!-- /.row -->
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
<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>

<!-- ace scripts -->
<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/js/biz/sale/orderPayList.js"></script>
</body>
</html>



