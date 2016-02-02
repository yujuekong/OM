<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../commons/head.jsp"/>
<%--设置浏览器后退时页面不缓存数据--%>
<%
    response.addHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.addHeader("Cache-Control", "pre-check=0, post-check=0");
    response.setDateHeader("Expires", 0);
%>
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
                <li><a href="#">统计分析</a></li>
                <li class="active">商品销售</li>
            </ul>
            <!-- /.breadcrumb -->
            <div class="nav-search" id="nav-search">
                <%--<a onclick="preAddOrModifySellerInfo('')" class="tooltip-info" data-rel="tooltip"--%>
                <%--title="维修">--%>
                <%--<button class="btn btn-xs btn-success">新增</button>--%>
                <%--</a>--%>
            </div>
            <!-- /.nav-search -->
        </div>
        <div class="page-content">
            <div class="row">
                <%--<div class="col-xs-2">--%>
                <%--<div style="height: 500px; overflow-y: scroll; border: 1px solid #ddd;">--%>
                <%--<ul id="org_tree" class="ztree"></ul>--%>
                <%--</div>--%>
                <%--</div>--%>
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="searchbar">
                            <%--<ul class="list-inline">--%>
                            <%--<li>--%>
                            <%--</li>--%>
                            <%--<button id="reportChart">查看报表</button>--%>
                            <%--&lt;%&ndash;<span style="float: right" class="tooltip-info" data-rel="tooltip" title="新增">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<button onclick="preAddOrModifySellerInfo('')" class="btn btn-xs btn-success">新增&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</button>&nbsp;&nbsp;&nbsp;&nbsp;&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</span>&ndash;%&gt;--%>
                            <%--</ul>--%>
                            <label class="col-sm-2 control-label no-padding-right">
                                <button id="reportChart" class="btn-info bigger-120">查看报表</button>
                            </label>
                            <%--<label class="col-sm-2 control-label no-padding-right">月份--%>
                            <%--<input id="reportChart_month"/>--%>
                            <%--</label>--%>
                            <%--<label class="col-sm-2 control-label no-padding-right">选择公司--%>
                            <%--<select id="select_id">--%>
                            <%--<option value="0">全部公司</option>--%>
                            <%--<option value="1" selected>1</option>--%>
                            <%--</select>--%>
                            <%--</label>--%>
                            <%--<button id="reportChart" class="btn-info bigger-120">查看报表</button>--%>
                        </div>
                        <div class="col-xs-12">
                            <div>
                                <input type="hidden" id="sellerId">
                                <input id="orgId" type="hidden" value="${account.orgId}"/>

                                <input id="year_report" type="hidden"/>
                                <input id="month_report" type="hidden"/>
                                <input id="orgId_report" type="hidden"/>
                                <table id="goodsReport_list" class="table table-striped table-bordered table-hover"
                                       style="text-align: center">
                                    <thead>
                                    <tr>
                                        <th class="center">
                                            <div class="btn-group">
                                                <button id="year" data-toggle="dropdown"
                                                        class="btn btn-sm dropdown-75">
                                                    <span id="yearStr" class="ace-icon fa fa-caret-down">全部年份</span>
                                                </button>
                                                <ul id="year_ul" class="dropdown-menu dropdown-50">
                                                    <li onclick="selectYears(this)">
                                                        <input type="hidden" value="">
                                                        <a>全部年份</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </th>
                                        <th class="center">
                                            <div class="btn-group">
                                                <button id="month" data-toggle="dropdown"
                                                        class="btn btn-sm dropdown-75">
                                                    <span id="monthStr" class="ace-icon fa fa-caret-down">全部月份</span>
                                                </button>

                                                <ul class="dropdown-menu dropdown-50">
                                                    <li onclick="selectMonth(this)">
                                                        <input type="hidden" value="">
                                                        <a>全部月份</a>
                                                    </li>
                                                    <li onclick="selectMonth(this)">
                                                        <input type="hidden" value="01">
                                                        <a>一月</a>
                                                    </li>
                                                    <li onclick="selectMonth(this)">
                                                        <input type="hidden" value="02">
                                                        <a>二月</a>
                                                    </li>
                                                    <li onclick="selectMonth(this)">
                                                        <input type="hidden" value="03">
                                                        <a>三月</a>
                                                    </li>
                                                    <li onclick="selectMonth(this)">
                                                        <input type="hidden" value="04">
                                                        <a>四月</a>
                                                    </li>
                                                    <li onclick="selectMonth(this)">
                                                        <input type="hidden" value="05">
                                                        <a>五月</a>
                                                    </li>
                                                    <li onclick="selectMonth(this)">
                                                        <input type="hidden" value="06">
                                                        <a>六月</a>
                                                    </li>
                                                    <li onclick="selectMonth(this)">
                                                        <input type="hidden" value="07">
                                                        <a>七月</a>
                                                    </li>
                                                    <li onclick="selectMonth(this)">
                                                        <input type="hidden" value="08">
                                                        <a>八月</a>
                                                    </li>
                                                    <li onclick="selectMonth(this)">
                                                        <input type="hidden" value="09">
                                                        <a>九月</a>
                                                    </li>
                                                    <li onclick="selectMonth(this)">
                                                        <input type="hidden" value="10">
                                                        <a>十月</a>
                                                    </li>
                                                    <li onclick="selectMonth(this)">
                                                        <input type="hidden" value="11">
                                                        <a>十一月</a>
                                                    </li>
                                                    <li onclick="selectMonth(this)">
                                                        <input type="hidden" value="12">
                                                        <a>十二月</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </th>
                                        <th class="center">
                                            <div class="btn-group">
                                                <button id="orgName" data-toggle="dropdown"
                                                        class="btn btn-sm dropdown-100">
                                                    <span id="orgIdStr" class="ace-icon fa fa-caret-down">全部公司</span>
                                                </button>

                                                <ul id="org" class="dropdown-menu dropdown-75">
                                                    <li onclick="selectOrg(this)">
                                                        <input value="" hidden/>
                                                        <a>全部公司</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </th>
                                        <th class="center">销售额</th>
                                        <th class="center">明细</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>

                        </div>
                        <!-- /.span -->
                    </div>
                    <!-- /.row -->
                    <div><br></div>
                </div>
                <!-- /.col -->
                <!-- 选择弹出窗开始 -->
                <div id="house_choise_modal" class="modal hiden fade"
                     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog" style="width:820px;">
                        <div class="modal-content">
                            <div class="modal-header thicktitle">
                                <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                                <span class="modal-title" id="myModalLabel">供应商品</span>
                            </div>
                            <div class="modal-body thickcon"
                                 style="width:804px;height: 600px; padding-left: 10px; padding-right: 10px;">
                                <input type="hidden" id="purchase_house_rowno"/>
                                <input type="hidden" id="choise_type" value="0"/>

                                <div class="row">
                                    <div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
                                        <div class="input-group" style="float:right;width:300px;">
                                            <input type="text" id="keyword" class="form-control search-query"
                                                   placeholder="设备名称/设备编号"/>
									<span class="input-group-btn">
										<button id="searchGoodsBtn" type="button"
                                                class="btn btn-purple btn-sm no-border">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            搜索
                                        </button>
									</span>
                                        </div>

                                        <div class="col-xs-9" style="width: 800px">
                                            <table id="search_dtl_table"
                                                   class="table table-striped table-bordered table-hover"
                                                   style="text-align: center">
                                                <thead>
                                                <tr>
                                                    <th class="center">商品名称</th>
                                                    <th class="center">供应商名称</th>
                                                    <th class="center">商品产地</th>
                                                    <th class="center">商品单价</th>
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
                </div>
                <!-- /.page-content -->
            </div>
        </div>
        <!-- /.main-content -->
    </div>
</div>
</div>
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
<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>

<!-- ace scripts -->
<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>


<script src="${pageContext.request.contextPath}/js/biz/report/goodsReportList.js"></script>
</body>
</html>
