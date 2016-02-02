package com.smart.om.persist;

import com.smart.om.dao.base.BasePo;

/**
 * SellerInfo entity. @author MyEclipse Persistence Tools
 */

public class SellerInfo extends BasePo {

    // Fields

    private Integer sellerId;
    private String sellerName;
    private String sellerNo;
    private String linkMan;
    private String linkTel;
    private String sellerType;
    private String sellerGoodsType;
    private String sellerStatus;
    private String createDate;
    private Integer dictRegionId;
    private Integer dictProviceId;
    private Integer dictOrgId;
    private String sellerAddress;
    private String contractStartDate;
    private String contractEndDate;
    private String linkMail;
//    private String sellerGoodsDesc;


    private SysDict dictRegion;
    private SysDict dictProvice;
    // Constructors

    /**
     * default constructor
     */
    public SellerInfo() {
    }

    /**
     * full constructor
     */
    public SellerInfo(String sellerName, String sellerNo, String linkMan,
                      String linkTel, String sellerType, String sellerGoodsType,
                      String sellerStatus, String createDate, Integer dictRegionId,
                      Integer dictProviceId, String sellerAddress,
                      String contractStartDate, String contractEndDate, String linkMail,
                      String sellerGoodsDesc) {
        this.sellerName = sellerName;
        this.sellerNo = sellerNo;
        this.linkMan = linkMan;
        this.linkTel = linkTel;
        this.sellerType = sellerType;
        this.sellerGoodsType = sellerGoodsType;
        this.sellerStatus = sellerStatus;
        this.createDate = createDate;
        this.dictRegionId = dictRegionId;
        this.dictProviceId = dictProviceId;
        this.sellerAddress = sellerAddress;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.linkMail = linkMail;
//        this.sellerGoodsDesc = sellerGoodsDesc;
    }

    // Property accessors

    public Integer getSellerId() {
        return this.sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return this.sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerNo() {
        return this.sellerNo;
    }

    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo;
    }

    public String getLinkMan() {
        return this.linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkTel() {
        return this.linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public String getSellerType() {
        return this.sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    public String getSellerGoodsType() {
        return this.sellerGoodsType;
    }

    public void setSellerGoodsType(String sellerGoodsType) {
        this.sellerGoodsType = sellerGoodsType;
    }

    public String getSellerStatus() {
        return this.sellerStatus;
    }

    public void setSellerStatus(String sellerStatus) {
        this.sellerStatus = sellerStatus;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getDictRegionId() {
        return this.dictRegionId;
    }

    public void setDictRegionId(Integer dictRegionId) {
        this.dictRegionId = dictRegionId;
    }

    public Integer getDictProviceId() {
        return this.dictProviceId;
    }

    public void setDictProviceId(Integer dictProviceId) {
        this.dictProviceId = dictProviceId;
    }

    public String getSellerAddress() {
        return this.sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getContractStartDate() {
        return this.contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public String getContractEndDate() {
        return this.contractEndDate;
    }

    public void setContractEndDate(String contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getLinkMail() {
        return this.linkMail;
    }

    public void setLinkMail(String linkMail) {
        this.linkMail = linkMail;
    }

//    public String getSellerGoodsDesc() {
//        return this.sellerGoodsDesc;
//    }
//
//    public void setSellerGoodsDesc(String sellerGoodsDesc) {
//        this.sellerGoodsDesc = sellerGoodsDesc;
//    }

    public SysDict getDictRegion() {
        return dictRegion;
    }

    public void setDictRegion(SysDict dictRegion) {
        this.dictRegion = dictRegion;
    }

    public SysDict getDictProvice() {
        return dictProvice;
    }

    public void setDictProvice(SysDict dictProvice) {
        this.dictProvice = dictProvice;
    }

    public Integer getDictOrgId() {
        return dictOrgId;
    }

    public void setDictOrgId(Integer dictOrgId) {
        this.dictOrgId = dictOrgId;
    }
}