package com.smart.om.dao.sys;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.persist.*;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 系统会员DAO
 *
 * @author langyuk
 */
public class SysMemberDAO extends BaseDao {

    /**
     * 分页查询用户操作
     *
     * @param params   查询条件
     * @param pageData 分页信息
     * @return
     */
    public DTablePageModel querySysMemberPage(Map<String, Object> params, PageData pageData) {
        // 构造查询的HQL
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from SysMember as model where 1=1  ");
        hqlCount.append(" select count(model.memberId) from SysMember as model where 1=1  ");
        if (params != null) {
            if (params.containsKey("keyword")) {
                String keyword = (String) params.get("keyword");
                if (StringUtils.isNotBlank(keyword)) {
                    hql.append(" and (model.memberSex like '%").append(keyword).append("%'")
                            .append(" or model.memberTel like '%").append(keyword).append("%') ");
                    hqlCount.append(" and (model.memberSex like '%").append(keyword).append("%'")
                            .append(" or model.memberTel like '%").append(keyword).append("%') ");
                }
            }
            if (params.containsKey("memberNo")) {
                String memberNo = (String) params.get("memberNo");
                if (!"".equals(memberNo) && null != memberNo) {
                    hql.append(" and model.memberNo like '%").append(memberNo).append("%' ");
                    hqlCount.append(" and model.memberNo like '%").append(memberNo).append("%' ");
                }
            }
        }
        hql.append(" order by model.regDate desc,model.memberId");
        return this.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);
    }

    /**
     * 会员编号取最大一个
     */
    public List querySysMemberNo() {
        // 构造查询的HQL
        String hql = "select MAX(memberNo) as memberNO from SysMember as model";
        return this.find(hql, null);
    }

    /**
     * 根据会员编号查询会员信息
     */
    public SysMember queryContractByMemberNo(String memberNo) {
        SysMember sysMember = null;
        String hql = " from SysMember where memberNO='" + memberNo + "'";
        List list = this.find(hql, null);
        if (list != null && !list.isEmpty()) {
            sysMember = (SysMember) list.get(0);
        }
        return sysMember;
    }

    /**
     * 根据会员手机号查询会员信息
     */
    public SysMember queryContractByMemberTel(String memberTel) {
        SysMember sysMember = null;
        String hql = " from SysMember where memberTel='" + memberTel + "'";
        List list = this.find(hql, null);
        if (list != null && !list.isEmpty()) {
            sysMember = (SysMember) list.get(0);
        }
        return sysMember;
    }

    /**
     * App登录
     */
    public SysMember memberLogin(String memberTel, String memberPassword) {
        SysMember sysMember = null;
        String hql = " from SysMember where memberTel='" + memberTel + "' and memberPassword='" + memberPassword + "'";
        List list = this.find(hql, null);
        if (list != null && !list.isEmpty()) {
            sysMember = (SysMember) list.get(0);
        }
        return sysMember;
    }

    /**
     * 会员验证
     */
    public SysMember memberVerify(String memberTel) {
        SysMember sysMember = null;
        String hql = " from SysMember where memberTel='" + memberTel + "'";
        List list = this.find(hql, null);
        if (list != null && !list.isEmpty()) {
            sysMember = (SysMember) list.get(0);
        }
        return sysMember;
    }

    /**
     * 会员兑换码
     */
    public DTablePageModel memberCdkeyPage(Map<String, Object> params, PageData pageData) {
        // 构造查询的HQL
        StringBuffer hql = new StringBuffer();
        StringBuffer hqlCount = new StringBuffer();
        hql.append(" from MemberCdkey  mbck where 1=1  ");
        hqlCount.append(" select count(mbck.cdkeyId) from MemberCdkey  mbck where 1=1  ");
        if (params != null) {
            if (params.containsKey("memberId")) {
                String memberId = (String) params.get("memberId");
                if (!"".equals(memberId) && null != memberId) {
                    hql.append(" and mbck.memberId =").append(memberId);
                    hqlCount.append(" and mbck.memberId =").append(memberId);
                }
            }
        }
        //hql.append(" and mbck.cdkeyStatus !='1'");
        //hqlCount.append(" and mbck.cdkeyStatus !='1'");
        hql.append(" order by mbck.createDate desc  ");
        return this.getDTablePageModel(hql.toString(), hqlCount.toString(), pageData);

    }

    /**
     * APP操作信息List
     */
    public List<AppOp> AppOpList(int memberId) {
        String hql = " from AppOp where memberId=" + memberId + " order by appOpDate desc";
        return this.find(hql, null);
    }

    /**
     * APP会员地址
     */
    public List<AppMemberAddress> AppMemberAddressList(int memberId) {
        String hql = " from AppMemberAddress where memberId=" + memberId + " order by memberAddressId";
        return this.find(hql, null);
    }

    /**
     * APP会员地址默认
     */
    public int updateAddressTolerant(Map<String, Object> params) {
        String hql = "update from AppMemberAddress set isMr = '0'  where memberId=" + params.get("memberId");
        this.updateHql(hql, new Object[]{});
        hql = "update from AppMemberAddress set isMr = '1'  where memberAddressId =  " + params.get("memberAddressId") + " and memberId=" + params.get("memberId");
        return this.updateHql(hql, new Object[]{});
    }

	/**
	 * APP版本信息
	 */
	public AppVersionsInfo AppVersionsInfoSelType(String versionstype){
		String hql = "from AppVersionsInfo where versionsType='"+versionstype+"'";
		List list  = this.find(hql, null);
		AppVersionsInfo appVersionsInfo = null;
		if(list!=null){
			appVersionsInfo = (AppVersionsInfo)list.get(0);
		}
		return appVersionsInfo;
	}

	/**
	 * APP商品查询库存
	 */
	public GoodsInfo AppGoodsInventory(Integer goodsId,Integer goodsNumber){
		String hql = "from GoodsInfo where goodsId = (select goodsId  from StorageInventory where goodsId="+goodsId+"   group by goodsId having SUM(inventoryNumber) < "+goodsNumber+")";
		List list  = this.find(hql, null);
		GoodsInfo goodsInfo = null;
		if(list!=null){
			goodsInfo = (GoodsInfo)list.get(0);
		}
		return goodsInfo;
	}
}
