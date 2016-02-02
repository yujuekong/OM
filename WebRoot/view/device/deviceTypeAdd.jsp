<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../commons/head.jsp"/>

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

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">首页</a>
                </li>
                <li><a href="#">设备管理</a></li>
                <li><a href="#">设备类型管理</a></li>
                <li class="active">新增设备类型</li>
            </ul>
            <!-- /.breadcrumb -->

            <div class="nav-search" id="nav-search">
                <a href="javascript:history.back()">
                    <i class="ace-icon fa fa-arrow-left"></i>返回
                </a>
            </div>
            <!-- /.nav-search -->
        </div>

        <div class="page-content">

            <!--<div class="page-header">
                <h1>设备列表
                </h1>
            </div> /.page-header -->

            <div class="row">
                <form action="../deviceType/saveOrUpdateDeviceType.action" id="goods" method="post" class="form-horizontal" onsubmit="return check();">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <div class="col-xs-12">
                                <div class="widget-box">
                                    <div class="widget-header widget-header-small">
                                        <h5>新增设备类型</h5>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <div class="form-group" style="display:none"  >
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    设备类型ID：
                                                </label>
													<span class="input-icon input-icon-right">
														<input readonly type="text" value="${deviceType.deviceTypeId}"
                                                               id="gt__type_number" class="required"
                                                               style="width:250px" name="deviceType.deviceTypeId"/>
													</span>
                                            </div>
                                            <div class="form-group" style="display:none"  >
                                                <label class="col-sm-2 control-label no-padding-right">
                                                    设备类型编号：
                                                </label>
													<span class="input-icon input-icon-right">
														<input readonly type="text" value="${deviceType.deviceTypeNo}"
                                                               id="gt__type_no" class="required"
                                                               style="width:250px" name="deviceType.deviceTypeNo"/>
													</span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 no-padding-right">
                                                    设备类型名称：
                                                </label>
													<span class="input-icon input-icon-right">
														<input type="text" maxlength="20"
                                                               value="${deviceType.deviceTypeName}" id="gt__type_name"
                                                               class="required"
                                                               style="width:250px;" name="deviceType.deviceTypeName"/>
													</span>
													<span id= "nameError" class="input-icon input-icon-right">
													</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 表单操作按钮开始 -->
                        <div class="row">
                            <div id="form_action_btns" class="col-xs-12" style="text-align: center">
                                <button type="submit" id="save" class="btn btn-sm no-border btn-success" >
                                    <i class="ace-icon fa fa-floppy-o"></i>保存
                                </button>
                                &nbsp;&nbsp;&nbsp;
                                <a class="btn btn-sm no-border btn-default" onclick="javascript:history.back()">
                                    <i class="ace-icon fa fa-times red2"></i>取消</a>
                            </div>
                        </div>
                        </div>
                </form>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.page-content -->
    </div>
    <!-- /.main-content -->
</div>
<!-- /.main-container -->
<!-- basic scripts -->

<!--[if !IE]> -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='${pageContext.request.contextPath}/js/jquery.min.js'>" + "<" + "/script>");
</script>

<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.dataTables.bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>

<script src="${pageContext.request.contextPath}/plugins/Validator/bootstrapValidator.js"></script>
<script src="${pageContext.request.contextPath}/plugins/Validator/language/zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/Validator/bootstrapValidator.css"></link>

<script type="text/javascript">
		//检验
		function check() {
			if (checkDeviceTypeName()) {
				return true;
			} else {
				return false;
			}
		}
	    function checkDeviceTypeName() {
		    var name = $(":input[name='deviceType.deviceTypeName']").val();
		   	var reg=/^[a-zA-Z0-9\.\-\u4e00-\u9fa5]+$/;
            var result= reg.test(name);
		    if (name == "") {
				$("#nameError").html("<div></div><font color='red'>不能为空!</font>");
				return false;
			} else {
				$("#nameError").html("<div></div>");
				return true;
			}
		}
	</script>
</body>
</html>

