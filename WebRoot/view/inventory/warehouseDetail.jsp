<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <li><a href="${pageContext.request.contextPath}/view/inventory/warehouseList.jsp">仓库信息管理</a></li>
                <li class="active">仓库信息详情</li>
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
                                    <h5>仓库信息详情</h5>
                                </div>
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                仓库编号：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${storageWarehouse.warehouseNo}" id="warehouse_No"
                                                               class="required" style="width:450px;"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                仓库名称：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${storageWarehouse.warehouseName}"
                                                               id="warehouse_type" class="required" style="width:450px;"
                                                               name="purchase.deliveryTime"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                仓库地址：
                                            </label>
													<span class="input-icon input-icon-right">
														<input type="text" value="${storageWarehouse.warehouseAddress}"
                                                               disabled="disabled"
                                                               id="warehouse_location" class="required"
                                                               style="width:450px;" name="purchase.deliveryTime"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                仓库大小：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${storageWarehouse.warehouseSize}"
                                                               id="warehouse_size" class="required" style="width:450px;"
                                                               name="purchase.deliveryTime"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                仓库状态：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${storageWarehouse.warehouseStatus==0?"正常":"禁用"}"
                                                               id="warehouse_statu" class="required"
                                                               style="width:450px;" name="purchase.deliveryTime"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                创建日期：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${storageWarehouse.createDate}"
                                                               id="warehouse_createDate" class="required"
                                                               style="width:450px;" name="purchase.deliveryTime"/>
													</span>
                                        </div>
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                地址经度：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${storageWarehouse.warehouseLng}"
                                                               id="warehouse_lng" class="required"
                                                               style="width:450px;" name="purchase.warehouseLng"/>
													</span>
                                        </div>
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                地址纬度：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${storageWarehouse.warehouseLat}"
                                                               id="warehouse_lat" class="required"
                                                               style="width:450px;" name="purchase.warehouseLat"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                所属区域：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${storageWarehouse.dictRegionName}"
                                                               id="dict_provice_id" class="required"
                                                               style="width:450px;" name="purchase.dictRegionName"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                所属省：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${storageWarehouse.dictProviceName}"
                                                               id="dict_region_id" class="required"
                                                               style="width:450px;" name="purchase.dictProviceName"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                所属分公司：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${storageWarehouse.dictOrgName}"
                                                               id="dict_orgName_id" class="required"
                                                               style="width:450px;" name="purchase.dictOrgName"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                仓库类型：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${storageWarehouse.isRefrigeratory==0?"非冷库":"冷库"}"
                                                               id="isRefrigeratory" class="required"
                                                               style="width:450px;" name="purchase.dictOrgName"/>
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
<script type="text/javascript">
</script>
</body>
</html>



