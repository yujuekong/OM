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
                <li><a href="${pageContext.request.contextPath}/view/inventory/outList.jsp">商品出库</a></li>
                <li class="active">
                    <c:choose>
                        <c:when test="${storageDeliveryOrder.deliveryOrderId==null}">新增商品出库单</c:when>
                        <c:otherwise>修改商品出库单</c:otherwise>
                    </c:choose>
                </li>
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
            <form id="form" action="${pageContext.request.contextPath}/view/inventory/deliveryOrder/saveOrUpdateDeliveryOrder.action"
                  method="post">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <div class="col-xs-12">
                                <div class="widget-box">
                                    <div class="widget-header widget-header-small">
                                        <h5 id="h5Id">
                                            <c:choose>
                                                <c:when test="${warehousingEntry.warehousingEntryId==null}">新增出库单信息</c:when>
                                                <c:otherwise>修改出库单信息</c:otherwise>
                                            </c:choose>
                                        </h5>
                                    </div>
                                    <div class="widget-main">
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right"> 入库单ID:
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageDeliveryOrder.deliveryOrderId}"
                                                               type="text" name="storageDeliveryOrder.deliveryOrderId"
                                                               id="deliveryOrderId"
                                                               style="width:450px;"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                仓&nbsp;库：
                                            </label>
													<span class="input-icon input-icon-right">
														<input id="houseName" readonly
                                                               value="${storageDeliveryOrder.storageWarehouse.warehouseName}"
                                                               type="text" class="required" style="width:450px;"
                                                               onclick="choiseHouse(this)"/>
													    <input id="houseId" name="storageDeliveryOrder.warehouseId"
                                                               value="${storageDeliveryOrder.warehouseId}"
                                                               type="hidden" class="" style="width:450px;"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                交接用户：
                                            </label>
													<span class="input-icon input-icon-right">
														<input id="checkUser" readonly
                                                               value="${storageDeliveryOrder.checkUser.realName}"
                                                               type="text" class="" style="width:450px;"
                                                               onclick="choiseCheckUser(this)"/>
                                                        <input id="checkUserId" name="storageDeliveryOrder.checkUserId"
                                                               value="${storageDeliveryOrder.checkUserId}"
                                                               type="hidden" class="required" style="width:450px;"/>
													</span>
                                        </div>
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                出库单编号：
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageDeliveryOrder.deliveryNo}" type="text"
                                                               id="storageDeliveryOrder_deliveryNo" class=""
                                                               style="width:450px;"
                                                               name="storageDeliveryOrder.deliveryNo"/>
													</span>
                                        </div>
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                业务类型：
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageDeliveryOrder.bizType==1?"配送出库":"调拨出库"}" type="text"
                                                               id="storageDeliveryOrder_bizType" class=""
                                                               style="width:450px;"
                                                               name="storageDeliveryOrder.bizType"/>
                                                        <%--<select id="storageDeliveryOrder_bizType"--%>
                                                                <%--name="storageDeliveryOrder.bizType">--%>
                                                            <%--<option value="-1">请选择</option>--%>
                                                            <%--<option value="1" ${storageDeliveryOrder.bizType == 1 ?"selected= 'selected'" : ""}>--%>
                                                                <%--配送出库--%>
                                                            <%--</option>--%>
                                                            <%--<option value="2" ${storageDeliveryOrder.bizType == 1 ?"selected= 'selected'" : ""}>--%>
                                                                <%--调拨出库--%>
                                                            <%--</option>--%>
                                                        <%--</select>--%>
													</span>
                                        </div>
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                出库日期：
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageDeliveryOrder.deliveryDate}" type="text"
                                                               id="storageDeliveryOrder_deliveryDate" class=""
                                                               style="width:450px;"
                                                               name="storageDeliveryOrder.deliveryDate"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                要求出库日期：
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageDeliveryOrder.requestDate}" type="text"
                                                               id="storageDeliveryOrder_requestDate" class="required"
                                                               style="width:450px;" readonly
                                                               name="storageDeliveryOrder.requestDate"/>
													</span>
                                        </div>
                                        <div class="form-group" hidden>
                                            <label class="col-sm-2 control-label no-padding-right">
                                                清洗用户：
                                            </label>
													<span class="input-icon input-icon-right">
														<input id="cleanUser"
                                                               value="${storageDeliveryOrder.cleanUser.realName}"
                                                               type="text" class="" style="width:450px;"
                                                               onclick="choiseCleanUser(this)"/>
                                                        <input id="cleanUserId" name="storageDeliveryOrder.cleanUserId"
                                                               value="${storageDeliveryOrder.cleanUserId}"
                                                               type="hidden" class="" style="width:450px;"/>
													</span>
                                        </div>
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                录入用户：
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageDeliveryOrder.user.realName}" type="text"
                                                               id="storageDeliveryOrder.sysUser.realName"
                                                               class=""
                                                               style="width:450px;"
                                                                />
                                                        <input value="${account.userId}"
                                                               type="text"
                                                               id="warehousingEntry_sysUser" class=""
                                                               style="width:450px;"
                                                               name="storageDeliveryOrder.userId"/>
													</span>
                                        </div>
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                创建日期：
                                            </label>
													<span class="input-icon input-icon-right">
														<input value="${storageDeliveryOrder.createDate}" type="text"
                                                               id="warehousingEntry_createDate" class=""
                                                               style="width:450px;"
                                                               name="storageDeliveryOrder.createDate"/>
													</span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                出库备注：
                                            </label>
												<span class="input-icon input-icon-right">
                                                        <textarea id="warehousingEntry_remark" class=""
                                                                  style="width:450px;"
                                                                  name="storageDeliveryOrder.remark">${storageDeliveryOrder.remark}</textarea>
												</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 添加站点 开始 -->
                <div class="form-group">
                    <input id="agent" type="hidden">
                    <input id="deviceName" type="hidden">

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
                                            <th class="center">商品</th>
                                            <th class="center">出库数量</th>
                                            <th class="center">明细备注</th>
                                            <th class="center">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="purchase_goods_list">
                                        <c:if test="${empty storageDeliveryDtls}">
                                            <tr>
                                                <td class="center">1</td>
                                                <td>
                                                    <input type="text" id="goodsId"
                                                           style="text-align: center"  disabled/></td>
                                                </td>
                                                <td>
                                                    <input type="text"
                                                           style="text-align: center;"
                                                           name="storageDeliveryDtls[0].remark" disabled/>
                                                </td>
                                                <td>
                                                    <input type="text"
                                                           style="text-align: center; width: 400px"
                                                           name="storageDeliveryDtls[0].remark" disabled/>
                                                </td>
                                                <td>
                                                    <input type="hidden"/>
                                                    <a href="javascript:void(0)" onclick="removeGoodsItem(this)">移除</a>
                                                </td>
                                            </tr>
                                        </c:if>
                                        <c:forEach var="sd" items="${storageDeliveryDtls}" varStatus="rowIndex">
                                            <tr>
                                                <td class="center">${rowIndex.index + 1}</td>
                                                <td>
                                                    <input type="hidden" value="${sd.goodsId}" class="center"
                                                           name="storageDeliveryDtls[${rowIndex.index}].goodsId"/>
                                                    <input type="text" class="center" value="${sd.goodsInfo.goodsName}">
                                                </td>
                                                <td>
                                                    <input type="hidden" id=${rowIndex.index} value="${sd.deviceInfoId}"
                                                           class="center"
                                                           name="storageDeliveryDtls[${rowIndex.index}].deviceInfoId"/>
                                                    <input type="text" id=${rowIndex.index} class="center"
                                                           value="${sd.deviceInfo.deviceName}"
                                                           style="text-align: center; width: 200px"
                                                           onclick="choiseDevice(this)"/>
                                                </td>
                                                <td>
                                                    <input type="text" class="center"
                                                           name="storageDeliveryDtls[${rowIndex.index}].requestCount"
                                                           value="${sd.requestCount}"
                                                           style="text-align: center; width: 200px"/>
                                                </td>
                                                <td>
                                                    <input type="text" class="center"
                                                           value="${sd.sysDict.dictName}"
                                                           style="text-align: center; width: 200px"/>
                                                    <input type="hidden"
                                                           name="storageDeliveryDtls[${rowIndex.index}].agentType"
                                                           value="${sd.agentType}"/>
                                                </td>
                                                <td>
                                                    <input type="text" class="center"
                                                           name="storageDeliveryDtls[${rowIndex.index}].remark"
                                                           value="${sd.remark}"
                                                           style="text-align: center; width: 500px"/>
                                                </td>
                                                <td>
                                                    <input type="hidden" value="${sd.goodsId}"/>
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
                <!-- 添加站点 结束 -->
                <!-- /.row -->
                <div class="row">
                    <div id="form_action_btns" class="col-xs-12" style="text-align: center">
                        <button id="save" class="btn btn-sm no-border btn-success">
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

