<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<jsp:include page="../commons/head.jsp" />
	
	<body class="no-skin">
		<div class="main-container" id="main-container">
			<div>
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>

					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="#">首页</a>
						</li>
						<li><a href="#">广告管理</a></li>
						<li class="active">广告设备管理</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
					<a href="${pageContext.request.contextPath}/view/advert/adPositionList.jsp"> <i
						class="ace-icon fa fa-arrow-left"></i>返回
					</a>
				</div>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
					<!-- 搜索框 -->
					<div class="col-xs-12">
						<div class="searchbar">
							<ul class="list-inline">
								<li><input type="hidden" name="advertDeviceName"
									value="${advertDeviceName }" id="advertDeviceName"
									/></li>
							</ul>
						</div>
					</div>
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="row">
								<div class="col-xs-12">
									<table id="advertInfo_list" class="table table-striped table-bordered table-hover" style="text-align: center">
										<thead>
											<tr>
												<th class="center">广告名称</th>
												<th class="center">设备编号</th>
												<th class="center">设备名称</th>
												<th class="center">设备位置</th>
												<th class="center">广告费用</th>
												<th class="center">投放状态</th>											
												<th class="center">设备状态</th>
												<th class="center">操作</th>
											</tr>
										</thead>

										<tbody>
										</tbody>
									</table>
								</div><!-- /.span -->
							</div><!-- /.row -->
							<div><br></div>
							<div class="col-xs-6">
							<tr>
								<td><a href="${pageContext.request.contextPath}/view/advert/adPositionList.jsp"
									class="tooltip-info" data-rel="tooltip" title="返回"> <i
										class="ace-icon fa fa-arrow-left"></i>返回 </a></td>
								<td style="vertical-align:top;"></td>
							</tr>
						</div>
							
						</div><!-- /.col -->
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
			window.jQuery || document.write("<script src='${pageContext.request.contextPath}/js/jquery.min.js'>"+"<"+"/script>");
		</script>
<!-- page specific plugin scripts -->
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
		<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/ztree/js/jquery.ztree.core-3.5.min.js" ></script>
		
		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/biz/advert/advertPositionList.js"></script>
		<script type="text/javascript">
			$("#submenu-menu-advert-info").addClass("active");
			$("#menu-advert").addClass("active");
			$("#menu-advert").addClass("open");
		</script>
	</body>
</html>



