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
						<li><a href="#">设备管理</a></li>
						<li><a href="${pageContext.request.contextPath}/view/device/deviceTypeList.jsp">设备类型管理</a></li>
						<li class="active">设备类型信息详情</li>
					</ul><!-- /.breadcrumb -->
					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					
					<!--<div class="page-header">
						<h1>设备列表
						</h1>
					</div> /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<div class="form-group">
								<div class="col-xs-12">
									<div class="widget-box">
										<div class="widget-header widget-header-small">
											<h5>设备类型详情</h5>
										</div>
										<div class="widget-body">
											<div class="widget-main">
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														序号：
													</label>
													<span id="dp__type_num" class="input-icon input-icon-right">
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														设备类型编号：
													</label>
													<span id= "dp__type_id" class="input-icon input-icon-right">
																							
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														设备类型名称：
													</label>
													<span id="dp__type_name" class="input-icon input-icon-right">
													</span>
												</div>
												<div class="form-group">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
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
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
		<script type="text/javascript">
			$("#submenu-menu-device-info").addClass("active");
			$("#menu-device").addClass("active");
			$("#menu-device").addClass("open");
		</script>
			<script type="text/javascript">
				var url = location.search;
				var Request = new Object();
				if(url.indexOf("?")!=-1){
					var str = url.substr(1);
					var strs = str.split("&");
					for(var i =0;i<strs.length;i++){
						Request[strs[i ].split("=")[0]] = unescape(strs[ i].split("=")[1]);
					}
				}
				var typeNumber = decodeURI(request("typeNumber"));
				var typeId = decodeURI(request("typeId"));
				var typeName = decodeURI(request("typeName"));
				$("#dp__type_num").html(typeNumber);
				$("#dp__type_id").html(typeId);
				$("#dp__type_name").html(typeName);
				
				function request(paras){ 
				    var url = location.href;  
				    var paraString = url.substring(url.indexOf("?")+1,url.length).split("&");  
				    var paraObj = {}; 
				    for (var i=0; j=paraString[i]; i++){  
				        paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length);  
				    }  
				    var returnValue = paraObj[paras.toLowerCase()]; 
				    if(typeof(returnValue)=="undefined"){  
				        return "";  
				    }else{  
				        return returnValue;  
				    } 
				}

			</script>
	</body>
</html>



