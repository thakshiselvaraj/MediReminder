package com.example.reportinformation;

import android.content.Context;
import android.widget.ArrayAdapter;

public class AppData {
    public static String[] Gender={"Male","Female"};
    public static String[] BloodGroup={"A+","O+","B+","AB+","A-","O-","B-","AB-"};



    public static ArrayAdapter  getBloodGroupAdapter(Context c){
        ArrayAdapter BloodGroup=new ArrayAdapter(c,android.R.layout.simple_spinner_item, AppData.BloodGroup);

        BloodGroup.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return BloodGroup;
    }
    public static ArrayAdapter  getGenderAdapter(Context c){
        ArrayAdapter Gender=new ArrayAdapter(c,android.R.layout.simple_spinner_item, AppData.Gender);

        Gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        return Gender;
    }



}
