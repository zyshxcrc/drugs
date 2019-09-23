package com.drugs.manage.entity;

public class Warehouse {
    private int id;
    private String drugCode;
    private String drugName;
    private String drugModel;
    private String drugUnit;
    private String unitPrice;
    private String money;
    private String drawNum;
    private String supplier;
    private String drawTime;
    private String sendOrderNo;
    private String remarks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDrawNum() {
        return drawNum;
    }

    public void setDrawNum(String drawNum) {
        this.drawNum = drawNum;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(String drawTime) {
        this.drawTime = drawTime;
    }

    public String getSendOrderNo() {
        return sendOrderNo;
    }

    public void setSendOrderNo(String sendOrderNo) {
        this.sendOrderNo = sendOrderNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", drugCode='" + drugCode + '\'' +
                ", drugName='" + drugName + '\'' +
                ", drugModel='" + drugModel + '\'' +
                ", drugUnit='" + drugUnit + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", money='" + money + '\'' +
                ", drawNum='" + drawNum + '\'' +
                ", supplier='" + supplier + '\'' +
                ", drawTime='" + drawTime + '\'' +
                ", sendOrderNo='" + sendOrderNo + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
