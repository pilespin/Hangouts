package com.example.test.hangout;

import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by pilespin on 2/13/17.
 */

public class lib {

    public static String getString(String str)    {
        if (str != null && str.length() > 0)
            return (str);
        else
            return ("");
    }

    public static String getInsertedValue(EditText e) {

        if (e == null)
            return ("");

        String msg = e.getText().toString();
        if (msg != null && msg.length() > 0)
            return (msg);
        else
            return ("");
    }

    public static String getSmsTime(String timeFormated) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(simpleDateFormat.parse(timeFormated));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int month = Integer.parseInt(String.valueOf(calendar.get(Calendar.MONTH)));
        month++;
        return (calendar.get(Calendar.YEAR) + " " +
                month + " " +
                calendar.get(Calendar.DATE) + " " +
                calendar.get(Calendar.HOUR) + "H " +
                calendar.get(Calendar.MINUTE) + "m " +
                calendar.get(Calendar.SECOND) + "s");
    }
}
