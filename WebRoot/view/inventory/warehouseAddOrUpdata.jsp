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
                <li><a href="${pageContext.request.contextPath}/view/inventory/warehouseList.jsp">仓库信息管理</a></li>
                <li class="active">
                    <c:choose>
                        <c:when test="${storageWarehouse.warehouseId==null}">新增仓库信息</c:when>
                        <c:otherwise>修改仓库信息</c:otherwise>
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
            <form id="house"
                  action="${pageContext.request.contextPath}/view/inventory/warehouse/saveOrUpdateWarehouse.action"
                  method="post" class="form-horizontal">
                <div class="col-xs-12">
                    <div class="col-xs-12">
                        <div class="widget-box">
                            <div class="widget-header widget-header-small">
                                <h5>
                                    <c:choose>
                                        <c:when test="${storageWarehouse.warehouseId==null}">新增仓库信息</c:when>
                                        <c:otherwise>修改仓库信息</c:otherwise>
                                    </c:choose>
                                </h5>
                            </div>
                            <div class="widget-body">
                                <div class="widget-main">
                                    <div class="form-group" hidden="hidden">
                                        <label class="col-sm-2 control-label no-padding-right">
                                            仓库ID：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text"
                                                       value="${storageWarehouse.warehouseId}" id="warehouse_Id"
                                                       class="" style="width:450px;"
                                                       name="storageWarehouse.warehouseId"/>
                                            </span>
                                    </div>
                                    <div class="form-group" hidden="hidden">
                                        <label class="col-sm-2 control-label no-padding-right">
                                            仓库编号：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text"
                                                       value="${storageWarehouse.warehouseNo}" id="warehouse_No"
                                                       class="" style="width:450px;"
                                                       name="storageWarehouse.warehouseNo"/>
                                            </span>
                                    </div>
                                    <div class="form-group has-feedback">
                                        <label class="col-sm-1 no-padding-right">
                                            仓库名称：
                                        </label>
                                        <span class="col-sm-2 input-icon input-icon-right">
                                            <input type="text"
                                                   value="${storageWarehouse.warehouseName}"
                                                   id="warehouse_name" class="required" style="width:250px;"
                                                   name="storageWarehouse.warehouseName" required
                                                   onKeyup="vailForm(this)"/>
                                            <i class="form-control-feedback glyphicon glyphicon-ok"
                                               style="display: none;"></i>
                                        </span>
                                        <label class="col-sm-3" style="color: red"></label>
                                    </div>

                                    <div class="form-group has-feedback">
                                        <label class="col-sm-1 no-padding-right">
                                            仓库大小：
                                        </label>
                                            <span class="col-sm-2 input-icon input-icon-right">
                                                <input type="text"
                                                       value="${storageWarehouse.warehouseSize}"
                                                       id="warehouse_size" class="required" style="width:250px;"
                                                       name="storageWarehouse.warehouseSize" required
                                                       onKeyup="vailForm(this)"/>
                                                <i class="form-control-feedback glyphicon glyphicon-ok"
                                                   style="display: none;"></i>
                                            </span>
                                        <label class="col-sm-3" style="color: red"></label>
                                    </div>
                                    <div class="form-group has-feedback">
                                        <label class="col-sm-1 no-padding-right">
                                            仓库地址：
                                        </label>
                                            <span class="col-sm-3 input-icon input-icon-right">
                                                <input type="text" value="${storageWarehouse.warehouseAddress}"
                                                       id="warehouse_location" class="required"
                                                       style="width:380px;" required
                                                       name="storageWarehouse.warehouseAddress"
                                                       onKeyup="vailForm(this)"/>
                                                <i class="form-control-feedback glyphicon glyphicon-ok"
                                                   style="display: none;"></i>
                                            </span>
                                        <label class="col-sm-3" style="color: red"></label>
                                    </div>
                                    <div class="form-group" hidden="hidden">
                                        <label class="col-sm-2 control-label no-padding-right">
                                            创建日期：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text"
                                                       value="${storageWarehouse.createDate}"
                                                       id="warehouse_createDate" class=""
                                                       style="width:50px;" name="storageWarehouse.createDate"/>
                                            </span>
                                    </div>
                                    <div class="form-group" hidden="hidden">
                                        <label class="col-sm-2 control-label no-padding-right">
                                            地址经度：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text"
                                                       value="${storageWarehouse.warehouseLng}"
                                                       id="warehouse_lng" class=""
                                                       style="width:200px;"
                                                       name="storageWarehouse.warehouseLng"/>
                                            </span>
                                    </div>
                                    <div class="form-group" hidden="hidden">
                                        <label class="col-sm-2 control-label no-padding-right">
                                            地址纬度：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <input type="text"
                                                       value="${storageWarehouse.warehouseLat}"
                                                       id="warehouse_lat" class=""
                                                       style="width:200px;"
                                                       name="storageWarehouse.warehouseLat"/>
                                            </span>
                                    </div>
                                    <div class="form-group" hidden>
                                        <label class="col-sm-1 no-padding-right">
                                            仓库类别：
                                        </label>
												<span class="col-sm-2 input-icon input-icon-right">
                                                      <label><input checked
                                                                    <c:if test="${storageWarehouse.isRefrigeratory==1}">checked</c:if>
                                                                    name="storageWarehouse.isRefrigeratory" type="radio"
                                                                    value="1"/>冷库</label>
                                                      <label><input
                                                              <c:if test="${storageWarehouse.isRefrigeratory==0}">checked</c:if>
                                                              name="storageWarehouse.isRefrigeratory" type="radio"
                                                              value="0"/>非冷库</label>
												</span>
                                    </div>
                                    <div class="form-group" hidden="hidden">
                                        <label class="col-sm-2 control-label no-padding-right">仓库状态：
                                        </label>
											<span class="input-icon input-icon-right">
                                                   <select id="goodsStatus"
                                                           name="storageWarehouse.warehouseStatus">
                                                       <option value="-1">请选择</option>
                                                       <option value="0" ${storageWarehouse.warehouseStatus == 0 ?"selected= 'selected'" : ""}>
                                                           启用
                                                       </option>
                                                       <option value="1" ${storageWarehouse.warehouseStatus == 1 ?"selected= 'selected'" : ""}>
                                                           禁用
                                                       </option>
                                                   </select>
											</span>
                                    </div>
                                    <div class="form-group" hidden="hidden">
                                        <label class="col-sm-1 no-padding-right">
                                            所属分公司：
                                        </label>
                                            <span class="col-sm-2 input-icon input-icon-right">
                                                <input type="text" value="${account.orgId}"
                                                       id="warehouse_dictOrgId" class=""
                                                       style="width:350px;"
                                                       name="storageWarehouse.dictOrgId"/>
                                                <input type="hidden" value="${account.dictRegionId}"
                                                       name="storageWarehouse.dictRegionId">
                                                <input type="hidden" value="${account.dictProviceId}"
                                                       name="storageWarehouse.dictProviceId">
                                            </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%--<div class="col-xs-12">--%>
                    <%--<div class="widget-box">--%>
                    <%--<div class="widget-header widget-header-small">--%>
                    <%--<h5>添加商品</h5>--%>
                    <%--</div>--%>
                    <%--<div class="widget-body">--%>
                    <%--<div class="widget-main no-padding">--%>
                    <%--<table id="storage_inventory" class="table table-condensed table-bordered"--%>
                    <%--style="text-align: center">--%>
                    <%--<thead>--%>
                    <%--<tr>--%>
                    <%--<th class="center">行号</th>--%>
                    <%--<th class="center">商品</th>--%>
                    <%--<th class="center">库存数量</th>--%>
                    <%--<th class="center">商品单位</th>--%>
                    <%--<th class="center">操作</th>--%>
                    <%--</tr>--%>
                    <%--</thead>--%>
                    <%--<tbody id="purchase_goods_list">--%>
                    <%--<c:if test="${empty storageInventoryList}">--%>
                    <%--<tr>--%>
                    <%--<td class="center">1</td>--%>
                    <%--<td><input type="text" id="goodsId"--%>
                    <%--style="text-align: center" onclick="" disabled/>--%>
                    <%--</td>--%>
                    <%--</td>--%>
                    <%--<td>--%>
                    <%--<input type="text" class="input-sm limited" maxlength="400"--%>
                    <%--style="text-align: center"--%>
                    <%--&lt;%&ndash;name="storageInventoryList[0].requestCount"&ndash;%&gt;--%>
                    <%--/>--%>
                    <%--</td>--%>
                    <%--<td>--%>
                    <%--<input type="text"--%>
                    <%--style="text-align: center; width: 200px" disabled--%>
                    <%--&lt;%&ndash;name="storageInventoryList[0].remark"&ndash;%&gt;--%>
                    <%--/>--%>
                    <%--</td>--%>
                    <%--<td>--%>
                    <%--<input type="hidden"/>--%>
                    <%--<a href="javascript:void(0)"--%>
                    <%--onclick="removeGoodsItem(this)">移除</a>--%>
                    <%--</td>--%>
                    <%--</tr>--%>
                    <%--</c:if>--%>
                    <%--<c:forEach var="si" items="${storageInventoryList}" varStatus="rowIndex">--%>
                    <%--<tr>--%>
                    <%--<td class="center">${rowIndex.index + 1}--%>
                    <%--<input type="hidden" value="${si.inventoryId}" class="center"--%>
                    <%--name="storageInventoryList[${rowIndex.index}].inventoryId"/>--%>
                    <%--</td>--%>
                    <%--<td>--%>
                    <%--<input type="hidden" value="${si.goodsId}" class="center"--%>
                    <%--name="storageInventoryList[${rowIndex.index}].goodsId"/>--%>
                    <%--<input type="text"--%>
                    <%--class="center" ${si.goodsInfo.goodsName != '' ?"readonly= 'readonly'" : ""}--%>
                    <%--value="${si.goodsInfo.goodsName}">--%>
                    <%--</td>--%>
                    <%--<td><input type="text" class="center"--%>
                    <%--value="${si.inventoryNumber}" ${si.inventoryNumber != '' ?"readonly= 'readonly'" : ""}--%>
                    <%--name="storageInventoryList[${rowIndex.index}].inventoryNumber">--%>
                    <%--</td>--%>
                    <%--<td>--%>
                    <%--<input type="hidden" class="center"--%>
                    <%--name="storageInventoryList[${rowIndex.index}].goodsUnit"--%>
                    <%--value="${si.goodsUnit}">--%>
                    <%--<input type="text"--%>
                    <%--class="center" ${si.goodsInfo.unitName != '' ?"readonly= 'readonly'" : ""}--%>
                    <%--value="${si.goodsInfo.unitName}"--%>
                    <%--onfocus="showUnitMenu(this)">--%>
                    <%--</td>--%>
                    <%--<td>--%>
                    <%--<input type="hidden" value="${si.goodsId}"/>--%>
                    <%--<a href="javascript:void(0)"--%>
                    <%--onclick="removeGoodsItem(this)">移除</a>--%>
                    <%--</td>--%>
                    <%--</tr>--%>
                    <%--</c:forEach>--%>
                    <%--</tbody>--%>
                    <%--</table>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="col-xs-12">--%>
                    <%--<div class="row">--%>
                    <%--<div class="col-xs-6">--%>
                    <%--<a class="btn btn-xs no-border btn-info"--%>
                    <%--onclick="batchChoiseGoods()">--%>
                    <%--<i class="ace-icon fa glyphicon-plus bigger-120"></i>--%>
                    <%--添加商品--%>
                    <%--</a>--%>
                    <%--</div>--%>

                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
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
            </form>
        </div>
    </div>
    <!-- /.row -->
