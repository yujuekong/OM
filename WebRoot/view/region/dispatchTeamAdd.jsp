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
					<li><a href="#">配送管理</a></li>
					<li><a
						href="${pageContext.request.contextPath}/view/region/dispatchTeamList.jsp">配送小组管理</a></li>
					<li class="active">新增配送小组</li>
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
			<div class="page-content">
				<div class="row">
					<form
						action="${pageContext.request.contextPath}/view/region/dispatchTeam/saveOrUpdateDispatchTeam.action"
						method="post" enctype="multipart/form-data" onsubmit="return check();">
						<div class="col-xs-12">
							<div class="form-group">
								<div class="col-xs-12">
									<div class="widget-box">
										<div class="widget-header widget-header-small">
											<h5 id="h5Id">新增配送小组</h5>
										</div>
										<div class="widget-body">
											<div class="widget-main">

												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right">
														配送小组ID: </label> <span class="input-icon input-icon-right">
														<input type="text" id="gt__type_num"
														value="${dispatchingTeam.teamId}" id="advertInfoId"
														class="required" style="width:450px;"
														name="dispatchingTeam.teamId" />
													</span>
												</div>
												<div class="form-group"  style="display:none">
	                                                <label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
	                                          		          所属子公司： 
	                                                </label>
	                                                <span class="input-icon input-icon-right">
	                                                	<input  type="text" name="dispatchingTeam.dictOrgId" value="${account.orgId}"  />
														<input  type="text" name="dispatchingTeam.dictProviceId" value="${account.dictProviceId}"  />
														<input  type="text" name="dispatchingTeam.dictRegionId" value="${account.dictRegionId}"/>														
													</span>
												 </div>	
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i> 配送小组名称： </label> <span
														class="input-icon input-icon-right"> <input
														id="teamName" type="text" class="required"
														style="width:350px;" maxlength="20" name="dispatchingTeam.teamName"
														value="${dispatchingTeam.teamName}" />
													</span>
													<span id= "teamNameError" class="input-icon input-icon-right"></span>
												</div>
												<div class="form-group" hidden="hidden">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i> 配送小组编号： </label> <span
														class="input-icon input-icon-right"> <input
														 type="text" class="required"
														style="width:350px;" name="dispatchingTeam.teamNo"
														value="${dispatchingTeam.teamNo}" />
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i> 配送小组负责人： </label> <span
														class="input-icon input-icon-right"><input
														id="userIdStr" type="hidden"
														name="dispatchingTeam.mainUser" value="${dispatchingTeam.mainUser }" /> <input id="userNameStr"
														type="text" class="required" style="width:200px;"
														value="${mainUser.realName }" onclick="choiseMainUser(this)" />
													</span>
													<span id= "userIdStrError" class="input-icon input-icon-right"></span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i> 配送小组成员： </label> <span
														class="input-icon input-icon-right"><input
														id="userIdStr2" type="hidden"
														name="dispatchingTeam.otherUser" value="${dispatchingTeam.otherUser }" /> <input id="userNameStr2"
														type="text" class="required" style="width:200px;"
														value="${otherUser.realName }" onclick="choiseOtherUser(this)" />
													</span>
													<span id= "userIdStr2Error" class="input-icon input-icon-right"></span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i> 配送车辆： </label> <span
														class="input-icon input-icon-right"><input
														id="carIdStr" type="hidden"
														name="dispatchingTeam.carId" value="${dispatchingTeam.carId }" /> <input id="carNameStr"
														type="text" class="required" style="width:200px;"
														value="${carInfo.carNo }" onclick="choiseCar(this)" />
													</span>
													<span id= "carIdStrError" class="input-icon input-icon-right"></span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i> 配送线路： </label> <span
														class="input-icon input-icon-right"><input
														id="carLineIdStr" type="hidden"
														name="dispatchingTeam.carLineId" value="${dispatchingTeam.carLineId }" /> <input id="carLineNameStr"
														type="text" class="required" style="width:200px;"
														value="${carLine.lineName }" onclick="choiseCarLine(this)" />
													</span>
													<span id= "carLineIdStrError" class="input-icon input-icon-right"></span>
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
										href="${pageContext.request.contextPath}/view/region/dispatchTeamList.jsp"> <i
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

	<!-- 负责人选择窗开始 -->
	<div id="mainUser_choise_modal" class="modal hiden fade" tabindex="-1"
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
							<table id="mainUser_list"
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
	<!-- 负责人选择窗结束 -->
	
	<!-- 组员选择窗开始 -->
	<div id="otherUser_choise_modal" class="modal hiden fade" tabindex="-1"
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
							<table id="otherUser_list"
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
	<!-- 组员选择窗结束 -->
	
	<!-- 车辆选择窗开始 -->
	<div id="car_choise_modal" class="modal hiden fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:870px;height:610px;">
			<div class="modal-content">
				<div class="modal-header thicktitle">
					<a class="close" data-dismiss="modal" aria-hidden="true">×</a> <span
						class="modal-title" id="myModalLabel">选择车辆</span>
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
							<table id="car_list"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center">车牌号</th>
										<th class="center">车辆品牌</th>
										<th class="center">车辆类型</th>
										<th class="center">车厢尺寸</th>
										<th class="center">车辆状态</th>
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
	<!-- 车辆选择窗结束 -->
	
	<!-- 线路选择窗开始 -->
	<div id="carLine_choise_modal" class="modal hiden fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:870px;height:610px;">
			<div class="modal-content">
				<div class="modal-header thicktitle">
					<a class="close" data-dismiss="modal" aria-hidden="true">×</a> <span
						class="modal-title" id="myModalLabel">选择线路</span>
				</div>
				<div class="modal-body thickcon"
					style="width:860px;height: 630px; padding-left:10px; padding-right: 15px;" >
					<input type="hidden" id="purchase_goods_rowno" /> <input
						type="hidden" id="choise_type" value="0" />
					<div class="row">
						<div class="col-xs-3" style="width: 60px">
							<div style="height: 410px;width:0px ;scroll; border: 0px solid #ddd;">
								<ul id="device_tree" class="ztree"></ul>
							</div>
						</div>
						<div class="col-xs-9" style="width: 750px ;" >
							<table id="carLine_list"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center">线路编号</th>
										<th class="center">线路名称</th>
										<th class="center">线路类型</th>
										<th class="center">线路长度</th>
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
	<!-- 线路选择窗结束 -->
	
	
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
	<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>

	<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
	<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
	
	<script src="${pageContext.request.contextPath}/js/biz/region/dispatchTeamAdd.js"></script>
	<script type="text/javascript">
		 	function check() {
				if (checkTeamName() &&checkUser() && checkUser2() && checkCar() && checkCarLine() ) {
					return true;
				} else {
					return false;
				}
			} 
			//验证配送小组名称不为空
			function checkTeamName(){
				var num = $("#teamName").val();
			    if (num == "") {
			    	$("#teamNameError").html("<div></div><font color='red'>配送小组名称不能为空!</font>");
					return false;
				}else{
					$("#teamNameError").html("<div></div>");
					return true;
				}
			}
			//验证配送小组负责人不为空
			function checkUser(){
				var num = $("#userIdStr").val();
			    if (num == "") {
			    	$("#userIdStrError").html("<div></div><font color='red'>配送小组负责人不能为空!</font>");
					return false;
				}else{
					$("#userIdStrError").html("<div></div>");
					return true;
				}
			}
			//验证配送小组成员不为空
			function checkUser2(){
				var num = $("#userIdStr2").val();
			    if (num == "") {
			    	$("#userIdStr2Error").html("<div></div><font color='red'>配送小组成员不能为空!</font>");
					return false;
				}else{
					$("#userIdStr2Error").html("<div></div>");
					return true;
				}
			}
			//验证配送车辆不为空
			function checkCar(){
				var num = $("#carIdStr").val();
			    if (num == "") {
			    	$("#carIdStrError").html("<div></div><font color='red'>配送车辆不能为空!</font>");
					return false;
				}else{
					$("#carIdStrError").html("<div></div>");
					return true;
				}
			}
			//验证配送线路不为空
			function checkCarLine(){
				var num = $("#carLineIdStr").val();
			    if (num == "") {
			    	$("#carLineIdStrError").html("<div></div><font color='red'>配送线路不能为空!</font>");
					return false;
				}else{
					$("#carLineIdStrError").html("<div></div>");
					return true;
				}
			}
		</script>
</body>
</html>



