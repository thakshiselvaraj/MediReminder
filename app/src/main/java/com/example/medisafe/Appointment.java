package com.example.medisafe;

public class Appointment {

    private String name;
    private String special;
    private String docName;
    private String address;
    private Integer phone;

    public Appointment() {
    }

    public String getName() {
        return name;
    }

    public String getSpecial() {
        return special;
    }

    public String getDocName() {
        return docName;
    }

    public String getAddress() {
        return address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}
