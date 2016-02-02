<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.*,java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../commons/head.jsp" />

<body class="no-skin">
	<div class="main-container" id="main-container">
		<div>
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>

				<ul id="uiId" class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a>
					</li>
					<li><a href="#">广告管理</a></li>
					<li><a
						href="${pageContext.request.contextPath}/view/advert/adPositionList.jsp">广告设备管理</a></li>
					<li class="active">修改广告设备</li>
				</ul>
				<!-- /.breadcrumb -->

				<div class="nav-search" id="nav-search">
					<a href="javascript:history.back()"> <i
						class="ace-icon fa fa-arrow-left"></i>返回
					</a>
				</div>
				<!-- /.nav-search -->
			</div>

			<div class="page-content">
				<div class="row">
					<form
						action="${pageContext.request.contextPath}/view/advert/advertPosition/saveModifyAdvertDevice.action"
						method="post">
						<div class="col-xs-12">
							<div class="form-group">
								<div class="col-xs-12">
									<div class="widget-box">
										<div class="widget-header widget-header-small">
											<h5 id="h5Id">修改广告投放信息</h5>
										</div>
										<div class="widget-body">
											<div class="widget-main">
											
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">
														广告设备ID: </label> <span class="input-icon input-icon-right">
														<input type="text" id="gt__type_num"
														value="${advertDevice.adId}" id="advertDevice.adId"
														class="required" style="width:450px;"
														name="advertDevice.adId" />
													</span>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i> 广告标题： </label> <span
														class="input-icon input-icon-right"><input
														id="userIdStr" type="hidden"
														name="advertDevice.advertInfoId" value="${advertInfo.advertInfoId }"/> <input id="userNameStr"
														type="text" class="required" style="width:450px;"
														value="${advertInfo.advertTitle}" onclick="choiseAdvertInfo(this)" />
													</span>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i> 设备名称： </label> <span
														class="input-icon input-icon-right"><input
														id="deviceIdStr" type="hidden"
														name="advertDevice.deviceId" value="${deviceInfo.deviceId }"/> <input id="deviceNameStr"
														type="text" class="required" style="width:450px;"
														value="${deviceInfo.deviceName}" onclick="choiseDevice(this)" />
													</span>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i> 广告投放价格： </label> <span
														class="input-icon input-icon-right"><input type="hidden" id="advertFee" name="advertFee" 
														value="${advertDevice.advertFee }" />
														<input id="advertDevice.advertFee"
														type="text" class="required" style="width:450px;" name="advertDevice.advertFee"
														value="${advertDevice.advertFee}" />
													</span>
												</div>
												
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i> 广告投放状态： </label> <span
														class="input-icon input-icon-right">
														<input id="advertDevice.advertFee"
														type="text" class="required" style="width:450px;" name="advertDevice.adStatus"
														value="${advertDevice.adStatus}" />
													</span>
												</div>
												
											</div>
											
									
								
										</div>
									</div>
								</div>
							</div>
							
							
							<!-- 表单操作按钮开始 -->
							<div class="row">
								<div id="form_action_btns" class="col-xs-12"
									style="text-align: center">
									<button id="save" class="btn btn-sm no-border btn-success">
										<i class="ace-icon fa fa-floppy-o"></i>保存
									</button>
									&nbsp;&nbsp;&nbsp; <a class="btn btn-sm no-border btn-default"
										onclick="javascript:history.back()"> <i
										class="ace-icon fa fa-times red2"></i>取消
									</a>
								</div>
							</div>
							
							

							
						</div>
					</form>
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->
	</div>
	<!-- /.main-container -->
	
	<!-- 设备选择弹出窗开始 -->
	<div id="devices_choise_modal" class="modal hiden fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:860px;">
			<div class="modal-content">
				<div class="modal-header thicktitle">
					<a class="close" data-dismiss="modal" aria-hidden="true">×</a> <span
						class="modal-title" id="myModalLabel">选择设备</span>
				</div>
				<div class="modal-body thickcon"
					style="width:858px;height: 610px; padding-left: 10px; padding-right: 10px;">
					<input type="hidden" id="purchase_goods_rowno" /> <input
						type="hidden" id="choise_type" value="0" />
					<div class="row">
						<div id="search-form" class="col-xs-12"
							style="padding-bottom: 5px;">
							<div class="input-group" style="float:right;width:300px;">
								<input type="text" id="keyword"
									class="form-control search-query" placeholder="设备名称/设备编号" /> <span
									class="input-group-btn">
									<button id="searchGoodsBtn" type="button"
										class="btn btn-purple btn-sm no-border">
										<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
										搜索
									</button>
								</span>
							</div>
						</div>
						<div class="col-xs-3">
							<div
								style="height: 410px;overflow-y: scroll; border: 1px solid #ddd;">
								<ul id="device_tree" class="ztree"></ul>
							</div>
						</div>
						<div class="col-xs-9">
							<table id="deviceInfo_list"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center">设备ID</th>
										<th class="center">设备编号</th>
										<th class="center">设备名称</th>
										<th class="center">设备类型</th>
										<th class="center">设备地址</th>
										<th class="center">设备状态</th>
										<th class="center">创建日期</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	<!-- 设备选择弹出窗结束 -->
	<!-- basic scripts -->
	
	<!-- 广告内容选择窗开始 -->
	<div id="user_choise_modal" class="modal hiden fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:870px;height:610px;">
			<div class="modal-content">
				<div class="modal-header thicktitle">
					<a class="close" data-dismiss="modal" aria-hidden="true">×</a> <span
						class="modal-title" id="myModalLabel">选择广告</span>
				</div>
				<div class="modal-body thickcon"
					style="width:860px;height: 630px; padding-left:10px; padding-right: 15px;" >
					<input type="hidden" id="purchase_goods_rowno" /> <input
						type="hidden" id="choise_type" value="0" />
					<div class="row">
						<div class="col-xs-3" style="width: 60px">
							<div
								style="height: 410px;width:0px ;scroll; border: 0px solid #ddd;">
								<ul id="device_tree" class="ztree"></ul>
							</div>
						</div>
						
						<div class="col-xs-9" style="width: 750px ;" >
							<table id="user_list"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center">广告内容ID</th>
										<th class="center">广告标题</th>
										<th class="center">广告主名称</th>
										<th class="center">广告主电话</th>
										<th class="center">广告开始日期</th>
										<th class="center">广告结束日期</th>
										<th class="center">创建日期</th>
										<th class="center">广告状态</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 广告内容选择窗结束 -->

	<!--[if !IE]> -->
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

	<!-- <![endif]-->

	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='${pageContext.request.contextPath}/js/jquery.min.js'>"
								+ "<"+"/script>");
	</script>

	<!-- page specific plugin scripts -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/ztree/js/jquery.ztree.core-3.5.min.js"></script>

	<!-- ace scripts -->
	<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script src="${pageContext.request.contextPath}/js/biz/advert/advertPositionModify.js"></script>
	<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>

	<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
	<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
</body>
</html>



