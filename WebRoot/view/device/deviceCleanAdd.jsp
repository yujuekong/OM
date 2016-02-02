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
					<input id="orgStr" type="hidden" value="${account.orgId}"  />
					<input id="provinceStr" type="hidden"  value="${account.dictProviceId}"  />
					<input id="regionStr" type="hidden"  value="${account.dictRegionId}"  />
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
						<form action="../deviceClean/saveOrUpdateDeviceClean.action" method="post" onsubmit="return check();">
							<div class="col-xs-12">
								<div class="form-group">
									<div class="col-xs-12">
										<div class="widget-box">
											<div class="widget-header widget-header-small">
												<h5 id="h5Id">新增清洗设备</h5>
											</div>
											<div class="widget-body">
												<div class="widget-main">													
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															清洗设备： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="deviceIdStr" type="hidden" name="deviceClean.deviceId" value="${deviceClean.deviceId}"  />
															<input id="deviceNameStr" type="text"  value="${deviceClean.deviceName}"  class="required" style="width:300px;"  onclick="choiseDevice(this)"/>														
														</span>
														<span id= "deviceError" class="input-icon input-icon-right">
														</span>
													</div>
													<div class="form-group"  >
		                                                <label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
		                                          		          清洗用户：
		                                                </label>
														<span class="input-icon input-icon-right">
														<input id="userIdStr" type="hidden" name="deviceClean.cleanUserId" value="${deviceClean.cleanUserId}"  />
															<input  type="text"  id="userNameStr" class="required" style="width:300px" onclick="choiseUser(this)"/>
														</span>
														<span id= "userError" class="input-icon input-icon-right">
														</span>
		                                            </div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															要求完成时间： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="dc_cleanEndTiem" class="required" style="width:150px;" name="deviceClean.cleanEndTiem" data-link-field="delive_time" value="${deviceClean.cleanEndTiem}"/>
															<i class="ace-icon fa fa-clock-o blue"></i>
														</span>
														<span id= "cleanEndTimeError" class="input-icon input-icon-right">
														</span>
													</div>													
													<div class="form-group" style="display:none">
														<label class="col-sm-2 control-label no-padding-right">
															实际完成时间： 
														</label>
														<div class="col-xs-5 input-group input-group-sm" >
															<input name="deviceClean.finishTime"  id="dp_finishTime" disabled="disabled"  class="col-xs-10 required" type="text"  style="width:150px;"  value="${deviceClean.finishTime }" />																
														</div>
													</div>
													<div class="form-group" style="display:none">
														<label class="col-sm-2 control-label no-padding-right">
															清洗日期： 
														</label>
														<div class="col-xs-5 input-group input-group-sm">
															<input name="deviceClean.cleanDate"  id="cleanDate"  class="col-xs-10 required" type="text"  style="width:150px;"  value="${deviceClean.cleanDate }" />																
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															清洗原因： 
														</label>
														<div class="col-xs-5 input-group input-group-sm">
															<textarea name="deviceClean.cleanReason" maxlength="2000" id="cleanReason"  class="col-xs-10 required"   style="width:300px;"   /></textarea>																
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															备注： 
														</label>
														<div class="col-xs-5 input-group input-group-sm">
															<textarea name="deviceClean.remark"  id="remark" maxlength="2000" class="col-xs-10 required"   style="width:300px;"   /></textarea>																
														</div>
													</div>
													
												</div>
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
							</div>
						</form>
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->
		
		<!-- 设备选择弹出窗开始 -->
		<div id="goods_choise_modal" class="modal hiden fade" 
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:860px;">
				<div class="modal-content" >
					<div class="modal-header thicktitle">
						<a class="close" data-dismiss="modal" aria-hidden="true">×</a>
						<span class="modal-title" id="myModalLabel">选择设备</span>
					</div>
					<div class="modal-body thickcon" style="width:858px;height: 650px; padding-left: 10px; padding-right: 10px;">
						<input type="hidden" id="purchase_goods_rowno" />
						<input type="hidden" id="choise_type" value="0"/>
						<div class="row">
							<div class="col-xs-3">
								<div style="height: 410px;overflow-y: scroll; border: 1px solid #ddd;">
									<ul id="device_tree" class="ztree"></ul>
								</div>
							</div>
							<div class="col-xs-9">
								<table id="deviceInfo_list" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center">设备编号</th>
											<th class="center">设备名称</th>
											<th class="center">设备地址</th>	
										</tr>
									</thead>
									<tbody>
										
									</tbody>
								</table>
							</div>
						</div>
						<!-- <div id="choise_goods_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
							<a id="choise_submit" class="btn btn-sm no-border btn-primary disabled" onclick="batchAppendGoods()">加入设备</a>
						</div> -->
					</div>
				</div>
			</div>
		</div>
		
		<div id="user_choise_modal" class="modal hiden fade" 
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:860px;">
				<div class="modal-content" >
					<div class="modal-header thicktitle">
						<a class="close" data-dismiss="modal" aria-hidden="true">×</a>
						<span class="modal-title" id="myModalLabel">选择用户</span>
					</div>
					<div class="modal-body thickcon" style="width:858px;height: 650px; padding-left: 10px; padding-right: 10px;">
						<input type="hidden" id="purchase_goods_rowno" />
						<input type="hidden" id="choise_type" value="0"/>
						<div class="row">
							<div class="col-xs-3">
								<!-- <div style="height: 400px; overflow-y: scroll; border: 1px solid #ddd;">
									<ul id="user_tree" class="ztree"></ul>
								</div> -->
							</div>
							<div class="col-xs-12">
								<table id="user_list" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center">用户帐号</th>											
											<th class="center">用户姓名</th>
											<th class="center">联系电话</th>
										</tr>
									</thead>
									<tbody>
										
									</tbody>
								</table>
							</div>
						</div>
						<!-- <div id="choise_goods_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
							<a id="choise_submit" class="btn btn-sm no-border btn-primary disabled" onclick="batchAppendGoods()">加入用户</a>
						</div> -->
					</div>
				</div>
			</div>
		</div>
		
		
		
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
		<script src="${pageContext.request.contextPath}/js/biz/device/deviceCleanAdd.js"></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
		
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
				<script type="text/javascript">
		//检验
		function check() {
			if (checkDevice() && checkUser() && checkTime()) {
				return true;
			} else {
				return false;
			}
		}
		//验证维护设备不为空
		function checkDevice(){
			var district = $("#deviceNameStr").val();
			if (district == "") {
		    	$("#deviceError").html("<div></div><font color='red'>清洗设备不能为空!</font>");
				return false;
			} else {
				$("#deviceError").html("<div></div>");
				return true;
			}
		}
		//验证维护用户不为空
		function checkUser(){
			var district = $("#userNameStr").val();
			if (district == "") {
		    	$("#userError").html("<div></div><font color='red'>清洗用户不能为空!</font>");
				return false;
			} else {
				$("#userError").html("<div></div>");
				return true;
			}
		}
		//验证要求完成时间不为空
		function checkTime(){
			var district = $("#dc_cleanEndTiem").val();
			if (district == "") {
		    	$("#cleanEndTimeError").html("<div></div><font color='red'>要求完成时间不能为空!</font>");
				return false;
			} else {
				$("#cleanEndTimeError").html("<div></div>");
				return true;
			}
		}
	</script>
	</body>
</html>



