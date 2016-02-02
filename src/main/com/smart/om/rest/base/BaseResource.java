package com.smart.om.rest.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Parameter;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.googlecode.jsonplugin.JSONException;
import com.googlecode.jsonplugin.JSONUtil;
import com.smart.om.util.Const;
import com.smart.om.util.PageData;



@SuppressWarnings("unchecked")
public abstract class BaseResource extends ServerResource {
	private static final Logger logger = Logger.getLogger(BaseResource.class);
	/**
	 * 服务端返回json的键名：结果代码
	 */	
	private static final String KEY_RESULT_CODE = "RESULT_CODE";
	/**
	 * 服务端返回json的键名：结果数据
	 */
	private static final String KEY_RESULT_DATA = "RESULT_DATA";
	/**
	 * 成功操作提示代码
	 */
	private static final String KEY_SUCCESS_CODE = "SUCCESS_CODE";
	/**
	 * 成功操作提示详情
	 */
	private static final String KEY_SUCCESS_MESSAGE = "SUCCESS_MESSAGE";

	// 成功调用返回的代码： 9999为网络异常
	/**
	 * 返回预期结果
	 */
	private static final int RESULT_CODE_SUCCESS = 0;
	/**
	 * 返回非预期结果
	 */
	private static final int RESULT_CODE_ERROR = 1;

	/**
	 * Rest中具体的方法
	 */
	private String method;
	/**
	 * 参数：json格式的字符串
	 */
	private String data;

	/**
	 * 获取参数
	 */
	protected void doInit() {
		Parameter paraMethod = this.getQuery().getFirst("method");
		Parameter paraDataJson = this.getQuery().getFirst("data");
		if (paraMethod != null) {
			method = paraMethod.getValue();
		}
		if (paraDataJson != null) {
			data = paraDataJson.getValue();
		}
	}

	/**
	 * 直接以地址形式调用 /rest地址?method=rest方法&data=UrlEncode(json格式的参数,"UTF-8")
	 * 
	 * @return
	 */
	@Get("json")
	public String dispatcherGet() {
		return this.dispatcher(method, data,null);
	}

	/**
	 * 以ClientResource的Post形式调用 ClientResource cr = new
	 * ClientResource("http://localhost:8080/sams/rest/alarm");<br>
	 * Form form = new Form(); form.add("method", "query"); <br>
	 * form.add("data", URLEncode("{\"user_id\":123}"));<br>
	 * Representation r = cr.post(form.getWebRepresentation());<br>
	 * Strong json = r.getText();<br>
	 * cr.getResponseEntity().exhaust();<br>	 * 
	 * @param entity
	 * @return
	 * @throws DocumentException 
	 */
	@Post
	public Representation dispatcherPost(Representation entity) throws DocumentException {
		if (method == null) {
			Form form = new Form(entity);
			method = form.getFirstValue("method");
			data = form.getFirstValue("data");
		}
		return new StringRepresentation(this.dispatcher(method, data, entity),
				MediaType.APPLICATION_JSON);
	}

	/**
	 * 调度器，调度指定的method
	 * @param method Rest中具体的方法
	 * @param data json格式的字符串
	 * @return
	 */
	private String dispatcher(String method, String data,Representation entity) {
		String returnValue = "";
		if (method == null || method.equals("")) {
			returnValue = this.toErrorResultJson("method为空!",Const.ERROR_404);
		}
		try {
			// 资源转发！
			if (this.getClass().getName().equals("com.smart.om.rest.base.RestResource")) {
				// REST服务器
				Method methodHandler = this.getClass().getDeclaredMethod("executeHandler",String.class, Map.class,Representation.class,String.class);
				returnValue = (String) methodHandler.invoke(this, method,this.getMapFromJson(data),entity,data);
			} else {
				returnValue = this.toErrorResultJson("非法服务请求！",Const.ERROR_500);
			}
		} 
		catch (NoSuchMethodException ex) {
			ex.printStackTrace();
			returnValue = this.toErrorResultJson("不存在形参为Map的方法：" + method,Const.ERROR_500);
		} 
		catch (IllegalArgumentException ex) {
			returnValue = this.toErrorResultJson(method + "方法调用异常!", Const.ERROR_500);
			logger.error("method：" + method + "\r\ndata  ：" + data, ex);
		} catch (IllegalAccessException ex) {
			returnValue = this.toErrorResultJson(method + "方法调用异常!", Const.ERROR_500);
			logger.error("method：" + method + "\r\ndata  ：" + data, ex);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			returnValue = this.toErrorResultJson(method + "方法调用异常!",Const.ERROR_500);
		}
		return returnValue;
	}

