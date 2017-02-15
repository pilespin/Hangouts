package com.example.test.hangout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Iterator;
import java.util.List;

public class SendMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        Contact c = intentHelper.getContact(getIntent());
        setTitle(c.getFirstname() + " " + c.getLastname());

        TextView tvfname = (TextView) findViewById(R.id.displayAllSms);

        dbHelper dbHelper = new dbHelper(getBaseContext());

        List<sms> allSms = dbHelper.getSmsByPhone(getBaseContext(), c.getPhone());

        String allTextSms = "";

        Iterator i = allSms.iterator();
        while (i.hasNext())
        {
            sms s = (sms)i.next();
            allTextSms += "//////////////////////\n";
            if (s.getFromPhone().compareTo("OUT") == 0)
                allTextSms += "sended " + "\n";
            else if (s.getFromPhone().compareTo("IN") == 0)
                allTextSms += "received " + "\n";
//            allTextSms += s.getToPhone() + "\n";
            allTextSms += s.getTime() + "\n";
            allTextSms += s.getContent() + "\n";
        }

        tvfname.setText(allTextSms);

        ScrollView scroll = (ScrollView) this.findViewById(R.id.activity_send_message);
        scroll.fullScroll(View.FOCUS_DOWN);
//        scroll.fullScroll(scroll.FOCUS_DOWN);
//        scroll.scrollTo(0, 5000);
//        scroll.fullScroll(scroll.getBottom());
    }

    public void buttonSendMessage(View view) {

        smsHelper s = new smsHelper();

        String content = lib.getInsertedValue((EditText)findViewById(R.id.message_content));
        Contact c = intentHelper.getContact(getIntent());

        s.sendSms(getBaseContext(), c.getPhone(), content);

        dbHelper db = new dbHelper(getBaseContext());
        if (db.insertSms("OUT", c.getPhone(), content) == false)
            Log.d("------ SMS ------ : ", "Sms not save in database");
        else
            Log.d("------ SMS ------ : ", "Sms save in database");

        Intent intent = new Intent(getBaseContext(), MainActivity.class);
//        intentHelper.putContact(intent, c);
        startActivity(intent);
    }
}
