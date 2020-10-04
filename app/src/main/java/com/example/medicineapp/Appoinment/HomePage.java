package com.example.medicineapp.Appoinment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicineapp.MainActivity;
import com.example.medicineapp.R;

public class HomePage extends AppCompatActivity {

    Button buttonInfo, buttonMed, buttonAppo, buttonWater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        buttonInfo = findViewById(R.id.buttonInfo);
        buttonMed = findViewById(R.id.buttonMed);
        buttonAppo = findViewById(R.id.buttonAppo);
        buttonWater = findViewById(R.id.buttonWater);

        buttonAppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                openDetailPage();

            }
        });
        buttonMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmedicine();
            }
        });
    }

    public void openmedicine(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openDetailPage(){
        Intent intent = new Intent(this, activityAppoinment.class);
        startActivity(intent);
    }



}
