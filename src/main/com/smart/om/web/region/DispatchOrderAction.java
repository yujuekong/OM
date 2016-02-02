package com.smart.om.web.region;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import com.smart.om.biz.region.RegionHandler;
import com.smart.om.persist.DispatchingOrder;
import com.smart.om.persist.DispatchingSubOrder;
import com.smart.om.persist.SysUser;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.web.base.BaseAction;

/**
 * 配送管理Action
 * @author lc
 *
 */
@Namespace("/view/region/dispatchOrder")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class DispatchOrderAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(DispatchOrderAction.class);
	@Resource
	private RegionHandler regionHandler;
	
	private DispatchingOrder dOrder;
	
	/**
	 * 查询所有订单
	 */
	@Action(value = "queryDOrder")
	public void queryDOrder() {
		SysUser sysUser = (SysUser) this.getSession().get("account");
		try{
			//搜索关键字
			String keyword = this.getRequestParm().getParameter("keyword");
			String startDate = this.getRequestParm().getParameter("startDate");
			String endDate = this.getRequestParm().getParameter("endDate");
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			params.put("orgId", sysUser.getOrgId());//用户所属服务站
			params.put("startDate", startDate);
			params.put("endDate", endDate);
			
			
			DTablePageModel dtPageModel = regionHandler.queryDOrder(params, super.getPageData());
			String jsonData = JSONUtil.serialize(dtPageModel);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	
	
	/**
	 * 修改订单状态
	 * @return
	 * @throws IOException
	 */
	@Action(value="changeStatus", results = {
			@Result(name = SUCCESS, location = "/view/region/dispatchOrderList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String changeStatus() throws IOException {
		String result = SUCCESS;
		try {
			String orderId = this.getRequestParm().getParameter("orderId");
			String isDisable = this.getRequestParm().getParameter("isDisable");
			DispatchingOrder dSubOrder = (DispatchingOrder)regionHandler.queryDOrderById(Integer.valueOf(orderId));
			dSubOrder.setOrderStatus(isDisable);
			dSubOrder.setNewer(false);
			regionHandler.saveOrUpdateDOrder(dSubOrder);
			this.getResponse().getWriter().print(true);
		} catch (Exception e) {
			e.printStackTrace();
			this.getResponse().getWriter().print(false);
			result = ERROR;
		}
		return result;
	}
	
	
	
	/**
	 * 进入添加或编辑子订单页面
	 */
	@Action(value = "addOrModifyDOrder", results = {
			@Result(name = SUCCESS, location = "/view/region/dispatchSubOrderAdd.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String addOrModifyDOrder() {
		String result = SUCCESS;
		try{
			String orderId = this.getRequestParm().getParameter("orderId");
			if(StringUtils.isNotBlank(orderId)){
				Integer dId =Integer.valueOf(orderId);
				DispatchingOrder dOrder = (DispatchingOrder) regionHandler.queryDOrderById(dId);
				getRequest().put("dOrder", dOrder);
			}
		}catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}
	
	/**
	 * 添加或修改订单信息
	 */
	@Action(value = "saveOrUpdateDOrder", results = {
			@Result(name = SUCCESS, location = "/view/region/dispatchOrderList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String saveOrUpdateDOrder(){
		String result = SUCCESS;
		try{
			Integer orderId =  dOrder.getOrderId();
			if(orderId == null ){
				dOrder.setNewer(true);
				//dSubOrder.setPlanNo(DateUtil.getNo("DP"));
				dOrder.setOrderTime(DateUtil.getDateY_M_DH_M_S());
				dOrder.setOrderStatus(Const.IS_VALID_TRUE);
			}
			DispatchingOrder order =  regionHandler.saveOrUpdateDOrder(dOrder);
			//Integer dPlanId = subOrder.getorderId();
			//List<DispatchingGoods> gList = new ArrayList<DispatchingGoods>();
		}catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}
	/**
	 * 删除订单
	 */
	@Action(value = "delDOrderById", results = {
			@Result(name = SUCCESS, location = "/view/region/dispatchOrderList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String delDOrderById(){
		String result = SUCCESS;
		try{
			Integer orderId =Integer.valueOf(this.getRequestParm().getParameter("orderId"));
			regionHandler.delDOrderById(orderId);
			regionHandler.delDSubOrderByDOrderIdId(orderId);
		}catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}
	
	/**
	 * 根据订单ID进入订单页面
	 */
	@Action(value = "dispatchOrderDtl", results = {
			@Result(name = SUCCESS, location = "/view/region/dispatchOrderDtl.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String dispatchOrderDtl() {
		String result = SUCCESS;
		try{
			Integer orderId =Integer.valueOf(this.getRequestParm().getParameter("orderId"));
			DispatchingOrder dOrder = (DispatchingOrder) regionHandler.queryDOrderById(orderId);
			getRequest().put("dOrder", dOrder);
			
			List<DispatchingSubOrder> DSOrderlist = (List<DispatchingSubOrder>) regionHandler.queryDSubOrderByOrderId(orderId);
			getRequest().put("DSOrderlist", DSOrderlist);
			
		}catch (Exception e) {
			e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}
//
//	/**
//	 * 查询某些配送线路下的所有设备
//	 * 
//	 * 实现方法:先查到该线路下有多少个站点,再根据站点获取站点所在的商圈,在设备列表根据商圈ID查询设备
//	 */
//	@Action(value = "queryDeviceByCarLine")
//	public void queryDeviceByCarLine() {
//		try {
//			String cId = this.getRequestParm().getParameter("carLineId");
//			Integer carLineId =Integer.valueOf(cId);
//			List<DeviceCarLineNodeDto> dInfoList = (List<DeviceCarLineNodeDto>) regionHandler.queryDeviceByCarLine(carLineId);
//			String jsonData = JSONUtil.serialize(dInfoList);
//			PrintWriter pw = super.getResponse().getWriter();
//			pw.write(jsonData);
//			pw.flush();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//			
//	}






	public DispatchingOrder getdOrder() {
		return dOrder;
	}

	public void setdOrder(DispatchingOrder dOrder) {
		this.dOrder = dOrder;
	}
}
