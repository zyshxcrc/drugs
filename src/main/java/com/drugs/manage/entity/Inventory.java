package com.drugs.manage.entity;

/**
 * Created by zyliu on 2019/9/22.
 */
public class Inventory {
    private Integer id;
    private String drugCode;
    private String drugName;
    private String drugModel;
    private String drugUnit;
    private String warehouseNum;
    private String outgoingNum;
    private String inventoryNum;
    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWarehouseNum() {
        return warehouseNum;
    }

    public void setWarehouseNum(String warehouseNum) {
        this.warehouseNum = warehouseNum;
    }

    public String getOutgoingNum() {
        return outgoingNum;
    }

    public void setOutgoingNum(String outgoingNum) {
        this.outgoingNum = outgoingNum;
    }

    public String getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(String inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    public String getDrugModel() {
        return drugModel;
    }

    public void setDrugModel(String drugModel) {
        this.drugModel = drugModel;
    }

    public String getDrugUnit() {
        return drugUnit;
    }

    public void setDrugUnit(String drugUnit) {
        this.drugUnit = drugUnit;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", drugCode='" + drugCode + '\'' +
                ", drugName='" + drugName + '\'' +
                ", drugModel='" + drugModel + '\'' +
                ", drugUnit='" + drugUnit + '\'' +
                ", warehouseNum='" + warehouseNum + '\'' +
                ", outgoingNum='" + outgoingNum + '\'' +
                ", inventoryNum='" + inventoryNum + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
