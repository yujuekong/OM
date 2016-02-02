<%--<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<jsp:include page="../commons/head.jsp"/>--%>

<%--<body class="no-skin">--%>
<%--<div class="main-container" id="main-container">--%>
    <%--<div>--%>
        <%--<div class="breadcrumbs" id="breadcrumbs">--%>
            <%--<script type="text/javascript">--%>
                <%--try {--%>
                    <%--ace.settings.check('breadcrumbs', 'fixed')--%>
                <%--} catch (e) {--%>
                <%--}--%>
            <%--</script>--%>

            <%--<ul class="breadcrumb">--%>
                <%--<li>--%>
                    <%--<i class="ace-icon fa fa-home home-icon"></i>--%>
                    <%--<a href="#">首页</a>--%>
                <%--</li>--%>
                <%--<li><a href="#">仓库管理</a></li>--%>
                <%--<li><a href="${pageContext.request.contextPath}/view/inventory/warehouseList.jsp">仓库信息管理</a></li>--%>
                <%--<li class="active">仓库信息详情</li>--%>
            <%--</ul>--%>
            <%--<!-- /.breadcrumb -->--%>

            <%--<div class="nav-search" id="nav-search">--%>
                <%--<a href="javascript:history.back()">--%>
                    <%--<i class="ace-icon fa fa-arrow-left"></i>返回--%>
                <%--</a>--%>
            <%--</div>--%>
            <%--<!-- /.nav-search -->--%>
        <%--</div>--%>

        <%--<div class="page-content">--%>
            <%--<form id="seller"--%>
                  <%--action="${pageContext.request.contextPath}/view/seller/sellerInfo/saveOrUpdateSellerInfo.action"--%>
                  <%--method="post">--%>
                <%--<div class="col-xs-12">--%>
                    <%--<div class="widget-box">--%>
                        <%--<div class="widget-header widget-header-small">--%>
                            <%--<h5>商品信息</h5>--%>
                        <%--</div>--%>
                        <%--<div class="widget-body">--%>
                            <%--<div class="widget-main">--%>
                                <%--<div hidden="hidden" class="form-group">--%>
                                    <%--<label class="col-sm-2 control-label no-padding-right">--%>
                                        <%--供应商ID：--%>
                                    <%--</label>--%>
                                    <%--<span class="input-icon input-icon-right">--%>
                                        <%--<input type="text"--%>
                                               <%--value="${sellerinfo.sellerId}"--%>
                                               <%--id="SellerInfo_sellerId"--%>
                                               <%--class="required" style="width:450px;"--%>
                                               <%--name="sellerinfo.sellerId"/>--%>
                                    <%--</span>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-2 control-label no-padding-right">--%>
                                        <%--供应商名称：--%>
                                    <%--</label>--%>
                                        <%--<span class="input-icon input-icon-right">--%>
                                            <%--<input type="text"--%>
                                                   <%--value="${sellerinfo.sellerName}"--%>
                                                   <%--id="SellerInfo_sellerName" class="required"--%>
                                                   <%--style="width:450px;"--%>
                                                   <%--name="sellerinfo.sellerName"/>--%>
                                        <%--</span>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-2 control-label no-padding-right">--%>
                                        <%--供应商编号：--%>
                                    <%--</label>--%>
                                        <%--<span class="input-icon input-icon-right">--%>
                                            <%--<input type="text" value="${sellerinfo.sellerNo}"--%>

                                                   <%--id="SellerInfo_sellerNo" class="required"--%>
                                                   <%--style="width:450px;" name="sellerinfo.sellerNo"/>--%>
                                        <%--</span>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-2 control-label no-padding-right">--%>
                                        <%--供应商联系人：--%>
                                    <%--</label>--%>
                                        <%--<span class="input-icon input-icon-right">--%>
                                            <%--<input type="text"--%>
                                                   <%--value="${sellerinfo.linkMan}"--%>
                                                   <%--id="SellerInfo_linkMan" class="required"--%>
                                                   <%--style="width:450px;"--%>
                                                   <%--name="sellerinfo.linkMan"/>--%>
                                        <%--</span>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-2 control-label no-padding-right">--%>
                                        <%--联系人电话：--%>
                                    <%--</label>--%>
                                        <%--<span class="input-icon input-icon-right">--%>
                                            <%--<input type="text"--%>
                                                   <%--value="${sellerinfo.linkTel}"--%>
                                                   <%--id="SellerInfo_linkTel" class="required"--%>
                                                   <%--style="width:450px;" name="sellerinfo.linkTel"/>--%>
                                        <%--</span>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-2 control-label no-padding-right">--%>
                                        <%--供应商类型：--%>
                                    <%--</label>--%>
                                        <%--<span class="input-icon input-icon-right">--%>
                                            <%--<input type="text"--%>
                                                   <%--value="${sellerinfo.sellerType}"--%>
                                                   <%--id="SellerInfo_sellerType" class="required"--%>
                                                   <%--style="width:450px;" name="sellerinfo.sellerType"/>--%>
                                        <%--</span>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-2 control-label no-padding-right">--%>
                                        <%--供货类型：--%>
                                    <%--</label>--%>
                                        <%--<span class="input-icon input-icon-right">--%>
                                            <%--<input type="text"--%>
                                                   <%--value="${sellerinfo.sellerGoodsType}"--%>
                                                   <%--id="SellerInfo_sellerGoodsType" class="required"--%>
                                                   <%--style="width:450px;"--%>
                                                   <%--name="sellerinfo.sellerGoodsType"/>--%>
                                        <%--</span>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-2 control-label no-padding-right">--%>
                                        <%--供应商状态：--%>
                                    <%--</label>--%>
                                        <%--<span class="input-icon input-icon-right">--%>
                                              <%--<select id="SellerInfo_sellerStatus"--%>
                                                      <%--name="sellerinfo.sellerStatus">--%>
                                                  <%--<option value="-1">请选择</option>--%>
                                                  <%--<option value="0" ${sellerinfo.sellerStatus == 0 ?"selected= 'selected'" : ""}>--%>
                                                      <%--正常--%>
                                                  <%--</option>--%>
                                                  <%--<option value="1" ${sellerinfo.sellerStatus == 1 ?"selected= 'selected'" : ""}>--%>
                                                      <%--禁用--%>
                                                  <%--</option>--%>
                                              <%--</select>--%>
                                        <%--</span>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-2 control-label no-padding-right">--%>
                                        <%--创建日期：--%>
                                    <%--</label>--%>
                                        <%--<span class="input-icon input-icon-right">--%>
                                            <%--<input type="text"--%>
                                                   <%--value="${sellerinfo.createDate}"--%>
                                                   <%--id="SellerInfo_createDate" class="required"--%>
                                                   <%--style="width:450px;" name="sellerinfo.createDate"/>--%>
                                        <%--</span>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-2 control-label no-padding-right">--%>
                                        <%--供应商地址：--%>
                                    <%--</label>--%>
                                        <%--<span class="input-icon input-icon-right">--%>
                                            <%--<input type="text"--%>
                                                   <%--value="${sellerinfo.sellerAddress}"--%>
                                                   <%--id="SellerInfo_sellerAddress" class="required"--%>
                                                   <%--style="width:450px;" name="sellerinfo.sellerAddress"/>--%>
                                        <%--</span>--%>
                                <%--</div>--%>
                                <%--<div class="form-group" hidden="hidden">--%>
                                    <%--<label class="col-sm-2 control-label no-padding-right">--%>
                                        <%--所属区域：--%>
                                    <%--</label>--%>
                                        <%--<span class="input-icon input-icon-right">--%>
                                            <%--<input type="text"--%>
                                                   <%--value="${sellerinfo.dictRegion.dictName}"--%>
                                                   <%--id="dict_region_id" class="required"--%>
                                                   <%--style="width:450px;"/>--%>
                                        <%--</span>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-sm-2 control-label no-padding-right">--%>
                                        <%--所属省：--%>
                                    <%--</label>--%>
                                        <%--<span class="input-icon input-icon-right">--%>
                                        <%--<input type="text" value="${sellerinfo.dictProvice.dictName}"--%>
                                               <%--id="userPro" class="required" style="width:450px;"--%>
                                               <%--onfocus="showMenu()"/>--%>
                                        <%--<input type="hidden" id="orgStrId" name="sellerinfo.dictProviceId"--%>
                                               <%--value="${sellerinfo.dictProviceId}"/>--%>
                                        <%--<input type="hidden" id="regionStrId" name="sellerinfo.dictRegionId"--%>
                                               <%--value="${sellerinfo.dictRegionId}"/>--%>
                                        <%--<i class="ace-icon fa fa-caret-down" onclick="showMenu()"></i>--%>
                                        <%--</span>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div id="form_action_btns" class="col-xs-12" style="text-align: center">--%>
                            <%--<button id="save" class="btn btn-sm no-border btn-success" onclick="saveOp(0)">--%>
                                <%--<i class="ace-icon fa fa-floppy-o"></i>保存--%>
                            <%--</button>--%>
                            <%--&nbsp;&nbsp;&nbsp;--%>
                            <%--<a class="btn btn-sm no-border btn-default" onclick="javascript:history.back()">--%>
                                <%--<i class="ace-icon fa fa-times red2"></i>取消</a>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>

            <%--</form>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<!-- /.row -->--%>