<script src="${pageContext.request.contextPath}/js/biz/inventory/outAddOrUpdata.js"></script>
<%--<script src="${pageContext.request.contextPath}/js/biz/car/carLineAdd.js"></script>--%>



<script type="text/javascript">
    $("#submenu-menu-device-info").addClass("active");
    $("#menu-device").addClass("active");
    $("#menu-device").addClass("open");
</script>
<!-- 仓库选择弹出窗开始 -->
<div id="house_choise_modal" class="modal hiden fade"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:860px;">
        <div class="modal-content">
            <div class="modal-header thicktitle">
                <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                <span class="modal-title" id="myModalLabel">选择设备</span>
            </div>
            <div class="modal-body thickcon"
                 style="width:858px;height: 630px; padding-left: 10px; padding-right: 10px;">
                <input type="hidden" id="purchase_house_rowno"/>
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
                        <input id="orgId" type="hidden" value="${account.orgId}">
                        <table id="house_list" class="table table-striped table-bordered table-hover"
                               style="text-align: center">
                            <thead>
                            <tr>
                                <th class="center">仓库名称</th>
                                <th class="center">仓库地址</th>
                                <th class="center">仓库大小</th>
                                <th class="center">仓库创建日期</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="choise_house_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
                    <a id="choise_submit_2" class="btn btn-sm no-border btn-primary disabled"
                       onclick="batchAppendGoods()">添加</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 交接用户选择弹出窗开始 -->
