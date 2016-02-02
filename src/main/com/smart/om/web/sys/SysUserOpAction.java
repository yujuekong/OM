package com.smart.om.web.sys;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.SysDict;
import com.smart.om.persist.SysUser;
import com.smart.om.persist.SysUserOp;
import com.smart.om.util.DTablePageModel;
import com.smart.om.web.base.BaseAction;

/**
 * 用户操作管理管理
 * @author liuz
 *
 */
@Namespace("/view/sys/sysUserOp")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class SysUserOpAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(SysUserOpAction.class);
	
	@Resource
	private SysFuncHandler sysFuncHandler;

    @Resource
    private SysAssistHandler sysAssistHandler;
    
    /**
     * 用户操作管理列表分页
     */
    @Action(value = "querySysUserOpPage")
    public void querySysUserOpPage(){
        try {
            SysDict sysDict = sysAssistHandler.queryDictByPcode("AL_POSITION");
            SysUser sysUser = (SysUser) this.getSession().get("account");
            //搜索关键字
            String keyword = this.getRequestParm().getParameter("keyword");
            String startDate = this.getRequestParm().getParameter("startDate");//查询开始日期
            String endDate = this.getRequestParm().getParameter("endDate");//查询结束日期
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("startDate", startDate);
            params.put("endDate", endDate);
            params.put("keyword", keyword);
            params.put("orgId", sysUser.getOrgId());
            
            DTablePageModel dtPageModel = sysFuncHandler.queryUserOpPage(params, super.getPageData());
            List list = dtPageModel.getAaData();
            for (int i = 0; i < list.size(); i++) {
            	SysUserOp sysUserOp = (SysUserOp)list.get(i);
            	if("1".equals(sysUserOp.getBizType())){
            		sysUserOp.setBizType("用户设备清洗");
            	}else if("2".equals(sysUserOp.getBizType())){
            		sysUserOp.setBizType("用户设备维修洗");
            	}else if("3".equals(sysUserOp.getBizType())){
            		sysUserOp.setBizType("用户商品入库");
            	}else if("4".equals(sysUserOp.getBizType())){
            		sysUserOp.setBizType("用户商品出库");
            	}else if("5".equals(sysUserOp.getBizType())){
            		sysUserOp.setBizType("用户商品出库处理");
            	}else if("6".equals(sysUserOp.getBizType())){
            		sysUserOp.setBizType("用户配送");
            	}else if("7".equals(sysUserOp.getBizType())){
            		sysUserOp.setBizType("商品分配");
            	}
			}
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * 查询用户操作详细信息
     */
    @Action(value = "querySysUserOpById", results = {
			@Result(name = SUCCESS, location = "/view/region/serviceUserLine.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
    public String querySysUserOpById(){
    	String result = SUCCESS;
    	Date dt=new Date();
        SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
        String dDate = matter1.format(dt);

    	Calendar calendar = Calendar.getInstance();
    	calendar.add(Calendar.DATE, -1); //得到前一天
    	Date date = calendar.getTime();
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	String qDate =df.format(date);
        try {
        	String str = this.getRequestParm().getParameter("userId");
        	Integer id =Integer.valueOf(str);
        	//List<SysUserOp> dateList =new ArrayList<SysUserOp>();
        	Map<String,List> map = new LinkedHashMap<String,List>();
        	//List list = new ArrayList();
        	List<SysUserOp> list =  sysFuncHandler.querySysUserOpById(id);
        	if(!list.isEmpty()){
	        	for(int i=0;i<list.size();i++){
	        		SysUserOp sysUserOp = list.get(i);
	        		String strDate = sysUserOp.getOpDate();
	    		    if(map.containsKey(strDate)){
	    			     map.get(strDate).add(sysUserOp);
	    			 }else{
	    			   List l = new ArrayList();
	    			   l.add(sysUserOp);
	    			   map.put(strDate,l);
	    			}
	        	}
        	}
        	this.getRequest().put("sysUserOpMap", map);
        	this.getRequest().put("qDate", qDate);
        	this.getRequest().put("dDate", dDate);
        } catch (Exception e) {
            e.printStackTrace();
            this.getRequest().put("where", " 查询用户操作详细信息");
			this.getRequest().put("errors",	"系统正忙，请联系管理员");
        }
        return result;
    }
}
