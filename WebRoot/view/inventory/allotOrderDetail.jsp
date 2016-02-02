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
					<li><a href="#">仓库管理</a></li>
					<li><a
						href="${pageContext.request.contextPath}/view/inventory/allotOrderList.jsp">调拨单管理</a></li>
					<li class="active">调拨单详情</li>
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
										<h5>调拨单详细信息</h5>
									</div>
									<div class="widget-body">
										<div class="widget-body">
											<div class="widget-main">
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														调拨单编号： </label> <span class="input-icon input-icon-right">
														<input type="text" style="width:250px;" value="${storageAllot.allotNo}"
														 class="required"
														style="width:450px;" name="advertInfo.advertTitle" disabled="disabled" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														商品出库仓库： </label> <span class="input-icon input-icon-right">
														<input type="text" style="width:250px;" value="${startWarehouse.warehouseName}"
														 class="required"
														style="width:450px;" name="advertInfo.advertTitle" disabled="disabled" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														商品入库仓库： </label> <span class="input-icon input-icon-right">
														<input type="text" style="width:250px;" value="${endWarehouse.warehouseName}"
														 class="required"
														style="width:450px;" name="advertInfo.advertTitle" disabled="disabled" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														调拨单创建人： </label> <span class="input-icon input-icon-right">
														<input type="text" style="width:250px;" value="${createUser.realName}"
														 class="required"
														style="width:450px;" name="advertInfo.advertTitle" disabled="disabled" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														调拨单创建日期： </label> <span class="input-icon input-icon-right">
														<input type="text" style="width:250px;" value="${storageAllot.createDate}"
														 class="required"
														style="width:450px;" name="advertInfo.advertTitle" disabled="disabled" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														审核人： </label> <span class="input-icon input-icon-right">
														<input type="text" style="width:250px;" value="${auditingUser.realName}"
														 class="required"
														style="width:450px;" name="advertInfo.advertTitle" disabled="disabled" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														审核日期： </label> <span class="input-icon input-icon-right">
														<input type="text" style="width:250px;" value="${storageAllot.auditingDate}"
														 class="required"
														style="width:450px;" name="advertInfo.advertTitle" disabled="disabled" />
													</span>
												</div>
												<%-- <div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														领导审核人： </label> <span class="input-icon input-icon-right">
														<input type="text" style="width:250px;" value="${checkUser.realName}"
														 class="required"
														style="width:450px;" name="advertInfo.advertTitle" disabled="disabled" />
													</span>
												</div> --%>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														调拨原因：  </label>
													<textarea rows="4px" cols="32px" maxlength="1990" wrap="soft" name="advertInfo.advertContent" readonly="readonly"
													>${storageAllot.allotReason }</textarea> 
												</div>
												<div class="row">
													<div id="form_action_btns" class="col-xs-12"
														style="text-align: center">
														<a href="${pageContext.request.contextPath}/view/inventory/allotOrderList.jsp"
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
		<script
			src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>
	</div>
</body>
</html>


