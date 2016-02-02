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
						<li class="active">新增配送计划</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<form  action="../dispatch/saveOrUpdateDispatchPlan.action" method="post"  onsubmit="return check();" >
						<div class="form-group">
								<input type="hidden" id ='userId' value="${account.orgId}"/>
								<div class="col-xs-12">
									<div class="widget-box">
										<div class="widget-header widget-header-small">
											<h5>添加配送信息</h5>
										</div>
										<div class="widget-body">
											<div class="widget-main no-padding">
												<table id="tb" class="table table-condensed table-bordered">
													<thead>
														<tr>
														    <th class="center">行号</th>
															<th  style="width:150px">设备名称</th>
															<th class="center">站点编号</th>
															<th class="center">商圈名称</th>
															<th class="center">配送商品</th>
															<th class="center">商品数量</th>
															<th class="center">操作</th>
														</tr>
													</thead>
													<tbody id="purchase_device_list">
														<c:forEach var="dp" items="${dispatchingPlan}" varStatus="rowIndex">
														<tr>
															<td class="center">${rowIndex.index + 1}</td>
															<td>
																<input type="text" id="lNodeId" name="goodsList[${rowIndex.index}].deviceId"  value="${dp.dInfo.deviceName}"/>
															</td>
															<td class="center">${dp.lineNode.nodeNo}
																<input type="text" id="nodeId" name="goodsList[${rowIndex.index}].nodeId"  value="${dp.lineNode.nodeNo}"/>
															</td>
															<td class="center">${dp.lineNode.motionDistrict.districtName}</td>
															<td class="center"><input type="text" id="lNodeId" name="goodsList[${rowIndex.index}].goodsId"  value="${dp.gInfo.goodsName}"/></td>
															<td class="center"><input type="text" id="lNodeId" name="goodsList[${rowIndex.index}].goodsNumber"  value="${dp.goodsNumber}"/></td>
															<td class="center">
																<input class="center" type="hidden"  value="${dp.lineNodeId}"/>
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
												onclick="batchChoiseDevice()">
												<i class="ace-icon fa glyphicon-plus bigger-120"></i>
												批量添加
											</a>
										</div>
										
									</div>
								</div>
							</div>
							<div class="form-group">
									<div class="col-xs-12">
										<div class="widget-box">
											<div class="widget-header widget-header-small">
												<h5>添加线路</h5>
											</div>
											<div class="widget-body">
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															选择配送单号： 
														</label>
														<span class="input-icon input-icon-right">
															<span class="input-icon input-icon-right">
																<input id="orderIdStr" type="hidden" name="dispatchingPlan.deliveryOrderId" value="${dispatchingPlan.deliveryOrderId}"  />
																<input id="orderNameStr" type="text" class="required" style="width:300px" onclick="choiseUser(this)"/>
														</span>
														</span>
													</div>
													<div class="form-group"  >
		                                                <label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
		                                          		          选择配送线路： 
		                                                </label>
		                                                <span class="input-icon input-icon-right">
		                                                	<input id="lineIdStr" type="hidden" name="dispatchingPlan.carLineId" value="${dispatchingPlan.carLineId}"  />
															<input id="lineNameStr" type="text"  class="required" style="width:300px;"  onclick="choiseDevice(this)"/>														
														</span>
		                                            </div>
		                                            <div class="form-group"  >
		                                                <label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
		                                          		          选择配送车辆： 
		                                                </label>
		                                                <span class="input-icon input-icon-right">
		                                                	<input id="carStr" type="hidden" name="dispatchingPlan.dispatchingCar" value="${dispatchingPlan.dispatchingCar}"  />
															<input id="carNameStr" type="text"  class="required" style="width:300px;"  onclick="choiseCar(this)"/>														
														</span>
		                                            </div>
		                                            <div class="form-group"  >
		                                                <label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
		                                          		          选择配送司机： 
		                                                </label>
		                                                <span class="input-icon input-icon-right">
		                                                	<input id="driverStr" type="hidden" name="dispatchingPlan.driverUser" value="${dispatchingPlan.driverUser}"  />
															<input id="driverNameStr" type="text"  class="required" style="width:300px;"  onclick="choiseDriver(this)"/>														
														</span>
		                                            </div>
		                                            <div class="form-group" >
														<label class="col-sm-2 control-label no-padding-right">
															配送员： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="dispatchingUser" type="text"  name="dispatchingPlan.dispatchingUser" value="${dispatchingPlan.dispatchingUser}"  class="required" style="width:300px;"  />														
														</span>
													</div>
													<div class="form-group" style="display:none">
														<label class="col-sm-2 control-label no-padding-right">
															配送日期： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="planDate" type="text"  name="dispatchingPlan.planDate" value="${dispatchingPlan.planDate}"  class="required" style="width:300px;"  />														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															配送开始时间： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="startTime" type="text"  name="dispatchingPlan.startTime" value="${dispatchingPlan.startTime}"  class="required" style="width:300px;"  />														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															配送结束时间： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="endTime" type="text"  value="${dispatchingPlan.endTime}" name="dispatchingPlan.endTime"  class="required" style="width:300px;"  />														
														</span>
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
		
		
		<!-- 配送出库单选择弹出窗开始 -->
		<div id="order_choise_modal" class="modal hiden fade" 
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:860px;">
				<div class="modal-content" >
					<div class="modal-header thicktitle">
						<a class="close" data-dismiss="modal" aria-hidden="true">×</a>
						<span class="modal-title" id="myModalLabel">选择出库单</span>
					</div>
					<div class="modal-body thickcon" style="width:858px;height: 650px; padding-left: 10px; padding-right: 10px;">
						<div class="row">
							<div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
								<div class="input-group" style="float:right;width:300px;">
									<input type="text" id="keyword" class="form-control search-query" placeholder="出库单号" /> 
									<span class="input-group-btn">
										<button id="searchGoodsBtn" type="button" class="btn btn-purple btn-sm no-border">
											<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
											搜索
										</button>
									</span>
								</div>
							</div>
							<div class="col-xs-12">
								<table id="order_list" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center">出库单ID</th>
											<th class="center">出库编号</th>
		                                    <th class="center">始发仓库</th>
		                                    <th class="center">目的地</th>
		                                    <th class="center">出库数量</th>
		                                    <th class="center">出库时间</th>
		                                    <th class="center">出库状态</th>
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
		
		<!-- 配送线路选择弹出窗开始 -->
		<div id="carLine_choise_modal" class="modal hiden fade" 
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:860px;">
				<div class="modal-content" >
					<div class="modal-header thicktitle">
						<a class="close" data-dismiss="modal" aria-hidden="true">×</a>
						<span class="modal-title" id="myModalLabel">选择配送线路</span>
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
								<table id="carLine_list" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center">线路ID</th>
											<th class="center">线路编号</th>
											<th class="center">线路长度</th>
											<th class="center">线路名称</th>
											<th class="center">开始日期</th>
											<th class="center">结束日期</th>
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
		
		<!-- 配送车辆选择弹出窗开始 -->
		<div id="car_choise_modal" class="modal hiden fade" 
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:860px;">
				<div class="modal-content" >
					<div class="modal-header thicktitle">
						<a class="close" data-dismiss="modal" aria-hidden="true">×</a>
						<span class="modal-title" id="myModalLabel">选择配送车辆</span>
					</div>
					<div class="modal-body thickcon" style="width:858px;height: 650px; padding-left: 10px; padding-right: 10px;">
						<div class="row">
							<div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
								<div class="input-group" style="float:right;width:300px;">
									<input type="text" id="keyword" class="form-control search-query" placeholder="车牌号" /> 
									<span class="input-group-btn">
										<button id="searchGoodsBtn" type="button" class="btn btn-purple btn-sm no-border">
											<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
											搜索
										</button>
									</span>
								</div>
							</div>
							<div class="col-xs-12">
								<table id="car_list" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
		                                    <th class="center">所属服务站</th>
		                                    <th class="center">车辆ID</th>
											<th class="center">车牌号</th>
											<th class="center">车辆品牌</th>
		                                    <th class="center">车辆类型</th>
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
		
		<!-- 配送司机选择弹出窗开始 -->
		<div id="driver_choise_modal" class="modal hiden fade" 
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:860px;">
				<div class="modal-content" >
					<div class="modal-header thicktitle">
						<a class="close" data-dismiss="modal" aria-hidden="true">×</a>
						<span class="modal-title" id="myModalLabel">选择司机</span>
					</div>
					<div class="modal-body thickcon" style="width:858px;height: 650px; padding-left: 10px; padding-right: 10px;">
						<div class="row">
							<div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
								<div class="input-group" style="float:right;width:300px;">
									<input type="text" id="keyword" class="form-control search-query" placeholder="用户帐号/用户姓名" /> 
									<span class="input-group-btn">
										<button id="searchGoodsBtn" type="button" class="btn btn-purple btn-sm no-border">
											<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
											搜索
										</button>
									</span>
								</div>
							</div>
							<div class="col-xs-12">
								<table id="driver_list" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center">用户ID</th>
											<th class="center">所属部门</th>
											<th class="center">所属机构</th>
											<th class="center">用户姓名</th>												
											<th class="center">联系电话</th>
											<th class="center">用户角色</th>
											<th class="center">创建日期</th>
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
								<div class="col-xs-12">
									<table id="device_list" class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th class="center">
													<label class="position-relative">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</th>
												<th class="center">设备编号</th>
												<th class="center">设备名称</th>
												<th class="center">所属商圈</th>
												<th class="center">设备地址</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
							</div>
							<div id="choise_device_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
								<a id="choise_device" class="btn btn-sm no-border btn-primary disabled"
								   onclick="batchAppendDevice()">添加设备</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 出库商品选择弹出窗开始 -->
			<div id="out_dtl_modal" class="modal hiden fade"
	             tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	            <div class="modal-dialog" style="width:820px;">
	                <div class="modal-content">
	                    <div class="modal-header thicktitle">
	                        <a class="close" data-dismiss="modal" aria-hidden="true">×</a>
	                        <span class="modal-title" id="myModalLabel">出库明细</span>
	                    </div>
	                    <div class="modal-body thickcon"
	                         style="width:804px;height: 600px; padding-left: 10px; padding-right: 10px;">
	                        <input type="hidden" id="sId"/>
	                        <div class="row">
	                            <div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
	                                <div class="input-group" style="float:right;width:300px;">
	                                    <input type="text" id="keyword" class="form-control search-query"
	                                           placeholder="设备名称/设备编号"/>
										<span class="input-group-btn">
											<button id="searchGoodsBtn" type="button"
	                                                class="btn btn-purple btn-sm no-border">
	                                            <i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
	                                            搜索
	                                        </button>
										</span>
	                                </div>
	
	                                <div class="col-xs-9" style="width: 800px">
	                                    <table id="out_dtl_table"
	                                           class="table table-striped table-bordered table-hover"
	                                           style="text-align: center">
	                                        <thead>
	                                        <tr>
	                                        	<th class="center">商品ID</th>
	                                            <th class="center">出库单编号</th>
	                                            <th class="center">商品名称</th>
	                                            <th class="center">设备名称</th>
	                                            <th class="center">出库数量</th>
	                                            <th class="center">明细备注</th>
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
	        </div>

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
				<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js" ></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js" ></script>
		<script src="${pageContext.request.contextPath}/js/biz/region/dispatchPlanAdd.js"></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
		
		
	</body>
</html>



