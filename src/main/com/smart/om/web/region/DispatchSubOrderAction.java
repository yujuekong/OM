package com.smart.om.web.region;

import com.smart.om.biz.inventory.InventoryHandler;
import com.smart.om.biz.region.RegionHandler;
import com.smart.om.dao.goods.GoodsInfoDAO;
import com.smart.om.persist.*;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.web.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配送管理Action
 * @author lc
 *
 */
@Transactional
@Namespace("/view/region/dispatchSubOrder")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class DispatchSubOrderAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(DispatchSubOrderAction.class);
	@Resource
	private RegionHandler regionHandler;
	@Resource
    private InventoryHandler inventoryHandler;
	@Autowired
	private GoodsInfoDAO goodsInfoDAO;
	private DispatchingSubOrder dispatchingSubOrder;
	private List<DispatchingSubOrder> subOrderList;

	public List<DispatchingSubOrder> getSubOrderList() {
		return subOrderList;
	}

	public void setSubOrderList(List<DispatchingSubOrder> subOrderList) {
		this.subOrderList = subOrderList;
	}

	public DispatchingSubOrder getDispatchingSubOrder() {
		return dispatchingSubOrder;
	}

	public void setDispatchingSubOrder(DispatchingSubOrder dispatchingSubOrder) {
		this.dispatchingSubOrder = dispatchingSubOrder;
	}

	/**
	 * 查询所有子订单
	 */
	@Action(value = "queryDSubOrder")
	public void queryDSubOrder() {
		SysUser sysUser = (SysUser) this.getSession().get("account");
		try{
			//搜索关键字
			String keyword = this.getRequestParm().getParameter("keyword");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			params.put("orgId", sysUser.getOrgId());//用户所属服务站
			DTablePageModel dtPageModel = regionHandler.queryDSubOrder(params, super.getPageDataForSubOrder());
			String jsonData = JSONUtil.serialize(dtPageModel);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改子订单状态
	 * @return
	 * @throws IOException
	 */
	@Action(value="changeStatus", results = {
			@Result(name = SUCCESS, location = "/view/region/dispatchSubOrderList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String changeStatus() throws IOException {
		String result = SUCCESS;
		try {
			String subOrderId = this.getRequestParm().getParameter("subOrderId");
			String isDisable = this.getRequestParm().getParameter("isDisable");
			DispatchingSubOrder dSubOrder = regionHandler.queryDSubOrderById(Integer.valueOf(subOrderId));
			dSubOrder.setOrderAllocation(isDisable);
			dSubOrder.setNewer(false);
			regionHandler.saveOrUpdateDSubOrder(dSubOrder);
			this.getResponse().getWriter().print(true);
		} catch (Exception e) {
			e.printStackTrace();
			this.getResponse().getWriter().print(false);
			result = ERROR;
		}
		return result;
	}

	/**
	 * 进入添加子订单页面
	 */
	@Action(value = "addDSubOrder", results = {
			@Result(name = SUCCESS, location = "/view/region/dispatchSubOrderAdd.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String addDSubOrder() {
		String result = SUCCESS;
		return result;
	}

	/**
	 * 进入编辑子订单页面
	 */
	@Action(value = "modifyDSubOrder", results = {
			@Result(name = SUCCESS, location = "/view/region/dispatchSubOrderModify.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String modifyDSubOrder() {
		String result = SUCCESS;
		try{
			String subOrderId = this.getRequestParm().getParameter("subOrderId");
			if(StringUtils.isNotBlank(subOrderId)){
				Integer dId =Integer.valueOf(subOrderId);
				DispatchingSubOrder dSubOrder = regionHandler.queryDSubOrderById(dId);
				getRequest().put("dSubOrder", dSubOrder);
			}
		}catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}

	/**
	 * 添加或修改子订单信息
	 */
	@Action(value = "saveDSubOrder", results = {
			@Result(name = SUCCESS, location = "/view/region/dispatchSubOrderList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String saveDSubOrder(){
		String result = SUCCESS;
		try{
			Integer subOrderId =  dispatchingSubOrder.getSubOrderId();
			if(subOrderId == null ){
				if(subOrderList.size() > 0){
					for (DispatchingSubOrder model : subOrderList) {
						if(model != null){
							if(model.getGoodsNumber() != null){
								model.getGoodsId();
								model.getGoodsName();
								model.getGoodsNumber();
								model.setDeviceId(dispatchingSubOrder.getDeviceId());
								model.setDeviceNo(dispatchingSubOrder.getDeviceNo());
								model.setDistrictId(dispatchingSubOrder.getDistrictId());
								model.setDistrictName(dispatchingSubOrder.getDistrictName());
								model.setNewer(true);
								model.setDispatchingTime(DateUtil.getDateY_M_DH_M_S());
								model.setSubOrderStatus(Const.IS_VALID_TRUE);
								model.setOrderAllocation(Const.IS_VALID_TRUE);
								model.setDictOrgId(dispatchingSubOrder.getDictOrgId());
								model.setDictProviceId(dispatchingSubOrder.getDictProviceId());
								model.setDictRegionId(dispatchingSubOrder.getDictRegionId());
								regionHandler.saveOrUpdateDSubOrder(model);
							}

						}
					}
				}
			}
			//regionHandler.saveOrUpdateDSubOrder(dispatchingSubOrder);
		}catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}

	/**
	 * 修改子订单
	 */
	@Action(value = "updateDSubOrder", results = {
			@Result(name = SUCCESS, location = "/view/region/dispatchSubOrderList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String updateDSubOrder(){
		String result = SUCCESS;
		try{
			Integer subOrderId =  dispatchingSubOrder.getSubOrderId();
			dispatchingSubOrder.setNewer(false);
			dispatchingSubOrder.setOrderAllocation(Const.IS_DEL_FALSE);
			regionHandler.saveOrUpdateDSubOrder(dispatchingSubOrder);
		}catch (Exception e) {
			e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}

	/**
	 * 删除子订单
	 */
	@Action(value = "delDSubOrderById", results = {
			@Result(name = SUCCESS, location = "/view/region/dispatchSubOrderList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String delDSubOrderById(){
		String result = SUCCESS;
		try{
			Integer subOrderId =Integer.valueOf(this.getRequestParm().getParameter("subOrderId"));
			regionHandler.delDSubOrderById(subOrderId);
		}catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}

	/**
	 * 根据子订单ID进入子订单页面
	 */
	@Action(value = "dispatchSubOrderDtl", results = {
			@Result(name = SUCCESS, location = "/view/region/dispatchSubOrderDtl.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String dispatchSubOrderDtl() {
		String result = SUCCESS;
		try{
			Integer subOrderId =Integer.valueOf(this.getRequestParm().getParameter("subOrderId"));
			DispatchingSubOrder subOrder = regionHandler.queryDSubOrderById(subOrderId);
			getRequest().put("dSubOrder", subOrder);
		}catch (Exception e) {
			e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}

	/**
	 * 合并子订单
	 */
	@Action(value = "combineDSubOrder")
	public void combineDSubOrder(){
		SysUser sysUser = (SysUser) this.getSession().get("account");
		String result = SUCCESS;
		try{
			//根据刀片名称查询刀片ID
			GoodsInfo goodsInfo = goodsInfoDAO.getGoodsInfoByName("刀片");
			String subOrderIds = this.getRequestParm().getParameter("idsList");

			String[] subOIdStr = subOrderIds.split(",");
			for (int j = 0; j < subOIdStr.length; j++) {
				int subOId =Integer.parseInt(subOIdStr[j]);
				List subOIdList = regionHandler.queryTeamBySubOrderId(subOId);
				if(subOIdList == null ){
					result="teamException";
					String jsonData = JSONUtil.serialize(result);
					PrintWriter pw = super.getResponse().getWriter();
					pw.write(jsonData);
					pw.flush();
					return;
				}
			}

			if(subOrderIds!=null && !"".equals(subOrderIds)){
				//根据子订单ID查询订单属于那条路线
				List dispatchingTeamIdList = regionHandler.dispatchingTeamIdList(subOrderIds);
				SysUser user = (SysUser) this.getHttpSession().getAttribute("account");
				List<StorageWarehouse> list = inventoryHandler.queryStorageWarehouseById(user.getOrgId());
				StorageWarehouse  storageWarehouse = null;
				StorageDeliveryOrder sOrder = null;
				if(list != null ){
					  storageWarehouse = list.get(0);
					  	//生成出库单
						StorageDeliveryOrder storageDeliveryOrder = new StorageDeliveryOrder();
						storageDeliveryOrder.setWarehouseId(storageWarehouse.getWarehouseId());
						storageDeliveryOrder.setDeliveryNo(DateUtil.getNo("C"));
						storageDeliveryOrder.setBizType(Const.OUT_BIZ_TYPE_DISPATCHING);
						storageDeliveryOrder.setUserId(user.getUserId());
						storageDeliveryOrder.setCreateDate(DateUtil.getDateY_M_DH_M_S());
						storageDeliveryOrder.setDeliveryStatus("0");
						storageDeliveryOrder.setRequestDate(DateUtil.getDateY_M_DH_M_S());
						storageDeliveryOrder.setDeliveryHandover("0");
						storageDeliveryOrder.setAgentHandover("0");
						storageDeliveryOrder.setCleanStatus("0");
						storageDeliveryOrder.setNewer(true);

						//新增出库单反正对象
						sOrder =(StorageDeliveryOrder)inventoryHandler.saveOrUpdateDeliveryOrder(storageDeliveryOrder);

						List<Object[]> dispatchingSubOrderList = regionHandler.queryDSubOrderByIdS(subOrderIds);

						if(dispatchingSubOrderList != null){
							for (int i = 0; i < dispatchingSubOrderList.size(); i++) {
								Object[] obj  = dispatchingSubOrderList.get(i);
								StorageDeliveryDtl  StorageDeliveryDtl = new StorageDeliveryDtl();//创建子订单对象
								StorageDeliveryDtl.setGoodsId(Integer.parseInt(obj[0].toString()));
								if(Integer.parseInt(obj[0].toString()) == goodsInfo.getGoodsId()){
									StorageDeliveryDtl.setRequestCount(Double.parseDouble(obj[1].toString())+Const.BLADE_NUMBER);
								}else{
									StorageDeliveryDtl.setRequestCount(Double.parseDouble(obj[1].toString()));
								}

								StorageDeliveryDtl.setDeliveryOrderId(sOrder.getDeliveryOrderId());
								StorageDeliveryDtl.setNewer(true);
								inventoryHandler.saveOrUpdateDebtDtl(StorageDeliveryDtl);
							}
						}else{
							sOrder.setDeliveryHandover("1");
							sOrder.setDeliveryStatus("1");
							sOrder.setDeliveryDate(DateUtil.getDateY_M_DH_M_S());
							storageDeliveryOrder.setCleanTime(DateUtil.getDateY_M_DH_M_S());
							inventoryHandler.saveOrUpdateDeliveryOrder(sOrder);
						}
						if(dispatchingTeamIdList!=null){
							for (int i = 0; i < dispatchingTeamIdList.size(); i++) {
								Integer num = (Integer)dispatchingTeamIdList.get(i);
								//返回线路ID只有一条时
								if(dispatchingTeamIdList.size()==1){
									//生成订单
									DispatchingOrder dispatchingOrder = new DispatchingOrder();
									dispatchingOrder.setOrderNo(DateUtil.getNo("D"));
									dispatchingOrder.setWarehouseId(storageWarehouse.getWarehouseId());
									dispatchingOrder.setOrderTime(DateUtil.getDateY_M_DH_M_S());
									dispatchingOrder.setCreateUser(user.getUserId());
									dispatchingOrder.setOrderStatus("0");
									dispatchingOrder.setOrderEndTime(DateUtil.getDateY_M_DH_M_S());
									dispatchingOrder.setTeamId(num);
									dispatchingOrder.setDeliveryOrderId(sOrder.getDeliveryOrderId());
									dispatchingOrder.setOrderAllocation("0");
									dispatchingOrder.setDictOrgId(sysUser.getOrgId());
									dispatchingOrder.setDictProviceId(sysUser.getDictProviceId());
									dispatchingOrder.setDictRegionId(sysUser.getDictRegionId());
									dispatchingOrder.setNewer(true);
									//保存订单返回对象
									DispatchingOrder dOrder = regionHandler.saveOrUpdateDOrder(dispatchingOrder);
									if(dOrder!=null){
										String[] str = subOrderIds.split(",");
										for (int j = 0; j < str.length; j++) {
											int subOrderId =Integer.parseInt(str[j]);
											DispatchingSubOrder dispatchingSubOrder = regionHandler.queryDSubOrderById(subOrderId);
											if(dispatchingSubOrder.getGoodsId() == goodsInfo.getGoodsId()){
												dispatchingSubOrder.setSubOrderStatus("1");
											}else{
												dispatchingSubOrder.setSubOrderStatus("0");
											}
											dispatchingSubOrder.setSubOrderId(subOrderId);
											dispatchingSubOrder.setOrderId(dOrder.getOrderId());

											dispatchingSubOrder.setOrderAllocation("1");
											regionHandler.saveOrUpdateDSubOrder(dispatchingSubOrder);
										}

//										List deviceIdDispatchingSubOrderList = regionHandler.queryDeviceIdDSubOrderByIdS(subOrderIds);
//										if(deviceIdDispatchingSubOrderList !=null){
//											for (int j = 0; j < deviceIdDispatchingSubOrderList.size(); j++) {
//												Integer deviceId = Integer.parseInt(deviceIdDispatchingSubOrderList.get(j).toString());
//												DeviceOrder deviceOrder = new DeviceOrder();
//												deviceOrder.setDeviceId(deviceId);
//												deviceOrder.setOrderId(dOrder.getOrderId());
//												deviceOrder.setNewer(true);
//												regionHandler.saveOrUpdateDeviceOrder(deviceOrder);
//											}
//										}
									}
									break;
								}else{
									//生成订单
									DispatchingOrder dispatchingOrder = new DispatchingOrder();
									dispatchingOrder.setOrderNo(DateUtil.getNo("D"));
									dispatchingOrder.setWarehouseId(storageWarehouse.getWarehouseId());
									dispatchingOrder.setOrderTime(DateUtil.getDateY_M_DH_M_S());
									dispatchingOrder.setCreateUser(user.getUserId());
									dispatchingOrder.setOrderStatus("0");
									dispatchingOrder.setOrderEndTime(DateUtil.getDateY_M_DH_M_S());
									dispatchingOrder.setTeamId(num);
									dispatchingOrder.setOrderAllocation("0");
									dispatchingOrder.setDictOrgId(sysUser.getOrgId());
									dispatchingOrder.setDictProviceId(sysUser.getDictProviceId());
									dispatchingOrder.setDictRegionId(sysUser.getDictRegionId());
									dispatchingOrder.setDeliveryOrderId(sOrder.getDeliveryOrderId());
									dispatchingOrder.setNewer(true);
									//保存订单返回对象
									DispatchingOrder dOrder = regionHandler.saveOrUpdateDOrder(dispatchingOrder);
									if(dOrder!=null){
										String[] str = subOrderIds.split(",");
										for (int j = 0; j < str.length; j++) {
											Integer subOrderId =Integer.parseInt(str[j]);
											List dispatchingTeamId = regionHandler.dispatchingTeamIdList(subOrderId.toString());
											if(dispatchingTeamId != null){
												Integer num1 = (Integer)dispatchingTeamId.get(0);
												String teamId = num1.toString();
												String teamIds = num.toString();
												//判断当前子订单是不是当前路线的
												if(teamId.equals(teamIds)){
													DispatchingSubOrder dispatchingSubOrder = regionHandler.queryDSubOrderById(subOrderId);
													if(dispatchingSubOrder.getGoodsId() == goodsInfo.getGoodsId()){
														dispatchingSubOrder.setSubOrderStatus("1");
													}else{
														dispatchingSubOrder.setSubOrderStatus("0");
													}
													dispatchingSubOrder.setSubOrderId(subOrderId);
													dispatchingSubOrder.setOrderId(dOrder.getOrderId());
													dispatchingSubOrder.setOrderAllocation("1");
													regionHandler.saveOrUpdateDSubOrder(dispatchingSubOrder);
												}
											}else{
												result = "teamException";
											}
										}
//										List deviceIdDispatchingSubOrderList = regionHandler.queryDeviceIdDSubOrderByIdS(subOrderIds);
//										if(deviceIdDispatchingSubOrderList !=null){
//											for (int j = 0; j < deviceIdDispatchingSubOrderList.size(); j++) {
//												Integer deviceId = Integer.parseInt(deviceIdDispatchingSubOrderList.get(j).toString());
//												DeviceOrder deviceOrder = new DeviceOrder();
//												deviceOrder.setDeviceId(deviceId);
//												deviceOrder.setOrderId(dOrder.getOrderId());
//												deviceOrder.setNewer(true);
//												regionHandler.saveOrUpdateDeviceOrder(deviceOrder);
//											}
//										}
									}
								}
							}
						}else{
							 result = "teamException";
							 inventoryHandler.deleteDeliveryDtlByDeliveryId(sOrder.getDeliveryOrderId());
							 inventoryHandler.deleteDeliveryOrder(sOrder.getDeliveryOrderId());
						}
				}else{
					result = "storageWarehouseException";
				}
			}
			String jsonData = JSONUtil.serialize(result);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();

		}catch (Exception e) {
			e.printStackTrace();
        }

	}
}
