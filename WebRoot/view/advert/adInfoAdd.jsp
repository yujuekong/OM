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
						href="${pageContext.request.contextPath}/view/advert/adInfoList.jsp">广告内容管理</a></li>
					<li class="active">新增广告</li>
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
						action="${pageContext.request.contextPath}/view/advert/advertInfo/saveOrUpdateAdvertInfo.action"
						method="post" enctype="multipart/form-data" onsubmit="return checkBlank();">
						<div class="col-xs-12">
							<div class="form-group">
								<div class="col-xs-12">
									<div class="widget-box">
										<div class="widget-header widget-header-small">
											<h5 id="h5Id">新增广告</h5>
										</div>
										<div class="widget-body">
											<div class="widget-main">

												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">
														广告内容ID: </label> <span class="input-icon input-icon-right">
														<input type="text" id="gt__type_num"
														value="${advertInfo.advertInfoId}" id="advertInfoId"
														class="required" style="width:450px;"
														name="advertInfo.advertInfoId" />
													</span>
												</div>

												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i> 广告内容标题： </label> <span
														class="input-icon input-icon-right"> <input
														id="advertTitle" type="text" class="required"
														style="width:350px;" name="advertInfo.advertTitle"
														value="${advertInfo.advertTitle}" onblur="titleError();"/>
													</span>
													<span id= "titleError" class="input-icon input-icon-right">
													</span>
												</div>

												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">&nbsp;广告内容描述： </label>
													<textarea rows="6px" cols="46px" maxlength="1990" wrap="soft" name="advertInfo.advertContent" id="advertContent">${advertInfo.advertContent }</textarea>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>上传文件名(文件大小不能超过1M)： </label> <span
														class="input-icon input-icon-right"><input type="hidden" name="advertInfo.advertFile" value="${advertInfo.advertFile }" />
														<input type="text" name="advertInfo.advertFileName" id="adFileName" value="${advertInfo.advertFileName }"><input type="file" id="file" name="adInfoUpload" accept=".png,.swf,.jpg" onchange="fileChange(this);"/>
													</span>
													<span id= "upLoadError" class="input-icon input-icon-right">
													</span>
												</div>
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i> 上传文件类型： </label> <span
														class="input-icon input-icon-right"><input
														id="advertFileType" type="hidden"
														name="advertInfo.advertFileType" value="${advertInfo.advertFileType }" /> 
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i> 广告主名称： </label> <span
														class="input-icon input-icon-right"><input
														id="userIdStr" type="hidden"
														name="advertInfo.advertUserId" value="${advertInfo.advertUserId }" /> <input id="userNameStr"
														type="text" class="required" style="width:200px;"
														value="${advertUser_auName}" onclick="choiseUser(this)" onblur="nameError();"/>
													</span>
													<span id= "nameError" class="input-icon input-icon-right">
													</span>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">&nbsp;广告已支付费用： </label> <span
														class="input-icon input-icon-right"><input id="payFee"
														type="text" class="required" style="width:200px;"
														value="${advertInfo.payFee}" name="advertInfo.payFee" onblur="checkAdvertFee();"/>
													</span>
													<span id= "feeError" class="input-icon input-icon-right">
													</span>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>广告开始日期： </label> <span
														class="input-icon input-icon-right"> <input
														id="advertInfo_startDate" type="text" class="col-xs-10 required"
														style="width:150px;" name="advertInfo.startDate"
														value="${advertInfo.startDate}" onblur="startError();"/><i class="ace-icon fa fa-clock-o blue"></i>
													</span>
													<span id= "startError" class="input-icon input-icon-right">
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>广告结束日期： </label> <span
														class="input-icon input-icon-right"> <input
														id="advertInfo_endDate" type="text" class="col-xs-10 required"
														style="width:150px;" name="advertInfo.endDate"
														value="${advertInfo.endDate}" onblur="endError();"/><i class="ace-icon fa fa-clock-o blue"></i>
													</span>
													<span id= "endError" class="input-icon input-icon-right">
													</span>
												</div>
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i> 广告创建日期： </label> <span
														class="input-icon input-icon-right"> <input
														id="advertInfo_endDate" type="text" class="col-xs-10 required"
														style="width:300px;" name="advertInfo.createDate"
														value="${advertInfo.createDate}" />
													</span>
												</div>
												
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i> 广告总费用： </label> <span
														class="input-icon input-icon-right"> <input
														id="advertInfo_advertTotalFee" type="text" class="col-xs-10 required"
														style="width:300px;" name="advertInfo.advertTotalFee"
														value="${advertInfo.advertTotalFee}" />
													</span>
												</div>
												
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i> 广告支付状态： </label> <span
														class="input-icon input-icon-right"> <input
														id="advertInfo_advertTotalFee" type="text" class="col-xs-10 required"
														style="width:450px;" name="advertInfo.payStatus"
														value="${advertInfo.payStatus}" />
													</span>
												</div>
												
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i> 广告状态： </label> <span
														class="input-icon input-icon-right"> <%--<input type="text"--%>
														<%--value="${goodsInfo.goodsStatus}" id="goods__type_Status"--%>
														<%--class="required"--%> <%--style="width:450px;" name="goodsInfo.goodsStatus"/>--%>
														<select id="advertStatus" name="advertInfo.advertStatus">
															<option value="-1">请选择</option>
															<option value="0"
																${advertInfo.advertStatus == 0 ?"selected= 'selected'" : ""}>正常</option>
															<option value="1"
																${advertInfo.advertStatus == 1 ?"selected= 'selected'" : ""}>禁用</option>
													</select>
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
										
										<th class="center">设备编号</th>
										<th class="center">设备名称</th>
										<th class="center">设备地址</th>
										<th class="center">设备类型</th>
										<th class="center">占地面积</th>
										
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

	<!-- 广告主选择窗开始 -->
	<div id="user_choise_modal" class="modal hiden fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:870px;height:610px;">
			<div class="modal-content">
				<div class="modal-header thicktitle">
					<a class="close" data-dismiss="modal" aria-hidden="true">×</a> <span
						class="modal-title" id="myModalLabel">选择用户</span>
				</div>
				<div class="modal-body thickcon"
					style="width:860px;height: 630px; padding-left:10px; padding-right: 15px;" >
					<input type="hidden" id="purchase_goods_rowno" /> <input
						type="hidden" id="choise_type" value="0" />
					<!-- <div class="row">
						<div id="search-form" class="col-xs-12"
							style="padding-bottom: 5px;">
							<div class="input-group" style="float:right;width:300px;">
								<input type="text" id="keyword"
									class="form-control search-query" placeholder="用户帐号/用户姓名" /> <span
									class="input-group-btn">
									<button id="searchGoodsBtn" type="button"
										class="btn btn-purple btn-sm no-border">
										<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
										搜索
									</button>
								</span>
							</div>
						</div> -->
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
										<th class="center">广告主姓名</th>
										<th class="center">联系人姓名</th>
										<th class="center">联系人电话</th>
										<th class="center">联系人地址</th>
										<th class="center">广告主级别</th>
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
	<!-- 广告主选择窗结束 -->

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
	<script src="${pageContext.request.contextPath}/js/biz/advert/advertInfoAdd.js"></script>
	<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>

	<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
	<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
</body>
</html>



