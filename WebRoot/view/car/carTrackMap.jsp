<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String  jsonData = (String)request.getAttribute("jsonData");%>
<!DOCTYPE html>
<html lang="en">
	<jsp:include page="../commons/head.jsp" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#allmap{width:100%;height:90%;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=wYELzyLpaRxWSOgD0gS9wGM3"></script>
	<title>添加多个标注点</title>
	<body class="no-skin">
		<div class="main-container" id="main-container">
			<div>
			<input type="hidden" id ='userId' value="${account.userCity}"/>
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>

					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="#">首页</a>
						</li>
						<li><a href="#">设备管理管理</a></li>
						<li class="active">设备地址</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>
				
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->
		<!-- 地图展示 -->
		<div id="allmap"></div>
		<button id="run" onclick= "daohang()">开始导航</button> 
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
		<script src="${pageContext.request.contextPath}/js/ycs.util.js" ></script>
 		<script src="${pageContext.request.contextPath}/js/biz/car/carTrackMap.js"></script>
		<script src="${pageContext.request.contextPath}/js/lushu.js"></script>
		
		<script type="text/javascript">
			var jsonData = '<%=jsonData%>';
			var om = '${pageContext.request.contextPath}';

		</script>
	</body>
</html>



