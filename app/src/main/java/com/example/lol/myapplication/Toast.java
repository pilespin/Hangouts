package com.example.lol.myapplication;

import android.content.Context;

/**
 * Created by pilespin on 2/4/17.
 */

public class Toast {

    public Toast() {}

    public static void putToast(Context context, String msg) {
        CharSequence text = msg;
        int duration = android.widget.Toast.LENGTH_SHORT;

        android.widget.Toast toast = android.widget.Toast.makeText(context, text, duration);
        toast.show();
    }

}
