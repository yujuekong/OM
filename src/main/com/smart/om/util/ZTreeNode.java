package com.smart.om.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ZTree节点DTO
 * @author huanglian
 *
 */
public class ZTreeNode implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 5004070638433650715L;
	/**
	 * 节点ID
	 */
	private String id;
	/**
	 * 节点父ID
	 */
	private String pid;
	/**
	 * 节点业务指
	 */
	private String key;
	/**
	 * 节点层级
	 */
	private int level;
	/**
	 * 节点名称
	 */
	private String name;
	/**
	 * 节点是否打开
	 */
	private boolean open;

	/**
	 * 节点是否选中
	 */
	private boolean checked;
	/**
	 * 节点图标
	 */
	private String icon;

	/**
	 * 子节点集合
	 */
	private List<ZTreeNode> children = new ArrayList<ZTreeNode>();

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public List<ZTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<ZTreeNode> children) {
		this.children = children;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
