package com.drugs.manage.entity;

/**
 * Created by zyliu on 2019/9/22.
 */
public class Inventory {
    private Integer id;
    private String drugId;
    private String warehouseNum;
    private String outgoingNum;
    private String inventoryNum;
    private String remarks;
    private Drug drug;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
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

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", drugId='" + drugId + '\'' +
                ", warehouseNum='" + warehouseNum + '\'' +
                ", outgoingNum='" + outgoingNum + '\'' +
                ", inventoryNum='" + inventoryNum + '\'' +
                ", remarks='" + remarks + '\'' +
                ", drug=" + drug +
                '}';
    }
}
