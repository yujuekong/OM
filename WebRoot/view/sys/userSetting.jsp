<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,java.text.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../commons/head.jsp" />
<style type="text/css">
#preview{width:200px;height:200px;border:1px solid #000;overflow:hidden;}
#imghead {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
</style>
<body class="no-skin">
	<div class="main-container" id="main-container">
		<div>
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed');
					} catch (e) {
					}
				</script>

				<ul id="uiId" class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a>
					</li>
					<li><a href="#">首页</a></li>
					<li><a href="${pageContext.request.contextPath}/view/sys/userList.jsp">欢迎您,${account.realName }</a></li>
					<li class="active">个人资料</li>
				</ul>
				<!-- /.breadcrumb -->
				<div class="nav-search" id="nav-search">
					<a href="javascript:history.back()"> <i
						class="ace-icon fa fa-arrow-left"></i>返回
					</a>
				</div>
				<!-- /.nav-search -->
			</div>

			<div class="page-content">
				<div class="row">
					<form action="${pageContext.request.contextPath}/view/sys/sysUser/userSetting.action" id="userSetting"
							method="post"  enctype="multipart/form-data" onsubmit="return check();">
						<div class="col-xs-12">
							<div class="form-group">
								<div class="col-xs-12">
									<div class="widget-box">
										<div class="widget-main">
											<div>
												<div id="user-profile-1" class="user-profile row">
													<div class="col-xs-12 col-sm-3 center">
														<div>
															<span class="profile-picture">
																<div id="preview">
																	<img width="200px" height="200px" src="${pageContext.request.contextPath}${account.userImage}"/>
																</div>
																<br/>
																<div class="form-group" style="display:none">
                                          							<label class="col-sm-2 no-padding-right">图片路径：</label>
								                                        <span class="col-sm-2 input-icon input-icon-right">
								                                        <iframe>
								                                        	<img alt="nothing" src="${account.userImage}">
								                                        </iframe>
								                                        </span>
								                                </div>
                                        
															</span>
															<div class="space-4"></div>
																<input type="file" onchange="previewImage(this)" name="userFile"  />
														</div>
														<div class="space-6"></div>
													</div>
									
													<input type="hidden" id="gt__type_num" value="${account.userId}" name="sysUser.userId" class="required"  />
													<div class="col-xs-12 col-sm-9">
														<div class="space-12"></div>
														<div class="profile-user-info profile-user-info-striped">
															
															<div class="profile-info-row">
																<div class="profile-info-name"> 姓名  </div>
																	<span class="profile-info-value">
																		<input type="text" id="tName" value="${account.realName}" name="sysUser.realName" style="width:300px;" class="required"  />
																	</span>
															</div>
															<div class="profile-info-row">
																<div class="profile-info-name"> 性别  </div>
																	<span class="profile-info-value">
																		<select id="advertUserSex" name="sysUser.sex" name="sysUser.sex">
																			<option value="-1">请选择</option>
																			<option value="0" ${account.sex == "0" ?"selected= 'selected'" : ""}>男</option>
																			<option value="1" ${account.sex == "1" ?"selected= 'selected'" : ""}>女</option>
																			
																		</select>
																	</span>
															</div>
															<div class="profile-info-row">
																<div class="profile-info-name"> 邮箱   </div>
																	<span class="profile-info-value">
																		<input type="text" id="tEmail" value="${account.email}" name="sysUser.email" style="width:300px;" class="required"  />
																	</span>
															</div>
																
														</div>
													</div>
														<div class="row">
															<div id="form_action_btns" class="col-xs-12"
																style="text-align: center">
																<button type="submit" id="save" class="btn btn-sm no-border btn-success"
																	>
																	<i class="ace-icon fa fa-floppy-o"></i>保存
																</button>
																&nbsp;&nbsp;&nbsp; <a class="btn btn-sm no-border btn-default"
																	onclick="javascript:history.back()"> <i
																	class="ace-icon fa fa-times red2"></i>取消
																</a>
															</div>
														</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
				<!-- /.row -->
				</form>
				
			</div>
			<!-- /.page-content -->
		</div>
		<!-- /.main-content -->
	</div>
	<!-- /.main-container -->

	<!-- 机构选择树 -->
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
		window.jQuery|| document.write("<script src='${pageContext.request.contextPath}/js/jquery.min.js'>"
								+ "<" + "/script>");
	</script>
	<!-- page specific plugin scripts -->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>
	<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/biz/sys/sysUserAdd.js"></script>
	<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>
	<script src="${pageContext.request.contextPath}/js/lazyload-min.js"></script>
	
	<script type="text/javascript">
	function check(){
		var result = true;
		var tName = $("#tName").val();
		var tEmail = $("#tEmail").val();
		if(tName.length > 4){
			alert("用户名超过最大长度");
			result = false;
		}
		else if(tEmail.length > 40){
			alert("邮箱长度超过最大长度");
			result = false;
		}
		return result;
	}
	
	function previewImage(file)
	{
	  var MAXWIDTH  = 200;
	  var MAXHEIGHT = 200;
	  var div = document.getElementById('preview');
	  if (file.files && file.files[0])
	  {
	    div.innerHTML = '<img id=imghead>';
	    var img = document.getElementById('imghead');
	    img.onload = function(){
	      var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
	      img.width = rect.width;
	      img.height = rect.height;
	      img.style.marginLeft = rect.left+'px';
	      img.style.marginTop = rect.top+'px';
	    }
	    var reader = new FileReader();
	    reader.onload = function(evt){img.src = evt.target.result;}
	    reader.readAsDataURL(file.files[0]);
	  }
	  else
	  {
	    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
	    file.select();
	    var src = document.selection.createRange().text;
	    div.innerHTML = '<img id=imghead>';
	    var img = document.getElementById('imghead');
	    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
	    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
	    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
	    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;margin-left:"+rect.left+"px;"+sFilter+src+"\"'></div>";
	  }
	}
	function clacImgZoomParam( maxWidth, maxHeight, width, height ){
	    var param = {top:0, left:0, width:width, height:height};
	    if( width>maxWidth || height>maxHeight )
	    {
	        rateWidth = width / maxWidth;
	        rateHeight = height / maxHeight;
	        if( rateWidth > rateHeight )
	        {
	            param.width =  maxWidth;
	            param.height = Math.round(height / rateWidth);
	        }else
	        {
	            param.width = Math.round(width / rateHeight);
	            param.height = maxHeight;
	        }
	    }
	    param.left = Math.round((maxWidth - param.width) / 2);
	    param.top = Math.round((maxHeight - param.height) / 2);
	    return param;
	}
	</script>
</body>
</html>

