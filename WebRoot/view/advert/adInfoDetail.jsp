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
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>

				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a>
					</li>
					<li><a href="#">广告管理</a></li>
					<li><a
						href="${pageContext.request.contextPath}/view/advert/adInfoList.jsp">广告内容管理</a></li>
					<li class="active">广告内容详情</li>
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
					<div class="col-xs-12">
						<div class="form-group">
							<div class="col-xs-12">
								<div class="widget-box">
									<div class="widget-header widget-header-small">
										<h5>广告内容详细信息</h5>
									</div>
									<div class="widget-body">
										<div class="widget-body">
											<div class="widget-main">
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">
														广告内容ID: </label> <span class="input-icon input-icon-right">
														<input type="text" id="advertInfoId"
														value="${advertInfo.advertInfoId}" 
														class="required" style="width:450px;"
														name="advertInfo.advertInfoId" disabled="disabled" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														广告内容标题： </label> <span class="input-icon input-icon-right">
														<input type="text" style="width:250px;" value="${advertInfo.advertTitle}"
														id="advertInfo.advertTitle" class="required"
														style="width:450px;" name="advertInfo.advertTitle" disabled="disabled" />
													</span>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														广告内容：  </label><%--  <span class="input-icon input-icon-right">
														<input type="text" value="${advertInfo.advertContent}"
														id="advertInfo.advertContent" class="required"
														style="width:450px;" name="advertInfo.advertContent" disabled="disabled"/>
													</span> --%>
													<textarea rows="6px" cols="32px" maxlength="1990" wrap="soft" name="advertInfo.advertContent" readonly="readonly"
													>${advertInfo.advertContent }</textarea> 
												</div>
												<form action="${pageContext.request.contextPath}/view/advert/advertInfo/downLoadFile.action?inputPath=${advertInfo.advertFile }" 
														method="post" onsubmit="return downLoad();">
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														 上传文件名称： </label> <span class="input-icon input-icon-right"> 
														 <input type="text" id="advertFileName" class="required" style="width:150px;" data-link-field="delive_time" value="${advertInfo.advertFileName}" disabled="disabled"/>
														 <button class="ace">下载</button>
													</span>
												</div>
												</form>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														 广告已支付： </label> <span class="input-icon input-icon-right"> <input
														type="text" id="advertInfo.endDate" class="required"
														style="width:150px;" name="advertInfo.endDate"
														data-link-field="delive_time"
														value="${advertInfo.payFee}" disabled="disabled"/>
													</span>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														 广告未支付： </label> <span class="input-icon input-icon-right"> <input
														type="text" id="advertInfo.endDate" class="required"
														style="width:150px;" name="advertInfo.endDate"
														data-link-field="delive_time"
														value="${unPayFee }" disabled="disabled"/>
													</span>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														 广告总费用： </label> <span class="input-icon input-icon-right"> <input
														type="text" id="advertInfo.endDate" class="required"
														style="width:150px;" name="advertInfo.endDate"
														data-link-field="delive_time"
														value="${advertInfo.advertTotalFee}" disabled="disabled"/>
													</span>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														广告开始日期： </label> <span class="input-icon input-icon-right">
														<%--<input type="text"--%> <%--value="${goodsInfo.goodsStatus}" id="goods__type_Status"--%>
														<%--class="required"--%> <%--style="width:450px;" name="goodsInfo.goodsStatus"/>--%>
														<input type="text" value="${advertInfo.startDate}" style="width:150px;"
														id="advertInfo.startDate" class="required"
														style="width:450px;" name="advertInfo.startDate" disabled="disabled"/>
													</span>
												</div>

												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														 广告结束日期： </label> <span class="input-icon input-icon-right"> <input
														type="text" id="advertInfo.endDate" class="required"
														style="width:150px;" name="advertInfo.endDate"
														data-link-field="delive_time"
														value="${advertInfo.endDate}" disabled="disabled"/>
													</span>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														已投放的设备数量： </label> <span class="input-icon input-icon-right"> <input
														type="text" id="advertInfo.endDate" class="required"
														style="width:150px;" name="advertInfo.endDate"
														data-link-field="delive_time"
														value="${totalDevice }" disabled="disabled"/>
														<button class="ace" onclick="showDevice();" >查看</button>
													</span>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														创建日期：  </label> <span class="input-icon input-icon-right"> <input
														type="text" id="advertInfo.createDate" class="required"
														style="width:150px;" name="advertInfo.createDate"
														data-link-field="delive_time"
														value="${advertInfo.createDate}" disabled="disabled"/>
													</span>
												</div>
												
												<div class="row">
													<div id="form_action_btns" class="col-xs-12"
														style="text-align: center">
														<a href="javascript:history.back();"
															class="btn btn-sm no-border btn-success"> <i
															class="ace-icon fa fa-arrow-left"></i>返回
														</a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->
		</div>
		<!-- /.main-container -->
		<!-- basic scripts -->
		
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
		
		<!--[if !IE]> -->
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

		<!-- <![endif]-->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery
					|| document
							.write("<script src='${pageContext.request.contextPath}/js/jquery.min.js'>"
									+ "<" + "/script>");
		</script>

		<!-- page specific plugin scripts -->
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
			<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/ztree/js/jquery.ztree.core-3.5.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>
		<script src="${pageContext.request.contextPath }/js/biz/advert/advertInfoDevice.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>
	</div>
</body>
</html>


