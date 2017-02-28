package com.example.test.hangout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends BaseClass {

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setColor();
        requestPermission(false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getBaseContext(), CreateContactActivity.class));
            }
        });
        ////////////////////////////

        dbHelper dbHelper = new dbHelper(getBaseContext());

//        dbHelper.insertContact(new Contact("John", "Doe", "0554656", "", ""));
//        dbHelper.insertContact(new Contact("Foo", "Bar", "0654656", "", ""));
//        dbHelper.insertContact(new Contact("Lee", "Kol", "061234", "", ""));
//        dbHelper.insertContact(new Contact("Mes", "Lev.J", "061237", "", ""));
//        dbHelper.insertContact(new Contact("avd", "56", "15555215556", "", ""));

        final List<Contact> allContact  = dbHelper.getAllContact(getBaseContext());
        final ArrayList<String> allname     = new ArrayList<String>();

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

                Intent intent = new Intent(MainActivity.this, ShowContactActivity.class);

                intentHelper.putOnceKey(intent, "Firstname", allContact.get(position).getFirstname());
                intentHelper.putOnceKey(intent, "Lastname", allContact.get(position).getLastname());
                intentHelper.putOnceKey(intent, "Phone", allContact.get(position).getPhone());
                intentHelper.putOnceKey(intent, "Email", allContact.get(position).getEmail());
                intentHelper.putOnceKey(intent, "City", allContact.get(position).getCity());
                finish();
                startActivity(intent);
            }
        });

        //android.R.layout.simple_list_item_1 est une vue disponible de base dans le SDK android,
        //Contenant une TextView avec comme identifiant "@android:id/text1"
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, allname);
        mListView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
            startActivity(new Intent(getBaseContext(), SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
