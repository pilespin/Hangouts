package com.example.lol.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public List<Contact> getAllContact() {

        dbHelper dbHelper = new dbHelper(this);
        SQLiteDatabase bdd = dbHelper.getWritableDatabase();

        Cursor c = bdd.rawQuery("SELECT * FROM contacts", null);
        c.moveToFirst();

        Log.d("------ MY LG ------ : ", "BEFORE WHILE PASSED");
        List<Contact> allContact = new ArrayList<Contact>();

        while (c.isAfterLast() == false) {
            Log.d("------ MY LG ------ : ", "ON WHILE PASSED");
            Contact contact = getContactInCursor(c);
            if (contact != null)
                allContact.add(contact);
            c.moveToNext();
        }
        return (allContact);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("------ MY LG ------ : ", "On create CALLED of mainActivity");

        List<Contact>               allContact  = getAllContact();
        final ArrayList<String>     allname     = new ArrayList<String>();

        Iterator i = allContact.iterator();
        while (i.hasNext())
        {
            Contact co = (Contact)i.next();
            allname.add(co.getFirstname() + " " + co.getLastname());
        }

        Log.d("------ MY LG ------ : ", "AFTER WHILE PASSED");
        ///////////////////////////////////////////////////////
        final ListView mListView;

        mListView = (ListView) findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.putToast(getApplicationContext(), "Click " + allname.get(position));
            }
        });

        //android.R.layout.simple_list_item_1 est une vue disponible de base dans le SDK android,
        //Contenant une TextView avec comme identifiant "@android:id/text1"

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, allname);
        mListView.setAdapter(adapter);

    }

    public void createContact(View view) {
        Log.d("------ MY LOG ------ : ", "The createContactActivity function was called");
        Intent intent = new Intent(this, createContactActivity.class);
        startActivity(intent);
    }
}

/////////////////////////////////////////////////////

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
