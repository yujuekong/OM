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
						<li><a href="${pageContext.request.contextPath}/view/device/deviceInfoList.jsp">设备信息管理</a></li>
						<li class="active">新增设备信息</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<form action="../deviceInfo/saveOrUpdateDeviceInfo.action" method="post" >
							<div class="col-xs-12">
								<div class="form-group">
									<div class="col-xs-12">
										<div class="widget-box">
											<div class="widget-header widget-header-small">
												<h5 id="h5Id">新增设备信息</h5>
											</div>
											<div class="widget-body">
												<div class="widget-main">
												<div class="form-group" style="display:none"  >
		                                                <label class="col-sm-2 control-label no-padding-right">
		                                          		          设备ID：
		                                                </label>
															<span class="input-icon input-icon-right">
																<input readonly type="text" value="${deviceInfo.deviceId}"
		                                                               id="gt__type_number" class="required"
		                                                               style="width:450px" name="deviceInfo.deviceId"/>
															</span>
		                                            </div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															设备编号： 
														</label>
														<span class="input-icon input-icon-right">
															<input  disabled="disabled" type="text" id="deviceInfo.deviceNo" class="required" style="width:450px;" name="deviceInfo.deviceNo" value="${deviceInfo.deviceNo}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															设备名称： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.deviceName" class="required" style="width:450px;" name="deviceInfo.deviceName" value="${deviceInfo.deviceName}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															设备类型： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.deviceType.deviceTypeName" class="required" style="width:450px;"  value="${deviceInfo.deviceType.deviceTypeName}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															占地面积： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.deviceEara" class="required" style="width:450px;" name="deviceInfo.deviceEara" value="${deviceInfo.deviceEara}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															额定功耗： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.powerRating" class="required" style="width:450px;" name="deviceInfo.powerRating" value="${deviceInfo.powerRating}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															供电方式： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.eleMothod" class="required" style="width:450px;" name="deviceInfo.eleMothod" value="${deviceInfo.eleMothod}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															设备自重： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.deviceWeight" class="required" style="width:450px;" name="deviceInfo.deviceWeight" value="${deviceInfo.deviceWeight}"/>														
														</span>
													</div>	
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															显示屏： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.deviceLed" class="required" style="width:450px;" name="deviceInfo.deviceLed" value="${deviceInfo.deviceLed}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															触摸屏： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.deviceTouchScreen" class="required" style="width:450px;" name="deviceInfo.deviceTouchScreen" value="${deviceInfo.deviceTouchScreen}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															投币机： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.deviceSlot" class="required" style="width:450px;" name="deviceInfo.deviceSlot" value="${deviceInfo.deviceSlot}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															设备内温度： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.deviceTemperature" class="required" style="width:450px;" name="deviceInfo.deviceTemperature" value="${deviceInfo.deviceTemperature}"/>														
														</span>
													</div>	
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															环境温度： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.environTemperature" class="required" style="width:450px;" name="deviceInfo.environTemperature" value="${deviceInfo.environTemperature}"/>														
														</span>
													</div>	
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															联网通讯功能： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.isNetwork" class="required" style="width:450px;" name="deviceInfo.isNetwork" value="${deviceInfo.isNetwork}"/>														
														</span>
													</div>	
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															无人值守： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.isPerson" class="required" style="width:450px;" name="deviceInfo.isPerson" value="${deviceInfo.isPerson}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															存放地址： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.deviceAddress" class="required" style="width:450px;" name="deviceInfo.deviceAddress"  value="${deviceInfo.deviceAddress}"/>
														</span>
													</div>														
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															创建日期： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.createDate" class="required" style="width:150px;" name="deviceInfo.createDate" data-link-field="delive_time" value="${deviceInfo.createDate}"/>
															<i class="ace-icon fa fa-clock-o blue"></i>
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															地址经度： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.deviceLng" class="required" style="width:450px;" name="deviceInfo.deviceLng"  value="${deviceInfo.deviceLng}"/>
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															地址纬度： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.deviceLat" class="required" style="width:450px;" name="deviceInfo.deviceLat"  value="${deviceInfo.deviceLat}"/>
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															所属区域： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.dictRegionId" class="required" style="width:450px;" name="deviceInfo.dictRegionId"  value="${deviceInfo.region.dictName}"/>
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															所属省份： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.dictProviceId" class="required" style="width:450px;" name="deviceInfo.dictProviceId"  value="${deviceInfo.province.dictName}"/>
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															所属服务站： 
														</label>
														<span class="input-icon input-icon-right">
															<input disabled="disabled" type="text" id="deviceInfo.dictOrgId" class="required" style="width:450px;" name="deviceInfo.dictOrgId"  value="${deviceInfo.org.dictName}"/>
														</span>
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
	    </script>
	</body>
</html>



