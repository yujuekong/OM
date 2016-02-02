package com.smart.om.biz.sale.impl;

import com.smart.om.biz.sale.SaleHandler;
import com.smart.om.dao.base.BasePo;
import com.smart.om.dao.sale.*;
import com.smart.om.persist.*;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 销售业务逻辑
 *
 * @author langyuk
 */
@Component("SaleHandler")
public class SaleHandlerImpl implements SaleHandler {
    private static final Logger logger = Logger.getLogger(SaleHandlerImpl.class);
    @Autowired
    private SaleInfoDAO saleInfoDAO;//销售DAO
    @Autowired
    private GameInfoDAO gameInfoDAO;//抽奖活动DAO
    @Autowired
    private IntegralDAO integralDAO;//积分活动DAO
    @Autowired
    private GamePrizeDAO gamePrizeDAO;//活动奖励DAO

    @Autowired
    private CouponDAO couponDAO;//优惠劵DAO

    @Autowired
    private MemberCouponDAO memberCouponDAO;//优惠劵DAO

    @Autowired
    private OrderPayDAO orderPayDAO;//支付订单DAO

    @Autowired
    private GoodsIntegralDAO goodsIntegralDAO;//商品积分DAO

    /**
     * 分页查询销售信息
     */
    public DTablePageModel querySaleInfoPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = saleInfoDAO.querySaleInfoPage(params, pageData);
        }
        return pageModel;
    }

    /**
     * 删除当天数据
     */
    public void deleteSaleInfoIntraday(String startDate, String overDate) {
        saleInfoDAO.deleteSaleInfoIntraday(startDate, overDate);
    }

    /**
     * 添加销售信息
     */
    public SaleInfo saveOrUpdateSaleInfoDAO(SaleInfo saleInfo) {
        return (SaleInfo) saleInfoDAO.save(saleInfo);
    }

    /**
     * 根据订单号查询销售记录
     */
    public List<SaleInfo> querySaleInfo(String bsinessOrderNo) {
        return saleInfoDAO.querySaleInfo(bsinessOrderNo);
    }

    /**
     * 优惠劵列表分页
     */
    @Override
    public DTablePageModel queryCouponPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = couponDAO.queryCouponPage(params, pageData);
        }
        return pageModel;
    }

    /**
     * 优惠劵列表分页
     */
    @Override
    public List<ActivityCoupon> queryIsCouponPage(Map<String, Object> params) {
        return couponDAO.queryIsCouponPage(params);
    }

    /**
     * 更新优惠券数量
     */
    public int updateCouponNumber(String couponId) {
        return couponDAO.updateCouponNumber(couponId);
    }

    @Override
    public BasePo saveOrUpdateCoupon(BasePo activityCoupon) {
        return couponDAO.save(activityCoupon);
    }

    /**
     * 新增和修改优惠券关联表
     *
     * @param activityMemberCoupon
     * @return
     */
    public ActivityMemberCoupon saveOrUpdateMemberCoupon(ActivityMemberCoupon activityMemberCoupon) {
        return (ActivityMemberCoupon) couponDAO.save(activityMemberCoupon);
    }

    /**
     * 修改优惠券状态
     *
     * @param memberId 会员ID
     * @param couponId 优惠劵ID
     * @return
     */
    public int updateMemberCouponIsUser(String memberId, String couponId) {
        return couponDAO.updateMemberCouponIsUser(memberId, couponId);
    }

    @Override
    public BasePo queryCouponById(Integer couponId) {
        return couponDAO.find(couponId);
    }

    @Override
    public BasePo deleteCoupon(Integer couponId) {
        return couponDAO.delete(couponId);
    }

    /**
     * 会员优惠劵分页
     */
    @Override
    public DTablePageModel queryMemberCouponPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = memberCouponDAO.queryMemberCouponPage(params, pageData);
        }
        return pageModel;
    }

    @Override
    public List<ActivityMemberCoupon> queryMemberCouponById(Integer memberId) {
        return memberCouponDAO.queryMemberCouponById(memberId);
    }

    @Override
    public int deleteMemberCouponById(Integer memberId) {
        return memberCouponDAO.deleteMemberCouponById(memberId);
    }

    /**
     * 查询抽奖活动的分页信息
     */
    public DTablePageModel queryGameInfoPage(Map<String, Object> params,
                                             PageData pageData) {
        return gameInfoDAO.queryGameInfoPage(params, pageData);
    }

    /**
     * 根据id查询活动信息
     */
    public ActivityGameInfo queryGameInfoById(Integer id) {
        return (ActivityGameInfo) gameInfoDAO.find(id);
    }

    /**
     * 根据id查询活动奖励信息
     */
    public ActivityGamePrize queryGamePrizeById(Integer id) {
        return (ActivityGamePrize) gamePrizeDAO.find(id);
    }

    /**
     * 根据id删除活动信息
     */
    public ActivityGameInfo deleteGameInfoById(Integer id) {
        return (ActivityGameInfo) gameInfoDAO.delete(id);
    }

    /**
     * 保存活动信息
     */
    public ActivityGameInfo saveOrUpdateGameInfo(
            ActivityGameInfo activityGameInfo) {
        return (ActivityGameInfo) gameInfoDAO.saveOrUpdate(activityGameInfo);
    }

    /**
     * 删除活动奖励信息
     */
    public ActivityGamePrize deleteGamePrize(ActivityGamePrize activityGamePrize) {
        return (ActivityGamePrize) gamePrizeDAO.delete(activityGamePrize);
    }

    /**
     * 查询积分活动分页
     */
    public DTablePageModel queryIntegralPage(Map<String, Object> params,
                                             PageData pageData) {
        return integralDAO.queryIntegralPage(params, pageData);
    }

    /**
     * 保存积分活动
     */
    public ActivityIntegrlBill saveOrUpdateIntegral(
            ActivityIntegrlBill activityIntegral) {
        return (ActivityIntegrlBill) integralDAO.saveOrUpdate(activityIntegral);
    }

    /**
     * 根据ID查询积分活动信息
     */
    public ActivityIntegrlBill queryIntegralBillById(Integer id) {
        return (ActivityIntegrlBill) integralDAO.find(id);
    }

    /**
     * 根据Id删除积分活动
     */
    public ActivityIntegrlBill deleteIntegralById(Integer id) {
        return (ActivityIntegrlBill) integralDAO.delete(id);
    }

    @Override
    public DTablePageModel queryOrderPayPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = orderPayDAO.queryOrderPayPage(params, pageData);
        }
        return pageModel;
    }

    /**
     * 保存支付订单信息
     *
     * @param basePo
     * @return
     */
    @Override
    public BasePo saveOrderPay(BasePo basePo) {
        return orderPayDAO.save(basePo);
    }

    @Override
    public BasePo queryOrderPayById(Integer payId) {
        return orderPayDAO.find(payId);
    }

    @Override
    public String sellTotalNumber() {
        return orderPayDAO.sellTotalNumber();
    }

    @Override
    public DTablePageModel queryGoodsIntegralPage(Map<String, Object> params, PageData pageData) {
        DTablePageModel pageModel = new DTablePageModel();
        if (pageData != null) {
            pageModel = goodsIntegralDAO.queryGoodsIntegralPage(params, pageData);
        }
        return pageModel;
    }

    @Override
    public BasePo queryGoodsIntegralDt(Integer id) {
        return goodsIntegralDAO.queryGoodsIntegralDt(id);
    }

    @Override
    public BasePo AddOrModifyIntegral(BasePo basePo) {
        return goodsIntegralDAO.save(basePo);
    }

    @Override
    public BasePo deleteGoodsIntegral(Integer id) {
        return goodsIntegralDAO.delete(id);
    }


    /**
     * 查询活动结果页面
     */
    public DTablePageModel querymemberGamePage(Map<String, Object> params,
                                               PageData pageData) {
        return gameInfoDAO.querymemberGamePage(params, pageData);
    }

    /**
     * 查询活动奖励分页信息
     **/
    public DTablePageModel queryGamePrizePage(Map<String, Object> params,
                                              PageData pageData) {
        return gameInfoDAO.queryGamePrizePage(params, pageData);
    }

    /**
     * 保存活动游戏奖励信息
     **/
    public ActivityGamePrize saveOrupdateGamePrize(
            ActivityGamePrize activityGamePrize) {
        return (ActivityGamePrize) gamePrizeDAO.saveOrUpdate(activityGamePrize);
    }

    /**
     * 根据活动ID查询活动奖励
     **/
    public List<ActivityGamePrize> queryActivityPrizeByGameId(Integer id) {
        return gamePrizeDAO.queryActivityPrizeByGameId(id);
    }

    /**
     * 商品ID查询积分
     */
    public GoodsIntegral getGoodsIntegralByGoodsId(String goodsId){
    	return goodsIntegralDAO.getGoodsIntegralByGoodsId(goodsId);
    }
}
