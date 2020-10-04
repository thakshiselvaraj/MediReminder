package com.example.medicineapp;

import android.text.Editable;

public class Medicine {
    public String mediname;
    public String type;
    public Float dosage;
    public String time;

    public Medicine() {
    }

    public String getMediname() {
        return mediname;
    }

    public void setMediname(String mediname) {
        this.mediname = mediname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getDosage() {
        return dosage;
    }

    public void setDosage(Float dosage) {
        this.dosage = dosage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
