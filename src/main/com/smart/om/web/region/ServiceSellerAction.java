package com.smart.om.web.region;

import com.smart.om.biz.car.CarHandler;
import com.smart.om.biz.region.RegionHandler;
import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.persist.CarLineNode;
import com.smart.om.persist.MotionDistrict;
import com.smart.om.persist.SysDict;
import com.smart.om.persist.SysUser;
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

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商圈管理Action
 * @author lc
 *
 */
@Namespace("/view/region/serviceInfo")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class ServiceSellerAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(ServiceSellerAction.class);
	@Resource
	private RegionHandler serviceSellerHandler;
	@Resource
    private SysAssistHandler sysAssistHandler;
	@Resource
	private CarHandler carHandler;
	
	private MotionDistrict motionDistrict;
	
	public MotionDistrict getMotionDistrict() {
		return motionDistrict;
	}

	public void setMotionDistrict(MotionDistrict motionDistrict) {
		this.motionDistrict = motionDistrict;
	}

	/**
	 * 查询所有商家商圈
	 */
	@Action(value = "queryService")
	public void queryServiceSeller() {
		SysUser sysUser = (SysUser) this.getSession().get("account");
		SysDict sysDict = sysAssistHandler.queryDictByPcode("AL_POSITION");
		try{
			//搜索关键字
			String keyword = this.getRequestParm().getParameter("keyword");
			String nodeId = this.getRequestParm().getParameter("id");
	        String nodePid = this.getRequestParm().getParameter("pid");
	        String level = this.getRequestParm().getParameter("level");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			params.put("orgId", sysUser.getOrgId());//用户所属服务站
			if (StringUtils.isNotBlank(nodePid)) {
            	if(sysDict != null){
                	if(Const.IS_REGION.equals(level)){//点击区域
                		params.put("dictRegionId", nodeId);
                	}else if(Const.IS_PROVICE.equals(level)){//点击省份
                		params.put("dictProviceId", nodeId);
                	}else if(Const.IS_ORG.equals(level)) {//点击服务站
                		params.put("dictOrgId", nodeId);
                	}
                }
            }
			DTablePageModel dtPageModel = serviceSellerHandler.queryServiceSeller(params, super.getPageData());
			String jsonData = JSONUtil.serialize(dtPageModel);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 进入添加或编辑商家商圈页面
	 */
	@Action(value = "addOrModifyServiceSeller", results = {
			@Result(name = SUCCESS, location = "/view/region/serviceSellerAdd.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String addOrModifyServiceSeller() {
		String result = SUCCESS;
		try{
			String districtId = this.getRequestParm().getParameter("districtId");
			if(StringUtils.isNotBlank(districtId)){
				Integer dId =Integer.valueOf(districtId);
				MotionDistrict mDistrict = serviceSellerHandler.queryServiceSellerById(dId);
				getRequest().put("motionDistrict", mDistrict);
			}
		}catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}
	
	/**
	 * 添加或修改商家商圈信息
	 */
	@Action(value = "saveOrUpdateServiceSeller", results = {
			@Result(name = SUCCESS, location = "/view/region/serviceSellerList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String saveOrUpdateServiceSeller(){
		String result = SUCCESS;
		try{
			Integer districtId =  motionDistrict.getDistrictId();
			CarLineNode carLineNode = new CarLineNode();
			if(districtId == null ){
				motionDistrict.setNewer(true);
				motionDistrict.setDistrictNo(DateUtil.getNo("MD"));
			}
			motionDistrict.setIsDel(Const.IS_DEL_FALSE);
			MotionDistrict mdt = serviceSellerHandler.saveOrUpdateServiceSeller(motionDistrict);
			
			if(districtId == null){
				carLineNode.setNewer(true);
				carLineNode.setNodeNo(DateUtil.getNo("X"));
				carLineNode.setIsDel(Const.IS_DEL_FALSE);
				carLineNode.setNodePositionId(mdt.getDistrictId());
				carLineNode.setNodePositionName(mdt.getDistrictName());
				carLineNode.setNodeLat(mdt.getDistrictLat());
				carLineNode.setNodeLng(mdt.getDistrictLng());
				carHandler.saveOrUpdateCarlineNode(carLineNode);
			}
		}catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}
	/**
	 * 删除设备信息
	 */
	@Action(value = "delServiceSellerById", results = {
			@Result(name = SUCCESS, location = "/view/region/serviceSellerList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String delServiceSellerById(){
		String result = SUCCESS;
		try{
			Integer districtId =Integer.valueOf(this.getRequestParm().getParameter("districtId"));
			MotionDistrict mDistrict = serviceSellerHandler.queryServiceSellerById(districtId);
			mDistrict.setIsDel(Const.IS_DEL_TRUE);
			mDistrict.setNewer(false);
			serviceSellerHandler.saveOrUpdateServiceSeller(mDistrict);
		}catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}
	
	/**
	 * 根据设备ID进入设备信息页面
	 */
	@Action(value = "serviceSellerDtl", results = {
			@Result(name = SUCCESS, location = "/view/region/serviceSellerDtl.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String serviceSellerDtl() {
		String result = SUCCESS;
		try{
			Integer districtId =Integer.valueOf(this.getRequestParm().getParameter("districtId"));
			MotionDistrict mDistrict = serviceSellerHandler.queryServiceSellerById(districtId);
			getRequest().put("motionDistrict", mDistrict);
		}catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}
	
	/**
	 * 根据商圈ID进入地图页面
	 */
	@Action(value = "districtMap", results = {
			@Result(name = SUCCESS, location = "/view/region/serviceSellerMap.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String districtMap() {
		String result = SUCCESS;
		SysUser sysUser = (SysUser) this.getSession().get("account");
		try{
			String districtId = this.getRequestParm().getParameter("districtId");
			Integer id =Integer.valueOf(districtId);
			MotionDistrict dInfo = serviceSellerHandler.queryServiceSellerById(id);
			String jsonData = JSONUtil.serialize(dInfo);
			getRequest().put("jsonData", jsonData);
			
			List<MotionDistrict> dList;// = (List<MotionDistrict>) serviceSellerHandler.queryAllServiceSeller();
			
			if(sysUser.getOrgId() != null ){
				dList = serviceSellerHandler.queryAllMDForMap(sysUser.getOrgId());
			}else{
				dList = serviceSellerHandler.queryAllServiceSeller();
			}
			String jsonList = JSONUtil.serialize(dList);
			getRequest().put("jsonList", jsonList);
		}catch (Exception e) {
			e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}
	
	/**
	 * 查询所有商圈
	 */
	@Action(value = "allDistrictMap", results = {
			@Result(name = SUCCESS, location = "/view/region/allServiceSellerMap.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String allDistrictMap() {
		String result = SUCCESS;
		SysUser sysUser = (SysUser) this.getSession().get("account");
		List<MotionDistrict> dList;
		try{
			if(sysUser.getOrgId() != null ){
				dList = serviceSellerHandler.queryAllMDForMap(sysUser.getOrgId());
			}else{
				dList = serviceSellerHandler.queryAllServiceSeller();
			}
			String jsonList = JSONUtil.serialize(dList);
			getRequest().put("jsonList", jsonList);
		}catch (Exception e) {
			e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}
	
	
}
