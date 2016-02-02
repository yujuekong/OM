package com.smart.om.dao.base;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 基类
 */
public abstract class BasePo implements Serializable {
	// 主键类的元数据
	private Class pkClazz = null;
	// 表示PO实例对象是否是新建的
	private boolean newer = false;
	
	public BasePo() {
	}

	/** 两个实例是否相等 */
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (this == o) {
			return true;
		}
		if (getClass() != o.getClass()) {
			return false;
		}
		return EqualsBuilder.reflectionEquals(this, o);
	}

	public Class getPkClazz() {
		return pkClazz;
	}

	public void setPkClazz(Class pkClazz) {
		this.pkClazz = pkClazz;
	}

	public boolean isNewer() {
		return newer;
	}

	public void setNewer(boolean newer) {
		this.newer = newer;
	}
}

