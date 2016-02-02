<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>

<div id="navbar" class="navbar navbar-default">
	
	<script type="text/javascript">
		var ROOT_PATH = "${pageContext.request.contextPath}";
		try{ace.settings.check('navbar' , 'fixed')}catch(e){}
	</script>
	<div class="navbar-container" id="navbar-container">
		<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler">
			<span class="sr-only">Toggle sidebar</span>

			<span class="icon-bar"></span>

			<span class="icon-bar"></span>

			<span class="icon-bar"></span>
		</button>

		<div class="navbar-header pull-left">
			<a href="#" class="navbar-brand">
				<small>
					<img src="${pageContext.request.contextPath}/images/login/logo-last.png" width="35" height="25"
						alt="智慧生活运营管理平台" />
					智慧生活运营管理平台
				</small>
			</a>
		</div>
		
		<div class="navbar-buttons navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<li class="green">
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">
						<i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
						<span class="badge badge-success">${listCount }</span>
					</a>

					<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-header">
							<i class="ace-icon fa fa-envelope-o"></i>
							${listCount }条消息
						</li>

						<li class="dropdown-content">
							<ul class="dropdown-menu dropdown-navbar">
								<c:forEach var="pg" items="${resultList }" varStatus="rowIndex">
								<li>
									<a href="${pageContext.request.contextPath}/${pg.msgAddress}" target="mainFrame">
									<i class="ace-icon fa fa-envelope-o"></i>
										<span class="msg-body">
											<span class="msg-title">
												<span class="blue">${pg.msgCount }</span>
											</span>
										</span>
									</a>
								</li>
							</c:forEach>
							</ul>
						</li>

						<li class="dropdown-footer">
							
						</li>
					</ul>
				</li>

				<li class="light-blue">
					<a data-toggle="dropdown" href="#" class="dropdown-toggle">
						<img class="nav-user-photo" src="${pageContext.request.contextPath}${account.userImage}" alt="noImage" />
						<span class="user-info">
							<small>欢迎您,</small>
							${account.userName }
						</span>
						<i class="ace-icon fa fa-caret-down"></i>
					</a>
					<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<li><a href="${pageContext.request.contextPath}/view/sys/sysUser/userSet.action" target="mainFrame"
						><i class="ace-icon fa fa-exchange"></i>设置</a></li>
				<!-- 		<li><a href="#"><i class="ace-icon fa fa-users"></i>用户信息</a></li> -->
						<li>
							<a href="${pageContext.request.contextPath}/sys/user/exit.action">
								<i class="ace-icon fa fa-power-off"></i>退出
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div><!-- /.navbar-container -->
</div>
<body>
<script type="text/javascript">
function add(id){
	window.location.href = ROOT_PATH +"/view/car/carInfo/addOrModifyCarInfo.action?carId="+id;
}
</script>
</body>
</html>

