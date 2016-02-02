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
						<li><a href="#">配送管理</a></li>
						<li><a href="#">待处理订单管理</a></li>
						<li class="active">新增入处理订单</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<input type="hidden" id ='userId' value="${account.orgId}"/>
						<form  action="../dispatchSubOrder/saveDSubOrder.action" method="post"   >
							<div class="form-group">
											<div class="widget-header widget-header-small">
												<h5>添加子订单</h5>
											</div>
											<div class="widget-body" style="margin-top:20px;">
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
														选择设备： 
													</label>
													<span class="input-icon input-icon-right">
														<input id="deviceIdStr" type="hidden" name="dispatchingSubOrder.deviceId" value="${dispatchingSubOrder.deviceId}"  />
														<input id="deviceNoStr" type="hidden" name="dispatchingSubOrder.deviceNo" value="${dispatchingSubOrder.deviceNo}"  />
														<input id="districtIdStr" type="hidden" name="dispatchingSubOrder.districtId" value="${dispatchingSubOrder.districtId}"  />
														<input id="districtNameStr" type="hidden" name="dispatchingSubOrder.districtName" value="${dispatchingSubOrder.districtName}"  />
														<input id="deviceNameStr" type="text"  value="${dispatchingSubOrder.deviceName}" class="required" style="width:200px;"  onclick="choiseDevice(this)"/>														
													</span>
												</div>
												<div class="form-group"  style="display:none">
	                                                <label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
	                                          		          所属子公司： 
	                                                </label>
	                                                <span class="input-icon input-icon-right">
	                                                	<input  type="hidden" name="dispatchingSubOrder.dictOrgId" value="${account.orgId}"  />
														<input  type="hidden" name="dispatchingSubOrder.dictProviceId" value="${account.dictProviceId}"  />
														<input  type="hidden" name="dispatchingSubOrder.dictRegionId" value="${account.dictRegionId}"/>														
													</span>
												 </div>	
											<!--	<div class="form-group"  >
													 <label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
	                                          		          商品数量： style="display:none"
	                                                </label>
	                                                 <span class="input-icon input-icon-right">
														<input id="goodsNumberStr" type="text" name="dispatchingSubOrder.goodsNumber" value="${dispatchingSubOrder.goodsNumber}" style="width:200px;" />
													</span>
	                                            </div>
											-->
											</div>
											<div class="col-xs-12">
												<div class="widget-box">
													<div class="widget-body">
														<div class="widget-main no-padding">
															<table id="tb" class="table table-condensed table-bordered">
																<thead>
																	<tr>
																		<th class="center" style="width:200px">行号</th>
																		<th class="center" style="width:300px">商品名称</th>
																	    <th class="center" style="width:300px">商品数量</th>
																	</tr>
																</thead>
																<tbody id="purchase_goods_list">
																	<c:forEach var="pg" items="${carLineNode}" varStatus="rowIndex">
																	<tr>
																		<td class="center">${rowIndex.index + 1}</td>
																		<td>
																			<input type="hidden" id="lNodeId" name="subOrderList[${rowIndex.index}].goodsId"  value="${pg.goodsId}"/>
																			<input type="text" name="subOrderList[${rowIndex.index}].goodsName"  value="${pg.goodsName}"/>
																			<input type="text" name="subOrderList[${rowIndex.index}].goodsUnit"  value="${pg.goodsUnit}"/>
																		</td>
																	</tr>
																	</c:forEach>
																</tbody>
															</table>
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
							</form>
						</div>
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
		
		
		<!-- 设备选择弹出窗开始 -->
		<div id="device_choise_modal" class="modal hiden fade" 
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:860px;">
				<div class="modal-content" >
					<div class="modal-header thicktitle">
						<a class="close" data-dismiss="modal" aria-hidden="true">×</a>
						<span class="modal-title" id="myModalLabel">选择设备</span>
					</div>
					<div class="modal-body thickcon" style="width:858px;height: 650px; padding-left: 10px; padding-right: 10px;">
						<div class="row">
							<div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
								<div class="input-group" style="float:right;width:300px;">
									<input type="text" id="keyword" class="form-control search-query" placeholder="" /> 
									<span class="input-group-btn">
										<button id="searchGoodsBtn" type="button" class="btn btn-purple btn-sm no-border">
											<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
											搜索
										</button>
									</span>
								</div>
							</div>
							<div class="col-xs-12">
								<table id="device_list" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center">设备编号</th>
											<th class="center">设备名称</th>
											<th class="center">商圈名称</th>
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
				<div class="modal-content" >
					<div class="modal-header thicktitle">
						<a class="close" data-dismiss="modal" aria-hidden="true">×</a>
						<span class="modal-title" id="myModalLabel">选择商品</span>
					</div>
					<div class="modal-body thickcon" style="width:858px;height: 650px; padding-left: 10px; padding-right: 10px;">
						<div class="row">
							<div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
								<div class="input-group" style="float:right;width:300px;">
									<input type="text" id="keyword" class="form-control search-query" placeholder="" /> 
									<span class="input-group-btn">
										<button id="searchGoodsBtn" type="button" class="btn btn-purple btn-sm no-border">
											<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
											搜索
										</button>
									</span>
								</div>
							</div>
							<div class="col-xs-12">
								<table id="goods_list" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center">
												<label class="position-relative">
													<input type="checkbox" class="ace" />
													<span class="lbl"></span>
												</label>
											</th>
											<th class="center">商品名称</th>
											<th class="center">商品单位</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
						<div id="choise_goods_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
							<a id="choise_submit" class="btn btn-sm no-border btn-primary disabled"
							   onclick="batchAppendGoods()">选择商品</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
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
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js" ></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js" ></script>
		<script src="${pageContext.request.contextPath}/js/biz/region/dispatchSubOrderAdd.js"></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
	</body>
</html>



