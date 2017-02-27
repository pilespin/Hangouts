package com.example.test.hangout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class SettingsActivity extends BaseClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setColor();

    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    public void buttonSetBlue(View view) {
        ioHelper.writeToFile(getBaseContext(), "color", "blue");
        Toolbar tlb = (Toolbar) findViewById(R.id.toolbar);
        tlb.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    public void buttonSetGreen(View view) {
        ioHelper.writeToFile(getBaseContext(), "color", "green");
        Toolbar tlb = (Toolbar) findViewById(R.id.toolbar);
        tlb.setBackgroundColor(getResources().getColor(R.color.colorGreen));
    }

    public void buttonSetRed(View view) {
        ioHelper.writeToFile(getBaseContext(), "color", "red");
        Toolbar tlb = (Toolbar) findViewById(R.id.toolbar);
        tlb.setBackgroundColor(getResources().getColor(R.color.colorRed));
    }

    public void buttonSetBlack(View view) {
        ioHelper.writeToFile(getBaseContext(), "color", "black");
        Toolbar tlb = (Toolbar) findViewById(R.id.toolbar);
        tlb.setBackgroundColor(getResources().getColor(R.color.colorBlack));
    }

}
