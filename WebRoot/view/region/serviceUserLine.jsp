<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
						<li><a href="#">配送管理</a></li>
						<li class="active">用户操作详细信息</li>
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
							<div id="timeline-1">
								<div class="row">
									<div class="col-xs-12 col-sm-10 col-sm-offset-1">
										<c:forEach var="uMap" items="${sysUserOpMap}">
											<div class="timeline-container">
												<div class="timeline-label">
													<span class="label label-primary arrowed-in-right label-lg">
														<b>
														<c:if test="${uMap.key==dDate}">今天</c:if>
														<c:if test="${uMap.key==qDate}">昨天</c:if>
														<c:if test="${uMap.key!=qDate && uMap.key!=dDate}">${uMap.key}</c:if></b>
													</span>
												</div>
												<c:forEach items="${uMap.value}" var="sysUserOp">
													<div class="timeline-items">
														<div class="timeline-item clearfix">
															<div class="timeline-info">
																<i class="timeline-indicator ace-icon fa fa-star btn btn-warning no-hover green"></i>
															</div>
															<div class="widget-box transparent">
																<div class="widget-header widget-header-small">
																	<h5 class="widget-title smaller">
																	<c:if test="${sysUserOp.bizType==1}">用户设备清洗</c:if>
																	<c:if test="${sysUserOp.bizType==2}">用户设备维修洗</c:if>
																	<c:if test="${sysUserOp.bizType==3}">用户商品入库</c:if>
																	<c:if test="${sysUserOp.bizType==4}">用户商品出库</c:if>
																	<c:if test="${sysUserOp.bizType==5}">用户商品出库处理</c:if>
																	<c:if test="${sysUserOp.bizType==6}">用户配送</c:if>
																	<c:if test="${sysUserOp.bizType==7}">商品分配</c:if>
																	：订单号：${sysUserOp.bizNo}</h5>
		
																	<span class="widget-toolbar">
																		<i class="ace-icon fa fa-clock-o bigger-110"></i>
																		${sysUserOp.opTime}
																	</span>
																</div>
		
																<div class="widget-body">
																	<div class="widget-main">
																		<div class="clearfix">
																			<div class="pull-left">
																				原因：${sysUserOp.opDesc}
																				<br />
																				维修说明：${sysUserOp.opReason}
																			</div>
																			<div class="ace-thumbnails pull-right">
																				<i class="ace-icon fa fa-chevron-left blue bigger-110"></i>
																				&nbsp;
																				<script type="text/javascript">
																				</script>
																				<c:if test="${sysUserOp.opPic1 !=null }">
																				<a href="${pageContext.request.contextPath}${sysUserOp.opPic1}" title="Photo Title" data-rel="colorbox">
																					<img alt="Image 4" width="36" src="${pageContext.request.contextPath}${sysUserOp.opPic1}" />
																				</a>
																				</c:if>
																				<c:if test="${sysUserOp.opPic2 !=null }">
																				<a href="${pageContext.request.contextPath}${sysUserOp.opPic2}" title="Photo Title" data-rel="colorbox">
																				<img alt="Image 3" width="36" src="${pageContext.request.contextPath}${sysUserOp.opPic2}" />
																				</a>
																				</c:if>
																				<c:if test="${sysUserOp.opPic3 !=null }">
																				<a href="${pageContext.request.contextPath}${sysUserOp.opPic3}" title="Photo Title" data-rel="colorbox">
																				<img alt="Image 2" width="36" src="${pageContext.request.contextPath}${sysUserOp.opPic3}" />
																				</a>
																				</c:if>
																				<c:if test="${sysUserOp.opPic4 !=null }">
																				<a href="${pageContext.request.contextPath}${sysUserOp.opPic4}" title="Photo Title" data-rel="colorbox">
																				<img alt="Image 1" width="36" src="${pageContext.request.contextPath}${sysUserOp.opPic4}" />
																				</a>
																				</c:if>
																				<c:if test="${sysUserOp.opPic5 !=null }">
																				<a href="${pageContext.request.contextPath}${sysUserOp.opPic5}" title="Photo Title" data-rel="colorbox">
																				<img alt="Image 5" width="36" src="${pageContext.request.contextPath}${sysUserOp.opPic5}" />
																				</a>
																				</c:if>
																				&nbsp;
																				<i class="ace-icon fa fa-chevron-right blue bigger-110"></i>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</c:forEach>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
							<!-- PAGE CONTENT ENDS -->
						</div><!-- /.col -->
					</div><!-- /.row -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->
			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
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
		<script src="${pageContext.request.contextPath}/js/jquery.form.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-extra.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/ace.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.colorbox-min.js"></script>

		<!-- inline scripts related to this page -->
		<script src="${pageContext.request.contextPath}/js/ycs.util.js" ></script>
		<script src="${pageContext.request.contextPath}/js/sys/userList.js" ></script>
		<script type="text/javascript">
			$("#submenu-menu-area-user").addClass("active");
			$("#menu-area").addClass("active");
			$("#menu-area").addClass("open");
			jQuery(function($) {
			var $overflow = '';
			var colorbox_params = {
				rel: 'colorbox',
				reposition:true,
				scalePhotos:true,
				scrolling:false,
				previous:'<i class="ace-icon fa fa-arrow-left"></i>',
				next:'<i class="ace-icon fa fa-arrow-right"></i>',
				close:'&times;',
				current:'{current} of {total}',
				maxWidth:'100%',
				maxHeight:'100%',
				onOpen:function(){
					$overflow = document.body.style.overflow;
					document.body.style.overflow = 'hidden';
				},
				onClosed:function(){
					document.body.style.overflow = $overflow;
				},
				onComplete:function(){
					$.colorbox.resize();
				}
			};
		
			$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
			$("#cboxLoadingGraphic").append("<i class='ace-icon fa fa-spinner orange'></i>");//let's add a custom loading icon
		})
		</script>
	</body>
</html>



