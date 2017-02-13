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
import android.widget.EditText;

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

            String content = lib.getInsertedValue((EditText)findViewById(R.id.message_content));
            Contact c = intentHelper.getContact(getIntent());

            s.sendSms(getBaseContext(), c.getPhone(), content);

        }
        else
        {
            Log.d("------ BASE ------ : ", "You need to allow sending sms to send sms");
            return;
        }
    }
}
