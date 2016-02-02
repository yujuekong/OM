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
                <li><a href="#">设备管理</a></li>
                <li><a href="#">设备信息管理</a></li>
                <li class="active">设备清洗列表</li>
            </ul>
            <!-- /.breadcrumb -->
            <%--<div class="nav-search" id="nav-search">--%>
                <%--<tr>--%>
                    <%--<td>--%>
                        <%--<a class="tooltip-info" data-rel="tooltip">--%>
                            <%--<button class="btn btn-xs btn-success" onclick="add()">新增</button>--%>
                        <%--</a>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%--</div>--%>
            <!-- /.nav-search -->
        </div>

        <input type="hidden" id="deviceId" value="${deviceId }">
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
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
		                                                   class="input-sm" style="width:110px;" onchange="dateFind();"/>
													<i class="ace-icon fa fa-calendar blue"></i>
												</span>
		                                <label>至</label>
												<span class="input-icon input-icon-right">
													<input type="text" id="dp_quoteEndDate" name="quoteEndDate" class="input-sm"
		                                                   style="width:110px;" onchange="dateFind();"/>
													<i class="ace-icon fa fa-calendar blue"></i>
												</span>
		                                    <span class="input-icon">
										        <input type="text" placeholder="Search ..." class="nav-search-input"
		                                               id="keyword" autocomplete="off" style="height: 30px"/>
										     <i class="ace-icon fa fa-search nav-search-icon"></i>
									        </span>
		                            </li>
		                            <span style="float: right" class="tooltip-info" data-rel="tooltip" title="新增">
                                   		<button onclick="add('')" class="btn btn-xs btn-success">新增
                                    </button>&nbsp;&nbsp;&nbsp;&nbsp;
                                </span>
		                        </ul>
		                    </div>
						</div>
                        <div class="col-xs-12">
                            <div>
                                <table id="deviceClean_list" class="table table-striped table-bordered table-hover">
                                    <%--<div class="nav-search" id="nav-search_seller">--%>
											         <%--<span class="input-icon">--%>
												        <%--<input type="text" placeholder="Search ..."--%>
                                                               <%--class="nav-search-input"--%>
                                                               <%--id="info_keyword" autocomplete="off"/>--%>
												     <%--<i class="ace-icon fa fa-search nav-search-icon"></i>--%>
											        <%--</span>--%>
                                    <%--</div>--%>
                                    <thead>
                                    <tr>
                                 	    <th class="center">清洗编号</th>
                                        <th class="center">设备编号</th>
                                        <th class="center">设备名称</th>
                                        <th class="center">设备类型</th>
                                        <th class="center">要求完成时间</th>
                                        <th class="center">实际完成时间</th>
                                        <th class="center">订单创建时间</th>
                                        <th class="center">清洗设备人</th>
                                        <th class="center">清洗状态</th>
                                        <th class="center">操作</th>
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
<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>
<!-- ace scripts -->
<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
<script src="${pageContext.request.contextPath}/js/biz/device/deviceCleanList.js"></script>
</body>
</html>



