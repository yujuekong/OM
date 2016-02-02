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

						<li>
							<a href="#">系统</a>
						</li>
						<li class="active">菜单管理</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon">
								<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
								<i class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
						</form>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="table-header">
								菜单管理
							</div>
							<!-- PAGE CONTENT BEGINS -->
							<div class="row">
								<div class="col-xs-12">
									<table id="sample-table-2" class="table table-striped table-bordered table-hover" style="text-align: center">
										<thead>
											<tr>
												<th class="center">序号</th>
												<th class="center">菜单名称</th>
												<th class="center">上级菜单名称</th>												
												<th class="center">URL地址</th>
												<th class="center">排序</th>
												<th class="center">菜单图标</th>
												<th class="center">菜单级别</th>												
												<th class="center">操作</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach var="sysMenu" items="${sysMenuList }" varStatus="rowIndex" >
												<tr>
													<td class="center">${rowIndex.count }</td>	
													<td>${sysMenu.menuName }</td>
													<td>${sysMenu.sysPmenu.menuName }</td>
													<td>${sysMenu.menuUrl }</td>
													<td>${sysMenu.menuSort }</td>
													<td>${sysMenu.menuCss }</td>
													<td>${sysMenu.menuLevel }</td>
													<td>
														<div class="hidden-sm hidden-xs btn-group">
															<button class="btn btn-xs btn-info" onclick="modifyMenu('${sysMenu.menuId }')">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>
															<button class="btn btn-xs btn-danger" onclick="deleteMenu('${sysMenu.menuId }')">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</div>															
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div><!-- /.span -->
							</div><!-- /.row -->
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
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		
		<script src="${pageContext.request.contextPath}/js/biz/sys/menuList.js"></script>
	</body>
</html>



