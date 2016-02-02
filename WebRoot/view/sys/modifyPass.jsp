<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<jsp:include page="../commons/head.jsp" />	
	<script type="text/javascript">
		function modifyPass(){
			var oldPass = document.all("oldPass").value;	
			var newPass = document.all("newPass").value;	
			var confirmPass = document.all("confirmPass").value;				
			if(oldPass == ""){
				alert("旧密码不能为空！");
				document.all("oldPass").focus();
				return false;
			}			
			if(newPass == ""){
				alert("新密码不能为空！");
				document.all("newPass").focus();
				return false;
			}	
			if(newPass != confirmPass){
				alert("确认密码必须和新密码相同！");
				document.all("newPass").focus();
				return false;
			}
			if(newPass.length < 6){
				alert("密码长度至少为6位 ！");
				document.all("newPass").focus();
				return false;
			}
			return true;
		}
		
		function cancel() {
			//history.back(-1);
			document.all("oldPass").value = "";
			document.all("newPass").value = "";
			document.all("confirmPass").value = "";
		}	
		
		//旧密码正确性判断
		function checkOldPassword(){
			var password = $("#oldPassWord").val();
			if(password){
				$.ajax({
					url: ROOT_PATH + '/view/sys/sysUser/checkOldPassword.action',
					type:'POST',
					data: {
							"password":password
			        },
					dataType:'json',
					success:function(data){					
						if(data){
							$("#oldPassError").html("<div></div><font color='#00AA00'>密码正确!</font>");
						}
						else{
							$("#oldPassError").html("<div></div><font color='red'>密码错误，请重新输入!</font>");
							$("#oldPassWord").val("");
						}
					}
				});
			}
		}
	</script>
	<body class="no-skin">
		<div class="main-container" id="main-container">
			<div>
				<div class="breadcrumbs" id="breadcrumbs">
					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="#">首页</a>
						</li>
						<li><a href="#">系统</a></li>
						<li class="active">修改密码</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<form class="form-horizontal" role="passform" 
							action="${pageContext.request.contextPath}/view/sys/sysUser/modifyPass.action" method="post" onsubmit="return modifyPass()">
							<!-- 商家信息开始 -->
								<div class="form-group">
									<div class="col-xs-12">
										<div class="widget-box">
											<div class="widget-header">
												<h5>修改密码</h5>
											</div>
											<div class="widget-body">
												<div class="widget-main">
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right" for="form-field-2">&nbsp;旧密码 :</label>				
														<span class="input-icon input-icon-right">
															<input name="oldPass" type="password" id="oldPassWord" placeholder="请输入旧密码" onblur="checkOldPassword();"/>
														</span>
														<label id="oldPassError"></label>
													</div>																								
												</div>	
												<div class="widget-main">
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right" for="form-field-2">&nbsp;新密码 :</label>				
														<span class="input-icon input-icon-right">
															<input name="newPass" type="password" id="form-field-2" placeholder="请输入新密码" />
														</span>
													</div>																								
												</div>	
												<div class="widget-main">
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right" for="form-field-2">&nbsp;确认新密码: </label>				
														<span class="input-icon input-icon-right">
															<input name="confirmPass" type="password" id="form-field-2" placeholder="请输入新密码" />
														</span>
													</div>																								
												</div>	
											</div>
										</div>
									</div>
								</div>
								<!-- 商家信息结束 -->
								
								<!-- 表单操作按钮开始 -->
								<div class="row">
									<div class="col-xs-12" style="text-align: center">
										<button class="btn btn-sm btn-success">
											<i class="ace-icon fa fa-check"></i>提交</button>&nbsp;&nbsp;&nbsp;
										<a class="btn btn-white btn-info btn-bold" onclick="cancel()">
											<i class="ace-icon fa fa-times red2"></i>重置</a>
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

		<!-- inline scripts related to this page -->
		<script src="${pageContext.request.contextPath}/js/ycs.util.js" ></script>
		<%-- <script src="${pageContext.request.contextPath}/js/sys/userList.js" ></script> --%>
		<script type="text/javascript">
			$("#submenu-menu-sys-dict").addClass("active");
			$("#menu-sys").addClass("active");
			$("#menu-sys").addClass("open");
		</script>
	</body>
</html>



