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
            <ul id="uiId" class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">首页</a>
                </li>
                <li><a href="#">销售管理</a></li>
                <li><a href="${pageContext.request.contextPath}/view/goods/activityCouponList.jsp">活动优惠劵管理</a></li>
                <li class="active">
                    <c:choose>
                        <c:when test="${activityCoupon.couponId==null}">新增活动优惠劵管理</c:when>
                        <c:otherwise>修改活动优惠劵管理</c:otherwise>
                    </c:choose>
                </li>
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
                <form id="goods"
                      action="${pageContext.request.contextPath}/view/sale/coupon/saveOrUpdateCoupon.action"
                      method="post" enctype="multipart/form-data" class="form-horizontal">
                    <div class="col-xs-12">
                        <div class="col-xs-12">
                            <div class="widget-box">
                                <div class="widget-header widget-header-small">
                                    <h5 id="h5Id">
                                        <c:choose>
                                            <c:when test="${activityCoupon.couponId==null}">新增活动优惠劵管理</c:when>
                                            <c:otherwise>修改活动优惠劵管理</c:otherwise>
                                        </c:choose>
                                    </h5>
                                </div>
                                <div class="widget-body">
                                    <div class="widget-main" style="float: left;width: 45%">
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                优惠券ID:
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${activityCoupon.couponId}" id="activityCoupon_couponId"
                                                   class="required" style="width:250px;"
                                                   name="activityCoupon.couponId"/>
                                        </span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                优惠券名称：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${activityCoupon.couponName}" id="activityCoupon_couponName"
                                                   class="required control-label"
                                                   style="width:250px;height: 36px" name="activityCoupon.couponName"/>
                                        </span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                有效开始日期：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                           <input type="text"
                                                  value="${activityCoupon.startDate}" id="activityCoupon_startDate"
                                                  class="required control-label"
                                                  style="width:250px;" name="activityCoupon.startDate"/>
                                        </span>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-2 no-padding-right">
                                                有效结束日期：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${activityCoupon.endDate}" id="activityCoupon_endDate"
                                                   class="required control-label"
                                                   style="width:250px;" name="activityCoupon.endDate"/>
                                        </span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 no-padding-right">
                                                优惠券类型：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <select name="activityCoupon.couponType" class="required control-label"
                                                    style="width:250px;">
                                                <option value="0">请选择</option>
                                                <option value="0" ${activityCoupon.couponType=="0"?"selected":""}>现金
                                                </option>
                                                <option value="1" ${activityCoupon.couponType=="1"?"selected":""}>折扣
                                                </option>
                                            </select>
                                        </span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 no-padding-right">
                                                优惠券面值：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${activityCoupon.couponAmount}" id="goods__type_Brand"
                                                   class="required control-label"
                                                   style="width:250px;" name="activityCoupon.couponAmount"/>
                                        </span>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-2 no-padding-right">
                                                领取结束日期：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${activityCoupon.getCouponEndDate}"
                                                   id="activityCoupon_getCouponEndDate"
                                                   class="required control-label"
                                                   style="width:250px;" name="activityCoupon.getCouponEndDate"/>
                                        </span>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-2 no-padding-right">
                                                优惠券数量：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${activityCoupon.couponNumber}"
                                                   id="activityCoupon__couponNumber"
                                                   class="required control-label"
                                                   style="width:250px;" name="activityCoupon.couponNumber"/>
                                        </span>
                                        </div>
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                所属分公司：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                        <input type="text" value="${activityCoupon.sysDict.dictName}"
                                               id="dictOrg" class="required" style="width:250px;"
                                               onfocus="showMenu()"/>
                                        <input type="hidden" id="regionStrId" name="activityCoupon.dictOrgId"
                                               value="${sellerInfo.dictOrgId}"/>
                                        <i class="ace-icon fa fa-caret-down" onclick="showMenu()"></i>
                                        </span>
                                        </div>

                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 no-padding-right">
                                                优惠劵地区说明：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <select name="activityCoupon.couponDistrict" style="width: 80px">
                                                <option>1</option>
                                                <option>2</option>
                                                <option>3</option>
                                            </select>
                                            <%--<input type="text"--%>
                                                   <%--value="${activityCoupon.couponDistrict}"--%>
                                                   <%--id="activityCoupon__couponDistrict"--%>
                                                   <%--class="required control-label"--%>
                                                   <%--style="width:250px;" name="activityCoupon.couponDistrict"/>--%>
                                        </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.row -->
                    <div class="row">
                        <div id="form_action_btns" class="col-xs-12" style="text-align: center">
                            <button type="submit" id="save" class="btn btn-sm no-border btn-success"
                                    onclick="saveOp(0)">
                                <i class="ace-icon fa fa-floppy-o"></i>保存
                            </button>
                            &nbsp;&nbsp;&nbsp;
                            <a class="btn btn-sm no-border btn-default" onclick="history.back()">
                                <i class="ace-icon fa fa-times red2"></i>取消</a>
                        </div>
                    </div>
                </form>
            </div>
            <!-- /.page-content -->
        </div>
    </div>
    <!-- /.main-content -->
</div>
<!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- <![endif]-->
<div id="gt_combobox"
     style="display:none; height:400px;position: absolute;border: 1px solid #ddd;background-color:#F0F0F0;overflow:auto">
    <ul id="gt_tree" class="ztree" style="margin-top:0; "></ul>
</div>
<div id="unit_combobox"
     style="display:none; height:400px;position: absolute;border: 1px solid #ddd;background-color:#F0F0F0;overflow:auto">
    <ul id="unit_tree" class="ztree" style="margin-top:0; "></ul>
</div>
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
<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>

<script src="${pageContext.request.contextPath}/plugins/Validator/bootstrapValidator.js"></script>
<script src="${pageContext.request.contextPath}/plugins/Validator/language/zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/Validator/bootstrapValidator.css"/>


<script src="${pageContext.request.contextPath}/js/biz/sale/couponAddOrUpdata.js"></script>
<script type="text/javascript">

</script>
</div>
</body>
</html>