	/**
	 * 返回成功操作提示
	 * 
	 * @param successMessage
	 * @return
	 */
	protected String toSuccessTipJson(String successMessage) {
		if (successMessage == null) {
			successMessage = "";
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(KEY_SUCCESS_CODE, RESULT_CODE_SUCCESS);
		map.put(KEY_SUCCESS_MESSAGE, successMessage);
		return this.toResultJson(RESULT_CODE_SUCCESS, map);
	}
	
	/**
	 * 返回失败操作提示
	 * 
	 * @param successMessage
	 * @return
	 */
	protected String toFailTipJson(String successMessage) {
		if (successMessage == null) {
			successMessage = "";
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(KEY_SUCCESS_CODE, RESULT_CODE_ERROR);
		map.put(KEY_SUCCESS_MESSAGE, successMessage);
		return this.toResultJson(RESULT_CODE_ERROR, map);
	}
	
	/**
	 * 返回对象或对象列表
	 * 
	 * @param resultData
	 * @return
	 */
	protected String toResultJsonSuccess(Object resultData) {
		return this.toResultJson(RESULT_CODE_SUCCESS, resultData);
	}
	
	/**
	 * 返回系统错误提示
	 * 
	 * @param errorTitle
	 * @param ex
	 * @return
	 * @throws AppException
	 */
	protected String toErrorResultJson(String errorMessage, int errorCode) {
		String resourceRef = "";
		if (this.getRequest() != null) {
			resourceRef = this.getRequest().getResourceRef().toString();
		}
		String invokeMsg = "调用REST： " + resourceRef + "\r\nmethod：" + method + "\r\ndata  ：" + data;
		Map<Object, Object> map = new HashMap<Object, Object>();		
		if (StringUtils.isNotBlank(errorMessage)) {
			Map<String,Object> mapMassage = new HashMap<String,Object>();
			mapMassage.put(KEY_SUCCESS_CODE, RESULT_CODE_ERROR);
			mapMassage.put(KEY_SUCCESS_MESSAGE, errorMessage);
			map.put(KEY_RESULT_CODE, errorCode);
			map.put(KEY_RESULT_DATA, mapMassage);
			
//			map.put(KEY_RESULT_CODE, errorCode);
//			map.put(KEY_RESULT_DATA, errorMessage);
		} else {
		}
		try {
			return JSONUtil.serialize(map);
		} catch (JSONException ex) {
//			return "{\"" + KEY_RESULT_CODE + "\":" + RESULT_CODE_ERROR + ",\""
//					+ KEY_RESULT_DATA + "\":\"{\"" + KEY_ERROR_TITLE
//					+ "\":\"转换为Json异常\"}}";
			
			return "{\"" + KEY_RESULT_CODE + "\":" + RESULT_CODE_ERROR + ",\""
			+ KEY_RESULT_DATA + "\":\"转换为Json异常\"}";
		}
	}

	/**
	 * 返回Json数据 
	 * @param resultCode 整型
	 * @param resultData
	 *            对象或对象列表<br>
	 *            成功信息的Map：SUCCESS_TITLE,SUCCESS_MESSAGE<br>
	 *            错误信息的Map：ERROR_TITLE,ERROR_MESSAGE 
	 * @return
	 */
	protected String toResultJson(int resultCode, Object resultData) {
		try {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put(KEY_RESULT_CODE, resultCode);
			map.put(KEY_RESULT_DATA, resultData);
			return JSONUtil.serialize(map);
		} catch (JSONException ex) {
			return "{\"" + KEY_RESULT_CODE + "\":" + RESULT_CODE_ERROR + ",\""
					+ KEY_RESULT_DATA + "\":\"转换为Json异常\"}";
		}
	}

	/**
	 * 从Json里获取Map 
	 * @param data
	 * @return
	 */
	private Map<Object, Object> getMapFromJson(String data) {
		try {
			if (data == null || data.equals("")) {
				return new HashMap<Object, Object>();
			}
			return (Map<Object, Object>) JSONUtil.deserialize(data);
		} catch (JSONException ex) {
			return null;
		}
	}
	
	/**
	 * 根据当前页和每页显示条数构造PageData对象
	 * @param pageSize：每页显示条数
	 * @param currentPage：当前页
	 * @return
	 */
	protected PageData getPageData(String pageSize,String currentPage){
		PageData pageData = new PageData();
		pageData.setPageSize(Integer.parseInt(pageSize));
		pageData.setCurrentPage(Integer.parseInt(currentPage));
		return pageData;
	}
}
