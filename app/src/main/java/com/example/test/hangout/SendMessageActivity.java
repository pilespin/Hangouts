package com.example.test.hangout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SendMessageActivity extends BaseClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setColor();
        requestPermission(true);

        //SET TITTLE
        c = intentHelper.getContact(getIntent());
        setTitle(c.getFirstname() + " " + c.getLastname());

        displaySms();
        doTheAutoRefresh();
    }

    Contact c = null;
    private final Handler handler = new Handler();
    boolean killRefresh = false;

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    private void doTheAutoRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dbHelper dbHelper = new dbHelper(getBaseContext());

                int oldsize = dbHelper.getSmsRead(c.getPhone());

                List<sms> allSms = dbHelper.getSmsByPhone(getBaseContext(), c.getPhone());
                int tmp = allSms.size();

                if (oldsize != tmp) {
                    displaySms();
                }

                if (killRefresh == false)
                    doTheAutoRefresh();
            }
        }, 1000);
    }

    private void displaySms() {

        dbHelper dbHelper = new dbHelper(getBaseContext());

        List<sms> allSms = dbHelper.getSmsByPhone(getBaseContext(), c.getPhone());
        dbHelper.setSmsRead(c.getPhone(), allSms.size());

        final ListView mListView;
        mListView = (ListView) findViewById(R.id.listViewSms);

        if (allSms != null) {

            final ArrayList<String> all = new ArrayList<String>();
            Iterator i = allSms.iterator();
            while (i.hasNext()) {
                sms s = (sms) i.next();
                all.add("");
            }

            SmsAdapter adapter = new SmsAdapter(getBaseContext(), all, allSms);
            mListView.setAdapter(adapter);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(null);
        killRefresh = true;
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

        finish();
        startActivity(getIntent());
    }

}
