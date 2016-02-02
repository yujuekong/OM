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
                <li class="active">商品报损</li>
            </ul>
            <!-- /.breadcrumb -->

            <div class="nav-search" id="nav-search">
                <%--<a class="tooltip-info" data-rel="tooltip" title="维修">--%>
                <%--<button onclick="preAddOrModifyStorageDebt('')" class="btn btn-xs btn-success">新增--%>
                <%--</button>--%>
                <%--</a>--%>
            </div>
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
                                               id="debt_keyword" autocomplete="off" style="height: 30px"/>
								     <i class="ace-icon fa fa-search nav-search-icon"></i>
							        </span>
                            </li>
                            <a style="float: right" class="tooltip-info" data-rel="tooltip" title="新增">
                                <button onclick="preAddOrModifyStorageDebt('')" class="btn btn-xs btn-success">新增
                                </button>
                            </a>
                        </ul>
                    </div>
                </div>
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <input id="debtId" type="hidden">
                            <input id="orgId" type="hidden" value="${account.orgId}">

                            <%--<div class="nav-search" id="nav-search_seller">--%>
                            <%--<span class="input-icon">--%>
                            <%--<input type="text" placeholder="Search ..." class="nav-search-input"--%>
                            <%--id="debt_keyword" autocomplete="off"/>--%>
                            <%--<i class="ace-icon fa fa-search nav-search-icon"></i>--%>
                            <%--</span>--%>
                            <%--</div>--%>
                            <table id="debt_list" class="table table-striped table-bordered table-hover"
                                   STYLE="text-align: center">
                                <thead>
                                <tr>
                                    <th class="center">报损单号</th>
                                    <th class="center">报损日期</th>
                                    <th class="center">报损人</th>
                                    <th class="center">报损状态</th>
                                    <th class="center">报损明细</th>
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
                    <div class="hr hr-18 dotted hr-double"></div>
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </div>
        <!-- 报损明细弹出框 -->
        <div id="debtDtl_show_modal" class="modal hiden fade"
             tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width:820px;">
                <div class="modal-content">
                    <div class="modal-header thicktitle">
                        <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                        <span class="modal-title" id="myModalLabel">报损明细</span>
                    </div>
                    <div class="modal-body thickcon"
                         style="width:804px;height: 600px; padding-left: 10px; padding-right: 10px;">
                        <input type="hidden" id="purchase_house_rowno"/>
                        <input type="hidden" id="choise_type" value="0"/>

                        <div class="row">
                            <div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
                                <!-- <div class="input-group" style="float:right;width:300px;">
                                    <input type="text" id="keyword" class="form-control search-query"
                                           placeholder=""/>
									<span class="input-group-btn">
										<button id="searchGoodsBtn" type="button"
                                                class="btn btn-purple btn-sm no-border">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            搜索
                                        </button>
									</span>
                                </div> -->

                                <div class="col-xs-9" style="width: 800px">
                                    <table id="search_dtl_table"
                                           class="table table-striped table-bordered table-hover"
                                           style="text-align: center">
                                        <thead>
                                        <tr>
                                            <th class="center">报损商品</th>
                                            <th class="center">报损数量</th>
                                            <th class="center">报损说明</th>
                                            <th class="center">报损类型</th>
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
            <!-- /.page-content -->
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

<script src="${pageContext.request.contextPath}/js/biz/inventory/storageDebtList.js"></script>

<script type="text/javascript">

</script>
</body>
</html>