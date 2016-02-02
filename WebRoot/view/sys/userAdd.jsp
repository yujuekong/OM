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
					<li><a href="#">系统管理</a></li>
					<li><a
						href="${pageContext.request.contextPath}/view/sys/userList.jsp">系统用户管理</a></li>
					<li class="active">新增用户</li>
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
					<form action="${pageContext.request.contextPath}/view/sys/sysUser/saveOrUpdateUser.action"
						method="post" onsubmit="return checkBlank();">
						<div class="col-xs-12">
							<div class="form-group">
								<div class="col-xs-12">
									<div class="widget-box">
										<div class="widget-header widget-header-small">
											<h5 id="h5Id">新增用户</h5>
										</div>
										<div class="widget-body">
											<div class="widget-main">
												<input type="hidden" id="orgId" value="${account.orgId }"/>
												<input type="hidden" id="proviceId" value="${account.dictProviceId }" />
												<input type="hidden" id="regionId" value="${account.dictRegionId }" />
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">
														用户ID: </label> <span class="input-icon input-icon-right">
														<input type="text" id="gt__type_num" value="${sysUser.userId}" id="advertUserId" class="required" style="width:450px;" name="sysUser.userId" />
													</span>
												</div>
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">&nbsp;删除标志: </label> <span class="input-icon input-icon-right">
														<input type="text" id="gt__type_num" value="${sysUser.isDel}" id="sysUserIsDel" class="required" style="width:450px;" name="sysUser.isDel" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">&nbsp;用户账号： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${sysUser.userName}" id="sysUserName" class="required" style="width:400px;" name="sysUser.userName" onblur="checkUserOnly();"/>
													</span>
													<span id= "nameError" class="input-icon input-icon-right">
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">&nbsp;真实姓名： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${sysUser.realName}" id="realUserName" class="required" style="width:400px;" name="sysUser.realName"  onchange="checkRealName();"/>
													</span>
													<span id= "realNameError" class="input-icon input-icon-right">
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">&nbsp;用户性别：</label> <span class="input-icon input-icon-right"> 
															<select id="advertUserSex" name="sysUser.sex">
															<option value="-1">请选择</option>
															<option value="0" ${sysUser.sex == "0" ?"selected= 'selected'" : ""}>男</option>
															<option value="1" ${sysUser.sex == "1" ?"selected= 'selected'" : ""}>女</option>
															
														</select>
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">&nbsp;联系电话： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${sysUser.tel}"  class="required" id="telnumber" style="width:400px;" name="sysUser.tel" onblur="checkTelOnly();"/>
													</span>
													<span id= "telError" class="input-icon input-icon-right">
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">&nbsp;Email： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${sysUser.email}" id="advertUserEmail" class="required" style="width:400px;" name="sysUser.email" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">&nbsp;所属城市： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${sysUser.userCity}" id="advertUserCity" readonly="readonly" style="width:400px;" name="sysUser.userCity"/>
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">&nbsp;用户所属部门： </label> <span class="input-icon input-icon-right">
														<input type="hidden" id="sysDeptId" value="${sysUser.deptId}" id="advertUserId" class="required" style="width:450px;" name="sysUser.deptId" />
														<input type="text" value="${sysDept.deptName}" id="sysDeptName" class="required" style="width:400px;"  onclick="choiseDept();"/>
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">&nbsp;所属分公司： </label> <span class="input-icon input-icon-right">
														<input id="orgNameStr" type="text" class="required" style="width:400px;" value="${sysDict.dictName }" onclick="showMenu()" />
														<input type="hidden" id="beforeShow" value="${beforeShow }"/>
														<input type="hidden" id="dictId" name="dictId" />
				                                         <input type="hidden"  id="dictPid" name="dictPid" 
				                                                   />
				                                         <input type="hidden" id="dictPPid" name="dictPPid"
				                                                   /> 
				                                          <input type="hidden" id="treeNode" name="treeNode"/>  
													</span>
												</div>
												<input type="hidden" name="sysUser.orgId" value="${sysUser.orgId }"/>
												<input type="hidden" name="sysUser.dictRegionId" value="${sysUser.dictRegionId }"/>
												<input type="hidden" name="sysUser.dictProviceId" value="${sysUser.dictProviceId }"/>
												<%-- <div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">&nbsp;用户所属角色： </label> <span class="input-icon input-icon-right">
														<input type="hidden" value="${sysUser.roleId }" id="sysUserRoleId" name="sysUser.roleId"/>
														<input type="text" value="${sysRole.roleName}" id="sysUserRole" class="required" style="width:400px;" onclick="choiseRole(this)"/>
													</span>
												</div> --%>
												<div class="form-group" >
												<div class="widget-box">
										<div class="widget-header widget-header-small">
											<h5><a onclick="choiseRoleBatch();" class="btn btn-xs no-border btn-info"><i class="ace-icon fa glyphicon-plus bigger-120"></i>批量添加角色</a></h5>
										</div>
										<div class="widget-body">
											<div class="widget-main no-padding">
												<table  class="table table-condensed table-bordered">
													<thead>
														<tr>
														    <th class="center">行号</th>
														    <th class="center">角色名称</th>
															<th class="center">角色描述</th>												
															<th class="center">操作</th>
														</tr>
													</thead>
													<tbody id="purchase_goods_list">
														<c:if test="${empty pgLst}">
														<tr>
															<td class="center">1</td>
															<td></td>
															<td></td>
															<td class="center">
																<a href="javascript:void(0)" >移除</a>
															</td>
														</tr>
														</c:if>
														
														<c:forEach var="pg" items="${sysRoleList }" varStatus="rowIndex">
														<tr>
															<td class="center">${rowIndex.index + 1}</td>
															<td id="roleName" class="center"><input type="text" id="roleRealName" value="${pg.roleName }"  style="border-left: 0;border-right: 0;border-top: 0;border-bottom: 0px;color:#222222;font-size:13px;"></td>
															<td id="roleDesc" class="center">${pg.roleDesc }</td>
															<td class="center">
																<input type="hidden" id="roleId" name="roleInfoList[${rowIndex.index }].roleId" value="${pg.roleId }"/>
																<a onclick="removeGoodsItem(this)" style="cursor: pointer">移除</a>
															</td>
														</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</div>
												</div>
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">&nbsp;创建日期： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${sysUser.createDate}"  class="required" style="width:450px;" name="sysUser.createDate" />
													</span>
												</div>
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">
														最后登陆时间： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${sysUser.lastLoginTime}"  class="required" style="width:450px;" name="sysUser.lastLoginTime" />
													</span>
												</div>
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">
														用户密码： </label> <span class="input-icon input-icon-right">
														<input type="text" value="${sysUser.password}"  class="required" style="width:450px;" name="sysUser.password" />
													</span>
												</div>
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">
														用户状态： </label> <span class="input-icon input-icon-right">
														<select id="userStatus" name="sysUser.userStatus">
															<option value="-1">请选择</option>
															<option value="0"
																${sysUser.userStatus == 0 ?"selected= 'selected'" : ""}>正常</option>
															<option value="1"
																${sysUser.userStatus == 1 ?"selected= 'selected'" : ""}>禁用</option>
													</select>
													</span>
												</div>
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
					</form>
				</div>
				<!-- /.row -->
				
				
			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->
	</div>
	<!-- /.main-container -->

	<!-- 机构选择树 -->
		<div id="gt_combobox" 
	         style=" display:none; position: absolute;border: 1px solid #ddd;background-color:#F0F0F0;overflow:auto">
	        <ul id="org_tree" class="ztree" style="margin-top:0; "></ul>
	    </div>

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
	
	<!-- 部门选择弹出窗开始 -->
	<div id="dept_choise_modal" class="modal hiden fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:860px;">
			<div class="modal-content">
				<div class="modal-header thicktitle">
					<a class="close" data-dismiss="modal" aria-hidden="true">×</a> <span
						class="modal-title" id="myModalLabel">选择部门</span>
				</div>
				<div class="modal-body thickcon"
					style="width:858px;height: 610px; padding-left: 10px; padding-right: 10px;">
					<input
						type="hidden" id="choise_type" value="0" />
					<div class="row">
					<%-- <div id="search-form" class="col-xs-12"
							style="padding-bottom: 5px;">
							<div class="input-group" style="float:right;width:300px;">
								<input type="text" id="keyword"
									class="form-control search-query" placeholder="部门名称/公司名称" /><span
									class="input-group-btn">
									<button id="searchDeptBtn" type="button"
										class="btn btn-purple btn-sm no-border">
										<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
										搜索
									</button>
								</span>
							</div>
						</div> --%>
						<div class="col-xs-3" style="width: 60px">
							<div
								style="height: 410px;width:0px ;scroll; border: 0px solid #ddd;">
								<ul id="device_tree" class="ztree"></ul>
							</div>
						</div>
						<div class="col-xs-9" style="width: 750px ;">
							<table id="dept_list"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center">部门名称</th>
										<!-- <th class="center">所属机构</th> -->
										<th class="center">部门负责人</th>
										<th class="center">部门电话</th>
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
	<!-- 部门选择弹出窗结束 -->
	
	<!-- 角色选择窗开始 -->
	<div id="role_choise_modal" class="modal hiden fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:870px;height:610px;">
			<div class="modal-content">
				<div class="modal-header thicktitle">
					<a class="close" data-dismiss="modal" aria-hidden="true">×</a> <span
						class="modal-title" id="myModalLabel">选择角色</span>
				</div>
				<div class="modal-body thickcon"
					style="width:860px;height: 630px; padding-left:10px; padding-right: 15px;" >
					<input type="hidden" id="purchase_goods_rowno" /> <input
						type="hidden" id="choise_type" value="0" />
					<div class="row">
						<div class="col-xs-3" style="width: 60px">
							<div
								style="height: 410px;width:0px ;scroll; border: 0px solid #ddd;">
								<ul id="device_tree" class="ztree"></ul>
							</div>
						</div>
						<div class="col-xs-9" style="width: 750px ;" >
							<table id="search_goods_table"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center">
											<label class="position-relative">
												<input type="checkbox" class="ace" />
												<span class="lbl"></span>
											</label>
										</th>
										<th class="center">角色名称</th>
										<th class="center">角色描述</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>
					<div id="choise_goods_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
								<a id="choise_submit" class="btn btn-sm no-border btn-primary" onclick="batchAppendRole()">添加角色</a>
							</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 角色选择窗结束 -->
	
	<!-- page specific plugin scripts -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/biz/sys/sysUserAdd.js"></script>
	<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>
	<script src="${pageContext.request.contextPath}/js/lazyload-min.js"></script>
</body>
</html>

