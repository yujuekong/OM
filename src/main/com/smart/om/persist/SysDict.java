package com.smart.om.persist;

import com.smart.om.dao.base.BaseDao;
import com.smart.om.dao.base.BasePo;

/**
 * SysDict entity. @author MyEclipse Persistence Tools
 */

public class SysDict extends BasePo{

	// Fields

	private Integer dictId;
	private Integer dictPid;
	private String dictName;
	private String dictCode;
	private Integer dictSort;
	private String dictPcode;
	private String dictDesc;
	private Integer dictLevel;
	private String dictTopCode;

	private SysDict sysDictPid;

	// Constructors

	/** default constructor */
	public SysDict() {
	}

	/** full constructor */
//	public SysDict(Integer dictPid, String dictName, String dictCode,
//			Integer dictSort, String dictPcode, String dictDesc,
//			Integer dictLevel) {
//		this.dictPid = dictPid;
//		this.dictName = dictName;
//		this.dictCode = dictCode;
//		this.dictSort = dictSort;
//		this.dictPcode = dictPcode;
//		this.dictDesc = dictDesc;
//		this.dictLevel = dictLevel;
//	}

	// Property accessors

	public Integer getDictId() {
		return this.dictId;
	}

	public void setDictId(Integer dictId) {
		this.dictId = dictId;
	}

	public Integer getDictPid() {
		return this.dictPid;
	}

	public void setDictPid(Integer dictPid) {
		this.dictPid = dictPid;
	}

	public String getDictName() {
		return this.dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictCode() {
		return this.dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public Integer getDictSort() {
		return this.dictSort;
	}

	public void setDictSort(Integer dictSort) {
		this.dictSort = dictSort;
	}

	public String getDictPcode() {
		return this.dictPcode;
	}

	public void setDictPcode(String dictPcode) {
		this.dictPcode = dictPcode;
	}

	public String getDictDesc() {
		return this.dictDesc;
	}

	public void setDictDesc(String dictDesc) {
		this.dictDesc = dictDesc;
	}

	public Integer getDictLevel() {
		return this.dictLevel;
	}

	public void setDictLevel(Integer dictLevel) {
		this.dictLevel = dictLevel;
	}

	public String getDictTopCode() {
		return dictTopCode;
	}

	public void setDictTopCode(String dictTopCode) {
		this.dictTopCode = dictTopCode;
	}

	public SysDict getSysDictPid() {
		return sysDictPid;
	}

	public void setSysDictPid(SysDict sysDictPid) {
		this.sysDictPid = sysDictPid;
	}
}