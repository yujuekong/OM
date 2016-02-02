<%@ page language="java" pageEncoding="utf-8"%>
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

					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="#">首页</a>
						</li>
						<li><a href="#">销售管理</a></li>
						<li class="active"><a href="${pageContext.request.contextPath}/view/sale/gameInfoList.jsp">抽奖活动管理</a></li>
						<li class="active">活动奖励管理</li>
					</ul><!-- /.breadcrumb -->
				</div>
				
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="row">
							<div class="searchbar">
							<ul class="list-inline">
								<li>
									&nbsp;&nbsp;<span class="input-icon">
								        <input type="text" placeholder="Search ..." class="nav-search-input"
											   id="prize_keyword" autocomplete="off" style="height: 30px"/>
								     <i class="ace-icon fa fa-search nav-search-icon"></i>
							  </span>
								</li>
								<span  style="float:right;" class="tooltip-info" data-rel="tooltip" title="新增">
									<button onclick="saveOrUpdatePrize('');" class="btn btn-xs btn-success">新增
									</button>&nbsp;&nbsp;&nbsp;&nbsp;
								</span>
							</ul>
							<input type="hidden" id="gameId" value="${gameId }"/>
						</div>
								<div class="col-xs-12">
									<table id="gameInfo_list" class="table table-striped table-bordered table-hover" style="text-align: center">
										<thead>
											<tr>
												<th class="center">奖励名称</th>
												<th class="center">活动名称</th>
												<th class="center">奖励类型</th>
												<th class="center">奖励数量（面额）</th>
												<th class="center">奖品总数量</th>
												<th class="center">中奖概率</th>
												<th class="center">操作</th>
											</tr>
										</thead>

										<tbody>
										</tbody>
									</table>
								</div><!-- /.span -->
							</div><!-- /.row -->
							<div><br></div>
							
							
							<!-- 添加角色开始 -->
							<form action="${pageContext.request.contextPath}/view/sale/gameInfo/addGamePrize.action" method="post" onsubmit="return checkPrize();">
							<div class="form-group"  id="role_choise_modal" style="display: none" >
								<div class="col-xs-12">
									<div class="widget-box">
										<div class="widget-header">
											<h5>添加奖励</h5>
										</div>
										<div class="widget-body">
											<div class="widget-main">
												<div class="form-group">
													<div class="row">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															奖励名称： </label>
														<div class="col-xs-2 input-group input-group-sm">
															<input type="text"  class="required" id="prizeName" name="activityGamePrize.prizeName" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<div class="row">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															奖励类型： </label>
														<div class="col-xs-2 input-group input-group-sm">
															<select name="activityGamePrize.prizeType" id="prizeType" class="required">
																<option value="1">积分</option>
																<option value="0">现金</option>
															</select>
														</div>
													</div>
												</div>
												<div class="form-group">
													<div class="row">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															奖励数量： </label>
														<div class="col-xs-2 input-group input-group-sm">
															<input type="text"  class="required" id="delive_time" name="activityGamePrize.prizeAmount" />
														</div>
													</div>
												</div>
												<div class="form-group">
													<div class="row">
														<label class="col-sm-2 control-label no-padding-right"><i class="red">*</i>
															中奖概率： </label>
														<div class="col-xs-2 input-group input-group-sm">
															<input type="text"  class="required" id="delive_time" name="activityGamePrize.prizeAmount" />
														</div>
													</div>
												</div>
												<input type="hidden" id="gamePId" name="gameId" value="${gameId }"/>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- 添加角色结束 -->
							<!-- 表单操作按钮开始 -->
							<div class="row"  id="submit_choise_modal" style="display: none">
								<div class="col-xs-12" style="text-align: center">
									<button class="btn btn-sm no-border btn-success">
										<i class="ace-icon fa fa-check"></i>提交</button>&nbsp;&nbsp;&nbsp;
								</div>
							</div>
							</form>
							<!-- 表单操作按钮结束 -->
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->

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
		<script src="${pageContext.request.contextPath}/js/biz/sale/gamePrizeList.js"></script>
		<script type="text/javascript">
			$("#submenu-menu-advert-info").addClass("active");
			$("#menu-advert").addClass("active");
			$("#menu-advert").addClass("open");
		</script>
	</body>
</html>
