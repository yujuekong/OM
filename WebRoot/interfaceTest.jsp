<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.*,java.text.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="view/commons/head.jsp" />

<body class="no-skin">
	<div class="main-container" id="main-container">
		<div>
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>

				<ul id="uiId" class="breadcrumb">
					<li>接口测试 </li>
				</ul>

				<!-- /.nav-search -->
			</div>

			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group">
							<div class="col-xs-12">
								<div class="widget-box">
									<div class="widget-header widget-header-small">
										<h5 id="h5Id">接口测试</h5>
									</div>
									<div class="widget-body">
										<div class="widget-main">
											<div class="form-group" >
												<label class="col-sm-2 control-label no-padding-right"> 请求地址: </label>
												<input id="urlPath" type="text" class="required" style="width:1000px;" value="http://192.168.10.198/rest/rest?method=device.queryDeviceMaintenances" />
											</div>
											<div class="form-group" >
												<label class="col-sm-2 control-label no-padding-right"> 请求参数: </label>
												<input id="dataParam" type="text" class="required" style="width:1000px;" value="{'userId':'3925','orderDate':'2015-12-01','maintenanceStatus':'0','pageSize':'10','currentPage':'1'}" />
											</div>
											<div class="form-group" >
												<label class="col-sm-2 control-label no-padding-right"> 请求方式: </label>
												<label style="float:left;padding-left: 5px;"><input name="form-field-radio1" id="form-field-radio1" onclick="setType('POST');" type="radio" value="icon-edit" checked="checked"><span class="lbl">POST</span></label>
												<label style="float:left;padding-left: 15px;"><input name="form-field-radio1" id="form-field-radio2" onclick="setType('GET');" type="radio" value="icon-edit"><span class="lbl">GET</span></label>
											</div>
											<div style="margin-top: -5px;">
												&nbsp;&nbsp;<a class="btn btn-small btn-success" onclick="sendSever();">请求</a>
												&nbsp;&nbsp;<a class="btn btn-small btn-info" onclick="gReload();">重置</a>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label no-padding-right">&nbsp;返回内容： </label>
												<textarea rows="15px" cols="146px" maxlength="1990" wrap="soft" id="jsonContent" ></textarea>
											</div>																						
										</div>
									</div>
								</div>
							</div>
						</div>							
					</div>
				</div>
				<input type="hidden" name="S_TYPE" id="S_TYPE" value="POST"/>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->
	</div>
	<!-- /.main-container -->

	<!-- 广告主选择窗结束 -->

	<!--[if !IE]> -->
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<!-- <![endif]-->
	<!-- page specific plugin scripts -->
	<script type="text/javascript">
		//请求类型
		function setType(value){
			$("#S_TYPE").val(value);
		}
		function sendSever(){	
			var urlPath = 	$("#urlPath").val();	//请求地址
			var paraname = $("#dataParam").val();	//参数
			var S_TYPE = $("#S_TYPE").val();	//请求方式
			if($("#urlPath").val()==""){
				alert("请输入请求地址！");
				$("#urlPath").focus();
				return false;
			}
			var param = {"data":paraname};			
			var startTime = new Date().getTime(); //请求开始时间  毫秒
			$.ajax({
				type: "POST",
				url: "http://localhost:8080/OM/rest/rest?method=device.queryDeviceMaintenances&data="+paraname,
				dataType:'json',
				data: param, // 以json格式传递  
				cache: false,
				success: function(data){
					if(data.RESULT_CODE == 0){
						if(data.RESULT_DATA == ""){
							$("#jsonContent").val("返回数据为空");
						}
						else{
							$("#jsonContent").val(data);
						}							
					}
					else{
						alert("请求失败！")
					}					 			 
				}
			});
		}
		function gReload(){
			$("#urlPath").val('');
			$("#dataParam").val('');
			self.location.reload();
		}
	</script>
</body>
</html>



