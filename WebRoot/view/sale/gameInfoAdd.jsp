<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,java.text.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../commons/head.jsp" />

<body class="no-skin">
	<div class="main-container" id="main-container">
		<div>
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed');
					} catch (e) {
					}
				</script>

				<ul id="uiId" class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a>
					</li>
					<li><a href="#">销售管理</a></li>
					<li><a
						href="${pageContext.request.contextPath}/view/sale/gameInfoList.jsp">抽奖活动管理</a></li>
					<li class="active">新增抽奖活动</li>
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
					<form action="${pageContext.request.contextPath}/view/sale/gameInfo/saveOrUpdateGameInfo.action"
						method="post" onsubmit="return checkBlank();">
						<div class="col-xs-12">
							<div class="form-group">
								<div class="col-xs-12">
									<div class="widget-box">
										<div class="widget-header widget-header-small">
											<h5 id="h5Id">新增抽奖活动</h5>
										</div>
										
										<input type="hidden" value="${account.orgId }" id="orgId" name="activityGameInfo.dictOrgId"/>
										<input type="hidden" value="${activityGameInfo.gameStatus }" id="gameInfoStatus" name="activityGameInfo.gameStatus"/>
										
										<div class="widget-body">
											<div class="widget-main">
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">
														活动ID: </label> <span class="input-icon input-icon-right">
														<input type="text" id="gt__type_num"
														value="${activityGameInfo.gameId}" id="gameId"
														class="required" style="width:450px;"
														name="activityGameInfo.gameId" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i>
														活动名称： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${activityGameInfo.gameName}"
														id="gameName" class="required"
														style="width:250px;" name="activityGameInfo.gameName" />
													</span>
													<span id= "userError" class="input-icon input-icon-right">
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														&nbsp;活动消耗类型： </label> <span class="input-icon input-icon-right">
														<select name="activityGameInfo.payType" >
															<c:forEach var="sd" items="${sysDictList }">
															<option value="${sd.dictId }" ${sd.dictId == payType ?"selected= 'selected'" : ""}>${sd.dictName }</option>
															</c:forEach>
														</select>
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														&nbsp;活动消耗数量： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${activityGameInfo.payAmount}"
														id="payAmount" class="required"
														style="width:150px;" name="activityGameInfo.payAmount" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i> 活动开始日期： </label> <span
														class="input-icon input-icon-right"> <input
														id="gameInfo_startDate" type="text" class="col-xs-10 required"
														style="width:150px;" name="activityGameInfo.startDate"
														value="${activityGameInfo.startDate}" /><i class="ace-icon fa fa-clock-o blue"></i>
													</span>
													<span id= "startError" class="input-icon input-icon-right">
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i> 活动结束日期： </label> <span
														class="input-icon input-icon-right"> <input
														id="gameInfo_endDate" type="text" class="col-xs-10 required"
														style="width:150px;" name="activityGameInfo.endDate"
														value="${activityGameInfo.endDate}" /><i class="ace-icon fa fa-clock-o blue"></i>
													</span>
													<span id= "endError" class="input-icon input-icon-right">
													</span>
												</div>
												
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
							<div id="form_action_btns" class="col-xs-12" style="text-align: center">
							<button id="save" class="btn btn-sm no-border btn-success"><i class="ace-icon fa fa-floppy-o"></i>保存</button>
							&nbsp;&nbsp;&nbsp; <a class="btn btn-sm no-border btn-default" onclick="javascript:history.back()"> <i class="ace-icon fa fa-times red2"></i>取消
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
	<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>
	<script src="${pageContext.request.contextPath}/js/biz/sale/gameInfoAdd.js"></script>
	
	
</body>
</html>

