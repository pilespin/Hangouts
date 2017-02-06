package com.example.test.hangout;

import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.util.Log;

/**
 * Created by pilespin on 2/6/17.
 */

public class BaseClass extends AppCompatActivity {

    String filename = "lastOpened.txt";

    public String getTime() {

        Time time = new Time();   time.setToNow();
        int hour = time.hour;
        int min = time.minute;
        int sec = time.second;
        String currentTime = "Last opened is at ";

        currentTime += currentTime.valueOf(hour) + " h ";
        currentTime += currentTime.valueOf(min) + " min ";
        currentTime += currentTime.valueOf(sec) + " sec ";

        return (currentTime);
    }

    public void onRestart() {
        super.onRestart();
        Log.d("------ BASE ------ : ", "On Restart called");

        String ret = ioHelper.readFile(getBaseContext(), filename);
        if (ret != null && ret.length() > 0)
            Toast.putToast(getBaseContext(), ret);

    }

    public void onResume() {
        super.onResume();
    }

    public void onStart() {
        super.onStart();
    }

    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        super.onStop();
        Log.d("------ BASE ------ : ", "On Stop called");

        ioHelper.writeToFile(getBaseContext(), filename, getTime());
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d("------ BASE ------ : ", "On destroy called");
    }
}
