package com.example.test.hangout;

import android.widget.EditText;

/**
 * Created by pilespin on 2/13/17.
 */

public class lib {

    public static String getInsertedValue(EditText e) {

//        EditText editText = (EditText)findViewById(id);
        if (e == null)
            return ("");

        String msg = e.getText().toString();
        if (msg != null && msg.length() > 0)
            return (msg);
        else
            return ("");
    }
}
