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
						<li><a href="#">销售管理</a></li>
						<li class="active">销售商品管理</li>
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
										<label>商品： </label>
										<input type="text" id="quotationNo" name="quotationNo" class="input-sm" style="width:100px;" />
										<a id="searchQuoteBillBtn" class="btn btn-sm btn-success no-border">
											<i class="ace-icon fa fa-search nav-search-icon"></i>
											搜索</a>
									</li>
								</ul>
							</div>
						</div>
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
												<th class="center">销售设备</th>
												<th class="center">设备所在位置</th>
												<th class="center">销售商品</th>												
												<th class="center">已售数量</th>
												<th class="center">剩余数量</th>
												<th class="center">报警数量</th>
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
												<td class="center">1111110000001</td>
												<td class="center">武汉光谷广场</td>
												<td class="center">橙子</td>
												<td class="center">80斤</td>
												<td class="center">20斤</td>
												<td class="center">15斤</td>											
											</tr>
											<tr class="alert alert-danger">
												<td class="center">
													<label class="position-relative">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</td>
												<td class="center">1111110000002</td>
												<td class="center">武汉光谷广场</td>
												<td class="center">橙子</td>
												<td class="center">90斤</td>
												<td class="center"><span class="label label-sm label-warning">10斤</span></td>
												<td class="center">15斤</td>											
											</tr>
											<tr>
												<td class="center">
													<label class="position-relative">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</td>
												<td class="center">1111110000003</td>
												<td class="center">武汉光谷天地</td>
												<td class="center">橙子</td>
												<td class="center">80斤</td>
												<td class="center">20斤</td>
												<td class="center">15斤</td>											
											</tr>
											<tr>
												<td class="center">
													<label class="position-relative">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</td>
												<td class="center">1111110000004</td>
												<td class="center">武汉光谷天地</td>
												<td class="center">橙子</td>
												<td class="center">80斤</td>
												<td class="center">20斤</td>
												<td class="center">15斤</td>											
											</tr>
											<tr>
												<td class="center">
													<label class="position-relative">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</td>
												<td class="center">1111110000005</td>
												<td class="center">武汉光谷天地</td>
												<td class="center">橙子</td>
												<td class="center">80斤</td>
												<td class="center">20斤</td>
												<td class="center">15斤</td>											
											</tr>
											<tr>
												<td class="center">
													<label class="position-relative">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</td>
												<td class="center">1111110000006</td>
												<td class="center">武汉光谷天地</td>
												<td class="center">橙子</td>
												<td class="center">80斤</td>
												<td class="center">20斤</td>
												<td class="center">15斤</td>											
											</tr>
											<tr>
												<td class="center">
													<label class="position-relative">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</td>
												<td class="center">1111110000007</td>
												<td class="center">武汉光谷天地</td>
												<td class="center">橙子</td>
												<td class="center">80斤</td>
												<td class="center">20斤</td>
												<td class="center">15斤</td>											
											</tr>
											<tr>
												<td class="center">
													<label class="position-relative">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</td>
												<td class="center">1111110000008</td>
												<td class="center">群光广场</td>
												<td class="center">橙子</td>
												<td class="center">80斤</td>
												<td class="center">20斤</td>
												<td class="center">15斤</td>											
											</tr>
										</tbody>
									</table>
								</div><!-- /.span -->
							</div><!-- /.row -->

							<div class="col-xs-6">
								
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
			$("#submenu-menu-sale-goods").addClass("active");
			$("#menu-sale").addClass("active");
			$("#menu-sale").addClass("open");
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



