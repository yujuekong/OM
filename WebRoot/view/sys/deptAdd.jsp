
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.*,java.text.*"%>
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

				<ul id="uiId" class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a>
					</li>
					<li><a href="#">系统</a></li>
					<li><a
						href="${pageContext.request.contextPath}/view/sys/deptList.jsp">部门管理</a></li>
					<li class="active">新增部门</li>
				</ul>
				<!-- /.breadcrumb -->

				<div class="nav-search" id="nav-search">
					<a href="javascript:history.back()"> <i
						class="ace-icon fa fa-arrow-left"></i>返回
					</a>
				</div>
				<!-- /.nav-search -->
			</div>
			<input type="hidden" id="orgId" value="${account.orgId }"/>
			<input type="hidden" id="proviceId" value="${account.dictProviceId }" />
			<input type="hidden" id="regionId" value="${account.dictRegionId }" />
			<input type="hidden" id="accountId" value="${account.userId }"/>
			<div class="page-content">
				<div class="row">
					<form
						action="${pageContext.request.contextPath}/view/sys/sysDept/sysDeptAdd.action"
						method="post" onsubmit="return checkBlank();">
						<div class="col-xs-12">
							<div class="form-group">
								<div class="col-xs-12">
									<div class="widget-box">
										<div class="widget-header widget-header-small">
											<h5 id="h5Id">新增部门</h5>
										</div>
										<div class="widget-body">
											<div class="widget-main">

												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">
														部门ID: </label> <span class="input-icon input-icon-right">
														<input type="hidden" id="gt__type_num"
														value="${sysDept.deptId}" id="sysDept.deptId"
														class="required" style="width:450px;"
														name="sysDept.deptId" />
													</span>
												</div>

												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i> 部门名称： </label> <span
														class="input-icon input-icon-right"> <input
														id="sysDeptName" type="text" class="required"
														style="width:400px;" name="sysDept.deptName"
														value="${sysDept.deptName}" onblur="checkDeptName();"/>
													</span>
													<span id= "nameError" class="input-icon input-icon-right">
													</span>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">&nbsp;所属分公司： </label> <span class="input-icon input-icon-right">
														<input id="orgNameStr" type="text" class="required" style="width:400px;" value="${sysDict.dictName }" name="orgNameStr" onclick="showMenu()" />
														<input type="hidden" id="beforeShow" value="${beforeShow }"/>
														<input type="hidden" id="dictId" name="dictId" />
				                                         <input type="hidden"  id="dictPid" name="dictPid" 
				                                                   />
				                                         <input type="hidden" id="regionPid" name="regionId"
				                                                   "/> 
				                                          <input type="hidden" id="treeNode" name="treeNode"/>      
													</span>
												</div>
												<input type="hidden" name="sysDept.orgId" value="${sysDept.orgId }"/>
												<input type="hidden" name="sysDept.dictRegionId" value="${sysDept.dictRegionId }"/>
												<input type="hidden" name="sysDept.dictProviceId" value="${sysDept.dictProviceId }"/>

												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>部门负责人： </label> <span
														class="input-icon input-icon-right"><input
														id="userIdStr" type="hidden"
														name="sysDept.deptChief" value="${sysDept.deptChief }"/><input id="sysDeptChief"
														type="text" class="required" style="width:400px;" value="${sysUser.realName }"
														onclick="choiseDeptChief(this)" />
													</span>
												</div>

												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">&nbsp;部门电话： </label> <span
														class="input-icon input-icon-right"> <input
														id="sysDeptTel" type="text" class="col-xs-10 required"
														style="width:400px;" name="sysDept.deptTel"
														value="${sysDept.deptTel}" onblur="checkTel();"/>
													</span>
													<span id= "telError" class="input-icon input-icon-right">
													</span>
												</div>
												
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- 表单操作按钮开始 -->
							<div class="row">
								<div id="form_action_btns" class="col-xs-12"
									style="text-align: center">
									<button id="save" class="btn btn-sm no-border btn-success">
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
	<!-- 机构选择树 -->
		<div id="gt_combobox" 
	         style=" display:none; position: absolute;border: 1px solid #ddd;background-color:#F0F0F0;overflow:auto">
	        <ul id="org_tree" class="ztree" style="margin-top:0; "></ul>
	    </div>


	<!-- 用户选择窗开始 -->
	<div id="user_choise_modal" class="modal hiden fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:870px;height:610px;">
			<div class="modal-content">
				<div class="modal-header thicktitle">
					<a class="close" data-dismiss="modal" aria-hidden="true">×</a> <span
						class="modal-title" id="myModalLabel">选择用户</span>
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
							<table id="user_list"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center">用户姓名</th>
										<th class="center">联系电话</th>
										<th class="center">Email</th>
										<th class="center">用户状态</th>
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
	<!-- 用户选择窗结束 -->

	<!--[if !IE]> -->
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

	<!-- <![endif]-->

	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='${pageContext.request.contextPath}/js/jquery.min.js'>"
								+ "<"+"/script>");
	</script>

	<!-- page specific plugin scripts -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/ztree/js/jquery.ztree.core-3.5.min.js"></script>

	<!-- ace scripts -->
	<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script src="${pageContext.request.contextPath}/js/biz/sys/sysDeptAdd.js"></script>
	<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>

	<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
	<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
</body>
</html>



