package com.smart.om.web.device;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import com.smart.om.biz.device.DeviceHandler;
import com.smart.om.persist.DeviceGoods;
import com.smart.om.persist.DeviceGrid;
import com.smart.om.persist.DeviceInfo;
import com.smart.om.persist.SysUser;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.web.base.BaseAction;

/**
 * 设备类型管理Action
 *
 * @author lc
 */
@Namespace("/view/device/deviceGoods")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class DeviceGoodsAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(DeviceGoodsAction.class);
    @Resource
    private DeviceHandler deviceHandler;

    private DeviceGoods deviceGoods;
    private DeviceInfo deviceInfo;

    private List<DeviceGoods> goodsList;

    /**
     * 分页查询所有设备商品
     */
    @Action(value = "queryDeviceGoodsByDevice")
    public void queryDeviceGoodsByDevice() {
        SysUser sysUser = (SysUser) this.getSession().get("account");
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String deviceId = this.getRequestParm().getParameter("deviceId");
            String level = this.getRequestParm().getParameter("level");
            String nodeId = this.getRequestParm().getParameter("id");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("orgId", sysUser.getOrgId());
            if (StringUtils.isNotBlank(level)) {
                if (level.equals("0")) {//点击的是根节点

                } else if (level.equals("1")) {//点击的是区域
                    params.put("dictRegionId", nodeId);
                } else if (level.equals("2")) {//点击的是省份
                    params.put("dictProviceId", nodeId);
                } else if (level.equals("3")) {//点击的是分公司
                    params.put("dictOrgId", nodeId);
                }
            }
            params.put("keyword", keyword);
            params.put("deviceId", deviceId);
            params.put("orgId", sysUser.getOrgId());

            DTablePageModel dtPageModel = deviceHandler.queryDeviceGoodsByDevice(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Action(value = "queryDeviceGoods")
    public void queryDeviceGoods() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String deviceId = this.getRequestParm().getParameter("deviceId");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            params.put("deviceId", deviceId);

            DTablePageModel dtPageModel = deviceHandler.queryDeviceGoods(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 进入格子
     */
    @Action(value = "geZi")
    public void geZi() {
        try {
            JsonConfig config = new JsonConfig();
            config.setJsonPropertyFilter(new PropertyFilter() {
                public boolean apply(Object arg0, String arg1, Object arg2) {
                    return arg1.equals("deviceInfo") || arg1.equals("goodsInfo") || arg1.equals("sysDict");
                }
            });
            String deviceId = this.getRequestParm().getParameter("deviceId");
            if (StringUtils.isNotBlank(deviceId)) {
                Integer id = Integer.valueOf(deviceId);
                List<Object> obj = new ArrayList<Object>();
                List<DeviceGrid> gridList = deviceHandler.queryDeviceGridBydeviceId(id);
                List<DeviceGoods> goodsList = deviceHandler.queryDeviceGoodsBydeviceId(id);
                obj.add(gridList);
                obj.add(goodsList);

                HttpServletResponse response = ServletActionContext.getResponse();
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                JSONArray json = JSONArray.fromObject(obj, config);
                out.print(json);
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
        }
    }

    /**
     * 进入格子明细
     */
    @Action(value = "geZiDtl")
    public void geZiDtl() {
        try {
            String deviceId = this.getRequestParm().getParameter("deviceId");
            if (StringUtils.isNotBlank(deviceId)) {
                Integer id = Integer.valueOf(deviceId);
                DeviceInfo dInfo = deviceHandler.queryDeviceById(id);
                List<DeviceGoods> dGoodsList = deviceHandler.queryDeviceGoodsBydeviceId(id);
                List<DeviceGrid> gridList = deviceHandler.queryDeviceGridBydeviceId(id);

                List<DeviceGoods> dGList = new ArrayList<DeviceGoods>();

                for (int i = 0; i < gridList.size(); i++) {
                    DeviceGoods DG = new DeviceGoods();
                    for (int j = 0; j < dGoodsList.size(); j++) {
                        if (gridList.get(i).getGridNo().equals(dGoodsList.get(j).getGridNo())) {
                            DG.setDeviceGoodsId(dGoodsList.get(j).getDeviceGoodsId());
                            DG.setDeviceId(dGoodsList.get(j).getDeviceId());
                            DG.setDeviceNo(dGoodsList.get(j).getDeviceNo());
                            DG.setGoodsId(dGoodsList.get(j).getGoodsId());
                            DG.setGoodsName(dGoodsList.get(j).getGoodsName());
                        }
                    }
                    dGList.add(DG);
                }


                String jsonData = JSONUtil.serialize(dGList);
                PrintWriter pw = super.getResponse().getWriter();
                pw.write(jsonData);
                pw.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
        }
    }

    /**
     * 进入添加或编辑设备类型信息页面
     */
    @Action(value = "addOrModifydeviceGoods", results = {
            @Result(name = SUCCESS, location = "/view/device/deviceGoodsAdd.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public String addOrModifydeviceGoods() {
        String result = SUCCESS;
        try {
            String deviceId = this.getRequestParm().getParameter("deviceId");
            if (StringUtils.isNotBlank(deviceId)) {
                Integer id = Integer.valueOf(deviceId);
                DeviceInfo dInfo = deviceHandler.queryDeviceById(id);
                List<DeviceGoods> dGoodsList = deviceHandler.queryDeviceGoodsBydeviceId(id);
                List<DeviceGrid> gridList = deviceHandler.queryDeviceGridBydeviceId(id);

                List<DeviceGoods> dGList = new ArrayList<DeviceGoods>();

                for (int i = 0; i < gridList.size(); i++) {
                    DeviceGoods DG = new DeviceGoods();
                    for (int j = 0; j < dGoodsList.size(); j++) {
                        if (gridList.get(i).getGridNo().equals(dGoodsList.get(j).getGridNo())) {
                            DG.setDeviceGoodsId(dGoodsList.get(j).getDeviceGoodsId());
                            DG.setDeviceId(dGoodsList.get(j).getDeviceId());
                            DG.setDeviceNo(dGoodsList.get(j).getDeviceNo());
                            DG.setEndTime(dGoodsList.get(j).getEndTime());
                            DG.setGoodsId(dGoodsList.get(j).getGoodsId());
                            DG.setGoodsName(dGoodsList.get(j).getGoodsName());
                            DG.setGoodsUnit(dGoodsList.get(j).getGoodsUnit());
                            DG.setGridBar(dGoodsList.get(j).getGridBar());
                            DG.setGridId(dGoodsList.get(j).getGridId());
                            DG.setGridNo(dGoodsList.get(j).getGridNo());
                            DG.setStartTime(dGoodsList.get(j).getStartTime());
                        }
                    }
                    dGList.add(DG);
                }

                getRequest().put("deviceInfo", dInfo);
                getRequest().put("dGoodsList", dGList);
                getRequest().put("gridList", gridList);
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
     * 进入添加或编辑设备类型信息页面
     */
    @Action(value = "addOrModifydeviceGoodstest", results = {
            @Result(name = SUCCESS, location = "/view/device/deviceGoodsAddtest.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public String addOrModifydeviceGoodstest() {
        String result = SUCCESS;
        try {
            String deviceId = this.getRequestParm().getParameter("deviceId");
            if (StringUtils.isNotBlank(deviceId)) {
                Integer id = Integer.valueOf(deviceId);
                DeviceInfo dInfo = deviceHandler.queryDeviceById(id);
                List<DeviceGoods> dGoodsList = deviceHandler.queryDeviceGoodsBydeviceId(id);
                List<DeviceGrid> gridList = deviceHandler.queryDeviceGridBydeviceId(id);
                getRequest().put("deviceInfo", dInfo);
                getRequest().put("dGoodsList", dGoodsList);
                getRequest().put("gridList", gridList);
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
     * 添加或修改设备商品信息
     */
    @Action(value = "saveOrUpdateDeviceGoods", results = {
            @Result(name = SUCCESS, location = "/view/device/deviceGoodsList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public String saveOrUpdateDeviceGoods() {
        String result = SUCCESS;
        try {
            Integer deviceGoodsId = deviceGoods.getDeviceGoodsId();
            if (goodsList.size() > 0) {
                deviceHandler.delDeviceGoodsById(deviceGoods.getDeviceId());
                if (deviceGoodsId == null) {
                    for (DeviceGoods model : goodsList) {
                        if (model != null) {
                            //if(model.getDeviceGoodsId() == null ){
                            if (model.getGoodsId() != null) {
                                model.getGoodsId();
                                model.getGoodsName();
                                model.getGoodsUnit();
                                model.getGridId();
                                model.getGridNo();
                                model.getGridId();
                                model.setStartTime(DateUtil.getDateY_M_D());
                                model.setDeviceId(deviceGoods.getDeviceId());
                                model.setDeviceNo(deviceGoods.getDeviceNo());
                                model.setNewer(true);
                                deviceHandler.saveOrUpdateDeviceGoods(model);
                            }
                            //}
//							else{
//								model.getDeviceGoodsId();
//								model.getGoodsId();
//								model.getGoodsName();
//								model.getGoodsUnit();
//								model.getGridId();
//								model.getGridNo();
//								model.getGridId();
//								model.setStartTime(DateUtil.getDateY_M_D());
//								model.setDeviceId(deviceGoods.getDeviceId());
//								model.setDeviceNo(deviceGoods.getDeviceNo());
//								deviceHandler.saveOrUpdateDeviceGoods(model);
//							}

                        }
                    }
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
     * 根据设备ID删除设备商品信息
     */
    @Action(value = "delDeviceGoodsById", results = {
            @Result(name = SUCCESS, location = "/view/device/deviceGoodsList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")})
    public String delDeviceGoodsById() {
        String result = SUCCESS;
        try {
            Integer deviceId = Integer.valueOf(this.getRequestParm().getParameter("deviceId"));
            //删除设备类型下的所有设备
            deviceHandler.delDeviceGoodsById(deviceId);
        } catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 根据设备ID删除设备商品信息
     */
    @Action(value = "delById")
    public void delById() {
        try {
            Integer deviceGoodsId = Integer.valueOf(this.getRequestParm().getParameter("deviceGoodsId"));
            //删除设备类型下的所有设备
            Integer dg = deviceHandler.delById(deviceGoodsId);
            String jsonData = JSONUtil.serialize(dg);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
        }
    }


    public DeviceGoods getDeviceGoods() {
        return deviceGoods;
    }

    public void setDeviceGoods(DeviceGoods deviceGoods) {
        this.deviceGoods = deviceGoods;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public List<DeviceGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<DeviceGoods> goodsList) {
        this.goodsList = goodsList;
    }

}
