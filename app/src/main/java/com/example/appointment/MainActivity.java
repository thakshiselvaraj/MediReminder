package com.example.appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button5;
    private Button button4;
    private Button button10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opennextpage();
            }
        });

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openaddpage();
            }
        });

        button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openhomepage();
            }
        });
    }

    public void opennextpage(){
        Intent intent = new Intent(this, NewAppointment.class);
        startActivity(intent);
    }

    public void openaddpage(){
        Intent intent = new Intent(this, careCenter.class);
        startActivity(intent);
    }

    public void openhomepage(){
        Intent intent = new Intent(this, homepage.class);
        startActivity(intent);
    }
}