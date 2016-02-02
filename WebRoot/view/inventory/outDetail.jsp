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
                <li><a href="${pageContext.request.contextPath}/view/inventory/inList.jsp">商品出库</a></li>
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
                                                出库单ID:
                                            </label>
											<span class="input-icon input-icon-right">
														<input value="${storageDeliveryOrder.deliveryOrderId}"
                                                               type="text"
                                                               id="storageDeliveryOrder_deliveryOrderId"
                                                               class="required" style="width:450px;"/>
											</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                仓&nbsp;库：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled"
                                                               value="${storageDeliveryOrder.storageWarehouse.warehouseName}"
                                                               type="text" id="storageDeliveryOrder_storageWarehouse"
                                                               class="required" style="width:450px;"
                                                               name="purchase.deliveryTime"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                出库单编号：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled"
                                                               value="${storageDeliveryOrder.deliveryNo}" type="text"
                                                               id="storageDeliveryOrder_warehousingNo" class="required"
                                                               style="width:450px;" name="purchase.deliveryTime"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                业务类别：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" value="${storageDeliveryOrder.bizType==1?"配送出库":"调拨出库"}"
                                                               type="text"
                                                               id="storageDeliveryOrder_bizType" class="required"
                                                               style="width:450px;" name="purchase.deliveryTime"/>
                                                        <%--<select id="storageDeliveryOrder_bizType"--%>
                                                                <%--name="storageDeliveryOrder.bizType">--%>
                                                            <%--<option value="-1">请选择</option>--%>
                                                            <%--<option value="1" ${storageDeliveryOrder.bizType == 1 ?"selected= 'selected'" : ""}>--%>
                                                                <%--配送出库--%>
                                                            <%--</option>--%>
                                                            <%--<option value="2" ${storageDeliveryOrder.bizType == 2 ?"selected= 'selected'" : ""}>--%>
                                                                <%--调拨出库--%>
                                                            <%--</option>--%>
                                                        </select>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                出库备注：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" value="${storageDeliveryOrder.remark}"
                                                               type="text"
                                                               id="storageDeliveryOrder_remark" class="required"
                                                               style="width:450px;" name="purchase.deliveryTime"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                出库日期：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled"
                                                               value="${storageDeliveryOrder.deliveryDate}"
                                                               type="text" id="storageDeliveryOrder_warehousingDate"
                                                               class="required" style="width:450px;"
                                                               name="purchase.deliveryTime"/>
													</span>
                                        </div>
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                录入用户：
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageDeliveryOrder.user.realName}"
                                                               type="text" id="storageDeliveryOrder_sysUser"
                                                               class="required" style="width:450px;"
                                                               name="purchase.deliveryTime"/>
                                                        <input value="${account.userId}"
                                                               type="text"
                                                               id="warehousingEntry_sysUser" class="required"
                                                               style="width:450px;"
                                                               name="storageDeliveryOrder.userId"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                创建日期：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled"
                                                               value="${storageDeliveryOrder.createDate}" type="text"
                                                               id="storageDeliveryOrder_createDate" class="required"
                                                               style="width:450px;" name="purchase.deliveryTime"/>
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
<%--$("#in_id").attr("disabled="disabled" value",decodeURI(request("inId")));--%>
<%--$("#in_warehouse").attr("disabled="disabled" value",decodeURI(request("inWarehouse")));--%>
<%--$("#in_date").attr("disabled="disabled" value",decodeURI(request("inDate")));--%>
<%--$("#eligible_num").attr("disabled="disabled" value",decodeURI(request("eligibleNum")));--%>
<%--$("#damaged_num").attr("disabled="disabled" value",decodeURI(request("damagedNum")));--%>
<%--var certificateStatu = decodeURI(request("certificateStatu"));--%>
<%--if(certificateStatu == 1){--%>
<%--$("#certificate_statu").attr("disabled="disabled" value","完成");--%>
<%--}else{--%>
<%--$("#certificate_statu").attr("disabled="disabled" value","未完成");--%>
<%--}--%>
<%----%>
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
</body>
</html>