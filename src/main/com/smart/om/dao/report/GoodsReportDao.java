package com.smart.om.dao.report;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品销售分析DAO
 * Created by hxt on 2016/1/19.
 */
public class GoodsReportDao extends BaseDao {
    /**
     * 查询商品销售统计
     **/
    public DTablePageModel queryGoodsReportPage(Map<String, Object> params, PageData pageData) {
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append("select new Map(ss.year as year,ss.month as month,sum(ss.saleMoney) as saleMoney,sd.dictName as dictName) " +
                "from StatisticalStatement ss,SysDict sd where ss.dictOrgId=sd.dictId");
        hqlCount.append("select count(*) from StatisticalStatement ss where ss.id in(select min(sm.id)  from StatisticalStatement sm where 1=1");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = params.get("keyword").toString();
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (ss.gameName like '%").append(keyword).append("%') ");
                    hqlCount.append(" and (ss.gameName like '%").append(keyword).append("%') ");
                }
            }
            if (params.containsKey("year")) {
                String year = params.get("year").toString();
                if (StringUtils.isNotBlank(year)) {
                    hql.append(" and ss.year = '").append(year).append("'");
                    hqlCount.append(" and ss.year = '").append(year).append("'");
                }
            }
            if (params.containsKey("month")) {
                String month = params.get("month").toString();
                if (StringUtils.isNotBlank(month)) {
                    hql.append(" and ss.month = '").append(month).append("'");
                    hqlCount.append(" and ss.month = '").append(month).append("'");
                }
            }
            if (params.containsKey("dictOrgId")) {
                String dictOrgId = params.get("dictOrgId").toString();
                if (StringUtils.isNotBlank(dictOrgId)) {
                    hql.append(" and ss.dictOrgId = '").append(dictOrgId).append("'");
                    hqlCount.append(" and ss.dictOrgId = '").append(dictOrgId).append("'");
                }
            }
        }
        hql.append(" group by ss.year, ss.month,ss.dictOrgId,sd.dictName");
        hql.append(" order by ss.year desc,ss.month desc,ss.dictOrgId");
        hqlCount.append(" group by sm.year,sm.month,sm.dictOrgId)");
        return super.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    public Map initSelect() {
        Map map = new HashMap();
        StringBuffer yearHaql = new StringBuffer();
        yearHaql.append("select ss.year from StatisticalStatement ss group by year");
        List yearlist = this.find(yearHaql.toString(), null);
        StringBuffer orgHql = new StringBuffer();
        orgHql.append("select new Map(ss.dictOrgId as dictOrgId,sd.dictName as dictName) " +
                "from StatisticalStatement ss,SysDict sd " +
                "where ss.dictOrgId=sd.dictId group by ss.dictOrgId,sd.dictName");
        List orgList = this.find(orgHql.toString(), null);
        map.put("years", yearlist);
        map.put("org", orgList);
        return map;
    }

    /**
     * 报表查询
     *
     * @param params
     */
    public List getGoodsReportChart(Map<String, Object> params) {
        List list = new ArrayList();
        StringBuffer hql = new StringBuffer();
        String yearStr = params.get("year").toString();
        String monthStr = params.get("month").toString();
        String orgIdStr = params.get("orgId").toString();
        if (StringUtils.isNotBlank(yearStr) && StringUtils.isNotBlank(monthStr)) {
            hql.append("select new Map(sd.dictName as dictName,sum(ss.saleMoney) as sumMoney) " +
                    "from StatisticalStatement ss,SysDict sd " +
                    "where ss.dictOrgId=sd.dictId and ss.saleMoney is not null and ss.year=" + yearStr + " and ss.month=" + monthStr + " " +
                    "group by sd.dictName");

        }
        if (StringUtils.isNotBlank(yearStr) && StringUtils.isNotBlank(orgIdStr)) {
            hql.append("select new Map(ss.month as month,sd.dictName as dictName,sum(ss.saleMoney) as sumMoney) " +
                    "from StatisticalStatement ss,SysDict sd " +
                    "where ss.dictOrgId=sd.dictId and ss.saleMoney is not null and ss.year=" + yearStr + " and ss.dictOrgId=" + orgIdStr + " " +
                    "group by sd.dictName,ss.month");

        }
        list = this.find(hql.toString(), null);
        return list;
    }


}
