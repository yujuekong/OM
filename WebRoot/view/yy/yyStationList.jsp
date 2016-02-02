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
						<li class="active">线路和站点关系管理</li>
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
												<th class="center">线路编号</th>
												<th class="center">车辆编号</th>
												<th class="center">服务站点</th>	
												<th class="center">有效开始日期</th>		
												<th class="center">有效结束日期</th>											
												<th class="center">状态</th>												
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
												<td class="center"><a href="${pageContext.request.contextPath}/view/car/carMap.jsp"  class="tooltip-info" data-rel="tooltip" title="查看">线路1</a> </td></td>
												<td class="center">鄂A00003</td>
												<td class="center">武汉服务站</td>
												<td class="center">2015-08-10</td>
												<td class="center">2015-08-10</td>												
												<td class="center"><span class="label label-sm label-success">正常</span></td>
												<td class="center">
													<div class="hidden-sm hidden-xs btn-group">
														<a href="${pageContext.request.contextPath}/view/yy/yyStationView.jsp"  class="tooltip-info" data-rel="tooltip" title="查看">
															<button class="btn btn-xs btn-success">
																<i class="ace-icon fa fa-search-plus bigger-120"></i>
															</button>
														</a>
														<a href="${pageContext.request.contextPath}/view/yy/yyStationEdit.jsp"  class="tooltip-info" data-rel="tooltip" title="编辑">
															<button class="btn btn-xs btn-info">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>
														</a>
														<a href="#"  class="tooltip-info" data-rel="tooltip" title="删除">
															<button class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</a>
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
												<td class="center"><a href="${pageContext.request.contextPath}/view/car/carMap.jsp"  class="tooltip-info" data-rel="tooltip" title="查看">线路2</a> </td></td>
												<td class="center">鄂A00001</td>
												<td class="center">武汉服务站</td>
												<td class="center">2015-08-10</td>
												<td class="center">2015-08-10</td>												
												<td class="center"><span class="label label-sm label-warning">失效</span></td>
												<td class="center">
													<div class="hidden-sm hidden-xs btn-group">
														<a href="${pageContext.request.contextPath}/view/yy/yyStationView.jsp"  class="tooltip-info" data-rel="tooltip" title="查看">
															<button class="btn btn-xs btn-success">
																<i class="ace-icon fa fa-search-plus bigger-120"></i>
															</button>
														</a>
														<a href="${pageContext.request.contextPath}/view/yy/yyStationEdit.jsp"  class="tooltip-info" data-rel="tooltip" title="编辑">
															<button class="btn btn-xs btn-info">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>
														</a>
														<a href="#"  class="tooltip-info" data-rel="tooltip" title="删除">
															<button class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</a>
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
												<td class="center"><a href="${pageContext.request.contextPath}/view/car/carMap.jsp"  class="tooltip-info" data-rel="tooltip" title="查看">线路3</a> </td></td>
												<td class="center">鄂A00002</td>
												<td class="center">武汉服务站</td>
												<td class="center">2015-08-10</td>
												<td class="center">2015-08-10</td>												
												<td class="center"><span class="label label-sm label-success">正常</span></td>
												<td class="center">
													<div class="hidden-sm hidden-xs btn-group">
														<a href="${pageContext.request.contextPath}/view/yy/yyStationView.jsp"  class="tooltip-info" data-rel="tooltip" title="查看">
															<button class="btn btn-xs btn-success">
																<i class="ace-icon fa fa-search-plus bigger-120"></i>
															</button>
														</a>
														<a href="${pageContext.request.contextPath}/view/yy/yyStationEdit.jsp"  class="tooltip-info" data-rel="tooltip" title="编辑">
															<button class="btn btn-xs btn-info">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>
														</a>
														<a href="#"  class="tooltip-info" data-rel="tooltip" title="删除">
															<button class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</a>
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
												<td class="center"><a href="${pageContext.request.contextPath}/view/car/carMap.jsp"  class="tooltip-info" data-rel="tooltip" title="查看">线路4</a> </td></td>
												<td class="center">鄂A00004</td>
												<td class="center">武汉服务站</td>
												<td class="center">2015-08-10</td>
												<td class="center">2015-08-10</td>												
												<td class="center"><span class="label label-sm label-success">正常</span></td>
												<td class="center">
													<div class="hidden-sm hidden-xs btn-group">
														<a href="${pageContext.request.contextPath}/view/yy/yyStationView.jsp"  class="tooltip-info" data-rel="tooltip" title="查看">
															<button class="btn btn-xs btn-success">
																<i class="ace-icon fa fa-search-plus bigger-120"></i>
															</button>
														</a>
														<a href="${pageContext.request.contextPath}/view/yy/yyStationEdit.jsp"  class="tooltip-info" data-rel="tooltip" title="编辑">
															<button class="btn btn-xs btn-info">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>
														</a>
														<a href="#"  class="tooltip-info" data-rel="tooltip" title="删除">
															<button class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</a>
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
												<td class="center"><a href="${pageContext.request.contextPath}/view/car/carMap.jsp"  class="tooltip-info" data-rel="tooltip" title="查看">线路5</a> </td></td>
												<td class="center">鄂A00005</td>
												<td class="center">武汉服务站</td>
												<td class="center">2015-08-10</td>
												<td class="center">2015-08-10</td>												
												<td class="center"><span class="label label-sm label-success">正常</span></td>
												<td class="center">
													<div class="hidden-sm hidden-xs btn-group">
														<a href="${pageContext.request.contextPath}/view/yy/yyStationView.jsp"  class="tooltip-info" data-rel="tooltip" title="查看">
															<button class="btn btn-xs btn-success">
																<i class="ace-icon fa fa-search-plus bigger-120"></i>
															</button>
														</a>
														<a href="${pageContext.request.contextPath}/view/yy/yyStationEdit.jsp"  class="tooltip-info" data-rel="tooltip" title="编辑">
															<button class="btn btn-xs btn-info">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>
														</a>
														<a href="#"  class="tooltip-info" data-rel="tooltip" title="删除">
															<button class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</a>
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
												<td class="center"><a href="${pageContext.request.contextPath}/view/car/carMap.jsp"  class="tooltip-info" data-rel="tooltip" title="查看">线路6</a> </td></td>
												<td class="center">粤B00002</td>
												<td class="center">深圳服务站</td>
												<td class="center">2015-08-10</td>
												<td class="center">2015-08-10</td>												
												<td class="center"><span class="label label-sm label-success">正常</span></td>
												<td class="center">
													<div class="hidden-sm hidden-xs btn-group">
														<a href="${pageContext.request.contextPath}/view/yy/yyStationView.jsp"  class="tooltip-info" data-rel="tooltip" title="查看">
															<button class="btn btn-xs btn-success">
																<i class="ace-icon fa fa-search-plus bigger-120"></i>
															</button>
														</a>
														<a href="${pageContext.request.contextPath}/view/yy/yyStationEdit.jsp"  class="tooltip-info" data-rel="tooltip" title="编辑">
															<button class="btn btn-xs btn-info">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>
														</a>
														<a href="#"  class="tooltip-info" data-rel="tooltip" title="删除">
															<button class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</a>
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
												<td class="center"><a href="${pageContext.request.contextPath}/view/car/carMap.jsp"  class="tooltip-info" data-rel="tooltip" title="查看">线路7</a> </td></td>
												<td class="center">粤B00003</td>
												<td class="center">深圳服务站</td>
												<td class="center">2015-08-10</td>
												<td class="center">2015-08-10</td>												
												<td class="center"><span class="label label-sm label-success">正常</span></td>
												<td class="center">
													<div class="hidden-sm hidden-xs btn-group">
														<a href="${pageContext.request.contextPath}/view/yy/yyStationView.jsp"  class="tooltip-info" data-rel="tooltip" title="查看">
															<button class="btn btn-xs btn-success">
																<i class="ace-icon fa fa-search-plus bigger-120"></i>
															</button>
														</a>
														<a href="${pageContext.request.contextPath}/view/yy/yyStationEdit.jsp"  class="tooltip-info" data-rel="tooltip" title="编辑">
															<button class="btn btn-xs btn-info">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>
														</a>
														<a href="#"  class="tooltip-info" data-rel="tooltip" title="删除">
															<button class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</a>
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
												<td class="center"><a href="${pageContext.request.contextPath}/view/car/carMap.jsp"  class="tooltip-info" data-rel="tooltip" title="查看">线路8</a> </td></td>
												<td class="center">粤B00001</td>
												<td class="center">深圳服务站</td>
												<td class="center">2015-08-10</td>
												<td class="center">2015-08-10</td>												
												<td class="center"><span class="label label-sm label-success">正常</span></td>
												<td class="center">
													<div class="hidden-sm hidden-xs btn-group">
														<a href="${pageContext.request.contextPath}/view/yy/yyStationView.jsp"  class="tooltip-info" data-rel="tooltip" title="查看">
															<button class="btn btn-xs btn-success">
																<i class="ace-icon fa fa-search-plus bigger-120"></i>
															</button>
														</a>
														<a href="${pageContext.request.contextPath}/view/yy/yyStationEdit.jsp"  class="tooltip-info" data-rel="tooltip" title="编辑">
															<button class="btn btn-xs btn-info">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>
														</a>
														<a href="#"  class="tooltip-info" data-rel="tooltip" title="删除">
															<button class="btn btn-xs btn-danger">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</a>
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
		<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
		<script src="${pageContext.request.contextPath}/js/ycs.util.js" ></script>
		<script src="${pageContext.request.contextPath}/js/sys/userList.js" ></script>
		<script type="text/javascript">
			$("#submenu-menu-yy-station").addClass("active");
			$("#menu-yy").addClass("active");
			$("#menu-yy").addClass("open");
		</script>
	</body>
</html>



