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
                <li><a href="#">商品管理</a></li>
                <li><a href="${pageContext.request.contextPath}/view/goods/goodsTypeList.jsp">商品类型管理</a></li>
                <li class="active">
                    <c:choose>
                        <c:when test="${goodsType.gtId==null}">新增商品类别</c:when>
                        <c:otherwise>修改商品类别</c:otherwise>
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

            <!--<div class="page-header">
                <h1>设备列表
                </h1>
            </div> /.page-header -->

            <div class="row">
                <form id="goodsType"
                      action="${pageContext.request.contextPath}/view/goods/goodsType/saveOrUpdateGoodsType.action"
                      method="post">
                    <div class="col-xs-12">
                        <div class="widget-box">
                            <div class="widget-header widget-header-small">
                                <h5>商品类型详情</h5>
                            </div>
                            <div class="widget-body">
                                <div class="widget-main">
                                    <div class="form-group" hidden="hidden">
                                        <label class="col-sm-2 control-label no-padding-right">
                                            商品类型ID：
                                        </label>
													<span class="input-icon input-icon-right">
														<input type="text" value="${goodsType.gtId}"
                                                               id="gt__type_num"
                                                               class="required" style="width:450px;"
                                                               name="goodsType.gtId"/>

													</span>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label no-padding-right">
                                            上级商品类别：
                                        </label>
													<span class="input-icon input-icon-right">
														<%--<input type="text"--%>
                                                               <%--value="${goodsType.dgtId==null?"无上级商品类别":goodsType.goodsType.gtName}"--%>
                                                               <%--id="gt__super_type"--%>
                                                               <%--class="required"--%>
                                                               <%--style="width:450px;" name="goodsType.dgtId"/>--%>
                                                        <input type="text"
                                                               value="${goodsType.dgtId==null?"无上级商品类别":goodsType.goodsTypeName}"
                                                               id="userPro" class="required" style="width:450px;"
                                                               onfocus="showMenu()"/>
                                                        <input type="hidden" id="orgStrId" name="goodsType.dgtId"
                                                               value="${goodsType.dgtId}"/>
													</span>
                                    </div>
                                    <div class="form-group" hidden="hidden">
                                        <label class="col-sm-2 control-label no-padding-right">
                                            商品类别编号：
                                        </label>
													<span class="input-icon input-icon-right">
														<input type="text" value="${goodsType.gtNo}"
                                                               id="gt__type_number" class="required"
                                                               style="width:450px;" name="goodsType.gtNo"/>
													</span>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label no-padding-right">
                                            商品类别名称：
                                        </label>
													<span class="input-icon input-icon-right">
														<input type="text"
                                                               value="${goodsType.gtName}" id="gt__type_name"
                                                               class="required"
                                                               style="width:450px;" name="goodsType.gtName"/>
													</span>
                                    </div>
                                    <div class="form-group" hidden="hidden">
                                        <label class="col-sm-2 control-label no-padding-right">
                                            删除标志：
                                        </label>
                                            <span class="input-icon input-icon-right">
                                            <select id="goodsStatus" name="goodsType.isDel">
                                                <option value="-1">请选择</option>
                                                <option value="0" ${goodsType.isDel == 0 ?"selected= 'selected'" : ""}>
                                                    正常
                                                </option>
                                                <option value="1" ${goodsType.isDel == 1 ?"selected= 'selected'" : ""}>
                                                    禁用
                                                </option>
                                            </select>
                                            </span>
                                    </div>
                                    <div class="form-group">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 表单操作按钮开始 -->
                    <div class="row">
                        <div id="form_action_btns" class="col-xs-12" style="text-align: center">
                            <button id="save" type="submit" class="btn btn-sm no-border btn-success" onclick="saveOp(0)">
                                <i class="ace-icon fa fa-floppy-o"></i>保存
                            </button>
                            &nbsp;&nbsp;&nbsp;
                            <a class="btn btn-sm no-border btn-default" onclick="javascript:history.back()">
                                <i class="ace-icon fa fa-times red2"></i>取消</a>
                        </div>
                    </div>
            </form>
        </div>
        <!-- /.row -->
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


<script src="${pageContext.request.contextPath}/js/biz/goods/goodsTypeAddOrUpdata.js"></script>
<script type="text/javascript">
    $("#submenu-menu-device-info").addClass("active");
    $("#menu-device").addClass("active");
    $("#menu-device").addClass("open");
</script>

</div>
</body>
</html>



