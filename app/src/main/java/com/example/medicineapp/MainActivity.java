package com.example.medicineapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.FirebaseAppLifecycleListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

     Button btn_save;
    EditText medi_name;
    EditText dosage1;
    Spinner mySpinner;
    TextView spinvalue;
    String item;



    String[] medi_type = {"Choose Type","Pill(s)","Puffs(s)","Drop(s)","Injection(s)"};




    DatabaseReference reff;

    Medicine medicine;

    private int notificationId = 1;

    TimePicker timePicker;



    EditText choosetime;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currenthour;
    int currentminute;
    String ampm;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        medi_name = (EditText) findViewById(R.id.title_medicine_name);








        dosage1 = (EditText) findViewById(R.id.dosage_medi);


        spinvalue = findViewById(R.id.selected_type);



        mySpinner = (Spinner) findViewById(R.id.spinner1);
        mySpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,medi_type);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(arrayAdapter);


        btn_save=(Button) findViewById(R.id.btn_save);
        medicine = new Medicine();
        reff  = FirebaseDatabase.getInstance().getReference().child("Medicine");

        choosetime = findViewById(R.id.choosetime);


        choosetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int currenthour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentminute = calendar.get(Calendar.MINUTE);




                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            ampm = "PM";
                        } else {
                            ampm = "AM";
                        }
                        choosetime.setText(String.format("%02d:%02d", hourOfDay, minutes) + ampm);




                    }
                }, currenthour, currentminute, false);

                timePickerDialog.show();

            }
        });





        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Insert to DB
                SaveValue(item);
                Float dos = Float.parseFloat(dosage1.getText().toString().trim());


                medicine.setMediname(medi_name.getText().toString().trim());
                medicine.setTime(choosetime.getText().toString().trim());
                medicine.setDosage(dos);


                reff.push().setValue(medicine);
                Toast.makeText(MainActivity.this, "data insert successfully", Toast.LENGTH_LONG).show();

                //Next Activity intent
                opendisplayDetails();

                //alarm notification


                medi_name = findViewById(R.id.title_medicine_name);
                dosage1 = findViewById(R.id.dosage_medi);
                spinvalue = findViewById(R.id.selected_type);


                String namevalue = medi_name.getText().toString();
                Intent intent= new Intent(MainActivity.this,AddReminder.class);
                intent.putExtra("Name",namevalue);
                startActivity(intent);


                /*Intent intent = new Intent(MainActivity.this, AlramReceiver.class);
                intent.putExtra("notificationId", notificationId);
                intent.putExtra("todo", medi_name.getText().toString());


                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);


                    Calendar calendar = Calendar.getInstance();
                    int currenthour = calendar.get(Calendar.HOUR_OF_DAY);
                    int currentminute = calendar.get(Calendar.MINUTE);

                //calender time
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, currenthour);
                startTime.set(Calendar.MINUTE, currenthour);
                startTime.set(Calendar.SECOND, 0);
                long alarmStarTime = startTime.getTimeInMillis();

                if (calendar.before(Calendar.getInstance()))
                    alarmStarTime += AlarmManager.INTERVAL_DAY * 7;

                assert alarm != null;

                alarm.set(AlarmManager.RTC_WAKEUP, alarmStarTime, pendingIntent);

                Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_LONG).show();*/
            }




        });






                TextView txtVMsg11 = findViewById(R.id.txtnew);
        txtVMsg11.setText(R.string.tooltext);




        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);






    }





    private void opendisplayDetails() {
        Intent intent = new Intent(this, DisplayDetails.class);
        startActivity(intent);
    }


    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            item = (String) mySpinner.getSelectedItem();
            spinvalue.setText( item);


    }


    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    void SaveValue (String item){
        if(item == "Choose Type"){
            Toast.makeText(this, "Please select a medicine type",Toast.LENGTH_SHORT).show();
        }else{
            medicine.setType(item);

            Toast.makeText(this, "value saved",Toast.LENGTH_SHORT).show();

        }
    }

}
