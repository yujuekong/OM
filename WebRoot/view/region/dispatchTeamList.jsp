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
						<li><a href="#">配送管理</a></li>
						<li class="active">配送小组管理</li>
					</ul><!-- /.breadcrumb -->

					<%-- <div class="nav-search" id="nav-search">
						<form class="form-search">
							<!-- <span class="input-icon">
								<input type="text" id="searchAdUser"  placeholder="Search ..." class="nav-search-input" autocomplete="off" />
								<i class="ace-icon fa fa-search nav-search-icon" onclick="searchAdvertUser(this);"></i>
							</span> -->
							<span class="input-icon">
								<a href="${pageContext.request.contextPath}/view/region/dispatchTeam/saveOrUpdateDispatchTeam.action" class="tooltip-info" data-rel="tooltip" title="新增">
											<span class="btn btn-xs btn-success">新增</span>
								</a>
							</span>
						</form>
					</div><!-- /.nav-search --> --%>
				</div>
				
				

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
						<span class="input-icon">
						 <input type="text" placeholder="Search ..." class="nav-search-input"
									id="team_keyword" autocomplete="off"/>
										     <i class="ace-icon fa fa-search nav-search-icon"></i>
									        </span>
						        <span class="input-icon" style="float:right">
												<a href="${pageContext.request.contextPath}/view/region/dispatchTeam/updateDispatchTeam.action" class="tooltip-info" data-rel="tooltip" title="新增">
											<span class="btn btn-xs btn-success">新增</span>
								</a>
							</span>
							<!-- PAGE CONTENT BEGINS -->
							<div class="row">
								<div class="col-xs-12">
									<div>
										<table id="dispatchTeam_list" class="table table-striped table-bordered table-hover" style="text-align: center">
											<thead>
												<tr>
													<th class="center">小组编号</th>
													<th class="center">小组名称</th>
													<th class="center">负责人</th>
													<th class="center">配送成员</th>
													<th class="center">配送车辆</th>	
													<th class="center">配送线路</th>
													<th class="center">操作</th>
												</tr>
											</thead>
	
											<tbody>
												
											</tbody>
										</table>
									</div>
									
								</div><!-- /.span -->
							</div><!-- /.row -->
							<div><br></div>
							<%-- <div class="col-xs-6">
								<tr>
									<td> 
										<a href="${pageContext.request.contextPath}/view/advert/advertUser/saveAdvertUserAdd.action" class="tooltip-info" data-rel="tooltip" title="维修">
											<button class="btn btn-xs btn-success">新增</button>
										</a>										
										<!-- <button class="btn btn-xs btn-danger" hidden="hidden">批量删除</button> -->
									</td>									
									<td style="vertical-align:top;"> 
										
									</td>
								</tr>
							</div>	 --%>
							
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
		<script src="${pageContext.request.contextPath}/js/biz/region/dispatchTeamList.js"></script>		
	</body>
</html>



