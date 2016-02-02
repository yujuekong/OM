<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>智慧生活运营管理平台</title>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/icon.png">
		<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
body {
	background: #fff;
}

.loginr p {
	padding: 0 0 20px;
}

.ui-input {
	width: 280px;
	float: left;
}

.codeinput {
	width: 180px;
}

.logspan {
	display: inline-block;
	width: 60px;
	height: 40pxp;
	line-height: 40px;
	font-size: 14px;
	float: left;
}

.code {
	width: 80px;
}

.loginbtn {
	margin-top: 30px;
}

.lbtn {
	margin-left: 60px;
	width: 292px;
}

.app_downs {
	margin-top: 15px;
	margin-left: 60px;
	width: 292px;
	height: 130px;
	overflow: hidden;
}

.app_downs p {
	line-height: 30px;
	height: 30px;
	font-size: 14px;
	text-align: center;
}

.down_android {
	float: left;
	width: 100px;
	height: 100px;
}

.down_ios {
	float: right;
	width: 100px;
	height: 100px;
}
</style>
	</head>
	<body>
		<div class="topbg">
			<div class="warp">
				<div class="logo">
					<img src="${pageContext.request.contextPath}/images/login/logo.jpg" width="405" height="98"
						alt="智慧生活运营管理平台" />
				</div>
				<div class="telephone">
					服务热线：027-87003631
				</div>
			</div>
		</div>

		<div class="warpbg">
			<div class="lmain">
				<div class="loginl">
					<img src="${pageContext.request.contextPath}/images/login/machine-new2.png" width="431" height="450"/>
				</div>
				<div class="loginr">
					<div class="mainlogin">
						<p>
							用户登录
						</p>
						<form name="loginForm" id="loginForm" action="${pageContext.request.contextPath}/sys/user/userLogin.action" method="post">
							<div class="controlinput">
								<span class="logspan">用户名：</span>
								<input type="text" maxlength="30" name="sysUser.userName" id="userName"
									class="ui-input" value="" />
							</div>
							<div class="controlinput">
								<span class="logspan">密&nbsp;&nbsp;&nbsp;码：</span>
								<input type="password" maxlength="30" name="sysUser.password"
									id="passWord" class="ui-input" value="" />
							</div>							
							<div class="controlinput">
								<span class="logspan">验证码：</span>
								<input type="text" maxlength="4" name="randCode" id="randCode" class="codeinput" value="" />	
								<span class="lspan">
								<img id="randCodeImg" title="看不清换一张" src="${pageContext.request.contextPath}/authocode/authCodeImage.jsp" style="width:100px; height:42px; float:right;cursor: pointer;">
								</span>							
							</div> 
							
							<c:if test="${! empty errors }">
							<div style="color: red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errors } &nbsp;</div>
							</c:if> 
							<div class="loginbtn">
								<input type="submit"  class="lbtn" id="submitLogin" name="submitLogin" value="登录" />
							</div>
						</form>
					</div>
					<div class="app_downs clearfix">
						<div class="down_android">
							<img src="${pageContext.request.contextPath}/images/login/android.png" width="100"
								height="100">
							<p>安卓手机下载</p>
						</div>
						<div class="down_ios">
							<img src="${pageContext.request.contextPath}/images/login/dbcode.png" width="100"
								height="100">
							<p>
								IOS手机下载
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" id="errormsg" name="errormsg" value="${errormsg}">
		<div class="botombg">
			<div class="warp">
				Copyright ©2000-2015  智慧生活网络科技有限公司  - 京ICP备15057190号 
			</div>
		</div>

	</body>
</html>