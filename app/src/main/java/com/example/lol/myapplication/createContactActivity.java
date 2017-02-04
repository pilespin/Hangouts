package com.example.lol.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class createContactActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.lol.myapplication.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
    }

    /** Called when the user clicks the Send button */

    public String getInsertedValue(int id) {

        EditText editText = (EditText)findViewById(id);
        String msg = editText.getText().toString();
        if (msg.length() > 0)
            return (msg);
        else
            return ("");
    }

    public void addContactToDatabase(View view) {
        Log.d("------ MY LOG ------ : ", "The sendMessage function was called");
        Intent intent = new Intent(this, MainActivity.class);

        dbHelper dbHelper = new dbHelper(this);

        dbHelper.insertContact(new Contact(
                getInsertedValue(R.id.Firstname),
                getInsertedValue(R.id.Lastname),
                getInsertedValue(R.id.Phone),
                getInsertedValue(R.id.Email),
                getInsertedValue(R.id.City)
        ));

        Toast.putToast(getApplicationContext(), getString(R.string.toastContactAdded));

//        intent.putExtra(EXTRA_MESSAGE + "Firstname", getInsertedValue(R.id.Firstname));
//        intent.putExtra(EXTRA_MESSAGE + "Lastname", getInsertedValue(R.id.Lastname));
//        intent.putExtra(EXTRA_MESSAGE + "Phone", getInsertedValue(R.id.Phone));
//        intent.putExtra(EXTRA_MESSAGE + "Email", getInsertedValue(R.id.Email));
//        intent.putExtra(EXTRA_MESSAGE + "City", getInsertedValue(R.id.City));
//
        startActivity(intent);
    }
}
