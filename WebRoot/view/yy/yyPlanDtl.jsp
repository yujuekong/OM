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
						<li><a href="#">配送管理</a></li>
						<li><a href="#">配送计划管理</a></li>
						<li class="active">配送计划详情</li>
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
												<h5 id="h5Id">配送计划详情</h5>
											</div>
											<div class="widget-body">
												<div class="widget-main">
													<div class="form-group"  >
		                                                <label class="col-sm-2 control-label no-padding-right">
		                                          		          配送车辆： 
		                                                </label>
		                                                <span class="input-icon input-icon-right">
															<input id="yyLineIdStr" disabled="disabled" type="text"  name="carDispatch.yyLineId" value="${carDispatch.yyLineId}"  class="required" style="width:450px;"  onclick="choiseDevice(this)"/>														
														</span>
		                                            </div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															配送线路： 
														</label>
														<span class="input-icon input-icon-right">
															<span class="input-icon input-icon-right">
																<input id="userIdStr" disabled="disabled" type="text"  style="width:450px;"  value="${carDispatch.carYyLine.carLine.lineName}"  />
														</span>
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															配送司机： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="realName" type="text" disabled="disabled" name="carDispatch.dispatchDate" value="${carDispatch.carYyLine.sysUser.realName}"  class="required" style="width:450px;"  />														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															出库单编号： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="storageDeliveryOrder" disabled="disabled" type="text"   value="${carDispatch.storageDeliveryOrder.deliveryNo}"  class="required" style="width:450px;"  />														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															配送日期： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="dispatchDate" type="text" disabled="disabled" name="carDispatch.dispatchDate" value="${carDispatch.dispatchDate}"  class="required" style="width:450px;"  />														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															配送开始时间： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="startTime" type="text"  disabled="disabled" name="carDispatch.startTime" value="${carDispatch.startTime}"  class="required" style="width:450px;"  />														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															配送结束时间： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="endTime" type="text" disabled="disabled"  value="${carDispatch.endTime}" name="carDispatch.endTime"  class="required" style="width:450px;"  />														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															配送状态： 
														</label>
														<span class="input-icon input-icon-right">	
															<input id="dispatchStatus" disabled="disabled" type="text" 
															 value="${carDispatch.dispatchStatus==0?"在途":"完成"}" name="carDispatch.dispatchStatus"  class="required" style="width:450px;"  />														
														</span>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- 表单操作按钮开始 -->
							</div>
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
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js" ></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js" ></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
		
		
	</body>
</html>



