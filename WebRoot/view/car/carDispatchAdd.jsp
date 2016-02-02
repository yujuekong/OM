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
						<li><a href="#">车辆管理</a></li>
						<li><a href="${pageContext.request.contextPath}/view/car/carInfoList.jsp">车辆信息管理</a></li>
						<li class="active">新增调度车辆信息</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<form action="../carInfo/saveOrUpdateCarInfo.action" method="post" >
							<div class="col-xs-12">
								<div class="form-group">
									<div class="col-xs-12">
										<div class="widget-box">
											<div class="widget-header widget-header-small">
												<h5 id="h5Id">新增调度车辆信息</h5>
											</div>
											<div class="widget-body">
												<div class="widget-main">
												<div class="form-group" style="display:none"  >
		                                                <label class="col-sm-2 control-label no-padding-right">
		                                          		          车辆ID：
		                                                </label>
															<span class="input-icon input-icon-right">
																<input readonly type="text" value="${carInfo.carId}"
		                                                               id="gt__type_number" class="required"
		                                                               style="width:450px" name="carInfo.carId"/>
															</span>
		                                            </div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															车牌号： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="carInfo.carNo" class="required" style="width:450px;" name="carInfo.carNo" value="${carInfo.carNo}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															车辆品牌： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="carInfo.carBrand" class="required" style="width:450px;" name="carInfo.carBrand" value="${carInfo.carBrand}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															车辆类型： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="carInfo.carType" class="required" style="width:450px;" name="carInfo.carType" value="${carInfo.carType}"/>														
														</span>
													</div>
														<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															发动机号码： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="carInfo.motorNo" class="required" style="width:450px;" name="carInfo.motorNo" value="${carInfo.motorNo}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															车辆识别代码： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="carInfo.vehicleCode" class="required" style="width:450px;" name="carInfo.vehicleCode" value="${carInfo.vehicleCode}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															载重大小： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="carInfo.carWeight" class="required" style="width:450px;" name="carInfo.carWeight" value="${carInfo.carWeight}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															车厢尺寸： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="carInfo.carSize" class="required" style="width:450px;" name="carInfo.carSize" value="${carInfo.carSize}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															注册日期： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="carInfo.regDate" class="required" style="width:450px;" name="carInfo.regDate" value="${carInfo.regDate}"/>														
														</span>
													</div>	
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															检验有效期： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="carInfo.checkDate" class="required" style="width:450px;" name="carInfo.checkDate" value="${carInfo.checkDate}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															备注： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="carInfo.remark" class="required" style="width:450px;" name="carInfo.remark" value="${carInfo.remark}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															创建日期： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="carInfo.createDate" class="required" style="width:450px;" name="carInfo.createDate" value="${carInfo.createDate}"/>														
														</span>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- 表单操作按钮开始 -->
								<div class="row">
	                            <div id="form_action_btns" class="col-xs-12" style="text-align: center">
	                                <button id="save" class="btn btn-sm no-border btn-success">
	                                    <i class="ace-icon fa fa-floppy-o"></i>保存
	                                </button>
	                                &nbsp;&nbsp;&nbsp;
	                                <a class="btn btn-sm no-border btn-default" onclick="javascript:history.back()">
	                                    <i class="ace-icon fa fa-times red2"></i>取消</a>
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
	</body>
</html>



