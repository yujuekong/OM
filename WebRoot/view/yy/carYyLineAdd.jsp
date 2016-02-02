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
						<li><a href="#">配送线路管理</a></li>
						<li class="active">新增配送线路</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<form action="../carYyLine/saveOrUpdateCarYyLine.action" method="post" >
							<div class="col-xs-12">
								<div class="form-group">
									<div class="col-xs-12">
										<div class="widget-box">
											<div class="widget-header widget-header-small">
												<h5 id="h5Id">新增配送线路</h5>
											</div>
											<div class="widget-body">
												<div class="widget-main">
													<div class="form-group"  >
		                                                <label class="col-sm-2 control-label no-padding-right">
		                                          		          选择司机： 
		                                                </label>
														<span class="input-icon input-icon-right">
														<input id="userIdStr" type="hidden" name="carYyLine.userId" value="${carYyLine.userId}"  />
															<input  type="text"  id="userNameStr" class="required" style="width:450px" onclick="choiseUser(this)"/>
														</span>
		                                            </div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															选择线路： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="deviceIdStr" type="hidden" name="carYyLine.carLineId" value="${carYyLine.carLineId}"  />
															<input id="deviceNameStr" type="text"    class="required" style="width:450px;"  onclick="choiseDevice(this)"/>														
														</span>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label no-padding-right">
															选择车辆： 
														</label>
														<span class="input-icon input-icon-right">
															<input id="carIdStr" type="hidden" name="carYyLine.carId" value="${carYyLine.carId}"  />
															<input id="carNameStr" type="text"  value="${carYyLine.carId}"  class="required" style="width:450px;"  onclick="choiseCar(this)"/>														
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
						<span class="modal-title" id="myModalLabel">选择线路</span>
					</div>
					<div class="modal-body thickcon" style="width:858px;height: 650px; padding-left: 10px; padding-right: 10px;">
						<input type="hidden" id="purchase_goods_rowno" />
						<input type="hidden" id="choise_type" value="0"/>
						<div class="row">
							<div class="col-xs-3">
								<div style="height: 410px;overflow-y: scroll; border: 1px solid #ddd;">
									<ul id="carLine_tree" class="ztree"></ul>
								</div>
							</div>
							<div class="col-xs-9">
								<table id="deviceInfo_list" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center">车辆线路ID</th>
											<th class="center">线路长度</th>
											<th class="center">线路类型</th>
											<th class="center">线路名称</th>
											<th class="center">线路编号</th>
											<th class="center">开始日期</th>												
											<th class="center">结束日期</th>
											<th class="center">在线控制时长</th>
											<th class="center">维护人员</th>
											<th class="center">维护日期</th>
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
						<span class="modal-title" id="myModalLabel">选择司机</span>
					</div>
					<div class="modal-body thickcon" style="width:858px;height: 530px; padding-left: 10px; padding-right: 10px;">
						<input type="hidden" id="purchase_goods_rowno" />
						<input type="hidden" id="choise_type" value="0"/>
						<div class="row">
							<div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
								<div class="input-group" style="float:right;width:300px;">
									<input type="text" id="keyword" class="form-control search-query" placeholder="司机帐号/司机姓名" /> 
									<span class="input-group-btn">
										<button id="searchGoodsBtn" type="button" class="btn btn-purple btn-sm no-border">
											<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
											搜索
										</button>
									</span>
								</div>
							</div>
							<div class="col-xs-3">
								<div style="height: 410px;overflow-y: scroll; border: 1px solid #ddd;">
									<ul id="user_tree" class="ztree"></ul>
								</div>
							</div>
							<div class="col-xs-9">
								<table id="user_list" class="table table-striped table-bordered table-hover">
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
						<div id="choise_goods_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
							<a id="choise_submit" class="btn btn-sm no-border btn-primary disabled" onclick="batchAppendGoods()">加入广告内容</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div id="car_choise_modal" class="modal hiden fade" 
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:860px;">
				<div class="modal-content" >
					<div class="modal-header thicktitle">
						<a class="close" data-dismiss="modal" aria-hidden="true">×</a>
						<span class="modal-title" id="myModalLabel">选择车辆</span>
					</div>
					<div class="modal-body thickcon" style="width:858px;height: 650px; padding-left: 10px; padding-right: 10px;">
						<input type="hidden" id="purchase_goods_rowno" />
						<input type="hidden" id="choise_type" value="0"/>
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
							<div class="col-xs-3">
								<div style="height: 410px;overflow-y: scroll; border: 1px solid #ddd;">
									<ul id="car_tree" class="ztree"></ul>
								</div>
							</div>
							<div class="col-xs-9">
								<table id="car_list" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
												<th class="center">车辆ID</th>
												<th class="center">车牌号</th>
												<th class="center">车辆品牌</th>
												<th class="center">车辆类型</th>
												<th class="center">车辆状态</th>													
												<th class="center">创建日期</th>
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
		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js" ></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js" ></script>
		<script src="${pageContext.request.contextPath}/js/biz/yy/carYyLineAdd.js"></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
		
		
	</body>
</html>



