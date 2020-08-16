package com.example.appointment;

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
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class careCenter extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private static final String TAG = "careCenter";
    private TextView displayDate;
    private DatePickerDialog.OnDateSetListener displayDateSetListener;

    private TextView displayTime;

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_center);

        displayTime = (TextView) findViewById(R.id.textView16);
        displayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opennewpage();
            }
        });

        displayDate = (TextView) findViewById(R.id.textView15);
        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int date = cal.get(Calendar.DATE);

                DatePickerDialog dialog = new DatePickerDialog(
                        careCenter.this,
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
                displayDate.setText(day);
            }
        };

    }
        public void opennewpage (){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        TextView textView = (TextView)findViewById(R.id.textView16);
        textView.setText(i + ":" + i1);

    }
}