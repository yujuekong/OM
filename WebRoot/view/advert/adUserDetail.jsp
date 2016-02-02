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
						href="${pageContext.request.contextPath}/view/advert/adUserList.jsp">广告主管理</a></li>
					<li class="active">广告主详情</li>
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
										<h5>广告主详细信息</h5>
									</div>
									<div class="widget-body">
										<div class="widget-body">
											<div class="widget-main">
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">
														广告主ID: </label> <span class="input-icon input-icon-right">
														<input type="text" id="gt__type_num"
														value="${advertUser.advertUserId}" id="goods_Info_id"
														class="required" style="width:450px;"
														name="goodsInfo.goodsId" disabled="disabled" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														广告主姓名： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${advertUser.auName}"
														id="advertUser.auName" class="required"
														style="width:450px;" name="advertUser.auName"
														disabled="disabled" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														广告主地址： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${advertUser.auLinkaddress}"
														id="advertUser.auLinkaddress" class="required"
														style="width:450px;" name="advertUser.auLinkaddress"
														disabled="disabled" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														联系人姓名：</label> <span class="input-icon input-icon-right"> <input
														type="text" value="${advertUser.auLinkman}"
														id="advertUser.auLinkman" class="required"
														style="width:450px;" name="advertUser.auLinkman"
														disabled="disabled" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														联系人电话： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${advertUser.auLinktel}"
														id="advertUser.auLinktel" class="required"
														style="width:450px;" name="advertUser.auLinktel"
														disabled="disabled" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														联系人Email： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${advertUser.auMail}"
														id="advertUser.auLinktel" class="required"
														style="width:450px;" name="advertUser.auLinktel"
														disabled="disabled" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														广告主级别： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${advertUser.auLevel}"
														id="advertUser.auLevel" class="required"
														style="width:450px;" name="advertUser.auLevel"
														disabled="disabled" />
													</span>
												</div>

												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														创建日期： </label> <span class="input-icon input-icon-right"> <input
														type="text" id="advertUser.createDate" class="required"
														style="width:150px;" name="advertUser.createDate"
														data-link-field="delive_time"
														value="${advertUser.createDate}" disabled="disabled" />
													</span>
												</div>

												<div class="row">
													<div id="form_action_btns" class="col-xs-12"
														style="text-align: center">
														<a href="javascript:history.back();" id="save"
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
			src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/jquery.dataTables.bootstrap.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
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


