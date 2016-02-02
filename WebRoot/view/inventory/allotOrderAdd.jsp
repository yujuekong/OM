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
                <li><a href="${pageContext.request.contextPath}/view/inventory/allotOrderList.jsp">调拨单管理</a></li>
                <li class="active">新增调拨单</li>
            </ul>
            <!-- /.breadcrumb -->

            <div class="nav-search" id="nav-search">
                <a href="javascript:history.back()">
                    <i class="ace-icon fa fa-arrow-left"></i>返回
                </a>
            </div>
            <!-- /.nav-search -->
        </div>
		<input type="hidden" id="orgId" value="${account.orgId }"/>
        <div class="page-content">
            <form action="${pageContext.request.contextPath}/view/inventory/allotOrder/saveOrUpdateAllotOrder.action"
                  method="post" onsubmit="return checkForm();">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <div class="col-xs-12">
                                <div class="widget-box">
                                    <div class="widget-header widget-header-small">
                                        <h5 id="h5Id">新增调拨单信息</h5>
                                    </div>
                                    <div class="widget-main">
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right"> 调拨单ID:
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageAllot.allotId}"
                                                               type="text" name="storageAllot.allotId"
                                                               id="allotId" class="required"
                                                               style="width:450px;"/>
													</span>
                                        </div>
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right"> 调拨单编号:
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageAllot.allotNo}"
                                                               type="text" name="storageAllot.allotNo"
                                                               id="allotId" class="required"
                                                               style="width:450px;"/>
													</span>
                                        </div>
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right"> 调拨单编号:
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageAllot.allotStatus}"
                                                               type="text" name="storageAllot.allotStatus"
                                                               id="allotId" class="required"
                                                               style="width:450px;"/>
													</span>
                                        </div>
                                         <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right"> 创建人:
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageAllot.createUser}"
                                                               type="text" name="storageAllot.createUser"
                                                               id="allotId" class="required"
                                                               style="width:450px;"/>
													</span>
                                        </div>
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right"> 审核人:
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageAllot.checkUser}"
                                                               type="text" name="storageAllot.checkUser"
                                                               id="allotId" class="required"
                                                               style="width:450px;"/>
													</span>
                                        </div>
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right"> 仓库审核人:
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageAllot.auditingUser}"
                                                               type="text" name="storageAllot.auditingUser"
                                                               id="allotId" class="required"
                                                               style="width:450px;"/>
													</span>
                                        </div>
                                         <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right"> 仓库审核时间:
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageAllot.auditingDate}"
                                                               type="text" name="storageAllot.auditingDate"
                                                               id="allotId" class="required"
                                                               style="width:450px;"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right"><i class="red">*</i> 出库仓库：</label>
													<span class="input-icon input-icon-right">
														<input id="inventoryName" value="${startWarehouse.warehouseName}" type="text" class="required" style="width:450px;" onclick="choiseInventory(this)"/>
                                                        <input id="inventoryId" name="storageAllot.startWarehouse" value="${storageAllot.startWarehouse }" type="hidden" class="required" style="width:450px;"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right"><i class="red">*</i> 入库仓库：</label>
													<span class="input-icon input-icon-right">
														<input id="inventoryName2" value="${endWarehouse.warehouseName}" type="text" class="required" style="width:450px;" onclick="choiseInventory2(this)"/>
                                                        <input id="inventoryId2" name="storageAllot.endWarehouse" value="${storageAllot.endWarehouse }" type="hidden" class="required" style="width:450px;"/>
													</span>
                                        </div>
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right"> 创建日期：</label>
													<span class="input-icon input-icon-right">
														<input value="${storageAllot.createDate}" type="text" class="required" style="width:450px;" name="storageAllot.createDate"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">&nbsp;调拨原因：</label>
													<span class="input-icon input-icon-right">
                                                        <textarea id="warehousingEntry_remark" class="required"
                                                                  style="width:450px;"
                                                                  name="storageAllot.allotReason">${storageAllot.allotReason}</textarea>
													</span>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 添加商品开始 -->
                <div class="form-group">

                    <div class="col-xs-12">
                        <div class="widget-box">
                            <div class="widget-header widget-header-small">
                                <h5>添加商品</h5>
                            </div>
                            <div class="widget-body">
                                <div class="widget-main no-padding">
                                    <table id="" class="table table-condensed table-bordered"
                                           style="text-align: center">
                                        <thead>
                                        <tr>
                                            <th class="center">行号</th>
                                            <th class="center" style="width:150px">商品</th>
                                            <th class="center">商品条码</th>
                                            <th class="center" style="width:150px">计划入库数量</th>
                                            <th class="center">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="purchase_goods_list">
                                        <c:if test="${empty storageWarehousingDtls}">
                                            <tr>
                                                <td class="center">1</td>
                                                <td>
                                                    <!-- <input type="hidden" id="goodsId"
                                                           style="text-align: center" onclick="choiseGoods(this)"
                                                           />
                                                    <input type="text" class="center"> -->
                                                </td>
                                                <td><input type="hidden" id="checkEmpty" value="1"></td>
                                                <td>
                                                    <input type="text" id="expectNum" class="input-sm limited" maxlength="500"
                                                           style="text-align: center"
                                                           />
                                                </td>
                                                <td>
                                                    <input type="hidden"/>
                                                    <a href="javascript:void(0)" onclick="removeGoodsItem(this)">移除</a>
                                                </td>
                                            </tr>
                                        </c:if>
                                        <c:forEach var="sd" items="${storageAllotDt}" varStatus="rowIndex">
                                            <tr>
                                                <td class="center">
                                                ${rowIndex.index + 1}
                                                </td>
                                                <td>
                                                    <input type="hidden" value="${sd.goodsId}" id="goodsId" class="center"
                                                           name="storageAllotDt[${rowIndex.index}].goodsId"/>
                                                    <input type="text" id="goodsName" class="center" value="${sd.goodsName}" style="border-left: 0;border-right: 0;border-top: 0;border-bottom: 0px;color:#222222;font-size:13px;"/>
                                                </td>
                                                <td class="center">${sd.goodsBarCode}</td>
                                                <td><input type="text" id="expectNum" class="center" value="${sd.expectNumber}"
                                                           name="storageAllotDt[${rowIndex.index}].expectNumber"></td>
                                                 <td>
                                                 	<input type="hidden" value="${sd.allotDtlId}" name="storageAllotDt[${rowIndex.index}].allotDtlId"/>
                                                    <a href="javascript:void(0)" onclick="removeGoodsItem(this)">移除</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xs-12">
                        <div class="row">
                            <div class="col-xs-6">
                                <a class="btn btn-xs no-border btn-info"
                                   onclick="batchChoiseGoods()">
                                    <i class="ace-icon fa glyphicon-plus bigger-120"></i>
                                    添加商品
                                </a>
                            </div>

                        </div>
                    </div>
                </div>
                <!-- 添加商品 结束 -->
                <!-- /.row -->
                <div class="row">
                    <div id="form_action_btns" class="col-xs-12" style="text-align: center">
                        <button id="save" class="btn btn-sm no-border btn-success" >
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
<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>

