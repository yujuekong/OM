package com.smart.om.biz.sale;

import com.smart.om.dao.base.BasePo;
import com.smart.om.persist.*;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;

import java.util.List;
import java.util.Map;

/**
 * 销售业务逻辑
 *
 * @author langyuk
 */
public interface SaleHandler {

    /**
     * 分页查询销售信息
     */
    DTablePageModel querySaleInfoPage(Map<String, Object> params, PageData pageData);

    /**
     * 删除当天数据
     */
    void deleteSaleInfoIntraday(String startDate, String overDate);

    /**
     * 添加销售信息
     **/
    SaleInfo saveOrUpdateSaleInfoDAO(SaleInfo saleInfo);

    /**
     * 根据订单号查询销售记录
     **/
    List<SaleInfo> querySaleInfo(String bsinessOrderNo);

    /**
     * 优惠劵列表分页
     */
    DTablePageModel queryCouponPage(Map<String, Object> params, PageData pageData);

    /**
     * 已领取优惠劵
     */
    List<ActivityCoupon> queryIsCouponPage(Map<String, Object> params);

    /**
     * 更新优惠券数量
     */
    int updateCouponNumber(String couponId);

    /**
     * 新增和修改优惠劵信息
     */
    BasePo saveOrUpdateCoupon(BasePo activityCoupon);

    /**
     * 新增和修改优惠券关联表
     *
     * @param activityMemberCoupon
     * @return
     */
    ActivityMemberCoupon saveOrUpdateMemberCoupon(ActivityMemberCoupon activityMemberCoupon);

    /**
     * 修改优惠券状态
     *
     * @param activityMemberCoupon
     * @return
     */
    int updateMemberCouponIsUser(String memberId, String couponId);

    /**
     * 指定优惠劵详情
     */
    BasePo queryCouponById(Integer couponId);

    /**
     * 删除优惠劵
     */
    BasePo deleteCoupon(Integer couponId);

    /************************************* 会员优惠劵 ***************************************/
    /**
     * 会员优惠劵分页
     */
    DTablePageModel queryMemberCouponPage(Map<String, Object> params, PageData pageData);

    /**
     * 查询会员拥有优惠劵列表
     *
     * @param memberId 会员ID
     * @return
     */
    List<ActivityMemberCoupon> queryMemberCouponById(Integer memberId);

    /**
     * 删除会员拥有的优惠劵
     *
     * @param memberId
     * @return
     */
    int deleteMemberCouponById(Integer memberId);

    /************************************* 抽奖活动 ***************************************/

    /**
     * 分页查询游戏信息
     */
    DTablePageModel queryGameInfoPage(Map<String, Object> params, PageData pageData);

    /**
     * 根据id查询活动信息
     **/
    ActivityGameInfo queryGameInfoById(Integer id);

    /**
     * 删除活动信息
     **/
    ActivityGameInfo deleteGameInfoById(Integer id);

    /**
     * 保存活动信息
     **/
    ActivityGameInfo saveOrUpdateGameInfo(ActivityGameInfo activityGameInfo);

    /**
     * 删除活动奖励信息
     **/
    ActivityGamePrize deleteGamePrize(ActivityGamePrize activityGamePrize);

    /**
     * 查询活动结果页面
     **/
    DTablePageModel querymemberGamePage(Map<String, Object> params, PageData pageData);

    /**
     * 查询活动奖励分页信息
     **/
    DTablePageModel queryGamePrizePage(Map<String, Object> params, PageData pageData);

    /**
     * 保存活动游戏奖励
     **/
    ActivityGamePrize saveOrupdateGamePrize(ActivityGamePrize activityGamePrize);

    /**
     * 根据Id查询活动奖励
     **/
    ActivityGamePrize queryGamePrizeById(Integer id);

    /**
     * 根据活动ID查询游戏奖励
     **/
    List<ActivityGamePrize> queryActivityPrizeByGameId(Integer id);

    /************************************* 积分活动 *************************************/

    /**
     * 分页查询积分活动信息
     */
    DTablePageModel queryIntegralPage(Map<String, Object> params, PageData pageData);

    /**
     * 保存积分活动信息
     */
    ActivityIntegrlBill saveOrUpdateIntegral(ActivityIntegrlBill activityIntegral);

    /**
     * 根据ID查询活动信息
     */
    ActivityIntegrlBill queryIntegralBillById(Integer id);

    /**
     * 根据ID删除活动信息
     **/
    ActivityIntegrlBill deleteIntegralById(Integer id);


    /**
     * 分页查询订单信息
     */
    DTablePageModel queryOrderPayPage(Map<String, Object> params, PageData pageData);


    /**
     * 保存支付订单信息
     *
     * @param basePo
     * @return basePo
     */
    BasePo saveOrderPay(BasePo basePo);

    /**
     * 查询单个支付订单
     *
     * @param payId
     * @return
     */
    BasePo queryOrderPayById(Integer payId);

    /**
     * 已售出数量统计
     *
     * @return
     */
    String sellTotalNumber();

    /************************商品积分业务**********************/
    /**
     * 分页查询商品积分
     */
    DTablePageModel queryGoodsIntegralPage(Map<String, Object> params, PageData pageData);

    /**
     * 根据ID查询商品积分
     */
    BasePo queryGoodsIntegralDt(Integer id);

    /**
     * 保存商品积分信息
     */
    BasePo AddOrModifyIntegral(BasePo basePo);

    /**
     * 根据ID删除商品积分
     **/
    BasePo deleteGoodsIntegral(Integer id);
    
    /**
     * 商品ID查询积分
     */
    GoodsIntegral getGoodsIntegralByGoodsId(String goodsId);
}