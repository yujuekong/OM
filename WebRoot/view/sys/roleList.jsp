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
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="#">首页</a>
						</li>
						<li><a href="#">系统</a></li>
						<li><a href="${pageContext.request.contextPath}/view/sys/sysRole/queryRole.action">权限管理</a></li>
						<li class="active">角色管理</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="appendButton">
						<a href="#">
							<button type="button"  onclick="openRole()" class="btn btn-minier btn-success">添加角色</button>
						</a>
					</div><!-- /.nav-search -->
				</div>
				<input type="hidden" id="orgId" value="${account.orgId }"/>
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
						<form class="form-horizontal" role="form" enctype="multipart/form-data"
							action="${pageContext.request.contextPath}/view/sys/sysRole/addRole.action" method="post" onsubmit="return check()">
							<div class="row">
								<div class="col-xs-12">
									<!-- <div class="table-responsive"> -->

									<!-- <div class="dataTables_borderWrap"> -->
									<div>
										<table class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
																									
													<th class="center">角色名称</th>
													<th class="center">角色描述</th>
													<th class="center">添加成员</th>
													<th class="center">后台菜单设置</th>
													<th class="center">手机菜单设置</th>
													<th class="center">删除角色</th>
													
												</tr>
											</thead>

											<tbody>
												<c:forEach var="sysRole" items="${roleList }" varStatus="rowIndex" >
													<tr>
															
														<td class="center">${sysRole.roleName } </td>
														<td>${sysRole.roleDesc } </td>
														<td class="center">
															<div class="hidden-sm hidden-xs action-buttons">
																<a class="green" href="#">													
																	<i class="ace-icon fa fa-share bigger-150" onclick="preAddUserToRole(${sysRole.roleId })"></i>
																</a>																													
															</div>															
														</td>
														<td class="center">	
															<div class="hidden-sm hidden-xs action-buttons">
																<c:if test="${sysRole.roleName ne '管理员'}">
																	<a class="gray" href="#">													
																		<i class="ace-icon fa fa-cog bigger-150" onclick="setTree('${sysRole.roleId }','TREE','${account.orgId }')"></i>
																	</a>
																</c:if>
															</div>
														</td>
														
														<td class="center">	
															<div class="hidden-sm hidden-xs action-buttons">
																<c:if test="${sysRole.roleName ne '管理员'}">
																	<a class="gray" href="#">													
																		<i class="ace-icon fa fa-cog bigger-150" onclick="setTreeApp('${sysRole.roleId }','APP','${account.orgId }')"></i>
																	</a>
																</c:if>
															</div>
														</td>
														<td class="center">
															<div class="hidden-sm hidden-xs action-buttons">
																
																<c:choose>
																	<c:when test="${sysRole.roleName eq '系统管理员'}"></c:when>
																	<c:otherwise>
																		<c:if test="${sysRole.roleName ne '管理员'}">
																			<a class="red" href="#">
																				<i class="ace-icon fa fa-trash-o bigger-150" onclick="delRole('${sysRole.roleId }','${sysRole.roleName }')"></i>
																			</a>
																		</c:if>
																	</c:otherwise>
																</c:choose>
															</div>
														</td>														
													</tr>
												</c:forEach>					
											</tbody>
										</table>
									</div>
								</div>
							</div>
							
							<!-- 添加角色开始 -->
							<div class="form-group"  id="role_choise_modal" style="display: none" >
								<div class="col-xs-12">
									<div class="widget-box">
										<div class="widget-header">
											<h5>添加角色</h5>
										</div>
										<div class="widget-body">
											<div class="widget-main">
												<div class="form-group">
													<div class="row">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															角色名称： </label>
														<div class="col-xs-2 input-group input-group-sm">
															<input type="text"  class="required" id="delive_time" name="sysRole.roleName" />
														</div>
													</div>
												</div>
												
												<div class="form-group">
													<div class="row">
														<label class="col-sm-2 control-label no-padding-right">&nbsp;角色描述： </label>
														<div class="col-xs-10 input-group input-group-sm">
															<textarea class="form-control limited" maxlength="2000" style="width:503px;height:70px" name="sysRole.roleDesc"></textarea>
														</div>
													</div>
												</div>
												<div class="form-group">
													<div class="row">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															角色所属公司： </label>
														<div class="col-xs-2 input-group input-group-sm">
															<select id="company" name="sysRole.isOrgId">
																<option value="0">总公司</option>
																<option value="1">分公司</option>
															</select>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- 添加角色结束 -->
							<!-- 表单操作按钮开始 -->
							<div class="row"  id="submit_choise_modal" style="display: none">
								<div class="col-xs-12" style="text-align: center">
									<button class="btn btn-sm no-border btn-success" onclick="saveRole()">
										<i class="ace-icon fa fa-check"></i>提交</button>&nbsp;&nbsp;&nbsp;
								</div>
							</div>
							<!-- 表单操作按钮结束 -->
							</form>
						</div><!-- /.col -->
								
					</div><!-- /.row -->						
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

		<!-- <![endif]-->
		<!-- 机构选择树 -->
		<div id="gt_combobox" 
	         style=" display:none; position: absolute;border: 1px solid #ddd;background-color:#F0F0F0;overflow:auto">
	        <ul id="org_tree" class="ztree" style="margin-top:0; "></ul>
	    </div>
		
		<!-- 商品选择弹出窗开始 -->
		<div id="dict_choise_modal" class="modal hiden fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:600px;">
				<div class="modal-content" >
					<div class="modal-header thicktitle">
					<a class="close" data-dismiss="modal" aria-hidden="true">×</a> <span
						class="modal-title" id="myModalLabel">增加角色权限</span>
				</div>
					<div class="modal-body thickcon"
					style="width:592px;height: 520px; padding-left: 10px; padding-right: 10px;overflow:scroll">
							
							<div class="row">
								<div class="col-xs-12" >
									<div style=" border: 1px solid #ddd;margin-left: auto;margin-right: auto;">
										<input type="hidden" name="roleId" id="roleId" value="${roleId }"/>
										<input type="hidden" name="menutype" id="menutype" value="${menutype }"/>
										<ul id="menu_tree" class="ztree"></ul>
									</div>
								</div>							
							</div>
					</div>
					<div class="row">
					<div class="col-xs-12" id="roleButton" hidden="hidden">
					<div class="col-xs-12" style="text-align:center;margin-top:5px;margin-bottom:5px " >
					<button style="cursor:pointer;" onclick="updateAuth()" class="btn btn-sm no-border btn-success" >添加权限</button>
						</div>
						</div>
						</div>
				</div>
			</div>
		</div>
		<!-- 商品选择弹出窗结束 -->
		
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
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js" ></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.excheck-3.5.min.js" ></script>
		<!-- inline scripts related to this page -->
		<script src="${pageContext.request.contextPath}/js/ycs.util.js" ></script>
		<script src="${pageContext.request.contextPath}/js/biz/sys/roleList.js" ></script>
		<script src="${pageContext.request.contextPath}/js/biz/sys/roleMenu.js" ></script>
		<script type="text/javascript">
			$("#submenu-menu-sys-role").addClass("active");
			$("#menu-sys").addClass("active");
			$("#menu-sys").addClass("open");
		</script>
	</body>
</html>



