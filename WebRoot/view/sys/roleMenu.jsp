<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<jsp:include page="../commons/head.jsp" />	
	<body class="no-skin">
		
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div>
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>

					<ul class="breadcrumb">
						<li>系统</li>
						<li><a href="${pageContext.request.contextPath}/view/sys/sysRole/queryRole.action">角色权限管理</a></li>
						<li class="active">WEB菜单权限设置</li>
					</ul><!-- /.breadcrumb -->
					<!-- <div class="nav-search" id="nav-search">
						<a href="#">
							<button type="button" onclick="updateAuth()" class="btn btn-minier btn-success">添加权限</button>
						</a>
					</div> -->
					<!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<h4 class="blue">设置角色:${roleName }</h4>			
						</div>
						<div class="nav-search" id="nav-search">
						<a href="#">
							<button type="button" onclick="updateAuth()" class="btn btn-xs btn-success">添加权限</button>
						</a>
						</div>
						<div class="col-xs-12">
							<div class="row">
								<div class="col-xs-12" >
									<div style="height: 1100px; border: 1px solid #ddd;margin-left: auto;margin-right: auto;">
										<input type="hidden" name="roleId" id="roleId" value="${roleId }">
										<input type="hidden" name="menutype" id="menutype" value="${menutype }"/>
										<ul id="menu_tree" class="ztree"></ul>
									</div>
								</div>							
							</div>
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
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
		<script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.dataTables.bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js" ></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.excheck-3.5.min.js" ></script>
		
		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
		<script src="${pageContext.request.contextPath}/js/biz/sys/roleMenu.js" ></script>
		
	</body>
</html>



