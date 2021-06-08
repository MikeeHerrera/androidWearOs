package com.example.myapplicationwear;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.RemoteInput;

public class MainActivity extends WearableActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);

        // Enables Always-on
        setAmbientEnabled();
    }


    public void notifyMe(View view){
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        Intent viewIntent = new Intent(this,NotificationsDetails.class);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0,viewIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long []{1000,500,1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder  NotificationBuilder = new NotificationCompat.Builder(this , NOTIFICATION_CHANNEL_ID);
        NotificationBuilder.setAutoCancel(true).setSmallIcon(R.drawable.ic_full_sad).setContentTitle("My Notification").setContentText("Hello from here").setContentInfo("Helloo")
                .setContentIntent(viewPendingIntent );

notificationManager.notify(1, NotificationBuilder.build());
    }

    public void voiceInput(View view){
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        Intent viewIntent = new Intent(this,NotificationsDetails.class);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0,viewIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        String reply_label = getResources().getString(R.string.reply_label);
        String[] reply_choices = getResources().getStringArray(R.array.reply_choices);
        RemoteInput remoteInput = new RemoteInput.Builder(NotificationUtils.EXTRA_VOICE_REPLY).setLabel(reply_label).setChoices(reply_choices).build();
        NotificationCompat.Action.Builder notificationActionBuilder = new NotificationCompat.Action.Builder(R.drawable.ic_expand_less_white_22, "My voice" , viewPendingIntent);
        notificationActionBuilder.addRemoteInput(remoteInput);
        NotificationCompat.Action action = notificationActionBuilder.build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long []{1000,500,1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder  NotificationBuilder = new NotificationCompat.Builder(this , NOTIFICATION_CHANNEL_ID);
        NotificationBuilder.setAutoCancel(true).setSmallIcon(R.drawable.ic_full_sad).setContentTitle("My Notification").setContentText("Hello from here").setContentInfo("info")
                .setContentIntent(viewPendingIntent ).addAction(action).extend(new NotificationCompat.WearableExtender().setContentAction(0));

        notificationManager.notify(1, NotificationBuilder.build());
    }
}
