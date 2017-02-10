package com.example.test.hangout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ShowContactActivity extends BaseClass {

    public final static String EXTRA_MESSAGE = "com.example.lol.myapplication.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SendMessageActivity.class));
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        /////////////////////////////////////
        Intent intent = getIntent();
        String firstname = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Firstname");
        String lastname = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Lastname");
        String phone = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Phone");
        String email = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Email");
        String city = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "City");

//        TextView textView = new TextView(this);
//        textView.setTextSize(40);
//        textView.setText(firstname + lastname + phone + email + city);

        setTitle(firstname + " " + lastname);

        String allinfo = "";

        if (firstname.length() > 0) {
            allinfo += "\n" + firstname;
        }
        if (lastname.length() > 0) {
            allinfo += "\n\n" + lastname;
        }
        if (phone.length() > 0) {
            allinfo += "\n\n" + phone;
        }
        if (email.length() > 0) {
            allinfo += "\n\n" + email;
        }
        if (city.length() > 0) {
            allinfo += "\n\n" + city;
        }

        TextView tvfname = (TextView) findViewById(R.id.displayContactInformationAll);
        tvfname.setText(allinfo);
    }

    public void deleteContact(View view) {

        dbHelper dbHelper = new dbHelper(getBaseContext());

        Intent intent = getIntent();
        String phone = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Phone");
        dbHelper.deleteContactByPhone(getBaseContext(), phone);

        startActivity(new Intent(this, MainActivity.class));

    }

    public void modifyContact(View view) {

        Intent oldintent = getIntent();
        Intent newintent = new Intent(getBaseContext(), CreateContactActivity.class);

        newintent.putExtra(EXTRA_MESSAGE + "Firstname", oldintent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Firstname"));
        newintent.putExtra(EXTRA_MESSAGE + "Lastname", oldintent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Lastname"));
        newintent.putExtra(EXTRA_MESSAGE + "Phone", oldintent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Phone"));
        newintent.putExtra(EXTRA_MESSAGE + "Email", oldintent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Email"));
        newintent.putExtra(EXTRA_MESSAGE + "City", oldintent.getStringExtra(MainActivity.EXTRA_MESSAGE + "City"));

        startActivity(newintent);

    }
}
