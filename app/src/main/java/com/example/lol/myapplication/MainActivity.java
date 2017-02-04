package com.example.lol.myapplication;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("------ MY LG ------ : ", "On create CALLED of mainActivity");

        dbHelper dbHelper = new dbHelper(getBaseContext());

        final List<Contact>         allContact  = dbHelper.getAllContact(getBaseContext());
        final ArrayList<String>     allname     = new ArrayList<String>();

        Iterator i = allContact.iterator();
        while (i.hasNext())
        {
            Contact co = (Contact)i.next();
            allname.add(co.getFirstname() + " " + co.getLastname());
        }

        //Listview
        /////////////////////////
        final ListView mListView;
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.putToast(getApplicationContext(), "Click " + allname.get(position));
                Intent intent = new Intent(MainActivity.this, ShowContactActivity.class);

                intent.putExtra(EXTRA_MESSAGE + "Firstname", allContact.get(position).getFirstname());
                intent.putExtra(EXTRA_MESSAGE + "Lastname", allContact.get(position).getLastname());
                intent.putExtra(EXTRA_MESSAGE + "Phone", allContact.get(position).getPhone());
                intent.putExtra(EXTRA_MESSAGE + "Email", allContact.get(position).getEmail());
                intent.putExtra(EXTRA_MESSAGE + "City", allContact.get(position).getCity());

                startActivity(intent);
            }
        });

        //android.R.layout.simple_list_item_1 est une vue disponible de base dans le SDK android,
        //Contenant une TextView avec comme identifiant "@android:id/text1"

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, allname);
        mListView.setAdapter(adapter);
        /////////////////////////

    }

    public void createContact(View view) {
        Log.d("------ MY LOG ------ : ", "The createContactActivity function was called");
        startActivity(new Intent(this, createContactActivity.class));
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
