package com.example.medicineapp.Appoinment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.medicineapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class activityAppoinment extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {


    EditText txtCname, txtSpecial,txtDocName, txtadd, txtphone, editTextDate, editTextTime;
    Button button;
    DatabaseReference dbRef;
    Appointment appointment;
    FirebaseDatabase firebaseDatabase;
    Button buttonH;

    private static final String TAG = "MainActivity";
    private TextView displayDate;
    private DatePickerDialog.OnDateSetListener displayDateSetListener;
    private TextView displayTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinment);

        txtCname = (EditText)findViewById(R.id.eTTCenterName);
        txtSpecial = (EditText)findViewById(R.id.eTTSpeciality);
        txtDocName= (EditText)findViewById(R.id.eTTdocName);
        txtadd = (EditText)findViewById(R.id.eTTAddress);
        txtphone = (EditText)findViewById(R.id.eTTPhone);
        editTextDate = (EditText)findViewById(R.id.eTTDate);

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int date = cal.get(Calendar.DATE);

                DatePickerDialog dialog = new DatePickerDialog(
                        activityAppoinment.this,
                        android.R.style.Theme_Holo_Dialog_NoActionBar_MinWidth,
                        displayDateSetListener,
                        year,month,date);

                dialog.getWindow().setBackgroundDrawable((new ColorDrawable(Color.TRANSPARENT)));
                dialog.show();

            }
        });

        displayDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                month = month +1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + date + "/" + year);

                String day = month + "/" + date + "/" + year;
                editTextDate.setText(day);
            }
        };
        editTextTime = (EditText)findViewById(R.id.eTTtime);
        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });


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
                appointment.setDate(editTextDate.getText().toString().trim());
                appointment.setTime(editTextTime.getText().toString().trim());




                String id = dbRef.push().getKey();
                dbRef.child(id).setValue(appointment);

                Toast.makeText(activityAppoinment.this, "saved successfully",Toast.LENGTH_LONG).show();
                opennewpage();

                String nameValue = txtCname.getText().toString();
                Intent intent = new Intent(activityAppoinment.this, Reminder.class);
                intent.putExtra("Name",nameValue);
                startActivity(intent);

            }


        });
    }


    public void opennewpage (){
        Intent intent = new Intent(this, AppointmentDetail.class);
        startActivity(intent);
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textView = (TextView)findViewById(R.id.eTTtime);
        textView.setText(hourOfDay + ":" + minute);

    }
}