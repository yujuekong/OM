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
						<li><a href="#">系统</a></li>
						<li class="active">数据字典管理</li>
					</ul><!-- /.breadcrumb -->

					<div class="nav-search" id="nav-search">
						<button class="btn btn-xs btn-success" onclick="preAddDict()">新增</button>
					</div>
				</div>

				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<div class="row">
								<div class="col-xs-12">
									<table id="sample-table-1" class="table table-striped table-bordered table-hover" style="text-align: center">
										<thead>
											<tr>
												<th class="center">序号</th>
												<th class="center">数据字典名称</th>
												<th class="center">数据字典值</th>
												<th class="center">数据字典排序</th>
												<th class="center">数据字典描述</th>
												<th class="center">数据字典级别</th>
												<th class="center">操作</th>
											</tr>
										</thead>

										<tbody>
											<c:if test="${empty  sysDictList}">
												<tr>
													<td colspan="7" class="center">暂无数据</td>	
												</tr>
											</c:if>
											<c:forEach var="sysDict" items="${sysDictList }" varStatus="rowIndex" >
												<tr>
													<td class="center">${rowIndex.count }</td>	
													<td><a href="${pageContext.request.contextPath}/view/sys/sysDict/querySysDictByPid.action?dictId=${sysDict.dictId }">${sysDict.dictName }</a> </td>
													<td>${sysDict.dictCode }</td>
													<td>${sysDict.dictSort }</td>
													<td>${sysDict.dictDesc }</td>
													<td>${sysDict.dictLevel }</td>
													<td>
														<div class="hidden-sm hidden-xs btn-group">
															<!--<button class="btn btn-xs btn-info" onclick="modifyDict('${sysDict.dictId }','${sysDict.dictName }','${sysDict.dictSort }','${sysDict.dictDesc }')">
																<i class="ace-icon fa fa-pencil bigger-120"></i>
															</button>
															--><button class="btn btn-xs btn-danger" onclick="deleteDict('${sysDict.dictId }','${sysDict.dictPid }')">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
															</button>
														</div>															
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div><!-- /.span -->
							</div><!-- /.row -->
							<!-- <div class="col-xs-6">
								<tr>
									
									<td> 
										<button class="btn btn-xs btn-success" onclick="preAddDict()">新增</button>
									</td>									
									<td style="vertical-align:top;"> 
										
									</td>
								</tr>
							</div>	 -->
							

						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->
		<!-- 商品选择弹出窗开始 -->
		<form action="${pageContext.request.contextPath}/view/sys/sysDict/saveOrUpdateDict.action" method="post">
		<div id="dict_choise_modal" class="modal hiden fade" 
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:360px;">
				<div class="modal-content" >
					<div class="modal-header thicktitle">
						<a class="close" data-dismiss="modal" aria-hidden="true">×</a>
						<span class="modal-title" id="myModalLabel">增加数据字典</span>
						<input type="hidden" name="sysDict.dictPid"  value="${dictId }" />
						<input type="hidden" name="sysDict.dictLevel"   value="${dictLevel }"/>
						<input type="hidden" name="sysDict.dictPcode"   value="${dictPcode }"/>
						<input type="hidden" name="sysDict.dictTopCode"   value="${dictTopCode }"/>
					</div>
					<div class="modal-body thickcon" style="width:358px;height: 230px; padding-left: 10px; padding-right: 10px;">
						<div class="row">
							<div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
								<div class="input-group" style="float:left;width:300px;">
									<input type="text" name="sysDict.dictName" class="form-control search-query" placeholder="数据字典名称" /> 
								</div>
							</div>							
						</div>
						<div class="row">
							<div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
								<div class="input-group" style="float:left;width:300px;">
									<input type="text" name="sysDict.dictCode" class="form-control search-query" placeholder="数据字典编码" /> 
								</div>
							</div>							
						</div>
						<div class="row">
							<div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
								<div class="input-group" style="float:left;width:300px;">
									<input type="text" name="sysDict.dictDesc" class="form-control search-query" placeholder="数据字典描述" /> 
								</div>
							</div>							
						</div>
						<div class="row">
							<div id="search-form" class="col-xs-12" style="padding-bottom: 5px;">
								<div class="input-group" style="float:left;width:300px;">
									<input type="text" name="sysDict.dictSort" class="form-control search-query" placeholder="排序" /> 
								</div>
							</div>							
						</div>
						<div id="choise_goods_action" class="col-xs-12" style="text-align:center;margin-top:10px;">
							<!--<a id="choise_submit" class="btn btn-sm no-border btn-primary" onclick="addDict()">加入数据字典</a>
							--><button class="btn btn-sm no-border btn-success" onclick="addDict('${dictId }')">
										<i class="ace-icon fa fa-floppy-o"></i>加入数据字典</button>&nbsp;&nbsp;&nbsp;
						</div>
					</div>
				</div>
			</div>
		</div>
		</form>
		<!-- 商品选择弹出窗结束 -->
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
		<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
		<script src="${pageContext.request.contextPath}/js/ycs.util.js" ></script>
		<script src="${pageContext.request.contextPath}/js/biz/sys/dictList.js" ></script>
		
	</body>
</html>



