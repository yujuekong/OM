jQuery(function($) {
	$("#submenu-sys-fun-op").addClass("active");
	$("#menu-sys").addClass("active");
	$("#menu-sys").addClass("open");
	var orgId = $("#orgId").val();
	if(orgId){
		$("#appendButton").attr("hidden","hidden");
		$("#delRole").removeAttr("onclick");
	}
});
function delRole(roleId,roleName){
	var orgId = $("#orgId").val();
	if(orgId){
		alert("您没有权限删除角色！");
		return;
	}
	if(confirm("您确认删除角色：'"+roleName+" '吗?")){
		$.ajax({
			url: ROOT_PATH +'/view/sys/sysRole/delRole.action',
			type:'POST',
			data:"roleId=" + roleId,
			dataType:'json',
			success:function(data){					
				if(data){
					alert("删除成功！");
				}
				else{
					alert("删除失败！");
				}
				var url = ROOT_PATH + "/view/sys/sysRole/queryRole.action";
				var gotoLink = document.createElement('a');  
			    gotoLink .href = url;  
		        gotoLink.id="delSeller";
		        document.body.appendChild(gotoLink);  
		        gotoLink .click();  
		        $("#delSeller").remove();
			}
		});
	}
}

function preAddUserToRole(roleId){
	var url = ROOT_PATH + "/view/sys/sysRole/preAddEmpToRole.action?roleId="+roleId;
	location.href = url;
}

//全选
function checkAll(selectObj){
	var checkBoxs= document.getElementsByTagName("input");
	for(var i=0;i<checkBoxs.length;i++)
	{
		if(checkBoxs[i].type == "checkbox")
			checkBoxs[i].checked = selectObj.checked;
	}
}
//检查是否checkbox被选中，如果有一个checkbox被选中就返回true
function isCheck(){
    var isCheck =false;
	var checkBoxs= document.getElementsByTagName("input");
	for(var i=0;i<checkBoxs.length;i++)
	{
		if(checkBoxs[i].type == "checkbox" && checkBoxs[i].checked == true)
		{
		    isCheck = true;
		}
	}
	return isCheck;
}

//添加员工到角色
function addUserToRole(roleId,empIds){
	if(!isCheck()){
		alert('请先选择要添加的员工！'); 
		return false;
		}
	else {
		alert('添加的员工！');
	}	
}

//后台菜单权限设置
function setTree(roleId,servState,isOrg){
//	var url = ROOT_PATH + "/view/sys/sysRole/setMenu.action?servState="+servState+"&roleId="+roleId+"&menuTypeOne=1";
//	location.href = url;
	$("#roleId").val(roleId);
	if(servState == 'TREE'){
		$("#menutype").val("1");
	}
	if(servState == 'APP'){
		$("#menutype").val("2");
	}
	if(isOrg == '' || isOrg == undefined || isOrg == null){
		$("#roleButton").removeAttr("hidden");
	}
	initMenuTree();
	$('#dict_choise_modal').modal('show');
}

//app菜单权限设置
function setTreeApp(roleId,servState,isOrg){
//	var url = ROOT_PATH + "/view/sys/sysRole/setMenu.action?servState="+servState+"&roleId="+roleId+"&menuTypeTwo=2";
//	location.href = url;
	$("#roleId").val(roleId);
	if(servState == 'TREE'){
		$("#menutype").val("1");
	}
	if(servState == 'APP'){
		$("#menutype").val("2");
	}
	if(isOrg == '' || isOrg == undefined || isOrg == null){
		$("#roleButton").removeAttr("hidden");
	}
	initMenuTree();
	$('#dict_choise_modal').modal('show');
}

//打开添加角色
function openRole(){
	//隐藏底部批量操作的按钮
	//$("#choise_goods_action").hide();
	//$('#role_choise_modal').modal('show');
	if(role_choise_modal.style.display=="none"){
		var orgId =$("#orgId").val();
		if(orgId){
			$("#company").val("1");
			$("#company").attr("disabled","disabled");
		}
		role_choise_modal.style.display="";
		submit_choise_modal.style.display="";
	}
	else if(role_choise_modal.style.display==""){
		role_choise_modal.style.display="none";
		submit_choise_modal.style.display="none";
	}
}

function addRole(){
	//var roleName = $("#roleName").val("");
	//alert(roleName);
	$("#role_choise_modal").modal('hide');
	$("#submit_choise_modal").modal('hide');
}

//表单提交验证
function check() {
	var flag = true;
	$("form :input.required").each(function(){
       var val = $(this).val();
       if(!val) {
    	   flag = false;
       }
    });
	if(!flag) {
		alert("请输入角色名称！");
	}
	return flag;
}

