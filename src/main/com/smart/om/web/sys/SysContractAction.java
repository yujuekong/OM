package com.smart.om.web.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import com.smart.om.biz.advert.AdvertHandler;
import com.smart.om.biz.region.RegionHandler;
import com.smart.om.biz.seller.SellerHandler;
import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.AdvertInfo;
import com.smart.om.persist.MotionDistrict;
import com.smart.om.persist.SellerInfo;
import com.smart.om.persist.SysContract;
import com.smart.om.persist.SysDict;
import com.smart.om.persist.SysUser;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.web.base.BaseAction;

/**
 * 系统用户管理Action
 * @author ienovo
 *
 */
@Namespace("/view/sys/contract")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class SysContractAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(SysContractAction.class);
	@Resource
	private SysFuncHandler sysFuncHandler;
	@Resource
	private AdvertHandler advertHandler;//广告
	@Resource
	private RegionHandler serviceSellerHandler;//商圈
	@Resource
	private SellerHandler sellerHandler;//供应商
	@Resource
    private SysAssistHandler sysAssistHandler;
	
	private SysContract sysContract;//合同
	
	/**
	 * 查询服务合同信息
	 */
	@Action(value="queryContract",results = { @Result(name = SUCCESS, location = "/view/sys/contractDetail.jsp" ),
			   @Result(name = ERROR, location = "/view/error.jsp") })
	public String queryContract() {
		try {
			String contractId = this.getRequestParm().getParameter("contractId");
			if(StringUtils.isNotBlank(contractId)){
				Integer contId = Integer.valueOf(contractId);
				SysContract sysContract = sysFuncHandler.queryContractById(contId);
				this.getRequest().put("sysContract",sysContract);
				if("0".equals(sysContract.getContractType())){
					SellerInfo sellerInfo = (SellerInfo) sellerHandler.querySellerInfoById(sysContract.getContractSeller());
					String sellerName = sellerInfo.getSellerName();
					this.getRequest().put("sellerName", sellerName);
				}
				else if("1".equals(sysContract.getContractType())){
					MotionDistrict motionDistrict = serviceSellerHandler.queryServiceSellerById(sysContract.getContractSeller());
					String sellerName = motionDistrict.getDistrictName();
					this.getRequest().put("sellerName", sellerName);
				}
				else if("2".equals(sysContract.getContractType())){
					AdvertInfo advertInfo = (AdvertInfo) advertHandler.queryAdvertInfoById(sysContract.getContractSeller());
					String sellerName = advertInfo.getAdvertTitle();
					this.getRequest().put("sellerName", sellerName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", " 查询合同");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 根据合同名称查询合同
	 */
	@Action(value="queryContractDt",results={
			@Result(name=SUCCESS,location="/view/sys/contractView.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String queryContractDt(){
		try{
			
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统正忙，请联系管理员！");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 分页查询合同信息
	 */
	@Action(value = "queryContractPage")
	public void queryContractPage() {
		SysDict sysDict = sysAssistHandler.queryDictByPcode("AL_POSITION");
		try {
			// 搜索关键字
			String keyword = this.getRequestParm().getParameter("keyword");//关键字查找
			String contractType = this.getRequestParm().getParameter("contractType");//合同类型
			String contractStatus = this.getRequestParm().getParameter("contractStatus");//合同状态
			String orgId = this.getRequestParm().getParameter("orgId");
            String proviceId = this.getRequestParm().getParameter("proviceId");
            String regionId = this.getRequestParm().getParameter("regionId");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			params.put("contractType", contractType);
			params.put("contractStatus", contractStatus);
			params.put("dictOrgId", orgId);
			params.put("dictProviceId", proviceId);
			params.put("dictRegionId", regionId);
			/*if (StringUtils.isNotBlank(nodePid)) {
            	if(sysDict != null){
                	if(Const.IS_REGION.equals(level)){//点击区域
                		params.put("dictRegionId", nodeId);
                	}else if(Const.IS_PROVICE.equals(level)){//点击省份
                		params.put("dictProviceId", nodeId);
                	}else if(Const.IS_ORG.equals(level)) {//点击服务站
                		params.put("dictOrgId", nodeId);
                	}
                }
            } */
			DTablePageModel dtPageModel = sysFuncHandler.queryContractPage(params, super.getPageData());
			String jsonData = JSONUtil.serialize(dtPageModel);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 进入添加用户
	 * @return
	 */
	@Action(value="preAddOrModifyContract",results = {
			@Result(name = SUCCESS, location = "/view/sys/contractInfo.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") 
	})
	public String preAddOrModifyContract() {
		try {
			String contractId = this.getRequestParm().getParameter("contractId");
			if(StringUtils.isNotBlank(contractId)){
				SysContract sysContract = sysFuncHandler.queryContractById(Integer.valueOf(contractId));
				getRequest().put("sysContract", sysContract);
				if("0".equals(sysContract.getContractType())){
					SellerInfo sellerInfo = (SellerInfo) sellerHandler.querySellerInfoById(sysContract.getContractSeller());
					if(sellerInfo != null){
						String sellerName = sellerInfo.getSellerName();
						this.getRequest().put("sellerId", sellerInfo.getSellerId());
						this.getRequest().put("sellerName", sellerName);
					}
					else{
						this.getRequest().put("errors", "供应商不存在，请重试！");
					}
				}
				else if("1".equals(sysContract.getContractType())){
					MotionDistrict motionDistrict = serviceSellerHandler.queryServiceSellerById(sysContract.getContractSeller());
					if(motionDistrict != null){
						String sellerName = motionDistrict.getDistrictName();
						this.getRequest().put("sellerId", motionDistrict.getDistrictId());
						this.getRequest().put("sellerName", sellerName);
					}
					else{
						this.getRequest().put("errors", "商圈不存在，请重试！");
					}
				}
				else if("2".equals(sysContract.getContractType())){
					AdvertInfo advertInfo = (AdvertInfo) advertHandler.queryAdvertInfoById(sysContract.getContractSeller());
					if(advertInfo != null){
						String sellerName = advertInfo.getAdvertTitle();
						this.getRequest().put("sellerId", advertInfo.getAdvertInfoId());
						this.getRequest().put("sellerName", sellerName);
					}
					else{
						this.getRequest().put("errors", "广告不存在，请重试！");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", " 进入添加合同");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");	
		}
		return SUCCESS;
	}
	
	/**
	 * 添加合同
	 * @return
	 */
	@Action(value="saveOrUpdateContract",results = { @Result(name = SUCCESS, type = "redirect", location = "/view/sys/contractList.jsp" ),
			   @Result(name = ERROR, location = "/view/error.jsp") })
	public String saveOrUpdateContract() {
		try {
			if (sysContract.getContractId() == null) {//新增
				sysContract.setNewer(true);
				sysContract.setCreateDate(DateUtil.getDateY_M_D());
				sysContract.setIsDel(Const.IS_DEL_FALSE);
				sysContract.setContractStatus(Const.IS_STATUS_INIT);
				if(Const.CONTRACT_SELLER.equals(sysContract.getContractType())){//供应商合同
					sysContract.setContractNo(DateUtil.getNo("HT_00"));
				}
				else if(Const.CONTRACT_DISTRICT.equals(sysContract.getContractType())){//商圈合同
					sysContract.setContractNo(DateUtil.getNo("HT_01"));
				}
				else if(Const.CONTRACT_ADVERT.equals(sysContract.getContractType())){
					sysContract.setContractNo(DateUtil.getNo("HT_02"));
				}
				SysUser sysUser = (SysUser) this.getHttpSession().getAttribute("account");//获取session中的用户信息
				if(sysUser.getOrgId() != null){
					sysContract.setOrgId(sysUser.getOrgId());
				}
				if(sysUser.getDictProviceId() != null){
					sysContract.setDictProviceId(sysUser.getDictProviceId());
				}
				if(sysUser.getDictRegionId() != null){
					sysContract.setDictRegionId(sysUser.getDictRegionId());
				}
			}
			else{//修改
				SysContract contract = sysFuncHandler.queryContractById(sysContract.getContractId());
				sysContract.setNewer(false);
				sysContract.setCreateDate(contract.getCreateDate());
				sysContract.setContractNo(contract.getContractNo());
				sysContract.setIsDel(contract.getIsDel());
				sysContract.setContractStatus(contract.getContractStatus());
			}
			sysFuncHandler.saveOrUpdateContract(sysContract);
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("where", " 添加合同");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");	
		}
		return SUCCESS;
	}
	
	//删除合同
	@Action(value="deleteContract",results={
			@Result(name=SUCCESS,location="/view/sys/contractList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String deleteContract(){
		try {
			String contractId = this.getRequestParm().getParameter("contractId");
			SysContract contract = sysFuncHandler.queryContractById(Integer.valueOf(contractId));
			contract.setIsDel(Const.IS_DEL_TRUE);
			sysFuncHandler.saveOrUpdateContract(contract);
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试！");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改支付状态
	 * @return
	 * @throws IOException
	 */
	@Action(value="changePayStatus",results={
			@Result(name=SUCCESS,location="/view/sys/contractList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String changePayStatus(){
		try {
			String contractId = this.getRequestParm().getParameter("contractId");
			if(StringUtils.isNotBlank(contractId)){
				SysContract contract = sysFuncHandler.queryContractById(Integer.valueOf(contractId));
				if(Const.IS_VALID_TRUE.equals(contract.getIsPay())){
					contract.setIsPay(Const.IS_VALID_FALSE);
				}
				else{
					contract.setIsPay(Const.IS_VALID_TRUE);
				}
				sysFuncHandler.saveOrUpdateContract(contract);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试！");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改合同状态
	 * @return
	 * @throws IOException
	 */
	@Action(value="changeContractStatus",results={
			@Result(name=SUCCESS,location="/view/sys/contractList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String changeContractStatus(){
		try {
			String contractId = this.getRequestParm().getParameter("contractId");
			if(StringUtils.isNotBlank(contractId)){
				SysContract contract = sysFuncHandler.queryContractById(Integer.valueOf(contractId));
				if(Const.IS_VALID_TRUE.equals(contract.getContractStatus())){
					contract.setContractStatus(Const.IS_VALID_FALSE);
				}
				else{
					contract.setContractStatus(Const.IS_VALID_TRUE);
				}
				sysFuncHandler.saveOrUpdateContract(contract);
			}
		}catch(Exception e) {
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试！");
			return ERROR;
		}
		return SUCCESS;
	}
	
	public SysContract getSysContract() {
		return sysContract;
	}
	public void setSysContract(SysContract sysContract) {
		this.sysContract = sysContract;
	}
}
