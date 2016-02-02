<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../commons/head.jsp"/>
<body class="no-skin">
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>
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
                <li><a href="#">仓储管理</a></li>
                <li class="active">仓库信息管理</li>
            </ul>
            <!-- /.breadcrumb -->

            <div class="nav-search" id="nav-search">
                <%--<a onclick="preAddOrModifystorageWarehouse('')" class="tooltip-info" data-rel="tooltip"--%>
                <%--title="维修">--%>
                <%--<button class="btn btn-xs btn-success">新增</button>--%>
                <%--</a>--%>
            </div>
            <!-- /.nav-search -->

        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-2" id="searchTree">
                    <div style="height: 500px; overflow-y: scroll; border: 1px solid #ddd;">
                        <ul id="org_tree" class="ztree"></ul>
                    </div>
                </div>
                <div class="col-xs-10" id="houseTable">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="searchbar">
                            <ul class="list-inline">
                                <li>
                                    &nbsp;&nbsp;<span class="input-icon">
								        <input type="text" placeholder="Search ..." class="nav-search-input"
                                               id="house_keyword" autocomplete="off" style="height: 30px"/>
								     <i class="ace-icon fa fa-search nav-search-icon"></i>
							        </span>
                                </li>
                            <span style="float: right" class="tooltip-info" data-rel="tooltip" title="新增">
                                <button onclick="preAddOrModifystorageWarehouse('')" class="btn btn-xs btn-success">新增
                                </button>&nbsp;&nbsp;&nbsp;&nbsp;
                            </span>
                            </ul>
                        </div>
                        <div class="col-xs-12">
                            <div>
                                <input id="warehouseId" type="hidden">
                                <input id="orgId" type="hidden" value="${account.orgId}">
                                <table id="warehouse_list" class="table table-striped table-bordered table-hover"
                                       style="text-align: center">
                                    <thead>
                                    <tr>
                                        <th class="center">仓库编号</th>
                                        <th class="center">仓库名称</th>
                                        <th class="center">仓库地址</th>
                                        <th class="center">仓库大小</th>
                                        <th class="center">仓库创建日期</th>
                                        <th class="center">仓库库存</th>
                                        <th class="center">仓库状态</th>
                                        <th class="center">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>

                        </div>
                        <!-- /.span -->
                    </div>
                    <!-- /.row -->
                    <div><br></div>
                </div>
                <!-- /.col -->


                <div id="house_choise_modal" class="modal hiden fade"
                     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog" style="width:820px;">
                        <div class="modal-content">
                            <div class="modal-header thicktitle">
                                <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                                <span class="modal-title" id="myModalLabel">商品库存</span>
                            </div>
                            <div class="modal-body thickcon"
                                 style="width:804px;height: 600px; padding-left: 10px; padding-right: 10px;">
                                <input type="hidden" id="purchase_house_rowno"/>
                                <input type="hidden" id="choise_type" value="0"/>

                                <div class="row">
                                    <div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
                                       <!--  <div class="input-group" style="float:right;width:300px;">
                                            <input type="text" id="keyword" class="form-control search-query"
                                                   placeholder="商品名称"/>
									<span class="input-group-btn">
										<button id="searchGoodsBtn" type="button"
                                                class="btn btn-purple btn-sm no-border">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            搜索
                                        </button>
									</span>
                                        </div> -->

                                        <div class="col-xs-9" style="width: 800px">
                                            <table id="search_goods_table"
                                                   class="table table-striped table-bordered table-hover"
                                                   style="text-align: center">
                                                <thead>
                                                <tr>
                                                    <th class="center">商品名称</th>
                                                    <th class="center">商品数量</th>
                                                    <th class="center">商品单位</th>
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
                    <!-- /.page-content -->
                </div>
                <!-- /.page-content -->
            </div>
            <!-- /.main-content -->
        </div>
    </div>
</div>
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
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>

<!-- ace scripts -->
<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
<script src="${pageContext.request.contextPath}/js/biz/inventory/warehouse.js"></script>
<%--<script src="${pageContext.request.contextPath}/js/biz/inventory/warehouseList.js"></script>--%>

</body>
</html>
