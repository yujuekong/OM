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
						<li><a href="#">设备管理</a></li>
						<li class="active">设备巡检保养</li>
					</ul><!-- /.breadcrumb -->
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
						<span class="input-icon">
						 <input type="text" placeholder="Search ..." class="nav-search-input"
									id="team_keyword" autocomplete="off"/>
										     <i class="ace-icon fa fa-search nav-search-icon"></i>
									        </span>
							<!-- PAGE CONTENT BEGINS -->
							<div class="row">
								<div class="col-xs-12">
									<div>
										<table id="inspection_list" class="table table-striped table-bordered table-hover" style="text-align: center">
											<thead>
												<tr>
													<th class="center">设备编号</th>
													<th class="center">设备名称</th>
													<th class="center">保养人</th>
													<th class="center">上次保养日期</th>
													<th class="center">本次保养日期</th>
													<th class="center">备注</th>
													<th class="center">
													<select onchange="searchStatus()" id="inspectionStatus" style="border: none;">
															<option value=" ">状态</option>
															<option value="1">已完成</option>
															<option value="0">未完成</option>
														</select>
												</th>
												</tr>
											</thead>
	
											<tbody>
												
											</tbody>
										</table>
									</div>
									
								</div><!-- /.span -->
							</div><!-- /.row -->
							<div><br></div>
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
		<script src="${pageContext.request.contextPath}/js/biz/region/inspectionList.js"></script>	
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
		<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.min.js"></script>
		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
	</body>
</html>



