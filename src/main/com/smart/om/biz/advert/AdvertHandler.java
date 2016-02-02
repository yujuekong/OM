package com.smart.om.biz.advert;

import java.util.List;
import java.util.Map;

import com.smart.om.dao.base.BasePo;
import com.smart.om.dto.advert.AdvertPositionDto;
import com.smart.om.persist.AdvertDevice;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

public interface AdvertHandler {

	/** 查询广告主 **/
	public DTablePageModel queryAdvertUserPage(Map<String, Object> params,
			PageData pageData);

	/** 新增广告主 **/
	BasePo saveOrUpdateAdvertUser(BasePo basePo);

	/** 查询单个广告主 **/
	BasePo queryAdvertUserById(Integer id);

	/** 删除单个广告主 **/
	BasePo deleteAdvertUser(Integer id);
	
	/*********************************** 广告内容管理  *****************************************************/
	
	/** 查询广告内容 **/
	public DTablePageModel queryAdvertInfoPage(Map<String, Object> params,
			PageData pageData);

	/** 新增广告 **/
	BasePo saveOrUpdateAdvertInfo(BasePo basePo);

	/** 删除广告 **/
	BasePo deleteAdvertInfo(Integer id);

	/** 查询单个广告内容 **/
	BasePo queryAdvertInfoById(Integer id);
	
	/** 查询投放的设备信息**/
	public DTablePageModel queryAdvertDevicePage(Map<String, Object> params,
			PageData pageData);
	
	/**查询广告投放设备的数量**/
	public Long totalDevice(Integer advertInfoId);
	
	/**根据ID查询广告投放的设备**/
	BasePo queryAdvertDeviceById(Integer id);
	
	/**删除广告投放信息**/
	BasePo deleteAdvertDeviceById(Integer id);
	
	/**根据广告内容ID查询广告投放设备信息**/
	public List<AdvertPositionDto> queryAdvertDeviceByAdId(Integer id);
	
	/** 查询广告设备 **/
	public List<AdvertDevice> queryAdvertDevice(Integer id);
	
	/** 查询广告主分页 **/
	public DTablePageModel queryUserPage(Map<String, Object> params,
			PageData pageData);
	
	/**********************************  广告设备管理    *****************************************************/
	
	/**查询广告设备分页信息**/
	public DTablePageModel queryAdvertPositionPage(Map<String, Object> params,
			PageData pageData);
	
	/**新增广告设备**/
	BasePo saveOrUpdateAdvertDevice(BasePo basePo);
	
	/** 删除广告设备 **/
	public AdvertDevice deleteAdvertDevice(AdvertDevice advertDevice);
}