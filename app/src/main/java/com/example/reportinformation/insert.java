package com.example.reportinformation;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

public class insert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        Button btnsave ;
        TextView tname,gender,tblood,tbmi,tbloodpressure;
        final EditText tCholestrol,tcholesdescription,tdiabetes,tHealth,tallergy;
        final Spinner sgender,sblood;


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

        sgender.setAdapter(AppData.getBudgetsAdapter(this));
        sblood.setAdapter(AppData.getBudgetsAdapter(this));

        btnsave.setOnClickListner(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Database.getInstance.insert(new Member(tname.getText().toString(), gender.getText().toString(),sgender.getSelectedItem().toString(), tblood.getText().toString(),sblood.getSelectedItem().toString(), tbmi.getText().toString(), tbloodpressure.getText().toString(), tCholestrol.getText().toString(),tcholesdescription.getText().toString(),tdiabetes.getText().toString(),tHealth.getText().toString(),tallergy.getText().toString(), sblood.getSelectedItem().toString()));
                Toast.makeText(insert.this, "Successfully saved", Toast.LENGTH_LONG).show();
            }

            });


}
  }