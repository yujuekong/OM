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
						<li><a href="${pageContext.request.contextPath}/view/device/deviceCleanList.jsp">设备清洗管理</a></li>
						<li class="active">新增清洗设备</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<form action="../deviceClean/saveOrUpdateDeviceClean.action" method="post" >
							<div class="col-xs-12">
								<div class="form-group">
									<div class="col-xs-12">
										<div class="widget-box">
											<div class="widget-header widget-header-small">
												<h5 id="h5Id">新增清洗设备</h5>
											</div>
											<div class="widget-body">
												<div class="widget-main">
													<div class="form-group"  >
		                                                <label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
		                                          		          清洗用户：
		                                                </label>
														<span class="input-icon input-icon-right">
														<input id="userIdStr"  type= "text" disabled="disabled"  value="${deviceClean.sysUser.realName}"  />
														</span>
		                                            </div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															清洗设备： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="deviceIdStr" type= "text" disabled="disabled"   value="${deviceClean.deviceInfo.deviceName}"  />
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															要求完成时间： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="dc_cleanEndTiem" disabled="disabled" class="required" style="width:150px;"  data-link-field="delive_time" value="${deviceClean.cleanEndTiem}"/>
															<i class="ace-icon fa fa-clock-o blue"></i>
														</span>
													</div>
													
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															实际完成时间： 
														</label>
														<div class="col-xs-5 input-group input-group-sm" >
															<input   id="dp_finishTime" disabled="disabled"  class="col-xs-10 required" type="text"  style="width:150px;"  value="${deviceClean.finishTime }" />																
														</div>
													</div>
													<div class="form-group" style="display:none">
														<label class="col-sm-2 control-label no-padding-right">
															清洗日期： 
														</label>
														<div class="col-xs-5 input-group input-group-sm">
															<input disabled="disabled"  id="cleanDate"  class="col-xs-10 required" type="text"  style="width:150px;"  value="${deviceClean.cleanDate }" />																
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															清洗状态： 
														</label>
														<div class="col-xs-5 input-group input-group-sm">
															<span class="input-icon input-icon-right">
																<input  type="text"  disabled="disabled" style="width: 150px"  value="${deviceClean.cleanStatus==1?"正常":"禁用"}"  />
															</span>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															故障原因： 
														</label>
														<div class="col-xs-5 input-group input-group-sm">
															<textarea   id="cleanReason"  class="col-xs-10 required"   style="width:300px;"   >${deviceClean.cleanReason }</textarea>																
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															备注： 
														</label>
														<div class="col-xs-5 input-group input-group-sm">
															<textarea   id="remark"  class="col-xs-10 required"   style="width:300px;"   >${deviceClean.remark }</textarea>																
														</div>
													</div>
													
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- 表单操作按钮开始 -->
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
		<script src="${pageContext.request.contextPath}/plugins/ztree/js/jquery.ztree.core-3.5.min.js" ></script>
		
		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
		
		
	</body>
</html>



