<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<ul class="nav nav-list">
		<li id="menu-home" class="open hover highlight">
			<a href="${pageContext.request.contextPath}/view/commons/main.jsp"> 
			<i class="menu-icon fa fa-home home-icon"></i> 
			<span class="menu-text">首页 </span>
			</a> <b class="arrow"></b>
		</li>
		<c:forEach var="sysMenu" items="${menuList }" varStatus="rowIndex" >
			<li id="${sysMenu.menuCss }" >
				<a href="#" class="dropdown-toggle">
					<i class="${sysMenu.menuIcon }"></i>
					<span class="menu-text">${sysMenu.menuName } </span>	
					<b class="arrow fa fa-angle-down"></b>
				</a>
				<b class="arrow"></b>
				<c:if test="${! empty sysMenu.sysMenulist }">
					<ul class="submenu">
					<c:forEach var="oneMenu" items="${sysMenu.sysMenulist }" varStatus="rowIndex" >
						<li id="${oneMenu.menuCss }" >
							<a  target="mainFrame"  href="${oneMenu.menuUrl }" <c:if test="${! empty oneMenu.sysMenulist }">class="dropdown-toggle"</c:if> >
								<i class="menu-icon fa fa-caret-right"></i> ${oneMenu.menuName }
								<c:if test="${! empty oneMenu.sysMenulist }"><b class="arrow fa fa-angle-down"></b></c:if>								
							</a>
							<b class="arrow"></b>
							<c:if test="${! empty oneMenu.sysMenulist }">
								<ul class="submenu">
								<c:forEach var="twoMenu" items="${oneMenu.sysMenulist }"  >
									<li id="${twoMenu.menuCss }" class="">
										<a target="mainFrame" href="${twoMenu.menuUrl }">
											<i class="menu-icon fa fa-caret-right"></i>${twoMenu.menuName }
										</a>
										<b class="arrow"></b>
									</li>
								</c:forEach>
								</ul>
							</c:if>							
						</li>
					</c:forEach>
					</ul>
				</c:if>
			</li>
		</c:forEach> 
		
		<!--<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-desktop"></i>
				<span class="menu-text"> 设备管理 </span>

				<b class="arrow fa fa-angle-down"></b>
			</a>
			<b class="arrow"></b>
			<ul class="submenu">
				<li class="">							
					<a target="mainFrame" href="${pageContext.request.contextPath}/view/device/deviceTypeList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						设备类型管理						
					</a>
					<b class="arrow"></b>					
				</li>

				<li class="">
					<a target="mainFrame" href="#" class="dropdown-toggle">
						<i class="menu-icon fa fa-caret-right"></i>
						设备信息管理
						<b class="arrow fa fa-angle-down"></b>
					</a>
					<b class="arrow"></b>
					<ul class="submenu">						
						<li class="">
							<a target="mainFrame" href="${pageContext.request.contextPath}/view/device/deviceInfoList.jsp">
								<i class="menu-icon fa fa-caret-right"></i>
								设备信息查询
							</a>
							<b class="arrow"></b>
						</li>
						<li class="">
							<a target="mainFrame" href="${pageContext.request.contextPath}/view/device/deviceInfoList.jsp">
								<i class="menu-icon fa fa-caret-right"></i>
								设备分布地图
							</a>
							<b class="arrow"></b>
						</li>
					</ul>
				</li>

				<li class="">
					<a target="mainFrame" href="${pageContext.request.contextPath}/view/device/deviceMaintenanceList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						设备维护管理
					</a>
					<b class="arrow"></b>
				</li>

				<li class="">
					<a target="mainFrame" href="${pageContext.request.contextPath}/view/device/deviceCleanList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						设备清洗管理
					</a>
					<b class="arrow"></b>
				</li>
			</ul>
		</li>
		
		<li class="">
			<a  target="mainFrame" href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-book"></i>
				<span class="menu-text"> 商品管理 </span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			<b class="arrow"></b>
			<ul class="submenu">
				 <li class="">
					<a target="mainFrame" href="${pageContext.request.contextPath}/view/goods/goodsTypeList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						商品类型管理
					</a>
					<b class="arrow"></b>
				</li> 

				<li class="">
					<a target="mainFrame" href="${pageContext.request.contextPath}/view/goods/goodsInfoList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						商品信息管理
					</a>
					<b class="arrow"></b>
				</li>
			</ul>
		</li>

		<li class="">
			<a target="mainFrame"  href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-car"></i>
				<span class="menu-text"> 车辆管理 </span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>

			<ul class="submenu">
				<li class="">
					<a  target="mainFrame" href="${pageContext.request.contextPath}/view/car/carInfoList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						车辆信息管理
					</a>
					<b class="arrow"></b>
				</li>

				<li class="">
					<a  target="mainFrame" href="${pageContext.request.contextPath}/view/car/carDispatchList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						车辆调度管理
					</a>
					<b class="arrow"></b>
				</li>
			</ul>
		</li>
		
		<li class="">
			<a target="mainFrame" href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-exchange"></i>
				<span class="menu-text"> 仓储管理 </span>

				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>

			<ul class="submenu">
				<li class="">
					<a  target="mainFrame" href="${pageContext.request.contextPath}/view/inventory/warehouseList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						仓库信息管理
					</a>
					<b class="arrow"></b>
				</li>

				<li class="">
					<a href="#" class="dropdown-toggle">
						<i class="menu-icon fa fa-caret-right"></i>
						商品出入库管理
						<b class="arrow fa fa-angle-down"></b>
					</a>	
					<b class="arrow"></b>
					<ul class="submenu">
						<li class="">
							<a target="mainFrame" href="${pageContext.request.contextPath}/view/inventory/inList.jsp">
								<i class="menu-icon fa fa-caret-right"></i>
								商品入库
							</a>
							<b class="arrow"></b>
						</li>
						<li class="">
							<a  target="mainFrame" href="${pageContext.request.contextPath}/view/inventory/outList.jsp">
								<i class="menu-icon fa fa-caret-right"></i>
								商品出库
							</a>
							<b class="arrow"></b>
						</li>
						<li class="">
							<a target="mainFrame" href="${pageContext.request.contextPath}/view/inventory/storageDebtList.jsp">
								<i class="menu-icon fa fa-caret-right"></i>
								商品报损
							</a>
							<b class="arrow"></b>
						</li>
					</ul>
				</li>
				<li class="">
					<a target="mainFrame" href="${pageContext.request.contextPath}/view/inventory/inventoryList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						库存查询
					</a>
					<b class="arrow"></b>
				</li>
			</ul>
		</li>

		
		
		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-cogs"></i>
				<span class="menu-text"> 销售管理 </span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			<b class="arrow"></b>

			<ul class="submenu">
				<li class="">
					<a target="mainFrame" href="${pageContext.request.contextPath}/view/sale/saleInfoList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						销售信息管理
					</a>
					<b class="arrow"></b>
				</li>
				<li class="">
					<a href="${pageContext.request.contextPath}/view/sale/saleGoodsList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						销售商品管理
					</a>
					<b class="arrow"></b>
				</li>
			</ul>
		</li>
		
		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-globe"></i>
				<span class="menu-text"> 区域管理 </span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			<b class="arrow"></b>

			<ul class="submenu">
				<li class="">
					<a href="${pageContext.request.contextPath}/view/region/serviceGarageList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						服务站管理
					</a>
					<b class="arrow"></b>
				</li>
				<li class="">
					<a href="${pageContext.request.contextPath}/view/region/serviceUserList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						服务人员管理
					</a>
					<b class="arrow"></b>
				</li>
				<li class="">
					<a href="${pageContext.request.contextPath}/view/region/serviceUserOpList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						人员操作管理
					</a>
					<b class="arrow"></b>
				</li>
				<li class="">
					<a href="${pageContext.request.contextPath}/view/region/serviceSellerList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						供应商管理
					</a>
					<b class="arrow"></b>
				</li>
				<li class="">
					<a href="${pageContext.request.contextPath}/view/region/serviceSellerList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						商圈商家管理
					</a>
					<b class="arrow"></b>
				</li>
				<li class="">
					<a href="${pageContext.request.contextPath}/view/region/serviceSaleList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						销售信息管理
					</a>
					<b class="arrow"></b>
				</li>
			</ul>
		</li>
		
		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-laptop"></i>
				<span class="menu-text"> 运营管理 </span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			<b class="arrow"></b>

			<ul class="submenu">
				<li class="">
					<a href="${pageContext.request.contextPath}/view/advert/adUserList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						制定进货计划
					</a>
					<b class="arrow"></b>
				</li>
				<li class="">
					<a href="${pageContext.request.contextPath}/view/advert/adInfoList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						制定入库计划
					</a>
					<b class="arrow"></b>
				</li>
				<li class="">
					<a href="${pageContext.request.contextPath}/view/advert/adPositionList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						制定出库计划
					</a>
					<b class="arrow"></b>
				</li>
				<li class="">
					<a href="${pageContext.request.contextPath}/view/advert/adFeeList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						制定商品投放计划
					</a>
					<b class="arrow"></b>
				</li>
			</ul>
		</li>
		
		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-bullhorn"></i>
				<span class="menu-text"> 广告管理 </span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			<b class="arrow"></b>

			<ul class="submenu">
				<li class="">
					<a target="mainFrame" href="${pageContext.request.contextPath}/view/advert/adUserList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						广告主管理
					</a>
					<b class="arrow"></b>
				</li>
				<li class="">
					<a target="mainFrame" href="${pageContext.request.contextPath}/view/advert/adInfoList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						广告内容管理
					</a>
					<b class="arrow"></b>
				</li>
				<li class="">
					<a target="mainFrame" href="${pageContext.request.contextPath}/view/advert/adPositionList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						广告投放管理
					</a>
					<b class="arrow"></b>
				</li>
				<li class="">
					<a target="mainFrame" href="${pageContext.request.contextPath}/view/advert/adFeeList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						广告费用管理
					</a>
					<b class="arrow"></b>
				</li>
			</ul>
		</li>
		
		<li class="">
			<a href="#" class="dropdown-toggle">
				<i class="menu-icon fa fa-bar-chart-o"></i>
				<span class="menu-text"> 统计分析 </span>
				<b class="arrow fa fa-angle-down"></b>
			</a>
			<b class="arrow"></b>

			<ul class="submenu">
				<li class="">
					<a href="${pageContext.request.contextPath}/view/report/inventoryReport.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						商品出入库统计
					</a>
					<b class="arrow"></b>
				</li>
				<li class="">
					<a href="${pageContext.request.contextPath}/view/report/goodsReport.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						商品销售统计
					</a>
					<b class="arrow"></b>
				</li>
				<li class="">
					<a href="${pageContext.request.contextPath}/view/report/deviceReport.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						设备维护统计
					</a>
					<b class="arrow"></b>
				</li>
				<li class="">
					<a href="${pageContext.request.contextPath}/view/report/areaReport.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						区域业务分析
					</a>
					<b class="arrow"></b>
				</li>
				<li class="">
					<a href="${pageContext.request.contextPath}/view/report/advertReport.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						广告统计分析
					</a>
					<b class="arrow"></b>
				</li>
			</ul>
		</li>
		
		<li class="">
			<a href="gallery.html" class="dropdown-toggle">
				<i class="menu-icon fa fa-file-o"></i>
				<span class="menu-text"> 系统管理 </span>
				<b class="arrow fa fa-angle-down"></b>
			</a>

			<b class="arrow"></b>
			<ul class="submenu">
				<li class="">
					<a href="${pageContext.request.contextPath}/view/sys/menuList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						菜单管理						
					</a>
					<b class="arrow"></b>					
				</li>

				<li class="">
					<a href="#" class="dropdown-toggle">
						<i class="menu-icon fa fa-caret-right"></i>
						组织机构管理
						<b class="arrow fa fa-angle-down"></b>
					</a>
					<b class="arrow"></b>
					<ul class="submenu">
						<li class="">
							<a href="${pageContext.request.contextPath}/view/sys/orgList.jsp">
								<i class="menu-icon fa fa-caret-right"></i>
								机构管理
							</a>
							<b class="arrow"></b>
						</li>
						<li class="">
							<a href="${pageContext.request.contextPath}/view/sys/userList.jsp">
								<i class="menu-icon fa fa-caret-right"></i>
								人员管理
							</a>
							<b class="arrow"></b>
						</li>
					</ul>
				</li>
				<li class="">
					<a href="${pageContext.request.contextPath}/view/sys/memberList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						会员管理						
					</a>
					<b class="arrow"></b>					
				</li>
				<li class="">
					<a href="#" class="dropdown-toggle">
						<i class="menu-icon fa fa-caret-right"></i>
						权限管理
						<b class="arrow fa fa-angle-down"></b>
					</a>
					<b class="arrow"></b>
					<ul class="submenu">
						<li class="">
							<a href="${pageContext.request.contextPath}/view/sys/groupList.jsp">
								<i class="menu-icon fa fa-caret-right"></i>
								分组管理
							</a>
							<b class="arrow"></b>
						</li>
						<li class="">
							<a href="${pageContext.request.contextPath}/view/sys/roleList.jsp">
								<i class="menu-icon fa fa-caret-right"></i>
								角色管理
							</a>
							<b class="arrow"></b>
						</li>
					</ul>
				</li>

				<li class="">
					<a href="${pageContext.request.contextPath}/view/sys/dictList.jsp">
						<i class="menu-icon fa fa-caret-right"></i>
						数据字典管理
					</a>
					<b class="arrow"></b>
				</li>
			--></ul>
		</li>
	
	</ul><!-- /.nav-list 

	<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
		<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
	</div>-->
	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
	</script>
	
