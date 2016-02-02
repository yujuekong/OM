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
						<form  action="../dispatchSubOrder/saveOrUpdateDSubOrder.action" method="post"   >
							<div class="form-group">
									<div class="col-xs-12">
										<div class="widget-box">
											<div class="widget-header widget-header-small">
												<h5>添加子订单</h5>
											</div>
											<div class="widget-body">
												<div class="form-group">
													<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
														选择设备： 
													</label>
													<span class="input-icon input-icon-right">
														<input id="deviceIdStr" type="hidden" name="dispatchingSubOrder.deviceId" value="${dispatchingSubOrder.deviceId}"  />
														<input id="deviceNoStr" type="hidden" name="dispatchingSubOrder.deviceNo" value="${dispatchingSubOrder.deviceNo}"  />
														<input id="districtIdStr" type="text" name="dispatchingSubOrder.districtId" value="${dispatchingSubOrder.districtId}"  />
														<input id="districtNameStr" type="text" name="dispatchingSubOrder.districtName" value="${dispatchingSubOrder.districtName}"  />
														<input id="deviceNameStr" type="text"  value="${dispatchingSubOrder.deviceName}" class="required" style="width:200px;"  onclick="choiseDevice(this)"/>														
													</span>
												</div>
												<div class="form-group"  >
	                                                <label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
	                                          		          选择商品： 
	                                                </label>
	                                                <span class="input-icon input-icon-right">
	                                                	<input id="goodsIdStr" type="hidden" name="dispatchingSubOrder.goodsId" value="${dispatchingSubOrder.goodsId}"  />
														<input id="goodsNameStr" type="text" name="dispatchingSubOrder.goodsName" class="required" style="width:200px;"  onclick="choiseGoods(this)"/>														
													</span>
												 </div>	
												<div class="form-group"  >
													 <label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
	                                          		          商品数量： 
	                                                </label>
	                                                 <span class="input-icon input-icon-right">
														<input id="goodsNumberStr" type="text" name="dispatchingSubOrder.goodsNumber" value="${dispatchingSubOrder.goodsNumber}" style="width:200px;" />
													</span>
	                                            </div>
	                                            <!--
	                                            <div>
	                                            	<a class="btn btn-xs no-border btn-info"
																onclick="addGoods()">
																<i class="ace-icon fa glyphicon-plus bigger-120"></i>
																批量添加
													</a>
	                                            </div>
												-->
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
											<th class="center">商品编号</th>
											<th class="center">商品名称</th>
											<th class="center">商品数量</th>
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
		<script src="${pageContext.request.contextPath}/js/biz/region/dispatchOrderAdd.js"></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
	</body>
</html>



