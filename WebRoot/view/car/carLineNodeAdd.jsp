<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<jsp:include page="../commons/head.jsp" />
	<body class="no-skin">
		<div class="main-container" id="main-container">
			<div>
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>

					<ul id="uiId" class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="#">首页</a>
						</li>
						<li><a href="#">车辆管理</a></li>
						<li><a href="">线路站点管理</a></li>
						<li class="active">新增线路站点</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>
				<input type="hidden" id="rowCount">
				<div class="page-content">
					<div class="row">
						<form action="../carLineNode/saveOrUpdateCarlineNode.action" method="post" >
							<div class="col-xs-12">
								<div class="form-group">
									<div class="col-xs-12">
										<div class="widget-box">
											<div class="widget-header widget-header-small">
												<h5 id="h5Id">新增线路站点</h5>
											</div>
											<div class="widget-body">
												<div class="widget-main">
													<div class="form-group" style="display:none">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															站点ID： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="lineNodeId" type="text" name="carLineNode.lineNodeId" style="width:300px;" value="${carLineNode.lineNodeId}"  />
														</span>
													</div>
													<div class="form-group" style="display:none">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															商圈ID： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="districtIdStr" type="text" name="carLineNode.nodePositionId" style="width:300px;" value="${carLineNode.nodePositionId}"  />
														</span>
													</div>
													<div class="form-group" style="display:none">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															站点编号： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="nodeNo" type="text" name="carLineNode.nodeNo" style="width:300px;" value="${carLineNode.nodeNo}"  />
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															商圈名称： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="districtNameStr" style="width:300px;" type="text" name="carLineNode.nodePositionName"  value="${carLineNode.nodePositionName}" class="required" style="width:300px;" onclick="choiseDevice(this)" />														
														</span>
													</div>
													
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															商圈经度： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="districtLngStr" style="width:300px;"  readonly="readonly" type="text" name="carLineNode.nodeLng" value="${carLineNode.nodeLng}"  />
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															商圈纬度： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="districtLatStr" style="width:300px;" readonly="readonly" type="text" name="carLineNode.nodeLat" value="${carLineNode.nodeLat}"  />
														</span>
													</div>
													
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															备注： 
														</label>
														<div class="col-xs-5 input-group input-group-sm">
															<textarea name="carLineNode.remark"  id="remark"  class="col-xs-10 required"   style="width:300px;"   >${carLineNode.remark}</textarea>																
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- 表单操作按钮开始 -->
								<div class="row">
	                            <div id="form_action_btns" class="col-xs-12" style="text-align: center">
	                                <button id="save" class="btn btn-sm no-border btn-success">
	                                    <i class="ace-icon fa fa-floppy-o"></i>保存
	                                </button>
	                                &nbsp;&nbsp;&nbsp;
	                                <a class="btn btn-sm no-border btn-default" onclick="javascript:history.back()">
	                                    <i class="ace-icon fa fa-times red2"></i>取消</a>
	                            </div>
								</div>
							</div>
						</form>
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->
		
		<!-- 设备选择弹出窗开始 -->
		<div id="goods_choise_modal" class="modal hiden fade" 
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:860px;">
				<div class="modal-content" >
					<div class="modal-header thicktitle">
						<a class="close" data-dismiss="modal" aria-hidden="true">×</a>
						<span class="modal-title" id="myModalLabel">选择设备</span>
					</div>
					<div class="modal-body thickcon" style="width:858px;height: 650px; padding-left: 10px; padding-right: 10px;">
						<input type="hidden" id="purchase_goods_rowno" />
						<input type="hidden" id="choise_type" value="0"/>
						<div class="row">
							<div class="col-xs-3">
								<div style="height: 410px;overflow-y: scroll; border: 1px solid #ddd;">
									<ul id="device_tree" class="ztree"></ul>
								</div>
							</div>
							<div class="col-xs-9">
								<table id="deviceInfo_list" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center">商圈编号</th>
											<th class="center">商圈名称</th>
											<th class="center">商圈地址</th>
											<th class="center">商圈经度</th>												
											<th class="center">商圈纬度</th>
										</tr>
									</thead>
									<tbody>
										
									</tbody>
								</table>
							</div>
						</div>
						<div id="choise_goods_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
							<a id="choise_submit" class="btn btn-sm no-border btn-primary disabled" onclick="batchAppendGoods()">加入广告内容</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 商品选择弹出窗结束 -->
		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>

		<!-- <![endif]-->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${pageContext.request.contextPath}/js/jquery.min.js'>"+"<"+"/script>");
		</script>

		<!-- page specific plugin scripts -->
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" ></script>
		<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/ztree/js/jquery.ztree.core-3.5.min.js" ></script>
		
		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js" ></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js" ></script>
		<script src="${pageContext.request.contextPath}/js/biz/car/carLineNodeAdd.js"></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
		
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
	</body>
</html>



