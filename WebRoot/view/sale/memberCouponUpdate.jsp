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
                <li><a href="#">销售管理</a></li>
                <li><a href="${pageContext.request.contextPath}/view/inventory/warehouseList.jsp">会员优惠劵管理</a></li>
                <li class="active">
                    <c:choose>
                        <c:when test="${sysMember.memberId==null}">新增会员优惠劵信息</c:when>
                        <c:otherwise>修改会员优惠劵信息</c:otherwise>
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
                  action="${pageContext.request.contextPath}//view/sale/MemberCoupon/memberCouponUpdate.action"
                  method="post" class="form-horizontal">
                <div class="col-xs-12">
                    <div class="col-xs-12">
                        <div class="widget-box">
                            <div class="widget-header widget-header-small">
                                <h5>
                                    <c:choose>
                                        <c:when test="${sysMember.memberId==null}">新增仓库信息</c:when>
                                        <c:otherwise>修改会员优惠劵信息</c:otherwise>
                                    </c:choose>
                                </h5>
                            </div>
                            <div class="widget-body">
                                <div class="widget-main">
                                    <div class="form-group" hidden="hidden">
                                        <label class="col-sm-2 control-label no-padding-right">
                                            会员ID：
                                        </label>
													<span class="input-icon input-icon-right">
														<input type="text"
                                                               value="${sysMember.memberId}" id="warehouse_Id"
                                                               class="required" style="width:450px;"
                                                               name="sysMember.memberId"/>
													</span>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-1 no-padding-right">
                                            会员账号：
                                        </label>
													<span class="col-sm-5 input-icon input-icon-right">
														<input type="text"
                                                               value="${sysMember.memberNo}"
                                                               id="sysMember_memberNo" class="required"
                                                               style="width:350px;"
                                                               disabled/>
													</span>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-1 no-padding-right">
                                            会员姓名：
                                        </label>
													<span class="col-sm-5 input-icon input-icon-right">
														<input type="text"
                                                               value="${sysMember.memberName}"
                                                               id="sysMember_memberName" class="required"
                                                               style="width:350px;"
                                                               disabled/>
													</span>
                                    </div>

                                    <div class="form-group">
                                        <label class="col-sm-1 no-padding-right">
                                            电话：
                                        </label>
													<span class="col-sm-2 input-icon input-icon-right">
														<input type="text"
                                                               value="${sysMember.memberTel}"
                                                               id="sysMember_memberTel" class="required"
                                                               style="width:260px;"
                                                               disabled/>
													</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <div class="widget-box">
                            <div class="widget-header widget-header-small">
                                <h5>添加优惠劵</h5>
                            </div>
                            <div class="widget-body">
                                <div class="widget-main no-padding">
                                    <table id="storage_inventory" class="table table-condensed table-bordered"
                                           style="text-align: center">
                                        <thead>
                                        <tr>
                                            <th class="center">行号</th>
                                            <th class="center">优惠劵</th>
                                            <th class="center">拥有数量</th>
                                            <th class="center">优惠券面值</th>
                                            <th class="center">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="purchase_goods_list">
                                        <c:if test="${empty memberCouponList}">
                                            <tr>
                                                <td class="center">1</td>
                                                <td><input type="text" id="goodsId"
                                                           style="text-align: center" onclick=""/>
                                                </td>
                                                </td>
                                                <td>
                                                    <input type="text" class="input-sm limited" maxlength="400"
                                                           style="text-align: center"
                                                        <%--name="memberCouponList[0].requestCount"--%>
                                                    />
                                                </td>
                                                <td>
                                                    <input type="text"
                                                           style="text-align: center; width: 200px"
                                                        <%--name="memberCouponList[0].remark"--%>
                                                    />
                                                </td>
                                                <td>
                                                    <input type="hidden"/>
                                                    <a href="javascript:void(0)"
                                                       onclick="removeGoodsItem(this)">移除</a>
                                                </td>
                                            </tr>
                                        </c:if>
                                        <c:forEach var="si" items="${memberCouponList}" varStatus="rowIndex">
                                            <tr>
                                                <td class="center">${rowIndex.index + 1}
                                                    <input type="hidden" value="${si.memberCouponId}" class="center"
                                                           name="memberCouponList[${rowIndex.index}].inventoryId"/>
                                                </td>
                                                <td>
                                                    <input type="text" class="center" value="${si.memberCouponId}"/>
                                                </td>
                                                <td>
                                                    <input type="text" class="center"
                                                           value="${si.memberCouponId}"/>
                                                </td>
                                                <td>
                                                    <input type="text" class="center"
                                                           value="${si.couponAmount}"/>
                                                </td>
                                                <td>
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

                        <div class="col-xs-12">
                            <div class="row">
                                <div class="col-xs-6">
                                    <a class="btn btn-xs no-border btn-info"
                                       onclick="batchChoiseGoods()">
                                        <i class="ace-icon fa glyphicon-plus bigger-120"></i>
                                        添加优惠劵</a>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div id="form_action_btns" class="col-xs-12" style="text-align: center">
                            <button type="button" id="save" class="btn btn-sm no-border btn-success"
                                    onclick="submitForm()">
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
                        <div style="height: 410px;width: 210px;overflow-y: scroll; border: 1px solid #ddd;">
                            <ul id="gt_tree" class="ztree"></ul>
                        </div>
                    </div>
                    <div class="col-xs-9">
                        <table id="couponList" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label class="position-relative">
                                        <input type="checkbox" class="ace"/>
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th class="center">优惠券名称</th>
                                <th class="center">优惠券面值</th>
                                <th class="center">有效开始日期</th>
                                <th class="center">有效结束日期</th>
                                <th class="center">优惠券数量</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div id="choise_goods_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
                    <a id="choise_submit" class="btn btn-sm no-border btn-primary"
                       onclick="batchAppendGoods()">添加优惠劵</a>
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


<script src="${pageContext.request.contextPath}/js/biz/sale/memberCouponUpdate.js"></script>
<script type="text/javascript">
    $("#submenu-menu-device-info").addClass("active");
    $("#menu-device").addClass("active");
    $("#menu-device").addClass("open");
</script>

</body>
</html>
