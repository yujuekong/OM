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
                <li><a href="#">销售管理</a></li>
                <li class="active">会员优惠劵管理</li>
                <%--<p>--%>
                <%--<button class="btn btn-minier " type="button">默认</button>--%>
                <%--<button class="btn btn-minier btn-primary" type="button">主要</button>--%>
                <%--<button class="btn btn-minier btn-info" type="button">信息</button>--%>
                <%--<button class="btn btn-minier btn-success" type="button">成功</button>--%>
                <%--<button class="btn btn-minier btn-warning" type="button">警告</button>--%>
                <%--<button class="btn btn-minier btn-danger" type="button">危险</button>--%>
                <%--<button class="btn btn-minier btn-inverse" type="button">反向</button>--%>
                <%--<button class="btn btn-minier btn-link" type="button">链接</button>--%>
                <%--</p>--%>
            </ul>
            <!-- /.breadcrumb -->

            <!-- /.nav-search -->
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="searchbar" hidden>
                        <ul class="list-inline">
                            <!-- <li>
                                <div class="btn-group">
                                    <p>
                                        <a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"
                                           onclick="quickSearch(0)">全部 </a>
                                        <a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"
                                           onclick="quickSearch(1)">本周</a>
                                        <a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"
                                           onclick="quickSearch(2)">上周</a>
                                        <a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"
                                           onclick="quickSearch(3)">本月</a>
                                        <a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"
                                           onclick="quickSearch(4)">上月</a>
                                    </p>
                                </div>
                            </li> -->
                            <li>
                               <!--  <label>开始日期： </label>
										<span class="input-icon input-icon-right">
											<input type="text" id="dp_quoteStartDate" name="quoteStartDate"
                                                   class="input-sm" style="width:110px;" onchange="dateFind()"/>
											<i class="ace-icon fa fa-calendar blue"></i>
										</span>
                                <label>至</label>
										<span class="input-icon input-icon-right">
											<input type="text" id="dp_quoteEndDate" name="quoteEndDate" class="input-sm"
                                                   style="width:110px;" onchange="dateFind()"/>
											<i class="ace-icon fa fa-calendar blue"></i>
										</span> -->
                            </li>
                            <%--<a style="float: right" class="tooltip-info" data-rel="tooltip" title="新增">--%>
                            <%--<button onclick="preAddOrModifyCoupon('')" class="btn btn-xs btn-success">新增--%>
                            <%--</button>--%>
                            <%--</a>--%>
                        </ul>
                    </div>
                    <span class="input-icon">
                        <input type="text" placeholder="Search ..." class="nav-search-input"
                               id="coupon_keyword" autocomplete="off" style="height: 30px"/>
                        <i class="ace-icon fa fa-search nav-search-icon"></i>
                    </span>
                </div>
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <input id="memberId" type="hidden">
                            <table id="couponList" class="table table-striped table-bordered table-hover"
                                   STYLE="text-align: center">
                                <thead>
                                <tr>
                                    <th class="center">会员名称</th>
                                    <th class="center">会员性别</th>
                                    <th class="center">电话</th>
                                    <th class="center">邮箱</th>
                                    <th class="center">拥有优惠劵</th>
                                    <%--<th class="center">操作</th>--%>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                        <!-- /.span -->
                    </div>
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </div>
        <div id="inOrder_show_modal" class="modal hiden fade"
             tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width:820px;">
                <div class="modal-content">
                    <div class="modal-header thicktitle">
                        <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                        <span class="modal-title" id="myModalLabel">入库明细</span>
                    </div>
                    <div class="modal-body thickcon"
                         style="width:804px;height: 600px; padding-left: 10px; padding-right: 10px;">
                        <input type="hidden" id="purchase_house_rowno"/>
                        <input type="hidden" id="choise_type" value="0"/>

                        <div class="row">
                            <div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
                                <div class="input-group" style="float:right;width:300px;">
                                    <input type="text" id="keyword" class="form-control search-query"
                                           placeholder="入库单号"/>
									<span class="input-group-btn">
										<button id="searchGoodsBtn" type="button"
                                                class="btn btn-purple btn-sm no-border">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            搜索
                                        </button>
									</span>
                                </div>

                                <div class="col-xs-9" style="width: 800px">
                                    <table id="order_inDtl_list"
                                           class="table table-striped table-bordered table-hover"
                                           style="text-align: center">
                                        <thead>
                                        <tr>
                                            <th class="center">优惠券名称</th>
                                            <th class="center">领取优惠券时间</th>
                                            <th class="center">优惠券面值</th>
                                            <th class="center">优惠券类型</th>
                                            <th class="center">是否使用</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                                <div class="row">
                                    <div id="form_action_btns" class="col-xs-12" style="text-align: center">
                                        <button id="save" onclick="sendCount()"
                                                class="btn btn-sm no-border btn-success">
                                            <i class="ace-icon fa fa-floppy-o"></i>保存
                                        </button>
                                        &nbsp;&nbsp;&nbsp;
                                        <a class="btn btn-sm no-border btn-default" onclick="hiddenModel()">
                                            <i class="ace-icon fa fa-times red2"></i>取消</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.page-content -->
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
<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<!-- ace scripts -->
<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/js/biz/sale/memberCouponList.js"></script>
</body>
</html>



