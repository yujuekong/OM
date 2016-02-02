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
                <li><a href="#">供应商管理</a></li>
                <li><a href="${pageContext.request.contextPath}/view/seller/sellerInfoList.jsp">供应商信息管理</a></li>
                <li class="active">
                    <c:choose>
                        <c:when test="${sellerInfo.sellerId==null}">新增供应商</c:when>
                        <c:otherwise>修改供应商</c:otherwise>
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
            <form id="seller"
                  action="${pageContext.request.contextPath}/view/seller/sellerInfo/saveOrUpdateSellerInfo.action"
                  method="post" class="form-horizontal">
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header widget-header-small">
                            <h5>
                                <c:choose>
                                    <c:when test="${sellerInfo.sellerId==null}">新增供应商信息</c:when>
                                    <c:otherwise>修改供应商信息</c:otherwise>
                                </c:choose>
                            </h5>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <div hidden="hidden" class="form-group">
                                    <label class="col-sm-1 control-label no-padding-right">
                                        供应商ID：
                                    </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${sellerInfo.sellerId}"
                                                   id="sellerinfo_sellerId"
                                                   class="required" style="width:450px;"
                                                   name="sellerInfo.sellerId"/>
                                        </span>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-1 control-label no-padding-right">
                                        供应商名称：
                                    </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${sellerInfo.sellerName}"
                                                   id="sellerinfo_sellerName" class="required"
                                                   style="width:450px;"
                                                   name="sellerInfo.sellerName" required maxlength="20"/>
                                        </span>
                                </div>
                                <div class="form-group" hidden="hidden">
                                    <label class="col-sm-1 control-label no-padding-right">
                                        供应商编号：
                                    </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text" value="${sellerInfo.sellerNo}"

                                                   id="sellerinfo_sellerNo" class="required"
                                                   style="width:450px;" name="sellerInfo.sellerNo"/>
                                        </span>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-1 control-label no-padding-right">
                                        供应商联系人：
                                    </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${sellerInfo.linkMan}"
                                                   id="sellerinfo_linkMan" class="required"
                                                   style="width:450px;"
                                                   name="sellerInfo.linkMan" required/>
                                        </span>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-1 control-label no-padding-right">
                                        联系人电话：
                                    </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${sellerInfo.linkTel}"
                                                   id="sellerinfo_linkTel" class="required"
                                                   style="width:450px;" name="sellerInfo.linkTel" required/>
                                        </span>
                                </div>
                                <div class="form-group" hidden="hidden">
                                    <label class="col-sm-1 control-label no-padding-right">
                                        供应商类型：
                                    </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${sellerInfo.sellerType}"
                                                   id="sellerinfo_sellerType" class="required"
                                                   style="width:450px;" name="sellerInfo.sellerType"/>
                                        </span>
                                </div>
                                <div class="form-group" hidden="hidden">
                                    <label class="col-sm-1 control-label no-padding-right">
                                        供货类型：
                                    </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${sellerInfo.sellerGoodsType}"
                                                   id="sellerinfo_sellerGoodsType" class="required"
                                                   style="width:450px;"
                                                   name="sellerInfo.sellerGoodsType"/>
                                        </span>
                                </div>
                                <div class="form-group" hidden="hidden">
                                    <label class="col-sm-1 control-label no-padding-right">
                                        供应商状态：
                                    </label>
                                        <span class="input-icon input-icon-right">
                                              <input type="text"
                                                     value="${sellerInfo.sellerStatus}"
                                                     id="sellerinfo_sellerStatus"
                                                     style="width:450px;" name="sellerInfo.sellerStatus"/>
                                        </span>
                                </div>
                                <div class="form-group" hidden="hidden">
                                    <label class="col-sm-1 control-label no-padding-right">
                                        创建日期：
                                    </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${sellerInfo.createDate}"
                                                   id="sellerinfo_createDate" class="required"
                                                   style="width:450px;" name="sellerInfo.createDate"/>
                                        </span>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-1 control-label no-padding-right">
                                        供应商地址：
                                    </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${sellerInfo.sellerAddress}"
                                                   id="sellerinfo_sellerAddress" class="required"
                                                   style="width:450px;" name="sellerInfo.sellerAddress" required/>
                                        </span>
                                </div>
                                <div class="form-group" hidden="hidden">
                                    <label class="col-sm-1 control-label no-padding-right">
                                        对应分公司：
                                    </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${account.orgId}"
                                                   id="sellerinfo_dictOrgId" class="required"
                                                   style="width:450px;" name="sellerInfo.dictOrgId"/>
                                        </span>
                                </div>
                                <div class="form-group" hidden="hidden">
                                    <label class="col-sm-1 control-label no-padding-right">
                                        所属区域：
                                    </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${sellerInfo.dictRegion.dictName}"
                                                   id="dict_region_id" class="required"
                                                   style="width:450px;"
                                            />
                                        </span>
                                </div>
                                <div class="form-group" hidden="hidden">
                                    <label class="col-sm-1 control-label no-padding-right">
                                        所属省：
                                    </label>
                                        <span class="input-icon input-icon-right">
                                        <input type="text" value="${sellerInfo.dictProvice.dictName}"
                                               id="userPro" class="required" style="width:450px;"
                                               onfocus="showMenu()"/>
                                        <input type="hidden" id="orgStrId" name="sellerInfo.dictProviceId"
                                               value="${sellerInfo.dictProviceId}"/>
                                        <input type="hidden" id="regionStrId" name="sellerInfo.dictRegionId"
                                               value="${sellerInfo.dictRegionId}"/>
                                        <i class="ace-icon fa fa-caret-down" onclick="showMenu()"></i>
                                        </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header widget-header-small">
                            <h5>添加商品</h5>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main no-padding">
                                <table id="sellGoods" class="table table-condensed table-bordered"
                                       style="text-align: center">
                                    <thead>
                                    <tr>
                                        <th class="center">行号</th>
                                        <th class="center">商品</th>
                                        <th class="center">商品产地</th>
                                        <th class="center">商品单价</th>
                                        <th class="center">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="purchase_goods_list">
                                    <c:if test="${empty sellerGoodsList}">
                                        <tr>
                                            <td class="center">1</td>
                                            <td><input type="text" id="goodsId"
                                                       style="text-align: center" disabled/>
                                            </td>
                                            </td>
                                            <td>
                                                <input type="text" class="input-sm limited"
                                                       style="text-align: center;width:400px" disabled/>
                                            </td>
                                            <td>
                                                <input type="text"
                                                       style="text-align: center" disabled/>
                                            </td>
                                            <td>
                                                <input type="hidden"/>
                                                <a href="javascript:void(0)"
                                                   onclick="removeGoodsItem(this)">移除</a>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:forEach var="sg" items="${sellerGoodsList}" varStatus="rowIndex">
                                        <tr>
                                            <td class="center">${rowIndex.index + 1}
                                                <input type="hidden" value="${sg.sellerGoodsId}" class="center"
                                                       name="sellerGoodsList[${rowIndex.index}].sellerGoodsId"/>
                                            </td>
                                            <td>
                                                <input type="hidden" value="${sg.goodsId}" class="center"
                                                       name="sellerGoodsList[${rowIndex.index}].goodsId"/>
                                                <input type="text" class="center"
                                                       value="${sg.goodsInfo.goodsName}">
                                            </td>
                                            <td><input type="text" class="center" value="${sg.goodsArea}"
                                                       name="sellerGoodsList[${rowIndex.index}].goodsArea">
                                            </td>
                                            <td><input type="text" class="center"
                                                       name="sellerGoodsList[${rowIndex.index}].goodsPrice"
                                                       value="${sg.goodsPrice}"
                                                       style="text-align: center;width: 500px">
                                            </td>
                                            <td>
                                                <input type="hidden" value="${sg.goodsId}"/>
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

                <div class="row">
                    <div id="form_action_btns" class="col-xs-12" style="text-align: center">
                        <button id="save" type="submit" class="btn btn-sm no-border btn-success">
                            <i class="ace-icon fa fa-floppy-o"></i>保存
                        </button>
                        &nbsp;&nbsp;&nbsp;
                        <a class="btn btn-sm no-border btn-default" onclick="history.back()">
                            <i class="ace-icon fa fa-times red2"></i>取消</a>
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
                    <a id="choise_submit" class="btn btn-sm no-border btn-primary disabled"
                       onclick="batchAppendGoods()">完成</a>
                </div>
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


<script src="${pageContext.request.contextPath}/js/biz/seller/sellerAddorUpdata.js"></script>
<script type="text/javascript">
    $("#submenu-menu-device-info").addClass("active");
    $("#menu-device").addClass("active");
    $("#menu-device").addClass("open");
</script>

</body>
</html>