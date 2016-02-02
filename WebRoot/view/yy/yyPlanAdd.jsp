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
						<form action="http://localhost:8080/OM/view/car/carDispatch/saveOrUpdateCarDispatch.action" method="post" >
							<div class="col-xs-12">
								<div class="form-group">
									<div class="col-xs-12">
										<div class="widget-box">
											<div class="widget-header widget-header-small">
												<h5 id="h5Id">新增配送计划</h5>
											</div>
											<div class="widget-body">
												<div class="widget-main">
													<div class="form-group"  >
		                                                <label class="col-sm-2 control-label no-padding-right">
		                                          		          选择配送线路： 
		                                                </label>
		                                                <span class="input-icon input-icon-right">
		                                                	<input id="yyLineIdStr" type="hidden" name="carDispatch.yyLineId" value="${carDispatch.yyLineId}"  />
															<input id="yyLineNameStr" type="text"  class="required" style="width:450px;"  onclick="choiseDevice(this)"/>														
															
														</span>
		                                            </div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															选择出库单： 
														</label>
														<span class="input-icon input-icon-right">
															<span class="input-icon input-icon-right">
																<input id="userIdStr" type="hidden" name="carDispatch.deliveryOrderId" value="${carDispatch.deliveryOrderId}"  />
																<input  type="text"  id="userNameStr" class="required" style="width:450px" onclick="choiseUser(this)"/>
														</span>
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															调度日期： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="dispatchDate" type="text"  name="carDispatch.dispatchDate" value="${carDispatch.dispatchDate}"  class="required" style="width:450px;"  />														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															调度开始时间： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="startTime" type="text"  name="carDispatch.startTime" value="${carDispatch.startTime}"  class="required" style="width:450px;"  />														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															调度结束时间： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="endTime" type="text"  value="${carDispatch.endTime}" name="carDispatch.endTime"  class="required" style="width:450px;"  />														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															调度状态： 
														</label>
														<span class="input-icon input-icon-right">
															<select id="statusSelect" lass="required" style="width: 150px" name="carDispatch.dispatchStatus" >
																	<option value="0">在途</option>
																	<option value="1">完成</option>
																</select>	
															</span>
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
						<span class="modal-title" id="myModalLabel">选择配送线路</span>
					</div>
					<div class="modal-body thickcon" style="width:858px;height: 530px; padding-left: 10px; padding-right: 10px;">
						<input type="hidden" id="purchase_goods_rowno" />
						<input type="hidden" id="choise_type" value="0"/>
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
								<table id="yyLine_list" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center">配送ID</th>
											<th class="center">配送车辆车牌号</th>
											<th class="center">配送司机</th>
											<th class="center">线路名称</th>
											<th class="center">载重大小</th>
											<th class="center">线路长度</th>
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
		
		<div id="user_choise_modal" class="modal hiden fade" 
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:860px;">
				<div class="modal-content" >
					<div class="modal-header thicktitle">
						<a class="close" data-dismiss="modal" aria-hidden="true">×</a>
						<span class="modal-title" id="myModalLabel">选择出库单</span>
					</div>
					<div class="modal-body thickcon" style="width:858px;height: 530px; padding-left: 10px; padding-right: 10px;">
						<input type="hidden" id="purchase_goods_rowno" />
						<input type="hidden" id="choise_type" value="0"/>
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
								<table id="user_list" class="table table-striped table-bordered table-hover">
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
		<script src="${pageContext.request.contextPath}/js/biz/yy/yyPlanAdd.js"></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
		
		
	</body>
</html>



