package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * SysContract entity. @author MyEclipse Persistence Tools
 */

public class SysContract extends BasePo {

	// Fields

	private Integer contractId;
	private String contractNo;
	private String contractName;
	private String contractType;
	private Integer contractSeller;
	private String startDate;
	private String endDate;
	private String createDate;
	private String contractStatus;
	private Double contractFee;
	private String feeType;
	private String feeStandard;
	private Double disRate;
	private String payMethod;
	private String payCycle;
	private String isPay;
	private String isDel;
	private Integer orgId;
	private Integer dictRegionId;
	private Integer dictProviceId;
	// Constructors

	/** default constructor */
	public SysContract() {
	}

	/** full constructor */
	public SysContract(String contractNo, String contractName,
			String contractType, Integer contractSeller, String startDate,
			String endDate, String createDate, String contractStatus,
			Double contractFee, String feeType, String feeStandard,
			Double disRate, String payMethod, String payCycle, String isPay,
			String isDel) {
		this.contractNo = contractNo;
		this.contractName = contractName;
		this.contractType = contractType;
		this.contractSeller = contractSeller;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createDate = createDate;
		this.contractStatus = contractStatus;
		this.contractFee = contractFee;
		this.feeType = feeType;
		this.feeStandard = feeStandard;
		this.disRate = disRate;
		this.payMethod = payMethod;
		this.payCycle = payCycle;
		this.isPay = isPay;
		this.isDel = isDel;
	}

	// Property accessors

	public Integer getContractId() {
		return this.contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractName() {
		return this.contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractType() {
		return this.contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public Integer getContractSeller() {
		return this.contractSeller;
	}

	public void setContractSeller(Integer contractSeller) {
		this.contractSeller = contractSeller;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getContractStatus() {
		return this.contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public Double getContractFee() {
		return this.contractFee;
	}

	public void setContractFee(Double contractFee) {
		this.contractFee = contractFee;
	}

	public String getFeeType() {
		return this.feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getFeeStandard() {
		return this.feeStandard;
	}

	public void setFeeStandard(String feeStandard) {
		this.feeStandard = feeStandard;
	}

	public Double getDisRate() {
		return this.disRate;
	}

	public void setDisRate(Double disRate) {
		this.disRate = disRate;
	}

	public String getPayMethod() {
		return this.payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getPayCycle() {
		return this.payCycle;
	}

	public void setPayCycle(String payCycle) {
		this.payCycle = payCycle;
	}

	public String getIsPay() {
		return this.isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	public String getIsDel() {
		return this.isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getDictRegionId() {
		return dictRegionId;
	}

	public void setDictRegionId(Integer dictRegionId) {
		this.dictRegionId = dictRegionId;
	}

	public Integer getDictProviceId() {
		return dictProviceId;
	}

	public void setDictProviceId(Integer dictProviceId) {
		this.dictProviceId = dictProviceId;
	}

}