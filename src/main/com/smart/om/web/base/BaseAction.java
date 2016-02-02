package com.smart.om.web.base;

import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.json.JSONUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.PageData;
import com.smart.om.util.PageModel;

/**
 * 基类Action
 * 
 * @author Mstanford
 * 
 */
@SuppressWarnings("unchecked")
public class BaseAction extends ActionSupport implements ParameterAware{
	
	// 获取request对象
	public Map<String, Object> getRequest() {
		// 通过ActionContext得到request对象
		return (Map) ActionContext.getContext().get("request");
	}
    // setSessionValue
    protected void setSessionValue(String name, Object value) {
    	this.getHttpSession().setAttribute(name, value);
    }
    // getSessionValue
    protected Object getSessionValue(String name) {
    	Object object =this.getHttpSession().getAttribute(name);
    	
        return object;
    }
    
    // Session
    protected HttpSession getHttpSession() {
    	HttpSession object =this.getRequestParm().getSession();
        return object;
    }
	// 获取request对象
	public HttpServletRequest getRequestParm() {
		// 通过ActionContext得到request对象
		return ServletActionContext.getRequest();
	}

	// 获取session对象
	public Map<String, Object> getSession() {
		return ActionContext.getContext().getSession();
	}

	// 获取application对象
	public Map<String, Object> getApplication() {
		return (Map) ActionContext.getContext().getApplication();
	}

	// 获取response对象
	public HttpServletResponse getResponse() {
		// 通过ServletActionContext类获取HttpServletResponse对象。
		HttpServletResponse response = ServletActionContext.getResponse();
		// 设置响应头与字符编码
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		return response;
	}

	/**
	 * 设置页面不缓存
	 * @param response
	 */
	protected void setNoCache(HttpServletResponse response)
	{
		response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
		response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
		response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
		response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility 
	}

    /**
     * string to html.
     *
     * @param text String
     * @throws Exception ex
     */
    protected void toHtml(String text) throws Exception {
        HttpServletResponse response = this.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
		out.println(text);
		out.flush();
		out.close();
    }

    protected void toXml(String text) throws Exception {
        HttpServletResponse response = this.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
		out.println(text);
		out.flush();
		out.close();
    }
	public void setParameters(Map<String, String[]> arg0) {
		// TODO Auto-generated method stub
		arg0.remove("pager.offset");
	}
		
	
	public PageData getPageData(){
		String sEcho = this.getRequestParm().getParameter("sEcho");
		String iDisplayStart = this.getRequestParm().getParameter("iDisplayStart");
		String iDisplayLength = this.getRequestParm().getParameter("iDisplayLength");			
		int pageSize = Integer.parseInt(iDisplayLength);
		int currentPage = (Integer.parseInt(iDisplayStart)/10) + 1;		
		PageData pageData = new PageData(currentPage, pageSize, Integer.parseInt(sEcho),"web");		
		return pageData;
	}
	
	public PageData getPageDataForSubOrder(){
		String sEcho = this.getRequestParm().getParameter("sEcho");
		String iDisplayStart = this.getRequestParm().getParameter("iDisplayStart");
		String iDisplayLength = this.getRequestParm().getParameter("iDisplayLength");			
		int pageSize = Integer.parseInt(iDisplayLength);
		int currentPage = (Integer.parseInt(iDisplayStart)/100) + 1;		
		PageData pageData = new PageData(currentPage, pageSize, Integer.parseInt(sEcho),"web");		
		return pageData;
	}
	
	public void writeJson(Object obj) {
		try {
			String jsonData = JSONUtil.serialize(obj);
			PrintWriter pw = this.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
