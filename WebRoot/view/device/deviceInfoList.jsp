<%@ page language="java" pageEncoding="utf-8"%>
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
						<li><a href="#">设备信息管理</a></li>
						<li class="active">设备信息列表</li>
					</ul><!-- /.breadcrumb -->
					<%--<div class="nav-search" id="nav-search">--%>
						<%--<tr>--%>
							<%--<td> --%>
								<%--<a onclick="add('')" class="tooltip-info" data-rel="tooltip" title="新增">--%>
									<%--<button class="btn btn-xs btn-success">新增</button>--%>
								<%--</a>										--%>
								<%--<button onclick="allDeviceMap()" class="btn btn-xs btn-danger">所有设备分布图</button>--%>
							<%--</td>									--%>
						<%--</tr>--%>
					<%--</div><!-- /.nav-search -->--%>
				</div>
				<input type="hidden" id="isAdmin" value="${account.userId }"/>
				<div class="page-content">
					<div class="row">
						<div class="col-xs-2">
							<div style="height: 500px; overflow-y: scroll; border: 1px solid #ddd;">
								<ul id="org_tree" class="ztree"></ul>
							</div>
						</div>
						<div class="col-xs-10">
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
										<tr>
											<td>
												<button onclick="allDeviceMap()" class="btn btn-xs btn-danger">所有设备分布图</button>
											</td>
										</tr>
											
										<span onclick="add('')" style="float: right" class="tooltip-info" data-rel="tooltip" title="新增">
											<button class="btn btn-xs btn-success">新增</button>&nbsp;&nbsp;&nbsp;&nbsp;
										</span>
									</ul>
								</div>
								<div class="col-xs-12">
									<div>
										<table id="deviceInfo_list" class="table table-striped table-bordered table-hover">
				                            <%--<div class="nav-search" id="nav-search_seller">--%>
											         <%--<span class="input-icon">--%>
												        <%--<input type="text" placeholder="Search ..." class="nav-search-input"--%>
				                                               <%--id="keyword" autocomplete="off"/>--%>
												     <%--<i class="ace-icon fa fa-search nav-search-icon"></i>--%>
											        <%--</span>--%>
				                            <%--</div>--%>
											<thead>
												<tr>
													<th class="center">设备编号</th>
													<th class="center">设备名称</th>
													<th class="center">设备类型</th>																						
													<th class="center">投放地址</th>
													<th class="center">投放日期</th>
													<th class="center">
														<select id="status_sele" onchange="searchDevice()" style="border: none;">
															<option value="">状态</option>
															<option value="1">启用</option>
															<option value="0 ">禁用</option>
														</select>
													</th>	
													<th class="center">管理</th>
													<th class="center">操作</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
									</div>									
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
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
		<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js" ></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js" ></script>		
		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/biz/device/deviceInfoList.js"></script>
	</body>
</html>



