<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,java.text.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
	<jsp:include page="../commons/head.jsp" />
	<style type="text/css">
		#allmap {width: 100%;height: 500px;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=wYELzyLpaRxWSOgD0gS9wGM3"></script>
	<body class="no-skin">
		<div class="main-container" id="main-container">
			<div>
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>
					<input type="hidden" id ='userId' value="${account.userCity}"/>
					<ul id="uiId" class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="#">首页</a>
						</li>
						<li><a href="#">运营管理</a></li>
						<li><a href="">商圈商家管理</a></li>
						<li class="active">新增商圈商家</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<form id="serviceSeller" action="../serviceInfo/saveOrUpdateServiceSeller.action" method="post" onsubmit="return check();" class="form-horizontal">
									<div class="col-xs-12">
											<div class="widget-header widget-header-small">
												<h5 id="h5Id">新增商圈商家</h5>
											</div>
											<div class="widget-body" style=" width:100%;height:100% ">
												<div class="widget-main"  style="float:left;width:50%"  >
													<div class="form-group" style="display:none"  >
		                                                <label style="width:130px;" class="col-sm-2 control-label no-padding-right">
		                                          		          商圈商家ID：
		                                                </label>
															<span class="input-icon input-icon-right">
																<input readonly type="text" value="${motionDistrict.districtId}"
		                                                               id="districtId" class="required"
		                                                               style="width:300px" name="motionDistrict.districtId"/>
															</span>
		                                            </div>
		                                            <div class="form-group" style="display:none" >
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															商圈编号： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="districtNo" class="required" style="width:300px;" name="motionDistrict.districtNo" value="${motionDistrict.districtNo}"/>														
														</span>
													</div>
		                                            <div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">
															所属分公司： 
														</label>
														<span class="input-icon input-icon-right">
				                                   			<c:if test="${account.dictName!='' && account.dictName!=null}">
				                                   				<c:if test="${motionDistrict.sysDict.dictName=='' || motionDistrict.sysDict.dictName==null}">
																	<input type="text" value="${account.dictName}"
				                                                    id="userPro" class="required" style="width:300px;" readonly="readonly"/>
				                                                    <input type="hidden" id="orgId" name="motionDistrict.dictOrgId"
				                                                    value="${account.orgId}"/>       
				                                            	    <input type="hidden"  id="provinceId" name="motionDistrict.dictProviceId"
				                                                    value="${account.dictProviceId}"/>
				                                            	    <input type="hidden" id="regionId" name="motionDistrict.dictRegionId"
				                                                    value="${account.dictRegionId}"/>
				                                                 </c:if>
				                                                 <c:if test="${motionDistrict.sysDict.dictName!='' && motionDistrict.sysDict.dictName!=null}">
				                                                 	<input type="text" value="${motionDistrict.sysDict.dictName}" readonly="readonly"
				                                                    id="userPro" class="required" style="width:300px;" />
						                                            <input type="hidden" id="orgId" name="motionDistrict.dictOrgId"
						                                                   value="${motionDistrict.dictOrgId}"/>       
						                                            <input type="hidden"  id="provinceId" name="motionDistrict.dictProviceId"
						                                                   value="${motionDistrict.dictProviceId}"/>
						                                            <input type="hidden" id="regionId" name="motionDistrict.dictRegionId"
						                                                   value="${motionDistrict.dictRegionId}"/>
				                                                 </c:if>
															</c:if>
															<c:if test="${account.dictName=='' || account.dictName==null}">
				                                   			<input type="text"  value="${motionDistrict.sysDict.dictName}"
				                                                   id="userPro" class="required" style="width:300px;"
				                                                   onfocus="showMenu()"/>
				                                            <input type="hidden" id="orgId" name="motionDistrict.dictOrgId"
				                                                   value="${motionDistrict.dictOrgId}"/>       
				                                            <input type="hidden"  id="provinceId" name="motionDistrict.dictProviceId"
				                                                   value="${motionDistrict.dictProviceId}"/>
				                                            <input type="hidden" id="regionId" name="motionDistrict.dictRegionId"
				                                                   value="${motionDistrict.dictRegionId}"/>
				                                            </c:if>       
				                                        </span>
				                                        <span id= "orgError" class="input-icon input-icon-right">
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															商圈名称： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" maxlength="20" id="districtName" class="required" style="width:300px;" name="motionDistrict.districtName" value="${motionDistrict.districtName}"/>														
														</span>
														<span id= "districtNameError" class="input-icon input-icon-right">
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															商圈地址： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" maxlength="20" id="districtAddress" class="required" style="width:300px;" name="motionDistrict.districtAddress" value="${motionDistrict.districtAddress}"/>														
														</span>
														<span id= "districtAddressError" class="input-icon input-icon-right">
														</span>
													</div>
													
													<div class="form-group">
														<label style="width:130px;"  maxlength="20" class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															联系人： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="linkMan" class="required" style="width:300px;" name="motionDistrict.linkMan" value="${motionDistrict.linkMan}"/>														
														</span>
														<span id= "linkManError" class="input-icon input-icon-right">
														</span>
													
													</div>
													
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">
															联络人电话： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" maxlength="20" id="linkTel" class="required" style="width:300px;" name="motionDistrict.linkTel" value="${motionDistrict.linkTel}"/>														
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">
															联系人邮件： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" maxlength="20" id="linkMail" class="required" style="width:300px;" name="motionDistrict.linkMail" value="${motionDistrict.linkMail}"/>														
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">
															商圈管理区： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="districtManager" maxlength="50" class="required" style="width:300px;" name="motionDistrict.districtManager" value="${motionDistrict.districtManager}"/>														
														</span>
													</div>
													<div class="form-group">
														<label  style="width:130px;" class="col-sm-2 control-label no-padding-right">
															商圈经度： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="districtLng" class="required" style="width:300px;" maxlength="20" name="motionDistrict.districtLng"  value="${motionDistrict.districtLng}"/>
															<input type="button" value="加载地图" onclick="btn()"/>
														
														</span>
														<span id= "districtLngError" class="input-icon input-icon-right">
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">
															商圈纬度： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="districtLat" class="required" maxlength="20" style="width:300px;" name="motionDistrict.districtLat"  value="${motionDistrict.districtLat}"/>
														</span>
														<span id= "districtLatError" class="input-icon input-icon-right">
														</span>
													</div>
												</div>
												<br />
												<div style="float:right;width:50%;">
													<div id="allmap" ></div>
												</div>
											
											</div>
									</div>
								<!-- 表单操作按钮开始 -->
								<div class="row">
	                            <div id="form_action_btns" class="col-xs-12" style="text-align: center">
	                                <button id="save" type="submit" class="btn btn-sm no-border btn-success">
	                                    <i class="ace-icon fa fa-floppy-o"></i>保存
	                                </button>
	                                &nbsp;&nbsp;&nbsp;
	                                <a class="btn btn-sm no-border btn-default" onclick="javascript:history.back()">
	                                    <i class="ace-icon fa fa-times red2"></i>取消</a>
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
		<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
		<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" ></link>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
		<script src="${pageContext.request.contextPath}/js/biz/region/serviceSellerAdd.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
   		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript">
		 	function check() {
				if (checkCarLineOrg &&checkDistrictName() && checkDistrictAddress() &&checkLinkMan() && checkDistrictLng() && checkDistrictLat() ) {
					return true;
				} else {
					return false;
				}
			} 
			//验证线路所属分公司不为空
			function checkCarLineOrg(){
				var num = $("#userPro").val();
			    if (num == "") {
			    	$("#orgError").html("<div></div><font color='red'>所属服务站不能为空!</font>");
					return false;
				}else{
					$("#orgError").html("<div></div>");
					return true;
				}
			}
			//验证线路名称不为空
			function checkDistrictName(){
				var num = $("#districtName").val();
			    if (num == "") {
			    	$("#districtNameError").html("<div></div><font color='red'>商圈名称不能为空!</font>");
					return false;
				}else{
					$("#districtNameError").html("<div></div>");
					return true;
				}
			}
			//验证线路类型不为空
			function checkDistrictAddress(){
				var num = $("#districtAddress").val();
			    if (num == "") {
			    	$("#districtAddressError").html("<div></div><font color='red'>商圈地址不能为空!</font>");
					return false;
				}else{
					$("#districtAddressError").html("<div></div>");
					return true;
				}
			}
			//验证维护人员不为空
			function checkLinkMan(){
				var num = $("#linkMan").val();
			    if (num == "") {
			    	$("#linkManError").html("<div></div><font color='red'>联系人不能为空!</font>");
					return false;
				}else{
					$("#linkManError").html("<div></div>");
					return true;
				}
			}
			//验证经度不为空
			function checkDistrictLng(){
				var num = $("#districtLng").val();
			    if (num == "") {
			    	$("#districtLngError").html("<div></div><font color='red'>商圈经度不能为空!</font>");
					return false;
				}else{
					$("#districtLngError").html("<div></div>");
					return true;
				}
			}
			//验证纬度人员不为空
			function checkDistrictLat(){
				var num = $("#districtLat").val();
			    if (num == "") {
			    	$("#districtLatError").html("<div></div><font color='red'>商圈纬度不能为空!</font>");
					return false;
				}else{
					$("#districtLatError").html("<div></div>");
					return true;
				}
			}
		</script>
		
	</body>
</html>