<%--</div>--%>
<%--<!-- /.page-content -->--%>
<%--</div>--%>
<%--<!-- /.main-content -->--%>
<%--</div>--%>
<%--<!-- /.main-container -->--%>

<%--<!-- Modal -->--%>
<%--<div id="gt_combobox"--%>
     <%--style="display:none; height:400px;position: absolute;border: 1px solid #ddd;background-color:#F0F0F0;overflow:auto">--%>
    <%--<ul id="gt_tree" class="ztree" style="margin-top:0; "></ul>--%>
<%--</div>--%>
<%--<!-- Modal -->--%>

<%--<!-- basic scripts -->--%>

<%--<!--[if !IE]> -->--%>
<%--<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>--%>

<%--<!-- <![endif]-->--%>

<%--<!--[if !IE]> -->--%>
<%--<script type="text/javascript">--%>
    <%--window.jQuery || document.write("<script src='${pageContext.request.contextPath}/js/jquery.min.js'>" + "<" + "/script>");--%>
<%--</script>--%>

<%--<!-- page specific plugin scripts -->--%>
<%--<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/jquery.dataTables.bootstrap.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/plugins/Validator/bootstrapValidator.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/plugins/Validator/language/zh_CN.js"></script>--%>


<%--&lt;%&ndash;<script src="${pageContext.request.contextPath}/js/biz/inventory/warehouseAddUpdata.js"></script>&ndash;%&gt;--%>
<%--<script type="text/javascript">--%>
    <%--$("#submenu-menu-device-info").addClass("active");--%>
    <%--$("#menu-device").addClass("active");--%>
    <%--$("#menu-device").addClass("open");--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>