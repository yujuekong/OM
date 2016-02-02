<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,java.text.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
	<jsp:include page="../commons/head.jsp" />
	<style type="text/css">
		#allmap {width: 100%;height: 1000px;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=wYELzyLpaRxWSOgD0gS9wGM3"></script>
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
						<li><a href="#">系统管理</a></li>
						<li><a href="${pageContext.request.contextPath}/view/sys/contractList.jsp">合同信息管理</a></li>
						<li class="active">合同详情</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<form action="${pageContext.request.contextPath}/view/sys/contract/saveOrUpdateContract.action" method="post">
							<div class="col-xs-12">
								<div class="form-group">
									<div class="col-xs-12">
										<div >
											<div class="widget-header widget-header-small">
												<h5 id="h5Id">合同信息</h5>
											</div>
											<div class="widget-body" style=" width:100%;height:100% ">
												<div class="widget-main"  style="float:left;width:50%"  >
													
		                                            <div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">合同名称： </label>
														<span class="input-icon input-icon-right">
															<input type="text" id="sysContract.contractName" class="required" style="width:300px;" name="sysContract.contractName" value="${sysContract.contractName}" readonly="readonly"/>														
														</span>
													</div>	                                            
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">合同类型：</label>
														<span class="input-icon input-icon-right">
															<select id="contractTypeSelect" class="required" style="width: 150px;" name="sysContract.contractType" disabled="disabled" >
																<option value="-1">请选择</option>
																<option value="0" ${sysContract.contractType == 0 ?"selected= 'selected'" : ""}>供应商合同</option>
																<option value="1" ${sysContract.contractType == 1 ?"selected= 'selected'" : ""}>商圈合同</option>
																<option value="2" ${sysContract.contractType == 2 ?"selected= 'selected'" : ""}>广告合同</option>
															</select>														
														</span>
													</div>		
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">合同商家： </label>
														<span class="input-icon input-icon-right">
															<input type="text" id="contract_startDate" class="required" style="width:150px;" name="sysContract.startDate" value="${sellerName }" readonly="readonly"/>														
														</span>
													</div>
																								
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">合同开始日期： </label>
														<span class="input-icon input-icon-right">
															<input type="text" id="contract_startDate" class="required" style="width:150px;" name="sysContract.startDate" value="${sysContract.startDate}" readonly="readonly"/>														
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">合同结束日期： </label>
														<span class="input-icon input-icon-right">
															<input type="text" id="contract_endDate" class="required" style="width:150px;" name="sysContract.endDate" value="${sysContract.endDate}" readonly="readonly"/>														
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">合同费用： </label>
														<span class="input-icon input-icon-right">
															<input type="text" id="sysContract.contractFee" class="required" style="width:150px;" name="sysContract.contractFee" value="${sysContract.contractFee}" readonly="readonly"/>														
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">费用支付状态： </label>
														<span class="input-icon input-icon-right">
															<select id="contractFeeSelect" class="required" style="width: 150px" name="sysContract.isPay" disabled="disabled">
																<option value="-1">请选择</option>
																<option value="0" ${sysContract.isPay == 0 ?"selected= 'selected'" : ""}>已支付</option>
																<option value="1" ${sysContract.isPay == 1 ?"selected= 'selected'" : ""}>部分支付</option>
																<option value="2" ${sysContract.isPay == 2 ?"selected= 'selected'" : ""}>未支付</option>
															</select>														
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">费用标准： </label>
														<span class="input-icon input-icon-right">
															<input type="text" id="sysContract.feeStandard" class="required" style="width:300px;" name="sysContract.feeStandard" value="${sysContract.feeStandard}" readonly="readonly"/>														
														</span>
													</div>	
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">折扣： </label>
														<span class="input-icon input-icon-right">
															<select id="disRateSelect" class="required" style="width: 150px" name="sysContract.disRate" disabled="disabled">
																<option value="-1">请选择</option>
																<option value="9" ${sysContract.disRate == 9 ?"selected= 'selected'" : ""}>9折</option>
																<option value="8" ${sysContract.disRate == 8 ?"selected= 'selected'" : ""}>8折</option>
																<option value="7" ${sysContract.disRate == 7 ?"selected= 'selected'" : ""}>7折</option>
																<option value="6" ${sysContract.disRate == 6 ?"selected= 'selected'" : ""}>6折</option>
																<option value="5" ${sysContract.disRate == 5 ?"selected= 'selected'" : ""}>5折</option>
															</select>															
														</span>
													</div>	
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">支付方式： </label>
														<span class="input-icon input-icon-right">
															<select id="payMethodSelect" class="required" style="width: 150px" name="sysContract.payMethod" disabled="disabled">
																<option value="-1">请选择</option>
																<option value="0" ${sysContract.payMethod == 0 ?"selected= 'selected'" : ""}>转账</option>
																<option value="1" ${sysContract.payMethod == 1 ?"selected= 'selected'" : ""}>现金</option>
															</select>														
														</span>
													</div>	
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">支付周期： </label>
														<span class="input-icon input-icon-right">
															<select id="payCycleSelect" class="required" style="width: 150px" name="sysContract.payCycle" disabled="disabled">
																<option value="-1">请选择</option>
																<option value="0" ${sysContract.payCycle == 0 ?"selected= 'selected'" : ""}>按年</option>
																<option value="1" ${sysContract.payCycle == 1 ?"selected= 'selected'" : ""}>按季</option>
																<option value="2" ${sysContract.payCycle == 2 ?"selected= 'selected'" : ""}>按月</option>
															</select>														
														</span>
													</div>	
												</div>
												<br />
												
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

		<div id="gt_combobox" 
	         style=" display:none; position: absolute;border: 1px solid #ddd;background-color:#F0F0F0;overflow:auto">
	        <ul id="org_tree" class="ztree" style="margin-top:0; "></ul>
	    </div>
		
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
		<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
		
	</body>
</html>



