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
                <li class="active">商品调拨管理</li>
                <li class="active">调拨管理</li>
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
                    <div class="searchbar">
                        <ul class="list-inline">
                            <li>
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
                            </li>
                            <li>
                                <label>开始日期： </label>
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
										</span>
                                    <span class="input-icon">
								        <input type="text" placeholder="Search ..." class="nav-search-input"
                                               id="allot_keyword" autocomplete="off" style="height: 30px"/>
								     <i class="ace-icon fa fa-search nav-search-icon"></i>
							        </span>
                            </li>
                            <a style="float: right" class="tooltip-info" data-rel="tooltip" title="新增">
                                <button onclick="preAddOrModifyOrderIn('')" class="btn btn-xs btn-success">新增
                                </button>
                            </a>
                        </ul>
                    </div>
                </div>
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="col-xs-12">
                            <input id="warehousingId" type="hidden">
                            <input id="allotStatus" type="hidden">
                            <table id="allot_order_list" class="table table-striped table-bordered table-hover"
                                   STYLE="text-align: center">
                                <thead>
                                <tr>
                                    <th class="center">调拨单号</th>
                                    <th class="center">出库仓库</th>
                                    <th class="center">入库仓库</th>
                                    
                                    <th class="center">创建日期</th>
                                    <th class="center">
										<select onchange="searchOrderStatus()" id="orderStatus" style="border: none;">
											<option value=" ">审核状态</option>
												<option value="0">已审核</option>
												<option value="1">未审核</option>
												<!-- <option value="2">已通过</option> -->
										</select>
									</th>
									<th class="center">明细</th>
                                    <th class="center">操作</th>
                                </tr>
                                </thead>
                                <tbody>

                                </tbody>
                            </table>
                        </div>
                        <!-- /.span -->
                    </div>
                    <!-- /.row -->
                    <div class="hr hr-18 dotted hr-double"></div>
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </div>
<!-- 商品选择弹出窗开始 -->
<div id="goods_choise_modal" class="modal hiden fade"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:860px;">
        <div class="modal-content">
            <div class="modal-header thicktitle">
                <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                <span class="modal-title" id="myModalLabel">选择商品</span>
            </div>
            <div class="modal-body thickcon"
                 style="width:858px;height: 630px; padding-left: 10px; padding-right: 10px;">
                <input type="hidden" id="purchase_goods_rowno"/>
                <input type="hidden" id="choise_goods" value="0"/>

                <div class="row">
                   <!--  <div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
                        <div class="input-group" style="float:right;width:300px;">
                            <input type="text" id="keyword" class="form-control search-query" placeholder="商品名称/商品编号"/>
									<span class="input-group-btn">
										<button id="searchGoodsBtn" type="button"
                                                class="btn btn-purple btn-sm no-border">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            搜索
                                        </button>
									</span>
                        </div>
                    </div> -->
                    <div class="col-xs-3">
                        <div style="height: 410px;overflow-y: scroll; border: 1px solid #ddd;">
                            <ul id="gt_tree" class="ztree"></ul>
                        </div>
                    </div>
                    <div class="col-xs-9">
                    <input type="hidden" id="allotId" />
                        <table id="search_goods_table" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">商品名称</th>
                                <th class="center">商品类型</th>
                                <th class="center">商品条码</th>
                                <th class="center">预计数量</th>
                                <th class="center">实际数量</th>
                                <th class="center">状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 商品选择弹出窗结束 -->
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
<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>

<!-- ace scripts -->
<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/js/biz/inventory/allotOrderList.js"></script>

<script type="text/javascript">

</script>
</body>
</html>



