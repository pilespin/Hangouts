package com.example.test.hangout;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

/**
 * Created by pilespin on 2/11/17.
 */

public class intentHelper {

    public final static String EXTRA_MESSAGE = "com.example.lol.myapplication.";

    public static Contact getContact(Intent intent) {

        String firstname = intent.getStringExtra(EXTRA_MESSAGE + "Firstname");
        String lastname = intent.getStringExtra(EXTRA_MESSAGE + "Lastname");
        String phone = intent.getStringExtra(EXTRA_MESSAGE + "Phone");
        String email = intent.getStringExtra(EXTRA_MESSAGE + "Email");
        String city = intent.getStringExtra(EXTRA_MESSAGE + "City");

        return (new Contact(firstname, lastname, phone, email, city));
    }

    public static void putContact(Intent intent, Contact contact) {

        intent.putExtra(EXTRA_MESSAGE + "Firstname", contact.getFirstname());
        intent.putExtra(EXTRA_MESSAGE + "Lastname", contact.getLastname());
        intent.putExtra(EXTRA_MESSAGE + "Phone", contact.getPhone());
        intent.putExtra(EXTRA_MESSAGE + "Email", contact.getEmail());
        intent.putExtra(EXTRA_MESSAGE + "City", contact.getCity());
    }

    public static void putOnceKey(Intent intent, String key, String content) {
        intent.putExtra(EXTRA_MESSAGE + key, content);
    }

}
