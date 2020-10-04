package com.example.medisafe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Reminder extends AppCompatActivity implements View.OnClickListener{

    private int notificationId = 1;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        findViewById(R.id.remind_set).setOnClickListener(this);
        findViewById(R.id.remind_cancel).setOnClickListener(this);
        editText=findViewById(R.id.textRemind);

        editText.setText(getIntent().getStringExtra("Name"));
    }

    @Override
    public void onClick(View v) {

         editText = findViewById(R.id.textRemind);
        TimePicker timePicker = findViewById(R.id.TimePicker);

        Intent intent = new Intent(Reminder.this, AlarmReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("message", editText.getText().toString());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                Reminder.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT
        );

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        switch (v.getId()) {
            case R.id.remind_set:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                // Create time.
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                // Set Alarm
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, pendingIntent);
                Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
                break;

            case R.id.remind_cancel:
                alarmManager.cancel(pendingIntent);
                Toast.makeText(this, "Alarm Canceled.", Toast.LENGTH_SHORT).show();
                break;
        }

        opendetails();

    }

    private void opendetails(){
        Intent intent =  new Intent(this, AppointmentDetail.class);
        startActivity(intent);
    }
}