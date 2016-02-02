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
                <li><a href="#">报损管理</a></li>
                <li><a href="${pageContext.request.contextPath}/view/inventory/warehouseList.jsp">报损信息管理</a></li>
                <li class="active">
                    <c:choose>
                        <c:when test="${storageDebt.debtId==null}">新增报损单</c:when>
                        <c:otherwise>修改报损单</c:otherwise>
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
            <form id="debtForm"
                  action="${pageContext.request.contextPath}/view/inventory/storageDebt/saveOrUpdateStorageDebt.action"
                  method="post" class="form-horizontal">
                <div class="col-xs-12">
                    <div class="col-xs-12">
                        <div class="widget-box">
                            <div class="widget-header widget-header-small">
                                <h5>
                                    <c:choose>
                                        <c:when test="${storageDebt.debtId==null}">新增报损单信息</c:when>
                                        <c:otherwise>修改报损单信息</c:otherwise>
                                    </c:choose>
                                </h5>
                            </div>
                            <div class="widget-body">
                                <div class="widget-main">
                                    <div class="form-group" hidden="hidden">
                                        <label class="col-sm-2 control-label no-padding-right">
                                            报损ID:
                                        </label>
													<span class="input-icon input-icon-right">
														<input value="${storageDebt.debtId}" type="text"
                                                               id="storageDebt_debtId" class="required"
                                                               name="storageDebt.debtId"
                                                               style="width:450px;"/>
													</span>
                                    </div>
                                    <div class="form-group" hidden="hidden">
                                        <label class="col-sm-2 control-label no-padding-right">
                                            报损单编号：
                                        </label>
													<span class="input-icon input-icon-right">
														<input value="${storageDebt.debtNo}" type="text"
                                                               id="storageDebt_debtNo" class="required"
                                                               name="storageDebt.debtNo"
                                                               style="width:450px;"/>
													</span>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label no-padding-right">
                                            报损日期：
                                        </label>
													<span class="input-icon input-icon-right">
														<input value="${storageDebt.debtDate}" type="text"
                                                               id="storageDebt_debtDate" class="required required-tip"
                                                               readonly
                                                               style="width:450px;" name="storageDebt.debtDate"/>
													</span>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label no-padding-right">
                                            报损人：
                                        </label>
													<span class="input-icon input-icon-right">
														<input value="${account.userName}" type="text"
                                                               readonly
                                                               id="storageDebt_sysUser_userName" class="required"
                                                               style="width:450px;"/>
                                                        <input type="hidden" value="${account.userId}"
                                                               name="storageDebt.debtUser">
													</span>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label no-padding-right">
                                            报损说明：
                                        </label>
                                        <span class="input-icon input-icon-right">
                                        <input value="${storageDebt.auditDesc}" type="text"
                                               id="storageDebt_auditDesc" class="required"
                                               style="width:450px;" name="storageDebt.auditDesc"/>
                                        </span>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label no-padding-right">
                                            报损状态：
                                        </label>
                                        <%--<span class="input-icon input-icon-right">--%>
                                        <%--<input onclick="showMenu(this)"--%>
                                        <%--value="${storageDebt.debtStatus}" type="text"--%>
                                        <%--id="storageDebt_debtStatus" class="required"--%>
                                        <%--style="width:450px;" name="purchase.deliveryTime"/>--%>
                                        <%--</span>--%>
                                        <select id="debtType" style="width: 100px"
                                                name="storageDebt.debtLink">
                                            <option>请选择</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="col-xs-12">
                        <div class="widget-box">
                            <div class="widget-header widget-header-small">
                                <h5>添加报损</h5>
                            </div>
                            <div class="widget-body">
                                <div class="widget-main no-padding">
                                    <table id="debtList" class="table table-condensed table-bordered"
                                           style="text-align: center">
                                        <thead>
                                        <tr>
                                            <th class="center">行号</th>
                                            <th class="center">报损商品</th>
                                            <th class="center">报损数量</th>
                                            <th class="center">报损类型</th>
                                            <th class="center">报损说明</th>
                                            <th class="center">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="purchase_goods_list">
                                        <c:if test="${empty storageDebtDtlList}">
                                            <tr>
                                                <td class="center">1</td>
                                                <td><input type="text" id="goodsId"
                                                           disabled
                                                           style="text-align: center"/>
                                                </td>
                                                </td>
                                                <td>
                                                    <input type="text" class="input-sm limited"
                                                           style="text-align: center;width: 200px"
                                                           disabled
                                                           name="storageDebtDtlList[0].requestCount"/>
                                                </td>
                                                <td>
                                                    <input type="text"
                                                           style="text-align: center; width: 200px"
                                                           disabled
                                                           name="storageDebtDtlList[0].remark"/>
                                                </td>
                                                <td>
                                                    <input type="text"
                                                           style="text-align: center; width: 500px"
                                                           disabled
                                                           name="storageDebtDtlList[0].remark"/>
                                                </td>
                                                <td>
                                                    <input type="hidden"/>
                                                    <a href="javascript:void(0)"
                                                       onclick="removeGoodsItem(this)">移除</a>
                                                </td>
                                            </tr>
                                        </c:if>
                                        <c:forEach var="sd" items="${storageDebtDtlList}" varStatus="rowIndex">
                                            <tr>
                                                <td class="center">${rowIndex.index + 1}
                                                    <input type="hidden" value="${sd.debtDtlId}" class="center"
                                                           name="storageDebtDtlList[${rowIndex.index}].debtDtlId"/>
                                                </td>
                                                <td>
                                                    <input type="hidden" value="${sd.goodsId}" class="center"
                                                           name="storageDebtDtlList[${rowIndex.index}].goodsId"/>
                                                    <input type="text" class="center"
                                                           value="${sd.goodsInfo.goodsName}"/>
                                                </td>
                                                <td>
                                                    <input type="text" class="center" value="${sd.debtGoodsCount}"
                                                           name="storageDebtDtlList[${rowIndex.index}].debtGoodsCount">
                                                </td>
                                                <td><input type="hidden" class="center"
                                                           name="storageDebtDtlList[${rowIndex.index}].debtType"
                                                           value="${sd.debtType}"
                                                           style="text-align: center; width: 200px"/>
                                                    <input type="text" class="center"
                                                           value="${sd.sysDict.dictName}"
                                                           style="text-align: center; width: 200px"/>
                                                </td>
                                                <td><input type="text" class="center"
                                                           name="storageDebtDtlList[${rowIndex.index}].debtDesc"
                                                           value="${sd.debtDesc}"
                                                           style="text-align: center; width: 500px">
                                                </td>
                                                <td>
                                                    <input type="hidden" value="${sd.goodsId}"/>
                                                    <a href="javascript:void(0)"
                                                       onclick="removeGoodsItem(this)">移除</a>
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
                        <a class="btn btn-xs no-border btn-info"
                           onclick="batchChoiseGoods()">
                            <i class="ace-icon fa glyphicon-plus bigger-120"></i>
                            添加商品
                        </a>

                    </div>
                    <div class="row">
                        <div id="form_action_btns" class="col-xs-12" style="text-align: center">
                            <button id="save" type="submit" class="btn btn-sm no-border btn-success"><i
                                    class="ace-icon fa fa-floppy-o"></i>保存
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
<!-- /.page-content -->
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
                <input id="agent" type="hidden"/>
            </div>
        </div>
    </div>
</div>
</div>
<!-- /.main-content -->
</div>
<!-- /.main-container -->

<!-- Modal -->
<div id="gt_combobox"
     style="display:none; height:400px;position: absolute;border: 1px solid #ddd;background-color:#F0F0F0;overflow:auto">
    <ul id="gt1_tree" class="ztree" style="margin-top:0; "></ul>
</div>
<div id="agent_combobox"
     style="display:none; height:400px;position: absolute;border: 1px solid #ddd;background-color:#F0F0F0;overflow:auto">
    <ul id="agent_tree" class="ztree" style="margin-top:0; "></ul>
</div>
<!-- Modal -->

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

<script src="${pageContext.request.contextPath}/plugins/Validator/bootstrapValidator.js"></script>
<script src="${pageContext.request.contextPath}/plugins/Validator/language/zh_CN.js"></script>

<script src="${pageContext.request.contextPath}/js/biz/inventory/debtAddOrUpdata.js"></script>
<script type="text/javascript">
    $("#submenu-menu-device-info").addClass("active");
    $("#menu-device").addClass("active");
    $("#menu-device").addClass("open");
</script>

</body>
</html>