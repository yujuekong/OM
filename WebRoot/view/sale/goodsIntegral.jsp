<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../commons/head.jsp" />

<body class="no-skin">
<div class="main-container" id="main-container">
    <div>
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">首页</a>
                </li>
                <li><a href="#">销售管理</a></li>
                <li class="active">商品积分管理</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
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
								<span  style="float:right;" class="tooltip-info" data-rel="tooltip" title="新增">
									<button onclick="preAddOrModifyIntegral('')" class="btn btn-xs btn-success">新增
                                    </button>&nbsp;&nbsp;&nbsp;&nbsp;
								</span>
                            </ul>
                            <input type="hidden" id="orgId" value="${account.orgId }"/>
                        </div>
                        <div class="col-xs-12">
                            <table id="gameInfo_list" class="table table-striped table-bordered table-hover" style="text-align: center">
                                <thead>
                                <tr>
                                    <th class="center">商品名称</th>
                                    <th class="center">积分值</th>
                                    <th class="center">是否使用</th>
                                    <th class="center">操作</th>
                                </tr>
                                </thead>

                                <tbody>
                                </tbody>
                            </table>
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                    <div><br></div>
                    <%-- <div class="col-xs-6">
                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath}/view/advert/advertInfo/saveAdvertInfoAdd.action" class="tooltip-info" data-rel="tooltip" title="维修">
                                    <button class="btn btn-xs btn-success">新增</button>
                                </a>
                                <!-- <button class="btn btn-xs btn-danger">批量删除</button> -->
                            </td>
                            <td style="vertical-align:top;">

                            </td>
                        </tr>
                    </div>	 --%>

                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.page-content -->
    </div><!-- /.main-content -->
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- <![endif]-->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='${pageContext.request.contextPath}/js/jquery.min.js'>"+"<"+"/script>");
</script>
<!-- page specific plugin scripts -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.min.js"></script>

<!-- ace scripts -->
<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
<script src="${pageContext.request.contextPath}/js/biz/sale/goodsIntegral.js"></script>
<script type="text/javascript">
    $("#submenu-menu-advert-info").addClass("active");
    $("#menu-advert").addClass("active");
    $("#menu-advert").addClass("open");
</script>
</body>
</html>



