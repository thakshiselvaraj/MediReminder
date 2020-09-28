package com.example.medicineapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AlramReceiver extends BroadcastReceiver {
    private  static  final String CHANNEL_ID= "SAMPLE_CHANNEL";

    @Override
    public void onReceive(Context context, Intent intent) {

        int notificationId = intent.getIntExtra("notificationId",0);
        String message = intent.getStringExtra("todo");

        String message1 = intent.getStringExtra("todo1");

        String message2 = intent.getStringExtra("todo");

        Intent mainIntent = new Intent(context,MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,0,mainIntent,0);

        NotificationManager myNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence channel_name = "My Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new  NotificationChannel(CHANNEL_ID,channel_name,importance);
            myNotificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID)

                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("It's time to take your Medicines")
                        .setContentText(message)
                        .setContentText(message1)
                        .setContentText(message2)
                        .setContentIntent(contentIntent)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setAutoCancel(true);



        myNotificationManager.notify(notificationId,builder.build());


    }
}
