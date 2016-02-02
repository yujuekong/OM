package com.smart.om.rest.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.smart.om.biz.device.DeviceHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.DeviceClean;
import com.smart.om.persist.SysUser;
import com.smart.om.persist.SysUserOp;
import com.smart.om.rest.base.BaseResource;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.PageData;
import com.smart.om.util.Tools;

/**
 * 设备清洗管理Rest接口
 * @author langyuk
 *
 */
@Component("DeviceCleanResource")
public class DeviceCleanResource  extends BaseResource{
	private static final Logger logger = Logger.getLogger(DeviceCleanResource.class);
	@Resource
	private DeviceHandler deviceHandler;
	@Resource
	private SysFuncHandler sysFuncHandler;
	
	//查询登录用户所有清洗订单
	public String queryDeviceCleans(Map dataMap){
		String result = "";
		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
		String userId = Tools.trim(dataMap.get("userId"));//登录用户	
		String orderDate = Tools.trim(dataMap.get("orderDate"));//清洗日期
		String deviceId = Tools.trim(dataMap.get("deviceId"));//设备ID
		String deviceNo =Tools.trim(dataMap.get("deviceNo"));//设备编号
		
		String checkUserId = Tools.trim(dataMap.get("checkUserId"));//登录用户	
		if(StringUtils.isBlank(deviceNo)){
			if(StringUtils.isBlank(orderDate)) {//如果参数没有清洗日期，则查询当前日期
				orderDate = DateUtil.getDateY_M_D();
			}
		}
		
		String cleanStatus = Tools.trim(dataMap.get("cleanStatus"));//清洗状态
		String pageSize = Tools.trim(dataMap.get("pageSize"));//每页显示条数
		String currentPage = Tools.trim(dataMap.get("currentPage"));//当前页	
		try {
			PageData pageData = this.getPageData(pageSize, currentPage);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("app", "app");
			params.put("userId", userId);
			params.put("orderDate", orderDate);
			params.put("cleanStatus", cleanStatus);
			params.put("deviceId", deviceId);
			DTablePageModel pageModel = deviceHandler.queryDeviceClean(params, pageData);
			List<DeviceClean> deviceCleanList = pageModel.getAaData();			
			if(deviceCleanList != null){
				for (DeviceClean deviceClean : deviceCleanList) {
					Map<String, Object> dcMap = new HashMap<String, Object>();					
					dcMap.put("cleanId", deviceClean.getCleanId());//清洗ID
					dcMap.put("deviceId", deviceClean.getDeviceId());//维修ID
					dcMap.put("cleanNo", deviceClean.getCleanNo());//清洗编号					
					dcMap.put("deviceAddress", deviceClean.getDeviceInfo().getDeviceAddress());//设备地址
					dcMap.put("orderTime", deviceClean.getCleanDate());//下单时间
					dcMap.put("requestTime", deviceClean.getCleanEndTiem());//要求完成时间
					dcMap.put("finishTime", deviceClean.getFinishTime());//实际完成时间
					dcMap.put("cleanStatus", deviceClean.getCleanStatus());//清洗状态
					dcMap.put("cleanReason", deviceClean.getCleanReason());//清洗说明
					if(StringUtils.isNotBlank(deviceClean.getFinishTime())){
						if(DateUtil.getTimeDiff(deviceClean.getFinishTime(), deviceClean.getCleanEndTiem())){
							dcMap.put("isOverTime", Const.IS_DEL_TRUE);//超时
						}
						else{
							dcMap.put("isOverTime", Const.IS_DEL_FALSE);//未超时
						}
					}	
					else{
						if("1".equals(deviceClean.getCleanStatus())){
							if(DateUtil.getTimeDiff(DateUtil.getDateY_M_DH_M_S(),deviceClean.getCleanEndTiem())){
								dcMap.put("isOverTime", "2");//未完成超时
							}else{
								dcMap.put("isOverTime", null);//未完成
							}
						}
					}
					lstMap.add(dcMap);
				}				
			}
			result = this.toResultJsonSuccess(lstMap);
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		return result;
	}
	
	//查询清洗订单详情
	public String queryDeviceCleanById(Map dataMap){
		String result = "";
		List<Map<String, Object>> lstMap = new ArrayList<Map<String, Object>>();
		String cleanId = Tools.trim(dataMap.get("cleanId"));//登录用户	
		try {
			DeviceClean deviceClean = deviceHandler.queryDeviceCleanById(Integer.valueOf(cleanId));
			Map<String, Object> dcMap = new HashMap<String, Object>();					
			dcMap.put("cleanId", deviceClean.getCleanId());//清洗ID
			dcMap.put("deviceId", deviceClean.getDeviceId());//维修ID
			dcMap.put("cleanNo", deviceClean.getCleanNo());//清洗编号					
			dcMap.put("deviceAddress", deviceClean.getDeviceInfo().getDeviceAddress());//设备地址
			dcMap.put("orderTime", deviceClean.getCleanDate());//下单时间
			dcMap.put("requestTime", deviceClean.getCleanEndTiem());//要求完成时间
			dcMap.put("finishTime", deviceClean.getFinishTime());//实际完成时间
			dcMap.put("cleanStatus", deviceClean.getCleanStatus());//清洗状态
			dcMap.put("remark", deviceClean.getRemark());//清洗说明
			dcMap.put("cleanPic1", deviceClean.getCleanPic1());//清洗图片1
			dcMap.put("cleanPic2", deviceClean.getCleanPic2());//清洗图片2
			dcMap.put("cleanPic3", deviceClean.getCleanPic3());//清洗图片3
			dcMap.put("cleanPic4", deviceClean.getCleanPic4());//清洗图片4
			dcMap.put("cleanPic5", deviceClean.getCleanPic5());//清洗图片5
			dcMap.put("cleanType", deviceClean.getCleanType());//清洗类型
			dcMap.put("cleanReason", deviceClean.getCleanReason());//清洗说明
			result = this.toResultJsonSuccess(dcMap);
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
		return result;
	}
	
	/**
	 * 完成设备清洗
	 * @param dataMap
	 * @return
	 */
	public String submitDeviceClean(String data){
		try {
			JSONObject dataObject=JSONObject.fromObject(data);
			String cleanId = Tools.trim(dataObject.getString("cleanId"));//清洗的订单ID
			String remark = Tools.trim(dataObject.getString("remark"));//清洗备注
			String cleanPics = dataObject.containsKey("cleanPics")?dataObject.getString("cleanPics"):null;
			
			DeviceClean deviceClean = deviceHandler.queryDeviceCleanById(Integer.valueOf(cleanId));			
			//获得图片地址
			String rootPath = this.getClass().getResource("/").getPath();
			rootPath = rootPath.substring(0, rootPath.indexOf("WEB-INF")-1);
			if(deviceClean != null) {
				if(StringUtils.isNotBlank(remark)) {
					deviceClean.setRemark(remark);
				}
				if(StringUtils.isNotEmpty(cleanPics)) {
					//解析出库信息的JSON串，循环修改订单明细的实际出库数量
					JSONArray picList = JSONArray.fromObject(cleanPics);
					int i = 0;
					for(Object obj:picList){
						i++;
						String fileName = "";//图片文件地址
						JSONObject jsonObject=JSONObject.fromObject(obj);
						String cleanPic = jsonObject.getString("cleanPic");
						if(StringUtils.isNotBlank(cleanPic)) {
							fileName = Const.DEVICE_CLEAN_IMG_PATH + "/" + deviceClean.getCleanNo() + i + ".png";
							Tools.base64StringToImage(cleanPic,"png",rootPath + fileName);							
						}
						//设置图片文件地址
						if(i == 1){
							deviceClean.setCleanPic1(fileName);
						}
						else if(i == 2){
							deviceClean.setCleanPic2(fileName);
						}
						else if(i == 3){
							deviceClean.setCleanPic3(fileName);
						}
						else if(i == 4){
							deviceClean.setCleanPic4(fileName);
						}
						else if(i == 5){
							deviceClean.setCleanPic5(fileName);
						}
					}
				}				
				
				deviceClean.setNewer(false);
				if(deviceClean.getFinishTime()==null){
					deviceClean.setFinishTime(DateUtil.getDateY_M_DH_M_S());
				}
				deviceClean.setCleanStatus(Const.IS_VALID_TRUE);
				deviceHandler.saveOrUpdateDeviceCleanDAO(deviceClean);
				
				//添加用户操作
				SysUser sysUser = sysFuncHandler.querySysUserById(deviceClean.getCleanUserId());
				SysUserOp sysUserOp = new SysUserOp(sysUser.getUserId(), sysUser.getRealName(), Const.OP_TYPE_CLEAN, deviceClean.getCleanId(), deviceClean.getCleanNo(), DateUtil.getDateY_M_D(), deviceClean.getFinishTime(), 
						deviceClean.getCleanStatus(), deviceClean.getRemark(), deviceClean.getCleanPic1(), deviceClean.getCleanPic2(), deviceClean.getCleanPic3(), deviceClean.getCleanPic4(), deviceClean.getCleanPic5(), deviceClean.getCleanReason());
				sysUserOp.setNewer(true);
				sysFuncHandler.saveOrUpdateOp(sysUserOp);
				return this.toSuccessTipJson("保存成功！");
			} else {
				return this.toFailTipJson("该清洗订单未找到！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this.toErrorResultJson("系统错误！",Const.ERROR_500);
		}
	}
}
