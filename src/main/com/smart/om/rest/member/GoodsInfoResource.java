package com.smart.om.rest.member;

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
import com.smart.om.biz.goods.GoodsHandler;
import com.smart.om.biz.region.RegionHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.DeviceInfo;
import com.smart.om.persist.GoodsInfo;
import com.smart.om.persist.SysUser;
import com.smart.om.rest.base.BaseResource;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.LngLatUtil;
import com.smart.om.util.PageData;
import com.smart.om.util.Tools;
/**
 * 商品Resource 接口
 * @author liuz
 *
 */
@Component("GoodsInfoResource")
public class GoodsInfoResource extends BaseResource{
	private static final Logger logger = Logger.getLogger(GoodsInfoResource.class);
	@Resource
    private GoodsHandler goodsHandler;
	@Resource
	private SysFuncHandler sysFuncHandler;
	@Resource
	private DeviceHandler deviceHandler;
	/**
	 * 查询所有商品
	 * @param dataMap
	 * @return
	 */
	public String goodsList(Map dataMap){
		List<Map<String, Object>> goodsInfoListMap = new ArrayList<Map<String, Object>>();
		Map<String, Object> params = new HashMap<String, Object>();
		String pageSize = Tools.trim(dataMap.get("pageSize"));//每页显示条数
		String currentPage = Tools.trim(dataMap.get("currentPage"));//当前页	
		PageData pageData = this.getPageData(pageSize, currentPage);
		params.put("app", "app");
        DTablePageModel dtPageModel = goodsHandler.queryGoodsInfoPage(params, pageData);
        List<GoodsInfo> goodsInfoList = dtPageModel.getAaData();
        if(goodsInfoList!=null){
        	for (int i = 0; i < goodsInfoList.size(); i++) {
        		Map<String,Object> map = new HashMap<String,Object>();
        		GoodsInfo goodsInfo = goodsInfoList.get(i);
        		map.put("goodsId", goodsInfo.getGoodsId());
        		map.put("goodsName",goodsInfo.getGoodsName());
        		map.put("goodsPic1",goodsInfo.getGoodsPic1());
        		map.put("goodsPic2",goodsInfo.getGoodsPic2());
        		map.put("goodsPrice",goodsInfo.getGoodsPrice());
        		map.put("goodsSort",goodsInfo.getGoodsSort());
        		map.put("synopsis",goodsInfo.getSynopsis());
        		
        		goodsInfoListMap.add(map);
			}
        }
		return this.toResultJsonSuccess(goodsInfoListMap);
	}
	
	/**
	 * 查询商品详细信息
	 * @param dataMap
	 * @return
	 */
	public String goodsInfoById(Map dataMap){
		JSONObject dataObject=JSONObject.fromObject(dataMap);
		List<Map<String, Object>> goodsInfoListMap = new ArrayList<Map<String, Object>>();
		String goodsInfoList = dataObject.containsKey("goodsInfoList")?dataObject.getString("goodsInfoList"):null;
		if(StringUtils.isNotEmpty(goodsInfoList)) {
			JSONArray goodsInfoArr = JSONArray.fromObject(goodsInfoList);
			for(Object obj:goodsInfoArr){
				JSONObject jsonObject=JSONObject.fromObject(obj);
				Integer goodsId = Integer.parseInt(jsonObject.getString("goodsId"));
				Map<String,Object> map = new HashMap<String,Object>();
				GoodsInfo goodsInfo = (GoodsInfo)goodsHandler.queryGoodsInfoById(goodsId);
				if(goodsInfo!=null){
					map.put("goodsId", goodsInfo.getGoodsId());
					map.put("goodsName",goodsInfo.getGoodsName());
					map.put("goodsPic1",goodsInfo.getGoodsPic1());
					map.put("goodsPic2",goodsInfo.getGoodsPic2());
					map.put("goodsPrice",goodsInfo.getGoodsPrice());
					map.put("goodsDesc",goodsInfo.getGoodsDesc());
					map.put("goodsNumber",jsonObject.getString("goodsNumber"));
					goodsInfoListMap.add(map);
				}else{
					return this.toFailTipJson("查询商品信息有误");
				}
			}
		}
		return this.toResultJsonSuccess(goodsInfoListMap);
	}
	
	
	/**
	 * APP查询附近设备
	 * @param dataMap
	 * @return
	 */
	public String getDeviceInfo(Map dataMap){
		List<Map<String, Object>> deviceInfoListMap = new ArrayList<Map<String, Object>>();
		String pageSize = Tools.trim(dataMap.get("pageSize"));//每页显示条数
		String currentPage = Tools.trim(dataMap.get("currentPage"));//当前页	
		String appLng = Tools.trim(dataMap.get("appLng"));//App地址经度
  		String appLat = Tools.trim(dataMap.get("appLat"));//App地址纬度
  		
  		PageData pageData = this.getPageData(pageSize, currentPage);
  		Map<String, Object> params = new HashMap<String, Object>();
		DTablePageModel dtPageModel = deviceHandler.queryDeviceInfo(params, pageData);
		
		if(dtPageModel!=null){
			List<Object[]> list =dtPageModel.getAaData();
			for (int i = 0; i < list.size(); i++) {
				Object[] obj =list.get(i);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("deviceName", obj[2]);
				map.put("deviceDistance", LngLatUtil.getDistance(appLng, appLat,  obj[12].toString(), obj[13].toString()));
				map.put("deviceAddress", obj[4]);
				deviceInfoListMap.add(map);
			}
 		}else{
			return this.toFailTipJson("查询设备有误");
		}
		
		return this.toResultJsonSuccess(deviceInfoListMap);
	}
}
