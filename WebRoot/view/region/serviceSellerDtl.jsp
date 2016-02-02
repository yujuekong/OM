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
						<li><a href="#">运营管理</a></li>
						<li><a href="#">商圈商家管理</a></li>
						<li class="active">商圈商家信息详情</li>
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
												<h5 id="h5Id">商圈商家信息详情</h5>
											</div>
											<div class="widget-body">
												<div class="widget-main">
													<div class="form-group"  >
		                                                <label class="col-sm-2 control-label no-padding-right">
		                                          		         所属服务站：
		                                                </label>
														<span class="input-icon input-icon-right">
														<input   type= "text" disabled="disabled"  style="width:300px;"
																value="${motionDistrict.sysDict.dictName}"  />
														</span>
		                                            </div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															商圈编号： 
														</label>
														<span class="input-icon input-icon-right">
															<input   type= "text" disabled="disabled"  style="width:300px;" 
																	value="${motionDistrict.districtNo}"  />
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															商圈名称： 
														</label>
														<span class="input-icon input-icon-right">
															<input 	type="text"  disabled="disabled"
																	class="required" style="width:300px;"   
																	value="${motionDistrict.districtName}"/>
														</span>
													</div>
													
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															商圈地址： 
														</label>
														<div class="col-xs-5 input-group input-group-sm" >
															<input    disabled="disabled"  
															class="col-xs-10 required" type="text"  
															style="width:300px;"  
															value="${motionDistrict.districtAddress }" />																
														</div>
													</div>
													<div class="form-group" style="display:none">
														<label class="col-sm-2 control-label no-padding-right">
															商圈联系人： 
														</label>
														<div class="col-xs-5 input-group input-group-sm">
															<input disabled="disabled"    
															class="col-xs-10 required" type="text"  
															style="width:300px;"  value="${motionDistrict.linkMan }" />																
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															联系人电话： 
														</label>
														<div class="col-xs-5 input-group input-group-sm">
															<span class="input-icon input-icon-right">
																<input  type="text"  disabled="disabled" 
																style="width: 300px"  
																value="${motionDistrict.linkTel}"  />
															</span>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															联系人邮件： 
														</label>
														<div class="col-xs-5 input-group input-group-sm">
															<span class="input-icon input-icon-right">
																<input  type="text"  disabled="disabled" 
																style="width: 300px"  
																value="${motionDistrict.linkMail}"  />
															</span>															
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															商圈管理区： 
														</label>
														<div class="col-xs-5 input-group input-group-sm">
															<span class="input-icon input-icon-right">
																<input  type="text"  disabled="disabled" 
																style="width: 300px"  
																value="${motionDistrict.districtManager}"  />
															</span>																
														</div>
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
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
		
		
	</body>
</html>



