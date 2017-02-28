package com.example.test.hangout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ShowContactActivity extends BaseClass {

    Contact contact = null;

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setColorColapsing();

        contact = intentHelper.getContact(getIntent());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), SendMessageActivity.class);

                intentHelper.putContact(intent, contact);
                finish();
                startActivity(intent);
            }
        });

        Contact c = intentHelper.getContact(getIntent());

        setTitle(c.getFirstname() + " " + c.getLastname());

        String allinfo = "";

        if (c.getFirstname().length() > 0) {
            allinfo += "\n" + c.getFirstname();
        }
        if (c.getLastname().length() > 0) {
            allinfo += "\n\n" + c.getLastname();
        }
        if (c.getPhone().length() > 0) {
            allinfo += "\n\n" + c.getPhone();
        }
        if (c.getEmail().length() > 0) {
            allinfo += "\n\n" + c.getEmail();
        }
        if (c.getCity().length() > 0) {
            allinfo += "\n\n" + c.getCity();
        }

        TextView tvfname = (TextView) findViewById(R.id.displayContactInformationAll);
        tvfname.setText(allinfo);
    }

    public void deleteContact(View view) {

        dbHelper dbHelper = new dbHelper(getBaseContext());

        dbHelper.deleteContactByPhone(getBaseContext(), contact.getPhone());
        Toast.putToast(getBaseContext(), getString(R.string.toastcontactDeleted));
        startActivity(new Intent(this, MainActivity.class));

    }

    public void modifyContact(View view) {

        Intent oldintent = getIntent();
        Intent newintent = new Intent(getBaseContext(), CreateContactActivity.class);

        contact = intentHelper.getContact(oldintent);
        intentHelper.putContact(newintent, contact);
        finish();
        startActivity(newintent);

    }
}
