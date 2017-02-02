package com.example.lol.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.lol.myapplication.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////////////////////////////////////////////////////

        dbHelper dbHelper = new dbHelper(this);
        SQLiteDatabase bdd = dbHelper.getWritableDatabase();

        dbHelper.insertContact(new Contact("John", "Doe", null, "", ""));

//        //read
//        String titre = "Doe";
//        Cursor c = bdd.query(
//                "contacts",
//                new String[] {"firstname", "lastname"},
//                "lastname LIKE \"" + titre +"\"",
//                null, null, null, null);

        Cursor c = bdd.rawQuery("SELECT * FROM contacts", null);

        c.moveToFirst();

        String firstname    = "";
        String lastname     = "";
        String phone        = "";
        String email        = "";
        String city         = "";

        if (c.getCount() > 0) {

            int index = -1;
            if ((index = c.getColumnIndex("firstname")) != -1)
                firstname = c.getString(index);
            if ((index = c.getColumnIndex("lastname")) != -1)
                lastname = c.getString(index);
            if ((index = c.getColumnIndex("phone")) != -1)
                phone = c.getString(index);
            if ((index = c.getColumnIndex("email")) != -1)
                email = c.getString(index);
            if ((index = c.getColumnIndex("city")) != -1)
                city = c.getString(index);

            Contact aa = new Contact(firstname, lastname, phone, email, city);

            Log.d("------ MY LG ------ : ", aa.getFirstname());
            Log.d("------ MY LG ------ : ", aa.getLastname());
            Log.d("------ MY LG ------ : ", aa.getPhone());
            Log.d("------ MY LOG ------ : ", "getcount superieur a 0");

        }
        else
        {
            Log.d("------ MY LOG ------ : ", "getcount inferieur ou = a 0");
        }

//        Cursor cursor = db.query(
//                FeedEntry.TABLE_NAME,                     // The table to query
//                projection,                               // The columns to return
//                selection,                                // The columns for the WHERE clause
//                selectionArgs,                            // The values for the WHERE clause
//                null,                                     // don't group the rows
//                null,                                     // don't filter by row groups
//                sortOrder                                 // The sort order
//        );


        /////////////////////////////////////////////////////
    }

    public void createContact(View view) {
        Log.d("------ MY LOG ------ : ", "The createContact function was called");
        Intent intent = new Intent(this, createContact.class);
        startActivity(intent);
    }
}