</div>
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
                                <th class="center">商品类别</th>
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
                    <a id="choise_submit" class="btn btn-sm no-border btn-primary"
                       onclick="batchAppendGoods()">添加商品</a>
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

<!-- Modal -->
<div id="gt_combobox"
     style="display:none; height:400px;position: absolute;border: 1px solid #ddd;background-color:#F0F0F0;overflow:auto">
    <ul id="gt1_tree" class="ztree" style="margin-top:0; "></ul>
</div>

<div id="unit_combobox"
     style="display:none; height:400px;position: absolute;border: 1px solid #ddd;background-color:#F0F0F0;overflow:auto">
    <ul id="unit_tree" class="ztree" style="margin-top:0; "></ul>
</div>
<!-- Modal -->

<!-- basic scripts -->

<!--[if !IE]> -->


<!-- <![endif]-->

<!--[if !IE]> -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
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

<script src="${pageContext.request.contextPath}/plugins/Validator/bootstrapValidator.js"></script>
<script src="${pageContext.request.contextPath}/plugins/Validator/language/zh_CN.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/Validator/bootstrapValidator.css"/>


<script src="${pageContext.request.contextPath}/js/biz/inventory/warehouseAddUpdata.js"></script>
<script type="text/javascript">
    $("#submenu-menu-device-info").addClass("active");
    $("#menu-device").addClass("active");
    $("#menu-device").addClass("open");
</script>

</body>
</html>
