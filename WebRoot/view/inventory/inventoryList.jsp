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

					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="#">首页</a>
						</li>
						<li><a href="#">仓库管理</a></li>
						<li class="active">库存管理</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<a href="javascript:history.back()" >
							<i class="ace-icon fa fa-arrow-left"></i>返回
						</a>
					</div><!-- /.nav-search -->
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-2">
							<div style="height: 500px; overflow-y: scroll; border: 1px solid #ddd;">
								<ul id="goodstype_tree" class="ztree"></ul>
							</div>
						</div>
						<div class="col-xs-10">
							<!-- PAGE CONTENT BEGINS -->
							<div class="row">
								<div class="col-xs-12">
									<table id="sample-table-1" class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th class="center">
													<label class="position-relative">
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</th>
												<th class="center">仓库</th>
												<th class="center">商品信息</th>
												<th class="center">当前库存</th>
												<th class="center">可用库存</th>
												<th class="center">预留库存</th>						
												<th class="center">破损库存</th>
												<th class="center">在途库存</th>
											</tr>
										</thead>

										<tbody id="inventory_list">

										</tbody>
									</table>
								</div><!-- /.span -->
							</div><!-- /.row -->

							<div class="hr hr-18 dotted hr-double"></div>


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
		<script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.dataTables.bootstrap.js"></script>
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.js"></script>
		<script src="${pageContext.request.contextPath}/js/date-time/bootstrap-datetimepicker.zh-CN.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.min.js" ></script>
		<script src="${pageContext.request.contextPath}/plugins/zTree/js/jquery.ztree.core-3.5.js" ></script>
		<script src="${pageContext.request.contextPath}/js/ycs.dateUtil.js" ></script>
		<script type="text/javascript">
			$("#submenu-menu-inventory-query").addClass("active");
			$("#menu-inventory").addClass("active");
			$("#menu-inventory").addClass("open");
			var setting = {
				data: {
					simpleData: {
						enable: true
					}
				}
			};	
			var zNodes =[
				{ id:1, pId:0, name:"所有仓库", open:true},
				{ id:11, pId:1, name:"武汉江夏仓库"},
				{ id:12, pId:1, name:"武汉东西湖仓库"},
				{ id:13, pId:1, name:"深圳龙岗仓库"}
			];

			var inventoryList =[
				{warehouseLocation:"武汉江夏工业园", goodsInfo:"苹果", currentInventory:"120kg",  userableInventory:"100kg",reservedInventory:"10kg",damagedInventory:"10kg", transportationInventory:"20kg"},
				{warehouseLocation:"武汉江夏工业园", goodsInfo:"橙子", currentInventory:"100kg",  userableInventory:"75kg",reservedInventory:"10kg",damagedInventory:"5kg", transportationInventory:"20kg"},
				{warehouseLocation:"武汉东西工业园", goodsInfo:"桔子", currentInventory:"80kg",  userableInventory:"65kg",reservedInventory:"10kg",damagedInventory:"5kg", transportationInventory:"20kg"},
				{warehouseLocation:"沈阳工业园", goodsInfo:"橙子", currentInventory:"100kg",  userableInventory:"75kg",reservedInventory:"10kg",damagedInventory:"15kg", transportationInventory:"20kg"},
			];	
			$(document).ready(function(){
				$.fn.zTree.init($("#goodstype_tree"), setting, zNodes);
			});
			
			var str = "";
					
			for(var i = 0; i < inventoryList.length; i++){
					str = str + "<tr>" +
						"<td class='center'>" +
							"<label class='position-relative'>" +
								"<input type='checkbox' class='ace' />" +
									"<span class='lbl'></span>" +
							"</label>" +
						"</td>" +
						"<td class='center'>" + inventoryList[i].warehouseLocation + "</td>" +
						"<td class='center'>" + inventoryList[i].goodsInfo + "</td>" ;

						str = str + "<td class='center'>" + inventoryList[i].currentInventory + "</td>" +
						"<td class='center'>" + inventoryList[i].userableInventory + "</td>" +
						"<td class='center'>" + inventoryList[i].reservedInventory + "</td>" +
						"<td class='center'>" + inventoryList[i].damagedInventory + "</td>" +
						"<td class='center'>" + inventoryList[i].transportationInventory+ "</td>" ;	
					str = str +"</tr>" ;
				}
				$("#inventory_list").empty();
				$("#inventory_list").append(str);		
		</script>
	</body>
</html>



