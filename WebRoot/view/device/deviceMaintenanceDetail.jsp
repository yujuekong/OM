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

					<ul id="uiId" class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="#">首页</a>
						</li>
						<li><a href="#">设备管理</a></li>
						<li><a href="${pageContext.request.contextPath}/view/device/deviceMaintenanceList.jsp">设备维护管理</a></li>
						<li class="active">新增维护设备</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<form action="../deviceMain/saveOrUpdateDeviceMain.action" method="post" >
							<div class="col-xs-12">
								<div class="form-group">
									<div class="col-xs-12">
										<div class="widget-box">
											<div class="widget-header widget-header-small">
												<h5 id="h5Id">新增维护设备</h5>
											</div>
											<div class="widget-body">
												<div class="widget-main">
													<div class="form-group"  >
		                                                <label class="col-sm-2 control-label no-padding-right">
		                                          		          维护用户：
		                                                </label>
														<span >
														<input id="userIdStr" type="text"  disabled="disabled" value="${deviceMain.sysUser.realName}"  />
														</span>
		                                            </div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															维护设备： 
														</label>
														<span >
															<input id="deviceIdStr"  type="text" disabled="disabled" value="${deviceMain.deviceInfo.deviceName}"  />
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															要求完成时间： 
														</label>
														<div class="col-xs-5 input-group input-group-sm">
															<input   type="text" id="dp_expEndTime"  disabled="disabled" class="col-xs-10 required" type="text"  style="width:150px;"  value="${deviceMain.expEndTime }" />																
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															实际完成时间： 
														</label>
														<div class="col-xs-5 input-group input-group-sm" >
															<input   type="text" id="dp_finishTime" disabled="disabled"  class="col-xs-10 required" type="text"  style="width:150px;"  value="${deviceMain.finishTime }" />																
														</div>
													</div>
													<div class="form-group" style="display:none">
														<label class="col-sm-2 control-label no-padding-right">
															维护日期： 
														</label>
														<div class="col-xs-5 input-group input-group-sm">
															<input  type="text" id="dp_maintenanceDate" disabled="disabled"  class="col-xs-10 required" type="text"  style="width:150px;"  value="${deviceMain.maintenanceDate }" />																
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															维护状态： 
														</label>
														<div class="col-xs-5 input-group input-group-sm">
															<span class="input-icon input-icon-right">
																<input  type="text"  disabled="disabled" style="width: 150px"  value="${deviceMain.maintenanceStatus==1?"正常":"禁用"}"  />
															</span>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															故障原因： 
														</label>
														<div class="col-xs-5 input-group input-group-sm">
															<textarea   id="maintenanceReason" disabled="disabled" class="col-xs-10 required"   style="width:300px;"   >${deviceMain.maintenanceReason }</textarea>																
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															备注： 
														</label>
														<div class="col-xs-5 input-group input-group-sm">
															<textarea   id="remark" name="remark"   disabled="disabled"  style="width:300px;">${deviceMain.remark }</textarea>																
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->
		
		<!-- 商品选择弹出窗结束 -->
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
		<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.min.js"></script>
		
		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
		
	</body>
</html>



