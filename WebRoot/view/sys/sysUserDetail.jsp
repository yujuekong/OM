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
					<li><a href="#">系统管理</a></li>
					<li><a
						href="${pageContext.request.contextPath}/view/sys/userList.jsp">系统用户管理</a></li>
					<li class="active">用户详情</li>
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
										<h5>系统用户详细信息</h5>
									</div>
									<div class="widget-body">
										<div class="widget-body">
											<div class="widget-main">
												
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														用户账号： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${sysUser.userName}"
														id="advertUser.auName" class="required"
														style="width:400px;" readonly="readonly" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														真实姓名： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${sysUser.realName}"
														id="advertUser.auLinkaddress" class="required"
														style="width:400px;" readonly="readonly"/>
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														用户性别：</label> <span class="input-icon input-icon-right"> 
															<select id="advertUserSex" name="sysUser.sex" disabled="disabled">
															<option value="-1">请选择</option>
															<option value="0" ${sysUser.sex == "0" ?"selected= 'selected'" : ""}>男</option>
															<option value="1" ${sysUser.sex == "1" ?"selected= 'selected'" : ""}>女</option>
															
														</select>
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														联系电话： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${sysUser.tel}"
														id="tel" class="required"
														style="width:400px;"  readonly="readonly"/>
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														Email： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${sysUser.email}"
														id="advertUserEmail" class="required"
														style="width:400px;"  readonly="readonly"/>
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														用户所属部门： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${sysDept.deptName}"
														id="sysUserRole" class="required"
														style="width:400px;" readonly="readonly"/>
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														用户所属城市： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${sysUser.userCity}" class="required" style="width:400px;" readonly="readonly"/>
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														所属区域： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${regionDict.dictName}"
														id="sysUserRole" class="required"
														style="width:400px;" readonly="readonly"/>
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														所属省： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${provinceDict.dictName}"
														id="sysUserRole" class="required"
														style="width:400px;" readonly="readonly"/>
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														所属分公司： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${orgDict.dictName}"
														id="sysUserRole" class="required"
														style="width:400px;" readonly="readonly"/>
													</span>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														最后登陆时间： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${sysUser.lastLoginTime}"
														id="advertUserEmail" class="required"
														style="width:400px;"  readonly="readonly"/>
													</span>
												</div>
											
									<div class="form-group" style="width:680px;">
										<div class="widget-box">
											<div class="widget-header widget-header-small">
											<h5>所属角色</h5>
											</div>
											<div class="widget-body">
												<div class="widget-main no-padding">
													<table  class="table table-condensed table-bordered">
														<tbody id="purchase_goods_list">
															<c:forEach var="pg" items="${sysRoleList }" varStatus="rowIndex">
															<tr>
																<td id="roleName" class="center">${pg.roleName }</td>
															</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
										
												
												
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">
														创建日期： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${sysUser.createDate}"
														id="advertUser.createDate" class="required"
														style="width:450px;" name="sysUser.createDate" />
													</span>
												</div>
												
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">
														用户状态： </label> <span class="input-icon input-icon-right">
														<select id="userStatus" name="sysUser.userStatus" disabled="disabled">
															<option value="-1">请选择</option>
															<option value="0"
																${sysUser.userStatus == 0 ?"selected= 'selected'" : ""}>正常</option>
															<option value="1"
																${sysUser.userStatus == 1 ?"selected= 'selected'" : ""}>禁用</option>
													</select>
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
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>
	</div>
</body>
</html>


