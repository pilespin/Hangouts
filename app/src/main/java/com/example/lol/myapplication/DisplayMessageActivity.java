package com.example.lol.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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



        // Gets the data repository in write mode
//        dbHelper db = new dbHelper();
        dbHelper dbh = new dbHelper(this);

        SQLiteDatabase db = dbh.getWritableDatabase();
//
// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(dbHelper.FeedEntry.COLUMN_NAME_TITLE, "title");
        values.put(dbHelper.FeedEntry.COLUMN_NAME_SUBTITLE, "subtitle");

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(dbHelper.FeedEntry.TABLE_NAME, null, values);

    }
}
