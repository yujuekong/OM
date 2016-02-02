package com.smart.om.web.region;

import java.io.PrintWriter;
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
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.dto.device.DeviceMaintainDto;
import com.smart.om.persist.DeviceMaintain;
import com.smart.om.persist.SysUser;
import com.smart.om.util.DTablePageModel;
import com.smart.om.web.base.BaseAction;

/**
 * 巡检管理Action
 * @author lc
 *
 */
@Namespace("/view/region/inspection")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class DeviceMaintainAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(DispatchOrderAction.class);
	@Resource
	private RegionHandler regionHandler;
	@Resource
	private SysFuncHandler sysFuncHandler;
	/**
	 * 查询所有巡检记录
	 */
	@Action(value = "queryInspectionPage")
	public void queryInspectionPage() {
		SysUser userOrg = (SysUser) this.getSession().get("account");
		try{
			//搜索关键字
			String keyword = this.getRequestParm().getParameter("teamKeyword");
			String inspectionStatus = this.getRequestParm().getParameter("inspectionStatus");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			if(StringUtils.isNotBlank(inspectionStatus)){
				params.put("inspectionStatus", inspectionStatus);
			}
			if(userOrg.getOrgId() != null){
				params.put("orgId", userOrg.getOrgId());
			}
			DTablePageModel dtPageModel = regionHandler.queryInspectionPage(params, super.getPageData());
			List list = dtPageModel.getAaData();
			for (int i = 0; i < list.size(); i++) {
				DeviceMaintainDto deviceMaintain = (DeviceMaintainDto)list.get(i);
				if(null != deviceMaintain.getMaintainUser() && deviceMaintain.getMaintainUser() > 0){
					Integer userId = deviceMaintain.getMaintainUser();
					SysUser sysUser = sysFuncHandler.querySysUserById(userId);
					deviceMaintain.setRealName(sysUser.getUserName());
				}
			}
			String jsonData = JSONUtil.serialize(dtPageModel);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
