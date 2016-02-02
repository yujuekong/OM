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

            <ul id="uiId" class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">首页</a>
                </li>
                <li><a href="#">仓库管理</a></li>
                <li><a href="${pageContext.request.contextPath}/view/inventory/inList.jsp">商品出库</a></li>
                <li class="active">新增出库商品信息</li>
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
            <form action="${pageContext.request.contextPath}/view/inventory/InListDetail/saveOrUpdateEntryDetail.action"
                  method="post">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <div class="col-xs-12">
                                <div class="widget-box">
                                    <div class="widget-header widget-header-small">
                                        <h5 id="h5Id">新增出库商品信息</h5>
                                    </div>
                                    <div class="widget-main">
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                入库单明细ID:
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageWarehousingDtl.warehousingDtlId}"
                                                               type="text" name="storageWarehousingDtl.warehousingDtlId"
                                                               id="in_id" class="required" style="width:450px;"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                入库单编号：
                                            </label>
													<span class="input-icon input-icon-right">
														<input disabled="disabled" value="${storageWarehousingDtl.storageWarehousingEntry.warehousingNo}" type="text"
                                                               id="storageWarehousingDtl_SellerInfo" class="required" style="width:450px;"  />
                                                          <input id="warehousingEntryId" name="storageWarehousingDtl.warehousingEntryId"
                                                                 value="${storageWarehousingDtl.warehousingEntryId}"
                                                                 type="hidden" class="required" style="width:450px;"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                商&nbsp;品：
                                            </label>
													<span class="input-icon input-icon-right">
														<input id="goodsName"
                                                               value="${storageWarehousingDtl.goodsInfo.goodsName}"
                                                               type="text" class="required" style="width:450px;"
                                                               onclick="choiseGoods(this)"/>
													    <input id="goodsId" name="storageWarehousingDtl.goodsId"
                                                               value="${storageWarehousingDtl.goodsId}"
                                                               type="hidden" class="required" style="width:450px;"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                入库数量：
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageWarehousingDtl.warehousingCount}" type="text"
                                                               id="storageDeliveryOrder_deliveryNo" class="required" style="width:450px;"
                                                               name="storageWarehousingDtl.warehousingCount"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                入库单价：
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageWarehousingDtl.warehousingPrice}" type="text"
                                                               id="storageDeliveryOrder_bizType" class="required"
                                                               style="width:450px;"
                                                               name="storageWarehousingDtl.warehousingPrice"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                入库金额：
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageWarehousingDtl.warehousingAmount}" type="text"
                                                               id="storageDeliveryOrder_remark" class="required"
                                                               style="width:450px;" name="storageWarehousingDtl.warehousingAmount"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                明细备注:
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageWarehousingDtl.remark}" type="text"
                                                               id="storageDeliveryOrder_deliveryDate" class="required"
                                                               style="width:450px;"
                                                               name="storageWarehousingDtl.remark"/>
													</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
                <div class="row">
                    <div id="form_action_btns" class="col-xs-12" style="text-align: center">
                        <button id="save" class="btn btn-sm no-border btn-success" onclick="saveOp(0)">
                            <i class="ace-icon fa fa-floppy-o"></i>保存
                        </button>
                        &nbsp;&nbsp;&nbsp;
                        <a class="btn btn-sm no-border btn-default" onclick="cancelOp()">
                            <i class="ace-icon fa fa-times red2"></i>取消</a>
                    </div>
                </div>
            </form>
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

<script src="${pageContext.request.contextPath}/js/biz/inventory/inDetailAddOrUpdata.js"></script>


<script type="text/javascript">
    $("#submenu-menu-device-info").addClass("active");
    $("#menu-device").addClass("active");
    $("#menu-device").addClass("open");
</script>
<!-- 仓库选择弹出窗开始 -->
<div id="goods_choise_modal" class="modal hiden fade"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:860px;">
        <div class="modal-content">
            <div class="modal-header thicktitle">
                <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                <span class="modal-title" id="myModalLabel">选择设备</span>
            </div>
            <div class="modal-body thickcon"
                 style="width:858px;height: 530px; padding-left: 10px; padding-right: 10px;">
                <input type="hidden" id="purchase_goods_rowno"/>
                <input type="hidden" id="choise_type" value="0"/>

                <div class="row">
                    <div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
                        <div class="input-group" style="float:right;width:300px;">
                            <input type="text" id="keyword" class="form-control search-query" placeholder="设备名称/设备编号"/>
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
                            <ul id="org_tree" class="ztree"></ul>
                        </div>
                    </div>
                    <div class="col-xs-9">
                        <table id="goods_list" class="table table-striped table-bordered table-hover"
                               style="text-align: center">
                            <thead>
                            <tr>
                                <th class="center">商品编号</th>
                                <th class="center">商品名称</th>
                                <th class="center">商品分类编号</th>
                                <th class="center">商品单位</th>
                                <th class="center">商品拼音</th>
                                <th class="center">商品状态</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="choise_seller_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
                    <a id="choise_submit_2" class="btn btn-sm no-border btn-primary disabled"
                       onclick="batchAppendGoods()">加入广告内容</a>
                </div>
            </div>
        </div>
    </div>
</div>