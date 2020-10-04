package com.example.myappfi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Retrieve extends AppCompatActivity {

    TextView a,b,c;
    Button btnShow;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        a=(TextView)findViewById(R.id.dateview);
        b=(TextView)findViewById(R.id.timeview);
        c=(TextView)findViewById(R.id.amountview);
        btnShow=(Button)findViewById(R.id.btnShow);

        ref= FirebaseDatabase.getInstance().getReference().child("AlarmApp").child("1");

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot snapshot) {

                        String date = snapshot.child("date").getValue().toString();
                        String time=snapshot.child("time").getValue().toString();
                        String amount= snapshot.child("amount").getValue().toString();
                        a.setText(date);
                        b.setText(time);
                        c.setText(amount);
                    }

                    @Override
                    public void onCancelled( DatabaseError error) {

                }
                });
            }
        });

    }
}