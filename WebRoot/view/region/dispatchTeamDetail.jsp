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
					<li><a href="#">配送管理</a></li>
					<li><a
						href="${pageContext.request.contextPath}/view/region/dispatchTeamList.jsp">配送小组管理</a></li>
					<li class="active">配送小组详情</li>
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
										<h5>配送小组详细信息</h5>
									</div>
									<div class="widget-body">
										<div class="widget-body">
											<div class="widget-main">
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														配送小组名称： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${dispatchingTeam.teamName}"
														id="advertUser.auName" class="required"
														style="width:450px;" name="advertUser.auName"
														disabled="disabled" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														配送小组编号： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${dispatchingTeam.teamNo}"
														id="advertUser.auLinkaddress" class="required"
														style="width:450px;" name="advertUser.auLinkaddress"
														disabled="disabled" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														配送小组负责人：</label> <span class="input-icon input-icon-right"> <input
														type="text" value="${mainUser.realName }"
														id="advertUser.auLinkman" class="required"
														style="width:450px;" name="advertUser.auLinkman"
														disabled="disabled" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														配送小组成员：</label> <span class="input-icon input-icon-right"> <input
														type="text" value="${otherUser.realName }"
														id="advertUser.auLinkman" class="required"
														style="width:450px;" name="advertUser.auLinkman"
														disabled="disabled" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														配送小组车辆： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${carInfo.carNo }"
														id="advertUser.auLinktel" class="required"
														style="width:450px;" name="advertUser.auLinktel"
														disabled="disabled" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														配送小组路线： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${carLine.lineName }"
														id="advertUser.auLinktel" class="required"
														style="width:450px;" name="advertUser.auLinktel"
														disabled="disabled" />
													</span>
												</div>


												<div class="row">
													<div id="form_action_btns" class="col-xs-12"
														style="text-align: center">
														<a href="${pageContext.request.contextPath}/view/region/dispatchTeamList.jsp" id="save"
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


