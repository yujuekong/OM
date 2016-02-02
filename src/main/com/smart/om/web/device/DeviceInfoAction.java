package com.smart.om.web.device;

import com.smart.om.biz.device.DeviceHandler;
import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.dto.device.DeviceMapDto;
import com.smart.om.persist.*;
import com.smart.om.rest.base.BaseController;
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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设备类型管理Action
 *
 * @author lc
 */
@Namespace("/view/device/deviceInfo")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class DeviceInfoAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(DeviceInfoAction.class);
    @Resource
    private DeviceHandler deviceHandler;
    @Resource
    private SysAssistHandler sysAssistHandler;
    @Resource
    private SysFuncHandler sysFuncHandler;
    private DeviceInfo deviceInfo;

    /**
     * 查询所有设备信息
     */
    @Action(value = "queryDeviceInfo")
    public void queryDeviceInfo() {
        try {
            SysUser sysUser = (SysUser) this.getSession().get("account");
            SysDict sysDict = sysAssistHandler.queryDictByPcode("AL_POSITION");
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String nodeId = this.getRequestParm().getParameter("id");
            String nodePid = this.getRequestParm().getParameter("pid");
            String level = this.getRequestParm().getParameter("level");
            String deviceStatus = this.getRequestParm().getParameter("deviceStatus");
            //设备存放商品管理时过滤已存放商品的设备
            String goodsFilter = this.getRequestParm().getParameter("goodsFilter");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            params.put("orgId", sysUser.getOrgId());//用户所属服务站
            if (StringUtils.isNotBlank(nodePid)) {
                if (sysDict != null) {
                    if (Const.IS_REGION.equals(level)) {//点击区域
                        params.put("dictRegionId", nodeId);
                    } else if (Const.IS_PROVICE.equals(level)) {//点击省份
                        params.put("dictProviceId", nodeId);
                    } else if (Const.IS_ORG.equals(level)) {//点击服务站
                        params.put("dictOrgId", nodeId);
                    }
                }
            }
            if (StringUtils.isNotBlank(deviceStatus)) {
                params.put("deviceStatus", deviceStatus);
            }
            if (StringUtils.isNotBlank(goodsFilter)) {
                params.put("goodsFilter", goodsFilter);
            }
            DTablePageModel dtPageModel = deviceHandler.queryDeviceInfo(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有设备信息
     */
    @Action(value = "choosePerson")
    public void choosePerson() {
        try {
            SysUser sysUser = (SysUser) this.getSession().get("account");
            SysDict sysDict = sysAssistHandler.queryDictByPcode("AL_POSITION");
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String nodeId = this.getRequestParm().getParameter("id");
            String nodePid = this.getRequestParm().getParameter("pid");
            String level = this.getRequestParm().getParameter("level");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            if(sysUser.getOrgId()!=null){
                params.put("orgId", sysUser.getOrgId());//用户所属服务站
            }
            if (StringUtils.isNotBlank(nodePid)) {
                if (sysDict != null) {
                    if (Const.IS_REGION.equals(level)) {//点击区域
                        params.put("dictRegionId", nodeId);
                    } else if (Const.IS_PROVICE.equals(level)) {//点击省份
                        params.put("dictProviceId", nodeId);
                    } else if (Const.IS_ORG.equals(level)) {//点击服务站
                        params.put("dictOrgId", nodeId);
                    }
                }
            }
            DTablePageModel dtPageModel = deviceHandler.choosePerson(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 进入添加或编辑设备信息页面
     */
    @Action(value = "addOrModifyDeviceInfo", results = {
            @Result(name = SUCCESS, location = "/view/device/deviceInfoAdd.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public String addOrModifyDeviceInfo() {
        String result = SUCCESS;
        try {
            String deviceId = this.getRequestParm().getParameter("deviceId");
            if (StringUtils.isNotBlank(deviceId)) {
                Integer dId = Integer.valueOf(deviceId);
                DeviceInfo dInfo = deviceHandler.queryDeviceById(dId);
                getRequest().put("deviceInfo", dInfo);
            }

        } catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 添加或修改设备信息
     */
    @Action(value = "saveOrUpdateDeviceInfo", results = {
            @Result(name = SUCCESS, location = "/view/device/deviceInfoList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public String saveOrUpdateDeviceInfo() {
        String result = SUCCESS;
        try {
            Integer deviceId = deviceInfo.getDeviceId();
            if (deviceId == null) {
                deviceInfo.setNewer(true);
                deviceInfo.setCreateDate(DateUtil.getDateY_M_D());
//				deviceInfo.setDeviceNo(DateUtil.getNo("X"));
                deviceInfo.setDeviceStatus(Const.DEVICE_STOP);
                deviceInfo.setBladeChangeTime(DateUtil.getDateY_M_D());
                deviceInfo.setCupCount(Const.CUP_MAX);
            }
            deviceInfo.setIsDel(Const.IS_DEL_FALSE);
            DeviceInfo dInfo = deviceHandler.saveOrUpdateDeviceInfoDAO(deviceInfo);
            if (deviceId == null) {
                if (!"".equals(deviceInfo.getIsPerson()) && null != deviceInfo.getIsPerson()) {
                    if (dInfo != null) {
                    	SysUser sysuser = sysFuncHandler.querySysUserById(Integer.valueOf(deviceInfo.getIsPerson()));
    					if(!"".equals(sysuser.getTel()) && null !=sysuser.getTel()){
    						BaseController baseController = new BaseController();
    						String accessKoken = baseController.getAccessToken();
    						StringBuffer param = new StringBuffer();
    						String url ="";
    						param.append("access_token=" + accessKoken);
    						String deviceNo = dInfo.getDeviceNo();
    						if(deviceId == null){
    							url ="PFPROWebAPI/api/JuicerApi/AddRemingSettingsInfo";
    							param.append("&machineCode="+deviceNo+"&phone="+sysuser.getTel());
    						}else{
    							url ="PFPROWebAPI/api/JuicerApi/UpdateRemingSettingsInfo";
    							param.append("&code="+deviceNo+"&phone="+sysuser.getTel());
    						}
    						String msg = baseController.extInf(url, param.toString(),null);
    					}
                    }
                }
            }
            if (deviceId == null) {
                //设备有13个格子
                for (int i = 0; i < 13; i++) {
                    DeviceGrid dGrid = new DeviceGrid();
                    dGrid.setDeviceId(dInfo.getDeviceId());
                    dGrid.setNewer(true);
                    dGrid.setGridNo(DateUtil.getNo("X" + i));
                    //dGrid.setGridBar(DateUtil.getNo("BAR"));
                    deviceHandler.saveDeviceGrid(dGrid);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 删除设备信息
     */
    @Action(value = "delDeviceById", results = {
            @Result(name = SUCCESS, location = "/view/device/deviceInfoList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public String delDeviceById() {
        String result = SUCCESS;
        String id = this.getRequestParm().getParameter("deviceId");
        try {
            if (StringUtils.isNotBlank(id)) {
                Integer deviceId = Integer.valueOf(id);
                DeviceInfo dInfo = deviceHandler.queryDeviceById(deviceId);
                dInfo.setIsDel(Const.IS_DEL_TRUE);
                dInfo.setNewer(false);
                deviceHandler.saveOrUpdateDeviceInfoDAO(dInfo);
            }
        } catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 根据设备ID进入设备信息页面
     */
    @Action(value = "queryDeviceById", results = {
            @Result(name = SUCCESS, location = "/view/device/deviceInfoDetail.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public String queryDeviceById() {
        String result = SUCCESS;
        try {
            String str = this.getRequestParm().getParameter("deviceId");
            Integer id = Integer.valueOf(str);
            DeviceInfo dInfo = deviceHandler.queryDeviceById(id);
            getRequest().put("deviceInfo", dInfo);
        } catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 根据设备ID进入地图页面
     */
    @Action(value = "deviceMap", results = {
            @Result(name = SUCCESS, location = "/view/device/deviceInfoMap.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public String deviceMap() {
        String result = SUCCESS;
        SysUser sysUser = (SysUser) this.getSession().get("account");
        try {
            String str = this.getRequestParm().getParameter("deviceId");
            Integer id = Integer.valueOf(str);
            DeviceInfo dInfo = deviceHandler.queryDeviceById(id);
            String jsonData = JSONUtil.serialize(dInfo);
            getRequest().put("jsonData", jsonData);
            List<DeviceInfo> dList;
            if (sysUser.getOrgId() != null) {
                dList = deviceHandler.queryAllDeviceForMap(sysUser.getOrgId());
            } else {
                dList = deviceHandler.queryAllDevice();
            }

            List<DeviceMapDto> dmdList = new ArrayList<DeviceMapDto>();
            for (DeviceInfo deInfo : dList) {
                DeviceMapDto ddto = new DeviceMapDto();
                ddto.setDeviceId(deInfo.getDeviceId());
                ddto.setDeviceAddress(deInfo.getDeviceAddress());
                ddto.setDeviceName(deInfo.getDeviceName());
                ddto.setDeviceNo(deInfo.getDeviceNo());
                ddto.setDeviceLat(deInfo.getDeviceLat());
                ddto.setDeviceLng(deInfo.getDeviceLng());
                ddto.setDeviceTypeName(deInfo.getDeviceType().getDeviceTypeName());

                List<DeviceMaintenance> dMList = deviceHandler.queryDMainByDeviceId(deInfo.getDeviceId());
                if (dMList != null) {
                    ddto.setFinishTime(dMList.get(0).getFinishTime());
                    ddto.setExpEndTime(dMList.get(0).getExpEndTime());
                    ddto.setMaintenanceDate(dMList.get(0).getMaintenanceDate());
                    ddto.setMaintenanceReason(dMList.get(0).getMaintenanceReason());
                    ddto.setMaintenanceUser(dMList.get(0).getSysUser().getRealName());
                    ddto.setRemark(dMList.get(0).getRemark());
                }
                dmdList.add(ddto);
            }
            String jsonList = JSONUtil.serialize(dmdList);
            getRequest().put("jsonList", jsonList);
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 点击设备信息页面,进入设备维护列表页面
     */
    @Action(value = "deviceMainList", results = {
            @Result(name = SUCCESS, location = "/view/device/deviceMaintenanceList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public String deviceMainList() {
        String result = SUCCESS;
        try {

        } catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 根据设备所在商圈查询 设备所在站点
     */
    @Action(value = "queryDistrict")
    public void queryDistrict() {
        try {
            String districtId = this.getRequestParm().getParameter("districtId");
            List<CarLineNode> nodeList = deviceHandler.queryDistrict(Integer.valueOf(districtId));
            CarLineNode carLineNode = new CarLineNode();
            if (nodeList.size() > 0) {
                carLineNode = nodeList.get(0);
            }
            String jsonData = JSONUtil.serialize(carLineNode);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有的设备信息
     */
    @Action(value = "queryAllDevice", results = {
            @Result(name = SUCCESS, location = "/view/device/allDeviceMap.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public String queryAllDevice() {
        String result = SUCCESS;
        SysUser sysUser = (SysUser) this.getSession().get("account");
        try {
//			List<DeviceInfo> dInfo = (List<DeviceInfo>) deviceHandler.queryAllDevice();
//			String jsonData = JSONUtil.serialize(dInfo);
//			getRequest().put("jsonData", jsonData);

            List<DeviceInfo> dList;
            if (sysUser.getOrgId() != null) {
                dList = deviceHandler.queryAllDeviceForMap(sysUser.getOrgId());
            } else {
                dList = deviceHandler.queryAllDevice();
            }

            List<DeviceMapDto> dmdList = new ArrayList<DeviceMapDto>();
            for (DeviceInfo deInfo : dList) {
                 DeviceMapDto ddto = new DeviceMapDto();
                 ddto.setDeviceId(deInfo.getDeviceId());
                 ddto.setDeviceAddress(deInfo.getDeviceAddress());
                 ddto.setDeviceName(deInfo.getDeviceName());
                 ddto.setDeviceNo(deInfo.getDeviceNo());
                 ddto.setDeviceLat(deInfo.getDeviceLat());
                 ddto.setDeviceLng(deInfo.getDeviceLng());
                 ddto.setDeviceTypeName(deInfo.getDeviceType().getDeviceTypeName());
                 ddto.setDeviceStatus(deInfo.getDeviceStatus());
                 List<DeviceMaintenance> dMList = deviceHandler.queryDMainByDeviceId(deInfo.getDeviceId());
                 if (dMList != null) {
                      ddto.setFinishTime(dMList.get(0).getFinishTime());
                      ddto.setExpEndTime(dMList.get(0).getExpEndTime());
                      ddto.setMaintenanceDate(dMList.get(0).getMaintenanceDate());
                      ddto.setMaintenanceReason(dMList.get(0).getMaintenanceReason());
                      ddto.setMaintenanceUser(dMList.get(0).getSysUser().getRealName());
                      ddto.setRemark(dMList.get(0).getRemark());
                 }
                     dmdList.add(ddto);
                 }
                 String jsonData = JSONUtil.serialize(dmdList);
                 getRequest().put("jsonData", jsonData);
         
        } catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 修改设备状态
     *
     * @return
     * @throws IOException
     */
    @Action(value = "changeDeviceStatus")
    public String changeDeviceStatus() throws IOException {
        try {
            String deviceId = this.getRequestParm().getParameter("deviceId");
            String isDisable = this.getRequestParm().getParameter("isDisable");
            DeviceInfo deviceInfo = deviceHandler.queryDeviceById(Integer.valueOf(deviceId));
            deviceInfo.setDeviceStatus(isDisable);
            deviceInfo.setNewer(false);
            deviceHandler.saveOrUpdateDeviceInfoDAO(deviceInfo);
            this.getResponse().getWriter().print(true);
        } catch (Exception e) {
            e.printStackTrace();
            this.getResponse().getWriter().print(false);
        }
        return null;
    }
    /**
     * 检查是否存在设备
     * @return
     */
    @Action(value="checkDevice")
    public void checkDevice(){
    	SysUser user = (SysUser) this.getSession().get("account");
    	List<DeviceInfo> list = new ArrayList<>();
    	try{
    		if(user.getOrgId() != null){
    			list = deviceHandler.queryAllDeviceForMap(user.getOrgId());
    		}
    		else{
    			list = deviceHandler.queryAllDevice();
    		}
    		if(list == null){
    			this.getResponse().getWriter().print(false);
    		}
    		else{
    			this.getResponse().getWriter().print(true);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}
