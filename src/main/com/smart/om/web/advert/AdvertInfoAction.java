package com.smart.om.web.advert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import com.smart.om.biz.advert.AdvertHandler;
import com.smart.om.biz.device.DeviceHandler;
import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.persist.AdvertDevice;
import com.smart.om.persist.AdvertInfo;
import com.smart.om.persist.AdvertUser;
import com.smart.om.persist.DeviceInfo;
import com.smart.om.persist.SysDict;
import com.smart.om.persist.SysUser;
import com.smart.om.rest.base.BaseController;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.web.base.BaseAction;

/**
 * 广告内容管理Action
 * @author CA
 *
 */
@Namespace("/view/advert/advertInfo")
@Results({ @Result(name = "error", location = "/view/error.jsp") })
public class AdvertInfoAction  extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(AdvertInfoAction.class);
	
	private static final BaseController baseController = new BaseController();
	@Resource
	private AdvertHandler advertHandler;
	@Resource
	private DeviceHandler deviceHandler;
	@Resource
    private SysAssistHandler sysAssistHandler;
	
	private File adInfoUpload;//广告内容上传的文件
	
	private String adInfoUploadFileName;//上传文件的名称
	
	private String adInfoUploadContentType;//上传文件的类型
	
	private AdvertInfo advertInfo;
	
	/**
	 * 查询所有广告分页内容
	 */
	@Action(value="queryAdvertInfoPage")
	public void queryAdvertInfoPage() {
		try{
			//搜索关键字
			String advertInfoStatus = this.getRequestParm().getParameter("advertInfoStatus");
			String advertFeeStatus = this.getRequestParm().getParameter("advertFeeStatus");
			String keyword = this.getRequestParm().getParameter("keyword");
			String orgId = this.getRequestParm().getParameter("orgId");
			String proviceId = this.getRequestParm().getParameter("proviceId");
			String regionId = this.getRequestParm().getParameter("regionId");	
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("orgId", orgId);
			params.put("proviceId", proviceId);
			params.put("regionId", regionId);
			params.put("keyword", keyword);
			if(StringUtils.isNotBlank(advertInfoStatus)){
				params.put("advertInfoStatus", advertInfoStatus);
			}
			if(StringUtils.isNotBlank(advertFeeStatus)){
				params.put("advertFeeStatus", advertFeeStatus);
			}
			DTablePageModel dtPageModel = advertHandler.queryAdvertInfoPage(params, super.getPageData());
			String jsonData = JSONUtil.serialize(dtPageModel);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询广告主分页内容
	 */
	@Action(value = "queryUserPage")
	public void queryUserPage() {
		try {
			// 搜索关键字
			SysUser sysUser = (SysUser) this.getSession().get("account");
			String adUserStatus = this.getRequestParm().getParameter("advertUserStatus");
			String keyword = this.getRequestParm().getParameter("keyword");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("keyword", keyword);
			if(sysUser.getOrgId() != null){
				params.put("proviceId", sysUser.getDictProviceId());
				params.put("regionId", sysUser.getDictRegionId());
				params.put("orgId", sysUser.getOrgId());
			}
			if(StringUtils.isNotBlank(adUserStatus)){
				params.put("adUserStatus",adUserStatus);
			}
			DTablePageModel dtPageModel = advertHandler.queryUserPage(
					params, super.getPageData());
			String jsonData = JSONUtil.serialize(dtPageModel);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询所有相关联的设备信息
	 */
	@Action(value="queryAdvertDevicePage")
	public void queryAdvertDevicePage() {
		SysDict sysDict = sysAssistHandler.queryDictByPcode("AL_POSITION");
		try{
			//搜索关键字
			String advertInfoId = this.getRequestParm().getParameter("advertInfoId");
			String nodeId = this.getRequestParm().getParameter("id");
            String nodePid = this.getRequestParm().getParameter("pid");
            String level = this.getRequestParm().getParameter("level");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("advertInfoId", advertInfoId);
			if (StringUtils.isNotBlank(nodePid)) {
            	if(sysDict != null){
                	if(Const.IS_REGION.equals(level)){//点击区域
                		params.put("dictRegionId", nodeId);
                	}else if(Const.IS_PROVICE.equals(level)){//点击省份
                		params.put("dictProviceId", nodeId);
                	}else if(Const.IS_ORG.equals(level)) {//点击服务站
                		params.put("dictOrgId", nodeId);
                	}
                }
            } 
			DTablePageModel dtPageModel = advertHandler.queryAdvertDevicePage(params, super.getPageData());
			String jsonData = JSONUtil.serialize(dtPageModel);
			PrintWriter pw = super.getResponse().getWriter();
			pw.write(jsonData);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 搜索广告内容
	 */
	@Action(value="searchAdvertInfo",results={
			@Result(name=SUCCESS,location="/view/advert/adInfoListSearch.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String searchAdvertInfo(){
		try{
			String advertInfoTitle = new String(this.getRequestParm().getParameter("advertInfoTitle").getBytes("ISO-8859-1"),"utf-8");
			getRequest().put("advertInfoDt", advertInfoTitle);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试！");
			return ERROR;
		}
	}
	
	/**
	 * 新增广告
	 */
	@Action(value="saveOrUpdateAdvertInfo",results={
			@Result(name=SUCCESS, location="/view/advert/adInfoList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String saveOrUpdateAdvertInfo(){
		try{
			if(!Const.IS_VALID_TRUE.equals(advertInfo.getAdvertStatus()) && !Const.IS_VALID_FALSE.equals(advertInfo.getAdvertStatus())){
				advertInfo.setAdvertStatus(Const.IS_VALID_FALSE);
			}
			if(advertInfo.getAdvertInfoId() == null){
				advertInfo.setCreateDate(DateUtil.getDateY_M_DH_M_S());
				advertInfo.setPayStatus(Const.IS_VALID_FALSE);
				String advertFile = this.saveAdInfoUpload();
				if(advertFile != null && !"large".equals(advertFile)){
					advertInfo.setAdvertFile(advertFile);
					if(StringUtils.isNotBlank(adInfoUploadContentType)){
						if(adInfoUploadContentType.startsWith("image")){
							advertInfo.setAdvertFileType("图片");
						}
						else if(adInfoUploadContentType.startsWith("application")){
							advertInfo.setAdvertFileType("视频");
						}
						else{
							advertInfo.setAdvertFileType("未知");
						}
					}
				}else if("large".equals(advertFile)){
					this.getRequest().put("errors", "上传文件超过1M,请重新上传！");
					return ERROR;
				}
			}else{
				if(adInfoUpload != null){
					String advertFile = this.saveAdInfoUpload();
					if(advertFile != null && !"large".equals(advertFile)){
						advertInfo.setAdvertFile(advertFile);
						if(adInfoUploadContentType.startsWith("image")){
							advertInfo.setAdvertFileType("图片");
						}
						else if(adInfoUploadContentType.startsWith("application")){
							advertInfo.setAdvertFileType("视频");
						}
						else{
							advertInfo.setAdvertFileType("未知");
						}
					}else if("large".equals(advertFile)){
						this.getRequest().put("errors", "上传文件超过1M,请重新上传！");
						return ERROR;
					}
				}
				if(advertInfo.getAdvertTotalFee() != null){
					if(advertInfo.getPayFee() != null && advertInfo.getPayFee() >= advertInfo.getAdvertTotalFee() ){
						advertInfo.setPayStatus(Const.IS_VALID_TRUE);
					}
					else{
						advertInfo.setPayStatus(Const.IS_VALID_FALSE);
					}
				}
				
			}
			if(advertInfo.getAdvertFile() != null){
				//上传物料
				String getName = this.SubmitPostFile(Const.DEVICE_URL + Const.ADVERT_UPLOAD, baseController.getAccessToken(), advertInfo.getAdvertFile());
				if(StringUtils.isNotBlank(getName)){
					//新增物料文件
					this.postForm(new String[]{Const.Name,Const.FileName,Const.ACCESS_TOKEN}, 
							new String[]{advertInfo.getAdvertFileName(),getName,baseController.getAccessToken()},Const.ADVERT_FILE);
				}
				else{
					this.getRequest().put("errors", "文件上传失败，请重试！");
					return ERROR;
				}
			}
			advertHandler.saveOrUpdateAdvertInfo(advertInfo);
			return SUCCESS;
		}catch(Exception e){
			this.getRequest().put("errors", "系统正忙，请重试！");
			e.printStackTrace();
			return ERROR;
		}
		
	}
	
	/**
	 * 新增和修改广告页面跳转
	 */
	@Action(value="addOrModifyAdvertInfo",results={
			@Result(name=SUCCESS,location="/view/advert/adInfoAdd.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String addOrModifyAdvertInfo(){
		try{
			String advertInfoId = this.getRequestParm().getParameter("advertInfoId");
			if(StringUtils.isNotBlank(advertInfoId)){
				int adInfoId = Integer.valueOf(advertInfoId);
				AdvertInfo advertInfo = (AdvertInfo) advertHandler.queryAdvertInfoById(adInfoId);
				AdvertUser advertUser = (AdvertUser) advertHandler.queryAdvertUserById(advertInfo.getAdvertUserId());
				getRequest().put("advertInfo", advertInfo);
				getRequest().put("advertUser_auName", advertUser.getAuName());
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors","系统异常，请重试！");
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除广告
	 */
	@Action(value="deleteAdvertInfo",results={
			@Result(name=SUCCESS,location="/view/advert/adInfoList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String deleteAdvertInfo(){
		try{
			String advertInfoId = this.getRequestParm().getParameter("advertInfoId");
			if(StringUtils.isNotBlank(advertInfoId)){
				int adInfoId = Integer.valueOf(advertInfoId);
				List<AdvertDevice> list = advertHandler.queryAdvertDevice(adInfoId);
				if(list != null && list.size() > 0){
					for(AdvertDevice advertDevice:list){
						DeviceInfo deviceInfo = deviceHandler.queryDeviceById(advertDevice.getDeviceId());
						
						String code = baseController.getAdvertMsg(deviceInfo.getDeviceNo(), "Code");
						//删除设备对应的广告
						if(StringUtils.isNotBlank(code)){
							baseController.delAdvert(Integer.valueOf(code));
						}
						//查询物料文件编码
						AdvertInfo advertInfo = (AdvertInfo) advertHandler.queryAdvertInfoById(Integer.valueOf(advertInfoId));
						logger.info("advertInfo.getAdvertFileName():" + advertInfo.getAdvertFileName());
						String fileCode = baseController.getFileName(advertInfo.getAdvertFileName(), "Code");
						logger.info("fileCode:" + fileCode);
						if(StringUtils.isNotBlank(fileCode)){
							baseController.delFile(Integer.valueOf(fileCode));
						}
						advertHandler.deleteAdvertDevice(advertDevice);
					}
				}
				advertHandler.deleteAdvertInfo(adInfoId);
				return SUCCESS;
			}else{
				this.getRequest().put("errors","系统异常，请重试！");
				return ERROR;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors","系统异常，请重试！");
			return ERROR;
		}
	}
	
	/**
	 * 查询广告内容信息
	 */
	@Action(value="queryAdvertInfoDt",results={
			@Result(name=SUCCESS,location="/view/advert/adInfoDetail.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String queryAdvertInfoDt(){
		try{
			String advertInfoId = this.getRequestParm().getParameter("advertInfoId");
			if(StringUtils.isNotBlank(advertInfoId)){
				int adInfoId = Integer.valueOf(advertInfoId);
				AdvertInfo advertInfo = (AdvertInfo)advertHandler.queryAdvertInfoById(adInfoId);
				if(advertInfo.getAdvertTotalFee() != null){
					if(advertInfo.getAdvertTotalFee() == advertInfo.getPayFee()){
						advertInfo.setPayStatus(Const.IS_VALID_TRUE);
					}
					advertInfo.setPayStatus(Const.IS_VALID_FALSE);
					double unPayFee = advertInfo.getAdvertTotalFee();
					if(advertInfo.getPayFee() != null){
						unPayFee = advertInfo.getAdvertTotalFee() - advertInfo.getPayFee();
					}
					getRequest().put("unPayFee", unPayFee);
				}
				Long totalDevice = advertHandler.totalDevice(adInfoId);
				getRequest().put("totalDevice", totalDevice);
				getRequest().put("advertInfo", advertInfo);
			return SUCCESS;
			}
			else{
				this.getRequest().put("errors","系统异常，请重试！");
				return ERROR;
			}
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors","系统异常，请重试！");
			return ERROR;
		}
	}
	
	/**
	 * 修改广告状态
	 */
	@Action(value="changeAdvertStatus",results={
			@Result(name=SUCCESS,location="/view/advert/adInfoList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String changeAdvertStatus(){
		try{
			String advertInfoId = this.getRequestParm().getParameter("advertInfoId");
			Integer adInfoId = Integer.valueOf(advertInfoId);
			advertInfo = (AdvertInfo) advertHandler.queryAdvertInfoById(adInfoId);
			List<AdvertDevice> list = advertHandler.queryAdvertDevice(advertInfo.getAdvertInfoId());
			if(Const.IS_VALID_TRUE.equals(advertInfo.getAdvertStatus()) && list != null){
				for(int i = 0;i < list.size();i++){
					AdvertDevice advertDevice = list.get(i);
					DeviceInfo deviceInfo = deviceHandler.queryDeviceById(advertDevice.getDeviceId());
					String code = baseController.getAdvertMsg(deviceInfo.getDeviceNo(), "Code");
					if(StringUtils.isNotBlank(code)){
						System.out.println("code:" + Integer.valueOf(code));
						baseController.delAdvert(Integer.valueOf(code));
					}
				}
				advertInfo.setAdvertStatus(Const.IS_VALID_FALSE);
			}
			else if(Const.IS_VALID_FALSE.equals(advertInfo.getAdvertStatus())){
				if(list != null ){
					for(int i = 0;i < list.size();i++){
						AdvertDevice advertDevice = list.get(i);
						DeviceInfo deviceInfo = deviceHandler.queryDeviceById(advertDevice.getDeviceId());
						//创建设备广告
						logger.info("fileName:" + advertInfo.getAdvertFileName() + ",startDate:" + advertInfo.getStartDate() + ",endDate:" + advertInfo.getEndDate() + ",deviceNo:" + deviceInfo.getDeviceNo());
						baseController.createAdvert(advertInfo.getStartDate(), advertInfo.getEndDate(), deviceInfo.getDeviceNo(), advertInfo.getAdvertFileName());
					}
					advertInfo.setAdvertStatus(Const.IS_VALID_TRUE);
				}
				else{
					this.getRequest().put("errors", "该广告未添加投放设备，无法启用！");
					return ERROR;
				}
			}
			else{
				this.getRequest().put("errors", "系统异常，请重试！");
				return ERROR;
			}
			advertHandler.saveOrUpdateAdvertInfo(advertInfo);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试！");
			return ERROR;
		}
	}
	
	/**
	 * 保存上传的文件
	 * @return
	 * @throws Exception
	 */
	private String saveAdInfoUpload () throws Exception {
		String filePath = "";
		if (adInfoUpload != null && adInfoUpload.length() < 11*1024*1024) {
			String realpath = ServletActionContext.getServletContext().getRealPath("/upload") + File.separator + "advertInfo" + File.separator + DateUtil.getDateYMD();
			File savefile = new File(new File(realpath), adInfoUploadFileName);
			if (!savefile.getParentFile().exists()) {
				savefile.getParentFile().mkdirs();
			}
			FileUtils.copyFile(adInfoUpload, savefile);
			filePath = savefile.getAbsolutePath();
		}
		if(adInfoUpload != null && adInfoUpload.length() >= 1.5*1024*1024){
			filePath = "large";
		}
		return filePath;
	}
	
	
	public InputStream getInputStream() throws Exception {
		String inputPath = new String(this.getRequestParm().getParameter("inputPath").getBytes("ISO-8859-1"),"utf-8");
		File file = new File(inputPath);
		adInfoUploadFileName = URLEncoder.encode(file.getName(),"utf-8");
		InputStream is = new FileInputStream(inputPath);
		return is;
	}
	
	/**
	 * 提交post上传文件请求
	 * @param url
	 * @param access_token
	 * @param filepath
	 */
	public String SubmitPostFile(String url,String access_token, String filepath){  
	        HttpClient httpclient = new DefaultHttpClient();
	        String result = "";//上传物料文件成功后返回的FileName参数
	        try {  
	            HttpPost httppost = new HttpPost(url);  
	              
	            FileBody bin = new FileBody(new File(filepath));  
	                
	            StringBody comment = new StringBody(access_token);  
	  
	            MultipartEntity reqEntity = new MultipartEntity();  
	              
	            reqEntity.addPart("file1", bin);//file1为请求后台的File upload属性      
	            reqEntity.addPart("access_token", comment);//filename1为请求后台的普通参数属性     
	            httppost.setEntity(reqEntity);  
	            HttpResponse response = httpclient.execute(httppost);  
	            int statusCode = response.getStatusLine().getStatusCode();  
	              
	            if(statusCode == HttpStatus.SC_OK){  
	                System.out.println("服务器正常响应.....");  
	                HttpEntity resEntity = response.getEntity();  
	                String responseMsg = EntityUtils.toString(resEntity,"UTF-8");
	                JSONObject jsonObject=JSONObject.fromObject(responseMsg);
	        		String getName = jsonObject.getString("FileNames");
	                EntityUtils.consume(resEntity); 
	                if(StringUtils.isNotBlank(getName)){
	                	result = getName.substring(2, getName.length()-2);
	                }
	            }  
	        	} catch (ParseException e) {  
	                e.printStackTrace();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            } finally {  
	                try {   
	                    httpclient.getConnectionManager().shutdown();   
	                } catch (Exception e) {  
	                    e.printStackTrace();
	                }  
	            } 
	        	return result;
	        }
	
	/**
	 * 提交post带参请求
	 * @param access_token
	 * @param name
	 * @param filename
	 */
	public void postForm(String[] param,String[] param2,String url) {  
        // 创建默认的httpClient实例.    
    	HttpClient httpclient = new DefaultHttpClient();  
        // 创建httppost    
        HttpPost httppost = new HttpPost(Const.DEVICE_URL + url);  
        // 创建参数队列    
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for(int i = 0 ;i<param.length;i++){
        	formparams.add(new BasicNameValuePair(param[i], param2[i])); 
        	System.out.println(param[i]);
        	System.out.println(param2[i]);
        }
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
            System.out.println("executing request:" + httppost.getURI());  
            HttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                    System.out.println("--------------------------------------");  
                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));  
                    System.out.println("--------------------------------------");  
                }  
            } finally {  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            httpclient.getConnectionManager().shutdown();   
        }  
    } 
	
	public String downLoadFile(){
		return SUCCESS;
	}
	
	public AdvertInfo getAdvertInfo() {
		return advertInfo;
	}

	public void setAdvertInfo(AdvertInfo advertInfo) {
		this.advertInfo = advertInfo;
	}

	public File getAdInfoUpload() {
		return adInfoUpload;
	}

	public void setAdInfoUpload(File adInfoUpload) {
		this.adInfoUpload = adInfoUpload;
	}

	public String getAdInfoUploadFileName() {
		return adInfoUploadFileName;
	}

	public void setAdInfoUploadFileName(String adInfoUploadFileName) {
		this.adInfoUploadFileName = adInfoUploadFileName;
	}

	public String getAdInfoUploadContentType() {
		return adInfoUploadContentType;
	}

	public void setAdInfoUploadContentType(String adInfoUploadContentType) {
		this.adInfoUploadContentType = adInfoUploadContentType;
	}
	
}
