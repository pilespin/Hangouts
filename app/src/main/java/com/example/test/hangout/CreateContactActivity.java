package com.example.test.hangout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CreateContactActivity extends AppCompatActivity {

    private String firstname    = "";
    private String lastname     = "";
    private String phone        = "";
    private String email        = "";
    private String city         = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContactToDatabase(view);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        /////////////////////
        Intent intent = getIntent();
        firstname = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Firstname");
        lastname = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Lastname");
        phone = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Phone");
        email = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Email");
        city = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "City");

        EditText e1 = (EditText)findViewById(R.id.Firstname);
        e1.setText(firstname, TextView.BufferType.EDITABLE);

        EditText e2 = (EditText)findViewById(R.id.Lastname);
        e2.setText(lastname, TextView.BufferType.EDITABLE);

        EditText e3 = (EditText)findViewById(R.id.Phone);
        e3.setText(phone, TextView.BufferType.EDITABLE);

        EditText e4 = (EditText)findViewById(R.id.Email);
        e4.setText(email, TextView.BufferType.EDITABLE);

        EditText e5 = (EditText)findViewById(R.id.City);
        e5.setText(city, TextView.BufferType.EDITABLE);

        /////////////////////
    }

    public String getInsertedValue(int id) {

        EditText editText = (EditText)findViewById(id);
        String msg = editText.getText().toString();
        if (msg.length() > 0)
            return (msg);
        else
            return ("");
    }

    public void addContactToDatabase(View view) {
        Log.d("------ MY LOG ------ : ", "The addContactToDatabase function was called");

        dbHelper dbHelper = new dbHelper(this);

        dbHelper.deleteContactByPhone(getBaseContext(), phone);
        boolean ret =   dbHelper.insertContact(new Contact(
                getInsertedValue(R.id.Firstname),
                getInsertedValue(R.id.Lastname),
                getInsertedValue(R.id.Phone),
                getInsertedValue(R.id.Email),
                getInsertedValue(R.id.City)
        ));
        if (!ret)
        {
            Toast.putToast(getApplicationContext(), getString(R.string.toastUnknowError));
            ret = dbHelper.insertContact(new Contact(firstname, lastname, phone, email, city));
            if (!ret)
                Toast.putToast(getApplicationContext(), getString(R.string.toastUnknowError));
        }
        else
        {
            Toast.putToast(getApplicationContext(), getString(R.string.toastContactAdded));
            startActivity(new Intent(this, MainActivity.class));
        }
    }

}
