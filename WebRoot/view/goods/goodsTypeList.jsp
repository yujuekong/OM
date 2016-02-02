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
                <li><a href="#">商品管理</a></li>
                <li class="active">商品类型管理</li>
            </ul>
            <!-- /.breadcrumb -->
            <%--<div class="nav-search" id="nav-search">--%>
            <%--<a class="tooltip-info" data-rel="tooltip">--%>
            <%--<button onclick="addOrModifyGoodsType('')" class="btn btn-xs btn-success">新增</button>--%>
            <%--</a>--%>
            <%--</div>--%>
            <!-- /.nav-search -->
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-2">
                    <div style="height: 500px; overflow-y: scroll; border: 1px solid #ddd;">
                        <ul id="gt_tree" class="ztree"></ul>
                    </div>
                </div>
                <div class="col-xs-10">
                    <!-- PAGE CONTENT BEGINS -->
                    <div class="row">
                        <div class="searchbar">
                            <ul class="list-inline">
                                <li>
                                    &nbsp;&nbsp;<span class="input-icon">
								        <input type="text" placeholder="Search ..." class="nav-search-input"
                                               id="type_keyword" autocomplete="off" style="height: 30px"/>
								     <i class="ace-icon fa fa-search nav-search-icon"></i>
							        </span>
                                </li>
                                <span style="float: right" class="tooltip-info" data-rel="tooltip" title="新增">
                                    <button onclick="addOrModifyGoodsType('')" class="btn btn-xs btn-success">新增
                                    </button>&nbsp;&nbsp;&nbsp;&nbsp;
                                </span>
                            </ul>
                        </div>
                        <div class="col-xs-12">
                            <table id="goodsType_list" class="table table-striped table-bordered table-hover"
                                   style="text-align: center">
                                <%--<div class="nav-search" id="nav-search_seller">--%>
                                <%--<span class="input-icon">--%>
                                <%--<input type="text" placeholder="Search ..." class="nav-search-input"--%>
                                <%--id="type_keyword" autocomplete="off"/>--%>
                                <%--<i class="ace-icon fa fa-search nav-search-icon"></i>--%>
                                <%--</span>--%>
                                <%--</div>--%>
                                <thead>
                                <tr>
                                    <th class="center">上级商品类别</th>
                                    <th class="center">商品类型编号</th>
                                    <th class="center">商品类型名称</th>
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
                </div>
                <!-- /.col -->
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
    window.jQuery || document.write("<script src='${pageContext.request.contextPath}/js/jquery.min.js'>" + "<" + "/script>")

</script>
<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>

<!-- ace scripts -->
<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
<script src="${pageContext.request.contextPath}/js/biz/goods/goodsTypeList.js"></script>
<script type="text/javascript">
    if ('${updateF}' != '') {
        alert('${updateF}');
    }
    if ('${msg}' != '') {
        alert('${msg}');
    }
</script>
</body>
</html>



