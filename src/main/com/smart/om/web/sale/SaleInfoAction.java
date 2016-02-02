package com.smart.om.web.sale;

import com.smart.om.biz.sale.SaleHandler;
import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.persist.SysDict;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.ZTreeNode;
import com.smart.om.util.ZTreeUtil;
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
import java.util.List;
import java.util.Map;

/**
 * 销售管理
 * @author liuz
 *
 */
@Namespace("/view/sale/saleInfo")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class SaleInfoAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(SaleInfoAction.class);
	
	@Resource
	private SaleHandler saleHandler;

    @Resource
    private SysAssistHandler sysAssistHandler;
    
    /**
     * 销售列表分页
     */
    @Action(value = "querySaleInfoPage")
    public void querySaleInfoPage(){
        try {
            SysDict sysDict = sysAssistHandler.queryDictByPcode("AL_POSITION");
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            //String nodeId = this.getRequestParm().getParameter("id");
            //String nodePid = this.getRequestParm().getParameter("pid");
            String startDate = this.getRequestParm().getParameter("startDate");//查询开始日期
            String endDate = this.getRequestParm().getParameter("endDate");//查询结束日期
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("startDate", startDate);
            params.put("endDate", endDate);
            params.put("keyword", keyword);
            DTablePageModel dtPageModel = saleHandler.querySaleInfoPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 根据上级字典Code获得数据字典数据
     */
    @Action(value = "getMulSubDictDataByPcode")
    public void getMulSubDictDataByPcode() {
        try {
            String dictPcode = this.getRequestParm().getParameter("dictPcode");
            String dictLevel = this.getRequestParm().getParameter("dictLevel");
            SysDict sysDict = sysAssistHandler.queryDictByPcode(dictPcode);
            List<SysDict> dictLst = sysAssistHandler.queryMulSubDictByPcode(dictPcode, dictLevel);
            ZTreeNode root = new ZTreeNode();
            root.setName("地区");
            root.setOpen(true);
            root.setId(String.valueOf(sysDict.getDictId()));
            String jsonData = ZTreeUtil.toJson(root, dictLst, "dictId", "dictPid", "dictCode", "dictName");
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
