package com.example.medisafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtVMsg = findViewById(R.id.txt1);
        txtVMsg.setText(R.string.title_medicine_name);

        TextView txtVMsg2 = findViewById(R.id.txt2);
        txtVMsg2.setText(R.string.medicine_days);

        TextView txtVMsg3 = findViewById(R.id.checkBox1);
        txtVMsg3.setText(R.string.check_box);

        TextView txtVMsg11 = findViewById(R.id.txtnew);
        txtVMsg11.setText(R.string.tooltext);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);


        TextView txtVMsgnew = findViewById(R.id.btn3);
        txtVMsgnew.setText(R.string.add_reminder);

        TextView txtVMsgnew1 = findViewById(R.id.btn4);
        txtVMsgnew1.setText(R.string.save_info);

        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmainpage();
            }
        });
       /* TextView txtVMsg4 = findViewById(R.id.rd1);
        txtVMsg4.setText(R.string.radio_1);

        TextView txtVMsg5 = findViewById(R.id.rd2);
        txtVMsg5.setText(R.string.radio_2);

        TextView txtVMsg6 = findViewById(R.id.rd3);
        txtVMsg6.setText(R.string.radio_2);

        TextView txtVMsg7 = findViewById(R.id.rd4);
        txtVMsg7.setText(R.string.radio4);

        TextView txtVMsg8 = findViewById(R.id.rd5);
        txtVMsg8.setText(R.string.radio5);
        TextView txtVMsg9 = findViewById(R.id.rd6);
        txtVMsg9.setText(R.string.radio_6);
        TextView txtVMsg10 = findViewById(R.id.rd7);
        txtVMsg10.setText(R.string.radio_7);*/


    }
    public void openmainpage(){
        Intent intent = new Intent(this, mainpage.class);
        startActivity(intent);
    }




}