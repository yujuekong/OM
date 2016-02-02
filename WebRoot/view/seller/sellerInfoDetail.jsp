<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
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
                <li><a href="${pageContext.request.contextPath}/view/seller/sellerInfoList.jsp">供应商信息管理</a></li>
                <li class="active">供应商信息详情</li>
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
                                        <div hidden="hidden" class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                供应商ID：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${sellerInfo.sellerId}" id="SellerInfo_sellerId"
                                                               class="required" style="width:450px;"
                                                               name="sellerInfo.sellerId"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                供应商名称：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${sellerInfo.sellerName}"
                                                               id="SellerInfo_sellerName" class="required" style="width:450px;"
                                                               name="sellerInfo.sellerName"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                供应商编号：
                                            </label>
													<span class="input-icon input-icon-right">
														<input type="text" value="${sellerInfo.sellerNo}"
                                                               disabled="disabled"
                                                               id="SellerInfo_sellerNo" class="required"
                                                               style="width:450px;" name="sellerInfo.sellerNo"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                供应商联系人：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${sellerInfo.linkMan}"
                                                               id="SellerInfo_linkMan" class="required" style="width:450px;"
                                                               name="sellerInfo.linkMan"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                联系人电话：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${sellerInfo.linkTel}"
                                                               id="SellerInfo_linkTel" class="required"
                                                               style="width:450px;" name="sellerInfo.linkTel"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                供应商类型：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${sellerInfo.sellerGoodsType}"
                                                               id="SellerInfo_sellerType" class="required"
                                                               style="width:450px;" name="sellerInfo.sellerType"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                供货类型：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${sellerInfo.sellerGoodsType}"
                                                               id="SellerInfo_sellerGoodsType" class="required"
                                                               style="width:450px;" name="sellerInfo.sellerGoodsType"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                供应商状态：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${sellerInfo.sellerStatus==0?"正常":"禁用"}"
                                                               id="SellerInfo_sellerStatus" class="required"
                                                               style="width:450px;" name="sellerInfo.sellerStatus"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                创建日期：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${sellerInfo.createDate}"
                                                               id="SellerInfo_createDate" class="required"
                                                               style="width:450px;" name="sellerInfo.createDate"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                供应商地址：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${sellerInfo.sellerAddress}"
                                                               id="SellerInfo_sellerAddress" class="required"
                                                               style="width:450px;" name="sellerInfo.sellerAddress"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                所属区域：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${sellerInfo.dictRegion.dictName}"
                                                               id="SellerInfo_dictProvice" class="required"
                                                               style="width:450px;" name="sellerInfo.dictProvice"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                所属省份：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" type="text"
                                                               value="${sellerInfo.dictProvice.dictName}"
                                                               id="SellerInfo_dictRegion" class="required"
                                                               style="width:450px;" name="sellerInfo.dictRegion"/>
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
    $(document).ready(function () {


    })
</script>
</body>
</html>