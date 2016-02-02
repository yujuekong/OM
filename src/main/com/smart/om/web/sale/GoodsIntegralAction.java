package com.smart.om.web.sale;

/**
 * Created by hxt on 2016/1/13.
 */

import com.smart.om.biz.sale.SaleHandler;
import com.smart.om.persist.GoodsIntegral;
import com.smart.om.util.DTablePageModel;
import com.smart.om.web.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.JSONUtil;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Namespace("/view/sale/goodsIntegral")
public class GoodsIntegralAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(GoodsIntegralAction.class);
    @Resource
    private SaleHandler saleHandler;

    private GoodsIntegral goodsIntegral;

    /**
     * 商品积分分页
     */
    @Action(value = "queryGoodsIntegralPage")
    public void queryGoodsIntegralPage() {
        try {
            //搜索关键字
            logger.info("queryGameInfoPage");
            String keyword = this.getRequestParm().getParameter("keyword");
            Map<String, Object> params = new HashMap<String, Object>();
            DTablePageModel dtPageModel = saleHandler.queryGoodsIntegralPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定商品积分详情
     */
    @Action(value = "queryGoodsIntegralDt", results = {
            @Result(name = SUCCESS, location = "/view/sale/goodsIntegralDetail.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String queryGoodsIntegralDt() {
        String result = SUCCESS;
        try {
            String goodsIntegralIdStr = this.getRequestParm().getParameter("goodsIntegralId");
            if (StringUtils.isNotBlank(goodsIntegralIdStr)) {
                Integer goodsIntegralId = Integer.valueOf(this.getRequestParm().getParameter("goodsIntegralId"));
                GoodsIntegral goodsIntegral = (GoodsIntegral) saleHandler.queryGoodsIntegralDt(goodsIntegralId);
                getRequest().put("goodsIntegral", goodsIntegral);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 新增和修改商品积分页面跳转
     */
    @Action(value = "preAddOrModifyIntegral", results = {
            @Result(name = SUCCESS, location = "/view/sale/goodsIntegralAddOrUpdate.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")

    })
    public String preAddOrModifyIntegral() {
        String result = SUCCESS;
        try {
            String goodsIntegralIdStr = this.getRequestParm().getParameter("goodsIntegralId");
            if (StringUtils.isNotBlank(goodsIntegralIdStr)) {
                Integer goodsIntegralId = Integer.valueOf(this.getRequestParm().getParameter("goodsIntegralId"));
                GoodsIntegral goodsIntegral = (GoodsIntegral) saleHandler.queryGoodsIntegralDt(goodsIntegralId);
                getRequest().put("goodsIntegral", goodsIntegral);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 新增和修改商品积分信息
     */
    @Action(value = "AddOrModifyIntegral", results = {
            @Result(name = SUCCESS, location = "/view/sale/goodsIntegral.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String AddOrModifyIntegral() {
        String result = SUCCESS;
        try {
            Integer goodsIntegralId = goodsIntegral.getGoodsIntegralId();
            if (goodsIntegralId == null) {
                goodsIntegral.setNewer(true);
            }
            saleHandler.AddOrModifyIntegral(goodsIntegral);
        } catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 删除商品积分
     */
    @Action(value = "deleteGoodsIntegral", results = {
            @Result(name = SUCCESS, location = "/view/sale/goodsIntegral.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String deleteGoodsIntegral() {
        String result = SUCCESS;
        try {
            Integer goodsIntegralId = Integer.valueOf(this.getRequestParm().getParameter("goodsIntegralId"));
            saleHandler.deleteGoodsIntegral(goodsIntegralId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    public GoodsIntegral getGoodsIntegral() {
        return goodsIntegral;
    }

    public void setGoodsIntegral(GoodsIntegral goodsIntegral) {
        this.goodsIntegral = goodsIntegral;
    }
}
