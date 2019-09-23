package com.drugs.manage.entity;

public class Receiver {
    private int id;
    private String receiverName;
    private String phone;
    private String address;
    private String createDate;
    private String remarks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Receiver{" +
                "id=" + id +
                ", receiverName='" + receiverName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", createDate='" + createDate + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
