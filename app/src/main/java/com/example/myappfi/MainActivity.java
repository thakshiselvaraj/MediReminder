package com.example.myappfi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText txtDate,txtTime,txtWAmount;
    Button btnSave;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    AlarmApp alarmApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDate=(EditText)findViewById(R.id.txtDate);
        txtTime=(EditText)findViewById(R.id.txtTime);
        txtWAmount=(EditText)findViewById(R.id.txtWAmount);
        btnSave=(Button)findViewById(R.id.btnSave);

        alarmApp= new AlarmApp();

        ref= FirebaseDatabase.getInstance().getReference().child("AlarmApp");

        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                alarmApp.setDate(txtDate.getText().toString().trim());
                alarmApp.setTime(txtTime.getText().toString().trim());
                alarmApp.setAmount(txtWAmount.getText().toString().trim());

                String id=ref.push().getKey();
                ref.child(id).setValue(alarmApp);
                Toast.makeText(MainActivity.this,"Data inserted successfully",Toast.LENGTH_LONG).show();
                nextpage();
            }

        });

    }
public void nextpage(){
        Intent intent=new Intent(this,Retrieve.class);
        startActivity(intent);
}



}