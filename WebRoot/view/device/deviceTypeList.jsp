<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<jsp:include page="../commons/head.jsp" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome-4.3.0/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ace.min.css" />
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
	</head>
	<body class="no-skin">
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<div >
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>
					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="#">首页</a>
						</li>
						<li><a href="#">设备管理</a></li>
						<li class="active">设备类型管理</li>
					</ul><!-- /.breadcrumb -->
					<div class="nav-search" id="nav-search">
						<tr>
							<%--<td> --%>
								<%--<a class="tooltip-info" data-rel="tooltip" >--%>
									<%--<button class="btn btn-xs btn-success" onclick="add('')">新增</button>--%>
								<%--</a>										--%>
								<%--<!-- <button class="btn btn-xs btn-danger">批量删除</button> -->--%>
							<%--</td>									--%>
						</tr>
					</div><!-- /.nav-search -->					
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							
							<div class="row">
								<div class="searchbar">
									<ul class="list-inline">
										<li>
											&nbsp;&nbsp;<span class="input-icon">
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
								<div class="col-xs-12">
									<div>
										<table id="deviceType_list" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th id = "dp_type_id" class="center">设备类型编号</th>
													<th id = "dp_type_name" class="center">设备类型名称</th>
													<th id = "dp_type_option" class="center">操作</th>
												</tr>
											</thead>

											<tbody >
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			
			</div><!-- /.main-content -->

			
			
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${pageContext.request.contextPath}/assets/js/jquery.min.js'>"+"<"+"/script>");
		</script>

		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->
		<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>

		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/biz/device/deviceTypeList.js"></script>

	</body>
</html>
