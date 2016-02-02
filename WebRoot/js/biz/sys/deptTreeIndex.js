jQuery(function($) {
	var str = $("#str").val();
	if(str == "1"){
		$("#selectCompany").attr("hidden","hidden");
	}
	else if(str == "0"){
		$("#checkCompany").attr("disabled","disabled");
	}
});

function companyChange(){
	var checkCompany = $("#checkCompany").val();
	var roleId = $("#roleId").val();
	if(checkCompany != null){
		window.location.href = ROOT_PATH + "/view/sys/sysRole/preAddEmpToRole.action?dictId=" + checkCompany + "&roleId=" + roleId;
	}
}