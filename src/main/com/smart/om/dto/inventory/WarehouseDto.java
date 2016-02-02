package com.smart.om.dto.inventory;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/9/17.
 */
public class WarehouseDto implements Serializable{
    private static final long serialVersionUID = -2878094627666035249L;

    private Integer warehouseId;
    private String warehouseNo;
    private String warehouseName;
    private String warehouseAddress;
    private String warehouseSize;
    private String warehouseStatus;
    private String createDate;
    private String warehouseLng;
    private String warehouseLat;

    private Integer dictRegionId;
    private Integer dictProviceId;


    private String regionName;
    private String proviceName;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getProviceName() {
        return proviceName;
    }

    public void setProviceName(String proviceName) {
        this.proviceName = proviceName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public String getWarehouseSize() {
        return warehouseSize;
    }

    public void setWarehouseSize(String warehouseSize) {
        this.warehouseSize = warehouseSize;
    }

    public String getWarehouseStatus() {
        return warehouseStatus;
    }

    public void setWarehouseStatus(String warehouseStatus) {
        this.warehouseStatus = warehouseStatus;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getWarehouseLng() {
        return warehouseLng;
    }

    public void setWarehouseLng(String warehouseLng) {
        this.warehouseLng = warehouseLng;
    }

    public String getWarehouseLat() {
        return warehouseLat;
    }

    public void setWarehouseLat(String warehouseLat) {
        this.warehouseLat = warehouseLat;
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
