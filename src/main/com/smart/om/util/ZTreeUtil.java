package com.smart.om.util;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.struts2.json.JSONUtil;

public class ZTreeUtil {

	public static String toJson(ZTreeNode rootNode,List<?> list,String idFiled,String pidField,String key,String name) {
		String jsonData = "";
		try {
			setChildNodes(rootNode,list,idFiled,pidField,key,name);
			jsonData = JSONUtil.serialize(new ZTreeNode[]{rootNode});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return jsonData;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void setChildNodes(ZTreeNode node,List<?> list,String idFiled,String pidField,String key,String name){
		try {
			if(list != null){
				for(Object obj : list) {
					Class clazz = obj.getClass();
					Method method = null;
					method = clazz.getMethod("get" + idFiled.substring(0, 1).toUpperCase()+idFiled.substring(1));
					Object id = method.invoke(obj);
					
					method = clazz.getMethod("get" + pidField.substring(0, 1).toUpperCase()+pidField.substring(1));
					Object pid = method.invoke(obj);
					
					method = clazz.getMethod("get" + key.substring(0, 1).toUpperCase()+key.substring(1));
					Object nodeKey = method.invoke(obj);
					
					method = clazz.getMethod("get" + name.substring(0, 1).toUpperCase()+name.substring(1));
					Object nodeName = method.invoke(obj);
					if(pid == null) {
						pid = "";
					}
					if(node.getId() == null) {
						node.setId("");
					}
					if(node.getId().equals(pid.toString())) {
						ZTreeNode childNode = new ZTreeNode();
						childNode.setId(id.toString());
						childNode.setPid(node.getId());
						childNode.setKey(nodeKey.toString());
						childNode.setName(nodeName.toString());
						childNode.setOpen(false);
						childNode.setLevel(node.getLevel() + 1);
						
						setChildNodes(childNode,list,idFiled,pidField,key,name);
						node.getChildren().add(childNode);
					}
				}
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
