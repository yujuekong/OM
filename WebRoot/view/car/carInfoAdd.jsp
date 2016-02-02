<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
						<li><a href="#">车辆管理</a></li>
						<li><a href="${pageContext.request.contextPath}/view/car/carInfoList.jsp">车辆信息管理</a></li>
						<li class="active">新增车辆信息</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<form id="car"  action="../carInfo/saveOrUpdateCarInfo.action" method="post" class="form-horizontal" onsubmit="return check();">
									<div class="col-xs-12">
											<div class="widget-header widget-header-small">
												<h5 id="h5Id">新增车辆信息</h5>
											</div>
											<div class="widget-body" style=" width:100%;height:100% ">
												<div class="widget-main"  style="float:left;width:50%"  >
													<div class="form-group" style="display:none"  >
		                                                <label class="col-sm-2 control-label no-padding-right">
		                                          		          车辆ID：
		                                                </label>
															<span class="input-icon input-icon-right">
																<input readonly type="text" value="${carInfo.carId}"
		                                                               id="carId" class="required"
		                                                               style="width:300px" name="carInfo.carId"/>
															</span>
		                                            </div>
		                                            <div class="form-group" style="display:none"  >
		                                                <label class="col-sm-2 control-label no-padding-right">
		                                          		          车辆状态：
		                                                </label>
															<span class="input-icon input-icon-right">
																<input readonly type="text" value="${carInfo.carStatus}"
		                                                               id="carStatus" class="required"
		                                                               style="width:300px" name="carInfo.carStatus"/>
															</span>
		                                            </div>
		                                            <div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															所属分公司： 
														</label>
														<span class="input-icon input-icon-right">
															<c:if test="${account.dictName!='' && account.dictName!=null}">
				                                   				<c:if test="${carInfo.org.dictName=='' || carInfo.org.dictName==null}">
																	<input type="text" value="${account.dictName}"
				                                                    id="userPro" class="required" style="width:300px;" readonly="readonly"/>
				                                                    <input type="hidden" id="orgId" name="carInfo.dictOrgId"
				                                                    value="${account.orgId}"/>      
				                                            	    <input type="hidden"  id="provinceId" name="carInfo.dictProviceId"
				                                                    value="${account.dictProviceId}"/>
				                                            	    <input type="hidden" id="regionId" name="carInfo.dictRegionId"
				                                                    value="${account.dictRegionId}"/>
				                                                 </c:if>
				                                                 <c:if test="${carInfo.org.dictName!='' && carInfo.org.dictName!=null}">
				                                                 	<input type="text" value="${carInfo.org.dictName}"
				                                                    id="userPro" class="required" style="width:300px;" readonly="readonly"/>
						                                            <input type="hidden" id="orgId" name="carInfo.dictOrgId"
						                                                   value="${carInfo.dictOrgId}"/>       
						                                            <input type="hidden"  id="provinceId" name="carInfo.dictProviceId"
						                                                   value="${carInfo.dictProviceId}"/>
						                                            <input type="hidden" id="regionId" name="carInfo.dictRegionId"
						                                                   value="${carInfo.dictRegionId}"/>
				                                                 </c:if>
															</c:if>
															<c:if test="${account.dictName=='' || account.dictName==null}">
				                                   			<input type="text" value="${carInfo.org.dictName}"
				                                                   id="userPro" class="required" style="width:300px;"
				                                                   onfocus="showMenu()"/>
				                                            <input type="hidden" id="orgId" name="carInfo.dictOrgId"
				                                                   value="${carInfo.dictOrgId}"/>       
				                                            <input type="hidden"  id="provinceId" name="carInfo.dictProviceId"
				                                                   value="${carInfo.dictProviceId}"/>
				                                            <input type="hidden" id="regionId" name="carInfo.dictRegionId"
				                                                   value="${carInfo.dictRegionId}"/>
				                                            </c:if>
														</span>
														<span id= "orgError" class="input-icon input-icon-right">
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															车牌号： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="carNo" class="required" maxlength="10" style="width:300px;" name="carInfo.carNo" value="${carInfo.carNo}"/>														
														</span>
														<span id= "carNoError" class="input-icon input-icon-right">
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															车辆品牌： 
														</label>
														<span class="input-icon input-icon-right">
															<select id="carBrandSelect" class="required" style="width: 300px" name="carInfo.carBrand" >
																<option value="">请选择</option>
															</select>																
														</span>
														<span id= "carBrandError" class="input-icon input-icon-right">
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															车辆类型： 
														</label>
														<span class="input-icon input-icon-right">
															<select id="carTypeSelect" class="required" style="width: 300px" name="carInfo.carType" >
																	<option value="">请选择</option>
															</select>	
														</span>
														<span id= "carTypeError" class="input-icon input-icon-right">
														</span>
													</div>
													<div   class="form-group" style="display:none" >
														<label class="col-sm-2 control-label no-padding-right">
															车辆状态： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="carInfo.carStatus" class="required" style="width:300px;"  value="${carInfo.carStatus}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															发动机号码： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="carInfo.motorNo" class="required" maxlength="40" style="width:300px;" name="carInfo.motorNo" value="${carInfo.motorNo}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															车辆识别代码： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="carInfo.vehicleCode" class="required" maxlength="40" style="width:300px;" name="carInfo.vehicleCode" value="${carInfo.vehicleCode}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															载重大小： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="carInfo.carWeight" maxlength="10" class="required" style="width:300px;" name="carInfo.carWeight" value="${carInfo.carWeight}"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															车厢尺寸： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="carInfo.carSize" class="required" maxlength="20" style="width:300px;" name="carInfo.carSize" value="${carInfo.carSize}"/>														
														</span>
													</div>
													<div class="form-group" style="display:none">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">
															创建日期： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="createDate" name="carInfo.createDate" class="required" style="width:20px;" value="${carInfo.createDate}" data-link-field="delive_time" />
															<i class="ace-icon fa fa-clock-o blue"></i>
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															注册日期： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="regDate" class="required" style="width:200px;" name="carInfo.regDate" value="${carInfo.regDate}"/>														
															<i class="ace-icon fa fa-clock-o blue"></i>
														</span>
													</div>	
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															检验有效期： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="checkDate" class="required" style="width:200px;" name="carInfo.checkDate" value="${carInfo.checkDate}"/>														
															<i class="ace-icon fa fa-clock-o blue"></i>
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															备注： 
														</label>
														<span class="input-icon input-icon-right">
															<textarea  id="remark" class="required" style="width:300px;" maxlength="2000" name="carInfo.remark" >${carInfo.remark}</textarea>														
														</span>
													</div>
												</div>
											</div>
									</div>
								<!-- 表单操作按钮开始 -->
								<div class="row">
	                            <div id="form_action_btns" class="col-xs-12" style="text-align: center">
	                                <button type="submit" id="save" class="btn btn-sm no-border btn-success">
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
		<!--[if !IE]> -->
		<div id="gt_combobox" 
	         style=" display:none; position: absolute;border: 1px solid #ddd;background-color:#F0F0F0;overflow:auto">
	        <ul id="org_tree" class="ztree" style="margin-top:0; "></ul>
	    </div>
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
		<script src="${pageContext.request.contextPath}/js/biz/car/carInfoAdd.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
   		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>
   		
		<script type="text/javascript">
			function check() {
			
				if (checkOrg() && checkCarNo() && checkCarBrand() && checkCarType()) {
					return true;
				} else {
					return false;
				}
			}

			//验证服务站不为空
			function checkOrg(){
				var num = $("#userPro").val();
			    if (num == "") {
			    	$("#orgError").html("<div></div><font color='red'>所属服务站不能为空!</font>");
					return false;
				}else{
					$("#orgError").html("<div></div>");
					return true;
				}
			}//验证车辆号不为空
			function checkCarNo(){
				var num = $("#carNo").val();
			    if (num == "") {
			    	$("#carNoError").html("<div></div><font color='red'>车牌号不能为空!</font>");
					return false;
				}else{
					$("#carNoError").html("<div></div>");
					return true;
				}
			}
			//验证车辆品牌不为空
			function checkCarBrand(){
				var num = $("#carBrandSelect").val();
			    if (num == "") {
			    	$("#carBrandError").html("<div></div><font color='red'>车辆品牌不能为空!</font>");
					return false;
				}else{
					$("#carBrandError").html("<div></div>");
					return true;
				}
			}
			//验证车辆类型不为空
			function checkCarType(){
				var num = $("#carTypeSelect").val();
			    if (num == "") {
			    	$("#carTypeError").html("<div></div><font color='red'>车辆类型不能为空!</font>");
					return false;
				}else{
					$("#carTypeError").html("<div></div>");
					return true;
				}
			}
			var brandSelectId = "${carInfo.carBrand}";
				$.ajax({
			        type: "post",
			        url: ROOT_PATH +"/view/car/carInfo/queryBrandList.action",
			        data: {"carBrand" : brandSelectId},
			        success: function (msg) {
				        	var str = "";
				      	  	var json = eval("(" + msg + ")");
				      	  	for (var i=0;i<json.length;i++) {
				        		var str = "";
					        	if (json[i].dictId == brandSelectId) {
					        		str = "<option value='"+json[i].dictId+"' selected='selected'>"+json[i].dictName+"</option>";
						        } else {
						        	str = "<option value='"+json[i].dictId+"'>"+json[i].dictName+"</option>";
							    }
					            $("#carBrandSelect").append(str);
						    }
				      	  	
			        },
					error: function(e) {
						console.error("e: ",e);
					}
			    });
			    
			   var typeSelect = "${carInfo.carType}";
				$.ajax({
			        type: "post",
			        url: ROOT_PATH +"/view/car/carInfo/queryCarTypeList.action",
			        data: {"carType" : typeSelect},
			        success: function (msg) {
				        	var str = "";
				      	  	var json = eval("(" + msg + ")");
				      	  	for (var i=0;i<json.length;i++) {
				        		var str = "";
					        	if (json[i].dictId == typeSelect) {
					        		str = "<option value='"+json[i].dictId+"' selected='selected'>"+json[i].dictName+"</option>";
						        } else {
						        	str = "<option value='"+json[i].dictId+"'>"+json[i].dictName+"</option>";
							    }
					            $("#carTypeSelect").append(str);
						    }
			        },
					error: function(e) {
						console.error("e: ",e);
					}
			    });
	    </script>
	</body>
</html>



