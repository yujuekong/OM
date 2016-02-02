<%--<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<jsp:include page="../commons/head.jsp" />--%>
<%----%>
<%--<body class="no-skin">--%>
<%--<div class="main-container" id="main-container">--%>
<%--<div>--%>
<%--<div class="breadcrumbs" id="breadcrumbs">--%>
<%--<script type="text/javascript">--%>
<%--try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}--%>
<%--</script>--%>
<%--<ul id="uiId"class="breadcrumb">--%>
<%--<li>--%>
<%--<i class="ace-icon fa fa-home home-icon"></i>--%>
<%--<a href="#">首页</a>--%>
<%--</li>--%>
<%--<li><a href="#">仓库管理</a></li>--%>
<%--<li><a href="${pageContext.request.contextPath}/view/inventory/outList.jsp">出库管理</a></li>--%>
<%--<li class="active">新增商品出库信息</li>--%>
<%--</ul><!-- /.breadcrumb -->--%>

<%--<div class="nav-search" id="nav-search">--%>
<%--<a href="javascript:history.back()" >--%>
<%--<i class="ace-icon fa fa-arrow-left"></i>返回--%>
<%--</a>--%>
<%--</div><!-- /.nav-search -->--%>
<%--</div>--%>

<%--<div class="page-content">--%>
<%--<div class="row">--%>
<%--<div class="col-xs-12">--%>
<%--<div class="form-group">--%>
<%--<div class="col-xs-12">--%>
<%--<div class="widget-box">--%>
<%--<div class="widget-header widget-header-small">--%>
<%--<h5 id="h5Id">新增商品出库信息</h5>--%>
<%--</div>--%>
<%--<div class="widget-body">--%>
<%--<div class="widget-main">--%>
<%--<div class="form-group">--%>
<%--<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>--%>
<%--出库编号： --%>
<%--</label>--%>
<%--<span class="input-icon input-icon-right">--%>
<%--<input type="text" id="out_warehouseId" class="required" style="width:450px;"  />														--%>
<%--</span>--%>
<%--</div>											--%>
<%--<div class="form-group">--%>
<%--<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>--%>
<%--始发仓库： --%>
<%--</label>--%>
<%--<span class="input-icon input-icon-right">--%>
<%--<input type="text" id="out_warehouse" class="required" style="width:450px;"  />														--%>
<%--</span>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>--%>
<%--目的地： --%>
<%--</label>--%>
<%--<span class="input-icon input-icon-right">--%>
<%--<input type="text" id="out_date" class="required" style="width:450px;" name="purchase.deliveryTime" />														--%>
<%--</span>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>--%>
<%--出库数量： --%>
<%--</label>--%>
<%--<span class="input-icon input-icon-right">--%>
<%--<input type="text" id="eligible_num" class="required" style="width:450px;" name="purchase.deliveryTime" />														--%>
<%--</span>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>--%>
<%--出库时间： --%>
<%--</label>--%>
<%--<span class="input-icon input-icon-right">--%>
<%--<input type="text" id="end_station" class="required" style="width:450px;" name="purchase.deliveryTime" />														--%>
<%--</span>--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>--%>
<%--出库状态： --%>
<%--</label>--%>
<%--<span class="input-icon input-icon-right">--%>
<%--<select id="certificate_statu" lass="required" style="width: 150px" name="purchase.sellerSubId" >--%>
<%--<option value="1">完成</option>--%>
<%--<option value="2">未完成</option>--%>
<%--</select>														--%>
<%--</span>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div><!-- /.row -->--%>
<%--<div class="row">--%>
<%--<div id="form_action_btns" class="col-xs-12" style="text-align: center">--%>
<%--<button id="save" class="btn btn-sm no-border btn-success" onclick="saveOp(0)">--%>
<%--<i class="ace-icon fa fa-floppy-o"></i>保存</button>&nbsp;&nbsp;&nbsp;--%>
<%--<button id = "submit" class="btn btn-sm no-border btn-success" onclick="submit(1)">--%>
<%--<i class="ace-icon fa fa-check"></i>提交</button>&nbsp;&nbsp;&nbsp;--%>
<%--<a class="btn btn-sm no-border btn-default" onclick="cancelOp()">--%>
<%--<i class="ace-icon fa fa-times red2"></i>取消</a>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div><!-- /.page-content -->--%>
<%--</div><!-- /.main-content -->--%>
<%--</div><!-- /.main-container -->--%>

<%--<!-- basic scripts -->--%>

<%--<!--[if !IE]> -->--%>
<%--<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>--%>

<%--<!-- <![endif]-->--%>

<%--<!--[if !IE]> -->--%>
<%--<script type="text/javascript">--%>
<%--window.jQuery || document.write("<script src='${pageContext.request.contextPath}/js/jquery.min.js'>"+"<"+"/script>");--%>
<%--</script>--%>

<%--<!-- page specific plugin scripts -->--%>
<%--<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/jquery.dataTables.bootstrap.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>--%>
<%--<script type="text/javascript">--%>
<%--$("#submenu-menu-device-info").addClass("active");--%>
<%--$("#menu-device").addClass("active");--%>
<%--$("#menu-device").addClass("open");--%>
<%--</script>--%>
<%--<script type="text/javascript">--%>
<%--var url = location.search;--%>
<%--var Request = new Object();--%>
<%--if(url.indexOf("?")!=-1){--%>
<%--var str = url.substr(1);--%>
<%--var strs = str.split("&");--%>
<%--for(var i =0;i<strs.length;i++){--%>
<%--Request[strs[i ].split("=")[0]] = unescape(strs[ i].split("=")[1]);--%>
<%--}--%>
<%--}--%>

<%----%>
<%--$("#out_warehouseId").attr("value",decodeURI(request("outWarehouseId")));--%>
<%--$("#out_warehouse").attr("value",decodeURI(request("outWarehouse")));--%>
<%--$("#out_date").attr("value",decodeURI(request("outDate")));--%>
<%--$("#eligible_num").attr("value",decodeURI(request("eligibleNum")));--%>
<%--$("#end_station").attr("value",decodeURI(request("endStation")));--%>
<%--var certificateStatu = decodeURI(request("certificateStatu"));--%>
<%--if(certificateStatu == 1){--%>
<%--$("#certificate_statu").val(1); --%>
<%--}else{--%>
<%--$("#certificate_statu").val(2); --%>
<%--}--%>
<%--if(decodeURI(request("outWarehouseId")) != ""){--%>
<%--$("#submit").hide();--%>
<%--document.getElementById("uiId").getElementsByTagName("li")[3].innerHTML="编辑出库商品信息";--%>
<%--$("#h5Id").text("编辑出库商品信息"); --%>
<%--}			--%>
<%----%>
<%----%>
<%--function request(paras){ --%>
<%--var url = location.href;  --%>
<%--var paraString = url.substring(url.indexOf("?")+1,url.length).split("&");  --%>
<%--var paraObj = {}; --%>
<%--for (var i=0; j=paraString[i]; i++){  --%>
<%--paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length);  --%>
<%--}  --%>
<%--var returnValue = paraObj[paras.toLowerCase()]; --%>
<%--if(typeof(returnValue)=="undefined"){  --%>
<%--return "";  --%>
<%--}else{  --%>
<%--return returnValue;  --%>
<%--} --%>
<%--} --%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>



