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
						href="${pageContext.request.contextPath}/view/advert/adInfoList.jsp">广告设备管理</a></li>
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
			<%-- <input type="hidden" id="orgId" value="${account.orgId }" />
			<input type="hidden" id="proviceId" value="${account.dictProviceId }" />
			<input type="hidden" id="regionId" value="${account.dictRegionId }" /> --%>
			<div class="page-content">
				<div class="row">
					<form action="${pageContext.request.contextPath}/view/advert/advertPosition/updateAdvertDevice.action"
						method="post" onsubmit="return checkFee();">
						<div class="col-xs-12">
							<div class="form-group" hidden="hidden">
								<div class="col-xs-12">
									<div class="widget-box">
										<div class="widget-header widget-header-small">
											<h5 id="h5Id">选择广告</h5>
										</div>
										<div class="widget-body">
											<div class="widget-main">

												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i> 广告标题： </label> <span
														class="input-icon input-icon-right"><input
														id="userIdStr" type="hidden"
														name="advertInfoId" value="${advertInfo.advertInfoId }"/> <input id="userNameStr"
														type="text" class="required" style="width:450px;"
														value="${advertInfo.advertTitle}" onclick="choiseAdvertInfo(this)" onchange="checkDevice()"/>
													</span>
													
												</div>
												
												
												
											</div>
											
									
								
										</div>
									</div>
								</div>
							</div>
							
							<!-- 添加站点 开始 -->
							<div class="form-group">
								
								<div class="col-xs-12">
									<div class="widget-box">
										<div class="widget-header widget-header-small">
											<h5>设备列表</h5>
										</div>
										<div class="widget-body">
											<div class="widget-main no-padding">
												<table  class="table table-condensed table-bordered">
													<thead>
														<tr>
														    <th class="center">行号</th>
														    <th class="center" style="width:150px">设备编号</th>
															<th class="center" class="fnumbox">设备名称</th>
															<th class="center">设备地址</th>												
															<th class="center" style="width:150px">广告位价格</th>
															<th class="center">操作</th>
														</tr>
													</thead>
													<tbody id="purchase_goods_list">
														<c:if test="${empty pgLst}">
														<tr>
															<td class="center">1</td>
															<td><input type="text"  name="deviceId"/></td>
															<td>
															</td>
															<td></td>
															<td></td>
															<td class="center">
																<input type="text"  name="advertDevice.advertFee" id="advertDevice.advertFee"/>
															</td>
															<td class="center">
																<input type="hidden" />
																<a href="javascript:void(0)" >移除</a>
															</td>
														</tr>
														</c:if>
														
														<c:forEach var="pg" items="${deviceInfoList }" varStatus="rowIndex">
														<tr>
															<c:set var="index" value="${rowIndex.index + 1}"/>
															<td class="center">${rowIndex.index + 1}</td>
															<%-- <td class="center"><input type="text" id="deviceId" name="advertDeviceDt[${rowIndex.index }].deviceId" value="${pg.deviceId }" onclick="choiseDevice(this)"/></td> --%>
															<td id="deviceNo" class="center"><input type="text" name="deviceNo" value="${pg.deviceNo }" onclick="choiseDevice(this)"/><input type="hidden" id="deviceId" name="advertDeviceDt[${rowIndex.index }].deviceId" value="${pg.deviceId }"/></td>
															<td id="deviceName" class="center">${pg.deviceName }</td>
															<td id="deviceAddress" class="center">${pg.deviceAddress }</td>
															<td><input type="text" id="advertFee" value="${pg.advertFee }" name="advertDeviceDt[${rowIndex.index}].advertFee"/></td>
															<td class="center">
																<input type="hidden" name="advertDeviceDt[${rowIndex.index }].adId" value="${pg.adId }" />
																<input type="hidden"  value="${pg.deviceId}"/>
																<!-- <span href="javascript:void(0)" >移除</span> -->
															</td>
														</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
								<div class="col-xs-12">
									<div class="row">
										<div class="col-xs-6">
											<a class="btn btn-xs no-border btn-info"
												onclick="choiseDeviceBatch()">
												<i class="ace-icon fa glyphicon-plus bigger-120"></i>
												批量添加
											</a>
										</div>
										
									</div>
								</div>
							</div>
							<!-- 添加站点 结束 -->
							<input type="hidden" name="purchase_goods_rowno" id="purchase_goods_rowno"/>
							<!-- 表单操作按钮开始 -->
							<div class="row">
								<div id="form_action_btns" class="col-xs-12"
									style="text-align: center">
									<button id="save" class="btn btn-sm no-border btn-success">
										<i class="ace-icon fa fa-floppy-o"></i>保存
									</button>
									
									&nbsp;&nbsp;&nbsp; <a class="btn btn-sm no-border btn-default"
										href="${pageContext.request.contextPath}/view/advert/adInfoList.jsp"> <i
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
	
	<!-- 多设备选择弹出窗开始 -->
	<div id="devices_choise_modal_batch" class="modal hiden fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:860px;">
			<div class="modal-content">
				<div class="modal-header thicktitle">
					<a class="close" data-dismiss="modal" aria-hidden="true">×</a> <span
						class="modal-title" id="myModalLabel">选择设备</span>
				</div>
				<div class="modal-body thickcon"
					style="width:858px;height: 610px; padding-left: 10px; padding-right: 10px;">
					<input
						type="hidden" id="choise_type" value="0" />
					<div class="row">
						<div class="col-xs-3">
							<div
								style="height: 410px;width:0px ;scroll; border: 0px solid #ddd;">
								<ul id="device_tree_batch" class="ztree"></ul>
							</div>
						</div>
						<div class="col-xs-9">
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
										<th class="center">设备编号</th>
										<th class="center">设备名称</th>
										<th class="center">设备地址</th>
										<th class="center">设备状态</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>
					<div id="choise_goods_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
								<a id="choise_submit" class="btn btn-sm no-border btn-primary" onclick="batchAppendDevices()">添加设备</a>
							</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 多设备选择弹出窗结束 -->
	<!-- basic scripts -->
	
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
	<!-- 非空判断 -->
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
	<script src="${pageContext.request.contextPath}/js/biz/advert/advertPosiitonModifyBatch.js"></script>
	<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>

	<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
	<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
</body>
</html>



