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
						<li class="active">机构管理</li>
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
						<div class="col-xs-2">
							<div style="height: 500px; overflow-y: scroll; border: 1px solid #ddd;">
								<ul id="devicetype_tree" class="ztree"></ul>
							</div>
						</div>
						<div class="col-xs-10">
							<!-- PAGE CONTENT BEGINS -->
							<div class="row">
								<div class="col-xs-12">
									<table id="sample-table-1" class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th class="center">
													<label class="position-relative">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</th>
												<th class="center">序号</th>
												<th class="center">机构名称</th>
												<th class="center">所属省份</th>
												<th class="center">所属区域</th>	
												<th class="center">机构负责人</th>
												<th class="center">机构电话</th>																							
												<th class="center">操作</th>
											</tr>
										</thead>

										<tbody>
											<tr>
												<td class="center">
													<label class="position-relative">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</td>
												<td class="center"><a href="#">1</a></td>
												<td class="center">武汉服务站</td>
												<td class="center">湖北省</td>
												<td class="center">华中区域站</td>												
												<td class="center">张进军</td>
												<td class="center">13971177654</td>
												<td class="center">
													<div class="hidden-sm hidden-xs btn-group">
														<button class="btn btn-xs btn-success">
															<i class="ace-icon fa fa-search-plus bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-info">
															<i class="ace-icon fa fa-pencil bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-danger">
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
														</button>
													</div>													
												</td>
											</tr>
											<tr>
												<td class="center">
													<label class="position-relative">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</td>
												<td class="center"><a href="#">2</a></td>
												<td class="center">襄樊服务站</td>
												<td class="center">湖北省</td>
												<td class="center">华中区域站</td>												
												<td class="center">李忠</td>
												<td class="center">13971177654</td>
												<td class="center">
													<div class="hidden-sm hidden-xs btn-group">
														<button class="btn btn-xs btn-success">
															<i class="ace-icon fa fa-search-plus bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-info">
															<i class="ace-icon fa fa-pencil bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-danger">
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
														</button>
													</div>													
												</td>
											</tr>
											<tr>
												<td class="center">
													<label class="position-relative">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</td>
												<td class="center"><a href="#">3</a></td>
												<td class="center"><a href="${pageContext.request.contextPath}/view/sys/userList.jsp">宜昌服务站</a> </td>
												<td class="center">湖北省</td>
												<td class="center">华中区域站</td>												
												<td class="center">余兵</td>
												<td class="center">13971177654</td>
												<td class="center">
													<div class="hidden-sm hidden-xs btn-group">
														<button class="btn btn-xs btn-success">
															<i class="ace-icon fa fa-search-plus bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-info">
															<i class="ace-icon fa fa-pencil bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-danger">
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
														</button>
													</div>													
												</td>
											</tr>
											<tr>
												<td class="center">
													<label class="position-relative">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</td>
												<td class="center"><a href="#">4</a></td>
												<td class="center"><a href="${pageContext.request.contextPath}/view/sys/userList.jsp">深圳服务站</a> </td>
												<td class="center">广东省</td>
												<td class="center">华南区域站</td>												
												<td class="center">李斌</td>
												<td class="center">13971177654</td>
												<td class="center">
													<div class="hidden-sm hidden-xs btn-group">
														<button class="btn btn-xs btn-success">
															<i class="ace-icon fa fa-search-plus bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-info">
															<i class="ace-icon fa fa-pencil bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-danger">
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
														</button>
													</div>													
												</td>
											</tr>
											<tr>
												<td class="center">
													<label class="position-relative">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</td>
												<td class="center"><a href="#">5</a></td>
												<td class="center"><a href="${pageContext.request.contextPath}/view/sys/userList.jsp">广州服务站</a> </td>
												<td class="center">广东省</td>
												<td class="center">华南区域站</td>												
												<td class="center">万山</td>
												<td class="center">13971177654</td>
												<td class="center">
													<div class="hidden-sm hidden-xs btn-group">
														<button class="btn btn-xs btn-success">
															<i class="ace-icon fa fa-search-plus bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-info">
															<i class="ace-icon fa fa-pencil bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-danger">
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
														</button>
													</div>													
												</td>
											</tr>
											<tr>
												<td class="center">
													<label class="position-relative">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</td>
												<td class="center"><a href="#">6</a></td>
												<td class="center"><a href="${pageContext.request.contextPath}/view/sys/userList.jsp">沈阳服务站</a> </td>
												<td class="center">辽林省</td>
												<td class="center">东北区域站</td>												
												<td class="center">邱庆</td>
												<td class="center">13971177654</td>
												<td class="center">
													<div class="hidden-sm hidden-xs btn-group">
														<button class="btn btn-xs btn-success">
															<i class="ace-icon fa fa-search-plus bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-info">
															<i class="ace-icon fa fa-pencil bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-danger">
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
														</button>
													</div>													
												</td>
											</tr>
											<tr>
												<td class="center">
													<label class="position-relative">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</td>
												<td class="center"><a href="#">7</a></td>
												<td class="center"><a href="${pageContext.request.contextPath}/view/sys/userList.jsp">大连服务站</a> </td>
												<td class="center">辽林省</td>
												<td class="center">东北区域站</td>												
												<td class="center">张晓林</td>
												<td class="center">13971177654</td>
												<td class="center">
													<div class="hidden-sm hidden-xs btn-group">
														<button class="btn btn-xs btn-success">
															<i class="ace-icon fa fa-search-plus bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-info">
															<i class="ace-icon fa fa-pencil bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-danger">
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
														</button>
													</div>													
												</td>
											</tr>
										</tbody>
									</table>
								</div><!-- /.span -->
							</div><!-- /.row -->

							<div class="col-xs-6">
								<tr>
									<td> 
										<a href="${pageContext.request.contextPath}/view/device/deviceInfoAdd.jsp" class="tooltip-info" data-rel="tooltip" title="维修">
											<button class="btn btn-xs btn-success">新增</button>
										</a>										
										<button class="btn btn-xs btn-danger">批量删除</button>
									</td>									
									<td style="vertical-align:top;"> 
										
									</td>
								</tr>
							</div>	
							<div class="col-xs-6"><div class="dataTables_paginate paging_bootstrap"><ul class="pagination"><li class="prev disabled"><a href="#"><i class="fa fa-angle-double-left"></i></a></li><li class="prev disabled"><a href="#"><i class="fa fa-angle-left"></i></a></li><li class="active"><a href="#">1</a></li><li><a href="#">2</a></li><li><a href="#">3</a></li><li class="next"><a href="#"><i class="fa fa-angle-right"></i></a></li><li class="next"><a href="#"><i class="fa fa-angle-double-right"></i></a></li></ul></div></div>
							<div class="hr hr-18 dotted hr-double"></div>

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
		<script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.dataTables.bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js" ></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js" ></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
		<script type="text/javascript">
			$("#submenu-submenu-menu-sys-org-org").addClass("active");
			$("#submenu-menu-sys-org").addClass("active");
			$("#submenu-menu-sys-org").addClass("open");
			$("#menu-sys").addClass("active");
			$("#menu-sys").addClass("open");
			var setting = {
				data: {
					simpleData: {
						enable: true
					}
				}
			};	
			var zNodes =[
				{ id:1, pId:0, name:"慧惠宝", open:true},
				{ id:11, pId:1, name:"东北"},
				{ id:12, pId:1, name:"华北"},
				{ id:13, pId:1, name:"华东"},
				{ id:14, pId:1, name:"华中"},
				{ id:15, pId:1, name:"华南"},
				{ id:16, pId:1, name:"西北"},
				{ id:17, pId:1, name:"西南"},
				
				{ id:111, pId:11, name:"辽宁省"},
				{ id:1111, pId:111, name:"沈阳服务站"},
				
				{ id:141, pId:14, name:"湖北"},
				{ id:1411, pId:141, name:"武汉服务站"},
				{ id:1412, pId:141, name:"宜昌服务站"},
				
				{ id:151, pId:15, name:"广东"},
				{ id:1511, pId:151, name:"深圳服务站"}
			];
	
			$(document).ready(function(){
				$.fn.zTree.init($("#devicetype_tree"), setting, zNodes);
			});
		</script>
	</body>
</html>



