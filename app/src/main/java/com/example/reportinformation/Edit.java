package com.example.reportinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class Edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Button btnsave ;
        final TextView tname,gender,tblood,tbmi,tbloodpressure;
         EditText tCholestrol,tcholesdescription,tdiabetes,tHealth,tallergy;
         Spinner sgender,sblood;
        String id;
        Member selctedMember;

        Intent i = getIntent();
        id = i.getStringExtra("id");
        selctedMember = (Member) i.getSerializableExtra("data");

        tname=findViewById(R.id.tname);
        gender=findViewById(R.id.gender);
        tblood=findViewById(R.id.tblood);
        tbmi=findViewById(R.id.tbmi);
        tbloodpressure=findViewById(R.id.tbloodpressure);
        tCholestrol=findViewById(R.id.tCholestrol);
        tcholesdescription=findViewById(R.id.tcholesdescription);
        tdiabetes=findViewById(R.id.tdiabetes);
        tHealth=findViewById(R.id.tHealth);
        tallergy=findViewById(R.id.tallergy);
        sgender=findViewById(R.id.sgender);
        sblood=findViewById(R.id.sblood);
        btnsave=findViewById(R.id.btnsave);

        sgender.setAdapter(AppData.getBloodGroupAdapter(this));
        sblood.setAdapter(AppData.getGenderAdapter(this));


        sgender.setSelection(Arrays.asList(AppData.Gender).indexOf(selctedMember.getGender()));
        sblood.setSelection(Arrays.asList(AppData.BloodGroup).indexOf(selctedMember.getTblood()));

        tname.setText(selctedMember.getTname());
        gender.setText(selctedMember.getGender());
        tblood.setText(selctedMember.getTblood());
        tbmi.setText(selctedMember.getTbmi());
        tbloodpressure.setText(selctedMember.getTbloodpressure());
        tCholestrol.setText(selctedMember.gettCholestrol());
        tcholesdescription.setText(selctedMember.getTcholesDescription());
        tdiabetes.setText(selctedMember.getTdiabetes());
        tHealth.setText(selctedMember.gettHealth());
        tallergy.setText(selctedMember.getTallergy());


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Database.getInstance.update(new Member(tname.getText().toString(), gender.getText().toString(), tblood.getText().toString(), tbmi.getText().toString(), tbloodpressure.getText().toString(), sbudget.getSelectedItem().toString(), sduration.getSelectedItem().toString(), tdescription.getText().toString()), id);
                Toast.makeText(Edit.this, "Successfully updated", Toast.LENGTH_LONG).show();

            }

        });

    }
}