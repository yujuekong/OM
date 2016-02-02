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
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>

					<ul class="breadcrumb">
						<li>系统</li>
						<li><a href="${pageContext.request.contextPath}/view/sys/sysRole/queryRole.action">角色权限管理</a></li>
						<li class="active">成员设置</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>
				<input type="hidden" id="str" value="${str }"/>
				<form class="form-horizontal" role="form" enctype="multipart/form-data"
							action="${pageContext.request.contextPath}/view/sys/sysRole/addEmpToRole.action" method="post" onsubmit="return check()">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<h4 class="blue">设置角色:
								${roleName }
								&nbsp;&nbsp;&nbsp;
							</h4>
							<span id="selectCompany">
							      分公司：
								<select id="checkCompany" onchange="companyChange();">
								<option value=" " >${sysDict.dictName }</option>
									<c:forEach var="cl" items="${dictList }">
										<option value="${cl.dictId }">${cl.dictName }</option>
									</c:forEach>
								</select>
							</span>
						</div>
						<br>
						<div class="col-xs-12">
							<div class="row">								
								<div class="col-xs-12">
									<div>
										<table id="deptUserTable" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<input type="hidden" name="roleId" id="roleId" value="${roleId }">
													<!-- <th class="center">
														<label class="position-relative">
															<input type="checkbox" class="ace"  onclick="checkAll(this)"/>
															<span class="lbl"></span>
														</label>
													</th> -->
													<th class="center">选择</th>
													<th class="center">用户账户</th>
													<th class="center">真实姓名</th>
													<th class="center">用户性别</th>
													<th class="center">联系电话</th>
													<th class="center">用户状态</th>
												</tr>
											</thead>
											<tbody>
												<c:if test="${empty userList }">
													<tr><td colspan="6">暂无用户数据</td></tr>
												</c:if>
												<c:if test="${!empty userList }">
													<c:forEach var="sellerUser" items="${userList }" varStatus="rowIndex" >
														<tr>
															<td class="center">
																<c:if test="${!empty roleUserList }">
																	<c:forEach var="roleUser" items="${roleUserList }" varStatus="rowIndex" >
																		<c:if test="${sellerUser.userId eq roleUser.userId }">
																			<c:set value="false" var="iss"></c:set>
																		</c:if>
																	</c:forEach>
																</c:if>
																<c:if test="${empty roleUserList }">
																	<c:set value="true" var="iss"></c:set>
																</c:if>
																<c:choose>
																	<c:when test="${iss eq false}">
																		<a href="${pageContext.request.contextPath}/view/sys/sysRole/delEmpFromRole.action?empId=${sellerUser.userId }&roleId=${roleId}">移除</a>
																		<label class="position-relative">
																			<input type="checkbox" class="ace" name="empIds" value="${sellerUser.userId }"	checked="checked">
																			<span class="lbl"></span>
																		</label>
																	</c:when>
																	<c:otherwise>
																	&nbsp;&nbsp;&nbsp;&nbsp;
																		<label class="position-relative">
																		<input type="checkbox" class="ace" name="empIds" value="${sellerUser.userId }">
																		<span class="lbl"></span>
																		</label>
																	</c:otherwise>
																</c:choose>
																<c:set value="true" var="iss"></c:set>
															
															</td>	
															<td class="center">${sellerUser.userName } </td>
															<td class="center">${sellerUser.realName } </td>
															<td class="center">
																<c:choose>
																	<c:when test="${sellerUser.sex eq 0}">男 </c:when>
																	<c:otherwise>女</c:otherwise>
																</c:choose>
															</td>
															<td class="center">${sellerUser.tel } </td>
															<td class="center">
																<c:choose>
																	<c:when test="${sellerUser.userStatus eq 0}"><span class="label label-sm label-success">正常</span></c:when>
																	<c:otherwise><span class="label label-sm label-error">禁用</span></c:otherwise>
																</c:choose>
															</td>
														</tr>
													</c:forEach>
												</c:if>
												<tr>
													<td colspan="9" bgcolor="white" align="center">
														<input type="submit" class="btn btn-sm btn-success no-border"
															
															class="BigInputMoney" value="加入该角色">
															<a class="btn btn-sm btn-success no-border" href="${pageContext.request.contextPath}/view/sys/sysRole/queryRole.action">返回</a>
													</td>
												</tr>				
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
				</form>
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

		<!-- <![endif]-->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${pageContext.request.contextPath}/js/jquery.min.js'>"+"<"+"/script>");
		</script>

		<!-- page specific plugin scripts -->
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
		<script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.dataTables.bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/biz/sys/deptTreeIndex.js"></script>
		<!-- inline scripts related to this page -->
		<script src="${pageContext.request.contextPath}/js/ycs.util.js" ></script>
	</body>
</html>



