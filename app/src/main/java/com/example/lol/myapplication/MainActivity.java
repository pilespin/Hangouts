package com.example.lol.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.lol.myapplication.";

    public Contact getContactInCursor(Cursor c) {

        String firstname    = "";
        String lastname     = "";
        String phone        = "";
        String email        = "";
        String city         = "";

        if (c.getColumnCount() > 0) {
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

            Contact contact = new Contact(firstname, lastname, phone, email, city);
            return (contact);
        }
        else
        {
            Log.d("------ MY LOG ------ : ", "Empty cursor");
            return (null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("------ MY LG ------ : ", "On create CALLED of mainActivity");
        /////////////////////////////////////////////////////

        dbHelper dbHelper = new dbHelper(this);
        SQLiteDatabase bdd = dbHelper.getWritableDatabase();

        dbHelper.insertContact(new Contact("John", "Doe", "062222222", "", ""));
        dbHelper.insertContact(new Contact("Foo", "Bar", "065555445", "fb@gg.zz", "Ticyy"));

        Cursor c = bdd.rawQuery("SELECT * FROM contacts", null);

        c.moveToFirst();

        Log.d("------ MY LG ------ : ", "BEFORE WHILE PASSED");
        while (c.isAfterLast() == false) {
//        for (c.moveToFirst() ; !c.isLast() ; c.moveToNext()) {
        Log.d("------ MY LG ------ : ", "ON WHILE PASSED");
        Log.d("------ MY NL ------ : ", "");
            Contact contact = getContactInCursor(c);

//            if (contact.getFirstname().length() > 0)
                Log.d("------ MY LG ------ : ", contact.getFirstname());
////            if (contact.getLastname().length() > 0)
                Log.d("------ MY LG ------ : ", contact.getLastname());
////            if (contact.getPhone().length() > 0)
                Log.d("------ MY LG ------ : ", contact.getPhone());
////            if (contact.getEmail().length() > 0)
                Log.d("------ MY LG ------ : ", contact.getEmail());
////            if (contact.getCity().length() > 0)
//                Log.d("------ MY LG ------ : ", contact.getCity());
            c.moveToNext();
        }
        Log.d("------ MY LG ------ : ", "AFTER WHILE PASSED");

//        //read
//        String titre = "Doe";
//        Cursor c = bdd.query(
//                "contacts",
//                new String[] {"firstname", "lastname"},
//                "lastname LIKE \"" + titre +"\"",
//                null, null, null, null);

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
        Log.d("------ MY LOG ------ : ", "The createContactActivity function was called");
        Intent intent = new Intent(this, createContactActivity.class);
        startActivity(intent);
    }
}
