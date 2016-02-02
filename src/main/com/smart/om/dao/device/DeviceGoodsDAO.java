package com.smart.om.dao.device;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.DeviceGoods;
import com.smart.om.persist.GoodsInfo;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 设备存放商品
 *
 * @author langyuk
 */
public class DeviceGoodsDAO extends BaseDao {
    /**
     * 分页查询设备商品
     **/
    public DTablePageModel queryDeviceGoods(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from DeviceGoods dg where 1 = 1 ");
        hqlCount.append("select count(*) from DeviceGoods dg  where 1 = 1 ");
        if (params != null) {
            if (params.containsKey("deviceId")) {
                String deviceId = (String) params.get("deviceId");
                if (StringUtils.isNotBlank(deviceId)) {
                    hql.append(" and dg.deviceId =").append(Integer.valueOf(deviceId));
                    hqlCount.append(" and dg.deviceId =").append(Integer.valueOf(deviceId));
                }
            }
        }
        hql.append("order by dg.deviceId desc");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    /**
     * 根据设备查询设备商品
     **/
    public DTablePageModel queryDeviceGoodsByDevice(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append("select new Map(dg.deviceId as deviceId,dg.deviceNo as deviceNo,di.deviceName as deviceName,di.deviceAddress as deviceAddress,di.districtId as districtId,md.districtName as districtName) from DeviceGoods dg,DeviceInfo di,MotionDistrict md where 1 = 1 and dg.deviceId = di.deviceId and di.districtId=md.districtId");
        hqlCount.append(" select count(*) from DeviceInfo di where di.deviceId in (select deviceId  from DeviceGoods group by deviceId)");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String teamKeyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(teamKeyword)) {
                    hql.append(" and (dg.deviceNo like '%").append(teamKeyword).append("%'")
                            .append(" or di.deviceName like '%").append(teamKeyword).append("%')");
                    hqlCount.append(" and (di.deviceNo like '%").append(teamKeyword).append("%'")
                            .append(" or di.deviceName like '%").append(teamKeyword).append("%')");
                }
            }
            if (params.containsKey("dictRegionId")) {
                String dictRegionId = (String) params.get("dictRegionId");
                if (StringUtils.isNotBlank(dictRegionId)) {
                    hql.append(" and di.dictRegionId =").append(Integer.valueOf(dictRegionId));
                    hqlCount.append(" and di.dictRegionId =").append(Integer.valueOf(dictRegionId));
                }
            }
            if (params.containsKey("dictProviceId")) {
                String dictProviceId = (String) params.get("dictProviceId");
                if (StringUtils.isNotBlank(dictProviceId)) {
                    hql.append(" and di.dictProviceId =").append(Integer.valueOf(dictProviceId));
                    hqlCount.append(" and di.dictProviceId =").append(Integer.valueOf(dictProviceId));
                }
            }
            if (params.containsKey("dictOrgId")) {
                String dictOrgId = (String) params.get("dictOrgId");
                hql.append(" and di.dictOrgId=").append(Integer.valueOf(dictOrgId));
                hqlCount.append(" and di.dictOrgId=").append(Integer.valueOf(dictOrgId));

            }
            if (params.containsKey("orgId")) {
                Integer orgId = (Integer) params.get("orgId");
                if (orgId != null) {
                    hql.append(" and di.dictOrgId =").append(Integer.valueOf(orgId));
                    hqlCount.append(" and di.dictOrgId =").append(Integer.valueOf(orgId));
                }
            }
        }
        hql.append(" group by dg.deviceId, dg.deviceNo,di.deviceName,di.deviceAddress,di.districtId,md.districtName order by dg.deviceId");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    public List<DeviceGoods> queryDeviceGoodsBydeviceId(Integer deviceId) {
        List<DeviceGoods> deviceGoodsList = new ArrayList<DeviceGoods>();
        String hql = "from DeviceGoods as model1 where model1.deviceId = " + deviceId + " order by model1.deviceGoodsId";
        deviceGoodsList = this.find(hql, null);
        return deviceGoodsList;
    }
    
    /**
     * APP查询设备格子商品
     **/
	public GoodsInfo AppSelDeviceGoods(Integer deviceId,String grNo){
		GoodsInfo goodsInfo = null;
		StringBuffer hql = new StringBuffer();
		hql.append("from GoodsInfo where goodsId = (select goodsId from DeviceGoods  where deviceId = " + deviceId);
		if(!"".equals(grNo) && null!=grNo){
			hql.append(" and gridNo like 'DI-"+grNo+"%')");
		}
        List list  = this.find(hql.toString(), null);
        if(list!=null){
        	goodsInfo = (GoodsInfo)list.get(0);
        }
        return goodsInfo;
    }

    /*
     * 根据设备ID删除设备商品
     */
    public int delDeviceGoodsById(Integer deviceId) {
        String hql = "delete from DeviceGoods dg where  dg.deviceId = " + deviceId;
        return this.updateHql(hql, new Object[]{});
    }

    /*
     * 根据设备ID删除设备商品
     */
    public int delById(Integer deviceGoodsId) {
        String hql = "delete from DeviceGoods dg where  dg.deviceGoodsId = " + deviceGoodsId;
        return this.updateHql(hql, new Object[]{});
    }
}
