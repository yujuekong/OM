<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
                <li><a href="#">仓库管理</a></li>
                <li><a href="${pageContext.request.contextPath}/view/inventory/inList.jsp">商品入库</a></li>
                <li class="active">商品信息详情</li>
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
            <div class="row">
                <div class="col-xs-12">
                    <div class="form-group">
                        <div class="col-xs-12">
                            <div class="widget-box">
                                <div class="widget-header widget-header-small">
                                    <h5>商品信息</h5>
                                </div>
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                出库单明细ID:
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled"
                                                               value="${storageDeliveryDtl.deliveryDtlId}" type="text"
                                                               id="storageDeliveryDtl_storageDeliveryDtlId"
                                                               class="required" style="width:450px;"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                出库单编号：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled"
                                                               value="${storageDeliveryDtl.storageDeliveryOrder.deliveryNo}"
                                                               type="text" id="storageDeliveryDtl_SellerInfo"
                                                               class="required" style="width:450px;"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                商&nbsp;品：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled"
                                                               value="${storageDeliveryDtl.goodsInfo.goodsName}"
                                                               type="text" id="storageDeliveryDtl_storageWarehouse"
                                                               class="required" style="width:450px;"
                                                               name="purchase.deliveryTime"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                设备名称：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled"
                                                               value="${storageDeliveryDtl.deviceInfo.deviceName}"
                                                               type="text" id="storageDeliveryDtl_warehousingNo"
                                                               class="required" style="width:450px;"
                                                               name="purchase.deliveryTime"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                要求出库数量：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled"
                                                               value="${storageDeliveryDtl.requestCount}" type="text"
                                                               id="storageDeliveryDtl_bizType" class="required"
                                                               style="width:450px;" name="purchase.deliveryTime"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                实际出库数量：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled"
                                                               value="${storageDeliveryDtl.deliveryCount}" type="text"
                                                               id="storageDeliveryDtl_remark" class="required"
                                                               style="width:450px;" name="purchase.deliveryTime"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                明细备注:
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" value="${storageDeliveryDtl.remark}"
                                                               type="text" id="storageDeliveryDtl_warehousingDate"
                                                               class="required" style="width:450px;"
                                                               name="purchase.deliveryTime"/>
													</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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

<!-- <![endif]-->

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
<script type="text/javascript">
    $("#submenu-menu-device-info").addClass("active");
    $("#menu-device").addClass("active");
    $("#menu-device").addClass("open");
</script>
</body>
</html>