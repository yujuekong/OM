package com.smart.om.dao.inventory;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.dao.sys.SysDictDAO;
import com.smart.om.persist.StorageWarehousingEntry;
import com.smart.om.persist.SysDict;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.util.PageData;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/16.
 */
public class WarehousingEntryDAO extends BaseDao {
    @Autowired
    private SysDictDAO sysDictDAO;

    /**
     * 查询入库清单分页
     */
    public DTablePageModel queryWarehousePage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }

//        String orgId = (String) params.get("orgId");
//        StringBuffer findHql = new StringBuffer();
//        findHql.append(" select userId from SysUser where dictServStation=" + dictServStation);
//        List userList = this.find(findHql.toString(), null);

        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from StorageWarehousingEntry sh where 1 = 1 ");
        hqlCount.append("select count(*) from StorageWarehousingEntry sh where 1 = 1");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (sh.warehousingNo like '%").append(keyword).append("%'")
                            .append(" or sh.remark like '%").append(keyword).append("%') ");
                    hqlCount.append(" and (sh.warehousingNo like '%").append(keyword).append("%'")
                            .append(" or sh.remark like '%").append(keyword).append("%') ");
                }
            }
            if (params.containsKey("userId")) {//查询条件有维修用户
                String userId = (String) params.get("userId");
                if (StringUtils.isNotBlank(userId)) {
                    hql.append(" and sh.userId = ").append(userId);
                    hqlCount.append(" and sh.userId = ").append(userId);
                }
            }

            if (params.containsKey("warehousingStatus")) {//查询条件有清洗状态
                String warehousingStatus = (String) params.get("warehousingStatus");
                if (StringUtils.isNotBlank(warehousingStatus)) {
                    String orderDate = (String) params.get("orderDate");
                    //hql.append(" and dc.warehousingStatus = '").append(warehousingStatus).append("' ");
                    //hqlCount.append(" and dc.warehousingStatus = '").append(warehousingStatus).append("' ");
                    String orderDateNext = DateUtil.getAfterDayOfSpecified(orderDate);
                    if ("1".equals(warehousingStatus)) {
                        hql.append(" and sh.warehousingStatus = '").append(warehousingStatus).append("' ");
                        hqlCount.append(" and sh.warehousingStatus = '").append(warehousingStatus).append("' ");
                        hql.append(" and sh.warehousingDate < '").append(orderDateNext).append("' ");
                        hql.append(" and sh.warehousingDate >= '").append(orderDate).append("' ");
                        hqlCount.append(" and sh.warehousingDate < '").append(orderDateNext).append("' ");
                        hqlCount.append(" and sh.warehousingDate >= '").append(orderDate).append("' ");
                    } else {
                        hql.append(" and sh.warehousingStatus = '").append(warehousingStatus).append("' ");
                        hqlCount.append(" and sh.warehousingStatus = '").append(warehousingStatus).append("' ");
                        //hql.append(" and sh.requestDate >= '").append(orderDate).append("' ");
                        hql.append(" and sh.requestDate < '").append(orderDateNext).append("' ");
                        //hqlCount.append(" and sh.requestDate >= '").append(orderDate).append("' ");
                        hqlCount.append(" and sh.requestDate < '").append(orderDateNext).append("' ");
                    }
                } else {
                    if (params.containsKey("orderDate")) {//查询条件有清洗下单日期
                        String orderDate = (String) params.get("orderDate");
                        if (StringUtils.isNotBlank(orderDate)) {
                            String orderDateNext = DateUtil.getAfterDayOfSpecified(orderDate);
                            hql.append(" and (sh.warehousingStatus='0' and sh.requestDate<'" + orderDateNext + "' or  sh.warehousingStatus='1' and sh.warehousingDate<'" + orderDateNext + "' and sh.warehousingDate>='" + orderDate + "')");
                            hqlCount.append(" and (sh.warehousingStatus='0' and sh.requestDate<'" + orderDateNext + "' or  sh.warehousingStatus='1' and sh.warehousingDate<'" + orderDateNext + "' and sh.warehousingDate>='" + orderDate + "')");
                        }
                    }
                }
            }
            if (params.containsKey("startDate")) {//查询条件有维修状态
                String startDate = (String) params.get("startDate");
                if (StringUtils.isNotBlank(startDate)) {
                    hql.append(" and sh.createDate >= '").append(startDate).append("' ");
                    hqlCount.append(" and sh.createDate >= '").append(startDate).append("' ");
                }
            }
            if (params.containsKey("endDate")) {//查询条件有维修状态
                String endDate = (String) params.get("endDate");
                if (StringUtils.isNotBlank(endDate)) {
                    hql.append(" and sh.createDate < '").append(endDate).append("' ");
                    hqlCount.append(" and sh.createDate < '").append(endDate).append("' ");
                }
            }
            if (params.containsKey("dictRid")) {//地区ID
                String dictRid = params.get("dictRid").toString();
                if (StringUtils.isNotBlank(dictRid)) {
                    hql.append(" and sh.warehouseId in ( select warehouseId  from StorageWarehouse where dictRegionId ='").append(dictRid).append("') ");
                    hqlCount.append(" and sh.warehouseId in ( select warehouseId  from StorageWarehouse where dictRegionId ='").append(dictRid).append("') ");
                }
            }

        }

        if (params.containsKey("orgId")) {
            String orgId = params.get("orgId").toString();
            StringBuffer findHql = new StringBuffer();
            findHql.append("select warehouseId from StorageWarehouse where dictOrgId=" + orgId);
            List list = this.find(findHql.toString(), null);
            if (list != null) {
                String listStr = list.toString().replaceAll("[\\[\\]]", "");
                hql.append(" and sh.warehouseId in (" + listStr + ")");
                hqlCount.append(" and sh.warehouseId in (" + listStr + ")");
            } else {
                hql.append(" and sh.warehouseId = null");
                hqlCount.append(" and sh.warehouseId = null");
            }
        }

        if ("app".equals(params.get("app"))) {
            hql.append(" order by  case when sh.requestDate>sh.warehousingDate then 1 else 0 end,sh.warehousingStatus,sh.requestDate  asc  ");
        } else {
            hql.append(" order by sh.warehousingDate desc,sh.warehousingEntryId");
        }

        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    /**
     * 查询入库业务类别
     */
    public List<SysDict> getAllBizType(String str) {
        List<SysDict> sysDictList;
        SysDict sysDict = sysDictDAO.queryDictByPcode(str);
        sysDictList = sysDictDAO.querySubDictByPcode(sysDict.getDictCode());
        return sysDictList;
    }

    /**
     * 查询个人入库任务
     **/
    public List<StorageWarehousingEntry> queryWarehousingList(Integer userId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" from StorageWarehousingEntry where warehousingStatus='0' and  warehouseId in "
                + "(select warehouseId from StorageWarehouse where dictOrgId =(select orgId from  SysUser where userId =" + userId + ")) and requestDate <'"+DateUtil.getAfterDayOfSpecified(DateUtil.getDateY_M_D())+"'");
        return this.find(hql.toString(), null);
    }
}
