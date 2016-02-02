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
						<li class="active">配送计划管理</li>
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
							<div class="searchbar">
								<div >
									<label>快捷搜索： </label>
									<div class="btn-group">
										<p>
											<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right" onclick="quickSearch(0)">全部 </a>
											<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right" onclick="quickSearch(1)">本周</a>
											<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right" onclick="quickSearch(2)">上周</a>
											<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right" onclick="quickSearch(3)">本月</a>
											<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right" onclick="quickSearch(4)">上月</a>
										</p>
									</div>
								</div>
								<ul class="list-inline">
									
									<li>
										<label>开始日期： </label>
										<span class="input-icon input-icon-right">
											<input type="text" id="dp_quoteStartDate" name="quoteStartDate" class="input-sm" style="width:110px;" />
											<i class="ace-icon fa fa-calendar blue"></i>
										</span>
										<label>至</label>
										<span class="input-icon input-icon-right">
											<input type="text" id="dp_quoteEndDate" name="quoteEndDate" class="input-sm" style="width:110px;"/>
											<i class="ace-icon fa fa-calendar blue"></i>
										</span>
									</li>
									<li>
										<label>车牌号： </label>
										<input type="text" id="quotationNo" name="quotationNo" class="input-sm" style="width:100px;" />
										<a id="searchQuoteBillBtn" class="btn btn-sm btn-success no-border">
											<i class="ace-icon fa fa-search nav-search-icon"></i>
											搜索</a>
									</li>
								</ul>
							</div>
						</div>
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
												<th class="center">配送车辆</th>
												<th class="center">配送司机</th>
												<th class="center">配送线路</th>
												<th class="center">配送商品</th>
												<th class="center">配送日期</th>
												<th class="center">配送时间</th>												
												<th class="center">配送状态</th>												
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
												<td class="center">鄂A00003</td>
												<td class="center">张三</td>
												<td class="center">线路1</td>
												<td class="center">橙子</td>
												<td class="center">2015-08-10</td>
												<td class="center">2015-08-10 10:00</td>												
												<td class="center">待审核</td>
												<td class="center">
													<div class="hidden-sm hidden-xs btn-group">
														<button class="btn btn-xs btn-success">
															<i class="ace-icon fa fa-check bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-info">
															<i class="ace-icon fa fa-pencil bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-danger">
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-warning">
															<i class="ace-icon fa fa-flag bigger-120"></i>
														</button>
													</div>
													<div class="hidden-md hidden-lg">
														<div class="inline position-relative">
															<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
																<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
															</button>
															<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
																<li>
																	<a href="#" class="tooltip-info" data-rel="tooltip" title="View">
																		<span class="blue">
																			<i class="ace-icon fa fa-search-plus bigger-120"></i>
																		</span>
																	</a>
																</li>
																<li>
																	<a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
																		<span class="green">
																			<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																		</span>
																	</a>
																</li>
																<li>
																	<a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
																		<span class="red">
																			<i class="ace-icon fa fa-trash-o bigger-120"></i>
																		</span>
																	</a>
																</li>
															</ul>
														</div>
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
												<td class="center">鄂A00001</td>
												<td class="center">李四</td>
												<td class="center">线路2</td>
												<td class="center">橙子</td>
												<td class="center">2015-08-10</td>
												<td class="center">2015-08-10 10:00</td>												
												<td class="center">已审核</td>
												<td class="center">
													<div class="hidden-sm hidden-xs btn-group">
														<button class="btn btn-xs btn-success">
															<i class="ace-icon fa fa-check bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-info">
															<i class="ace-icon fa fa-pencil bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-danger">
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-warning">
															<i class="ace-icon fa fa-flag bigger-120"></i>
														</button>
													</div>
													<div class="hidden-md hidden-lg">
														<div class="inline position-relative">
															<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
																<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
															</button>
															<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
																<li>
																	<a href="#" class="tooltip-info" data-rel="tooltip" title="View">
																		<span class="blue">
																			<i class="ace-icon fa fa-search-plus bigger-120"></i>
																		</span>
																	</a>
																</li>
																<li>
																	<a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
																		<span class="green">
																			<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																		</span>
																	</a>
																</li>
																<li>
																	<a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
																		<span class="red">
																			<i class="ace-icon fa fa-trash-o bigger-120"></i>
																		</span>
																	</a>
																</li>
															</ul>
														</div>
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
												<td class="center">鄂A00001</td>
												<td class="center">李四</td>
												<td class="center">线路2</td>
												<td class="center">橙子</td>
												<td class="center">2015-08-10</td>
												<td class="center">2015-08-10 10:00</td>												
												<td class="center">在途</td>
												<td class="center">
													<div class="hidden-sm hidden-xs btn-group">
														<button class="btn btn-xs btn-success">
															<i class="ace-icon fa fa-check bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-info">
															<i class="ace-icon fa fa-pencil bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-danger">
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
														</button>
														<button class="btn btn-xs btn-warning">
															<i class="ace-icon fa fa-flag bigger-120"></i>
														</button>
													</div>
													<div class="hidden-md hidden-lg">
														<div class="inline position-relative">
															<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
																<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
															</button>
															<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
																<li>
																	<a href="#" class="tooltip-info" data-rel="tooltip" title="View">
																		<span class="blue">
																			<i class="ace-icon fa fa-search-plus bigger-120"></i>
																		</span>
																	</a>
																</li>
																<li>
																	<a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
																		<span class="green">
																			<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																		</span>
																	</a>
																</li>
																<li>
																	<a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
																		<span class="red">
																			<i class="ace-icon fa fa-trash-o bigger-120"></i>
																		</span>
																	</a>
																</li>
															</ul>
														</div>
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
			$("#submenu-menu-yy-resource").addClass("active");
			$("#menu-yy").addClass("active");
			$("#menu-yy").addClass("open");
		</script>
	</body>
</html>



