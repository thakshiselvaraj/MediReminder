package com.example.medicineapp;

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

public class AddReminder extends AppCompatActivity implements View.OnClickListener {

    private int notificationId = 1;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);


        // Set onClick Listener
        findViewById(R.id.setBtn).setOnClickListener(this);
        editText = findViewById(R.id.editText);


        editText.setText(getIntent().getStringExtra("Name"));



    }

    @Override
    public void onClick(View v) {

         editText = findViewById(R.id.editText);
        TimePicker timePicker = findViewById(R.id.timePicker);

        // Intent
        Intent intent = new Intent(AddReminder.this, AlramReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("todo", editText.getText().toString());

        // PendingIntent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                AddReminder.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT
        );

        // AlarmManager
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        if (v.getId() == R.id.setBtn) {
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
            Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
        }

        opendisplaydetails();

    }

    private void opendisplaydetails() {
        Intent intent = new Intent(this, DisplayDetails.class);
        startActivity(intent);
    }


}