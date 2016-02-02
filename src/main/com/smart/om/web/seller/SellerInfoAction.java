package com.smart.om.web.seller;

import com.smart.om.biz.seller.SellerHandler;
import com.smart.om.persist.SellerGoods;
import com.smart.om.persist.SellerInfo;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.web.base.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/23.
 */
@Namespace("/view/seller/sellerInfo")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class SellerInfoAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(SellerInfoAction.class);
    @Resource
    private SellerHandler sellerHandler;
    private SellerInfo sellerInfo;

    private List<SellerGoods> sellerGoodsList;

    public SellerInfo getSellerInfo() {
        return sellerInfo;
    }

    public void setSellerInfo(SellerInfo sellerInfo) {
        this.sellerInfo = sellerInfo;
    }

    public List<SellerGoods> getSellerGoodsList() {
        return sellerGoodsList;
    }

    public void setSellerGoodsList(List<SellerGoods> sellerGoodsList) {
        this.sellerGoodsList = sellerGoodsList;
    }

    /**
     * 供应商列表分页
     */
    @Action(value = "querySellerPage")
    public void querySellerPage() {
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String id = this.getRequestParm().getParameter("id");
            String level = this.getRequestParm().getParameter("level");
            String orgId = this.getRequestParm().getParameter("orgId");
            Map<String, Object> params = new HashMap<String, Object>();
            if ("1".equals(level)) {
                params.put("dictRegionId", id);
            } else if ("2".equals(level)) {
                params.put("dictProviceId", id);
            }
            if (StringUtils.isNotBlank(orgId)) {
                params.put("orgId", orgId);
            }
            params.put("keyword", keyword);
            DTablePageModel dtPageModel = sellerHandler.querySellerPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 供应商详情
     */
    @Action(value = "querySellerInfoById", results = {
            @Result(name = SUCCESS, location = "/view/seller/sellerInfoDetail.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String querySellerInfoById() {
        String result = SUCCESS;
        try {
            Integer sellerId = Integer.valueOf(this.getRequestParm().getParameter("sellerId"));
            if (sellerId != null) {
                SellerInfo sellerInfo = (SellerInfo) sellerHandler.querySellerInfoById(sellerId);
                getRequest().put("sellerInfo", sellerInfo);
                logger.info(sellerInfo.getSellerAddress());
                result = SUCCESS;
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
     * 新增或修改供应商跳转
     */
    @Action(value = "preAddOrModifySellerInfo", results = {
            @Result(name = SUCCESS, location = "/view/seller/sellerInfoAddOrUpdata.jsp"),
    })
    public String preAddOrModifySellerInfo() {
        String result = SUCCESS;
        try {
            String sellerIdStr = this.getRequestParm().getParameter("sellerId");
            logger.info("goodsId:" + sellerIdStr);
            if (StringUtils.isNotBlank(sellerIdStr)) {
                Integer sellerId = Integer.valueOf(this.getRequestParm().getParameter("sellerId"));
                SellerInfo sellerInfo = (SellerInfo) sellerHandler.querySellerInfoById(sellerId);
                List<SellerGoods> sellerGoodsList = sellerHandler.querySellerGoodsBySellerId(sellerId);
                getRequest().put("sellerInfo", sellerInfo);
                getRequest().put("sellerGoodsList", sellerGoodsList);
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
     * 新增或修改供应商
     */
    @Action(value = "saveOrUpdateSellerInfo", results = {
            @Result(name = SUCCESS, location = "/view/seller/sellerInfoList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String saveOrUpdateSellerInfo() {
        String result = SUCCESS;
        try {
            Integer sellerId = sellerInfo.getSellerId();
            if (sellerId == null) {
                sellerInfo.setNewer(true);
                sellerInfo.setSellerStatus(Const.IS_VALID_TRUE);
                sellerInfo.setSellerNo(DateUtil.getNo("SJ"));
            }
            sellerInfo.setCreateDate(DateUtil.getDateY_M_D());
            sellerHandler.saveOrUpdateSellerInfo(sellerInfo);
            sellerId = sellerInfo.getSellerId();
            logger.info(sellerId);
            if (sellerGoodsList != null && sellerGoodsList.size() > 0) {
                sellerHandler.deleteSellerGoodsBySellerId(sellerId);
                for (SellerGoods sellerGoods : sellerGoodsList) {
                    sellerGoods.setSellerId(sellerId);
                    sellerGoods.setNewer(true);
                    sellerHandler.saveOrUpdateSellerInfo(sellerGoods);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 删除供应商
     */
    @Action(value = "deleteSellerInfo", results = {
            @Result(name = SUCCESS, location = "/view/seller/sellerInfoList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String deleteSellerInfo() {
        String result = SUCCESS;
        try {
            Integer sellerId = Integer.valueOf(this.getRequestParm().getParameter("sellerId"));
            sellerHandler.deleteSellerInfo(sellerId);
            sellerHandler.deleteSellerGoodsBySellerId(sellerId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 供应商品明细
     */
    @Action(value = "sellerGoodsDet", results = {
            @Result(name = SUCCESS, location = "/view/inventory/inDetailList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String sellerGoodsDet() {
        try {
            String sellerId = this.getRequestParm().getParameter("sellerId");
            getRequest().put("sellerId", sellerId);
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("where", "查询维护信息");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
        }
        return SUCCESS;
    }


    /**
     * 修改商品状态
     */
    @Action(value = "modifySellerStatus", results = {
            @Result(name = SUCCESS, location = "/view/seller/sellerInfoList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String modifySellerStatus() {
        String result = SUCCESS;
        try {
            Integer sellerId = Integer.valueOf(this.getRequestParm().getParameter("sellerId"));
            sellerInfo = (SellerInfo) sellerHandler.querySellerInfoById(sellerId);
            if (sellerInfo.getSellerStatus().equals("0")) {
                sellerInfo.setSellerStatus("1");
            } else {
                sellerInfo.setSellerStatus("0");
            }
            sellerHandler.saveOrUpdateSellerInfo(sellerInfo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

}
