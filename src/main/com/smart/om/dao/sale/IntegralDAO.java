package com.smart.om.dao.sale;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.smart.om.dao.base.BaseDao;
import com.smart.om.dto.sale.GameInfoDto;
import com.smart.om.dto.sale.IntegralDto;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import com.smart.om.util.PageModel;

public class IntegralDAO extends BaseDao{
	/** 查询游戏内容 **/
	public DTablePageModel queryIntegralPage(Map<String,Object> params,PageData pageData){
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();		
		hql.append(" select  ab.integrlBillId, ab.billType,ab.integrlNumber,ab.exchangeTime,ab.remark,sm.memberName,agi.gameName");
		hql.append(" from SysMember sm,ActivityIntegrlBill ab,ActivityGameInfo agi  where 1 = 1 ");
		hql.append(" and ab.memberId = sm.memberId and ab.activityId = agi.gameId ");
		hqlCount.append("select count(ab.integrlBillId)  ");		
		hqlCount.append("from SysMember sm,ActivityIntegrlBill ab,ActivityGameInfo agi  where 1 = 1 ");
		hqlCount.append(" and ab.memberId = sm.memberId and ab.activityId = agi.gameId ");
		if(params != null) {
			if(params.containsKey("keyword2")){
				String keyword2 = (String) params.get("keyword2");
				if(StringUtils.isNotBlank(keyword2)) {
					hql.append(" and (model1.advertTitle like '%").append(keyword2).append("%'")
					.append(" or model3.auName like '%").append(keyword2).append("%') ");
					hqlCount.append(" and (model1.advertTitle like '%").append(keyword2).append("%'")
					.append(" or model3.auName like '%").append(keyword2).append("%') ");
				}
			}
			if(params.containsKey("keyword")){
				String keyword = (String) params.get("keyword");
				if(StringUtils.isNotBlank(keyword)){
					hql.append(" and agi.dictOrgId = ").append(keyword);
					hqlCount.append(" and agi.dictOrgId = ").append(keyword);
				}
			}
		}
		hql.append(" order by ab.integrlBillId desc  ");

		DTablePageModel pageModel = new DTablePageModel();
		pageModel.setsEcho(pageData.getsEcho());
		PageModel pm = super.queryPageByHqlMySQl(hql.toString(), hqlCount.toString(), null, pageData.getCurrentPage(), pageData.getPageSize());
		if(pm != null) {
			pageModel.setiTotalDisplayRecords(pm.getTotal());
			pageModel.setiTotalRecords(pm.getTotal());
			@SuppressWarnings("unchecked")
			List<Object[]> rowLst = pm.getDatas();
			if(rowLst != null) {
				List<IntegralDto> didLst = new ArrayList<IntegralDto>();
				for(Object[] row : rowLst) {
					IntegralDto aid = new IntegralDto();
					aid.setIntegrlBillId((Integer)row[0]);
					aid.setBillType((String)row[1]);
					aid.setIntegrlNumber((Integer)row[2]);
					aid.setExchangeTime((String)row[3]);
					aid.setRemark((String)row[4]);
					aid.setMemberName((String)row[5]);
					aid.setGameName((String)row[6]);
					didLst.add(aid);
				}
				pageModel.setAaData(didLst);
			}
		}
		
		return pageModel;
	}
}
