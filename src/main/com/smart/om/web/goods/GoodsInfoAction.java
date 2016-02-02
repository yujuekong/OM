package com.smart.om.web.goods;

import com.smart.om.biz.goods.GoodsHandler;
import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.persist.GoodsInfo;
import com.smart.om.persist.GoodsType;
import com.smart.om.persist.SysDict;
import com.smart.om.persist.SysUser;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.Tools;
import com.smart.om.util.ZTreeNode;
import com.smart.om.util.ZTreeUtil;
import com.smart.om.web.base.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品信息管理Action
 * Created by Administrator on 2015/9/11.
 */
@Namespace("/view/goods/goodsInfo")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class GoodsInfoAction extends BaseAction {
    private static final Logger logger = Logger.getLogger(GoodsInfoAction.class);
    @Resource
    private GoodsHandler goodsHandler;
    @Resource
    private SysAssistHandler sysAssistHandler;

    private GoodsInfo goodsInfo;
    private File goodsFile1; //上传的文件
    private File goodsFile2; //上传的文件
    private String imageFileName; //文件名称
    private String imageContentType; //文件类型

//    /**
//     * 跳转至商品信息
//     */
//    @Action
//    public String showGoodsInfo() {
//       todo:
//        return SUCCESS;
//    }


    /**
     * 搜索商品信息列表
     */
    @Action(value = "queryGoodsInfoPage")
    public void queryGoodsInfoPage() {
        SysUser sysUser = (SysUser) this.getSession().get("account");
        SysDict sysDict = sysAssistHandler.queryDictByPcode("AL_POSITION");
        try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            Map<String, Object> params = new HashMap<String, Object>();
            String nodeId = this.getRequestParm().getParameter("id");
            String level = this.getRequestParm().getParameter("level");
            String sellerId = this.getRequestParm().getParameter("sellerId");
            //是否按登陆用户所属分公司下的仓库存在商品过滤,前端传递此参数则过滤，否则不过滤
            String orgFilter = this.getRequestParm().getParameter("orgFilter");
            Integer orgId = sysUser.getOrgId();
            if (orgId != null) {
                params.put("orgId", orgId.toString());
            }
            params.put("keyword", keyword);
            if (StringUtils.isNotBlank(sellerId)) {
                params.put("sellerId", sellerId);
            }
            if (StringUtils.isNotBlank(nodeId)) {
                params.put("dictRegionId", nodeId);
            }
            if (StringUtils.isNotBlank(orgFilter)) {
                params.put("orgFilter", orgFilter);
            }
            params.put("app", "");
            DTablePageModel dtPageModel = goodsHandler.queryGoodsInfoPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定商品信息详情
     */
    @Action(value = "queryGoodsInfoDt", results = {
            @Result(name = SUCCESS, location = "/view/goods/goodsInfoDetail.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String queryGoodsInfoDt() {
        String result = SUCCESS;
        try {

            Integer goodsInfoId = Integer.valueOf(this.getRequestParm().getParameter("goodsInfoId"));
            if (goodsInfoId != null) {
                GoodsInfo goodsInfo = (GoodsInfo) goodsHandler.queryGoodsInfoById(goodsInfoId);
                getRequest().put("goodsInfo", goodsInfo);
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
     * 新增和修改商品页面跳转
     */
    @Action(value = "addOrModifyGoodsInfo", results = {
            @Result(name = SUCCESS, location = "/view/goods/goodsInfoAddOrUpdate.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")

    })
    public String addOrModifyGoodsInfo() {
        String result = SUCCESS;
        try {
            String goodsInfoIdStr = this.getRequestParm().getParameter("goodsInfoId");
            if (StringUtils.isNotBlank(goodsInfoIdStr)) {
                Integer infoId = Integer.valueOf(goodsInfoIdStr);
                GoodsInfo goodsInfo = (GoodsInfo) goodsHandler.queryGoodsInfoById(infoId);
                getRequest().put("goodsInfo", goodsInfo);
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
     * 新增和修改商品信息
     */
    @Action(value = "saveOrUpdateGoodsInfo", results = {
            @Result(name = SUCCESS, location = "/view/goods/goodsInfoList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String saveOrUpdateGoodsInfo() throws FileNotFoundException {
        String result = SUCCESS;
        try {
            Integer goodsId = goodsInfo.getGoodsId();
            if (goodsId == null) {
                goodsInfo.setNewer(true);
                goodsInfo.setGoodsStatus("0");
            }
            Integer goodsSort = goodsInfo.getGoodsSort();
            if (goodsSort == null) {
                Integer maxSort = goodsHandler.findMaxSort();
                goodsInfo.setGoodsSort(maxSort + 1);
            }
            if (goodsFile1 != null) {
                // 建立文件上传流
                InputStream fis1 = new FileInputStream(goodsFile1);
                String uploadImg1;

                //保存图片和地址
                if (StringUtils.isNotBlank(goodsInfo.getGoodsPic1())) {
                    uploadImg1 = Tools.uploadImg(fis1, "goodsInfo", getRequestParm(), goodsInfo.getGoodsPic1());
                } else {
                    uploadImg1 = Tools.uploadImg(fis1, "goodsInfo", getRequestParm(), null);
                }
                goodsInfo.setGoodsPic1(uploadImg1);
            }

            if (goodsFile2 != null) {
                InputStream fis2 = new FileInputStream(goodsFile2);
                String uploadImg2;
                if (StringUtils.isNotBlank(goodsInfo.getGoodsPic2())) {
                    uploadImg2 = Tools.uploadImg(fis2, "goodsInfo", getRequestParm(), goodsInfo.getGoodsPic2());
                } else {
                    uploadImg2 = Tools.uploadImg(fis2, "goodsInfo", getRequestParm(), null);
                }
                goodsInfo.setGoodsPic2(uploadImg2);
            }

            //获得商品类别
            GoodsType goodsType = (GoodsType) goodsHandler.queryGoodsTypeById(goodsInfo.getGtId());
            if (goodsType != null) {
                goodsInfo.setGoodsTypeNo(goodsType.getGtNo());
            }
            //设置商品编号
            String goodsLsNo = goodsHandler.createGoodsLsNo(goodsInfo.getGoodsTypeNo());
            goodsInfo.setGoodsLsNo(goodsLsNo);
            goodsInfo.setGoodsSpell(Tools.getFirstLetter(goodsInfo.getGoodsName()));//设置商品拼音
            goodsHandler.saveOrUpdateGoodsInfo(goodsInfo);
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 删除商品信息
     */
    @Action(value = "deleteGoodsInfo", results = {
            @Result(name = SUCCESS, location = "/view/goods/goodsInfoList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String deleteGoodsInfo() {
        String result = SUCCESS;
        try {
            Integer goodsInfoId = Integer.valueOf(this.getRequestParm().getParameter("goodsInfoId"));
            GoodsInfo goodsInfo = (GoodsInfo) goodsHandler.queryGoodsInfoById(goodsInfoId);
            goodsInfo.setIsDel("1");
            goodsHandler.saveOrUpdateGoodsInfo(goodsInfo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 查询所有商品类型
     */
    @Action(value = "queryAllGoodsType")
    public void queryAllGoodsType() {
        List<GoodsType> goodsTypeList = goodsHandler.queryAllGoodsType(GoodsType.class);
        try {
            String jsonData = JSONUtil.serialize(goodsTypeList);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改商品状态
     */
    @Action(value = "modifyGoodsStatus", results = {
            @Result(name = SUCCESS, location = "/view/goods/goodsInfoList.jsp"),
            @Result(name = ERROR, location = "/view/error.jsp")
    })
    public String modifyGoodsStatus() {
        String result = SUCCESS;
        try {
            Integer goodsInfoId = Integer.valueOf(this.getRequestParm().getParameter("goodsInfoId"));
            goodsInfo = (GoodsInfo) goodsHandler.queryGoodsInfoById(goodsInfoId);
            if (goodsInfo.getGoodsStatus().equals("0")) {
                goodsInfo.setGoodsStatus("1");
            } else {
                goodsInfo.setGoodsStatus("0");
            }
            goodsHandler.saveOrUpdateGoodsInfo(goodsInfo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
        return result;
    }

    /**
     * 商品类别
     */
    @Action(value = "getMulSubGoodsTypeDataByPcode")
    public void getMulSubGoodsTypeDataByPcode() {
        try {

            List<GoodsType> goodsTypeList = goodsHandler.getMulSubGoodsTypeDataByPcode();
            ZTreeNode root = new ZTreeNode();
            root.setName("所有分类");
            root.setOpen(true);
            String jsonData = ZTreeUtil.toJson(root, goodsTypeList, "gtId", "dgtId", "gtNo", "gtName");
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 商品单位
     */
    @Action(value = "getMulSubGoodsUnitDataByPcode")
    public void getMulSubGoodsUnitDataByPcode() {
        try {
            try {
                String dictPcode = this.getRequestParm().getParameter("dictPcode");
                String dictLevel = this.getRequestParm().getParameter("dictLevel");
                SysDict sysDict = sysAssistHandler.queryDictByPcode(dictPcode);
                List<SysDict> dictLst = sysAssistHandler.queryMulSubDictByPcode(dictPcode, dictLevel);
                ZTreeNode root = new ZTreeNode();
                root.setName("单位");
                root.setOpen(true);
                root.setId(String.valueOf(sysDict.getDictId()));
                String jsonData = ZTreeUtil.toJson(root, dictLst, "dictId", "dictPid", "dictCode", "dictName");
                PrintWriter pw = super.getResponse().getWriter();
                pw.write(jsonData);
                pw.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理类型
     */
    @Action(value = "getAllGoodsAgent")
    public void getAllGoodsAgent() {
        String dictCode = this.getRequestParm().getParameter("dictCode");
        List<SysDict> sdList = goodsHandler.getAllGoodsAgent(dictCode);
        try {
            PrintWriter out = getResponse().getWriter();
            JsonConfig config = new JsonConfig();
            config.setJsonPropertyFilter(new PropertyFilter() {
                public boolean apply(Object arg0, String arg1, Object arg2) {
                    return arg1.equals("sysDictPid");
                }
            });
            out.print(JSONArray.fromObject(sdList, config));
            out.flush();
            out.close();
        } catch (IOException e) {

        }
    }
    
    /**
     * app首页展示商品列表分页
     * @return
     */
    @Action(value="queryPageGoodsInfos")
    public void queryPageGoodsInfos(){
    	 try {
             //搜索关键字
             String keyword = this.getRequestParm().getParameter("keyword");
             Map<String, Object> params = new HashMap<String, Object>();
             params.put("keyword", keyword);
             DTablePageModel dtPageModel = goodsHandler.queryPageGoodsInfos(params, super.getPageData());
             String jsonData = JSONUtil.serialize(dtPageModel);
             PrintWriter pw = super.getResponse().getWriter();
             pw.write(jsonData);
             pw.flush();
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
    
    /**
     * app首页添加商品
     * @return
     */
    @Action(value="addAppGoods")
    public void addAppGoods(){
    	try{
    		String goodsIdList = this.getRequestParm().getParameter("goodsIdList"); 
    		int sort = goodsHandler.findMaxSort();
    		if(StringUtils.isNotBlank(goodsIdList)){
    			JSONArray array = JSONArray.fromObject(goodsIdList);
    			for(int i = 0; i < array.size();i++){
    				String goodsId = array.getJSONObject(i).getString("goodsId");
    				logger.info("goodsId:" + goodsId);
    				if(StringUtils.isNotBlank(goodsId)){
    					GoodsInfo good = (GoodsInfo) goodsHandler.queryGoodsInfoById(Integer.valueOf(goodsId));
    					good.setGoodsSort(++sort);
    					good.setNewer(false);
    					goodsHandler.saveOrUpdateGoodsInfo(good);
    				}
    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * 删除app首页商品
     * @return
     */
    @Action(value="deleteGoodsInfoSort",results={
    		@Result(name=SUCCESS,location="/view/app/pageGoodsList.jsp"),
    		@Result(name=ERROR,location="/view/error.jsp")
    })
    public String deleteGoodsInfoSort(){
    	try{
    		String goodsId = this.getRequestParm().getParameter("goodsInfoId");
    		if(StringUtils.isNotBlank(goodsId)){
    			GoodsInfo goodsInfo = (GoodsInfo) goodsHandler.queryGoodsInfoById(Integer.valueOf(goodsId));
    			if(goodsInfo.getGoodsSort() != null && goodsInfo.getGoodsSort() > 0){
    				List<GoodsInfo> list = goodsHandler.queryGoodInfoBySort2(goodsInfo.getGoodsSort());
    				if(list != null && list.size() > 0){
    					for(GoodsInfo goods:list){
        					goods.setGoodsSort(goods.getGoodsSort() - 1);
        					goods.setNewer(false);
        					goodsHandler.saveOrUpdateGoodsInfo(goods);
        				}
    				}
    			}
    			goodsInfo.setGoodsSort(null);
    			goodsInfo.setNewer(false);
    			goodsHandler.saveOrUpdateGoodsInfo(goodsInfo);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		this.getRequest().put("errors", "系统正忙，请重试...");
    		return ERROR;
    	}
    	return SUCCESS;
    }
    
    /**
     * 修改app首页商品排序
     * @return
     */
    @Action(value="modifyGoodsInfoSort",results={
    		@Result(name=SUCCESS,location="/view/app/pageGoodsList.jsp"),
    		@Result(name=ERROR,location="/view/error.jsp")
    })
    public String modifyGoodsInfoSort(){
    	try{
    		String goodsId = this.getRequestParm().getParameter("goodsInfoId");
    		if(StringUtils.isNotBlank(goodsId)){
    			GoodsInfo goodsInfo = (GoodsInfo) goodsHandler.queryGoodsInfoById(Integer.valueOf(goodsId));
    			List<GoodsInfo> goodsInfoList = goodsHandler.queryGoodInfoBySort(goodsInfo.getGoodsSort());
    			if(goodsInfoList != null && goodsInfoList.size() > 0 ){
    				for(GoodsInfo goodsSort:goodsInfoList){
        				goodsSort.setNewer(false);
        				goodsSort.setGoodsSort(goodsSort.getGoodsSort() + 1);
        				goodsHandler.saveOrUpdateGoodsInfo(goodsSort);
        			}
    			}
    			goodsInfo.setGoodsSort(1);
    			goodsInfo.setNewer(false);
    			goodsHandler.saveOrUpdateGoodsInfo(goodsInfo);
    			
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		this.getRequest().put("errors", "系统正忙，请重试...");
    		return ERROR;
    	}
    	return SUCCESS;
    }
    
    /**
     * app首页商品添加列表
     */
    @Action(value="queryAppGoodsPage")
    public void queryAppGoodsPage(){
    	try {
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("keyword", keyword);
            DTablePageModel dtPageModel = goodsHandler.queryAppGoodsInfoPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public GoodsInfo getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public File getGoodsFile1() {
        return goodsFile1;
    }

    public void setGoodsFile1(File goodsFile1) {
        this.goodsFile1 = goodsFile1;
    }

    public File getGoodsFile2() {
        return goodsFile2;
    }

    public void setGoodsFile2(File goodsFile2) {
        this.goodsFile2 = goodsFile2;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }
}
