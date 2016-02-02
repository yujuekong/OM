<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<jsp:include page="../commons/head.jsp" />
	
	<body class="no-skin">
		<div class="main-container" id="main-container">
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
						<li class="active">设备维护管理</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div id="timeline-1">
								<div class="row">
									<div class="col-xs-12 col-sm-10 col-sm-offset-1">
										<div class="timeline-container">
											<div class="timeline-label">
												<span class="label label-primary arrowed-in-right label-lg">
													<b>今天</b>
												</span>
											</div>

											<div class="timeline-items">
												<div class="timeline-item clearfix">
													<div class="timeline-info">
														<img alt="Susan't Avatar" src="assets/avatars/avatar1.png" />
														<span class="label label-info label-sm">16:22</span>
													</div>

													<div class="widget-box transparent">
														<div class="widget-header widget-header-small">
															<h5 class="widget-title smaller">
																<a href="#" class="blue">张三</a>
																<span class="grey">维修刀片</span>
															</h5>

															<span class="widget-toolbar no-border">
																<i class="ace-icon fa fa-clock-o bigger-110"></i>
																16:22
															</span>

															<span class="widget-toolbar">
																<a href="#" data-action="reload">
																	<i class="ace-icon fa fa-refresh"></i>
																</a>

																<a href="#" data-action="collapse">
																	<i class="ace-icon fa fa-chevron-up"></i>
																</a>
															</span>
														</div>

														<div class="widget-body">
															<div class="widget-main">
																刀片损坏
																<span class="red">high life</span>

																更换刀片 &hellip;
																<div class="space-6"></div>

																<div class="widget-toolbox clearfix">
																	<div class="pull-left">
																		<i class="ace-icon fa fa-hand-o-right grey bigger-125"></i>
																		<a href="#" class="bigger-110">点击查看详情 &hellip;</a>
																	</div>

																	<div class="pull-right action-buttons">
																		<a href="#">
																			<i class="ace-icon fa fa-check green bigger-130"></i>
																		</a>

																		<a href="#">
																			<i class="ace-icon fa fa-pencil blue bigger-125"></i>
																		</a>

																		<a href="#">
																			<i class="ace-icon fa fa-times red bigger-125"></i>
																		</a>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>


												<div class="timeline-item clearfix">
													<div class="timeline-info">
														<i class="timeline-indicator ace-icon fa fa-star btn btn-warning no-hover green"></i>
													</div>

													<div class="widget-box transparent">
														<div class="widget-header widget-header-small">
															<h5 class="widget-title smaller">李四</h5>

															<span class="widget-toolbar no-border">
																<i class="ace-icon fa fa-clock-o bigger-110"></i>
																9:15
															</span>

															<span class="widget-toolbar">
																<a href="#" data-action="reload">
																	<i class="ace-icon fa fa-refresh"></i>
																</a>

																<a href="#" data-action="collapse">
																	<i class="ace-icon fa fa-chevron-up"></i>
																</a>
															</span>
														</div>

														<div class="widget-body">
															<div class="widget-main">
																维修电源
																<div class="space-6"></div>

																<div class="widget-toolbox clearfix">
																	<div class="pull-right action-buttons">
																		<div class="space-4"></div>

																		<div>
																			<a href="#">
																				<i class="ace-icon fa fa-heart red bigger-125"></i>
																			</a>

																			<a href="#">
																				<i class="ace-icon fa fa-facebook blue bigger-125"></i>
																			</a>

																			<a href="#">
																				<i class="ace-icon fa fa-reply light-green bigger-130"></i>
																			</a>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>

												<div class="timeline-item clearfix">
													<div class="timeline-info">
														<i class="timeline-indicator ace-icon fa fa-flask btn btn-default no-hover"></i>
													</div>

													<div class="widget-box transparent">
														<div class="widget-body">
															<div class="widget-main"> 检查机器所有部件 </div>
														</div>
													</div>
												</div>
											</div><!-- /.timeline-items -->
										</div><!-- /.timeline-container -->

										<div class="timeline-container">
											<div class="timeline-label">
												<span class="label label-success arrowed-in-right label-lg">
													<b>昨天</b>
												</span>
											</div>

											<div class="timeline-items">
												<div class="timeline-item clearfix">
													<div class="timeline-info">
														<i class="timeline-indicator ace-icon fa fa-beer btn btn-inverse no-hover"></i>
													</div>

													<div class="widget-box transparent">
														<div class="widget-header widget-header-small">
															<h5 class="widget-title smaller">维修传动装置</h5>

															<span class="widget-toolbar">
																<i class="ace-icon fa fa-clock-o bigger-110"></i>
																13：20
															</span>
														</div>

														<div class="widget-body">
															<div class="widget-main">
																<div class="clearfix">
																	<div class="pull-left">
																		检修
																		<br />
																		检修照片:
																	</div>

																	<div class="pull-right">
																		<i class="ace-icon fa fa-chevron-left blue bigger-110"></i>

																		&nbsp;
																		<img alt="Image 4" width="36" src="assets/images/gallery/thumb-4.jpg" />
																		<img alt="Image 3" width="36" src="assets/images/gallery/thumb-3.jpg" />
																		<img alt="Image 2" width="36" src="assets/images/gallery/thumb-2.jpg" />
																		<img alt="Image 1" width="36" src="assets/images/gallery/thumb-1.jpg" />
																		&nbsp;
																		<i class="ace-icon fa fa-chevron-right blue bigger-110"></i>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>

												<div class="timeline-item clearfix">
													<div class="timeline-info">
														<i class="timeline-indicator ace-icon fa fa-cutlery btn btn-success no-hover"></i>
													</div>

													<div class="widget-box transparent">
														<div class="widget-body">
															<div class="widget-main"> 检测 </div>
														</div>
													</div>
												</div>

												<div class="timeline-item clearfix">
													<div class="timeline-info">
														<i class="timeline-indicator ace-icon fa fa-bug btn btn-danger no-hover"></i>
													</div>

													<div class="widget-box widget-color-red2">
														<div class="widget-header widget-header-small">
															<h5 class="widget-title smaller">检查</h5>

															<span class="widget-toolbar no-border">
																<i class="ace-icon fa fa-clock-o bigger-110"></i>
																9:15
															</span>

															<span class="widget-toolbar">
																<a href="#" data-action="reload">
																	<i class="ace-icon fa fa-refresh"></i>
																</a>

																<a href="#" data-action="collapse">
																	<i class="ace-icon fa fa-chevron-up"></i>
																</a>
															</span>
														</div>

														<div class="widget-body">
															<div class="widget-main">
																测试
															</div>
														</div>
													</div>
												</div>
											</div><!-- /.timeline-items -->
										</div><!-- /.timeline-container -->

										<div class="timeline-container">
											<div class="timeline-label">
												<span class="label label-grey arrowed-in-right label-lg">
													<b>2015-08-10</b>
												</span>
											</div>

											<div class="timeline-items">
												<div class="timeline-item clearfix">
													<div class="timeline-info">
														<i class="timeline-indicator ace-icon fa fa-leaf btn btn-primary no-hover green"></i>
													</div>

													<div class="widget-box transparent">
														<div class="widget-header widget-header-small">
															<h5 class="widget-title smaller">测试</h5>

															<span class="widget-toolbar no-border">
																<i class="ace-icon fa fa-clock-o bigger-110"></i>
																10:22
															</span>

															<span class="widget-toolbar">
																<a href="#" data-action="reload">
																	<i class="ace-icon fa fa-refresh"></i>
																</a>

																<a href="#" data-action="collapse">
																	<i class="ace-icon fa fa-chevron-up"></i>
																</a>
															</span>
														</div>

														<div class="widget-body">
															<div class="widget-main">
																测试故障
																<span class="blue bolder">ok</span>
																！！ &hellip;
															</div>
														</div>
													</div>
												</div>
											</div><!-- /.timeline-items -->
										</div><!-- /.timeline-container -->
									</div>
								</div>
							</div>

							<!-- PAGE CONTENT ENDS -->
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
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
	</body>
</html>



