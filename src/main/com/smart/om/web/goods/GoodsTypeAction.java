package com.smart.om.web.goods;

import com.smart.om.biz.goods.GoodsHandler;
import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.persist.GoodsType;
import com.smart.om.persist.SysDict;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
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
 * 商品类型管理Action
 * Created by Administrator on 2015/9/8.
 */
@Namespace("/view/goods/goodsType")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class GoodsTypeAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(GoodsTypeAction.class);
    @Resource
    private GoodsHandler goodsHandler;
    @Resource
    private SysAssistHandler sysAssistHandler;
    private GoodsType goodsType;

    public GoodsType getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(GoodsType goodsType) {
        this.goodsType = goodsType;
    }

    /**
     * 搜索商品类型列表
     */
    @Action(value = "queryGoodsTypePage")
    public void queryGoodsTypePage() {
        SysDict sysDict = sysAssistHandler.queryDictByPcode("AL_POSITION");
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String nodeId = this.getRequestParm().getParameter("id");
//            String nodePid = this.getRequestParm().getParameter("pid");
//            String level = this.getRequestParm().getParameter("level");
            Map<String, Object> params = new HashMap<String, Object>();

            if (StringUtils.isNotBlank(nodeId)) {
                params.put("dictRegionId", nodeId);
            }

            params.put("keyword", keyword);
            DTablePageModel dtPageModel = goodsHandler.queryGoodsTypePage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定商品类型详情
     */
    @Action(value = "queryGoodsTypeDt", results = {
            @Result(name = SUCCESS, location = "/view/goods/goodsTypeDetail.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String queryGoodsTypeDt() {
        String result = null;
        try {
            Integer goodsTypeId = Integer.valueOf(this.getRequestParm().getParameter("goodsTypeId"));
            if (goodsTypeId != null) {
                GoodsType goodsType = (GoodsType) goodsHandler.queryGoodsTypeById(goodsTypeId);
                getRequest().put("goodsType", goodsType);
                result = SUCCESS;
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
     * 新增和修改商品类型页面跳转
     */
    @Action(value = "addOrModifyGoodsType", results = {
            @Result(name = SUCCESS, location = "/view/goods/goodsTypeAddOrUpdate.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String addOrModifyGoodsType() {
        String result = SUCCESS;
        try {
            String goodsTypeIdStr = this.getRequestParm().getParameter("goodsTypeId");
            if (StringUtils.isNotBlank(goodsTypeIdStr)) {
                Integer goodsTypeId = Integer.valueOf(this.getRequestParm().getParameter("goodsTypeId"));
                GoodsType goodsType = (GoodsType) goodsHandler.queryGoodsTypeById(goodsTypeId);
                getRequest().put("goodsType", goodsType);
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
     * 新增和修改商品类型
     */
    @Action(value = "saveOrUpdateGoodsType", results = {
            @Result(name = SUCCESS, location = "/view/goods/goodsTypeList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String saveOrUpdateGoodsType() {
        String result = SUCCESS;
        try {
            String gtNo = "";
            int childLength = 0;
            Integer gtId = goodsType.getGtId();
            if (gtId == null) {
                goodsType.setNewer(true);
                //生成商品类型编号
                List<GoodsType> goodsTypeList = goodsHandler.queryAllGoodsType(GoodsType.class);
                if (goodsTypeList != null) {
                    childLength = goodsTypeList.size();
                }
                gtNo = "GD_0000" + (11 + childLength);
                goodsType.setGtNo(gtNo);
                goodsType.setIsDel(Const.IS_DEL_FALSE);
            }
            if (!"".equals(goodsType.getDgtId()) && null != goodsType.getDgtId() &&
                    !"".equals(goodsType.getGtId()) && null != goodsType.getGtId()) {
                if (goodsType.getDgtId().equals(goodsType.getGtId())) {
                    this.getRequest().put("updateF", "更新失败，当前操作无效！");

                    return result;
                }
            }
            goodsHandler.saveOrUpdateGoodsType(goodsType);
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 删除商品类型
     */
    @Action(value = "deleteGoodsType", results = {
            @Result(name = SUCCESS, location = "/view/goods/goodsTypeList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String deleteGoodsType() {
        String result = SUCCESS;
        try {
            Integer goodsTypeId = Integer.valueOf(this.getRequestParm().getParameter("goodsTypeId"));
            GoodsType goodsType = (GoodsType) goodsHandler.queryGoodsTypeById(goodsTypeId);
            if (goodsHandler.isExistGoods(goodsTypeId)) {
                this.getRequest().put("msg", "此类别下存在商品,无法删除");
            } else {
                goodsType.setIsDel("1");
                goodsHandler.saveOrUpdateGoodsType(goodsType);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }
}
