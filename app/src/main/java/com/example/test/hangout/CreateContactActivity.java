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

public class CreateContactActivity extends AppCompatActivity {

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
        }
        else
        {
            Toast.putToast(getApplicationContext(), getString(R.string.toastContactAdded));
            startActivity(new Intent(this, MainActivity.class));
        }
    }

}
