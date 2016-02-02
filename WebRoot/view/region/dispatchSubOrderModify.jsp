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
                <form action="../dispatchSubOrder/updateDSubOrder.action" method="post" onsubmit="return check();">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <div class="col-xs-12">
                                <div class="widget-box">
                                    <div class="widget-header widget-header-small">
                                        <h5>新增设备类型</h5>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <div class="form-group" style="display:none"   >
                                                <label class="col-sm-2 control-label no-padding-right">
                                                   	 子订单ID：
                                                </label>
													<span class="input-icon input-icon-right">
														<input  readonly type="text" value="${dSubOrder.subOrderId}"
                                                               id="gt__type_number" class="required"
                                                               style="width:250px" name="dispatchingSubOrder.subOrderId"/>
													</span>
                                            </div>
                                            <div class="form-group"  style="display:none">
	                                                <label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
	                                          		          所属子公司： 
	                                                </label>
	                                                <span class="input-icon input-icon-right">
	                                                	<input  type="text" name="dispatchingSubOrder.dictOrgId" value="${account.orgId}"  />
														<input  type="text" name="dispatchingSubOrder.dictProviceId" value="${account.dictProviceId}"  />
														<input  type="text" name="dispatchingSubOrder.dictRegionId" value="${account.dictRegionId}"/>														
													</span>
												 </div>	
                                            <div class="form-group" style="display:none"  >
                                                <label class="col-sm-2 control-label no-padding-right">
                                                	    订单ID：
                                                </label>
													<span class="input-icon input-icon-right">
														<input   type="text" value="${dSubOrder.orderId}"
                                                               id="gt__type_no" class="required"
                                                               style="width:250px" name="dispatchingSubOrder.orderId"/>
													</span>
                                            </div>
                                            <div class="form-group" style="display:none">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                 	   设备ID：
                                                </label>
												<span class="input-icon input-icon-right">
													<input  type="text" 
                                                              value="${dSubOrder.deviceId}" id="gt__type_name"
                                                              class="required"
                                                              style="width:250px;" name="dispatchingSubOrder.deviceId"/>
												</span>
												<span id= "nameError" class="input-icon input-icon-right">
												</span>
                                            </div>
                                            <div class="form-group" style="display:none">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                 	   商圈ID：
                                                </label>
												<span class="input-icon input-icon-right">
													<input  type="text" 
                                                              value="${dSubOrder.districtId}" id="gt__type_name"
                                                              class="required"
                                                              style="width:250px;" name="dispatchingSubOrder.districtId"/>
												</span>
												<span id= "nameError" class="input-icon input-icon-right">
												</span>
                                            </div>
                                            <div class="form-group" style="display:none">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                 	   商品ID：
                                                </label>
												<span class="input-icon input-icon-right">
													<input  type="text" 
                                                              value="${dSubOrder.goodsId}" id="gt__type_name"
                                                              class="required"
                                                              style="width:250px;" name="dispatchingSubOrder.goodsId"/>
												</span>
												<span id= "nameError" class="input-icon input-icon-right">
												</span>
                                            </div>
                                            <div class="form-group" style="display:none">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                 	   配送小组ID：
                                                </label>
												<span class="input-icon input-icon-right">
													<input  type="text" 
                                                              value="${dSubOrder.dispatchingTeamId}" id="gt__type_name"
                                                              class="required"
                                                              style="width:250px;" name="dispatchingSubOrder.dispatchingTeamId"/>
												</span>
												<span id= "nameError" class="input-icon input-icon-right">
												</span>
                                            </div>
                                            <div class="form-group" style="display:none">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                 	   完成状态：
                                                </label>
												<span class="input-icon input-icon-right">
													<input  type="text" 
                                                              value="${dSubOrder.subOrderStatus}" id="gt__type_name"
                                                              class="required"
                                                              style="width:250px;" name="dispatchingSubOrder.subOrderStatus"/>
												</span>
												<span id= "nameError" class="input-icon input-icon-right">
												</span>
                                            </div>
                                            <div class="form-group" >
                                                <label class="col-sm-2 control-label no-padding-right">
                                                 	   设备编号：
                                                </label>
												<span class="input-icon input-icon-right">
													<input readonly type="text" 
                                                              value="${dSubOrder.deviceNo}" id="gt__type_name"
                                                              class="required"
                                                              style="width:250px;" name="dispatchingSubOrder.deviceNo"/>
												</span>
												<span id= "nameError" class="input-icon input-icon-right">
												</span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                 	   商圈名称：
                                                </label>
												<span class="input-icon input-icon-right">
													<input  type="text" readonly
                                                              value="${dSubOrder.districtName}" id="gt__type_name"
                                                              class="required"
                                                              style="width:250px;" name="dispatchingSubOrder.districtName"/>
												</span>
												<span id= "nameError" class="input-icon input-icon-right">
												</span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                 	   商品名称：
                                                </label>
												<span class="input-icon input-icon-right">
													<input  type="text" readonly
                                                              value="${dSubOrder.goodsName}" id="gt__type_name"
                                                              class="required"
                                                              style="width:250px;" name="dispatchingSubOrder.goodsName"/>
												</span>
												<span id= "nameError" class="input-icon input-icon-right">
												</span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                 	   商品数量：
                                                </label>
												<span class="input-icon input-icon-right">
													<input  type="text" 
                                                              value="${dSubOrder.goodsNumber}" id="gt__type_name"
                                                              class="required"
                                                              style="width:250px;" name="dispatchingSubOrder.goodsNumber"/>
												</span>
												<span id= "nameError" class="input-icon input-icon-right">
												</span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                 	   配送时间：
                                                </label>
												<span class="input-icon input-icon-right">
													<input  type="text" readonly
                                                              value="${dSubOrder.dispatchingTime}" id="gt__type_name"
                                                              class="required"
                                                              style="width:250px;" name="dispatchingSubOrder.dispatchingTime"/>
												</span>
												<span id= "nameError" class="input-icon input-icon-right">
												</span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">
                                                 	   取币金额：
                                                </label>
												<span class="input-icon input-icon-right">
													<input  type="text" readonly
                                                              value="${dSubOrder.currencyAmount}" id="gt__type_name"
                                                              class="required"
                                                              style="width:250px;" name="dispatchingSubOrder.currencyAmount"/>
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
                                <button id="save" class="btn btn-sm no-border btn-success" onclick="saveOp(0)">
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
</body>
</html>

