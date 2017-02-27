package com.example.test.hangout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;

/**
 * Created by pilespin on 2/6/17.
 */

public class BaseClass extends AppCompatActivity {

    String filename = "lastOpened.txt";

    public void setColor() {

        String co = ioHelper.readFile(getBaseContext(), "color");
        if (co != null && co.length() > 0) {
            Toolbar tlb = (Toolbar) findViewById(R.id.toolbar);
            if (co.compareTo("blue") == 0)
                tlb.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            if (co.compareTo("green") == 0)
                tlb.setBackgroundColor(getResources().getColor(R.color.colorGreen));
            if (co.compareTo("red") == 0)
                tlb.setBackgroundColor(getResources().getColor(R.color.colorRed));
            if (co.compareTo("black") == 0)
                tlb.setBackgroundColor(getResources().getColor(R.color.colorBlack));
        }
    }

    public String getTime() {

        Time time = new Time();   time.setToNow();
        int hour = time.hour;
        int min = time.minute;
        int sec = time.second;
        String currentTime = getString(R.string.toastLastOpened);

        currentTime += currentTime.valueOf(hour) + " h ";
        currentTime += currentTime.valueOf(min) + " min ";
        currentTime += currentTime.valueOf(sec) + " sec ";

        return (currentTime);
    }

    public void onRestart() {
        super.onRestart();

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
        ioHelper.writeToFile(getBaseContext(), filename, getTime());
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
