package com.drugs.manage.entity;

/**
 * Created by zyliu on 2019/9/22.
 */
public class Inventory {
    private Integer id;
    private String code;
    private String name;
    private String model;
    private String unit;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    @Override
    public String toString() {
        return "InventoryService{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", unit='" + unit + '\'' +
                ", warehouseNum='" + warehouseNum + '\'' +
                ", outgoingNum='" + outgoingNum + '\'' +
                ", inventoryNum='" + inventoryNum + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