<script src="${pageContext.request.contextPath}/js/biz/inventory/allotOrderAdd.js"></script>
<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript">
    $("#submenu-menu-device-info").addClass("active");
    $("#menu-device").addClass("active");
    $("#menu-device").addClass("open");
</script>
<!-- 出库仓库选择弹出窗开始 -->
<div id="outhouse_choise_modal" class="modal hiden fade"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:860px;">
        <div class="modal-content">
            <div class="modal-header thicktitle">
                <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                <span class="modal-title" id="myModalLabel">选择仓库</span>
            </div>
            <div class="modal-body thickcon"
                 style="width:858px;height: 630px; padding-left: 10px; padding-right: 10px;">
                <input type="hidden" id="purchase_house_rowno"/>
                <input type="hidden" id="choise_type" value="0"/>

                <div class="row">
                    <div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
                        <div class="input-group" style="float:right;width:300px;">
                            <input type="text" id="keyword" class="form-control search-query" placeholder="仓库名称"/>
									<span class="input-group-btn">
										<button id="searchGoodsBtn" type="button"
                                                class="btn btn-purple btn-sm no-border">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            搜索
                                        </button>
									</span>
                        </div>
                    </div>
                    <div class="col-xs-3" style="width:10px">
                        <div
							style="height: 410px;width:1px ;scroll; border: 0px solid #ddd;">
								<ul id="device_tree" class="ztree"></ul>
							</div>
                    </div>
                    <div class="col-xs-9" style="width: 790px">
                        <table id="outhouse_list" class="table table-striped table-bordered table-hover"
                               style="text-align: center">
                            <thead>
                            <tr>
                                <th class="center">仓库名称</th>
                                <th class="center">仓库编号</th>
                                <th class="center">仓库地址</th>
                                <th class="center">创建日期</th>
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

<!-- 入库仓库选择弹出窗开始 -->
<div id="inhouse_choise_modal" class="modal hiden fade"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:860px;">
        <div class="modal-content">
            <div class="modal-header thicktitle">
                <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                <span class="modal-title" id="myModalLabel">选择仓库</span>
            </div>
            <div class="modal-body thickcon"
                 style="width:858px;height: 630px; padding-left: 10px; padding-right: 10px;">
                <input type="hidden" id="purchase_house_rowno"/>
                <input type="hidden" id="choise_type" value="0"/>

                <div class="row">
                    <div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
                        <div class="input-group" style="float:right;width:300px;">
                            <input type="text" id="keyword" class="form-control search-query" placeholder="仓库名称"/>
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
                            <ul id="org2_tree" class="ztree"></ul>
                        </div>
                    </div>
                    <div class="col-xs-9">
                        <table id="inhouse_list" class="table table-striped table-bordered table-hover"
                               style="text-align: center">
                            <thead>
                            <tr>
                                <th class="center">仓库名称</th>
                                <th class="center">仓库编号</th>
                                <th class="center">仓库地址</th>
                                <th class="center">创建日期</th>
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
                    <div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
                        <div class="input-group" style="float:right;width:300px;">
                            <input type="text" id="goodsKey" class="form-control search-query" placeholder="商品名称/商品编号"/>
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
                        <table id="search_goods_table" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label class="position-relative">
                                        <input type="checkbox" class="ace"/>
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th class="center">商品名称</th>
                                <th class="center">商品类型</th>
                                <th class="center">商品条码</th>
                                <th class="center">状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="choise_goods_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
                    <a id="choise_submit" class="btn btn-sm no-border btn-primary disabled"
                       onclick="batchAppendGoods()">添加商品</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>


