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
			<script type="text/javascript">
		        try {
		            ace.settings.check('main-container', 'fixed')
		        } catch (e) {
		        }
		    </script>
			<div>
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>

					<ul id="uiId" class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="#">首页</a>
						</li>
						<li><a href="#">系统管理</a></li>
						<li><a href="${pageContext.request.contextPath}/view/sys/memberList.jsp">会员管理</a></li>
						<li class="active">
						<c:if test="${view=='view'}">查看</c:if><c:if test="${memberId=='New'}">新增</c:if><c:if test="${view=='editor'}">修改</c:if>会员</li>
					</ul>

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<form action="../sysMemberInfo/saveMemberInfo.action" method="post" onsubmit="return check();" autocomplete="off">
							<input hidden="hidden" value="${memberInfo.memberId}" name="memberInfo.memberId" id="memberId">
							<div class="col-xs-12">
								<div class="form-group">
									<div class="col-xs-12">
										<div>
											<div class="widget-header widget-header-small">
												<h5 id="h5Id"><c:if test="${view=='view'}">查看</c:if><c:if test="${memberId=='New'}">新增</c:if><c:if test="${view=='editor'}">修改</c:if>会员</h5>
											</div>
											<div class="widget-body" style=" width:100%;height:100% ">
												<div class="widget-main"  style="float:left;width:50%"  >
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															会员编号： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="memberNo" class="required" style="width:300px;" name="memberInfo.memberNo" value="${memberInfo.memberNo}" readonly="readonly"/>														
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															会员姓名： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="memberName" class="required" style="width:300px;" name="memberInfo.memberName" value="${memberInfo.memberName}"/>														
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">
															会员性别：
														</label>
														<span class="input-icon input-icon-right">
															<s:radio list="#{1:'男',0:'女'}" name="memberInfo.memberSex" value="1"/>
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;" class="col-sm-2 control-label no-padding-right">
															会员等级 ： 
														</label>
														<span class="input-icon input-icon-right">
															<select style="width: 59px; " name="memberInfo.memberLevel">
																<option value="">-----</option>
																<option value="1" <c:if test="${memberInfo.memberLevel==1 }">selected="selected"</c:if>>1</option>
																<option value="2" <c:if test="${memberInfo.memberLevel==2 }">selected="selected"</c:if>>2</option>
																<option value="3"<c:if test="${memberInfo.memberLevel==3 }">selected="selected"</c:if>>3</option>
															</select>
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">
															会员年龄： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="memberAge" style="width:300px;" name="memberInfo.memberAge" value="${memberInfo.memberAge}"/>														
														</span>
														
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															会员电话： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="memberTel" style="width:300px;" name="memberInfo.memberTel" value="${memberInfo.memberTel}"/>														
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right">
															会员邮箱： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="memberMail" style="width:300px;" name="memberInfo.memberMail" value="${memberInfo.memberMail}"/>														
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															注册日期： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="regDate" class="required" style="width:150px;" name="memberInfo.regDate" data-link-field="delive_time" value="${memberInfo.regDate}"/>
															<i class="ace-icon fa fa-clock-o blue"></i>
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															绑定微信号： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="wxNo" class="required" style="width:300px;" name="memberInfo.wxNo" value="${memberInfo.wxNo}"/>														
														</span>
													</div>
													<div class="form-group">
														<label style="width:130px;"  class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															绑定支付宝号： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" id="payNo" class="required" style="width:300px;" name="memberInfo.payNo" value="${memberInfo.payNo}"/>														
														</span>
													</div>
												</div>
												<br />
											</div>
										</div>
									</div>
								</div>
								<!-- 表单操作按钮开始 -->
								<div class="row">
		                            <div id="form_action_btns" class="col-xs-12" style="text-align: center">
		                                <c:if test="${view!='view'}"><button id="save" class="btn btn-sm no-border btn-success">
		                                    <i class="ace-icon fa fa-floppy-o"></i>保存
		                                </button></c:if>
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
		<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		 <script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
		<script src="${pageContext.request.contextPath}/js/biz/device/deviceInfoAdd.js"></script>
		
		<script type="text/javascript">
		if("${memberInfo.memberSex}"=="1"){
			document.getElementsByName("memberInfo.memberSex")[0].checked=true;//默认选中男
		}else if("${memberInfo.memberSex}"=="0"){
			document.getElementsByName("memberInfo.memberSex")[1].checked=true;//默认选中女
		}
		
			$("#regDate").datetimepicker({
				minView: "month",
				format: 'yyyy-mm-dd',
			    language: 'zh-CN',
				weekStart: 1,
			    todayBtn:  1,
				autoclose: true,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0
			});
	
			function check() {
				if($("#memberName").val()==''){
					alert("会员姓名不能为空，请重新输入！");
					return false;
					
				}
				if($("#memberTel").val()==''){
					alert("会员电话不能为空，请重新输入！");
					return false;
					
				}
				if($("#regDate").val()==''){
					alert("注册日期不能为空，请重新输入！");
					return false;
					
				}
				if($("#wxNo").val()==''){
					alert("绑定微信号不能为空，请重新输入！");
					return false;
					
				}
				if($("#payNo").val()==''){
					alert("绑定支付宝号不能为空，请重新输入！");
					return false;
					
				}
			}
		</script>
	</body>
</html>



