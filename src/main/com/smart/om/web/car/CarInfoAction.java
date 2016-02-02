package com.smart.om.web.car;

import com.smart.om.biz.car.CarHandler;
import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.persist.CarInfo;
import com.smart.om.persist.CarTrack;
import com.smart.om.persist.SysDict;
import com.smart.om.persist.SysUser;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.web.base.BaseAction;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.Now;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车辆信息管理Action
 *
 * @author lc
 */
@Namespace("/view/car/carInfo")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class CarInfoAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(CarInfoAction.class);
    @Resource
    private CarHandler carHandler;
    @Resource
    private SysAssistHandler sysAssistHandler;

    private CarInfo carInfo;

    public CarInfo getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(CarInfo carInfo) {
        this.carInfo = carInfo;
    }

    /**
     * 查询所有车辆信息
     */
    @Action(value = "queryCarInfo", results = {
            @Result(name = SUCCESS, location = "/view/car/carInfoList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public void queryCarInfo() {
		SysUser sysUser = (SysUser) this.getSession().get("account");
		SysDict sysDict = sysAssistHandler.queryDictByPcode("AL_POSITION");
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String nodeId = this.getRequestParm().getParameter("id");
            String nodePid = this.getRequestParm().getParameter("pid");
            String level = this.getRequestParm().getParameter("level");
            String carStatus = this.getRequestParm().getParameter("carStatus");
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            params.put("orgId", sysUser.getOrgId());
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
            if (StringUtils.isNotBlank(carStatus)) {
                params.put("carStatus", carStatus);
            }
            DTablePageModel dtPageModel = carHandler.queryCarInfo(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 进入添加或编辑车辆信息页面
     */
    @Action(value = "addOrModifyCarInfo", results = {
            @Result(name = SUCCESS, location = "/view/car/carInfoAdd.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public String addOrModifyCarInfo() {
        String result = SUCCESS;
        try {
            String lineNodeId = this.getRequestParm().getParameter("carId");
            if (StringUtils.isNotBlank(lineNodeId)) {
                Integer id = Integer.valueOf(lineNodeId);
                CarInfo cInfo = (CarInfo) carHandler.queryCarById(id);
                getRequest().put("carInfo", cInfo);
            }
        } catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 添加或修改车辆信息
     */
    @Action(value = "saveOrUpdateCarInfo", results = {
            @Result(name = SUCCESS, location = "/view/car/carInfoList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public String saveOrUpdateCarInfo() {
        String result = SUCCESS;
        try {
            Integer carId = carInfo.getCarId();
            if (carId == null) {
                carInfo.setNewer(true);
                carInfo.setCreateDate(DateUtil.getDateY_M_D());
                carInfo.setCarStatus(Const.IS_VALID_FALSE);
            }
            carInfo.setIsDel(Const.IS_DEL_FALSE);
            carHandler.saveOrUpdateCarInfo(carInfo);
        } catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 删除车辆信息
     */
    @Action(value = "delCarById", results = {
            @Result(name = SUCCESS, location = "/view/car/carInfoList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public String delCarById() {
        String result = SUCCESS;
        String id = this.getRequestParm().getParameter("carId");
        try {
            if (StringUtils.isNotBlank(id)) {
                Integer carId = Integer.valueOf(id);
                CarInfo cInfo = (CarInfo) carHandler.queryCarById(carId);
                cInfo.setIsDel(Const.IS_DEL_TRUE);
                cInfo.setNewer(false);
                carHandler.saveOrUpdateCarInfo(cInfo);
            }

            //carHandler.delCarById(id);
        } catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 根据ID查询车辆信息
     */
    @Action(value = "queryCarById", results = {
            @Result(name = SUCCESS, location = "/view/car/carInfoDetail.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public String queryCarById() {
        String result = SUCCESS;
        try {
            String str = this.getRequestParm().getParameter("carId");
            Integer id = Integer.valueOf(str);
            CarInfo cInfo = (CarInfo) carHandler.queryCarById(id);
            getRequest().put("carInfo", cInfo);
        } catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }


    /**
     * 查询所有的车辆品牌信息
     */
    @Action(value = "queryBrandList", results = {
            @Result(name = SUCCESS, location = "/view/car/carInfoAdd.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public void queryBrandList() {
        String str = "CAR_BRAND";
        List<SysDict> sdList = carHandler.queryDictList(str);
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            JsonConfig config = new JsonConfig();
            config.setJsonPropertyFilter(new PropertyFilter() {
                public boolean apply(Object arg0, String arg1, Object arg2) {
                    if (arg1.equals("sysDictPid")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            out.print(JSONArray.fromObject(sdList, config));
            out.flush();
            out.close();
        } catch (IOException e) {

        }
    }

    /**
     * 查询所有的车辆类型信息
     */
    @Action(value = "queryCarTypeList", results = {
            @Result(name = SUCCESS, location = "/view/car/carInfoAdd.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public void queryCarTypeList() {
        String str = "CAR_TYPE";
        List<SysDict> sdList = carHandler.queryDictList(str);
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            JsonConfig config = new JsonConfig();
            config.setJsonPropertyFilter(new PropertyFilter() {
                public boolean apply(Object arg0, String arg1, Object arg2) {
                    if (arg1.equals("sysDictPid")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            out.print(JSONArray.fromObject(sdList, config));
            out.flush();
            out.close();
        } catch (IOException e) {

        }
    }

    /**
     * 修改车辆状态
     *
     * @return
     * @throws IOException
     */
    @Action(value = "changeCarStatus")
    public String changeCarStatus() throws IOException {
        try {
            String carId = this.getRequestParm().getParameter("carId");
            String isDisable = this.getRequestParm().getParameter("isDisable");
            CarInfo carInfo = (CarInfo) carHandler.queryCarById(Integer.valueOf(carId));
            carInfo.setCarStatus(isDisable);
            carInfo.setNewer(false);
            carHandler.saveOrUpdateCarInfo(carInfo);
            this.getResponse().getWriter().print(true);
        } catch (Exception e) {
            e.printStackTrace();
            this.getResponse().getWriter().print(false);
        }
        return null;
    }
	/**
	 * 根据设备ID进入地图页面
	 */
	@Action(value = "carInfoMap", results = {
			@Result(name = SUCCESS, location = "/view/car/carInfoMap.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String carInfoMap() {
		String result = SUCCESS;
		try{
			String str = this.getRequestParm().getParameter("carId");
			Integer id =Integer.valueOf(str);
			CarInfo cInfo = (CarInfo)carHandler.queryCarById(id);
//			CarTrack cTrack = (CarTrack)carHandler.queryCarTrackByCarId(id);
//			cInfo.setCarLng(cTrack.getCarLng());
//			cInfo.setCarLat(cTrack.getCarLat());
			String jsonData = JSONUtil.serialize(cInfo);
			getRequest().put("jsonData", jsonData);
			
			List<CarInfo> cList = (List<CarInfo>) carHandler.queryAllCar();
			String jsonList = JSONUtil.serialize(cList);
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
	 * 查看所有车辆实时位置进入地图页面
	 */
	@Action(value = "carAllMap", results = {
			@Result(name = SUCCESS, location = "/view/car/allCarMap.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String carAllMap() {
		String result = SUCCESS;
		SysUser sysUser = (SysUser) this.getSession().get("account");
		try{
			List<CarInfo> cList;
			if(sysUser.getOrgId() != null){
				cList = (List<CarInfo>) carHandler.queryAllCarForMap(sysUser.getOrgId());
			}else{
				cList = (List<CarInfo>) carHandler.queryAllCar();
			}
			String jsonData = JSONUtil.serialize(cList);
			getRequest().put("jsonData", jsonData);
		}catch (Exception e) {
			e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}
	/**
	 * 根据设备ID进入车辆轨迹地图页面
	 */
	@Action(value = "carTrackMap", results = {
			@Result(name = SUCCESS, location = "/view/car/carTrackMap.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String carTrackMap() {
		String result = SUCCESS;
		try{
			String str = this.getRequestParm().getParameter("carId");
			Integer id =Integer.valueOf(str);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String endTime = df.format(new Date());// new Date()为获取当前系统时间
			Date as = new Date(new Date().getTime()-24*60*60*1000);
			SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String  startTime= matter1.format(as);
			System.out.println("endTime  :  "+endTime);
			System.out.println("startTime  :  "+startTime);
			
			List<CarTrack> cList = (List<CarTrack>) carHandler.queryCarTrack(id,startTime,endTime);
			String jsonData = JSONUtil.serialize(cList);
			getRequest().put("jsonData", jsonData);
		}catch (Exception e) {
			e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}

}
