package com.example.test.hangout;

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

        smsHelper.sendSms(getBaseContext(), "5556", "Hello World");
        Log.d("------ BASE ------ : ", "SMS Sended");

    }
}
