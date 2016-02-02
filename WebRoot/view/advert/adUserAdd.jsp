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
					<li><a href="#">广告管理</a></li>
					<li><a
						href="${pageContext.request.contextPath}/view/advert/adUserList.jsp">广告主管理</a></li>
					<li class="active">新增广告主信息</li>
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
					<form action="${pageContext.request.contextPath}/view/advert/advertUser/saveOrUpdateAdvertUser.action"
						method="post" onsubmit="return checkBlank();">
						<div class="col-xs-12">
							<div class="form-group">
								<div class="col-xs-12">
									<div class="widget-box">
										<div class="widget-header widget-header-small">
											<h5 id="h5Id">新增广告主</h5>
										</div>
										<div class="widget-body">
											<div class="widget-main">
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">
														广告主ID: </label> <span class="input-icon input-icon-right">
														<input type="text" id="gt__type_num"
														value="${advertUser.advertUserId}" id="advertUserId"
														class="required" style="width:450px;"
														name="advertUser.advertUserId" />
													</span>
												</div>
												<input type="hidden" id="orgId" name="advertUser.orgId" value="${advertUser.orgId }" />
												<input type="hidden" id="proviceId" name="advertUser.proviceId" value="${advertUser.proviceId }" />
												<input type="hidden" id="regionId" name="advertUser.regionId" value="${advertUser.regionId }"/>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i>
														广告主姓名： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${advertUser.auName}"
														id="advertUserName" class="required"
														style="width:250px;" name="advertUser.auName" onblur="userError();"/>
													</span>
													<span id= "userError" class="input-icon input-icon-right">
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														&nbsp;广告主地址： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${advertUser.auLinkaddress}"
														id="advertUser.auLinkaddress" class="required"
														style="width:400px;" name="advertUser.auLinkaddress" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i>
														联系人姓名：</label> <span class="input-icon input-icon-right"> <input
														type="text" value="${advertUser.auLinkman}"
														id="advertUserRealName" class="required"
														style="width:250px;" name="advertUser.auLinkman" onblur="realError();"/>
													</span>
													<span id= "realError" class="input-icon input-icon-right">
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i>
														联系人电话： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${advertUser.auLinktel}"
														id="advertUserTel" class="required"
														style="width:250px;" name="advertUser.auLinktel" onblur="telError();"/>
													</span>
													<span id= "telError" class="input-icon input-icon-right">
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														&nbsp;联系人Email： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${advertUser.auMail}"
														id="advertUserEmail" class="required"
														style="width:250px;" name="advertUser.auMail" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i>
														广告主级别： </label> <span class="input-icon input-icon-right">
														<select id="advertUserLevel" name="advertUser.auLevel" onchange="levelError();">
															<option value="-1">请选择</option>
															<option value="1" ${advertUser.auLevel == 1 ?"selected= 'selected'" : ""}>1</option>
															<option value="2" ${advertUser.auLevel == 2 ?"selected= 'selected'" : ""}>2</option>
															<option value="3" ${advertUser.auLevel == 3 ?"selected= 'selected'" : ""}>3</option>
														</select>
													</span>
													<span id= "levelError" class="input-icon input-icon-right">
													</span>
												</div>
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">
														创建日期： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${advertUser.createDate}"
														id="advertUser.createDate" class="required"
														style="width:450px;" name="advertUser.createDate" />
													</span>
												</div>
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">
														广告主状态： </label> <span class="input-icon input-icon-right"> <%--<input type="text"--%>
														<%--value="${goodsInfo.goodsStatus}" id="goods__type_Status"--%>
														<%--class="required"--%> <%--style="width:450px;" name="goodsInfo.goodsStatus"/>--%>
														<select id="goodsStatus" name="advertUser.auStatus">
															<option value="-1">请选择</option>
															<option value="0"
																${advertUser.auStatus == 0 ?"selected= 'selected'" : ""}>正常</option>
															<option value="1"
																${advertUser.auStatus == 1 ?"selected= 'selected'" : ""}>禁用</option>
													</select>
													</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
							<div id="form_action_btns" class="col-xs-12"
							style="text-align: center">
							<button id="save" class="btn btn-sm no-border btn-success"
							>
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
	<script src="${pageContext.request.contextPath}/js/biz/advert/advertUserAdd.js"></script>
	
	
</body>
</html>

