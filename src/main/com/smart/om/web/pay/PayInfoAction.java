package com.smart.om.web.pay;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import com.smart.om.biz.pay.PayHandler;
import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.persist.SysDict;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.ZTreeNode;
import com.smart.om.util.ZTreeUtil;
import com.smart.om.web.base.BaseAction;

/**
 * 支付管理
 * @author liuz
 *
 */
@Namespace("/view/pay/payInfo")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class PayInfoAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(PayInfoAction.class);
	
	@Resource
	private PayHandler payHandler;

    @Resource
    private SysAssistHandler sysAssistHandler;
    
    /**
     * 支付列表分页
     */
    @Action(value = "queryPayInfoPage")
    public void queryPayInfoPage(){
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
            DTablePageModel dtPageModel = payHandler.queryPayInfoPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
