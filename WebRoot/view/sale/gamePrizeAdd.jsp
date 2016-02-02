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
						<li><a href="javascript:pageReturn();">活动奖励管理</a></li>
					<li class="active">新增活动奖励</li>
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
					<form action="${pageContext.request.contextPath}/view/sale/gameInfo/addGamePrize.action"
						method="post" onsubmit="return checkBlank();">
						<div class="col-xs-12">
							<div class="form-group">
								<div class="col-xs-12">
									<div class="widget-box">
										<div class="widget-header widget-header-small">
											<h5 id="h5Id">新增抽奖活动</h5>
										</div>
										<div class="widget-body">
											<div class="widget-main">
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">
														奖励ID: </label> <span class="input-icon input-icon-right">
														<input type="text" id="gt__type_num"
														value="${activityGamePrize.gamePrizeId}" id="prizeId"
														class="required" style="width:450px;"
														name="activityGamePrize.gamePrizeId" />
													</span>
												</div>
												<input type="hidden" id="gameId" name="gameId" value="${gameId }"/>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i>
														奖励名称： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${activityGamePrize.prizeName}"
														id="prizeName" class="required"
														style="width:250px;" name="activityGamePrize.prizeName" />
													</span>
													<span id= "userError" class="input-icon input-icon-right">
													</span>
												</div>
												<div class="form-group" hidden="hidden">
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
														&nbsp;奖励类型： </label> <span class="input-icon input-icon-right">
														<select name="activityGamePrize.prizeType" >
															<c:forEach var="sd" items="${sysDictList }">
															<option value="${sd.dictId }" ${sd.dictId == payType ?"selected= 'selected'" : ""}>${sd.dictName }</option>
															</c:forEach>
														</select>
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														&nbsp;奖励数量(面额)： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${activityGamePrize.prizeAmount}"
														id="payAmount" class="required"
														style="width:150px;" name="activityGamePrize.prizeAmount" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														&nbsp;奖品总数量： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${activityGamePrize.prizeNum}"
														id="payAmount" class="required"
														style="width:150px;" name="activityGamePrize.prizeNum" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														&nbsp;中奖概率（输入1000，即1/1000）： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${activityGamePrize.prizePro}"
														id="payAmount" class="required"
														style="width:150px;" name="activityGamePrize.prizePro" />
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
	<script src="${pageContext.request.contextPath}/js/biz/sale/gamePrizeAdd.js"></script>
	
	
</body>
</html>

