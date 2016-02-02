package com.smart.om.biz.advert.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smart.om.biz.advert.AdvertHandler;
import com.smart.om.dao.advert.AdvertInfoDAO;
import com.smart.om.dao.advert.AdvertPositionDAO;
import com.smart.om.dao.advert.AdvertUserDAO;
import com.smart.om.dao.base.BasePo;
import com.smart.om.dto.advert.AdvertPositionDto;
import com.smart.om.persist.AdvertDevice;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

/**
 * 广告功能管理业务逻辑类
 * 
 * @author CA
 * 
 */
@Component("advertHandler")
public class AdvertHandlerImpl implements AdvertHandler {
	
	@Autowired
	private AdvertUserDAO advertUserDAO;
	@Autowired
	private AdvertInfoDAO advertInfoDAO;
	@Autowired
	private AdvertPositionDAO advertPositionDAO;

	/** 查询广告主 **/
	public DTablePageModel queryAdvertUserPage(Map<String, Object> params,
			PageData pageData) {
		DTablePageModel pageModel = new DTablePageModel();
		if (pageData != null) {
			pageModel = advertUserDAO.queryAdvertUserPage(params, pageData);
		}
		return pageModel;
	}

	/** 查询广告内容 **/
	public DTablePageModel queryAdvertInfoPage(Map<String, Object> params,
			PageData pageData) {
		DTablePageModel pageModel = new DTablePageModel();
		if (pageData != null) {
			pageModel = advertInfoDAO.queryAdvertInfoPage(params, pageData);
		}
		return pageModel;
	}
	
	/** 新增广告主 **/
	public BasePo saveOrUpdateAdvertUser(BasePo basePo) {

		return advertUserDAO.saveOrUpdate(basePo);
	}

	/** 查询单个广告主 */
	public BasePo queryAdvertUserById(Integer id) {

		return advertUserDAO.findById(id);
	}

	/** 删除单个广告主 **/
	public BasePo deleteAdvertUser(Integer id) {

		return advertUserDAO.delete(id);
	}

	/** 新增广告信息 **/
	public BasePo saveOrUpdateAdvertInfo(BasePo basePo) {

		return advertInfoDAO.saveOrUpdateAdvertInfo(basePo);
	}

	/** 删除广告信息 **/
	public BasePo deleteAdvertInfo(Integer id) {

		return advertInfoDAO.delete(id);
	}

	/** 查询单个广告内容 **/
	public BasePo queryAdvertInfoById(Integer id) {

		return advertInfoDAO.queryAdvertInfoByid(id);
	}

	/** 查询所有广告主 **/
	public DTablePageModel queryAdvertUser(Map<String, Object> params,
			PageData pageData) {
		DTablePageModel pageModel = new DTablePageModel();
		if (pageData != null) {
			pageModel = advertUserDAO.queryAdvertUserPage(params, pageData);
		}
		return pageModel;
	}
	
	/** 查询广告设备分页信息 **/
	public DTablePageModel queryAdvertPositionPage(Map<String, Object> params,
			PageData pageData) {
		DTablePageModel pageModel = new DTablePageModel();
		if (pageData != null) {
			pageModel = advertPositionDAO.queryAdvertPositionPage(params, pageData);
		}
		return pageModel;
	}
	
	/** 新增广告投放设备 **/
	public BasePo saveOrUpdateAdvertDevice(BasePo basePo) {
		
		return advertPositionDAO.saveOrUpdate(basePo);
	}

	/** 新增广告投放设备信息 **/
	public DTablePageModel queryAdvertDevicePage(Map<String, Object> params,
			PageData pageData) {
		return advertInfoDAO.queryDeviceInfo(params, pageData);
	}
	
	/** 查询广告投放设备的数量 **/
	public Long totalDevice(Integer advertInfoId) {
		
		return advertInfoDAO.totalDevice(advertInfoId);
	}

	/** 根据ID查询广告投放的设备 **/
	public BasePo queryAdvertDeviceById(Integer id) {
		
		return advertPositionDAO.find(id);
	}

	/** 删除广告投放设备 **/
	public BasePo deleteAdvertDeviceById(Integer id) {
		
		return advertPositionDAO.delete(id);
	}
	
	/** 根据广告内容ID查询广告投放设备信息 **/
	public List<AdvertPositionDto> queryAdvertDeviceByAdId(Integer id) {
		 
		return advertPositionDAO.queryAdvertDeviceByAdId(id);
	}

	/** 查询广告设备 **/
	public List<AdvertDevice> queryAdvertDevice(Integer id) {
		return advertInfoDAO.queryAdvertDevice(id);
	}

	/** 删除广告设备 **/
	public AdvertDevice deleteAdvertDevice(AdvertDevice advertDevice) {
		
		return (AdvertDevice) advertPositionDAO.delete(advertDevice);
	}

	@Override
	public DTablePageModel queryUserPage(Map<String, Object> params,
			PageData pageData) {
		return advertInfoDAO.queryUserPage(params, pageData);
	}

}
