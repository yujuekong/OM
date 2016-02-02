package com.smart.om.web.seller;

import com.smart.om.biz.seller.SellerHandler;
import com.smart.om.persist.SellerGoods;
import com.smart.om.util.DTablePageModel;
import com.smart.om.web.base.BaseAction;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/16.
 */
@Namespace("/view/seller/sellerGoods")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class SellerGoodsAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(SellerGoodsAction.class);
    @Resource
    private SellerHandler sellerHandler;
    private SellerGoods sellerGoods;


    /**
     * 供应商品列表分页
     */
    @Action(value = "querySellerGoodsPage")
    public void querySellerGoodsPage() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String sellerId = this.getRequestParm().getParameter("sellerId");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            params.put("sellerId", sellerId);
            DTablePageModel dtPageModel = sellerHandler.querySellerGoodsPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * set and get
     */
    public SellerGoods getSellerGoods() {
        return sellerGoods;
    }

    public void setSellerGoods(SellerGoods sellerGoods) {
        this.sellerGoods = sellerGoods;
    }

}
