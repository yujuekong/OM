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
						<li><a href="#">配送计划管理</a></li>
						<li class="active">配送计划详情</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>
				<div class="page-content">
					<div class="row">
							<div class="col-xs-12">
								<div class="form-group">
									<div class="col-xs-12">
										<div class="widget-box">
											<div class="widget-header widget-header-small">
												<h5 id="h5Id">配送订单详情</h5>
											</div>
											<div class="widget-body">
												<div class="widget-main">
													<div class="form-group"  >
		                                                <label class="col-sm-2 control-label no-padding-right">
		                                          		          订单编号： 
		                                                </label>
		                                                <span class="input-icon input-icon-right">
															<input  disabled="disabled" type="text" value="${dOrder.orderNo}"  class="required" style="width:300px;"  />														
														</span>
		                                            </div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															创建人： 
														</label>
														<span class="input-icon input-icon-right">
															<span class="input-icon input-icon-right">
																<input  disabled="disabled" type="text"  style="width:300px;"  value="${dOrder.user.realName}"  />
														</span>
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															配送小组： 
														</label>
														<span class="input-icon input-icon-right">
															<input  type="text" disabled="disabled"  value="${dOrder.team.teamName}"  class="required" style="width:300px;"  />														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															对应仓库： 
														</label>
														<span class="input-icon input-icon-right">
															<input  type="text" disabled="disabled"  value="${dOrder.warehouse.warehouseName}"  class="required" style="width:300px;"  />														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															创建时间： 
														</label>
														<span class="input-icon input-icon-right">
															<input  disabled="disabled" type="text"   value="${dOrder.orderTime}"  class="required" style="width:300px;"  />														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															要求完成时间： 
														</label>
														<span class="input-icon input-icon-right">
															<input type="text" disabled="disabled"  value="${dOrder.orderEndTime}"  class="required" style="width:300px;"  />														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															实际完成时间： 
														</label>
														<span class="input-icon input-icon-right">
															<input  type="text"  disabled="disabled"  value="${dOrder.orderFinishTime}"  class="required" style="width:300px;"  />														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															订单状态： 
														</label>
														<span class="input-icon input-icon-right">	
															<input  disabled="disabled" type="text" 
															 value="${dOrder.orderStatus==0?"在途":"完成"}"   class="required" style="width:300px;"  />														
														</span>
													</div>
												</div>
											</div>
											<div class="widget-body">
												<div class="widget-main no-padding">
													<table id="tb" class="table table-condensed table-bordered">
														<thead>
															<tr>
																<th class="center">序号</th>
																<th class="center">设备编号</th>
																<th class="center">商圈名称</th>
																<th class="center">商品名称</th>
																<th class="center">商品数量</th>
																<th class="center">配送时间</th>
															</tr>
														</thead>
														<tbody id="purchase_goods_list">
															<c:forEach var="pg" items="${DSOrderlist}" varStatus="rowIndex">
															<tr>
																<td class="center">${rowIndex.index + 1}</td>
																<td><input type="text" 	class="center"	readonly	value="${pg.deviceNo}"/></td>
																<td><input type="text"  class="center"	readonly	value="${pg.districtName}"/></td>
																<td><input type="text"  class="center"	readonly	value="${pg.goodsName}"/></td>
																<td><input type="text"	class="center"	readonly	value="${pg.goodsNumber}"/></td>
																<td><input type="text"	class="center"	readonly	value="${pg.dispatchingTime}"/></td>
															</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
											</div>
										</div>
									</div>
								</div>
								<!-- 表单操作按钮开始 -->
							</div>
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->
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
		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
	</body>
</html>



