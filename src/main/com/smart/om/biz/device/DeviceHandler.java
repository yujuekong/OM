package com.smart.om.biz.device;

import com.smart.om.persist.*;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

import java.util.List;
import java.util.Map;

/**
 * 设备功能管理业务逻辑类
 *
 * @author lc
 */
public interface DeviceHandler {

    /**
     * 分页查询设备类型
     **/
    DTablePageModel queryDeviceType(Map<String, Object> params, PageData pageData);

    /**
     * 查询所有设备类型
     **/
    List<DeviceType> queryAllDeviceType();

    /**
     * 添加或修改设备类型
     **/
    DeviceType saveOrUpdateDeviceTypeDAO(DeviceType deviceType);

    /**
     * 删除设备类型
     **/
    DeviceType delDeviceTypeById(Integer deviceTypeId);

    /**
     * 根据设备类型ID查询设备类型
     **/
    DeviceType queryDTypeById(Integer id);

    /********************************************/

    /**
     * 分页查询设备信息
     **/
    DTablePageModel queryDeviceInfo(Map<String, Object> params, PageData pageData);

    /**
     * 查询系统用户(值守人)
     **/
    DTablePageModel choosePerson(Map<String, Object> params, PageData pageData);

    /**
     * 添加或修改设备信息
     **/
    DeviceInfo saveOrUpdateDeviceInfoDAO(DeviceInfo deviceInfo);

    /**
     * 删除设备信息
     **/
    DeviceInfo delDeviceById(Integer deviceId);

    /**
     * 根据设备类型ID查询设备信息
     **/
    DeviceInfo queryDeviceById(Integer id);

    /**
     * 根据设备标号查询设备信息
     **/
    List<DeviceInfo> queryDeviceByNo(String deviceNo);

    /**
     * 查询所有设备
     **/
    List<DeviceInfo> queryAllDevice();

    /**
     * 查询所有设备FOR MAP
     **/
    List<DeviceInfo> queryAllDeviceForMap(Integer orgId);

    /**
     * 批量删除设备信息
     **/
    int delDeviceInfoByType(Integer deviceTypeId);

    /**
     * 根据设备所在商圈查询 设备所在站点
     **/
    List<CarLineNode> queryDistrict(Integer id);
    /******************************************/

    /**
     * 分页查询维护设备信息
     **/
    DTablePageModel queryDeviceMain(Map<String, Object> params, PageData pageData);

    /**
     * 查询个人维护任务
     **/
    List<DeviceMaintenance> queryDeviceMaintenanceList(Integer userId);

    /**
     * 添加或修改设备维护信息
     **/
    DeviceMaintenance saveOrUpdateDeviceMainDAO(DeviceMaintenance deviceInfo);

    /**
     * 删除设备维护信息
     **/
    DeviceMaintenance delDeviceMainById(Integer deviceId);

    /**
     * 根据ID查询维护设备信息
     **/
    DeviceMaintenance queryDeviceMainById(Integer deviceId);

    List<DeviceMaintenance> queryDMainByDeviceId(Integer deviceId);

    /********************************************/
    /**
     * 分页查询清洗设备信息
     **/
    DTablePageModel queryDeviceClean(Map<String, Object> params, PageData pageData);

    /**
     * 查询个人清洗任务
     **/
    List<DeviceClean> queryDeviceCleanList(Integer userId);

    /**
     * 添加或修改清洗设备信息
     **/
    DeviceClean saveOrUpdateDeviceCleanDAO(DeviceClean deviceClean);

    /**
     * 删除设备清洗信息
     **/
    DeviceClean delDeviceCleanById(Integer deviceId);

    /**
     * 根据设备清洗ID查询设备清洗信息
     **/
    DeviceClean queryDeviceCleanById(Integer id);

    /**
     * 查询用户信息
     **/
    DTablePageModel queryUser(Map<String, Object> params, PageData pageData);
    /**************************************************************/
    /**
     * 分页查询设备商品
     **/
    DTablePageModel queryDeviceGoods(Map<String, Object> params, PageData pageData);

    /**
     * 根据设备分页查询设备商品
     **/
    DTablePageModel queryDeviceGoodsByDevice(Map<String, Object> params, PageData pageData);

    /**
     * 根据设备ID查询设备商品
     **/
    List<DeviceGoods> queryDeviceGoodsBydeviceId(Integer deviceId);
    
    /**
     * APP查询设备格子商品
     **/
    GoodsInfo AppSelDeviceGoods(Integer deviceId,String grNo);

    /**
     * 添加或修改设备商品
     **/
    DeviceGoods saveOrUpdateDeviceGoods(DeviceGoods deviceGoods);

    /**
     * 根据设备ID删除设备商品
     **/
    int delDeviceGoodsById(Integer deviceId);

    /**
     * 根据设备商品ID删除设备商品
     **/
    int delById(Integer deviceGoodsId);

    /**
     * 根据设备商品ID查询设备商品
     **/
    DeviceGoods queryDeviceGoodsById(Integer deviceGoodsId);
    /**************************************************************/
    /**
     * 添加或修改设备格子
     **/
    DeviceGrid saveDeviceGrid(DeviceGrid deviceGrid);

    /**
     * 根据设备ID查询设备格子
     **/
    List<DeviceGrid> queryDeviceGridBydeviceId(Integer deviceGoodsId);

    /**
     * 生成刀片更新任务
     **/
    void queryDeviceByPeriod();

}