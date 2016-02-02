package com.smart.om.dao.pay;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.dao.base.BasePo;
import com.smart.om.persist.PayDeviceCash;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/17.
 */
public class PayCashDAO extends BaseDao {
    /**
     * 分页查询支付信息
     **/
    public DTablePageModel queryPayCashPage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" select pc.cashId,pc.deviceNo,pc.cashNumber,pc.lastCashTime,pc.thisCashTime,su.realName,pc.postingTime,pc.cashStatus,do.orderNo,pc.shouldCashNumber ");
        hql.append(" from PayDeviceCash pc,SysUser su,DispatchingOrder do where 1 = 1");
        hql.append(" and pc.postingUser = su.userId and pc.orderId = do.orderId ");
        hql.append(" and pc.cashNumber > 0 ");
        hqlCount.append("select count(*) from PayDeviceCash pc,SysUser su,DispatchingOrder do where 1 = 1 ")
        .append(" and pc.postingUser = su.userId and pc.orderId = do.orderId ")
        .append(" and pc.cashNumber > 0 ");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (pc.deviceNo like '%").append(keyword).append("%'")
                            .append(" or su.realName like '%").append(keyword).append("%'")
                            .append(" or do.orderNo like '%").append(keyword).append("%')");
                    hqlCount.append(" and (pc.deviceNo like '%").append(keyword).append("%'")
                            .append(" or su.realName like '%").append(keyword).append("%'")
                            .append(" or do.orderNo like '%").append(keyword).append("%')");

                }
            }
            if (params.containsKey("startDate")) {//开始时间
                String startDate = (String) params.get("startDate");
                if (StringUtils.isNotBlank(startDate)) {
                    hql.append(" and pc.thisCashTime >= '").append(startDate).append("' ");
                    hqlCount.append(" and pc.thisCashTime >= '").append(startDate).append("' ");
                }
            }
            if (params.containsKey("endDate")) {//结束时间
                String endDate = (String) params.get("endDate");
                if (StringUtils.isNotBlank(endDate)) {
                    hql.append(" and pc.thisCashTime <= '").append(endDate).append("' ");
                    hqlCount.append(" and pc.thisCashTime <= '").append(endDate).append("' ");
                }
            }
        }
        hql.append(" order by pc.cashId");

        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    /**
     * 现金管理详情
     */
    public List queryPayCashById(Integer id) {
        StringBuffer hql = new StringBuffer();
        hql.append("select di.deviceNo,pc.cashNumber,pc.lastCashTime,pc.thisCashTime,su.userName,pc.postingTime,pc.cashStatus,do.orderNo,pc.cashId from PayDeviceCash pc,DeviceInfo di,SysUser su,DispatchingOrder do where 1=1")
                .append(" and pc.deviceId=di.deviceId and su.userId=pc.postingUser and pc.orderId=do.orderId")
                .append(" and pc.cashId=").append(id);
        return find(hql.toString(), null);
    }

    /**
     * 删除现金管理条目
     */
    public BasePo deletePayCash(Integer id) {
        return delete(id);
    }
    
    /**
     * 根据设备ID和订单ID查询收取金额
     */
    public PayDeviceCash queryPayCashByDoId(Integer orderId,Integer deviceId){
    	PayDeviceCash payDeviceCash = new PayDeviceCash();
    	StringBuffer hql = new StringBuffer();
        hql.append("from PayDeviceCash where orderId ="+orderId+" and deviceId="+deviceId);
        List list = find(hql.toString(), null);
        if(list!=null){
        	payDeviceCash = (PayDeviceCash)list.get(0);
        }
    	return payDeviceCash;
    }
    
    /**
     * 根据设备ID查询收取金额
     */
    public PayDeviceCash queryPayCashByDeviceId(Integer deviceId){
    	PayDeviceCash payDeviceCash = new PayDeviceCash();
    	StringBuffer hql = new StringBuffer();
        hql.append("from PayDeviceCash where deviceId="+deviceId +" order by thisCashTime desc");
        List list = find(hql.toString(), null);
        if(list!=null){
        	payDeviceCash = (PayDeviceCash)list.get(0);
        }
    	return payDeviceCash;
    }

}
