package com.example.myapplicationwear;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.RemoteInput;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.Toast;

public class NotificationsDetails extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_details);
        CharSequence charSequence = getMessagetText(getIntent());
        String reply_was = getResources().getString(R.string.reply_was);
        Toast.makeText(this, reply_was + charSequence , Toast.LENGTH_LONG).show();
    }

    private CharSequence getMessagetText(Intent intent){
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if( remoteInput != null){
            return  remoteInput.getCharSequence(NotificationUtils.EXTRA_VOICE_REPLY);
        }
        return  null;
    }
}
