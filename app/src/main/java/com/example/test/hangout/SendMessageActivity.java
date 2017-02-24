package com.example.test.hangout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SendMessageActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        //SET TITTLE
        Contact c = intentHelper.getContact(getIntent());
        setTitle(c.getFirstname() + " " + c.getLastname());

        dbHelper dbHelper = new dbHelper(getBaseContext());

        List<sms> allSms = dbHelper.getSmsByPhone(getBaseContext(), c.getPhone());

        if (allSms != null) {

            final ListView mListView;
            mListView = (ListView) findViewById(R.id.listViewSms);

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
