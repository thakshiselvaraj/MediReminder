package com.example.medicineapp.Appoinment;

public class Appointment {
    private String name;
    private String special;
    private String docName;
    private String address;
    private Integer phone;
    private String date;
    private String time;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
