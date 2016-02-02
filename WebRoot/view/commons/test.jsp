<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<jsp:include page="../commons/head.jsp" />
	
	<body  style="padding-left: -10px;padding-top: 1px;">
		<div class="main-container" id="main-container">
			<div style="height:1000px;">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>

					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="#">首页</a>
						</li>
					</ul><!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<%
							String strXML = "";

							strXML += "<graph caption='DataChart' xAxisName='Month' yAxisName='值' decimalPrecision='0' formatNumberScale='0'>";
							strXML += "<set name='1' value='462' color='AFD8F8'/>";
							strXML += "<set name='2' value='857' color='F6BD0F'/>";
							strXML += "<set name='3' value='671' color='8BBA00'/>";
							strXML += "<set name='4' value='494' color='FF8E46'/>";
							strXML += "<set name='5' value='761' color='008E8E'/>";
							strXML += "<set name='6' value='960' color='D64646'/>";
							strXML += "<set name='7' value='629' color='8E468E'/>";
							strXML += "<set name='8' value='622' color='588526'/>";
							strXML += "<set name='9' value='376' color='B3AA00'/>";
							strXML += "<set name='10' value='494' color='008ED6'/>";
							strXML += "<set name='11' value='761' color='9D080D'/>";
							strXML += "<set name='12' value='960' color='A186BE'/>";
							strXML += "</graph>";
							//Create the chart - Column 3D Chart with data from strXML variable using dataXML method
						%>
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="row">
								<div class="col-xs-12">
									<div class="center">
										<div style="float:left;">
											<table border="0" width="90%">
												<tr>
													<td><jsp:include
															page="../report/fusionChartsHTMLRenderer.jsp" flush="true">
															<jsp:param name="chartSWF" value="../../fusionCharts/Column2D.swf" />
															<jsp:param name="strURL" value="" />
															<jsp:param name="strXML" value="<%=strXML%>" />
															<jsp:param name="chartId" value="myNext" />
															<jsp:param name="chartWidth" value="1000" />
															<jsp:param name="chartHeight" value="500" />
															<jsp:param name="debugMode" value="false" />
														</jsp:include></td>
												</tr>
											</table>
										</div>
									</div>
								</div><!-- /.span -->
							</div><!-- /.row -->

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
		<script src="${pageContext.request.contextPath}/js/sys/userList.js" ></script>
		<script type="text/javascript">
			$("#submenu-menu-device-clean").addClass("active");
			$("#menu-device").addClass("active");
			$("#menu-device").addClass("open");
		</script>
	</body>
</html>



