//新增非空验证
function checkBlank(){
	var result = true;
	var advertUserName = $("#advertUserName").val();
	var advertUserRealName = $("#advertUserRealName").val();
	var advertUserLevel = $("#advertUserLevel").val();
	var advertUserTel = $("#advertUserTel").val();
	
	if(!advertUserName){
		$("#userError").html("<div></div><font color='red'>广告主姓名不能为空!</font>");
		result = false;
	}else if(!advertUserRealName){
		$("#realError").html("<div></div><font color='red'>联系人姓名不能为空！</font>");
		result = false;
	}else if(!advertUserTel){
		$("#telError").html("<div></div><font color='red'>联系人电话不能为空!</font>");
		result = false;
	}else if(advertUserLevel == "-1"){
		$("#levelError").html("<div></div><font color='red'>广告主级别不能为空!</font>");
		result = false;
	}
	return result;
}

function userError(){
	var advertUserName = $("#advertUserName").val(); 
	if(advertUserName){
		$("#userError").html("");
	}
	else{
		$("#userError").html("<div></div><font color='red'>广告主姓名不能为空!</font>");
	}
}

function realError(){
	var advertUserRealName = $("#advertUserRealName").val();
	if(advertUserRealName){
		$("#realError").html("");
	}
	else{
		$("#realError").html("<div></div><font color='red'>联系人姓名不能为空！</font>");
	}
}

function telError(){
	var advertUserTel = $("#advertUserTel").val();
	if(advertUserTel){
		if(isNaN(advertUserTel)){
			$("#telError").html("<div></div><font color='red'>联系人电话必须为数字!</font>");
			$("#advertUserTel").val("");
		}
		else if(advertUserTel.length > 20){
			$("#telError").html("<div></div><font color='red'>联系人电话超过最大长度!</font>");
			$("#advertUserTel").val("");
		}
		else{
			$("#telError").html("");
		}
	}
	else{
		$("#telError").html("<div></div><font color='red'>联系人电话不能为空!</font>");
	}
}

function levelError(){
	var advertUserLevel = $("#advertUserLevel").val();
	if(advertUserLevel != "-1"){
		$("#levelError").html("");
	}
	else{
		$("#levelError").html("<div></div><font color='red'>广告主级别不能为空!</font>");
	}
}