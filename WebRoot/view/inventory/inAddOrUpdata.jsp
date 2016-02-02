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
                <li><a href="${pageContext.request.contextPath}/view/inventory/inList.jsp">商品入库</a></li>
                <li class="active">
                    <c:choose>
                        <c:when test="${warehousingEntry.warehousingEntryId==null}">新增入库单</c:when>
                        <c:otherwise>修改入库单</c:otherwise>
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
            <form id="orderIn"
                  action="${pageContext.request.contextPath}/view/inventory/warehousing/saveOrUpdateOrderIn.action"
                  method="post" class="form-horizontal">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="col-xs-12">
                            <div class="widget-box">
                                <div class="widget-header widget-header-small">
                                    <h5 id="h5Id">
                                        <c:choose>
                                            <c:when test="${warehousingEntry.warehousingEntryId==null}">新增入库单信息</c:when>
                                            <c:otherwise>修改入库单信息</c:otherwise>
                                        </c:choose>
                                    </h5>
                                </div>
                                <div class="widget-main">
                                    <div class="form-group" hidden="hidden">
                                        <label class="col-sm-1 control-label no-padding-right"> 入库单ID:
                                        </label>
													<span class="input-icon input-icon-right">
														<input value="${warehousingEntry.warehousingEntryId}"
                                                               type="text" name="warehousingEntry.warehousingEntryId"
                                                               id="warehousingEntryId" class="required"
                                                               style="width:450px;"/>
													</span>
                                    </div>
                                    <div class="form-group">
                                        <input id="agentType" type="hidden" value="${warehousingEntry.bizType}">
                                        <label class="col-sm-1 control-label no-padding-right">
                                            <label style="color: red">*</label>业务类型：
                                        </label>
                                        <span class="input-icon input-icon-right">
                                        <select id="bizType" style="width: 150px"
                                                name="warehousingEntry.bizType" onchange="getTypeName()">
                                            <option value="" selected>请选择</option>
                                        </select>
                                        </span>
                                        <%--<label id="typeValidate"></label>--%>
                                        <%--<input id="agentTypeName" type="hidden" name="goodsInfo.agentTypeName">--%>
                                    </div>
                                    <div class="form-group" id="divSeller">
                                        <label class="col-sm-1 control-label no-padding-right">
                                            <label style="color: red">*</label>供应商：
                                        </label>
													<span class="input-icon input-icon-right">
														<input id="sellerName"
                                                               name="sellerName"
                                                               value="${warehousingEntry.sellerInfo.sellerName}"
                                                               type="text" class="required" style="width:450px;"
                                                               onclick="choiseSeller(this)" readonly="readonly"/>
                                                        <input id="sellerId" name="warehousingEntry.sellerId"
                                                               value="${warehousingEntry.sellerId}"
                                                               type="hidden" class="required" style="width:450px;"/>
													</span>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-1 control-label no-padding-right">
                                            <label style="color: red">*</label>仓&nbsp;库：
                                        </label>
													<span class="input-icon input-icon-right">
														<input id="houseName"
                                                               name="houseName"
                                                               value="${warehousingEntry.storageWarehouse.warehouseName}"
                                                               type="text" class="required" style="width:450px;"
                                                               readonly
                                                               onclick="choiseHouse(this)"/>
													    <input id="houseId" name="warehousingEntry.warehouseId"
                                                               value="${warehousingEntry.warehouseId}"
                                                               type="hidden" class="required" style="width:450px;"/>
													</span>
                                    </div>
                                    <div class="form-group" hidden="hidden">
                                        <label class="col-sm-1 control-label no-padding-right">
                                            入库单编号：
                                        </label>
													<span class="input-icon input-icon-right">
														<input value="${warehousingEntry.warehousingNo}" type="text"
                                                               id="eligible_num" class="required" style="width:450px;"
                                                               name="warehousingEntry.warehousingNo"/>
													</span>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-1 control-label no-padding-right">
                                            要求入库日期：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                            <input value="${warehousingEntry.requestDate}" type="text"
                                                   id="warehousingEntry_warehousingDate" class="required"
                                                   style="width:200px;" readonly
                                                   name="warehousingEntry.requestDate"/>
                                            <i class="ace-icon fa fa-calendar blue"></i>
                                            </span>
                                    </div>
                                    <div class="form-group" hidden="hidden">
                                        <label class="col-sm-1 control-label no-padding-right">
                                            录入用户：
                                        </label>
													<span class="input-icon input-icon-right">
														<input value="${warehousingEntry.sysUser.realName}" type="text"
                                                               id="warehousingEntry.sysUser.realName" class="required"
                                                               style="width:450px;"
                                                        />
                                                        <input value="${account.userId}"
                                                               type="text"
                                                               id="warehousingEntry_sysUser" class="required"
                                                               style="width:450px;"
                                                               name="warehousingEntry.userId"/>
													</span>
                                    </div>
                                    <div class="form-group" hidden="hidden">
                                        <label class="col-sm-1 control-label no-padding-right">
                                            创建日期：
                                        </label>
                                        <input value="${warehousingEntry.createDate}" type="text"
                                               id="warehousingEntry_createDate" class="required"
                                               style="width:450px;"
                                               name="warehousingEntry.createDate"/>
                                        </span>
                                    </div>
                                    <div class="form-group" hidden="hidden">
                                        <label class="col-sm-1 control-label no-padding-right">
                                            入库单状态：
                                        </label>
													<span class="input-icon input-icon-right">
														<input value="${warehousingEntry.warehousingStatus}" type="text"
                                                               id="warehousingEntry_warehousingStatus" class="required"
                                                               style="width:450px;"
                                                               name="warehousingEntry.warehousingStatus"/>
													</span>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-1 control-label no-padding-right">
                                            &nbsp;入库备注：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                                <textarea id="warehousingEntry_remark" class="required"
                                                          style="width:450px;"
                                                          name="warehousingEntry.remark">${warehousingEntry.remark}</textarea>
                                            </span>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 添加站点 开始 -->
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header widget-header-small">
                            <h5>添加商品</h5>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main no-padding">
                                <table id="goods_list" class="table table-condensed table-bordered"
                                       style="text-align: center">
                                    <thead>
                                    <tr>
                                        <th class="center">行号</th>
                                        <th class="center">商品</th>
                                        <th class="center">计划入库数量</th>
                                        <th class="center">明细备注</th>
                                        <th class="center">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="purchase_goods_list">
                                    <c:if test="${empty storageWarehousingDtls}">
                                        <tr>
                                            <td class="center">1</td>
                                            <td>
                                                <input type="hidden" id="goodsId"
                                                       style="text-align: center" onclick="choiseGoods(this)"
                                                       name="warehousingDtls[0].goodsId"/>
                                                <input type="text" class="center" disabled>
                                            </td>
                                            <td>
                                                <input type="number" class="input-sm limited" maxlength="500"
                                                       style="text-align: center"
                                                       name="warehousingDtls[0].requestCount" disabled/>
                                            </td>
                                            <td>
                                                <input type="text"
                                                       style="text-align: center; width: 500px"
                                                       name="warehousingDtls[0].remark" disabled/>
                                            </td>
                                            <td>
                                                <input type="hidden"/>
                                                <a href="javascript:void(0)" onclick="removeGoodsItem(this)">移除</a>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:forEach var="sd" items="${storageWarehousingDtls}" varStatus="rowIndex">
                                        <tr>
                                            <td class="center">${rowIndex.index + 1}</td>
                                            <td>
                                                <input type="hidden" value="${sd.goodsId}" class="center"
                                                       name="warehousingDtls[${rowIndex.index}].goodsId"/>
                                                <input type="text" class="center" value="${sd.goodsInfo.goodsName}">
                                            </td>
                                            <td><input type="text" class="center" value="${sd.requestCount}"
                                                       name="warehousingDtls[${rowIndex.index}].requestCount"></td>
                                            <td><input type="text" class="center"
                                                       name="warehousingDtls[${rowIndex.index}].remark"
                                                       value="${sd.remark}"
                                                       style="text-align: center; width: 500px"></td>
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
                <!-- 添加站点 结束 -->
                <!-- /.row -->
                <div class="row">
                    <div id="form_action_btns" class="col-xs-12" style="text-align: center">
                        <button id="submitBtn" type="submit" class="btn btn-sm no-border btn-success">
                            <i class="ace-icon fa fa-floppy-o"></i>保存
                        </button>
                        &nbsp;&nbsp;&nbsp;
                        <a class="btn btn-sm no-border btn-default" onclick="history.back()">
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

