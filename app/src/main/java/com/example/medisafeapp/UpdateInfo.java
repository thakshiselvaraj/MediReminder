package com.example.medisafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UpdateInfo extends AppCompatActivity {
    private Button update_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        update_save = (Button) findViewById(R.id.update_save);
        update_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_mainpage();
            }
        });

    }

    public void open_mainpage(){
        Intent intent1 = new Intent(this,mainpage.class);
        startActivity(intent1);
    }
}