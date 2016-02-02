<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<style>
    .box {
        overflow: hidden;
        width: 430px;
        padding: 10px 0px 0px 10px;
        background: #CCC;
        margin-top: 10px;
        margin-bottom: 10px;
    }

    .box div {
        height: 110px;
        width: 200px;
        margin-right: 10px;
        border: 1px solid #0099FF;
        float: left;
        margin-bottom: 10px;
        background: #FFF;
        text-align: center;
    }
</style>
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
                <li><a href="#">配送管理</a></li>
                <li><a href="#">设备存放商品管理</a></li>
                <li class="active">设备存放商品列表</li>
            </ul><!-- /.breadcrumb -->
            <%--<div class="nav-search" id="nav-search">--%>
            <%--<tr>--%>
            <%--<td> --%>
            <%--<a onclick="add('')" class="tooltip-info" data-rel="tooltip" title="新增">--%>
            <%--<button class="btn btn-xs btn-success">新增</button>--%>
            <%--</a>										--%>
            <%--</td>									--%>
            <%--</tr>--%>
            <%--</div><!-- /.nav-search -->--%>
        </div>
        <input type="hidden" id="deviceId">
        <div class="page-content">
            <div class="row">
                <div class="col-xs-2">
                    <div style="height: 500px; overflow-y: scroll; border: 1px solid #ddd;">
                        <ul id="org_tree" class="ztree"></ul>
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
                                               id="keyword" autocomplete="off" style="height: 30px"/>
								     <i class="ace-icon fa fa-search nav-search-icon"></i>
							        </span>
                                </li>
										<span style="float: right" class="tooltip-info" data-rel="tooltip" title="新增">
											<button onclick="add('')" class="btn btn-xs btn-success">新增
                                            </button>&nbsp;&nbsp;&nbsp;&nbsp;
										</span>
                            </ul>
                        </div>

                        <div class="col-xs-12">
                            <div>
                                <table id="deviceInfo_list" class="table table-striped table-bordered table-hover">
                                    <%--<div class="nav-search" id="nav-search_seller">--%>
                                    <%--<span class="input-icon">--%>
                                    <%--<input type="text" placeholder="Search ..." class="nav-search-input"--%>
                                    <%--id="keyword" autocomplete="off"/>--%>
                                    <%--<i class="ace-icon fa fa-search nav-search-icon"></i>--%>
                                    <%--</span>--%>
                                    <%--</div>--%>
                                    <thead>
                                    <tr>
                                        <th class="center">设备编号</th>
                                        <th class="center">设备名称</th>
                                        <th class="center">设备地址</th>
                                        <th class="center">商品明细</th>
                                        <th class="center">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div><!-- /.span -->
                    </div><!-- /.row -->
                </div><!-- /.col -->
            </div><!-- /.row -->
        </div><!-- /.page-content -->
    </div><!-- /.main-content -->
</div><!-- /.main-container -->
<!-- 商品列表  -->
<div id="goods_choise_modal" class="modal hiden fade"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:860px;">
        <div class="modal-content">
            <div class="modal-header thicktitle">
                <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                <span class="modal-title" id="myModalLabel">选择商品</span>
            </div>
            <div class="modal-body thickcon"
                 style="width:858px;height: 650px; padding-left: 10px; padding-right: 10px;">
                <div class="row">
                    <div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
                        <div class="input-group" style="float:right;width:300px;">
                            <input type="text" id="keywords" class="form-control search-query" placeholder=""/>
									<span class="input-group-btn">
										<button id="searchGoodsBtn" type="button"
                                                class="btn btn-purple btn-sm no-border">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            搜索
                                        </button>
									</span>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <table id="goods_list" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">商品名称</th>
                                <th class="center">商品单位</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="choise_goods_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
                    <a id="choise_submit" class="btn btn-sm no-border btn-primary disabled"
                       onclick="batchAppendGoods()">选择商品</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 10格子 -->

