package com.example.medisafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    }

    public void openDetailPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}