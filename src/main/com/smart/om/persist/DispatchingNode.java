package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * DispatchingNode entity. @author MyEclipse Persistence Tools
 */

public class DispatchingNode extends BasePo {

	// Fields

	private Integer dispatchingNodeId;
	private Integer planId;
	private Integer lineNodeId;
	private Integer noSort;
	private String isFinish;

	// Constructors

	/** default constructor */
	public DispatchingNode() {
	}

	/** full constructor */
	public DispatchingNode(Integer planId, Integer lineNodeId, Integer noSort) {
		this.planId = planId;
		this.lineNodeId = lineNodeId;
		this.noSort = noSort;
	}

	// Property accessors

	public Integer getDispatchingNodeId() {
		return this.dispatchingNodeId;
	}

	public void setDispatchingNodeId(Integer dispatchingNodeId) {
		this.dispatchingNodeId = dispatchingNodeId;
	}

	public Integer getPlanId() {
		return this.planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getLineNodeId() {
		return this.lineNodeId;
	}

	public void setLineNodeId(Integer lineNodeId) {
		this.lineNodeId = lineNodeId;
	}

	public Integer getNoSort() {
		return this.noSort;
	}

	public void setNoSort(Integer noSort) {
		this.noSort = noSort;
	}

	public String getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}

}