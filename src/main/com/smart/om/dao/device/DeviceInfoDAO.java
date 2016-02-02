package com.smart.om.dao.device;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.CarLineNode;
import com.smart.om.persist.DeviceInfo;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 设备信息维护DAO
 *
 * @author lc
 */
public class DeviceInfoDAO extends BaseDao {

    /**
     * 查询设备信息
     **/
    public DTablePageModel queryDeviceInfo(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" select new Map(di.deviceId as deviceId,di.deviceNo as deviceNo,di.deviceName as deviceName,dt.deviceTypeName as deviceTypeName,di.deviceAddress as deviceAddress,di.createDate as createDate,di.deviceStatus as deviceStatus,md.districtName as districtName,md.districtId as districtId,di.dictOrgId as dictOrgId,di.dictProviceId as dictProviceId,di.dictRegionId as dictRegionId,di.deviceLng as deviceLng,di.deviceLat as deviceLat)");
        hql.append(" from  DeviceInfo di,DeviceType dt,MotionDistrict md,SysDict sd1,SysDict sd2,SysDict sd3 ");
        hql.append(" where di.deviceTypeId = dt.deviceTypeId  and di.dictOrgId = sd1.dictId and di.dictOrgId = sd2.dictId and di.dictOrgId = sd3.dictId and md.districtId = di.districtId");
        hql.append(" and di.isDel = '" + Const.IS_DEL_FALSE + "' ");
        hqlCount.append("select count(*) from DeviceInfo di where  1 = 1 ");
        hqlCount.append(" and  di.isDel = '" + Const.IS_DEL_FALSE + "' ");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and ( di.deviceNo like '%").append(keyword).append("%'")
                            .append(" or di.deviceName like '%").append(keyword).append("%'")
                            .append(" or di.deviceAddress like '%").append(keyword).append("%' )");
                    hqlCount.append(" and (di.deviceNo like '%").append(keyword).append("%'")
                            .append(" or di.deviceName like '%").append(keyword).append("%'")
                            .append(" or di.deviceAddress like '%").append(keyword).append("%' )");
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
                if (StringUtils.isNotBlank(dictOrgId)) {
                    hql.append(" and di.dictOrgId =").append(Integer.valueOf(dictOrgId));
                    hqlCount.append(" and di.dictOrgId =").append(Integer.valueOf(dictOrgId));
                }
            }
            if (params.containsKey("deviceStatus")) {
                String deviceStatus = (String) params.get("deviceStatus");
                if (StringUtils.isNotBlank(deviceStatus)) {
                    hql.append(" and di.deviceStatus ='").append(deviceStatus).append("'");
                    hqlCount.append(" and di.deviceStatus ='").append(deviceStatus).append("'");
                }
            }
            if (params.containsKey("orgId")) {
                Integer orgId = (Integer) params.get("orgId");
                if (orgId != null) {
                    hql.append(" and di.dictOrgId =").append(Integer.valueOf(orgId));
                    hqlCount.append(" and di.dictOrgId =").append(Integer.valueOf(orgId));
                }
            }
            if (params.containsKey("goodsFilter")) {
                hql.append(" and di.deviceId not in(select deviceId from DeviceGoods group by deviceId)");
                hqlCount.append(" and di.deviceId not in(select deviceId from DeviceGoods group by deviceId)");
            }

        }
        hql.append(" order by di.deviceId desc ");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    /**
     * 查询所有设备信息
     *
     * @author lC
     */
    public List<DeviceInfo> queryAllDevice() {
    	List<DeviceInfo> dTList = new ArrayList<DeviceInfo>();
    	String hql = "from DeviceInfo di where di.isDel = '0' ";
    	dTList = this.find(hql, null);
    	if(dTList != null && dTList.size() > 0){
    		return dTList;
    	}
        return null;
    }

    /**
     * 查询当前用户所在分公司的所有设备信息
     *
     * @author lC
     */
    public List<DeviceInfo> queryAllDeviceForMap(Integer orgId) {
        List<DeviceInfo> dTList = new ArrayList<DeviceInfo>();
        String hql = " from DeviceInfo di where  di.dictOrgId ='" + orgId + "' and isDel = '" + Const.IS_DEL_FALSE + "' ";
        dTList = this.find(hql, null);
        return dTList;
    }

    /**
     * 批量删除设备信息
     *
     * @param deviceTypeId
     * @return
     */
    public int delDeviceInfoByType(Integer deviceTypeId) {
        String hql = "delete from DeviceInfo where deviceTypeId = ? ";
        return this.updateHql(hql, new Object[]{deviceTypeId});
    }

    /**
     * 根据设备所在商圈查询 设备所在站点
     **/
    public List<CarLineNode> queryDistrict(Integer districtId) {
        List<CarLineNode> clNode = new ArrayList<CarLineNode>();
        String hql = " from CarLineNode cln where 1 = 1 and isDel = '" + Const.IS_DEL_FALSE + "' and nodePositionId = " + districtId;
        clNode = this.find(hql, null);
        return clNode;
    }

    public List<DeviceInfo> queryDeviceByNo(String deviceNo) {
        String hql = " from DeviceInfo  where deviceNo='" + deviceNo + "'";
        return this.find(hql, null);
    }

    /**
     * 生成刀片更新任务
     **/
    public List<DeviceInfo> queryDeviceByPeriod(String date) {
        String hql = " from DeviceInfo  where bladeChangeTime<='" + date + "'";
        return this.find(hql, null);
    }

    /**
     * 查询系统用户信息
     **/
    public DTablePageModel choosePerson(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append("from SysUser su where 1 = 1");
        hqlCount.append("select count(*) from SysUser su where 1 = 1");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and ( su.userName like '%").append(keyword).append("%'")
                            .append(" or su.realName like '%").append(keyword).append("%'")
                            .append(" or su.tel like '%").append(keyword).append("%' )");
                    hqlCount.append(" and (su.userName like '%").append(keyword).append("%'")
                            .append(" or su.realName like '%").append(keyword).append("%'")
                            .append(" or su.tel like '%").append(keyword).append("%' )");
                }
                if (params.containsKey("dictRegionId")) {
                    String dictRegionId = params.get("dictRegionId").toString();
                    if (StringUtils.isNotBlank(dictRegionId)) {
                        hql.append(" and su.dictRegionId =").append(Integer.valueOf(dictRegionId));
                        hqlCount.append(" and su.dictRegionId =").append(Integer.valueOf(dictRegionId));
                    }
                }
                if (params.containsKey("dictProviceId")) {
                    String dictProviceId = params.get("dictProviceId").toString();
                    if (StringUtils.isNotBlank(dictProviceId)) {
                        hql.append(" and su.dictProviceId =").append(Integer.valueOf(dictProviceId));
                        hqlCount.append(" and su.dictProviceId =").append(Integer.valueOf(dictProviceId));
                    }
                }
                if (params.containsKey("dictOrgId")) {
                    String dictOrgId = params.get("dictOrgId").toString();
                    if (StringUtils.isNotBlank(dictOrgId)) {
                        hql.append(" and su.orgId =").append(Integer.valueOf(dictOrgId));
                        hqlCount.append(" and su.orgId =").append(Integer.valueOf(dictOrgId));
                    }
                }
                if (params.containsKey("orgId")) {
                    String orgId = params.get("orgId").toString();
                    if (StringUtils.isNotBlank(orgId)) {
                        hql.append(" and su.orgId =").append(Integer.valueOf(orgId));
                        hqlCount.append(" and su.orgId =").append(Integer.valueOf(orgId));
                    }
                }
            }

        }
        hql.append(" order by su.userId desc ");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }
}
