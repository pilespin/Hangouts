package com.example.test.hangout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ShowContactActivity extends AppCompatActivity {

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /////////////////////////////////////
        Intent intent = getIntent();
        String firstname = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Firstname");
        String lastname = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Lastname");
        String phone = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Phone");
        String email = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Email");
        String city = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "City");

        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(firstname + lastname + phone + email + city);

        setTitle(firstname + " " + lastname);

//        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_show_contact);
//        layout.addView(textView);
    }
}
