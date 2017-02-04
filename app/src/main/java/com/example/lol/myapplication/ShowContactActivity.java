package com.example.lol.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ShowContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);

        Intent intent = getIntent();
        String firstname = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Firstname");
        String lastname = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Lastname");
        String phone = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Phone");
        String email = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Email");
        String city = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "City");

        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(firstname + lastname + phone + email + city);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_show_contact);
        layout.addView(textView);

    }

    public void deleteContact(View view) {
        Log.d("------ MY LOG ------ : ", "call function deleteContact");

        Intent intent = getIntent();
        String phone = intent.getStringExtra(MainActivity.EXTRA_MESSAGE + "Phone");
        dbHelper dbHelper = new dbHelper(this);

        dbHelper.deleteContactByPhone(getBaseContext(), phone);
//        SQLiteDatabase bdd = dbHelper.getWritableDatabase();
//
//        if (bdd.delete("contacts", "phone=?", new String[]{phone}) > 0)
//            Log.d("------ MY LOG ------ : ", "delete ROW OK");
//        else
//            Log.d("------ MY LOG ------ : ", "delete NOT DELETE KO");

        Toast.putToast(getApplicationContext() , getString(R.string.toastcontactDeleted));

        startActivity(new Intent(this, MainActivity.class));
    }
}
