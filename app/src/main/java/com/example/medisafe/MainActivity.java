package com.example.medisafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText txtCname, txtSpecial,txtDocName, txtadd, txtphone;
    Button button;
    DatabaseReference dbRef;
    Appointment appointment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCname = (EditText)findViewById(R.id.eTTCenterName);
        txtSpecial = (EditText)findViewById(R.id.eTTSpeciality);
        txtDocName= (EditText)findViewById(R.id.eTTdocName);
        txtadd = (EditText)findViewById(R.id.eTTAddress);
        txtphone = (EditText)findViewById(R.id.eTTPhone);

        button = (Button) findViewById(R.id.button);
        appointment = new Appointment();
        dbRef = FirebaseDatabase.getInstance().getReference().child("Appointment");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int phone = Integer.parseInt(txtphone.getText().toString().trim());

                appointment.setName(txtCname.getText().toString().trim());
                appointment.setSpecial(txtSpecial.getText().toString().trim());
                appointment.setDocName(txtDocName.getText().toString().trim());
                appointment.setAddress(txtadd.getText().toString().trim());
                appointment.setPhone(phone);

                dbRef.child("Appointment1").setValue(appointment);

                Toast.makeText(MainActivity.this, "saved successfully",Toast.LENGTH_LONG).show();
                

            }


        });
    }


}