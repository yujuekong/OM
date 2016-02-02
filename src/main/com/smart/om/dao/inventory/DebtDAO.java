package com.smart.om.dao.inventory;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.dao.sys.SysDictDAO;
import com.smart.om.persist.SysDict;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 报损DAO
 *
 * @author langyuk
 */
public class DebtDAO extends BaseDao {
    @Autowired
    private SysDictDAO sysDictDAO;

    /**
     * 查询报损单DAO
     **/
    public DTablePageModel queryStorageDebtPage(Map<String, Object> params, PageData pageData) {
        if (pageData == null) {
            return null;
        }
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from StorageDebt sd where 1 = 1 ");
        hqlCount.append("select count(*) from StorageDebt sd where 1 = 1");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (sd.debtNo like '%").append(keyword).append("%'")
                            .append(" or sd.debtDate like '%").append(keyword).append("%')");
                    hqlCount.append(" and (sd.debtNo like '%").append(keyword).append("%'")
                            .append(" or sd.debtDate like '%").append(keyword).append("%')");
                }
            }
            if (params.containsKey("startDate")) {//查询条件有维修状态
                String startDate = (String) params.get("startDate");
                if (StringUtils.isNotBlank(startDate)) {
                    hql.append(" and sd.debtDate >= '").append(startDate).append("' ");
                    hqlCount.append(" and sd.debtDate >= '").append(startDate).append("' ");
                }
            }
            if (params.containsKey("endDate")) {//查询条件有维修状态
                String endDate = (String) params.get("endDate");
                if (StringUtils.isNotBlank(endDate)) {
                    hql.append(" and sd.debtDate < '").append(endDate).append("' ");
                    hqlCount.append(" and sd.debtDate < '").append(endDate).append("' ");
                }
            }
            if (params.containsKey("orgId")) {
                String orgId = (String) params.get("orgId");
                StringBuffer findHql = new StringBuffer();
                findHql.append("select userId from SysUser where orgId=" + orgId);
                List list = this.find(findHql.toString(), null);
                String listStr = list.toString().replaceAll("[\\[\\]]", "");
                hql.append(" and sd.debtUser in (" + listStr + ")");
                hqlCount.append(" and sd.debtUser in (" + listStr + ")");
            }
        }
        hql.append(" order by sd.debtDate desc,sd.debtId");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    /**
     * 查询报损类型
     */
    public List<SysDict> getAllDebtType(String str) {
        List<SysDict> sysDictList;
        SysDict sysDict = sysDictDAO.queryDictByPcode(str);
        sysDictList = sysDictDAO.querySubDictByPcode(sysDict.getDictCode());
        return sysDictList;
    }

}