<!-- 仓库选择弹出窗开始 -->
<div id="house_choise_modal" class="modal hiden fade"
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
                            <input type="text" id="search" class="form-control search-query" placeholder="仓库名称"/>
									<span class="input-group-btn">
										<button id="searchGoodsBtn" type="button"
                                                class="btn btn-purple btn-sm no-border">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            搜索
                                        </button>
									</span>
                        </div>
                    </div>
                    <%--<div class="col-xs-3">--%>
                    <%--<div style="height: 410px;overflow-y: scroll; border: 1px solid #ddd;">--%>
                    <%--<ul id="org_tree" class="ztree"></ul>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <div class="col-xs-12">
                        <input type="hidden" id="warehouseId">
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
                <%--<div id="choise_house_action" class="col-xs-12" style="text-align:center;margin-top:10px;">--%>
                <%--<a id="choise_submit_2" class="btn btn-sm no-border btn-primary disabled"--%>
                <%--onclick="batchAppendGoods()">添加</a>--%>
                <%--</div>--%>
            </div>
        </div>
    </div>
</div>
<!-- 供应商选择弹出窗开始 -->
<div id="seller_choise_modal" class="modal hiden fade"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:860px;">
        <div class="modal-content">
            <div class="modal-header thicktitle">
                <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
                <span class="modal-title" id="myModalLabel_2">选择供应商</span>
            </div>
            <div class="modal-body thickcon"
                 style="width:858px;height: 630px; padding-left: 10px; padding-right: 10px;">
                <input type="hidden" id="purchase_seller_rowno"/>
                <input type="hidden" id="choise_seller" value="0"/>

                <div class="row">
                    <div id="search-form_2" class="col-xs-12" style="padding-bottom: 5px;">
                        <div class="input-group" style="float:right;width:300px;">
                            <input type="text" id="keyword_2" class="form-control search-query"
                                   placeholder="供应商名称"/>
									<span class="input-group-btn">
										<button id="searchGoodsBtn_2" type="button"
                                                class="btn btn-purple btn-sm no-border">
                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                            搜索
                                        </button>
									</span>
                        </div>
                    </div>
                    <%--<div class="col-xs-3">--%>
                    <%--<div style="height: 410px;overflow-y: scroll; border: 1px solid #ddd;">--%>
                    <%--<ul id="org1_tree" class="ztree"></ul>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <div class="col-xs-12">
                        <input id="orgId" type="hidden" value="${account.orgId}">
                        <table id="seller_list" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">供应商名称</th>
                                <th class="center">供应商编号</th>
                                <th class="center">联系人姓名</th>
                                <th class="center">联系方式</th>
                                <th class="center">创建日期</th>
                                <th class="center">供应商状态</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
                <%--<div id="choise_seller_action" class="col-xs-12" style="text-align:center;margin-top:10px;">--%>
                <%--<a id="choise_submi" class="btn btn-sm no-border btn-primary disabled"--%>
                <%--onclick="batchAppendGoods()">添加供应商</a>--%>
                <%--</div>--%>
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
</body>

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

<script src="${pageContext.request.contextPath}/plugins/Validator/bootstrapValidator.js"></script>
<script src="${pageContext.request.contextPath}/plugins/Validator/language/zh_CN.js"></script>

<script src="${pageContext.request.contextPath}/js/biz/inventory/orderInAddOrUpdata.js"></script>
</html>


