<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<jsp:include page="../commons/head.jsp" />
	<body class="no-skin">
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try {
					ace.settings.check('main-container', 'fixed')
				} catch (e) {
				}
			</script>
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
						<li><a href="#">支付管理</a></li>
						<li class="active">支付信息管理</li>
					</ul><!-- /.breadcrumb -->

					<!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="searchbar">
								<ul class="list-inline">
									<li>
										<div class="btn-group">
											<p>
												<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"
												   onclick="quickSearch(0)">全部 </a>
												<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"
												   onclick="quickSearch(1)">本周</a>
												<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"
												   onclick="quickSearch(2)">上周</a>
												<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"
												   onclick="quickSearch(3)">本月</a>
												<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"
												   onclick="quickSearch(4)">上月</a>
											</p>
										</div>
									</li>
									<li>
										<label>开始日期： </label>
										<span class="input-icon input-icon-right">
											<input type="text" id="dp_quoteStartDate" name="quoteStartDate"
												   class="input-sm" style="width:110px;" onchange="dateFind()"/>
											<i class="ace-icon fa fa-calendar blue"></i>
										</span>
										<label>至</label>
										<span class="input-icon input-icon-right">
											<input type="text" id="dp_quoteEndDate" name="quoteEndDate" class="input-sm"
												   style="width:110px;" onchange="dateFind()"/>
											<i class="ace-icon fa fa-calendar blue"></i>
										</span>
                                    <span class="input-icon">
								        <input type="text" placeholder="Search ..." class="nav-search-input"
											   id="searchGoodsBtn" autocomplete="off" style="height: 30px"/>
								     <i class="ace-icon fa fa-search nav-search-icon"></i>
							        </span>
									</li>
									<!-- <a style="float: right" class="tooltip-info" data-rel="tooltip" title="新增">
										<button onclick="preAddOrModifyOrderIn('')" class="btn btn-xs btn-success">新增
										</button>
									</a> -->
								</ul>
							</div>
							<%--<div class="searchbar">--%>
								<%--<div >--%>
									<%--<label>快捷搜索： </label>--%>
									<%--<div class="btn-group">--%>
										<%--<p>--%>
											<%--<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"--%>
		                                       <%--onclick="quickSearch(0)">全部 </a>--%>
		                                    <%--<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"--%>
		                                       <%--onclick="quickSearch(1)">本周</a>--%>
		                                    <%--<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"--%>
		                                       <%--onclick="quickSearch(2)">上周</a>--%>
		                                    <%--<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"--%>
		                                       <%--onclick="quickSearch(3)">本月</a>--%>
		                                    <%--<a href="javascript:void(0);" class="label label-lg label-pink arrowed-right"--%>
		                                       <%--onclick="quickSearch(4)">上月</a>--%>
										<%--</p>--%>
									<%--</div>--%>
								<%--</div>--%>
								<%--<ul class="list-inline">--%>
									<%----%>
									<%--<li>--%>
										<%--<label>开始日期： </label>--%>
										<%--<span class="input-icon input-icon-right">--%>
											<%--<input type="text" id="dp_quoteStartDate" name="quoteStartDate" class="input-sm" style="width:110px;" />--%>
											<%--<i class="ace-icon fa fa-calendar blue"></i>--%>
										<%--</span>--%>
										<%--<label>至</label>--%>
										<%--<span class="input-icon input-icon-right">--%>
											<%--<input type="text" id="dp_quoteEndDate" name="quoteEndDate" class="input-sm" style="width:110px;"/>--%>
											<%--<i class="ace-icon fa fa-calendar blue"></i>--%>
										<%--</span>--%>
									<%--</li>--%>
									<%--<li>--%>
										<%--<label>交易号： </label>--%>
										<%--<input type="text" id="quotationNo" name="quotationNo" class="input-sm" style="width:100px;" />--%>
										<%--<a id="searchQuoteBillBtn" class="btn btn-sm btn-success no-border" onclick="initTable()">--%>
											<%--<i class="ace-icon fa fa-search nav-search-icon"></i>--%>
											<%--搜索</a>--%>
									<%--</li>--%>
								<%--</ul>--%>
							<%--</div>--%>
						</div>
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="row">
								<div class="col-xs-12">
									<table id="payInfo_List" class="table table-striped table-bordered table-hover"  
									style="text-align: center">
										<thead>
											<tr>
												<th class="center">序号</th>
												<th class="center">设备编号</th>
												<th class="center">设备名称</th>
												<th class="center">交易状态</th>	
												<th class="center">支付的交易号</th>
												<th class="center">买家支付的用户号</th>
												<th class="center">买家支付账号</th>
												<th class="center">商户订单号</th>
												<th class="center">订单金额</th>
												<th class="center">实收金额</th>
												<th class="center">支付类型</th>
												<th class="center">交易创建时间</th>
												<th class="center">付款时间</th>
												<th class="center">是否退款</th>
												<th class="center">退款金额</th>
												<th class="center">退款时间</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div><!-- /.span -->
							</div><!-- /.row -->
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
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/assets/jquery.dataTables.bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery-ui.custom.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js"></script>
		
		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
		<script src="${pageContext.request.contextPath}/js/biz/pay/payInfoList.js"></script>
	</body>
</html>



