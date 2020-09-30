package com.example.medisafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText txtCname, txtSpecial,txtDocName, txtadd, txtphone, date, time;
    Button button;
    DatabaseReference dbRef;
    Appointment appointment;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCname = (EditText)findViewById(R.id.eTTCenterName);
        txtSpecial = (EditText)findViewById(R.id.eTTSpeciality);
        txtDocName= (EditText)findViewById(R.id.eTTdocName);
        txtadd = (EditText)findViewById(R.id.eTTAddress);
        txtphone = (EditText)findViewById(R.id.eTTPhone);
        date = (EditText)findViewById(R.id.editTextDate);
        time = (EditText)findViewById(R.id.editTextTime);

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
                appointment.setDate(date.getText().toString().trim());
                appointment.setTime(time.getText().toString().trim());

                String id = dbRef.push().getKey();
                dbRef.child(id).setValue(appointment);

                Toast.makeText(MainActivity.this, "saved successfully",Toast.LENGTH_LONG).show();
                opennewpage();

            }


        });
    }

    public void opennewpage (){
        Intent intent = new Intent(this, AppointmentDetail.class);
        startActivity(intent);
    }





}