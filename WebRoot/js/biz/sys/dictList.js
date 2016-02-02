jQuery(function($) {
	$("#submenu-menu-sys-dict").addClass("active");
	$("#menu-sys").addClass("active");
	$("#menu-sys").addClass("open");
});

//打开添加数据字典层
function preAddDict(){
	$('#dict_choise_modal').modal('show');
}
//打开修改的数据字典层
function preModifyDict(dictId,dictName,dictSort,dictDesc){
	$('#dict_choise_modal').modal('show');
	
}
//添加数据字典层
function addDict(dictId){
}
//删除菜单
function deleteDict(dictId,dictPid){
	if(confirm("您确认删除该数据字典吗?")){
		$.ajax({
			url: ROOT_PATH + '/view/sys/sysDict/delDict.action',
			type:'POST',
			data:{"dictId" : dictId },
			dataType:'json',
			success:function(data){					
				if(data){
					alert("删除成功！");
				}
				else{
					alert("删除失败！");
				}
				var url = ROOT_PATH + "/view/sys/sysDict/querySysDictByPid.action?dictId="+dictPid;
				var gotoLink = document.createElement('a');  
			    gotoLink.href = url;  
		        gotoLink.id="delDict";
		        document.body.appendChild(gotoLink);  
		        gotoLink .click();  
		        $("#delDict").remove();
			}
		});
	}
}
