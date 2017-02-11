package com.example.test.hangout;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SendMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
    }

    public void buttonSendMessage(View view) {

        smsHelper s = new smsHelper();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},1);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Log.d("------ BASE ------ : ", "GOOD");
            s.sendSms(getBaseContext(), "5554", "Hello World");
        }
        else
        {
            Log.d("------ BASE ------ : ", "You need to allow sending sms to send sms");
            return;
        }
    }
}
