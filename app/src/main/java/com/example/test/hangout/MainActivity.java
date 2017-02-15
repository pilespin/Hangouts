package com.example.test.hangout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.R.id.list;

public class MainActivity extends BaseClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /////////////////////////////// PERMISSION ///////////////////////////////////

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},1);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.putToast(getBaseContext() ,"Good now you can send and receive sms");
        }
        else
        {
            Toast.putToast(getBaseContext(), "Bad");
            Log.d("------ BASE ------ : ", "You need to allow sending sms to send sms");
        }

        //////////////////////////////////////////////////////////////////////////////

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), CreateContactActivity.class));

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ////////////////////////////

        Log.d("------ MY LG ------ : ", "On create CALLED of mainActivity");

        dbHelper dbHelper = new dbHelper(getBaseContext());

//        if (dbHelper.insertSms("15555215556", "Helloworld") == false)
////                if (db.insertSms(phone, str) == false)
//            {
//                Log.d("------ SMS ------ : ", "Sms not save in database");
//            }
//            else
//            {
//                Log.d("------ SMS ------ : ", "Sms save in database");
//            }

        List<sms> allSms = dbHelper.getSmsAll(getBaseContext());


        ////////////////////////////////////////////////////////////

//        List<sms> allSms = dbHelper.getSmsByPhone(getBaseContext(), "15555215554");

        String allTextSms = "";

        Log.d("------ ALLSMS ------ : ", "ALLSMS BEGIN");
        Iterator j = allSms.iterator();
        while (j.hasNext())
        {
            sms s = (sms)j.next();
            allTextSms += s.getContent() + "\n";
            Log.d("------ ALLSMS ------ : ", "oooooooooooooooooooooooo");
            Log.d("------ FROM ------ : ", s.getFromPhone());
            Log.d("------ TO ------ : ", s.getToPhone());
            Log.d("------ CONTENT------ : ", s.getContent());
            Log.d("------ TIME ------ : ", s.getTime());
        }
        Log.d("------ ALLSMS ------ : ", allTextSms);
        Log.d("------ ALLSMS ------ : ", "ALLSMS END");

//        dbHelper.insertContact(new Contact("John", "Doe", "0554656", "", ""));
//        dbHelper.insertContact(new Contact("Foo", "Bar", "0654656", "", ""));
//        dbHelper.insertContact(new Contact("Lee", "Kol", "061234", "", ""));
//        dbHelper.insertContact(new Contact("Mes", "Lev.J", "061237", "", ""));

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
//                Toast.putToast(getApplicationContext(), "Click " + allname.get(position));
//                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                Intent intent = new Intent(MainActivity.this, ShowContactActivity.class);

                intentHelper.putOnceKey(intent, "Firstname", allContact.get(position).getFirstname());
                intentHelper.putOnceKey(intent, "Lastname", allContact.get(position).getLastname());
                intentHelper.putOnceKey(intent, "Phone", allContact.get(position).getPhone());
                intentHelper.putOnceKey(intent, "Email", allContact.get(position).getEmail());
                intentHelper.putOnceKey(intent, "City", allContact.get(position).getCity());

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
