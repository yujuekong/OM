<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,java.text.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
						<li><a href="#">系统管理</a></li>
						<li><a href="${pageContext.request.contextPath}/view/sys/contractList.jsp">合同信息管理</a></li>
						<li class="active">新增合同</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<form action="${pageContext.request.contextPath}/view/sys/contract/saveOrUpdateContract.action" method="post" onsubmit="return checkBlank();">
							<div class="col-xs-12">
								<div class="form-group">
									<div class="col-xs-12">
									<div class="widget-box">
										<div >
											<div class="widget-header widget-header-small">
												<h5 id="h5Id">新增合同信息</h5>
											</div>
											<div class="widget-body">
												<div class="widget-main">
													<div class="form-group" hidden="hidden">
		                                                <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
		                                          		          合同ID：
		                                                </label>
															<span class="input-icon input-icon-right">
																<input type="text" value="${sysContract.contractId}"
		                                                               id="deviceId" class="required"
		                                                               style="width:300px" name="sysContract.contractId"/>
															</span>
		                                            </div>	
		                                            <div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i>合同名称： </label>
														<span class="input-icon input-icon-right">
															<input type="text" id="sysContractName" class="required" style="width:300px;" name="sysContract.contractName" value="${sysContract.contractName}"/>														
														</span>
													</div>
														                                            
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i>合同类型：</label>
														<span class="input-icon input-icon-right">
															<select id="contractTypeSelect" class="required" style="width: 150px" name="sysContract.contractType" >
																<option value="-1">请选择</option>
																<option value="0" ${sysContract.contractType == 0 ?"selected= 'selected'" : ""}>供应商合同</option>
																<option value="1" ${sysContract.contractType == 1 ?"selected= 'selected'" : ""}>商圈合同</option>
																<option value="2" ${sysContract.contractType == 2 ?"selected= 'selected'" : ""}>广告合同</option>
															</select>														
														</span>
													</div>
													
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i>关联商家： </label>
														<span class="input-icon input-icon-right">
															<input type="hidden" id="idStr" name="sysContract.contractSeller" value="${sellerId }" />
															<input type="text" id="nameStr" class="required" value="${sellerName }" style="width:300px;" onclick="choiseSeller(this)"/>														
														</span>
													</div>
																										
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i>合同开始日期： </label>
														<span class="input-icon input-icon-right">
															<input type="text" id="contract_startDate" class="required" style="width:150px;" name="sysContract.startDate" value="${sysContract.startDate}"  data-link-field="delive_time"/><i class="ace-icon fa fa-clock-o blue"></i>														
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right"><i
														class="red">*</i>合同结束日期： </label>
														<span class="input-icon input-icon-right">
															<input type="text" id="contract_endDate" class="required" style="width:150px;" name="sysContract.endDate" value="${sysContract.endDate}"  data-link-field="delive_time"/><i class="ace-icon fa fa-clock-o blue"></i>														
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">&nbsp;合同费用： </label>
														<span class="input-icon input-icon-right">
															<input type="text" id="sysContract.contractFee" class="required" style="width:150px;" name="sysContract.contractFee" value="${sysContract.contractFee}"/>														
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right"><i class="red">*</i>费用支付状态： </label>
														<span class="input-icon input-icon-right">
															<select id="contractFeeSelect" class="required" style="width: 150px" name="sysContract.isPay" >
																<option value="-1">请选择</option>
																<option value="0" ${sysContract.isPay == 0 ?"selected= 'selected'" : ""}>已支付</option>
																<option value="1" ${sysContract.isPay == 1 ?"selected= 'selected'" : ""}>部分支付</option>
																<option value="2" ${sysContract.isPay == 2 ?"selected= 'selected'" : ""}>未支付</option>
															</select>														
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">&nbsp;费用标准： </label>
														<span class="input-icon input-icon-right">
															<input type="text" id="sysContract.feeStandard" class="required" style="width:130px;" name="sysContract.feeStandard" value="${sysContract.feeStandard}"/>														
														</span>
													</div>	
													<div class="form-group" hidden="hidden">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right"><i class="red">*</i>所属机构： </label>
														<span class="input-icon input-icon-right">
															<input type="text" id="sysContractOrg" class="required" style="width:300px;" name="sysContract.orgId" value="${sysContract.orgId}"/>														
															<input type="hidden"  id="provinceId" name="sysContract.dictProviceId"
				                                                   value="${sysContract.dictProviceId}"/>
				                                         <input type="hidden" id="regionId" name="sysContract.dictRegionId"
				                                                   value="${sysContract.dictRegionId}"/>
														</span>
													</div>	
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">&nbsp;折扣： </label>
														<span class="input-icon input-icon-right">
															<select id="disRateSelect" class="required" style="width: 150px" name="sysContract.disRate" >
																<option value="-1">请选择</option>
																<option value="9" ${sysContract.disRate == 9 ?"selected= 'selected'" : ""}>9</option>
																<option value="8" ${sysContract.disRate == 8 ?"selected= 'selected'" : ""}>8</option>
																<option value="7" ${sysContract.disRate == 7 ?"selected= 'selected'" : ""}>7</option>
																<option value="6" ${sysContract.disRate == 6 ?"selected= 'selected'" : ""}>6</option>
																<option value="5" ${sysContract.disRate == 5 ?"selected= 'selected'" : ""}>5</option>
															</select>															
														</span>
													</div>	
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right"><i class="red">*</i>支付方式： </label>
														<span class="input-icon input-icon-right">
															<select id="payMethodSelect" class="required" style="width: 150px" name="sysContract.payMethod" >
																<option value="-1">请选择</option>
																<option value="0" ${sysContract.payMethod == 0 ?"selected= 'selected'" : ""}>转账</option>
																<option value="1" ${sysContract.payMethod == 1 ?"selected= 'selected'" : ""}>现金</option>
															</select>														
														</span>
													</div>	
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right"><i class="red">*</i>支付周期： </label>
														<span class="input-icon input-icon-right">
															<select id="payCycleSelect" class="required" style="width: 150px" name="sysContract.payCycle" >
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

		<div id="gt_combobox" 
	         style=" display:none; position: absolute;border: 1px solid #ddd;background-color:#F0F0F0;overflow:auto">
	        <ul id="org_tree" class="ztree" style="margin-top:0; "></ul>
	    </div>
		
		<!-- basic scripts -->

		<!-- 广告内容选择窗开始 -->
	<div id="advert_choise_modal" class="modal hiden fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:870px;height:610px;">
			<div class="modal-content">
				<div class="modal-header thicktitle">
					<a class="close" data-dismiss="modal" aria-hidden="true">×</a> <span
						class="modal-title" id="myModalLabel">选择广告</span>
				</div>
				<div class="modal-body thickcon"
					style="width:860px;height: 630px; padding-left:10px; padding-right: 15px;" >
					<input type="hidden" id="purchase_goods_rowno" /> <input
						type="hidden" id="choise_type" value="0" />
					<div class="row">
						<div class="col-xs-3" style="width: 60px">
							<div
								style="height: 410px;width:0px ;scroll; border: 0px solid #ddd;">
								<ul id="device_tree" class="ztree"></ul>
							</div>
						</div>
						
						<div class="col-xs-9" style="width: 750px ;" >
							<table id="advert_list"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center">广告标题</th>
										<th class="center">广告主名称</th>
										<th class="center">广告主电话</th>
										<th class="center">广告开始日期</th>
										<th class="center">广告结束日期</th>
										<th class="center">创建日期</th>
										<th class="center">广告状态</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 广告内容选择窗结束 -->
	
	<!-- 商圈选择窗开始 -->
	<div id="service_choise_modal" class="modal hiden fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:870px;height:610px;">
			<div class="modal-content">
				<div class="modal-header thicktitle">
					<a class="close" data-dismiss="modal" aria-hidden="true">×</a> <span
						class="modal-title" id="myModalLabel">选择商圈</span>
				</div>
				<div class="modal-body thickcon"
					style="width:860px;height: 630px; padding-left:10px; padding-right: 15px;" >
					<input type="hidden" id="purchase_goods_rowno" /> <input
						type="hidden" id="choise_type" value="0" />
					<div class="row">
						<div class="col-xs-3" style="width: 60px">
							<div
								style="height: 410px;width:0px ;scroll; border: 0px solid #ddd;">
								<ul id="device_tree" class="ztree"></ul>
							</div>
						</div>
						
						<div class="col-xs-9" style="width: 750px ;" >
							<table id="service_list"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center">商圈编号</th>
										<th class="center">商圈名称</th>
										<th class="center">商圈地址</th>
										<th class="center">所属服务站</th>																						
										<th class="center">管理区域</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 商圈选择窗结束 -->
	
	<!-- 供应商选择窗开始 -->
	<div id="seller_choise_modal" class="modal hiden fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:870px;height:610px;">
			<div class="modal-content">
				<div class="modal-header thicktitle">
					<a class="close" data-dismiss="modal" aria-hidden="true">×</a> <span
						class="modal-title" id="myModalLabel">选择供应商</span>
				</div>
				<div class="modal-body thickcon"
					style="width:860px;height: 630px; padding-left:10px; padding-right: 15px;" >
					<input type="hidden" id="purchase_goods_rowno" /> <input
						type="hidden" id="choise_type" value="0" />
					<div class="row">
						<div class="col-xs-3" style="width: 60px">
							<div
								style="height: 410px;width:0px ;scroll; border: 0px solid #ddd;">
								<ul id="seller_tree" class="ztree"></ul>
							</div>
						</div>
						
						<div class="col-xs-9" style="width: 750px ;" >
							<table id="seller_list"
								class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center">供应商名称</th>
										<th class="center">供应商编号</th>
										<th class="center">联系人姓名</th>
										<th class="center">联系方式</th>
										<th class="center">创建日期</th>
										<th class="center">供应商状态</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 供应商选择窗结束 -->


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
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
   		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
		<script src="${pageContext.request.contextPath}/js/biz/sys/contractAdd.js"></script>
		
	</body>
</html>



