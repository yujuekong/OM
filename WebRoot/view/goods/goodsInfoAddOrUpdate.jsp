<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
                <li><a href="#">商品管理</a></li>
                <li><a href="${pageContext.request.contextPath}/view/goods/goodsInfoList.jsp">商品信息管理</a></li>
                <li class="active">
                    <c:choose>
                        <c:when test="${goodsInfo.goodsId==null}">新增商品信息</c:when>
                        <c:otherwise>修改商品信息</c:otherwise>
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
            <div class="row">
                <form id="goods"
                      action="${pageContext.request.contextPath}/view/goods/goodsInfo/saveOrUpdateGoodsInfo.action"
                      method="post" enctype="multipart/form-data" class="form-horizontal">
                    <div class="col-xs-12">
                        <div class="col-xs-12">
                            <div class="widget-box">
                                <div class="widget-header widget-header-small">
                                    <h5 id="h5Id">新增商品信息</h5>
                                </div>
                                <div class="widget-body">
                                    <div class="widget-main" style="float: left;width: 45%">
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                商品ID:
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   id="gt__type_num"
                                                   value="${goodsInfo.goodsId}" id="goods_Info_id"
                                                   class="required" style="width:450px;"
                                                   name="goodsInfo.goodsId"/>
                                        </span>
                                        </div>
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                商品ID:
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   id="gt_sort"
                                                   value="${goodsInfo.goodsSort}" id="goods_goodsSort"
                                                   class="required" style="width:450px;"
                                                   name="goodsInfo.goodsSort"/>
                                        </span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 no-padding-right">
                                                商品名称：
                                            </label>
                                            <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.goodsName}" id="goods__type_Name"
                                                   class="required control-label"
                                                   style="width:450px;height: 36px" name="goodsInfo.goodsName"/>
                                            </span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 no-padding-right">
                                                商品类别：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="hidden" id="orgStrId" name="goodsInfo.gtId"
                                                   value="${goodsInfo.gtId}"/>
                                   			<input type="text" value="${goodsInfo.goodsTypeName}"
                                                   id="goodsType" class="required control-label" style="width:450px;"
                                                   name="goodsInfo.goodsTypeName" readonly
                                                   onfocus="showMenu()">
                                        </span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 no-padding-right">
                                                商品简介：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.synopsis}" id="goods__synopsis"
                                                   class="required control-label"
                                                   style="width:450px;" name="goodsInfo.synopsis"/>
                                        </span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 no-padding-right">
                                                商品条码：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.goodsBarCode}" id="goods__type_BarCode"
                                                   class="required control-label"
                                                   style="width:450px;" name="goodsInfo.goodsBarCode"/>
                                        </span>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-2 no-padding-right">
                                                商品品牌：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.goodsBrand}" id="goods__type_Brand"
                                                   class="required control-label"
                                                   style="width:450px;" name="goodsInfo.goodsBrand"/>
                                        </span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 no-padding-right">
                                                商品规格：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.goodsSpec}" id="goods__type_Spec"
                                                   class="required control-label"
                                                   style="width:450px;" name="goodsInfo.goodsSpec"/>
                                        </span>
                                        </div>
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                商品拼音：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.goodsSpell}" id="goods__type_Spell"
                                                   class="required control-label"
                                                   style="width:450px;" name="goodsInfo.goodsSpell"/>
                                        </span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 no-padding-right">
                                                保鲜期：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.antistaling}" id="goods__type_antistaling"
                                                   class="required control-label"
                                                   style="width:450px;" name="goodsInfo.antistaling"/>
                                        </span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 no-padding-right">
                                                保质期：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.qualityPeriod}" id="goods__type_qualityPeriod"
                                                   class="required control-label"
                                                   style="width:450px;" name="goodsInfo.qualityPeriod"/>
                                        </span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 no-padding-right">
                                                商品价格：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.goodsPrice}" id="goods__goodsPrice"
                                                   class="required control-label"
                                                   style="width:450px;" name="goodsInfo.goodsPrice"/>
                                        </span>
                                        </div>

                                        <div class="form-group">
                                            <input id="unitId" type="hidden" value="${goodsInfo.measurementUnit}">
                                            <label class="col-sm-2 no-padding-right">
                                                计量单位：
                                            </label>
                                            <span class="input-icon input-icon-right">
                                                <select id="unitSelect" style="width: 100px"
                                                        name="goodsInfo.measurementUnit" onchange="getUnitName()">
                                                    <option value="">请选择</option>
                                                </select>
                                            </span>
                                            <input id="unitName" type="hidden" name="goodsInfo.unitName">
                                        </div>

                                        <div class="form-group">
                                            <input id="agentType" type="hidden" value="${goodsInfo.agentType}">
                                            <label class="col-sm-2 no-padding-right">
                                                处理类型：
                                            </label>
                                            <span class="input-icon input-icon-right">
                                                <select id="goodsTypeSelect" style="width: 100px"
                                                        name="goodsInfo.agentType" onchange="getTypeName()">
                                                    <option value="">请选择</option>
                                                </select>
                                            </span>
                                            <input id="agentTypeName" type="hidden" name="goodsInfo.agentTypeName">
                                        </div>

                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                商品状态：
                                            </label>
                                                   <span class="input-icon input-icon-right">
                                                       <select id="goodsStatus" name="goodsInfo.goodsStatus">
                                                           <option value="-1">请选择</option>
                                                           <option value="0" ${goodsInfo.goodsStatus == 0 ?"selected= 'selected'" : ""}>
                                                               正常
                                                           </option>
                                                           <option value="1" ${goodsInfo.goodsStatus == 1 ?"selected= 'selected'" : ""}>
                                                               禁用
                                                           </option>
                                                       </select>
                                                   </span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 no-padding-right">
                                                是否冷藏：
                                            </label>
                                                   <span class="input-icon input-icon-right">
                                                       <label><input checked
                                                                     <c:if test="${goodsInfo.isFrozen==1}">checked</c:if>
                                                                     name="goodsInfo.isFrozen" type="radio"
                                                                     value="1"/>冷藏</label>
                                                       <label><input
                                                               <c:if test="${goodsInfo.isFrozen==0}">checked</c:if>
                                                               name="goodsInfo.isFrozen" type="radio"
                                                               value="0"/>非冷藏</label>
                                                   </span>
                                        </div>
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 no-padding-right">
                                                图片路径：
                                            </label>
                                        <span class="col-sm-2 input-icon input-icon-right">
                                            <input type="text"
                                                   value="${goodsInfo.goodsPic1}" id="goods__info_goodsPic1"
                                                   class="required"
                                                   style="width:450px;" name="goodsInfo.goodsPic1"/>
                                            <input type="text"
                                                   value="${goodsInfo.goodsPic2}" id="goods__info_goodsPic2"
                                                   class="required"
                                                   style="width:450px;" name="goodsInfo.goodsPic2"/>
                                        </span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 no-padding-right">
                                                智慧格图片：
                                            </label>
                                            <input type="file" id="goodsFile1"
                                                   class="required"
                                                   style="width:450px;" name="goodsFile1"
                                                   onchange="previewImage(this)"/>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 no-padding-right">
                                                图&nbsp;&nbsp;&nbsp;&nbsp;片：
                                            </label>
                                            <input type="file" id="zhgGoodsFile1"
                                                   class="required"
                                                   style="width:450px;" name="goodsFile2"
                                                   onchange="previewImage2(this)"/>
                                        </div>
                                        <div class="form-group">
                                        <span class="input-icon input-icon-right">
                                            <span id="preview1">
                                                <img width="200px" height="200px"
                                                     src="${pageContext.request.contextPath}/${goodsInfo.goodsPic1}"/>
                                            </span>
                                            <span id="preview2">
                                                <img width="200px" height="200px"
                                                     src="${pageContext.request.contextPath}/${goodsInfo.goodsPic2}"/>
                                            </span>
                                        </span>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-2 no-padding-right">
                                                商品描述：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <textarea name="goodsInfo.goodsDesc" class="col-xs-10 required"
                                                      style="width:450px;height:300px">${goodsInfo.goodsDesc }</textarea>
                                        </span>
                                        </div>
                                        <div class="form-group" hidden="hidden">
                                            <label class="col-sm-2 control-label no-padding-right">
                                                删除标志：
                                            </label>
                                        <span class="input-icon input-icon-right">
                                            <input type="text"
                                                   value="0" id="goods__type_isDel"
                                                   class="required"
                                                   style="width:450px;" name="goodsInfo.isDel"/>
                                        </span>
                                        </div>
                                    </div>
                                </div>

                                <%--<c:if test="${!empty goodsInfo.goodsPic1}">--%>
                                <%--<div style="float: right;width: 55%">--%>
                                <%--<img src="${pageContext.request.contextPath}${goodsInfo.goodsPic1}"/>--%>
                                <%--</div>--%>
                                <%--</c:if>--%>

                            </div>
                        </div>


                    </div>
                    <!-- /.row -->
                    <div class="row">
                        <div id="form_action_btns" class="col-xs-12" style="text-align: center">
                            <button type="submit" id="save" class="btn btn-sm no-border btn-success"
                                    onclick="saveOp(0)">
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
        <!-- /.page-content -->
    </div>
    <!-- /.main-content -->
</div>
<!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

<!-- <![endif]-->
<div id="gt_combobox"
     style="display:none; height:400px;position: absolute;border: 1px solid #ddd;background-color:#F0F0F0;overflow:auto">
    <ul id="gt_tree" class="ztree" style="margin-top:0; "></ul>
</div>
<div id="unit_combobox"
     style="display:none; height:400px;position: absolute;border: 1px solid #ddd;background-color:#F0F0F0;overflow:auto">
    <ul id="unit_tree" class="ztree" style="margin-top:0; "></ul>
</div>
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

<script src="${pageContext.request.contextPath}/plugins/Validator/bootstrapValidator.js"></script>
<script src="${pageContext.request.contextPath}/plugins/Validator/language/zh_CN.js"></script>


<script src="${pageContext.request.contextPath}/js/biz/goods/goodsInfoAddOrUpdata.js"></script>
<script type="text/javascript">

</script>
</div>
</body>
</html>

