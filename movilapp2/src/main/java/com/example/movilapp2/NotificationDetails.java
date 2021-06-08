package com.example.movilapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.RemoteInput;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class NotificationDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_details);
        CharSequence charSequence = getMessagetText(getIntent());
//        String reply_was = getResources().getString(R.string.reply_was);
//        Toast.makeText(this, reply_was + charSequence , Toast.LENGTH_LONG).show();
        String result = charSequence.toString();
        if(result.equalsIgnoreCase("yes")){

            //open map
            Intent mapIntent = new Intent(Intent.ACTION_VIEW);
            mapIntent.setData(Uri.parse("geo:40.758895, -73.85131"));
            startActivity(mapIntent);
        }else if(result.equalsIgnoreCase("no")){

            //open SMS
            int permissionCheck = ContextCompat.checkSelfPermission(
                    this, Manifest.permission.SEND_SMS);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                Log.i("Mensaje", "No se tiene permiso para enviar SMS.");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 225);
            } else {
                Log.i("Mensaje", "Se tiene permiso para enviar SMS!");

            }
            String phone = "2381521331";
            String text = "listo , ya quedo!";
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phone, null, text , null, null);
        }else if (result.equalsIgnoreCase("mat be")){
//show toast
        Toast.makeText(this,"Still deciding",Toast.LENGTH_LONG).show();


        }
    }

    private CharSequence getMessagetText(Intent intent){
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if( remoteInput != null){
            return  remoteInput.getCharSequence(NotificationUtils.EXTRA_VOICE_REPLY);
        }
        return  null;
    }
}
