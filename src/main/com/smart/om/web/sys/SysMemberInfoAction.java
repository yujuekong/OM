package com.smart.om.web.sys;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.JSONUtil;

import com.smart.om.biz.sys.SysAssistHandler;
import com.smart.om.biz.sys.SysFuncHandler;
import com.smart.om.persist.SysContract;
import com.smart.om.persist.SysDict;
import com.smart.om.persist.SysMember;
import com.smart.om.util.Const;
import com.smart.om.util.DTablePageModel;
import com.smart.om.util.DateUtil;
import com.smart.om.web.base.BaseAction;

/**
 * 会员管理
 * @author liuz
 *
 */
@Namespace("/view/sys/sysMemberInfo")
@Results({@Result(name = "error", location = "/view/error.jsp")})
public class SysMemberInfoAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(SysMemberInfoAction.class);
	
	@Resource
	private SysFuncHandler sysFuncHandler;

    @Resource
    private SysAssistHandler sysAssistHandler;
    
    private SysMember memberInfo;
    /**
     * 会员管理列表分页
     */
    @Action(value = "querySysMemberPage")
    public void querySysMemberPage(){
        try {
            SysDict sysDict = sysAssistHandler.queryDictByPcode("AL_POSITION");
            //搜索关键字
            String memberNo = this.getRequestParm().getParameter("sSearch");
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("memberNo", memberNo);
            DTablePageModel dtPageModel = sysFuncHandler.querySysMemberPage(params, super.getPageData());
            String jsonData = JSONUtil.serialize(dtPageModel);
            PrintWriter pw = super.getResponse().getWriter();
            pw.write(jsonData);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
	 * 进入添加或编辑设备信息页面
	 */
	@Action(value = "editorSysMemberById", results = {
			@Result(name = SUCCESS, location = "/view/sys/memberInfoEditor.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String editorSysMemberById() {
		String result = SUCCESS;
		try{
			String memberId = this.getRequestParm().getParameter("memberId");
			getRequest().put("memberId", memberId);
			if(memberId.equals("New")){//新增
//				List list= sysFuncHandler.querySysMemberNo();
//				if(!list.isEmpty() && list!=null){
//					SysMember memberInfo =new SysMember();
//					String member = (String)list.get(0);
//					if(!"".equals(member) && null!=member){
//						Integer num = Integer.parseInt(member)+1;
//						memberInfo.setMemberNo(num.toString());
//						getRequest().put("memberInfo", memberInfo);
//					}else{
//						memberInfo.setMemberNo("100000");
//						getRequest().put("memberInfo", memberInfo);
//					}
//				}else{
//					memberInfo.setMemberNo("100000");
//					getRequest().put("memberInfo", memberInfo);
//				}
				return result;
			}
			if(StringUtils.isNotBlank(memberId) && !memberId.equals("New")){//查看，编辑
				String view = this.getRequestParm().getParameter("view");
				Integer dId =Integer.valueOf(memberId);
				SysMember memberInfo = (SysMember) sysFuncHandler.editorSysMemberById(dId);
				getRequest().put("memberInfo", memberInfo);
				getRequest().put("view", view);
			}
		}catch (Exception e) {
            this.getRequest().put("where", " 查询会员个人信息");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}
    
	/**
	 * 添加或修改会员信息
	 */
	@Action(value = "saveMemberInfo", results = {@Result(name = SUCCESS, type = "redirect",location = "/view/sys/memberList.jsp"),
			@Result(name = ERROR, location = "/view/error.jsp") })
	public String saveMemberInfo(){
		String result = SUCCESS;
		try{
			if(memberInfo.getMemberId()==null){
				memberInfo.setNewer(true);
			}else{
				memberInfo.setNewer(false);
			}
			sysFuncHandler.saveOrUpdateMemberInfoDAO(memberInfo);
		}catch (Exception e) {
            this.getRequest().put("where", " 查询所有数据字典");
            this.getRequest().put("errors", "系统正忙，请联系管理员");
            result = ERROR;
        }
		return result;
	}
	
	//删除会员
	@Action(value="deleteSysMemberById",results={
			@Result(name=SUCCESS,location="/view/sys/memberList.jsp"),
			@Result(name=ERROR,location="/view/error.jsp")
	})
	public String deleteSysMemberById(){
		try {
			String memberId = this.getRequestParm().getParameter("memberId");
			SysMember memberInfo = sysFuncHandler.deleteSysMemberById(Integer.valueOf(memberId));
		} catch (Exception e) {
			e.printStackTrace();
			this.getRequest().put("errors", "系统异常，请重试！");
			return ERROR;
		}
		return SUCCESS;
	}

	public SysMember getMemberInfo() {
		return memberInfo;
	}

	public void setMemberInfo(SysMember memberInfo) {
		this.memberInfo = memberInfo;
	}
}