<div id="goods_modal" class="modal hiden fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" style="width:665px;margin-left: auto;margin-right: auto;">
        <div class="modal-content">
            <div class="row" style="margin-left: auto;margin-right: auto;">
                <div style="overflow:hidden; width:220px; float:left;padding:10px 0 0 10px;background:#CCC;margin-right:-9px;margin-left:10px;
							margin-top:10px;margin-bottom:10px;">
                    <div style="height:590px; width:200px; margin-right:10px; border:1px solid #0099FF; float:left; margin-bottom:10px;
							background:#FFF; text-align:center;">
                        <label id="lab0">0</label><br>
                        <input type="hidden" id="deviceGoodsId0" name="goodsList[0].deviceGoodsId"
                               value="${dGoodsList[0].deviceGoodsId}"/>
                        <input type="text" id="goodsName0" name="goodsList[0].goodsName"
                               value="${dGoodsList[0].goodsName}" readonly class="center"/>
                    </div>
                </div>
                <div class="box">
                    <div id="div1">
                        <label id="lab1">1</label><br>
                        <input type="hidden" id="deviceGoodsId1" name="goodsList[1].deviceGoodsId"
                               value="${dGoodsList[1].deviceGoodsId}"/>
                        <input type="text" id="goodsName1" name="goodsList[1].goodsName"
                               value="${dGoodsList[1].goodsName}" readonly class="center"/>
                    </div>
                    <div id="div2">
                        <label id="lab2">2</label><br>
                        <input type="hidden" id="deviceGoodsId2" name="goodsList[2].deviceGoodsId"
                               value="${dGoodsList[2].deviceGoodsId}"/>
                        <input type="text" id="goodsName2" name="goodsList[2].goodsName"
                               value="${dGoodsList[2].goodsName}" readonly class="center"/>
                    </div>
                    <div id="div3">
                        <label id="lab3">3</label><br>
                        <input type="hidden" id="deviceGoodsId3" name="goodsList[3].deviceGoodsId"
                               value="${dGoodsList[3].deviceGoodsId}"/>
                        <input type="text" id="goodsName3" name="goodsList[3].goodsName"
                               value="${dGoodsList[3].goodsName}" readonly class="center"/>
                    </div>
                    <div id="div4">
                        <label id="lab4">4</label><br>
                        <input type="hidden" id="deviceGoodsId4" name="goodsList[4].deviceGoodsId"
                               value="${dGoodsList[4].deviceGoodsId}"/>
                        <input type="text" id="goodsName4" name="goodsList[4].goodsName"
                               value="${dGoodsList[4].goodsName}" readonly class="center"/>
                    </div>
                    <div id="div4">
                        <label id="lab5">5</label><br>
                        <input type="hidden" id="deviceGoodsId5" name="goodsList[5].deviceGoodsId"
                               value="${dGoodsList[5].deviceGoodsId}"/>
                        <input type="text" id="goodsName5" name="goodsList[5].goodsName"
                               value="${dGoodsList[5].goodsName}" readonly class="center"/>
                    </div>
                    <div id="div4">
                        <label id="lab6">6</label><br>
                        <input type="hidden" id="deviceGoodsId6" name="goodsList[6].deviceGoodsId"
                               value="${dGoodsList[6].deviceGoodsId}"/>
                        <input type="text" id="goodsName6" name="goodsList[6].goodsName"
                               value="${dGoodsList[6].goodsName}" readonly class="center"/>
                    </div>
                    <div id="div4">
                        <label id="lab7">7</label><br>
                        <input type="hidden" id="deviceGoodsId7" name="goodsList[7].deviceGoodsId"
                               value="${dGoodsList[7].deviceGoodsId}"/>
                        <input type="text" id="goodsName7" name="goodsList[7].goodsName"
                               value="${dGoodsList[7].goodsName}" readonly class="center"/>
                    </div>
                    <div id="div4">
                        <label id="lab8">8</label><br>
                        <input type="hidden" id="deviceGoodsId8" name="goodsList[8].deviceGoodsId"
                               value="${dGoodsList[8].deviceGoodsId}"/>
                        <input type="text" id="goodsName8" name="goodsList[8].goodsName"
                               value="${dGoodsList[8].goodsName}" readonly class="center"/>
                    </div>
                    <div id="div4">
                        <label id="lab9">9</label><br>
                        <input type="hidden" id="deviceGoodsId9" name="goodsList[9].deviceGoodsId"
                               value="${dGoodsList[9].deviceGoodsId}"/>
                        <input type="text" id="goodsName9" name="goodsList[9].goodsName"
                               value="${dGoodsList[9].goodsName}" readonly class="center"/>
                    </div>
                    <div id="div4">
                        <label id="lab10">10</label><br>
                        <input type="hidden" id="deviceGoodsId10" name="goodsList[10].deviceGoodsId"
                               value="${dGoodsList[10].deviceGoodsId}"/>
                        <input type="text" id="goodsName10" name="goodsList[10].goodsName"
                               value="${dGoodsList[10].goodsName}" readonly class="center"/>
                    </div>
                    <%--<div id="div4">--%>
                    <%--<label id="lab11">11</label><br>--%>
                    <%--<input  type="hidden" id="deviceGoodsId11"   name="goodsList[11].deviceGoodsId"  value="${dGoodsList[11].deviceGoodsId}"/>--%>
                    <%--<input  type="text"   id="goodsName11" name="goodsList[11].goodsName"  value="${dGoodsList[11].goodsName}" readonly class="center"/>--%>
                    <%--</div>--%>
                    <%--<div id="div4">--%>
                    <%--<label id="lab12">12</label><br>--%>
                    <%--<input  type="hidden" id="deviceGoodsId12"   name="goodsList[12].deviceGoodsId"  value="${dGoodsList[12].deviceGoodsId}"/>--%>
                    <%--<input  type="text"   id="goodsName12" name="goodsList[12].goodsName"  value="${dGoodsList[12].goodsName}" readonly class="center"/>--%>
                    <%--</div>--%>
                </div>
            </div>
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
<script src="${pageContext.request.contextPath}/js/biz/device/deviceGoodsList.js"></script>
</body>
</html>