<div id="checkUser_choise_modal" class="modal hiden fade"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:860px;">
        <div class="modal-content">
            <div class="modal-header thicktitle">
                <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                <span class="modal-title" id="myModalLabel_2">选择设备</span>
            </div>
            <div class="modal-body thickcon"
                 style="width:858px;height: 630px; padding-left: 10px; padding-right: 10px;">
                <input type="hidden" id="purchase_seller_rowno"/>
                <input type="hidden" id="choise_seller" value="0"/>

                <div class="row">
                    <div id="search-form_2" class="col-xs-12" style="padding-bottom: 5px;">
                        <div class="input-group" style="float:right;width:300px;">
                            <input type="text" id="keyword_2" class="form-control search-query"
                                   placeholder="设备名称/设备编号"/>
									<span class="input-group-btn">
										<button id="searchGoodsBtn_2" type="button"
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
                        <table id="checkUser_list" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">用户账号</th>
                                <th class="center">姓名</th>
                                <th class="center">用户电话</th>
                                <th class="center">用户邮箱</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="choise_seller_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
                    <a id="choise_submi" class="btn btn-sm no-border btn-primary disabled"
                       onclick="batchAppendGoods()">添加供应商</a>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- 清洗用户选择弹出窗开始 -->
<div id="cleanUser_choise_modal" class="modal hiden fade"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:860px;">
        <div class="modal-content">
            <div class="modal-header thicktitle">
                <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                <span class="modal-title" id="myModalLabel_2">选择设备</span>
            </div>
            <div class="modal-body thickcon"
                 style="width:858px;height: 630px; padding-left: 10px; padding-right: 10px;">
                <input type="hidden" id="purchase_seller_rowno"/>
                <input type="hidden" id="choise_seller" value="0"/>

                <div class="row">
                    <div id="search-form_2" class="col-xs-12" style="padding-bottom: 5px;">
                        <div class="input-group" style="float:right;width:300px;">
                            <input type="text" id="keyword_2" class="form-control search-query"
                                   placeholder="用户名"/>
									<span class="input-group-btn">
										<button id="searchGoodsBtn_2" type="button"
                                                class="btn btn-purple btn-sm no-border">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            搜索
                                        </button>
									</span>
                        </div>
                    </div>
                    <div class="col-xs-3">
                        <div style="height: 410px;overflow-y: scroll; border: 1px solid #ddd;">
                            <ul id="org1_tree" class="ztree"></ul>
                        </div>
                    </div>
                    <div class="col-xs-9">
                        <table id="cleanUser_list" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">用户账号</th>
                                <th class="center">姓名</th>
                                <th class="center">用户电话</th>
                                <th class="center">用户邮箱</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="choise_seller_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
                    <a id="choise_submi" class="btn btn-sm no-border btn-primary disabled"
                       onclick="batchAppendGoods()">添加供应商</a>
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
                            <input type="text" id="keyword" class="form-control search-query" placeholder=""/>
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
                       onclick="batchAppendGoods()">添加商品</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%--设备选择弹出框--%>
<div id="device_choise_modal" class="modal hiden fade"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:860px;">
        <div class="modal-content">
            <div class="modal-header thicktitle">
                <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                <span class="modal-title" id="myModalLabel">选择设备</span>
            </div>
            <div class="modal-body thickcon"
                 style="width:858px;height: 630px; padding-left: 10px; padding-right: 10px;">
                <input type="hidden" id="purchase_goods_rowno"/>
                <input type="hidden" id="choise_goods" value="0"/>

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
                            <ul id="org3_tree" class="ztree"></ul>
                        </div>
                    </div>
                    <div class="col-xs-9">
                        <table id="search_device_table" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">设备ID</th>
                                <th class="center">设备编号</th>
                                <th class="center">设备名称</th>
                                <th class="center">设备类型</th>
                                <th class="center">投放日期</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="choise_goods_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
                    <a id="choise_submit" class="btn btn-sm no-border btn-primary disabled"
                       onclick="batchAppendGoods()">添加设备</a>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="agent_combobox"
     style="display:none; height:400px;position: absolute;border: 1px solid #ddd;background-color:#F0F0F0;overflow:auto">
    <ul id="agent_tree" class="ztree" style="margin-top:0; "></ul>
</div>
</body>
</html>


