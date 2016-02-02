$("#submenu-sys-fun-op").addClass("active");
$("#menu-sys-fun").addClass("active");
$("#menu-sys-fun").addClass("open");
$("#menu-sys").addClass("active");
$("#menu-sys").addClass("open");

//jQuery(function($) {
//	//初始化菜单树
//	initMenuTree();
//});

function initMenuTree() {
	var setting = {
		check: {
			enable: true  //设置 zTree 的节点上是否显示 checkbox / radio
		},
		data: {
			simpleData: {
				enable: true  //确定 zTree 初始化时的节点数据、异步加载时的节点数据、或 addNodes 方法中输入的 newNodes 数据是否采用简单数据模式 (Array)
			}
		}
	};

	var treeObj = $("#menu_tree");
	var roleId = $("#roleId").val();
	var menutype = $("#menutype").val();
	var zNodes;
	$.ajax({
		type : "POST",
		url : "getMenuTreeData.action",
		async : false,
		data : {"roleId" : roleId,"menutype":menutype},
		dataType : "json",
		success : function(data) {
			zNodes = data;
			$.fn.zTree.init(treeObj, setting, zNodes);
		}
	});

	
}

function updateAuth(e,treeId,treeNode){
	var roleId = $("#roleId").val();
    var treeObj=$.fn.zTree.getZTreeObj("menu_tree");
    var nodes=treeObj.getCheckedNodes(true);
    var treeIds = "";
    var type=$("#menutype").val();
    for(var i=0;i<nodes.length;i++){
    	treeIds+=nodes[i].id + ",";
    }
    if(confirm("您确认添加或删除权限吗?")){
		$.ajax({
			url: ROOT_PATH + '/view/sys/sysRole/updateAuth.action',
			type:'POST',
			data: {
					"roleId" : roleId,
					"servState" : type,
	        		"id" : treeIds
	        },
			dataType:'json',
			success:function(data){	
				if(data){
					alert("提交成功！");
				}
				else{
					alert("提交失败！");
				}
//				var url = ROOT_PATH + '/view/sys/sysRole/setMenu.action?menutype=' + data;
//				var gotoLink = document.createElement('a');  
//			    gotoLink .href = url;  
//		        gotoLink.id="queryMenu";
//		        document.body.appendChild(gotoLink);  
//		        gotoLink .click();  
//		        $("#queryMenu").remove();
			}
		});
	}
}


