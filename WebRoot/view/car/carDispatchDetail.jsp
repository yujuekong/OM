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
						<li><a href="#">车辆管理</a></li>
						<li><a href="${pageContext.request.contextPath}/view/car/carDispatchList.jsp">车辆调度管理</a></li>
						<li class="active">车辆调度信息详情</li>
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
							<div class="form-group">
								<div class="col-xs-12">
									<div class="widget-box">
										<div class="widget-header widget-header-small">
											<h5>车辆调度信息详情</h5>
										</div>
										<div class="widget-body">
											<div class="widget-main">
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														调度车辆： 
													</label>
													<span class="input-icon input-icon-right">
														<input type="text" id="car_id" class="required" style="width:450px;"  />														
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														开车司机： 
													</label>
													<span class="input-icon input-icon-right">
														<input type="text" id="car_driver" class="required" style="width:450px;"  />														
													</span>
												</div>
												
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														始发地： 
													</label>
													<span class="input-icon input-icon-right">
														<input type="text" id="start_station" class="required" style="width:450px;" name="purchase.deliveryTime" />														
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														目的地： 
													</label>
													<span class="input-icon input-icon-right">
														<input type="text" id="end_station" class="required" style="width:450px;"  />														
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														开始时间： 
													</label>
													<span class="input-icon input-icon-right">
														<input type="text" id="start_time" class="required" style="width:450px;"/>
														<i class="ace-icon fa fa-clock-o blue"></i>
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														结束时间： 
													</label>
													<span class="input-icon input-icon-right">
														<input type="text" id="end_time" class="required" style="width:450px;"/>
														<i class="ace-icon fa fa-clock-o blue"></i>
													</span>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right">
														当前位置： 
													</label>
													<span class="input-icon input-icon-right">
														<input type="text" id="now_station" class="required" style="width:450px;" name="purchase.deliveryTime" />														
													</span>
												</div>
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
		
		$("#car_id").attr("value",decodeURI(request("carId")));
		$("#car_driver").attr("value",decodeURI(request("carDriver")));
		$("#start_station").attr("value",decodeURI(request("startStation")));
		$("#end_station").attr("value",decodeURI(request("endStation")));
		$("#start_time").attr("value",decodeURI(request("startTime")));
		$("#end_time").attr("value",decodeURI(request("endTime")));
		$("#now_station").attr("value",decodeURI(request("nowStation")));
		

		
		
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



