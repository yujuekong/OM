package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * StorageDebt entity. @author MyEclipse Persistence Tools
 */

public class StorageDebt extends BasePo {

    // Fields

    private Integer debtId;
    private String debtNo;
    private String debtDate;
    private Integer debtUser;
    private String debtStatus;
    private String debtLink;
    private String auditDesc;

    private SysUser sysUser;
    // Constructors

    /**
     * default constructor
     */
    public StorageDebt() {
    }

    /**
     * full constructor
     */
    public StorageDebt(String debtNo, String debtDate,
                       Integer debtUser, String debtStatus) {
        this.debtNo = debtNo;
        this.debtDate = debtDate;
        this.debtUser = debtUser;
        this.debtStatus = debtStatus;
    }

    // Property accessors

    public Integer getDebtId() {
        return this.debtId;
    }

    public void setDebtId(Integer debtId) {
        this.debtId = debtId;
    }

    public String getDebtNo() {
        return this.debtNo;
    }

    public void setDebtNo(String debtNo) {
        this.debtNo = debtNo;
    }


    public String getDebtDate() {
        return this.debtDate;
    }

    public void setDebtDate(String debtDate) {
        this.debtDate = debtDate;
    }

    public Integer getDebtUser() {
        return this.debtUser;
    }

    public void setDebtUser(Integer debtUser) {
        this.debtUser = debtUser;
    }

    public String getDebtStatus() {
        return this.debtStatus;
    }

    public void setDebtStatus(String debtStatus) {
        this.debtStatus = debtStatus;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public String getAuditDesc() {
        return auditDesc;
    }

    public void setAuditDesc(String auditDesc) {
        this.auditDesc = auditDesc;
    }

    public String getDebtLink() {
        return debtLink;
    }

    public void setDebtLink(String debtLink) {
        this.debtLink = debtLink;
    }
}

