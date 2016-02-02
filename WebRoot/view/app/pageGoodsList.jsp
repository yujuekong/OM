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
						<li><a href="#">橙的味道</a></li>
						<li class="active">首页内容管理</li>
					</ul><!-- /.breadcrumb -->

				</div>
					<input type="hidden" id="orgId" value="${account.orgId }" />
					<input type="hidden" id="proviceId" value="${account.dictProviceId }" />
					<input type="hidden" id="regionId" value="${account.dictRegionId }" />
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="row">
							<div class="searchbar">
							<ul class="list-inline">
								<li>
									&nbsp;&nbsp;<span class="input-icon">
								        <input type="text" placeholder="Search ..." class="nav-search-input"
											   id="type_keyword" autocomplete="off" style="height: 30px"/>
								     <i class="ace-icon fa fa-search nav-search-icon"></i>
							  </span>
								</li>
								<span  style="float:right;" class="tooltip-info" data-rel="tooltip" title="新增">
									<button onclick="addGoodsInfo();" class="btn btn-xs btn-success">新增
									</button>&nbsp;&nbsp;&nbsp;&nbsp;
								</span>
							</ul>
							
						</div>
								<div class="col-xs-12">
									<table id="pageGoods_list" class="table table-striped table-bordered table-hover" style="text-align: center">
										<thead>
											<tr>
												<th class="center">商品序号</th>
												<th class="center">商品名称</th>
												<th class="center">商品条码</th>
												<th class="center">商品价格</th>
												<th class="center">
													<select onchange="searchAdvertStatus()" id="advertInfoStatus" style="border: none;">
															<option value=" ">商品状态</option>
															<option value="0">正常状态</option>
															<option value="1">禁用状态</option>
														</select>
												</th>
												<th class="center">操作</th>
											</tr>
										</thead>

										<tbody>
										</tbody>
									</table>
								</div><!-- /.span -->
							</div><!-- /.row -->
							<div><br></div>
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->
		
		<!-- 商品选择弹出窗开始 -->
	<div id="devices_choise_modal_batch" class="modal hiden fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:860px;">
			<div class="modal-content">
				<div class="modal-header thicktitle">
					<a class="close" data-dismiss="modal" aria-hidden="true">×</a> <span class="modal-title" id="myModalLabel">选择商品</span>
				</div>
				<div class="modal-body thickcon" style="width:858px;height: 610px; padding-left: 10px; padding-right: 10px;">
					<input
						type="hidden" id="choise_type" value="0" />
					<div class="row">
						<!-- <div class="col-xs-3">
							<div style="height: 410px;width:0px ;scroll; border: 0px solid #ddd;">
								<ul id="device_tree_batch" class="ztree"></ul>
							</div>
						</div> -->
						<div class="col-xs-12">
							<table id="deviceInfo_list_batch"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center">
											<label class="position-relative">
												<input type="checkbox" class="ace" />
												<span class="lbl"></span>
											</label>
										</th>
										<th class="center">商品名称</th>
										<th class="center">商品条码</th>
										<th class="center">商品价格</th>
										<th class="center">商品状态</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
						</div>
					</div>
					<div id="choise_goods_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
								<a id="choise_submit" class="btn btn-sm no-border btn-primary" onclick="batchAppendGoods()">添加商品</a>
							</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 商品选择弹出窗结束 -->
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
		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/biz/app/pageGoodsList.js"></script>
		<script type="text/javascript">
			$("#submenu-menu-advert-info").addClass("active");
			$("#menu-advert").addClass("active");
			$("#menu-advert").addClass("open");
		</script>
	</body>
</html>



