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
                <li><a href="${pageContext.request.contextPath}/view/sale/goodsIntegral.jsp">商品积分管理</a></li>
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
                <form action="${pageContext.request.contextPath}/view/sale/goodsIntegral/AddOrModifyIntegral.action"
                      method="post">
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
                                                <label class="col-sm-2 control-label no-padding-right">商品积分ID： </label>
                                                <span class="input-icon input-icon-right">
                                                    <input type="text" value="${goodsIntegral.goodsIntegralId}"
                                                           id="goodsIntegralId" class="required"
                                                           style="width:250px;" name="goodsIntegral.goodsIntegralId"
                                                    />
                                                </span>
                                                </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">商品名称：</label>
                                                <span class="input-icon input-icon-right">
                                                    <input type="text" value="${goodsIntegral.goodsName}"
                                                           name="goodsIntegral.goodsName"
                                                           id="goodsName" class="required"
                                                           onclick="chooseGoods()"
                                                           style="width:250px;"/>
                                                    <input id="goodsId" value="${goodsIntegral.goodsId}"
                                                           name="goodsIntegral.goodsId" hidden="hidden">
                                                </span>
                                                </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">积分值：</label>
                                                <span class="input-icon input-icon-right">
                                                    <input type="text" value="${goodsIntegral.integralNumber}"
                                                           id="integralNumber" class="required"
                                                           style="width:250px;" name="goodsIntegral.integralNumber"
                                                    />
                                                </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label no-padding-right">是否使用：</label>
                                                <span class="input-icon input-icon-right">
                                                    <select id="isUser" name="goodsIntegral.isUser">
                                                        <option value="">请选择</option>
                                                        <option value="0" ${goodsIntegral.isUser == 0 ?"selected= 'selected'" : ""}>
                                                            正常
                                                        </option>
                                                        <option value="1" ${goodsIntegral.isUser == 1 ?"selected= 'selected'" : ""}>
                                                            禁用
                                                        </option>
                                                    </select>
													</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div id="form_action_btns" class="col-xs-12" style="text-align: center">
                                        <button type="submit" id="save" class="btn btn-sm no-border btn-success">
                                            <i class="ace-icon fa fa-floppy-o"></i>保存
                                        </button>
                                        &nbsp;&nbsp;&nbsp;
                                        <a class="btn btn-sm no-border btn-default" onclick="history.back()">
                                            <i class="ace-icon fa fa-times red2"></i>取消</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <!-- /.row -->

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

                            <div class="row">
                                <div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
                                    <div class="input-group" style="float:right;width:300px;">
                                        <input type="text" id="keyword" class="form-control search-query"
                                               placeholder="设备名称/设备编号"/>
									<span class="input-group-btn">
										<button id="searchGoodsBtn" type="button"
                                                class="btn btn-purple btn-sm no-border">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            搜索
                                        </button>
									</span>
                                    </div>
                                </div>
                                <div class="col-xs-3">
                                    <div style="height: 410px;overflow-y: scroll; border: 1px solid #ddd;">
                                        <ul id="gt_tree" class="ztree"></ul>
                                    </div>
                                </div>
                                <div class="col-xs-9">
                                    <table id="goods_list"
                                           class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th class="center">商品名称</th>
                                            <th class="center">商品分类编号</th>
                                            <th class="center">商品单位</th>
                                            <th class="center">商品拼音</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div id="choise_goods_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
                                <a id="choise_submit" class="btn btn-sm no-border btn-primary disabled"
                                   onclick="batchAppendGoods()">完成</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.page-content -->
    </div>
    <!-- /.main-content -->
</div>
<!-- /.main-container -->

<!-- basic scripts -->

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- <![endif]-->

<script type="text/javascript">
    window.jQuery || document.write("<script src='${pageContext.request.contextPath}/js/jquery.min.js'>" + "<" + "/script>");
</script>

<!-- page specific plugin scripts -->
<%-- <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> --%>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js"></script>
<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>

<script src="${pageContext.request.contextPath}/js/biz/sale/goodsIntegralUpdate.js"></script>


</body>
</html>
