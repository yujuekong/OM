<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../commons/head.jsp"/>

<body class="no-skin">
<div class="main-container" id="main-container">
    <div>
        <div class="breadcrumbs" id="breadcrumbs">
            <%-- <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed');
                } catch (e) {
                }
            </script> --%>

            <ul id="uiId" class="breadcrumb">
                <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a>
                </li>
                <li><a href="#">销售管理</a></li>
                <li><a
                        href="${pageContext.request.contextPath}/view/sale/gameInfoList.jsp">商品积分管理</a></li>
                <li class="active">商品积分详情</li>
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
                <form action="${pageContext.request.contextPath}/view/sale/gameInfo/saveOrUpdateGameInfo.action"
                      method="post" onsubmit="return checkBlank();">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <div class="col-xs-12">
                                <div class="widget-box">
                                    <div class="widget-header widget-header-small">
                                        <h5 id="h5Id">抽奖活动</h5>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <div class="form-group" hidden="hidden">
                                                <label class="col-sm-2 control-label no-padding-right">商品名称： </label>
                                                <span class="input-icon input-icon-right">
                                                    <input type="text" value="${goodsIntegral.goodsIntegralId}"
                                                           id="goodsIntegralId" class="required"
                                                           style="width:250px;" name="goodsIntegral.goodsIntegralId"
                                                           disabled="disabled"/>
                                                </span>
                                                </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">商品名称： </label>
                                                <span class="input-icon input-icon-right">
                                                    <input type="text" value="${goodsIntegral.goodsName}"
                                                           id="goodsName" class="required"
                                                           style="width:250px;" name="goodsIntegral.gameName"
                                                           disabled="disabled"/>
                                                </span>
                                                </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">积分值：</label>
                                                <span class="input-icon input-icon-right">
                                                    <input type="text" value="${goodsIntegral.integralNumber}"
                                                           id="integralNumber" class="required"
                                                           style="width:250px;" name="goodsIntegral.integralNumber"
                                                           disabled="disabled"/>
                                                </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">是否使用：</label>
                                                <span class="input-icon input-icon-right">
                                                    <input type="text" value="${goodsIntegral.isUser==0?"使用":"禁用"}"
                                                           id="isUser" class="required"
                                                           style="width:150px;" name="goodsIntegral.isUser"
                                                           disabled="disabled"/>
													</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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

<!-- <![endif]-->

<!--[if !IE]> -->

<!-- page specific plugin scripts -->
<%-- <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>


</body>
</html>

