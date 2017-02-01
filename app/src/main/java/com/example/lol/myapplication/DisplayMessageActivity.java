package com.example.lol.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String firstname = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Firstname");
        String lastname = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Lastname");
        String phone = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Phone");
        String email = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Email");
        String city = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "City");

        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(firstname + lastname + phone + email + city);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);

    }
}
