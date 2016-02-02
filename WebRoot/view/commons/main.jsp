<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">	
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/icon.png">
	<jsp:include page="head.jsp" />
	<body class="no-skin" >
		<jsp:include page="top.jsp" />		
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<div id="sidebar" class="sidebar                  responsive">
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
				</script>
				<jsp:include page="left_tree.jsp" />
			</div>
			
			<div class="main-content" style="height:100%;">
				<iframe name="mainFrame" id="mainFrame" frameborder="0" src="../../view/commons/test.jsp" style="margin:0 auto;width:100%;height:800px;"></iframe>
			</div>

			<jsp:include page="../commons/bottom.jsp" />

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div>

		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${pageContext.request.contextPath}/js/jquery.min.js'>"+"<"+"/script>");
		</script>
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.ui.touch-punch.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.easypiechart.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.sparkline.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/flot/jquery.flot.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/flot/jquery.flot.pie.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/flot/jquery.flot.resize.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>

	</body>
</html>


